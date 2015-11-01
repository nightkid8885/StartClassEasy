package com.cmms.codetech.startclasseasy;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuActivity extends ListActivity {

    ListView list;
    String[] itemname = {
            "Calendar",
            "Feedback",
            "Admins",
            "Students"

    };
    Integer[] imgid = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic3,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MenuItemAdapter adapter = new MenuItemAdapter(this, itemname, imgid);
//        list=(ListView)findViewById(R.id.list_item);
        list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Slecteditem = itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
            }
        });

        list.setOnItemClickListener(menuclick);
    }

    AdapterView.OnItemClickListener menuclick = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            switch (position) {
                case 0:
                    Toast.makeText(getApplicationContext(), "Calendar", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(getApplicationContext(), "Feedback", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(), "Administration", Toast.LENGTH_SHORT).show();
                    Intent iAdmin = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(iAdmin);
                    break;
                case 3:
                    Toast.makeText(getApplicationContext(), "Student", Toast.LENGTH_SHORT).show();
                    Intent iStudent = new Intent(getApplicationContext(), AttendeeListActivity.class);
                    startActivity(iStudent);
                    break;
                default:
                    break;
            }


        }
    };

}
