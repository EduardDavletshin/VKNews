package com.example.eddy.vknews;

import android.os.AsyncTask;

import java.io.IOException;

import Models.ResponseWrapper;

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
        return responseWrapper;
    }

    @Override
    protected void onPostExecute(ResponseWrapper o) {
        super.onPostExecute(o);
        callback.onFinish(responseWrapper);
    }
}
