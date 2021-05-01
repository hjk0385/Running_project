package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CourseModeDao extends BaseDao<CourseMode> {
    @Query("SELECT * FROM course_mode WHERE course_mode_name=:course_mode_name")
    CourseMode getCourseMode(String course_mode_name);
}
