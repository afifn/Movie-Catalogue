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
import com.kuycoding.jetpack.data.source.local.entity.TvEntity;
import com.kuycoding.jetpack.ui.tvshow.detail.DetailTvShowActivity;

import java.util.ArrayList;
import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {
    private Activity activity;
    private List<TvEntity> tvshow = new ArrayList<>();

    public TvAdapter(Activity activity) {
        this.activity = activity;
    }

    private List<TvEntity> getListTvShows(){
        return tvshow;
    }

    public void setListTvshow(List<TvEntity> listTvshow) {
        if (listTvshow == null)
            return;
        this.tvshow.clear();
        this.tvshow.addAll(listTvshow);
    }

    @NonNull
    @Override
    public TvAdapter.TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent,false);
        return new TvViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TvAdapter.TvViewHolder holder, int position) {
        holder.tvTitle.setText(getListTvShows().get(position).getName());
        holder.tvVoteAverage.setText(getListTvShows().get(position).getVote_average());
        holder.tvGenre.setText(getListTvShows().get(position).getOverview());
        holder.tvDate.setText(getListTvShows().get(position).getFirst_air_date());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailTvShowActivity.class);
            intent.putExtra(DetailTvShowActivity.EXTRA_TV, getListTvShows().get(position).getId());
            activity.startActivity(intent);
        });
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500" + getListTvShows().get(position).getPoster_path())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return getListTvShows().size();
    }

    class TvViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvVoteAverage;
        private TextView tvGenre;
        private TextView tvDate;
        private ImageView imgPoster;

        TvViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle  = itemView.findViewById(R.id.tv_item_title);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvGenre = itemView.findViewById(R.id.tv_item_genre);
            tvVoteAverage = itemView.findViewById(R.id.tv_item_userscore);
            imgPoster = itemView.findViewById(R.id.img_item_poster);
        }
    }
}
