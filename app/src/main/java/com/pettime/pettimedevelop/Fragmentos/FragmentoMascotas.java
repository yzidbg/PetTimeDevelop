package com.pettime.pettimedevelop.Fragmentos;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pettime.pettimedevelop.R;
import com.pettime.pettimedevelop.Vista.RegistrarMascotas;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoMascotas extends Fragment {

    Button _registerButton;

    public static FragmentoMascotas newInstance(Bundle arguments){
        FragmentoMascotas  f = new FragmentoMascotas();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }

    public FragmentoMascotas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_mascotas, container, false);
        _registerButton = (Button) root.findViewById(R.id.btn_regMascotas);
        _registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegistrarMascotas.class);
                //Intent intent = new Intent(getApplicationContext(), MenuLateralActivity.class);
                //intent.putExtra();
                startActivity(intent);
            }
        });
        return root;
    }
}
