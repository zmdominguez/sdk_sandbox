package com.zdominguez.sdksandbox.bottomsheet;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zdominguez.sdksandbox.R;
import com.zdominguez.sdksandbox.databinding.ActivityTextBinding;

public class CustomShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTextBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_text);
        binding.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
    }
}
