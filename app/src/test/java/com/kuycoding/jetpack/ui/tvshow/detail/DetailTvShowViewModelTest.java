package com.kuycoding.jetpack.ui.tvshow.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.kuycoding.jetpack.R;
import com.kuycoding.jetpack.data.source.MovieTvRepository;
import com.kuycoding.jetpack.data.source.local.entity.MovieEntity;
import com.kuycoding.jetpack.data.source.local.entity.TvEntity;
import com.kuycoding.jetpack.utils.FakeDataDummy;
import com.kuycoding.jetpack.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailTvShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailTvShowViewModel viewModel;
    private MovieTvRepository academyRepository = mock(MovieTvRepository.class);
    private TvEntity dummyCourse = FakeDataDummy.generateDummyTvShow().get(0);
    private String courseId = dummyCourse.getId();


    @Before
    public void setUp() {
        viewModel = new DetailTvShowViewModel(academyRepository);
        viewModel.setCourseId(courseId);
    }

    @Test
    public void getTvDetail() {
        Resource<TvEntity> resource = Resource.success(FakeDataDummy.generateDummyTv(dummyCourse, true));
        MutableLiveData<Resource<TvEntity>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(resource);

        when(academyRepository.getAllTvDetail(courseId)).thenReturn(mutableLiveData);
        Observer<Resource<TvEntity>> observer = mock(Observer.class);
        viewModel.getCourses.observeForever(observer);
        verify(observer).onChanged(resource);
    }
}