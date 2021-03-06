package com.cmms.codetech.startclasseasy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.cmms.codetech.startclasseasy.adapter.CourseDateAdapter;

/**
 * Created by daryl on 12/11/2015.
 */
public class StudentCourseCalendarActivity extends Activity {
    ListView courseDateLv;

    CourseDateAdapter courseDateAdapter;
    UserDatabase dbHelper = new UserDatabase(StudentCourseCalendarActivity.this);

    Bundle extras;
    Long courseID;

    String TAG = "StudentCourseCalendarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_calendar);

        initView();

        extras = getIntent().getExtras();
        courseID = extras.getLong("courseID");


        Log.e(TAG, String.valueOf(courseID));
        inflateCourseCalendarList(courseID);

    }

    public void inflateCourseCalendarList(Long courseID) {
        courseDateAdapter = new CourseDateAdapter(this, dbHelper.listStudentCourseCalendar(courseID), courseID, false);
        courseDateLv.setAdapter(courseDateAdapter);
    }

    private void initView() {

        courseDateLv = (ListView) findViewById(R.id.acc_courseDateLv);
    }
}
