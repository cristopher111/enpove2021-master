package com.example.ricindigus.enpove2021.activities.admin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.activities.LoginActivity;
import com.example.ricindigus.enpove2021.util.FileChooser;

import java.io.File;

public class AdminActivity extends AppCompatActivity {

    Button btnCargarMarco;
    Button btnCargarUsuario;
    Button btnHorarioAsistencia;
    Button btnSalir;
    Button btnExportarBD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        btnCargarMarco = (Button) findViewById(R.id.btnCargarMarco);
        btnCargarUsuario = (Button) findViewById(R.id.btnCargarUsuario);
        btnExportarBD = (Button) findViewById(R.id.btnExportaBD);

        btnHorarioAsistencia = (Button) findViewById(R.id.btnHorarioAsistencia);
        btnSalir = (Button) findViewById(R.id.btnSalir);

//        btnCargarMarco.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FileChooser fileChooser = new FileChooser(AdminActivity.this);
//                fileChooser.setFileListener(new FileChooser.FileSelectedListener() {
//                    @Override
//                    public void fileSelected(File file) {
//                        String filename = file.getAbsolutePath();
//                        if(filename.substring(filename.length()-7,filename.length()).toLowerCase().equals(".sqlite")){
//                            Intent intent = new Intent(AdminActivity.this, AdmMarcoActivity.class);
//                            intent.putExtra("filename",filename);
//                            intent.putExtra("tipo_carga",1);
//                            startActivity(intent);
//                            finish();
//                        }else{
//                            Toast.makeText(AdminActivity.this, "archivo de tipo incorrecto", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                fileChooser.showDialog();
//
//            }
//        });

        btnCargarMarco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(AdminActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(AdminActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }else{
                    elegirArchivo();
                }
            }
        });

        btnCargarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(AdminActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(AdminActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }else{
                    elegirArchivo();
                }
            }
        });



        btnExportarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(AdminActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(AdminActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                }else{
                    Intent intent = new Intent(AdminActivity.this, AdmMarcoActivity.class);
                    intent.putExtra("tipo_carga",2);
                    startActivity(intent);
                }
            }
        });




        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void elegirArchivo(){
        FileChooser fileChooser = new FileChooser(AdminActivity.this);
        fileChooser.setFileListener(new FileChooser.FileSelectedListener() {
            @Override
            public void fileSelected(File file) {
                String filename = file.getAbsolutePath();
                if(filename.substring(filename.length()-4,filename.length()).toLowerCase().equals(".xml")){
                    Intent intent = new Intent(AdminActivity.this, AdmMarcoActivity.class);
                    intent.putExtra("filename",filename);
                    intent.putExtra("tipo_carga",1);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(AdminActivity.this, "archivo de tipo incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
        fileChooser.showDialog();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                elegirArchivo();
            }else {
                Toast.makeText(this, "Debe activar los permisos para lectura del almacenamiento", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 2){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(AdminActivity.this, AdmMarcoActivity.class);
                intent.putExtra("tipo_carga",2);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Debe activar los permisos para escritura en el almacenamiento", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
