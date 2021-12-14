package com.example.ricindigus.enpove2021.fragments.modulo5;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo5;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP512P513 extends FragmentPagina {
    String idEncuestado;
    String idInformante;
    Context context;

    Spinner informanteSpinner;
    RadioGroup c5_p512_RadioGroup, c5_p513_RadioGroup;
    RadioGroup c5_p514_RadioGroup, c5_p515_RadioGroup, c5_p516_RadioGroup;
    //    EditText c5_p512_o_EditText, c5_p513_o_EditText;
    EditText c5_p514_o_EditText, c5_p515_o_EditText, c5_p516_o_EditText;
    LinearLayout m5_p512_linearlayout, m5_p513_linearlayout, m5_p514_linearlayout;
    RelativeLayout m5_p515_linearlayout, m5_p516_linearlayout;
    TextView sub_modulo_5_5_textview,mod_5_etnicidad_encabezado;

    private String c5_p512;
    //    private String c5_p512_o;
    private String c5_p513;
    //    private String c5_p513_o;
    private String c5_p514;
    private String c5_p514_o;
    private String c5_p515;
    private String c5_p515_o;
    private String c5_p516;
    private String c5_p516_o;

    private int edad=0;
    private String p501;

    @SuppressLint("ValidFragment")
    public FragmentP512P513(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
//        idHogar = residente.getId_hogar();
//        idVivienda = residente.getId_vivienda();
        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        p501 = data.getModulo5(idEncuestado).getC5_p501();
        idInformante = "";
        Log.e("fragmentP508p5011","p501 = "+p501);
        data.close();
    }

    public FragmentP512P513() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p512_p513, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);
        sub_modulo_5_5_textview = (TextView) rootView.findViewById(R.id.sub_modulo_5_5_textview);
        c5_p512_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_512_radiogroup_C5_P512);
        // c5_p512_o_EditText = (EditText) rootView.findViewById(R.id.mod5_512_edittext_C5_P512_O);
        c5_p513_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_513_radiogroup_C5_P513);
        // c5_p513_o_EditText = (EditText) rootView.findViewById(R.id.mod5_513_edittext_C5_P513_O);
        //Anthony M
        mod_5_etnicidad_encabezado = (TextView) rootView.findViewById(R.id.mod_5_etnicidad);
        c5_p514_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_514_radiogroup_C5_P514);
        c5_p514_o_EditText = (EditText) rootView.findViewById(R.id.mod5_514_edittext_C5_P514_O);
        c5_p515_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_515_radiogroup_C5_P515);
        c5_p515_o_EditText = (EditText) rootView.findViewById(R.id.mod5_515_edittext_C5_P515_O);
        c5_p516_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_516_radiogroup_C5_P516);
        c5_p516_o_EditText = (EditText) rootView.findViewById(R.id.mod5_516_edittext_C5_P516_O);

        m5_p512_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p512);
        m5_p513_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p513);
        //Anthony
        m5_p514_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p514x);
        m5_p515_linearlayout = (RelativeLayout) rootView.findViewById(R.id.layout_m5_p515x);
        m5_p516_linearlayout = (RelativeLayout) rootView.findViewById(R.id.layout_m5_p516x);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Anthony M
        configurarEditText(c5_p514_o_EditText,m5_p514_linearlayout,0,30); //FLUJO 63
        configurarEditText(c5_p515_o_EditText,m5_p515_linearlayout,0,30); //FLUJO 64
        configurarEditText(c5_p516_o_EditText,m5_p516_linearlayout,0,30); //FLUJO 65
        c5_p512_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(validar_P513()) m5_p513_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p513();m5_p513_linearlayout.setVisibility(View.GONE);}
                if(validar_P514()) m5_p514_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p514();m5_p514_linearlayout.setVisibility(View.GONE);}
            }
        });
        c5_p513_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(validar_P514()) m5_p514_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p514();m5_p514_linearlayout.setVisibility(View.GONE);}
            }
        });
        c5_p514_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group,checkedId,4,c5_p514_o_EditText);
            }
        });
        c5_p515_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group,checkedId,6,c5_p515_o_EditText);
            }
        });
        c5_p516_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group,checkedId,7,c5_p516_o_EditText);
            }
        });
        //Anthony M 07/05/2021
        c5_p512_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { //FLUJO 61
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                if(seleccionado == 2){
                    limpiar_p513();limpiar_p514();
                    m5_p513_linearlayout.setVisibility(View.GONE);
                    m5_p514_linearlayout.setVisibility(View.GONE);
                }else if(seleccionado == 1){
                    m5_p513_linearlayout.setVisibility(View.VISIBLE);
//                    m5_p514_linearlayout.setVisibility(View.VISIBLE);
                }
            }
        });
        c5_p513_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { //FLUJO 62
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                if(seleccionado == 1){
                    limpiar_p514();
                    m5_p514_linearlayout.setVisibility(View.GONE);
                }else if(seleccionado == 2){
                    m5_p514_linearlayout.setVisibility(View.VISIBLE);
                }
            }
        });

        /*
        configurarEditText(c5_p512_o_EditText,m5_p512_linearlayout,0,30);
        configurarEditText(c5_p513_o_EditText,m5_p513_linearlayout,0,30);

        c5_p512_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group,checkedId,6,c5_p512_o_EditText);
            }
        });

        c5_p513_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group,checkedId,7,c5_p513_o_EditText);
            }
        });*/
        cargarDatos();


    }

    private void inicio() {
        Log.e("Fragment","Estas en P512P513");
        //P512
        if(validar_P512()) m5_p512_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p512();m5_p512_linearlayout.setVisibility(View.GONE);}
        //P513
        if(validar_P513()) m5_p513_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p513();m5_p513_linearlayout.setVisibility(View.GONE);}
        //P514
        if(validar_P514()) m5_p514_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p514();m5_p514_linearlayout.setVisibility(View.GONE);}
        //P515
        if(validar_P515()) m5_p515_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p515();m5_p515_linearlayout.setVisibility(View.GONE);}
        //P516
        if(validar_P516()) m5_p516_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p516();m5_p516_linearlayout.setVisibility(View.GONE);}
        //CABECERA TEXTVIEW
        if(validar_P512() || validar_P513() || validar_P514())
            sub_modulo_5_5_textview.setVisibility(View.VISIBLE);
        else {
            sub_modulo_5_5_textview.setVisibility(View.GONE);
        }
        //CABECERA 2 TEXTVIEW
        if(validar_P515() || validar_P516())
            mod_5_etnicidad_encabezado.setVisibility(View.VISIBLE);
        else {
            mod_5_etnicidad_encabezado.setVisibility(View.GONE);
        }
