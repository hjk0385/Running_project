package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface UserMapFlagDao {
    //오류 무시
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertUserMapFlag(UserMapFlag userMapFlag);
}
