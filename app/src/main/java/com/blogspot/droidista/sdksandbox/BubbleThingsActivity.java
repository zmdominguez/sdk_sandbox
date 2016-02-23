package com.blogspot.droidista.sdksandbox;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.WindowManager;

import com.blogspot.droidista.sdksandbox.com.blogspot.droidista.sdksandbox.SandboxScrollView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zarah.dominguez on 23/02/2016.
 */
public class BubbleThingsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.main_content)
    SandboxScrollView mScrollView;

    Display display;
    Point outSize;
    private int mCurrentScrollPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubbles_thing);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
        outSize = new Point();
        display.getSize(outSize);

        mScrollView.setOnScrollListener(new SandboxScrollView.OnScrollViewListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                // You'd probably have to math better than this. Kinda flaky when flinging.
                mCurrentScrollPosition += (t-oldt);
                if (mCurrentScrollPosition < 0) {
                    mCurrentScrollPosition = 0;
                }
                updateToolbarOpacity();
            }
        });
    }


    private void updateToolbarOpacity() {
        if (mCurrentScrollPosition > 0) {
            float opaque = 1f - mCurrentScrollPosition / (outSize.x / 2f);
            if (opaque < 0) {
                opaque = 0;
            }
            mToolbar.setBackgroundColor(adjustColorAlpha(ContextCompat.getColor(this, android.R.color.black), opaque));
        } else {
            mToolbar.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private static int adjustColorAlpha(int color, float factor) {
        int alpha = 255 - Math.round(255 * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
}
