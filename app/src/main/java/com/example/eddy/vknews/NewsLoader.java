package com.example.eddy.vknews;

import android.os.AsyncTask;

import com.example.eddy.vknews.Models.ResponseWrapper;

import java.io.IOException;

import static com.example.eddy.vknews.MainActivity.token;


public class NewsLoader extends AsyncTask<Object, Object, ResponseWrapper> {

    private ResponseWrapper responseWrapper;
    private Callback callback;

    NewsLoader(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected ResponseWrapper doInBackground(Object... params) {
        try {
            responseWrapper = RetrofitSingleton.getInstance().getRequestInterface().getResponse(token)
                    .execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < responseWrapper.getResponse().getItems().size(); i++) {
            if (responseWrapper.getResponse().getItems().get(i).getAttachments() == null) {
                responseWrapper.getResponse().getItems().remove(i);
            }
        }
        return responseWrapper;
    }

    @Override
    protected void onPostExecute(ResponseWrapper o) {
        super.onPostExecute(o);
        callback.onFinish(responseWrapper);
    }
}
