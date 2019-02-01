package com.zdominguez.sdksandbox;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.zdominguez.sdksandbox.models.AdventureTimeCharacters;

public class ResourceAnnotationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_annotations);

        for (AdventureTimeCharacters character : AdventureTimeCharacters.values()) {
            character.setQuote(ResourceAnnotationsActivity.this);
        }
    }


}
