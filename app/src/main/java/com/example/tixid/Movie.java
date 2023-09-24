package com.example.tixid;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie{
    private String name;
    private String description;
    private String url;
    private String genre;
    private String duration;

    public Movie(String name, String description, String url,String genre, String duration) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.genre = genre;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getGenre() {
        return genre;
    }

    public String getDuration() {
        return duration;
    }

}
