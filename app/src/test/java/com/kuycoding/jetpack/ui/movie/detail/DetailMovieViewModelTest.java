package com.kuycoding.jetpack.ui.movie.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.kuycoding.jetpack.R;
import com.kuycoding.jetpack.data.source.MovieTvRepository;
import com.kuycoding.jetpack.data.source.local.entity.MovieEntity;
import com.kuycoding.jetpack.utils.FakeDataDummy;
import com.kuycoding.jetpack.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailMovieViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailMovieViewModel viewModel;
    private MovieTvRepository academyRepository = mock(MovieTvRepository.class);
    private MovieEntity dummyCourse = FakeDataDummy.generateDataMovie().get(0);
    private String courseId = dummyCourse.getId();

    @Before
    public void setUp(){
        viewModel = new DetailMovieViewModel(academyRepository);
        viewModel.setCourseId(courseId);
    }


    @Test
    public void getMovieDetail(){
        Resource<MovieEntity> resource = Resource.success(FakeDataDummy.generateDummyMovie(dummyCourse, true));
        MutableLiveData<Resource<MovieEntity>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(resource);

        when(academyRepository.getAllMovieDetail(courseId)).thenReturn(mutableLiveData);
        Observer<Resource<MovieEntity>> observer = mock(Observer.class);
        viewModel.getCourses.observeForever(observer);
        verify(observer).onChanged(resource);
    }
}