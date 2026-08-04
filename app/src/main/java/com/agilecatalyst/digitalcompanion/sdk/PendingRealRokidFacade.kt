package com.agilecatalyst.digitalcompanion.sdk

import com.agilecatalyst.digitalcompanion.sdk.model.RokidDevice

class PendingRealRokidFacade : RokidSdkFacade {
    override fun scanForDevices(): List<RokidDevice> = emptyList()

    override fun connect(device: RokidDevice): Result<Unit> {
        return Result.failure(
            IllegalStateException("Real Rokid SDK integration is not wired yet."),
        )
    }

    override fun disconnect() = Unit

    override fun readBatteryPercent(): Int = 0

    override fun setGlassBrightness(level: Int): Int = level.coerceIn(0, 100)

    override fun setGlassVolume(level: Int): Int = level.coerceIn(0, 100)
}
