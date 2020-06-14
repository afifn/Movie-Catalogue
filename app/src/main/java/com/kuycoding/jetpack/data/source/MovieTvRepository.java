package com.kuycoding.jetpack.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.kuycoding.jetpack.data.source.local.LocalRepository;
import com.kuycoding.jetpack.data.source.local.entity.MovieEntity;
import com.kuycoding.jetpack.data.source.local.entity.TvEntity;
import com.kuycoding.jetpack.data.source.remote.ApiResponse;
import com.kuycoding.jetpack.data.source.remote.RemoteRepository;
import com.kuycoding.jetpack.data.source.remote.response.MovieModel;
import com.kuycoding.jetpack.data.source.remote.response.TvModel;
import com.kuycoding.jetpack.utils.AppExecutors;
import com.kuycoding.jetpack.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class MovieTvRepository implements MovieTvDataSource{
    private volatile static MovieTvRepository INSTANCE = null;

    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;
    private final AppExecutors appExecutors;

    private MovieTvRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository, AppExecutors appExecutors) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.appExecutors = appExecutors;
    }

    public static MovieTvRepository getInstance(LocalRepository localRepository, RemoteRepository remoteData, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (MovieTvRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieTvRepository(localRepository, remoteData, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<MovieEntity>>> getAllMoview() {
        return new NetworkBoundResource<List<MovieEntity>, List<MovieModel>>(appExecutors) {
            @Override
            protected LiveData<List<MovieEntity>> loadFromDB() {
                return localRepository.getAllMovies();
            }

            @Override
            protected Boolean shouldFetch(List<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieModel>>> createCall() {
                return remoteRepository.getMovie();
            }

            @Override
            protected void saveCallRequest(List<MovieModel> data) {
                List<MovieEntity> courseEntity = new ArrayList<>();
                for (MovieModel response : data) {
                    courseEntity.add(new MovieEntity(
                            response.getPopularity(), response.getVote_count(), response.getPoster_path(), response.getId(),
                            response.getAdult(), response.getBackdrop_path(), response.getOriginal_language(), response.getOriginal_title(),
                            response.getGenre_ids(), response.getTitle(), response.getVote_average(), response.getOverview(), response.getRelease_date()
                    ));
                }
                localRepository.insertMovie(courseEntity);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieEntity>> getAllMovieDetail(String courseId) {
        return new NetworkBoundResource<MovieEntity, List<MovieModel>>(appExecutors) {

            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localRepository.getWithMovies(courseId);
            }

            @Override
            protected Boolean shouldFetch(MovieEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieModel>>> createCall() {
                return remoteRepository.getAllModulesByMovieAsLiveData(courseId);
            }

            @Override
            protected void saveCallRequest(List<MovieModel> data) {
                List<MovieEntity> movieEntities = new ArrayList<>();
                for (MovieModel response : data) {
                    movieEntities.add(new MovieEntity(
                            response.getPopularity(), response.getVote_count(), response.getPoster_path(), response.getId(),
                            response.getAdult(), response.getBackdrop_path(), response.getOriginal_language(), response.getOriginal_title(),
                            response.getGenre_ids(), response.getTitle(), response.getVote_average(), response.getOverview(), response.getRelease_date()));
                }
                localRepository.insertMovie(movieEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getFavoriteMoviePagged() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieModel>>(appExecutors) {

            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getBookmarkMoviePagged(),20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieModel>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallRequest(List<MovieModel> data) {

            }
        }.asLiveData();
    }

    @Override
    public void setFavMovie(MovieEntity course, boolean state) {
        Runnable runnable = () -> localRepository.setFavMovie(course, state);
        appExecutors.diskIO().execute(runnable);
    }

    // Tv

    @Override
    public LiveData<Resource<List<TvEntity>>> getAllTv() {
        return new NetworkBoundResource<List<TvEntity>, List<TvModel>>(appExecutors) {

            @Override
            protected LiveData<List<TvEntity>> loadFromDB() {
                return localRepository.getAllTvShows();
            }

            @Override
            protected Boolean shouldFetch(List<TvEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<TvModel>>> createCall() {
                return remoteRepository.getTv();
            }

            @Override
            protected void saveCallRequest(List<TvModel> data) {
                List<TvEntity> tvEntities = new ArrayList<>();
                for (TvModel response : data){
                    tvEntities.add(new TvEntity(
                            response.getOriginal_name(), response.getGenre_ids(), response.getName(), response.getPopularity(),
                            response.getOrigin_country(), response.getVote_count(), response.getFirst_air_date(),
                            response.getBackdrop_path(), response.getOriginal_language(), response.getId(), response.getVote_average(),
                            response.getOverview(), response.getPoster_path()
                    ));
                }
                localRepository.inserTvShow(tvEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvEntity>> getAllTvDetail(String courseId) {
        return new NetworkBoundResource<TvEntity, List<TvModel>>(appExecutors) {

            @Override
            protected LiveData<TvEntity> loadFromDB() {
                return localRepository.getWithTvShow(courseId);
            }

            @Override
            protected Boolean shouldFetch(TvEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<List<TvModel>>> createCall() {
                return remoteRepository.getAllModulesByTVshowAsLiveData(courseId);
            }

            @Override
            protected void saveCallRequest(List<TvModel> data) {
                List<TvEntity> tvEntities = new ArrayList<>();
                for (TvModel response : data) {
                    tvEntities.add(new TvEntity(
                            response.getOriginal_name(), response.getGenre_ids(), response.getName(),
                            response.getPopularity(), response.getOrigin_country(), response.getVote_count(), response.getFirst_air_date(),
                            response.getBackdrop_path(), response.getOriginal_language(), response.getId(),
                            response.getVote_average(), response.getOverview(), response.getPoster_path()
                    ));
                }
                localRepository.inserTvShow(tvEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TvEntity>>> getFavoriteTvShwoPagged() {
        return new NetworkBoundResource<PagedList<TvEntity>, List<TvModel>>(appExecutors) {

            @Override
            protected LiveData<PagedList<TvEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getBookmarkTvShowPagged(),20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TvEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TvModel>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallRequest(List<TvModel> data) {

            }
        }.asLiveData();
    }

    @Override
    public void setFavTvShwo(TvEntity course, boolean state) {
        Runnable runnable = () -> localRepository.setFavTvShow(course, state);
        appExecutors.diskIO().execute(runnable);
    }
}
