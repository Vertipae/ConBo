package android.example.com.conbo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;
import java.util.Random;

/**
 * Created by Salinaattori on 9.5.2018.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    Context mContext;
    ContactHelper mData;


    public ContactAdapter(Context mContext) {
        this.mContext = mContext;
        this.mData = ContactHelper.getInstance(mContext);
    }
    // Initialize and inflate all the contact items to the contacts view
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.contact_item, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        // Set onClickListener to listen for clicks on single item
        vHolder.contact_item.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ContactActivity.class);
                intent.putExtra("id", vHolder.getmId());
                intent.putExtra("name", vHolder.tv_name.getText());
                intent.putExtra("phone", vHolder.tv_phone.getText());
                intent.putExtra("email", vHolder.getmEmail());
                intent.putExtra("address", vHolder.getmAddress());
                mContext.startActivity(intent);


            }
        });
        return vHolder;
    }
    // Fetch the data from database to be used in contact item in the contacts view
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // Execute the query to fetch single item data
        ContactModel current = mData.query(position);
        // Set the data to variables and text fields
        holder.setmId(current.getmId());
        holder.setmPhoto(current.getmPhoto());
        holder.tv_name.setText(current.getmName());
        holder.tv_phone.setText(current.getmPhone());
        holder.setmEmail(current.getmEmail());
        holder.setmAddress(current.getmAddress());
        // Generate icon according to first letter of name
        TextDrawable letterDrawable = TextDrawable.builder().buildRound(String.valueOf(current.getmName().charAt(0)), holder.getmPhoto());
        holder.img.setImageDrawable(letterDrawable);



    }
    // Count the entries in database
    @Override
    public int getItemCount() {
        return ((int) mData.count());
    }

    // Custom holder for recyclerviewholder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout contact_item;
        private TextView tv_name;
        private TextView tv_phone;
        private ImageView img;
        private int mId;
        private int mPhoto;
        private String mEmail;
        private String mAddress;

        public MyViewHolder(View itemView) {

            super(itemView);

            contact_item = (LinearLayout) itemView.findViewById(R.id.contact_item_id);
            tv_name = (TextView) itemView.findViewById(R.id.contact_name);
            tv_phone = (TextView) itemView.findViewById(R.id.contact_phone);
            img = (ImageView) itemView.findViewById(R.id.img_contact);
        }

        public int getmId() {
            return mId;
        }

        public void setmId(int mId) {
            this.mId = mId;
        }

        public int getmPhoto() {
            return mPhoto;
        }

        public void setmPhoto(int mPhoto) {
            this.mPhoto = mPhoto;
        }

        public String getmEmail() {
            return mEmail;
        }

        public void setmEmail(String mEmail) {
            this.mEmail = mEmail;
        }

        public String getmAddress() {
            return mAddress;
        }

        public void setmAddress(String mAddress) {
            this.mAddress = mAddress;
        }
    }
}
