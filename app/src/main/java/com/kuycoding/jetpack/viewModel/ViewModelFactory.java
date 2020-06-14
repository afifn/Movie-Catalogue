package com.kuycoding.jetpack.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kuycoding.jetpack.data.source.MovieTvRepository;
import com.kuycoding.jetpack.di.Injection;
import com.kuycoding.jetpack.ui.movie.detail.DetailMovieViewModel;
import com.kuycoding.jetpack.ui.tvshow.detail.DetailTvShowViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final MovieTvRepository mAcademyRepository;

    private ViewModelFactory(MovieTvRepository academyRepository) {
        mAcademyRepository = academyRepository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            //noinspection unchecked
            return (T) new MovieViewModel(mAcademyRepository);
        }
        else if (modelClass.isAssignableFrom(TVShowViewModel.class)) {
            //noinspection unchecked
            return (T) new TVShowViewModel(mAcademyRepository);
        }
        else if (modelClass.isAssignableFrom(DetailMovieViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailMovieViewModel(mAcademyRepository);
        }
        else if (modelClass.isAssignableFrom(DetailTvShowViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailTvShowViewModel(mAcademyRepository);
        }
        else if (modelClass.isAssignableFrom(FavMovieViewModel.class)) {
            return (T) new FavMovieViewModel(mAcademyRepository);
        }
        else if (modelClass.isAssignableFrom(FavTvShowViewModel.class)) {
            return (T) new FavTvShowViewModel(mAcademyRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
