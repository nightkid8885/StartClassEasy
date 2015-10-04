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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin, menu);
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
