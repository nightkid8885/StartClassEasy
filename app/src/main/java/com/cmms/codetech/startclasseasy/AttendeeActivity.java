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
}
