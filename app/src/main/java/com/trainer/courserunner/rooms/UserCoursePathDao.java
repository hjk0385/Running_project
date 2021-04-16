package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface UserCoursePathDao {
    @Insert
    public long insertUserCoursePath(UserCoursePath userCoursePath);
}
