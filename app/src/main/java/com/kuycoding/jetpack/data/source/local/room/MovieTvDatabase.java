package com.kuycoding.jetpack.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kuycoding.jetpack.data.source.local.entity.MovieEntity;
import com.kuycoding.jetpack.data.source.local.entity.TvEntity;

@Database(entities = {MovieEntity.class, TvEntity.class},
        version = 1,
        exportSchema = false)
public abstract class MovieTvDatabase extends RoomDatabase {
    private static MovieTvDatabase INSTANCE;

    public abstract MovieTvDao movieTvDao();

    private static final Object sLock = new Object();

    public static MovieTvDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MovieTvDatabase.class, "movietv.db")
                        .build();
            }
        }
        return INSTANCE;
    }
}
