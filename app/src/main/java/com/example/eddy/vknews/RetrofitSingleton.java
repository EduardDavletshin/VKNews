package com.example.eddy.vknews;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitSingleton {

    private static RetrofitSingleton instance = new RetrofitSingleton();
    private VkApiRequestInterface vkApiRequestInterface;

    private RetrofitSingleton() {
        String URL = "https://api.vk.com";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        vkApiRequestInterface = retrofit.create(VkApiRequestInterface.class);
    }

    public static RetrofitSingleton getInstance() {
        return instance;
    }

    public VkApiRequestInterface getRequestInterface() {
        return vkApiRequestInterface;
    }
}
