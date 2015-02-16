package com.zorz.mario.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mariozorz on 1/18/15.
 */
public class PhotoItem implements Parcelable {
    public String filename;
    public String original;
    public String full;
    public String big;
    public String small;
    public String thumb;
    public String ext;
    public String path;

    public PhotoItem(){}

    protected PhotoItem(Parcel in) {
        this.filename = in.readString();
        this.original = in.readString();
        this.full = in.readString();
        this.big = in.readString();
        this.small = in.readString();
        this.thumb = in.readString();
        this.ext = in.readString();
        this.path = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.filename);
        out.writeString(this.original);
        out.writeString(this.full);
        out.writeString(this.big);
        out.writeString(this.small);
        out.writeString(this.thumb);
        out.writeString(this.ext);
        out.writeString(this.path);

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
