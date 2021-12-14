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
public class FragmentP813P816 extends FragmentPagina {
    String idEncuestado;
    Context context;

    String idVivienda, idHogar, idInformante;
    Spinner informanteSpinner;

    CheckBox c8_p813_1_Checkbox, c8_p813_2_Checkbox, c8_p813_3_Checkbox , c8_p813_4_Checkbox, c8_p813_5_Checkbox,
            c8_p813_6_Checkbox, c8_p813_7_Checkbox, c8_p813_8_Checkbox, c8_p813_9_Checkbox, c8_p813_10_Checkbox,
            c8_p813_11_Checkbox, c8_p813_12_Checkbox, c8_p813_13_Checkbox, c8_p813_14_Checkbox;
    EditText c8_p813_o_EditText;
    CheckBox c8_p814_1_Checkbox, c8_p814_2_Checkbox, c8_p814_3_Checkbox , c8_p814_4_Checkbox, c8_p814_5_Checkbox,
            c8_p814_6_Checkbox, c8_p814_7_Checkbox, c8_p814_8_Checkbox;
    RadioGroup c8_p815_RadioGroup;
    CheckBox c8_p816_1_Checkbox, c8_p816_2_Checkbox, c8_p816_3_Checkbox , c8_p816_4_Checkbox, c8_p816_5_Checkbox,
            c8_p816_6_Checkbox, c8_p816_7_Checkbox, c8_p816_8_Checkbox, c8_p816_9_Checkbox, c8_p816_10_Checkbox,
            c8_p816_11_Checkbox, c8_p816_12_Checkbox, c8_p816_13_Checkbox;
    EditText c8_p816_o_EditText;
    LinearLayout m8_p813_linearlayout, m8_p814_linearlayout, m8_p815_linearlayout, m8_p816_linearlayout;

    private String c8_p813_1;
    private String c8_p813_2;
    private String c8_p813_3;
    private String c8_p813_4;
    private String c8_p813_5;
    private String c8_p813_6;
    private String c8_p813_7;
    private String c8_p813_8;
    private String c8_p813_9;
    private String c8_p813_10;
    private String c8_p813_11;
    private String c8_p813_12;
    private String c8_p813_13;
    private String c8_p813_14;
    private String c8_p813_o;
    private String c8_p814_1;
    private String c8_p814_2;
    private String c8_p814_3;
    private String c8_p814_4;
    private String c8_p814_5;
    private String c8_p814_6;
    private String c8_p814_7;
    private String c8_p814_8;
    private String c8_p815;
    private String c8_p816_1;
    private String c8_p816_2;
    private String c8_p816_3;
    private String c8_p816_4;
    private String c8_p816_5;
    private String c8_p816_6;
    private String c8_p816_7;
    private String c8_p816_8;
    private String c8_p816_9;
    private String c8_p816_10;
    private String c8_p816_11;
    private String c8_p816_12;
    private String c8_p816_13;
    private String c8_p816_o;



