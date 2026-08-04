package com.agilecatalyst.digitalcompanion.sdk

import com.agilecatalyst.digitalcompanion.sdk.model.RokidDevice

interface RokidSdkFacade {
    fun scanForDevices(): List<RokidDevice>
    fun connect(device: RokidDevice): Result<Unit>
    fun disconnect()
    fun readBatteryPercent(): Int
    fun setGlassBrightness(level: Int): Int
    fun setGlassVolume(level: Int): Int
}
