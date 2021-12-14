package com.example.ricindigus.enpove2021.activities.agregacion;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.M3Pregunta309;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo3;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;

import java.util.ArrayList;

public class AgregarRutaActivity extends AppCompatActivity {

    TextInputEditText edtCiudad;
    Spinner spPais;
    Spinner spAnio, spMes;
    Spinner spModo;
    TextView btnCancelar, btnGuardar;
    String _id, idEncuestado, idVivienda;
    int pais,anio,mes,anio_real;
    int ruta_ant_mes=0,ruta_ant_anio=0;
    String ciudad;
    int modo;
    String numero;
    String pais_nombre;

    String c3_p307_d;
    String c3_p307_m;
    String c3_p307_a;
    String c3_p309_a="";

    LinearLayout layoutCiudad;

    ArrayList<M3Pregunta309> m3Pregunta309s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_ruta);

        idEncuestado = getIntent().getExtras().getString("idEncuestado");
        idVivienda = getIntent().getExtras().getString("idVivienda");
        _id = getIntent().getExtras().getString("idRuta");
        numero = getIntent().getExtras().getString("numero");


        spPais = (Spinner) findViewById(R.id.agregar_ruta_spPais);
        edtCiudad = (TextInputEditText) findViewById(R.id.agregar_ruta_edtCiudad);

        spMes = (Spinner) findViewById(R.id.agregar_ruta_spMes);
        spAnio = (Spinner) findViewById(R.id.agregar_ruta_spAnio);

        spModo = (Spinner) findViewById(R.id.agregar_ruta_spModo);
        btnCancelar = (TextView) findViewById(R.id.agregar_ruta_btnCancelar);
        btnGuardar = (TextView) findViewById(R.id.agregar_ruta_btnGuardar);

        layoutCiudad = (LinearLayout) findViewById(R.id.layout_rutas_ciudad);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarDatos()) {
                    guardarDatos();
                    finish();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        configurarEditText(edtCiudad,layoutCiudad,1,20);

        cargarDatos();
    }

    public void cargarDatos(){
        Data data = new Data(this);
        data.open();
//        if(data.existeElemento(getNombreTabla(),_id)){
//            M3Pregunta309 m3Pregunta309 = data.getM3Pregunta309(_id);
//            spPais.setSelection(Integer.parseInt(m3Pregunta309.getC3_p309_p()));
//            edtCiudad.setText(m3Pregunta309.getC3_p309_c());
//            spModo.setSelection(data.getNumeroRutaPais(m3Pregunta309.getC3_p309_mod()));
//            spMes.setSelection(Integer.parseInt(m3Pregunta309.getC3_p309_m_cod()));
//            spAnio.setSelection(Integer.parseInt(m3Pregunta309.getC3_p309_a_cod()));
//        }
        Modulo3 modulo3 = data.getModulo3(idEncuestado);
        //c3_p307_d  = modulo3.getC3_p307_d();
       // c3_p307_m  = modulo3.getC3_p307_m();
       // c3_p307_a  = modulo3.getC3_p307_a();

        m3Pregunta309s = new ArrayList<>();

        m3Pregunta309s = data.getAllM3Pregunta309(idEncuestado);

        if(m3Pregunta309s.size()==0) {
            spPais.setSelection(1);spPais.setEnabled(false);
        }else {
            spPais.setEnabled(true);
           // c3_p307_m  = m3Pregunta309s.get(m3Pregunta309s.size()-1).getC3_p309_m();
          //  c3_p307_a  = m3Pregunta309s.get(m3Pregunta309s.size()-1).getC3_p309_a();
        }
        data.close();
    }

    public void llenarVariables(){
        pais = spPais.getSelectedItemPosition();
        pais_nombre = spPais.getSelectedItem().toString();
        ciudad = edtCiudad.getText().toString();
        mes = spMes.getSelectedItemPosition();
        anio = spAnio.getSelectedItemPosition();
        anio_real = 2019 - anio;
        modo = spModo.getSelectedItemPosition();
        c3_p309_a = spAnio.getSelectedItem().toString();
    }

    public void guardarDatos(){
        Data data = new Data(this);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo3_c3_p309_p,data.getCodigoRutaPais(pais));
        contentValues.put(SQLConstantes.modulo3_c3_p309_p_nom,pais_nombre);
        contentValues.put(SQLConstantes.modulo3_c3_p309_c,ciudad);
        contentValues.put(SQLConstantes.modulo3_p309_numero,numero);
        contentValues.put(SQLConstantes.modulo3_c3_p309_mod,modo+"");
        contentValues.put(SQLConstantes.modulo3_c3_p309_m,getResources().getStringArray(R.array.numeros_meses)[mes]);
        contentValues.put(SQLConstantes.modulo3_c3_p309_a,getResources().getStringArray(R.array.numeros_anios)[anio]);
        contentValues.put(SQLConstantes.modulo3_c3_p309_m_cod,mes);
        contentValues.put(SQLConstantes.modulo3_c3_p309_a_cod,anio);


        if(!data.existeElemento(getNombreTabla(),_id)){
            M3Pregunta309 m3Pregunta309 =  new M3Pregunta309();
            m3Pregunta309.set_id(_id);
            m3Pregunta309.setId_encuestado(idEncuestado);
            m3Pregunta309.setId_vivienda(idVivienda);
            data.insertarElemento(getNombreTabla(),m3Pregunta309.toValues());
        }
        data.actualizarElemento(getNombreTabla(),contentValues,_id);
        data.close();
    }



    public boolean validarDatos(){
        llenarVariables();
        if(pais == 0){mostrarMensaje("AGREGAR RUTA: DEBE INDICAR EL PAIS");return false;}
        if(ciudad.trim().equals("")){mostrarMensaje("AGREGAR RUTA: DEBE INDICAR LA CIUDAD");return false;}
        if(modo == 0){mostrarMensaje("AGREGAR RUTA: DEBE SELECCIONAR EL MODO DE TRANSPORTE");return false;}
        if(mes == 0){mostrarMensaje("AGREGAR RUTA: DEBE INDICAR EL MES");return false;}
        if(anio == 0){mostrarMensaje("AGREGAR RUTA: DEBE INDICAR EL AÑO");return false;}
        if(Integer.parseInt(c3_p307_a)>Integer.parseInt(c3_p309_a)){
            mostrarMensaje("AGREGAR RUTA - FECHA: AÑO DEBE SER MAYOR O IGUAL QUE EL AÑO("+c3_p307_a+")");return false;
        }else if(Integer.parseInt(c3_p307_a)==Integer.parseInt(c3_p309_a)){
            if(Integer.parseInt(c3_p307_m)>mes){
                mostrarMensaje("AGREGAR RUTA - FECHA: MES DEBE SER MAYOR O IGUAL QUE EL MES("+c3_p307_m+")");return false;
            }else{
                if (Integer.parseInt(numero) > 1){
                    int idAnterior = Integer.parseInt(numero)-2;
                    Data data = new Data(this);
                    data.open();
                    ArrayList<M3Pregunta309> m3Pregunta309s = data.getAllM3Pregunta309(idEncuestado);
                    data.close();
                    int mesesActual = Integer.parseInt(getResources().getStringArray(R.array.numeros_meses)[mes])
                            + Integer.parseInt(getResources().getStringArray(R.array.numeros_anios)[anio]) * 12;
                    int mesesAnterior = Integer.parseInt(m3Pregunta309s.get(idAnterior).getC3_p309_m())
                            + Integer.parseInt(m3Pregunta309s.get(idAnterior).getC3_p309_a()) * 12;
                    if (mesesActual < mesesAnterior){
                        mostrarMensaje("AGREGAR RUTA: LA FECHA DEBE SER MAYOR AL DE LA RUTAS ANTERIORMENTE REGISTRADAS");return false;
                    }
                }
            }
        }
        return true;
    }

    public void mostrarMensaje(String m){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(m);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void configurarEditText(final EditText editText, final View view, int tipo,int longitud){
        if (tipo == 1) editText.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(longitud)});

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    ocultarTeclado(editText);
                    view.requestFocus();
                    return true;
                }
                return false;
            }
        });
        if (tipo == 2) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(longitud)});
            editText.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        }
    }

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public String getNombreTabla(){
        return SQLConstantes.tablam3p309rutas;
    }
}
