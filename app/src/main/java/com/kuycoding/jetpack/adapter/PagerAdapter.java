package com.kuycoding.jetpack.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kuycoding.jetpack.ui.Favorite.tab.movie.FavMovieFragment;
import com.kuycoding.jetpack.ui.Favorite.tab.tvshow.FavTvShowFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FavMovieFragment();
            case 1:
                return new FavTvShowFragment();
                default:
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Movies";
        }
        return "TvShow";
    }

    @Override
    public int getCount() {
        return 2;
    }
}
