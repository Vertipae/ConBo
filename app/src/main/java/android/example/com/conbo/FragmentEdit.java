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

public class FragmentEdit extends Fragment {

    View vEdit;

    public FragmentEdit() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vEdit = inflater.inflate(R.layout.edit_fragment, container, false);
        return vEdit;
    }
}
