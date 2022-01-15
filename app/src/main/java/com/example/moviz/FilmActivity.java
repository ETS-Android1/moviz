package com.example.moviz;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmActivity  extends AppCompatActivity {
    private FilmsService filmsService;
    private ImageView imageDescription;
    private FilmInfosList filmInfosList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        imageDescription = findViewById(R.id.image_description);
        Intent intent = getIntent();
        int id = intent.getExtras().getInt("id");

        filmsService = new Retrofit.Builder()
                .baseUrl(filmsService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FilmsService.class);

        Call<FilmInfosList> call = filmsService.searchMovieDetails(id,filmsService.API_KEY,filmsService.LANGUAGE,filmsService.PAGE);
        call.enqueue(new Callback<FilmInfosList>(){
            @Override
            public void onResponse(Call<FilmInfosList> call, Response<FilmInfosList> response) {
                if(!response.isSuccessful() ){
                    Log.i("ERROR CODE",String.valueOf(response.code()));
                    return;
                }
                filmInfosList = response.body();
                Log.i("INFOS", String.valueOf(filmInfosList.getSize()));
                //Log.i("INFOS",filmInfosList.getFilmInfos(0).getBackdrop_path());
            }
            @Override
            public void onFailure(Call<FilmInfosList> call, Throwable t) {
                Log.i("ON FAILURE",t.getMessage());
            }
        });



    }

}
