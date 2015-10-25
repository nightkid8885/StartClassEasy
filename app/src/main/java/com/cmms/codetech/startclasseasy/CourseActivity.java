package com.cmms.codetech.startclasseasy;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.cmms.codetech.startclasseasy.adapter.CourseDateAdapter;

import java.sql.Date;
import java.util.Calendar;

public class CourseActivity extends AppCompatActivity {

    EditText courseNameEt;
    EditText conductorNameEt;

    Button saveCourseBtn;
    Button courseDateBtn;
    Button attendeeBtn;
    ListView courseDateLv;

    LinearLayout createCourseLl;
    LinearLayout dateAttendeeLl;

    CourseDateAdapter courseDateAdapter;

    private TextView Output;

    private int year;
    private int month;
    private int day;

    static final int DATE_PICKER_ID = 1111;

    long courseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        initView();

        //http://androidexample.com/In_this_example_creating_a_date_picker_to_pick_day__month_year_of_date/index.php?view=article_discription&aid=89&aaid=113
        // Get current date by calender

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // Show current date

//        Output.setText(new StringBuilder()
//                // Month is 0 based, just add 1
//                .append(month + 1).append("-").append(day).append("-")
//                .append(year).append(" "));

        // Button listener to show date picker dialog

        courseDateBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // On button click show datepicker dialog
                showDialog(DATE_PICKER_ID);

            }

        });

        attendeeBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT);
                Intent i = new Intent(getApplicationContext(), AttendeeListActivity.class);
                startActivity(i);

            }

        });

        saveCourseBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDatabase dbHelper = new UserDatabase(CourseActivity.this);

                if (courseNameEt.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Course name cannot be null", Toast.LENGTH_LONG).show();
                    courseNameEt.requestFocus();
                }else{
                    if (conductorNameEt.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Trainer name cannot be null", Toast.LENGTH_LONG).show();
                        conductorNameEt.requestFocus();

                    } else {
                        createCourseLl.setVisibility(View.GONE);
                        dateAttendeeLl.setVisibility(View.VISIBLE);

                        courseNameEt.setEnabled(false);
                        conductorNameEt.setEnabled(false);

                        courseID = dbHelper.addCourse(courseNameEt.getText().toString(), conductorNameEt.getText().toString());
                        //Long courseID = dbHelper.addCourse(courseNameEt.getText().toString(), conductorNameEt.getText().toString());

                        Toast.makeText(getApplicationContext(), "Save course name successfully, then only can add date and attendees, disable edit field", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "CourseID " + String.valueOf(courseID), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private void initView() {
        courseNameEt = (EditText) findViewById(R.id.ac_courseNameEt);
        conductorNameEt = (EditText) findViewById(R.id.ac_conductorNameEt);

        saveCourseBtn = (Button) findViewById(R.id.ac_saveCourseBtn);
        courseDateBtn = (Button) findViewById(R.id.ac_courseDateBtn);
        attendeeBtn = (Button) findViewById(R.id.ac_attendeeBtn);

        courseDateLv = (ListView) findViewById(R.id.ac_courseDateLv);
        createCourseLl = (LinearLayout) findViewById(R.id.ac_createCourseLl);
        dateAttendeeLl = (LinearLayout) findViewById(R.id.ac_dateAttendeeLl);

        createCourseLl.setVisibility(View.VISIBLE);
        dateAttendeeLl.setVisibility(View.GONE);

        Output = (TextView) findViewById(R.id.ac_conductorNameTv);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:

                // open datepicker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
                return new DatePickerDialog(this, pickerListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            UserDatabase dbHelper = new UserDatabase(CourseActivity.this);

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // Show selected date
            Toast.makeText(getApplicationContext(), new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "), Toast.LENGTH_SHORT).show();

            Toast.makeText(getApplicationContext(), "Course ID " + String.valueOf(courseID), Toast.LENGTH_SHORT).show();

            dbHelper.addCourseDate(courseID, new StringBuilder().append(year)
                    .append("-").append(month + 1).append("-").append(day)
                    .append(" 00:00:00").toString());
            // Output.setText(new StringBuilder().append(month + 1)
            //        .append("-").append(day).append("-").append(year)
            //         .append(" "));

            Toast.makeText(getApplicationContext(), "Count " + String.valueOf(dbHelper.listAllCourse(courseID).size()), Toast.LENGTH_LONG).show();

            courseDateAdapter = new CourseDateAdapter(getApplicationContext(), dbHelper.listAllCourse(courseID));
            courseDateLv.setAdapter(courseDateAdapter);


        }
    };

}
