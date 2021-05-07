package com.trainer.courserunner.Application;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.room.Room;

import com.trainer.courserunner.loader.ObbLoader;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.CourseMode;


public class AppDatabaseLoader extends Application {
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
        courseMode.courseModeId = Long.valueOf(1);
        courseMode.courseModeName = "SketchBook";
        appDatabase.courseModeDao().insertDto(courseMode);

        courseMode = new CourseMode();
        courseMode.courseModeId = Long.valueOf(2);
        courseMode.courseModeName = "GuideRunner";
        appDatabase.courseModeDao().insertDto(courseMode);

        courseMode = new CourseMode();
        courseMode.courseModeId = Long.valueOf(3);
        courseMode.courseModeName = "ProjectRunner";
        appDatabase.courseModeDao().insertDto(courseMode);
        //

        //debug
        Cursor cursor = roadAddressDatabase.rawQuery("SELECT * FROM addresstable limit 10", null);
        while (cursor.moveToNext()) {
            Log.v("DBCheck", "success");
            Log.v("DBCheck", String.valueOf(cursor.getColumnCount()));
            Log.v("DBCheck", String.valueOf(cursor.getDouble(cursor.getColumnIndex("latitude"))));
            Log.v("DBCheck", String.valueOf(cursor.getDouble(cursor.getColumnIndex("longitude"))));
            Log.v("DBCheck", String.valueOf(cursor.getColumnCount()));
        }
    }
}
