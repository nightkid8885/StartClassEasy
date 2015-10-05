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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_attendee_list, menu);
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
