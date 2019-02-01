package com.zdominguez.sdksandbox

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zdominguez.sdksandbox.databinding.ActivityConstraintBinding

class ConstraintLayoutDemoKt : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityConstraintBinding>(this, R.layout.activity_constraint)
    }
}
