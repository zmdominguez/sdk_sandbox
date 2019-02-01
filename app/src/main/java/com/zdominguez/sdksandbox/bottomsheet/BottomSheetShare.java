package com.zdominguez.sdksandbox.bottomsheet;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zdominguez.sdksandbox.R;
import com.zdominguez.sdksandbox.databinding.ActivityShareBinding;

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
