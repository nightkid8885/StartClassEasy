package com.cmms.codetech.startclasseasy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.cmms.codetech.startclasseasy.adapter.CourseAdapter;

/**
 * Created by daryl on 12/11/2015.
 */
public class StudentCourseListActivity extends Activity {

    EditText searchCourseEt;
    Button addNewCourseBtn;
    ListView listViewLv;

    CourseAdapter courseAdapter;
    UserDatabase dbHelper = new UserDatabase(StudentCourseListActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin);

        initView();

        //disable
        searchCourseEt.setVisibility(View.GONE);
        addNewCourseBtn.setVisibility(View.GONE);

        //
        inflateCourseList();

        listViewLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(StudentCourseListActivity.this, StudentCourseCalendarActivity.class);

                i.putExtra("courseID", dbHelper.listStudentAllCourse().get(position).getRowID());
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

    public void inflateCourseList() {
        courseAdapter = new CourseAdapter(this, dbHelper.listStudentAllCourse());
        listViewLv.setAdapter(courseAdapter);
    }

}
