package com.trainer.courserunner.Application;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Display;

import androidx.room.Room;

import com.trainer.courserunner.loader.ObbLoader;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseMode;


public class AppFunctionLoader extends Application {
    static private AppDatabase appDatabase = null;
    static private SQLiteDatabase roadAddressDatabase = null;

    static public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    static public SQLiteDatabase getRoadAddressDatabase() {
        return roadAddressDatabase;
    }

    public void onCreate() {
        super.onCreate();
        //앱DB불러오기
        appDatabase = Room.inMemoryDatabaseBuilder(getApplicationContext(), AppDatabase.class).allowMainThreadQueries().build();
        //지도DB불러오기
        String[] expansionFiles = ObbLoader.getAPKExpansionFiles(getApplicationContext(), 1, 0);
        String dbLocation = expansionFiles[0];
        roadAddressDatabase = SQLiteDatabase.openDatabase(dbLocation, null, SQLiteDatabase.OPEN_READONLY);

        //앱DB미리채우기(디버그용)
        CourseMode courseMode;
        courseMode = new CourseMode();
        courseMode.courseModeId = (long) ModeType.GUIDERUNNER.ordinal();
        courseMode.courseModeName = ModeType.GUIDERUNNER.name();
        appDatabase.courseModeDao().insertDto(courseMode);

        courseMode.courseModeId = (long) ModeType.PROJECTRUNNER.ordinal();
        courseMode.courseModeName = ModeType.PROJECTRUNNER.name();
        appDatabase.courseModeDao().insertDto(courseMode);

        courseMode.courseModeId = (long) ModeType.SKETCHBOOK.ordinal();
        courseMode.courseModeName = ModeType.SKETCHBOOK.name();
        appDatabase.courseModeDao().insertDto(courseMode);
        //

        //사운드 매니저 초기화
        SoundManagerGuide.initSoundManager(this);
    }
}
