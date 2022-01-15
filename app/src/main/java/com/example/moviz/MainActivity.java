package com.example.moviz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private FilmsService filmsService;
    private FilmsList filmsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmsService = new Retrofit.Builder()
                .baseUrl(filmsService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FilmsService.class);

        Call<FilmsList> call = filmsService.searchPopularMovies(filmsService.API_KEY,filmsService.LANGUAGE,filmsService.PAGE);
        call.enqueue(new Callback<FilmsList>(){
            @Override
            public void onResponse(Call<FilmsList> call, Response<FilmsList> response) {
                if(!response.isSuccessful() ){
                    Log.i("ERROR CODE",String.valueOf(response.code()));
                    return;
                }
                filmsList = response.body();
                RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
                RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(filmsList,MainActivity.this);
                myrv.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                myrv.setAdapter(myAdapter);

            }
            @Override
            public void onFailure(Call<FilmsList> call, Throwable t) {
                Log.i("ON FAILURE",t.getMessage());
            }
        });



    }
}