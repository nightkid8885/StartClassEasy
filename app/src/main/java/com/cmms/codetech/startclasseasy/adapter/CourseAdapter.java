package com.cmms.codetech.startclasseasy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmms.codetech.startclasseasy.R;
import com.cmms.codetech.startclasseasy.model.Course;
import com.cmms.codetech.startclasseasy.model.CourseDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daryl on 26/10/2015.
 */
public class CourseAdapter extends ArrayAdapter<Course> implements Filterable{

    private List<Course> courseList;
    private List<Course> origCourseList;
    private Filter courseFilter;
    private Context context;

    public CourseAdapter(Context ctx, List<Course> courseList) {
        super(ctx, R.layout.activity_admin_row, courseList);
        this.courseList = courseList;
        this.context = ctx;
        this.origCourseList = courseList;
    }

    @Override
    public int getCount() {

        return courseList.size();
    }

    @Override
    public Course getItem(int position) {

        return courseList.get(position);
    }

    @Override
    public int getPosition(Course item) {

        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return courseList.get(position).hashCode();
        //return courseList.get(position).getRowID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        CourseHolder holder = new CourseHolder();

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.activity_admin_row, null);

            TextView courseName = (TextView) v.findViewById(R.id.aar_courseNameTv);
            TextView courseTrainer = (TextView) v.findViewById(R.id.aar_conductorTv);
            TextView courseStatus = (TextView) v.findViewById(R.id.aar_courseStatusTv);

            holder.courseName = courseName;
            holder.courseTrainer = courseTrainer;
            holder.courseStatus = courseStatus;
            v.setTag(holder);
        } else {

            holder = (CourseHolder) v.getTag();
        }
        Course p = courseList.get(position);
        holder.courseName.setText(p.getCourseName());
        holder.courseTrainer.setText(p.getCourseTrainer());
        holder.courseStatus.setText(p.getCourseStatus());

        return v;
    }

    private static class CourseHolder {
        public TextView courseName;
        public TextView courseTrainer;
        public TextView courseStatus;

        // public ImageView image;
    }

    public void resetData() {
        courseList = origCourseList;
    }

    @Override
    public Filter getFilter() {
        if (courseFilter == null)
            courseFilter = new AssetFilter();

        return courseFilter;
    }

    private class AssetFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list

                results.values = origCourseList;
                results.count = origCourseList.size();
            } else {
                // We perform filtering operation
                List<Course> nPlanetList = new ArrayList<Course>();

                for (Course p : courseList) {
                    if (p.getCourseName().toUpperCase()
                            .contains(constraint.toString().toUpperCase()))
                        nPlanetList.add(p);
                }

                results.values = nPlanetList;
                results.count = nPlanetList.size();

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                courseList = (List<Course>) results.values;
                notifyDataSetChanged();
            }

        }

    }

}
