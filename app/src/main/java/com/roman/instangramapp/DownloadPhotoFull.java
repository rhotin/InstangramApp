package com.roman.instangramapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DownloadPhotoFull extends AsyncTask<Void, Integer, Void> {

    /*
    urls_single_album_thumbnail.add("http://static-a.reveraconnect.com/250/" + result.get(i) + ".jpg");
    urls_full.add("http://static-a.reveraconnect.com/full/" + result.get(i) + ".jpg");
     */

    ArrayList<PhotoObject> photosArrayList = new ArrayList<>();
    CommunicatorF context;

    DownloadPhotoFull(PhotoFragment c) {
        this.context = (CommunicatorF) c;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {

                String photo_name = PhotoFragment.photoName;
                String photo_ext = PhotoFragment.photoExt;
            // String image_url = "http://static-a.reveraconnect.com/250/" + photo_name + "." + photo_ext;
                 String image_url_full = "http://static-a.reveraconnect.com/full/" + photo_name +"."+ photo_ext;

                URL downloadURL = new URL(image_url_full);
                HttpURLConnection conn = (HttpURLConnection) downloadURL.openConnection();
                InputStream inputStream = conn.getInputStream();
                Bitmap bmp = BitmapFactory.decodeStream(inputStream);

               /* URL downloadURL2 = new URL(image_url_full);
                HttpURLConnection conn2 = (HttpURLConnection) downloadURL2.openConnection();
                InputStream inputStream2 = conn2.getInputStream();
                Bitmap bmp2 = BitmapFactory.decodeStream(inputStream2);
                */

                PhotoObject obj = new PhotoObject(photo_name, photo_ext, bmp);
                photosArrayList.add(obj);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        context.updateProgressTo(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        context.updateFUI(photosArrayList);
    }

    interface CommunicatorF {
        void updateProgressTo(int progress);

        void updateFUI(ArrayList<PhotoObject> photosArrayList);
    }
}