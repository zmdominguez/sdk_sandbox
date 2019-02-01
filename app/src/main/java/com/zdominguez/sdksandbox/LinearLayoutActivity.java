package com.zdominguez.sdksandbox;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zarah.dominguez on 22/02/2016.
 */
public class LinearLayoutActivity extends AppCompatActivity {

    @BindView(R.id.text2)
    TextView mText2;

    @BindView(R.id.text3)
    TextView mText3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);
        ButterKnife.bind(this);

        // left, top, right, and bottom
        DrawableCompat.setTint(mText2.getCompoundDrawables()[2].mutate(), ContextCompat.getColor(this, R.color.finn));

        // left, top, right, and bottom
        DrawableCompat.setTint(mText3.getCompoundDrawables()[0].mutate(), ContextCompat.getColor(this, R.color.red));

        // mText4 should have original drawable colour

    }
}
