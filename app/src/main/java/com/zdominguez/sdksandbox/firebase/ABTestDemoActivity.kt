package com.zdominguez.sdksandbox.firebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zdominguez.sdksandbox.R
import com.zdominguez.sdksandbox.SdkSandboxApplication
import com.zdominguez.sdksandbox.databinding.ActivityAbDemoBinding
import timber.log.Timber

class ABTestDemoActivity: AppCompatActivity() {
    lateinit var binding: ActivityAbDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ab_demo)
        binding.handlers = this
        refetch()
    }

    fun refetch() {
        val remoteConfig = (application as SdkSandboxApplication).remoteConfig

        remoteConfig.fetchAndActivate().addOnSuccessListener {
            // source: 0 - not found, 1 - local defaults, 2 - activated values

            val buttonColourValue = remoteConfig.getValue(ABTestValues.BUTTON_COLOUR.key)
            Timber.i("Returned button colour value? ${buttonColourValue.asString()} source: ${buttonColourValue.source}")
            binding.fetchedValue.text = "Fetched value from config = ${buttonColourValue.asString()}"

            val buttonCountValue = remoteConfig.getValue(ABTestValues.BUTTON_COUNT.key)
            Timber.i("Returned button count value? ${buttonCountValue.asLong()} source: ${buttonCountValue.source}")
            binding.buttonCount.text = "Fetched value from config for button count = ${buttonCountValue.asLong()}"
        }
    }
}