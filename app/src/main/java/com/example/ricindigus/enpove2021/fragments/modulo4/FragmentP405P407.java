package com.example.ricindigus.enpove2021.fragments.modulo4;


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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
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
public class FragmentP405P407 extends FragmentPagina {
    String idEncuestado;
    String idInformante;
    Context context;

    Spinner informanteSpinner;

 //   CheckBox c4_p405_1_CheckBox, c4_p405_2_CheckBox, c4_p405_3_CheckBox, c4_p405_4_CheckBox, c4_p405_5_CheckBox; // Anthony 30/04/2021

    CheckBox c4_p405_1_CheckBox, c4_p405_2_CheckBox, c4_p405_3_CheckBox, c4_p405_4_CheckBox, c4_p405_5_CheckBox,
            c4_p405_6_CheckBox, c4_p405_7_CheckBox;
    CheckBox c4_p406_1_CheckBox, c4_p406_2_CheckBox, c4_p406_3_CheckBox, c4_p406_4_CheckBox, c4_p406_5_CheckBox,
            c4_p406_6_CheckBox, c4_p406_7_CheckBox, c4_p406_8_CheckBox;
//    EditText c4_p406_o_EditText;
    EditText c4_p406_7_o_EditText; //Anthony M,

    CheckBox c4_p407_1_CheckBox, c4_p407_2_CheckBox, c4_p407_3_CheckBox, c4_p407_4_CheckBox, c4_p407_5_CheckBox,
            c4_p407_6_CheckBox, c4_p407_7_CheckBox, c4_p407_8_CheckBox, c4_p407_9_CheckBox, c4_p407_10_CheckBox,
            c4_p407_11_CheckBox, c4_p407_12_CheckBox, c4_p407_13_CheckBox;
//    EditText c4_p407_o_EditText;
    EditText c4_p407_13_o_EditText; //Anthony M.
    LinearLayout m4_p405_linearlayout, m4_p406_linearlayout, m4_p407_linearlayout;

    TextView tv405_periodo;

    private String c4_p405_1;
    private String c4_p405_2;
    private String c4_p405_3;
    private String c4_p405_4;
    private String c4_p405_5;
    private String c4_p405_6;
    private String c4_p405_7;
    private String c4_p406_1;
    private String c4_p406_2;
    private String c4_p406_3;
    private String c4_p406_4;
    private String c4_p406_5;
    private String c4_p406_6;
    private String c4_p406_7;
    private String c4_p406_7_o; //Anthony M.
    private String c4_p406_8;
//    private String c4_p406_o;
    private String c4_p407_1;
    private String c4_p407_2;
    private String c4_p407_3;
    private String c4_p407_4;
    private String c4_p407_5;
    private String c4_p407_6;
    private String c4_p407_7;
    private String c4_p407_8;
    private String c4_p407_9;
    private String c4_p407_10;
    private String c4_p407_11;
    private String c4_p407_12;
    private String c4_p407_13;
    private String c4_p407_13_o; //Anthony M.
//    private String c4_p407_o;
    String p204;

    int edad=0,sexo=0;

    @SuppressLint("ValidFragment")
    public FragmentP405P407(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;
        /*
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        if(residente.getC2_p204().equals("")) sexo = 0; else sexo = Integer.parseInt(residente.getC2_p204());
        data.close();

         */

        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        p204 = residente.getC2_p204();
        if(residente.getC2_p204().equals("")) sexo = 0; else sexo = Integer.parseInt(residente.getC2_p204());
        data.close();
    }

    public FragmentP405P407() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p405_p407, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

