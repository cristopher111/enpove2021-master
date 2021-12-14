package com.example.ricindigus.enpove2021.fragments.modulo7;


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
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo7;
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

public class FragmentP701P705 extends FragmentPagina {

    Context context;
    String idEncuestado;
    String idVivienda, idHogar, idInformante;

    RadioGroup c7_p701_RadioGroup;
    CheckBox c7_p702_1_Checkbox, c7_p702_2_Checkbox, c7_p702_3_Checkbox , c7_p702_4_Checkbox, c7_p702_5_Checkbox,
            c7_p702_6_Checkbox, c7_p702_7_Checkbox, c7_p702_8_Checkbox, c7_p702_9_Checkbox, c7_p702_10_Checkbox;
    //    EditText c7_p702_o_EditText;
    EditText c7_p702_7_o_EditText; //Anthony M 04/05/2021
    //    RadioGroup c7_p703_RadioGroup;
    CheckBox c7_p703_1_Checkbox, c7_p703_2_Checkbox, c7_p703_3_Checkbox , c7_p703_4_Checkbox, c7_p703_5_Checkbox, //Anthony M 04/05/2021
            c7_p703_6_Checkbox, c7_p703_7_Checkbox, c7_p703_8_Checkbox, c7_p703_9_Checkbox, c7_p703_10_Checkbox; //Anthony M 04/05/2021
    EditText c7_p703_10_o_EditText; //Anthony M 04/05/2021
    CheckBox c7_p704_1_Checkbox, c7_p704_2_Checkbox, c7_p704_3_Checkbox , c7_p704_4_Checkbox, c7_p704_5_Checkbox,
            c7_p704_6_Checkbox;
    CheckBox c7_p704_7_Checkbox, c7_p704_8_Checkbox, c7_p704_9_Checkbox;
    EditText c7_p704_8_o_EditText;
    RadioGroup c7_p705_RadioGroup;
    //    EditText c7_p704_o_EditText;
//    CheckBox c7_p705_1_Checkbox, c7_p705_2_Checkbox, c7_p705_3_Checkbox , c7_p705_4_Checkbox, c7_p705_5_Checkbox,
//            c7_p705_6_Checkbox, c7_p705_7_Checkbox;
//    EditText c7_p705_o_EditText;
    LinearLayout m7_p701_linearlayout, m7_p702_linearlayout, m7_p703_linearlayout, m7_p704_linearlayout,
            m7_p705_linearlayout;
    Spinner informanteSpinner;

    private String c7_p701;
    private String c7_p702_1;
    private String c7_p702_2;
    private String c7_p702_3;
    private String c7_p702_4;
    private String c7_p702_5;
    private String c7_p702_6;
    private String c7_p702_7;
    private String c7_p702_7_o; //Anthony M 04/05/2021
    //    private String c7_p702_8;
//    private String c7_p702_9;
//    private String c7_p702_10;
//    private String c7_p702_o;
//    private String c7_p703;
    private String c7_p703_1; //Anthony M 04/05/2021
    private String c7_p703_2; //Anthony M 04/05/2021
    private String c7_p703_3; //Anthony M 04/05/2021
    private String c7_p703_4; //Anthony M 04/05/2021
    private String c7_p703_5; //Anthony M 04/05/2021
    private String c7_p703_6; //Anthony M 04/05/2021
    private String c7_p703_7; //Anthony M 04/05/2021
    private String c7_p703_8; //Anthony M 04/05/2021
    private String c7_p703_9; //Anthony M 04/05/2021
    private String c7_p703_10; //Anthony M 04/05/2021
    private String c7_p703_10_o; //Anthony M 04/05/2021
    private String c7_p704_1;
    private String c7_p704_2;
    private String c7_p704_3;
    private String c7_p704_4;
    private String c7_p704_5;
    private String c7_p704_6;
    private String c7_p704_7; //Anthony M 04/05/2021
    private String c7_p704_8; //Anthony M 04/05/2021
    private String c7_p704_9; //Anthony M 04/05/2021
    private String c7_p704_8_o; //Anthony M 04/05/2021
    private String c7_p705;

    int edad=0;
//    private String c7_p704_o;
//    private String c7_p705_1;
//    private String c7_p705_2;
//    private String c7_p705_3;
//    private String c7_p705_4;
//    private String c7_p705_5;
//    private String c7_p705_6;
//    private String c7_p705_7;
//    private String c7_p705_o;

    @SuppressLint("ValidFragment")
    public FragmentP701P705( String idEncuestado, Context context) {
        this.context = context;
        this.idEncuestado = idEncuestado;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idVivienda = residente.getId_vivienda();
        idHogar = residente.getId_hogar();
        idInformante = "";

        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());

        data.close();
    }

    public FragmentP701P705(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p701_p705, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);
        c7_p701_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod7_701_radiogroup_C7_P701);
        c7_p702_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_702_checkbox_C7_P702_1);
        c7_p702_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_702_checkbox_C7_P702_2);
        c7_p702_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_702_checkbox_C7_P702_3);
        c7_p702_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_702_checkbox_C7_P702_4);
        c7_p702_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_702_checkbox_C7_P702_5);
        c7_p702_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_702_checkbox_C7_P702_6);
        c7_p702_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_702_checkbox_C7_P702_7);
        //  c7_p702_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_702_checkbox_C7_P702_8);
        //  c7_p702_9_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_702_checkbox_C7_P702_9);
        //  c7_p702_10_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_702_checkbox_C7_P702_10);
        //  c7_p702_o_EditText = (EditText) rootView.findViewById(R.id.mod7_702_edittext_C7_P702_O);
        c7_p702_7_o_EditText = (EditText) rootView.findViewById(R.id.mod7_702_edittext_C7_P702_O);
