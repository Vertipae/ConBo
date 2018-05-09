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

public class FragmentCalls extends Fragment{

    View vCalls;

    public FragmentCalls() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vCalls = inflater.inflate(R.layout.calls_fragment, container,false);
        return vCalls;
    }
}
