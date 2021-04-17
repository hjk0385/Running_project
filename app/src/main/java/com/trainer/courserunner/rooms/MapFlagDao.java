package com.trainer.courserunner.rooms;

import androidx.room.Insert;
import androidx.room.Query;

public interface MapFlagDao {
    @Insert
    long insertMapFlag(MapFlag mapFlag);
}
