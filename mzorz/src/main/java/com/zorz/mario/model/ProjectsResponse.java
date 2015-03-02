package com.zorz.mario.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ProjectsResponse implements Parcelable{

    public boolean success;
    public List<ProjectItem> projects;

    public ProjectsResponse(){
        this.projects = new ArrayList<ProjectItem>();
    }

    protected ProjectsResponse(Parcel in) {
        this();
        this.success = in.readByte() != 0;
        in.readTypedList(this.projects, ProjectItem.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeByte((byte) (this.success ? 1 : 0));
        out.writeTypedList(this.projects);
    }

    public static final Parcelable.Creator<ProjectsResponse> CREATOR = new Creator<ProjectsResponse>() {

        @Override
        public ProjectsResponse createFromParcel(Parcel source) {
            return new ProjectsResponse(source);
        }

        @Override
        public ProjectsResponse[] newArray(int size) {
            return new ProjectsResponse[size];
        }
    };

}
