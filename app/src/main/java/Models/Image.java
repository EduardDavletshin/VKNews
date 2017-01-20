package Models;

import android.os.Parcel;
import android.os.Parcelable;


public class Image implements Parcelable{
    private String image;

    protected Image(Parcel in) {
        image = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {

        return image;
    }
}
