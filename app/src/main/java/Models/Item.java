package Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Item implements Parcelable{
    private String type;
    @SerializedName("source_id")
    @Expose
    private int sourceId;
    private String text;

    protected Item(Parcel in) {
        type = in.readString();
        sourceId = in.readInt();
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
        dest.writeInt(sourceId);
        dest.writeString(text);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {

        return type;
    }

    public int getSourceId() {
        return sourceId;
    }

    public String getText() {
        return text;
    }
}
