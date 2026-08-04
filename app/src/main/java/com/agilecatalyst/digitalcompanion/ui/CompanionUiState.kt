package com.agilecatalyst.digitalcompanion.ui

import com.agilecatalyst.digitalcompanion.sdk.model.ConnectionState
import com.agilecatalyst.digitalcompanion.sdk.model.RokidDevice

data class CompanionUiState(
    val mockModeEnabled: Boolean = true,
    val discoveredDevices: List<RokidDevice> = emptyList(),
    val selectedDevice: RokidDevice? = null,
    val connectionState: ConnectionState = ConnectionState.DISCONNECTED,
    val brightness: Int = 60,
    val volume: Int = 45,
    val batteryPercent: Int = 0,
    val statusMessage: String = "Ready to scan on the phone.",
)
