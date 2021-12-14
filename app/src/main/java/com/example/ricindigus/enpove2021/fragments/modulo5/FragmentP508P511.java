package com.example.ricindigus.enpove2021.fragments.modulo5;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.FontRes;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;

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
public class FragmentP508P511 extends FragmentPagina {
    String idEncuestado;
    String idInformante;
    Context context;

    Spinner informanteSpinner;
    CheckBox c5_p508_1_Checkbox, c5_p508_2_Checkbox, c5_p508_3_Checkbox , c5_p508_4_Checkbox, c5_p508_5_Checkbox;
    //Anthony M
    CheckBox c5_p509_1_Checkbox, c5_p509_2_Checkbox, c5_p509_3_Checkbox, c5_p509_4_Checkbox, c5_p509_5_Checkbox, c5_p509_6_Checkbox, c5_p509_7_Checkbox, c5_p509_8_Checkbox;
    EditText c5_p508_4_o_EditText, c5_p509_7_o_EditText, c5_p510_o_EditText, c5_p511_o_EditText;
    RadioGroup c5_p510_RadioGroup, c5_p511_RadioGroup;

    LinearLayout m5_p508_linearlayout, m5_p509_linearlayout, m5_p511_linearlayout;
    RelativeLayout m5_p510_linearlayout;
    private String c5_p508_1;
    private String c5_p508_2;
    private String c5_p508_3;
    private String c5_p508_4;
    private String c5_p508_5;
    //Anthony M
    private String c5_p508_4_o;
    private String c5_p509_1;
    private String c5_p509_2;
    private String c5_p509_3;
    private String c5_p509_4;
    private String c5_p509_5;
    private String c5_p509_6;
    private String c5_p509_7;
    private String c5_p509_7_o;
    private String c5_p509_8;
    private String c5_p510_o;

    private String c5_p510;
    private String c5_p511;
    private String c5_p511_o;

    private String p505,p506,p503,p501,p506a;

    String p212;
    private int edad=0;

    @SuppressLint("ValidFragment")
    public FragmentP508P511(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        p212 = data.getResidente(idEncuestado).getC2_p212();
//        idHogar = residente.getId_hogar();
//        idVivienda = residente.getId_vivienda();
        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        p505 = data.getModulo5(idEncuestado).getC5_p505();
        p506 = data.getModulo5(idEncuestado).getC5_p506();
        p506a = data.getModulo5(idEncuestado).getC5_p506a();
        p503 = data.getModulo5(idEncuestado).getC5_p503();
        p501 = data.getModulo5(idEncuestado).getC5_p501();
        idInformante = "";
        Log.e("fragmentP508p5011","p505 = "+p505);
        Log.e("fragmentP508p5011","p506 = "+p506);
        data.close();
    }

    public FragmentP508P511() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p508_p511, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

        c5_p508_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod5_508_checkbox_C5_P508_1);
        c5_p508_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod5_508_checkbox_C5_P508_2);
        c5_p508_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod5_508_checkbox_C5_P508_3);
        c5_p508_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod5_508_checkbox_C5_P508_4);
        c5_p508_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod5_508_checkbox_C5_P508_5);
        //Anthony M
        c5_p508_4_o_EditText = (EditText) rootView.findViewById(R.id.mod5_508_edittext_C5_P508_O);
        c5_p509_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod5_509_checkbox_C5_P509_1);
        c5_p509_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod5_509_checkbox_C5_P509_2);
        c5_p509_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod5_509_checkbox_C5_P509_3);
        c5_p509_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod5_509_checkbox_C5_P509_4);
        c5_p509_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod5_509_checkbox_C5_P509_5);
        c5_p509_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod5_509_checkbox_C5_P509_6);
        c5_p509_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod5_509_checkbox_C5_P509_7);
        c5_p509_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod5_509_checkbox_C5_P509_8);
        c5_p509_7_o_EditText = (EditText) rootView.findViewById(R.id.mod5_509_edittext_C5_P508_O);
        c5_p510_o_EditText = (EditText) rootView.findViewById(R.id.mod5_510_edittext_C5_P510_O);

        c5_p510_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_510_radiogroup_C5_P510);
        c5_p511_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_511_radiogroup_C5_P511);
        c5_p511_o_EditText = (EditText) rootView.findViewById(R.id.mod5_511_edittext_C5_P511_O);

        m5_p508_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p508);
        m5_p509_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p509);
        m5_p510_linearlayout = (RelativeLayout) rootView.findViewById(R.id.layout_m5_p510);
        m5_p511_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p511);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        configurarEditText(c5_p508_o_EditText,m5_p508_linearlayout,0,30);
        configurarEditText(c5_p508_4_o_EditText,m5_p508_linearlayout,0,50);
        configurarEditText(c5_p509_7_o_EditText,m5_p509_linearlayout,0,80);
        configurarEditText(c5_p510_o_EditText,m5_p510_linearlayout,0,30);
        configurarEditText(c5_p511_o_EditText,m5_p511_linearlayout,0,50);

