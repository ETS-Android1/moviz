package com.example.moviz;

import java.util.List;

public class FilmsList {
    private List<Film> results;

    public int getSize(){
        return results.size();
    }
    public Film getFilm(int ind){return results.get(ind);}
}
