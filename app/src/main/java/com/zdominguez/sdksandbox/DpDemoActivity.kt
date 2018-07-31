package com.zdominguez.sdksandbox

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zdominguez.sdksandbox.databinding.ActivityDpDemoBinding

class DpDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityDpDemoBinding = DataBindingUtil.setContentView(this, R.layout.activity_dp_demo)
    }
}