package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CoursePathDao {
    @Insert
    public long insertCoursePath(CoursePath coursePaths);

    @Query("SELECT * FROM coursepath")
    public CoursePath[] loadAllCoursePath();


    @Query("SELECT * FROM coursepath WHERE course_id=:course_id")
    public CoursePath[] loadCoursePath(long course_id);


}
