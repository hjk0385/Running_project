package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserCourseInfoDao {
    @Insert
    public long insertUserCourseInfo(UserCourseInfo userCourseInfo);

    @Query("SELECT IFNULL(MAX(usercourse_id),0) FROM usercourseinfo")
    public long queryMaxUserCourseId();

}
