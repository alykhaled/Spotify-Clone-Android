package com.alykhaled.spotifyclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.alykhaled.spotifyclone.fragments.ArtistFragment;
import com.alykhaled.spotifyclone.fragments.MainFragment;
import com.alykhaled.spotifyclone.fragments.PlayingFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        Fragment fragment = new MainFragment();
        MainActivity.this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainView, fragment)
                .addToBackStack(null)
                .commit();
    }
}