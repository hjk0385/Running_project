package com.trainer.courserunner.rooms;

import androidx.room.Query;

public interface UserCourseRecordDao extends BaseDao<UserCourseRecord> {
    @Query("SELECT IFNULL(MAX(user_course_record_id),0)+1 FROM user_course_record WHERE user_course_id=:user_course_id")
    long getNextUserLocationOrder(long user_course_id);

}
