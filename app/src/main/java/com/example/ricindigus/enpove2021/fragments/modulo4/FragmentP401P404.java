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

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo4;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP401P404 extends FragmentPagina {
    String idEncuestado;
    String idVivienda, idHogar, idInformante;
    Context context;

    Spinner informanteSpinner;

    CheckBox c4_p401_1_CheckBox, c4_p401_2_CheckBox, c4_p401_3_CheckBox, c4_p401_4_CheckBox, c4_p401_5_CheckBox ;
//    EditText c4_p401_o_EditText;
    EditText c4_p401_4_o_EditText; //Anthony M.

    RadioGroup c4_p402_RadioGroup;

    CheckBox c4_p403_1_CheckBox, c4_p403_2_CheckBox, c4_p403_3_CheckBox, c4_p403_4_CheckBox, c4_p403_5_CheckBox,
            c4_p403_6_CheckBox, c4_p403_7_CheckBox, c4_p403_8_CheckBox, c4_p403_9_CheckBox, c4_p403_10_CheckBox,
            c4_p403_11_CheckBox, c4_p403_12_CheckBox, c4_p403_13_CheckBox, c4_p403_14_CheckBox;

//    EditText c4_p403_o_EditText;
    EditText c4_p403_14_o_EditText; //Anthony M.

    RadioGroup c4_p404_RadioGroup;
    LinearLayout m4_p401_linearlayout, m4_p402_linearlayout, m4_p403_linearlayout, m4_p404_linearlayout;

    private String c4_p401_1;
    private String c4_p401_2;
    private String c4_p401_3;
    private String c4_p401_4;
    private String c4_p401_4_o;
    private String c4_p401_5;
//    private String c4_p401_o;
    private String c4_p402;
    private String c4_p403_1;
    private String c4_p403_2;
    private String c4_p403_3;
    private String c4_p403_4;
    private String c4_p403_5;
    private String c4_p403_6;
    private String c4_p403_7;
    private String c4_p403_8;
    private String c4_p403_9;
    private String c4_p403_10;
    private String c4_p403_11;
    private String c4_p403_12;
    private String c4_p403_13;
    private String c4_p403_14;
//    private String c4_p403_o;
    private String c4_p403_14_o; //Anthony M.
    private String c4_p404;


    @SuppressLint("ValidFragment")
    public FragmentP401P404(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;

        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idHogar = residente.getId_hogar();
        idVivienda = residente.getId_vivienda();
        idInformante = "";
        data.close();

    }

    public FragmentP401P404() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p401_p404, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

        c4_p401_1_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_401_checkbox_C4_P401_1);
        c4_p401_2_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_401_checkbox_C4_P401_2);
        c4_p401_3_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_401_checkbox_C4_P401_3);
        c4_p401_4_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_401_checkbox_C4_P401_4);
//        c4_p401_o_EditText = (EditText) rootView.findViewById(R.id.mod4_401_edtittext_C4_P401_O);
        c4_p401_4_o_EditText = (EditText) rootView.findViewById(R.id.mod4_401_edtittext_C4_P401_O);
        c4_p401_5_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_401_checkbox_C4_P401_5);

        c4_p402_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_402_radiogroup_C4_P402);

        c4_p403_1_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_403_checkbox_C4_P403_1);
        c4_p403_2_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_403_checkbox_C4_P403_2);
        c4_p403_3_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_403_checkbox_C4_P403_3);
        c4_p403_4_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_403_checkbox_C4_P403_4);
        c4_p403_5_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_403_checkbox_C4_P403_5);
        c4_p403_6_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_403_checkbox_C4_P403_6);
        c4_p403_7_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_403_checkbox_C4_P403_7);
        c4_p403_8_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_403_checkbox_C4_P403_8);
        c4_p403_9_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_403_checkbox_C4_P403_9);
        c4_p403_10_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_403_checkbox_C4_P403_10);
        c4_p403_11_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_403_checkbox_C4_P403_11);
        c4_p403_12_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_403_checkbox_C4_P403_12);
        c4_p403_13_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_403_checkbox_C4_P403_13);
        c4_p403_14_CheckBox = (CheckBox) rootView.findViewById(R.id.mod4_403_checkbox_C4_P403_14);
