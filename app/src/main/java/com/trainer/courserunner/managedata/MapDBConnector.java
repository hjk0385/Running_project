package com.trainer.courserunner.managedata;

import android.database.sqlite.SQLiteDatabase;

public class MapDBConnector {
    private static SQLiteDatabase mapDB = null;
    private static String dbLocation = null;

    public MapDBConnector(String dbLocation) {
        if (MapDBConnector.mapDB != null) {
            MapDBConnector.mapDB = SQLiteDatabase.openDatabase(dbLocation, null, SQLiteDatabase.OPEN_READONLY);
            MapDBConnector.dbLocation = dbLocation;
        } else {
            if (MapDBConnector.dbLocation.compareTo(dbLocation) != 0) {
                //다른 DB를 호출하면 강제종료
                throw new AssertionError("Different DB");
            }
        }
    }
    public SQLiteDatabase getDBConnection()
    {
        return MapDBConnector.mapDB;
    }
}
