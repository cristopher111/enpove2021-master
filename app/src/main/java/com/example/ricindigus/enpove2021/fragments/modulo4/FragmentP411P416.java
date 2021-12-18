package com.example.ricindigus.enpove2021.fragments.modulo4;


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
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.activities.EncuestaActivity;
import com.example.ricindigus.enpove2021.activities.HogarActivity;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Hogar;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo4;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethods;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP411P416 extends FragmentPagina {
    String idHogar;
    String idEncuestado;
    String idInformante;
    Context context;


    Spinner informanteSpinner;
    /*
    CheckBox c4_p411_1_CheckBox, c4_p411_2_CheckBox, c4_p411_3_CheckBox, c4_p411_4_CheckBox, c4_p411_5_CheckBox,
            c4_p411_6_CheckBox, c4_p411_7_CheckBox, c4_p411_8_CheckBox, c4_p411_9_CheckBox, c4_p411_10_CheckBox,
            c4_p411_11_CheckBox, c4_p411_12_CheckBox, c4_p411_13_CheckBox, c4_p411_14_CheckBox;
    EditText c4_p411_o_EditText;
    RadioGroup c4_p412_RadioGroup, c4_p413_RadioGroup, c4_p414_RadioGroup, c4_p415_RadioGroup;*/
    RadioGroup c4_p413_1_RadioGroup, c4_p413_2_RadioGroup, c4_p413_3_RadioGroup, c4_p413_4_RadioGroup;
    CheckBox c4_p413_5_CheckBox;

    RadioGroup c4_p414_RadioGroup, c4_p415_RadioGroup;
    /*CheckBox c4_p416_1_CheckBox, c4_p416_2_CheckBox, c4_p416_3_CheckBox, c4_p416_4_CheckBox, c4_p416_5_CheckBox,
            c4_p416_6_CheckBox, c4_p416_7_CheckBox, c4_p416_8_CheckBox;*/
    CheckBox c4_p416_1_CheckBox, c4_p416_2_CheckBox, c4_p416_3_CheckBox, c4_p416_4_CheckBox, c4_p416_5_CheckBox, c4_p416_6_CheckBox; // Anthony M. 30/04/2021
    EditText c4_p416_5_o_EditText, c4_p417_1a_o_EditText;/////////////FALTA AGREGAR ESTA VARIALBLE OBSERVACION "c4_p417_1a_o_EditText" A LO DEMAS SOLO ESTA VISUAL

//    CheckBox c4_p417_1_CheckBox, c4_p417_2_CheckBox, c4_p417_3_CheckBox, c4_p417_4_CheckBox, c4_p417_5_CheckBox, c4_p417_6_CheckBox, c4_p417_7_CheckBox, c4_p417_8_CheckBox, c4_p417_9_CheckBox;
//    EditText c4_p417_7_o_EditText;
    CheckBox c4_p417_1_1_CheckBox, c4_p417_1_2_CheckBox,c4_p417_1_3_CheckBox,c4_p417_1_4_CheckBox,c4_p417_1_5_CheckBox,c4_p417_1_6_CheckBox,c4_p417_1_7_CheckBox,c4_p417_1_8_CheckBox,c4_p417_1_9_CheckBox,
        c4_p417_1_10_CheckBox,c4_p417_1_11_CheckBox, c4_p417_1_12_CheckBox,c4_p417_1_13_CheckBox;
    EditText c4_p417_1_13_o_EditText;

    RadioGroup c4_p417_1_RadioGroup,c4_p417_2_RadioGroup, c4_p417_3_RadioGroup, c4_p417_4_RadioGroup;
    EditText c4_p417_4_o_EditText;
    RadioGroup c4_p417_1a_RadioGroup;


    RadioGroup c4_p418_RadioGroup, c4_p418a_RadioGroup; // Anthony M. 30/04/2021
    //    LinearLayout m4_p411_linearlayout, m4_p412_linearlayout, m4_p413_linearlayout, m4_p414_linearlayout, m4_p415_linearlayout, m4_p416_linearlayout;
    LinearLayout m4_p413_linearlayout, m4_p414_linearlayout, m4_p415_linearlayout, m4_p416_linearlayout, m4_p417_linearlayout, m4_p418_linearlayout, m4_p418a_linearlayout;
    LinearLayout layout_m4_417_a,layout_m4_417_4;
    /*
        private String c4_p411_1;
        private String c4_p411_2;
        private String c4_p411_3;
        private String c4_p411_4;
        private String c4_p411_5;
        private String c4_p411_6;
        private String c4_p411_7;
        private String c4_p411_8;
        private String c4_p411_9;
        private String c4_p411_10;
        private String c4_p411_11;
        private String c4_p411_12;
        private String c4_p411_13;
        private String c4_p411_14;
        private String c4_p411_o;
        private String c4_p412;
        private String c4_p413;*/
    // Anthony 30/04/2021
    private String c4_p413_1;
    private String c4_p413_2;
    private String c4_p413_3;
    private String c4_p413_4;
    private String c4_p413_5;

    private String c4_p414;
    private String c4_p415;
    private String c4_p416_1;
    private String c4_p416_2;
    private String c4_p416_3;
    private String c4_p416_4;
    private String c4_p416_5;
    private String c4_p416_6;
    //    private String c4_p416_7;
//    private String c4_p416_8;
//    private String c4_p416_o;
    // Anthony 30/04/2021
    private String c4_p416_5_o;

    private String c4_p417_1;
    private String c4_p417_1a;
    private String c4_p417_2;
    private String c4_p417_3;
    private String c4_p417_4;
    private String c4_p417_4_o;
    private String c4_p417_1a_o;

    //Agregado 1/12/21
    private String c4_p417_1_1;
    private String c4_p417_1_2;
    private String c4_p417_1_3;
    private String c4_p417_1_4;
    private String c4_p417_1_5;
    private String c4_p417_1_6;
    private String c4_p417_1_7;
    private String c4_p417_1_8;
    private String c4_p417_1_9;
    private String c4_p417_1_10;
    private String c4_p417_1_11;
    private String c4_p417_1_12;
    private String c4_p417_1_13;
    private String c4_p417_1_13_o;


//    private String c4_p417_5;
//    private String c4_p417_6;
//    private String c4_p417_7;
//    private String c4_p417_8;
//    private String c4_p417_9;
//    private String c4_p417_7_o;

    private String c4_p418;
    private String c4_p418a;

    TextView tv413periodo;
    TextView tv414periodo;

    String p212;

    //    private int edad, sexo;
    private int anio_p205_a = 0, mes_p205_b = 0;
    private String sexo_p204 = "";
    private String parentesco_p203 = "";

    private String vive = "";
    private boolean p418_contestado = false;

    private Residente jefehogar= new Residente();


    @SuppressLint("ValidFragment")
    public FragmentP411P416(String idHogar,String idEncuestado, Context context) {
        this.idHogar = idHogar;
        this.idEncuestado = idEncuestado;
        this.context = context;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);

        Hogar hogar = data.getHogar(idHogar);

        jefehogar = data.getNombreResidente(idHogar,"1");

        vive= hogar.getVive();
        p212 = residente.getC2_p212();

        if(residente.getC2_p205_a().length()>0)
            anio_p205_a = Integer.parseInt(data.getResidente(idEncuestado).getC2_p205_a());
        if(residente.getC2_p205_m().length()>0)
            mes_p205_b  = Integer.parseInt(data.getResidente(idEncuestado).getC2_p205_m());

        parentesco_p203 = residente.getC2_p203();

//        idHogar = residente.getId_hogar();
//        idVivienda = residente.getId_vivienda();
        idInformante = "";
        if(residente.getC2_p204() != null && !residente.getC2_p204().equals(""))
            sexo_p204 = residente.getC2_p204().toString();
//        if(residente.getC2_p204()=="") sexo = -1; else sexo = Integer.parseInt(residente.getC2_p204());
//        if(residente.getC2_p205_a()=="") edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        if(data.getAllResidentesHogar(idHogar).size() > 0){
            for(int i=0 ; i < data.getAllResidentesHogar(idHogar).size() ; i++){
                if(!data.getAllResidentesHogar(idHogar).get(i).get_id().equals(idEncuestado)){
                    String cadena = "";
                    try{
                        cadena = data.getModulo4(data.getAllResidentesHogar(idHogar).get(i).get_id()).getC4_p418();
                    }catch (Exception e){}
                    if(!cadena.equals("")) {
                        p418_contestado = true;
                        break;
                    }
                }
            }
        }
        Log.e("const",""+p418_contestado);
        data.close();
    }

    public FragmentP411P416() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p411_p416, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);
        //Anthony M. 30/04/2021
        c4_p413_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_413_radiogroup_C4_P413_1);
        c4_p413_2_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_413_radiogroup_C4_P413_2);
        c4_p413_3_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_413_radiogroup_C4_P413_3);
        c4_p413_4_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_413_radiogroup_C4_P413_4);
        c4_p413_5_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_413_checkbox_C4_P413_5);

        c4_p414_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_414_radiogroup_C4_P414);
        c4_p415_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_415_radiogroup_C4_P415);
        c4_p416_1_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_416_checkbox_C4_P416_1);
        c4_p416_2_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_416_checkbox_C4_P416_2);
        c4_p416_3_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_416_checkbox_C4_P416_3);
        c4_p416_4_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_416_checkbox_C4_P416_4);
        c4_p416_5_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_416_checkbox_C4_P416_5);
