package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CoursePathDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCourse(CoursePath coursePath);

    @Query("SELECT * FROM coursepath")
    public CoursePath[] loadAllCourse();
}
