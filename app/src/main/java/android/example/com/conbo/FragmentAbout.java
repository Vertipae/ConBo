package android.example.com.conbo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sallamaarit Jaako 1601459 on 9.5.2018.
 */

public class FragmentAbout extends Fragment {
    View vAbout;

    public FragmentAbout() {
    }
    // Inflate the view to screen
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vAbout = inflater.inflate(R.layout.about_fragment, container, false);
        return vAbout;
    }
}
