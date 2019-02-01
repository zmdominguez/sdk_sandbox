package com.zdominguez.sdksandbox.bottomsheet;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
