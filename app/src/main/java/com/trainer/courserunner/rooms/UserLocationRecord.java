package com.trainer.courserunner.rooms;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "userlocationrecord",
        primaryKeys = {
                "userlocation_order",
                "usercourse_id"
        }
)
public class UserLocationRecord {
    public long userlocation_order;
    public long usercourse_id;
    public double longitude;
    public double latitude;
}
