package com.zorz.mario.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ProjectItem implements Parcelable {

    public int id;
    public String title;
    public String brief;
    public String description;
    public String icon;
    public String downloads;
    public String date;
    public int dl_priority;
    public ArrayList<PhotoItem> images;
    public boolean favorite;
    public String type;

    public ProjectItem(){
        this.images = new ArrayList<PhotoItem>();
    }

    protected ProjectItem(Parcel in) {
        this();
        this.id = in.readInt();
        this.title = in.readString();
        this.brief = in.readString();
        this.description = in.readString();
        this.icon = in.readString();
        this.downloads = in.readString();
        this.date = in.readString();
        this.dl_priority = in.readInt();
        this.favorite = in.readByte() != 0;
        this.type = in.readString();
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
        out.writeString(this.brief);
        out.writeString(this.description);
        out.writeString(this.icon);
        out.writeString(this.downloads);
        out.writeString(this.date);
        out.writeInt(this.dl_priority);
        out.writeByte((byte) (this.favorite ? 1 : 0));
        out.writeString(this.type);
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
