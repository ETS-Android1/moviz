package com.example.moviz;

import java.util.List;

public class FilmInfos {
    private String backdrop_path;
    private List<Genres> genres;
    private String overview;
    private String release_date;

    public List<Genres> getGenres() {
        return genres;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }
}
