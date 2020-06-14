package com.kuycoding.jetpack.data.source.local.room;

import android.graphics.Movie;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.kuycoding.jetpack.data.source.local.entity.MovieEntity;
import com.kuycoding.jetpack.data.source.local.entity.TvEntity;

import java.util.List;

@Dao
public interface MovieTvDao {
//    Movie
    @WorkerThread
    @Query("SELECT * FROM movies")
    LiveData<List<MovieEntity>> getFavMovie();

    @WorkerThread
    @Query("SELECT * FROM movies WHERE bookmarked = 1")
    LiveData<List<MovieEntity>> getFavoriteMovie();

    @WorkerThread
    @Query("SELECT * FROM movies WHERE id = :courseId")
    LiveData<MovieEntity> getMovieById(String courseId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMovies(List<MovieEntity> movies);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateMovie(MovieEntity movie);
//    Tv Show

    @WorkerThread
    @Query("SELECT * FROM tvshow")
    LiveData<List<TvEntity>> getFavTvShow();

    @WorkerThread
    @Query("SELECT * FROM tvshow WHERE bookmarked = 1")
    LiveData<List<TvEntity>> getFavoriteTvShow();

    @WorkerThread
    @Query("SELECT * FROM tvshow WHERE id = :courseId")
    LiveData<TvEntity> getTvShowById(String courseId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertTvShow(List<TvEntity> tvshows);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateTvShow(TvEntity tvshow);

//    Pagging
    @Query("SELECT * FROM movies WHERE bookmarked = 1")
    DataSource.Factory<Integer, MovieEntity> getFavMoviePagging();

    @Query("SELECT * FROM tvshow WHERE bookmarked = 1")
    DataSource.Factory<Integer, TvEntity> getFavTvShowPagging();
}
