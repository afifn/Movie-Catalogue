package com.kuycoding.jetpack.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kuycoding.jetpack.data.source.MovieTvRepository;
import com.kuycoding.jetpack.data.source.local.entity.MovieEntity;
import com.kuycoding.jetpack.vo.Resource;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private MovieTvRepository academyRepository;
    private MutableLiveData<String> mLogin = new MutableLiveData<>();

    MovieViewModel(MovieTvRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    public LiveData<Resource<List<MovieEntity>>> getCourses =Transformations.switchMap(mLogin,
            data -> academyRepository.getAllMoview());

    public void setUsernameMovie(String username) {
        mLogin.setValue(username);
    }

}
