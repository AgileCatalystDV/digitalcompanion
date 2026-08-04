package com.agilecatalyst.digitalcompanion.sdk

import com.agilecatalyst.digitalcompanion.sdk.model.RokidDevice

class MockRokidFacade : RokidSdkFacade {
    private val mockDevice = RokidDevice(
        id = "mock-rokid-ai-glasses",
        name = "Rokid AI Glasses",
        model = "AI Glasses",
        transport = "Mock BLE",
    )

    private var connectedDevice: RokidDevice? = null
    private var brightness = 60
    private var volume = 45
    private var batteryPercent = 82

    override fun scanForDevices(): List<RokidDevice> = listOf(mockDevice)

    override fun connect(device: RokidDevice): Result<Unit> {
        connectedDevice = device
        return Result.success(Unit)
    }

    override fun disconnect() {
        connectedDevice = null
    }

    override fun readBatteryPercent(): Int {
        if (connectedDevice != null && batteryPercent > 12) {
            batteryPercent -= 1
        }
        return batteryPercent
    }

    override fun setGlassBrightness(level: Int): Int {
        brightness = level.coerceIn(0, 100)
        return brightness
    }

    override fun setGlassVolume(level: Int): Int {
        volume = level.coerceIn(0, 100)
        return volume
    }
}