//        llenarVariables();
//        if((p501.equals("10") || p501.equals("11"))){ //FLUJO 61 - UNIVERSO modificado 18/05/2021
//            sub_modulo_5_5_textview.setVisibility(View.VISIBLE);
//            m5_p512_linearlayout.setVisibility(View.VISIBLE);
//            if(c5_p512.equals("1")){//FLUJO 62 - UNIVERSO
//                m5_p513_linearlayout.setVisibility(View.VISIBLE);
//                if(c5_p513.equals("2")){//FLUJO 63 - UNIVERSO
//                    m5_p514_linearlayout.setVisibility(View.VISIBLE);
//                }else {limpiar_p514();m5_p514_linearlayout.setVisibility(View.GONE);}
//            }else {
//                limpiar_p513();
//                m5_p513_linearlayout.setVisibility(View.GONE);
//            }
//        }else {
//            sub_modulo_5_5_textview.setVisibility(View.GONE);
//            limpiar_p512();limpiar_p513();limpiar_p514();
//            m5_p512_linearlayout.setVisibility(View.GONE); //FLUJO 61 - UNIVERSO
//            m5_p513_linearlayout.setVisibility(View.GONE); //FLUJO 62 - UNIVERSO
//            m5_p514_linearlayout.setVisibility(View.GONE); //FLUJO 63 - UNIVERSO
//        }
//        if(edad >= 14){ //FLUJO 64 FLUJO 65 - UNIVERSO
//            m5_p515_linearlayout.setVisibility(View.VISIBLE);
//            m5_p516_linearlayout.setVisibility(View.VISIBLE);
//        }else {
//            limpiar_p515();limpiar_p516();
//            m5_p515_linearlayout.setVisibility(View.GONE);
//            m5_p516_linearlayout.setVisibility(View.GONE);
//        }
//        if(m5_p514_linearlayout.getVisibility() == View.GONE && m5_p516_linearlayout.getVisibility() ==View.GONE)
//            mod_5_etnicidad_encabezado.setVisibility(View.GONE);
//        else
//            mod_5_etnicidad_encabezado.setVisibility(View.VISIBLE);
    }

    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo5_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo5_c5_p512,c5_p512);
