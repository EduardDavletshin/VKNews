package com.example.eddy.vknews.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Group implements Parcelable {
    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };
    private int id;
    private String name;
    @SerializedName("photo_100")
    private String photo100;

    private Group(Parcel in) {
        id = in.readInt();
        name = in.readString();
        photo100 = in.readParcelable(Image.class.getClassLoader());
    }

    public static Group findGroupBySourceId(ArrayList<Group> groups, int sourceId) {
        Group group = null;
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId() == sourceId * -1) {
                group = groups.get(i);
                break;
            }
        }
        return group;
    }

    public static boolean isSourceGroup(int sourceId) {
        return sourceId < 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(photo100);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto100() {
        return photo100;
    }

    public void setPhoto100(String photo100) {
        this.photo100 = photo100;
    }
}
