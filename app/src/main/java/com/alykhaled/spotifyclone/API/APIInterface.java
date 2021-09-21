package com.alykhaled.spotifyclone.API;

import com.alykhaled.spotifyclone.models.Album;
import com.alykhaled.spotifyclone.models.Artist;
import com.alykhaled.spotifyclone.models.RecentTrack;
import com.alykhaled.spotifyclone.models.Track;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    //////////////// Artist ////////////////
    @GET("/api/artist/top")
    Call<ArrayList<Artist>> getAllArtist();

    @GET("/api/artist/{id}")
    Call<Artist> getArtist(@Path("id") String id);

    @GET("/api/artist/{id}/albums")
    Call<ArrayList<Album>> getAlbums(@Path("id") String id);

    //////////////// ME ////////////////
    @GET("/api/me/recent")
    Call<ArrayList<RecentTrack>> getRecentTracks();

    //////////////// Album ////////////////
    @GET("/api/album/{id}")
    Call<Album> getAlbum(@Path("id") String id);

    @GET("/api/album/{id}/tracks")
    Call<ArrayList<Track>> getAlbumTracks(@Path("id") String id);
}
