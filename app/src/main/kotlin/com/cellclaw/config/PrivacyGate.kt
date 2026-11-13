package com.cellclaw.config

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Central privacy state for the native agent.
 *
 * Master Power OFF is authoritative: it clears Screen Access and Background Voice.
 * Screen-dependent tools must check canUseScreen(), including direct service paths.
 */
@Singleton
class PrivacyGate @Inject constructor(
    private val appConfig: AppConfig
) {
    val masterPower: Boolean
        get() = appConfig.masterPower

    val screenAccess: Boolean
        get() = masterPower && appConfig.screenAccess

    val backgroundVoice: Boolean
        get() = masterPower && appConfig.backgroundVoice

    fun setMasterPower(enabled: Boolean) {
        appConfig.masterPower = enabled
        if (!enabled) {
            appConfig.screenAccess = false
            appConfig.backgroundVoice = false
        }
    }

    fun setScreenAccess(enabled: Boolean): Boolean {
        if (!masterPower) {
            appConfig.screenAccess = false
            return false
        }
        appConfig.screenAccess = enabled
        return enabled
    }

    fun setBackgroundVoice(enabled: Boolean): Boolean {
        if (!masterPower) {
            appConfig.backgroundVoice = false
            return false
        }
        appConfig.backgroundVoice = enabled
        return enabled
    }

    fun canUseScreen(): Boolean = screenAccess
    fun canUseBackgroundVoice(): Boolean = backgroundVoice
}
