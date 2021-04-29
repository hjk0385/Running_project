package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface CourseFlagDao {
    @Insert
    void insertCourseFlag(CourseFlag courseFlag);
}
