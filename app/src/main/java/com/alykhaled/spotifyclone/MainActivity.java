package com.alykhaled.spotifyclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

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