//        c4_p416_o_EditText = (EditText) rootView.findViewById(R.id.mod4_416_edittext_C4_P416_O);
        c4_p416_6_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_416_checkbox_C4_P416_6);
        // c4_p416_7_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_416_checkbox_C4_P416_7);
        // c4_p416_8_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_416_checkbox_C4_P416_8);

        // Anthony M. 30/04/2021
        c4_p416_5_o_EditText = (EditText) rootView.findViewById(R.id.mod4_416_edittext_C4_P416_O);

        c4_p417_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_1);
        c4_p417_1a_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_A);
        ///////////////////////AGREGADO 1/12/21 OBSERVACION 102//////////////////
        c4_p417_1_1_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_1_1);
        c4_p417_1_2_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_1_2);
        c4_p417_1_3_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_1_3);
        c4_p417_1_4_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_1_4);
        c4_p417_1_5_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_1_5);
        c4_p417_1_6_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_1_6);
        c4_p417_1_7_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_1_7);
        c4_p417_1_8_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_1_8);
        c4_p417_1_9_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_1_9);
        c4_p417_1_10_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_1_10);
        c4_p417_1_11_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_1_11);
        c4_p417_1_12_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_1_12);
        c4_p417_1_13_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_1_13);
        c4_p417_1_13_o_EditText = (EditText) rootView.findViewById(R.id.mod4_417_edittext_C4_P417_1_13_O);


        c4_p417_1a_o_EditText = (EditText) rootView.findViewById(R.id.mod4_417_edittext_C4_P417_O);
        c4_p417_2_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_2);
        c4_p417_3_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_3);
        c4_p417_4_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_4);
        c4_p417_4_o_EditText = (EditText) rootView.findViewById(R.id.mod4_417_checkbox_C4_P417_4_o);
        c4_p418_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_p418_radiogroup_C2_P418);
        c4_p418a_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_p418_radiogroup_C2_P418_si_no);

        //(RadioButton) rootView.findViewById(R.id.p418_rb_1);

        tv413periodo  = (TextView) rootView.findViewById(R.id.tv413periodo);
        tv414periodo  = (TextView) rootView.findViewById(R.id.tv414periodo);

//        m4_p411_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p411);
//        m4_p412_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p412);
        m4_p413_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p413);
        m4_p414_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p414);
        m4_p415_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p415);
        m4_p416_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p416);
        // Anthony M. 30/04/2021
        m4_p417_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p417x);
        layout_m4_417_a = (LinearLayout) rootView.findViewById(R.id.layout_m4_417_a);
        layout_m4_417_4= (LinearLayout) rootView.findViewById(R.id.layout_m4_417_4);
        m4_p418_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p418x);
        m4_p418a_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p418a);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String text413 = getString(R.string.modulo_4_p413, UtilsMethods.getPeriodoReferenciaMes(4));
        tv413periodo.setText(text413);

        String text414 = getString(R.string.modulo_4_p414, UtilsMethods.getPeriodoReferenciaMes(6));
        tv414periodo.setText(text414);

//        configurarEditText(c4_p411_o_EditText,m4_p411_linearlayout,0,30);
//        configurarEditText(c4_p416_o_EditText,m4_p416_linearlayout,0,30);
        configurarEditText(c4_p416_5_o_EditText,m4_p416_linearlayout,0,50); //Anthony M 01/05/2021

       // configurarEditText(c4_p417_7_o_EditText,m4_p416_linearlayout,0,50); //Anthony M 01/05/2021
        configurarEditText(c4_p417_4_o_EditText,layout_m4_417_4,0,50);
        //configurarEditText(c4_p417_1a_o_EditText,layout_m4_417_a,0,50);
        configurarEditText(c4_p417_1_13_o_EditText,layout_m4_417_a,0,50);


//        controlarChecked(c4_p411_13_CheckBox,c4_p411_o_EditText);
//        controlarChecked(c4_p416_6_CheckBox,c4_p416_o_EditText);
//        controlarChecked(c4_p416_5_CheckBox,c4_p416_5_o_EditText); //Anthony M 01/05/2021 //FLUJO 48
//        controlarChecked(c4_p417_7_CheckBox,c4_p417_7_o_EditText); //Anthony M 01/05/2021 //FLUJO 49
        //Anthony M 06/05/2021
        c4_p413_4_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
//                validarFlujo45();
//                int pos = c4_p413_4_RadioGroup.indexOfChild(c4_p418_RadioGroup.findViewById(c4_p418_RadioGroup.getCheckedRadioButtonId()));
//                if(pos == 1){ //FLUJO 45
//                    m4_p418a_linearlayout.setVisibility(View.GONE); limpiar_p418a();
//                }else{
//                    m4_p418a_linearlayout.setVisibility(View.VISIBLE);
//                }
            }
        });
        //Anthony M 01/05/2021
        c4_p413_5_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                validarFlujo45();
//                if(b){
//                    c4_p413_1_RadioGroup.clearCheck();
//                    bloquearRadioGroup(c4_p413_1_RadioGroup);
//                    c4_p413_2_RadioGroup.clearCheck();
//                    bloquearRadioGroup(c4_p413_2_RadioGroup);
//                    c4_p413_3_RadioGroup.clearCheck();
//                    bloquearRadioGroup(c4_p413_3_RadioGroup);
//                    c4_p413_4_RadioGroup.clearCheck();
//                    bloquearRadioGroup(c4_p413_4_RadioGroup);
//                }else{
//                    desbloquearRadioGroup(c4_p413_1_RadioGroup);
//                    desbloquearRadioGroup(c4_p413_2_RadioGroup);
//                    desbloquearRadioGroup(c4_p413_3_RadioGroup);
//                    desbloquearRadioGroup(c4_p413_4_RadioGroup);
//                }
            }
        });
        c4_p414_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                //P415
                if(validar_P415()) m4_p415_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p415();m4_p415_linearlayout.setVisibility(View.GONE);}
                //P416
                if(validar_P416()) m4_p416_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p416();m4_p416_linearlayout.setVisibility(View.GONE);}
                //P417
                if(validar_P417()) m4_p417_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p417();m4_p417_linearlayout.setVisibility(View.GONE);}
                //P418
                if(validar_P418()) m4_p418_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p418();m4_p418_linearlayout.setVisibility(View.GONE);}
                //P418A
                if(validar_P418a()) m4_p418a_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p418a();m4_p418a_linearlayout.setVisibility(View.GONE);}
//                int pos = radioGroup.indexOfChild(c4_p414_RadioGroup.findViewById(c4_p414_RadioGroup.getCheckedRadioButtonId()));
//                if(pos != 1){ //FLUJO 46
//                    limpiar_p415();
//                    limpiar_p416();
//                    m4_p415_linearlayout.setVisibility(View.GONE);
//                    m4_p416_linearlayout.setVisibility(View.GONE);
//                }else{
//                    m4_p415_linearlayout.setVisibility(View.VISIBLE);
//                    m4_p416_linearlayout.setVisibility(View.VISIBLE);
//                }
            }
        });
        c4_p415_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                //P416
                if(validar_P416()) m4_p416_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p416();m4_p416_linearlayout.setVisibility(View.GONE);}
                //P417
                if(validar_P417()) m4_p417_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p417();m4_p417_linearlayout.setVisibility(View.GONE);}
                //P418
                if(validar_P418()) m4_p418_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p418();m4_p418_linearlayout.setVisibility(View.GONE);}
                //P418A
                if(validar_P418a()) m4_p418a_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p418a();m4_p418a_linearlayout.setVisibility(View.GONE);}
//                int pos = radioGroup.indexOfChild(c4_p415_RadioGroup.findViewById(c4_p415_RadioGroup.getCheckedRadioButtonId()));
//                if(pos == 2){ //FLUJO 47
//                    limpiar_p416();
//                    m4_p416_linearlayout.setVisibility(View.GONE);
//                }else if(pos == 1){
//                    m4_p416_linearlayout.setVisibility(View.VISIBLE);
//                }
            }
        });
        c4_p416_1_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                validarAchurar416();
            }
        });
        c4_p416_2_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                validarAchurar416();
            }
        });
        c4_p416_3_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                validarAchurar416();
            }
        });
        c4_p416_4_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                validarAchurar416();
            }
        });
        c4_p416_5_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                validarAchurar416();
                if(b) {//FLUJO 48 //REGLA 57
                    c4_p416_5_o_EditText.setEnabled(true);
                    c4_p416_5_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }
                else {
                    c4_p416_5_o_EditText.setText("");
                    c4_p416_5_o_EditText.setEnabled(false);
                    c4_p416_5_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });

        c4_p417_1_13_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {//FLUJO 48 //REGLA 57
                    c4_p417_1_13_o_EditText.setEnabled(true);
                    c4_p417_1_13_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }
                else {
                    c4_p417_1_13_o_EditText.setText("");
                    c4_p417_1_13_o_EditText.setEnabled(false);
                    c4_p417_1_13_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });
        c4_p418_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) { // FLUJO 50
                //P418A
                if(validar_P418a()) m4_p418a_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p418a();m4_p418a_linearlayout.setVisibility(View.GONE);}
