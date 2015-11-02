package com.cmms.codetech.startclasseasy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cmms.codetech.startclasseasy.model.Attendee;
import com.cmms.codetech.startclasseasy.model.Course;
import com.cmms.codetech.startclasseasy.model.CourseDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Esu on 10/4/15.
 */
public class UserDatabase extends SQLiteOpenHelper {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String SQLITE_TABLE_COURSE = "crs_mst";
    private static final String SQLITE_TABLE_COURSE_DATE = "crs_ls1";
    private static final String SQLITE_TABLE_COURSE_FEEDBACK = "crs_ls2";
    private static final String SQLITE_TABLE_ATTENDEES_COURSES = "atd_ls1";
    private static final String SQLITE_TABLE_ATTENDEES_ATTENDANCE = "atd_ls2";
    private static final String SQLITE_TABLE_ATTENDEES = "atd_mst";

    public static final String ROWID = "rowid";
    public static final String MST_ROWID = "mst_rowid";
    public static final String AUDIT_USER = "audit_user";
    public static final String AUDIT_DATE = "audit_date";

    public static final String CRS_MST_CRS_NAME = "crs_mst_crs_name";
    public static final String CRS_MST_CON_NAME = "crs_mst_con_name";
    public static final String CRS_MST_CRS_STS = "crs_mst_crs_sts";

    public static final String CRS_LS1_CRS_DATE = "crs_ls1_crs_date";

    public static final String CRS_LS2_CRS_ID = "crs_ls2_crs_id";
    public static final String CRS_LS2_STU_ID = "crs_ls2_stu_id";
    public static final String CRS_LS2_ANS_1 = "crs_ls2_ans_1";
    public static final String CRS_LS2_ANS_2 = "crs_ls2_ans_2";
    public static final String CRS_LS2_ANS_3 = "crs_ls2_ans_3";
    public static final String CRS_LS2_ANS_4 = "crs_ls2_ans_4";
    public static final String CRS_LS2_ANS_5 = "crs_ls2_ans_5";
    public static final String CRS_LS2_ANS_6 = "crs_ls2_ans_6";
    public static final String CRS_LS2_ANS_7 = "crs_ls2_ans_7";
    public static final String CRS_LS2_ANS_8 = "crs_ls2_ans_8";
    public static final String CRS_LS2_ANS_9 = "crs_ls2_ans_9";


    public static final String STU_LS1_CRS_ID = "stu_ls1_atd_course";
    public static final String STU_LS1_ATD_STS = "stu_ls1_atd_sts";
    public static final String STU_LS1_CRS_DATE = "stu_ls1_crs_date";

    public static final String STU_LS2_DATE_ID = "stu_ls2_date_id";
    public static final String STU_LS2_ATD_STS = "stu_ls2_atd_sts";

    public static final String STU_MST_NAME = "atd_mst_name";
    public static final String STU_MST_ID = "atd_mst_id";
    public static final String STU_MST_GENDER = "atd_mst_gen";
    public static final String STU_MST_CONTACT = "atd_mst_tel";
    public static final String STU_MST_EMAIL = "atd_mst_email";



