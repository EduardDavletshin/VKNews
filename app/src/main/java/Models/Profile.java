package Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eddy on 1/18/2017.
 */

public class Profile implements Parcelable {
    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
    private int id;
    private String first_name;
    private String last_name;
    private String photo_100;

    protected Profile(Parcel in) {
        id = in.readInt();
        first_name = in.readString();
        last_name = in.readString();
        photo_100 = in.readParcelable(Image.class.getClassLoader());
    }

    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhoto_100() {
        return photo_100;
    }

    public void setPhoto_100(String photo_100) {
        this.photo_100 = photo_100;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(photo_100);
    }
}
