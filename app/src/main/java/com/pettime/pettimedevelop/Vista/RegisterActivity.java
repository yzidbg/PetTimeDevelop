package com.pettime.pettimedevelop.Vista;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pettime.pettimedevelop.Datos.DataUsr;
import com.pettime.pettimedevelop.Modelo.Usuario;
import com.pettime.pettimedevelop.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private DataUsr du = new DataUsr();
    private ArrayList<Usuario> usuarios = du.getUsuarios();
    private ProgressDialog progressDialog;
    EditText _nameText;
    EditText _emailText;
    EditText _passwordText;
    EditText _passwordTextConfirm;
    Button _signupButton;
    TextView _loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        _nameText = (EditText) findViewById(R.id.input_name);
        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _passwordTextConfirm = (EditText) findViewById(R.id.input_password_retype);
        _signupButton = (Button) findViewById(R.id.btn_signup);
        _loginLink = (TextView) findViewById(R.id.link_login);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }


    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed("Error al crear la cuenta!");
            return;
        }

        _signupButton.setEnabled(false);


        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String passwordConfirm = _passwordText.getText().toString();

        if(!pwrMatcher(password,passwordConfirm)){
            onSignupFailed("Las contraseñas no coinciden!");
            return;
        }

        if(!findUsr(email)){
            progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setMessage("Autenticando...");
            progressDialog.setCancelable(true);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
            Usuario usr = new Usuario(1000,1,1,2,"",email,0,name,password,"123456","123456");
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.putExtra("Usr",usr);
            //Intent intent = new Intent(getApplicationContext(), MenuLateralActivity.class);
            startActivity(intent);
        }else{
            onSignupFailed("Ya existe un usuario con esos datos");
            return;
        }


        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public boolean pwrMatcher(String pwr1, String pwr2){
        boolean r=false;
        if(pwr1.equals(pwr2))r=true;
        return r;
    }

    public boolean findUsr(String email){
        boolean r=false;
        Usuario usr = new Usuario();
        for (int i=0; i<usuarios.size();i++){
            usr=(Usuario)usuarios.get(i);
            if(usr.getEmail().equals(email)){
                r=true;
            }
        }
        return r;
    }

    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("al menos 3 caracteres");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Digite un email válido");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Entre 4 y 10 caracteres alfanuméricos");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
