package com.example.ricindigus.enpove2021.fragments.modulo8;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
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

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo8;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOFragment;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOLayout;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP805P808 extends FragmentPagina {

    Context context;
    String idEncuestado;
    String idVivienda, idHogar, idInformante;
    Spinner informanteSpinner;

    //PREGUNTA 803
    RadioGroup   c8_p803_RadioGroup;
    String c8_p803;
    LinearLayout m8_p803_linearlayout;

    //PREGUNTA 804
    CheckBox c8_p804_1_CheckBox, c8_p804_2_CheckBox, c8_p804_3_CheckBox, c8_p804_4_CheckBox,
            c8_p804_5_CheckBox, c8_p804_6_CheckBox, c8_p804_7_CheckBox, c8_p804_8_CheckBox,
            c8_p804_9_CheckBox, c8_p804_10_CheckBox, c8_p804_11_CheckBox, c8_p804_12_CheckBox,
            c8_p804_13_CheckBox,c8_p804_14_CheckBox;
    EditText c8_p804_o_EditText;
    private String c8_p804_1;
    private String c8_p804_2;
    private String c8_p804_3;
    private String c8_p804_4;
    private String c8_p804_5;
    private String c8_p804_6;
    private String c8_p804_7;
    private String c8_p804_8;
    private String c8_p804_9;
    private String c8_p804_10;
    private String c8_p804_11;
    private String c8_p804_12;
    private String c8_p804_13;
    private String c8_p804_14;
    private String c8_p804_o;
    LinearLayout m8_p804_linearlayout;


    //PREGUNTA 805
    CheckBox c8_p805_1_CheckBox, c8_p805_2_CheckBox, c8_p805_3_CheckBox, c8_p805_4_CheckBox,
            c8_p805_5_CheckBox, c8_p805_6_CheckBox, c8_p805_7_CheckBox, c8_p805_8_CheckBox,
            c8_p805_9_CheckBox;
    private String c8_p805_1;
    private String c8_p805_2;
    private String c8_p805_3;
    private String c8_p805_4;
    private String c8_p805_5;
    private String c8_p805_6;
    private String c8_p805_7;
    private String c8_p805_8;
    private String c8_p805_9;
    LinearLayout m8_p805_linearlayout;

    //PREGUNTA 806
    RadioGroup c8_p806_RadioGroup;
    private String c8_p806_1;
    LinearLayout m8_p806_linearlayout;
//
//    //PREGUNTA 807
//    RadioGroup c8_p807_RadioGroup;
//    private String c8_p807;
//    LinearLayout m8_p807_linearlayout;
//
//    //agragdecimineto
//    EditText etEmail;
//    private String email;


//    RadioGroup c8_p805_1_RadioGroup, c8_p805_2_RadioGroup, c8_p805_3_RadioGroup, c8_p805_4_RadioGroup,
//            c8_p805_5_RadioGroup;
//    Spinner c8_p806_1_Spinner, c8_p806_2_Spinner, c8_p806_3_Spinner, c8_p806_4_Spinner,
//            c8_p806_5_Spinner, c8_p806_6_Spinner;
//    RadioGroup c8_p807_RadioGroup;
//    CheckBox c8_p808_1_CheckBox, c8_p808_2_CheckBox, c8_p808_3_CheckBox, c8_p808_4_CheckBox,
//            c8_p808_5_CheckBox, c8_p808_6_CheckBox, c8_p808_7_CheckBox, c8_p808_8_CheckBox,
//            c8_p808_9_CheckBox, c8_p808_10_CheckBox, c8_p808_11_CheckBox, c8_p808_12_CheckBox,
//            c8_p808_13_CheckBox;
//    EditText c8_p808_o_EditText;
//    LinearLayout m8_p805_linearlayout, m8_p806_linearlayout, m8_p807_linearlayout, m8_p808_linearlayout;
//
//    private String c8_p805_1;
//    private String c8_p805_2;
//    private String c8_p805_3;
//    private String c8_p805_4;
//    private String c8_p805_5;
//    private String c8_p806_1;
//    private String c8_p806_2;
//    private String c8_p806_3;
//    private String c8_p806_4;
//    private String c8_p806_5;
//    private String c8_p806_6;
//    private String c8_p807;
//    private String c8_p808_1;
//    private String c8_p808_2;
//    private String c8_p808_3;
//    private String c8_p808_4;
//    private String c8_p808_5;
//    private String c8_p808_6;
//    private String c8_p808_7;
//    private String c8_p808_8;
//    private String c8_p808_9;
//    private String c8_p808_10;
//    private String c8_p808_11;
//    private String c8_p808_12;
//    private String c8_p808_13;
//    private String c8_p808_o;


    @SuppressLint("ValidFragment")
    public FragmentP805P808(String idEncuestado, Context context) {
        this.context = context;
        this.idEncuestado = idEncuestado;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idVivienda = residente.getId_vivienda();
        idHogar = residente.getId_hogar();
        idInformante = "";
        data.close();
    }

    public FragmentP805P808() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p805_p808, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

        c8_p803_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_803_radiogroup_C8_P803);

        c8_p804_1_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_1);
        c8_p804_2_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_2);
        c8_p804_3_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_3);
        c8_p804_4_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_4);
        c8_p804_5_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_5);
        c8_p804_6_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_6);
        c8_p804_7_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_7);
        c8_p804_8_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_8);
        c8_p804_9_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_9);
        c8_p804_10_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_10);
        c8_p804_11_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_11);
        c8_p804_12_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_12);
        c8_p804_13_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_13);
        c8_p804_14_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_14);

        c8_p804_o_EditText = (EditText) rootView.findViewById(R.id.mod8_804_edittext_C8_P804_O);

        c8_p805_1_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_805_checkbox_C8_P805_1);
        c8_p805_2_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_805_checkbox_C8_P805_2);
        c8_p805_3_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_805_checkbox_C8_P805_3);
        c8_p805_4_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_805_checkbox_C8_P805_4);
        c8_p805_5_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_805_checkbox_C8_P805_5);
        c8_p805_6_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_805_checkbox_C8_P805_6);
        c8_p805_7_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_805_checkbox_C8_P805_7);
        c8_p805_8_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_805_checkbox_C8_P805_8);
        c8_p805_9_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_805_checkbox_C8_P805_9);

        c8_p806_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_806_radiogroup_C8_P806);
