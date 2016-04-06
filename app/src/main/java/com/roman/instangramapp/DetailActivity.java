package com.roman.instangramapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView username;
    TextView captionText;
    ImageView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        PhotoObject obj = getIntent().getParcelableExtra("theObject");
        username = (TextView) findViewById(R.id.userName);
        captionText = (TextView) findViewById(R.id.captionText);
        photoView = (ImageView) findViewById(R.id.userImage);

        username.setText(obj.userName);
        captionText.setText(obj.captionText);
        photoView.setImageBitmap(obj.thumbnail);

    }


}
