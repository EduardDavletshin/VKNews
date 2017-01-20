package com.example.eddy.vknews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import Models.Group;
import Models.Item;
import Models.Profile;
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
        Item dataPosition = newsResponse.getItems().get(position);

        holder.textPostText.setText(dataPosition.getText());

        if (dataPosition.getSource_id() < 0) {
            for (int i = 0; i < newsResponse.getGroups().size(); i++) {
                Group group = newsResponse.getGroups().get(i);
                if (group.getId() == dataPosition.getSource_id() * -1) {
                    holder.textUser.setText(group.getName());
                    holder.textLastname.setText("");
                    Picasso.with(holder.imageAvatar.getContext()).load(String.valueOf(group
                            .getPhoto_100())).into(holder.imageAvatar);
                }
            }
        } else {
            for (int i = 0; i < newsResponse.getProfiles().size(); i++) {
                Profile profile = newsResponse.getProfiles().get(i);
                if (profile.getId() == dataPosition.getSource_id()) {
                    holder.textUser.setText(profile.getFirst_name());
                    holder.textLastname.setText(profile.getLast_name());
                    Picasso.with(holder.imageAvatar.getContext()).load(String.valueOf(profile
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