    public FragmentP813P816() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentP813P816(String idEncuestado, Context context) {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p813_p816, container, false);
//        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);
//        c8_p813_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_813_checkbox_C8_P813_1);
//        c8_p813_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_813_checkbox_C8_P813_2);
//        c8_p813_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_813_checkbox_C8_P813_3);
//        c8_p813_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_813_checkbox_C8_P813_4);
//        c8_p813_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_813_checkbox_C8_P813_5);
//        c8_p813_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_813_checkbox_C8_P813_6);
//        c8_p813_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_813_checkbox_C8_P813_7);
//        c8_p813_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_813_checkbox_C8_P813_8);
//        c8_p813_9_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_813_checkbox_C8_P813_9);
//        c8_p813_10_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_813_checkbox_C8_P813_10);
//        c8_p813_11_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_813_checkbox_C8_P813_11);
//        c8_p813_12_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_813_checkbox_C8_P813_12);
//        c8_p813_13_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_813_checkbox_C8_P813_13);
//        c8_p813_14_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_813_checkbox_C8_P813_14);
//        c8_p813_o_EditText = (EditText) rootView.findViewById(R.id.mod8_813_edittext_C8_P813_O);
//
//        c8_p814_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_814_checkbox_C8_P814_1);
//        c8_p814_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_814_checkbox_C8_P814_2);
//        c8_p814_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_814_checkbox_C8_P814_3);
//        c8_p814_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_814_checkbox_C8_P814_4);
//        c8_p814_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_814_checkbox_C8_P814_5);
//        c8_p814_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_814_checkbox_C8_P814_6);
//        c8_p814_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_814_checkbox_C8_P814_7);
//        c8_p814_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_814_checkbox_C8_P814_8);
//
//        c8_p815_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_815_radiogroup_C8_P815);
//
//        c8_p816_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_816_checkbox_C8_P816_1);
//        c8_p816_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_816_checkbox_C8_P816_2);
//        c8_p816_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_816_checkbox_C8_P816_3);
//        c8_p816_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_816_checkbox_C8_P816_4);
//        c8_p816_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_816_checkbox_C8_P816_5);
//        c8_p816_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_816_checkbox_C8_P816_6);
//        c8_p816_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_816_checkbox_C8_P816_7);
//        c8_p816_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_816_checkbox_C8_P816_8);
//        c8_p816_9_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_816_checkbox_C8_P816_9);
//        c8_p816_10_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_816_checkbox_C8_P816_10);
//        c8_p816_11_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_816_checkbox_C8_P816_11);
//        c8_p816_12_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_816_checkbox_C8_P816_12);
//        c8_p816_13_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_816_checkbox_C8_P816_13);
//        c8_p816_o_EditText = (EditText) rootView.findViewById(R.id.mod8_816_edittext_C8_P816_O);
//
//        m8_p813_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p813);
//        m8_p814_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p814);
//        m8_p815_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p815);
//        m8_p816_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p816);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        configurarEditText(c8_p813_o_EditText,m8_p813_linearlayout,0,30);
//        configurarEditText(c8_p816_o_EditText,m8_p816_linearlayout,0,30);
//
//
//        c8_p815_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
//                switch (seleccionado){
//                    case 1:
//                        m8_p816_linearlayout.setVisibility(View.VISIBLE);
//                        break;
//                    case 2:
//                        c8_p816_1_Checkbox.setChecked(false);
//                        c8_p816_2_Checkbox.setChecked(false);
//                        c8_p816_3_Checkbox.setChecked(false);
//                        c8_p816_4_Checkbox.setChecked(false);
//                        c8_p816_5_Checkbox.setChecked(false);
//                        c8_p816_6_Checkbox.setChecked(false);
//                        c8_p816_7_Checkbox.setChecked(false);
//                        c8_p816_8_Checkbox.setChecked(false);
//                        c8_p816_9_Checkbox.setChecked(false);
//                        c8_p816_10_Checkbox.setChecked(false);
//                        c8_p816_11_Checkbox.setChecked(false);
//                        c8_p816_12_Checkbox.setChecked(false);
//                        c8_p816_13_Checkbox.setChecked(false);
//                        c8_p816_o_EditText.setText("");
//                        m8_p816_linearlayout.setVisibility(View.GONE);
//                        break;
//                }
//            }
//        });
//
//        checkOcultaP814(c8_p813_1_Checkbox);
//        checkOcultaP814(c8_p813_2_Checkbox);
//        checkOcultaP814(c8_p813_3_Checkbox);
//        checkOcultaP814(c8_p813_4_Checkbox);
//        checkOcultaP814(c8_p813_5_Checkbox);
//        checkOcultaP814(c8_p813_6_Checkbox);
//        checkOcultaP814(c8_p813_7_Checkbox);
//        checkOcultaP814(c8_p813_8_Checkbox);
//        checkOcultaP814(c8_p813_9_Checkbox);
//        checkOcultaP814(c8_p813_10_Checkbox);
//        checkOcultaP814(c8_p813_11_Checkbox);
//        checkOcultaP814(c8_p813_12_Checkbox);
//        checkOcultaP814(c8_p813_13_Checkbox);
//
//
//        c8_p813_14_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    ocultarP814();
//                    c8_p813_o_EditText.setEnabled(true);
//                    c8_p813_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
//                }
//                else{
//                    if (noCheckeadoP813()) mostrarP814();
//                    c8_p813_o_EditText.setText("");
//                    c8_p813_o_EditText.setEnabled(false);
//                    c8_p813_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
//                }
//            }
//        });
//
//        controlarChecked(c8_p816_13_Checkbox,c8_p816_o_EditText);
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
//        contentValues.put(SQLConstantes.modulo8_c8_p813_1,c8_p813_1);
//        contentValues.put(SQLConstantes.modulo8_c8_p813_2,c8_p813_2);
//        contentValues.put(SQLConstantes.modulo8_c8_p813_3,c8_p813_3);
//        contentValues.put(SQLConstantes.modulo8_c8_p813_4,c8_p813_4);
//        contentValues.put(SQLConstantes.modulo8_c8_p813_5,c8_p813_5);
//        contentValues.put(SQLConstantes.modulo8_c8_p813_6,c8_p813_6);
//        contentValues.put(SQLConstantes.modulo8_c8_p813_7,c8_p813_7);
//        contentValues.put(SQLConstantes.modulo8_c8_p813_8,c8_p813_8);
//        contentValues.put(SQLConstantes.modulo8_c8_p813_9,c8_p813_9);
//        contentValues.put(SQLConstantes.modulo8_c8_p813_10,c8_p813_10);
//        contentValues.put(SQLConstantes.modulo8_c8_p813_11,c8_p813_11);
//        contentValues.put(SQLConstantes.modulo8_c8_p813_12,c8_p813_12);
//        contentValues.put(SQLConstantes.modulo8_c8_p813_13,c8_p813_13);
//        contentValues.put(SQLConstantes.modulo8_c8_p813_14,c8_p813_14);
//        contentValues.put(SQLConstantes.modulo8_c8_p813_o,c8_p813_o);
//        contentValues.put(SQLConstantes.modulo8_c8_p814_1,c8_p814_1);
//        contentValues.put(SQLConstantes.modulo8_c8_p814_2,c8_p814_2);
//        contentValues.put(SQLConstantes.modulo8_c8_p814_3,c8_p814_3);
//        contentValues.put(SQLConstantes.modulo8_c8_p814_4,c8_p814_4);
//        contentValues.put(SQLConstantes.modulo8_c8_p814_5,c8_p814_5);
//        contentValues.put(SQLConstantes.modulo8_c8_p814_6,c8_p814_6);
//        contentValues.put(SQLConstantes.modulo8_c8_p814_7,c8_p814_7);
//        contentValues.put(SQLConstantes.modulo8_c8_p814_8,c8_p814_8);
//        contentValues.put(SQLConstantes.modulo8_c8_p815,c8_p815);
//        contentValues.put(SQLConstantes.modulo8_c8_p816_1,c8_p816_1);
//        contentValues.put(SQLConstantes.modulo8_c8_p816_2,c8_p816_2);
//        contentValues.put(SQLConstantes.modulo8_c8_p816_3,c8_p816_3);
//        contentValues.put(SQLConstantes.modulo8_c8_p816_4,c8_p816_4);
//        contentValues.put(SQLConstantes.modulo8_c8_p816_5,c8_p816_5);
//        contentValues.put(SQLConstantes.modulo8_c8_p816_6,c8_p816_6);
//        contentValues.put(SQLConstantes.modulo8_c8_p816_7,c8_p816_7);
//        contentValues.put(SQLConstantes.modulo8_c8_p816_8,c8_p816_8);
//        contentValues.put(SQLConstantes.modulo8_c8_p816_9,c8_p816_9);
//        contentValues.put(SQLConstantes.modulo8_c8_p816_10,c8_p816_10);
//        contentValues.put(SQLConstantes.modulo8_c8_p816_11,c8_p816_11);
//        contentValues.put(SQLConstantes.modulo8_c8_p816_12,c8_p816_12);
//        contentValues.put(SQLConstantes.modulo8_c8_p816_13,c8_p816_13);
//        contentValues.put(SQLConstantes.modulo8_c8_p816_o,c8_p816_o);
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
//        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp813p816,"1",idEncuestado);
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
//        if(c8_p813_1_Checkbox.isChecked()) c8_p813_1 = "1"; else c8_p813_1 = "0";
//        if(c8_p813_2_Checkbox.isChecked()) c8_p813_2 = "1"; else c8_p813_2 = "0";
//        if(c8_p813_3_Checkbox.isChecked()) c8_p813_3 = "1"; else c8_p813_3 = "0";
//        if(c8_p813_4_Checkbox.isChecked()) c8_p813_4 = "1"; else c8_p813_4 = "0";
//        if(c8_p813_5_Checkbox.isChecked()) c8_p813_5 = "1"; else c8_p813_5 = "0";
//        if(c8_p813_6_Checkbox.isChecked()) c8_p813_6 = "1"; else c8_p813_6 = "0";
//        if(c8_p813_7_Checkbox.isChecked()) c8_p813_7 = "1"; else c8_p813_7 = "0";
//        if(c8_p813_8_Checkbox.isChecked()) c8_p813_8 = "1"; else c8_p813_8 = "0";
//        if(c8_p813_9_Checkbox.isChecked()) c8_p813_9 = "1"; else c8_p813_9 = "0";
//        if(c8_p813_10_Checkbox.isChecked()) c8_p813_10 = "1"; else c8_p813_10 = "0";
//        if(c8_p813_11_Checkbox.isChecked()) c8_p813_11 = "1"; else c8_p813_11 = "0";
//        if(c8_p813_12_Checkbox.isChecked()) c8_p813_12 = "1"; else c8_p813_12 = "0";
//        if(c8_p813_13_Checkbox.isChecked()) c8_p813_13 = "1"; else c8_p813_13 = "0";
//        if(c8_p813_14_Checkbox.isChecked()) c8_p813_14 = "1"; else c8_p813_14 = "0";
//        c8_p813_o = c8_p813_o_EditText.getText().toString();
//        if(c8_p814_1_Checkbox.isChecked()) c8_p814_1 = "1"; else c8_p814_1 = "0";
//        if(c8_p814_2_Checkbox.isChecked()) c8_p814_2 = "1"; else c8_p814_2 = "0";
//        if(c8_p814_3_Checkbox.isChecked()) c8_p814_3 = "1"; else c8_p814_3 = "0";
//        if(c8_p814_4_Checkbox.isChecked()) c8_p814_4 = "1"; else c8_p814_4 = "0";
//        if(c8_p814_5_Checkbox.isChecked()) c8_p814_5 = "1"; else c8_p814_5 = "0";
//        if(c8_p814_6_Checkbox.isChecked()) c8_p814_6 = "1"; else c8_p814_6 = "0";
//        if(c8_p814_7_Checkbox.isChecked()) c8_p814_7 = "1"; else c8_p814_7 = "0";
//        if(c8_p814_8_Checkbox.isChecked()) c8_p814_8 = "1"; else c8_p814_8 = "0";
//        c8_p815 = c8_p815_RadioGroup.indexOfChild(c8_p815_RadioGroup.findViewById(c8_p815_RadioGroup.getCheckedRadioButtonId()))+"";
//        if(c8_p816_1_Checkbox.isChecked()) c8_p816_1 = "1"; else c8_p816_1 = "0";
//        if(c8_p816_2_Checkbox.isChecked()) c8_p816_2 = "1"; else c8_p816_2 = "0";
//        if(c8_p816_3_Checkbox.isChecked()) c8_p816_3 = "1"; else c8_p816_3 = "0";
//        if(c8_p816_4_Checkbox.isChecked()) c8_p816_4 = "1"; else c8_p816_4 = "0";
//        if(c8_p816_5_Checkbox.isChecked()) c8_p816_5 = "1"; else c8_p816_5 = "0";
//        if(c8_p816_6_Checkbox.isChecked()) c8_p816_6 = "1"; else c8_p816_6 = "0";
//        if(c8_p816_7_Checkbox.isChecked()) c8_p816_7 = "1"; else c8_p816_7 = "0";
//        if(c8_p816_8_Checkbox.isChecked()) c8_p816_8 = "1"; else c8_p816_8 = "0";
//        if(c8_p816_9_Checkbox.isChecked()) c8_p816_9 = "1"; else c8_p816_9 = "0";
//        if(c8_p816_10_Checkbox.isChecked()) c8_p816_10 = "1"; else c8_p816_10 = "0";
//        if(c8_p816_11_Checkbox.isChecked()) c8_p816_11 = "1"; else c8_p816_11 = "0";
//        if(c8_p816_12_Checkbox.isChecked()) c8_p816_12 = "1"; else c8_p816_12 = "0";
//        if(c8_p816_13_Checkbox.isChecked()) c8_p816_13 = "1"; else c8_p816_13 = "0";
//        c8_p816_o = c8_p816_o_EditText.getText().toString();
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
//            if(modulo8.getC8_p813_1().equals("1")) c8_p813_1_Checkbox.setChecked(true);
//            if(modulo8.getC8_p813_2().equals("1")) c8_p813_2_Checkbox.setChecked(true);
//            if(modulo8.getC8_p813_3().equals("1")) c8_p813_3_Checkbox.setChecked(true);
//            if(modulo8.getC8_p813_4().equals("1")) c8_p813_4_Checkbox.setChecked(true);
//            if(modulo8.getC8_p813_5().equals("1")) c8_p813_5_Checkbox.setChecked(true);
//            if(modulo8.getC8_p813_6().equals("1")) c8_p813_6_Checkbox.setChecked(true);
//            if(modulo8.getC8_p813_7().equals("1")) c8_p813_7_Checkbox.setChecked(true);
//            if(modulo8.getC8_p813_8().equals("1")) c8_p813_8_Checkbox.setChecked(true);
//            if(modulo8.getC8_p813_9().equals("1")) c8_p813_9_Checkbox.setChecked(true);
//            if(modulo8.getC8_p813_10().equals("1")) c8_p813_10_Checkbox.setChecked(true);
//            if(modulo8.getC8_p813_11().equals("1")) c8_p813_11_Checkbox.setChecked(true);
//            if(modulo8.getC8_p813_12().equals("1")) c8_p813_12_Checkbox.setChecked(true);
//            if(modulo8.getC8_p813_13().equals("1")) c8_p813_13_Checkbox.setChecked(true);
//            if(modulo8.getC8_p813_14().equals("1")) c8_p813_14_Checkbox.setChecked(true);
//            c8_p813_o_EditText.setText(modulo8.getC8_p813_o());
//            if(modulo8.getC8_p814_1().equals("1")) c8_p814_1_Checkbox.setChecked(true);
//            if(modulo8.getC8_p814_2().equals("1")) c8_p814_2_Checkbox.setChecked(true);
//            if(modulo8.getC8_p814_3().equals("1")) c8_p814_3_Checkbox.setChecked(true);
//            if(modulo8.getC8_p814_4().equals("1")) c8_p814_4_Checkbox.setChecked(true);
//            if(modulo8.getC8_p814_5().equals("1")) c8_p814_5_Checkbox.setChecked(true);
//            if(modulo8.getC8_p814_6().equals("1")) c8_p814_6_Checkbox.setChecked(true);
//            if(modulo8.getC8_p814_7().equals("1")) c8_p814_7_Checkbox.setChecked(true);
//            if(modulo8.getC8_p814_8().equals("1")) c8_p814_8_Checkbox.setChecked(true);
//
//            if(!modulo8.getC8_p815().equals("-1")&&!modulo8.getC8_p815().equals(""))((RadioButton)c8_p815_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p815()))).setChecked(true);
//
//            if(modulo8.getC8_p816_1().equals("1")) c8_p816_1_Checkbox.setChecked(true);
//            if(modulo8.getC8_p816_2().equals("1")) c8_p816_2_Checkbox.setChecked(true);
//            if(modulo8.getC8_p816_3().equals("1")) c8_p816_3_Checkbox.setChecked(true);
//            if(modulo8.getC8_p816_4().equals("1")) c8_p816_4_Checkbox.setChecked(true);
//            if(modulo8.getC8_p816_5().equals("1")) c8_p816_5_Checkbox.setChecked(true);
//            if(modulo8.getC8_p816_6().equals("1")) c8_p816_6_Checkbox.setChecked(true);
//            if(modulo8.getC8_p816_7().equals("1")) c8_p816_7_Checkbox.setChecked(true);
//            if(modulo8.getC8_p816_8().equals("1")) c8_p816_8_Checkbox.setChecked(true);
//            if(modulo8.getC8_p816_9().equals("1")) c8_p816_9_Checkbox.setChecked(true);
//            if(modulo8.getC8_p816_10().equals("1")) c8_p816_10_Checkbox.setChecked(true);
//            if(modulo8.getC8_p816_11().equals("1")) c8_p816_11_Checkbox.setChecked(true);
//            if(modulo8.getC8_p816_12().equals("1")) c8_p816_12_Checkbox.setChecked(true);
//            if(modulo8.getC8_p816_13().equals("1")) c8_p816_13_Checkbox.setChecked(true);
//            c8_p816_o_EditText.setText(modulo8.getC8_p816_o());
//        }
//        data.close();
    }

    @Override
    public void llenarVista() {
//        Data data = new Data(context);
//        data.open();
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p813,idEncuestado)) m8_p813_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p814,idEncuestado)) m8_p814_linearlayout.setVisibility(View.GONE);
//        data.close();
    }

    @Override
    public boolean validarDatos() {
//        llenarVariables();
//        if (m8_p813_linearlayout.getVisibility() == View.VISIBLE){
//            if(c8_p813_1.equals("0") && c8_p813_2.equals("0") && c8_p813_3.equals("0") && c8_p813_4.equals("0") && c8_p813_5.equals("0") && c8_p813_6.equals("0")
//                    && c8_p813_7.equals("0") && c8_p813_8.equals("0") && c8_p813_9.equals("0") && c8_p813_10.equals("0") && c8_p813_11.equals("0") && c8_p813_12.equals("0") && c8_p813_13.equals("0") && c8_p813_14.equals("0")){
//                mostrarMensaje("PREGUNTA 813: DEBE SELECCIONAR ALGUNA OPCION");
//                return false;
//            }
//            if (c8_p813_14.equals("1")){
//                if(c8_p813_o.trim().equals("")){ mostrarMensaje("PREGUNTA 813 - OPCION 14: DEBE ESPECIFICAR OTRO");return false;}
//            }
//        }
//
//        if (m8_p814_linearlayout.getVisibility() == View.VISIBLE){
//            if(c8_p814_1.equals("0") && c8_p814_2.equals("0") && c8_p814_3.equals("0") && c8_p814_4.equals("0") && c8_p814_5.equals("0") && c8_p814_6.equals("0")
//                    && c8_p814_7.equals("0") && c8_p814_8.equals("0") ){
//                mostrarMensaje("PREGUNTA 814: DEBE SELECCIONAR ALGUNA OPCION");
//                return false;
//            }
//        }
//
//        if(c8_p815.equals("-1")){ mostrarMensaje("PREGUNTA 815: DEBE SELECCIONAR UNA OPCION");return false;}
//
//        if (m8_p816_linearlayout.getVisibility() == View.VISIBLE){
//            if(c8_p816_1.equals("0") && c8_p816_2.equals("0") && c8_p816_3.equals("0") && c8_p816_4.equals("0")&& c8_p816_5.equals("0")&& c8_p816_6.equals("0")
//                    && c8_p816_7.equals("0")&& c8_p816_8.equals("0")&& c8_p816_9.equals("0")&& c8_p816_10.equals("0")&& c8_p816_11.equals("0")&& c8_p816_12.equals("0")&& c8_p816_13.equals("0")){
//                mostrarMensaje("PREGUNTA 816: DEBE SELECCIONAR ALGUNA OPCION");
//                return false;
//            }
//
//            if (c8_p816_13.equals("1")){
//                if(c8_p816_o.trim().equals("")){ mostrarMensaje("PREGUNTA 816 - OPCION 13: DEBE ESPECIFICAR OTRO");return false;}
//            }
//        }


        return true;
    }

    public void ocultarOtrosLayouts(){
//        if (c8_p815_RadioGroup.indexOfChild(c8_p815_RadioGroup.findViewById(c8_p815_RadioGroup.getCheckedRadioButtonId())) == 2){
//            Data data = new Data(context);
//            data.open();
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.modulo8_c8_p817,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p817,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p818,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p819_1,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p819_2,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p819_3,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p819_4,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p819_5,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p819_6,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p819_7,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p819_8,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p819_9,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p819_10,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p819_11,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p819_12,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p819_13,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p819_14,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p819_o,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p820_1,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p820_2,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p820_3,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p820_4,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p820_5,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p820_6,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p820_7,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p820_8,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p820_9,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p820_10,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p820_11,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p820_o,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p821_1,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p821_2,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p821_3,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p821_4,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p821_5,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p821_6,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p821_7,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p821_8,"");
//            data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
//            contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.layouts_p817,"0");
//            contentValues.put(SQLConstantes.layouts_p818,"0");
//            contentValues.put(SQLConstantes.layouts_p819,"0");
//            contentValues.put(SQLConstantes.layouts_p820,"0");
//            contentValues.put(SQLConstantes.layouts_p821,"0");
//            data.actualizarElemento(SQLConstantes.tablalayouts,contentValues,idEncuestado);
//            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p817p820,"-1",idEncuestado);
//        }else{
//            Data data = new Data(context);
//            data.open();
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.layouts_p817,"1");
//            contentValues.put(SQLConstantes.layouts_p818,"1");
//            contentValues.put(SQLConstantes.layouts_p819,"1");
//            contentValues.put(SQLConstantes.layouts_p820,"1");
//            contentValues.put(SQLConstantes.layouts_p821,"1");
//            data.actualizarElemento(SQLConstantes.tablalayouts,contentValues,idEncuestado);
//            if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p817p820,idEncuestado).equals("-1"))
//                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p817p820,"1",idEncuestado);
//            data.close();
//        }
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

    public void mostrarP814(){
        m8_p814_linearlayout.setVisibility(View.VISIBLE);
    }

    public void ocultarP814(){
        c8_p814_1_Checkbox.setChecked(false);
        c8_p814_2_Checkbox.setChecked(false);
        c8_p814_3_Checkbox.setChecked(false);
        c8_p814_4_Checkbox.setChecked(false);
        c8_p814_5_Checkbox.setChecked(false);
        c8_p814_6_Checkbox.setChecked(false);
        c8_p814_7_Checkbox.setChecked(false);
        c8_p814_8_Checkbox.setChecked(false);
        m8_p814_linearlayout.setVisibility(View.GONE);
    }

    public boolean noCheckeadoP813(){
        return !c8_p813_1_Checkbox.isChecked() && !c8_p813_2_Checkbox.isChecked() && !c8_p813_3_Checkbox.isChecked() &&
                !c8_p813_4_Checkbox.isChecked() && !c8_p813_5_Checkbox.isChecked() && !c8_p813_6_Checkbox.isChecked() &&
                !c8_p813_7_Checkbox.isChecked() && !c8_p813_8_Checkbox.isChecked() && !c8_p813_9_Checkbox.isChecked() &&
                !c8_p813_10_Checkbox.isChecked() && !c8_p813_11_Checkbox.isChecked() && !c8_p813_12_Checkbox.isChecked() &&
                !c8_p813_13_Checkbox.isChecked() && !c8_p813_14_Checkbox.isChecked();
    }

    public void checkOcultaP814(CheckBox checkBox){
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){ ocultarP814(); }
                else{ if (noCheckeadoP813()) mostrarP814(); }
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
