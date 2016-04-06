package com.roman.instangramapp;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;


public class PhotoObject implements Parcelable {
    Bitmap thumbnail;
    String userName;
    String captionText;

    PhotoObject(Bitmap photo_thumbnail, String photo_userName, String caption){
        this.thumbnail = photo_thumbnail;
        this.userName = photo_userName;
        this.captionText = caption;
    }

    protected PhotoObject(Parcel in) {
        thumbnail = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
        userName = in.readString();
        captionText = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(thumbnail);
        dest.writeString(userName);
        dest.writeString(captionText);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PhotoObject> CREATOR = new Parcelable.Creator<PhotoObject>() {
        @Override
        public PhotoObject createFromParcel(Parcel in) {
            return new PhotoObject(in);
        }

        @Override
        public PhotoObject[] newArray(int size) {
            return new PhotoObject[size];
        }
    };
}