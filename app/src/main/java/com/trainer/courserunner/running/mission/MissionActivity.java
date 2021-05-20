package com.trainer.courserunner.running.mission;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.trainer.courserunner.R;

public class MissionActivity extends AppCompatActivity {
    static final String[] LIST_MENU = {"3일 연속 달리기", "프로젝트 하나 성공하기", "총 달린 거리 5Km 이상"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.activity_list_item, LIST_MENU);

        ListView listView = (ListView) findViewById(R.id.listview1);
        listView.setAdapter(adapter);
    }
}