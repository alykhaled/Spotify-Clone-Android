package com.alykhaled.spotifyclone.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alykhaled.spotifyclone.API.APIClient;
import com.alykhaled.spotifyclone.API.APIInterface;
import com.alykhaled.spotifyclone.R;
import com.alykhaled.spotifyclone.adapter.TracksAdapter;
import com.alykhaled.spotifyclone.models.Album;
import com.alykhaled.spotifyclone.models.Track;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumFragment extends Fragment {
    String albumID = "";
    APIInterface apiInterface;
    ImageView albumImage;
    TextView albumName;
    TextView albumArtist;
    ArrayList<Track> tracksList;
    TracksAdapter tracksAdapter;
    RecyclerView tracksView;


    public AlbumFragment() {
        // Required empty public constructor
    }

    public static AlbumFragment newInstance(String param1, String param2) {
        AlbumFragment fragment = new AlbumFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle id = getArguments();
            albumID = id.getString("albumId");
        }
    }
    public static int getDominantColor(Bitmap bitmap) {
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 1, 1, true);
        final int color = newBitmap.getPixel(0, 0);
        newBitmap.recycle();
        return color;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        RelativeLayout gradientLayout = view.findViewById(R.id.gradientBackground);

        albumImage = (ImageView) view.findViewById(R.id.albumCoverImage);
        albumName = (TextView) view.findViewById(R.id.albumName);
        albumArtist = (TextView) view.findViewById(R.id.albumArtistName);
        tracksView = view.findViewById(R.id.albumTrackView);
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);;

        tracksView.setHasFixedSize(true);
        tracksView.setLayoutManager(HorizontalLayout);
        tracksList = new ArrayList<>();

        Call<Album> getAlbum = apiInterface.getAlbum(albumID);
        getAlbum.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                Album album = response.body();
                Picasso.get().load(album.getImage()).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                        Palette.generateAsync(bitmap, palette -> {
                            GradientDrawable gd = new GradientDrawable(
                                    GradientDrawable.Orientation.TOP_BOTTOM,
                                    new int[] {palette.getDominantColor(0xFF131313),0xFF131313});
                            gd.setCornerRadius(0f);
                            gradientLayout.setBackground(gd);
                            gradientLayout.getBackground().setDither(true);

                        });
                        int color = getDominantColor(bitmap);
                        albumImage.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
                albumName.setText(album.getName());
                albumArtist.setText(album.getArtist());



            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                t.printStackTrace();
            }
        });
        Call<ArrayList<Track>> getAlbumTracks = apiInterface.getAlbumTracks(albumID);
        getAlbumTracks.enqueue(new Callback<ArrayList<Track>>() {
            @Override
            public void onResponse(Call<ArrayList<Track>> call, Response<ArrayList<Track>> response) {
                tracksList = response.body();
                tracksAdapter = new TracksAdapter(tracksList);
                tracksView.setAdapter(tracksAdapter);
//                BitmapDrawable drawable = (BitmapDrawable) albumImage.getDrawable();

            }

            @Override
            public void onFailure(Call<ArrayList<Track>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        albumImage.invalidate();


        return view;
    }
}