//        controlarChecked(c5_p508_4_Checkbox,c5_p508_4_o_EditText);//Anthony M //FLUJO 57
//        controlarChecked(c5_p509_7_Checkbox,c5_p509_7_o_EditText);//Anthony M //FLUJO 58
//        controlarChecked(c5_p508_11_Checkbox,c5_p508_o_EditText);
        //Anthony M 07/05/2021
//        c5_p508_5_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked) {
//                    limpiar_p509();
//                 m5_p509_linearlayout.setVisibility(View.GONE);
//                }else{
//                    m5_p509_linearlayout.setVisibility(View.VISIBLE);
//                }
//            }
//        });
        //Anthony M 08/05/2021
        c5_p508_1_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(validar_P509()) m5_p509_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p509();m5_p509_linearlayout.setVisibility(View.GONE);}
//                validarFlujo58();
                //validarAchurar508();
            }
        });
        c5_p508_2_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(validar_P509()) m5_p509_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p509();m5_p509_linearlayout.setVisibility(View.GONE);}
//                validarFlujo58();
                //validarAchurar508();
            }
        });
        c5_p508_3_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(validar_P509()) m5_p509_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p509();m5_p509_linearlayout.setVisibility(View.GONE);}
//                validarFlujo58();
                //validarAchurar508();
            }
        });
        c5_p508_4_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(validar_P509()) m5_p509_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p509();m5_p509_linearlayout.setVisibility(View.GONE);}
//                validarFlujo58();
                //validarAchurar508();
                if(isChecked){//Anthony M //FLUJO 57
                    c5_p508_4_o_EditText.setEnabled(true);
                    c5_p508_4_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else {
                    c5_p508_4_o_EditText.setText("");
                    c5_p508_4_o_EditText.setEnabled(false);
                    c5_p508_4_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });
     /*   c5_p508_5_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(validar_P509()) m5_p509_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p509();m5_p509_linearlayout.setVisibility(View.GONE);}
                if(isChecked){
                    c5_p508_1_Checkbox.setChecked(false);
                    c5_p508_1_Checkbox.setEnabled(false);
                    c5_p508_2_Checkbox.setChecked(false);
                    c5_p508_2_Checkbox.setEnabled(false);
                    c5_p508_3_Checkbox.setChecked(false);
                    c5_p508_3_Checkbox.setEnabled(false);
                    c5_p508_4_Checkbox.setChecked(false);
                    c5_p508_4_Checkbox.setEnabled(false);
                }else{
                    c5_p508_1_Checkbox.setEnabled(true);
                    c5_p508_2_Checkbox.setEnabled(true);
                    c5_p508_3_Checkbox.setEnabled(true);
                    c5_p508_4_Checkbox.setEnabled(true);
                }
            }
        });*/
        c5_p509_1_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar509();
            }
        });
        c5_p509_2_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar509();
            }
        });
        c5_p509_3_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar509();
            }
        });
        c5_p509_4_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar509();
            }
        });
        c5_p509_5_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar509();
            }
        });
        c5_p509_6_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar509();
            }
        });
        c5_p509_7_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar509();
                if(isChecked){//Anthony M //FLUJO 58
                    c5_p509_7_o_EditText.setEnabled(true);
                    c5_p509_7_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    c5_p509_7_o_EditText.setText("");
                    c5_p509_7_o_EditText.setEnabled(false);
                    c5_p509_7_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });
        c5_p509_8_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    c5_p509_1_Checkbox.setChecked(false);
                    c5_p509_1_Checkbox.setEnabled(false);
                    c5_p509_2_Checkbox.setChecked(false);
                    c5_p509_2_Checkbox.setEnabled(false);
                    c5_p509_3_Checkbox.setChecked(false);
                    c5_p509_3_Checkbox.setEnabled(false);
                    c5_p509_4_Checkbox.setChecked(false);
                    c5_p509_4_Checkbox.setEnabled(false);
                    c5_p509_5_Checkbox.setChecked(false);
                    c5_p509_5_Checkbox.setEnabled(false);
                    c5_p509_6_Checkbox.setChecked(false);
                    c5_p509_6_Checkbox.setEnabled(false);
                    c5_p509_7_Checkbox.setChecked(false);
                    c5_p509_7_Checkbox.setEnabled(false);
                }else{
                    c5_p509_1_Checkbox.setEnabled(true);
                    c5_p509_2_Checkbox.setEnabled(true);
                    c5_p509_3_Checkbox.setEnabled(true);
                    c5_p509_4_Checkbox.setEnabled(true);
                    c5_p509_5_Checkbox.setEnabled(true);
                    c5_p509_6_Checkbox.setEnabled(true);
                    c5_p509_7_Checkbox.setEnabled(true);
                }
            }
        });

