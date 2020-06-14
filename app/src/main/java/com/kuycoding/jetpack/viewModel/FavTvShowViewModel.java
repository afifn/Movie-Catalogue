package com.kuycoding.jetpack.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.kuycoding.jetpack.data.source.MovieTvRepository;
import com.kuycoding.jetpack.data.source.local.entity.TvEntity;
import com.kuycoding.jetpack.vo.Resource;


public class FavTvShowViewModel extends ViewModel {
    private MovieTvRepository repository;

    public FavTvShowViewModel(MovieTvRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<PagedList<TvEntity>>> getFavTv() {
        return repository.getFavoriteTvShwoPagged();
    }

    public void setBookmark(TvEntity courseEntity) {
        final boolean newState = !courseEntity.isFavorite();
        repository.setFavTvShwo(courseEntity, newState);
    }
}