//                int pos = radioGroup.indexOfChild(c4_p418_RadioGroup.findViewById(c4_p418_RadioGroup.getCheckedRadioButtonId()));
//                if(pos==2){
//                    m4_p418a_linearlayout.setVisibility(View.GONE); limpiar_p418a();
//                }else{
//                    m4_p418a_linearlayout.setVisibility(View.VISIBLE);
//                }
            }
        });
        c4_p416_6_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){ //REGLA 57
                    c4_p416_1_CheckBox.setChecked(false);
                    c4_p416_1_CheckBox.setEnabled(false);
                    c4_p416_2_CheckBox.setChecked(false);
                    c4_p416_2_CheckBox.setEnabled(false);
                    c4_p416_3_CheckBox.setChecked(false);
                    c4_p416_3_CheckBox.setEnabled(false);
                    c4_p416_4_CheckBox.setChecked(false);
                    c4_p416_4_CheckBox.setEnabled(false);
                    c4_p416_5_CheckBox.setChecked(false);
                    c4_p416_5_CheckBox.setEnabled(false);
                    c4_p416_5_o_EditText.setText(""); //Anthony M
//                    c4_p416_6_CheckBox.setChecked(false);
//                    c4_p416_6_CheckBox.setEnabled(false);
//                    c4_p416_7_CheckBox.setChecked(false);
//                    c4_p416_7_CheckBox.setEnabled(false);
//                    c4_p416_o_EditText.setText("");
//                    c4_p416_o_EditText.setBackgroundResource(R.drawable.cajas_de_texto_disabled);
                }else{
                    c4_p416_1_CheckBox.setEnabled(true);
                    c4_p416_2_CheckBox.setEnabled(true);
                    c4_p416_3_CheckBox.setEnabled(true);
                    c4_p416_4_CheckBox.setEnabled(true);
                    c4_p416_5_CheckBox.setEnabled(true);
//                    c4_p416_6_CheckBox.setEnabled(true);
//                    c4_p416_7_CheckBox.setEnabled(true);
                }
            }
        });


        c4_p417_1_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c4_p417_1_RadioGroup.findViewById(c4_p417_1_RadioGroup.getCheckedRadioButtonId()));
                if(pos==1){
                    //YA NO SE MUESTRA LA PREGUNTA 417_A
                    layout_m4_417_a.setVisibility(View.VISIBLE);
                    c4_p417_1_1_CheckBox.setEnabled(true);
                    c4_p417_1_2_CheckBox.setEnabled(true);
                    c4_p417_1_3_CheckBox.setEnabled(true);
                    c4_p417_1_4_CheckBox.setEnabled(true);
                    c4_p417_1_5_CheckBox.setEnabled(true);
                    c4_p417_1_6_CheckBox.setEnabled(true);
                    c4_p417_1_7_CheckBox.setEnabled(true);
                    c4_p417_1_8_CheckBox.setEnabled(true);
                    c4_p417_1_9_CheckBox.setEnabled(true);
                    c4_p417_1_10_CheckBox.setEnabled(true);
                    c4_p417_1_11_CheckBox.setEnabled(true);
                    c4_p417_1_12_CheckBox.setEnabled(true);
                    c4_p417_1_13_CheckBox.setEnabled(true);

                }else{
                    layout_m4_417_a.setVisibility(View.GONE);
                    //c4_p417_1a_RadioGroup.clearCheck();
                    c4_p417_1_1_CheckBox.setEnabled(false);
                    c4_p417_1_2_CheckBox.setEnabled(false);
                    c4_p417_1_3_CheckBox.setEnabled(false);
                    c4_p417_1_4_CheckBox.setEnabled(false);
                    c4_p417_1_5_CheckBox.setEnabled(false);
                    c4_p417_1_6_CheckBox.setEnabled(false);
                    c4_p417_1_7_CheckBox.setEnabled(false);
                    c4_p417_1_8_CheckBox.setEnabled(false);
                    c4_p417_1_9_CheckBox.setEnabled(false);
                    c4_p417_1_10_CheckBox.setEnabled(false);
                    c4_p417_1_11_CheckBox.setEnabled(false);
                    c4_p417_1_12_CheckBox.setEnabled(false);
                    c4_p417_1_13_CheckBox.setEnabled(false);

                }
            }
        });

        c4_p417_4_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c4_p417_4_RadioGroup.findViewById(c4_p417_4_RadioGroup.getCheckedRadioButtonId()));
                if(pos==1){
                    layout_m4_417_4.setVisibility(View.VISIBLE); //especifique
                    c4_p417_4_o_EditText.setEnabled(true);
                    c4_p417_4_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    layout_m4_417_4.setVisibility(View.GONE); //especifique
                    c4_p417_4_o_EditText.setText("");
                    c4_p417_4_o_EditText.setEnabled(false);
                    c4_p417_4_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
                if(validar_P418()){
                    m4_p418_linearlayout.setVisibility(View.VISIBLE);
                }else {
                    limpiar_p418();limpiar_p418a();
                    m4_p418_linearlayout.setVisibility(View.GONE);
                }
            }
        });


     /*   c4_p417_1a_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                controlarEspecifiqueRadio(radioGroup, i,13,c4_p417_1a_o_EditText);
            }
        });*/



        // Anthony M.
//        c4_p417_1_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                validarAchurar417();
//            }
//        });
//        c4_p417_2_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                validarAchurar417();
//            }
//        });
//        c4_p417_3_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                validarAchurar417();
//            }
//        });
//        c4_p417_4_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                validarAchurar417();
//            }
//        });
//        c4_p417_5_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                validarAchurar417();
//            }
//        });
//        c4_p417_6_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                validarAchurar417();
//            }
//        });
//        c4_p417_7_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                validarAchurar417(); //REGLA 58
//                if(b){ //FLUJO 49
//                    c4_p417_7_o_EditText.setEnabled(true);
//                    c4_p417_7_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
//                }else {
//                    c4_p417_7_o_EditText.setText("");
//                    c4_p417_7_o_EditText.setEnabled(false);
//                    c4_p417_7_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
//                }
//            }
//        });
//        c4_p417_8_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b){ //REGLA 58
//                    c4_p417_1_CheckBox.setChecked(false);
//                    c4_p417_1_CheckBox.setEnabled(false);
//                    c4_p417_2_CheckBox.setChecked(false);
//                    c4_p417_2_CheckBox.setEnabled(false);
//                    c4_p417_3_CheckBox.setChecked(false);
//                    c4_p417_3_CheckBox.setEnabled(false);
//                    c4_p417_4_CheckBox.setChecked(false);
//                    c4_p417_4_CheckBox.setEnabled(false);
//                    c4_p417_5_CheckBox.setChecked(false);
//                    c4_p417_5_CheckBox.setEnabled(false);
//                    c4_p417_6_CheckBox.setChecked(false);
//                    c4_p417_6_CheckBox.setEnabled(false);
//                    c4_p417_7_CheckBox.setChecked(false);
//                    c4_p417_7_CheckBox.setEnabled(false);
//                    c4_p417_7_o_EditText.setText("");
//                    c4_p417_9_CheckBox.setChecked(false);
//                    c4_p417_9_CheckBox.setEnabled(false);
//                }else{
//                    c4_p417_1_CheckBox.setEnabled(true);
//                    c4_p417_2_CheckBox.setEnabled(true);
//                    c4_p417_3_CheckBox.setEnabled(true);
//                    c4_p417_4_CheckBox.setEnabled(true);
//                    c4_p417_5_CheckBox.setEnabled(true);
//                    c4_p417_6_CheckBox.setEnabled(true);
//                    c4_p417_7_CheckBox.setEnabled(true);
//                    c4_p417_9_CheckBox.setEnabled(true);
//                }
//            }
//        });
//        c4_p417_9_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b){ //REGLA 58
//                    c4_p417_1_CheckBox.setChecked(false);
//                    c4_p417_1_CheckBox.setEnabled(false);
//                    c4_p417_2_CheckBox.setChecked(false);
//                    c4_p417_2_CheckBox.setEnabled(false);
//                    c4_p417_3_CheckBox.setChecked(false);
//                    c4_p417_3_CheckBox.setEnabled(false);
//                    c4_p417_4_CheckBox.setChecked(false);
//                    c4_p417_4_CheckBox.setEnabled(false);
//                    c4_p417_5_CheckBox.setChecked(false);
//                    c4_p417_5_CheckBox.setEnabled(false);
//                    c4_p417_6_CheckBox.setChecked(false);
//                    c4_p417_6_CheckBox.setEnabled(false);
//                    c4_p417_7_CheckBox.setChecked(false);
//                    c4_p417_7_CheckBox.setEnabled(false);
//                    c4_p417_7_o_EditText.setText("");
//                    c4_p417_8_CheckBox.setChecked(false);
//                    c4_p417_8_CheckBox.setEnabled(false);
//                }else{
//                    c4_p417_1_CheckBox.setEnabled(true);
//                    c4_p417_2_CheckBox.setEnabled(true);
//                    c4_p417_3_CheckBox.setEnabled(true);
//                    c4_p417_4_CheckBox.setEnabled(true);
//                    c4_p417_5_CheckBox.setEnabled(true);
//                    c4_p417_6_CheckBox.setEnabled(true);
//                    c4_p417_7_CheckBox.setEnabled(true);
//                    c4_p417_8_CheckBox.setEnabled(true);
//                }
//            }
//        });
        llenarVista();
        cargarDatos();

    }

    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo4_id_informante,idInformante);
//        contentValues.put(SQLConstantes.modulo4_c4_p411_1,c4_p411_1);
//        contentValues.put(SQLConstantes.modulo4_c4_p411_2,c4_p411_2);
//        contentValues.put(SQLConstantes.modulo4_c4_p411_3,c4_p411_3);
//        contentValues.put(SQLConstantes.modulo4_c4_p411_4,c4_p411_4);
//        contentValues.put(SQLConstantes.modulo4_c4_p411_5,c4_p411_5);
//        contentValues.put(SQLConstantes.modulo4_c4_p411_6,c4_p411_6);
//        contentValues.put(SQLConstantes.modulo4_c4_p411_7,c4_p411_7);
//        contentValues.put(SQLConstantes.modulo4_c4_p411_8,c4_p411_8);
//        contentValues.put(SQLConstantes.modulo4_c4_p411_9,c4_p411_9);
//        contentValues.put(SQLConstantes.modulo4_c4_p411_10,c4_p411_10);
//        contentValues.put(SQLConstantes.modulo4_c4_p411_11,c4_p411_11);
//        contentValues.put(SQLConstantes.modulo4_c4_p411_12,c4_p411_12);
//        contentValues.put(SQLConstantes.modulo4_c4_p411_13,c4_p411_13);
//        contentValues.put(SQLConstantes.modulo4_c4_p411_14,c4_p411_14);
//        contentValues.put(SQLConstantes.modulo4_c4_p411_o,c4_p411_o);
//        contentValues.put(SQLConstantes.modulo4_c4_p412,c4_p412);
//        contentValues.put(SQLConstantes.modulo4_c4_p413,c4_p413);
        //Anthony M. 30/04/2021
        contentValues.put(SQLConstantes.modulo4_c4_p413_1,c4_p413_1);
        contentValues.put(SQLConstantes.modulo4_c4_p413_2,c4_p413_2);
        contentValues.put(SQLConstantes.modulo4_c4_p413_3,c4_p413_3);
        contentValues.put(SQLConstantes.modulo4_c4_p413_4,c4_p413_4);
        contentValues.put(SQLConstantes.modulo4_c4_p413_5,c4_p413_5);

        contentValues.put(SQLConstantes.modulo4_c4_p414,c4_p414);
        contentValues.put(SQLConstantes.modulo4_c4_p415,c4_p415);
        contentValues.put(SQLConstantes.modulo4_c4_p416_1,c4_p416_1);
        contentValues.put(SQLConstantes.modulo4_c4_p416_2,c4_p416_2);
        contentValues.put(SQLConstantes.modulo4_c4_p416_3,c4_p416_3);
        contentValues.put(SQLConstantes.modulo4_c4_p416_4,c4_p416_4);
        contentValues.put(SQLConstantes.modulo4_c4_p416_5,c4_p416_5);
        contentValues.put(SQLConstantes.modulo4_c4_p416_6,c4_p416_6);
