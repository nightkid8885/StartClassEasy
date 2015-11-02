package com.cmms.codetech.startclasseasy.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cmms.codetech.startclasseasy.StudentListActivity;
import com.cmms.codetech.startclasseasy.R;
import com.cmms.codetech.startclasseasy.UserDatabase;
import com.cmms.codetech.startclasseasy.model.Attendee;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by daryl on 28/10/2015.
 */
public class CourseAttendeeAdapter extends ArrayAdapter<Attendee> implements Filterable {

    private List<Attendee> attendeeList;
    private List<Attendee> origAttendeeList;
    private Filter AttendeeFilter;
    private Context context;

    long attendee_rowID;

    public CourseAttendeeAdapter(Context ctx, List<Attendee> attendeeList) {
        super(ctx, R.layout.activity_attendee_list_row_attendee, attendeeList);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        AttendeeHolder holder = new AttendeeHolder();

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.activity_attendee_list_row_attendee, null);

            TextView attendeeName = (TextView) v.findViewById(R.id.acra_attendeeNameTv);
            ImageView minus = (ImageView) v.findViewById(R.id.acra_attendeeNameIv);

            minus.setTag(position);
            holder.attendeeName = attendeeName;
            holder.minus = minus;

            v.setTag(holder);

        } else {

            holder = (AttendeeHolder) v.getTag();
        }
        Log.e("Check attendeeList", String.valueOf(attendeeList.size()));
        Attendee p = attendeeList.get(position);
        holder.attendeeName.setText(p.getAttendeeName());
        holder.minus.setOnClickListener(minus);

        return v;
    }

    private static class AttendeeHolder {
        public TextView attendeeName;
        public ImageView minus;
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

    View.OnClickListener minus = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            RelativeLayout rl = (RelativeLayout) v.getParent();

            Log.e("TAG", String.valueOf(attendeeList.get((Integer) v.getTag()).getRowID()));

            attendee_rowID = attendeeList.get((Integer) v.getTag()).getRowID();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getRootView().getContext());
            alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");

            alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(context, "You clicked yes button", Toast.LENGTH_LONG).show();
                    UserDatabase dbHelper = new UserDatabase(context);
                    dbHelper.deleteAttendee(attendee_rowID);

                    ((StudentListActivity) getContext()).inflateAttendeeList();

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