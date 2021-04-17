package com.trainer.courserunner.rooms;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "userlocationrecord")
public class UserLocationRecord {
    @PrimaryKey
    public long userlocation_order;
    public long usercourse_id;
    public double longitude;
    public double latitude;
}
