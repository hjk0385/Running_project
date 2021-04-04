package com.trainer.courserunner.managedata;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class MapDAO {
    private static SQLiteDatabase mapDB = null;

    public static void initMapDB(Context context) {
        String[] expansionFiles = ObbLoader.getAPKExpansionFiles(context, 1, 0);
        String dbLocation = expansionFiles[0];
        if (mapDB == null) {
            MapDAO.mapDB = SQLiteDatabase.openDatabase(dbLocation, null, SQLiteDatabase.OPEN_READONLY);
        } else {
            Log.v("DBLOG", "DB Load Success");
        }
    }

    public ArrayList<MapDTO> getScopeAddress(double startX, double startY,
                                             double endX, double endY) {
        String sql = "SELECT longitude, latitude FROM addresstable " +
                "WHERE longitude > ? AND latitude > ? AND longitude < ? AND latitude< ?";
        String[] whereArgs = new String[]{
                String.valueOf(startX),
                String.valueOf(startY),
                String.valueOf(endX),
                String.valueOf(endY)
        };
        Cursor cursor = mapDB.rawQuery(sql, whereArgs);
        ArrayList<MapDTO> Address = new ArrayList<>();
        while (cursor.moveToNext()) {
            Address.add(new MapDTO(cursor.getDouble(cursor.getColumnIndex("longitude")),
                            cursor.getDouble(cursor.getColumnIndex("latitude"))
                    )
            );
        }
        cursor.close();
        return Address;
    }
}

/*
    obb 확장
    https://gogorchg.tistory.com/entry/Android-Expansion-file-%EC%9D%B4%EC%9A%A9%ED%95%98%EA%B8%B0-1

*/