package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CourseInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCourseInfo(CourseInfo courseInfo);

    @Query("SELECT MAX(course_id) FROM courseinfo")
    public int loadMaxCourseId();

    @Query("SELECT * FROM courseinfo WHERE course_id=:course_id")
    public CourseInfo[] loadCourseInfo(int course_id);
}
