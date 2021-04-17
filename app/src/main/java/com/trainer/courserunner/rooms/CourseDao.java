package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CourseDao {

    @Query("SELECT mapflag_id,latitude,longitude FROM mapflag " +
            "JOIN courseflag USING(mapflag_id) WHERE course_id=:course_id " +
            "ORDER BY courseflag_order")
    MapFlag[] getCourseMapflags(long course_id);
}
