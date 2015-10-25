package com.cmms.codetech.startclasseasy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View.OnClickListener;

public class AdminActivity extends AppCompatActivity {

    EditText searchCourseEt;
    Button addNewCourseBtn;
    ListView listViewLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initView();

        addNewCourseBtn.setOnClickListener(newCourse);



    }

    private void initView() {
        searchCourseEt = (EditText) findViewById(R.id.aa_searchCourseEt);
        addNewCourseBtn = (Button) findViewById(R.id.aa_newBtn);
        listViewLv = (ListView) findViewById(R.id.aa_listviewLv);
    }

    OnClickListener newCourse = new OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(AdminActivity.this,
                    CourseActivity.class));
        }
    };
}
