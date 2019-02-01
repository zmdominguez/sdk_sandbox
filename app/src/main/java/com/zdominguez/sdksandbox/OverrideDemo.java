package com.zdominguez.sdksandbox;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by zarah.dominguez on 5/11/2015.
 */
public class OverrideDemo extends AppCompatActivity {

    // Auto-complete

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // CMD+N --> "Override" --> select method

    @Override
    protected void onPause() {
        super.onPause();
    }


    // CTRL+O --> select method


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
