package com.kuycoding.jetpack.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.kuycoding.jetpack.data.source.MovieTvRepository;
import com.kuycoding.jetpack.data.source.local.entity.MovieEntity;
import com.kuycoding.jetpack.vo.Resource;


public class FavMovieViewModel extends ViewModel {

    private MovieTvRepository academyRepository;

    public FavMovieViewModel(MovieTvRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    public LiveData<Resource<PagedList<MovieEntity>>> getBookmarksPaged() {
        return academyRepository.getFavoriteMoviePagged();
    }

    public void setBookmark(MovieEntity courseEntity) {
        final boolean newState = !courseEntity.isFavorite();
        academyRepository.setFavMovie(courseEntity, newState);
    }
}
