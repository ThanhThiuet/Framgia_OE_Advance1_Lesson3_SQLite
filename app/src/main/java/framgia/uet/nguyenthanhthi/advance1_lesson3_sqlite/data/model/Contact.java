package framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data.model;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Random;

import framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.R;
import framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data.local.ContactContract;

/**
 * Created by thanhthi on 31/10/2017.
 */

public class Contact {

    private int mId;
    private int mAvatar;
    private String mName;
    private String mPhone;
    private String mAddress;

    public Contact(String name, String phone, String address) {
        mName = name;
        mPhone = phone;
        mAddress = address;
    }

    public Contact(int avatar, String name, String phone, String address) {
        mAvatar = avatar;
        mName = name;
        mPhone = phone;
        mAddress = address;
    }

    public Contact(Cursor cursor) {
        mId = cursor.getInt(cursor.getColumnIndex(ContactContract.ContactEntry._ID));
        mName = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_NAME));
        mPhone = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_PHONE_NUMBER));
        mAddress = cursor.getString(
                cursor.getColumnIndex(ContactContract.ContactEntry.COLUMN_ADDRESS));
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getAvatar() {
        return mAvatar;
    }

    public void setAvatar(int avatar) {
        mAvatar = avatar;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        if (mName != null) {
            contentValues.put(ContactContract.ContactEntry.COLUMN_NAME, mName);
        }
        if (mPhone != null) {
            contentValues.put(ContactContract.ContactEntry.COLUMN_PHONE_NUMBER, mPhone);
        }
        if (mAddress != null) {
            contentValues.put(ContactContract.ContactEntry.COLUMN_ADDRESS, mAddress);
        }
        if (mId != 0) {
            contentValues.put(ContactContract.ContactEntry._ID, mId);
        }
        return contentValues;
    }

    public static Contact getNewInstance() {
        //hàm tự động tạo đối tượng contact mới
        Random rd = new Random();
        int rdContact = rd.nextInt(100) + 1;
        if (rdContact % 2 == 1) {
            return new Contact(R.drawable.image_avatar_boy, "Contact " + rdContact,
                    "0123 456 7" + rd.nextInt(10) + rd.nextInt(10),
                    "Ha Noi");
        } else {
            return new Contact(R.drawable.image_avatar_girl, "Contact " + rdContact,
                    "0123 456 7" + rd.nextInt(10) + rd.nextInt(10),
                    "Ha Noi");
        }
    }
}
