package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface CourseModeDao {
    @Insert
    void insertCourseModeDao(CourseMode courseMode);
}
