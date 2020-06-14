package com.kuycoding.jetpack.viewModel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.kuycoding.jetpack.data.source.MovieTvRepository;
import com.kuycoding.jetpack.data.source.local.entity.TvEntity;
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

public class TVShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TVShowViewModel viewModel;
    private MovieTvRepository academyRepository = mock(MovieTvRepository.class);

    private String USERNAME = "Kuycoding";
    @Before
    public void setUp() {
        viewModel = new TVShowViewModel(academyRepository);
    }

    @Test
    public void getTv() {
        Resource<List<TvEntity>> dummyCourse = Resource.success(FakeDataDummy.generateDummyTvShow());
        MutableLiveData<Resource<List<TvEntity>>> course = new MutableLiveData<>();
        course.setValue(dummyCourse);

        when(academyRepository.getAllTv()).thenReturn(course);

        Observer<Resource<List<TvEntity>>> observer = mock(Observer.class);
        viewModel.setUsernameTv(USERNAME);
        viewModel.getCourses.observeForever(observer);
        verify(observer).onChanged(dummyCourse);
    }

}