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
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP817P820 extends FragmentPagina {

    String idEncuestado;
    Context context;
    String idVivienda, idHogar, idInformante;

    Spinner informanteSpinner;
    RadioGroup c8_p817_RadioGroup, c8_p818_RadioGroup;
    CheckBox c8_p819_1_Checkbox, c8_p819_2_Checkbox, c8_p819_3_Checkbox , c8_p819_4_Checkbox, c8_p819_5_Checkbox,
            c8_p819_6_Checkbox, c8_p819_7_Checkbox, c8_p819_8_Checkbox, c8_p819_9_Checkbox, c8_p819_10_Checkbox,
            c8_p819_11_Checkbox, c8_p819_12_Checkbox, c8_p819_13_Checkbox, c8_p819_14_Checkbox;
    EditText c8_p819_o_EditText;
    CheckBox c8_p820_1_Checkbox, c8_p820_2_Checkbox, c8_p820_3_Checkbox , c8_p820_4_Checkbox, c8_p820_5_Checkbox,
            c8_p820_6_Checkbox, c8_p820_7_Checkbox, c8_p820_8_Checkbox, c8_p820_9_Checkbox, c8_p820_10_Checkbox,
            c8_p820_11_Checkbox;
    EditText c8_p820_o_EditText;
    LinearLayout m8_p817_linearlayout, m8_p818_linearlayout, m8_p819_linearlayout, m8_p820_linearlayout;

    private String c8_p817;
    private String c8_p818;
    private String c8_p819_1;
    private String c8_p819_2;
    private String c8_p819_3;
    private String c8_p819_4;
    private String c8_p819_5;
    private String c8_p819_6;
    private String c8_p819_7;
    private String c8_p819_8;
    private String c8_p819_9;
    private String c8_p819_10;
    private String c8_p819_11;
    private String c8_p819_12;
    private String c8_p819_13;
    private String c8_p819_14;
    private String c8_p819_o;
    private String c8_p820_1;
    private String c8_p820_2;
    private String c8_p820_3;
    private String c8_p820_4;
    private String c8_p820_5;
    private String c8_p820_6;
    private String c8_p820_7;
    private String c8_p820_8;
    private String c8_p820_9;
    private String c8_p820_10;
    private String c8_p820_11;
    private String c8_p820_o;

    @SuppressLint("ValidFragment")
    public FragmentP817P820(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idVivienda = residente.getId_vivienda();
        idHogar = residente.getId_hogar();
        idInformante = "";
        data.close();
    }

    public FragmentP817P820() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p817_p820, container, false);
