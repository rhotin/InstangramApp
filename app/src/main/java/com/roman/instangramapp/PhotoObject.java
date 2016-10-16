package com.roman.instangramapp;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;


public class PhotoObject implements Parcelable {
    String photoName;
    String photoExt;
    Bitmap thumbnail;

    PhotoObject(String photo_name, String photo_ext, Bitmap photo_thumbnail) {
        this.photoName = photo_name;
        this.photoExt = photo_ext;
        this.thumbnail = photo_thumbnail;
    }

    protected PhotoObject(Parcel in) {
        photoName = in.readString();
        photoExt = in.readString();
        thumbnail = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(photoName);
        dest.writeString(photoExt);
        dest.writeValue(thumbnail);
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