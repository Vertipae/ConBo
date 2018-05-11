package android.example.com.conbo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Salinaattori on 9.5.2018.
 */

public class FragmentAdd extends Fragment{

    View vAdd;
    Button mButton;
    ContactHelper mData;
    TextView mName;
    TextView mPhone;

    public FragmentAdd() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mData = ContactHelper.getInstance(getContext());
        vAdd = inflater.inflate(R.layout.add_fragment, container,false);
        mButton = vAdd.findViewById(R.id.add_button);
        mName = vAdd.findViewById(R.id.item_name_add);
        mPhone = vAdd.findViewById(R.id.item_phone_add);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.insert(mName.getText().toString(), mPhone.getText().toString());
                Intent intent = new Intent(vAdd.getContext(), MainActivity.class);
                Toast.makeText(vAdd.getContext(),"Contact added successfully",Toast.LENGTH_SHORT).show();
                vAdd.getContext().startActivity(intent);
            }
        });
        return vAdd;
    }

}