    public UserDatabase(Context context) {
        super(context, "UserDatabase", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE_CRS_MST);
        db.execSQL(DATABASE_CREATE_CRS_LS1);
        db.execSQL(DATABASE_CREATE_CRS_LS2);
        db.execSQL(DATABASE_CREATE_ATD_LS1);
        db.execSQL(DATABASE_CREATE_ATD_LS2);
        db.execSQL(DATABASE_CREATE_ATD_MST);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    private static final String DATABASE_CREATE_CRS_MST = "CREATE TABLE if not exists "
            + SQLITE_TABLE_COURSE
            + " ("
            + ROWID
            + " integer PRIMARY KEY autoincrement,"
            + CRS_MST_CRS_NAME
            + " TEXT,"
            + CRS_MST_CON_NAME
            + " TEXT,"
            + CRS_MST_CRS_STS
            + " TEXT,"
            + AUDIT_USER
            + " TEXT,"
            + AUDIT_DATE
            + " )";

    private static final String DATABASE_CREATE_ATD_LS1 = "CREATE TABLE if not exists "
            + SQLITE_TABLE_ATTENDEES_COURSES
            + " ("
            + MST_ROWID
            + " integer NOT NULL,"
            + STU_LS1_CRS_ID
            + " TEXT,"
            + STU_LS1_ATD_STS
            + " TEXT,"
            + AUDIT_USER
            + " TEXT,"
            + AUDIT_DATE
            + " ,"
            + ROWID
            + " integer PRIMARY KEY autoincrement"
            + ","
            + " UNIQUE (" + MST_ROWID + "," + STU_LS1_CRS_ID + "));";

    private static final String DATABASE_CREATE_ATD_LS2 = "CREATE TABLE if not exists "
            + SQLITE_TABLE_ATTENDEES_ATTENDANCE
            + " ("
            + MST_ROWID
            + " integer NOT NULL,"
            + STU_LS2_DATE_ID
            + " TEXT,"
            + STU_LS2_ATD_STS
            + " TEXT,"
            + AUDIT_USER
            + " TEXT,"
            + AUDIT_DATE
            + " ,"
            + ROWID
            + " integer PRIMARY KEY autoincrement"
            + ","
            + " UNIQUE (" + MST_ROWID + "," + STU_LS2_DATE_ID + "));";

    private static final String DATABASE_CREATE_CRS_LS1 = "CREATE TABLE if not exists "
            + SQLITE_TABLE_COURSE_DATE
            + " ("
            + MST_ROWID
            + " integer NOT NULL,"
            + CRS_LS1_CRS_DATE
            + " ,"
            + AUDIT_USER
            + " TEXT,"
            + AUDIT_DATE
            + " ,"
            + ROWID
            + " integer PRIMARY KEY autoincrement)";

    private static final String DATABASE_CREATE_CRS_LS2 = "CREATE TABLE if not exists "
            + SQLITE_TABLE_COURSE_FEEDBACK
            + " ("
            + MST_ROWID
            + " integer NOT NULL,"
            + CRS_LS2_CRS_ID
            + " TEXT,"
            + CRS_LS2_STU_ID
            + " TEXT,"
            + CRS_LS2_ANS_1
            + " TEXT,"
            + CRS_LS2_ANS_2
            + " TEXT,"
            + CRS_LS2_ANS_3
            + " TEXT,"
            + CRS_LS2_ANS_4
            + " TEXT,"
            + CRS_LS2_ANS_5
            + " TEXT,"
            + CRS_LS2_ANS_6
            + " TEXT,"
            + CRS_LS2_ANS_7
            + " TEXT,"
            + CRS_LS2_ANS_8
            + " TEXT,"
            + CRS_LS2_ANS_9
            + " TEXT,"
            + AUDIT_USER
            + " TEXT,"
            + AUDIT_DATE
            + " ,"
            + ROWID
            + " integer PRIMARY KEY autoincrement)";

    private static final String DATABASE_CREATE_ATD_MST = "CREATE TABLE if not exists "
            + SQLITE_TABLE_ATTENDEES
            + " ("
            + STU_MST_NAME
            + " TEXT,"
            + STU_MST_ID
            + " TEXT,"
            + STU_MST_GENDER
            + " TEXT,"
            + STU_MST_CONTACT
            + " TEXT,"
            + STU_MST_EMAIL
            + " TEXT,"
            + AUDIT_USER
            + " TEXT,"
            + AUDIT_DATE
            + " ,"
            + ROWID
            + " integer PRIMARY KEY autoincrement)";

    //Add course Title
    public long addCourse(String courseName, String trainerName, String courseStatus) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CRS_MST_CRS_NAME, courseName);
        values.put(CRS_MST_CON_NAME, trainerName);
        values.put(CRS_MST_CRS_STS, courseStatus);
        values.put(AUDIT_USER, "daryl");
        values.put(AUDIT_DATE, dateFormat.format(new java.util.Date()));

