package com.pettime.pettimedevelop.Fragmentos;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pettime.pettimedevelop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoVacio extends Fragment {


    public FragmentoVacio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_vacio, container, false);
    }

}
