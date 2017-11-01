package framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data.ContactDataSource;
import framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data.model.Contact;

/**
 * Created by thanhthi on 31/10/2017.
 */

public class ContactLocalDataSource extends DatabaseHelper implements ContactDataSource {

    private static ContactDataSource sSource;

    public static ContactDataSource getInstance(Context context) {
        if (sSource == null) {
            sSource = new ContactLocalDataSource(context);
        }
        return sSource;
    }

    public ContactLocalDataSource(Context context) {
        super(context);
    }

    @Override
    public boolean insertContact(Contact contact) {
        if (contact == null) return false;
        SQLiteDatabase db = getWritableDatabase();

        long result = db.insert(
                ContactContract.ContactEntry.TABLE_NAME,
                null,
                contact.getContentValues());

        return result != -1;
    }

    @Override
    public boolean updateContact(Contact contact) {
        if (contact == null) return false;
        SQLiteDatabase db = getWritableDatabase();

        String whereClause = ContactContract.ContactEntry._ID + " =?";
        String[] whereArgs = {String.valueOf(contact.getId())};

        long result = db.update(ContactContract.ContactEntry.TABLE_NAME,
                contact.getContentValues(), whereClause, whereArgs);
        return result != -1;
    }

    @Override
    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        //Định nghĩa một phép chiếu (projection) xác định các cột từ csdl
        //bạn sẽ thực sự sử dụng projection này trong truy vấn sau đó
        String[] projection = {
                ContactContract.ContactEntry._ID,
                ContactContract.ContactEntry.COLUMN_NAME,
                ContactContract.ContactEntry.COLUMN_PHONE_NUMBER,
                ContactContract.ContactEntry.COLUMN_ADDRESS
        };

        Cursor cursor = db.query(
                ContactContract.ContactEntry.TABLE_NAME, //bảng để truy vấn
                projection,                //các cột để trả về
                null,            //các cột cho mệnh đề WHERE
                null,        //các giá trị cho mệnh đề WHERE
                null,            //không group các hàng
                null,             //không lọc bởi group hàng
                null);           //không sắp thứ tự theo order

        if (cursor != null && cursor.moveToFirst()) {
            contacts = new ArrayList<>();
            do {
                contacts.add(new Contact(cursor));
            } while (cursor.moveToNext());
        }

        if (cursor != null) cursor.close();
        db.close();

        return contacts;
    }

    @Override
    public Contact getContactById(int id) {
        Contact contact = null;
        SQLiteDatabase db = getReadableDatabase();

        //Định nghĩa một phép chiếu (projection) xác định các cột từ csdl
        //bạn sẽ thực sự sử dụng projection này trong truy vấn sau đó
        String[] projection = {
                ContactContract.ContactEntry._ID,
                ContactContract.ContactEntry.COLUMN_NAME,
                ContactContract.ContactEntry.COLUMN_PHONE_NUMBER,
                ContactContract.ContactEntry.COLUMN_ADDRESS
        };

        String selection = ContactContract.ContactEntry._ID + " =?";
        String[] selectionArgs = { String.valueOf(id) };

        Cursor cursor = db.query(
                ContactContract.ContactEntry.TABLE_NAME, //bảng để truy vấn
                projection,            //các cột để trả về
                selection,             //các cột cho mệnh đề WHERE
                selectionArgs,         //các giá trị cho mệnh đề WHERE
                null,        //không group các hàng
                null,          //không lọc bởi group hàng
                null);        //không sắp thứ tự theo order

        if (cursor != null && cursor.moveToFirst()) {
            contact = new Contact(cursor);
        }

        if (cursor != null) cursor.close();
        db.close();

        return contact;
    }

    @Override
    public List<Contact> getContactByName(String name) {
        List<Contact> contacts = null;
        SQLiteDatabase db = getReadableDatabase();

        //Định nghĩa một phép chiếu (projection) xác định các cột từ csdl
        //bạn sẽ thực sự sử dụng projection này trong truy vấn sau đó
        String[] projection = {
                ContactContract.ContactEntry._ID,
                ContactContract.ContactEntry.COLUMN_NAME,
                ContactContract.ContactEntry.COLUMN_PHONE_NUMBER,
                ContactContract.ContactEntry.COLUMN_ADDRESS
        };

        String selection = ContactContract.ContactEntry.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = { name };

        Cursor cursor = db.query(
                ContactContract.ContactEntry.TABLE_NAME, //bảng để truy vấn
                projection,            //các cột để trả về
                selection,             //các cột cho mệnh đề WHERE
                selectionArgs,         //các giá trị cho mệnh đề WHERE
                null,        //không group các hàng
                null,          //không lọc bởi group hàng
                null);        //không sắp thứ tự theo order

        if (cursor != null && cursor.moveToFirst()) {
            contacts = new ArrayList<>();
            do {
                contacts.add(new Contact(cursor));
            } while (cursor.moveToNext());
        }

        if (cursor != null) cursor.close();
        db.close();

        return contacts;
    }
}
