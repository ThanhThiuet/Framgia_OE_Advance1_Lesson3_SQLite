package framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.screen.main;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.R;
import framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data.ContactDataSource;
import framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data.ContactRepository;
import framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data.model.Contact;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerContacts;
    private ContactAdapter mAdapter;
    private List<Contact> mContacts;
    private ContactDataSource mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("List Contacts");

        getData();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Hàm khởi tạo Option Menu

        getMenuInflater().inflate(R.menu.contact_option_menu, menu);

        MenuItem menu_search = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menu_search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "Đang tìm kiếm...", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nameNeedFound) {
                List<Contact> resultSearch = new ArrayList<>();
                resultSearch = mRepository.getContactByName(nameNeedFound);

                //Vẽ lại giao diện RecyclerView Contacts với danh sách kết quả tìm kiếm
                mAdapter = new ContactAdapter(resultSearch);
                mRecyclerContacts.setAdapter(mAdapter);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //hàm control sự kiện khi click menu item trên Option Menu

        switch (item.getItemId()) {
            case R.id.menu_add:
                Contact contact = Contact.getNewInstance();
                insertContact(contact);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertContact(final Contact contact) {
        if (mRepository.insertContact(contact)) {
            mContacts.add(0, contact);
            mAdapter.notifyItemInserted(0);
            mRecyclerContacts.scrollToPosition(0);
            Toast.makeText(this, "Add contact successfully", Toast.LENGTH_SHORT).show();
        } else {
            Snackbar.make(mRecyclerContacts, "Add contact failed, please try again!", Snackbar.LENGTH_LONG)
                    .setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            insertContact(contact);
                        }
                    })
                    .show();
        }
    }

    private void getData() {
        mRepository = ContactRepository.getInstance(this);
        mContacts = mRepository.getContacts();
    }

    private void initView() {
        //hàm khởi tạo giao diện

        mRecyclerContacts = findViewById(R.id.recycler_contacts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerContacts.setLayoutManager(layoutManager);

        mAdapter = new ContactAdapter(mContacts);
        mRecyclerContacts.setAdapter(mAdapter);
    }
}
