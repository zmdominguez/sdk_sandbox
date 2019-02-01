package com.zdominguez.sdksandbox;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ConstraintLayoutDemo extends AppCompatActivity {
    @BindView(R.id.checkbox) CheckBox mCheckbox;
    @BindView(R.id.container) ViewGroup mContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_compat);
        ButterKnife.bind(this);
        mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mContainer.setVisibility(View.VISIBLE);
                } else {
                    mContainer.setVisibility(View.GONE);
                }
            }
        });
    }
}
