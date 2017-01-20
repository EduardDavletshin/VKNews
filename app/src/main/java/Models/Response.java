package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by eddy on 1/18/2017.
 */

public class Response implements Parcelable {
    private ArrayList<Item> items;
    private ArrayList<Profile> profiles;
    private ArrayList<Group> groups;

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    public Response(ArrayList<Item> items, ArrayList<Profile> profiles, ArrayList<Group> groups) {
        this.items = items;
        this.profiles = profiles;
        this.groups = groups;
    }

    protected Response(Parcel in) {
        items = in.createTypedArrayList(Item.CREATOR);
        profiles = in.createTypedArrayList(Profile.CREATOR);
        groups = in.createTypedArrayList(Group.CREATOR);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(items);
        dest.writeTypedList(profiles);
        dest.writeTypedList(groups);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(ArrayList<Profile> profiles) {
        this.profiles = profiles;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }
}
