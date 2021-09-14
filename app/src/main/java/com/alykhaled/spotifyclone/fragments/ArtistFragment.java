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

import com.alykhaled.spotifyclone.API.APIClient;
import com.alykhaled.spotifyclone.API.APIInterface;
import com.alykhaled.spotifyclone.R;
import com.alykhaled.spotifyclone.adapter.ArtistsAdapter;
import com.alykhaled.spotifyclone.models.Album;
import com.alykhaled.spotifyclone.models.Artist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistFragment extends Fragment {
    APIInterface apiInterface;
    RecyclerView mAlbumsView;
    ArrayList<Album> mAlbumList;
    ArtistsAdapter mAlbumAdapter;
    TextView artistName;
    ImageView artistImage;
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artist, container, false);
        artistName = (TextView) view.findViewById(R.id.artistName);
        artistImage = (ImageView) view.findViewById(R.id.artistImage);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        mAlbumsView = view.findViewById(R.id.featuringRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager HorizontalLayout;
        HorizontalLayout = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        mAlbumsView.setLayoutManager(mLayoutManager);
        mAlbumsView.setHasFixedSize(true);
        mAlbumsView.setLayoutManager(HorizontalLayout);

        Call<ArrayList<Album>> call = apiInterface.getAlbums("61406b98395c385c1a3ec027");
        call.enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {

            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {

            }
        });
        return view;
    }
}