//        // c7_p703_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod7_703_radiogroup_C7_P703);
        //Anthony M 04/05/2021
        c7_p703_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_703_checkbox_C7_P703_1);
        c7_p703_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_703_checkbox_C7_P703_2);
        c7_p703_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_703_checkbox_C7_P703_3);
        c7_p703_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_703_checkbox_C7_P703_4);
        c7_p703_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_703_checkbox_C7_P703_5);
        c7_p703_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_703_checkbox_C7_P703_6);
        c7_p703_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_703_checkbox_C7_P703_7);
        c7_p703_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_703_checkbox_C7_P703_8);
        c7_p703_9_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_703_checkbox_C7_P703_9);
        c7_p703_10_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_703_checkbox_C7_P703_10);
        c7_p703_10_o_EditText = (EditText) rootView.findViewById(R.id.mod7_703_edittext_C7_P703_O);
        c7_p704_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_704_checkbox_C7_P704_1);
        c7_p704_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_704_checkbox_C7_P704_2);
        c7_p704_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_704_checkbox_C7_P704_3);
        c7_p704_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_704_checkbox_C7_P704_4);
        c7_p704_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_704_checkbox_C7_P704_5);
        c7_p704_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_704_checkbox_C7_P704_6);
        c7_p704_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_704_checkbox_C7_P704_7);
        c7_p704_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_704_checkbox_C7_P704_8);
        c7_p704_9_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_704_checkbox_C7_P704_9);
        c7_p704_8_o_EditText = (EditText) rootView.findViewById(R.id.mod7_704_edittext_C7_P704_O);
        c7_p705_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod7_705_radiogroup_C7_P705);
//        c7_p704_o_EditText = (EditText) rootView.findViewById(R.id.mod7_704_edittext_C7_P704_O);
//
//        // c7_p705_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_705_checkbox_C7_P705_1);
//        // c7_p705_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_705_checkbox_C7_P705_2);
//        // c7_p705_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_705_checkbox_C7_P705_3);
//        // c7_p705_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_705_checkbox_C7_P705_4);
//        // c7_p705_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_705_checkbox_C7_P705_5);
//        // c7_p705_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_705_checkbox_C7_P705_6);
//        // c7_p705_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_705_checkbox_C7_P705_7);
//        // c7_p705_o_EditText = (EditText) rootView.findViewById(R.id.mod7_705_edittext_C7_P705_O);
//
        m7_p701_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m7_p701);
        m7_p702_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m7_p702);
        m7_p703_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m7_p703);
        m7_p704_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m7_p704);
        m7_p705_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m7_p705);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        configurarEditText(c7_p702_7_o_EditText,m7_p702_linearlayout,0,50);
        configurarEditText(c7_p703_10_o_EditText,m7_p703_linearlayout,0,50);
        configurarEditText(c7_p704_8_o_EditText,m7_p704_linearlayout,0,50);

        controlarChecked(c7_p702_7_Checkbox,c7_p702_7_o_EditText);
        controlarChecked(c7_p703_10_Checkbox,c7_p703_10_o_EditText);
        //controlarChecked(c7_p704_8_Checkbox,c7_p704_8_o_EditText);  //BORRAR
        controlarChecked(c7_p704_9_Checkbox,c7_p704_8_o_EditText);

        //PREGUNTA 701
        c7_p701_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                switch (seleccionado) {
                    case 1:
                        m7_p702_linearlayout.setVisibility(View.VISIBLE);
                        m7_p703_linearlayout.setVisibility(View.VISIBLE);
                        m7_p704_linearlayout.setVisibility(View.VISIBLE);
                        m7_p705_linearlayout.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        c7_p702_1_Checkbox.setChecked(false);
                        c7_p702_2_Checkbox.setChecked(false);
                        c7_p702_3_Checkbox.setChecked(false);
                        c7_p702_4_Checkbox.setChecked(false);
                        c7_p702_5_Checkbox.setChecked(false);
                        c7_p702_6_Checkbox.setChecked(false);
                        c7_p702_7_Checkbox.setChecked(false);
                        c7_p702_7_o_EditText.setText("");
                        m7_p702_linearlayout.setVisibility(View.GONE);

                        c7_p703_1_Checkbox.setChecked(false);
                        c7_p703_2_Checkbox.setChecked(false);
                        c7_p703_3_Checkbox.setChecked(false);
                        c7_p703_4_Checkbox.setChecked(false);
                        c7_p703_5_Checkbox.setChecked(false);
                        c7_p703_6_Checkbox.setChecked(false);
                        c7_p703_7_Checkbox.setChecked(false);
                        c7_p703_8_Checkbox.setChecked(false);
                        c7_p703_9_Checkbox.setChecked(false);
                        c7_p703_10_Checkbox.setChecked(false);
                        c7_p703_10_o_EditText.setText("");
                        m7_p703_linearlayout.setVisibility(View.GONE);

                        c7_p704_1_Checkbox.setChecked(false);
                        c7_p704_2_Checkbox.setChecked(false);
                        c7_p704_3_Checkbox.setChecked(false);
                        c7_p704_4_Checkbox.setChecked(false);
                        c7_p704_5_Checkbox.setChecked(false);
                        c7_p704_6_Checkbox.setChecked(false);
                        c7_p704_7_Checkbox.setChecked(false);
                        c7_p704_8_Checkbox.setChecked(false);
                        c7_p704_9_Checkbox.setChecked(false);
                        c7_p704_8_o_EditText.setText("");
                        m7_p704_linearlayout.setVisibility(View.GONE);

                        break;
                }
            }
        });



