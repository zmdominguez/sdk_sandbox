package com.zdominguez.sdksandbox.firebase

enum class RemoteConfigValues(val key: String, val default: Boolean) {
    REMOTE_CONFIG_ENABLED("remote_config_enabled", false)
}