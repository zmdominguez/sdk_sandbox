package com.zdominguez.sdksandbox;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.zdominguez.sdksandbox.databinding.DataBindingSpansBinding;
import com.zdominguez.sdksandbox.models.AdventureTimeCharacters;

import org.parceler.Parcels;

public class DataBindingSpans extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingSpansBinding binding = DataBindingUtil.setContentView(this, R.layout.data_binding_spans);

        Bundle bundle = getIntent().getExtras();
        AdventureTimeCharacters character = Parcels.unwrap(bundle.getParcelable("extra"));
        binding.setCharacter(character);
    }

    @BindingAdapter("charText")
    public static void setCharacterQuote(TextView view, AdventureTimeCharacters character) {
        Context activity = view.getContext();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(activity.getString(character.getQuote()));
        spannableStringBuilder.append(" - " + activity.getString(character.getName()),
                new ForegroundColorSpan(ContextCompat.getColor(activity, character.getColour())), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(spannableStringBuilder, TextView.BufferType.SPANNABLE);
    }
}
