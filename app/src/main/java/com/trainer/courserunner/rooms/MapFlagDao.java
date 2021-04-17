package com.trainer.courserunner.rooms;

import androidx.room.Insert;

public interface MapFlagDao {
    @Insert
    public long insertMapFlag(MapFlag mapFlag);
}
