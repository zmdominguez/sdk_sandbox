package com.blogspot.droidista.sdksandbox;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Created by zarah.dominguez on 2/11/2015.
 */
public class ResourceAnnotationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_annotations);

        for (AdventureTimeCharacters character : AdventureTimeCharacters.values()) {
            character.setQuote(ResourceAnnotationsActivity.this);
        }
    }



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

        private void setQuote(Activity activity) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(activity.getString(mQuote));
            spannableStringBuilder.append(" - " + activity.getString(mName), new ForegroundColorSpan(activity.getColor(mColour)), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            TextView textView = ButterKnife.findById(activity, mTextView);
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
}