        c4_p405_1_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_405_checkbox_C4_P405_1);
        c4_p405_2_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_405_checkbox_C4_P405_2);
        c4_p405_3_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_405_checkbox_C4_P405_3);
        c4_p405_4_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_405_checkbox_C4_P405_4);
        c4_p405_5_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_405_checkbox_C4_P405_5);
        c4_p405_6_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_405_checkbox_C4_P405_6);
        c4_p405_7_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_405_checkbox_C4_P405_7);

        c4_p406_1_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_406_checkbox_C4_P406_1);
        c4_p406_2_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_406_checkbox_C4_P406_2);
        c4_p406_3_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_406_checkbox_C4_P406_3);
        c4_p406_4_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_406_checkbox_C4_P406_4);
        c4_p406_5_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_406_checkbox_C4_P406_5);
        c4_p406_6_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_406_checkbox_C4_P406_6);
        c4_p406_7_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_406_checkbox_C4_P406_7);
//        c4_p406_o_EditText = (EditText) rootView.findViewById(R.id.mod4_406_edittext_C4_P406_O);
        c4_p406_7_o_EditText = (EditText) rootView.findViewById(R.id.mod4_406_edittext_C4_P406_O); //Anthony M,
        c4_p406_8_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_406_ck8);

        c4_p407_1_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_407_checkbox_C4_P407_1);
        c4_p407_2_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_407_C4_P407_2);
        c4_p407_3_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_407_C4_P407_3);
        c4_p407_4_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_407_C4_P407_4);
        c4_p407_5_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_407_C4_P407_5);
        c4_p407_6_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_407_C4_P407_6);
        c4_p407_7_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_407_C4_P407_7);
        c4_p407_8_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_407_C4_P407_8);
        c4_p407_9_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_407_C4_P407_9);
        c4_p407_10_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_407_C4_P407_10);
        c4_p407_11_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_407_C4_P407_11);
        c4_p407_12_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_407_C4_P407_12);
        c4_p407_13_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_407_C4_P407_13);
//        c4_p407_o_EditText = (EditText) rootView.findViewById(R.id.mod4_407_edittext_C4_P407_O);
        c4_p407_13_o_EditText = (EditText) rootView.findViewById(R.id.mod4_407_edittext_C4_P407_O);

        tv405_periodo = (TextView) rootView.findViewById(R.id.tv405_periodo);

       // m4_p405_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p405x);
        m4_p405_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p405);
        m4_p406_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p406);
        m4_p407_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p407);



        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        m4_p407_linearlayout.setVisibility(View.GONE);

        String text405 = getString(R.string.modulo_4_p405x, UtilsMethods.getPeriodoReferenciaSemana(4));
        tv405_periodo.setText(text405);

//        configurarEditText(c4_p406_o_EditText,m4_p406_linearlayout,0,30);
//        configurarEditText(c4_p407_o_EditText,m4_p407_linearlayout,0,30);
//        controlarChecked(c4_p406_7_CheckBox,c4_p406_o_EditText);
//        controlarChecked(c4_p407_13_CheckBox,c4_p407_o_EditText);
        //Anthony M.
        configurarEditText(c4_p406_7_o_EditText,m4_p406_linearlayout,0,50);
        configurarEditText(c4_p407_13_o_EditText,m4_p407_linearlayout,0,50);
//        controlarChecked(c4_p406_7_CheckBox,c4_p406_7_o_EditText);
        controlarChecked(c4_p407_13_CheckBox,c4_p407_13_o_EditText); //FLUJO 42

        c4_p405_1_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar405();
            }
        });
        c4_p405_2_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar405();
            }
        });
        c4_p405_3_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar405();
            }
        });
        c4_p405_4_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar405();
            }
        });
        c4_p405_5_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar405();
            }
        });
        c4_p405_6_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar405();
            }
        });

