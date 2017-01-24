package Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group implements Parcelable{
    private int id;
    private String name;
    @SerializedName("photo_100")
    @Expose
    private String photo100;

    protected Group(Parcel in) {
        id = in.readInt();
        name = in.readString();
        photo100 = in.readParcelable(Image.class.getClassLoader());
    }

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

    public String getName() {
        return name;
    }

    public String getPhoto100() {
        return photo100;
    }
}