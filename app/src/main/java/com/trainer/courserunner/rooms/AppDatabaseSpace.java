package com.trainer.courserunner.rooms;

import android.content.Context;

import androidx.room.Room;

public class AppDatabaseSpace {
    private AppDatabase appDatabase=null;
    public AppDatabaseSpace(Context context){
        appDatabase=Room.databaseBuilder(context,AppDatabase.class,"database21").allowMainThreadQueries().build();
    }
    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
