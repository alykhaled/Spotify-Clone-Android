package com.alykhaled.spotifyclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alykhaled.spotifyclone.R;
import com.alykhaled.spotifyclone.models.Track;

import java.util.ArrayList;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder> {
    private ArrayList<Track>  tracksList;
    private Context mContext;

    public TracksAdapter(ArrayList<Track> tracksList) {
        this.tracksList = tracksList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_item,parent,false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Track currentTrack = tracksList.get(position);
        holder.trackName.setText(currentTrack.getName());
        holder.trackArtist.setText(currentTrack.getArtist().getName());
    }

    @Override
    public int getItemCount() {
        return tracksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView trackName;
        private TextView trackArtist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trackName = (TextView) itemView.findViewById(R.id.trackName);
            trackArtist = (TextView) itemView.findViewById(R.id.trackArtist);
        }
    }
}
