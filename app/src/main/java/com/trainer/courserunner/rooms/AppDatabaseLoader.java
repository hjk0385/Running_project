package com.trainer.courserunner.rooms;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.trainer.courserunner.MyApplication;

public class AppDatabaseLoader extends Application {
    static private AppDatabase appDatabase = null;

    static public AppDatabase getAppDatabase() {
        if (appDatabase == null) {
            Context applicationContext = MyApplication.ApplicationContext();
            appDatabase = Room.databaseBuilder(applicationContext, AppDatabase.class,
                    "testdatabase114").build();
        }
        return appDatabase;
    }
}