//        controlarChecked(c7_p702_10_Checkbox,c7_p702_o_EditText);
//        controlarChecked(c7_p704_6_Checkbox,c7_p704_o_EditText);
//        controlarChecked(c7_p705_6_Checkbox,c7_p705_o_EditText);
//
//        configurarEditText(c7_p702_o_EditText,m7_p702_linearlayout,0,30);
//        configurarEditText(c7_p704_o_EditText,m7_p704_linearlayout,0,30);
//        configurarEditText(c7_p705_o_EditText,m7_p705_linearlayout,0,30);
//
//        c7_p701_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
//                switch (seleccionado){
//                    case 1: m7_p702_linearlayout.setVisibility(View.VISIBLE);break;
//                    case 2:
//                        c7_p702_1_Checkbox.setChecked(false);
//                        c7_p702_2_Checkbox.setChecked(false);
//                        c7_p702_3_Checkbox.setChecked(false);
//                        c7_p702_4_Checkbox.setChecked(false);
//                        c7_p702_5_Checkbox.setChecked(false);
//                        c7_p702_6_Checkbox.setChecked(false);
//                        c7_p702_7_Checkbox.setChecked(false);
//                        c7_p702_8_Checkbox.setChecked(false);
//                        c7_p702_9_Checkbox.setChecked(false);
//                        c7_p702_10_Checkbox.setChecked(false);
//                        c7_p702_o_EditText.setText("");
//                        m7_p702_linearlayout.setVisibility(View.GONE);
//                        break;
//                }
//            }
//        });
//
//        c7_p703_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
//                switch (seleccionado){
//                    case 1: m7_p704_linearlayout.setVisibility(View.VISIBLE);m7_p705_linearlayout.setVisibility(View.VISIBLE);break;
//                    case 2:
//                        c7_p704_1_Checkbox.setChecked(false);
//                        c7_p704_2_Checkbox.setChecked(false);
//                        c7_p704_3_Checkbox.setChecked(false);
//                        c7_p704_4_Checkbox.setChecked(false);
//                        c7_p704_5_Checkbox.setChecked(false);
//                        c7_p704_6_Checkbox.setChecked(false);
//                        c7_p705_1_Checkbox.setChecked(false);
//                        c7_p705_2_Checkbox.setChecked(false);
//                        c7_p705_3_Checkbox.setChecked(false);
//                        c7_p705_4_Checkbox.setChecked(false);
//                        c7_p705_5_Checkbox.setChecked(false);
//                        c7_p705_6_Checkbox.setChecked(false);
//                        c7_p705_7_Checkbox.setChecked(false);
//                        c7_p704_o_EditText.setText("");
//                        c7_p705_o_EditText.setText("");
//                        m7_p704_linearlayout.setVisibility(View.GONE);
//                        m7_p705_linearlayout.setVisibility(View.GONE);
//                        break;
//                }
//            }
//        });
     llenarVista();
        cargarDatos();
    }

    public void guardarDatos(){
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo7_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo7_c7_p701, c7_p701);
        contentValues.put(SQLConstantes.modulo7_c7_p702_1, c7_p702_1);
        contentValues.put(SQLConstantes.modulo7_c7_p702_2, c7_p702_2);
        contentValues.put(SQLConstantes.modulo7_c7_p702_3, c7_p702_3);
        contentValues.put(SQLConstantes.modulo7_c7_p702_4, c7_p702_4);
        contentValues.put(SQLConstantes.modulo7_c7_p702_5, c7_p702_5);
        contentValues.put(SQLConstantes.modulo7_c7_p702_6, c7_p702_6);
        contentValues.put(SQLConstantes.modulo7_c7_p702_7, c7_p702_7);
        //Anthony M 04/05/2021
        contentValues.put(SQLConstantes.modulo7_c7_p702_7_o ,c7_p702_7_o);
        contentValues.put(SQLConstantes.modulo7_c7_p703_1   ,c7_p703_1);
        contentValues.put(SQLConstantes.modulo7_c7_p703_2   ,c7_p703_2);
        contentValues.put(SQLConstantes.modulo7_c7_p703_3   ,c7_p703_3);
        contentValues.put(SQLConstantes.modulo7_c7_p703_4   ,c7_p703_4);
        contentValues.put(SQLConstantes.modulo7_c7_p703_5   ,c7_p703_5);
        contentValues.put(SQLConstantes.modulo7_c7_p703_6   ,c7_p703_6);
        contentValues.put(SQLConstantes.modulo7_c7_p703_7   ,c7_p703_7);
        contentValues.put(SQLConstantes.modulo7_c7_p703_8   ,c7_p703_8);
        contentValues.put(SQLConstantes.modulo7_c7_p703_9   ,c7_p703_9);
        contentValues.put(SQLConstantes.modulo7_c7_p703_10  ,c7_p703_10);
        contentValues.put(SQLConstantes.modulo7_c7_p703_10_o,c7_p703_10_o);
//        contentValues.put(SQLConstantes.modulo7_c7_p702_8, c7_p702_8);
//        contentValues.put(SQLConstantes.modulo7_c7_p702_9, c7_p702_9);
//        contentValues.put(SQLConstantes.modulo7_c7_p702_10, c7_p702_10);
//        contentValues.put(SQLConstantes.modulo7_c7_p702_o, c7_p702_o);
//        contentValues.put(SQLConstantes.modulo7_c7_p703,c7_p703);
        contentValues.put(SQLConstantes.modulo7_c7_p704_1,c7_p704_1);
        contentValues.put(SQLConstantes.modulo7_c7_p704_2,c7_p704_2);
        contentValues.put(SQLConstantes.modulo7_c7_p704_3,c7_p704_3);
        contentValues.put(SQLConstantes.modulo7_c7_p704_4,c7_p704_4);
        contentValues.put(SQLConstantes.modulo7_c7_p704_5,c7_p704_5);
        contentValues.put(SQLConstantes.modulo7_c7_p704_6,c7_p704_6);
