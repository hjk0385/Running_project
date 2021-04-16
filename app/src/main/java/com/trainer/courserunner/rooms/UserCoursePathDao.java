package com.trainer.courserunner.rooms;

import androidx.room.Insert;

public interface UserCoursePathDao {
    @Insert
    public long insertUserCoursePath(UserCoursePath userCoursePath);
}
