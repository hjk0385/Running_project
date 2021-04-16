package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface UserCourseInfoDao {
    @Insert
    public long insertUserCourseInfo(UserCourseInfo userCourseInfo);

}
