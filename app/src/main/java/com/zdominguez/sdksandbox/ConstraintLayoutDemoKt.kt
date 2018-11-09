package com.zdominguez.sdksandbox

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zdominguez.sdksandbox.databinding.ActivityConstraintBinding

class ConstraintLayoutDemoKt : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityConstraintBinding>(this, R.layout.activity_constraint)
    }
}
