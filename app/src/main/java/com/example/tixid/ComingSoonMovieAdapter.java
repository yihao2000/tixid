package com.example.tixid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ComingSoonMovieAdapter extends RecyclerView.Adapter<ComingSoonMovieAdapter.ViewHolder> {

    ArrayList<Movie> comingSoonMovieList;
    Context context;
    final View.OnClickListener onClickListener;
    RecyclerView rvMovies;


    public ComingSoonMovieAdapter(Context context, ArrayList<Movie> comingSoonMovieList, MainActivity main, RecyclerView recyclerView) {

        this.context = context;
        this.comingSoonMovieList = comingSoonMovieList;
        onClickListener = new ComingSoonMovieAdapter.MyOnClickListener(main);
        rvMovies = recyclerView;
    }

    @NonNull
    @Override
    public ComingSoonMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_comingsoon, parent, false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComingSoonMovieAdapter.ViewHolder holder, int position) {

        String imageUri = comingSoonMovieList.get(position).getUrl();
        Picasso.get().load(imageUri).into(holder.imageView);


        holder.textView.setText(comingSoonMovieList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return comingSoonMovieList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivComingSoonMoviePoster);
            textView = itemView.findViewById(R.id.tvComingSoonMovieName);

        }
    }

    public class MyOnClickListener implements View.OnClickListener {
        MainActivity main;

        public MyOnClickListener(MainActivity main){
            this.main = main;
        }
        @Override
        public void onClick(View view) {
            int itemPosition =rvMovies.getChildLayoutPosition(view);
            String movieName = comingSoonMovieList.get(itemPosition).getName();
            String url = comingSoonMovieList.get(itemPosition).getUrl();
            String description = comingSoonMovieList.get(itemPosition).getDescription();
            String genre = comingSoonMovieList.get(itemPosition).getGenre();
            String duration = comingSoonMovieList.get(itemPosition).getDuration();
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
