package com.cmms.codetech.startclasseasy;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.cmms.codetech.startclasseasy.adapter.CourseChooseAttendeeAdapter;
import com.cmms.codetech.startclasseasy.model.Attendee;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by daryl on 29/10/2015.
 */
public class ChooseAttendeeActivity extends AppCompatActivity {

    ListView chooseAttendeeLv;
    EditText searchAttendee;
    Button addBtn;

    CourseChooseAttendeeAdapter courseChooseAttendeeAdapter;
    ArrayAdapter<String> adapter;
    UserDatabase dbHelper = new UserDatabase(ChooseAttendeeActivity.this);

    final ArrayList<String> selectedItems = new ArrayList<String>();
    final ArrayList<Long> selectedItemsRowID = new ArrayList<Long>();

    List<Attendee> attendees = new ArrayList<Attendee>();
    private int mRequestCode101 = 101;
    Bundle extras;
    Long courseID;


    final static String TAG = "ChooseAttendeeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_attendee_list);

        initView();
        extras = getIntent().getExtras();

        courseID = extras.getLong("courseID");

        inflateStudent(courseID);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        chooseAttendeeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SparseBooleanArray checked = chooseAttendeeLv.getCheckedItemPositions();

                //selectedItems.add(adapter.getItem(position));

                //Log.e("Boolean", String.valueOf(selectedItems.contains(adapter.getItem(position))));
                Log.e("position", String.valueOf(position));
                Log.e(TAG, "check click2");

                if (!selectedItems.contains(adapter.getItem(position))) {
                    selectedItems.add(adapter.getItem(position));
                    //selectedItemsRowID.add(attendees.get(position).getRowID());

                    if (dbHelper.addCourseAttendee(attendees.get(position).getRowID(), extras.getLong("courseID"))){
                        dbHelper.addCourseAttendeeFeedback(attendees.get(position).getRowID(), extras.getLong("courseID"));
                    }

                } else {
                    for (int i = 0; i < selectedItems.size(); i++) {
                        if (selectedItems.get(i) == adapter.getItem(position)) {
                            selectedItems.remove(i);
                            //selectedItemsRowID.remove(i);

                            //if stu_ls2 having record it should not delete
                            Log.e(TAG, String.valueOf(dbHelper.checkIfAttended(attendees.get(position).getRowID(), extras.getLong("courseID" )).size()));

                            if (dbHelper.checkIfAttended(attendees.get(position).getRowID(),extras.getLong("courseID")).size() == 0){
                                if (dbHelper.deleteCourseAttendee(attendees.get(position).getRowID(),extras.getLong("courseID"))){
                                    dbHelper.deleteCourseAttendeeFeedback(attendees.get(position).getRowID(), extras.getLong("courseID"));
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Already have attendance, cannot remove student from course" , Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });

        //searchAttendee.addTextChangedListener(textWatcher);

        //addBtn.setOnClickListener(addStudent);

    }

//    TextWatcher textWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            adapter.getFilter().filter(s.toString());
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    };

    View.OnClickListener addStudent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ChooseAttendeeActivity.this, StudentActivity.class);

            i.putExtra("isEditMode", false);

            startActivityForResult(i, mRequestCode101);

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == mRequestCode101) {
            Log.e("DEBUG_TAG", String.valueOf(resultCode));
            if (resultCode == Activity.RESULT_OK) {
                //Toast.makeText(StudentListActivity.this, String.valueOf(data.getExtras().getBoolean("inserted")), Toast.LENGTH_LONG).show();
                //attendeeAdapter.notifyDataSetChanged();
                inflateStudent(courseID);
            }
        }
    }

    public void inflateStudent(Long courseID) {
        attendees = dbHelper.listAllStudentsByCourse(courseID);

        String[] attendeesList = new String[attendees.size()];
        Boolean[] attendeesListChecked = new Boolean[attendees.size()];

        for (int i = 0; i < attendees.size(); i++) {
            attendeesList[i] = attendees.get(i).getAttendeeName() + " (" + attendees.get(i).getAttendeeContact() + ") ";
            if (attendees.get(i).getChecked()==1){
                attendeesListChecked[i] = true;
            }else{
                attendeesListChecked[i] = false;
            }
        }

        courseChooseAttendeeAdapter = new CourseChooseAttendeeAdapter(this, R.layout.activity_attendance_list, attendees, extras.getLong("courseID" ));

        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, attendeesList);
        chooseAttendeeLv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        chooseAttendeeLv.setAdapter(courseChooseAttendeeAdapter);

        //Load true false to checkbox
        for (int i = 0; i < attendees.size(); i++){
            chooseAttendeeLv.setItemChecked(i, attendeesListChecked[i]);

            if (attendeesListChecked[i]){
                selectedItems.add(adapter.getItem(i));
            }
        }
    }

    private void initView() {

        chooseAttendeeLv = (ListView) findViewById(R.id.acal_chooseAttendeeLv);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_attendee, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        switch (item.getItemId()) {
            case R.id.addStudent:

                Toast.makeText(this, "ADD", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(ChooseAttendeeActivity.this, StudentActivity.class);

                i.putExtra("isEditMode", false);

                startActivityForResult(i, mRequestCode101);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
