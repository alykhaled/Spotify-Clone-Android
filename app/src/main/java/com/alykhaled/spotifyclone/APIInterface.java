package com.alykhaled.spotifyclone;

import retrofit2.Call;
import retrofit2.http.GET;

interface APIInterface {
    @GET("/api/artist/top")
    Call<Artist> getAllArtist();
}
