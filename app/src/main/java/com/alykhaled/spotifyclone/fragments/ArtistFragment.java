package com.alykhaled.spotifyclone.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alykhaled.spotifyclone.API.APIClient;
import com.alykhaled.spotifyclone.API.APIInterface;
import com.alykhaled.spotifyclone.R;
import com.alykhaled.spotifyclone.adapter.AlbumsAdapter;
import com.alykhaled.spotifyclone.adapter.ArtistsAdapter;
import com.alykhaled.spotifyclone.models.Album;
import com.alykhaled.spotifyclone.models.Artist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistFragment extends Fragment {
    APIInterface apiInterface;
    RecyclerView mAlbumsView;
    ArrayList<Album> mAlbumList;
    AlbumsAdapter mAlbumsAdapter;
    TextView artistName;
    ImageView artistImage;
    ImageView artistAboutImage;
    String artistID = "";
    public ArtistFragment() {

    }

    public static ArtistFragment newInstance(String param1, String param2) {
        ArtistFragment fragment = new ArtistFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle id = getArguments();
        artistID = id.getString("artistId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artist, container, false);
        artistName = (TextView) view.findViewById(R.id.artistName);
        artistImage = (ImageView) view.findViewById(R.id.artistImage);
        artistAboutImage = (ImageView) view.findViewById(R.id.artistAboutImage);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        mAlbumsView = view.findViewById(R.id.featuringRecyclerView);
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);;

        mAlbumList = new ArrayList<>();

        mAlbumsView.setHasFixedSize(true);
        mAlbumsView.setLayoutManager(HorizontalLayout);

        Call<ArrayList<Album>> getAlbums = apiInterface.getAlbums(artistID);
        getAlbums.enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
                mAlbumList = response.body();
                mAlbumsAdapter = new AlbumsAdapter(mAlbumList);
                mAlbumsView.setAdapter(mAlbumsAdapter);
            }
            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {
                call.cancel();
                Toast.makeText(getContext(), "FAIL" , Toast.LENGTH_SHORT).show();
            }
        });

        Call<Artist> getArtist = apiInterface.getArtist(artistID);
        getArtist.enqueue(new Callback<Artist>() {
            @Override
            public void onResponse(Call<Artist> call, Response<Artist> response) {
                Artist currentArtist = response.body();
                artistName.setText(currentArtist.getName());
                Picasso.get().load(currentArtist.getProfileImage()).fit().centerInside().into(artistImage);
                Picasso.get().load(currentArtist.getProfileImage()).fit().centerInside().into(artistAboutImage);
            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {

            }
        });
        return view;
    }
}