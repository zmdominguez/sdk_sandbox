package com.zdominguez.sdksandbox

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zdominguez.sdksandbox.databinding.ActivityDbImportBinding

class DataBindingImportBug : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDbImportBinding>(this, R.layout.activity_db_import)
        binding.handlers = this
    }

    fun onEditorAction() : Boolean {
        Toast.makeText(this, "Action clicked!", Toast.LENGTH_LONG).show()
        return false
    }
}