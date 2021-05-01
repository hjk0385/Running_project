package com.trainer.courserunner.rooms;

import androidx.room.Query;

public interface UserCourseRecordDao extends BaseDao<UserCourseRecord> {
    @Query("SELECT IFNULL(MAX(user_course_record_id),0)+1 FROM user_course_record WHERE user_course_id=:user_course_id")
    Long getNextUserLocationOrder(Long user_course_id);

    @Query("SELECT * FROM user_course_record WHERE user_course_id=:user_course_id")
    UserCourseRecord[] getUserLocationRecords(Long user_course_id);
}
