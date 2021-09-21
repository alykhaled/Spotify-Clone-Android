package com.alykhaled.spotifyclone.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.alykhaled.spotifyclone.API.APIClient;
import com.alykhaled.spotifyclone.API.APIInterface;
import com.alykhaled.spotifyclone.adapter.RecentlyPlayedAdapter;
import com.alykhaled.spotifyclone.models.Album;
import com.alykhaled.spotifyclone.models.Artist;
import com.alykhaled.spotifyclone.R;
import com.alykhaled.spotifyclone.adapter.ArtistsAdapter;
import com.alykhaled.spotifyclone.models.RecentTrack;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    APIInterface apiInterface;
    RecyclerView mArtistView;
    GridView mRecentlyPlayedView;
    ArrayList<Artist> mArtistList;
    ArtistsAdapter mArtistsAdapter;
    ArrayList<RecentTrack> mRecentlyPlayedList;
    RecentlyPlayedAdapter mRecentlyPlayedAdapter;

    public MainFragment() {

    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mArtistView = view.findViewById(R.id.artistRecyclerView);
        mRecentlyPlayedView = view.findViewById(R.id.recentlyPlayedTracksView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager HorizontalLayout;
        HorizontalLayout = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mArtistView.setLayoutManager(mLayoutManager);
        mArtistView.setHasFixedSize(true);
        mArtistView.setLayoutManager(HorizontalLayout);

        mArtistList = new ArrayList<>();
        mRecentlyPlayedList = new ArrayList<>();

        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<ArrayList<Artist>> getAllArtist = apiInterface.getAllArtist();
        getAllArtist.enqueue(new Callback<ArrayList<Artist>>() {
            @Override
            public void onResponse(Call<ArrayList<Artist>> call, Response<ArrayList<Artist>> response) {
                mArtistList = response.body();
                Collections.reverse(mArtistList);
                mArtistsAdapter = new ArtistsAdapter(mArtistList);
                mArtistView.setAdapter(mArtistsAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Artist>> call, Throwable t) {
                call.cancel();
                Toast.makeText(getContext(), "FAIL" , Toast.LENGTH_SHORT).show();
            }
        });

        Call<ArrayList<RecentTrack>> getRecentTracks = apiInterface.getRecentTracks();
        getRecentTracks.enqueue(new Callback<ArrayList<RecentTrack>>() {
            @Override
            public void onResponse(Call<ArrayList<RecentTrack>> call, Response<ArrayList<RecentTrack>> response) {
                mRecentlyPlayedList = response.body();
                mRecentlyPlayedAdapter = new RecentlyPlayedAdapter(getContext(),mRecentlyPlayedList);
                mRecentlyPlayedView.setAdapter(mRecentlyPlayedAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<RecentTrack>> call, Throwable t) {
                Toast.makeText(getContext(), "FAIL" , Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }
}