package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CourseFlagDao extends BaseDao<CourseFlag> {
    @Query("SELECT * FROM course_flag WHERE course_id=:course_id ")
    CourseFlag[] getCourseFlags(long course_id);

    @Query("SELECT * FROM (SELECT * FROM course_flag WHERE course_id=:course_id) " +
            "WHERE course_flag_Id " +
            "NOT IN (SELECT course_flag_Id FROM user_course_flag WHERE user_course_id=:user_course_id)")
    CourseFlag[] getNotVisitedCourseFlags(long course_id, long user_course_id);
}