//        contentValues.put(SQLConstantes.modulo5_c5_p512_o,c5_p512_o);
        contentValues.put(SQLConstantes.modulo5_c5_p513,c5_p513);
//        contentValues.put(SQLConstantes.modulo5_c5_p513_o,c5_p513_o);
        //Anthony M
        contentValues.put(SQLConstantes.modulo5_c5_p514,c5_p514);
        contentValues.put(SQLConstantes.modulo5_c5_p514_o,c5_p514_o);
        contentValues.put(SQLConstantes.modulo5_c5_p515,c5_p515);
        contentValues.put(SQLConstantes.modulo5_c5_p515_o,c5_p515_o);
        contentValues.put(SQLConstantes.modulo5_c5_p516,c5_p516);
        contentValues.put(SQLConstantes.modulo5_c5_p516_o,c5_p516_o);

        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp512p513,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo5_COB500,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo5_COB500,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();
    }

    @Override
    public void llenarVariables() {

        idInformante = obtener_Nresidente(informanteSpinner);
        c5_p512 = c5_p512_RadioGroup.indexOfChild(c5_p512_RadioGroup.findViewById(c5_p512_RadioGroup.getCheckedRadioButtonId()))+"";
//        c5_p512_o = c5_p512_o_EditText.getText().toString();
        c5_p513 = c5_p513_RadioGroup.indexOfChild(c5_p513_RadioGroup.findViewById(c5_p513_RadioGroup.getCheckedRadioButtonId()))+"";
//        c5_p513_o = c5_p513_o_EditText.getText().toString();
        //Anthony M
        c5_p514 = c5_p514_RadioGroup.indexOfChild(c5_p514_RadioGroup.findViewById(c5_p514_RadioGroup.getCheckedRadioButtonId()))+"";
        c5_p514_o = c5_p514_o_EditText.getText().toString();
        c5_p515 = c5_p515_RadioGroup.indexOfChild(c5_p515_RadioGroup.findViewById(c5_p515_RadioGroup.getCheckedRadioButtonId()))+"";
        c5_p515_o = c5_p515_o_EditText.getText().toString();
        c5_p516 = c5_p516_RadioGroup.indexOfChild(c5_p516_RadioGroup.findViewById(c5_p516_RadioGroup.getCheckedRadioButtonId()))+"";
        c5_p516_o = c5_p516_o_EditText.getText().toString();

    }

    @Override
    public void cargarDatos() {
        Data data = new Data(context);
        data.open();
        if (data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo5 modulo5 = data.getModulo5(idEncuestado);
            ArrayList<String> informantes = data.getListaInformantesMayor12(modulo5.getIdHogar());
            UtilsMethodsInputs.loadSpinner(informantes,informanteSpinner,context);
            if(modulo5.getIdInformante() != null && !modulo5.getIdInformante().equals("")) informanteSpinner.setSelection(obtener_posicion(modulo5.getIdInformante(),informanteSpinner));
            if(modulo5.getC5_p512() != null && !modulo5.getC5_p512().equals("-1") && !modulo5.getC5_p512().equals(""))((RadioButton)c5_p512_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p512()))).setChecked(true);