//        c4_p403_o_EditText = (EditText) rootView.findViewById(R.id.mod4_403_edittext_C4_P403_O);
        c4_p403_14_o_EditText = (EditText) rootView.findViewById(R.id.mod4_403_edittext_C4_P403_O);

        c4_p404_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_404_radiogroup_C4_P404);

        m4_p401_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p401);
        m4_p402_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p402);
        m4_p403_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p403);
        m4_p404_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p404);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            mostrarPregunta3(false);
            mostrarPregunta4(false);
//        controlarChecked(c4_p401_4_CheckBox,c4_p401_o_EditText);
//        configurarEditText(c4_p401_o_EditText,m4_p401_linearlayout,0,30);
//        controlarChecked(c4_p403_14_CheckBox,c4_p403_o_EditText);
//        configurarEditText(c4_p403_o_EditText,m4_p403_linearlayout,0,30);
        //Anthony M.
//        controlarChecked(c4_p401_4_CheckBox,c4_p401_4_o_EditText); //FLUJO 37
        configurarEditText(c4_p401_4_o_EditText,m4_p401_linearlayout,0,50);
        controlarChecked(c4_p403_14_CheckBox,c4_p403_14_o_EditText); //FLUJO 39
        configurarEditText(c4_p403_14_o_EditText,m4_p403_linearlayout,0,50);

        c4_p401_1_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar401();
            }
        });
        c4_p401_2_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar401();
            }
        });
        c4_p401_3_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar401();
            }
        });
        c4_p401_4_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar401();
                if(isChecked) { //FLUJO 37
                    c4_p401_4_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    c4_p401_4_o_EditText.setEnabled(true);
                }else {
                    c4_p401_4_o_EditText.setText("");
                    c4_p401_4_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    c4_p401_4_o_EditText.setEnabled(false);
                }
            }
        });
        c4_p401_5_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    c4_p401_1_CheckBox.setChecked(false);
                    c4_p401_2_CheckBox.setChecked(false);
                    c4_p401_3_CheckBox.setChecked(false);
                    c4_p401_4_CheckBox.setChecked(false);
//                    c4_p401_o_EditText.setText("");
                    c4_p401_4_o_EditText.setText(""); //Anthony M.
                    c4_p401_1_CheckBox.setEnabled(false);
                    c4_p401_2_CheckBox.setEnabled(false);
                    c4_p401_3_CheckBox.setEnabled(false);
                    c4_p401_4_CheckBox.setEnabled(false);
//                    c4_p401_o_EditText.setEnabled(false);
                    c4_p401_4_o_EditText.setEnabled(false); //Anthony M.
                }else{
                    c4_p401_1_CheckBox.setEnabled(true);
                    c4_p401_2_CheckBox.setEnabled(true);
                    c4_p401_3_CheckBox.setEnabled(true);
                    c4_p401_4_CheckBox.setEnabled(true);
                }
            }
        });

        c4_p402_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                switch (seleccionado){
                    case 1: mostrarPregunta3(true);mostrarPregunta4(true);break; //FLUJO 38
                    case 2: mostrarPregunta3(false);mostrarPregunta4(false);break; //FLUJO 38

//                    case 1: m4_p403_linearlayout.setVisibility(View.VISIBLE);m4_p404_linearlayout.setVisibility(View.VISIBLE);break;
//                    case 2: m4_p403_linearlayout.setVisibility(View.GONE);m4_p404_linearlayout.setVisibility(View.GONE);
//                        c4_p403_1_CheckBox.setChecked(false);
//                        c4_p403_2_CheckBox.setChecked(false);
//                        c4_p403_3_CheckBox.setChecked(false);
//                        c4_p403_4_CheckBox.setChecked(false);
//                        c4_p403_5_CheckBox.setChecked(false);
//                        c4_p403_6_CheckBox.setChecked(false);
//                        c4_p403_7_CheckBox.setChecked(false);
//                        c4_p403_8_CheckBox.setChecked(false);
//                        c4_p403_9_CheckBox.setChecked(false);
//                        c4_p403_10_CheckBox.setChecked(false);
//                        c4_p403_11_CheckBox.setChecked(false);
//                        c4_p403_12_CheckBox.setChecked(false);
//                        c4_p403_13_CheckBox.setChecked(false);
//                        c4_p403_14_CheckBox.setChecked(false);
//                        c4_p403_o_EditText.setText("");
//                        c4_p404_RadioGroup.clearCheck();
//                        break;
                }
            }
        });

        cargarDatos();
        llenarVista();

    }

    @Override
    public void guardarDatos() {

        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo4_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo4_c4_p401_1,c4_p401_1);
        contentValues.put(SQLConstantes.modulo4_c4_p401_2,c4_p401_2);
        contentValues.put(SQLConstantes.modulo4_c4_p401_3,c4_p401_3);
        contentValues.put(SQLConstantes.modulo4_c4_p401_4,c4_p401_4);
        contentValues.put(SQLConstantes.modulo4_c4_p401_4_o,c4_p401_4_o); //Anthony M.
        contentValues.put(SQLConstantes.modulo4_c4_p401_5,c4_p401_5);
