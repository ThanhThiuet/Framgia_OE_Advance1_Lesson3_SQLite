package framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data;

import java.util.List;

import framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data.model.Contact;

/**
 * Created by thanhthi on 31/10/2017.
 */

public interface ContactDataSource {

    boolean insertContact(Contact contact);
    boolean updateContact(Contact contact);

    List<Contact> getContacts();
    Contact getContactById(int id);
    List<Contact> getContactByName(String name);

}
