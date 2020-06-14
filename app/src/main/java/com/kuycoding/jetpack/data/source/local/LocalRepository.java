package com.kuycoding.jetpack.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.kuycoding.jetpack.data.source.local.entity.MovieEntity;
import com.kuycoding.jetpack.data.source.local.entity.TvEntity;
import com.kuycoding.jetpack.data.source.local.room.MovieTvDao;

import java.util.List;

public class LocalRepository {
    private final MovieTvDao movieTvDao;


    private LocalRepository(MovieTvDao movieTvDao) {
        this.movieTvDao = movieTvDao;
    }

    private static LocalRepository INSTANCE;

    public static LocalRepository getInstance(MovieTvDao movieTvDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(movieTvDao);
        }
        return INSTANCE;
    }

    public LiveData<List<MovieEntity>> getAllMovies() {
        return movieTvDao.getFavMovie();
    }

    public LiveData<MovieEntity> getWithMovies(final String courseId) {
        return movieTvDao.getMovieById(courseId);
    }

    public void insertMovie(List<MovieEntity> courses) {
        movieTvDao.insertMovies(courses);
    }

    public DataSource.Factory<Integer, MovieEntity> getBookmarkMoviePagged() {
        return movieTvDao.getFavMoviePagging();
    }

    public void setFavMovie(MovieEntity movie, boolean newState) {
        movie.setFavorite(newState);
        movieTvDao.updateMovie(movie);
    }
//    tv
    public LiveData<List<TvEntity>> getAllTvShows() {
        return movieTvDao.getFavTvShow();
    }

    public LiveData<TvEntity> getWithTvShow(final String courseId) {
        return movieTvDao.getTvShowById(courseId);
    }

    public void inserTvShow(List<TvEntity> courses) {
        movieTvDao.insertTvShow(courses);
    }

    public DataSource.Factory<Integer, TvEntity> getBookmarkTvShowPagged(){
        return movieTvDao.getFavTvShowPagging();
    }

    public void setFavTvShow(TvEntity tvShow, boolean newState) {
        tvShow.setFavorite(newState);
        movieTvDao.updateTvShow(tvShow);
    }
}
