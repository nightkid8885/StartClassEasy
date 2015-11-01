package com.cmms.codetech.startclasseasy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.cmms.codetech.startclasseasy.adapter.CourseAttendeeAdapter;

public class AttendeeListActivity extends AppCompatActivity {

    EditText searchAttendeeEt;
    Button addNewAttendeeBtn;
    ListView listViewLv;

    long courseID;

    boolean isEditMode = false;
    private int mRequestCode101 = 101;

    CourseAttendeeAdapter attendeeAdapter;
    Bundle extras;

    UserDatabase dbHelper = new UserDatabase(AttendeeListActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendee_list);

        initView();

        extras = getIntent().getExtras();

        addNewAttendeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AttendeeListActivity.this, AttendeeActivity.class);

                i.putExtra("isEditMode", false);
                //Log.e("AttendList Rowid", String.valueOf(extras.getLong("rowID")));
                //startActivity(i);
                startActivityForResult(i, mRequestCode101);
            }
        });

        searchAttendeeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out
                        .println("Text [" + s + "] - Start [" + start
                                + "] - Before [" + before + "] - Count ["
                                + count + "]");
                if (count < before) {
                    // We're deleting char so we need to reset the adapter data
                    attendeeAdapter.resetData();
                }

                attendeeAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listViewLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(AttendeeListActivity.this, AttendeeActivity.class);

                i.putExtra("isEditMode", true);
                i.putExtra("rowID", dbHelper.listAllAttendees().get(position).getRowID());

                startActivity(i);


            }
        });

        inflateAttendeeList();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == mRequestCode101) {
            Log.e("DEBUG_TAG", String.valueOf(resultCode));
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(AttendeeListActivity.this, String.valueOf(data.getExtras().getBoolean("inserted")), Toast.LENGTH_LONG).show();
                //attendeeAdapter.notifyDataSetChanged();
                inflateAttendeeList();
            }
        }
    }

    private void initView() {

        searchAttendeeEt = (EditText) findViewById(R.id.aal_searchAttendeeEt);
        addNewAttendeeBtn = (Button) findViewById(R.id.aal_newAttendeeBtn);
        listViewLv = (ListView) findViewById(R.id.aal_listviewLv);
    }



    public void inflateAttendeeList() {
        attendeeAdapter = new CourseAttendeeAdapter(this, dbHelper.listAllAttendees());
        listViewLv.setAdapter(attendeeAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();

        //Toast.makeText(getApplicationContext(), "on Resume", Toast.LENGTH_SHORT).show();

        //attendeeAdapter.notifyDataSetChanged();

    }
}