//        c4_p405_5_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          c4_p405_7_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){ //REGLA 51.2
//                    m4_p406_linearlayout.setVisibility(View.GONE); limpiar_p406();
//                    m4_p407_linearlayout.setVisibility(View.GONE); limpiar_p407();
                    c4_p405_1_CheckBox.setChecked(false);
                    c4_p405_1_CheckBox.setEnabled(false);
                    c4_p405_2_CheckBox.setChecked(false);
                    c4_p405_2_CheckBox.setEnabled(false);
                    c4_p405_3_CheckBox.setChecked(false);
                    c4_p405_3_CheckBox.setEnabled(false);
                    c4_p405_4_CheckBox.setChecked(false);
                    c4_p405_4_CheckBox.setEnabled(false);
                    c4_p405_5_CheckBox.setChecked(false);
                    c4_p405_5_CheckBox.setEnabled(false);
                    c4_p405_6_CheckBox.setChecked(false);
                    c4_p405_6_CheckBox.setEnabled(false);
                    mostrarPregunta6(false); //FLUJO 40
                    mostrarPregunta7(false);

                }else{
//                    m4_p406_linearlayout.setVisibility(View.VISIBLE);
//                    m4_p407_linearlayout.setVisibility(View.VISIBLE);
                    c4_p405_1_CheckBox.setEnabled(true);
                    c4_p405_2_CheckBox.setEnabled(true);
                    c4_p405_3_CheckBox.setEnabled(true);
                    c4_p405_4_CheckBox.setEnabled(true);
                    c4_p405_5_CheckBox.setEnabled(true);
                    c4_p405_6_CheckBox.setEnabled(true);
                    mostrarPregunta6(true); //FLUJO 40

//                    c4_p405_5_CheckBox.setEnabled(true);
//                    c4_p405_6_CheckBox.setEnabled(true);
                }
            }
        });
        // Anthony M
        //inicio FLUJO 41
        c4_p406_1_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarFlujo41();validarAchurar406();
            }
        });
        c4_p406_2_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarFlujo41();validarAchurar406();
            }
        });
        c4_p406_3_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarFlujo41();validarAchurar406();
            }
        });
        c4_p406_4_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarFlujo41();validarAchurar406();
            }
        });
        c4_p406_5_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarFlujo41();validarAchurar406();
            }
        });
        c4_p406_6_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarFlujo41();validarAchurar406();
            }
        });

        c4_p406_7_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarFlujo41();validarAchurar406();
                if(isChecked){ //FLUJO 41
                    c4_p406_7_o_EditText.setEnabled(true);
                    c4_p406_7_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else {
                    c4_p406_7_o_EditText.setText("");
                    c4_p406_7_o_EditText.setEnabled(false);
                    c4_p406_7_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }

        });
        //fin FLUJO 41

        c4_p406_8_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               validarFlujo41();
               if(isChecked){
                   c4_p406_1_CheckBox.setChecked(false);
                   c4_p406_1_CheckBox.setEnabled(false);
                   c4_p406_2_CheckBox.setChecked(false);
                   c4_p406_2_CheckBox.setEnabled(false);
                   c4_p406_3_CheckBox.setChecked(false);
                   c4_p406_3_CheckBox.setEnabled(false);
                   c4_p406_4_CheckBox.setChecked(false);
                   c4_p406_4_CheckBox.setEnabled(false);
                   c4_p406_5_CheckBox.setChecked(false);
                   c4_p406_5_CheckBox.setEnabled(false);
                   c4_p406_6_CheckBox.setChecked(false);
                   c4_p406_6_CheckBox.setEnabled(false);
                   c4_p406_7_CheckBox.setChecked(false);
                   c4_p406_7_CheckBox.setEnabled(false);
               }else{
                   c4_p406_1_CheckBox.setEnabled(true);
                   c4_p406_2_CheckBox.setEnabled(true);
                   c4_p406_3_CheckBox.setEnabled(true);
                   c4_p406_4_CheckBox.setEnabled(true);
                   c4_p406_5_CheckBox.setEnabled(true);
                   c4_p406_6_CheckBox.setEnabled(true);
                   c4_p406_7_CheckBox.setEnabled(true);
               }
            }
        });
        llenarVista();
        cargarDatos();


    }

    /*
    public void ocultarP407(CheckBox checkBox){
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    m4_p407_linearlayout.setVisibility(View.GONE); limpiar_p407();
                }else{
                    if (!c4_p406_1_CheckBox.isChecked() && !c4_p406_2_CheckBox.isChecked() &&
                            !c4_p406_3_CheckBox.isChecked() && !c4_p406_4_CheckBox.isChecked()){
                        m4_p407_linearlayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

     */


    @Override
    public void guardarDatos() {

        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo4_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo4_c4_p405_1,c4_p405_1);
        contentValues.put(SQLConstantes.modulo4_c4_p405_2,c4_p405_2);
        contentValues.put(SQLConstantes.modulo4_c4_p405_3,c4_p405_3);
        contentValues.put(SQLConstantes.modulo4_c4_p405_4,c4_p405_4);
        contentValues.put(SQLConstantes.modulo4_c4_p405_5,c4_p405_5);
        contentValues.put(SQLConstantes.modulo4_c4_p405_6,c4_p405_6);
        contentValues.put(SQLConstantes.modulo4_c4_p405_7,c4_p405_7);
        contentValues.put(SQLConstantes.modulo4_c4_p406_1,c4_p406_1);
        contentValues.put(SQLConstantes.modulo4_c4_p406_2,c4_p406_2);
        contentValues.put(SQLConstantes.modulo4_c4_p406_3,c4_p406_3);
        contentValues.put(SQLConstantes.modulo4_c4_p406_4,c4_p406_4);
        contentValues.put(SQLConstantes.modulo4_c4_p406_5,c4_p406_5);
        contentValues.put(SQLConstantes.modulo4_c4_p406_6,c4_p406_6);
        contentValues.put(SQLConstantes.modulo4_c4_p406_7,c4_p406_7);
        contentValues.put(SQLConstantes.modulo4_c4_p406_7_o,c4_p406_7_o); //Anthony M,
        contentValues.put(SQLConstantes.modulo4_c4_p406_8,c4_p406_8);
