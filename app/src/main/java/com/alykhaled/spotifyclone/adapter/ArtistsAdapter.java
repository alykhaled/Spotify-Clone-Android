package com.alykhaled.spotifyclone.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.alykhaled.spotifyclone.fragments.ArtistFragment;
import com.alykhaled.spotifyclone.models.Artist;
import com.alykhaled.spotifyclone.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ViewHolder> {

    private ArrayList<Artist> mArtistsList;
    private Context mContext;

    public ArtistsAdapter(ArrayList<Artist> mArtistsList) {
        this.mArtistsList = mArtistsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item,parent,false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Artist currentArtist = mArtistsList.get(position);
        holder.artistText.setText(currentArtist.getName());
        Picasso.get().load(currentArtist.getProfileImage()).fit().centerCrop().into(holder.artistImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("artistId",currentArtist.getId());
                Fragment fragment = new ArtistFragment();
                fragment.setArguments(args);
                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainView, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArtistsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView artistImage;
        private TextView artistText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            artistText = (TextView) itemView.findViewById(R.id.artistItemName);
            artistImage = (ImageView) itemView.findViewById(R.id.artistItemImage);
        }
    }
}
