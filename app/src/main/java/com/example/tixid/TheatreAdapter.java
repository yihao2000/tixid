package com.example.tixid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TheatreAdapter extends RecyclerView.Adapter<TheatreAdapter.ViewHolder> {
    Context context;
    List<Theatre> theatreList;
    RecyclerView rvTheatres;
    MainActivity main;

    final View.OnClickListener onClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.tvTheatreName);
        }
    }


    public TheatreAdapter(Context context, List<Theatre> theatreList, RecyclerView rvTheatres, MainActivity main){
        this.context = context;
        this.theatreList = theatreList;
        this.rvTheatres = rvTheatres;
        this.main = main;
        onClickListener= new MyOnClickListener(main);
    }


    @NonNull
    @Override
    public TheatreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_theatres, parent, false);

        view.setOnClickListener(onClickListener);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TheatreAdapter.ViewHolder holder, int position) {
        Theatre theatre = theatreList.get(position);

        holder.rowName.setText(theatre.getName());

    }

    @Override
    public int getItemCount() {
        return theatreList.size();
    }

    private class MyOnClickListener implements View.OnClickListener {
        MainActivity main;

        public MyOnClickListener(MainActivity main){
            this.main = main;
        }
        @Override
        public void onClick(View view) {
            int itemPosition = rvTheatres.getChildLayoutPosition(view);
            String item = theatreList.get(itemPosition).getName();
            double x = theatreList.get(itemPosition).getX();
            double y = theatreList.get(itemPosition).getY();
            Toast.makeText(context, item, Toast.LENGTH_SHORT).show();
            main.replaceFragment(new MapsFragment(x, y));
            
        }


    }




}
