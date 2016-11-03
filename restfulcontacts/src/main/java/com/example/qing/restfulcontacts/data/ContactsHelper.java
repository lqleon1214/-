package com.example.qing.restfulcontacts.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by qing on 16/9/9.
 */

public class ContactsHelper extends SQLiteOpenHelper {

    static final int VERSION = 2;

    static final String DB_FILE = "contacts.db";

    static final String TAB_CONTACTS = "contacts";

    //pk
    static final String COL_ID = "id";      // long

    // contact data
    public static final String COL_FNAME = "firstName"; // string
    public static final String COL_LNAME = "lastName";  // string
    public static final String COL_PHONE = "phone"; // string
    public static final String COL_EMAIL = "email"; //string

    // meta-data
    static final String COL_REMOTE_ID = "remoteId"; // string
    static final String COL_DELETE = "deleted";     // boolean
    static final String COL_DIRTY = "dirty";        // boolean
    static final String COL_SYNC = "sync";          // boolean


    public ContactsHelper(Context context) {
        super(context, DB_FILE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TAB_CONTACTS + "(" + COL_ID + " integer PRIMARY KEY AUTOINCREMENT,"
        + COL_FNAME + " text," + COL_LNAME + " text," + COL_PHONE + " text," + COL_EMAIL + " text," +
        COL_REMOTE_ID + " string UNIQUE," + COL_DELETE + " integer," + COL_DIRTY + " integer," + COL_SYNC
                + "string UNIQUE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try {
            db.execSQL("drop table " + TAB_CONTACTS);
        } catch (SQLiteException e) {
        }
        onCreate(db);
    }
}
