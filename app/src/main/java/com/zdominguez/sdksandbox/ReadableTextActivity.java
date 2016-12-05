package com.zdominguez.sdksandbox;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReadableTextActivity extends AppCompatActivity {

    private static final String TAG = "ReadableTextActivity";
    @Bind(R.id.input_colour)
    EditText inputColour;

    @Bind(R.id.output)
    TextView output;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readable_text);
        ButterKnife.bind(this);

        //TODO allow dropdown of named colours
    }

    @OnClick(R.id.submit_colour)
    public void submitColour() {
        String inputString = "#" + inputColour.getText().toString();
        final int color = sanitiseInput(inputString);
        int textColour = contrastColor(color);
        output.setText(inputString);
        output.setTextColor(textColour);
        output.setBackgroundColor(color);
    }

    private int sanitiseInput(String inputString) {
        try {
            return Color.parseColor(inputString);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "sanitiseInput: Unparseable colour - " + inputString, e);
        }

        return 0;
    }

    public @ColorInt int contrastColor(int colourInput) {
        //  Counting the perceptive luminance (aka luma) - human eye favors green color...
        double luma = (((0.299 * Color.red(colourInput)) + ((0.587 * Color.green(colourInput)) + (0.114 * Color.blue(colourInput))))  / 255);

        // Return black for bright colors, white for dark colors
        return luma > 0.5 ? Color.BLACK : Color.WHITE;
    }
}
