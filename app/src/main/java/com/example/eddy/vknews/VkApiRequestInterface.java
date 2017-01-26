package com.example.eddy.vknews;

import com.example.eddy.vknews.Models.ResponseWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface VkApiRequestInterface {

    @GET("/method/newsfeed.get?filters=post&source_ids=g39009769&v=5.62")
    Call<ResponseWrapper> getResponse(@Query("access_token") String token);
}