        return db.insert(SQLITE_TABLE_COURSE, null, values);
    }

    //Add Course Attendee while tick
    public long addCourseAttendee(Long attendeeID, Long courseID) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MST_ROWID, attendeeID);
        values.put(STU_LS1_CRS_ID, courseID);
        //values.put(ATD_LS1_ATD_STS, status);
        //values.put(ATD_LS1_CRS_DATE, courseDate);
        values.put(AUDIT_USER, "daryl");
        values.put(AUDIT_DATE, dateFormat.format(new java.util.Date()));

        return db.insert(SQLITE_TABLE_ATTENDEES_COURSES, null, values);
    }

    //Delete course Attende while untick
    public boolean deleteCourseAttendee(Long rowID, Long courseID){

        SQLiteDatabase db = getWritableDatabase();

        return db.delete(SQLITE_TABLE_ATTENDEES_COURSES, MST_ROWID + "=" + String.valueOf(rowID) + " AND " + STU_LS1_CRS_ID + "=" + String.valueOf(courseID), null) > 0;

    }

    //Add Course Date
    public long addCourseDate(long courseID, String courseDate) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        Log.d("Date", courseDate);
        values.put(MST_ROWID, courseID);
        values.put(CRS_LS1_CRS_DATE, courseDate);
        values.put(AUDIT_USER, "daryl");
        values.put(AUDIT_DATE, dateFormat.format(new java.util.Date()));

        return db.insert(SQLITE_TABLE_COURSE_DATE, null, values);
    }

    //Add Master Attendee
    public boolean addAttendee(String attendeeName, String attendeeID, String attendeeEmail, String attendeeContact) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        //values.put(MST_ROWID, rowID);
        values.put(STU_MST_NAME, attendeeName);
        values.put(STU_MST_ID, attendeeID);
        values.put(STU_MST_EMAIL, attendeeEmail);
        values.put(STU_MST_CONTACT, attendeeContact);
        values.put(AUDIT_USER, "daryl");
        values.put(AUDIT_DATE, dateFormat.format(new java.util.Date()));

        if (db.insert(SQLITE_TABLE_ATTENDEES, null, values)>0){
            return true;
        }else{
            return false;
        }


    }

    public List<CourseDate> listCourseDate(long courseID) {

        List<CourseDate> list = new ArrayList<CourseDate>();

        SQLiteDatabase db = getWritableDatabase();

        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE_DATE;
        String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE_DATE + " WHERE " + MST_ROWID + " = " + String.valueOf(courseID);

        Log.e("Select Query", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new CourseDate(cursor.getString(cursor.getColumnIndex(CRS_LS1_CRS_DATE)),
                                        cursor.getLong(cursor.getColumnIndex(ROWID))));
            } while (cursor.moveToNext());
        }
        cursor.close();

        Log.e("Check Size", String.valueOf(list.size()));

        return list;
    }

    public List<Course> listAllCourse(){
        List<Course> list = new ArrayList<Course>();

        SQLiteDatabase db = getWritableDatabase();

        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE_DATE;
        String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE;

        Log.e("Select Query", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new Course(cursor.getString(cursor.getColumnIndex(CRS_MST_CRS_NAME)),
                                    cursor.getString(cursor.getColumnIndex(CRS_MST_CON_NAME)),
                                    cursor.getString(cursor.getColumnIndex(CRS_MST_CRS_STS)),
                                    cursor.getLong(cursor.getColumnIndex(ROWID))));
            } while (cursor.moveToNext());
        }
        cursor.close();

        Log.e("Check Size", String.valueOf(list.size()));

        return list;
    };

    //List all Master Attendee
    public List<Attendee> listAllAttendees(){
        List<Attendee> list = new ArrayList<Attendee>();

        SQLiteDatabase db = getWritableDatabase();

        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE_DATE;
        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_ATTENDEES + " WHERE " + MST_ROWID + " = " + String.valueOf(courseID);
        String selectQuery = "SELECT * FROM " + SQLITE_TABLE_ATTENDEES;

        Log.e("Select Query", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                list.add(new Attendee(cursor.getString(cursor.getColumnIndex(STU_MST_NAME)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_ID)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_CONTACT)),
                        cursor.getLong(cursor.getColumnIndex(ROWID))));
            } while (cursor.moveToNext());
        }
        cursor.close();

        Log.e("Check Size for Attendee", String.valueOf(list.size()));

        return list;
    };

    public List<Attendee> listAllCourseAttendees(Long courseID){
        List<Attendee> list = new ArrayList<Attendee>();

        SQLiteDatabase db = getWritableDatabase();

        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE_DATE;
        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_ATTENDEES + " WHERE " + MST_ROWID + " = " + String.valueOf(courseID);
        String selectQuery = "SELECT * FROM " + SQLITE_TABLE_ATTENDEES + ", " + SQLITE_TABLE_ATTENDEES_COURSES + " WHERE " + SQLITE_TABLE_ATTENDEES + "." + ROWID + "=" + SQLITE_TABLE_ATTENDEES_COURSES +"." + MST_ROWID + " AND " + STU_LS1_CRS_ID + " = " + String.valueOf(courseID);

        Log.e("Select Query", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                list.add(new Attendee(cursor.getString(cursor.getColumnIndex(STU_MST_NAME)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_ID)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_CONTACT)),
                        cursor.getLong(cursor.getColumnIndex(ROWID))));
            } while (cursor.moveToNext());
        }
        cursor.close();

        Log.e("Check Size for Attendee", String.valueOf(list.size()));

        return list;
    };

    public boolean deleteCourseDate(Long rowID){

        SQLiteDatabase db = getWritableDatabase();

        return db.delete(SQLITE_TABLE_COURSE_DATE, ROWID + "=" + String.valueOf(rowID), null) > 0;

    }

    public boolean deleteAttendee(Long rowID){

        SQLiteDatabase db = getWritableDatabase();

        return db.delete(SQLITE_TABLE_ATTENDEES, ROWID + "=" + String.valueOf(rowID), null) > 0;

    }

    public List<Course> getCourse(Long rowID){
        List<Course> list = new ArrayList<Course>();

        SQLiteDatabase db = getWritableDatabase();

        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE_DATE;
        String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE + " WHERE " + ROWID + " = " + String.valueOf(rowID);

        Log.e("Select getCourse", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new Course(cursor.getString(cursor.getColumnIndex(CRS_MST_CRS_NAME)),
                        cursor.getString(cursor.getColumnIndex(CRS_MST_CON_NAME)),
                        cursor.getString(cursor.getColumnIndex(CRS_MST_CRS_STS)),
                        cursor.getLong(cursor.getColumnIndex(ROWID))));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;

    }

    public List<Attendee> getAttendee(Long rowID){
        List<Attendee> list = new ArrayList<Attendee>();

        SQLiteDatabase db = getWritableDatabase();

        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE_DATE;
        String selectQuery = "SELECT * FROM " + SQLITE_TABLE_ATTENDEES + " WHERE " + ROWID + " = " + String.valueOf(rowID);

        Log.e("Select getCourse", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new Attendee(cursor.getString(cursor.getColumnIndex(STU_MST_NAME)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_ID)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_CONTACT)),
                        cursor.getLong(cursor.getColumnIndex(ROWID))));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;

    }

    public boolean updateAttendeeInfo(Long rowID, String attendeeName, String attendeeEmail, String attendeeContact) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues initialValues = new ContentValues();

        initialValues.put(STU_MST_NAME, attendeeName);
        initialValues.put(STU_MST_EMAIL, attendeeEmail);
        initialValues.put(STU_MST_CONTACT, attendeeContact);

        if (db.update(SQLITE_TABLE_ATTENDEES, initialValues, ROWID + " = " + String.valueOf(rowID), null) > 0){
            return true;
        }else{
            return false;
        }

    }
}
