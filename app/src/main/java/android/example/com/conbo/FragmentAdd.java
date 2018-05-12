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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sallamaarit Jaako 1601459 on 9.5.2018.
 */

public class FragmentAdd extends Fragment{

    View vAdd;
    Button mButton;
    ContactHelper mData;
    TextView mName;
    TextView mPhone;
    TextView mEmail;
    TextView mAddress;

    public FragmentAdd() {
    }
    // Inflate the view to screen and initialize variables
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mData = ContactHelper.getInstance(getContext());
        vAdd = inflater.inflate(R.layout.add_fragment, container,false);
        mButton = vAdd.findViewById(R.id.add_button);
        mName = vAdd.findViewById(R.id.item_name_add);
        mPhone = vAdd.findViewById(R.id.item_phone_add);
        mEmail = vAdd.findViewById(R.id.item_email_add);
        mAddress = vAdd.findViewById(R.id.item_address_add);
        // Set on onClickListener for save button
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize validator
                Validator validator = new Validator();
                // Initialize and enter values to list
                List<String> values = new ArrayList<>();
                values.add(mName.getText().toString());
                values.add(mPhone.getText().toString());
                values.add(mEmail.getText().toString());
                values.add(mAddress.getText().toString());
                // Validate values and return if empty fields
                if (!validator.validate(values)) {
                    Toast.makeText(getContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show();
                }else {
                    mData.insert(mName.getText().toString(), mPhone.getText().toString(), mEmail.getText().toString(), mAddress.getText().toString());
                    Intent intent = new Intent(vAdd.getContext(), MainActivity.class);
                    Toast.makeText(vAdd.getContext(), "Contact added successfully", Toast.LENGTH_SHORT).show();
                    vAdd.getContext().startActivity(intent);
                }
            }
        });
        return vAdd;
    }

}