/*        c5_p511_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group,checkedId,4,c5_p511_o_EditText);
            }
        });

        c5_p509_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()));
                switch (pos){
                    case 1:
                        m5_p510_linearlayout.setVisibility(View.VISIBLE);
                        m5_p511_linearlayout.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        c5_p510_RadioGroup.clearCheck();
                        c5_p511_RadioGroup.clearCheck();
                        m5_p510_linearlayout.setVisibility(View.GONE);
                        m5_p511_linearlayout.setVisibility(View.GONE);
                        break;
                }
            }
        });
*/
        c5_p510_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                controlarEspecifiqueRadio(radioGroup,i,4,c5_p510_o_EditText); //FLUJO 59
                validar_P511();
//                int pos = radioGroup.indexOfChild(c5_p510_RadioGroup.findViewById(c5_p510_RadioGroup.getCheckedRadioButtonId()));
////                if(pos==1){
////                    m5_p511_linearlayout.setVisibility(View.GONE); limpiar_p511();
////                }else{
////                    m5_p511_linearlayout.setVisibility(View.VISIBLE);
////                }
//                //Anthony M
//                if(pos==4){ //FLUJO 59
//                    c5_p510_o_EditText.setEnabled(true);
//                    c5_p510_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
//                }else{
//                    c5_p510_o_EditText.setEnabled(false);
//                    c5_p510_o_EditText.setText("");
//                    c5_p510_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
//                }
            }
        });
        c5_p511_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                controlarEspecifiqueRadio(radioGroup,i,12,c5_p511_o_EditText); //FLUJO 60
//                int pos = radioGroup.indexOfChild(c5_p511_RadioGroup.findViewById(c5_p511_RadioGroup.getCheckedRadioButtonId()));
//                if(pos==12){ //FLUJO 60
//                    c5_p511_o_EditText.setEnabled(true);
//                    c5_p511_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
//                }else{
//                    c5_p511_o_EditText.setEnabled(false);
//                    c5_p511_o_EditText.setText("");
//                    c5_p511_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
//                }
            }
        });
        llenarVista();
        cargarDatos();


    }

    private void inicio() {
        Log.e("Fragment","Estas en P508P511");
        //P508
        if(validar_P508()) m5_p508_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p508();m5_p508_linearlayout.setVisibility(View.GONE);}
        //P509
        if(validar_P509()) m5_p509_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p509();m5_p509_linearlayout.setVisibility(View.GONE);}
        //P510
        if(validar_P510()) m5_p510_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p510();m5_p510_linearlayout.setVisibility(View.GONE);}
        //P511
        if(validar_P511()) m5_p511_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p511();m5_p511_linearlayout.setVisibility(View.GONE);}
