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

import java.sql.Struct;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP706P709 extends FragmentPagina {

    Context context;
    String idEncuestado;
    String idInformante;

    //Anthony M 04/05/2021
    CheckBox c7_p706_1_Checkbox, c7_p706_2_Checkbox, c7_p706_3_Checkbox , c7_p706_4_Checkbox, c7_p706_5_Checkbox,
            c7_p706_6_Checkbox,c7_p706_7_Checkbox,c7_p706_8_Checkbox, c7_p706_9_Checkbox;
    EditText c7_p706_8_o_EditText;
    CheckBox c7_p707_1_Checkbox, c7_p707_2_Checkbox, c7_p707_3_Checkbox , c7_p707_4_Checkbox, c7_p707_5_Checkbox,
            c7_p707_6_Checkbox, c7_p707_7_Checkbox, c7_p707_8_Checkbox, c7_p707_9_Checkbox;
    EditText c7_p707_8_o_EditText;
    RadioGroup c7_p708_1_RadioGroup, c7_p708_2_RadioGroup, c7_p708_3_RadioGroup;
    CheckBox c7_p709_1_Checkbox, c7_p709_2_Checkbox, c7_p709_3_Checkbox , c7_p709_4_Checkbox, c7_p709_5_Checkbox,
            c7_p709_6_Checkbox, c7_p709_7_Checkbox, c7_p709_8_Checkbox, c7_p709_9_Checkbox, c7_p709_10a_Checkbox,c7_p709_10_Checkbox, c7_p709_11_Checkbox;
    EditText c7_p709_10_o_EditText;

    //    RadioGroup c7_p706_RadioGroup;
//    EditText c7_p707_o_EditText;
//    CheckBox c7_p707_1_Checkbox, c7_p707_2_Checkbox, c7_p707_3_Checkbox , c7_p707_4_Checkbox, c7_p707_5_Checkbox,
//            c7_p707_6_Checkbox, c7_p707_7_Checkbox, c7_p707_8_Checkbox, c7_p707_9_Checkbox,
//            c7_p708_1_Checkbox, c7_p708_2_Checkbox, c7_p708_3_Checkbox , c7_p708_4_Checkbox, c7_p708_5_Checkbox,
//            c7_p709_1_Checkbox, c7_p709_2_Checkbox, c7_p709_3_Checkbox , c7_p709_4_Checkbox, c7_p709_5_Checkbox,
//            c7_p709_6_Checkbox, c7_p709_7_Checkbox, c7_p709_8_Checkbox, c7_p709_9_Checkbox, c7_p709_10_Checkbox;
//    EditText c7_p709_o_EditText;
    LinearLayout m7_p706_linearlayout, m7_p707_linearlayout, m7_p708_linearlayout, m7_p709_linearlayout;
    Spinner informanteSpinner;

    //    private String c7_p706;
    private String c7_p706_1; //Anthony M 04/05/2021
    private String c7_p706_2; //Anthony M 04/05/2021
    private String c7_p706_3; //Anthony M 04/05/2021
    private String c7_p706_4; //Anthony M 04/05/2021
    private String c7_p706_5; //Anthony M 04/05/2021
    private String c7_p706_6; //Anthony M 04/05/2021
    private String c7_p706_7;
    private String c7_p706_8;
    private String c7_p706_9;
    private String c7_p706_8_o; //Anthony M 04/05/2021
    private String c7_p707_1;
    private String c7_p707_2;
    private String c7_p707_3;
    private String c7_p707_4;
    private String c7_p707_5;
    private String c7_p707_6;
    private String c7_p707_7;
    private String c7_p707_8;
    private String c7_p707_8_o; //Anthony M 04/05/2021
    private String c7_p707_9;
    //    private String c7_p707_o;
    private String c7_p708_1;
    private String c7_p708_2;
    private String c7_p708_3;
    //    private String c7_p708_4;
//    private String c7_p708_5;
    private String c7_p709_1;
    private String c7_p709_2;
    private String c7_p709_3;
    private String c7_p709_4;
    private String c7_p709_5;
    private String c7_p709_6;
    private String c7_p709_7;
    private String c7_p709_8;
    private String c7_p709_9;
    private String c7_p709_10a;
    private String c7_p709_10;
    private String c7_p709_10_o; //Anthony M 04/05/2021
    private String c7_p709_11; //Anthony M 04/05/2021
//    private String c7_p709_o;
    String p212;
    int edad=0;
    String p705;

    @SuppressLint("ValidFragment")
    public FragmentP706P709(String idEncuestado, Context context) {
        this.context = context;
        this.idEncuestado = idEncuestado;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        p212 = residente.getC2_p212();
        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());

        p705 =  data.getModulo7(idEncuestado).getC7_p705();


        data.close();
    }

    public FragmentP706P709() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p706_p709, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);
//        // c7_p706_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod7_706_radiogroup_C7_P706);
//
        //Anthony M 04/05/2021
        c7_p706_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_706_checkbox_C7_P706_1);
        c7_p706_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_706_checkbox_C7_P706_2);
        c7_p706_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_706_checkbox_C7_P706_3);
        c7_p706_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_706_checkbox_C7_P706_4);
        c7_p706_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_706_checkbox_C7_P706_5);
        c7_p706_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_706_checkbox_C7_P706_6);
        c7_p706_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_706_checkbox_C7_P706_7);
        c7_p706_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_706_checkbox_C7_P706_8);
        c7_p706_9_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_706_checkbox_C7_P706_9);
        c7_p706_8_o_EditText = (EditText) rootView.findViewById(R.id.mod7_706_edittext_C7_P706_O);

        c7_p707_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_707_checkbox_C7_P707_1);
        c7_p707_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_707_checkbox_C7_P707_2);
        c7_p707_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_707_checkbox_C7_P707_3);
        c7_p707_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_707_checkbox_C7_P707_4);
        c7_p707_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_707_checkbox_C7_P707_5);
        c7_p707_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_707_checkbox_C7_P707_6);
        c7_p707_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_707_checkbox_C7_P707_7);
        c7_p707_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_707_checkbox_C7_P707_8);
        c7_p707_9_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_707_checkbox_C7_P707_9);
        c7_p707_8_o_EditText = (EditText) rootView.findViewById(R.id.mod7_707_edittext_C7_P707_O);
