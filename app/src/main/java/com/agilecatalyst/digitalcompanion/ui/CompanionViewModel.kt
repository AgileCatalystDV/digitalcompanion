package com.agilecatalyst.digitalcompanion.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.agilecatalyst.digitalcompanion.BuildConfig
import com.agilecatalyst.digitalcompanion.sdk.RokidFacadeProvider
import com.agilecatalyst.digitalcompanion.sdk.RokidSdkFacade
import com.agilecatalyst.digitalcompanion.sdk.model.ConnectionState
import com.agilecatalyst.digitalcompanion.sdk.model.RokidDevice

class CompanionViewModel : ViewModel() {
    var uiState by mutableStateOf(
        CompanionUiState(
            mockModeEnabled = BuildConfig.USE_MOCK_SDK,
            batteryPercent = 82,
        ),
    )
        private set

    private var rokidFacade: RokidSdkFacade = RokidFacadeProvider.create(uiState.mockModeEnabled)

    fun setMockMode(enabled: Boolean) {
        rokidFacade = RokidFacadeProvider.create(enabled)
        uiState = uiState.copy(
            mockModeEnabled = enabled,
            discoveredDevices = emptyList(),
            selectedDevice = null,
            connectionState = ConnectionState.DISCONNECTED,
            batteryPercent = if (enabled) 82 else 0,
            statusMessage = if (enabled) {
                "Mock mode enabled. Safe to deploy on the S26."
            } else {
                "Real Rokid mode is prepared but not wired yet."
            },
        )
    }

    fun scanForDevices() {
        val devices = rokidFacade.scanForDevices()
        uiState = uiState.copy(
            discoveredDevices = devices,
            selectedDevice = devices.firstOrNull(),
            statusMessage = if (devices.isEmpty()) {
                "No Rokid devices available in this mode."
            } else {
                "Found ${devices.size} Rokid device for dry-run."
            },
        )
    }

    fun connectSelectedDevice() {
        val device = uiState.selectedDevice ?: run {
            uiState = uiState.copy(statusMessage = "Scan first to select a device.")
            return
        }
        uiState = uiState.copy(
            connectionState = ConnectionState.CONNECTING,
            statusMessage = "Connecting to ${device.name}...",
        )
        val result = rokidFacade.connect(device)
        uiState = if (result.isSuccess) {
            uiState.copy(
                connectionState = ConnectionState.CONNECTED,
                batteryPercent = rokidFacade.readBatteryPercent(),
                statusMessage = "Connected to ${device.name}.",
            )
        } else {
            uiState.copy(
                connectionState = ConnectionState.DISCONNECTED,
                statusMessage = result.exceptionOrNull()?.message
                    ?: "Unable to connect to the selected Rokid device.",
            )
        }
    }

    fun disconnect() {
        rokidFacade.disconnect()
        uiState = uiState.copy(
            connectionState = ConnectionState.DISCONNECTED,
            statusMessage = "Disconnected from Rokid device.",
        )
    }

    fun setBrightness(level: Int) {
        val appliedLevel = rokidFacade.setGlassBrightness(level)
        uiState = uiState.copy(
            brightness = appliedLevel,
            statusMessage = "Brightness set to $appliedLevel.",
        )
    }

    fun setVolume(level: Int) {
        val appliedLevel = rokidFacade.setGlassVolume(level)
        uiState = uiState.copy(
            volume = appliedLevel,
            statusMessage = "Volume set to $appliedLevel.",
        )
    }

    fun refreshBattery() {
        uiState = uiState.copy(
            batteryPercent = rokidFacade.readBatteryPercent(),
            statusMessage = if (uiState.connectionState == ConnectionState.CONNECTED) {
                "Battery refreshed from current device."
            } else {
                "Battery uses mock values until a device is connected."
            },
        )
    }

    fun selectDevice(device: RokidDevice) {
        uiState = uiState.copy(
            selectedDevice = device,
            statusMessage = "${device.name} selected.",
        )
    }
}
