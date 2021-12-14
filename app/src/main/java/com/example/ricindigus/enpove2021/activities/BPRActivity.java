package com.example.ricindigus.enpove2021.activities;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.adapters.EvaluacionAdapter;
import com.example.ricindigus.enpove2021.adapters.PreguntaAdapter;
import com.example.ricindigus.enpove2021.adapters.VisitaEncuestadorAdapter;
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Caratula;
import com.example.ricindigus.enpove2021.modelo.pojos.Pregunta;
import com.example.ricindigus.enpove2021.util.UtilsMethods;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;

public class BPRActivity extends AppCompatActivity {
    Toolbar toolbar;
    String idVivienda;
    String idHogar;
    private String idUsuario;
    FloatingActionButton fb_agregar;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    PreguntaAdapter preguntaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bpr);

        idUsuario   = getIntent().getExtras().getString("idUsuario");

        recyclerView      = (RecyclerView) findViewById(R.id.lista_bpr_rv);
        fb_agregar        = (FloatingActionButton) findViewById(R.id.btn_add_pregunta);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        setearAdapter(DAOUtils.getPreguntas(idUsuario,getApplicationContext()));
        toolbar  = (Toolbar) findViewById(R.id.toolbar_bpr);
        setSupportActionBar(toolbar);

        fb_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPregunta();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mapa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_close:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setearAdapter(ArrayList<Pregunta> lista){
        preguntaAdapter = new PreguntaAdapter(lista, getApplicationContext(), new PreguntaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        recyclerView.setAdapter(preguntaAdapter);
    }

    public void addPregunta(){
        AlertDialog.Builder alert = new AlertDialog.Builder(BPRActivity.this);
        final View dialogView = getLayoutInflater().inflate(R.layout.dialog_agregar_pregunta, null);
        final EditText edtPregunta = (EditText) dialogView.findViewById(R.id.dialog_agregar_pregunta);

        UtilsMethodsInputs.setupEditText(edtPregunta,getApplicationContext(),4,2000);

        //alert.setTitle("AGREGAR PREGUNTA");
        alert.setView(dialogView);
        alert.setPositiveButton("Enviar",null);
        alert.setNegativeButton("Cancelar",null);
        final AlertDialog alertDialog = alert.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button btnFinalizarVisita = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnFinalizarVisita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean valido = false;
                        boolean estado = true;
                        String mensaje = "";

                        if(edtPregunta.getText().toString().trim().length()<1){
                            estado = false;
                            if(mensaje.equals("")) mensaje = "DEBE INGRESAR UNA PREGUNTA";
                        }
                        valido = estado;

                        if(valido){
                            ContentValues contentValues = new ContentValues();
                            //contentValues.put(SQLConstantes.bpr_id,null);
                            contentValues.put(SQLConstantes.bpr_idUsuario,""+idUsuario);
                            contentValues.put(SQLConstantes.bpr_usuario,""+DAOUtils.getUsuarioId(idUsuario,getApplicationContext()).getUsuario());
                            contentValues.put(SQLConstantes.bpr_nombres,""+DAOUtils.getUsuarioId(idUsuario,getApplicationContext()).getNombre());
                            contentValues.put(SQLConstantes.bpr_fecha,""+UtilsMethods.getFechaNow().getDiaInicio()+"/"+UtilsMethods.getFechaNow().getMesInicio()+"/"+UtilsMethods.getFechaNow().getAnioInicio());
                            contentValues.put(SQLConstantes.bpr_hora,""+UtilsMethods.getFechaNow().getHoraInicio()+":"+UtilsMethods.getFechaNow().getMinutoInicio());
                            contentValues.put(SQLConstantes.bpr_pregunta,""+edtPregunta.getText().toString());
                            try{
                                Data dTablas = new Data(getApplicationContext());
                                dTablas.open();
                                dTablas.insertarElemento(SQLConstantes.tablabpr,contentValues);
                                dTablas.close();
                                setearAdapter(DAOUtils.getPreguntas(idUsuario,getApplicationContext()));
                            }catch (SQLException e){}
                            alertDialog.dismiss();
                        }else {
                            showMensaje(true,mensaje);
                        }
                    }
                });
            }
        });
        alertDialog.show();
    }

    public void showMensaje(boolean valor,String mensaje){
        if (valor){
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }
    }

}
