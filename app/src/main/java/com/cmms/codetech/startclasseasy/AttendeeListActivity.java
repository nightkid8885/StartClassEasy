package com.cmms.codetech.startclasseasy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class AttendeeListActivity extends AppCompatActivity {

    EditText searchCourseEt;
    Button addNewAttendeeBtn;
    ListView listViewLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendee_list);

        initView();

        addNewAttendeeBtn.setOnClickListener(addAttendee);

    }

    private void initView() {

        searchCourseEt = (EditText) findViewById(R.id.aal_searchCourseEt);
        addNewAttendeeBtn = (Button) findViewById(R.id.aal_newAttendeeBtn);
        listViewLv = (ListView) findViewById(R.id.aal_listviewLv);
    }

    View.OnClickListener addAttendee = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(AttendeeListActivity.this,
                    AttendeeActivity.class));
        }
    };

}
