package com.example.moviz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FilmsService {

    public static final String ENDPOINT = "https://api.themoviedb.org/3/movie/";
    public static int PAGE=1;
    public static String API_KEY ="cc7910d91f67906ede8d5894df4d4894";
    public static String LANGUAGE="en-US";
    @GET("popular")
    Call<FilmsList> searchPopularMovies(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("{movie_id}")
    Call<FilmInfos> searchMovieDetails(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page
    );

}
