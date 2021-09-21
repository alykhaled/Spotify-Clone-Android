package com.alykhaled.spotifyclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alykhaled.spotifyclone.R;
import com.alykhaled.spotifyclone.models.Album;
import com.alykhaled.spotifyclone.models.Artist;
import com.alykhaled.spotifyclone.models.RecentTrack;
import com.alykhaled.spotifyclone.models.TrackContext;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecentlyPlayedAdapter extends BaseAdapter {
    private final Context mContext;
    private final ArrayList<RecentTrack> tracksList;

    public RecentlyPlayedAdapter(Context mContext, ArrayList<RecentTrack> tracksList) {
        this.mContext = mContext;
        this.tracksList = tracksList;
    }

    @Override
    public int getCount() {
        return tracksList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.latest_played_item, null);
        }

        RecentTrack currentTrack = tracksList.get(position);
        TrackContext currentContext = currentTrack.getContext();

        final ImageView trackImage = (ImageView)convertView.findViewById(R.id.latestPlayedImage);
        final TextView trackName = (TextView) convertView.findViewById(R.id.latestPlayedName);

        if (currentContext.getType().equals("Artist"))
        {
            Artist currentArtist = currentContext.getArtist();
            trackName.setText(currentArtist.getName());
            Picasso.get().load(currentArtist.getProfileImage()).fit().centerInside().into(trackImage);
        }

        if (currentContext.getType().equals("Album"))
        {
            Album currentArtist = currentContext.getAlbum();
            trackName.setText(currentArtist.getName());
            Picasso.get().load(currentArtist.getImage()).fit().centerInside().into(trackImage);
        }
        return convertView;
    }
}