//        contentValues.put(SQLConstantes.modulo4_c4_p406_o,c4_p406_o);
        contentValues.put(SQLConstantes.modulo4_c4_p407_1,c4_p407_1);
        contentValues.put(SQLConstantes.modulo4_c4_p407_2,c4_p407_2);
        contentValues.put(SQLConstantes.modulo4_c4_p407_3,c4_p407_3);
        contentValues.put(SQLConstantes.modulo4_c4_p407_4,c4_p407_4);
        contentValues.put(SQLConstantes.modulo4_c4_p407_5,c4_p407_5);
        contentValues.put(SQLConstantes.modulo4_c4_p407_6,c4_p407_6);
        contentValues.put(SQLConstantes.modulo4_c4_p407_7,c4_p407_7);
        contentValues.put(SQLConstantes.modulo4_c4_p407_8,c4_p407_8);
        contentValues.put(SQLConstantes.modulo4_c4_p407_9,c4_p407_9);
        contentValues.put(SQLConstantes.modulo4_c4_p407_10,c4_p407_10);
        contentValues.put(SQLConstantes.modulo4_c4_p407_11,c4_p407_11);
        contentValues.put(SQLConstantes.modulo4_c4_p407_12,c4_p407_12);
        contentValues.put(SQLConstantes.modulo4_c4_p407_13,c4_p407_13);
