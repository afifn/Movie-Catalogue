package com.kuycoding.jetpack.ui.movie.detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kuycoding.jetpack.data.source.MovieTvRepository;
import com.kuycoding.jetpack.data.source.local.entity.MovieEntity;
import com.kuycoding.jetpack.vo.Resource;

import static android.content.ContentValues.TAG;

public class DetailMovieViewModel extends ViewModel {
    private MovieTvRepository academyRepository;
    private MutableLiveData<String> courseId = new MutableLiveData<>();

    public DetailMovieViewModel(MovieTvRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    LiveData<Resource<MovieEntity>> getCourses = Transformations.switchMap(courseId,
            mCourseId -> academyRepository.getAllMovieDetail(mCourseId));

    public void setCourseId(String courseId){
        this.courseId.setValue(courseId);
    }

    public String getCourseId() {
        if (courseId.getValue() == null) return null;
        return courseId.getValue();
    }
    void setFavorite() {
        if (getCourses.getValue() != null) {
            MovieEntity courseEntity = getCourses.getValue().data;
            Log.e(TAG, "setFavorited: " + courseEntity );

            assert courseEntity != null;
            final boolean newState = !courseEntity.isFavorite();
            Log.e(TAG, "setFavoritedState: "+newState);

            academyRepository.setFavMovie(courseEntity, newState);
        }
    }
}
