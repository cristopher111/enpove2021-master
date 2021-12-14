package com.example.ricindigus.enpove2021.fragments.modulo6;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo6;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOLayout;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethods;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP601P604 extends FragmentPagina {
    String idEncuestado;
    String idVivienda, idHogar, idInformante, id_informante="";
    Context context;

    Spinner informanteSpinner;
    // TextView c6_p601_TextView;
    RadioGroup c6_p601_RadioGroup, c6_p602_RadioGroup, c6_p603_RadioGroup;
    RadioGroup c6_p604_1_RadioGroup;

//    RadioGroup c6_p604_1_RadioGroup, c6_p604_2_RadioGroup, c6_p604_3_RadioGroup, c6_p604_4_RadioGroup,
//            c6_p604_5_RadioGroup, c6_p604_6_RadioGroup, c6_p604_7_RadioGroup, c6_p604_8_RadioGroup,
//            c6_p604_9_RadioGroup, c6_p604_10_RadioGroup, c6_p604_11_RadioGroup;
//    EditText c6_p604_o_EditText;
    LinearLayout m6_p601_linearlayout, m6_p602_linearlayout, m6_p603_linearlayout, m6_p604_linearlayout;

    TextView tv601periodo,tv602periodo;

    private String c6_p601;
    private String c6_p602;
    private String c6_p603;

    private String c6_p604_1;
    private String c6_p604_2;
    private String c6_p604_3;
    private String c6_p604_4;
    private String c6_p604_5;
    private String c6_p604_6;
    private String c6_p604_7;
    private String c6_p604_8;
    private String c6_p604_9;
    private String c6_p604_10;
    private String c6_p604_11;
    private String c6_p604_o;

    boolean corresponde=false;
    int edad=0;

    @SuppressLint("ValidFragment")
    public FragmentP601P604(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idHogar = residente.getId_hogar();
        idVivienda = residente.getId_vivienda();
        idInformante = "";
        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        data.close();
    }

    public FragmentP601P604() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p601_p604, container, false);
        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

     //   c6_p601_TextView = (TextView) rootView.findViewById(R.id.mod6_601_textview_C6_P601);
        c6_p601_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_601_radiogroup_C6_P601);
        c6_p602_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_602_radiogroup_C6_P602);
        c6_p603_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_603_radiogroup_C6_P603);

       c6_p604_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_604_radiogroup_C6_P604_1);

        tv601periodo = (TextView) rootView.findViewById(R.id.tv601periodo);
        tv602periodo = (TextView) rootView.findViewById(R.id.tv602periodo);

        //  c6_p604_2_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_604_radiogroup_C6_P604_2);
        //c6_p604_3_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_604_radiogroup_C6_P604_3);
        //c6_p604_4_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_604_radiogroup_C6_P604_4);
        //c6_p604_5_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_604_radiogroup_C6_P604_5);
        // c6_p604_6_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_604_radiogroup_C6_P604_6);
        //  c6_p604_7_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_604_radiogroup_C6_P604_7);
        // c6_p604_8_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_604_radiogroup_C6_P604_8);
        // c6_p604_9_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_604_radiogroup_C6_P604_9);
        // c6_p604_10_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_604_radiogroup_C6_P604_10);
        // c6_p604_11_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_604_radiogroup_C6_P604_11);
        // c6_p604_o_EditText = (EditText) rootView.findViewById(R.id.mod6_604_edittext_C6_P604_O);
        m6_p601_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p601);
        m6_p602_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p602);
        m6_p603_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p603);
        m6_p604_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p604);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String text601 = getString(R.string.modulo_6_p601, UtilsMethods.getPeriodoReferenciaSemana(1));
        tv601periodo.setText(text601);

        String text602 = getString(R.string.modulo_6_p602, UtilsMethods.getPeriodoReferenciaSemana(1));
        tv602periodo.setText(text602);

        c6_p601_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c6_p601_RadioGroup.findViewById(c6_p601_RadioGroup.getCheckedRadioButtonId()));