//        contentValues.put(SQLConstantes.modulo4_c4_p416_7,c4_p416_7);
//        contentValues.put(SQLConstantes.modulo4_c4_p416_8,c4_p416_8);
//        contentValues.put(SQLConstantes.modulo4_c4_p416_o,c4_p416_o);
        //Anthony M. 30/04/2021
        contentValues.put(SQLConstantes.modulo4_c4_p416_5_o,c4_p416_5_o);

        contentValues.put(SQLConstantes.modulo4_c4_p417_1,c4_p417_1);
        //contentValues.put(SQLConstantes.modulo4_c4_p417_1a,c4_p417_1a);
        //contentValues.put(SQLConstantes.modulo4_c4_p417_1a_o,c4_p417_1a_o);
        contentValues.put(SQLConstantes.modulo4_c4_p417_1_1,c4_p417_1_1);
        contentValues.put(SQLConstantes.modulo4_c4_p417_1_2,c4_p417_1_2);
        contentValues.put(SQLConstantes.modulo4_c4_p417_1_3,c4_p417_1_3);
        contentValues.put(SQLConstantes.modulo4_c4_p417_1_4,c4_p417_1_4);
        contentValues.put(SQLConstantes.modulo4_c4_p417_1_5,c4_p417_1_5);
        contentValues.put(SQLConstantes.modulo4_c4_p417_1_6,c4_p417_1_6);
        contentValues.put(SQLConstantes.modulo4_c4_p417_1_7,c4_p417_1_7);
        contentValues.put(SQLConstantes.modulo4_c4_p417_1_8,c4_p417_1_8);
        contentValues.put(SQLConstantes.modulo4_c4_p417_1_9,c4_p417_1_9);
        contentValues.put(SQLConstantes.modulo4_c4_p417_1_10,c4_p417_1_10);
        contentValues.put(SQLConstantes.modulo4_c4_p417_1_11,c4_p417_1_11);
        contentValues.put(SQLConstantes.modulo4_c4_p417_1_12,c4_p417_1_12);
        contentValues.put(SQLConstantes.modulo4_c4_p417_1_13,c4_p417_1_13);
        contentValues.put(SQLConstantes.modulo4_c4_p417_1_13_o,c4_p417_1_13_o);
        contentValues.put(SQLConstantes.modulo4_c4_p417_2,c4_p417_2);
        contentValues.put(SQLConstantes.modulo4_c4_p417_3,c4_p417_3);
        contentValues.put(SQLConstantes.modulo4_c4_p417_4,c4_p417_4);
        contentValues.put(SQLConstantes.modulo4_c4_p417_4_o,c4_p417_4_o);

//        contentValues.put(SQLConstantes.modulo4_c4_p417_5,c4_p417_5);
//        contentValues.put(SQLConstantes.modulo4_c4_p417_6,c4_p417_6);
//        contentValues.put(SQLConstantes.modulo4_c4_p417_7,c4_p417_7);
//        contentValues.put(SQLConstantes.modulo4_c4_p417_8,c4_p417_8);
//        contentValues.put(SQLConstantes.modulo4_c4_p417_9,c4_p417_9);
//        contentValues.put(SQLConstantes.modulo4_c4_p417_7_o,c4_p417_7_o);
        contentValues.put(SQLConstantes.modulo4_c4_p418,c4_p418);
        contentValues.put(SQLConstantes.modulo4_c4_p418a,c4_p418a);
        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp411p416,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo4_COB400,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo4_COB400,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();
        ocultarOtrosLayouts();
    }

    private void ocultarOtrosLayouts() {
        if((anio_p205_a == 1 || anio_p205_a == 2) &&
                (Integer.parseInt(c4_p413_1) > 0 && Integer.parseInt(c4_p413_2) > 0 &&Integer.parseInt(c4_p413_3) > 0 && Integer.parseInt(c4_p413_4) > 0 && Integer.parseInt(c4_p413_5) > 0)){
            // debe finalizar encuesta - Ya se encuentra programado en AgregarResidenteActivity
        }else
        if((anio_p205_a == 3 || anio_p205_a == 4) &&
                (Integer.parseInt(c4_p413_1) > 0 && Integer.parseInt(c4_p413_2) > 0 &&Integer.parseInt(c4_p413_3) > 0 && Integer.parseInt(c4_p413_4) > 0 && Integer.parseInt(c4_p413_5) > 0)){
            // continua con el modulo 5 - Ya se encuentra programado en AgregarResidenteActivity
        }
    }

    @Override
    public void llenarVariables() {

        idInformante = obtener_Nresidente(informanteSpinner);
        // Anthony M. 30/04/2021
        c4_p413_1 = c4_p413_1_RadioGroup.indexOfChild(c4_p413_1_RadioGroup.findViewById(c4_p413_1_RadioGroup.getCheckedRadioButtonId()))+"";
        c4_p413_2 = c4_p413_2_RadioGroup.indexOfChild(c4_p413_2_RadioGroup.findViewById(c4_p413_2_RadioGroup.getCheckedRadioButtonId()))+"";
        c4_p413_3 = c4_p413_3_RadioGroup.indexOfChild(c4_p413_3_RadioGroup.findViewById(c4_p413_3_RadioGroup.getCheckedRadioButtonId()))+"";
        c4_p413_4 = c4_p413_4_RadioGroup.indexOfChild(c4_p413_4_RadioGroup.findViewById(c4_p413_4_RadioGroup.getCheckedRadioButtonId()))+"";
        if(c4_p413_5_CheckBox.isChecked()) c4_p413_5 = "1"; else c4_p413_5 = "0";

        c4_p414 = c4_p414_RadioGroup.indexOfChild(c4_p414_RadioGroup.findViewById(c4_p414_RadioGroup.getCheckedRadioButtonId()))+"";
        c4_p415 = c4_p415_RadioGroup.indexOfChild(c4_p415_RadioGroup.findViewById(c4_p415_RadioGroup.getCheckedRadioButtonId()))+"";
        if(c4_p416_1_CheckBox.isChecked()) c4_p416_1 = "1"; else c4_p416_1 = "0";
        if(c4_p416_2_CheckBox.isChecked()) c4_p416_2 = "1"; else c4_p416_2 = "0";
        if(c4_p416_3_CheckBox.isChecked()) c4_p416_3 = "1"; else c4_p416_3 = "0";
        if(c4_p416_4_CheckBox.isChecked()) c4_p416_4 = "1"; else c4_p416_4 = "0";
        if(c4_p416_5_CheckBox.isChecked()) c4_p416_5 = "1"; else c4_p416_5 = "0";
        if(c4_p416_6_CheckBox.isChecked()) c4_p416_6 = "1"; else c4_p416_6 = "0";
//        if(c4_p416_7_CheckBox.isChecked()) c4_p416_7 = "1"; else c4_p416_7 = "0";
//        if(c4_p416_8_CheckBox.isChecked()) c4_p416_8 = "1"; else c4_p416_8 = "0";
//        c4_p416_o = c4_p416_o_EditText.getText().toString();
        // Anthony M. 30/04/2021
        c4_p416_5_o = c4_p416_5_o_EditText.getText().toString();
//        if(c4_p417_1_CheckBox.isChecked()) c4_p417_1 = "1"; else c4_p417_1 = "0";
//        if(c4_p417_2_CheckBox.isChecked()) c4_p417_2 = "1"; else c4_p417_2 = "0";
//        if(c4_p417_3_CheckBox.isChecked()) c4_p417_3 = "1"; else c4_p417_3 = "0";
//        if(c4_p417_4_CheckBox.isChecked()) c4_p417_4 = "1"; else c4_p417_4 = "0";
//        if(c4_p417_5_CheckBox.isChecked()) c4_p417_5 = "1"; else c4_p417_5 = "0";
//        if(c4_p417_6_CheckBox.isChecked()) c4_p417_6 = "1"; else c4_p417_6 = "0";
//        if(c4_p417_7_CheckBox.isChecked()) c4_p417_7 = "1"; else c4_p417_7 = "0";
//        if(c4_p417_8_CheckBox.isChecked()) c4_p417_8 = "1"; else c4_p417_8 = "0";
//        if(c4_p417_9_CheckBox.isChecked()) c4_p417_9 = "1"; else c4_p417_9 = "0";
//        c4_p417_7_o = c4_p417_7_o_EditText.getText().toString();
        c4_p417_1 = c4_p417_1_RadioGroup.indexOfChild(c4_p417_1_RadioGroup.findViewById(c4_p417_1_RadioGroup.getCheckedRadioButtonId()))+"";
       // c4_p417_1a = c4_p417_1a_RadioGroup.indexOfChild(c4_p417_1a_RadioGroup.findViewById(c4_p417_1a_RadioGroup.getCheckedRadioButtonId()))+"";
        c4_p417_2 = c4_p417_2_RadioGroup.indexOfChild(c4_p417_2_RadioGroup.findViewById(c4_p417_2_RadioGroup.getCheckedRadioButtonId()))+"";
        c4_p417_3 = c4_p417_3_RadioGroup.indexOfChild(c4_p417_3_RadioGroup.findViewById(c4_p417_3_RadioGroup.getCheckedRadioButtonId()))+"";
        c4_p417_4 = c4_p417_4_RadioGroup.indexOfChild(c4_p417_4_RadioGroup.findViewById(c4_p417_4_RadioGroup.getCheckedRadioButtonId()))+"";
        c4_p417_4_o = c4_p417_4_o_EditText.getText().toString();
      //  c4_p417_1a_o = c4_p417_1a_o_EditText.getText().toString();
        ////agregado 1/12/21 obs102///////////////////////////
        if(c4_p417_1_1_CheckBox.isChecked()) c4_p417_1_1 = "1"; else c4_p417_1_1 = "0";
        if(c4_p417_1_2_CheckBox.isChecked()) c4_p417_1_2 = "1"; else c4_p417_1_2 = "0";
        if(c4_p417_1_3_CheckBox.isChecked()) c4_p417_1_3 = "1"; else c4_p417_1_3 = "0";
        if(c4_p417_1_4_CheckBox.isChecked()) c4_p417_1_4 = "1"; else c4_p417_1_4 = "0";
        if(c4_p417_1_5_CheckBox.isChecked()) c4_p417_1_5 = "1"; else c4_p417_1_5 = "0";
        if(c4_p417_1_6_CheckBox.isChecked()) c4_p417_1_6 = "1"; else c4_p417_1_6 = "0";
        if(c4_p417_1_7_CheckBox.isChecked()) c4_p417_1_7 = "1"; else c4_p417_1_7 = "0";
        if(c4_p417_1_8_CheckBox.isChecked()) c4_p417_1_8 = "1"; else c4_p417_1_8 = "0";
        if(c4_p417_1_9_CheckBox.isChecked()) c4_p417_1_9 = "1"; else c4_p417_1_9 = "0";
        if(c4_p417_1_10_CheckBox.isChecked()) c4_p417_1_10 = "1"; else c4_p417_1_10 = "0";
        if(c4_p417_1_11_CheckBox.isChecked()) c4_p417_1_11 = "1"; else c4_p417_1_11 = "0";
        if(c4_p417_1_12_CheckBox.isChecked()) c4_p417_1_12 = "1"; else c4_p417_1_12 = "0";
        if(c4_p417_1_13_CheckBox.isChecked()) c4_p417_1_13 = "1"; else c4_p417_1_13 = "0";

        c4_p417_1_13_o = c4_p417_1_13_o_EditText.getText().toString();

////////////////////////////////////////////////////////////////////////////////////////////
        c4_p418 = c4_p418_RadioGroup.indexOfChild(c4_p418_RadioGroup.findViewById(c4_p418_RadioGroup.getCheckedRadioButtonId()))+"";
        c4_p418a = c4_p418a_RadioGroup.indexOfChild(c4_p418a_RadioGroup.findViewById(c4_p418a_RadioGroup.getCheckedRadioButtonId()))+"";

    }

    @Override
    public void cargarDatos() {

        Data data = new Data(context);
        data.open();
        if (data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo4 modulo4 = data.getModulo4(idEncuestado);
            ArrayList<String> informantes = data.getListaInformantesMayor12(modulo4.getIdHogar());
            UtilsMethodsInputs.loadSpinner(informantes,informanteSpinner,context);
            if(modulo4.getIdInformante() != null && !modulo4.getIdInformante().equals("")) informanteSpinner.setSelection(obtener_posicion(modulo4.getIdInformante(),informanteSpinner));
            //Anthony M. 30/04/2021
            if(modulo4.getC4_p413_1() != null && !modulo4.getC4_p413_1().equals("-1") && !modulo4.getC4_p413_1().equals(""))((RadioButton)c4_p413_1_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p413_1()))).setChecked(true);
            if(modulo4.getC4_p413_2() != null && !modulo4.getC4_p413_2().isEmpty() && !modulo4.getC4_p413_2().equals("-1") && !modulo4.getC4_p413_2().equals(""))((RadioButton)c4_p413_2_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p413_2()))).setChecked(true);
            if(modulo4.getC4_p413_3() != null && !modulo4.getC4_p413_3().equals("-1") && !modulo4.getC4_p413_3().equals(""))((RadioButton)c4_p413_3_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p413_3()))).setChecked(true);
            if(modulo4.getC4_p413_4() != null && !modulo4.getC4_p413_4().equals("-1") && !modulo4.getC4_p413_4().equals(""))((RadioButton)c4_p413_4_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p413_4()))).setChecked(true);
            if(modulo4.getC4_p413_5() != null && modulo4.getC4_p413_5().equals("1")) c4_p413_5_CheckBox.setChecked(true);

            if(modulo4.getC4_p414() != null && !modulo4.getC4_p414().equals("-1") && !modulo4.getC4_p414().equals(""))((RadioButton)c4_p414_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p414()))).setChecked(true);
            if(modulo4.getC4_p415() != null && !modulo4.getC4_p415().equals("-1") && !modulo4.getC4_p415().equals(""))((RadioButton)c4_p415_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p415()))).setChecked(true);
            if(modulo4.getC4_p416_1().equals("1")) c4_p416_1_CheckBox.setChecked(true);
            if(modulo4.getC4_p416_2().equals("1")) c4_p416_2_CheckBox.setChecked(true);
            if(modulo4.getC4_p416_3().equals("1")) c4_p416_3_CheckBox.setChecked(true);
            if(modulo4.getC4_p416_4().equals("1")) c4_p416_4_CheckBox.setChecked(true);
            if(modulo4.getC4_p416_5().equals("1")) c4_p416_5_CheckBox.setChecked(true);
            if(modulo4.getC4_p416_6().equals("1")) c4_p416_6_CheckBox.setChecked(true);
            //Anthony M. 30/04/2021
            c4_p416_5_o_EditText.setText(modulo4.getC4_p416_5_o());