//
//        c8_p807_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_807_radiogroup_C8_P807);
//
//        etEmail = (EditText) rootView.findViewById(R.id.mod8_edittext_email);

        m8_p803_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p803);
        m8_p804_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p804);

        m8_p805_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p805);
        m8_p806_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p806);
//        m8_p807_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p807);


//        //   c8_p805_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_805_radiogroup_C8_P805_1);
//        //  c8_p805_2_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_805_radiogroup_C8_P805_2);
//        // c8_p805_3_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_805_radiogroup_C8_P805_3);
//        // c8_p805_4_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_805_radiogroup_C8_P805_4);
//        // c8_p805_5_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_805_radiogroup_C8_P805_5);
//
//        // c8_p806_1_Spinner = (Spinner) rootView.findViewById(R.id.mod8_806_spinner_C8_P806_1);
//        // c8_p806_2_Spinner = (Spinner) rootView.findViewById(R.id.mod8_806_spinner_C8_P806_2);
//        // c8_p806_3_Spinner = (Spinner) rootView.findViewById(R.id.mod8_806_spinner_C8_P806_3);
//        // c8_p806_4_Spinner = (Spinner) rootView.findViewById(R.id.mod8_806_spinner_C8_P806_4);
//        // c8_p806_5_Spinner = (Spinner) rootView.findViewById(R.id.mod8_806_spinner_C8_P806_5);
//        //  c8_p806_6_Spinner = (Spinner) rootView.findViewById(R.id.mod8_806_spinner_C8_P806_6);
//
//        c8_p807_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_807_radiogroup_C8_P807);
//
//        c8_p808_1_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_808_checkbox_C8_P808_1);
//        c8_p808_2_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_808_checkbox_C8_P808_2);
//        c8_p808_3_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_808_checkbox_C8_P808_3);
//        c8_p808_4_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_808_checkbox_C8_P808_4);
//        c8_p808_5_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_808_checkbox_C8_P808_5);
//        c8_p808_6_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_808_checkbox_C8_P808_6);
//        c8_p808_7_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_808_checkbox_C8_P808_7);
//        c8_p808_8_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_808_checkbox_C8_P808_8);
//        c8_p808_9_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_808_checkbox_C8_P808_9);
//        c8_p808_10_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_808_checkbox_C8_P808_10);
//        c8_p808_11_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_808_checkbox_C8_P808_11);
//        c8_p808_12_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_808_checkbox_C8_P808_12);
//        c8_p808_13_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_808_checkbox_C8_P808_13);
//
//        c8_p808_o_EditText = (EditText) rootView.findViewById(R.id.mod8_808_edittext_C8_P808_O);
//
//        m8_p805_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p805);
//        m8_p806_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p806);
//        m8_p807_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p807);
//        m8_p808_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p808);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        configurarEditText(c8_p804_o_EditText,m8_p804_linearlayout,0,30);
        controlarChecked(c8_p804_14_CheckBox,c8_p804_o_EditText);

        c8_p804_14_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    mostrarPregunta6(false);
                    c8_p804_o_EditText.setEnabled(true);
                    c8_p804_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);

                }else{
                    mostrarPregunta6(false);
                    c8_p804_o_EditText.setEnabled(false);
                    c8_p804_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }


            }
        });


        c8_p805_1_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar();
            }
        });
        c8_p805_2_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar();
            }
        });
        c8_p805_3_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar();
            }
        });
        c8_p805_4_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar();
            }
        });
        c8_p805_5_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar();
            }
        });
        c8_p805_6_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar();
            }
        });
        c8_p805_7_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar();
            }
        });
        c8_p805_8_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar();
            }
        });
        c8_p805_9_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    c8_p805_1_CheckBox.setChecked(false);
                    c8_p805_2_CheckBox.setChecked(false);
                    c8_p805_3_CheckBox.setChecked(false);
                    c8_p805_4_CheckBox.setChecked(false);
                    c8_p805_5_CheckBox.setChecked(false);
                    c8_p805_6_CheckBox.setChecked(false);
                    c8_p805_7_CheckBox.setChecked(false);
                    c8_p805_8_CheckBox.setChecked(false);

                    c8_p805_1_CheckBox.setEnabled(false);
                    c8_p805_2_CheckBox.setEnabled(false);
                    c8_p805_3_CheckBox.setEnabled(false);
                    c8_p805_4_CheckBox.setEnabled(false);
                    c8_p805_5_CheckBox.setEnabled(false);
                    c8_p805_6_CheckBox.setEnabled(false);
                    c8_p805_7_CheckBox.setEnabled(false);
                    c8_p805_8_CheckBox.setEnabled(false);


                }else{
                    c8_p805_1_CheckBox.setEnabled(true);
                    c8_p805_2_CheckBox.setEnabled(true);
                    c8_p805_3_CheckBox.setEnabled(true);
                    c8_p805_4_CheckBox.setEnabled(true);
                    c8_p805_5_CheckBox.setEnabled(true);
                    c8_p805_6_CheckBox.setEnabled(true);
                    c8_p805_7_CheckBox.setEnabled(true);
                    c8_p805_8_CheckBox.setEnabled(true);
                }
            }
        });

        c8_p803_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                switch (seleccionado){
//                    SI P803 = 2 Entonces pase a P805_1
//                    SI P803 = 1 Entonces pase a P804_1

                    case 1:
                        m8_p804_linearlayout.setVisibility(View.VISIBLE);
                        limpiar805();
                        m8_p805_linearlayout.setVisibility(View.GONE);
                        break;
                    case 2:
                        limpiar804();
                        m8_p804_linearlayout.setVisibility(View.GONE);
                        m8_p805_linearlayout.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        llenarVista();
        cargarDatos();
//        configurarEditText(c8_p808_o_EditText,m8_p808_linearlayout,0,30);
//        controlarChecked(c8_p808_13_CheckBox,c8_p808_o_EditText);
//        c8_p807_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
//                switch (seleccionado){
//                    case 1:
//                        m8_p808_linearlayout.setVisibility(View.VISIBLE);
//                        break;
//                    case 2:
//                        c8_p808_1_CheckBox.setChecked(false);
//                        c8_p808_2_CheckBox.setChecked(false);
//                        c8_p808_3_CheckBox.setChecked(false);
//                        c8_p808_4_CheckBox.setChecked(false);
//                        c8_p808_5_CheckBox.setChecked(false);
//                        c8_p808_6_CheckBox.setChecked(false);
//                        c8_p808_7_CheckBox.setChecked(false);
//                        c8_p808_8_CheckBox.setChecked(false);
//                        c8_p808_9_CheckBox.setChecked(false);
//                        c8_p808_10_CheckBox.setChecked(false);
//                        c8_p808_11_CheckBox.setChecked(false);
//                        c8_p808_12_CheckBox.setChecked(false);
//                        c8_p808_13_CheckBox.setChecked(false);
//                        c8_p808_o_EditText.setText("");
//                        m8_p808_linearlayout.setVisibility(View.GONE);
//                        break;
//                }
//            }
//        });
//        cargarDatos();
    }

    public void mostrarPregunta6(boolean visible){
        if(visible){
            m8_p805_linearlayout.setVisibility(View.GONE);
            limpiar805();
        }else {
            limpiar805();
            m8_p805_linearlayout.setVisibility(View.GONE);
        }
    }



    private void limpiar804() {
        c8_p804_1_CheckBox.setChecked(false);
        c8_p804_2_CheckBox.setChecked(false);
        c8_p804_3_CheckBox.setChecked(false);
        c8_p804_4_CheckBox.setChecked(false);
        c8_p804_5_CheckBox.setChecked(false);
        c8_p804_6_CheckBox.setChecked(false);
        c8_p804_7_CheckBox.setChecked(false);
        c8_p804_8_CheckBox.setChecked(false);
        c8_p804_9_CheckBox.setChecked(false);
        c8_p804_10_CheckBox.setChecked(false);
        c8_p804_11_CheckBox.setChecked(false);
        c8_p804_12_CheckBox.setChecked(false);
        c8_p804_13_CheckBox.setChecked(false);
        c8_p804_14_CheckBox.setChecked(false);
        c8_p804_o_EditText.setText("");
    }


    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo8_id_informante,idInformante);

        contentValues.put(SQLConstantes.modulo8_c8_p803,c8_p803);

        contentValues.put(SQLConstantes.modulo8_c8_p804_1,c8_p804_1);
        contentValues.put(SQLConstantes.modulo8_c8_p804_2,c8_p804_2);
        contentValues.put(SQLConstantes.modulo8_c8_p804_3,c8_p804_3);
        contentValues.put(SQLConstantes.modulo8_c8_p804_4,c8_p804_4);
        contentValues.put(SQLConstantes.modulo8_c8_p804_5,c8_p804_5);
        contentValues.put(SQLConstantes.modulo8_c8_p804_6,c8_p804_6);
        contentValues.put(SQLConstantes.modulo8_c8_p804_7,c8_p804_7);
        contentValues.put(SQLConstantes.modulo8_c8_p804_8,c8_p804_8);
        contentValues.put(SQLConstantes.modulo8_c8_p804_9,c8_p804_9);
        contentValues.put(SQLConstantes.modulo8_c8_p804_10,c8_p804_10);
        contentValues.put(SQLConstantes.modulo8_c8_p804_11,c8_p804_11);
        contentValues.put(SQLConstantes.modulo8_c8_p804_12,c8_p804_12);
        contentValues.put(SQLConstantes.modulo8_c8_p804_13,c8_p804_13);
        contentValues.put(SQLConstantes.modulo8_c8_p804_14,c8_p804_14);
        contentValues.put(SQLConstantes.modulo8_c8_p804_o,c8_p804_o);

        contentValues.put(SQLConstantes.modulo8_c8_p805_1,c8_p805_1);
        contentValues.put(SQLConstantes.modulo8_c8_p805_2,c8_p805_2);
        contentValues.put(SQLConstantes.modulo8_c8_p805_3,c8_p805_3);
        contentValues.put(SQLConstantes.modulo8_c8_p805_4,c8_p805_4);
        contentValues.put(SQLConstantes.modulo8_c8_p805_5,c8_p805_5);
        contentValues.put(SQLConstantes.modulo8_c8_p805_6,c8_p805_6);
        contentValues.put(SQLConstantes.modulo8_c8_p805_7,c8_p805_7);
        contentValues.put(SQLConstantes.modulo8_c8_p805_8,c8_p805_8);
        contentValues.put(SQLConstantes.modulo8_c8_p805_9,c8_p805_9);

        contentValues.put(SQLConstantes.modulo8_c8_p806_1,c8_p806_1);