//        c7_p707_o_EditText = (EditText) rootView.findViewById(R.id.mod7_707_edittext_C7_P707_O);
//
//        //  c7_p708_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_708_checkbox_C7_P708_1);
//        //  c7_p708_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_708_checkbox_C7_P708_2);
//        //  c7_p708_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_708_checkbox_C7_P708_3);
//        //  c7_p708_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_708_checkbox_C7_P708_4);
//        //  c7_p708_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_708_checkbox_C7_P708_5);

        //Anthony M 04/05/2021
        c7_p708_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_708_radiogroup_C1_P708_1);
        c7_p708_2_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_708_radiogroup_C1_P708_2);
        c7_p708_3_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_708_radiogroup_C1_P708_3);

        c7_p709_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_709_checkbox_C7_P709_1);
        c7_p709_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_709_checkbox_C7_P709_2);
        c7_p709_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_709_checkbox_C7_P709_3);
        c7_p709_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_709_checkbox_C7_P709_4);
        c7_p709_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_709_checkbox_C7_P709_5);
        c7_p709_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_709_checkbox_C7_P709_6);
        c7_p709_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_709_checkbox_C7_P709_7);
        c7_p709_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_709_checkbox_C7_P709_8);
        c7_p709_9_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_709_checkbox_C7_P709_9);
        c7_p709_10a_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_709_checkbox_C7_P709_10A);
//        c7_p709_o_EditText = (EditText) rootView.findViewById(R.id.mod7_709_edittext_C7_P709_O);
        c7_p709_10_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_709_checkbox_C7_P709_10);//Anthony M 04/05/2021
        c7_p709_10_o_EditText = (EditText) rootView.findViewById(R.id.mod7_709_edittext_C7_P709_O);//Anthony M 04/05/2021
        c7_p709_11_Checkbox = (CheckBox) rootView.findViewById(R.id.mod7_709_checkbox_C7_P709_11);//Anthony M 04/05/2021

        m7_p706_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m7_p706);
        m7_p707_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m7_p707);
        m7_p708_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m7_p708);
        m7_p709_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m7_p709);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Anthony M 04/05/2021
        configurarEditText(c7_p706_8_o_EditText,m7_p706_linearlayout,0,50);
        configurarEditText(c7_p707_8_o_EditText,m7_p707_linearlayout,0,50);
        configurarEditText(c7_p709_10_o_EditText,m7_p709_linearlayout,0,50);

        //controlarChecked(c7_p706_8_Checkbox,c7_p706_8_o_EditText); //BORRAR
        controlarChecked(c7_p706_9_Checkbox,c7_p706_8_o_EditText);
//        controlarChecked(c7_p707_8_Checkbox,c7_p707_8_o_EditText);
//        controlarChecked(c7_p709_10_Checkbox,c7_p709_10_o_EditText);
        c7_p707_1_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar707();
            }
        });
        c7_p707_2_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar707();
            }
        });
        c7_p707_3_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar707();
            }
        });
        c7_p707_4_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar707();
            }
        });
        c7_p707_5_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar707();
            }
        });
        c7_p707_6_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar707();
            }
        });
        c7_p707_7_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar707();
            }
        });
        c7_p707_8_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar707();
                if(isChecked){
                    c7_p707_8_o_EditText.setEnabled(true);
                    c7_p707_8_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else {
                    c7_p707_8_o_EditText.setText("");
                    c7_p707_8_o_EditText.setEnabled(false);
                    c7_p707_8_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });
        c7_p707_9_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    c7_p707_1_Checkbox.setChecked(false);
                    c7_p707_2_Checkbox.setChecked(false);
                    c7_p707_3_Checkbox.setChecked(false);
                    c7_p707_4_Checkbox.setChecked(false);
                    c7_p707_5_Checkbox.setChecked(false);
                    c7_p707_6_Checkbox.setChecked(false);
                    c7_p707_7_Checkbox.setChecked(false);
                    c7_p707_8_Checkbox.setChecked(false);
                    c7_p707_8_o_EditText.setText("");
//                    c7_p707_o_EditText.setText("");
                    c7_p707_1_Checkbox.setEnabled(false);
                    c7_p707_2_Checkbox.setEnabled(false);
                    c7_p707_3_Checkbox.setEnabled(false);
                    c7_p707_4_Checkbox.setEnabled(false);
                    c7_p707_5_Checkbox.setEnabled(false);
                    c7_p707_6_Checkbox.setEnabled(false);
                    c7_p707_7_Checkbox.setEnabled(false);
                    c7_p707_8_Checkbox.setEnabled(false);
                    c7_p707_8_o_EditText.setEnabled(false);
//                    c7_p707_o_EditText.setEnabled(false);
                }else{
                    c7_p707_1_Checkbox.setEnabled(true);
                    c7_p707_2_Checkbox.setEnabled(true);
                    c7_p707_3_Checkbox.setEnabled(true);
                    c7_p707_4_Checkbox.setEnabled(true);
                    c7_p707_5_Checkbox.setEnabled(true);
                    c7_p707_6_Checkbox.setEnabled(true);
                    c7_p707_7_Checkbox.setEnabled(true);
                    c7_p707_8_Checkbox.setEnabled(true);
                }
            }
        });
