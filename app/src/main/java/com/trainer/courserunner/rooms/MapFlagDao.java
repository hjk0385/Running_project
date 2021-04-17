package com.trainer.courserunner.rooms;

import androidx.room.Insert;

public interface MapFlagDao {
    @Insert
    long insertMapFlag(MapFlag mapFlag);
}
