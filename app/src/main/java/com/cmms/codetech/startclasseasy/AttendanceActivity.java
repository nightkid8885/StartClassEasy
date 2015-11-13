package com.cmms.codetech.startclasseasy;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cmms.codetech.startclasseasy.model.Attendee;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by daryl on 31/10/2015.
 */
public class AttendanceActivity extends Activity {

    TextView courseDate;
    ListView attendanceLv;

    public static final String MIME_TEXT_PLAIN = "text/plain";
    UserDatabase dbHelper = new UserDatabase(AttendanceActivity.this);
    ArrayAdapter<String> adapter;
    List<Attendee> attendees = new ArrayList<Attendee>();
    Bundle extras;
    private static AttendanceActivity inst;
    final ArrayList<Long> attendeesRowIDList = new ArrayList<Long>();

    String TAG = "AttendanceActivity";

    private NfcAdapter mNfcAdapter;


    public static AttendanceActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);

        initView();

        extras = getIntent().getExtras();

        courseDate.setText(extras.getString("courseDate"));

        inflateAttendeeList();

        if (mNfcAdapter == null) {
            // Stop here, we definitely need NFC
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;

        }

        if (!mNfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC is disabled", Toast.LENGTH_SHORT ).show();
        } else {
            Toast.makeText(this, "NFC is Enabled", Toast.LENGTH_LONG ).show();
        }

        handleIntent(getIntent());

    }

    private void initView() {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        courseDate = (TextView) findViewById(R.id.aal_coursedateTv);
        attendanceLv = (ListView) findViewById((R.id.aal_attendanceLv));
    }

    public void inflateAttendeeList() {

        attendees = dbHelper.listAllCourseAttendees(extras.getLong("courseID"), extras.getLong("dateID"));

        String[] attendeesList = new String[attendees.size()];

        for (int i = 0; i < attendees.size(); i++) {
            attendeesList[i] = attendees.get(i).getAttendeeName() + " (" + attendees.get(i).getAttendeeContact() + ")";
            attendeesRowIDList.add(attendees.get(i).getRowID());
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, attendeesList);
        attendanceLv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        attendanceLv.setAdapter(adapter);

        for (int i = 0; i < attendees.size(); i++) {
            Log.e(TAG, String.valueOf(attendees.get(i).getChecked()));
            if (attendees.get(i).getChecked()==1){

                attendanceLv.setItemChecked(i, true);
            }else{
                attendanceLv.setItemChecked(i, false);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        /**
         * It's important, that the activity is in the foreground (resumed). Otherwise
         * an IllegalStateException is thrown.
         */
        setupForegroundDispatch(this, mNfcAdapter);
    }

    @Override
    protected void onPause() {
        /**
         * Call this before onPause, otherwise an IllegalArgumentException is thrown as well.
         */
        stopForegroundDispatch(this, mNfcAdapter);

        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        /**
         * This method gets called, when a new Intent gets associated with the current activity instance.
         * Instead of creating a new activity, onNewIntent will be called. For more information have a look
         * at the documentation.
         *
         * In our case this method gets called, when the user attaches a Tag to the device.
         */
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        //Log.e(TAG, action);
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {

            String type = intent.getType();
            Log.e("Type", type);
            if (MIME_TEXT_PLAIN.equals(type)) {

                android.nfc.Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                new NdefReaderTask().execute(tag);

            } else {
                Log.d(TAG, "Wrong mime type: " + type);
            }
        } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {

            // In case we would still use the Tech Discovered Intent
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String[] techList = tag.getTechList();
            String searchedTech = Ndef.class.getName();

            for (String tech : techList) {
                if (searchedTech.equals(tech)) {
                    new NdefReaderTask().execute(tag);
                    break;
                }
            }
        }
    }

    private class NdefReaderTask extends AsyncTask<Tag, Void, String> {

        @Override
        protected String doInBackground(Tag... params) {
            Tag tag = params[0];

            Ndef ndef = Ndef.get(tag);
            if (ndef == null) {
                // NDEF is not supported by this Tag.
                return null;
            }

            NdefMessage ndefMessage = ndef.getCachedNdefMessage();

            NdefRecord[] records = ndefMessage.getRecords();
            for (NdefRecord ndefRecord : records) {
                if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
                    try {
                        return readText(ndefRecord);
                    } catch (UnsupportedEncodingException e) {
                        Log.e(TAG, "Unsupported Encoding", e);
                    }
                }
            }

            return null;
        }

        private String readText(NdefRecord record) throws UnsupportedEncodingException {
        /*
         * See NFC forum specification for "Text Record Type Definition" at 3.2.1
         *
         * http://www.nfc-forum.org/specs/
         *
         * bit_7 defines encoding
         * bit_6 reserved for future use, must be 0
         * bit_5..0 length of IANA language code
         */

            byte[] payload = record.getPayload();

            // Get the Text Encoding
            String textEncoding;

            if ((payload[0] & 128) == 0){
                textEncoding = "UTF-8";
            }else{
                textEncoding = "UTF-16";
            }
            // Get the Language Code
            int languageCodeLength = payload[0] & 0063;

            // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
            // e.g. "en"

            // Get the Text
            return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {

                Toast.makeText(AttendanceActivity.this, "Read content: " + result, Toast.LENGTH_SHORT ).show();
                Toast.makeText(AttendanceActivity.this, "CourseID = " + String.valueOf(extras.getLong("courseID")) + ", dateID = " + String.valueOf(extras.getLong("dateID")), Toast.LENGTH_SHORT ).show();

                if (dbHelper.addCourseAttendance(extras.getLong("dateID"), extras.getLong("courseID"), Long.valueOf(result))){
                    inflateAttendeeList();
                }else{
                    Toast.makeText(AttendanceActivity.this, "Insert Failed", Toast.LENGTH_SHORT ).show();
                };


            }
        }
    }

    public static void setupForegroundDispatch(final Activity activity, NfcAdapter adapter) {
        final Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        final PendingIntent pendingIntent = PendingIntent.getActivity(activity.getApplicationContext(), 0, intent, 0);

        IntentFilter[] filters = new IntentFilter[1];
        String[][] techList = new String[][]{};

        // Notice that this is the same filter as in our manifest.
        filters[0] = new IntentFilter();
        filters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filters[0].addCategory(Intent.CATEGORY_DEFAULT);
        try {
            filters[0].addDataType(MIME_TEXT_PLAIN);
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("Check your mime type.");
        }

        adapter.enableForegroundDispatch(activity, pendingIntent, filters, techList);
    }

    public static void stopForegroundDispatch(final Activity activity, NfcAdapter adapter) {
        adapter.disableForegroundDispatch(activity);
    }
}
