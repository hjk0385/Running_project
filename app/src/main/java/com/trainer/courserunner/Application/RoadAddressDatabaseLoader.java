package com.trainer.courserunner.Application;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.Room;

import com.trainer.courserunner.loader.ObbLoader;
import com.trainer.courserunner.map.roadaddress.RoadAddressDao;
import com.trainer.courserunner.rooms.AppDatabase;

import java.io.IOException;
import java.io.InputStream;

public class RoadAddressDatabaseLoader extends Application {
    static private SQLiteDatabase roadAddressDatabase = null;
    static public SQLiteDatabase getRoadAddressDatabase() {
        return roadAddressDatabase;
    }

    public void onCreate() {
        super.onCreate();
        String[] expansionFiles = ObbLoader.getAPKExpansionFiles(getApplicationContext(), 1, 0);
        String dbLocation = expansionFiles[0];

        //앱이 처음으로 시작될때 호출되는 메소드
        roadAddressDatabase = SQLiteDatabase.openDatabase(dbLocation, null, SQLiteDatabase.OPEN_READONLY);
    }
}
