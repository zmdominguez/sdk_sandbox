package com.zdominguez.sdksandbox

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

fun SpannableString.withClickableSpan(clickableText: String, typeface: Typeface?, onClickListener: () -> Unit): SpannableString {
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View?) = onClickListener.invoke()
        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = false
        }
    }
    val clickableTextStart = indexOf(clickableText)
    setSpan(clickableSpan,
        clickableTextStart,
        clickableTextStart + clickableText.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    setSpan(FontSpan(typeface), clickableTextStart,
        clickableTextStart + clickableText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}