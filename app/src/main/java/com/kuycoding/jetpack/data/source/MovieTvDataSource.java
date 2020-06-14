package com.kuycoding.jetpack.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.kuycoding.jetpack.data.source.local.entity.MovieEntity;
import com.kuycoding.jetpack.data.source.local.entity.TvEntity;
import com.kuycoding.jetpack.vo.Resource;

import java.util.List;

public interface MovieTvDataSource {
    LiveData<Resource<List<MovieEntity>>> getAllMoview();
    LiveData<Resource<MovieEntity>> getAllMovieDetail(String courseId);
    LiveData<Resource<PagedList<MovieEntity>>> getFavoriteMoviePagged();
    void setFavMovie(MovieEntity course, boolean state);

    LiveData<Resource<List<TvEntity>>> getAllTv();
    LiveData<Resource<TvEntity>> getAllTvDetail(String courseId);
    LiveData<Resource<PagedList<TvEntity>>> getFavoriteTvShwoPagged();
    void setFavTvShwo(TvEntity course, boolean state);
}
