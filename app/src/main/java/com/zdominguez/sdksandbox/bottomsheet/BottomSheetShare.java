package com.zdominguez.sdksandbox.bottomsheet;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.zdominguez.sdksandbox.R;
import com.zdominguez.sdksandbox.databinding.ActivityShareBinding;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetShare extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityShareBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_share);
        binding.setHandlers(this);
    }

    public void onShare() {
        // Construct the intent we want to send
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        shareIntent.setType("text/plain");

        // Ask Android to create the chooser for us
        final Intent chooser = Intent.createChooser(shareIntent, getString(R.string.share_text));

        Intent customSharer = new Intent(this, CustomShareActivity.class);
        Intent[] initialIntents = new Intent[] {customSharer};
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, initialIntents);

        startActivity(chooser);
    }
}
