package com.trainer.courserunner.rooms;

import android.content.Context;

import androidx.room.Room;

public class AppDatabaseInstance {
    private AppDatabase appDatabase=null;
    public AppDatabaseInstance(Context context){
        appDatabase=Room.databaseBuilder(context,AppDatabase.class,"database211").allowMainThreadQueries().build();
    }
    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
