package com.zdominguez.sdksandbox

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.zdominguez.sdksandbox.databinding.ActivitySpannableStringsBindingImpl

class SpannableStringsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySpannableStringsBindingImpl>(this, R.layout.activity_spannable_strings)


        val clickableText = "reusable bags"

        val roboto = Typeface.create(ResourcesCompat.getFont(this, R.font.roboto_medium), Typeface.NORMAL)

        // TextView where link press is DIFFERENT text colour
        val spanBuilder = SpannableString("Change colours: " + clickableText)
        spanBuilder.withClickableSpan(clickableText, roboto) {
            Toast.makeText(this@SpannableStringsActivity, "Link clicked!", Toast.LENGTH_LONG).show()
        }

        binding.changeColorLink.apply {
            text = spanBuilder
            movementMethod = LinkMovementMethod.getInstance()
        }


        // TextView where link press is ALMOST SAME text colour
        val spanBuilder2 = SpannableString("Change alpha: " + clickableText)
        spanBuilder2.withClickableSpan(clickableText, roboto) {
            Toast.makeText(this@SpannableStringsActivity, "Link barely changing colours!", Toast.LENGTH_LONG).show()
        }

        binding.noChangeColorLink.apply {
            text = spanBuilder2
            movementMethod = LinkMovementMethod.getInstance()
        }

        val spanBuilder3 = SpannableString("No change: " + clickableText)
        spanBuilder3.withClickableSpan(clickableText, roboto) {
            Toast.makeText(this@SpannableStringsActivity, "Link not changing colours!", Toast.LENGTH_LONG).show()
        }
        binding.samples.apply {
            text = spanBuilder3
            movementMethod = LinkMovementMethod.getInstance()
        }
    }
}