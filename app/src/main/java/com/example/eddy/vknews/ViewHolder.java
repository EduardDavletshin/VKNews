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
    @BindView(R.id.image_1)
    ImageView image1;
    @BindView(R.id.image_2)
    ImageView image2;
    @BindView(R.id.image_3)
    ImageView image3;
    @BindView(R.id.image_4)
    ImageView image4;
    @BindView(R.id.image_5)
    ImageView image5;
    @BindView(R.id.image_6)
    ImageView image6;
    @BindView(R.id.image_7)
    ImageView image7;
    @BindView(R.id.image_8)
    ImageView image8;
    @BindView(R.id.image_9)
    ImageView image9;
    ImageView[] images;


    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        images = new ImageView[]{image1, image2, image3, image4, image5, image6, image7, image8, image9};
    }
}
