package com.cmms.codetech.startclasseasy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class AttendeeActivity extends AppCompatActivity {

    EditText nameEt;
    EditText emailEt;
    EditText studentIdEt;
    EditText contactNumberEt;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendee);

        initView();
    }

    private void initView() {
        nameEt = (EditText) findViewById(R.id.aat_nameEt);
        emailEt = (EditText) findViewById(R.id.aat_emailEt);
        studentIdEt = (EditText) findViewById(R.id.aat_studentIdEt);
        contactNumberEt = (EditText) findViewById(R.id.aat_contactNumberEt);
        submitBtn = (Button) findViewById(R.id.aat_submitBtn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_attendee, menu);
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
