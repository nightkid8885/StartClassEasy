package com.cmms.codetech.startclasseasy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;

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
    private static final String SQLITE_TABLE_STUDENT_COURSES = "stu_ls1";
    private static final String SQLITE_TABLE_STUDENT_ATTENDANCE = "stu_ls2";
    private static final String SQLITE_TABLE_STUDENT = "stu_mst";

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
    public static final String CRS_LS2_FORM_ID = "crs_ls2_form_id";
    public static final String CRS_LS2_STS = "crs_ls2_sts";
    public static final String CRS_LS2_ANS_1 = "crs_ls2_ans_1";
    public static final String CRS_LS2_ANS_2 = "crs_ls2_ans_2";
    public static final String CRS_LS2_ANS_3 = "crs_ls2_ans_3";
    public static final String CRS_LS2_ANS_4 = "crs_ls2_ans_4";
    public static final String CRS_LS2_ANS_5 = "crs_ls2_ans_5";
    public static final String CRS_LS2_ANS_6 = "crs_ls2_ans_6";
    public static final String CRS_LS2_ANS_7 = "crs_ls2_ans_7";
    public static final String CRS_LS2_ANS_8 = "crs_ls2_ans_8";
    public static final String CRS_LS2_ANS_9 = "crs_ls2_ans_9";
    public static final String CRS_LS2_STATUS = "crs_ls2_status";

    public static final String STU_LS1_CRS_ID = "stu_ls1_atd_course";
    public static final String STU_LS1_ATD_STS = "stu_ls1_atd_sts";
    public static final String STU_LS1_CRS_DATE = "stu_ls1_crs_date";

    public static final String STU_LS2_DATE_ID = "stu_ls2_date_id";
    public static final String STU_LS2_ATD_STS = "stu_ls2_atd_sts";
    public static final String STU_LS2_CRS_ID = "stu_ls2_crs_id";


    public static final String STU_MST_NAME = "stu_mst_name";
    public static final String STU_MST_ID = "stu_mst_id";
    public static final String STU_MST_GENDER = "stu_mst_gen";
    public static final String STU_MST_CONTACT = "stu_mst_tel";
    public static final String STU_MST_EMAIL = "stu_mst_email";

    public UserDatabase(Context context) {
        super(context, "UserDatabase", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE_CRS_MST);
        db.execSQL(DATABASE_CREATE_CRS_LS1);
        db.execSQL(DATABASE_CREATE_CRS_LS2);
        db.execSQL(DATABASE_CREATE_STU_LS1);
        db.execSQL(DATABASE_CREATE_STU_LS2);
        db.execSQL(DATABASE_CREATE_STU_MST);

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

    private static final String DATABASE_CREATE_STU_LS1 = "CREATE TABLE if not exists "
            + SQLITE_TABLE_STUDENT_COURSES
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

    private static final String DATABASE_CREATE_STU_LS2 = "CREATE TABLE if not exists "
            + SQLITE_TABLE_STUDENT_ATTENDANCE
            + " ("
            + MST_ROWID
            + " integer NOT NULL,"
            + STU_LS2_DATE_ID
            + " integer,"
            + STU_LS2_CRS_ID
            + " integer,"
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
            + CRS_LS2_FORM_ID
            + " TEXT,"
            + CRS_LS2_STS
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
            + CRS_LS2_STATUS
            + " TEXT,"
            + AUDIT_USER
            + " TEXT,"
            + AUDIT_DATE
            + " ,"
            + ROWID
            + " integer PRIMARY KEY autoincrement)";

    private static final String DATABASE_CREATE_STU_MST = "CREATE TABLE if not exists "
            + SQLITE_TABLE_STUDENT
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
            + " integer PRIMARY KEY autoincrement"
            + ", "
            + " UNIQUE (" + STU_MST_ID + "));";

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
    public boolean addCourseAttendee(Long stuID, Long courseID) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MST_ROWID, stuID);
        values.put(STU_LS1_CRS_ID, courseID);
        values.put(STU_LS1_ATD_STS, "0");
        //values.put(ATD_LS1_CRS_DATE, courseDate);
        values.put(AUDIT_USER, "daryl");
        values.put(AUDIT_DATE, dateFormat.format(new java.util.Date()));

        if (db.insert(SQLITE_TABLE_STUDENT_COURSES, null, values)>0){
            return true;
        }else{
            return false;
        }

    }

    //Add Course Attendee Feedback form
    public long addCourseAttendeeFeedback(Long stuID, Long courseID) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MST_ROWID, courseID);
        values.put(CRS_LS2_STU_ID, stuID);
        values.put(AUDIT_USER, "daryl");
        values.put(AUDIT_DATE, dateFormat.format(new java.util.Date()));

        return db.insert(SQLITE_TABLE_COURSE_FEEDBACK, null, values);
    }

    //Delete course Attende while untick
    public boolean deleteCourseAttendee(Long rowID, Long courseID){

        SQLiteDatabase db = getWritableDatabase();

        return db.delete(SQLITE_TABLE_STUDENT_COURSES, MST_ROWID + "=" + String.valueOf(rowID) + " AND " + STU_LS1_CRS_ID + "=" + String.valueOf(courseID), null) > 0;

    }

    public boolean deleteCourseAttendeeFeedback(Long stuID, Long courseID){

        SQLiteDatabase db = getWritableDatabase();

        return db.delete(SQLITE_TABLE_COURSE_FEEDBACK, MST_ROWID + "=" + String.valueOf(courseID) + " AND " + CRS_LS2_STU_ID + "=" + String.valueOf(stuID), null) > 0;

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

        if (db.insert(SQLITE_TABLE_STUDENT, null, values)>0){
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

        return list;
    }

    //Admin Module List All Course
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

    //Calendar Module List All Course
    public List<Course> listStudentAllCourse(){
        List<Course> list = new ArrayList<Course>();

        SQLiteDatabase db = getWritableDatabase();

        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE_DATE;
        String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE + "," + SQLITE_TABLE_STUDENT_COURSES
                + " WHERE " + SQLITE_TABLE_COURSE + "." + ROWID + "=" + SQLITE_TABLE_STUDENT_COURSES+ "." + MST_ROWID
                + " AND " + SQLITE_TABLE_STUDENT_COURSES + "." + MST_ROWID + "=1";

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

    //Calendar Module List All Date By Course
    public List<Course> listStudentCourseCalendar(Long courseID){
        List<Course> list = new ArrayList<Course>();

        SQLiteDatabase db = getWritableDatabase();

        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE_DATE;
        String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE + "," + SQLITE_TABLE_COURSE_DATE
                + " WHERE " + SQLITE_TABLE_COURSE + "." + ROWID + "=" + SQLITE_TABLE_COURSE_DATE+ "." + MST_ROWID
                + " AND " + SQLITE_TABLE_COURSE + "." + ROWID + "=" + String.valueOf(courseID);

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

    //List all Master Student for choose for course
    public List<Attendee> listAllStudentsByCourse(Long courseID){
        List<Attendee> list = new ArrayList<Attendee>();

        SQLiteDatabase db = getWritableDatabase();

        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE_DATE;
        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_ATTENDEES + " WHERE " + MST_ROWID + " = " + String.valueOf(courseID);
        String selectQuery = "SELECT " + STU_MST_NAME + ", " + STU_MST_ID + ", " + STU_MST_EMAIL + ", " + STU_MST_CONTACT + ", "
                + SQLITE_TABLE_STUDENT + "." + ROWID + ", " + "0"
                + " FROM " + SQLITE_TABLE_STUDENT + " WHERE " + ROWID
                + " NOT IN (SELECT " + MST_ROWID + " FROM " + SQLITE_TABLE_STUDENT_COURSES + " WHERE "
                + STU_LS1_CRS_ID + " = " + String.valueOf(courseID) + ")"
                + " UNION ALL "
                + "SELECT " + STU_MST_NAME + ", " + STU_MST_ID + ", " + STU_MST_EMAIL + ", " + STU_MST_CONTACT + ", "
                + SQLITE_TABLE_STUDENT + "." + ROWID + ", " + "1"
                + " FROM " + SQLITE_TABLE_STUDENT + ", " + SQLITE_TABLE_STUDENT_COURSES + " WHERE "
                + SQLITE_TABLE_STUDENT + "." + ROWID + " = " + SQLITE_TABLE_STUDENT_COURSES + "." + MST_ROWID + " AND "
                + STU_LS1_CRS_ID + " = " + String.valueOf(courseID);

        Log.e("listAllStudentsByCourse", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                list.add(new Attendee(cursor.getString(cursor.getColumnIndex(STU_MST_NAME)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_ID)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_CONTACT)),
                        cursor.getLong(4),
                        cursor.getLong(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        Log.e("Check Size for Attendee", String.valueOf(list.size()));

        return list;
    };

    //StudentListActivity
    public List<Attendee> listAllStudents(){
        List<Attendee> list = new ArrayList<Attendee>();

        SQLiteDatabase db = getWritableDatabase();

        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE_DATE;
        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_ATTENDEES + " WHERE " + MST_ROWID + " = " + String.valueOf(courseID);
        String selectQuery = "SELECT " + STU_MST_NAME + ", " + STU_MST_ID + ", " + STU_MST_EMAIL + ", " + STU_MST_CONTACT + ", "
                + SQLITE_TABLE_STUDENT + "." + ROWID + ", " + "0"
                + " FROM " + SQLITE_TABLE_STUDENT + " WHERE " + ROWID
                + " NOT IN (SELECT " + MST_ROWID + " FROM " + SQLITE_TABLE_STUDENT_COURSES + ")"
                + " UNION ALL "
                + "SELECT " + STU_MST_NAME + ", " + STU_MST_ID + ", " + STU_MST_EMAIL + ", " + STU_MST_CONTACT + ", "
                + SQLITE_TABLE_STUDENT + "." + ROWID + ", " + "1"
                + " FROM " + SQLITE_TABLE_STUDENT + ", " + SQLITE_TABLE_STUDENT_COURSES + " WHERE "
                + SQLITE_TABLE_STUDENT + "." + ROWID + " = " + SQLITE_TABLE_STUDENT_COURSES + "." + MST_ROWID;

        Log.e("listAllStudents", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                list.add(new Attendee(cursor.getString(cursor.getColumnIndex(STU_MST_NAME)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_ID)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_CONTACT)),
                        cursor.getLong(4),
                        cursor.getLong(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        Log.e("Check Size for Attendee", String.valueOf(list.size()));

        return list;
    };

    //STU_MST, STU_LS1
    public List<Attendee> listAllCourseAttendees(Long courseID, Long dateID){
        List<Attendee> list = new ArrayList<Attendee>();

        SQLiteDatabase db = getWritableDatabase();

        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE_DATE;
        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_ATTENDEES + " WHERE " + MST_ROWID + " = " + String.valueOf(courseID);
        String selectQuery = "SELECT " + STU_MST_NAME + "," + STU_MST_ID + ", " + STU_MST_EMAIL + ", " + STU_MST_CONTACT + ","
                + SQLITE_TABLE_STUDENT + "." + ROWID + ", 0"
                + " FROM " + SQLITE_TABLE_STUDENT + ", " + SQLITE_TABLE_STUDENT_COURSES + " WHERE "
                + SQLITE_TABLE_STUDENT + "." + ROWID + "=" + SQLITE_TABLE_STUDENT_COURSES +"." + MST_ROWID + " AND "
                + STU_LS1_CRS_ID + " = " + String.valueOf(courseID) + " AND " + SQLITE_TABLE_STUDENT + "." + ROWID + " NOT IN "
                + "(SELECT " + MST_ROWID + " FROM " + SQLITE_TABLE_STUDENT_ATTENDANCE + " WHERE " + STU_LS2_DATE_ID + "=" + String.valueOf(dateID) + ")"
                + " UNION ALL "
                + "SELECT " + STU_MST_NAME + "," + STU_MST_ID + ", " + STU_MST_EMAIL + ", " + STU_MST_CONTACT + ","
                + SQLITE_TABLE_STUDENT + "." + ROWID + ", 1"
                + " FROM " + SQLITE_TABLE_STUDENT + ", " + SQLITE_TABLE_STUDENT_ATTENDANCE + " WHERE "
                + SQLITE_TABLE_STUDENT + "." + ROWID + "=" + SQLITE_TABLE_STUDENT_ATTENDANCE +"." + MST_ROWID + " AND "
                + STU_LS2_CRS_ID + " = " + String.valueOf(courseID) + " AND " + STU_LS2_DATE_ID + "=" + String.valueOf(dateID);

        Log.e("Select Query", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                list.add(new Attendee(cursor.getString(cursor.getColumnIndex(STU_MST_NAME)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_ID)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(STU_MST_CONTACT)),
                        cursor.getLong(4),
                        cursor.getLong(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;
    };

    public boolean deleteCourseDate(Long rowID){

        SQLiteDatabase db = getWritableDatabase();

        return db.delete(SQLITE_TABLE_COURSE_DATE, ROWID + "=" + String.valueOf(rowID), null) > 0;

    }

    public boolean deleteAttendee(Long rowID){

        SQLiteDatabase db = getWritableDatabase();

        return db.delete(SQLITE_TABLE_STUDENT, ROWID + "=" + String.valueOf(rowID), null) > 0;

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
        String selectQuery = "SELECT * FROM " + SQLITE_TABLE_STUDENT + " WHERE " + ROWID + " = " + String.valueOf(rowID);

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

        if (db.update(SQLITE_TABLE_STUDENT, initialValues, ROWID + " = " + String.valueOf(rowID), null) > 0){
            return true;
        }else{
            return false;
        }

    }

    //Update Feedback form
    public boolean updateAttendeeFeedback(Long courseID, String rememberKeyPtsRb, String understandReexplainRb,
                                          String confidentTransferRb, String instructorPreparedRb, String instructorDeliveryRb, String instructorEngagementRb,
                                          String workedWellFeedback, String improvementFeedback, String overallFeedback, String status) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues initialValues = new ContentValues();

        initialValues.put(CRS_LS2_ANS_1, rememberKeyPtsRb);
        initialValues.put(CRS_LS2_ANS_2, understandReexplainRb);
        initialValues.put(CRS_LS2_ANS_3, confidentTransferRb);
        initialValues.put(CRS_LS2_ANS_4, instructorPreparedRb);
        initialValues.put(CRS_LS2_ANS_5, instructorDeliveryRb);
        initialValues.put(CRS_LS2_ANS_6, instructorEngagementRb);
        initialValues.put(CRS_LS2_ANS_7, workedWellFeedback);
        initialValues.put(CRS_LS2_ANS_8, improvementFeedback);
        initialValues.put(CRS_LS2_ANS_9, overallFeedback);
        initialValues.put(CRS_LS2_STATUS, status);

        Log.e("Select feedback", "SELECT * FROM " + SQLITE_TABLE_COURSE_FEEDBACK + " WHERE " + MST_ROWID + " = " + String.valueOf(courseID) + " AND " + CRS_LS2_STATUS + " = 'True' ");
        Log.e("Select feedback count", String.valueOf(db.rawQuery("SELECT * FROM " + SQLITE_TABLE_COURSE_FEEDBACK + " WHERE " + MST_ROWID + " = " + String.valueOf(courseID) + " AND " + CRS_LS2_STATUS + " = 'True' ", null).getCount()));
        if(db.rawQuery("SELECT * FROM " + SQLITE_TABLE_COURSE_FEEDBACK + " WHERE " + MST_ROWID + " = " + String.valueOf(courseID) + " AND " + CRS_LS2_STATUS + " = 'True' ", null).getCount() == 0) {
        //        if (db.update(SQLITE_TABLE_COURSE_FEEDBACK, initialValues, CRS_LS2_STU_ID + " = " + String.valueOf(stuID) + " AND " + MST_ROWID +   "= " + String.valueOf(courseID), null) > 0){
            if (db.update(SQLITE_TABLE_COURSE_FEEDBACK, initialValues, CRS_LS2_STU_ID + " = 1" + " AND " + MST_ROWID +   "= " + String.valueOf(courseID), null) > 0) {

                return true;
            } else {

                return false;
            }
        } else {
            return false;
        }

    }

    //To prevent user from doing the same feedback again - user can only do once
    public List<Course> listStudentFeedbackAllCourse(){
        List<Course> list = new ArrayList<Course>();

        SQLiteDatabase db = getWritableDatabase();

        //String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE_DATE;
        String selectQuery = "SELECT * FROM " + SQLITE_TABLE_COURSE + "," + SQLITE_TABLE_STUDENT_COURSES + "," + SQLITE_TABLE_COURSE_FEEDBACK
                + " WHERE " + SQLITE_TABLE_COURSE + "." + ROWID + "=" + SQLITE_TABLE_STUDENT_COURSES+ "." + MST_ROWID
                + " AND " + SQLITE_TABLE_STUDENT_COURSES + "." + MST_ROWID + "=1" + " AND " + SQLITE_TABLE_COURSE_FEEDBACK + "." + CRS_LS2_STATUS + " is NULL ";

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

    //INSERT INTO STU_LS2
    public boolean addCourseAttendance(Long dateID, Long courseID, Long stuID) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MST_ROWID, stuID);
        values.put(STU_LS2_DATE_ID, dateID);
        values.put(STU_LS2_CRS_ID, courseID);
        values.put(STU_LS2_ATD_STS, "1");
        values.put(AUDIT_USER, "daryl");
        values.put(AUDIT_DATE, dateFormat.format(new java.util.Date()));

        if (db.insert(SQLITE_TABLE_STUDENT_ATTENDANCE, null, values) > 0) {
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }
}