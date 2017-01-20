package Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Group implements Parcelable{
    private int id;
    private String name;
    private String photo_100;

    protected Group(Parcel in) {
        id = in.readInt();
        name = in.readString();
        photo_100 = in.readParcelable(Image.class.getClassLoader());
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
        dest.writeString(photo_100);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto_100() {
        return photo_100;
    }
}
