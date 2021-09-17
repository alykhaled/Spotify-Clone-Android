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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecentlyPlayedAdapter extends BaseAdapter {
    private final Context mContext;
    private final ArrayList<Artist> tracksList;

    public RecentlyPlayedAdapter(Context mContext, ArrayList<Artist> tracksList) {
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
        Artist currentTrack = tracksList.get(position);
        final ImageView trackImage = (ImageView)convertView.findViewById(R.id.latestPlayedImage);
        final TextView trackName = (TextView) convertView.findViewById(R.id.latestPlayedName);

        trackName.setText(currentTrack.getName());
        Picasso.get().load(currentTrack.getProfileImage()).fit().centerInside().into(trackImage);
        return convertView;
    }
}