//            if(modulo4.getC4_p417_1().equals("1")) c4_p417_1_CheckBox.setChecked(true);
//            if(modulo4.getC4_p417_2().equals("1")) c4_p417_2_CheckBox.setChecked(true);
//            if(modulo4.getC4_p417_3().equals("1")) c4_p417_3_CheckBox.setChecked(true);
//            if(modulo4.getC4_p417_4().equals("1")) c4_p417_4_CheckBox.setChecked(true);
//            if(modulo4.getC4_p417_5().equals("1")) c4_p417_5_CheckBox.setChecked(true);
//            if(modulo4.getC4_p417_6().equals("1")) c4_p417_6_CheckBox.setChecked(true);
//            if(modulo4.getC4_p417_7().equals("1")) c4_p417_7_CheckBox.setChecked(true);
//            if(modulo4.getC4_p417_8().equals("1")) c4_p417_8_CheckBox.setChecked(true);
//            if(modulo4.getC4_p417_9().equals("1")) c4_p417_9_CheckBox.setChecked(true);
//            c4_p417_7_o_EditText.setText(modulo4.getC4_p417_7_o());
            if(modulo4.getC4_p417_1() != null && !modulo4.getC4_p417_1().equals("-1") && !modulo4.getC4_p417_1().equals(""))((RadioButton)c4_p417_1_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p417_1()))).setChecked(true);
           // if(modulo4.getC4_p417_1a() != null && !modulo4.getC4_p417_1a().equals("-1") && !modulo4.getC4_p417_1a().equals(""))((RadioButton)c4_p417_1a_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p417_1a()))).setChecked(true);
            if(modulo4.getC4_p417_2() != null && !modulo4.getC4_p417_2().equals("-1") && !modulo4.getC4_p417_2().equals(""))((RadioButton)c4_p417_2_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p417_2()))).setChecked(true);
            if(modulo4.getC4_p417_3() != null && !modulo4.getC4_p417_3().equals("-1") && !modulo4.getC4_p417_3().equals(""))((RadioButton)c4_p417_3_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p417_3()))).setChecked(true);
            if(modulo4.getC4_p417_4() != null && !modulo4.getC4_p417_4().equals("-1") && !modulo4.getC4_p417_4().equals(""))((RadioButton)c4_p417_4_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p417_4()))).setChecked(true);
            c4_p417_4_o_EditText.setText(modulo4.getC4_p417_4_o());
            //c4_p417_1a_o_EditText.setText(modulo4.getC4_p417_1a_o());

            //////////////////AGREGADO 1/12/21////////////////////////////////////////////////////////////////////////////
            if(modulo4.getC4_p417_1_1().equals("1")) c4_p417_1_1_CheckBox.setChecked(true);
            if(modulo4.getC4_p417_1_2().equals("1")) c4_p417_1_2_CheckBox.setChecked(true);
            if(modulo4.getC4_p417_1_3().equals("1")) c4_p417_1_3_CheckBox.setChecked(true);
            if(modulo4.getC4_p417_1_4().equals("1")) c4_p417_1_4_CheckBox.setChecked(true);
            if(modulo4.getC4_p417_1_5().equals("1")) c4_p417_1_5_CheckBox.setChecked(true);
            if(modulo4.getC4_p417_1_6().equals("1")) c4_p417_1_6_CheckBox.setChecked(true);
            if(modulo4.getC4_p417_1_7().equals("1")) c4_p417_1_7_CheckBox.setChecked(true);
            if(modulo4.getC4_p417_1_8().equals("1")) c4_p417_1_8_CheckBox.setChecked(true);
            if(modulo4.getC4_p417_1_9().equals("1")) c4_p417_1_9_CheckBox.setChecked(true);
            if(modulo4.getC4_p417_1_10().equals("1")) c4_p417_1_10_CheckBox.setChecked(true);
            if(modulo4.getC4_p417_1_11().equals("1")) c4_p417_1_11_CheckBox.setChecked(true);
            if(modulo4.getC4_p417_1_12().equals("1")) c4_p417_1_12_CheckBox.setChecked(true);
            if(modulo4.getC4_p417_1_13().equals("1")) c4_p417_1_13_CheckBox.setChecked(true);

            c4_p417_1_13_o_EditText.setText(modulo4.getC4_p417_1_13_o());

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if(modulo4.getC4_p418() != null && !modulo4.getC4_p418().equals("-1") && !modulo4.getC4_p418().equals(""))((RadioButton)c4_p418_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p418()))).setChecked(true);
            if(modulo4.getC4_p418a() != null && !modulo4.getC4_p418a().equals("-1") && !modulo4.getC4_p418a().equals(""))((RadioButton)c4_p418a_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p418a()))).setChecked(true);
        }
        inicio();
        data.close();

    }

    @Override
    public void llenarVista() {
        /*
        Data data = new Data(context);
        data.open();
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p411,idEncuestado))
            m4_p411_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p412,idEncuestado))
            m4_p412_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p413,idEncuestado))
            m4_p413_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p414,idEncuestado))
            m4_p414_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p415,idEncuestado))
            m4_p415_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p416,idEncuestado))
            m4_p416_linearlayout.setVisibility(View.GONE);
        data.close();

         */
    }

    @Override
    public boolean validarDatos() {

        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        int cant_p416_respondidos = Integer.parseInt(c4_p416_1)+Integer.parseInt(c4_p416_2)+Integer.parseInt(c4_p416_3)+Integer.parseInt(c4_p416_4)+ Integer.parseInt(c4_p416_5)+
                Integer.parseInt(c4_p416_6);

//        int cant_p417_respondidos = Integer.parseInt(c4_p417_1)+Integer.parseInt(c4_p417_2)+Integer.parseInt(c4_p417_3)+Integer.parseInt(c4_p417_4)+ Integer.parseInt(c4_p417_5)+
//                Integer.parseInt(c4_p417_6) + Integer.parseInt(c4_p417_7) + Integer.parseInt(c4_p417_8) + Integer.parseInt(c4_p417_9);

        if (m4_p413_linearlayout.getVisibility()==View.VISIBLE){
            if(c4_p413_5.equals("0") && c4_p413_1.equals("-1")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR UNA RESPUESTA EN LA PREGUNTA 413_ITEM 1");return false;
            }
            if(c4_p413_5.equals("0") && c4_p413_2.equals("-1")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR UNA RESPUESTA EN LA PREGUNTA 413_ITEM 2");return false;
            }
            if(c4_p413_5.equals("0") && c4_p413_3.equals("-1")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR UNA RESPUESTA EN LA PREGUNTA 413_ITEM 3");return false;
            }
            if(c4_p413_5.equals("0") && c4_p413_4.equals("-1")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR UNA RESPUESTA EN LA PREGUNTA 413_ITEM 4");return false;
            }
            if(c4_p413_5.equals("0")
                    && c4_p413_1.equals("-1") && c4_p413_2.equals("-1") && c4_p413_3.equals("-1") && c4_p413_4.equals("-1")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR UNA RESPUESTA EN LA PREGUNTA 413_ITEM 5");return false;
            }

            //FRANCISCO PIDIO QUE SE COMENTARA EN SUS OBSERVACIONES 01 06 2021
//            if(c4_p413_4.equals("1") || c4_p413_4.equals("2") || c4_p415.equals("0") || c4_p415.equals("1"))
//            {
//                Toast.makeText(context, "SI DESEA PUEDE ANOTAR OBSERVACIONES", Toast.LENGTH_SHORT).show();
//            }

//            //--- PREGUNTA COGNITIVA  p212 != null && p212.equal(1)--//
////Todas las personas AND (P205_A<=4 OR P205_B>0) AND P212=1
////VERIFICACIÓN	: Sí P413_i<>VACIO, para cualquier i=1,2,3,4,5 Entonces mostrar mensaje:
//
            if ((anio_p205_a<=4 || mes_p205_b>0) && ( p212.equals("1") && !(p212.equals("")) ) &&
                    (!c4_p413_1.equals("-1") || !c4_p413_2.equals("-1") || !c4_p413_3.equals("-1") || !c4_p413_4.equals("-1")) ) {
                mostrarMensaje("“SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
            }


        }else { c4_p413_1 = "";c4_p413_2 = "";c4_p413_3 = "";c4_p413_4 = "";c4_p413_5 = ""; }
        if (m4_p414_linearlayout.getVisibility()==View.VISIBLE){
            if(c4_p414.equals("-1")){mostrarMensaje("ERROR  “DEBE SELECCIONAR AL MENOS UNA ALTERNATIVA EN LA PREGUNTA 414”");return false;}

            //COGNITIVA
            if((p212.equals("1") && !(p212.equals("")))  && !(parentesco_p203.equals("1")) && ( anio_p205_a>=12 && anio_p205_a<=14 )  && (c4_p414.equals("2") || c4_p414.equals("3"))){
                mostrarMensaje("“SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
            }

            //: SI P414 =2 AND P205_A=12:14  => pase a OBS_400
            //FRANCISCO PIDIO QUE SE COMENTARA EN SUS OBSERVACIONES 01 06 2021
//            if (c4_p414.equals("2") && ( anio_p205_a>=12 && anio_p205_a<=14 )){
//                Toast.makeText(context, "SI DESEA PUEDE ANOTAR OBSERVACIONES", Toast.LENGTH_SHORT).show();
//            }

        }
        if (m4_p415_linearlayout.getVisibility()==View.VISIBLE){
            if(c4_p415.equals("-1")){mostrarMensaje("ERROR  “DEBE SELECCIONAR AL MENOS UNA ALTERNATIVA EN LA PREGUNTA 415”");return false;}

            //REGLA COGNITIVA
            //Si P212=1 AND P203<>1 AND P205_A=[12,13,14] AND P415=2 => NUEVO NO ES OBSERVACION.
            if ( (p212.equals("1") && !(p212.equals(""))) &&
                    !(parentesco_p203.equals("1")) &&
                    (anio_p205_a==12 || anio_p205_a==13 || anio_p205_a==14) &&
                    c4_p415.equals("2")) {
                mostrarMensaje("“SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
            }

            //FRANCISCO PIDIO QUE SE COMENTARA EN SUS OBSERVACIONES 01 06 2021
//            if(c4_p415.equals("2") && (anio_p205_a>=12 && anio_p205_a<=14))
//            {
//                Toast.makeText(context, "SI DESEA PUEDE ANOTAR OBSERVACIONES", Toast.LENGTH_SHORT).show();
//            }

        }
        if (m4_p416_linearlayout.getVisibility()==View.VISIBLE){
            if(c4_p416_1.equals("0") && c4_p416_2.equals("0") && c4_p416_3.equals("0") && c4_p416_4.equals("0") && c4_p416_5.equals("0") && c4_p416_6.equals("0")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR AL MENOS UNA ALTERNATIVA EN LA PREGUNTA 416”");return false;
            }
            if(c4_p416_5.equals("1") && c4_p416_5_o.equals("")){
                mostrarMensaje("ERROR  “DEBE ESPECIFICAR OTRO EN LA PREGUNTA 416”");return false;
            }

//            //--- PREGUNTA COGNITIVA  p212 != null && p212.equal(1)--//
            // P212=1 AND P203<>1 AND P205_A=[12:14] AND P204=2 AND SUM(P416_k=1)>0, para cualquier k=1,2,3,4,5,6=> Mostrar siguiente Mensaje
            //: Sí P413_i=VACIO, para todos los i=1,2,3,4,5 AND SUM(P416_j=1)>0, para cualquier j=1,2,3,4,5,6 ESTO YA NO VA SEGUN NUEVA MODIFICACION 27052015
            if ( (p212.equals("1") && !(p212.equals(""))) &&
                    !(parentesco_p203.equals("1")) &&
                    ( anio_p205_a>=12 && anio_p205_a<=14 ) &&
                    sexo_p204.equals("2") &&
                    cant_p416_respondidos >0) {
                mostrarMensaje("“SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
            }



        }else {
            c4_p416_1 = "";
            c4_p416_2 = "";
            c4_p416_3 = "";
            c4_p416_4 = "";
            c4_p416_5 = "";
            c4_p416_5_o = "";
            c4_p416_6 = "";
        }

        if (m4_p417_linearlayout.getVisibility()==View.VISIBLE){

            if (c4_p417_1.equals("-1")) { mostrarMensaje("PREGUNTA 417-1: DEBE SELECCIONAR UNA OPCION");return false;}
            if (c4_p417_1.equals("1")){
                /*if(c4_p417_1a.equals("-1")){ mostrarMensaje("PREGUNTA 417-A: DEBE SELECCIONAR UNA OPCION");return false; }
                if(c4_p417_1a.equals("13")) {
                    if (c4_p417_1a_o.trim().equals("")) {
                        mostrarMensaje("ERROR \"P417A - OPCION 1A. DEBE ESPECIFICAR OTRO\"");
                        return false;
                    }
                    if (c4_p417_1a_o.length() < 3) {
                        mostrarMensaje("ERROR \"P417A - OPCION 1A. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES\"");
                        return false;
                    }
                }*/

            }

            if (c4_p417_2.equals("-1")) { mostrarMensaje("PREGUNTA 417-2: DEBE SELECCIONAR UNA OPCION");return false;}

            if (c4_p417_3.equals("-1")) { mostrarMensaje("PREGUNTA 417-3: DEBE SELECCIONAR UNA OPCION");return false;}

            if (c4_p417_4.equals("-1")) { mostrarMensaje("PREGUNTA 417-4: DEBE SELECCIONAR UNA OPCION");return false;}
            if (c4_p417_4.equals("1")){
                if (c4_p417_4_o.trim().equals("")){ mostrarMensaje("ERROR \"P417 - OPCION 4. DEBE ESPECIFICAR OTRO\"");return false; }
                if (c4_p417_4_o.length() < 3) { mostrarMensaje("ERROR \"P417 - OPCION 4. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES\"");return false; }
            }

        }else {
            c4_p417_1 = "";
            //c4_p417_1a = "";
            //c4_p417_1a_o = "";


            c4_p417_2 = "";
            c4_p417_3 = "";
            c4_p417_4 = "";
            c4_p417_4_o = "";
        }

        if(layout_m4_417_a.getVisibility() == View.VISIBLE){
            if(c4_p417_1_1.equals("0") && c4_p417_1_2.equals("0") && c4_p417_1_3.equals("0") && c4_p417_1_4.equals("0") && c4_p417_1_5.equals("0") && c4_p417_1_6.equals("0") &&
                    c4_p417_1_7.equals("0") && c4_p417_1_8.equals("0") && c4_p417_1_9.equals("0") && c4_p417_1_10.equals("0") && c4_p417_1_11.equals("0") && c4_p417_1_12.equals("0")&& c4_p417_1_13.equals("0")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR AL MENOS UNA ALTERNATIVA EN LA PREGUNTA 417A”");return false;
            }
            if(c4_p417_1_13.equals("1") && c4_p417_1_13_o.equals("")){
                mostrarMensaje("ERROR  “DEBE ESPECIFICAR OTRO EN LA PREGUNTA 417A”");return false;   }
            if (c4_p417_1_13.equals("1") && c4_p417_1_13_o.length() < 3){ mostrarMensaje("ERROR  EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES EN LA PREGUNTA 417A");return false;}

        }else{
            c4_p417_1_1 = "";
            c4_p417_1_2 = "";
            c4_p417_1_3 = "";
            c4_p417_1_4 = "";
            c4_p417_1_5 = "";
            c4_p417_1_6 = "";
            c4_p417_1_7 = "";
            c4_p417_1_8 = "";
            c4_p417_1_9 = "";
            c4_p417_1_10 = "";
            c4_p417_1_11 = "";
            c4_p417_1_12 = "";
            c4_p417_1_13 = "";
            c4_p417_1_13_o = "";


        }


//        if (m4_p417_linearlayout.getVisibility()==View.VISIBLE){
//            if(c4_p417_1.equals("0") && c4_p417_2.equals("0") && c4_p417_3.equals("0") && c4_p417_4.equals("0") && c4_p417_5.equals("0") && c4_p417_6.equals("0") && c4_p417_7.equals("0") && c4_p417_8.equals("0") && c4_p417_9.equals("0")){
//                mostrarMensaje("ERROR  “DEBE SELECCIONAR AL MENOS UNA ALTERNATIVA EN LA PREGUNTA 417”");return false;
//            }
//            if(c4_p417_7.equals("1") && c4_p417_7_o.equals("")){mostrarMensaje("PREGUNTA 417: DEBE COMPLETAR EN OTRO");return false;}
//            if(c4_p417_7.equals("1") && !c4_p417_7_o.equals("") && c4_p417_7_o.length() < 3){mostrarMensaje("PREGUNTA 417: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}
//
////            //--- PREGUNTA COGNITIVA  p212 != null && p212.equal(1)--//
//////            VERIFICACIÓN	: Sí P413_i=VACIO, para todos los i=1,2,3,4,5 AND P416_j=VACIO, para todos los j=1,2,3,4,5,6 AND
//////                                   SUM(P417_k=1)>0, para cualquier k=1,2,3,4,5,6,7,8,9 Entonces mostrar mensaje:
//            if ( (anio_p205_a>15) &&
//                    (p212.equals("1") && !(p212.equals(""))) &&
//                    (c4_p413_1.equals("-1") && c4_p413_2.equals("-1") && c4_p413_3.equals("-1") && c4_p413_4.equals("-1")) &&
//                    cant_p416_respondidos==0 &&
//                    cant_p417_respondidos>0 ) {
//                mostrarMensaje("“SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
//            }
//
//
//        }else {
//            c4_p417_1 = "";
//            c4_p417_2 = "";
//            c4_p417_3 = "";
//            c4_p417_4 = "";
//            c4_p417_5 = "";
//            c4_p417_6 = "";
//            c4_p417_7 = "";
//            c4_p417_8 = "";
//            c4_p417_9 = "";
//        }
        if (m4_p418_linearlayout.getVisibility() == View.VISIBLE && !p418_contestado){
            if(c4_p418.equals("-1")){mostrarMensaje("DEBE SELECCIONAR UNA ALTERNATIVA PREGUNTA 418");return false;}
//            if(c4_p418.equals("2")){mostrarMensaje("EN LA PREGUNTA 418 SELECCIONÓ LA OPCIÓN 418_2, DEBERÁ REGISTRAR OBSERVACIÓN DEL MÓDULO 4");}
            //FRANCISCO PIDIO QUE SE COMENTARA EN SUS OBSERVACIONES 01 06 2021
//            if(c4_p418.equals("2")){mostrarMensaje("SI DESEA PUEDE ANOTAR OBSERVACIONES");} //modificado 18/05/2021
            if (m4_p418a_linearlayout.getVisibility() == View.VISIBLE){
                if(c4_p418a.equals("-1")){mostrarMensaje("DEBE SELECCIONAR UNA ALTERNATIVA PREGUNTA 418a");return false;}
            }else {
                c4_p418a = "";
            }

//            //--- PREGUNTA COGNITIVA  p212 != null && p212.equal(1)--//
//            //VERIFICACIÓN	: Sí (P418=2 OR P418A>0) Entonces mostrar mensaje:
            if ((p212.equals("1") && !(p212.equals(""))) &&
                    ( c4_p418.equals("2") || c4_p418a.equals("1") || c4_p418a.equals("2"))) {
                mostrarMensaje("“SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
            }

        }else {
            c4_p418 = "";
            c4_p418a = "";
        }

//        if(m4_p411_linearlayout.getVisibility()==View.VISIBLE){
//            if(c4_p411_1.equals("0") && c4_p411_2.equals("0") && c4_p411_3.equals("0") && c4_p411_4.equals("0") && c4_p411_5.equals("0") &&
//                    c4_p411_6.equals("0") && c4_p411_7.equals("0") && c4_p411_8.equals("0") && c4_p411_9.equals("0") && c4_p411_10.equals("0") &&
//                    c4_p411_11.equals("0") && c4_p411_12.equals("0") && c4_p411_13.equals("0") && c4_p411_14.equals("0")){
//                mostrarMensaje("PREGUNTA 411: DEBE SELECCIONAR ALGUNA OPCION");
//                return false;
//            }
//            if(c4_p411_13.equals("1")){
//                if(c4_p411_o.trim().equals("")){
//                    mostrarMensaje("PREGUNTA 411 - OPCION 13: DEBE ESPECIFICAR OTRO");
//                    return false;
//                }
//            }
//        }else{
//            c4_p411_1 = "";
//            c4_p411_2 = "";
//            c4_p411_3 = "";
//            c4_p411_4 = "";
//            c4_p411_5 = "";
//            c4_p411_6 = "";
//            c4_p411_7 = "";
//            c4_p411_8 = "";
//            c4_p411_9 = "";
//            c4_p411_10 = "";
//            c4_p411_11 = "";
//            c4_p411_12 = "";
//            c4_p411_13 = "";
//            c4_p411_14 = "";
//            c4_p411_o = "";
//        }
//
//        if (m4_p412_linearlayout.getVisibility()==View.VISIBLE){
//            if(c4_p412.equals("-1")){
//                mostrarMensaje("PREGUNTA 412: DEBE SELECCIONAR UNA OPCION");
//                return false;
//            }
//        }else c4_p412 = "";
//
//        if (m4_p413_linearlayout.getVisibility()==View.VISIBLE){
//            if(c4_p413.equals("-1")){
//                mostrarMensaje("PREGUNTA 413: DEBE SELECCIONAR UNA OPCION");
//                return false;
//            }
//        }else c4_p413 = "";
//
//        if (m4_p414_linearlayout.getVisibility()==View.VISIBLE){
//            if(c4_p414.equals("-1")){
//                mostrarMensaje("PREGUNTA 414: DEBE SELECCIONAR UNA OPCION");
//                return false;
//            }
//        }else c4_p414 = "";
//
//        if (m4_p415_linearlayout.getVisibility()==View.VISIBLE){
//            if(c4_p415.equals("-1")){
//                mostrarMensaje("PREGUNTA 415: DEBE SELECCIONAR UNA OPCION");
//                return false;
//            }
//        }else c4_p415 = "";
//
//
//        if(m4_p416_linearlayout.getVisibility()==View.VISIBLE){
//            if(c4_p416_1.equals("0") && c4_p416_2.equals("0") && c4_p416_3.equals("0") && c4_p416_4.equals("0") && c4_p416_5.equals("0") &&
//                    c4_p416_6.equals("0") && c4_p416_7.equals("0") && c4_p416_8.equals("0")){
//                mostrarMensaje("PREGUNTA 416: DEBE SELECCIONAR ALGUNA OPCION");
//                return false;
//            }
//            if(c4_p416_6.equals("1")){
//                if(c4_p416_o.trim().equals("")){
//                    mostrarMensaje("PREGUNTA 416 - OPCION 6: DEBE ESPECIFICAR OTRO");
//                    return false;
//                }
//            }
//        }else{
//            c4_p416_1 = "";
//            c4_p416_2 = "";
//            c4_p416_3 = "";
//            c4_p416_4 = "";
//            c4_p416_5 = "";
//            c4_p416_6 = "";
//            c4_p416_7 = "";
//            c4_p416_8 = "";
//            c4_p416_o = "";
//        }
        return true;
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablamodulo4;
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


    public void controlarChecked(CheckBox checkBox,final EditText editText){
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

    /*
    public void limpiar_p411(){
        c4_p411_1_CheckBox.setChecked(false);
        c4_p411_2_CheckBox.setChecked(false);
        c4_p411_3_CheckBox.setChecked(false);
        c4_p411_4_CheckBox.setChecked(false);
        c4_p411_5_CheckBox.setChecked(false);
        c4_p411_6_CheckBox.setChecked(false);
        c4_p411_7_CheckBox.setChecked(false);
        c4_p411_8_CheckBox.setChecked(false);
        c4_p411_9_CheckBox.setChecked(false);
        c4_p411_10_CheckBox.setChecked(false);
        c4_p411_11_CheckBox.setChecked(false);
        c4_p411_12_CheckBox.setChecked(false);
        c4_p411_13_CheckBox.setChecked(false);
        c4_p411_14_CheckBox.setChecked(false);
        c4_p411_o_EditText.setText("");
    }

     */

    /*
    public void limpiar_p412(){
        c4_p412_RadioGroup.clearCheck();
    }

     */
/*
    public void limpiar_p413(){
        c4_p413_RadioGroup.clearCheck();
    }

    public void limpiar_p414(){
        c4_p414_RadioGroup.clearCheck();
    }

    public void limpiar_p415(){
        c4_p415_RadioGroup.clearCheck();
    }
    public void limpiar_p416(){
        c4_p416_1_CheckBox.setChecked(false);
        c4_p416_2_CheckBox.setChecked(false);
        c4_p416_3_CheckBox.setChecked(false);
        c4_p416_4_CheckBox.setChecked(false);
        c4_p416_5_CheckBox.setChecked(false);
        c4_p416_6_CheckBox.setChecked(false);
        c4_p416_7_CheckBox.setChecked(false);
        c4_p416_8_CheckBox.setChecked(false);
        c4_p416_o_EditText.setText("");
    }

 */
    // Anthony M. 01/05/20251
    public void limpiar_p418a(){
        c4_p418a_RadioGroup.clearCheck();
    }

    public void inicio(){
        //P413
        if(validar_P413()) m4_p413_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p413();m4_p413_linearlayout.setVisibility(View.GONE);}
        //P414
        if(validar_P414()) m4_p414_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p414();m4_p414_linearlayout.setVisibility(View.GONE);}
        //P415
        if(validar_P415()) m4_p415_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p415();m4_p415_linearlayout.setVisibility(View.GONE);}
        //P416
        if(validar_P416()) m4_p416_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p416();m4_p416_linearlayout.setVisibility(View.GONE);}
        //P417
        if(validar_P417()) m4_p417_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p417();m4_p417_linearlayout.setVisibility(View.GONE);}
        //P418
        if(validar_P418()) m4_p418_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p418();m4_p418_linearlayout.setVisibility(View.GONE);}
        //P418A
        if(validar_P418a()) m4_p418a_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p418a();m4_p418a_linearlayout.setVisibility(View.GONE);}

//        llenarVariables();
//        if(anio_p205_a <= 4 || mes_p205_b > 0){ //FUJO 45 - UNIVERSO
//            m4_p413_linearlayout.setVisibility(View.VISIBLE);
//        }else {
//            limpiar_p413();
//            m4_p413_linearlayout.setVisibility(View.GONE);
//        }
//        validarFlujo45();
    }

    private void bloquearRadioGroup(RadioGroup radioGroup) {
        for(int id = 0; id < radioGroup.getChildCount(); id++){
            ((RadioButton) radioGroup.getChildAt(id)).setEnabled(false);
        }
    }

    private void desbloquearRadioGroup(RadioGroup radioGroup) {
        for(int id = 0; id < radioGroup.getChildCount(); id++){
            ((RadioButton) radioGroup.getChildAt(id)).setEnabled(true);
        }
    }
    public boolean verificarCoberturaCapitulo(){
        /*
        Data data = new Data(context);
        data.open();
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p401p404,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp401p404,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p405p407,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp405p407,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p408p410,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp408p410,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p411p416,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp411p416,idEncuestado).equals("0")) return false;
        data.close();

         */
        return true;
    }
    //Anthony M 06/05/2021
    public void limpiar_p413(){
        c4_p413_1_RadioGroup.clearCheck();
        c4_p413_2_RadioGroup.clearCheck();
        c4_p413_3_RadioGroup.clearCheck();
        c4_p413_4_RadioGroup.clearCheck();
        c4_p413_5_CheckBox.setChecked(false);
    }
    public void limpiar_p414(){
        c4_p414_RadioGroup.clearCheck();
    }
    public void limpiar_p415(){
        c4_p415_RadioGroup.clearCheck();
    }
    public void limpiar_p416(){
        c4_p416_1_CheckBox.setChecked(false);
        c4_p416_2_CheckBox.setChecked(false);
        c4_p416_3_CheckBox.setChecked(false);
        c4_p416_4_CheckBox.setChecked(false);
        c4_p416_5_CheckBox.setChecked(false);
        c4_p416_6_CheckBox.setChecked(false);
        c4_p416_5_o_EditText.setText("");
    }
    public void limpiar_p417(){
        c4_p417_1_1_CheckBox.setChecked(false);
        c4_p417_1_2_CheckBox.setChecked(false);
        c4_p417_1_3_CheckBox.setChecked(false);
        c4_p417_1_4_CheckBox.setChecked(false);
        c4_p417_1_5_CheckBox.setChecked(false);
        c4_p417_1_6_CheckBox.setChecked(false);
        c4_p417_1_7_CheckBox.setChecked(false);
        c4_p417_1_8_CheckBox.setChecked(false);
        c4_p417_1_9_CheckBox.setChecked(false);
        c4_p417_1_10_CheckBox.setChecked(false);
        c4_p417_1_11_CheckBox.setChecked(false);
        c4_p417_1_12_CheckBox.setChecked(false);
        c4_p417_1_13_CheckBox.setChecked(false);
        c4_p417_1_13_o_EditText.setText("");

    }
    public void limpiar_p418(){
        c4_p418_RadioGroup.clearCheck();
        limpiar_p418a();
    }
    //    public void comprobar_mostrarPregunta413(){//FLUJO 45
