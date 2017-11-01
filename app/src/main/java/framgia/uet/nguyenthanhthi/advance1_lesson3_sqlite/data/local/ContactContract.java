package framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data.local;

import android.provider.BaseColumns;

/**
 * Created by thanhthi on 31/10/2017.
 */

public class ContactContract {

    public ContactContract() {

    }

    public static class ContactEntry implements BaseColumns {

        public static final String TABLE_NAME = "tbl_contact";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE_NUMBER = "phone";
        public static final String COLUMN_ADDRESS = "address";
    }
}
