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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cmms.codetech.startclasseasy.adapter.CourseDateAdapter;
import com.cmms.codetech.startclasseasy.model.Course;
import com.cmms.codetech.startclasseasy.model.CourseDate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CourseActivity extends AppCompatActivity {

    EditText courseNameEt;
    EditText conductorNameEt;

    Button saveCourseBtn;
    Button courseDateBtn;
    Button addAttendeeBtn;
    Button editBtn;
    RadioGroup courseStatusRg;
    RadioButton courseStatusRb;
    ListView courseDateLv;
    ImageView minusIv;

    LinearLayout createCourseLl;
    LinearLayout dateAttendeeLl;
    LinearLayout editCourseLl;

    CourseDateAdapter courseDateAdapter;

    private int year;
    private int month;
    private int day;

    boolean isEditMode = false;
    long courseID;

    UserDatabase dbHelper = new UserDatabase(CourseActivity.this);
    Bundle extras;

    static final int DATE_PICKER_ID = 1111;
    private int mRequestCode101 = 101;


    List<CourseDate> courseDateList = new ArrayList<CourseDate>();

    final static String TAG = "CourseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        initView();

        Log.e(TAG, "1");

        extras = getIntent().getExtras();
        isEditMode = extras.getBoolean("isEditMode");

        if (isEditMode){
            courseNameEt.setEnabled(false);
            conductorNameEt.setEnabled(false);
            courseStatusRg.setEnabled(false);
            courseDateLv.setEnabled(false);
            createCourseLl.setVisibility(View.GONE);
            dateAttendeeLl.setVisibility(View.GONE);
            editCourseLl.setVisibility(View.VISIBLE);
            courseDateLv.setClickable(false);

            List<Course> course = dbHelper.getCourse(extras.getLong("rowID"));
            String courseName = course.get(0).getCourseName();
            String trainerName = course.get(0).getCourseTrainer();
            //String courseStatus = course.get(0).getCourseStatus();

            courseNameEt.setText(courseName);
            conductorNameEt.setText(trainerName);

            inflateCourseDateListView(extras.getLong("rowID"), isEditMode);

        }
        //http://androidexample.com/In_this_example_creating_a_date_picker_to_pick_day__month_year_of_date/index.php?view=article_discription&aid=89&aaid=113
        // Get current date by calender

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        courseDateLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(CourseActivity.this, AttendanceActivity.class);
                i.putExtra("isEditMode", false);

                if (isEditMode){
                    i.putExtra("rowID", courseDateList.get(position).getRowID());
                    i.putExtra("courseDate", courseDateList.get(position).getCourseDate());
                    i.putExtra("courseID", extras.getLong("rowID"));
                }else{
                    i.putExtra("rowID", courseDateList.get(position).getRowID());
                    i.putExtra("courseDate", courseDateList.get(position).getCourseDate());
                    i.putExtra("courseID", courseID);
                }

                startActivity(i);

            }
        });

        editBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                createCourseLl.setVisibility(View.GONE);
                dateAttendeeLl.setVisibility(View.VISIBLE);
                editCourseLl.setVisibility(View.GONE);

                courseNameEt.setEnabled(false);
                conductorNameEt.setEnabled(false);
                courseStatusRg.setEnabled(true);
                courseDateLv.setEnabled(true);

                inflateCourseDateListView(extras.getLong("rowID"), false);
            }
        });

        addAttendeeBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CourseActivity.this, ChooseAttendeeActivity.class);
                i.putExtra("isEditMode", false);

                if (isEditMode){
                    i.putExtra("courseID", extras.getLong("rowID"));
                }else{
                    i.putExtra("courseID", courseID);
                }

                startActivity(i);
            }
        });

        courseDateBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // On button click show datepicker dialog
                showDialog(DATE_PICKER_ID);

            }

        });

        saveCourseBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (courseNameEt.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Course name cannot be empty", Toast.LENGTH_LONG).show();
                    courseNameEt.requestFocus();
                }else{
                    if (conductorNameEt.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Trainer name cannot be empty", Toast.LENGTH_LONG).show();
                        conductorNameEt.requestFocus();

                    } else {
                        createCourseLl.setVisibility(View.GONE);
                        dateAttendeeLl.setVisibility(View.VISIBLE);

                        String courseStatus;

                        courseNameEt.setEnabled(false);
                        conductorNameEt.setEnabled(false);

                        int courseStatusID = courseStatusRg.getCheckedRadioButtonId();
                        courseStatusRb = (RadioButton) findViewById(courseStatusID);

                        courseID = dbHelper.addCourse(courseNameEt.getText().toString(), conductorNameEt.getText().toString(), String.valueOf(courseStatusRb.getText()));
                        //Long courseID = dbHelper.addCourse(courseNameEt.getText().toString(), conductorNameEt.getText().toString());

                        Toast.makeText(getApplicationContext(), "CourseID " + String.valueOf(courseID), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    private void initView() {

        courseNameEt = (EditText) findViewById(R.id.ac_courseNameEt);
        conductorNameEt = (EditText) findViewById(R.id.ac_conductorNameEt);
        courseStatusRg = (RadioGroup) findViewById(R.id.courseStatusRg);

        saveCourseBtn = (Button) findViewById(R.id.ac_saveCourseBtn);
        courseDateBtn = (Button) findViewById(R.id.ac_courseDateBtn);
        addAttendeeBtn = (Button) findViewById(R.id.ac_addAttendeeBtn);
        editBtn = (Button) findViewById(R.id.ac_editBtn);

        courseDateLv = (ListView) findViewById(R.id.ac_courseDateLv);
        createCourseLl = (LinearLayout) findViewById(R.id.ac_createCourseLl);
        dateAttendeeLl = (LinearLayout) findViewById(R.id.ac_dateAttendeeLl);
        editCourseLl = (LinearLayout) findViewById(R.id.ac_editCourseLl);

        createCourseLl.setVisibility(View.VISIBLE);
        dateAttendeeLl.setVisibility(View.GONE);
        editCourseLl.setVisibility(View.GONE);

        minusIv = (ImageView) courseDateLv.findViewById(R.id.acrd_removeDateTimeIv);

    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong("rowID", extras.getLong("rowID"));
        savedInstanceState.putBoolean("isEditMode", isEditMode);

        Log.e(TAG, "2");
    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        courseID = savedInstanceState.getLong("rowID");
        isEditMode = savedInstanceState.getBoolean("isEditMode");

        Log.e(TAG, "3");
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

            inflateCourseDateListView(courseID, isEditMode);

        }
    };

    public void inflateCourseDateListView(Long courseID, boolean mode){

        courseDateList = dbHelper.listCourseDate(courseID);

        courseDateAdapter = new CourseDateAdapter(this, courseDateList, courseID, mode);
        courseDateLv.setAdapter(courseDateAdapter);
    }

}
