package com.example.eddy.vknews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.example.eddy.vknews.Models.Attachment;
import com.example.eddy.vknews.Models.Group;
import com.example.eddy.vknews.Models.Item;
import com.example.eddy.vknews.Models.Profile;
import com.example.eddy.vknews.Models.Response;

import static com.example.eddy.vknews.Models.Group.isSourceGroup;
import static com.example.eddy.vknews.Models.Profile.isSourceProfile;

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
        Item itemPosition = newsResponse.getItems().get(position);
        Context context = holder.imageAvatar.getContext();

        holder.textPostText.setText(itemPosition.getText());
        if (isSourceGroup(itemPosition.getSourceId())) {
            Group group = Group.findGroupBySourceId(newsResponse.getGroups(),
                    itemPosition.getSourceId());
            holder.textUser.setText(group.getName());
            holder.textLastname.setText("");
            Picasso.with(context).load(group
                    .getPhoto100()).into(holder.imageAvatar);
        } else if (isSourceProfile(itemPosition.getSourceId())) {
            Profile profile = Profile.findProfileBySourceId(newsResponse.getProfiles(),
                    itemPosition.getSourceId());
            holder.textUser.setText(profile.getFirst_name());
            holder.textLastname.setText(profile.getLast_name());
            Picasso.with(context).load(profile.getPhoto_100()).into(holder.imageAvatar);
        }

        ArrayList<Attachment> attachmentArrayList = itemPosition.getAttachments();
        if (attachmentArrayList != null) {
            for (int i = 0; i < Math.min(attachmentArrayList.size(), 9); i++) {
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
