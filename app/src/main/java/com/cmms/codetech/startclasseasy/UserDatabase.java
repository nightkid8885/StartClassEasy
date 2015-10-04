package com.cmms.codetech.startclasseasy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Esu on 10/4/15.
 */
public class UserDatabase extends SQLiteOpenHelper {

    private static final String SQLITE_TABLE_COURSE = "crs_mst";
    private static final String SQLITE_TABLE_COURSE_DATE = "crs_ls1";
    private static final String SQLITE_TABLE_ATTENDEES = "crs_ls2";

    public static final String ROWID = "rowid";
    public static final String MST_ROWID = "mst_rowid";
    public static final String AUDIT_USER = "audit_user";
    public static final String AUDIT_DATE = "audit_date";

    public static final String CRS_MST_CRS_NAME = "crs_mst_crs_name";
    public static final String CRS_MST_CON_NAME = "crs_mst_con_name";

    public static final String CRS_LS1_CRS_DATE = "crs_ls1_crs_date";

    public static final String CRS_LS2_ATD_NAME = "crs_ls2_atd_name";
    public static final String CRS_LS2_ATD_ID = "crs_ls2_atd_id";
    public static final String CRS_LS2_ATD_GENDER = "crs_ls2_atd_gen";
    public static final String CRS_LS2_ATD_CONTACT = "crs_ls2_atd_tel";
    public static final String CRS_LS2_ATD_EMAIL = "crs_ls2_atd_email";


    public UserDatabase(Context context) {
        super(context, "UserDatabase", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE_CRS_MST);
        db.execSQL(SQLITE_TABLE_CRS_LS1);
        db.execSQL(SQLITE_TABLE_CRS_LS2);

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
            + AUDIT_USER
            + " TEXT,"
            + AUDIT_DATE
            + " DATETIME";

    private static final String SQLITE_TABLE_CRS_LS1 = "CREATE TABLE if not exists "
            + SQLITE_TABLE_COURSE_DATE
            + " ("
            + MST_ROWID
            + " integer NOT NULL,"
            + CRS_LS1_CRS_DATE
            + " DATE,"
            + AUDIT_USER
            + " TEXT,"
            + AUDIT_DATE
            + " DATETIME,"
            + ROWID
            + " integer PRIMARY KEY autoincrement";

    private static final String SQLITE_TABLE_CRS_LS2 = "CREATE TABLE if not exists "
            + SQLITE_TABLE_ATTENDEES
            + " ("
            + MST_ROWID
            + " integer NOT NULL,"
            + CRS_LS2_ATD_NAME
            + " TEXT,"
            + CRS_LS2_ATD_ID
            + " TEXT,"
            + CRS_LS2_ATD_GENDER
            + " TEXT,"
            + CRS_LS2_ATD_CONTACT
            + " TEXT,"
            + CRS_LS2_ATD_EMAIL
            + " TEXT,"
            + AUDIT_USER
            + " TEXT,"
            + AUDIT_DATE
            + " DATETIME,"
            + ROWID
            + " integer PRIMARY KEY autoincrement";
}
