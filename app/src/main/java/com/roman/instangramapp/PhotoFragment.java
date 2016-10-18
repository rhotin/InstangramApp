package com.roman.instangramapp;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment implements DownloadPhotos.Communicator, DownloadPhotoFull.CommunicatorF {

    public static String locationSelected = "leaside";
    public String RURL = "http://revera.mxs-s.com/displays/" + locationSelected + "/albums.json?album=";
    public static String newRURL = "";

    ListView photoListView;
    ProgressBar progressBar;
    TextView messageText;

    ImageView photoView;

    static String photoName = "";
    static String photoExt = "";
    DownloadPhotoFull downloadPhotosFull;

    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_photo, container, false);

        AlbumObject obj = getActivity().getIntent().getParcelableExtra("theObject");

        newRURL = RURL + obj.albumID;

        Log.e("RURL", "" + newRURL);

        photoListView = (ListView) V.findViewById(R.id.listView);
        progressBar = (ProgressBar) V.findViewById(R.id.progressBar);
        messageText = (TextView) V.findViewById(R.id.textView);


        DownloadPhotos downloadPhotos = new DownloadPhotos(this);
        downloadPhotos.execute();

        photoListView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        messageText.setVisibility(View.VISIBLE);

        photoView = (ImageView) V.findViewById(R.id.userImage);


        return V;
    }

    @Override
    public void updateProgressTo(int progress) {
        progressBar.setProgress(progress);
    }

    @Override
    public void updateFUI(ArrayList<PhotoObject> photosArrayList) {
        photoView.setImageBitmap(photosArrayList.get(0).thumbnail);
    }


    @Override
    public void updateUI(final ArrayList<PhotoObject> photosArrayList) {
        photoListView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        messageText.setVisibility(View.GONE);

        photoName = photosArrayList.get(0).photoName;
        photoExt = photosArrayList.get(0).photoExt;

        PhotoAdapter adapter = new PhotoAdapter(getActivity().getApplicationContext(), photosArrayList);
        photoListView.setAdapter(adapter);

        photoView.setImageBitmap(photosArrayList.get(0).thumbnail);
        downloadPhotosFull = new DownloadPhotoFull(this);
        downloadPhotosFull.execute();

        photoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhotoObject objectToPass = photosArrayList.get(position);

                photoName = objectToPass.photoName;
                photoExt = objectToPass.photoExt;
                photoView.setImageBitmap(objectToPass.thumbnail);

                downloadPhotosFull = new DownloadPhotoFull(PhotoFragment.this);
                downloadPhotosFull.execute();

                /*Intent intent = new Intent(PhotoActivity.this, DetailActivity.class);
                intent.putExtra("thePObject", objectToPass);
                startActivity(intent);
                */

            }
        });
    }

}
