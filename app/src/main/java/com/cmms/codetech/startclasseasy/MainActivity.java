package com.cmms.codetech.startclasseasy;

import android.content.Intent;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    UserDatabase db;
    Button main_submitBtn;
    EditText main_email, main_password;
    UserDatabase dbHelper = new UserDatabase(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        findViewById(R.id.am_submitBtn).setOnClickListener((View.OnClickListener) this);

        initView();

        dbHelper.addAttendee("Daryl Lee", "1", "onnchai85@gmail.com", "0122690122");
        dbHelper.addAttendee("Estelle", "2", "estelle@gmail.com", "0122323133");
        dbHelper.addAttendee("Esther", "3", "esther@gmail.com", "012121212");
        dbHelper.addAttendee("Jacky", "4", "jacky@gmail.com", "0181238283");


    }

    private void initView() {
        main_email = (EditText) findViewById(R.id.main_email);
        main_password = (EditText) findViewById(R.id.main_password);
        main_submitBtn = (Button) findViewById(R.id.main_submitBtn);
    }


    public void onSignIn(View button){ //behaviour

        if (dbHelper.authStudent(main_email.getText().toString()).size() > 0){
            Intent i = new Intent(this, MenuActivity.class);
            i.putExtra("rowID", dbHelper.authStudent(main_email.getText().toString()).get(0).getRowID());
            startActivity(i);
        }else{
            Toast.makeText(this, "Invalid username or password, please try again", Toast.LENGTH_LONG).show();
        }


//        if(main_email.getEditableText().toString().contentEquals("abc@abc.com" ) &&
//                main_password.getEditableText().toString().contentEquals("password")) {
//
//
//            Intent i = new Intent(this, MenuActivity.class);
//            startActivity(i);
//        }
//        else {
//            Toast.makeText(this, "Invalid username or password, please try again", Toast.LENGTH_LONG).show();
//        }

    }
}