//        contentValues.put(SQLConstantes.modulo4_c4_p407_o,c4_p407_o);
        contentValues.put(SQLConstantes.modulo4_c4_p407_13_o,c4_p407_13_o);
        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp405p407,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo4_COB400,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo4_COB400,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();

    }

    @Override
    public void llenarVariables() {

        idInformante = obtener_Nresidente(informanteSpinner);
        if(c4_p405_1_CheckBox.isChecked()) c4_p405_1 = "1"; else c4_p405_1 = "0";
        if(c4_p405_2_CheckBox.isChecked()) c4_p405_2 = "1"; else c4_p405_2 = "0";
        if(c4_p405_3_CheckBox.isChecked()) c4_p405_3 = "1"; else c4_p405_3 = "0";
        if(c4_p405_4_CheckBox.isChecked()) c4_p405_4 = "1"; else c4_p405_4 = "0";
        if(c4_p405_5_CheckBox.isChecked()) c4_p405_5 = "1"; else c4_p405_5 = "0";
        if(c4_p405_6_CheckBox.isChecked()) c4_p405_6 = "1"; else c4_p405_6 = "0";
        if(c4_p405_7_CheckBox.isChecked()) c4_p405_7 = "1"; else c4_p405_7 = "0";

        if(c4_p406_1_CheckBox.isChecked()) c4_p406_1 = "1"; else c4_p406_1 = "0";
        if(c4_p406_2_CheckBox.isChecked()) c4_p406_2 = "1"; else c4_p406_2 = "0";
        if(c4_p406_3_CheckBox.isChecked()) c4_p406_3 = "1"; else c4_p406_3 = "0";
        if(c4_p406_4_CheckBox.isChecked()) c4_p406_4 = "1"; else c4_p406_4 = "0";
        if(c4_p406_5_CheckBox.isChecked()) c4_p406_5 = "1"; else c4_p406_5 = "0";
        if(c4_p406_6_CheckBox.isChecked()) c4_p406_6 = "1"; else c4_p406_6 = "0";
        if(c4_p406_7_CheckBox.isChecked()) c4_p406_7 = "1"; else c4_p406_7 = "0";
        if(c4_p406_8_CheckBox.isChecked()) c4_p406_8 = "1"; else c4_p406_8 = "0";
//        c4_p406_o = c4_p406_o_EditText.getText().toString();
        c4_p406_7_o = c4_p406_7_o_EditText.getText().toString(); //Anthony M,
        if(c4_p407_1_CheckBox.isChecked()) c4_p407_1 = "1"; else c4_p407_1 = "0";
        if(c4_p407_2_CheckBox.isChecked()) c4_p407_2 = "1"; else c4_p407_2 = "0";
        if(c4_p407_3_CheckBox.isChecked()) c4_p407_3 = "1"; else c4_p407_3 = "0";
        if(c4_p407_4_CheckBox.isChecked()) c4_p407_4 = "1"; else c4_p407_4 = "0";
        if(c4_p407_5_CheckBox.isChecked()) c4_p407_5 = "1"; else c4_p407_5 = "0";
        if(c4_p407_6_CheckBox.isChecked()) c4_p407_6 = "1"; else c4_p407_6 = "0";
        if(c4_p407_7_CheckBox.isChecked()) c4_p407_7 = "1"; else c4_p407_7 = "0";
        if(c4_p407_8_CheckBox.isChecked()) c4_p407_8 = "1"; else c4_p407_8 = "0";
        if(c4_p407_9_CheckBox.isChecked()) c4_p407_9 = "1"; else c4_p407_9 = "0";
        if(c4_p407_10_CheckBox.isChecked()) c4_p407_10 = "1"; else c4_p407_10 = "0";
        if(c4_p407_11_CheckBox.isChecked()) c4_p407_11 = "1"; else c4_p407_11 = "0";
        if(c4_p407_12_CheckBox.isChecked()) c4_p407_12 = "1"; else c4_p407_12 = "0";
        if(c4_p407_13_CheckBox.isChecked()) c4_p407_13 = "1"; else c4_p407_13 = "0";
//        c4_p407_o = c4_p407_o_EditText.getText().toString();
        c4_p407_13_o = c4_p407_13_o_EditText.getText().toString(); //Anthony M.

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
            if(modulo4.getC4_p405_1().equals("1")) c4_p405_1_CheckBox.setChecked(true);
            if(modulo4.getC4_p405_2().equals("1")) c4_p405_2_CheckBox.setChecked(true);
            if(modulo4.getC4_p405_3().equals("1")) c4_p405_3_CheckBox.setChecked(true);
            if(modulo4.getC4_p405_4().equals("1")) c4_p405_4_CheckBox.setChecked(true);
            if(modulo4.getC4_p405_5().equals("1")) c4_p405_5_CheckBox.setChecked(true);
            if(modulo4.getC4_p405_6().equals("1")) c4_p405_6_CheckBox.setChecked(true);
            if(modulo4.getC4_p405_7().equals("1")) c4_p405_7_CheckBox.setChecked(true);
            if(modulo4.getC4_p405_7().equals("0")) mostrarPregunta6(true);

            if(modulo4.getC4_p406_1().equals("1")) c4_p406_1_CheckBox.setChecked(true);
            if(modulo4.getC4_p406_2().equals("1")) c4_p406_2_CheckBox.setChecked(true);
            if(modulo4.getC4_p406_3().equals("1")) c4_p406_3_CheckBox.setChecked(true);
            if(modulo4.getC4_p406_4().equals("1")) c4_p406_4_CheckBox.setChecked(true);
            if(modulo4.getC4_p406_5().equals("1")) c4_p406_5_CheckBox.setChecked(true);
            if(modulo4.getC4_p406_6().equals("1")) c4_p406_6_CheckBox.setChecked(true);
            if(modulo4.getC4_p406_7().equals("1")) c4_p406_7_CheckBox.setChecked(true);
            if(modulo4.getC4_p406_8().equals("1")) c4_p406_8_CheckBox.setChecked(true);
//            c4_p406_o_EditText.setText(modulo4.getC4_p406_o());
            c4_p406_7_o_EditText.setText(modulo4.getC4_p406_7_o()); //Anthony M.
            if(modulo4.getC4_p407_1().equals("1")) c4_p407_1_CheckBox.setChecked(true);
            if(modulo4.getC4_p407_2().equals("1")) c4_p407_2_CheckBox.setChecked(true);
            if(modulo4.getC4_p407_3().equals("1")) c4_p407_3_CheckBox.setChecked(true);
            if(modulo4.getC4_p407_4().equals("1")) c4_p407_4_CheckBox.setChecked(true);
            if(modulo4.getC4_p407_5().equals("1")) c4_p407_5_CheckBox.setChecked(true);
            if(modulo4.getC4_p407_6().equals("1")) c4_p407_6_CheckBox.setChecked(true);
            if(modulo4.getC4_p407_7().equals("1")) c4_p407_7_CheckBox.setChecked(true);
            if(modulo4.getC4_p407_8().equals("1")) c4_p407_8_CheckBox.setChecked(true);
            if(modulo4.getC4_p407_9().equals("1")) c4_p407_9_CheckBox.setChecked(true);
            if(modulo4.getC4_p407_10().equals("1")) c4_p407_10_CheckBox.setChecked(true);
            if(modulo4.getC4_p407_11().equals("1")) c4_p407_11_CheckBox.setChecked(true);
            if(modulo4.getC4_p407_12().equals("1")) c4_p407_12_CheckBox.setChecked(true);
            if(modulo4.getC4_p407_13().equals("1")) c4_p407_13_CheckBox.setChecked(true);

//            c4_p407_o_EditText.setText(modulo4.getC4_p407_o());
            c4_p407_13_o_EditText.setText(modulo4.getC4_p407_13_o()); //Anthony M.
        }
        data.close();

    }

    @Override
    public void llenarVista() {
        /*
        Data data = new Data(context);
        data.open();
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p405,idEncuestado)) m4_p405_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p406,idEncuestado)) m4_p406_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p407,idEncuestado)) m4_p407_linearlayout.setVisibility(View.GONE);
        data.close();

         */
    }

    @Override
    public boolean validarDatos() {

        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

//        if(c4_p405_1.equals("0") && c4_p405_2.equals("0") && c4_p405_3.equals("0") && c4_p405_4.equals("0") && c4_p405_5.equals("0") &&
//                c4_p405_6.equals("0") && c4_p405_7.equals("0")){
        if(m4_p405_linearlayout.getVisibility() == View.VISIBLE){

            if(c4_p405_6.equals("1") && sexo == 1){mostrarMensaje("VERIFICAR  “P405 - ITEM 6 - ES UN HOMBRE (P204) Y HA INDICADO EMBARAZO”");return true;}
            if(c4_p405_1.equals("0") && c4_p405_2.equals("0") && c4_p405_3.equals("0") && c4_p405_4.equals("0") && c4_p405_5.equals("0")&& c4_p405_6.equals("0")&& c4_p405_7.equals("0")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR AL MENOS UNA ALTERNATIVA EN LA PREGUNTA 405”");return false;
            }


        }else {
            c4_p405_1 = "";
            c4_p405_2 = "";
            c4_p405_3 = "";
            c4_p405_4 = "";
            c4_p405_5 = "";
            c4_p405_6 = "";
            c4_p405_7 = "";
        }

//        if(c4_p405_6.equals("1") && sexo==1){
//            mostrarMensaje("PREGUNTA 405: DEBE SELECCIONAR OTRA OPCION - SEXO NO CORRESPONDE");return false;
//        }
//        if(c4_p405_6.equals("1") && sexo==2 && edad<12){
//            mostrarMensaje("PREGUNTA 405: DEBE SELECCIONAR OTRA OPCION - EDAD NO CORRESPONDE (MÍNIMO 12 AÑOS)");return false;
//        }
        if(m4_p406_linearlayout.getVisibility() == View.VISIBLE){
            if(c4_p406_1.equals("0") && c4_p406_2.equals("0") && c4_p406_3.equals("0") && c4_p406_4.equals("0") && c4_p406_5.equals("0") &&
                    c4_p406_6.equals("0") && c4_p406_7.equals("0") && c4_p406_8.equals("0")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR AL MENOS UNA ALTERNATIVA EN LA PREGUNTA 406”");return false;
            }
            if(c4_p406_7.equals("1")){
                if(c4_p406_7_o.trim().equals("")){ mostrarMensaje("PREGUNTA 406 - OPCION 7: DEBE ESPECIFICAR OTRO");return false; }

                if (c4_p406_7_o.length()<3){
                    mostrarMensaje("ERROR  “P406. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }

            }



        }else{
            c4_p406_1 = "";
            c4_p406_2 = "";
            c4_p406_3 = "";
            c4_p406_4 = "";
            c4_p406_5 = "";
            c4_p406_6 = "";
            c4_p406_7 = "";
            c4_p406_8 = "";
            c4_p406_7_o = "";
        }

        if(m4_p407_linearlayout.getVisibility()==View.VISIBLE){
            if(c4_p407_1.equals("0") && c4_p407_2.equals("0") && c4_p407_3.equals("0") && c4_p407_4.equals("0") && c4_p407_5.equals("0") &&
                    c4_p407_6.equals("0") && c4_p407_7.equals("0") && c4_p407_8.equals("0") && c4_p407_9.equals("0") && c4_p407_10.equals("0") &&
                    c4_p407_11.equals("0") && c4_p407_12.equals("0") && c4_p407_13.equals("0")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR AL MENOS UNA ALTERNATIVA EN LA PREGUNTA 407”");return false;
            }
            if(c4_p407_13.equals("1")){
                if(c4_p407_13_o.trim().equals("")){ mostrarMensaje("PREGUNTA 407 - OPCION 13: DEBE ESPECIFICAR OTRO");return false; }

                if (c4_p407_13_o.length()<3){
                    mostrarMensaje("ERROR  “P407. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }
            }



        }else{
            c4_p407_1 = "";
            c4_p407_2 = "";
            c4_p407_3 = "";
            c4_p407_4 = "";
            c4_p407_5 = "";
            c4_p407_6 = "";
            c4_p407_7 = "";
            c4_p407_8 = "";
            c4_p407_9 = "";
            c4_p407_10 = "";
            c4_p407_11 = "";
            c4_p407_12 = "";
            c4_p407_13 = "";
            c4_p407_13_o = "";
        }

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

    public void limpiar_p406(){

        c4_p406_1_CheckBox.setChecked(false);
        c4_p406_2_CheckBox.setChecked(false);
        c4_p406_3_CheckBox.setChecked(false);
        c4_p406_4_CheckBox.setChecked(false);
        c4_p406_5_CheckBox.setChecked(false);
        c4_p406_6_CheckBox.setChecked(false);
        c4_p406_7_CheckBox.setChecked(false);
        c4_p406_7_o_EditText.setText("");
        c4_p406_8_CheckBox.setChecked(false);
//        c4_p406_o_EditText.setText("");

    }

    public void limpiar_p407(){

        c4_p407_1_CheckBox.setChecked(false);
        c4_p407_2_CheckBox.setChecked(false);
        c4_p407_3_CheckBox.setChecked(false);
        c4_p407_4_CheckBox.setChecked(false);
        c4_p407_5_CheckBox.setChecked(false);
        c4_p407_6_CheckBox.setChecked(false);
        c4_p407_7_CheckBox.setChecked(false);
        c4_p407_8_CheckBox.setChecked(false);
        c4_p407_9_CheckBox.setChecked(false);
        c4_p407_10_CheckBox.setChecked(false);
        c4_p407_11_CheckBox.setChecked(false);
        c4_p407_12_CheckBox.setChecked(false);
        c4_p407_13_CheckBox.setChecked(false);
        c4_p407_13_o_EditText.setText("");
//        c4_p407_o_EditText.setText("");


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
    public void mostrarPregunta6(boolean visible){
        if(visible){
            m4_p406_linearlayout.setVisibility(View.VISIBLE);
        }else {
            m4_p406_linearlayout.setVisibility(View.GONE);
            limpiar_p406();
        }
    }
    public void mostrarPregunta7(boolean visible){
        if(visible){
            m4_p407_linearlayout.setVisibility(View.VISIBLE);
        }else {
            m4_p407_linearlayout.setVisibility(View.GONE);
            limpiar_p407();
        }
    }
    public void validarFlujo41(){
        if((c4_p406_1_CheckBox.isChecked() || c4_p406_2_CheckBox.isChecked() || c4_p406_3_CheckBox.isChecked() || c4_p406_4_CheckBox.isChecked())) { //FLUJO 41 modificado 18/05/2021
            mostrarPregunta7(false);
        }else {
            mostrarPregunta7(true);
        }
//        if((c4_p406_1_CheckBox.isChecked() || c4_p406_2_CheckBox.isChecked() || c4_p406_3_CheckBox.isChecked() || c4_p406_4_CheckBox.isChecked()) && //FLUJO 41
//                (!c4_p406_5_CheckBox.isChecked() && !c4_p406_6_CheckBox.isChecked() && !c4_p406_7_CheckBox.isChecked() && !c4_p406_8_CheckBox.isChecked())) {
//            mostrarPregunta7(false);
//        }
//        else if (c4_p406_5_CheckBox.isChecked() || c4_p406_6_CheckBox.isChecked() || c4_p406_7_CheckBox.isChecked() || c4_p406_8_CheckBox.isChecked()) { //FLUJO 42
//            mostrarPregunta7(true);
//        }
//        else mostrarPregunta7(false);
    }
    public void validarAchurar405(){
        llenarVariables();
        if(c4_p405_1.equals("1") || c4_p405_2.equals("1") || c4_p405_3.equals("1") || c4_p405_4.equals("1") || c4_p405_5.equals("1") || c4_p405_6.equals("1")){
            c4_p405_7_CheckBox.setChecked(false);c4_p405_7_CheckBox.setEnabled(false);
        }else {
            c4_p405_7_CheckBox.setEnabled(true);
        }
    }
    public void validarAchurar406(){
        llenarVariables();
        if(c4_p406_1.equals("1") || c4_p406_2.equals("1") || c4_p406_3.equals("1") || c4_p406_4.equals("1") ||
            c4_p406_5.equals("1") || c4_p406_6.equals("1") || c4_p406_7.equals("1")){
            c4_p406_8_CheckBox.setChecked(false);c4_p406_8_CheckBox.setEnabled(false);
        }else {
            c4_p406_8_CheckBox.setEnabled(true);
        }
    }
}
