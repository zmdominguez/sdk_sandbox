package com.zdominguez.sdksandbox.models;

import android.app.Activity;
import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import com.zdominguez.sdksandbox.R;

import org.parceler.Parcel;

import butterknife.ButterKnife;

@Parcel
public enum AdventureTimeCharacters {
    JAKE(R.id.jake_quote, R.string.jake_quote, R.string.jake, R.color.jake),
    FINN(R.id.finn_quote, R.string.finn_quote, R.string.finn, R.color.finn),
    LSP(R.id.lsp_quote, R.string.lsp_quote, R.string.lsp, R.color.lsp),
    PRINCESS_BUBBLEGUM(R.id.princess_bubblegum_quote, R.string.pb_quote, R.string.pb, R.color.pb),
    LEMONGRAB(R.id.lemongrab_quote, R.string.lemongrab_quote, R.string.lemongrab, R.color.lemongrab);

    private int mTextView;
    private int mQuote;
    private int mName;
    private int mColour;

    AdventureTimeCharacters(@IdRes int textView, @StringRes int quote, @StringRes int name, @ColorRes int colour) {
        mTextView = textView;
        mQuote = quote;
        mName = name;
        mColour = colour;
    }

    AdventureTimeCharacters(){
        //For parceler
    }

    public void setQuote(Activity activity) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(activity.getString(mQuote));
        spannableStringBuilder.append(" - " + activity.getString(mName),
            new ForegroundColorSpan(ContextCompat.getColor(activity, mColour)), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView textView = activity.findViewById(mTextView);
        textView.setText(spannableStringBuilder, TextView.BufferType.SPANNABLE);
    }

    public int getTextView() {
        return mTextView;
    }

    public int getQuote() {
        return mQuote;
    }

    public int getName() {
        return mName;
    }

    public int getColour() {
        return mColour;
    }
}