//        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);
//        c8_p817_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_817_radiogroup_C8_P817);
//
//        c8_p818_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_818_radiogroup_C8_P818);
//
//        c8_p819_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_819_checkbox_C8_P819_1);
//        c8_p819_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_819_checkbox_C8_P819_2);
//        c8_p819_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_819_checkbox_C8_P819_3);
//        c8_p819_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_819_checkbox_C8_P819_4);
//        c8_p819_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_819_checkbox_C8_P819_5);
//        c8_p819_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_819_checkbox_C8_P819_6);
//        c8_p819_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_819_checkbox_C8_P819_7);
//        c8_p819_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_819_checkbox_C8_P819_8);
//        c8_p819_9_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_819_checkbox_C8_P819_9);
//        c8_p819_10_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_819_checkbox_C8_P819_10);
//        c8_p819_11_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_819_checkbox_C8_P819_11);
//        c8_p819_12_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_819_checkbox_C8_P819_12);
//        c8_p819_13_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_819_checkbox_C8_P819_13);
//        c8_p819_14_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_819_checkbox_C8_P819_14);
//        c8_p819_o_EditText = (EditText) rootView.findViewById(R.id.mod8_819_edittext_C8_P819_O);
//
//        c8_p820_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_820_checkbox_C8_P820_1);
//        c8_p820_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_820_checkbox_C8_P820_2);
//        c8_p820_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_820_checkbox_C8_P820_3);
//        c8_p820_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_820_checkbox_C8_P820_4);
//        c8_p820_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_820_checkbox_C8_P820_5);
//        c8_p820_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_820_checkbox_C8_P820_6);
//        c8_p820_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_820_checkbox_C8_P820_7);
//        c8_p820_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_820_checkbox_C8_P820_8);
//        c8_p820_9_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_820_checkbox_C8_P820_9);
//        c8_p820_10_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_820_checkbox_C8_P820_10);
//        c8_p820_11_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_820_checkbox_C8_P820_11);
//        c8_p820_o_EditText = (EditText) rootView.findViewById(R.id.mod8_820_edittext_C8_P820_O);
//
//        m8_p817_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p817);
//        m8_p818_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p818);
//        m8_p819_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p819);
//        m8_p820_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p820);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        configurarEditText(c8_p819_o_EditText,m8_p819_linearlayout,0,30);
//        configurarEditText(c8_p820_o_EditText,m8_p820_linearlayout,0,30);
//        controlarChecked(c8_p819_14_Checkbox,c8_p819_o_EditText);
//        controlarChecked(c8_p820_11_Checkbox,c8_p820_o_EditText);
//
//        c8_p818_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
//                switch (seleccionado){
//                    case 1:
//                        m8_p819_linearlayout.setVisibility(View.VISIBLE);
//                        m8_p820_linearlayout.setVisibility(View.VISIBLE);
//                        break;
//                    case 2:
//                        c8_p819_1_Checkbox.setChecked(false);
//                        c8_p819_2_Checkbox.setChecked(false);
//                        c8_p819_3_Checkbox.setChecked(false);
//                        c8_p819_4_Checkbox.setChecked(false);
//                        c8_p819_5_Checkbox.setChecked(false);
//                        c8_p819_6_Checkbox.setChecked(false);
//                        c8_p819_7_Checkbox.setChecked(false);
//                        c8_p819_8_Checkbox.setChecked(false);
//                        c8_p819_9_Checkbox.setChecked(false);
//                        c8_p819_10_Checkbox.setChecked(false);
//                        c8_p819_11_Checkbox.setChecked(false);
//                        c8_p819_12_Checkbox.setChecked(false);
//                        c8_p819_13_Checkbox.setChecked(false);
//                        c8_p819_14_Checkbox.setChecked(false);
//                        c8_p819_o_EditText.setText("");
//                        c8_p820_1_Checkbox.setChecked(false);
//                        c8_p820_2_Checkbox.setChecked(false);
//                        c8_p820_3_Checkbox.setChecked(false);
//                        c8_p820_4_Checkbox.setChecked(false);
//                        c8_p820_5_Checkbox.setChecked(false);
//                        c8_p820_6_Checkbox.setChecked(false);
//                        c8_p820_7_Checkbox.setChecked(false);
//                        c8_p820_8_Checkbox.setChecked(false);
//                        c8_p820_9_Checkbox.setChecked(false);
//                        c8_p820_10_Checkbox.setChecked(false);
//                        c8_p820_11_Checkbox.setChecked(false);
//                        c8_p820_o_EditText.setText("");
//                        m8_p819_linearlayout.setVisibility(View.GONE);
//                        m8_p820_linearlayout.setVisibility(View.GONE);
//                        break;
//                }
//            }
//        });
//
//        llenarVista();
//        cargarDatos();
    }

    @Override
    public void guardarDatos() {

//        Data data = new Data(context);
//        data.open();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(SQLConstantes.modulo8_id_informante,idInformante);
//        contentValues.put(SQLConstantes.modulo8_c8_p817,c8_p817);
//        contentValues.put(SQLConstantes.modulo8_c8_p818,c8_p818);
//
//        contentValues.put(SQLConstantes.modulo8_c8_p819_1,c8_p819_1);
//        contentValues.put(SQLConstantes.modulo8_c8_p819_2,c8_p819_2);
//        contentValues.put(SQLConstantes.modulo8_c8_p819_3,c8_p819_3);
//        contentValues.put(SQLConstantes.modulo8_c8_p819_4,c8_p819_4);
//        contentValues.put(SQLConstantes.modulo8_c8_p819_5,c8_p819_5);
//        contentValues.put(SQLConstantes.modulo8_c8_p819_6,c8_p819_6);
//        contentValues.put(SQLConstantes.modulo8_c8_p819_7,c8_p819_7);
//        contentValues.put(SQLConstantes.modulo8_c8_p819_8,c8_p819_8);
//        contentValues.put(SQLConstantes.modulo8_c8_p819_9,c8_p819_9);
//        contentValues.put(SQLConstantes.modulo8_c8_p819_10,c8_p819_10);
//        contentValues.put(SQLConstantes.modulo8_c8_p819_11,c8_p819_11);
//        contentValues.put(SQLConstantes.modulo8_c8_p819_12,c8_p819_12);
//        contentValues.put(SQLConstantes.modulo8_c8_p819_13,c8_p819_13);
//        contentValues.put(SQLConstantes.modulo8_c8_p819_14,c8_p819_14);
//        contentValues.put(SQLConstantes.modulo8_c8_p819_o,c8_p819_o);
//
//        contentValues.put(SQLConstantes.modulo8_c8_p820_1,c8_p820_1);
//        contentValues.put(SQLConstantes.modulo8_c8_p820_2,c8_p820_2);
//        contentValues.put(SQLConstantes.modulo8_c8_p820_3,c8_p820_3);
//        contentValues.put(SQLConstantes.modulo8_c8_p820_4,c8_p820_4);
//        contentValues.put(SQLConstantes.modulo8_c8_p820_5,c8_p820_5);
//        contentValues.put(SQLConstantes.modulo8_c8_p820_6,c8_p820_6);
//        contentValues.put(SQLConstantes.modulo8_c8_p820_7,c8_p820_7);
//        contentValues.put(SQLConstantes.modulo8_c8_p820_8,c8_p820_8);
//        contentValues.put(SQLConstantes.modulo8_c8_p820_9,c8_p820_9);
//        contentValues.put(SQLConstantes.modulo8_c8_p820_10,c8_p820_10);
//        contentValues.put(SQLConstantes.modulo8_c8_p820_11,c8_p820_11);
//        contentValues.put(SQLConstantes.modulo8_c8_p820_o,c8_p820_1);
//
//        if(!data.existeElemento(getNombreTabla(),idEncuestado)){
//            Modulo8 modulo8 = new Modulo8();
//            modulo8.set_id(idEncuestado);
//            modulo8.setIdHogar(idHogar);
//            modulo8.setIdVivienda(idVivienda);
//            data.insertarElemento(getNombreTabla(), modulo8.toValues());
//        }
//        data.actualizarElemento(getNombreTabla(), contentValues, idEncuestado);
//
//        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
//        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp817p820,"1",idEncuestado);
//        //ocultamos o mostramos preguntas o fragments
//        ocultarOtrosLayouts();
//        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
//        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo8_COB800,"1",idEncuestado);
//        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo8_COB800,"0",idEncuestado);
//        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
//        data.close();
    }

    @Override
    public void llenarVariables() {
//        idInformante = informanteSpinner.getSelectedItemPosition() + "";
//        c8_p817 = c8_p817_RadioGroup.indexOfChild(c8_p817_RadioGroup.findViewById(c8_p817_RadioGroup.getCheckedRadioButtonId()))+"";
//        c8_p818 = c8_p818_RadioGroup.indexOfChild(c8_p818_RadioGroup.findViewById(c8_p818_RadioGroup.getCheckedRadioButtonId()))+"";
//        if(c8_p819_1_Checkbox.isChecked()) c8_p819_1 = "1"; else c8_p819_1 = "0";
//        if(c8_p819_2_Checkbox.isChecked()) c8_p819_2 = "1"; else c8_p819_2 = "0";
//        if(c8_p819_3_Checkbox.isChecked()) c8_p819_3 = "1"; else c8_p819_3 = "0";
//        if(c8_p819_4_Checkbox.isChecked()) c8_p819_4 = "1"; else c8_p819_4 = "0";
//        if(c8_p819_5_Checkbox.isChecked()) c8_p819_5 = "1"; else c8_p819_5 = "0";
//        if(c8_p819_6_Checkbox.isChecked()) c8_p819_6 = "1"; else c8_p819_6 = "0";
//        if(c8_p819_7_Checkbox.isChecked()) c8_p819_7 = "1"; else c8_p819_7 = "0";
//        if(c8_p819_8_Checkbox.isChecked()) c8_p819_8 = "1"; else c8_p819_8 = "0";
//        if(c8_p819_9_Checkbox.isChecked()) c8_p819_9 = "1"; else c8_p819_9 = "0";
//        if(c8_p819_10_Checkbox.isChecked()) c8_p819_10 = "1"; else c8_p819_10 = "0";
//        if(c8_p819_11_Checkbox.isChecked()) c8_p819_11 = "1"; else c8_p819_11 = "0";
//        if(c8_p819_12_Checkbox.isChecked()) c8_p819_12 = "1"; else c8_p819_12 = "0";
//        if(c8_p819_13_Checkbox.isChecked()) c8_p819_13 = "1"; else c8_p819_13 = "0";
//        if(c8_p819_14_Checkbox.isChecked()) c8_p819_14 = "1"; else c8_p819_14 = "0";
//        c8_p819_o = c8_p819_o_EditText.getText().toString();
//        if(c8_p820_1_Checkbox.isChecked()) c8_p820_1 = "1"; else c8_p820_1 = "0";
//        if(c8_p820_2_Checkbox.isChecked()) c8_p820_2 = "1"; else c8_p820_2 = "0";
//        if(c8_p820_3_Checkbox.isChecked()) c8_p820_3 = "1"; else c8_p820_3 = "0";
//        if(c8_p820_4_Checkbox.isChecked()) c8_p820_4 = "1"; else c8_p820_4 = "0";
//        if(c8_p820_5_Checkbox.isChecked()) c8_p820_5 = "1"; else c8_p820_5 = "0";
//        if(c8_p820_6_Checkbox.isChecked()) c8_p820_6 = "1"; else c8_p820_6 = "0";
//        if(c8_p820_7_Checkbox.isChecked()) c8_p820_7 = "1"; else c8_p820_7 = "0";
//        if(c8_p820_8_Checkbox.isChecked()) c8_p820_8 = "1"; else c8_p820_8 = "0";
//        if(c8_p820_9_Checkbox.isChecked()) c8_p820_9 = "1"; else c8_p820_9 = "0";
//        if(c8_p820_10_Checkbox.isChecked()) c8_p820_10 = "1"; else c8_p820_10 = "0";
//        if(c8_p820_11_Checkbox.isChecked()) c8_p820_11 = "1"; else c8_p820_11 = "0";
//        c8_p820_o = c8_p820_o_EditText.getText().toString();
    }

    @Override
    public void cargarDatos() {
//        Data data = new Data(context);
//        data.open();
//        if(data.existeElemento(getNombreTabla(), idEncuestado)){
//            Modulo8 modulo8 = data.getModulo8(idEncuestado);
//            ArrayList<String> residentes = data.getListaSpinnerResidentesHogar(modulo8.getIdHogar());
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,residentes);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            informanteSpinner.setAdapter(adapter);
//            informanteSpinner.setSelection(Integer.parseInt(modulo8.getIdInformante()));
//            if(!modulo8.getC8_p817().equals("-1")&&!modulo8.getC8_p817().equals(""))((RadioButton)c8_p817_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p817()))).setChecked(true);
//            if(!modulo8.getC8_p818().equals("-1")&&!modulo8.getC8_p818().equals(""))((RadioButton)c8_p818_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p818()))).setChecked(true);
//            if(modulo8.getC8_p819_1().equals("1")) c8_p819_1_Checkbox.setChecked(true);
//            if(modulo8.getC8_p819_2().equals("1")) c8_p819_2_Checkbox.setChecked(true);
//            if(modulo8.getC8_p819_3().equals("1")) c8_p819_3_Checkbox.setChecked(true);
//            if(modulo8.getC8_p819_4().equals("1")) c8_p819_4_Checkbox.setChecked(true);
//            if(modulo8.getC8_p819_5().equals("1")) c8_p819_5_Checkbox.setChecked(true);
//            if(modulo8.getC8_p819_6().equals("1")) c8_p819_6_Checkbox.setChecked(true);
//            if(modulo8.getC8_p819_7().equals("1")) c8_p819_7_Checkbox.setChecked(true);
//            if(modulo8.getC8_p819_8().equals("1")) c8_p819_8_Checkbox.setChecked(true);
//            if(modulo8.getC8_p819_9().equals("1")) c8_p819_9_Checkbox.setChecked(true);
//            if(modulo8.getC8_p819_10().equals("1")) c8_p819_10_Checkbox.setChecked(true);
//            if(modulo8.getC8_p819_11().equals("1")) c8_p819_11_Checkbox.setChecked(true);
//            if(modulo8.getC8_p819_12().equals("1")) c8_p819_12_Checkbox.setChecked(true);
//            if(modulo8.getC8_p819_13().equals("1")) c8_p819_13_Checkbox.setChecked(true);
//            if(modulo8.getC8_p819_14().equals("1")) c8_p819_14_Checkbox.setChecked(true);
//            c8_p819_o_EditText.setText(modulo8.getC8_p819_o());
//            if(modulo8.getC8_p820_1().equals("1")) c8_p820_1_Checkbox.setChecked(true);
//            if(modulo8.getC8_p820_2().equals("1")) c8_p820_2_Checkbox.setChecked(true);
//            if(modulo8.getC8_p820_3().equals("1")) c8_p820_3_Checkbox.setChecked(true);
//            if(modulo8.getC8_p820_4().equals("1")) c8_p820_4_Checkbox.setChecked(true);
//            if(modulo8.getC8_p820_5().equals("1")) c8_p820_5_Checkbox.setChecked(true);
//            if(modulo8.getC8_p820_6().equals("1")) c8_p820_6_Checkbox.setChecked(true);
//            if(modulo8.getC8_p820_7().equals("1")) c8_p820_7_Checkbox.setChecked(true);
//            if(modulo8.getC8_p820_8().equals("1")) c8_p820_8_Checkbox.setChecked(true);
//            if(modulo8.getC8_p820_9().equals("1")) c8_p820_9_Checkbox.setChecked(true);
//            if(modulo8.getC8_p820_10().equals("1")) c8_p820_10_Checkbox.setChecked(true);
//            if(modulo8.getC8_p820_11().equals("1")) c8_p820_11_Checkbox.setChecked(true);
//            c8_p820_o_EditText.setText(modulo8.getC8_p820_o());
//        }
//        data.close();
    }

    @Override
    public void llenarVista() {
//        Data data = new Data(context);
//        data.open();
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p817,idEncuestado)) m8_p817_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p818,idEncuestado)) m8_p818_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p819,idEncuestado)) m8_p819_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p820,idEncuestado)) m8_p820_linearlayout.setVisibility(View.GONE);
//        data.close();
    }

    @Override
    public boolean validarDatos() {
//        llenarVariables();
//        if (m8_p817_linearlayout.getVisibility() == View.VISIBLE){
//            if(c8_p817.equals("-1")){mostrarMensaje("PREGUNTA 817: DEBE SELECCIONAR UNA OPCION");return false;}
//        }else{c8_p817 = "";}
//
//        if (m8_p818_linearlayout.getVisibility() == View.VISIBLE){
//            if(c8_p818.equals("-1")){mostrarMensaje("PREGUNTA 818: DEBE SELECCIONAR UNA OPCION");return false;}
//        }else{c8_p818 = "";}
//
//        if (m8_p819_linearlayout.getVisibility() == View.VISIBLE){
//            if(c8_p819_1.equals("0") && c8_p819_2.equals("0") && c8_p819_3.equals("0") && c8_p819_4.equals("0") && c8_p819_5.equals("0") && c8_p819_6.equals("0")
//                    && c8_p819_7.equals("0") && c8_p819_8.equals("0") && c8_p819_9.equals("0") && c8_p819_10.equals("0") && c8_p819_11.equals("0") && c8_p819_12.equals("0") && c8_p819_13.equals("0") && c8_p819_14.equals("0")){
//                mostrarMensaje("PREGUNTA 819: DEBE SELECCIONAR ALGUNA OPCION");
//                return false;
//            }
//            if (c8_p819_14.equals("1")){
//                if(c8_p819_o.trim().equals("")){mostrarMensaje("PREGUNTA 819 - OPCION 14: DEBE ESPECIFICAR OTRO");return false;}
//            }
//        }
//
//        if (m8_p820_linearlayout.getVisibility() == View.VISIBLE){
//            if(c8_p820_1.equals("0") && c8_p820_2.equals("0") && c8_p820_3.equals("0") && c8_p820_4.equals("0") && c8_p820_5.equals("0") && c8_p820_6.equals("0")
//                    && c8_p820_7.equals("0") && c8_p820_8.equals("0") && c8_p820_9.equals("0") && c8_p820_10.equals("0") && c8_p820_11.equals("0")){
//                mostrarMensaje("PREGUNTA 820: DEBE SELECCIONAR ALGUNA OPCION");
//                return false;
//            }
//            if (c8_p820_11.equals("1")){
//                if(c8_p820_o.trim().equals("")){mostrarMensaje("PREGUNTA 820 - OPCION 11: DEBE ESPECIFICAR OTRO");return false;}
//            }
//        }
        return true;
    }

    public void ocultarOtrosLayouts(){
        if (c8_p820_1_Checkbox.isChecked() || c8_p820_2_Checkbox.isChecked() || c8_p820_3_Checkbox.isChecked() ||
                c8_p820_4_Checkbox.isChecked() || c8_p820_5_Checkbox.isChecked() || c8_p820_6_Checkbox.isChecked() ||
                c8_p820_7_Checkbox.isChecked() || c8_p820_8_Checkbox.isChecked() || c8_p820_9_Checkbox.isChecked() ||
                c8_p820_10_Checkbox.isChecked() || c8_p820_11_Checkbox.isChecked()){
            Data data = new Data(context);
            data.open();
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.modulo8_c8_p821_1,"");
            contentValues.put(SQLConstantes.modulo8_c8_p821_2,"");
            contentValues.put(SQLConstantes.modulo8_c8_p821_3,"");
            contentValues.put(SQLConstantes.modulo8_c8_p821_4,"");
            contentValues.put(SQLConstantes.modulo8_c8_p821_5,"");
            contentValues.put(SQLConstantes.modulo8_c8_p821_6,"");
            contentValues.put(SQLConstantes.modulo8_c8_p821_7,"");
            contentValues.put(SQLConstantes.modulo8_c8_p821_8,"");
            data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
            contentValues = new ContentValues();
            contentValues.put(SQLConstantes.layouts_p821,"0");
            data.actualizarElemento(SQLConstantes.tablalayouts,contentValues,idEncuestado);
        }else{
            Data data = new Data(context);
            data.open();
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.layouts_p821,"1");
            data.actualizarElemento(SQLConstantes.tablalayouts,contentValues,idEncuestado);
            data.close();
        }
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
}
