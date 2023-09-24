package com.example.tixid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ComingSoonAdapter extends RecyclerView.Adapter<ComingSoonAdapter.ViewHolder> {

    Context context;
    ArrayList<Movie> comingSoonMovieList;



    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView rowImage;
        TextView rowName, rowGenre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowImage = itemView.findViewById(R.id.ivComingSoonPoster);
            rowName = itemView.findViewById(R.id.tvSingleComingSoonMovieName);
            rowGenre = itemView.findViewById(R.id.tvSingleComingSoonMovieGenre);

        }
    }

    public ComingSoonAdapter(Context context, ArrayList<Movie> comingSoonMovieList) {
        this.context = context;
        this.comingSoonMovieList = comingSoonMovieList;
    }

    @NonNull
    @Override
    public ComingSoonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_allcomingsoon_movies, parent, false);
        ComingSoonAdapter.ViewHolder viewHolder = new ComingSoonAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComingSoonAdapter.ViewHolder holder, int position) {
        holder.rowName.setText(comingSoonMovieList.get(position).getName());
        holder.rowGenre.setText(comingSoonMovieList.get(position).getGenre());

        String imageUri = comingSoonMovieList.get(position).getUrl();
        Picasso.get().load(imageUri).into(holder.rowImage);

    }

    @Override
    public int getItemCount() {
        return comingSoonMovieList.size();
    }
}
