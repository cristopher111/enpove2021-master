package com.example.ricindigus.enpove2021.activities;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.activities.admin.AdminActivity;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.pojos.Usuario;
import com.example.ricindigus.enpove2021.util.UtilsMethods;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LoginActivity extends AppCompatActivity {
    private Button ingresarButton;
    private TextInputEditText usuarioEditText;
    private TextInputEditText passwordEditText;
    private TextView          versionTxt;
    String userUsuario;
    String passwordUsuario;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ingresarButton   = (Button) findViewById(R.id.login_button_ingresar);
        usuarioEditText  = (TextInputEditText) findViewById(R.id.login_textInput_usuario);
        passwordEditText = (TextInputEditText) findViewById(R.id.login_textInput_password);
        versionTxt       = (TextView) findViewById(R.id.login_txt_version);

        usuarioEditText.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(8)});
        passwordEditText.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(8)});

        //usuarioEditText.setText("ENC001");
        //passwordEditText.setText("291538");
        //usuarioEditText.setText("SUP001");
        //passwordEditText.setText("908231");
        //versionTxt.setText("Primera Prueba Piloto (Cognitiva) - Versión "+ UtilsMethods.getVersion(this));
        //versionTxt.setText("Prueba Requerimientos - Versión 0.3.1");
        versionTxt.setText("II ENPOVE 2021-2022 - Versión "+ UtilsMethods.getVersion(this) +" (Campo)");

        ingresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userUsuario = usuarioEditText.getText().toString();
                passwordUsuario = passwordEditText.getText().toString();

                if (validarCampos()){
                    if(userUsuario.equals("SIS") && passwordUsuario.equals("123")){
                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Data data =  new Data(LoginActivity.this);
                        data.open();
                        Usuario user = data.getUsuario(userUsuario);
                        data.close();
                        if(user == null){
                            usuarioEditText.setText("");
                            passwordEditText.setText("");
                            Toast.makeText(LoginActivity.this, "USUARIO NO EXISTE", Toast.LENGTH_SHORT).show();
                        }else{
                            if(passwordUsuario.equals(user.getClave())){
                                Intent intent = new Intent(LoginActivity.this, MarcoActivity.class);
                                intent.putExtra("nickUsuario",user.getUsuario());
                                intent.putExtra("idUsuario",user.get_id()+"");
                                intent.putExtra("idCargo",user.getCargo_id()+"");
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this, "PASSWORD INCORRECTO", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Debe Ingresar USUARIO y CONTRASEÑA", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean validarCampos(){
        boolean valido = true;
        if(usuarioEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")) valido = false;
        return valido;
    }






}
