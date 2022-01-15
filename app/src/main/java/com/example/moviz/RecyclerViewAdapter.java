package com.example.moviz;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    FilmsList films;
    Context context;

    public RecyclerViewAdapter(FilmsList films,Context context) {
        this.films = films;
        this.context = context;
    }
    @NonNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.cardview_item_film,parent,false);
        return new MyViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {
        ImageView mImageView = holder.img_film_thumbnail;
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+films.getFilm(position).getPoster_path()).into(mImageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FilmActivity.class);
                intent.putExtra("id",films.getFilm(position).getId());
                // start the activity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return films.getSize();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img_film_thumbnail;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView,Context ct) {
            super(itemView);
            img_film_thumbnail = (ImageView) itemView.findViewById(R.id.film_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}
