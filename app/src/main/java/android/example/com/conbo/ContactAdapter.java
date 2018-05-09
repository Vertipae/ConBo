package android.example.com.conbo;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    public ContactAdapter(Context mContext, ContactHelper mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.contact_item, parent, false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }
// Generate one row for recyclerview
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ContactModel current = mData.query(position);
        holder.tv_name.setText(current.getmName());
        holder.tv_phone.setText(current.getmPhone());
        // Generate icon according to first letter of name
        TextDrawable letterDrawable = TextDrawable.builder().buildRound(String.valueOf(current.getmName().charAt(0)), ColorGenerator.MATERIAL.getRandomColor());
        holder.img.setImageDrawable(letterDrawable);
        // Keep a reference to the view holder for the click listener
        // final WordViewHolder h = holder; // needs to be final for use in callback

    }

    @Override
    public int getItemCount() {
        return ((int) mData.count());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_phone;
        private ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.contact_name);
            tv_phone = (TextView) itemView.findViewById(R.id.contact_phone);
            img = (ImageView) itemView.findViewById(R.id.img_contact);

        }
    }
}
