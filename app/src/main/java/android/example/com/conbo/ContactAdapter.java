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
    //

    public ContactAdapter(Context mContext) {
        this.mContext = mContext;
        this.mData = ContactHelper.getInstance(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.contact_item, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);


        vHolder.contact_item.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ContactActivity.class);
                intent.putExtra("id", vHolder.getmId());
                intent.putExtra("name", vHolder.tv_name.getText());
                intent.putExtra("phone", vHolder.tv_phone.getText());
                mContext.startActivity(intent);


            }
        });
        return vHolder;
    }
// Generate one row for recyclerview
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ContactModel current = mData.query(position);
        holder.setmId(current.getmId());
        holder.setmPhoto(current.getmPhoto());
        holder.tv_name.setText(current.getmName());
        holder.tv_phone.setText(current.getmPhone());
        // Generate icon according to first letter of name
        TextDrawable letterDrawable = TextDrawable.builder().buildRound(String.valueOf(current.getmName().charAt(0)), holder.getmPhoto());
        holder.img.setImageDrawable(letterDrawable);
        // Keep a reference to the view holder for the click listener
        // final WordViewHolder h = holder; // needs to be final for use in callback

    }

    @Override
    public int getItemCount() {
        return ((int) mData.count());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //
        private LinearLayout contact_item;
        private TextView tv_name;
        private TextView tv_phone;
        private ImageView img;
        private int mId;
        private int mPhoto;

        public MyViewHolder(View itemView) {

            super(itemView);
            //
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
    }
}
