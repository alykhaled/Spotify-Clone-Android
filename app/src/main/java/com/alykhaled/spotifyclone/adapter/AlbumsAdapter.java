package com.alykhaled.spotifyclone.adapter;

import android.content.Context;
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

import com.alykhaled.spotifyclone.R;
import com.alykhaled.spotifyclone.fragments.AlbumFragment;
import com.alykhaled.spotifyclone.fragments.ArtistFragment;
import com.alykhaled.spotifyclone.models.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {
    private ArrayList<Album> mAlbumsList;
    private Context mContext;

    public AlbumsAdapter(ArrayList<Album> mAlbumsList) {
        this.mAlbumsList = mAlbumsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item,parent,false);
        mContext = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album currentAlbum = mAlbumsList.get(position);
        holder.name.setText(currentAlbum.getName());
        holder.desc.setText(currentAlbum.getYear());
        Picasso.get().load(currentAlbum.getImage()).fit().centerInside().into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("albumId",currentAlbum.getId());
                Fragment fragment = new AlbumFragment();
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
