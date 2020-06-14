package com.kuycoding.jetpack.viewModel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.kuycoding.jetpack.data.source.MovieTvRepository;
import com.kuycoding.jetpack.data.source.local.entity.MovieEntity;
import com.kuycoding.jetpack.utils.FakeDataDummy;
import com.kuycoding.jetpack.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MovieViewModel viewModel;
    private MovieTvRepository academyRepository = mock(MovieTvRepository.class);

    private String USERNAME = "Kuycoding";
    @Before
    public void setUp() {
        viewModel = new MovieViewModel(academyRepository);
    }

    @Test
    public void getMovie() {
        Resource<List<MovieEntity>> dummyCourse = Resource.success(FakeDataDummy.generateDataMovie());
        MutableLiveData<Resource<List<MovieEntity>>> course = new MutableLiveData<>();

        course.setValue(dummyCourse);

        when(academyRepository.getAllMoview()).thenReturn(course);

        Observer<Resource<List<MovieEntity>>> observer = mock(Observer.class);
        viewModel.setUsernameMovie(USERNAME);
        viewModel.getCourses.observeForever(observer);
        verify(observer).onChanged(dummyCourse);
    }

}