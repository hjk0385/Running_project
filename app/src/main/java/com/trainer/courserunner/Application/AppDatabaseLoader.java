package com.trainer.courserunner.Application;

import android.app.Application;

import androidx.room.Room;

import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseMode;


public class AppDatabaseLoader extends Application {
    static private AppDatabase appDatabase = null;

    static public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public void onCreate() {
        super.onCreate();
        //앱이 처음으로 시작될때 호출되는 메소드
        /*
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "testdatabase114").build();
        */
        appDatabase = Room.inMemoryDatabaseBuilder(getApplicationContext(), AppDatabase.class).allowMainThreadQueries().build();

        //나중에 미리채우기로 변경
        CourseMode courseMode;

        courseMode = new CourseMode();
        courseMode.courseModeId = Long.valueOf(1);
        courseMode.courseModeName = "SketchBook";
        appDatabase.courseModeDao().insertDto(courseMode);

        courseMode = new CourseMode();
        courseMode.courseModeId = Long.valueOf(2);
        courseMode.courseModeName = "GuideRunner";
        appDatabase.courseModeDao().insertDto(courseMode);

        courseMode = new CourseMode();
        courseMode.courseModeId = Long.valueOf(3);
        courseMode.courseModeName = "";
        appDatabase.courseModeDao().insertDto(courseMode);
    }
}