//        if(edad >= 3 && edad <= 17 && (p505.equals("1") || p506.equals("1"))){ //FLUJO 57 FLUJO 58 FLUJO 59 - UNIVERSO
//            m5_p508_linearlayout.setVisibility(View.VISIBLE);
//            m5_p509_linearlayout.setVisibility(View.VISIBLE);
//            m5_p510_linearlayout.setVisibility(View.VISIBLE);
//            validarFlujo58();
//        } else {
//            limpiar_p508();limpiar_p509();limpiar_p510();
//            m5_p508_linearlayout.setVisibility(View.GONE);
//            m5_p509_linearlayout.setVisibility(View.GONE);
//            m5_p510_linearlayout.setVisibility(View.GONE);
//        }
//        if(edad >= 3 && edad <= 25 && p505.equals("2") && p506.equals("2")){//FLUJO 60 - UNIVERSO
//                m5_p511_linearlayout.setVisibility(View.VISIBLE);
//        }else {
//            limpiar_p511();
//            m5_p511_linearlayout.setVisibility(View.GONE);
//        }
    }

    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo5_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo5_c5_p508_1,c5_p508_1);
        contentValues.put(SQLConstantes.modulo5_c5_p508_2,c5_p508_2);
        contentValues.put(SQLConstantes.modulo5_c5_p508_3,c5_p508_3);
        contentValues.put(SQLConstantes.modulo5_c5_p508_4,c5_p508_4);
        contentValues.put(SQLConstantes.modulo5_c5_p508_5,c5_p508_5);
        //Anthony M
        contentValues.put(SQLConstantes.modulo5_c5_p508_4_o,c5_p508_4_o);
        contentValues.put(SQLConstantes.modulo5_c5_p509_1,c5_p509_1);
        contentValues.put(SQLConstantes.modulo5_c5_p509_2,c5_p509_2);
        contentValues.put(SQLConstantes.modulo5_c5_p509_3,c5_p509_3);
        contentValues.put(SQLConstantes.modulo5_c5_p509_4,c5_p509_4);
        contentValues.put(SQLConstantes.modulo5_c5_p509_5,c5_p509_5);
        contentValues.put(SQLConstantes.modulo5_c5_p509_6,c5_p509_6);
        contentValues.put(SQLConstantes.modulo5_c5_p509_7,c5_p509_7);
        contentValues.put(SQLConstantes.modulo5_c5_p509_7_o,c5_p509_7_o);
        contentValues.put(SQLConstantes.modulo5_c5_p509_8,c5_p509_8);
        contentValues.put(SQLConstantes.modulo5_c5_p510_o,c5_p510_o);

        contentValues.put(SQLConstantes.modulo5_c5_p510,c5_p510);
        contentValues.put(SQLConstantes.modulo5_c5_p511,c5_p511);
        contentValues.put(SQLConstantes.modulo5_c5_p511_o,c5_p511_o);
        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp508p511,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo5_COB500,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo5_COB500,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();
        ocultarOtrosLayouts();
    }

    private void ocultarOtrosLayouts() {
        llenarVariables();
        //Se ponen las validaciones empezando por la ultima pregunta para evitar conflictos
        /**FLUJO 60**/
        if(!c5_p511.equals("12") && (p501.equals("10") || p501.equals("11"))) {
            ocultarFragmentP512P513(false);
        }else
        if(!c5_p511.equals("12") && !(p501.equals("10") || p501.equals("11")) && edad >= 3 && edad <= 4) {
            ocultarFragmentP512P513(true);
        }else
        if(!c5_p511.equals("12") && !(p501.equals("10") || p501.equals("11")) && edad >= 5 && edad <= 13) {
            ocultarFragmentP512P513(true);
        }else
        if(!c5_p511.equals("12") && !(p501.equals("10") || p501.equals("11")) && edad >= 14) {
            ocultarFragmentP512P513(false);
        }else
            /**FLUJO 59**/
            if(!c5_p510.equals("4") && edad >= 3 && edad <= 25 && (p505.equals("2") || p506.equals("2"))){
                ocultarFragmentP512P513(false); //salta a fargment de P511
            }else
            if(!c5_p510.equals("4") && edad >25 && (p505.equals("2") || p506.equals("2")) && (p501.equals("10") || p501.equals("11"))){
                ocultarFragmentP512P513(false); //salta a fargment de P512
            }else
            if(!c5_p510.equals("4") && edad >25 && (p505.equals("2") || p506.equals("2")) && !(p501.equals("10") || p501.equals("11"))){
                ocultarFragmentP512P513(false); //salta a fargment de P515
            }else
            if(!c5_p510.equals("4") && edad >= 3 && edad <= 25 && p505.equals("1") && p506.equals("1") && (p501.equals("10") || p501.equals("11"))){
                ocultarFragmentP512P513(false); //salta a fargment de P512
            }else
            if(!c5_p510.equals("4") && edad >= 3 && edad <= 13 && p505.equals("1") && p506.equals("1") && !(p501.equals("10") || p501.equals("11"))){
                ocultarFragmentP512P513(true); //salta a fargment modulo 6
            }else
//            if(!c5_p510.equals("4") && edad >= 3 && edad <= 4 && p505.equals("1") && p506.equals("1") && !(p501.equals("10") || p501.equals("11"))){
//                ocultarFragmentP512P513(true);
//            }else
            if(!c5_p510.equals("4") && edad > 13 && p505.equals("1") && p506.equals("1") && !(p501.equals("10") || p501.equals("11"))){
                ocultarFragmentP512P513(false); //salta a fargment de P515
            }else
                /**FLUJO 57**/
                if(c5_p508_5.equals("1") && p503.equals("2") && (p501.equals("10") || p501.equals("11"))){
                    ocultarFragmentP512P513(false);
                }else
                if(c5_p508_5.equals("1") && p503.equals("2") && !(p501.equals("10") || p501.equals("11")) && edad >= 3 && edad <= 13){
                    ocultarFragmentP512P513(true);
                }else
                if(c5_p508_5.equals("1") && p503.equals("2") && !(p501.equals("10") || p501.equals("11")) && edad > 13){
                    ocultarFragmentP512P513(false);
                }else
                    Log.e("P508P511","No cumple con condiciones ocultarOtrosLayouts()");
    }

    @Override
    public void llenarVariables() {
        idInformante = obtener_Nresidente(informanteSpinner);
        if(c5_p508_1_Checkbox.isChecked()) c5_p508_1 = "1"; else c5_p508_1 = "0";
        if(c5_p508_2_Checkbox.isChecked()) c5_p508_2 = "1"; else c5_p508_2 = "0";
        if(c5_p508_3_Checkbox.isChecked()) c5_p508_3 = "1"; else c5_p508_3 = "0";
        if(c5_p508_4_Checkbox.isChecked()) c5_p508_4 = "1"; else c5_p508_4 = "0";
        //if(c5_p508_5_Checkbox.isChecked()) c5_p508_5 = "1"; else c5_p508_5 = "0";
        //Anthony M
        c5_p508_4_o = c5_p508_4_o_EditText.getText().toString();
        if(c5_p509_1_Checkbox.isChecked()) c5_p509_1 = "1"; else c5_p509_1 = "0";
        if(c5_p509_2_Checkbox.isChecked()) c5_p509_2 = "1"; else c5_p509_2 = "0";
        if(c5_p509_3_Checkbox.isChecked()) c5_p509_3 = "1"; else c5_p509_3 = "0";
        if(c5_p509_4_Checkbox.isChecked()) c5_p509_4 = "1"; else c5_p509_4 = "0";
        if(c5_p509_5_Checkbox.isChecked()) c5_p509_5 = "1"; else c5_p509_5 = "0";
        if(c5_p509_6_Checkbox.isChecked()) c5_p509_6 = "1"; else c5_p509_6 = "0";
        if(c5_p509_7_Checkbox.isChecked()) c5_p509_7 = "1"; else c5_p509_7 = "0";
        if(c5_p509_8_Checkbox.isChecked()) c5_p509_8 = "1"; else c5_p509_8 = "0";
        c5_p509_7_o = c5_p509_7_o_EditText.getText().toString();
        c5_p510_o = c5_p510_o_EditText.getText().toString();

        c5_p510 = c5_p510_RadioGroup.indexOfChild(c5_p510_RadioGroup.findViewById(c5_p510_RadioGroup.getCheckedRadioButtonId()))+"";
        c5_p511 = c5_p511_RadioGroup.indexOfChild(c5_p511_RadioGroup.findViewById(c5_p511_RadioGroup.getCheckedRadioButtonId()))+"";
        c5_p511_o = c5_p511_o_EditText.getText().toString();
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
            if(modulo5.getC5_p508_1().equals("1")) c5_p508_1_Checkbox.setChecked(true);
            if(modulo5.getC5_p508_2().equals("1")) c5_p508_2_Checkbox.setChecked(true);
            if(modulo5.getC5_p508_3().equals("1")) c5_p508_3_Checkbox.setChecked(true);
            if(modulo5.getC5_p508_4().equals("1")) c5_p508_4_Checkbox.setChecked(true);
            //if(modulo5.getC5_p508_5().equals("1")) c5_p508_5_Checkbox.setChecked(true);
            //Anthony M
            c5_p508_4_o_EditText.setText(modulo5.getC5_p508_4_o());
            if(modulo5.getC5_p509_1().equals("1")) c5_p509_1_Checkbox.setChecked(true);
            if(modulo5.getC5_p509_2().equals("1")) c5_p509_2_Checkbox.setChecked(true);
            if(modulo5.getC5_p509_3().equals("1")) c5_p509_3_Checkbox.setChecked(true);
            if(modulo5.getC5_p509_4().equals("1")) c5_p509_4_Checkbox.setChecked(true);
            if(modulo5.getC5_p509_5().equals("1")) c5_p509_5_Checkbox.setChecked(true);
            if(modulo5.getC5_p509_6().equals("1")) c5_p509_6_Checkbox.setChecked(true);
            if(modulo5.getC5_p509_7().equals("1")) c5_p509_7_Checkbox.setChecked(true);
            if(modulo5.getC5_p509_8().equals("1")) c5_p509_8_Checkbox.setChecked(true);
            c5_p509_7_o_EditText.setText(modulo5.getC5_p509_7_o());
            c5_p510_o_EditText.setText(modulo5.getC5_p510_o());

            if(!modulo5.getC5_p510().equals("-1") && !modulo5.getC5_p510().equals(""))((RadioButton)c5_p510_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p510()))).setChecked(true);
            if(!modulo5.getC5_p511().equals("-1") && !modulo5.getC5_p511().equals(""))((RadioButton)c5_p511_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p511()))).setChecked(true);
            c5_p511_o_EditText.setText(modulo5.getC5_p511_o());
        }
        inicio();
        data.close();
    }

    @Override
    public void llenarVista() {
        /*
        Data data = new Data(context);
        data.open();
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p508,idEncuestado)) m5_p508_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p509,idEncuestado)) m5_p509_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p510,idEncuestado)) m5_p510_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p511,idEncuestado)) m5_p511_linearlayout.setVisibility(View.GONE);
        data.close();

         */
    }

    @Override
    public boolean validarDatos() {

        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}


        if(m5_p508_linearlayout.getVisibility()==View.VISIBLE) {
            if(c5_p508_1.equals("0") && c5_p508_2.equals("0") && c5_p508_3.equals("0") && c5_p508_4.equals("0") )
            {mostrarMensaje("ERROR  “DEBE SELECCIONAR AL MENOS UNA ALTERNATIVA EN LA PREGUNTA 508”");return false;}
            // if(c5_p508_4_o_EditText.isEnabled() && c5_p508_4_o.equals("")) {mostrarMensaje("PREGUNTA 508 - OPCION 4: DEBE ESPECIFICAR OTRO");}
            if (c5_p508_4.equals("1")){//18052021
                if (c5_p508_4_o.trim().equals("")){ mostrarMensaje("PREGUNTA 508 - OPCION 4: DEBE ESPECIFICAR OTRO");return false;}//18052021
                if (c5_p508_4_o.length() < 3){ mostrarMensaje("ERROR : P508 OPCION 4. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;}
            }//18052021
//            if(c5_p508_1.equals("0") && c5_p508_2.equals("0") && c5_p508_3.equals("0") && c5_p508_4.equals("0") && c5_p508_5.equals("0") &&
//                    c5_p508_6.equals("0") && c5_p508_7.equals("0") && c5_p508_8.equals("0") && c5_p508_9.equals("0") && c5_p508_10.equals("0") &&
//                    c5_p508_11.equals("0")){
//                mostrarMensaje("PREGUNTA 508: DEBE SELECCIONAR ALGUNA OPCION");return false;
//            }else{
//                if(c5_p508_11.equals("1")){
//                    if(c5_p508_o.trim().equals("")){ mostrarMensaje("PREGUNTA 508 - OPCION 11: DEBE ESPECIFICAR OTRO");return false; }
//                }
//            }

            //PREGUNTA COGNITIVA P212=0 o P212 <>null

//            //--- PREGUNTA COGNITIVA  p212 != null && p212.equal(1)--//
//            if ( (p212.equals("1") && !(p212.equals(""))) &&
//                    (c5_p508_1.equals("1") || c5_p508_2.equals("1") || c5_p508_3.equals("1") || c5_p508_4.equals("1") || c5_p508_5.equals("1"))) {
//                mostrarMensaje("“SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
//            }

        }else{
            c5_p508_1 = "";
            c5_p508_2 = "";
            c5_p508_3 = "";
            c5_p508_4 = "";
            c5_p508_4_o = "";
            c5_p508_5 = "";
        }
        if (m5_p509_linearlayout.getVisibility()==View.VISIBLE){
            if(c5_p509_1.equals("0") && c5_p509_2.equals("0") && c5_p509_3.equals("0") && c5_p509_4.equals("0") && c5_p509_5.equals("0") && c5_p509_6.equals("0") && c5_p509_7.equals("0") && c5_p509_8.equals("0"))
            {mostrarMensaje("ERROR  “DEBE SELECCIONAR AL MENOS UNA ALTERNATIVA EN LA PREGUNTA 509”");return false;}
            //if(c5_p509_7_o_EditText.isEnabled() && c5_p509_7_o.equals("")) {mostrarMensaje("PREGUNTA 509 - OPCION 7: DEBE ESPECIFICAR OTRO");}
            if (c5_p509_7.equals("1")){//18052021
                if (c5_p509_7_o.trim().equals("")){ mostrarMensaje("PREGUNTA 509 - OPCION 7: DEBE ESPECIFICAR OTRO");return false;}//18052021
                if (c5_p509_7_o.length() < 3){ mostrarMensaje("ERROR : P509 OPCION 7. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;}
            }//18052021
//            if(c5_p509.equals("-1")){ mostrarMensaje("PREGUNTA 509: DEBE SELECCIONAR UNA OPCION");return false; } esto ya estaba comentado antes
        }else {
//            c5_p509 = "";
            c5_p509_1 = "";
            c5_p509_2 = "";
            c5_p509_3 = "";
            c5_p509_4 = "";
            c5_p509_5 = "";
            c5_p509_6 = "";
            c5_p509_7 = "";
            c5_p509_7_o = "";
            c5_p509_8 = "";
        }

        //        if(layoutp309.getVisibility()==View.VISIBLE) {
//            if(c3_p309.equals("-1")){ mostrarMensaje("PREGUNTA 309: DEBE SELECCIONAR UNA OPCION");return false; }
//            if(c3_p309.equals("7")){
//                if(c3_p309_o.trim().equals("")){ mostrarMensaje("PREGUNTA 309: DEBE ESPECIFICAR OTRO");return false; }
//            }
//        }else{
//            c3_p309 = "";
//            c3_p309_o = "";
//        }

        if (m5_p510_linearlayout.getVisibility()==View.VISIBLE){
            if(c5_p510.equals("-1")){ mostrarMensaje("PREGUNTA 510: DEBE SELECCIONAR UNA OPCION");return false; }
            //  if(c5_p510_o_EditText.isEnabled() && c5_p510_o.equals("")) {mostrarMensaje("PREGUNTA 510: DEBE ESPECIFICAR OTRO");}
            if(c5_p510.equals("4")){//18052021
                if(c5_p510_o.trim().equals("")){ mostrarMensaje("PREGUNTA 510: DEBE ESPECIFICAR OTRO");return false; }//18052021
                if (c5_p510_o.length() < 3){ mostrarMensaje("ERROR : P510. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;}
            }//18052021
        }else {
            c5_p510 = "";
            c5_p510_o = "";
        }

        if (m5_p511_linearlayout.getVisibility()==View.VISIBLE){
            if(c5_p511.equals("-1")){ mostrarMensaje("PREGUNTA 511: DEBE SELECCIONAR UNA OPCION");return false; }
            //  if(c5_p511_o_EditText.isEnabled() && c5_p511_o.equals("")) {mostrarMensaje("PREGUNTA 511: DEBE ESPECIFICAR OTRO");}

            if(c5_p511.equals("12")){
                if (c5_p511_o.equals("")){ mostrarMensaje("PREGUNTA 511: DEBE ESPECIFICAR OTRO");return false;}
            }
            if(c5_p511.equals("12") && !c5_p511_o.equals("") && c5_p511_o.length() < 3){mostrarMensaje("PREGUNTA 511: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}
        }else {
            c5_p511 = "";
            c5_p511_o = "";
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
    public void limpiar_p508(){
        c5_p508_1_Checkbox.setChecked(false);
        c5_p508_2_Checkbox.setChecked(false);
        c5_p508_3_Checkbox.setChecked(false);
        c5_p508_4_Checkbox.setChecked(false);
       // c5_p508_5_Checkbox.setChecked(false);
        c5_p508_4_o_EditText.setText("");
    }
    public void limpiar_p509(){
        c5_p509_1_Checkbox.setChecked(false);
        c5_p509_2_Checkbox.setChecked(false);
        c5_p509_3_Checkbox.setChecked(false);
        c5_p509_4_Checkbox.setChecked(false);
        c5_p509_5_Checkbox.setChecked(false);
        c5_p509_6_Checkbox.setChecked(false);
        c5_p509_7_Checkbox.setChecked(false);
        c5_p509_7_o_EditText.setText("");
        c5_p509_8_Checkbox.setChecked(false);
    }
    public void limpiar_p510(){
        c5_p510_RadioGroup.clearCheck();
        c5_p510_o_EditText.setText("");
    }
    public void limpiar_p511(){
        c5_p511_RadioGroup.clearCheck();
        c5_p511_o_EditText.setText("");
    }
    public void validarFlujo58(){ //FLUJO 58
        llenarVariables();
        if(edad >25 && (p505.equals("2") || p506.equals("2")) && (p501.equals("10") || p501.equals("11"))){}
        if(c5_p508_1.equals("1") || c5_p508_2.equals("1") || c5_p508_3.equals("1") || c5_p508_4.equals("1")){
            m5_p509_linearlayout.setVisibility(View.VISIBLE);
        }else if(c5_p508_1.equals("0") && c5_p508_2.equals("0") && c5_p508_3.equals("0") && c5_p508_4.equals("0")){
            limpiar_p509();
            m5_p509_linearlayout.setVisibility(View.GONE);
        }
    }
    public void validarAchurar508(){
        llenarVariables();
        if(c5_p508_1.equals("1") || c5_p508_2.equals("1") || c5_p508_3.equals("1") || c5_p508_4.equals("1")){
            c5_p508_5_Checkbox.setChecked(false);c5_p508_5_Checkbox.setEnabled(false);
        }else {
            c5_p508_5_Checkbox.setEnabled(true);c5_p508_5_Checkbox.setEnabled(true);
        }
    }
    public void validarAchurar509(){
        llenarVariables();
        if(c5_p509_1.equals("1") || c5_p509_2.equals("1") || c5_p509_3.equals("1") || c5_p509_4.equals("1") ||
                c5_p509_5.equals("1") || c5_p509_6.equals("1") || c5_p509_7.equals("1")){
            c5_p509_8_Checkbox.setChecked(false);c5_p509_8_Checkbox.setEnabled(false);
        }else {
            c5_p509_8_Checkbox.setEnabled(true);c5_p509_8_Checkbox.setEnabled(true);
        }
    }
    public void ocultarFragmentP512P513(boolean ocultar){
        Data data = new Data(context);
        data.open();
        if(ocultar){
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.modulo5_c5_p512,"");
            contentValues.put(SQLConstantes.modulo5_c5_p513,"");
            contentValues.put(SQLConstantes.modulo5_c5_p514,"");
            contentValues.put(SQLConstantes.modulo5_c5_p514_o,"");
            contentValues.put(SQLConstantes.modulo5_c5_p515,"");
            contentValues.put(SQLConstantes.modulo5_c5_p515_o,"");
            contentValues.put(SQLConstantes.modulo5_c5_p516,"");
            contentValues.put(SQLConstantes.modulo5_c5_p516_o,"");
            //Setea valores string vacio en los campos del fragment
            data.actualizarElemento(getNombreTabla(), contentValues, idEncuestado);
            //Oculta el fragment
            data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p512p513, "-1", idEncuestado);
        }else {
            data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p512p513, "1", idEncuestado);
        }
        data.close();
    }
    public boolean validar_P508(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 3 && edad <= 17 && p505.equals("1") && p506.equals("1"))
            valido = true;
        return valido;
    }
    public boolean validar_P509(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 3 && edad <= 17 &&
                (c5_p508_1.equals("1") || c5_p508_2.equals("1") || c5_p508_3.equals("1") || c5_p508_4.equals("1")))
            valido = true;
        return valido;
    }
    public boolean validar_P510(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 3 //&& edad <= 17
                && p503.equals("1")) // Se agregó para que tenga coherencia del FLUJO 55
            valido = true;
        return valido;
    }
    public boolean validar_P511(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 3 && edad <= 25 && (p505.equals("2") || p506.equals("2")) //&& p503.equals("2")
        )
            valido = true;
        return valido;
    }
}
