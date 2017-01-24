package com.example.eddy.vknews;

import android.support.annotation.StringDef;
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

    private Group findGroupById(int position) {
        Item itemPosition = newsResponse.getItems().get(position);
        Group group = null;
        for (int i = 0; i < newsResponse.getGroups().size(); i++) {
            if (newsResponse.getGroups().get(i).getId() == itemPosition.getSourceId() * -1) {
                group = newsResponse.getGroups().get(i);
                break;
            }
        }
        return group;
    }

    private Profile findProfileById(int position) {
        Item itemPosition = newsResponse.getItems().get(position);
        Profile profile = null;
        for (int i = 0; i < newsResponse.getProfiles().size(); i++) {
            if (newsResponse.getProfiles().get(i).getId() == itemPosition.getSourceId()) {
                profile = newsResponse.getProfiles().get(i);
                break;
            }
        }
        return profile;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item itemPosition = newsResponse.getItems().get(position);

        holder.textPostText.setText(itemPosition.getText());
        if (itemPosition.getSourceId() < 0 ) {
            holder.textUser.setText(findGroupById(position).getName());
            holder.textLastname.setText("");
            Picasso.with(holder.imageAvatar.getContext()).load(findGroupById(position)
                    .getPhoto100()).into(holder.imageAvatar);
        } else {
            holder.textUser.setText(findProfileById(position).getFirst_name());
            holder.textLastname.setText(findProfileById(position).getLast_name());
            Picasso.with(holder.imageAvatar.getContext()).load(findProfileById(position)
                    .getPhoto_100()).into(holder.imageAvatar);
        }
//
//        if (dataPosition.getSourceId() < 0) {
//            for (int i = 0; i < newsResponse.getGroups().size(); i++) {
//                Group group = newsResponse.getGroups().get(i);
//                if (group.getId() == dataPosition.getSourceId() * -1) {
//                    holder.textUser.setText(group.getName());
//                    holder.textLastname.setText("");
//                    Picasso.with(holder.imageAvatar.getContext()).load(String.valueOf(group
//                            .getPhoto100())).into(holder.imageAvatar);
//                    Log.e("width", String.valueOf(holder.imageAvatar.getWidth()));
//                    break;
//                }
//            }
//        } else {
//            for (int i = 0; i < newsResponse.getProfiles().size(); i++) {
//                Profile profile = newsResponse.getProfiles().get(i);
//                if (profile.getId() == dataPosition.getSourceId()) {
//                    holder.textUser.setText(profile.getFirst_name());
//                    holder.textLastname.setText(profile.getLast_name());
//                    Picasso.with(holder.imageAvatar.getContext()).load(String.valueOf(profile
//                            .getPhoto_100())).into(holder.imageAvatar);
//                    break;
//                }
//            }
//        }
    }

    @Override
    public int getItemCount() {
        return newsResponse.getItems().size();
    }
}
