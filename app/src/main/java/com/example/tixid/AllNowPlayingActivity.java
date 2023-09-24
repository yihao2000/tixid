package com.example.tixid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class AllNowPlayingActivity extends AppCompatActivity {
    DBHelper myDB;
    RecyclerView recyclerView;
    RecyclerView.Adapter allNowPlayingAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Movie> allNowPlayingMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_now_playing);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        ActionBar actionBar = getSupportActionBar();


        actionBar.setDisplayHomeAsUpEnabled(true);

        myDB = new DBHelper(this);
        allNowPlayingMovie = myDB.getAllMovies();

        recyclerView = findViewById(R.id.rvAllNowPlaying);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        allNowPlayingAdapter = new AllNowPlayingAdapter(this, allNowPlayingMovie);
        recyclerView.setAdapter(allNowPlayingAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}