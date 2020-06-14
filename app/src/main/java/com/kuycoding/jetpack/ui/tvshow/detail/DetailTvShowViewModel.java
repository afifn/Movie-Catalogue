package com.kuycoding.jetpack.ui.tvshow.detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kuycoding.jetpack.data.source.MovieTvRepository;
import com.kuycoding.jetpack.data.source.local.entity.TvEntity;
import com.kuycoding.jetpack.vo.Resource;

import static android.content.ContentValues.TAG;

public class DetailTvShowViewModel extends ViewModel {
    private MovieTvRepository academyRepository;
    private MutableLiveData<String> courseId = new MutableLiveData<>();

    public DetailTvShowViewModel(MovieTvRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    LiveData<Resource<TvEntity>> getCourses = Transformations.switchMap(courseId,
            id -> academyRepository.getAllTvDetail(id));

    public void setCourseId(String courseId){
        this.courseId.setValue(courseId);
    }

    public String getCourseId() {
        if (courseId.getValue() == null) return null;
        return courseId.getValue();
    }

    void setFavorite() {
        if (getCourses.getValue() != null) {
            TvEntity courseEntity = getCourses.getValue().data;
            Log.e(TAG, "setFavorited: " + courseEntity );

            assert courseEntity != null;
            final boolean newState = !courseEntity.isFavorite();
            Log.e(TAG, "setFavoritedState: "+newState);

            academyRepository.setFavTvShwo(courseEntity, newState);
        }
    }
}
