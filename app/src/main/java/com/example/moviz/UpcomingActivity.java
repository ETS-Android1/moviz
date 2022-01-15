package com.example.moviz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpcomingActivity extends AppCompatActivity {

    private FilmsService filmsService;
    private FilmsList filmsList;
    private ConstraintLayout boutonPopular;
    private ImageView imageBoutonPopular;
    private ImageView imageBoutonUpcoming;
    private TextView texteBoutonPopular;
    private TextView texteBoutonUpcoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boutonPopular = findViewById(R.id.button_popular);

        imageBoutonPopular = findViewById(R.id.image_popular);
        imageBoutonUpcoming = findViewById(R.id.image_upcoming);
        texteBoutonPopular = findViewById(R.id.text_popular);
        texteBoutonUpcoming = findViewById(R.id.text_upcoming);

        boutonPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("BOUTON","Appui sur le bouton upcoming");
                imageBoutonPopular.setColorFilter(R.color.gray);
                texteBoutonPopular.setTextColor(getResources().getColor(R.color.gray));
                imageBoutonUpcoming.setColorFilter(Color.argb(255, 255, 255, 255));
                texteBoutonUpcoming.setTextColor(getResources().getColor(R.color.white));
                Intent intent = new Intent(UpcomingActivity.this,MainActivity.class);
                // start the activity
                UpcomingActivity.this.startActivity(intent);
            }
        });
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
