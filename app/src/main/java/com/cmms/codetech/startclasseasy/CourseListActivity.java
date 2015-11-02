package com.cmms.codetech.startclasseasy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View.OnClickListener;

import com.cmms.codetech.startclasseasy.adapter.CourseAdapter;

public class CourseListActivity extends AppCompatActivity {

    EditText searchCourseEt;
    Button addNewCourseBtn;
    ListView listViewLv;

    boolean isEditMode = true;

    CourseAdapter courseAdapter;

    UserDatabase dbHelper = new UserDatabase(CourseListActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initView();

        addNewCourseBtn.setOnClickListener(newCourse);

        searchCourseEt.addTextChangedListener(new TextWatcher() {
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
                    courseAdapter.resetData();
                }

                courseAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inflateCourseList();

        listViewLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(CourseListActivity.this, CourseActivity.class);

                i.putExtra("rowID", dbHelper.listAllCourse().get(position).getRowID());
                i.putExtra("isEditMode", true);

                startActivity(i);
            }
        });


    }


    private void initView() {
        searchCourseEt = (EditText) findViewById(R.id.aa_searchCourseEt);
        addNewCourseBtn = (Button) findViewById(R.id.aa_newBtn);
        listViewLv = (ListView) findViewById(R.id.aa_listviewLv);
    }

    OnClickListener newCourse = new OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i = new Intent(CourseListActivity.this, CourseActivity.class);
            i.putExtra("isEditMode", false);
            startActivity(i);
        }
    };

    public void inflateCourseList() {
        courseAdapter = new CourseAdapter(this, dbHelper.listAllCourse());
        listViewLv.setAdapter(courseAdapter);
    }


}
