package com.example.tixid;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    TextView username;
    String imageUri;
    ImageView ivBasicImage;
    ViewPager2 viewPager2;
    RecyclerView recyclerView, recyclerView2;
    DBHelper db;
    ImageButton support;
    Button discoverMore, discoverMore2;


    Handler sliderHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        username = getView().findViewById(R.id.tvUsername);
        username.setText(PreferenceData.getLoggedInUserUsername(getActivity().getApplicationContext()));
        support = getView().findViewById(R.id.ibSupport);

        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MessageActivity.class);
                startActivity(intent);
            }
        });
        viewPager2 = getView().findViewById(R.id.vpImageSlider);

        discoverMore = getView().findViewById(R.id.btnDiscoverMoreNowPlaying);
        discoverMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AllNowPlayingActivity.class);
                startActivity(intent);
            }
        });




        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem("https://i.ibb.co/wJpJNk0/buy1get1rabu.jpg"));
        sliderItems.add(new SliderItem("https://i.ibb.co/r0KqJ2P/cashback.jpg"));
        sliderItems.add(new SliderItem("https://i.ibb.co/BtFSbBB/promotional.jpg"));
        sliderItems.add(new SliderItem("https://i.ibb.co/hD4yJYB/buy1get1.jpg"));


        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
        });

        recyclerView = getView().findViewById(R.id.rvNowPlaying);

        db = new DBHelper(getActivity().getApplicationContext());


        ArrayList<Movie> movieList = db.getAllMovies();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(new MovieAdapter(getActivity().getApplicationContext(), movieList, (MainActivity) getActivity(), recyclerView));

        recyclerView2 = getView().findViewById(R.id.rvComingSoon);




        ArrayList<Movie> comingSoonMovieList = db.getComingSoonMovie();




        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(new ComingSoonMovieAdapter(getActivity().getApplicationContext(), comingSoonMovieList, (MainActivity) getActivity(), recyclerView));


        discoverMore2 = getView().findViewById(R.id.btnDiscoverMoreComingSoon);
        discoverMore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ComingSoonActivity.class);
                startActivity(intent);
            }
        });







    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };
}