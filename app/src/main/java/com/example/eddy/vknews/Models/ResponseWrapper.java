package com.example.eddy.vknews.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ResponseWrapper implements Parcelable{

    private Response response;

    private ResponseWrapper(Parcel in) {
        response = in.readParcelable(Response.class.getClassLoader());
    }

    public static final Creator<ResponseWrapper> CREATOR = new Creator<ResponseWrapper>() {
        @Override
        public ResponseWrapper createFromParcel(Parcel in) {
            return new ResponseWrapper(in);
        }

        @Override
        public ResponseWrapper[] newArray(int size) {
            return new ResponseWrapper[size];
        }
    };

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(response, flags);
    }
}
