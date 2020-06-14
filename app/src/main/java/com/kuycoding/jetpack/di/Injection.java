package com.kuycoding.jetpack.di;

import android.app.Application;

import com.kuycoding.jetpack.data.source.MovieTvRepository;
import com.kuycoding.jetpack.data.source.local.LocalRepository;
import com.kuycoding.jetpack.data.source.local.room.MovieTvDatabase;
import com.kuycoding.jetpack.data.source.remote.RemoteRepository;
import com.kuycoding.jetpack.utils.AppExecutors;
import com.kuycoding.jetpack.utils.JsonHelper;

public class Injection {
    public static MovieTvRepository provideRepository(Application application) {

        MovieTvDatabase database = MovieTvDatabase.getInstance(application);

        LocalRepository localRepository = LocalRepository.getInstance(database.movieTvDao());

        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));

        AppExecutors appExecutors = new AppExecutors();

        return MovieTvRepository.getInstance(localRepository, remoteRepository, appExecutors);
    }
}
