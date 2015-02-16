package com.zorz.mario.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProjectItem implements Parcelable {

    public String id_news;
    public String title;
    public String excerpt_es;
    public String body_es;
    public String date;
    public String created_ts;
    public String updated_ts;
    public PhotoItem photos;

    public ProjectItem(){}

    protected ProjectItem(Parcel in) {
        this.id_news = in.readString();
        this.title = in.readString();
        this.excerpt_es = in.readString();
        this.body_es = in.readString();
        this.date = in.readString();
        this.created_ts = in.readString();
        this.updated_ts = in.readString();
        this.photos = in.readParcelable(PhotoItem.class.getClassLoader());

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.id_news);
        out.writeString(this.title);
        out.writeString(this.excerpt_es);
        out.writeString(this.body_es);
        out.writeString(this.date);
        out.writeString(this.created_ts);
        out.writeString(this.updated_ts);
        out.writeParcelable(this.photos, flags);

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
