package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserLocationPathDao {
    @Insert
    public long insertUserLocationPath(UserLocationPath userLocationPath);

    @Query("SELECT IFNULL(MAX(userlocation_id),0) FROM userlocationpath WHERE usercourse_id=:usercourse_id")
    public long queryMaxUserLocationId(long usercourse_id);
}
