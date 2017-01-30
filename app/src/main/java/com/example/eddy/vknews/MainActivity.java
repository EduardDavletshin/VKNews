package com.example.eddy.vknews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.eddy.vknews.Models.ResponseWrapper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    NewsLoader newsLoader;
    ResponseWrapper responseWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadNews();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("jsonresponse", responseWrapper);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getParcelable("jsonresponse");
    }

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

    public void initRecyclerView(ResponseWrapper responseWrapper) {
        if (responseWrapper != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new RecyclerViewAdapter(responseWrapper.getResponse()));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}
