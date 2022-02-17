package com.example.ricindigus.enpove2021.fragments.modulo3;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.activities.agregacion.AgregarResidenteActivity;
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Hogar;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo3;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.modelo.pojos.VisitaEncuestador;
import com.example.ricindigus.enpove2021.modelo.pojos.VisitaSupervisor;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethods;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

/**
 * A simple {@link Fragment} subclass.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FragmentP301P305 extends FragmentPagina {
    String idEncuestado;
    Context context;

    private static final String CERO = "0";
    public final Calendar c = Calendar.getInstance();
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    TextView c3_p301_d_TextView, c3_p301_m_TextView, c3_p301_a_TextView;
    Button c3_p301_d_f_Button;

    Spinner c3_p302_Spinner;

    Spinner p303spMes,p303spAnio;
    CheckBox c3_p303_CheckBox;

    RadioGroup c3_p304_RadioGroup;
    EditText mod3_304_edittext_C3_P304_O;

    RadioGroup c3_p305_RadioGroup;

    //EditText c3_p305_o_EditText;

    EditText et_p003_o;

    LinearLayout layout301,layout302,layout303,layout304,layout305;

    Spinner informanteSpinner;



    String idInformante;
    String idHogar;
    String idVivienda;
    String c3_p301_d;
    String c3_p301_m;
    String c3_p301_a;
    String c3_p302;
    String c3_p302_o;
    String c3_p303_m;
    String c3_p303_a;
    String p303_a;
    String c3_p303_no_nacio;
    String c3_p304;
    String c3_p304_o;
    String c3_p305;


    Calendar calendario;
    int aa=0, mm=0, dd=0;
    String v_aa="",v_mm="",v_dd="";

    int edad=0, edad_ingresada=0, edad_fecha=0;

    public FragmentP301P305() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentP301P305(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idHogar = residente.getId_hogar();
        idVivienda = residente.getId_vivienda();
        idInformante = "";
        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        VisitaEncuestador visita = data.getUltimaVisitasHogar(idHogar);
        VisitaSupervisor visitaSupervisor = data.getUltimaVisitasHogarSupervisor(idHogar);
        if(visita!=null){
            if(!visita.getVis_fecha_aa().equals("")){v_aa = visita.getVis_fecha_aa(); aa = Integer.parseInt(v_aa);}
            if(!visita.getVis_fecha_mm().equals("")){v_mm = visita.getVis_fecha_mm(); mm = Integer.parseInt(v_mm);}
            if(!visita.getVis_fecha_dd().equals("")){v_dd = visita.getVis_fecha_dd(); dd = Integer.parseInt(v_dd);}
        }else if (visitaSupervisor!=null){
            Log.e("añovisita",""+visitaSupervisor.getVis_fecha_aa());
            if(!visitaSupervisor.getVis_fecha_aa().equals("")){v_aa = visitaSupervisor.getVis_fecha_aa(); aa = Integer.parseInt(v_aa);}
            if(!visitaSupervisor.getVis_fecha_mm().equals("")){v_mm = visitaSupervisor.getVis_fecha_mm(); mm = Integer.parseInt(v_mm);}
            if(!visitaSupervisor.getVis_fecha_dd().equals("")){v_dd = visitaSupervisor.getVis_fecha_dd(); dd = Integer.parseInt(v_dd);}
        }

        data.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p301_p305, container, false);
        c3_p301_d_TextView = (TextView) rootView.findViewById(R.id.mod3_301_textview_C3_P301_D);
        c3_p301_m_TextView = (TextView) rootView.findViewById(R.id.mod3_301_textview_C3_P301_M);
        c3_p301_a_TextView = (TextView) rootView.findViewById(R.id.mod3_301_textview_C3_P301_A);
        c3_p301_d_f_Button = (Button) rootView.findViewById(R.id.mod3_301_button_C3_P301_F);

        c3_p302_Spinner = (Spinner) rootView.findViewById(R.id.mod3_302_spinner_C3_P302);

        p303spMes = (Spinner) rootView.findViewById(R.id.mod3_303_spinner_C3_P303_M);
        p303spAnio = (Spinner) rootView.findViewById(R.id.mod3_303_spinner_C3_P303_A);
        c3_p303_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_303_checkbox_C3_P303_NO_NACIO);

        c3_p304_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod3_304_radiogroup_C3_P304);
        mod3_304_edittext_C3_P304_O = (EditText) rootView.findViewById(R.id.mod3_304_edittext_C3_P304_O);

        c3_p305_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod3_305_radiogroup_C3_P305);

        //c3_p305_o_EditText = (EditText) rootView.findViewById(R.id.mod3_305_edittext_C3_P305_O);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

        et_p003_o  = (EditText) rootView.findViewById(R.id.et_p003_o);


        layout301 = (LinearLayout) rootView.findViewById(R.id.layout_m3_p301);
        layout302 = (LinearLayout) rootView.findViewById(R.id.layout_m3_p302);
        layout303 = (LinearLayout) rootView.findViewById(R.id.layout_m3_p303);
        layout304 = (LinearLayout) rootView.findViewById(R.id.layout_m3_p304);
        layout305 = (LinearLayout) rootView.findViewById(R.id.layout_m3_p305);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Data data =  new Data(context);
        data.open();

        configurarEditText(et_p003_o,layout302,0,50);

        c3_p301_d_f_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog recogerFecha = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        final int mesActual = month + 1;
                        String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                        String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                        c3_p301_d_TextView.setText(""+diaFormateado);
                        c3_p301_m_TextView.setText(""+mesFormateado);
                        c3_p301_a_TextView.setText(""+year);

                    }
                },anio, mes, dia);
                recogerFecha.show();
            }
        });

         configurarEditText(mod3_304_edittext_C3_P304_O,layout304,0,50);

        c3_p303_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    p303spMes.setSelection(0);
                    p303spAnio.setSelection(0);
                    p303spMes.setEnabled(false);
                    p303spAnio.setEnabled(false);
                    c3_p304_RadioGroup.clearCheck();layout304.setVisibility(View.GONE);
                    //c3_p305_RadioGroup.clearCheck();c3_p305_o_EditText.setText("");layout305.setVisibility(View.GONE);
                }else{
                    p303spMes.setEnabled(true);p303spAnio.setEnabled(true);
                    layout304.setVisibility(View.VISIBLE);
                    layout305.setVisibility(View.VISIBLE);
                }
            }
        });

        c3_p304_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                switch (seleccionado){
                    case 5:
                        mod3_304_edittext_C3_P304_O.setEnabled(true);
                        mod3_304_edittext_C3_P304_O.setBackgroundResource(R.drawable.input_text_enabled);
                        break;
                    default:
                        mod3_304_edittext_C3_P304_O.setText("");
                        mod3_304_edittext_C3_P304_O.setBackgroundResource(R.drawable.input_text_disabled);
                        mod3_304_edittext_C3_P304_O.setEnabled(false);
                        break;
//                    case 1:layout305.setVisibility(View.VISIBLE);break;
                    //  case 2: c3_p305_RadioGroup.clearCheck();c3_p305_o_EditText.setText("");layout305.setVisibility(View.GONE);break;
                }
            }
        });

        c3_p305_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // controlarEspecifiqueRadio(group, checkedId,4,c3_p305_o_EditText);
            }
        });


        c3_p302_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos == 36){
                    et_p003_o.setEnabled(true);
                    et_p003_o.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    et_p003_o.setEnabled(false);
                    et_p003_o.setText("");
                    et_p003_o.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


        llenarVista();
        cargarDatos();
        validateResidentesVenezolanos();
    }



    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo3_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo3_c3_p301_d,c3_p301_d);
        contentValues.put(SQLConstantes.modulo3_c3_p301_m,c3_p301_m);
        contentValues.put(SQLConstantes.modulo3_c3_p301_a,c3_p301_a);
        contentValues.put(SQLConstantes.modulo3_c3_p302,c3_p302);
        contentValues.put(SQLConstantes.modulo3_c3_p302_o,c3_p302_o);
        contentValues.put(SQLConstantes.modulo3_c3_p303_m,c3_p303_m);
        contentValues.put(SQLConstantes.modulo3_c3_p303_a,getResources().getStringArray(R.array.numeros_anios)[Integer.parseInt(c3_p303_a)]);
        contentValues.put(SQLConstantes.modulo3_c3_p303_no_nacio, c3_p303_no_nacio);
        contentValues.put(SQLConstantes.modulo3_c3_p304,c3_p304);
        contentValues.put(SQLConstantes.modulo3_c3_p304_o,c3_p304_o);
        contentValues.put(SQLConstantes.modulo3_c3_p305,c3_p305);
        //contentValues.put(SQLConstantes.modulo3_c3_p305_o,c3_p305_o);
        if(!data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo3 modulo3 = new Modulo3();
            modulo3.set_id(idEncuestado);
            modulo3.setIdHogar(idHogar);
            modulo3.setIdVivienda(idVivienda);
            data.insertarElemento(getNombreTabla(),modulo3.toValues());
        }
        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp301p305,"1",idEncuestado);
        //ocultamos o mostramos preguntas o fragments
        ocultarOtrosLayouts();
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo3_COB300,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo3_COB300,"0",idEncuestado);
        //data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();
    }

    @Override
    public void llenarVariables() {
        String fecha_naci="",fecha_vici="";
        idInformante = obtener_Nresidente(informanteSpinner);
        c3_p301_d = c3_p301_d_TextView.getText().toString();
        c3_p301_m = c3_p301_m_TextView.getText().toString();
        c3_p301_a = c3_p301_a_TextView.getText().toString();
        fecha_naci = c3_p301_a + "-" + c3_p301_m + "-" + c3_p301_d;

        if(c3_p302_Spinner.getSelectedItemPosition() == 36){
            c3_p302 = "99";
            c3_p302_o = et_p003_o.getText()+"";
        }else{
            c3_p302 = c3_p302_Spinner.getSelectedItemPosition() + "";
             try{
                 c3_p302_o = DAOUtils.getPais(c3_p302_Spinner.getSelectedItemPosition()+"",context).getNombre();
            }catch (Exception e){
                 c3_p302_o = "0";
             }

        }

        fecha_vici = v_aa  + "-" + v_mm + "-" + v_dd;
        Log.e("fechaNacimiento", ""+fecha_naci);
        Log.e("fechaVisita", ""+fecha_vici);
        c3_p303_m = p303spMes.getSelectedItemPosition() + "";
        c3_p303_a = p303spAnio.getSelectedItemPosition() + "";
        p303_a  = p303spAnio.getSelectedItem().toString();
        if(c3_p303_CheckBox.isChecked())c3_p303_no_nacio = 1 + "";
        else c3_p303_no_nacio = 0 + "";
        c3_p304 = c3_p304_RadioGroup.indexOfChild(c3_p304_RadioGroup.findViewById(c3_p304_RadioGroup.getCheckedRadioButtonId())) + "";
        c3_p304_o = mod3_304_edittext_C3_P304_O.getText().toString();
        c3_p305  = c3_p305_RadioGroup.indexOfChild(c3_p305_RadioGroup.findViewById(c3_p305_RadioGroup.getCheckedRadioButtonId())) + "";
        // c3_p305_o = c3_p305_o_EditText.getText().toString();
        try {
            DateFormat dateFormat = dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date fechaNacimiento = dateFormat.parse(fecha_naci);
            Date fechaVisita = dateFormat.parse(fecha_vici);
            Log.e("fechaNacimiento", ""+fechaNacimiento);
            Log.e("fechaVisita", ""+fechaVisita);
            Calendar cal = Calendar.getInstance();
            Date fechaActual = cal.getTime();
            edad_ingresada = getEdad(fechaNacimiento, fechaActual);
            edad_fecha = getEdad(fechaNacimiento, fechaVisita);///antes estaba (fechaNacimiento, fechaVisita)
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cargarDatos() {
        Data data = new Data(context);
        data.open();
        if (data.existeElemento(getNombreTabla(),idEncuestado)){

            Modulo3 modulo3 = data.getModulo3(idEncuestado);
            ArrayList<String> informantes = data.getListaInformantesMayor12(idHogar);
            UtilsMethodsInputs.loadSpinner(informantes,informanteSpinner,context);
            if(modulo3.getIdInformante() != null && !modulo3.getIdInformante().equals("")) informanteSpinner.setSelection(obtener_posicion(modulo3.getIdInformante(),informanteSpinner));


            c3_p301_d_TextView.setText(modulo3.getC3_p301_d());
            c3_p301_m_TextView.setText(modulo3.getC3_p301_m());
            c3_p301_a_TextView.setText(modulo3.getC3_p301_a());

//            if(modulo3.getC3_p302() !=null && !modulo3.getC3_p302().equals("")){
//                try{
//                    int p = Integer.parseInt(modulo3.getC3_p302());
//                    if(p < 36) c3_p302_Spinner.setSelection(p);
//                }catch (Exception e){
//                    c3_p302_Spinner.setSelection(36);
//                    et_p003_o.setText(modulo3.getC3_p302());
//                }
//            }
            ArrayList<String> paises = data.getListaPaises();
            UtilsMethodsInputs.loadSpinner(paises,c3_p302_Spinner,context);
            if(modulo3.getC3_p302() !=null && !modulo3.getC3_p302().equals("")){
                    int p = Integer.parseInt(modulo3.getC3_p302());
                    if(p <= 35) {
                        c3_p302_Spinner.setSelection(p);}
                    else {
                        c3_p302_Spinner.setSelection(36);
                        et_p003_o.setText(modulo3.getC3_p302_o());
                    }
            }

            if(modulo3.getC3_p303_no_nacio().equals("1")) c3_p303_CheckBox.setChecked(true);
            else{
                if(!modulo3.getC3_p303_m().equals(""))p303spMes.setSelection(Integer.parseInt(modulo3.getC3_p303_m()));
                if(!modulo3.getC3_p303_a().equals(""))p303spAnio.setSelection(2023 - Integer.parseInt(modulo3.getC3_p303_a()));
            }
            if(!modulo3.getC3_p304().equals("-1") && !modulo3.getC3_p304().equals(""))((RadioButton)c3_p304_RadioGroup.getChildAt(Integer.parseInt(modulo3.getC3_p304()))).setChecked(true);
            mod3_304_edittext_C3_P304_O.setText(modulo3.getC3_p304_o());
            if(!modulo3.getC3_p305().equals("-1") && !modulo3.getC3_p305().equals(""))((RadioButton)c3_p305_RadioGroup.getChildAt(Integer.parseInt(modulo3.getC3_p305()))).setChecked(true);
            // c3_p305_o_EditText.setText(modulo3.getC3_p305_o());




        }
        data.close();
    }

    @Override
    public void llenarVista() {

    }

    @Override
    public boolean validarDatos() {
        llenarVariables();

        Log.e("edad",""+edad);
        Log.e("edad_fecha",""+edad_fecha);
       if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

       if (c3_p301_d.trim().equals("")){mostrarMensaje("PREGUNTA 301: DEBE AGREGAR FECHA");return false;}
//        if(((edad!=(aa-Integer.parseInt(c3_p301_a))) && (mm>Integer.parseInt(c3_p301_m))) ||
//            ((edad!=(aa-Integer.parseInt(c3_p301_a)-1)) && (mm<Integer.parseInt(c3_p301_m))) ||
//            ((edad!=(aa-Integer.parseInt(c3_p301_a))) && (mm==Integer.parseInt(c3_p301_m)) && (dd>Integer.parseInt(c3_p301_d))) ||
//            ((edad!=(aa-Integer.parseInt(c3_p301_a)-1)) && (mm==Integer.parseInt(c3_p301_m)) && (dd<Integer.parseInt(c3_p301_d)))){
//            mostrarMensaje("PREGUNTA 301: DIFERENTE A EDAD CALCULADA ENTRE FECHA DE NACIMIENTO Y FECHA DE ENTREVISTA");return false;
//        }
        if(edad!=edad_fecha){
            showCapitulo200();
            Log.e("edad",""+edad);
            Log.e("edad_fecha",""+edad_fecha);
            //mostrarMensaje("PREGUNTA 301: DIFERENTE A EDAD CALCULADA ENTRE FECHA DE NACIMIENTO Y FECHA DE ENTREVISTA")
            ;return false;
        }
        if (edad!=edad_ingresada){mostrarMensaje("PREGUNTA 301: NO COINCIDE CON EDAD INGRESADA CAPITULO 200("+edad+")");return false;}
        if (c3_p302.equals("0")) {mostrarMensaje("PREGUNTA 302: DEBE INDICAR PAIS DE NACIMIENTO");return false;}
        if(et_p003_o.isEnabled() && (et_p003_o.getText().toString().equals("") || et_p003_o.length()<3)){mostrarMensaje("PREGUNTA P302: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}

        if (!c3_p303_CheckBox.isChecked()){
            if(c3_p303_m.equals("0")) {mostrarMensaje("PREGUNTA 303: DEBE AGREGAR MES");return false;}
            if(c3_p303_a.equals("0")) {mostrarMensaje("PREGUNTA 303: DEBE AGREGAR AÑO");return false;}
            if(Integer.parseInt(p303_a)<Integer.parseInt(c3_p301_a)){
                mostrarMensaje("PREGUNTA 303: EL AÑO DE INGRESO AL PERÚ  ES MENOR A LA FECHA DE NACIMIENTO DECLARADA");return false;
            }else if(Integer.parseInt(p303_a)==Integer.parseInt(c3_p301_a)){
                if(Integer.parseInt(c3_p303_m)<Integer.parseInt(c3_p301_m)){
                    mostrarMensaje("PREGUNTA 303: EL AÑO DE INGRESO AL PERÚ  ES MENOR A LA FECHA DE NACIMIENTO DECLARADA");return false;
                }
            }else if(Integer.parseInt(p303_a)==2022 && Integer.parseInt(c3_p303_m)>Integer.parseInt(UtilsMethods.getFechaNow().getMesInicio())){
                    mostrarMensaje("PREGUNTA 303: EL MES DE INGRESO AL PERÚ  NO PUEDE SER POSTERIOR AL MES ACTUAL");return false;
            }
        }
        if (layout304.getVisibility() == View.VISIBLE){
            if (c3_p304.equals("-1")){mostrarMensaje("PREGUNTA 304: DEBE MARCAR UNA OPCIÓN");return false;}
            else if (c3_p304.equals("5")){
                if(c3_p304_o.trim().equals("")){
                    mostrarMensaje("PREGUNTA 304 - DEBE ESPECIFICAR CIUDAD");return false;
                }
                if(c3_p304_o.length()<3){
                    mostrarMensaje("ERROR  “P304. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }
            }
        }else{
            c3_p304 = "";
            c3_p304_o = "";
        }

        if (layout305.getVisibility() == View.VISIBLE){
            if (c3_p305.equals("-1")){mostrarMensaje("PREGUNTA 305: DEBE MARCAR UNA OPCIÓN");return false;}
        }else{
            c3_p305 = "";
        }
        return true;
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablamodulo3;
    }

    public String obtener_Nresidente(Spinner spinner) {
        String cadena="";
        try{
            cadena = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
            cadena = cadena.substring(0,cadena.indexOf("-"));
        }catch (Exception e){}
        return cadena;
    }
    private int obtener_posicion(String Nresidente,Spinner spinner) {
        int posicion=0;
        try{
            for(int i=0; i<spinner.getCount();i++){
                if(Nresidente.equals(spinner.getItemAtPosition(i).toString().substring(0,spinner.getItemAtPosition(i).toString().indexOf("-")))){
                    posicion = i;
                    Log.e("posicion",""+posicion);
                }
            }
        }catch (Exception e){posicion = 0;}
        return posicion;
    }
    public void mostrarMensaje(String m){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
        switch (tipo){
            case 0:editText.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(longitud), new InputFilterSoloLetras()});break;
            case 1:editText.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(longitud)});break;
            case 2:editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(longitud)});
                editText.setTransformationMethod(new NumericKeyBoardTransformationMethod());break;
        }

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
    }

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    private void controlarEspecifiqueRadio(RadioGroup group, int checkedId, int opcionEsp, EditText editTextEspecifique) {
        int seleccionado = group.indexOfChild(group.findViewById(checkedId));
        if(seleccionado == opcionEsp){
            editTextEspecifique.setBackgroundResource(R.drawable.input_text_enabled);
            editTextEspecifique.setEnabled(true);
        }else{
            editTextEspecifique.setText("");
            editTextEspecifique.setBackgroundResource(R.drawable.input_text_disabled);
            editTextEspecifique.setEnabled(false);
        }
    }

    public void ocultarOtrosLayouts(){
        if (c3_p303_CheckBox.isChecked()){
            // 6,7,8,9,10
            Data data = new Data(context);
            data.open();
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.modulo3_c3_p306,"");
            contentValues.put(SQLConstantes.modulo3_c3_p306_o,"");
            contentValues.put(SQLConstantes.modulo3_c3_p307,"");
            contentValues.put(SQLConstantes.modulo3_c3_p307_o6,"");
            contentValues.put(SQLConstantes.modulo3_c3_p307_o12,"");
            //contentValues.put(SQLConstantes.modulo3_c3_p307_d,"");
            //contentValues.put(SQLConstantes.modulo3_c3_p307_m,"");
            //  contentValues.put(SQLConstantes.modulo3_c3_p307_a,"");
            contentValues.put(SQLConstantes.modulo3_c3_p308,"");
            //   contentValues.put(SQLConstantes.modulo3_c3_p308_e,"");
            //  contentValues.put(SQLConstantes.modulo3_c3_p308_m,"");
            //   contentValues.put(SQLConstantes.modulo3_c3_p308_e_seleccion,"");
            //   contentValues.put(SQLConstantes.modulo3_c3_p308_m_seleccion,"");
//            contentValues.put(SQLConstantes.modulo3_c3_p310_1,"");
//            contentValues.put(SQLConstantes.modulo3_c3_p310_2,"");
//            contentValues.put(SQLConstantes.modulo3_c3_p310_3,"");
//            contentValues.put(SQLConstantes.modulo3_c3_p310_4,"");
            data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
            data.borrarAllData(SQLConstantes.tablam3p309rutas);
            contentValues = new ContentValues();
            contentValues.put(SQLConstantes.layouts_p306,"0");
            contentValues.put(SQLConstantes.layouts_p307,"0");
            contentValues.put(SQLConstantes.layouts_p308,"0");
            contentValues.put(SQLConstantes.layouts_p309,"0");
            contentValues.put(SQLConstantes.layouts_p309_1,"0");
            contentValues.put(SQLConstantes.layouts_p310,"0");
            data.actualizarElemento(SQLConstantes.tablalayouts,contentValues,idEncuestado);
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p306p308,"-1",idEncuestado);
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p309,"-1",idEncuestado);
            data.close();
        }else{
            Data data = new Data(context);
            data.open();
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.layouts_p306,"1");
            contentValues.put(SQLConstantes.layouts_p307,"1");
            contentValues.put(SQLConstantes.layouts_p308,"1");
            contentValues.put(SQLConstantes.layouts_p309,"1");
            contentValues.put(SQLConstantes.layouts_p309_1,"1");
            contentValues.put(SQLConstantes.layouts_p310,"1");
            data.actualizarElemento(SQLConstantes.tablalayouts,contentValues,idEncuestado);
            if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p306p308,idEncuestado).equals("-1"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p306p308,"1",idEncuestado);
            if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p309,idEncuestado).equals("-1"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p309,"1",idEncuestado);

            data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp306p308,"0",idEncuestado);
            data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp309,"0",idEncuestado);
            data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp310p312,"0",idEncuestado);
            data.close();
        }
    }

    public  int getEdad(Date fechaNacimiento, Date fechaActual) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Log.e("fechaNacimiento", ""+fechaNacimiento);
        Log.e("fechaActual", ""+fechaActual);
        int dIni = Integer.parseInt(formatter.format(fechaNacimiento));
        int dEnd = Integer.parseInt(formatter.format(fechaActual));
        Log.e("getedadnacimiento",""+dIni);
        int age = (dEnd-dIni)/10000;

        return age;
    }

    public boolean verificarCoberturaCapitulo(){
        Data data = new Data(context);
        data.open();
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p301p305,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp301p305,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p306p308,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp306p308,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p309,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp309,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p310p312,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp310p312,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p313p317,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp313p317,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p318,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp318,idEncuestado).equals("0")) return false;
        data.close();
        return true;
    }

    public void showCapitulo200(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("LA FECHA DE NACIMIENTO NO COINCIDE CON LA EDAD (P205_A)" +
                "¿Desea corregir la edad?")
                .setTitle("AVISO:")
                .setCancelable(false)
                .setNegativeButton("SI",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ArrayList<Residente> residentes = DAOUtils.getRedidentes(idHogar,context);
                                Residente residente = DAOUtils.getResidente(idEncuestado,context);
                                Intent intent = new Intent(context, AgregarResidenteActivity.class);
                                intent.putExtra("idEncuestado", idEncuestado);
                                intent.putExtra("numero", residente.getNumero());
                                intent.putExtra("idHogar", idHogar);
                                intent.putExtra("idVivienda", idVivienda);
                                intent.putExtra("idJefeHogar", residentes.get(0).get_id());
                                intent.putExtra("vista", "2");
                                startActivity(intent);
                                dialog.cancel();
                                //cleanModulos();
                            }
                        })
                .setPositiveButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void validateResidentesVenezolanos(){
        Hogar hogar = DAOUtils.getHogar(idHogar,context);
        if(!hogar.getP15_o().equals(hogar.getP17())){
            Toast.makeText(context,"LOS MIGRANTES VENEZOLANOS REGISTRADOS NO COINCIDEN CON LA PREGUNTA 15",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        llenarVariables();
        edad = Integer.parseInt(DAOUtils.getResidente(idEncuestado,context).getC2_p205_a());
    }
}
