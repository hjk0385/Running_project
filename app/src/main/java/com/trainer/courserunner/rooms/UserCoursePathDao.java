package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserCoursePathDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUserCoursePath(UserCoursePath userCoursePath);

    @Query("SELECT * FROM usercoursepath")
    public UserCoursePath[] loadAllCourse();
}
