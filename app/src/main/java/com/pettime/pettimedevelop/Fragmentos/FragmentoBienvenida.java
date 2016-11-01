package com.pettime.pettimedevelop.Fragmentos;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pettime.pettimedevelop.Modelo.Usuario;
import com.pettime.pettimedevelop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoBienvenida extends Fragment {

    private TextView titulo;
    private Usuario user;

    public static final String TAG = "FragmentoBienvenida";

    public static FragmentoBienvenida newInstance(Bundle arguments){
        FragmentoBienvenida f = new FragmentoBienvenida();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }

    public FragmentoBienvenida() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        user=(Usuario)getArguments().getSerializable("user");
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_fragmento_bienvenida, container, false);
        cargarDatosUsuarios(root);
        return root;
    }

    private void cargarDatosUsuarios(ViewGroup root){
        if (root != null){
            titulo = (TextView)root.findViewById(R.id.txtTituloBv);
            titulo.setText("Bienvenid@ "+user.getNombre());
            if(user.getImagenPerfil()==null){
                //extraemos el drawable en un bitmap
                Drawable originalDrawable = getResources().getDrawable(R.drawable.def_avatar);
                Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();
                //creamos el drawable redondeado
                RoundedBitmapDrawable roundedDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);
                //asignamos el CornerRadius
                roundedDrawable.setCornerRadius(originalBitmap.getHeight());
                ImageView imageView = (ImageView) root.findViewById(R.id.avatarBig);
                imageView.setImageDrawable(roundedDrawable);
            }else{
                //codigo si el usuario tiene avatar
            }
        }
    }

}
