package com.roman.instangramapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity implements DownloadAlbums.CommunicatorA{

    public static String locationSelected = "leaside";
    public static final String RURL = "http://revera.mxs-s.com/displays/" + locationSelected + "/albums.json?album=";

    ListView albumListView;
    ProgressBar progressBar;
    TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        albumListView = (ListView) findViewById(R.id.listView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        messageText = (TextView) findViewById(R.id.textView);

        DownloadAlbums downloadAlbum = new DownloadAlbums(this);
        downloadAlbum.execute();

        albumListView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        messageText.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateProgressTo(int progress) {
        progressBar.setProgress(progress);
    }

    @Override
    public void updateUI(final ArrayList<AlbumObject> albumsArrayList) {
        albumListView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        messageText.setVisibility(View.GONE);

        AlbumAdapter adapter = new AlbumAdapter(this, albumsArrayList);
        albumListView.setAdapter(adapter);


        albumListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlbumObject objectToPass = albumsArrayList.get(position);

                Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
                intent.putExtra("theObject", objectToPass);
                startActivity(intent);
            }
        });
    }
}