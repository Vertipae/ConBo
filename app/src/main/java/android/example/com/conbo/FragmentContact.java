package android.example.com.conbo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Salinaattori on 9.5.2018.
 */

public class FragmentContact extends Fragment{

    View vContact;

    public FragmentContact() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vContact = inflater.inflate(R.layout.contact_fragment, container, false);
        return vContact;
    }
}
