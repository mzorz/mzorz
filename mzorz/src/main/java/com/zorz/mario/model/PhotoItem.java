package com.zorz.mario.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PhotoItem implements Parcelable {

    public String title;
    public String description;
    public String url;

    public PhotoItem(){}

    protected PhotoItem(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.title);
        out.writeString(this.description);
        out.writeString(this.url);
    }

    public static final Creator<PhotoItem> CREATOR = new Creator<PhotoItem>() {

        @Override
        public PhotoItem createFromParcel(Parcel source) {
            return new PhotoItem(source);
        }

        @Override
        public PhotoItem[] newArray(int size) {
            return new PhotoItem[size];
        }
    };

}
