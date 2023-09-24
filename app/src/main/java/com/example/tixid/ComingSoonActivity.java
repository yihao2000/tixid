package com.example.tixid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ComingSoonActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter movieAdapter;
    RecyclerView.LayoutManager layoutManager;
    DBHelper myDB;
    ArrayList<Movie> comingSoonMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_soon);

        myDB = new DBHelper(this);

        comingSoonMovieList = myDB.getComingSoonMovie();


        recyclerView = findViewById(R.id.rvAllComingSoon);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        movieAdapter = new ComingSoonAdapter(this, comingSoonMovieList);




    }
}