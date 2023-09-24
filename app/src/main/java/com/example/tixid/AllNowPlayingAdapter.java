package com.example.tixid;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllNowPlayingAdapter extends RecyclerView.Adapter<AllNowPlayingAdapter.ViewHolder> {
    Context context;
    ArrayList<Movie> allNowPlayingMovie;





    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView rowImage;
        TextView rowName, rowGenre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowImage = itemView.findViewById(R.id.ivAllNowPlayingPoster);
            rowName = itemView.findViewById(R.id.tvSingleMovieName);
            rowGenre = itemView.findViewById(R.id.tvSingleMovieGenre);

        }
    }

    public AllNowPlayingAdapter(Context context, ArrayList<Movie> allNowPlayingMovie) {
        this.context = context;
        this.allNowPlayingMovie = allNowPlayingMovie;

    }

    @NonNull
    @Override
    public AllNowPlayingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_allnowplaying_movies, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllNowPlayingAdapter.ViewHolder holder, int position) {
        holder.rowName.setText(allNowPlayingMovie.get(position).getName());
        holder.rowGenre.setText(allNowPlayingMovie.get(position).getGenre());

        String imageUri = allNowPlayingMovie.get(position).getUrl();
        Picasso.get().load(imageUri).into(holder.rowImage);

    }

    @Override
    public int getItemCount() {
        return allNowPlayingMovie.size();
    }






}
