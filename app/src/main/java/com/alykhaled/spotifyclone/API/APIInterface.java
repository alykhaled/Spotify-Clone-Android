package com.alykhaled.spotifyclone.API;

import com.alykhaled.spotifyclone.models.Album;
import com.alykhaled.spotifyclone.models.Artist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/api/artist/top")
    Call<ArrayList<Artist>> getAllArtist();

    @GET("/api/album/")
    Call<ArrayList<Album>> getAlbums(@Query("artistId") String id);
}
