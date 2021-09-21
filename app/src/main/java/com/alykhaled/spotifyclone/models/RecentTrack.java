package com.alykhaled.spotifyclone.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecentTrack {
    @SerializedName("context")
    @Expose
    private TrackContext context;
    @SerializedName("track")
    @Expose
    private Track track = null;

    public TrackContext getContext() {
        return context;
    }

    public void setContext(TrackContext context) {
        this.context = context;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
