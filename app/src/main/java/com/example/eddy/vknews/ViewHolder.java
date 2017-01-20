package com.example.eddy.vknews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_avatar)
    ImageView imageAvatar;
    @BindView(R.id.text_user)
    TextView textUser;
    @BindView(R.id.text_user_lastname)
    TextView textLastname;
    @BindView(R.id.text_post_text)
    TextView textPostText;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
