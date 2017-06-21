package com.zdominguez.sdksandbox;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.zdominguez.sdksandbox.databinding.ActivityInflateLayoutParamsBinding;
import com.zdominguez.sdksandbox.databinding.ItemInflatedLayoutBinding;

public class InflateLayoutParams extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityInflateLayoutParamsBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_inflate_layout_params);
        inflateNoParent(binding.noParentContainer);
        inflateWithParent(binding.withParentContainer);
    }

    private void inflateNoParent(LinearLayout noParentContainer) {
        noParentContainer.addView(ItemInflatedLayoutBinding.inflate(getLayoutInflater()).getRoot());
        noParentContainer.addView(ItemInflatedLayoutBinding.inflate(getLayoutInflater()).getRoot());
        noParentContainer.addView(ItemInflatedLayoutBinding.inflate(getLayoutInflater()).getRoot());
    }

    private void inflateWithParent(LinearLayout withParentContainer) {
        withParentContainer.addView(ItemInflatedLayoutBinding.inflate(getLayoutInflater(), withParentContainer, false).getRoot());
        withParentContainer.addView(ItemInflatedLayoutBinding.inflate(getLayoutInflater(), withParentContainer, false).getRoot());
        withParentContainer.addView(ItemInflatedLayoutBinding.inflate(getLayoutInflater(), withParentContainer, false).getRoot());

    }
}