//        contentValues.put(SQLConstantes.modulo4_c4_p401_o,c4_p401_o);
        contentValues.put(SQLConstantes.modulo4_c4_p402,c4_p402);
        contentValues.put(SQLConstantes.modulo4_c4_p403_1,c4_p403_1);
        contentValues.put(SQLConstantes.modulo4_c4_p403_2,c4_p403_2);
        contentValues.put(SQLConstantes.modulo4_c4_p403_3,c4_p403_3);
        contentValues.put(SQLConstantes.modulo4_c4_p403_4,c4_p403_4);
        contentValues.put(SQLConstantes.modulo4_c4_p403_5,c4_p403_5);
        contentValues.put(SQLConstantes.modulo4_c4_p403_6,c4_p403_6);
        contentValues.put(SQLConstantes.modulo4_c4_p403_7,c4_p403_7);
        contentValues.put(SQLConstantes.modulo4_c4_p403_8,c4_p403_8);
        contentValues.put(SQLConstantes.modulo4_c4_p403_9,c4_p403_9);
        contentValues.put(SQLConstantes.modulo4_c4_p403_10,c4_p403_10);
        contentValues.put(SQLConstantes.modulo4_c4_p403_11,c4_p403_11);
        contentValues.put(SQLConstantes.modulo4_c4_p403_12,c4_p403_12);
        contentValues.put(SQLConstantes.modulo4_c4_p403_13,c4_p403_13);
        contentValues.put(SQLConstantes.modulo4_c4_p403_14,c4_p403_14);
