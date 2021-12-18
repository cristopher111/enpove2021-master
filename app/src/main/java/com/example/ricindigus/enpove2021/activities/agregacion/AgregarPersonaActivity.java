package com.example.ricindigus.enpove2021.activities.agregacion;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.M3Pregunta318;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;

public class AgregarPersonaActivity extends AppCompatActivity {

    Spinner spParentesco;
    RadioGroup rgSexo;
    EditText edtEdad;
    RadioGroup rgSiNo;

    Toolbar toolbar;
    LinearLayout lyt1,lyt2,lyt3,lyt4;

    String numero, idEncuestado,idVivienda, _id;


    private int c3_p318_f;
    private int c3_p318_s;
    private String c3_p318_e;
    private int c3_p318_p;

    int edad=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);

        _id = getIntent().getExtras().getString("id");
        idEncuestado = getIntent().getExtras().getString("idEncuestado");
        idVivienda = getIntent().getExtras().getString("idVivienda");

        numero = getIntent().getExtras().getString("numero");

        spParentesco = (Spinner) findViewById(R.id.agregar_persona_sp_parentesco);
        rgSexo = (RadioGroup) findViewById(R.id.agregar_persona_rg_sexo);
        edtEdad = (EditText) findViewById(R.id.agregar_persona_edt_edad);
        rgSiNo = (RadioGroup) findViewById(R.id.agregar_persona_rg_sino);
        lyt1 = (LinearLayout) findViewById(R.id.agregar_personas_lyt1);
        lyt2 = (LinearLayout) findViewById(R.id.agregar_personas_lyt2);
        lyt3 = (LinearLayout) findViewById(R.id.agregar_personas_lyt3);
        lyt4 = (LinearLayout) findViewById(R.id.agregar_personas_lyt4);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("AGREGAR PERSONA DEJADA EN VENEZUELA");
        getSupportActionBar().setSubtitle("PERSONA Nº " + numero);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        configurarEditText(edtEdad,lyt3,2,2);

        cargarDatos();
    }

    public void cargarDatos(){
        Data data = new Data(this);
        data.open();
        if(data.existeElemento(getNombreTabla(),_id)){
            M3Pregunta318 m3Pregunta318 = data.getM3Pregunta318(_id);
            spParentesco.setSelection(Integer.parseInt(m3Pregunta318.getC3_p318_f()));
            edtEdad.setText(m3Pregunta318.getC3_p318_e());
            if(!m3Pregunta318.getC3_p318_s().equals("-1"))((RadioButton)rgSexo.getChildAt(Integer.parseInt(m3Pregunta318.getC3_p318_s()))).setChecked(true);
            if(!m3Pregunta318.getC3_p318_p().equals("-1"))((RadioButton)rgSiNo.getChildAt(Integer.parseInt(m3Pregunta318.getC3_p318_p()))).setChecked(true);
        }
        Data data2 = new Data(this);
        data2.open();
        Residente residente = data2.getResidente(idEncuestado);
        if(residente.getC2_p205_a()=="") edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        data2.close();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_agregar_persona, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_guardar_persona:
                llenarVariables();

                /* if(spParentesco.getSelectedItemPosition() != 0){
                    guardarDatos();
                    finish();
                }else{
                    mostrarMensaje("ESCOGER UN PARENTESCO ANTES DE GUARDAR");
                }*/

                if (validarDatos()){
                    guardarDatos();
                    finish();
                }

                return true;


            case R.id.action_siguiente_persona:
                M3Pregunta318 m3Pregunta318 = new M3Pregunta318();


                if (!getExistePersona(_id)){
                    Toast.makeText(this,"DEBE COMPLETAR P313_1 Y GUARDAR PARA CONTINUAR",Toast.LENGTH_SHORT).show();
                }//else if((getExistePersona(_id) && !getJefeHogar().getC3_p204().equals("")) || (_id.equals(getJefeHogar().get_id()))){
                    else if(getExistePersona(_id) || (_id.equals(m3Pregunta318.getNumero()))){
                    lyt1.setVisibility(View.GONE);
                    lyt2.setVisibility(View.VISIBLE);
                    lyt3.setVisibility(View.VISIBLE);
                    lyt4.setVisibility(View.VISIBLE);
                }
                    return true;
////////////////////////////
              /*  if(spParentesco.getSelectedItemPosition() == 0){
                    mostrarMensaje("ESCOGER UN PARENTESCO");
                }
                if(numero.trim().equals("")){
                    mostrarMensaje("DEBE COMPLETAR P313_1 Y GUARDAR PARA CONTINUAR");
                }
                if(!numero.trim().equals("")){
                    lyt1.setVisibility(View.GONE);
                    lyt2.setVisibility(View.VISIBLE);
                    lyt3.setVisibility(View.VISIBLE);
                    lyt4.setVisibility(View.VISIBLE);
                }

                return true;*/
/////////////////////
              /* if(spParentesco.getSelectedItemPosition() == 0){
                    mostrarMensaje("ESCOGER UN PARENTESCO");
                }else {
                    lyt1.setVisibility(View.GONE);
                    lyt2.setVisibility(View.VISIBLE);
                    lyt3.setVisibility(View.VISIBLE);
                    lyt4.setVisibility(View.VISIBLE);
                }
                return true;*/

            case R.id.action_anterior_persona:
                lyt1.setVisibility(View.VISIBLE);
                lyt2.setVisibility(View.GONE);
                lyt3.setVisibility(View.GONE);
                lyt4.setVisibility(View.GONE);
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }
    }

    public void llenarVariables(){
        c3_p318_f = spParentesco.getSelectedItemPosition();
        c3_p318_s = rgSexo.indexOfChild(rgSexo.findViewById(rgSexo.getCheckedRadioButtonId()));
        c3_p318_e = edtEdad.getText().toString().trim();
        c3_p318_p = rgSiNo.indexOfChild(rgSiNo.findViewById(rgSiNo.getCheckedRadioButtonId()));
    }

    public boolean validarDatos(){
        llenarVariables();

        if(lyt1.getVisibility() == View.VISIBLE){
            if (c3_p318_f == 0){mostrarMensaje("FAMILIAR: DEBE SELECCIONAR UNA OPCIÓN"); return false;}
        }
        if(lyt2.getVisibility() == View.VISIBLE && lyt3.getVisibility() == View.VISIBLE && lyt4.getVisibility() == View.VISIBLE){
            if (c3_p318_s == -1){mostrarMensaje("SEXO: DEBE MARCAR UNA OPCIÓN"); return false;}
            if (c3_p318_e.equals("")){mostrarMensaje("EDAD: DEBE INDICAR SU EDAD"); return false;}
            if (c3_p318_f==1 && c3_p318_s!=1){mostrarMensaje("SELECCIONO PAPÁ, SEXO DEBE SER HOMBRE"); return false;}
            if (c3_p318_f==2 && c3_p318_s!=2){mostrarMensaje("SELECCIONO MAMÁ, SEXO DEBE SER MUJER"); return false;}
            if (c3_p318_p == -1){mostrarMensaje("PIENSA TRAER A SU FAMILIAR: DEBE MARCAR UNA OPCIÓN"); return false;}

            if((c3_p318_f == 1 || c3_p318_f == 2) && Integer.parseInt(c3_p318_e)-edad<12 ){
                mostrarMensaje("VERIFIQUE LA RELACION DE PARENTESCO DEL FAMILIAR QUE DEJÓ EN VENEZUELA Y SU EDAD"); return false;
            }else if(c3_p318_f == 4 && edad-Integer.parseInt(c3_p318_e)<12 ){
                mostrarMensaje("VERIFIQUE LA RELACION DE PARENTESCO DEL FAMILIAR QUE DEJÓ EN VENEZUELA Y SU EDAD"); return false;
            }else if(c3_p318_f == 6 && edad-Integer.parseInt(c3_p318_e)<24 ){
                mostrarMensaje("VERIFIQUE LA RELACION DE PARENTESCO DEL FAMILIAR QUE DEJÓ EN VENEZUELA Y SU EDAD"); return false;
            }else if(c3_p318_f == 7 && Integer.parseInt(c3_p318_e)-edad<12 ){
                mostrarMensaje("VERIFIQUE LA RELACION DE PARENTESCO DEL FAMILIAR QUE DEJÓ EN VENEZUELA Y SU EDAD"); return false;
            }
        }


//        if(c3_p318_f==1 || c3_p318_f==2){
//            if(Integer.parseInt(c3_p318_e)<edad){ mostrarMensaje("EDAD: DEBE SER MAYOR O IGUAL A SU EDAD("+edad+")"); return false;}
//        }
        return true;
    }

    public void guardarDatos(){
        Data data = new Data(this);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo3_c3_p318_f,c3_p318_f);
        contentValues.put(SQLConstantes.modulo3_c3_p318_s,c3_p318_s);
        contentValues.put(SQLConstantes.modulo3_c3_p318_e,c3_p318_e);
        contentValues.put(SQLConstantes.modulo3_c3_p318_p,c3_p318_p);

        if(!data.existeElemento(getNombreTabla(),_id)){
            M3Pregunta318 m3Pregunta318 =  new M3Pregunta318();
            m3Pregunta318.set_id(_id);
            m3Pregunta318.setIdEncuestado(idEncuestado);
            m3Pregunta318.setId_vivienda(idVivienda);
            m3Pregunta318.setNumero(numero);
            data.insertarElemento(getNombreTabla(),m3Pregunta318.toValues());
        }
        data.actualizarElemento(getNombreTabla(),contentValues,_id);
        data.actualizarValor(SQLConstantes.tablamodulo3,SQLConstantes.modulo3_c3_p313,"1",idEncuestado);
        data.close();
    }



    public boolean getExistePersona(String idPersona){
        boolean respuesta = false;
        Data data = new Data(this);
        data.open();
        if(data.existeElemento(getNombreTabla(),idPersona)){
            respuesta = true;
        }
        data.close();
        return respuesta;
    }



    public String getNombreTabla() {
        return SQLConstantes.tablam3p318personas;
    }

}

