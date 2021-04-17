package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface UserCourseDao {
    @Query("SELECT * FROM userlocationrecord WHERE usercourse_id=:usercourse_id")
    UserLocationRecord[] getUserLocationRecords(long usercourse_id);
}
