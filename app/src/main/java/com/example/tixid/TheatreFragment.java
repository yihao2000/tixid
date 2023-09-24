package com.example.tixid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TheatreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TheatreFragment extends Fragment {
    DBHelper myDb;
    RecyclerView rvTheatres;
    TheatreAdapter theatreAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Theatre> theatresList = new ArrayList<>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_theatre, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        runProgram();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        runProgram();

    }

    public void runProgram(){
        myDb = new DBHelper(getActivity().getApplicationContext());

        theatresList = myDb.getAllTheatres();
        rvTheatres = getActivity().findViewById(R.id.rvTheatres);
        rvTheatres.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rvTheatres.setLayoutManager(layoutManager);

        theatreAdapter = new TheatreAdapter(getActivity().getApplicationContext(), theatresList, rvTheatres, (MainActivity) getActivity());
        rvTheatres.setAdapter(theatreAdapter);
    }
}