//        contentValues.put(SQLConstantes.modulo4_c4_p403_14,c4_p403_14);
        contentValues.put(SQLConstantes.modulo4_c4_p403_14_o,c4_p403_14_o); //Anthony M.
        contentValues.put(SQLConstantes.modulo4_c4_p404,c4_p404);

        if(!data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo4 modulo4 = new Modulo4();
            modulo4.set_id(idEncuestado);
            modulo4.setIdHogar(idHogar);
            modulo4.setIdVivienda(idVivienda);
            data.insertarElemento(getNombreTabla(),modulo4.toValues());
        }
        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp401p404,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo4_COB400,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo4_COB400,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();

    }

    @Override
    public void llenarVariables() {

        idInformante = obtener_Nresidente(informanteSpinner);
        if(c4_p401_1_CheckBox.isChecked()) c4_p401_1 = "1"; else c4_p401_1 = "0";
        if(c4_p401_2_CheckBox.isChecked()) c4_p401_2 = "1"; else c4_p401_2 = "0";
        if(c4_p401_3_CheckBox.isChecked()) c4_p401_3 = "1"; else c4_p401_3 = "0";
        if(c4_p401_4_CheckBox.isChecked()) c4_p401_4 = "1"; else c4_p401_4 = "0";
        if(c4_p401_5_CheckBox.isChecked()) c4_p401_5 = "1"; else c4_p401_5 = "0";
//        c4_p401_o = c4_p401_o_EditText.getText().toString();
        c4_p401_4_o = c4_p401_4_o_EditText.getText().toString(); //Anthony M.
        c4_p402 = c4_p402_RadioGroup.indexOfChild(c4_p402_RadioGroup.findViewById(c4_p402_RadioGroup.getCheckedRadioButtonId()))+"";
        if(c4_p403_1_CheckBox.isChecked()) c4_p403_1 = "1"; else c4_p403_1 = "0";
        if(c4_p403_2_CheckBox.isChecked()) c4_p403_2 = "1"; else c4_p403_2 = "0";
        if(c4_p403_3_CheckBox.isChecked()) c4_p403_3 = "1"; else c4_p403_3 = "0";
        if(c4_p403_4_CheckBox.isChecked()) c4_p403_4 = "1"; else c4_p403_4 = "0";
        if(c4_p403_5_CheckBox.isChecked()) c4_p403_5 = "1"; else c4_p403_5 = "0";
        if(c4_p403_6_CheckBox.isChecked()) c4_p403_6 = "1"; else c4_p403_6 = "0";
        if(c4_p403_7_CheckBox.isChecked()) c4_p403_7 = "1"; else c4_p403_7 = "0";
        if(c4_p403_8_CheckBox.isChecked()) c4_p403_8 = "1"; else c4_p403_8 = "0";
        if(c4_p403_9_CheckBox.isChecked()) c4_p403_9 = "1"; else c4_p403_9 = "0";
        if(c4_p403_10_CheckBox.isChecked()) c4_p403_10 = "1"; else c4_p403_10 = "0";
        if(c4_p403_11_CheckBox.isChecked()) c4_p403_11 = "1"; else c4_p403_11 = "0";
        if(c4_p403_12_CheckBox.isChecked()) c4_p403_12 = "1"; else c4_p403_12 = "0";
        if(c4_p403_13_CheckBox.isChecked()) c4_p403_13 = "1"; else c4_p403_13 = "0";
        if(c4_p403_14_CheckBox.isChecked()) c4_p403_14 = "1"; else c4_p403_14 = "0";
//        c4_p403_o = c4_p403_o_EditText.getText().toString();
        c4_p403_14_o = c4_p403_14_o_EditText.getText().toString(); //Anthony M.
        c4_p404 = c4_p404_RadioGroup.indexOfChild(c4_p404_RadioGroup.findViewById(c4_p404_RadioGroup.getCheckedRadioButtonId()))+"";


    }

    @Override
    public void cargarDatos() {
        Data data = new Data(context);
        data.open();
        if (data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo4 modulo4 = data.getModulo4(idEncuestado);
//            ArrayList<String> residentes = data.getListaSpinnerResidentesHogar(modulo4.getIdHogar());
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,residentes);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            informanteSpinner.setAdapter(adapter);
            ArrayList<String> informantes = data.getListaInformantesMayor12(modulo4.getIdHogar());
            UtilsMethodsInputs.loadSpinner(informantes,informanteSpinner,context);
            //REGLA 49
            if(modulo4.getIdInformante() != null && !modulo4.getIdInformante().equals("")) informanteSpinner.setSelection(obtener_posicion(modulo4.getIdInformante(),informanteSpinner));
            if(modulo4.getC4_p401_1().equals("1")) c4_p401_1_CheckBox.setChecked(true);
            if(modulo4.getC4_p401_1().equals("0")) c4_p401_1_CheckBox.setChecked(false);
            if(modulo4.getC4_p401_2().equals("1")) c4_p401_2_CheckBox.setChecked(true);
            if(modulo4.getC4_p401_2().equals("0")) c4_p401_2_CheckBox.setChecked(false);
            if(modulo4.getC4_p401_3().equals("1")) c4_p401_3_CheckBox.setChecked(true);
            if(modulo4.getC4_p401_3().equals("0")) c4_p401_3_CheckBox.setChecked(false);
            if(modulo4.getC4_p401_4().equals("1")) c4_p401_4_CheckBox.setChecked(true);
            if(modulo4.getC4_p401_4().equals("0")) c4_p401_4_CheckBox.setChecked(false);
//            c4_p401_o_EditText.setText(modulo4.getC4_p401_o());
            c4_p401_4_o_EditText.setText(modulo4.getC4_p401_4_o()); //Anthony M.
            if(modulo4.getC4_p401_5().equals("1")) c4_p401_5_CheckBox.setChecked(true);
            if(modulo4.getC4_p401_5().equals("0")) c4_p401_5_CheckBox.setChecked(false);
            if(!modulo4.getC4_p402().equals("-1") && !modulo4.getC4_p402().equals(""))((RadioButton)c4_p402_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p402()))).setChecked(true);
            if(modulo4.getC4_p403_1().equals("1")) c4_p403_1_CheckBox.setChecked(true);
            if(modulo4.getC4_p403_1().equals("0")) c4_p403_1_CheckBox.setChecked(false);
            if(modulo4.getC4_p403_2().equals("1")) c4_p403_2_CheckBox.setChecked(true);
            if(modulo4.getC4_p403_2().equals("0")) c4_p403_2_CheckBox.setChecked(false);
            if(modulo4.getC4_p403_3().equals("1")) c4_p403_3_CheckBox.setChecked(true);
            if(modulo4.getC4_p403_3().equals("0")) c4_p403_3_CheckBox.setChecked(false);
            if(modulo4.getC4_p403_4().equals("1")) c4_p403_4_CheckBox.setChecked(true);
            if(modulo4.getC4_p403_4().equals("0")) c4_p403_4_CheckBox.setChecked(false);
            if(modulo4.getC4_p403_5().equals("1")) c4_p403_5_CheckBox.setChecked(true);
            if(modulo4.getC4_p403_5().equals("0")) c4_p403_5_CheckBox.setChecked(false);
            if(modulo4.getC4_p403_6().equals("1")) c4_p403_6_CheckBox.setChecked(true);
            if(modulo4.getC4_p403_6().equals("0")) c4_p403_6_CheckBox.setChecked(false);
            if(modulo4.getC4_p403_7().equals("1")) c4_p403_7_CheckBox.setChecked(true);
            if(modulo4.getC4_p403_7().equals("0")) c4_p403_7_CheckBox.setChecked(false);
            if(modulo4.getC4_p403_8().equals("1")) c4_p403_8_CheckBox.setChecked(true);
            if(modulo4.getC4_p403_8().equals("0")) c4_p403_8_CheckBox.setChecked(false);
            if(modulo4.getC4_p403_9().equals("1")) c4_p403_9_CheckBox.setChecked(true);
            if(modulo4.getC4_p403_9().equals("0")) c4_p403_9_CheckBox.setChecked(false);
            if(modulo4.getC4_p403_10().equals("1")) c4_p403_10_CheckBox.setChecked(true);
            if(modulo4.getC4_p403_10().equals("0")) c4_p403_10_CheckBox.setChecked(false);
            if(modulo4.getC4_p403_11().equals("1")) c4_p403_11_CheckBox.setChecked(true);
            if(modulo4.getC4_p403_11().equals("0")) c4_p403_11_CheckBox.setChecked(false);
            if(modulo4.getC4_p403_12().equals("1")) c4_p403_12_CheckBox.setChecked(true);
            if(modulo4.getC4_p403_12().equals("0")) c4_p403_12_CheckBox.setChecked(false);
            if(modulo4.getC4_p403_13().equals("1")) c4_p403_13_CheckBox.setChecked(true);
            if(modulo4.getC4_p403_13().equals("0")) c4_p403_13_CheckBox.setChecked(false);
            if(modulo4.getC4_p403_14().equals("1")) c4_p403_14_CheckBox.setChecked(true);
            if(modulo4.getC4_p403_14().equals("0")) c4_p403_14_CheckBox.setChecked(false);
