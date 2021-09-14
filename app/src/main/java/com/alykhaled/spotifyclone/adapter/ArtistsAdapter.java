package com.alykhaled.spotifyclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alykhaled.spotifyclone.models.Artist;
import com.alykhaled.spotifyclone.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Artist> mArtistsList;

    public ArtistsAdapter(Context mContext, ArrayList<Artist> mArtistsList) {
        this.mContext = mContext;
        this.mArtistsList = mArtistsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Artist currentArtist = mArtistsList.get(position);
        holder.artistText.setText(currentArtist.getName());
        Picasso.get().load(currentArtist.getProfileImage()).fit().centerInside().into(holder.artistImage);

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
