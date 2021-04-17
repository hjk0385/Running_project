package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MapFlagDao {
    @Insert
    long insertMapFlag(MapFlag mapFlag);
}
