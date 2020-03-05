package com.zdominguez.sdksandbox

import android.app.Application
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.zdominguez.sdksandbox.firebase.ABTestValues
import com.zdominguez.sdksandbox.firebase.RemoteConfigValues
import timber.log.Timber
import timber.log.Timber.DebugTree

class SdkSandboxApplication : Application() {
    lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(10)
            .build()
        remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(REMOTE_CONFIG_DEFAULTS + AB_TEST_DEFAULTS)
            fetchAndActivate()
        }
    }

    companion object {
        private val REMOTE_CONFIG_DEFAULTS = RemoteConfigValues.values()
            .associate { remoteConfig -> remoteConfig.key to remoteConfig.default }

        private val AB_TEST_DEFAULTS = ABTestValues.values()
            .associate { abTestValues -> abTestValues.key to abTestValues.default }
    }
}