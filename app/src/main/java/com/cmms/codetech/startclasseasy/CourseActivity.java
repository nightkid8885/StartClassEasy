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

    private TextView Output;

    private int year;
    private int month;
    private int day;

    static final int DATE_PICKER_ID = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        initView();

        //http://androidexample.com/In_this_example_creating_a_date_picker_to_pick_day__month_year_of_date/index.php?view=article_discription&aid=89&aaid=113
        // Get current date by calender

        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);

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
                createCourseLl.setVisibility(View.GONE);
                dateAttendeeLl.setVisibility(View.VISIBLE);

                Toast.makeText(getApplicationContext(), "Save course name successfully, then only can add date and attendees, disable edit field", Toast.LENGTH_LONG).show();
                courseNameEt.setEnabled(false);
                conductorNameEt.setEnabled(false);

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
                return new DatePickerDialog(this, pickerListener, year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;

            // Show selected date
            Toast.makeText(getApplicationContext(), new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "), Toast.LENGTH_SHORT).show();
           // Output.setText(new StringBuilder().append(month + 1)
            //        .append("-").append(day).append("-").append(year)
           //         .append(" "));

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
