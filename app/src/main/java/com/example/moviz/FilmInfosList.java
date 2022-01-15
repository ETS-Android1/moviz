package com.example.moviz;

import java.util.List;

public class FilmInfosList {
    private List<FilmInfos> filmsInfos;

    public int getSize(){
        return filmsInfos.size();
    }
    public FilmInfos getFilmInfos(int ind){return filmsInfos.get(ind);}

}
