package com.cmms.codetech.startclasseasy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cmms.codetech.startclasseasy.model.Attendee;

import java.util.List;

public class StudentActivity extends AppCompatActivity {

    EditText nameEt;
    EditText emailEt;
    EditText studentIdEt;
    EditText contactNumberEt;
    Button submitBtn;

    UserDatabase dbHelper = new UserDatabase(StudentActivity.this);
    Integer requestcode;
    Boolean updated;
    Boolean inserted;
    Boolean isEditMode = false;

    Bundle extras;
    Long rowID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendee);

        initView();

        extras = getIntent().getExtras();
        isEditMode = extras.getBoolean("isEditMode");


        if (isEditMode){
            List<Attendee> attendee = dbHelper.getAttendee(extras.getLong("rowID"));

            nameEt.setText(attendee.get(0).getAttendeeName());
            studentIdEt.setText(attendee.get(0).getAttendeeID());
            emailEt.setText(attendee.get(0).getAttendeeEmail());
            contactNumberEt.setText(attendee.get(0).getAttendeeContact());
            submitBtn.setText("SAVE");

            studentIdEt.setEnabled(false);
        }



        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.e("AddCourse Attendee", String.valueOf(extras.getLong("rowID")));
                if (nameEt.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Name cannot be null", Toast.LENGTH_LONG).show();
                    nameEt.requestFocus();
                } else {
                    if (studentIdEt.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "StudentID cannot be null", Toast.LENGTH_LONG).show();
                        studentIdEt.requestFocus();
                    } else {
                        if (emailEt.getText().toString().matches("")) {
                            Toast.makeText(getApplicationContext(), "Email cannot be null", Toast.LENGTH_LONG).show();
                            emailEt.requestFocus();
                        } else {
                            if (contactNumberEt.getText().toString().matches("")) {
                                Toast.makeText(getApplicationContext(), "Contact cannot be null", Toast.LENGTH_LONG).show();
                                contactNumberEt.requestFocus();
                            } else {

                                Intent i = new Intent();

                                if (isEditMode){
                                    updated = dbHelper.updateAttendeeInfo(extras.getLong("rowID"), nameEt.getText().toString(), emailEt.getText().toString(), contactNumberEt.getText().toString());

                                    i.putExtra("inserted", updated);
                                    setResult(Activity.RESULT_OK, i);
                                    finish();
                                }else{
                                    //Log.e("AddCourse Attendee", String.valueOf(extras.getLong("rowID")));
                                    inserted = dbHelper.addAttendee(nameEt.getText().toString(), studentIdEt.getText().toString(), emailEt.getText().toString(), contactNumberEt.getText().toString());

                                    if(inserted){
                                        i.putExtra("inserted", inserted);
                                        setResult(Activity.RESULT_OK, i);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Insert Course Attendee Failed", Toast.LENGTH_LONG).show();
                                    }
                                }


                            }
                        }
                    }
                }
            }
        });
    }

    private void initView() {
        nameEt = (EditText) findViewById(R.id.aat_nameEt);
        emailEt = (EditText) findViewById(R.id.aat_emailEt);
        studentIdEt = (EditText) findViewById(R.id.aat_studentIdEt);
        contactNumberEt = (EditText) findViewById(R.id.aat_contactNumberEt);
        submitBtn = (Button) findViewById(R.id.aat_submitBtn);
    }


}
