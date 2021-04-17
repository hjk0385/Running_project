package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserLocationRecordDao {
    @Insert
    void insertUserLocationRecord(UserLocationRecord userLocationRecord);

    @Query("SELECT IFNULL(MAX(userlocation_order),0)+1 FROM userlocationrecord WHERE usercourse_id=:usercourse_id")
    long getNextUserLocationOrder(long usercourse_id);
}