//        if(anio_205a >= 4 || mes_205b >0)
//            m4_p413_linearlayout.setVisibility(View.VISIBLE);
//        else {
//            m4_p413_linearlayout.setVisibility(View.GONE);
//            limpiar_p413();
//        }
//    }
    public void validarFlujo45(){
        llenarVariables();
//        if(c4_p413_4.equals("1") || c4_p413_4.equals("2") || c4_p413_5.equals("0") || c4_p413_5.equals("1")){ //FLUJO 45

        if( ( anio_p205_a>=3 && anio_p205_a<=4) && c4_p413_4.equals("1") || c4_p413_4.equals("2")){ //FLUJO 45   29052021
            m4_p414_linearlayout.setVisibility(View.GONE);
            m4_p415_linearlayout.setVisibility(View.GONE);
            m4_p416_linearlayout.setVisibility(View.GONE);
            m4_p417_linearlayout.setVisibility(View.GONE);
            m4_p418_linearlayout.setVisibility(View.GONE);
            limpiar_p414();
            limpiar_p415();
            limpiar_p416();
            limpiar_p417();
            limpiar_p418();
        }else{
            if(anio_p205_a >= 12 && anio_p205_a <= 49){
                if(sexo_p204.equals("2")){
                    m4_p414_linearlayout.setVisibility(View.VISIBLE);
                    if(c4_p414.equals("1")){
                        m4_p415_linearlayout.setVisibility(View.VISIBLE);
                        if(c4_p415.equals("1")){
                            m4_p416_linearlayout.setVisibility(View.VISIBLE);
                        }else if(c4_p415.equals("2") && anio_p205_a>14) {   //29052021
                            limpiar_p416();
                            m4_p416_linearlayout.setVisibility(View.GONE);
                        }
                    }else if(c4_p414.equals("2") && anio_p205_a>14){   //29052021
                        limpiar_p415();limpiar_p416();
                        m4_p415_linearlayout.setVisibility(View.GONE);
                        m4_p416_linearlayout.setVisibility(View.GONE);
                    }
                }else {
                    limpiar_p414();limpiar_p415();limpiar_p416();
                    m4_p414_linearlayout.setVisibility(View.GONE);
                    m4_p415_linearlayout.setVisibility(View.GONE);
                    m4_p415_linearlayout.setVisibility(View.GONE);
                }
            }else{
                limpiar_p414();limpiar_p415();limpiar_p416();
                m4_p414_linearlayout.setVisibility(View.GONE);
                m4_p415_linearlayout.setVisibility(View.GONE);
                m4_p416_linearlayout.setVisibility(View.GONE);
            }
            if(anio_p205_a >= 15){
                m4_p417_linearlayout.setVisibility(View.VISIBLE);
            }else {
                limpiar_p417();
                m4_p417_linearlayout.setVisibility(View.GONE);
            }
            if(parentesco_p203.equals("1")){
                m4_p418_linearlayout.setVisibility(View.VISIBLE);
                if(c4_p418.equals("1")){
                    m4_p418a_linearlayout.setVisibility(View.VISIBLE);
                }else {
                    limpiar_p418a();
                    m4_p418a_linearlayout.setVisibility(View.GONE);
                }
            }else {
                limpiar_p418();limpiar_p418a();
                m4_p418_linearlayout.setVisibility(View.GONE);
            }
        }
    }
    public void validarAchurar416(){//REGLA 57
        llenarVariables();
        if(c4_p416_1.equals("1") || c4_p416_2.equals("1") || c4_p416_3.equals("1") || c4_p416_4.equals("1") || c4_p416_5.equals("1")){
            c4_p416_6_CheckBox.setChecked(false);c4_p416_6_CheckBox.setEnabled(false);
        }else {
            c4_p416_6_CheckBox.setEnabled(true);
        }
    }
    public void validarAchurar417(){//REGLA 58
        llenarVariables();
//        if(c4_p417_1.equals("1") || c4_p417_2.equals("1") || c4_p417_3.equals("1") || c4_p417_4.equals("1") || c4_p417_5.equals("1") ||
//                c4_p417_6.equals("1") || c4_p417_7.equals("1")){
//            c4_p417_8_CheckBox.setChecked(false);c4_p417_8_CheckBox.setEnabled(false);
//            c4_p417_9_CheckBox.setChecked(false);c4_p417_9_CheckBox.setEnabled(false);
//        }else {
//            c4_p417_8_CheckBox.setEnabled(true);c4_p417_9_CheckBox.setEnabled(true);
//        }
    }
    public boolean validar_P413(){
        boolean valido = false;
        llenarVariables();
        if(anio_p205_a <= 4 || mes_p205_b > 0) // UNIVERSO FLUJO 45
            valido = true;
        return valido;
    }
    public boolean validar_P414(){
        boolean valido = false;
        llenarVariables();
        if(sexo_p204.equals("2") && anio_p205_a >= 12 && anio_p205_a <= 49) // UNIVERSO FLUJO 46
            valido = true;
        return valido;
    }
    public boolean validar_P415(){
        boolean valido = false;
        llenarVariables();
        if(sexo_p204.equals("2") && anio_p205_a >= 12 && anio_p205_a <= 49 && c4_p414.equals("1")) // UNIVERSO FLUJO 47
            valido = true;
        return valido;
    }
    public boolean validar_P416(){
        boolean valido = false;
        llenarVariables();
        if(sexo_p204.equals("2") && anio_p205_a >= 12 && anio_p205_a <= 49 && c4_p415.equals("1")) // UNIVERSO FLUJO 48
            valido = true;
        return valido;
    }
    public boolean validar_P417(){
        boolean valido = false;
        llenarVariables();
        if(anio_p205_a >= 15) // UNIVERSO FLUJO 49
            valido = true;
        return valido;
    }
    public boolean validar_P418(){
        boolean valido = false;
        llenarVariables();
        Log.e("hector",vive);
            if( (anio_p205_a >=18 || parentesco_p203.equals("1"))// UNIVERSO FLUJO 50
                    || (parentesco_p203.equals("1") || (!parentesco_p203.equals("1") && vive.equals(idEncuestado) && jefehogar.getC2_p208().equals("2")) ||vive.equals("") || vive== null)){
            //achurar
            if(p418_contestado){
                limpiar_p418();limpiar_p418a();
                c4_p418_RadioGroup.getChildAt(1).setEnabled(false);
                c4_p418_RadioGroup.getChildAt(2).setEnabled(false);
            }else {
                c4_p418_RadioGroup.getChildAt(1).setEnabled(true);
                c4_p418_RadioGroup.getChildAt(2).setEnabled(true);
            }
            valido = true;
        }

        return valido;
    }
    public boolean validar_P418a(){
        boolean valido = false;
        llenarVariables();
        if(c4_p418.equals("1")) // FLUJO 50
            valido = true;
        return valido;
    }


}
