package android.example.com.conbo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sallamaarit Jaako 1601459 on 9.5.2018.
 */

public class FragmentContact extends Fragment{

    View vContact;
    private RecyclerView mRecyclerView;
    private ContactHelper mData;

    public FragmentContact() {
    }

    public void initializeContactHelper(Context context) {
        mData = new ContactHelper(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vContact = inflater.inflate(R.layout.contact_fragment, container, false);
        mRecyclerView = (RecyclerView) vContact.findViewById(R.id.contact_recyclerview);
        ContactAdapter recyclerAdapter = new ContactAdapter(getContext(), mData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(recyclerAdapter);
        return vContact;
    }
}
