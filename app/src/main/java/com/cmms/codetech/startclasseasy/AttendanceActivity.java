package com.cmms.codetech.startclasseasy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cmms.codetech.startclasseasy.model.Attendee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daryl on 31/10/2015.
 */
public class AttendanceActivity extends Activity {

    TextView courseDate;
    ListView attendanceLv;

    UserDatabase dbHelper = new UserDatabase(AttendanceActivity.this);
    ArrayAdapter<String> adapter;
    List<Attendee> attendees = new ArrayList<Attendee>();
    Bundle extras;

    String Tag = "AttendanceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);

        initView();

        extras = getIntent().getExtras();

        courseDate.setText(extras.getString("courseDate"));

        inflateAttendeeList();
    }

    private void initView() {
        courseDate = (TextView) findViewById(R.id.aal_coursedateTv);
        attendanceLv = (ListView) findViewById((R.id.aal_attendanceLv));
    }

    public void inflateAttendeeList() {

        attendees = dbHelper.listAllCourseAttendees(extras.getLong("courseID"));

        String[] attendeesList = new String[attendees.size()];

        for (int i = 0; i < attendees.size(); i++) {
            attendeesList[i] = attendees.get(i).getAttendeeName() + " (" + attendees.get(i).getAttendeeContact() + ")";
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, attendeesList);
        //attendanceLv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        attendanceLv.setAdapter(adapter);

    }
}
