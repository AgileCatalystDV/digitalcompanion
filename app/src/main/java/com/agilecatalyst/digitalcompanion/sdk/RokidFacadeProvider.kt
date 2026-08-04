package com.agilecatalyst.digitalcompanion.sdk

import com.agilecatalyst.digitalcompanion.BuildConfig

object RokidFacadeProvider {
    fun create(mockModeEnabled: Boolean = BuildConfig.USE_MOCK_SDK): RokidSdkFacade {
        return if (mockModeEnabled) {
            MockRokidFacade()
        } else {
            PendingRealRokidFacade()
        }
    }
}
