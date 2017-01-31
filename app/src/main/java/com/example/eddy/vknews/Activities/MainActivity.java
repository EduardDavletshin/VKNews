package com.example.eddy.vknews.Activities;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.eddy.vknews.Adapters.RecyclerViewAdapter;
import com.example.eddy.vknews.Callback;
import com.example.eddy.vknews.Models.ResponseWrapper;
import com.example.eddy.vknews.NewsLoader;
import com.example.eddy.vknews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String LAYOUT_MANAGER_STATE = "LinearLayoutManagerState";
    private static final String JSON_RESPONSE = "jsonrecponse";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    NewsLoader newsLoader;
    ResponseWrapper responseWrapper;
    LinearLayoutManager llm = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.app_name);
        loadNews();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(JSON_RESPONSE, responseWrapper);
        outState.putParcelable(LAYOUT_MANAGER_STATE, llm.onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        responseWrapper = savedInstanceState.getParcelable(JSON_RESPONSE);
        llm.onRestoreInstanceState(savedInstanceState.getParcelable(LAYOUT_MANAGER_STATE));
    }

    public void loadNews() {
        if (responseWrapper == null) {
            newsLoader = new NewsLoader(new Callback() {
                @Override
                public void onFinish(ResponseWrapper callbackResponse) {
                    responseWrapper = callbackResponse;
                    initRecyclerView(responseWrapper);
                }
            });
            newsLoader.execute();
        } else {
            initRecyclerView(responseWrapper);
        }
    }

    public void initRecyclerView(ResponseWrapper responseWrapper) {
        if (responseWrapper != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new RecyclerViewAdapter(responseWrapper.getResponse()));
            recyclerView.setLayoutManager(llm);
        }
    }
}
