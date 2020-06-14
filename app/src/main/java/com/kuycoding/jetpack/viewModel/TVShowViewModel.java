package com.kuycoding.jetpack.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kuycoding.jetpack.data.source.MovieTvRepository;
import com.kuycoding.jetpack.data.source.local.entity.TvEntity;
import com.kuycoding.jetpack.vo.Resource;

import java.util.List;

public class TVShowViewModel extends ViewModel {
    private MovieTvRepository academyRepository;
    private MutableLiveData<String> mLogin = new MutableLiveData<>();

    TVShowViewModel(MovieTvRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }
    public LiveData<Resource<List<TvEntity>>> getCourses = Transformations.switchMap(mLogin,
            data -> academyRepository.getAllTv());
    public void setUsernameTv(String username){
        mLogin.setValue(username);
    }
}
