package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserCourseDao {
    @Insert
    long insertUserCourseInfo(UserCourseInfo userCourseInfo);

    @Insert
    void insertUserLocationRecord(UserLocationRecord userLocationRecord);

    //이미 있더라도 데이터 저장
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertUserMapFlag(UserMapFlag userMapFlag);

    @Query("SELECT IFNULL(MAX(userlocation_order),0)+1 FROM userlocationrecord WHERE usercourse_id=:usercourse_id")
    long getNextUserLocationOrder(long usercourse_id);

    @Query("SELECT * FROM userlocationrecord WHERE usercourse_id=:usercourse_id")
    UserLocationRecord[] getUserLocationRecords(long usercourse_id);

    @Query("SELECT * FROM usermapflag WHERE usercourse_id=:usercourseId")
    UserMapFlag[] getUserMapFlags(long usercourseId);
}
