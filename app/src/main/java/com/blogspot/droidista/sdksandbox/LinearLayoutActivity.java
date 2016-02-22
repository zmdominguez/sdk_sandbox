package com.blogspot.droidista.sdksandbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zarah.dominguez on 22/02/2016.
 */
public class LinearLayoutActivity extends AppCompatActivity {

    @Bind(R.id.text2)
    TextView mText2;

    @Bind(R.id.text3)
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
