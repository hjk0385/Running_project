package com.trainer.courserunner.managedata;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MapDAO extends MapDBConnector{
    public MapDAO(String dbLocation) {
        super(dbLocation);
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
        Cursor cursor = this.getDBConnection().rawQuery(sql, whereArgs);
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
