package com.kuycoding.jetpack.data.source.remote;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kuycoding.jetpack.data.source.remote.response.MovieModel;
import com.kuycoding.jetpack.data.source.remote.response.TvModel;
import com.kuycoding.jetpack.utils.EspressoIdlingResource;
import com.kuycoding.jetpack.utils.JsonHelper;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private JsonHelper jsonHelper;
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;


    private RemoteRepository(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteRepository getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(helper);
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<MovieModel>>> getMovie() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<MovieModel>>> resultMovie = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultMovie.setValue(ApiResponse.success(jsonHelper.loadMovie()));
            Log.e(TAG, "getMovie: "+ jsonHelper.loadMovie() );
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultMovie;
    }

    public LiveData<ApiResponse<List<MovieModel>>> getAllModulesByMovieAsLiveData(String courseId) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<MovieModel>>> resultModule = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultModule.setValue(ApiResponse.success(jsonHelper.loadMovieById(courseId)));
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        },SERVICE_LATENCY_IN_MILLIS);
        return resultModule;
    }

    public LiveData<ApiResponse<List<TvModel>>> getTv() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<TvModel>>> resultTv = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultTv.setValue(ApiResponse.success(jsonHelper.loadTv()));
            Log.e(TAG, "getMovie: "+ jsonHelper.loadTv() );
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultTv;
    }

    public LiveData<ApiResponse<List<TvModel>>> getAllModulesByTVshowAsLiveData(String courseId){
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<TvModel>>> resultTv = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultTv.setValue(ApiResponse.success(jsonHelper.loadTvShowById(courseId)));
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        },SERVICE_LATENCY_IN_MILLIS);
        return resultTv;
    }

    public interface LoadMovieCallback {
        void onAllCoursesReceived(List<MovieModel> movieModels);

        void onDataNotAvailable();
    }
    public interface LoadTvCallback {
        void onAllCoursesReceived(List<TvModel> tvShowModels);

        void onDataNotAvailable();
    }

}