//                : SI P601=2 Entonces pase a P603
//                SI P601=1 Entonces pase a P602
                if(pos==1) {
                    m6_p602_linearlayout.setVisibility(View.VISIBLE);
                    limpiar603();
                    m6_p603_linearlayout.setVisibility(View.GONE);
                    limpiar604();
                    m6_p604_linearlayout.setVisibility(View.GONE);
                }else{
                    c6_p602_RadioGroup.clearCheck();
                    m6_p602_linearlayout.setVisibility(View.GONE);
                    m6_p603_linearlayout.setVisibility(View.VISIBLE);

                }
            }
        });

        c6_p602_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c6_p602_RadioGroup.findViewById(c6_p602_RadioGroup.getCheckedRadioButtonId()));
//                : SI P602>0 Entonces pase a P609
                if(pos==1){
//                    validarFlujo68();
                    c6_p603_RadioGroup.clearCheck();
                    m6_p603_linearlayout.setVisibility(View.GONE);
                    c6_p604_1_RadioGroup.clearCheck();
                    m6_p604_linearlayout.setVisibility(View.GONE);

                }else{
                    c6_p603_RadioGroup.clearCheck();
                    m6_p603_linearlayout.setVisibility(View.GONE);
                    c6_p604_1_RadioGroup.clearCheck();
                    m6_p604_linearlayout.setVisibility(View.GONE);
                }
            }
        });


        c6_p603_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c6_p603_RadioGroup.findViewById(c6_p603_RadioGroup.getCheckedRadioButtonId()));
//                : SI P603=1 Entonces pase a P606
//		            SI P603=2 Entonces pase a P604
                if(pos==1){
                    c6_p604_1_RadioGroup.clearCheck();
                    m6_p604_linearlayout.setVisibility(View.GONE);
                }else{
                    m6_p604_linearlayout.setVisibility(View.VISIBLE);
                }
            }
        });

//        c6_p601_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
//                int pos = radioGroup.indexOfChild(c6_p601_RadioGroup.findViewById(c6_p601_RadioGroup.getCheckedRadioButtonId()));
//                if(pos==1){
//                    m6_p602_linearlayout.setVisibility(View.GONE); limpiar_p602();
//                    m6_p603_linearlayout.setVisibility(View.GONE); limpiar_p603();
//                    m6_p604_linearlayout.setVisibility(View.GONE); limpiar_p604();
//                }else{
//                    m6_p602_linearlayout.setVisibility(View.VISIBLE);
//                    m6_p603_linearlayout.setVisibility(View.VISIBLE);
//                    m6_p604_linearlayout.setVisibility(View.VISIBLE);
//                    c6_p602_RadioGroup.requestFocus();
//                }
//            }
//        });
//        c6_p602_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
//                int pos = radioGroup.indexOfChild(c6_p602_RadioGroup.findViewById(c6_p602_RadioGroup.getCheckedRadioButtonId()));
//                if(pos==1){
//                    m6_p603_linearlayout.setVisibility(View.GONE); limpiar_p603();
//                    m6_p604_linearlayout.setVisibility(View.GONE); limpiar_p604();
//                }else{
//                    m6_p603_linearlayout.setVisibility(View.VISIBLE);
//                    m6_p604_linearlayout.setVisibility(View.VISIBLE);
//                    c6_p603_RadioGroup.requestFocus();
//                }
//            }
//        });
//        c6_p603_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
//                int pos = radioGroup.indexOfChild(c6_p603_RadioGroup.findViewById(c6_p603_RadioGroup.getCheckedRadioButtonId()));
//                if(pos==1){
//                    m6_p604_linearlayout.setVisibility(View.GONE); limpiar_p604();
//                }else{
//                    m6_p604_linearlayout.setVisibility(View.VISIBLE);
//                    c6_p604_1_RadioGroup.requestFocus();
//                }
//            }
//        });
//        c6_p604_11_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
//                int pos = radioGroup.indexOfChild(c6_p604_11_RadioGroup.findViewById(c6_p604_11_RadioGroup.getCheckedRadioButtonId()));
//                if(pos==1){
//                    c6_p604_o_EditText.setEnabled(true);
//                    c6_p604_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
//                }else{
//                    c6_p604_o_EditText.setText("");
//                    c6_p604_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
//                    c6_p604_o_EditText.setEnabled(false);
//                }
//            }
//        });
//
//        configurarEditText(c6_p604_o_EditText,m6_p604_linearlayout,0,30);
//        cargarDatos();

        llenarVista();
        cargarDatos();
    }

    private void limpiar604() {
        c6_p604_1_RadioGroup.clearCheck();
    }

    private void limpiar603() {
        c6_p603_RadioGroup.clearCheck();
    }

    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo6_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo6_c6_p601,c6_p601);
        contentValues.put(SQLConstantes.modulo6_c6_p602,c6_p602);
        contentValues.put(SQLConstantes.modulo6_c6_p603,c6_p603);
        contentValues.put(SQLConstantes.modulo6_c6_p604_1,c6_p604_1);
