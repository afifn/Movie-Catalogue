package com.kuycoding.jetpack.viewModel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.kuycoding.jetpack.data.source.MovieTvRepository;
import com.kuycoding.jetpack.data.source.local.entity.TvEntity;
import com.kuycoding.jetpack.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FavTvShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private FavTvShowViewModel viewModel;
    private MovieTvRepository academyRepository = mock(MovieTvRepository.class);

    @Before
    public void setUp() {
        viewModel = new FavTvShowViewModel(academyRepository);
    }

    @Test
    public void getFavTv() {
        MutableLiveData<Resource<PagedList<TvEntity>>> course = new MutableLiveData<>();
        when(academyRepository.getFavoriteTvShwoPagged()).thenReturn(course);

        Observer<Resource<PagedList<TvEntity>>> observer = mock(Observer.class);
        viewModel.getFavTv().observeForever(observer);
    }
}