package com.example.eddy.vknews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import Models.Response;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Response newsResponse;

    public RecyclerViewAdapter(Response response) {
        newsResponse = response;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textPostText.setText(newsResponse.getItems().get(position).getText());

        if (newsResponse.getItems().get(position).getSource_id() < 0) {
            for (int i = 0; i < newsResponse.getGroups().size(); i++) {
                if (newsResponse.getGroups().get(i).getId() == newsResponse.getItems().get(position).getSource_id()) {
                    holder.textUser.setText(newsResponse.getGroups().get(i).getName());
                    Picasso.with(holder.imageAvatar.getContext()).load(String.valueOf(newsResponse.getGroups().get(i)
                            .getPhoto_100())).into(holder.imageAvatar);
                }
            }
        } else {
            for (int i = 0; i < newsResponse.getProfiles().size(); i++) {
                if (newsResponse.getProfiles().get(i).getId() == newsResponse.getItems().get(position).getSource_id()) {
                    holder.textUser.setText(newsResponse.getProfiles().get(i).getFirst_name());
                    holder.textLastname.setText(newsResponse.getProfiles().get(i).getLast_name());
                    Picasso.with(holder.imageAvatar.getContext()).load(String.valueOf(newsResponse.getProfiles().get(i)
                            .getPhoto_100())).into(holder.imageAvatar);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return newsResponse.getItems().size();
    }
}
