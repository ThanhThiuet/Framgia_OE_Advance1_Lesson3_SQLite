package framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thanhthi on 31/10/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact.db";

    private static final String SQL_CREATE_CONTACTS = "CREATE TABLE \""
            + ContactContract.ContactEntry.TABLE_NAME + "\" (\""
            + ContactContract.ContactEntry._ID + "\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL  UNIQUE, \""
            + ContactContract.ContactEntry.COLUMN_NAME + "\" TEXT, \""
            + ContactContract.ContactEntry.COLUMN_PHONE_NUMBER + "\" TEXT, \""
            + ContactContract.ContactEntry.COLUMN_ADDRESS + "\" TEXT)";

    private static final String SQL_DELETE_CONTACTS = "DROP TABLE IF EXISTS \""
            + ContactContract.ContactEntry.TABLE_NAME + "\"";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_CONTACTS);
        db.execSQL(SQL_CREATE_CONTACTS);
    }
}
