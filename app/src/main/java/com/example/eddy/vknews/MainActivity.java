package com.example.eddy.vknews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import Models.Response;
import Models.ResponseWrapper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static String token;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    ResponseWrapper responseWrapper;
    NewsLoader newsLoader;
    private String[] scope = new String[]{VKScope.WALL, VKScope.FRIENDS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        VKSdk.login(this, scope);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // User passed Authorization
                Toast.makeText(getApplicationContext(), "Authorized", Toast.LENGTH_LONG).show();
                token = res.accessToken;
            }

            @Override
            public void onError(VKError error) {
                // User didn't pass Authorization
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnClick(R.id.button_news)
    public void loadNews() {
        newsLoader = new NewsLoader(new Callback() {
            @Override
            public void onFinish(ResponseWrapper callbackResponse) {
                responseWrapper = callbackResponse;
                initRecyclerView(responseWrapper);
            }

        });
        newsLoader.execute();
    }

    public void initRecyclerView(ResponseWrapper response) {
        if (response != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new RecyclerViewAdapter(response.getResponse()));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}
