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
    private FilmInfos filmInfos;
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

        Call<FilmInfos> call = filmsService.searchMovieDetails(id,filmsService.API_KEY,filmsService.LANGUAGE,filmsService.PAGE);
        call.enqueue(new Callback<FilmInfos>(){
            @Override
            public void onResponse(Call<FilmInfos> call, Response<FilmInfos> response) {
                if(!response.isSuccessful() ){
                    Log.i("ERROR CODE",String.valueOf(response.code()));
                    return;
                }
                filmInfos = response.body();
                Glide.with(FilmActivity.this).load("https://image.tmdb.org/t/p/original"+filmInfos.getBackdrop_path()).into(imageDescription);
                





            }
            @Override
            public void onFailure(Call<FilmInfos> call, Throwable t) {
                Log.i("ON FAILURE",t.getMessage());
            }
        });



    }

}
