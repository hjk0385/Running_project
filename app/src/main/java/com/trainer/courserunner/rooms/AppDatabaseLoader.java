package com.trainer.courserunner.rooms;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;


public class AppDatabaseLoader extends Application {
    static private AppDatabase appDatabase = null;
    static public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public void onCreate() {
        super.onCreate();
        //앱이 처음으로 시작될때 호출되는 메소드
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "testdatabase114").build();
    }
}
