package com.zdominguez.sdksandbox

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zdominguez.sdksandbox.bottomsheet.CustomShareActivity
import com.zdominguez.sdksandbox.databinding.ActivityBottomSheetsBinding
import com.zdominguez.sdksandbox.databinding.ActivityConstraintBinding

class BottomSheetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityBottomSheetsBinding>(this, R.layout.activity_bottom_sheets)
        binding.handlers = this
    }

    fun onShareClicked() {
        // Construct the intent we want to send
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
        shareIntent.type = "text/plain"

        // Ask Android to create the chooser for us
        val chooser = Intent.createChooser(shareIntent, getString(R.string.share_text))

        val customSharer = Intent(this, CustomShareActivity::class.java)
        val initialIntents = arrayOf(customSharer)
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, initialIntents)

        startActivity(chooser)
    }

    fun onShowModalClicked() {
        BottomSheetFragment().show(supportFragmentManager, "bottom_sheet")
    }

    class BottomSheetFragment : BottomSheetDialogFragment() {
        override fun onCreateView(inflater: LayoutInflater,
                                  container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            return ActivityConstraintBinding.inflate(inflater).root
        }
    }
}