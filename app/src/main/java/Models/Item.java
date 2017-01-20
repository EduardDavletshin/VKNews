package Models;

import android.os.Parcel;
import android.os.Parcelable;


public class Item implements Parcelable{
    private String type;
    private int source_id;
    private String text;

    protected Item(Parcel in) {
        type = in.readString();
        source_id = in.readInt();
        text = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeInt(source_id);
        dest.writeString(text);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSource_id(int source_id) {
        this.source_id = source_id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {

        return type;
    }

    public int getSource_id() {
        return source_id;
    }

    public String getText() {
        return text;
    }
}
