package com.trainer.courserunner.runactivity.record;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.trainer.courserunner.Application.ApplicationBoot;
import com.trainer.courserunner.Application.rooms.AppDatabaseConnector;
import com.trainer.courserunner.Application.rooms.UserCourse;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecordListActivity extends ListActivity {
    UserCourse[] userCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new RecordListStarter().execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        UserCourse userCourse = userCourses[position];

        Intent intent = new Intent(getBaseContext(), TimelapsActivity.class);
        intent.putExtra("userCourseId",userCourse.userCourseId);
        startActivity(intent);
    }

    class RecordListStarter extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            userCourses= AppDatabaseConnector.getAppDatabaseConnection().userCourseDao().getAllUserCourse();
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            List<String> userCourseNameList;
            userCourseNameList = Arrays.stream(userCourses)
                                        .map(userCourse -> String.valueOf(userCourse.userCourseId))
                                        .collect(Collectors.toList());
            ArrayAdapter<String> userCourseAdapter = new ArrayAdapter<>(RecordListActivity.this, android.R.layout.simple_list_item_1,userCourseNameList);
            setListAdapter(userCourseAdapter);
        }
    }
}
