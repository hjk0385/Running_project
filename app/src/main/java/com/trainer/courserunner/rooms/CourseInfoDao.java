package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface CourseInfoDao {
    @Insert
    long insertCourseInfo(CourseInfo courseInfo);
}
