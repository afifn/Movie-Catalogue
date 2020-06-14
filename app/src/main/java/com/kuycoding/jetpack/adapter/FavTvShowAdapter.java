package com.kuycoding.jetpack.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kuycoding.jetpack.R;
import com.kuycoding.jetpack.data.source.local.entity.TvEntity;
import com.kuycoding.jetpack.ui.Favorite.FavoriteFragmentCallback;
import com.kuycoding.jetpack.ui.tvshow.detail.DetailTvShowActivity;

public class FavTvShowAdapter extends PagedListAdapter<TvEntity, FavTvShowAdapter.TvShowViewHolder> {
    private FavoriteFragmentCallback callback;

    public FavTvShowAdapter(FavoriteFragmentCallback callback) {
        super(DIFF_CALLBACK);
        this.callback = callback;
    }

    @NonNull
    @Override
    public FavTvShowAdapter.TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new FavTvShowAdapter.TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavTvShowAdapter.TvShowViewHolder holder, int position) {
        final TvEntity favorite = getItem(position);
        if (favorite != null) {
            holder.tvTitle.setText(favorite.getName());
            holder.tvVoteAverage.setText(favorite.getVote_average());
            holder.tvGenre.setText(favorite.getOverview());
            holder.tvDate.setText(favorite.getFirst_air_date());
            holder.itemView.setOnClickListener(v -> {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetailTvShowActivity.class);
                String courseId = favorite.getId();
                intent.putExtra(DetailTvShowActivity.EXTRA_TV, courseId);
                context.startActivity(intent);
            });

            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500" + favorite.getPoster_path())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(holder.imgPoster);
        }
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvVoteAverage;
        final TextView tvGenre;
        final TextView tvDate;
        final ImageView imgPoster;
        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle  = itemView.findViewById(R.id.tv_item_title);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvGenre = itemView.findViewById(R.id.tv_item_genre);
            tvVoteAverage = itemView.findViewById(R.id.tv_item_userscore);
            imgPoster = itemView.findViewById(R.id.img_item_poster);
        }
    }

    private static DiffUtil.ItemCallback<TvEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<TvEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull TvEntity oldItem, @NonNull TvEntity newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull TvEntity oldItem, @NonNull TvEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    public TvEntity getItemById(int swipedPosition) {
        return getItem(swipedPosition);
    }
}
