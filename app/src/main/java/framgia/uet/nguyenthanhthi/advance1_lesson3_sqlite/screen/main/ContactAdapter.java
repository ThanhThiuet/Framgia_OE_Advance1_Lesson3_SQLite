package framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.screen.main;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.R;
import framgia.uet.nguyenthanhthi.advance1_lesson3_sqlite.data.model.Contact;

/**
 * Created by thanhthi on 01/11/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> mContacts;
    private LayoutInflater mInflater;

    public ContactAdapter(List<Contact> contacts) {
        mContacts = contacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        View view = mInflater.inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mContacts.get(position));
    }

    @Override
    public int getItemCount() {
        return mContacts != null ? mContacts.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        private ImageView mImageAvatar;
        private TextView mTextName;
        private TextView mTextPhone;
        private TextView mTextAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageAvatar = itemView.findViewById(R.id.image_avatar);
            mTextName = itemView.findViewById(R.id.text_name);
            mTextPhone = itemView.findViewById(R.id.text_phone);
            mTextAddress = itemView.findViewById(R.id.text_address);

            itemView.setOnLongClickListener(this);
        }

        public void bindData(Contact contact) {
            if (contact == null) return;
            mImageAvatar.setImageResource(contact.getAvatar());
            mTextName.setText(contact.getName());
            mTextPhone.setText(contact.getPhone());
            mTextAddress.setText(contact.getAddress());
        }


        @Override
        public boolean onLongClick(final View v) {
            //hàm bắt sự kiện long click trên item view để thực hiện xoá contact
            //show confirm dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Delete")
                    .setIcon(R.mipmap.ic_dialog_delete)
                    .setMessage("Do you want to delete this contact?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mContacts.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            Toast.makeText(v.getContext(), "Delete contact successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show();
            return false;
        }
    }
}
