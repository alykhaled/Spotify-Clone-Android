package com.alykhaled.spotifyclone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alykhaled.spotifyclone.R;
import com.alykhaled.spotifyclone.models.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {
    private ArrayList<Album> mAlbumsList;

    public AlbumsAdapter(ArrayList<Album> mAlbumsList) {
        this.mAlbumsList = mAlbumsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album currentAlbum = mAlbumsList.get(position);
        holder.name.setText(currentAlbum.getName());
        holder.desc.setText(currentAlbum.getYear());
        Picasso.get().load(currentAlbum.getImage()).fit().centerInside().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mAlbumsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        private TextView name;
        private TextView desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.albumItemImage);
            name = (TextView) itemView.findViewById(R.id.albumItemName);
            desc = (TextView) itemView.findViewById(R.id.albumItemDesc);
        }
    }

}