//
//        controlarChecked(c7_p707_8_Checkbox,c7_p707_o_EditText);
//
//        controlarChecked(c7_p709_9_Checkbox,c7_p709_o_EditText);
//        configurarEditText(c7_p707_o_EditText,m7_p707_linearlayout,0,30);
//        configurarEditText(c7_p709_o_EditText,m7_p709_linearlayout,0,30);
//
//        c7_p708_5_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    c7_p708_1_Checkbox.setChecked(false);
//                    c7_p708_2_Checkbox.setChecked(false);
//                    c7_p708_3_Checkbox.setChecked(false);
//                    c7_p708_4_Checkbox.setChecked(false);
//                    c7_p708_1_Checkbox.setEnabled(false);
//                    c7_p708_2_Checkbox.setEnabled(false);
//                    c7_p708_3_Checkbox.setEnabled(false);
//                    c7_p708_4_Checkbox.setEnabled(false);
//
//                }else{
//                    c7_p708_1_Checkbox.setEnabled(true);
//                    c7_p708_2_Checkbox.setEnabled(true);
//                    c7_p708_3_Checkbox.setEnabled(true);
//                    c7_p708_4_Checkbox.setEnabled(true);
//                }
//            }
//        });
//
        c7_p709_1_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar709();
            }
        });
        c7_p709_2_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar709();
            }
        });
        c7_p709_3_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar709();
            }
        });
        c7_p709_4_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar709();
            }
        });
        c7_p709_5_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar709();
            }
        });
        c7_p709_6_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar709();
            }
        });
        c7_p709_7_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar709();
            }
        });
        c7_p709_8_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar709();
            }
        });
        c7_p709_9_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar709();
            }
        });
        c7_p709_10a_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar709();
            }
        });
        c7_p709_10_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar709();
                if(isChecked){
                    c7_p709_10_o_EditText.setEnabled(true);
                    c7_p709_10_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else {
                    c7_p709_10_o_EditText.setText("");
                    c7_p709_10_o_EditText.setEnabled(false);
                    c7_p709_10_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });
        c7_p709_11_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    c7_p709_1_Checkbox.setChecked(false);
                    c7_p709_2_Checkbox.setChecked(false);
                    c7_p709_3_Checkbox.setChecked(false);
                    c7_p709_4_Checkbox.setChecked(false);
                    c7_p709_5_Checkbox.setChecked(false);
                    c7_p709_6_Checkbox.setChecked(false);
                    c7_p709_7_Checkbox.setChecked(false);
                    c7_p709_8_Checkbox.setChecked(false);
                    c7_p709_9_Checkbox.setChecked(false);
                    c7_p709_10a_Checkbox.setChecked(false);
                    c7_p709_10_Checkbox.setChecked(false);//08/05/2021
                    c7_p709_10_o_EditText.setText("");//08/05/2021
//                    c7_p709_o_EditText.setText("");
                    c7_p709_1_Checkbox.setEnabled(false);
                    c7_p709_2_Checkbox.setEnabled(false);
                    c7_p709_3_Checkbox.setEnabled(false);
                    c7_p709_4_Checkbox.setEnabled(false);
                    c7_p709_5_Checkbox.setEnabled(false);
                    c7_p709_6_Checkbox.setEnabled(false);
                    c7_p709_7_Checkbox.setEnabled(false);
                    c7_p709_8_Checkbox.setEnabled(false);
                    c7_p709_9_Checkbox.setEnabled(false);
                    c7_p709_10a_Checkbox.setEnabled(false);
                    c7_p709_10_Checkbox.setEnabled(false);//08/05/2021
                    c7_p709_10_o_EditText.setEnabled(false);//08/05/2021
