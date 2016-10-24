package com.pettime.pettimedevelop.Vista;

import android.app.ProgressDialog;
import android.content.Intent;
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

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private DataUsr du = new DataUsr();
    public ArrayList<Usuario> usuarios = du.getUsuarios();
    private ProgressDialog progressDialog;

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    EditText _emailText;
    EditText _passwordText;
    Button _loginButton;
    TextView _signupLink;
    Usuario usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent i = getIntent();
        Usuario usr;
        usr=(Usuario) i.getSerializableExtra("Usr");
        if(usr!=null){
            usuarios.add(usr);
        }
        _emailText = (EditText)findViewById(R.id.input_email);
        _passwordText= (EditText)findViewById(R.id.input_password);
        _loginButton= (Button) findViewById(R.id.btn_login);
        _signupLink= (TextView) findViewById(R.id.link_signup);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed("Datos incorrectos");
            _emailText.setText("");
            _passwordText.setText(null);
            _emailText.requestFocus();
            return;
        }

        _loginButton.setEnabled(false);


        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.
        if (findUsr(email)){
            if(authUsr(email, password)){
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Autenticando...");
                progressDialog.setCancelable(true);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                Intent intent = new Intent(getApplicationContext(), Navegador.class);
                //Intent intent = new Intent(getApplicationContext(), MenuLateralActivity.class);
                intent.putExtra("User",usr);
                startActivity(intent);
                onLoginSuccess();
            }else {
                onLoginFailed("Contraseña incorrecta");
                _passwordText.setText(null);
                _passwordText.requestFocus();
            }
        }else{
            onLoginFailed("No existe un usuario con estos datos");
            _emailText.setText("");
            _passwordText.setText(null);
            _emailText.requestFocus();
        }


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);


    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        this.finish();
    }

    public void onLoginFailed(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Digite una dirección de correo válida");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("La contraseña tiene entre 4 y 10 caracteres");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
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

    public boolean authUsr(String email, String pwr){
        boolean r=false;
        usr = new Usuario();
        for (int i=0; i<usuarios.size();i++){
            usr=(Usuario)usuarios.get(i);
            if(usr.getEmail().equals(email)&&usr.getPassword().equals(pwr)){
                r=true;
            }
        }
        return r;
    }


}
