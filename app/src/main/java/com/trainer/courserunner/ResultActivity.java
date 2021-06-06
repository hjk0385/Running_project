package com.trainer.courserunner;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.trainer.courserunner.Application.rooms.UserCourseAnalyzer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {
    private CalendarView calendarView;
    private TextView textViewMonthCalorie;
    private TextView textViewMonthDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewMonthCalorie=findViewById(R.id.textView_month_calorie);
        textViewMonthDistance=findViewById(R.id.textView_month_distance);
        calendarView=findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd", Locale.KOREAN);
                String stringPrevDate=String.valueOf(year)+"/"+String.valueOf(month)+"/"+String.valueOf(day);
                Log.v("TIMER",stringPrevDate);
                try {
                    Date prevDate=sdf.parse(stringPrevDate);
                    Date nextDate = new Date(prevDate.getTime() + 86400000);
                    textViewMonthCalorie.setText(UserCourseAnalyzer.getStartEndTimeDistance(prevDate,nextDate).toString());
                    textViewMonthDistance.setText(UserCourseAnalyzer.getStartEndTimeExercise(prevDate,nextDate).toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}