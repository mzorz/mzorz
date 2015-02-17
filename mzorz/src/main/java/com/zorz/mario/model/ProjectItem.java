package com.zorz.mario.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ProjectItem implements Parcelable {

    public int id;
    public String title;
    public String description;
    public String date;
    public List<PhotoItem> images;

    public ProjectItem(){}

    protected ProjectItem(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.date = in.readString();
        this.images = in.readArrayList(PhotoItem.class.getClassLoader());
        if (this.images == null)
            this.images = new ArrayList<PhotoItem>();
        in.readTypedList(this.images, PhotoItem.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.id);
        out.writeString(this.title);
        out.writeString(this.description);
        out.writeString(this.date);
        out.writeTypedList(this.images);
    }

    public static final Creator<ProjectItem> CREATOR = new Creator<ProjectItem>() {

        @Override
        public ProjectItem createFromParcel(Parcel source) {
            return new ProjectItem(source);
        }

        @Override
        public ProjectItem[] newArray(int size) {
            return new ProjectItem[size];
        }
    };

}
