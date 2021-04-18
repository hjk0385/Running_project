package com.trainer.courserunner.rooms;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public class AppDatabaseLoader {
    static private AppDatabase appDatabase;

    static public void initAppdatabase(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "testdatabase10").allowMainThreadQueries().build();
    }

    static public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