//                    c7_p709_o_EditText.setEnabled(false);
                }else{
                    c7_p709_1_Checkbox.setEnabled(true);
                    c7_p709_2_Checkbox.setEnabled(true);
                    c7_p709_3_Checkbox.setEnabled(true);
                    c7_p709_4_Checkbox.setEnabled(true);
                    c7_p709_5_Checkbox.setEnabled(true);
                    c7_p709_6_Checkbox.setEnabled(true);
                    c7_p709_7_Checkbox.setEnabled(true);
                    c7_p709_8_Checkbox.setEnabled(true);
                    c7_p709_9_Checkbox.setEnabled(true);
                    c7_p709_10a_Checkbox.setEnabled(true);
                    c7_p709_10_Checkbox.setEnabled(true);//08/05/2021
                    c7_p709_10_o_EditText.setEnabled(true);//08/05/2021
//                    c7_p709_o_EditText.setEnabled(true);
                }
            }
        });

        llenarVista();
        cargarDatos();
    }

    public void llenarVariables(){
        idInformante = obtener_Nresidente(informanteSpinner);
//        c7_p706 = c7_p706_RadioGroup.indexOfChild(c7_p706_RadioGroup.findViewById(c7_p706_RadioGroup.getCheckedRadioButtonId()))+"";
        //Anthony M 04/05/2021


        if (c7_p706_1_Checkbox.isChecked()) c7_p706_1 = "1"; else c7_p706_1 = "0";
        if (c7_p706_2_Checkbox.isChecked()) c7_p706_2 = "1"; else c7_p706_2 = "0";
        if (c7_p706_3_Checkbox.isChecked()) c7_p706_3 = "1"; else c7_p706_3 = "0";
        if (c7_p706_4_Checkbox.isChecked()) c7_p706_4 = "1"; else c7_p706_4 = "0";
        if (c7_p706_5_Checkbox.isChecked()) c7_p706_5 = "1"; else c7_p706_5 = "0";
        if (c7_p706_6_Checkbox.isChecked()) c7_p706_6 = "1"; else c7_p706_6 = "0";
        if (c7_p706_7_Checkbox.isChecked()) c7_p706_7= "1"; else c7_p706_7 = "0";
        if (c7_p706_8_Checkbox.isChecked()) c7_p706_8 = "1"; else c7_p706_8 = "0";
        if (c7_p706_9_Checkbox.isChecked()) c7_p706_9 = "1"; else c7_p706_9 = "0";
        c7_p706_8_o = c7_p706_8_o_EditText.getText().toString();

        if (c7_p707_1_Checkbox.isChecked()) c7_p707_1 = "1"; else c7_p707_1 = "0";
        if (c7_p707_2_Checkbox.isChecked()) c7_p707_2 = "1"; else c7_p707_2 = "0";
        if (c7_p707_3_Checkbox.isChecked()) c7_p707_3 = "1"; else c7_p707_3 = "0";
        if (c7_p707_4_Checkbox.isChecked()) c7_p707_4 = "1"; else c7_p707_4 = "0";
        if (c7_p707_5_Checkbox.isChecked()) c7_p707_5 = "1"; else c7_p707_5 = "0";
        if (c7_p707_6_Checkbox.isChecked()) c7_p707_6 = "1"; else c7_p707_6 = "0";
        if (c7_p707_7_Checkbox.isChecked()) c7_p707_7 = "1"; else c7_p707_7 = "0";
        if (c7_p707_8_Checkbox.isChecked()) c7_p707_8 = "1"; else c7_p707_8 = "0";
        if (c7_p707_9_Checkbox.isChecked()) c7_p707_9 = "1"; else c7_p707_9 = "0";
        c7_p707_8_o = c7_p707_8_o_EditText.getText().toString();
        //Anthony M 04/05/2021
        c7_p708_1 = c7_p708_1_RadioGroup.indexOfChild(c7_p708_1_RadioGroup.findViewById(c7_p708_1_RadioGroup.getCheckedRadioButtonId()))+""; //Anthony M 04/05/2021
        c7_p708_2 = c7_p708_2_RadioGroup.indexOfChild(c7_p708_2_RadioGroup.findViewById(c7_p708_2_RadioGroup.getCheckedRadioButtonId()))+""; //Anthony M 04/05/2021
        c7_p708_3 = c7_p708_3_RadioGroup.indexOfChild(c7_p708_3_RadioGroup.findViewById(c7_p708_3_RadioGroup.getCheckedRadioButtonId()))+""; //Anthony M 04/05/2021
//        c7_p707_o = c7_p707_o_EditText.getText().toString();
//
//        if (c7_p708_1_Checkbox.isChecked()) c7_p708_1 = "1"; else c7_p708_1 = "0";
//        if (c7_p708_2_Checkbox.isChecked()) c7_p708_2 = "1"; else c7_p708_2 = "0";
//        if (c7_p708_3_Checkbox.isChecked()) c7_p708_3 = "1"; else c7_p708_3 = "0";
//        if (c7_p708_4_Checkbox.isChecked()) c7_p708_4 = "1"; else c7_p708_4 = "0";
//        if (c7_p708_5_Checkbox.isChecked()) c7_p708_5 = "1"; else c7_p708_5 = "0";
//
        if (c7_p709_1_Checkbox.isChecked()) c7_p709_1 = "1"; else c7_p709_1 = "0";
        if (c7_p709_2_Checkbox.isChecked()) c7_p709_2 = "1"; else c7_p709_2 = "0";
        if (c7_p709_3_Checkbox.isChecked()) c7_p709_3 = "1"; else c7_p709_3 = "0";
        if (c7_p709_4_Checkbox.isChecked()) c7_p709_4 = "1"; else c7_p709_4 = "0";
        if (c7_p709_5_Checkbox.isChecked()) c7_p709_5 = "1"; else c7_p709_5 = "0";
        if (c7_p709_6_Checkbox.isChecked()) c7_p709_6 = "1"; else c7_p709_6 = "0";
        if (c7_p709_7_Checkbox.isChecked()) c7_p709_7 = "1"; else c7_p709_7 = "0";
        if (c7_p709_8_Checkbox.isChecked()) c7_p709_8 = "1"; else c7_p709_8 = "0";
        if (c7_p709_9_Checkbox.isChecked()) c7_p709_9 = "1"; else c7_p709_9 = "0";
        if (c7_p709_10a_Checkbox.isChecked()) c7_p709_10a = "1"; else c7_p709_10a = "0";
        if (c7_p709_10_Checkbox.isChecked()) c7_p709_10 = "1"; else c7_p709_10 = "0";
        if (c7_p709_11_Checkbox.isChecked()) c7_p709_11 = "1"; else c7_p709_11 = "0"; //Anthony M 04/05/2021
        c7_p709_10_o = c7_p709_10_o_EditText.getText().toString(); //Anthony M 04/05/2021
//        c7_p709_o = c7_p709_o_EditText.getText().toString();

    }

    public boolean validarDatos(){
        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        int cant_p3_respondidos = Integer.parseInt(c7_p709_1)+Integer.parseInt(c7_p709_2)+Integer.parseInt(c7_p709_3)+Integer.parseInt(c7_p709_4)+ Integer.parseInt(c7_p709_5)+
                Integer.parseInt(c7_p709_6)+Integer.parseInt(c7_p709_7)+Integer.parseInt(c7_p709_8)+Integer.parseInt(c7_p709_9)+Integer.parseInt(c7_p709_10a)+ Integer.parseInt(c7_p709_10);


        //PREGUNTA 706
        if (m7_p706_linearlayout.getVisibility() == View.VISIBLE){

            if (c7_p706_1.equals("0") && c7_p706_2.equals("0") && c7_p706_3.equals("0") &&
                    c7_p706_4.equals("0") && c7_p706_5.equals("0") && c7_p706_6.equals("0") &&
                    c7_p706_7.equals("0") && c7_p706_8.equals("0") && c7_p706_9.equals("0")){
                mostrarMensaje("PREGUNTA 706: DEBE SELECCIONAR UNA RESPUESTA");return false;
            }
            if (c7_p706_9.equals("1")){
                if (c7_p706_8_o.trim().equals("")){ mostrarMensaje("PREGUNTA 706 - OPCION 9: DEBE ESPECIFICAR OTRO");return false;}
            }
            if(c7_p706_9.equals("1") && !c7_p706_8_o.equals("") && c7_p706_8_o.length() < 3){mostrarMensaje("PREGUNTA 706: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}

//            //--- PREGUNTA COGNITIVA  p212 != null && p212.equal(1)--//
////            Todas las personas AND P205_A>=5 AND P705=1 AND P212=1
////VERIFICACIÓN	: Sí SUM(P706_i=1)>0, para cualquier i=1,2,3,4,5,6 Entonces mostrar mensaje:
//            int cantidad706 = Integer.parseInt(c7_p706_1) + Integer.parseInt(c7_p706_2) + Integer.parseInt(c7_p706_3) + Integer.parseInt(c7_p706_4) + Integer.parseInt(c7_p706_5) + Integer.parseInt(c7_p706_6) + Integer.parseInt(c7_p706_7) + Integer.parseInt(c7_p706_8);
//            if ( (p212.equals("1") && !(p212.equals(""))) && cantidad706>0) {
//                mostrarMensaje("“SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
//            }

        }else{
            c7_p706_1 = "";
            c7_p706_2 = "";
            c7_p706_3 = "";
            c7_p706_4 = "";
            c7_p706_5 = "";
            c7_p706_6 = "";
            c7_p706_7 = "";
            c7_p706_8 = "";
            c7_p706_9 = "";
            c7_p706_8_o = "";
        }

        //PREGUNTA 707
        if (m7_p707_linearlayout.getVisibility() == View.VISIBLE){
            if (c7_p707_1.equals("0") && c7_p707_2.equals("0") &&
                    c7_p707_3.equals("0") && c7_p707_4.equals("0") &&
                    c7_p707_5.equals("0") && c7_p707_6.equals("0") &&
                    c7_p707_7.equals("0") && c7_p707_8.equals("0") && c7_p707_9.equals("0")){
                mostrarMensaje("PREGUNTA 707: DEBE SELECCIONAR AL MENOS UNA ALTERNATIVA");return false;
            }
            if (c7_p707_8.equals("1")){
                if (c7_p707_8_o.trim().equals("")){ mostrarMensaje("PREGUNTA 707 - OPCION 08: DEBE ESPECIFICAR OTRO");return false;}

                if (c7_p707_8_o.length()<3){
                    mostrarMensaje("ERROR  “P707. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }

            }
        }else{
            c7_p707_1 = "";
            c7_p707_2 = "";
            c7_p707_3 = "";
            c7_p707_4 = "";
            c7_p707_5 = "";
            c7_p707_6 = "";
            c7_p707_7 = "";
            c7_p707_8 = "";
            c7_p707_9 = "";
            c7_p707_8_o = "";
        }

        //PREGUNTA 708
        if (m7_p708_linearlayout.getVisibility() == View.VISIBLE){
            if (c7_p708_1.equals("-1")){mostrarMensaje("PREGUNTA 708: DEBE SELECCIONAR UNA RESPUESTA 708-1"); return false;}
            if (c7_p708_2.equals("-1")){mostrarMensaje("PREGUNTA 708: DEBE SELECCIONAR UNA RESPUESTA 708-2"); return false;}
            if (c7_p708_3.equals("-1")){mostrarMensaje("PREGUNTA 708: DEBE SELECCIONAR UNA RESPUESTA 708-3"); return false;}
        }else{
            c7_p708_1 ="";
            c7_p708_2 ="";
            c7_p708_3 ="";
        }

        //PREGUNTA 709
        if (m7_p709_linearlayout.getVisibility() == View.VISIBLE){
            if ((c7_p709_1.equals("0") && c7_p709_2.equals("0") &&
                    c7_p709_3.equals("0") && c7_p709_4.equals("0") &&
                    c7_p709_5.equals("0") && c7_p709_6.equals("0") &&
                    c7_p709_7.equals("0") && c7_p709_8.equals("0") &&
                    c7_p709_9.equals("0") && c7_p709_10a.equals("0") && c7_p709_10.equals("0") && c7_p709_11.equals("0"))  ){ /*|| (cant_p3_respondidos != 3 && c7_p709_11.equals("0"))*/
                mostrarMensaje("PREGUNTA 709: DEBE SELECCIONAR AL MENOS DOS NECESIDADES");return false;
            }

            if(cant_p3_respondidos < 2 && c7_p709_11.equals("0")){
                mostrarMensaje("PREGUNTA 709: DEBE SELECCIONAR AL MENOS DOS NECESIDADES");return false;
            }

            if(cant_p3_respondidos > 3){
                mostrarMensaje("PREGUNTA 709: PUEDE SELECCIONAR TRES NECESIDADES COMO MÁXIMO");return false;
            }


            if (c7_p709_10.equals("1")){
                if (c7_p709_10_o.trim().equals("")){ mostrarMensaje("PREGUNTA 709 - OPCION 11: DEBE ESPECIFICAR OTRO");return false;}

                if (c7_p709_10_o.length()<3){
                    mostrarMensaje("ERROR  “P709 - OPCION 11. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }
            }

//            //--- PREGUNTA COGNITIVA  p212 != null && p212.equal(1)--//
////            Sí SUM(P709_i=1)=3, para cualquier i=1,2,3,4,5,6,7,8,9,10 OR P709_11=1 Entonces mostrar mensaje:
            if ((p212.equals("1") && !(p212.equals(""))) && (cant_p3_respondidos == 3 || c7_p709_11.equals("1"))) {
                mostrarMensaje("“SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
            }

        }else{
            c7_p709_1 = "";
            c7_p709_2 = "";
            c7_p709_3 = "";
            c7_p709_4 = "";
            c7_p709_5 = "";
            c7_p709_6 = "";
            c7_p709_7 = "";
            c7_p709_8 = "";
            c7_p709_9 = "";
            c7_p709_10a = "";
            c7_p709_10 = "";
            c7_p709_11 = "";
            c7_p709_10_o = "";
        }

//        if (c7_p706.equals("-1")){mostrarMensaje("PREGUNTA 706: DEBE MARCAR UNA OPCIÓN"); return false;}
//
//        if(c7_p707_1.equals("0") && c7_p707_2.equals("0") && c7_p707_3.equals("0") && c7_p707_4.equals("0") && c7_p707_5.equals("0") &&
//                c7_p707_6.equals("0") && c7_p707_7.equals("0") && c7_p707_8.equals("0") && c7_p707_9.equals("0")){
//            mostrarMensaje("PREGUNTA 707: DEBE SELECCIONAR ALGUNA OPCION");return false;
//        }
//        if (c7_p707_8.equals("1")){
//            if(c7_p707_o.trim().equals("")){mostrarMensaje("PREGUNTA 707 - OPCION 8: DEBE ESPECIFICAR OTRO");return false; }
//        }
//        if(c7_p708_1.equals("0") && c7_p708_2.equals("0") && c7_p708_3.equals("0") && c7_p708_4.equals("0") && c7_p708_5.equals("0")){
//            mostrarMensaje("PREGUNTA 708: DEBE SELECCIONAR ALGUNA OPCION");return false;
//        }
//
//        if(c7_p709_1.equals("0") && c7_p709_2.equals("0") && c7_p709_3.equals("0") && c7_p709_4.equals("0") && c7_p709_5.equals("0") &&
//                c7_p709_6.equals("0") && c7_p709_7.equals("0") && c7_p709_8.equals("0") && c7_p709_9.equals("0") && c7_p709_10.equals("0")){
//            mostrarMensaje("PREGUNTA 709: DEBE SELECCIONAR LAS TRES PRINCIPALES RAZONES O INDICAR QUE YA ESTAN CUBIERTAS");return false;
//        }else{
//            if (c7_p709_10.equals("0")){
//                int contCheck = 0;
//                if(c7_p709_1.equals("1")) contCheck ++;
//                if(c7_p709_2.equals("1")) contCheck ++;
//                if(c7_p709_3.equals("1")) contCheck ++;
//                if(c7_p709_4.equals("1")) contCheck ++;
//                if(c7_p709_5.equals("1")) contCheck ++;
//                if(c7_p709_6.equals("1")) contCheck ++;
//                if(c7_p709_7.equals("1")) contCheck ++;
//                if(c7_p709_8.equals("1")) contCheck ++;
//                if(c7_p709_9.equals("1")) contCheck ++;
//                if (contCheck < 3){
//                    mostrarMensaje("PREGUNTA 709: DEBE SELECCIONAR LAS TRES PRINCIPALES RAZONES");return false;
//                }else if (contCheck > 3){ mostrarMensaje("PREGUNTA 709: DEBE SELECCIONAR SOLO TRES PRINCIPALES RAZONES");return false;}
//            }
//        }
//        if (c7_p709_9.equals("1")){
//            if(c7_p709_o.trim().equals("")){mostrarMensaje("PREGUNTA 709 - OPCION 9: DEBE ESPECIFICAR OTRO");return false; }
//        }
        return true;
    }

    public void guardarDatos(){
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo7_id_informante,idInformante);
//        contentValues.put(SQLConstantes.modulo7_c7_p706, c7_p706 + "");
        //Anthony M 04/05/2021
        contentValues.put(SQLConstantes.modulo7_c7_p706_1   ,c7_p706_1);
        contentValues.put(SQLConstantes.modulo7_c7_p706_2   ,c7_p706_2);
        contentValues.put(SQLConstantes.modulo7_c7_p706_3   ,c7_p706_3);
        contentValues.put(SQLConstantes.modulo7_c7_p706_4   ,c7_p706_4);
        contentValues.put(SQLConstantes.modulo7_c7_p706_5   ,c7_p706_5);
        contentValues.put(SQLConstantes.modulo7_c7_p706_6   ,c7_p706_6);
        contentValues.put(SQLConstantes.modulo7_c7_p706_7   ,c7_p706_7);
        contentValues.put(SQLConstantes.modulo7_c7_p706_8   ,c7_p706_8);
        contentValues.put(SQLConstantes.modulo7_c7_p706_9   ,c7_p706_9);
        contentValues.put(SQLConstantes.modulo7_c7_p706_8_o   ,c7_p706_8_o);
        contentValues.put(SQLConstantes.modulo7_c7_p707_1,c7_p707_1 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p707_2,c7_p707_2 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p707_3,c7_p707_3 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p707_4,c7_p707_4 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p707_5,c7_p707_5 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p707_6,c7_p707_6 + "");

        contentValues.put(SQLConstantes.modulo7_c7_p707_7,c7_p707_7 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p707_8,c7_p707_8 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p707_9,c7_p707_9 + "");
//        contentValues.put(SQLConstantes.modulo7_c7_p707_o,c7_p707_o);
        contentValues.put(SQLConstantes.modulo7_c7_p708_1,c7_p708_1 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p708_2,c7_p708_2 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p708_3,c7_p708_3 + "");
//        contentValues.put(SQLConstantes.modulo7_c7_p708_4,c7_p708_4 + "");
//        contentValues.put(SQLConstantes.modulo7_c7_p708_5,c7_p708_5 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p709_1,c7_p709_1 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p709_2,c7_p709_2 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p709_3,c7_p709_3 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p709_4,c7_p709_4 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p709_5,c7_p709_5 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p709_6,c7_p709_6 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p709_7,c7_p709_7 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p709_8,c7_p709_8 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p707_8_o ,c7_p707_8_o); //Anthony M 04/05/2021
        contentValues.put(SQLConstantes.modulo7_c7_p709_9,c7_p709_9 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p709_10a,c7_p709_10a + "");
        contentValues.put(SQLConstantes.modulo7_c7_p709_10,c7_p709_10 + "");
        contentValues.put(SQLConstantes.modulo7_c7_p709_10_o,c7_p709_10_o); //Anthony M 04/05/2021
        contentValues.put(SQLConstantes.modulo7_c7_p709_11  ,c7_p709_11+""); //Anthony M 04/05/2021
//        contentValues.put(SQLConstantes.modulo7_c7_p709_o,c7_p709_o);
        data.actualizarElemento(getNombreTabla(), contentValues, idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp706p709,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo7_COB700,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo7_COB700,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();

    }



    @Override
    public void cargarDatos() {
        Data data = new Data(context);
        data.open();
        if(data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo7 modulo7 =  data.getModulo7(idEncuestado);
            ArrayList<String> informantes = data.getListaInformantesMayor12(modulo7.getIdHogar());
            UtilsMethodsInputs.loadSpinner(informantes,informanteSpinner,context);
            if(modulo7.getIdInformante() != null && !modulo7.getIdInformante().equals("")) informanteSpinner.setSelection(obtener_posicion(modulo7.getIdInformante(),informanteSpinner));
//            if(!modulo7.getC7_p706().equals("-1") && !modulo7.getC7_p706().equals("")) ((RadioButton)c7_p706_RadioGroup.getChildAt(Integer.parseInt(modulo7.getC7_p706()))).setChecked(true);
            //Anthony M 04/05/2021
            if(modulo7.getC7_p706_1().equals("1")) c7_p706_1_Checkbox.setChecked(true);
            if(modulo7.getC7_p706_2().equals("1")) c7_p706_2_Checkbox.setChecked(true);
            if(modulo7.getC7_p706_3().equals("1")) c7_p706_3_Checkbox.setChecked(true);
            if(modulo7.getC7_p706_4().equals("1")) c7_p706_4_Checkbox.setChecked(true);
            if(modulo7.getC7_p706_5().equals("1")) c7_p706_5_Checkbox.setChecked(true);
            if(modulo7.getC7_p706_6().equals("1")) c7_p706_6_Checkbox.setChecked(true);
            if(modulo7.getC7_p706_7().equals("1")) c7_p706_7_Checkbox.setChecked(true);
            if(modulo7.getC7_p706_8().equals("1")) c7_p706_8_Checkbox.setChecked(true);
            if(modulo7.getC7_p706_9().equals("1")) c7_p706_9_Checkbox.setChecked(true);
            c7_p706_8_o_EditText.setText(modulo7.getC7_p706_8_o());

            if(modulo7.getC7_p707_1().equals("1")) c7_p707_1_Checkbox.setChecked(true);
            if(modulo7.getC7_p707_2().equals("1")) c7_p707_2_Checkbox.setChecked(true);
            if(modulo7.getC7_p707_3().equals("1")) c7_p707_3_Checkbox.setChecked(true);
            if(modulo7.getC7_p707_4().equals("1")) c7_p707_4_Checkbox.setChecked(true);
            if(modulo7.getC7_p707_5().equals("1")) c7_p707_5_Checkbox.setChecked(true);
            if(modulo7.getC7_p707_6().equals("1")) c7_p707_6_Checkbox.setChecked(true);
            if(modulo7.getC7_p707_7().equals("1")) c7_p707_7_Checkbox.setChecked(true);
            if(modulo7.getC7_p707_8().equals("1")) c7_p707_8_Checkbox.setChecked(true);
            if(modulo7.getC7_p707_9().equals("1")) c7_p707_9_Checkbox.setChecked(true);
            c7_p707_8_o_EditText.setText(modulo7.getC7_p707_8_o()); //Anthony M 04/05/2021
            if(modulo7.getC7_p708_1() != null && !modulo7.getC7_p708_1().equals("-1") && !modulo7.getC7_p708_1().equals("")) ((RadioButton)c7_p708_1_RadioGroup.getChildAt(Integer.parseInt(modulo7.getC7_p708_1()))).setChecked(true); //Anthony M 04/05/2021
            if(modulo7.getC7_p708_2() != null && !modulo7.getC7_p708_2().equals("-1") && !modulo7.getC7_p708_2().equals("")) ((RadioButton)c7_p708_2_RadioGroup.getChildAt(Integer.parseInt(modulo7.getC7_p708_2()))).setChecked(true); //Anthony M 04/05/2021
            if(modulo7.getC7_p708_3() != null && !modulo7.getC7_p708_3().equals("-1") && !modulo7.getC7_p708_3().equals("")) ((RadioButton)c7_p708_3_RadioGroup.getChildAt(Integer.parseInt(modulo7.getC7_p708_3()))).setChecked(true); //Anthony M 04/05/2021

//            c7_p707_o_EditText.setText(modulo7.getC7_p707_o());
//            if(modulo7.getC7_p708_1().equals("1")) c7_p708_1_Checkbox.setChecked(true);
//            if(modulo7.getC7_p708_2().equals("1")) c7_p708_2_Checkbox.setChecked(true);
//            if(modulo7.getC7_p708_3().equals("1")) c7_p708_3_Checkbox.setChecked(true);
//            if(modulo7.getC7_p708_4().equals("1")) c7_p708_4_Checkbox.setChecked(true);
//            if(modulo7.getC7_p708_5().equals("1")) c7_p708_5_Checkbox.setChecked(true);
            if(modulo7.getC7_p709_1().equals("1")) c7_p709_1_Checkbox.setChecked(true);
            if(modulo7.getC7_p709_2().equals("1")) c7_p709_2_Checkbox.setChecked(true);
            if(modulo7.getC7_p709_3().equals("1")) c7_p709_3_Checkbox.setChecked(true);
            if(modulo7.getC7_p709_4().equals("1")) c7_p709_4_Checkbox.setChecked(true);
            if(modulo7.getC7_p709_5().equals("1")) c7_p709_5_Checkbox.setChecked(true);
            if(modulo7.getC7_p709_6().equals("1")) c7_p709_6_Checkbox.setChecked(true);
            if(modulo7.getC7_p709_7().equals("1")) c7_p709_7_Checkbox.setChecked(true);
            if(modulo7.getC7_p709_8().equals("1")) c7_p709_8_Checkbox.setChecked(true);
            if(modulo7.getC7_p709_9().equals("1")) c7_p709_9_Checkbox.setChecked(true);
            if(modulo7.getC7_p709_10a().equals("1")) c7_p709_10a_Checkbox.setChecked(true);
            if(modulo7.getC7_p709_10().equals("1")) c7_p709_10_Checkbox.setChecked(true);
            if(modulo7.getC7_p709_11().equals("1")) c7_p709_11_Checkbox.setChecked(true); //Anthony M 04/05/2021
            c7_p709_10_o_EditText.setText(modulo7.getC7_p709_10_o()); //Anthony M 04/05/2021
//            c7_p709_o_EditText.setText(modulo7.getC7_p709_o());

        }
        inicio();
        data.close();
    }

    private void inicio() {

        if(validar_P706()) {
            m7_p706_linearlayout.setVisibility(View.VISIBLE);
        }
        else {
            limpiar706();
            m7_p706_linearlayout.setVisibility(View.GONE);
        }


        if(validar_P707()) {
            m7_p707_linearlayout.setVisibility(View.VISIBLE);
        }
        else {
            limpiar707();
            m7_p707_linearlayout.setVisibility(View.GONE);
        }

        if(validar_P708()) {
            m7_p708_linearlayout.setVisibility(View.VISIBLE);
        }
        else {
            limpiar708();
            m7_p708_linearlayout.setVisibility(View.GONE);
        }

        if(validar_P709()) {
            m7_p709_linearlayout.setVisibility(View.VISIBLE);
        }
        else {
            limpiar709();
            m7_p709_linearlayout.setVisibility(View.GONE);
        }

    }

    private void limpiar709() {
        c7_p709_1_Checkbox.setChecked(false);
        c7_p709_2_Checkbox.setChecked(false);
        c7_p709_3_Checkbox.setChecked(false);
        c7_p709_4_Checkbox.setChecked(false);
        c7_p709_5_Checkbox.setChecked(false);
        c7_p709_6_Checkbox.setChecked(false);
        c7_p709_7_Checkbox.setChecked(false);
        c7_p709_8_Checkbox.setChecked(false);
        c7_p709_9_Checkbox.setChecked(false);
        c7_p709_10a_Checkbox.setChecked(false);
        c7_p709_10_Checkbox.setChecked(false);
        c7_p709_11_Checkbox.setChecked(false);

        c7_p709_10_o_EditText.setText("");
    }

    private void limpiar708() {
        c7_p708_1_RadioGroup.clearCheck();
        c7_p708_2_RadioGroup.clearCheck();
        c7_p708_3_RadioGroup.clearCheck();
    }

    private void limpiar707() {
        c7_p707_1_Checkbox.setChecked(false);
        c7_p707_2_Checkbox.setChecked(false);
        c7_p707_3_Checkbox.setChecked(false);
        c7_p707_4_Checkbox.setChecked(false);
        c7_p707_5_Checkbox.setChecked(false);
        c7_p707_6_Checkbox.setChecked(false);
        c7_p707_7_Checkbox.setChecked(false);
        c7_p707_8_Checkbox.setChecked(false);
        c7_p707_9_Checkbox.setChecked(false);
        c7_p707_8_o_EditText.setText("");

    }

    private void limpiar706() {
        c7_p706_1_Checkbox.setChecked(false);
        c7_p706_2_Checkbox.setChecked(false);
        c7_p706_3_Checkbox.setChecked(false);
        c7_p706_4_Checkbox.setChecked(false);
        c7_p706_5_Checkbox.setChecked(false);
        c7_p706_6_Checkbox.setChecked(false);

        c7_p706_8_o_EditText.setText("");

    }

    public boolean validar_P706(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 && p705.equals("1")){
            valido = true;
        }
        return valido;
    }

    public boolean validar_P707(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5){
            valido = true;
        }
        return valido;
    }

    public boolean validar_P708(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5){
            valido = true;
        }
        return valido;
    }
    public boolean validar_P709(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5){
            valido = true;
        }
        return valido;
    }

    @Override
    public void llenarVista() {
        Data data = new Data(context);
        data.open();
        POJOFragment pojoFragment = data.getFragmentsLayouts(idEncuestado);
        POJOLayout pojoLayout = data.getLayouts(idEncuestado);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p706,idEncuestado)) m7_p706_linearlayout.setVisibility(View.GONE);
        data.close();
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
    public void validarAchurar707(){
        llenarVariables();
        if(c7_p707_1.equals("1") || c7_p707_2.equals("1") || c7_p707_3.equals("1") ||
                c7_p707_4.equals("1") || c7_p707_5.equals("1") || c7_p707_6.equals("1") ||
                c7_p707_7.equals("1") || c7_p707_8.equals("1")){
            c7_p707_9_Checkbox.setChecked(false);c7_p707_9_Checkbox.setEnabled(false);
        }else {
            c7_p707_9_Checkbox.setEnabled(true);
        }
    }
    public void validarAchurar709(){
        llenarVariables();
        if(c7_p709_1.equals("1") || c7_p709_2.equals("1") || c7_p709_3.equals("1") ||
                c7_p709_4.equals("1") || c7_p709_5.equals("1") || c7_p709_6.equals("1") ||
                c7_p709_7.equals("1") || c7_p709_8.equals("1") || c7_p709_9.equals("1") ||
                c7_p709_10a.equals("1") || c7_p709_10.equals("1")){
            c7_p709_11_Checkbox.setChecked(false);c7_p709_11_Checkbox.setEnabled(false);
        }else {
            c7_p709_11_Checkbox.setEnabled(true);
        }
    }
}