//Anthony M 04/05/2021
        contentValues.put(SQLConstantes.modulo7_c7_p704_7   ,c7_p704_7);
        contentValues.put(SQLConstantes.modulo7_c7_p704_8   ,c7_p704_8);
        contentValues.put(SQLConstantes.modulo7_c7_p704_9   ,c7_p704_9);
        contentValues.put(SQLConstantes.modulo7_c7_p704_8_o ,c7_p704_8_o);
        contentValues.put(SQLConstantes.modulo7_c7_p705     ,c7_p705);
//        contentValues.put(SQLConstantes.modulo7_c7_p704_o,c7_p704_o);
//        contentValues.put(SQLConstantes.modulo7_c7_p705_1,c7_p705_1);
//        contentValues.put(SQLConstantes.modulo7_c7_p705_2,c7_p705_2);
//        contentValues.put(SQLConstantes.modulo7_c7_p705_3,c7_p705_3);
//        contentValues.put(SQLConstantes.modulo7_c7_p705_4,c7_p705_4);
//        contentValues.put(SQLConstantes.modulo7_c7_p705_5,c7_p705_5);
//        contentValues.put(SQLConstantes.modulo7_c7_p705_6,c7_p705_6);
//        contentValues.put(SQLConstantes.modulo7_c7_p705_7,c7_p705_7);
//        contentValues.put(SQLConstantes.modulo7_c7_p705_o,c7_p705_o);

        if(!data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo7 modulo7 = new Modulo7();
            modulo7.set_id(idEncuestado);
            modulo7.setIdHogar(idHogar);
            modulo7.setIdVivienda(idVivienda);
            data.insertarElemento(getNombreTabla(), modulo7.toValues());
        }
        data.actualizarElemento(getNombreTabla(), contentValues, idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp701p705,"1",idEncuestado);

        ocultarOtrosLayouts();

        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo7_COB700,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo7_COB700,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();
    }

    private void ocultarOtrosLayouts() {
        Data data = new Data(context);
        data.open();

        if (c7_p705.equals("2") || c7_p705.equals("3")){
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.modulo7_c7_p706_1,"");
            contentValues.put(SQLConstantes.modulo7_c7_p706_2,"");
            contentValues.put(SQLConstantes.modulo7_c7_p706_3,"");
            contentValues.put(SQLConstantes.modulo7_c7_p706_4,"");
            contentValues.put(SQLConstantes.modulo7_c7_p706_5,"");
            contentValues.put(SQLConstantes.modulo7_c7_p706_6,"");
            contentValues.put(SQLConstantes.modulo7_c7_p706_6_o,"");
            data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);

            contentValues = new ContentValues();
            contentValues.put(SQLConstantes.layouts_p706,"0");
            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
        }else{
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.layouts_p706,"1");
            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
            data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp706p709,"0",idEncuestado);
        }

        data.close();

