package com.cmms.codetech.startclasseasy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.cmms.codetech.startclasseasy.CourseActivity;
import com.cmms.codetech.startclasseasy.R;
import com.cmms.codetech.startclasseasy.UserDatabase;
import com.cmms.codetech.startclasseasy.model.CourseDate;

import java.util.List;

/**
 * Created by daryl on 24/10/2015.
 */
public class CourseDateAdapter  extends ArrayAdapter<CourseDate> {

    private List<CourseDate> courseDateList;
    private Context context;

    long courseDate_rowID;
    long course_rowID;
    boolean isEditMode;


    public CourseDateAdapter(Context ctx, List<CourseDate> courseDateList, Long courseID, Boolean isEditMode) {
            super(ctx, R.layout.activity_course_row_date, courseDateList);
            this.courseDateList = courseDateList;
            this.context = ctx;
            this.course_rowID = courseID;
            this.isEditMode = isEditMode;

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public int getPosition(CourseDate item) {
        return super.getPosition(item);
    }

    @Override
    public CourseDate getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        CourseDateHolder holder = new CourseDateHolder();

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.activity_course_row_date, parent, false);

            TextView courseDate = (TextView) v.findViewById(R.id.acrd_courseDateTimeTv);
            ImageView minus = (ImageView) v.findViewById(R.id.acrd_removeDateTimeIv);

            if (isEditMode){
                minus.setVisibility(View.GONE);
            }

            minus.setTag(position);
            holder.courseDate = courseDate;
            holder.minus = minus;
            v.setTag(holder);
        } else {

            holder = (CourseDateHolder) v.getTag();
        }
        CourseDate p = courseDateList.get(position);
        holder.courseDate.setText(p.getCourseDate());
        holder.minus.setOnClickListener(minus);

        return v;
    }

    private static class CourseDateHolder {
        public TextView courseDate;
        public ImageView minus;

        // public ImageView image;
    }

    View.OnClickListener minus = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            RelativeLayout rl = (RelativeLayout) v.getParent();

            Log.e("TAG", String.valueOf(courseDateList.get((Integer) v.getTag()).getRowID()));

            courseDate_rowID = courseDateList.get((Integer) v.getTag()).getRowID();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getRootView().getContext());
            alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");

            alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(context, "You clicked yes button", Toast.LENGTH_LONG).show();
                    UserDatabase dbHelper = new UserDatabase(context);
                    dbHelper.deleteCourseDate(courseDate_rowID);

                    ((CourseActivity)getContext()).inflateCourseDateListView(course_rowID, false);

                }
            });

            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //finish();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    };
}