//        contentValues.put(SQLConstantes.modulo6_c6_p604_2,c6_p604_2);
//        contentValues.put(SQLConstantes.modulo6_c6_p604_3,c6_p604_3);
//        contentValues.put(SQLConstantes.modulo6_c6_p604_4,c6_p604_4);
//        contentValues.put(SQLConstantes.modulo6_c6_p604_5,c6_p604_5);
//        contentValues.put(SQLConstantes.modulo6_c6_p604_6,c6_p604_6);
//        contentValues.put(SQLConstantes.modulo6_c6_p604_7,c6_p604_7);
//        contentValues.put(SQLConstantes.modulo6_c6_p604_8,c6_p604_8);
//        contentValues.put(SQLConstantes.modulo6_c6_p604_9,c6_p604_9);
//        contentValues.put(SQLConstantes.modulo6_c6_p604_10,c6_p604_10);
//        contentValues.put(SQLConstantes.modulo6_c6_p604_11,c6_p604_11);
//        contentValues.put(SQLConstantes.modulo6_c6_p604_o,c6_p604_o);

        if(!data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo6 modulo6 = new Modulo6();
            modulo6.set_id(idEncuestado);
            modulo6.setIdHogar(idHogar);
            modulo6.setIdVivienda(idVivienda);
            data.insertarElemento(getNombreTabla(),modulo6.toValues());
        }
        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp601p604,"1",idEncuestado);
        //ocultamos o mostramos preguntas o fragments
        ocultarOtrosLayouts();
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();
    }

    @Override
    public void llenarVariables() {
        idInformante = obtener_Nresidente(informanteSpinner);
//        String[] infor_id = (informanteSpinner.getItemAtPosition(informanteSpinner.getSelectedItemPosition()).toString()).split("-");
//        id_informante = idHogar + "_" + infor_id[0];
        c6_p601 = c6_p601_RadioGroup.indexOfChild(c6_p601_RadioGroup.findViewById(c6_p601_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p602 = c6_p602_RadioGroup.indexOfChild(c6_p602_RadioGroup.findViewById(c6_p602_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p603 = c6_p603_RadioGroup.indexOfChild(c6_p603_RadioGroup.findViewById(c6_p603_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p604_1 = c6_p604_1_RadioGroup.indexOfChild(c6_p604_1_RadioGroup.findViewById(c6_p604_1_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p604_2 = c6_p604_2_RadioGroup.indexOfChild(c6_p604_2_RadioGroup.findViewById(c6_p604_2_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p604_3 = c6_p604_3_RadioGroup.indexOfChild(c6_p604_3_RadioGroup.findViewById(c6_p604_3_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p604_4 = c6_p604_4_RadioGroup.indexOfChild(c6_p604_4_RadioGroup.findViewById(c6_p604_4_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p604_5 = c6_p604_5_RadioGroup.indexOfChild(c6_p604_5_RadioGroup.findViewById(c6_p604_5_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p604_6 = c6_p604_6_RadioGroup.indexOfChild(c6_p604_6_RadioGroup.findViewById(c6_p604_6_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p604_7 = c6_p604_7_RadioGroup.indexOfChild(c6_p604_7_RadioGroup.findViewById(c6_p604_7_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p604_8 = c6_p604_8_RadioGroup.indexOfChild(c6_p604_8_RadioGroup.findViewById(c6_p604_8_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p604_9 = c6_p604_9_RadioGroup.indexOfChild(c6_p604_9_RadioGroup.findViewById(c6_p604_9_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p604_10 = c6_p604_10_RadioGroup.indexOfChild(c6_p604_10_RadioGroup.findViewById(c6_p604_10_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p604_11 = c6_p604_11_RadioGroup.indexOfChild(c6_p604_11_RadioGroup.findViewById(c6_p604_11_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p604_o = c6_p604_o_EditText.getText().toString();
    }

    @Override
    public void cargarDatos() {
        Data data = new Data(context);
        data.open();
        if (data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo6 modulo6 = data.getModulo6(idEncuestado);
//            ArrayList<String> residentes = data.getListaSpinnerResidentesHogar(modulo6.getIdHogar());
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,residentes);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            informanteSpinner.setAdapter(adapter);
            ArrayList<String> informantes = data.getListaInformantesMayor12(modulo6.getIdHogar());
            UtilsMethodsInputs.loadSpinner(informantes,informanteSpinner,context);

            if(modulo6.getIdInformante() != null && !modulo6.getIdInformante().equals("")) informanteSpinner.setSelection(obtener_posicion(modulo6.getIdInformante(),informanteSpinner));
            if(!modulo6.getC6_p601().equals("-1") && !modulo6.getC6_p601().equals(""))((RadioButton)c6_p601_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p601()))).setChecked(true);
            if(!modulo6.getC6_p602().equals("-1") && !modulo6.getC6_p602().equals(""))((RadioButton)c6_p602_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p602()))).setChecked(true);
            if(!modulo6.getC6_p603().equals("-1") && !modulo6.getC6_p603().equals(""))((RadioButton)c6_p603_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p603()))).setChecked(true);
            if(!modulo6.getC6_p604_1().equals("-1") && !modulo6.getC6_p604_1().equals(""))((RadioButton)c6_p604_1_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p604_1()))).setChecked(true);
//            if(!modulo6.getC6_p604_2().equals("-1") && !modulo6.getC6_p604_2().equals(""))((RadioButton)c6_p604_2_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p604_2()))).setChecked(true);
//            if(!modulo6.getC6_p604_3().equals("-1") && !modulo6.getC6_p604_3().equals(""))((RadioButton)c6_p604_3_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p604_3()))).setChecked(true);
//            if(!modulo6.getC6_p604_4().equals("-1") && !modulo6.getC6_p604_4().equals(""))((RadioButton)c6_p604_4_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p604_4()))).setChecked(true);
//            if(!modulo6.getC6_p604_5().equals("-1") && !modulo6.getC6_p604_5().equals(""))((RadioButton)c6_p604_5_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p604_5()))).setChecked(true);
//            if(!modulo6.getC6_p604_6().equals("-1") && !modulo6.getC6_p604_6().equals(""))((RadioButton)c6_p604_6_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p604_6()))).setChecked(true);
//            if(!modulo6.getC6_p604_7().equals("-1") && !modulo6.getC6_p604_7().equals(""))((RadioButton)c6_p604_7_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p604_7()))).setChecked(true);
//            if(!modulo6.getC6_p604_8().equals("-1") && !modulo6.getC6_p604_8().equals(""))((RadioButton)c6_p604_8_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p604_8()))).setChecked(true);
//            if(!modulo6.getC6_p604_9().equals("-1") && !modulo6.getC6_p604_9().equals(""))((RadioButton)c6_p604_9_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p604_9()))).setChecked(true);
//            if(!modulo6.getC6_p604_10().equals("-1") && !modulo6.getC6_p604_10().equals(""))((RadioButton)c6_p604_10_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p604_10()))).setChecked(true);
//            if(!modulo6.getC6_p604_11().equals("-1") && !modulo6.getC6_p604_11().equals(""))((RadioButton)c6_p604_11_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p604_11()))).setChecked(true);
//            c6_p604_o_EditText.setText(modulo6.getC6_p604_o());

        }
       // inicio();  CREO QUE ESTE ES PARA LAS FECHAS
        data.close();
    }

//    private void inicio() {
//
////Todas las personas AND P205_A>=5 AND P601=1
//        if(edad >= 5  && (c6_p601.equals("1"))){
//            m6_p602_linearlayout.setVisibility(View.VISIBLE);
//            validarFlujo68();
//        } else {
//             limpiar603();
//            m6_p603_linearlayout.setVisibility(View.GONE);
//            limpiar604();
//            m6_p604_linearlayout.setVisibility(View.GONE);
//
//        }
//    }

//    private void validarFlujo68() {
//        llenarVariables();
//
//        if(edad >= 5  && (c6_p601.equals("1"))){
//            m6_p602_linearlayout.setVisibility(View.VISIBLE);
//        }else if(edad >= 5  && (c6_p601.equals("2"))){
//            m6_p603_linearlayout.setVisibility(View.VISIBLE);
//        }
//    }
//
//    private void limpiar604() {
//        c6_p604_1_RadioGroup.clearCheck();
//    }
//
//    private void limpiar603() {
//        c6_p603_RadioGroup.clearCheck();
//    }

    @Override
    public void llenarVista() {

    }

    @Override
    public boolean validarDatos() {
        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        //PREGUNTA 601
        if (m6_p601_linearlayout.getVisibility()==View.VISIBLE){
            if(c6_p601.equals("-1")){
                mostrarMensaje("PREGUNTA 601: DEBE SELECCIONAR UNA OPCION");
                return false;
            }
        }else{
            c6_p601 = "";
        }

        //PREGUNTA 602
        if (m6_p602_linearlayout.getVisibility()==View.VISIBLE){
            if(c6_p602.equals("-1")){
                mostrarMensaje("PREGUNTA 602: DEBE SELECCIONAR UNA OPCION");
                return false;
            }
        }else{
            c6_p602 = "";
        }

        //PREGUNTA 603
        if (m6_p603_linearlayout.getVisibility()==View.VISIBLE){
            if(c6_p603.equals("-1")){
                mostrarMensaje("PREGUNTA 603: DEBE SELECCIONAR UNA OPCION");
                return false;
            }
        }else{
            c6_p603 = "";
        }

        //PREGUNTA 604
        if (m6_p604_linearlayout.getVisibility()==View.VISIBLE){
            if(c6_p604_1.equals("-1")){
                mostrarMensaje("PREGUNTA 604: DEBE SELECCIONAR UNA OPCION");
                return false;
            }
        }else{
            c6_p604_1 = "";
        }

//
//        if(!corresponde)  {mostrarMensaje("PERIODO DE REFERENCIA NO CORRESPONDE A FECHA DE LA ENCUESTA");return false;}
//        if(idInformante.equals("0")){mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}
//        if(!id_informante.equals(idEncuestado) && edad>=12){mostrarMensaje("NÚMERO INFORMANTE: NO ES EL MISMO QUE ESTA SIENDO ENTREVISTADO");return false;}
//        if(c6_p601.equals("-1")){
//            mostrarMensaje("PREGUNTA 601: DEBE SELECCIONAR UNA OPCION");
//            return false;
//        }
//        if (m6_p602_linearlayout.getVisibility()==View.VISIBLE){
//            if(c6_p602.equals("-1")){ mostrarMensaje("PREGUNTA 602: DEBE SELECCIONAR UNA OPCION");return false; }
//        }else c6_p602 = "";
//
//        if (m6_p603_linearlayout.getVisibility()==View.VISIBLE){
//            if(c6_p603.equals("-1")){ mostrarMensaje("PREGUNTA 603: DEBE SELECCIONAR UNA OPCION");return false; }
//        }else c6_p603 = "";
//
//
//        if(m6_p604_linearlayout.getVisibility()==View.VISIBLE) {
//            if(c6_p604_1.equals("-1")){ mostrarMensaje("PREGUNTA 604-1: DEBE SELECCIONAR UNA OPCION");return false; }
//            if(c6_p604_2.equals("-1")){ mostrarMensaje("PREGUNTA 604-2: DEBE SELECCIONAR UNA OPCION");return false; }
//            if(c6_p604_3.equals("-1")){ mostrarMensaje("PREGUNTA 604-3: DEBE SELECCIONAR UNA OPCION");return false; }
//            if(c6_p604_4.equals("-1")){ mostrarMensaje("PREGUNTA 604-4: DEBE SELECCIONAR UNA OPCION");return false; }
//            if(c6_p604_5.equals("-1")){ mostrarMensaje("PREGUNTA 604-5: DEBE SELECCIONAR UNA OPCION");return false; }
//            if(c6_p604_6.equals("-1")){ mostrarMensaje("PREGUNTA 604-6: DEBE SELECCIONAR UNA OPCION");return false; }
//            if(c6_p604_7.equals("-1")){ mostrarMensaje("PREGUNTA 604-7: DEBE SELECCIONAR UNA OPCION");return false; }
//            if(c6_p604_8.equals("-1")){ mostrarMensaje("PREGUNTA 604-8: DEBE SELECCIONAR UNA OPCION");return false; }
//            if(c6_p604_9.equals("-1")){ mostrarMensaje("PREGUNTA 604-9: DEBE SELECCIONAR UNA OPCION");return false; }
//            if(c6_p604_10.equals("-1")){ mostrarMensaje("PREGUNTA 604-10: DEBE SELECCIONAR UNA OPCION");return false;}
//            if(c6_p604_11.equals("-1")){ mostrarMensaje("PREGUNTA 604-11: DEBE SELECCIONAR UNA OPCION");return false; }
//            if(c6_p604_11.equals("1")){
//                if(c6_p604_o.trim().equals("")){ mostrarMensaje("PREGUNTA 604 - OPCION 11: DEBE ESPECIFICAR OTRO");return false; }
//            }
//        }else{
//            c6_p604_1 = "";
//            c6_p604_2 = "";
//            c6_p604_3 = "";
//            c6_p604_4 = "";
//            c6_p604_5 = "";
//            c6_p604_6 = "";
//            c6_p604_7 = "";
//            c6_p604_8 = "";
//            c6_p604_9 = "";
//            c6_p604_10 = "";
//            c6_p604_11 = "";
//            c6_p604_o = "";
//        }
        return true;
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablamodulo6;
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

//    public void limpiar_p601(){
//        c6_p601_RadioGroup.clearCheck();
//    }
//
//    public void limpiar_p602(){
//        c6_p602_RadioGroup.clearCheck();
//    }
//
//    public void limpiar_p603(){
//        c6_p603_RadioGroup.clearCheck();
//    }
//
//    public void limpiar_p604(){
//        c6_p604_1_RadioGroup.clearCheck(); c6_p604_2_RadioGroup.clearCheck(); c6_p604_3_RadioGroup.clearCheck();
//        c6_p604_4_RadioGroup.clearCheck(); c6_p604_5_RadioGroup.clearCheck(); c6_p604_6_RadioGroup.clearCheck();
//        c6_p604_7_RadioGroup.clearCheck(); c6_p604_8_RadioGroup.clearCheck(); c6_p604_9_RadioGroup.clearCheck();
//        c6_p604_10_RadioGroup.clearCheck(); c6_p604_11_RadioGroup.clearCheck();
//        c6_p604_o_EditText.setText("");
//    }

    public String NombreMes(int mes){
        String nom_mes="";
        switch(mes){
            case 0: nom_mes="ENERO"; break;
            case 1: nom_mes="FEBRERO"; break;
            case 2: nom_mes="MARZO"; break;
            case 3: nom_mes="ABRIL"; break;
            case 4: nom_mes="MAYO"; break;
            case 5: nom_mes="JUNIO"; break;
            case 6: nom_mes="JULIO"; break;
            case 7: nom_mes="AGOSTO"; break;
            case 8: nom_mes="SETIEMBRE"; break;
            case 9: nom_mes="OCTUBRE"; break;
            case 10: nom_mes="NOVIEMBRE"; break;
            case 11: nom_mes="DICIEMBRE"; break;
        }
        return nom_mes;
    }
//
//    public void fecha(){
//        corresponde=false;
//        Calendar calendario;
//        int aa=0, mm=0, dd=0, hora=0, min=0,mes_ini=0,dia_ini=0,mes_fin=0,dia_fin=0;
//        String fecha_inicial="", fecha_final="";
//        calendario = Calendar.getInstance();
//        aa = calendario.get(Calendar.YEAR);
//        mm = calendario.get(Calendar.MONTH);
//        dd = calendario.get(Calendar.DAY_OF_MONTH);
//        hora = calendario.get(Calendar.HOUR_OF_DAY);
//
////        Log.e("aa", "fecha: "+aa );
////        Log.e("mm", "fecha: "+mm );
////        Log.e("dd", "fecha: "+dd );
////        Log.e("hora", "fecha: "+hora );
//
//        if(aa==2018){
//            if(mm==10){
//                if(dd>=20 && dd<=23){
//                    mes_ini = 10; dia_ini = 11; mes_fin = 10; dia_fin = 17;
//                    corresponde = true;
//                }else if(dd==24){
//                    if(hora<12){
//                        mes_ini = 10; dia_ini = 11; mes_fin = 10; dia_fin = 17;
//                        corresponde = true;
//                    }else{
//                        mes_ini = 10; dia_ini = 18; mes_fin = 10; dia_fin = 24;
//                        corresponde = true;
//                    }
//                }else if(dd>=25 && dd<=30){
//                    mes_ini = 10; dia_ini = 18; mes_fin = 10; dia_fin = 24;
//                    corresponde = true;
//                }
//            }else if(mm==11){
//                if(dd==1){
//                    if(hora<12){
//                        mes_ini = 10; dia_ini = 18; mes_fin = 10; dia_fin = 24;
//                        corresponde = true;
//                    }else{
//                        mes_ini = 10; dia_ini = 25; mes_fin = 11; dia_fin = 1;
//                        corresponde = true;
//                    }
//                }else if(dd>=2 && dd<=7){
//                    mes_ini = 10; dia_ini = 25; mes_fin = 11; dia_fin = 1;
//                    corresponde = true;
//                }else if(dd==8){
//                    if(hora<12){
//                        mes_ini = 10; dia_ini = 25; mes_fin = 11; dia_fin = 1;
//                        corresponde = true;
//                    }else{
//                        mes_ini = 11; dia_ini = 2; mes_fin = 11; dia_fin = 8;
//                        corresponde = true;
//                    }
//                }else if(dd>=9 && dd<=14){
//                    mes_ini = 11; dia_ini = 2; mes_fin = 11; dia_fin = 8;
//                    corresponde = true;
//                }else if(dd==15){
//                    if(hora<12){
//                        mes_ini = 11; dia_ini = 2; mes_fin = 11; dia_fin = 8;
//                        corresponde = true;
//                    }else{
//                        mes_ini = 11; dia_ini = 9; mes_fin = 11; dia_fin = 15;
//                        corresponde = true;
//                    }
//                }else if(dd>=16 && dd<=21){
//                    mes_ini = 11; dia_ini = 9; mes_fin = 11; dia_fin = 15;
//                    corresponde = true;
//                }else if(dd==22){
//                    if(hora<12){
//                        mes_ini = 11; dia_ini = 9; mes_fin = 11; dia_fin = 15;
//                        corresponde = true;
//                    }else{
//                        mes_ini = 11; dia_ini = 16; mes_fin = 11; dia_fin = 22;
//                        corresponde = true;
//                    }
//                }else if(dd>=23 && dd<=28){
//                    mes_ini = 11; dia_ini = 16; mes_fin = 11; dia_fin = 22;
//                    corresponde = true;
//                }else if(dd==29){
//                    if(hora<12){
//                        mes_ini = 11; dia_ini = 16; mes_fin = 11; dia_fin = 22;
//                        corresponde = true;
//                    }
//                }
//            }
//        }
//
//        if(corresponde){
//            fecha_final = "" + dia_fin + " DE " + NombreMes(mes_fin);
//            fecha_inicial = "" + dia_ini + " DE " + NombreMes(mes_ini);
//            String enunciado_p601 = c6_p601_TextView.getText()+"";
//            enunciado_p601 = enunciado_p601.replace("FECHAINI", fecha_inicial);
//            enunciado_p601 = enunciado_p601.replace("FECHAFIN", fecha_final);
//            c6_p601_TextView.setText(enunciado_p601);
//        }else{
//            fecha_final = "" + dd + " DE " + NombreMes(mm);
//            calendario.add(Calendar.DAY_OF_MONTH,-7);
//            mm = calendario.get(Calendar.MONTH);
//            dd = calendario.get(Calendar.DAY_OF_MONTH);
//            fecha_inicial = "" + dd + " DE " + NombreMes(mm);
//            String enunciado_p601 = c6_p601_TextView.getText()+"";
//            enunciado_p601 = enunciado_p601.replace("FECHAINI", fecha_inicial);
//            enunciado_p601 = enunciado_p601.replace("FECHAFIN", fecha_final);
//            c6_p601_TextView.setText(enunciado_p601);
//        }
//
//
//    }



//    public void inicio(){
//        fecha();
//    }

    public void ocultarOtrosLayouts() {
        llenarVariables();
        //FLUJO 70
        if(c6_p604_1.equals("1")){
            ocultarFragmentP605P608(false); // Muestra fragment del P606
        }else
        if(c6_p604_1.equals("2")){
            ocultarFragmentP605P608(false); // Muestra fragment del P605
        }else
        //FLUJO 69
        if(c6_p603.equals("1")){
            ocultarFragmentP605P608(false); // Muestra fragment del P606
        }else
        //FLUJO 68
        if(!c6_p602.equals("-1")){
            ocultarFragmentP605P608(true);
            ocultarFragmentP609P612(false); // Muestra fragment del P609
        }else Log.e("FragmentP601P604","No cumplen condiciones en ocultarOtrosLayouts()");
    }

    private void ocultarFragmentP605P608(Boolean ocultar){
        Data data = new Data(context);
        data.open();
        if(ocultar){
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.modulo6_c6_p605_1, "");
            contentValues.put(SQLConstantes.modulo6_c6_p605_2, "");
            contentValues.put(SQLConstantes.modulo6_c6_p605_3, "");
            contentValues.put(SQLConstantes.modulo6_c6_p605_4, "");
            contentValues.put(SQLConstantes.modulo6_c6_p605_5, "");
            contentValues.put(SQLConstantes.modulo6_c6_p605_6, "");
            contentValues.put(SQLConstantes.modulo6_c6_p605_7, "");
            contentValues.put(SQLConstantes.modulo6_c6_p605_8, "");
            contentValues.put(SQLConstantes.modulo6_c6_p605_9, "");
            contentValues.put(SQLConstantes.modulo6_c6_p605_10, "");
            contentValues.put(SQLConstantes.modulo6_c6_p605_11, "");
            contentValues.put(SQLConstantes.modulo6_c6_p605_12, "");
            contentValues.put(SQLConstantes.modulo6_c6_p605_o, "");
            contentValues.put(SQLConstantes.modulo6_c6_p606, "");
            contentValues.put(SQLConstantes.modulo6_c6_p606_o, "");
            contentValues.put(SQLConstantes.modulo6_c6_p607, "");
            contentValues.put(SQLConstantes.modulo6_c6_p608, "");
            data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p605p608,"-1",idEncuestado);
        }else {
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p605p608,"1",idEncuestado);
        }
        data.close();
    }
    private void ocultarFragmentP609P612(Boolean ocultar){
        Data data = new Data(context);
        data.open();
        if(ocultar){
            ContentValues contentValues1 = new ContentValues();
            contentValues1.put(SQLConstantes.modulo6_c6_p609, "");
            contentValues1.put(SQLConstantes.modulo6_c6_p610_pd, "");
            contentValues1.put(SQLConstantes.modulo6_c6_p611, "");
            contentValues1.put(SQLConstantes.modulo6_c6_p612, "");
            contentValues1.put(SQLConstantes.modulo6_c6_p613, "");
            contentValues1.put(SQLConstantes.modulo6_c6_p614_esp, "");
            contentValues1.put(SQLConstantes.modulo6_c6_p614_mon, "");
            data.actualizarElemento(getNombreTabla(),contentValues1,idEncuestado);
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p609p612,"-1", idEncuestado);
        }else {
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p609p612,"1",idEncuestado); //Muestra e Fragment de la P609
        }
        data.close();
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
//        Data data = new Data(context);
//        data.open();
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p601p604,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp601p604,idEncuestado).equals("0")) return false;
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p605p608,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp605p608,idEncuestado).equals("0")) return false;
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p609p612,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp609p612,idEncuestado).equals("0")) return false;
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp613p617,idEncuestado).equals("0")) return false;
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p618p621,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp618p621,idEncuestado).equals("0")) return false;
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp622p625,idEncuestado).equals("0")) return false;
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p626p629,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp626p629,idEncuestado).equals("0")) return false;
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p630,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp630,idEncuestado).equals("0")) return false;
//        data.close();
        return true;
    }

}