//            c5_p512_o_EditText.setText(modulo5.getC5_p512_o());
            if(modulo5.getC5_p513() != null && !modulo5.getC5_p513().equals("-1") && !modulo5.getC5_p513().equals(""))((RadioButton)c5_p513_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p513()))).setChecked(true);
//            c5_p513_o_EditText.setText(modulo5.getC5_p513_o());
            //Anthony M
            if(modulo5.getC5_p514() != null && !modulo5.getC5_p514().equals("-1") && !modulo5.getC5_p514().equals(""))((RadioButton)c5_p514_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p514()))).setChecked(true);
            c5_p514_o_EditText.setText(modulo5.getC5_p514_o());
            if(modulo5.getC5_p515() != null && !modulo5.getC5_p515().equals("-1") && !modulo5.getC5_p515().equals(""))((RadioButton)c5_p515_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p515()))).setChecked(true);
            c5_p515_o_EditText.setText(modulo5.getC5_p515_o());
            if(modulo5.getC5_p516() != null && !modulo5.getC5_p516().equals("-1") && !modulo5.getC5_p516().equals(""))((RadioButton)c5_p516_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p516()))).setChecked(true);
            c5_p516_o_EditText.setText(modulo5.getC5_p516_o());
        }
        inicio();
        data.close();
    }

    @Override
    public void llenarVista() {

    }

    @Override
    public boolean validarDatos() {

        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        if(m5_p512_linearlayout.getVisibility()==View.VISIBLE) {
            if(c5_p512.equals("-1")){ mostrarMensaje("PREGUNTA 512: DEBE SELECCIONAR UNA OPCION");return false;
            }
//            if(c5_p512.equals("6")){
//                if(c5_p512_o.trim().equals("")){ mostrarMensaje("PREGUNTA 512 - OPCION 6: DEBE ESPECIFICAR SI MARCÓ OTRO");return false; }
//            }
        } else{
            c5_p512 = "";
        }

        if(m5_p513_linearlayout.getVisibility()==View.VISIBLE) {
            if(c5_p513.equals("-1")){ mostrarMensaje("PREGUNTA 513: DEBE SELECCIONAR UNA OPCION");return false; }
//            if(c5_p513.equals("7")){
//                if(c5_p513_o.trim().equals("")){ mostrarMensaje("PREGUNTA 513 - OPCION 7: DEBE ESPECIFICAR SI MARCÓ OTRO");return false; }
//            }
        }else {
            c5_p513 = "";
        }
        if(m5_p514_linearlayout.getVisibility()==View.VISIBLE) {
            if(c5_p514.equals("-1")){ mostrarMensaje("PREGUNTA 514: DEBE SELECCIONAR UNA OPCION");return false; }
            if(c5_p514_o_EditText.isEnabled() && c5_p514_o.equals("")){ mostrarMensaje("PREGUNTA 514: DEBE COMPLETAR OTRO");return false; }
        }else {
            c5_p514 = "";
            c5_p514_o = "";
        }
        if(m5_p515_linearlayout.getVisibility()==View.VISIBLE) {
            if(c5_p515.equals("-1")){ mostrarMensaje("PREGUNTA 515: DEBE SELECCIONAR UNA OPCION");return false; }
            if(c5_p515.equals("6")){
                if (c5_p515_o.trim().equals("")){
                    mostrarMensaje("PREGUNTA 515: DEBE COMPLETAR OTRO");return false; }

                if (c5_p515_o.length()<3){
                    mostrarMensaje("ERROR  “P515. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }
            }

        }else {
            c5_p515 = "";
            c5_p515_o = "";
        }
        if(m5_p516_linearlayout.getVisibility()==View.VISIBLE) {
            if(c5_p516.equals("-1")){ mostrarMensaje("PREGUNTA 516: DEBE SELECCIONAR UNA OPCION");return false; }
            if(c5_p516.equals("7")){
                if (c5_p516_o.trim().equals("")){
                    mostrarMensaje("PREGUNTA 516: DEBE COMPLETAR OTRO");return false;
                }
                if (c5_p516_o.length()<3){
                    mostrarMensaje("ERROR  “P516. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }
            }

            //FRANCISCO PIDIO QUE SE COMENTARA EN SUS OBSERVACIONES 01 06 2021
//            if(!c5_p516.equals("7")){ mostrarMensaje("SI DESEA PUEDE ANOTAR OBSERVACIONES");}//18052021


        }else {
            c5_p516 = "";
            c5_p516_o = "";
        }

        return true;
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablamodulo5;
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
                }
            }
        }catch (Exception e){posicion = 0;}
        return posicion;
    }

    public void mostrarMensaje(String m){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(m);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void mostrarTeclado(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
/*
    public void limpiar_512_513(){
        c5_p512_RadioGroup.clearCheck();
        c5_p512_o_EditText.setText("");
        c5_p513_RadioGroup.clearCheck();
        c5_p513_o_EditText.setText("");
    }

 */

    private void controlarEspecifiqueRadio(RadioGroup group, int checkedId, int opcionEsp, EditText editTextEspecifique) {
        int seleccionado = group.indexOfChild(group.findViewById(checkedId));
        Log.e("Modulo 5","seleccionado = "+seleccionado);
        if(seleccionado == opcionEsp){
            editTextEspecifique.setBackgroundResource(R.drawable.input_text_enabled);
            editTextEspecifique.setEnabled(true);
        }else{
            editTextEspecifique.setText("");
            editTextEspecifique.setBackgroundResource(R.drawable.input_text_disabled);
            editTextEspecifique.setEnabled(false);
        }
    }

    private void configurarEditText(final EditText editText, final View viewLayout, int tipo,int longitud){
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
                    viewLayout.requestFocus();
                    return true;
                }
                return false;
            }
        });
    }


    public void controlarChecked(CheckBox checkBox, final EditText editText){
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    editText.setBackgroundResource(R.drawable.input_text_enabled);
                    editText.setEnabled(true);
                }else{
                    editText.setText("");
                    editText.setBackgroundResource(R.drawable.input_text_disabled);
                    editText.setEnabled(false);
                }
            }
        });
    }
    public boolean verificarCoberturaCapitulo(){
        /*
        Data data = new Data(context);
        data.open();
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p501p505,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp501p505,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p506p507,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp506p507,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p508p511,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp508p511,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p512p513,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp512p513,idEncuestado).equals("0")) return false;
        data.close();

         */
        return true;
    }
    public void limpiar_p512(){
        c5_p512_RadioGroup.clearCheck();
    }
    public void limpiar_p513(){
        c5_p513_RadioGroup.clearCheck();
    }
    public void limpiar_p514(){
        c5_p514_RadioGroup.clearCheck();
        c5_p514_o_EditText.setText("");
    }
    public void limpiar_p515(){
        c5_p515_RadioGroup.clearCheck();
        c5_p515_o_EditText.setText("");
    }
    public void limpiar_p516(){
        c5_p516_RadioGroup.clearCheck();
        c5_p516_o_EditText.setText("");
    }
    public boolean validar_P512(){
        boolean valido = false;
        llenarVariables();
        if(p501.equals("10") || p501.equals("11"))
            valido = true;
        return valido;
    }
    public boolean validar_P513(){
        boolean valido = false;
        llenarVariables();
        if((p501.equals("10") || p501.equals("11")) && c5_p512.equals("1"))
            valido = true;
        return valido;
    }
    public boolean validar_P514(){
        boolean valido = false;
        llenarVariables();
        if((p501.equals("10") || p501.equals("11")) && c5_p513.equals("2"))
            valido = true;
        return valido;
    }
    public boolean validar_P515(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 14)
            valido = true;
        return valido;
    }
    public boolean validar_P516(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 14)
            valido = true;
        return valido;
    }
}
