package com.example.tixid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    TextView movieName, movieGenre, movieDescription, movieDuration;
    ImageView moviePoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        ActionBar actionBar = getSupportActionBar();


        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_movie_detail);
        movieName = findViewById(R.id.tvMovieNem);
        moviePoster = findViewById(R.id.ivMoviePoster);
        movieGenre = findViewById(R.id.tvMovieGenre);
        movieDescription = findViewById(R.id.tvMovieDescription);
        movieDuration = findViewById(R.id.tvMovieDuration);


        String imageUri = (String)getIntent().getExtras().getString("url");
        Picasso.get().load(imageUri).into(moviePoster);
        String movieNames = (String)getIntent().getExtras().getString("name");
        String movieGenres = (String)getIntent().getExtras().getString("genre");
        String movieDescriptions = (String)getIntent().getExtras().getString("description");
        String movieDurations= (String)getIntent().getExtras().getString("duration");
        movieName.setText(movieNames);
        movieGenre.setText(movieGenres);
        movieDescription.setText(movieDescriptions);
        movieDuration.setText(movieDurations);




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