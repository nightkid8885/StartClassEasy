package com.cmms.codetech.startclasseasy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.cmms.codetech.startclasseasy.adapter.CourseChooseAttendeeAdapter;
import com.cmms.codetech.startclasseasy.model.Attendee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daryl on 29/10/2015.
 */
public class ChooseAttendeeActivity extends Activity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_attendee_list);

        initView();
        extras = getIntent().getExtras();

        inflateStudent();

        chooseAttendeeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SparseBooleanArray checked = chooseAttendeeLv.getCheckedItemPositions();

                //selectedItems.add(adapter.getItem(position));
                Log.e("Position Item", adapter.getItem(position));
                Log.e("Boolean", String.valueOf(selectedItems.contains(adapter.getItem(position))));

                if (!selectedItems.contains(adapter.getItem(position))) {
                    selectedItems.add(adapter.getItem(position));
                    selectedItemsRowID.add(attendees.get(position).getRowID());

                    dbHelper.addCourseAttendee(attendees.get(position).getRowID(), extras.getLong("courseID"));

                } else {
                    for (int i = 0; i < selectedItems.size(); i++) {
                        if (selectedItems.get(i) == adapter.getItem(position)) {
                            selectedItems.remove(i);
                            selectedItemsRowID.remove(i);

                            dbHelper.deleteCourseAttendee(attendees.get(position).getRowID(),extras.getLong("courseID"));

                        }
                    }
                }
            }

        });

        searchAttendee.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                System.out
//                        .println("Text [" + s + "] - Start [" + start
//                                + "] - Before [" + before + "] - Count ["
//                                + count + "]");
//                if (count < before) {
//                    // We're deleting char so we need to reset the adapter data
//                    //courseChooseAttendeeAdapter.resetData();
//                    courseChooseAttendeeAdapter.resetData();
//                }

                //courseChooseAttendeeAdapter.getFilter().filter(s.toString());
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ChooseAttendeeActivity.this, StudentActivity.class);

                i.putExtra("isEditMode", false);

                startActivityForResult(i, mRequestCode101);

//                int len = chooseAttendeeLv.getCount();
//                Log.e("Len", String.valueOf(len));
//                SparseBooleanArray checked = chooseAttendeeLv.getCheckedItemPositions();
//                for (int i = 0; i < len; i++)
//                    if (checked.valueAt(i)) {
//                        Log.e("Name", attendees.get(i).getAttendeeName());
//                    }
//                for (int i = 0; i < selectedItems.size(); i++) {
//                    Log.e("Name", selectedItems.get(i));
//                    Log.e("RowID", String.valueOf(selectedItemsRowID.get(i)));
//                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == mRequestCode101) {
            Log.e("DEBUG_TAG", String.valueOf(resultCode));
            if (resultCode == Activity.RESULT_OK) {
                //Toast.makeText(StudentListActivity.this, String.valueOf(data.getExtras().getBoolean("inserted")), Toast.LENGTH_LONG).show();
                //attendeeAdapter.notifyDataSetChanged();
                inflateStudent();
            }
        }
    }

    private void inflateStudent() {
        attendees = dbHelper.listAllAttendees();

        String[] attendeesList = new String[attendees.size()];
        Long[] rowIDtoInsert = new Long[attendees.size()];

        for (int i = 0; i < attendees.size(); i++) {
            attendeesList[i] = attendees.get(i).getAttendeeName() + " (" + attendees.get(i).getAttendeeContact() + ") ";
            rowIDtoInsert[i] = attendees.get(i).getRowID();
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, attendeesList);
        chooseAttendeeLv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        chooseAttendeeLv.setAdapter(adapter);
    }

    private void initView() {

        searchAttendee = (EditText) findViewById(R.id.acal_searchChooseAttendeeEt);
        addBtn = (Button) findViewById(R.id.acal_addBtn);
        chooseAttendeeLv = (ListView) findViewById(R.id.acal_chooseAttendeeLv);

    }
}