//
//        contentValues.put(SQLConstantes.modulo8_c8_p807,c8_p807);
//
//        contentValues.put(SQLConstantes.modulo8_email,email);

        if(!data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo8 modulo8 = new Modulo8();
            modulo8.set_id(idEncuestado);
            modulo8.setIdHogar(idHogar);
            modulo8.setIdVivienda(idVivienda);
            data.insertarElemento(getNombreTabla(), modulo8.toValues());
        }
        data.actualizarElemento(getNombreTabla(), contentValues, idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp805p808,"1",idEncuestado);

        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo8_COB800,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo8_COB800,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();

//        Data data = new Data(context);
//        data.open();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(SQLConstantes.modulo8_id_informante,idInformante);
//        contentValues.put(SQLConstantes.modulo8_c8_p805_1,c8_p805_1);
//        contentValues.put(SQLConstantes.modulo8_c8_p805_2,c8_p805_2);
//        contentValues.put(SQLConstantes.modulo8_c8_p805_3,c8_p805_3);
//        contentValues.put(SQLConstantes.modulo8_c8_p805_4,c8_p805_4);
//        contentValues.put(SQLConstantes.modulo8_c8_p805_5,c8_p805_5);
//        contentValues.put(SQLConstantes.modulo8_c8_p806_1,c8_p806_1);
//        contentValues.put(SQLConstantes.modulo8_c8_p806_2,c8_p806_2);
//        contentValues.put(SQLConstantes.modulo8_c8_p806_3,c8_p806_3);
//        contentValues.put(SQLConstantes.modulo8_c8_p806_4,c8_p806_4);
//        contentValues.put(SQLConstantes.modulo8_c8_p806_5,c8_p806_5);
//        contentValues.put(SQLConstantes.modulo8_c8_p806_6,c8_p806_6);
//        contentValues.put(SQLConstantes.modulo8_c8_p807,c8_p807);
//        contentValues.put(SQLConstantes.modulo8_c8_p808_1,c8_p808_1);
//        contentValues.put(SQLConstantes.modulo8_c8_p808_2,c8_p808_2);
//        contentValues.put(SQLConstantes.modulo8_c8_p808_3,c8_p808_3);
//        contentValues.put(SQLConstantes.modulo8_c8_p808_4,c8_p808_4);
//        contentValues.put(SQLConstantes.modulo8_c8_p808_5,c8_p808_5);
//        contentValues.put(SQLConstantes.modulo8_c8_p808_6,c8_p808_6);
//        contentValues.put(SQLConstantes.modulo8_c8_p808_7,c8_p808_7);
//        contentValues.put(SQLConstantes.modulo8_c8_p808_8,c8_p808_8);
//        contentValues.put(SQLConstantes.modulo8_c8_p808_9,c8_p808_9);
//        contentValues.put(SQLConstantes.modulo8_c8_p808_10,c8_p808_10);
//        contentValues.put(SQLConstantes.modulo8_c8_p808_11,c8_p808_11);
//        contentValues.put(SQLConstantes.modulo8_c8_p808_12,c8_p808_12);
//        contentValues.put(SQLConstantes.modulo8_c8_p808_13,c8_p808_13);
//        contentValues.put(SQLConstantes.modulo8_c8_p808_o,c8_p808_o);
//        if(!data.existeElemento(getNombreTabla(),idEncuestado)){
//            Modulo8 modulo8 = new Modulo8();
//            modulo8.set_id(idEncuestado);
//            modulo8.setIdHogar(idHogar);
//            modulo8.setIdVivienda(idVivienda);
//            data.insertarElemento(getNombreTabla(), modulo8.toValues());
//        }
//        data.actualizarElemento(getNombreTabla(), contentValues, idEncuestado);
//        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
//        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp805p808,"1",idEncuestado);
//        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
//        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo8_COB800,"1",idEncuestado);
//        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo8_COB800,"0",idEncuestado);
//        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
//        data.close();
    }





    private void limpiar805() {
        c8_p805_1_CheckBox.setChecked(false);
        c8_p805_2_CheckBox.setChecked(false);
        c8_p805_3_CheckBox.setChecked(false);
        c8_p805_4_CheckBox.setChecked(false);
        c8_p805_5_CheckBox.setChecked(false);
        c8_p805_6_CheckBox.setChecked(false);
        c8_p805_7_CheckBox.setChecked(false);
        c8_p805_8_CheckBox.setChecked(false);
        c8_p805_9_CheckBox.setChecked(false);

    }



    @Override
    public void llenarVariables() {

        idInformante = obtener_Nresidente(informanteSpinner);

        c8_p803 = c8_p803_RadioGroup.indexOfChild(c8_p803_RadioGroup.findViewById(c8_p803_RadioGroup.getCheckedRadioButtonId()))+"";

        if(c8_p804_1_CheckBox.isChecked()) c8_p804_1 = "1"; else c8_p804_1 = "0";
        if(c8_p804_2_CheckBox.isChecked()) c8_p804_2 = "1"; else c8_p804_2 = "0";
        if(c8_p804_3_CheckBox.isChecked()) c8_p804_3 = "1"; else c8_p804_3 = "0";
        if(c8_p804_4_CheckBox.isChecked()) c8_p804_4 = "1"; else c8_p804_4 = "0";
        if(c8_p804_5_CheckBox.isChecked()) c8_p804_5 = "1"; else c8_p804_5 = "0";
        if(c8_p804_6_CheckBox.isChecked()) c8_p804_6 = "1"; else c8_p804_6 = "0";
        if(c8_p804_7_CheckBox.isChecked()) c8_p804_7 = "1"; else c8_p804_7 = "0";
        if(c8_p804_8_CheckBox.isChecked()) c8_p804_8 = "1"; else c8_p804_8 = "0";
        if(c8_p804_9_CheckBox.isChecked()) c8_p804_9 = "1"; else c8_p804_9 = "0";
        if(c8_p804_10_CheckBox.isChecked()) c8_p804_10 = "1"; else c8_p804_10 = "0";
        if(c8_p804_11_CheckBox.isChecked()) c8_p804_11 = "1"; else c8_p804_11 = "0";
        if(c8_p804_12_CheckBox.isChecked()) c8_p804_12 = "1"; else c8_p804_12 = "0";
        if(c8_p804_13_CheckBox.isChecked()) c8_p804_13 = "1"; else c8_p804_13 = "0";
        if(c8_p804_14_CheckBox.isChecked()) c8_p804_14 = "1"; else c8_p804_14 = "0";
        c8_p804_o = c8_p804_o_EditText.getText().toString();


        if(c8_p805_1_CheckBox.isChecked()) c8_p805_1 = "1"; else c8_p805_1 = "0";
        if(c8_p805_2_CheckBox.isChecked()) c8_p805_2 = "1"; else c8_p805_2 = "0";
        if(c8_p805_3_CheckBox.isChecked()) c8_p805_3 = "1"; else c8_p805_3 = "0";
        if(c8_p805_4_CheckBox.isChecked()) c8_p805_4 = "1"; else c8_p805_4 = "0";
        if(c8_p805_5_CheckBox.isChecked()) c8_p805_5 = "1"; else c8_p805_5 = "0";
        if(c8_p805_6_CheckBox.isChecked()) c8_p805_6 = "1"; else c8_p805_6 = "0";
        if(c8_p805_7_CheckBox.isChecked()) c8_p805_7 = "1"; else c8_p805_7 = "0";
        if(c8_p805_8_CheckBox.isChecked()) c8_p805_8 = "1"; else c8_p805_8 = "0";
        if(c8_p805_9_CheckBox.isChecked()) c8_p805_9 = "1"; else c8_p805_9 = "0";

        c8_p806_1 = c8_p806_RadioGroup.indexOfChild(c8_p806_RadioGroup.findViewById(c8_p806_RadioGroup.getCheckedRadioButtonId())) + "";
//
//        c8_p807 = c8_p807_RadioGroup.indexOfChild(c8_p807_RadioGroup.findViewById(c8_p807_RadioGroup.getCheckedRadioButtonId())) + "";
//
//        email = etEmail.getText().toString();

//        idInformante = informanteSpinner.getSelectedItemPosition() + "";
//        c8_p805_1 = c8_p805_1_RadioGroup.indexOfChild(c8_p805_1_RadioGroup.findViewById(c8_p805_1_RadioGroup.getCheckedRadioButtonId())) + "";
//        c8_p805_2 = c8_p805_2_RadioGroup.indexOfChild(c8_p805_2_RadioGroup.findViewById(c8_p805_2_RadioGroup.getCheckedRadioButtonId())) + "";
//        c8_p805_3 = c8_p805_3_RadioGroup.indexOfChild(c8_p805_3_RadioGroup.findViewById(c8_p805_3_RadioGroup.getCheckedRadioButtonId())) + "";
//        c8_p805_4 = c8_p805_4_RadioGroup.indexOfChild(c8_p805_4_RadioGroup.findViewById(c8_p805_4_RadioGroup.getCheckedRadioButtonId())) + "";
//        c8_p805_5 = c8_p805_5_RadioGroup.indexOfChild(c8_p805_5_RadioGroup.findViewById(c8_p805_5_RadioGroup.getCheckedRadioButtonId())) + "";
//        c8_p806_1 = c8_p806_1_Spinner.getSelectedItemPosition() + "";
//        c8_p806_2 = c8_p806_2_Spinner.getSelectedItemPosition() + "";
//        c8_p806_3 = c8_p806_3_Spinner.getSelectedItemPosition() + "";
//        c8_p806_4 = c8_p806_4_Spinner.getSelectedItemPosition() + "";
//        c8_p806_5 = c8_p806_5_Spinner.getSelectedItemPosition() + "";
//        c8_p806_6 = c8_p806_6_Spinner.getSelectedItemPosition() + "";
//        c8_p807 = c8_p807_RadioGroup.indexOfChild(c8_p807_RadioGroup.findViewById(c8_p807_RadioGroup.getCheckedRadioButtonId())) + "";
//        if(c8_p808_1_CheckBox.isChecked()) c8_p808_1 = "1"; else c8_p808_1 = "0";
//        if(c8_p808_2_CheckBox.isChecked()) c8_p808_2 = "1"; else c8_p808_2 = "0";
//        if(c8_p808_3_CheckBox.isChecked()) c8_p808_3 = "1"; else c8_p808_3 = "0";
//        if(c8_p808_4_CheckBox.isChecked()) c8_p808_4 = "1"; else c8_p808_4 = "0";
//        if(c8_p808_5_CheckBox.isChecked()) c8_p808_5 = "1"; else c8_p808_5 = "0";
//        if(c8_p808_6_CheckBox.isChecked()) c8_p808_6 = "1"; else c8_p808_6 = "0";
//        if(c8_p808_7_CheckBox.isChecked()) c8_p808_7 = "1"; else c8_p808_7 = "0";
//        if(c8_p808_8_CheckBox.isChecked()) c8_p808_8 = "1"; else c8_p808_8 = "0";
//        if(c8_p808_9_CheckBox.isChecked()) c8_p808_9 = "1"; else c8_p808_9 = "0";
//        if(c8_p808_10_CheckBox.isChecked()) c8_p808_10 = "1"; else c8_p808_10 = "0";
//        if(c8_p808_11_CheckBox.isChecked()) c8_p808_11 = "1"; else c8_p808_11 = "0";
//        if(c8_p808_12_CheckBox.isChecked()) c8_p808_12 = "1"; else c8_p808_12 = "0";
//        if(c8_p808_13_CheckBox.isChecked()) c8_p808_13 = "1"; else c8_p808_13 = "0";
//        c8_p808_o = c8_p808_o_EditText.getText().toString();
    }

    @Override
    public void cargarDatos() {
        Data data = new Data(context);
        data.open();
        if(data.existeElemento(getNombreTabla(), idEncuestado)){
            Modulo8 modulo8 = data.getModulo8(idEncuestado);
            ArrayList<String> informantes = data.getListaInformantesMayor18(idHogar);
            UtilsMethodsInputs.loadSpinner(informantes,informanteSpinner,context);
            if(modulo8.getIdInformante() != null && !modulo8.getIdInformante().equals("")) informanteSpinner.setSelection(obtener_posicion(modulo8.getIdInformante(),informanteSpinner));

            if(!modulo8.getC8_p803().equals("-1") && !modulo8.getC8_p803().equals(""))((RadioButton)c8_p803_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p803()))).setChecked(true);

            if(modulo8.getC8_p804_1().equals("1")) c8_p804_1_CheckBox.setChecked(true);
            if(modulo8.getC8_p804_2().equals("1")) c8_p804_2_CheckBox.setChecked(true);
            if(modulo8.getC8_p804_3().equals("1")) c8_p804_3_CheckBox.setChecked(true);
            if(modulo8.getC8_p804_4().equals("1")) c8_p804_4_CheckBox.setChecked(true);
            if(modulo8.getC8_p804_5().equals("1")) c8_p804_5_CheckBox.setChecked(true);
            if(modulo8.getC8_p804_6().equals("1")) c8_p804_6_CheckBox.setChecked(true);
            if(modulo8.getC8_p804_7().equals("1")) c8_p804_7_CheckBox.setChecked(true);
            if(modulo8.getC8_p804_8().equals("1")) c8_p804_8_CheckBox.setChecked(true);
            if(modulo8.getC8_p804_9().equals("1")) c8_p804_9_CheckBox.setChecked(true);
            if(modulo8.getC8_p804_10().equals("1")) c8_p804_10_CheckBox.setChecked(true);
            if(modulo8.getC8_p804_11().equals("1")) c8_p804_11_CheckBox.setChecked(true);
            if(modulo8.getC8_p804_12().equals("1")) c8_p804_12_CheckBox.setChecked(true);
            if(modulo8.getC8_p804_13().equals("1")) c8_p804_13_CheckBox.setChecked(true);
            if(modulo8.getC8_p804_14().equals("1")) c8_p804_14_CheckBox.setChecked(true);
            c8_p804_o_EditText.setText(modulo8.getC8_p804_o());

            if(modulo8.getC8_p805_1().equals("1")) c8_p805_1_CheckBox.setChecked(true);
            if(modulo8.getC8_p805_2().equals("1")) c8_p805_2_CheckBox.setChecked(true);
            if(modulo8.getC8_p805_3().equals("1")) c8_p805_3_CheckBox.setChecked(true);
            if(modulo8.getC8_p805_4().equals("1")) c8_p805_4_CheckBox.setChecked(true);
            if(modulo8.getC8_p805_5().equals("1")) c8_p805_5_CheckBox.setChecked(true);
            if(modulo8.getC8_p805_6().equals("1")) c8_p805_6_CheckBox.setChecked(true);
            if(modulo8.getC8_p805_7().equals("1")) c8_p805_7_CheckBox.setChecked(true);
            if(modulo8.getC8_p805_8().equals("1")) c8_p805_8_CheckBox.setChecked(true);
            if(modulo8.getC8_p805_9().equals("1")) c8_p805_9_CheckBox.setChecked(true);

            if(!modulo8.getC8_p806_1().equals("-1")&&!modulo8.getC8_p806_1().equals(""))((RadioButton)c8_p806_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p806_1()))).setChecked(true);

