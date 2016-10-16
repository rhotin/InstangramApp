package com.roman.instangramapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    ImageView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        PhotoObject obj = getIntent().getParcelableExtra("thePObject");

        photoView = (ImageView) findViewById(R.id.userImage);

        photoView.setImageBitmap(obj.thumbnail);
    }
}
