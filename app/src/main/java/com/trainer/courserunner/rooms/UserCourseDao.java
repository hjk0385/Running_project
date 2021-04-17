package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface UserCourseDao {
    @Query("")
    UserLocationRecord[] getUserLocationRecords(long usercourse_id);
}