//            if(!modulo8.getC8_p807().equals("-1")&&!modulo8.getC8_p807().equals(""))((RadioButton)c8_p807_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p807()))).setChecked(true);
//
//            etEmail.setText(modulo8.getEmail());

        }

        data.close();


//        Data data = new Data(context);
//        data.open();
//        if(data.existeElemento(getNombreTabla(), idEncuestado)){
//            Modulo8 modulo8 = data.getModulo8(idEncuestado);
//            ArrayList<String> residentes = data.getListaSpinnerResidentesHogar(modulo8.getIdHogar());
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,residentes);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            informanteSpinner.setAdapter(adapter);
//            informanteSpinner.setSelection(Integer.parseInt(modulo8.getIdInformante()));
//            if(!modulo8.getC8_p805_1().equals("-1")&&!modulo8.getC8_p805_1().equals(""))((RadioButton)c8_p805_1_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p805_1()))).setChecked(true);
//            if(!modulo8.getC8_p805_2().equals("-1")&&!modulo8.getC8_p805_2().equals(""))((RadioButton)c8_p805_2_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p805_2()))).setChecked(true);
//            if(!modulo8.getC8_p805_3().equals("-1")&&!modulo8.getC8_p805_3().equals(""))((RadioButton)c8_p805_3_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p805_3()))).setChecked(true);
//            if(!modulo8.getC8_p805_4().equals("-1")&&!modulo8.getC8_p805_4().equals(""))((RadioButton)c8_p805_4_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p805_4()))).setChecked(true);
//            if(!modulo8.getC8_p805_5().equals("-1")&&!modulo8.getC8_p805_5().equals(""))((RadioButton)c8_p805_5_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p805_5()))).setChecked(true);
//
//            if(!modulo8.getC8_p806_1().equals("")) c8_p806_1_Spinner.setSelection(Integer.parseInt(modulo8.getC8_p806_1()));
//            if(!modulo8.getC8_p806_2().equals("")) c8_p806_2_Spinner.setSelection(Integer.parseInt(modulo8.getC8_p806_2()));
//            if(!modulo8.getC8_p806_3().equals("")) c8_p806_3_Spinner.setSelection(Integer.parseInt(modulo8.getC8_p806_3()));
//            if(!modulo8.getC8_p806_4().equals("")) c8_p806_4_Spinner.setSelection(Integer.parseInt(modulo8.getC8_p806_4()));
//            if(!modulo8.getC8_p806_5().equals("")) c8_p806_5_Spinner.setSelection(Integer.parseInt(modulo8.getC8_p806_5()));
//            if(!modulo8.getC8_p806_6().equals("")) c8_p806_6_Spinner.setSelection(Integer.parseInt(modulo8.getC8_p806_6()));
//
//            if(!modulo8.getC8_p807().equals("-1")&&!modulo8.getC8_p807().equals(""))((RadioButton)c8_p807_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p807()))).setChecked(true);
//            if(modulo8.getC8_p808_1().equals("1")) c8_p808_1_CheckBox.setChecked(true);
//            if(modulo8.getC8_p808_2().equals("1")) c8_p808_2_CheckBox.setChecked(true);
//            if(modulo8.getC8_p808_3().equals("1")) c8_p808_3_CheckBox.setChecked(true);
//            if(modulo8.getC8_p808_4().equals("1")) c8_p808_4_CheckBox.setChecked(true);
//            if(modulo8.getC8_p808_5().equals("1")) c8_p808_5_CheckBox.setChecked(true);
//            if(modulo8.getC8_p808_6().equals("1")) c8_p808_6_CheckBox.setChecked(true);
//            if(modulo8.getC8_p808_7().equals("1")) c8_p808_7_CheckBox.setChecked(true);
//            if(modulo8.getC8_p808_8().equals("1")) c8_p808_8_CheckBox.setChecked(true);
//            if(modulo8.getC8_p808_9().equals("1")) c8_p808_9_CheckBox.setChecked(true);
//            if(modulo8.getC8_p808_10().equals("1")) c8_p808_10_CheckBox.setChecked(true);
//            if(modulo8.getC8_p808_11().equals("1")) c8_p808_11_CheckBox.setChecked(true);
//            if(modulo8.getC8_p808_12().equals("1")) c8_p808_12_CheckBox.setChecked(true);
//            if(modulo8.getC8_p808_13().equals("1")) c8_p808_13_CheckBox.setChecked(true);
//            c8_p808_o_EditText.setText(modulo8.getC8_p808_o());
//        }
//        data.close();
    }

    @Override
    public void llenarVista() {
        Data data = new Data(context);
        data.open();
        POJOFragment pojoFragment = data.getFragmentsLayouts(idEncuestado);
        POJOLayout pojoLayout = data.getLayouts(idEncuestado);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p803,idEncuestado)) m8_p803_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p804,idEncuestado)) m8_p804_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p805,idEncuestado)) m8_p805_linearlayout.setVisibility(View.GONE);

        data.close();
    }

    @Override
    public boolean validarDatos() {
        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        //PREGUNTA 803
        if (m8_p803_linearlayout.getVisibility() == View.VISIBLE){
            if (c8_p803.equals("-1")){mostrarMensaje("PREGUNTA 803: DEBE MARCAR UNA OPCIÓN"); return false;}
        }else{
            c8_p803 ="";
        }

        //PREGUNTA 804
        if (m8_p804_linearlayout.getVisibility() == View.VISIBLE){
            if (c8_p804_1.equals("0") && c8_p804_2.equals("0") &&
                    c8_p804_3.equals("0") && c8_p804_4.equals("0") &&
                    c8_p804_5.equals("0") && c8_p804_6.equals("0") &&
                    c8_p804_7.equals("0") && c8_p804_8.equals("0") &&
                    c8_p804_9.equals("0") && c8_p804_10.equals("0") &&
                    c8_p804_11.equals("0") && c8_p804_12.equals("0") &&
                    c8_p804_13.equals("0") && c8_p804_14.equals("0")){
                mostrarMensaje("PREGUNTA 804: DEBE SELECCIONAR ALGUNA OPCION");return false;
            }
            if (c8_p804_14.equals("1")){
                if (c8_p804_o.trim().equals("")){ mostrarMensaje("PREGUNTA 804 - OPCION 14: DEBE ESPECIFICAR OTRO");return false;}
                if (c8_p804_o.length()<3){
                    mostrarMensaje("ERROR  “P804. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }
            }
        }else{
            c8_p804_1 = "";
            c8_p804_2 = "";
            c8_p804_3 = "";
            c8_p804_4 = "";
            c8_p804_5 = "";
            c8_p804_6= "";
            c8_p804_7 = "";
            c8_p804_8 = "";
            c8_p804_9 = "";
            c8_p804_10 = "";
            c8_p804_11 = "";
            c8_p804_12 = "";
            c8_p804_13 = "";
            c8_p804_14 = "";
            c8_p804_o = "";
        }


        //PREGUNTA 805
        if (m8_p805_linearlayout.getVisibility() == View.VISIBLE){
            if (c8_p805_1.equals("0") && c8_p805_2.equals("0") &&
                    c8_p805_3.equals("0") && c8_p805_4.equals("0") &&
                    c8_p805_5.equals("0") && c8_p805_6.equals("0") &&
                    c8_p805_7.equals("0") && c8_p805_8.equals("0") &&
                    c8_p805_9.equals("0")){
                mostrarMensaje("PREGUNTA 805: DEBE SELECCIONAR ALGUNA OPCION");return false;
            }
        }else{
            c8_p805_1 = "";
            c8_p805_2 = "";
            c8_p805_3 = "";
            c8_p805_4 = "";
            c8_p805_5 = "";
            c8_p805_6= "";
            c8_p805_7 = "";
            c8_p805_8 = "";
            c8_p805_9 = "";
        }

        //PREGUNTA 806
        if (m8_p806_linearlayout.getVisibility() == View.VISIBLE){
            if (c8_p806_1.equals("-1")){
                mostrarMensaje("PREGUNTA 806: DEBE SELECCIONAR ALGUNA OPCION");return false;
            }
        }else{
            c8_p806_1 = "";
        }

//        //PREGUNTA 807
//        if (m8_p807_linearlayout.getVisibility() == View.VISIBLE){
//            if (c8_p807.equals("0")){
//                mostrarMensaje("PREGUNTA 807: DEBE SELECCIONAR ALGUNA OPCION");return false;
//            }
//        }else{
//            c8_p807 = "";
//        }
//
//        //PREGUNTA DEL EMAIL
//        if (email.trim().equals("")){mostrarMensaje("PREGUNTA EMAIL: DEBE INDICAR EL EMAIL");return false;}

        //        if (email.trim().equals("")){mostrarMensaje("PREGUNTA EMAIL: DEBE INDICAR EL EMAIL");
//            return false;}

//        if(c8_p805_1.equals("-1")){mostrarMensaje("PREGUNTA 805-a: DEBE SELECCIONAR UNA OPCION");return false;}
//        if(c8_p805_2.equals("-1")){mostrarMensaje("PREGUNTA 805-b: DEBE SELECCIONAR UNA OPCION");return false;}
//        if(c8_p805_3.equals("-1")){mostrarMensaje("PREGUNTA 805-c: DEBE SELECCIONAR UNA OPCION");return false;}
//        if(c8_p805_4.equals("-1")){mostrarMensaje("PREGUNTA 805-d: DEBE SELECCIONAR UNA OPCION");return false;}
//        if(c8_p805_5.equals("-1")){mostrarMensaje("PREGUNTA 805-e: DEBE SELECCIONAR UNA OPCION");return false;}
//        if(c8_p806_1.equals("0")){mostrarMensaje("PREGUNTA 806-a: DEBE SELECCIONAR UNA OPCION");return false;}
//        if(c8_p806_2.equals("0")){mostrarMensaje("PREGUNTA 806-b: DEBE SELECCIONAR UNA OPCION");return false;}
//        if(c8_p806_3.equals("0")){mostrarMensaje("PREGUNTA 806-c: DEBE SELECCIONAR UNA OPCION");return false;}
//        if(c8_p806_4.equals("0")){mostrarMensaje("PREGUNTA 806-d: DEBE SELECCIONAR UNA OPCION");return false;}
//        if(c8_p806_5.equals("0")){mostrarMensaje("PREGUNTA 806-e: DEBE SELECCIONAR UNA OPCION");return false;}
//        if(c8_p806_6.equals("0")){mostrarMensaje("PREGUNTA 806-f: DEBE SELECCIONAR UNA OPCION");return false;}
//
//        if(c8_p807.equals("-1")){mostrarMensaje("PREGUNTA 807: DEBE SELECCIONAR UNA OPCION");return false;}
//
//        if (m8_p808_linearlayout.getVisibility() == View.VISIBLE){
//            if(c8_p808_1.equals("0") && c8_p808_2.equals("0") && c8_p808_3.equals("0") && c8_p808_4.equals("0") && c8_p808_5.equals("0")
//                    && c8_p808_6.equals("0") && c8_p808_7.equals("0") && c8_p808_8.equals("0") && c8_p808_9.equals("0") && c8_p808_10.equals("0")
//                    && c8_p808_11.equals("0") && c8_p808_12.equals("0") && c8_p808_13.equals("0")){
//                mostrarMensaje("PREGUNTA 808: DEBE SELECCIONAR ALGUNA OPCION");
//                return false;
//            }
//            if(c8_p808_13.equals("1")){
//                if(c8_p808_o.trim().equals("")){
//                    mostrarMensaje("PREGUNTA 808 - OPCION 13: DEBE ESPECIFICAR OTRO");
//                    return false;
//                }
//            }
//        }else{
//            c8_p808_1 = "";
//            c8_p808_2 = "";
//            c8_p808_3 = "";
//            c8_p808_4 = "";
//            c8_p808_5 = "";
//            c8_p808_6 = "";
//            c8_p808_7 = "";
//            c8_p808_8 = "";
//            c8_p808_9 = "";
//            c8_p808_10 = "";
//            c8_p808_11 = "";
//            c8_p808_12 = "";
//            c8_p808_13 = "";
//            c8_p808_o = "";
//        }

        return true;
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

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablamodulo8;
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

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void mostrarTeclado(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
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
        Data data = new Data(context);
        data.open();
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p801p804,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp801p804,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p805p808,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp805p808,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p809p812,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp809p812,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p813p816,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp813p816,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p817p820,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp817p820,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p821p823,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp821p823,idEncuestado).equals("0")) return false;
        data.close();
        return true;
    }
    public void validarAchurar(){
        llenarVariables();
        if(c8_p805_1.equals("1") || c8_p805_2.equals("1") || c8_p805_3.equals("1") || c8_p805_4.equals("1") || c8_p805_5.equals("1") || c8_p805_6.equals("1") || c8_p805_7.equals("1") || c8_p805_8.equals("1")){
            c8_p805_9_CheckBox.setChecked(false);c8_p805_9_CheckBox.setEnabled(false);
        }else {
            c8_p805_9_CheckBox.setEnabled(true);
        }
    }
}
