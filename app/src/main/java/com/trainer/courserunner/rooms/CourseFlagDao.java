package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CourseFlagDao extends BaseDao<CourseFlag> {
    @Query("SELECT * FROM course_flag WHERE course_id=:course_id ")
    CourseFlag[] getCourseFlags(Long course_id);

    @Query("SELECT * FROM course_flag WHERE course_id=:course_id AND marker_flag==1")
    CourseFlag[] getCourseMarkerFlags(Long course_id);
}
