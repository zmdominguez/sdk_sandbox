package com.zdominguez.sdksandbox

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
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

    fun onEditorAction(view: TextView, actionId: Int?, event: KeyEvent?) : Boolean {
        var message = "Action clicked!"
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            message = "Send was clicked!"
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG)
            .show()
        return false
    }
}