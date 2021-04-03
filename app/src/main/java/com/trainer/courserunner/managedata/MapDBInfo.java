package com.trainer.courserunner.managedata;

import java.io.File;

public class MapDBInfo {
    static protected final String mapDBLocation = "/data/data/com.trainer.courserunner/AddressDB.db";
    static protected final String mapDBAssetLocation = "AddressDB.db";

    static public String getMapDBLocation() {
        if (!(new File(mapDBLocation).exists()))
            //DB가 복사되지 않은 경우
            return null;
        else {
            return mapDBLocation;
        }
    }
}
