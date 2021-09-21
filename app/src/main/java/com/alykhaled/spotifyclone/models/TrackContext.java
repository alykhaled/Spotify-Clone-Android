package com.alykhaled.spotifyclone.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackContext {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("artist")
    @Expose
    private Artist artist;
    @SerializedName("album")
    @Expose
    private Album album;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