//            c4_p403_o_EditText.setText(modulo4.getC4_p403_o());
            c4_p403_14_o_EditText.setText(modulo4.getC4_p403_14_o()); //Anthony M.
            if(!modulo4.getC4_p404().equals("-1") && !modulo4.getC4_p404().equals(""))((RadioButton)c4_p404_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p404()))).setChecked(true);
        }
        data.close();

    }

    @Override
    public void llenarVista() {
        /*
        Data data = new Data(context);
        data.open();
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p401,idEncuestado)) m4_p401_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p402,idEncuestado)) m4_p402_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p403,idEncuestado)) m4_p403_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p404,idEncuestado)) m4_p404_linearlayout.setVisibility(View.GONE);
        data.close();

         */
    }

    @Override
    public boolean validarDatos() {

        llenarVariables();

        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        if(m4_p401_linearlayout.getVisibility() == View.VISIBLE){
            if (c4_p401_1.equals("0") && c4_p401_2.equals("0") && c4_p401_3.equals("0") && c4_p401_4.equals("0") && c4_p401_5.equals("0")) {
                mostrarMensaje("ERROR  “DEBE SELECCIONAR AL MENOS UNA ALTERNATIVA EN LA PREGUNTA 401”");return false;
            }
            if (c4_p401_4.equals("1")) {
                if (c4_p401_4_o.trim().equals("")){ mostrarMensaje("PREGUNTA 401 - OPCION 4: DEBE ESPECIFICAR OTRO");return false;}

                if (c4_p401_4_o.length()<3){
                    mostrarMensaje("ERROR  “P401. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }
            }


        }else {
            c4_p401_1 = "";
            c4_p401_2 = "";
            c4_p401_3 = "";
            c4_p401_4 = "";
            c4_p401_5 = "";
        }

//        if (c4_p401_1.equals("1") && c4_p401_3.equals("1")) {
//            mostrarMensaje("PREGUNTA 401: NO DEBE SELECCIONAR SIMULTANEAMENTE (ESSALUD Y Seguro Integral de Salud – SIS)");return false;
//        }

        if(m4_p402_linearlayout.getVisibility() == View.VISIBLE){
            if(c4_p402.equals("-1")){ mostrarMensaje("PREGUNTA 402: DEBE SELECCIONAR UNA OPCION: ");return false; }
        }else {
            c4_p402 = "";
        }

        if(m4_p403_linearlayout.getVisibility()==View.VISIBLE) {
            if (c4_p403_1.equals("0") && c4_p403_2.equals("0") && c4_p403_3.equals("0") && c4_p403_4.equals("0") && c4_p403_5.equals("0") &&
                    c4_p403_6.equals("0") && c4_p403_7.equals("0") && c4_p403_8.equals("0") && c4_p403_9.equals("0") && c4_p403_10.equals("0") &&
                    c4_p403_11.equals("0") && c4_p403_12.equals("0") && c4_p403_13.equals("0") && c4_p403_14.equals("0")) {
                mostrarMensaje("ERROR  “DEBE SELECCIONAR AL MENOS UNA ALTERNATIVA EN LA PREGUNTA 403”");return false;
            }
            if (c4_p403_14.equals("1")){
                if (c4_p403_14_o.trim().equals("")) {mostrarMensaje("PREGUNTA 403 - OPCION 14: DEBE ESPECIFICAR OTRO");return false;}
                if (c4_p403_14_o.length()<3){
                    mostrarMensaje("ERROR  “P403. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }
            }



        }else{
            c4_p403_1 = "";
            c4_p403_2 = "";
            c4_p403_3 = "";
            c4_p403_4 = "";
            c4_p403_5 = "";
            c4_p403_6 = "";
            c4_p403_7 = "";
            c4_p403_8 = "";
            c4_p403_9 = "";
            c4_p403_10 = "";
            c4_p403_11 = "";
            c4_p403_12 = "";
            c4_p403_13 = "";
            c4_p403_14 = "";
            c4_p403_14_o = "";
        }

        if (m4_p404_linearlayout.getVisibility() == View.VISIBLE){
            if(c4_p404.equals("-1")){ mostrarMensaje("PREGUNTA 404: DEBE SELECCIONAR UNA OPCION");return false; }
        }else{
            c4_p404 = "";
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
    //Anthony M 06/05/2021
    public void mostrarPregunta3(boolean visible){
        if(visible){
            m4_p403_linearlayout.setVisibility(View.VISIBLE);
        }else{
            m4_p403_linearlayout.setVisibility(View.GONE);
            c4_p403_1_CheckBox.setChecked(false);
            c4_p403_2_CheckBox.setChecked(false);
            c4_p403_3_CheckBox.setChecked(false);
            c4_p403_4_CheckBox.setChecked(false);
            c4_p403_5_CheckBox.setChecked(false);
            c4_p403_6_CheckBox.setChecked(false);
            c4_p403_7_CheckBox.setChecked(false);
            c4_p403_8_CheckBox.setChecked(false);
            c4_p403_9_CheckBox.setChecked(false);
            c4_p403_10_CheckBox.setChecked(false);
            c4_p403_11_CheckBox.setChecked(false);
            c4_p403_12_CheckBox.setChecked(false);
            c4_p403_13_CheckBox.setChecked(false);
            c4_p403_14_CheckBox.setChecked(false);
            c4_p403_14_o_EditText.setText("");
        }
    }
    public void mostrarPregunta4(boolean visible){
        if(visible){
            m4_p404_linearlayout.setVisibility(View.VISIBLE);
        }else{
            m4_p404_linearlayout.setVisibility(View.GONE);
            c4_p404_RadioGroup.clearCheck();
        }
    }
    public void validarAchurar401(){
        llenarVariables();
        if(c4_p401_1.equals("1") || c4_p401_2.equals("1") || c4_p401_3.equals("1") || c4_p401_4.equals("1")){
            c4_p401_5_CheckBox.setChecked(false);c4_p401_5_CheckBox.setEnabled(false);
        }else {
            c4_p401_5_CheckBox.setEnabled(true);
        }
    }
}
