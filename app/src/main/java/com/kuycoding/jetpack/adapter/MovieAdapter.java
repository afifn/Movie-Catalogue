package com.kuycoding.jetpack.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kuycoding.jetpack.R;
import com.kuycoding.jetpack.data.source.local.entity.MovieEntity;
import com.kuycoding.jetpack.ui.movie.detail.DetailMovieActivity;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewholder> {
    private final Activity activity;
    private List<MovieEntity> movies = new ArrayList<>();

    public MovieAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<MovieEntity> getListMovies() {
        return movies;
    }

    public void setListMovie(List<MovieEntity> listMovie){
        if (listMovie == null)
            return;
        this.movies.clear();
        this.movies.addAll(listMovie);
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewholder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewholder holder, int position) {
        holder.tvTitle.setText(getListMovies().get(position).getTitle());
        holder.tvVoteAverage.setText(getListMovies().get(position).getVote_average());
        holder.tvGenre.setText(getListMovies().get(position).getOverview());
        holder.tvDate.setText(getListMovies().get(position).getRelease_date());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, getListMovies().get(position).getId());
            activity.startActivity(intent);
        });

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185" + getListMovies().get(position).getPoster_path())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getListMovies().size();
    }

    class MovieViewholder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvVoteAverage;
        final TextView tvGenre;
        final TextView tvDate;
        final ImageView imgPoster;

        MovieViewholder(@NonNull View itemView) {
            super(itemView);
            tvTitle  = itemView.findViewById(R.id.tv_item_title);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvGenre = itemView.findViewById(R.id.tv_item_genre);
            tvVoteAverage = itemView.findViewById(R.id.tv_item_userscore);
            imgPoster = itemView.findViewById(R.id.img_item_poster);
        }
    }
}
