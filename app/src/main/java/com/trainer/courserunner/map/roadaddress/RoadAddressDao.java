package com.trainer.courserunner.map.roadaddress;

import android.database.Cursor;

import com.trainer.courserunner.Application.RoadAddressDatabaseLoader;
import com.trainer.courserunner.course.maker.scopetype.ScopeMapInfo;

import java.util.ArrayList;
import java.util.List;

public class RoadAddressDao {
    public static List<RoadAddress> getScopeAddress(ScopeMapInfo scopeMapInfo) {
        String sql = "SELECT longitude, latitude FROM addresstable " +
                "WHERE longitude > ? AND latitude > ? AND longitude < ? AND latitude< ?";
        String[] whereArgs = new String[]{
                String.valueOf(scopeMapInfo.getStartX()),
                String.valueOf(scopeMapInfo.getStartY()),
                String.valueOf(scopeMapInfo.getEndX()),
                String.valueOf(scopeMapInfo.getEndY())
        };
        Cursor cursor = RoadAddressDatabaseLoader.getRoadAddressDatabase().rawQuery(sql, whereArgs);
        ArrayList<RoadAddress> Address = new ArrayList<>();
        while (cursor.moveToNext()) {
            Address.add(new RoadAddress(cursor.getDouble(cursor.getColumnIndex("longitude")),
                            cursor.getDouble(cursor.getColumnIndex("latitude"))
                    )
            );
        }
        cursor.close();
        return Address;
    }
}
