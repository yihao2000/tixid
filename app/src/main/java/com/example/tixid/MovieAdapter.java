package com.example.tixid;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    ArrayList<Movie> movieList;
    Context context;
    RecyclerView rvMovies;

    final View.OnClickListener onClickListener;


    public MovieAdapter(Context context, ArrayList<Movie> movieList, MainActivity main, RecyclerView rvMovies){
        this.context = context;
        this.movieList = movieList;
        this.rvMovies = rvMovies;
        onClickListener = new MovieAdapter.MyOnClickListener(main);

    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_movies,parent,false);
        view.setOnClickListener(onClickListener);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {

        String imageUri = movieList.get(position).getUrl();
        Picasso.get().load(imageUri).into(holder.imageView);


        holder.textView.setText(movieList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivMoviePoster);
            textView = itemView.findViewById(R.id.tvMovieName);


        }
    }



    public class MyOnClickListener implements View.OnClickListener {
        MainActivity main;

        public MyOnClickListener(MainActivity main){
            this.main = main;
        }
        @Override
        public void onClick(View view) {

            int itemPosition = rvMovies.getChildLayoutPosition(view);
            String movieName = movieList.get(itemPosition).getName();
            String url = movieList.get(itemPosition).getUrl();
            String description = movieList.get(itemPosition).getDescription();
            String genre = movieList.get(itemPosition).getGenre();
            String duration = movieList.get(itemPosition).getDuration();
            Intent intent = new Intent(main.getApplicationContext(), MovieDetailActivity.class);
            intent.putExtra("name", movieName);
            intent.putExtra("url", url);
            intent.putExtra("description", description);
            intent.putExtra("genre", genre);
            intent.putExtra("duration", duration);
            main.startActivity(intent);

        }


    }



}
