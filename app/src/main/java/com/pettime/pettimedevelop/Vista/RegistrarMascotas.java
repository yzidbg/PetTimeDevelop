package com.pettime.pettimedevelop.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.pettime.pettimedevelop.R;

/**
 * Created by yerar on 7/10/2016.
 */
public class RegistrarMascotas extends AppCompatActivity implements OnItemSelectedListener {

    private int pos;
    private String sel;
    protected ArrayAdapter<CharSequence> adapter;
    public static final int DEFAULT_POSITION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mascota);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerEspecies);
        adapter = ArrayAdapter.createFromResource(this, R.array.Especies,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(DEFAULT_POSITION);
        spinner.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.pos=position;
        sel = parent.getItemAtPosition(position).toString();
        Toast.makeText(this,"Selección actual: "+sel, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}