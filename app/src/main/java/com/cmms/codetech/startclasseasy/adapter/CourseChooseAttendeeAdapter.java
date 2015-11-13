package com.cmms.codetech.startclasseasy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.cmms.codetech.startclasseasy.R;
import com.cmms.codetech.startclasseasy.model.Attendee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daryl on 28/10/2015.
 */
public class CourseChooseAttendeeAdapter extends ArrayAdapter<Attendee> implements Filterable {

    private List<Attendee> attendeeList;
    private List<Attendee> origAttendeeList;
    private Filter AttendeeFilter;
    private Context context;

    long attendee_rowID;

    private ArrayList<Boolean> itemChecked = new ArrayList<Boolean>();

    public CourseChooseAttendeeAdapter(Context ctx, List<Attendee> attendeeList) {
        super(ctx, R.layout.activity_choose_attendee_list_row, attendeeList);
        this.context = ctx;
        this.attendeeList = attendeeList;
        this.origAttendeeList = attendeeList;

    }

    @Override
    public int getCount() {
        return attendeeList.size();
    }


    @Override
    public Attendee getItem(int position) {
        return attendeeList.get(position);
    }

    @Override
    public int getPosition(Attendee item) {

        return super.getPosition(item);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        AttendeeHolder holder = new AttendeeHolder();

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.activity_choose_attendee_list_row, null);

            TextView attendeeName = (TextView) v.findViewById(R.id.acalr_attendeeNameTv);
            CheckBox checkBox = (CheckBox) v.findViewById(R.id.acalr_attendeeNameCb);


            checkBox.setTag(position);
            holder.attendeeName = attendeeName;
            holder.checkBox = checkBox;

            v.setTag(holder);

        } else {

            holder = (AttendeeHolder) v.getTag();
        }
        Log.e("Check CheckBox", String.valueOf(attendeeList.size()));
        Attendee p = attendeeList.get(position);
        holder.attendeeName.setText(p.getAttendeeName());

        if (attendeeList.get(position).getChecked() == 1){
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }

//        holder.checkBox.setOnClickListener(check);
//
//        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (buttonView.isChecked()) {
//                    itemChecked.set(position, true);
//                    Log.e("check change 1", String.valueOf(position));
//                } else if (!buttonView.isChecked()) {
//                    itemChecked.set(position, false);
//                    Log.e("check change 2", String.valueOf(position));
//                }
//            }
//        });
//
//        holder.checkBox.setChecked(itemChecked.get(position));
//        Log.e("check boolean", String.valueOf(itemChecked.get(position)));

        return v;
    }

    private static class AttendeeHolder {
        public TextView attendeeName;
        public CheckBox checkBox;
    }


    public void resetData() {
        attendeeList = origAttendeeList;
    }

    @Override
    public Filter getFilter() {
        if (AttendeeFilter == null)
            AttendeeFilter = new CourseFilter();

        return AttendeeFilter;
    }

    private class CourseFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list

                results.values = origAttendeeList;
                results.count = origAttendeeList.size();
            } else {
                // We perform filtering operation
                List<Attendee> nPlanetList = new ArrayList<Attendee>();

                for (Attendee p : attendeeList) {
                    if (p.getAttendeeName().toUpperCase()
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
                attendeeList = (List<Attendee>) results.values;
                notifyDataSetChanged();
            }

        }

    }

    View.OnClickListener check = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

        }

    };
}