//        Data data = new Data(context);
//        data.open();
//        if(c7_p705.equals("2") || c7_p705.equals("3")){
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.modulo7_c7_p706_1,"");
//            contentValues.put(SQLConstantes.modulo7_c7_p706_2,"");
//            contentValues.put(SQLConstantes.modulo7_c7_p706_3,"");
//            contentValues.put(SQLConstantes.modulo7_c7_p706_4,"");
//            contentValues.put(SQLConstantes.modulo7_c7_p706_5,"");
//            contentValues.put(SQLConstantes.modulo7_c7_p706_6,"");
//            contentValues.put(SQLConstantes.modulo7_c7_p706_6_o,"");
//            data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
//
//            contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.layouts_p706,"0");
//
//            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
//            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p706p709,"-1",idEncuestado);
//        }else{
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.layouts_p706,"1");
//
//            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
//            if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p706p709,idEncuestado).equals("-1"))
//                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p706p709,"1",idEncuestado);
//            data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp706p709,"0",idEncuestado);
//        }

    }


    public void llenarVariables(){
        idInformante = obtener_Nresidente(informanteSpinner);
        c7_p701 = c7_p701_RadioGroup.indexOfChild(c7_p701_RadioGroup.findViewById(c7_p701_RadioGroup.getCheckedRadioButtonId()))+"";
        if (c7_p702_1_Checkbox.isChecked()) c7_p702_1 = "1"; else c7_p702_1 = "0";
        if (c7_p702_2_Checkbox.isChecked()) c7_p702_2 = "1"; else c7_p702_2 = "0";
        if (c7_p702_3_Checkbox.isChecked()) c7_p702_3 = "1"; else c7_p702_3 = "0";
        if (c7_p702_4_Checkbox.isChecked()) c7_p702_4 = "1"; else c7_p702_4 = "0";
        if (c7_p702_5_Checkbox.isChecked()) c7_p702_5 = "1"; else c7_p702_5 = "0";
        if (c7_p702_6_Checkbox.isChecked()) c7_p702_6 = "1"; else c7_p702_6 = "0";
        if (c7_p702_7_Checkbox.isChecked()) c7_p702_7 = "1"; else c7_p702_7 = "0";
        c7_p702_7_o = c7_p702_7_o_EditText.getText().toString(); //Anthony M 04/05/2021
//        if (c7_p702_8_Checkbox.isChecked()) c7_p702_8 = "1"; else c7_p702_8 = "0";
//        if (c7_p702_9_Checkbox.isChecked()) c7_p702_9 = "1"; else c7_p702_9 = "0";
//        if (c7_p702_10_Checkbox.isChecked()) c7_p702_10 ="1"; else c7_p702_10 = "0";
//        c7_p702_o = c7_p702_o_EditText.getText().toString();
//        c7_p703 = c7_p703_RadioGroup.indexOfChild(c7_p703_RadioGroup.findViewById(c7_p703_RadioGroup.getCheckedRadioButtonId()))+"";
        //Anthony M 04/05/2021
        if (c7_p703_1_Checkbox.isChecked()) c7_p703_1 = "1"; else c7_p703_1 = "0";
        if (c7_p703_2_Checkbox.isChecked()) c7_p703_2 = "1"; else c7_p703_2 = "0";
        if (c7_p703_3_Checkbox.isChecked()) c7_p703_3 = "1"; else c7_p703_3 = "0";
        if (c7_p703_4_Checkbox.isChecked()) c7_p703_4 = "1"; else c7_p703_4 = "0";
        if (c7_p703_5_Checkbox.isChecked()) c7_p703_5 = "1"; else c7_p703_5 = "0";
        if (c7_p703_6_Checkbox.isChecked()) c7_p703_6 = "1"; else c7_p703_6 = "0";
        if (c7_p703_7_Checkbox.isChecked()) c7_p703_7 = "1"; else c7_p703_7 = "0";
        if (c7_p703_8_Checkbox.isChecked()) c7_p703_8 = "1"; else c7_p703_8 = "0";
        if (c7_p703_9_Checkbox.isChecked()) c7_p703_9 = "1"; else c7_p703_9 = "0";
        if (c7_p703_10_Checkbox.isChecked()) c7_p703_10 = "1"; else c7_p703_10 = "0";
        c7_p703_10_o = c7_p703_10_o_EditText.getText().toString();

        if (c7_p704_1_Checkbox.isChecked()) c7_p704_1 = "1"; else c7_p704_1 = "0";
        if (c7_p704_2_Checkbox.isChecked()) c7_p704_2 = "1"; else c7_p704_2 = "0";
        if (c7_p704_3_Checkbox.isChecked()) c7_p704_3 = "1"; else c7_p704_3 = "0";
        if (c7_p704_4_Checkbox.isChecked()) c7_p704_4 = "1"; else c7_p704_4 = "0";
        if (c7_p704_5_Checkbox.isChecked()) c7_p704_5 = "1"; else c7_p704_5 = "0";
        if (c7_p704_6_Checkbox.isChecked()) c7_p704_6 = "1"; else c7_p704_6 = "0";
        if (c7_p704_7_Checkbox.isChecked()) c7_p704_7 = "1"; else c7_p704_7 = "0"; //Anthony M 04/05/2021
        if (c7_p704_8_Checkbox.isChecked()) c7_p704_8 = "1"; else c7_p704_8 = "0"; //Anthony M 04/05/2021
        if (c7_p704_9_Checkbox.isChecked()) c7_p704_9 = "1"; else c7_p704_9 = "0"; //Anthony M 04/05/2021
        c7_p704_8_o = c7_p704_8_o_EditText.getText().toString();
        c7_p705 = c7_p705_RadioGroup.indexOfChild(c7_p705_RadioGroup.findViewById(c7_p705_RadioGroup.getCheckedRadioButtonId()))+"";
//        c7_p704_o = c7_p704_o_EditText.getText().toString();
//        if (c7_p705_1_Checkbox.isChecked()) c7_p705_1 = "1"; else c7_p705_1 = "0";
//        if (c7_p705_2_Checkbox.isChecked()) c7_p705_2 = "1"; else c7_p705_2 = "0";
//        if (c7_p705_3_Checkbox.isChecked()) c7_p705_3 = "1"; else c7_p705_3 = "0";
//        if (c7_p705_4_Checkbox.isChecked()) c7_p705_4 = "1"; else c7_p705_4 = "0";
//        if (c7_p705_5_Checkbox.isChecked()) c7_p705_5 = "1"; else c7_p705_5 = "0";
//        if (c7_p705_6_Checkbox.isChecked()) c7_p705_6 = "1"; else c7_p705_6 = "0";
//        if (c7_p705_7_Checkbox.isChecked()) c7_p705_7 = "1"; else c7_p705_7 = "0";
//        c7_p705_o = c7_p705_o_EditText.getText().toString();

    }

    @Override
    public void cargarDatos() {
        Data data = new Data(context);
        data.open();
        if(data.existeElemento(getNombreTabla(), idEncuestado)){
            Modulo7 modulo7 = data.getModulo7(idEncuestado);
//            ArrayList<String> residentes = data.getListaSpinnerResidentesHogar(idHogar);
//            data.close();
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,residentes);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            informanteSpinner.setAdapter(adapter);
            ArrayList<String> informantes = data.getListaInformantesMayor12(idHogar);
            UtilsMethodsInputs.loadSpinner(informantes,informanteSpinner,context);
            if(modulo7.getIdInformante() != null && !modulo7.getIdInformante().equals("")) informanteSpinner.setSelection(obtener_posicion(modulo7.getIdInformante(),informanteSpinner));
            if(!modulo7.getC7_p701().equals("-1") && !modulo7.getC7_p701().equals(""))((RadioButton)c7_p701_RadioGroup.getChildAt(Integer.parseInt(modulo7.getC7_p701()))).setChecked(true);
            if(modulo7.getC7_p702_1().equals("1")) c7_p702_1_Checkbox.setChecked(true);
            if(modulo7.getC7_p702_2().equals("1")) c7_p702_2_Checkbox.setChecked(true);
            if(modulo7.getC7_p702_3().equals("1")) c7_p702_3_Checkbox.setChecked(true);
            if(modulo7.getC7_p702_4().equals("1")) c7_p702_4_Checkbox.setChecked(true);
            if(modulo7.getC7_p702_5().equals("1")) c7_p702_5_Checkbox.setChecked(true);
            if(modulo7.getC7_p702_6().equals("1")) c7_p702_6_Checkbox.setChecked(true);
            if(modulo7.getC7_p702_7().equals("1")) c7_p702_7_Checkbox.setChecked(true);
            if(modulo7.getC7_p702_8().equals("1")) c7_p702_8_Checkbox.setChecked(true);
            if(modulo7.getC7_p702_9().equals("1")) c7_p702_9_Checkbox.setChecked(true);
            if(modulo7.getC7_p702_10().equals("1")) c7_p702_10_Checkbox.setChecked(true);
            c7_p702_7_o_EditText.setText(modulo7.getC7_p702_7_o()); //Anthony M 04/05/2021
//            c7_p702_o_EditText.setText(modulo7.getC7_p702_o());
//            if(!modulo7.getC7_p703().equals("-1") && !modulo7.getC7_p703().equals(""))((RadioButton)c7_p703_RadioGroup.getChildAt(Integer.parseInt(modulo7.getC7_p703()))).setChecked(true);
            //Anthony M 04/05/2021
            if(modulo7.getC7_p703_1().equals("1")) c7_p703_1_Checkbox.setChecked(true);
            if(modulo7.getC7_p703_2().equals("1")) c7_p703_2_Checkbox.setChecked(true);
            if(modulo7.getC7_p703_3().equals("1")) c7_p703_3_Checkbox.setChecked(true);
            if(modulo7.getC7_p703_4().equals("1")) c7_p703_4_Checkbox.setChecked(true);
            if(modulo7.getC7_p703_5().equals("1")) c7_p703_5_Checkbox.setChecked(true);
            if(modulo7.getC7_p703_6().equals("1")) c7_p703_6_Checkbox.setChecked(true);
            if(modulo7.getC7_p703_7().equals("1")) c7_p703_7_Checkbox.setChecked(true);
            if(modulo7.getC7_p703_8().equals("1")) c7_p703_8_Checkbox.setChecked(true);
            if(modulo7.getC7_p703_9().equals("1")) c7_p703_9_Checkbox.setChecked(true);
            if(modulo7.getC7_p703_10().equals("1")) c7_p703_10_Checkbox.setChecked(true);
            c7_p703_10_o_EditText.setText(modulo7.getC7_p703_10_o());

            if(modulo7.getC7_p704_1().equals("1")) c7_p704_1_Checkbox.setChecked(true);
            if(modulo7.getC7_p704_2().equals("1")) c7_p704_2_Checkbox.setChecked(true);
            if(modulo7.getC7_p704_3().equals("1")) c7_p704_3_Checkbox.setChecked(true);
            if(modulo7.getC7_p704_4().equals("1")) c7_p704_4_Checkbox.setChecked(true);
            if(modulo7.getC7_p704_5().equals("1")) c7_p704_5_Checkbox.setChecked(true);
            if(modulo7.getC7_p704_6().equals("1")) c7_p704_6_Checkbox.setChecked(true);
            if(modulo7.getC7_p704_7().equals("1")) c7_p704_7_Checkbox.setChecked(true); //Anthony M 04/05/2021
            if(modulo7.getC7_p704_8().equals("1")) c7_p704_8_Checkbox.setChecked(true); //Anthony M 04/05/2021
            if(modulo7.getC7_p704_9().equals("1")) c7_p704_9_Checkbox.setChecked(true); //Anthony M 04/05/2021
            c7_p704_8_o_EditText.setText(modulo7.getC7_p704_8_o()); //Anthony M 04/05/2021
//            c7_p704_o_EditText.setText(modulo7.getC7_p704_o());
//            if(modulo7.getC7_p705_1().equals("1")) c7_p705_1_Checkbox.setChecked(true);
//            if(modulo7.getC7_p705_2().equals("1")) c7_p705_2_Checkbox.setChecked(true);
//            if(modulo7.getC7_p705_3().equals("1")) c7_p705_3_Checkbox.setChecked(true);
//            if(modulo7.getC7_p705_4().equals("1")) c7_p705_4_Checkbox.setChecked(true);
//            if(modulo7.getC7_p705_5().equals("1")) c7_p705_5_Checkbox.setChecked(true);
//            if(modulo7.getC7_p705_6().equals("1")) c7_p705_6_Checkbox.setChecked(true);
//            if(modulo7.getC7_p705_7().equals("1")) c7_p705_7_Checkbox.setChecked(true);
//            c7_p705_o_EditText.setText(modulo7.getC7_p705_o());
            if(modulo7.getC7_p705() != null && !modulo7.getC7_p705().equals("-1") && !modulo7.getC7_p705().equals(""))((RadioButton)c7_p705_RadioGroup.getChildAt(Integer.parseInt(modulo7.getC7_p705()))).setChecked(true); //Anthony M 04/05/2021
        }
        inicio();
        data.close();


    }

    private void inicio() {

        if(validar_P701()) {
            m7_p701_linearlayout.setVisibility(View.VISIBLE);
        }
        else {
            limpiar701();
            m7_p701_linearlayout.setVisibility(View.GONE);
        }

        if(validar_P702()) {
            m7_p702_linearlayout.setVisibility(View.VISIBLE);
        }
        else {
            limpiar702();
            m7_p702_linearlayout.setVisibility(View.GONE);
        }


        if(validar_P703()) {
            m7_p703_linearlayout.setVisibility(View.VISIBLE);
        }
        else {
            limpiar703();
            m7_p703_linearlayout.setVisibility(View.GONE);
        }


        if(validar_P704()) {
            m7_p704_linearlayout.setVisibility(View.VISIBLE);
        }
        else {
            limpiar704();
            m7_p704_linearlayout.setVisibility(View.GONE);
        }


        if(validar_P705()) {
            m7_p705_linearlayout.setVisibility(View.VISIBLE);
        }
        else {
            limpiar705();
            m7_p705_linearlayout.setVisibility(View.GONE);
        }

    }




    private void limpiar701() {
        c7_p701_RadioGroup.clearCheck();
    }

    private void limpiar702() {
        c7_p702_1_Checkbox.setChecked(false);
        c7_p702_2_Checkbox.setChecked(false);
        c7_p702_3_Checkbox.setChecked(false);
        c7_p702_4_Checkbox.setChecked(false);
        c7_p702_5_Checkbox.setChecked(false);
        c7_p702_6_Checkbox.setChecked(false);
        c7_p702_7_Checkbox.setChecked(false);
        c7_p702_7_o_EditText.setText("");
    }

    private void limpiar703() {
        c7_p703_1_Checkbox.setChecked(false);
        c7_p703_2_Checkbox.setChecked(false);
        c7_p703_3_Checkbox.setChecked(false);
        c7_p703_4_Checkbox.setChecked(false);
        c7_p703_5_Checkbox.setChecked(false);
        c7_p703_6_Checkbox.setChecked(false);
        c7_p703_7_Checkbox.setChecked(false);
        c7_p703_8_Checkbox.setChecked(false);
        c7_p703_9_Checkbox.setChecked(false);
        c7_p703_10_Checkbox.setChecked(false);

        c7_p703_10_o_EditText.setText("");
    }

    private void limpiar704() {
        c7_p704_1_Checkbox.setChecked(false);
        c7_p704_2_Checkbox.setChecked(false);
        c7_p704_3_Checkbox.setChecked(false);
        c7_p704_4_Checkbox.setChecked(false);
        c7_p704_5_Checkbox.setChecked(false);
        c7_p704_6_Checkbox.setChecked(false);
        c7_p704_7_Checkbox.setChecked(false);
        c7_p704_8_Checkbox.setChecked(false);
        c7_p704_9_Checkbox.setChecked(false);

        c7_p704_8_o_EditText.setText("");
    }

    private void limpiar705() {
        c7_p705_RadioGroup.clearCheck();
    }


    public boolean validar_P701(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 ){
            valido = true;
        }
        return valido;
    }

    public boolean validar_P702(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 && c7_p701.equals("1")){
            valido = true;
        }
        return valido;
    }

    public boolean validar_P703(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 && c7_p701.equals("1")){
            valido = true;
        }
        return valido;
    }


    public boolean validar_P704(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 && c7_p701.equals("1")){
            valido = true;
        }
        return valido;
    }

    public boolean validar_P705(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5){
            valido = true;
        }
        return valido;
    }

    @Override
    public void llenarVista() {
//        Data data = new Data(context);
//        data.open();
//        POJOFragment pojoFragment = data.getFragmentsLayouts(idEncuestado);
//        POJOLayout pojoLayout = data.getLayouts(idEncuestado);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p701,idEncuestado)) m7_p701_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p702,idEncuestado)) m7_p702_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p703,idEncuestado)) m7_p703_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p704,idEncuestado)) m7_p704_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p705,idEncuestado)) m7_p705_linearlayout.setVisibility(View.GONE);
//        data.close();
    }


    public boolean validarDatos(){
        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        //PREGUNTA 701
        if (m7_p701_linearlayout.getVisibility() == View.VISIBLE){
            if (c7_p701.equals("-1")){mostrarMensaje("PREGUNTA 701: DEBE MARCAR UNA OPCIÓN"); return false;}
        }

        //PREGUNTA 702
        if (m7_p702_linearlayout.getVisibility() == View.VISIBLE){
            if (c7_p702_1.equals("0") && c7_p702_2.equals("0") && c7_p702_3.equals("0") && c7_p702_4.equals("0") && c7_p702_5.equals("0") && c7_p702_6.equals("0")
                    && c7_p702_7.equals("0")){
                mostrarMensaje("PREGUNTA 702: DEBE SELECCIONAR UNA RESPUESTA");return false;
            }
            if (c7_p702_7.equals("1")){
                if (c7_p702_7_o.trim().equals("")){ mostrarMensaje("PREGUNTA 702 - OPCION 07: DEBE ESPECIFICAR OTRO");return false;}

                if (c7_p702_7_o.length()<3){
                    mostrarMensaje("ERROR  “P702. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }
            }

        }else{
            c7_p702_1 = "";
            c7_p702_2 = "";
            c7_p702_3 = "";
            c7_p702_4 = "";
            c7_p702_5 = "";
            c7_p702_6 = "";
            c7_p702_7 = "";
            c7_p702_7_o = "";
        }


        //PREGUNTA 703
        if (m7_p703_linearlayout.getVisibility() == View.VISIBLE){
            if (c7_p703_1.equals("0") && c7_p703_2.equals("0") && c7_p703_3.equals("0") && c7_p703_4.equals("0") && c7_p703_5.equals("0") && c7_p703_6.equals("0")
                    && c7_p703_7.equals("0") && c7_p703_8.equals("0") && c7_p703_9.equals("0") && c7_p703_10.equals("0")){
                mostrarMensaje("PREGUNTA 703: DEBE SELECCIONAR UNA RESPUESTA");return false;
            }
            if (c7_p703_10.equals("1")){
                if (c7_p703_10_o.trim().equals("")){ mostrarMensaje("PREGUNTA 703 - OPCION 10: DEBE ESPECIFICAR OTRO");return false;}

                if (c7_p703_10_o.length()<3){
                    mostrarMensaje("ERROR  “P703. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }
            }
        }else{
            c7_p703_1 = "";
            c7_p703_2 = "";
            c7_p703_3 = "";
            c7_p703_4 = "";
            c7_p703_5 = "";
            c7_p703_6 = "";
            c7_p703_7 = "";
            c7_p703_8 = "";
            c7_p703_9 = "";
            c7_p703_10 = "";
            c7_p703_10_o = "";
        }

        //PREGUNTA 704
        if (m7_p704_linearlayout.getVisibility() == View.VISIBLE){
            if (c7_p704_1.equals("0") && c7_p704_2.equals("0") && c7_p704_3.equals("0") && c7_p704_4.equals("0") && c7_p704_5.equals("0") && c7_p704_6.equals("0")
                    && c7_p704_7.equals("0") && c7_p704_8.equals("0") && c7_p704_9.equals("0")){
                mostrarMensaje("PREGUNTA 704: DEBE SELECCIONAR UNA RESPUESTA");return false;
            }
            if (c7_p704_9.equals("1")){
                if (c7_p704_8_o.trim().equals("")){ mostrarMensaje("PREGUNTA 704 - OPCION 09: DEBE ESPECIFICAR OTRO");return false;}

                if (c7_p704_8_o.length()<3){ mostrarMensaje("ERROR  “P704. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;}
            }
        }else{
            c7_p704_1 = "";
            c7_p704_2 = "";
            c7_p704_3 = "";
            c7_p704_4 = "";
            c7_p704_5 = "";
            c7_p704_6 = "";
            c7_p704_7 = "";
            c7_p704_8 = "";
            c7_p704_9 = "";
            c7_p704_8_o = "";
        }

        //PREGUNTA 705
        if (m7_p705_linearlayout.getVisibility() == View.VISIBLE){
            if (c7_p705.equals("-1")){mostrarMensaje("PREGUNTA 705: DEBE MARCAR UNA OPCIÓN"); return false;}
        }else{
            c7_p705 ="";
        }


//        if (c7_p701.equals("-1")){mostrarMensaje("PREGUNTA 701: DEBE MARCAR UNA OPCIÓN"); return false;}
//
//        if (m7_p702_linearlayout.getVisibility() == View.VISIBLE){
//            if (c7_p702_1.equals("0") && c7_p702_2.equals("0") && c7_p702_3.equals("0") && c7_p702_4.equals("0") && c7_p702_5.equals("0") && c7_p702_6.equals("0")
//                    && c7_p702_7.equals("0") && c7_p702_8.equals("0") && c7_p702_9.equals("0") && c7_p702_10.equals("0")){
//                mostrarMensaje("PREGUNTA 702: DEBE SELECCIONAR ALGUNA OPCION");return false;
//            }
//            if (c7_p702_10.equals("1")){
//                if (c7_p702_o.trim().equals("")){ mostrarMensaje("PREGUNTA 702 - OPCION 10: DEBE ESPECIFICAR OTRO");return false;}
//            }
//        }else{
//            c7_p702_1 = "";
//            c7_p702_2 = "";
//            c7_p702_3 = "";
//            c7_p702_4 = "";
//            c7_p702_5 = "";
//            c7_p702_6 = "";
//            c7_p702_7 = "";
//            c7_p702_8 = "";
//            c7_p702_9 = "";
//            c7_p702_10 = "";
//            c7_p702_o = "";
//        }
//
//        if (c7_p703.equals("-1")){ mostrarMensaje("PREGUNTA 703: DEBE MARCAR UNA OPCIÓN"); return false; }
//
//        if (m7_p704_linearlayout.getVisibility() == View.VISIBLE){
//            if (c7_p704_1.equals("0") && c7_p704_2.equals("0") && c7_p704_3.equals("0") && c7_p704_4.equals("0") && c7_p704_5.equals("0") && c7_p704_6.equals("0")){
//                mostrarMensaje("PREGUNTA 704: DEBE SELECCIONAR ALGUNA OPCION"); return false;
//            }
//            if (c7_p704_6.equals("1")){
//                if (c7_p704_o.trim().equals("")){ mostrarMensaje("PREGUNTA 704 - OPCION 6: DEBE ESPECIFICAR OTRO"); return false; }
//            }
//        }else{
//            c7_p704_1 = "";
//            c7_p704_2 = "";
//            c7_p704_3 = "";
//            c7_p704_4 = "";
//            c7_p704_5 = "";
//            c7_p704_6 = "";
//            c7_p704_o = "";
//        }
//
//        if (m7_p705_linearlayout.getVisibility() == View.VISIBLE){
//            if (c7_p705_1.equals("0") && c7_p705_2.equals("0") && c7_p705_3.equals("0") && c7_p705_4.equals("0") && c7_p705_5.equals("0") && c7_p705_6.equals("0") && c7_p705_7.equals("0")){
//                mostrarMensaje("PREGUNTA 705: DEBE SELECCIONAR ALGUNA OPCION"); return false;
//            }
//            if (c7_p705_6.equals("1")){
//                if (c7_p705_o.trim().equals("")){ mostrarMensaje("PREGUNTA 705 - OPCION 6: DEBE ESPECIFICAR OTRO");return false; }
//            }
//        }else{
//            c7_p705_1 = "";
//            c7_p705_2 = "";
//            c7_p705_3 = "";
//            c7_p705_4 = "";
//            c7_p705_5 = "";
//            c7_p705_6 = "";
//            c7_p705_7 = "";
//            c7_p705_o = "";
//        }
        return true;
    }

    public String getNombreTabla() {
        return SQLConstantes.tablamodulo7;
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
        Data data = new Data(context);
        data.open();
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p701p705,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp701p705,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p706p709,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp706p709,idEncuestado).equals("0")) return false;
        data.close();
        return true;
    }
}
