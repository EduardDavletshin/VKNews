package com.example.eddy.vknews;

import android.os.AsyncTask;

import com.example.eddy.vknews.Models.Item;
import com.example.eddy.vknews.Models.ResponseWrapper;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.eddy.vknews.Activities.LoginActivity.token;


public class NewsLoader extends AsyncTask<Object, Object, ResponseWrapper> {

    private ResponseWrapper responseWrapper;
    private Callback callback;

    public NewsLoader(Callback callback) {
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

        ArrayList<Item> items = responseWrapper.getResponse().getItems();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getAttachments() == null) {
                items.remove(i);
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
