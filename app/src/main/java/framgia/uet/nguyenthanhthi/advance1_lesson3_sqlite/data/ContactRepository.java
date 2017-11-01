package framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data;

import android.content.Context;

import java.util.List;

import framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data.local.ContactLocalDataSource;
import framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data.model.Contact;

/**
 * Created by thanhthi on 31/10/2017.
 */

public class ContactRepository implements ContactDataSource {

    private static ContactRepository sRepository;
    private ContactDataSource mLocalDataSource;

    public ContactRepository(ContactDataSource localDataSource) {
        mLocalDataSource = localDataSource;
    }

    public static ContactRepository getInstance(Context context) {
        if (sRepository == null) {
            sRepository = new ContactRepository(ContactLocalDataSource.getInstance(context));
        }
        return sRepository;
    }

    @Override
    public boolean insertContact(Contact contact) {
        return mLocalDataSource.insertContact(contact);
    }

    @Override
    public boolean updateContact(Contact contact) {
        return mLocalDataSource.updateContact(contact);
    }

    @Override
    public Contact getContactById(int id) {
        return mLocalDataSource.getContactById(id);
    }

    @Override
    public List<Contact> getContactByName(String name) {
        return mLocalDataSource.getContactByName(name);
    }

    @Override
    public List<Contact> getContacts() {
        return mLocalDataSource.getContacts();
    }
}
