package com.kuycoding.jetpack.viewModel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.kuycoding.jetpack.data.source.MovieTvRepository;
import com.kuycoding.jetpack.data.source.local.entity.MovieEntity;
import com.kuycoding.jetpack.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FavMovieViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private FavMovieViewModel viewModel;
    private MovieTvRepository academyRepository = mock(MovieTvRepository.class);

    @Before
    public void setUp() {
        viewModel = new FavMovieViewModel(academyRepository);
    }

    @Test
    public void getFavMovie() {
        MutableLiveData<Resource<PagedList<MovieEntity>>> course = new MutableLiveData<>();
        when(academyRepository.getFavoriteMoviePagged()).thenReturn(course);

        Observer<Resource<PagedList<MovieEntity>>> observer = mock(Observer.class);
        viewModel.getBookmarksPaged().observeForever(observer);
        assertNotNull(course);
    }
}