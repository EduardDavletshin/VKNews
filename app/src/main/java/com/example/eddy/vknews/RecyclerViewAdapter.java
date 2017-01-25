package com.example.eddy.vknews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Models.Attachment;
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
        Context context = holder.imageAvatar.getContext();

        holder.textPostText.setText(itemPosition.getText());
        if (itemPosition.getSourceId() < 0) {
            holder.textUser.setText(findGroupById(position).getName());
            holder.textLastname.setText("");
            Picasso.with(context).load(findGroupById(position)
                    .getPhoto100()).into(holder.imageAvatar);
        } else {
            holder.textUser.setText(findProfileById(position).getFirst_name());
            holder.textLastname.setText(findProfileById(position).getLast_name());
            Picasso.with(context).load(findProfileById(position)
                    .getPhoto_100()).into(holder.imageAvatar);
        }

        ArrayList<Attachment> attachmentArrayList = itemPosition.getAttachments();
        if (attachmentArrayList != null) {
            for (int i = 0; i < attachmentArrayList.size() && i < 9; i++) {
                if (attachmentArrayList.get(i).getPhoto() != null &&
                        attachmentArrayList.get(i).getPhoto().getPhoto604() != null) {
                    Picasso.with(context)
                            .load(attachmentArrayList.get(i).getPhoto().getPhoto604())
                            .into(holder.images[i]);
                    holder.images[i].setVisibility(View.VISIBLE);
                }
            }
            for (int i = attachmentArrayList.size(); i < 9; i++) {
                holder.images[i].setVisibility(View.GONE);
            }
        } else {
            for (int i = 0; i < 9; i++) {
                holder.images[i].setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return newsResponse.getItems().size();
    }
}
