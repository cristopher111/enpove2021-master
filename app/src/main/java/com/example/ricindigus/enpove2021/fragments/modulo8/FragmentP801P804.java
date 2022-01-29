package com.example.ricindigus.enpove2021.fragments.modulo8;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo8;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP801P804 extends FragmentPagina {

    Context context;
    String idEncuestado;

    String idVivienda, idHogar, idInformante;

    Spinner informanteSpinner;

    //PREGUNTA 801
    RadioGroup c8_p801_RadioGroup;
    String c8_p801;
    LinearLayout m8_p801_linearlayout;

    //PREGUNTA 802
    RadioGroup c8_p802_RadioGroup_1,c8_p802_RadioGroup_2,c8_p802_RadioGroup_3;
    String c8_p802_1;
    String c8_p802_2;
    String c8_p802_3;
    LinearLayout m8_p802_linearlayout;

    //PREGUNTA 802 A
    Spinner c8_p802a_1_Spinner, c8_p802a_2_Spinner, c8_p802a_3_Spinner; //Anthony M 04/05/2021
    CheckBox c8_p802a_1_1_Checkbox,c8_p802a_1_2_Checkbox,c8_p802a_1_3_Checkbox,c8_p802a_1_4_Checkbox,c8_p802a_1_5_Checkbox,c8_p802a_1_6_Checkbox,
            c8_p802a_1_7_Checkbox,c8_p802a_1_8_Checkbox;
    CheckBox c8_p802a_2_1_Checkbox,c8_p802a_2_2_Checkbox,c8_p802a_2_3_Checkbox,c8_p802a_2_4_Checkbox,c8_p802a_2_5_Checkbox,c8_p802a_2_6_Checkbox,
            c8_p802a_2_7_Checkbox,c8_p802a_2_8_Checkbox;
    CheckBox c8_p802a_3_1_Checkbox,c8_p802a_3_2_Checkbox,c8_p802a_3_3_Checkbox,c8_p802a_3_4_Checkbox,c8_p802a_3_5_Checkbox,c8_p802a_3_6_Checkbox,
            c8_p802a_3_7_Checkbox,c8_p802a_3_8_Checkbox;
    EditText c8_p802a_1_o_EditText, c8_p802a_2_o_EditText, c8_p802a_3_o_EditText; //Anthony M 04/05/2021
    String  c8_p802a_1, c8_p802a_1_o, c8_p802a_2, c8_p802a_2_o, c8_p802a_3, c8_p802a_3_o; //Anthony M 04/05/2021
    String  c8_p802a_1_1, c8_p802a_1_2, c8_p802a_1_3, c8_p802a_1_4, c8_p802a_1_5, c8_p802a_1_6, c8_p802a_1_7, c8_p802a_1_8; //Anthony M 04/05/2021
    String  c8_p802a_2_1, c8_p802a_2_2, c8_p802a_2_3, c8_p802a_2_4, c8_p802a_2_5, c8_p802a_2_6, c8_p802a_2_7, c8_p802a_2_8; //Anthony M 04/05/2021
    String  c8_p802a_3_1, c8_p802a_3_2, c8_p802a_3_3, c8_p802a_3_4, c8_p802a_3_5, c8_p802a_3_6, c8_p802a_3_7, c8_p802a_3_8; //Anthony M 04/05/2021

    TextView tv802atitulo;
//    LinearLayout m8_p802a_linearlayout;

//    //PREGUNTA 803
//    RadioGroup   c8_p803_RadioGroup;
//    String c8_p803;
//    LinearLayout m8_p803_linearlayout;
//
//    //PREGUNTA 804
//    CheckBox c8_p804_1_CheckBox, c8_p804_2_CheckBox, c8_p804_3_CheckBox, c8_p804_4_CheckBox,
//            c8_p804_5_CheckBox, c8_p804_6_CheckBox, c8_p804_7_CheckBox, c8_p804_8_CheckBox,
//            c8_p804_9_CheckBox, c8_p804_10_CheckBox, c8_p804_11_CheckBox, c8_p804_12_CheckBox,
//            c8_p804_13_CheckBox,c8_p804_14_CheckBox;
//    EditText c8_p804_o_EditText;
//    private String c8_p804_1;
//    private String c8_p804_2;
//    private String c8_p804_3;
//    private String c8_p804_4;
//    private String c8_p804_5;
//    private String c8_p804_6;
//    private String c8_p804_7;
//    private String c8_p804_8;
//    private String c8_p804_9;
//    private String c8_p804_10;
//    private String c8_p804_11;
//    private String c8_p804_12;
//    private String c8_p804_13;
//    private String c8_p804_14;
//    private String c8_p804_o;
//    LinearLayout m8_p804_linearlayout;

    //nuevos linear
    LinearLayout linear802a1,linear802a2,linear802a3;

    String p212;


    @SuppressLint("ValidFragment")
    public FragmentP801P804( String idEncuestado, Context context) {
        this.context = context;
        this.idEncuestado = idEncuestado;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idVivienda = residente.getId_vivienda();
        idHogar = residente.getId_hogar();
        idInformante = "";
        p212 = data.getResidente(idEncuestado).getC2_p212();
        data.close();
    }

    public FragmentP801P804() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p801_p804, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

        c8_p801_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_801_radiogroup_C8_P801);

        c8_p802_RadioGroup_1 = (RadioGroup) rootView.findViewById(R.id.mod8_802_radiogroup_C8_P802_1);
        c8_p802_RadioGroup_2 = (RadioGroup) rootView.findViewById(R.id.mod8_802_radiogroup_C8_P802_2);
        c8_p802_RadioGroup_3 = (RadioGroup) rootView.findViewById(R.id.mod8_802_radiogroup_C8_P802_3);

        // c8_p802a_Spinner = (Spinner) rootView.findViewById(R.id.mod8_802a_spinner_C2_P802a);
        c8_p802a_1_Spinner = (Spinner) rootView.findViewById(R.id.mod8_802a_spinner_C2_P802a_1); //Anthony M 04/05/2021
        c8_p802a_2_Spinner = (Spinner) rootView.findViewById(R.id.mod8_802a_spinner_C2_P802a_2); //Anthony M 04/05/2021
        c8_p802a_3_Spinner = (Spinner) rootView.findViewById(R.id.mod8_802a_spinner_C2_P802a_3); //Anthony M 04/05/2021

        c8_p802a_1_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_1_1);
        c8_p802a_1_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_1_2);
        c8_p802a_1_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_1_3);
        c8_p802a_1_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_1_4);
        c8_p802a_1_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_1_5);
        c8_p802a_1_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_1_6);
        c8_p802a_1_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_1_7);
        c8_p802a_1_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_1_8);
        c8_p802a_1_o_EditText = (EditText) rootView.findViewById(R.id.mod8_802a_edittext_C8_P802a_1_O);

        c8_p802a_2_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_2_1);
        c8_p802a_2_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_2_2);
        c8_p802a_2_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_2_3);
        c8_p802a_2_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_2_4);
        c8_p802a_2_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_2_5);
        c8_p802a_2_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_2_6);
        c8_p802a_2_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_2_7);
        c8_p802a_2_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_2_8);
        c8_p802a_2_o_EditText = (EditText) rootView.findViewById(R.id.mod8_802a_edittext_C8_P802a_2_O);

        c8_p802a_3_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_3_1);
        c8_p802a_3_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_3_2);
        c8_p802a_3_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_3_3);
        c8_p802a_3_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_3_4);
        c8_p802a_3_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_3_5);
        c8_p802a_3_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_3_6);
        c8_p802a_3_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_3_7);
        c8_p802a_3_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod8_802a_checkbox_C8_P802a_3_8);
        c8_p802a_3_o_EditText = (EditText) rootView.findViewById(R.id.mod8_802a_edittext_C8_P802a_3_O);

        tv802atitulo = (TextView) rootView.findViewById(R.id.tv802atitulo);

//        c8_p803_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_803_radiogroup_C8_P803);
//
//        c8_p804_1_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_1);
//        c8_p804_2_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_2);
//        c8_p804_3_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_3);
//        c8_p804_4_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_4);
//        c8_p804_5_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_5);
//        c8_p804_6_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_6);
//        c8_p804_7_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_7);
//        c8_p804_8_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_8);
//        c8_p804_9_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_9);
//        c8_p804_10_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_10);
//        c8_p804_11_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_11);
//        c8_p804_12_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_12);
//        c8_p804_13_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_13);
//        c8_p804_14_CheckBox = (CheckBox) rootView.findViewById(R.id.mod8_804_checkbox_C8_P804_14);
//
//        c8_p804_o_EditText = (EditText) rootView.findViewById(R.id.mod8_804_edittext_C8_P804_O);

        m8_p801_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p801);
        m8_p802_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p802);
//        m8_p803_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p803);
//        m8_p804_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p804);


        //  m8_p802a_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p802a);

//        c8_p801_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_801_radiogroup_C8_P801);
//        // c8_p802_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_802_radiogroup_C8_P802);
//        c8_p803_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_803_radiogroup_C8_P803);
//        //   c8_p804_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod8_804_radiogroup_C8_P804);
//
//        m8_p801_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p801);
//        m8_p802_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p802);
//        m8_p803_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p803);
//        m8_p804_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m8_p804);
        //nuevos linear
        linear802a1= (LinearLayout) rootView.findViewById(R.id.linear802a1);
        linear802a2= (LinearLayout) rootView.findViewById(R.id.linear802a2);
        linear802a3= (LinearLayout) rootView.findViewById(R.id.linear802a3);

        return rootView;
    }


    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

//        configurarEditText(c8_p804_o_EditText,m8_p804_linearlayout,0,30);
        configurarEditText(c8_p802a_1_o_EditText,m8_p802_linearlayout,0,30);
        configurarEditText(c8_p802a_2_o_EditText,m8_p802_linearlayout,0,30);
        configurarEditText(c8_p802a_3_o_EditText,m8_p802_linearlayout,0,30);

//        controlarChecked(c8_p804_14_CheckBox,c8_p804_o_EditText);

        //Anthony M 04/05/2021
        c8_p802_RadioGroup_1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c8_p802_RadioGroup_1.findViewById(c8_p802_RadioGroup_1.getCheckedRadioButtonId()));
                if(pos==1){
                    linear802a1.setVisibility(View.VISIBLE);
                    //c8_p802a_1_Spinner.setEnabled(true);
//                    c8_p802a_1_o_EditText.setEnabled(true);
//                    c8_p802a_1_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    //c8_p802a_1_Spinner.setSelection(0);
                    //c8_p802a_1_Spinner.setEnabled(false);
                    limpiar802a_1();
                    //c8_p802a_1_o_EditText.setText("");
                    c8_p802a_1_o_EditText.setEnabled(false);
                    c8_p802a_1_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    linear802a1.setVisibility(View.GONE);
                }
            }
        });
        c8_p802_RadioGroup_2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c8_p802_RadioGroup_2.findViewById(c8_p802_RadioGroup_2.getCheckedRadioButtonId()));
                if(pos==1){
                    linear802a2.setVisibility(View.VISIBLE);
                    //c8_p802a_2_Spinner.setEnabled(true);
//                    c8_p802a_2_o_EditText.setEnabled(true);
//                    c8_p802a_2_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    //c8_p802a_2_Spinner.setSelection(0);
                    //c8_p802a_2_Spinner.setEnabled(false);
                    //c8_p802a_2_o_EditText.setText("");
                    limpiar802a_2();
                    c8_p802a_2_o_EditText.setEnabled(false);
                    c8_p802a_2_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    linear802a2.setVisibility(View.GONE);
                }
            }
        });

        c8_p802_RadioGroup_3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c8_p802_RadioGroup_3.findViewById(c8_p802_RadioGroup_3.getCheckedRadioButtonId()));
                if(pos==1){
                    //linear802a3.setVisibility(View.VISIBLE);
                    limpiar802a_3();
                    c8_p802a_3_o_EditText.setEnabled(false);
                    c8_p802a_3_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    linear802a3.setVisibility(View.GONE);

                }else{
                    //c8_p802a_3_Spinner.setSelection(0);
                    //c8_p802a_3_Spinner.setEnabled(false);
                    //c8_p802a_3_o_EditText.setText("");
                    limpiar802a_3();
                    c8_p802a_3_o_EditText.setEnabled(false);
                    c8_p802a_3_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    linear802a3.setVisibility(View.GONE);
//                    m8_p803_linearlayout.setVisibility(View.GONE);
//                    c8_p803_RadioGroup.clearCheck();
//                    m8_p804_linearlayout.setVisibility(View.VISIBLE);

                }
            }
        });

///////////COMENTADO 13/12/21
     /*   c8_p802a_1_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos == 8){
                    c8_p802a_1_o_EditText.setEnabled(true);
                    c8_p802a_1_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    c8_p802a_1_o_EditText.setEnabled(false);
                    c8_p802a_1_o_EditText.setText("");
                    c8_p802a_1_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });*/

        c8_p802a_1_8_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    c8_p802a_1_o_EditText.setEnabled(true);
                    c8_p802a_1_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else {
                    c8_p802a_1_o_EditText.setText("");
                    c8_p802a_1_o_EditText.setEnabled(false);
                    c8_p802a_1_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });

        /*
        c8_p802a_2_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos == 8){
                    c8_p802a_2_o_EditText.setEnabled(true);
                    c8_p802a_2_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    c8_p802a_2_o_EditText.setEnabled(false);
                    c8_p802a_2_o_EditText.setText("");
                    c8_p802a_2_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });*/

        c8_p802a_2_8_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    c8_p802a_2_o_EditText.setEnabled(true);
                    c8_p802a_2_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else {
                    c8_p802a_2_o_EditText.setText("");
                    c8_p802a_2_o_EditText.setEnabled(false);
                    c8_p802a_2_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });

        /*c8_p802a_3_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos == 8){
                    c8_p802a_3_o_EditText.setEnabled(true);
                    c8_p802a_3_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    c8_p802a_3_o_EditText.setEnabled(false);
                    c8_p802a_3_o_EditText.setText("");
                    c8_p802a_3_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });*/

        c8_p802a_3_8_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    c8_p802a_3_o_EditText.setEnabled(true);
                    c8_p802a_3_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else {
                    c8_p802a_3_o_EditText.setText("");
                    c8_p802a_3_o_EditText.setEnabled(false);
                    c8_p802a_3_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });

//        c8_p803_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
//                switch (seleccionado){
////                    SI P803 = 2 Entonces pase a P805_1
////                    SI P803 = 1 Entonces pase a P804_1
//
//                    case 1:
//                        m8_p804_linearlayout.setVisibility(View.VISIBLE);
//                   break;
//                   case 2:
//                        limpiar804();
//                       m8_p804_linearlayout.setVisibility(View.GONE);
//                       break;
//                }
//            }
//        });


        llenarVista();
        cargarDatos();
    }

//    private void limpiar804() {
//        c8_p804_1_CheckBox.setChecked(false);
//        c8_p804_2_CheckBox.setChecked(false);
//        c8_p804_3_CheckBox.setChecked(false);
//        c8_p804_4_CheckBox.setChecked(false);
//        c8_p804_5_CheckBox.setChecked(false);
//        c8_p804_6_CheckBox.setChecked(false);
//        c8_p804_7_CheckBox.setChecked(false);
//        c8_p804_8_CheckBox.setChecked(false);
//        c8_p804_9_CheckBox.setChecked(false);
//        c8_p804_10_CheckBox.setChecked(false);
//        c8_p804_11_CheckBox.setChecked(false);
//        c8_p804_12_CheckBox.setChecked(false);
//        c8_p804_13_CheckBox.setChecked(false);
//        c8_p804_14_CheckBox.setChecked(false);
//        c8_p804_o_EditText.setText("");
//    }
     private void limpiar802a_1() {
        c8_p802a_1_1_Checkbox.setChecked(false);
        c8_p802a_1_2_Checkbox.setChecked(false);
        c8_p802a_1_3_Checkbox.setChecked(false);
        c8_p802a_1_4_Checkbox.setChecked(false);
        c8_p802a_1_5_Checkbox.setChecked(false);
        c8_p802a_1_6_Checkbox.setChecked(false);
        c8_p802a_1_7_Checkbox.setChecked(false);
        c8_p802a_1_8_Checkbox.setChecked(false);
        c8_p802a_1_o_EditText.setText("");
    }
    private void limpiar802a_2() {
        c8_p802a_2_1_Checkbox.setChecked(false);
        c8_p802a_2_2_Checkbox.setChecked(false);
        c8_p802a_2_3_Checkbox.setChecked(false);
        c8_p802a_2_4_Checkbox.setChecked(false);
        c8_p802a_2_5_Checkbox.setChecked(false);
        c8_p802a_2_6_Checkbox.setChecked(false);
        c8_p802a_2_7_Checkbox.setChecked(false);
        c8_p802a_2_8_Checkbox.setChecked(false);
        c8_p802a_2_o_EditText.setText("");
    }
    private void limpiar802a_3() {
        c8_p802a_3_1_Checkbox.setChecked(false);
        c8_p802a_3_2_Checkbox.setChecked(false);
        c8_p802a_3_3_Checkbox.setChecked(false);
        c8_p802a_3_4_Checkbox.setChecked(false);
        c8_p802a_3_5_Checkbox.setChecked(false);
        c8_p802a_3_6_Checkbox.setChecked(false);
        c8_p802a_3_7_Checkbox.setChecked(false);
        c8_p802a_3_8_Checkbox.setChecked(false);
        c8_p802a_3_o_EditText.setText("");
    }

    @Override
    public void guardarDatos() {

        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo8_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo8_c8_p801,c8_p801);
        contentValues.put(SQLConstantes.modulo8_c8_p802_1,c8_p802_1);
        contentValues.put(SQLConstantes.modulo8_c8_p802_2,c8_p802_2);
        contentValues.put(SQLConstantes.modulo8_c8_p802_3,c8_p802_3);

        //contentValues.put(SQLConstantes.modulo8_c8_p802a_1,c8_p802a_1); //Anthony M 04/05/2021
        contentValues.put(SQLConstantes.modulo8_c8_p802a_1_1,c8_p802a_1_1);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_1_2,c8_p802a_1_2);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_1_3,c8_p802a_1_3);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_1_4,c8_p802a_1_4);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_1_5,c8_p802a_1_5);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_1_6,c8_p802a_1_6);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_1_7,c8_p802a_1_7);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_1_8,c8_p802a_1_8);
        //contentValues.put(SQLConstantes.modulo8_c8_p802a_2,c8_p802a_2); //Anthony M 04/05/2021
        contentValues.put(SQLConstantes.modulo8_c8_p802a_2_1,c8_p802a_2_1);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_2_2,c8_p802a_2_2);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_2_3,c8_p802a_2_3);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_2_4,c8_p802a_2_4);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_2_5,c8_p802a_2_5);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_2_6,c8_p802a_2_6);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_2_7,c8_p802a_2_7);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_2_8,c8_p802a_2_8);
        //contentValues.put(SQLConstantes.modulo8_c8_p802a_3,c8_p802a_3); //Anthony M 04/05/2021
        contentValues.put(SQLConstantes.modulo8_c8_p802a_3_1,c8_p802a_3_1);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_3_2,c8_p802a_3_2);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_3_3,c8_p802a_3_3);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_3_4,c8_p802a_3_4);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_3_5,c8_p802a_3_5);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_3_6,c8_p802a_3_6);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_3_7,c8_p802a_3_7);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_3_8,c8_p802a_3_8);
        contentValues.put(SQLConstantes.modulo8_c8_p802a_1_o,c8_p802a_1_o); //Anthony M 04/05/2021
        contentValues.put(SQLConstantes.modulo8_c8_p802a_2_o,c8_p802a_2_o); //Anthony M 04/05/2021
        contentValues.put(SQLConstantes.modulo8_c8_p802a_3_o,c8_p802a_3_o); //Anthony M 04/05/2021
//        contentValues.put(SQLConstantes.modulo8_c8_p803,c8_p803);
//
//        contentValues.put(SQLConstantes.modulo8_c8_p804_1,c8_p804_1);
//        contentValues.put(SQLConstantes.modulo8_c8_p804_2,c8_p804_2);
//        contentValues.put(SQLConstantes.modulo8_c8_p804_3,c8_p804_3);
//        contentValues.put(SQLConstantes.modulo8_c8_p804_4,c8_p804_4);
//        contentValues.put(SQLConstantes.modulo8_c8_p804_5,c8_p804_5);
//        contentValues.put(SQLConstantes.modulo8_c8_p804_6,c8_p804_6);
//        contentValues.put(SQLConstantes.modulo8_c8_p804_7,c8_p804_7);
//        contentValues.put(SQLConstantes.modulo8_c8_p804_8,c8_p804_8);
//        contentValues.put(SQLConstantes.modulo8_c8_p804_9,c8_p804_9);
//        contentValues.put(SQLConstantes.modulo8_c8_p804_10,c8_p804_10);
//        contentValues.put(SQLConstantes.modulo8_c8_p804_11,c8_p804_11);
//        contentValues.put(SQLConstantes.modulo8_c8_p804_12,c8_p804_12);
//        contentValues.put(SQLConstantes.modulo8_c8_p804_13,c8_p804_13);
//        contentValues.put(SQLConstantes.modulo8_c8_p804_14,c8_p804_14);
//        contentValues.put(SQLConstantes.modulo8_c8_p804_o,c8_p804_o);
        if(!data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo8 modulo8 = new Modulo8();
            modulo8.set_id(idEncuestado);
            modulo8.setIdHogar(idHogar);
            modulo8.setIdVivienda(idVivienda);
            data.insertarElemento(getNombreTabla(), modulo8.toValues());
        }
        data.actualizarElemento(getNombreTabla(), contentValues, idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp801p804,"1",idEncuestado);

        ocultarOtrosLayouts();

        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo8_COB800,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo8_COB800,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();
//        Data data = new Data(context);
//        data.open();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(SQLConstantes.modulo8_id_informante,idInformante);
//        contentValues.put(SQLConstantes.modulo8_c8_p801,c8_p801);
//        contentValues.put(SQLConstantes.modulo8_c8_p802,c8_p802);
//        contentValues.put(SQLConstantes.modulo8_c8_p803,c8_p803);
//        contentValues.put(SQLConstantes.modulo8_c8_p804,c8_p804);
//
//        if(!data.existeElemento(getNombreTabla(),idEncuestado)){
//            Modulo8 modulo8 = new Modulo8();
//            modulo8.set_id(idEncuestado);
//            modulo8.setIdHogar(idHogar);
//            modulo8.setIdVivienda(idVivienda);
//            data.insertarElemento(getNombreTabla(), modulo8.toValues());
//        }
//        data.actualizarElemento(getNombreTabla(), contentValues, idEncuestado);
//        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
//        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp801p804,"1",idEncuestado);
//        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
//        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo8_COB800,"1",idEncuestado);
//        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo8_COB800,"0",idEncuestado);
//        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
//        data.close();
    }

    private void ocultarOtrosLayouts() {

        Data data = new Data(context);
        data.open();
        if ( c8_p802_1.equals("2") && c8_p802_2.equals("2") && c8_p802_3.equals("2") ){
            ContentValues contentValues = new ContentValues();

            contentValues.put(SQLConstantes.modulo8_c8_p803,"");

            contentValues.put(SQLConstantes.modulo8_c8_p804_1,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_2,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_3,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_4,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_5,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_6,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_7,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_8,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_9,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_10,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_11,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_12,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_13,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_14,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_o,"");

            contentValues.put(SQLConstantes.modulo8_c8_p805_1,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_2,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_3,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_4,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_5,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_6,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_7,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_8,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_9,"");


            data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);

            contentValues = new ContentValues();
            contentValues.put(SQLConstantes.layouts_p803,"0");
            contentValues.put(SQLConstantes.layouts_p804,"0");
            contentValues.put(SQLConstantes.layouts_p805,"0");


            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
        }else  if ((c8_p802_1.equals("1") || c8_p802_2.equals("1")) && c8_p802_3.equals("2")){
            ContentValues contentValues = new ContentValues();

            contentValues.put(SQLConstantes.modulo8_c8_p803,"");

            contentValues.put(SQLConstantes.modulo8_c8_p804_1,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_2,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_3,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_4,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_5,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_6,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_7,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_8,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_9,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_10,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_11,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_12,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_13,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_14,"");
            contentValues.put(SQLConstantes.modulo8_c8_p804_o,"");

            contentValues.put(SQLConstantes.modulo8_c8_p805_1,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_2,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_3,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_4,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_5,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_6,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_7,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_8,"");
            contentValues.put(SQLConstantes.modulo8_c8_p805_9,"");


            data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);

            contentValues = new ContentValues();
            contentValues.put(SQLConstantes.layouts_p803,"0");
            contentValues.put(SQLConstantes.layouts_p804,"0");
            contentValues.put(SQLConstantes.layouts_p805,"0");


            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
        }else{
            ContentValues contentValues = new ContentValues();

            contentValues.put(SQLConstantes.layouts_p803,"1");
            contentValues.put(SQLConstantes.layouts_p804,"1");
            contentValues.put(SQLConstantes.layouts_p805,"1");
            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
            data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp805p808,"0",idEncuestado);


        }





        data.close();

//        //SI (P802_1=2 AND P802_2=2 AND P802_3=2) Entonces pase a P807
//        Data data = new Data(context);
//        data.open();
//
//        if (c8_p802_1.equals("2") && c8_p802_2.equals("2") && c8_p802_3.equals("2")){
//            ContentValues contentValues = new ContentValues();
//
//            contentValues.put(SQLConstantes.modulo8_c8_p805_1,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p805_2,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p805_3,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p805_4,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p805_5,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p805_6,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p805_7,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p805_8,"");
//            contentValues.put(SQLConstantes.modulo8_c8_p805_9,"");
//
//            contentValues.put(SQLConstantes.modulo8_c8_p806_1,"");
//
//            data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
//
//            contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.layouts_p805,"0");
//            contentValues.put(SQLConstantes.layouts_p806,"0");
//            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
//        }else{
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.layouts_p805,"1");
//            contentValues.put(SQLConstantes.layouts_p806,"1");
//            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
//            data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp805p808,"0",idEncuestado);
//        }
//        data.close();

    }

    @Override
    public void llenarVariables() {
        idInformante = obtener_Nresidente(informanteSpinner);
        c8_p801 = c8_p801_RadioGroup.indexOfChild(c8_p801_RadioGroup.findViewById(c8_p801_RadioGroup.getCheckedRadioButtonId()))+"";

        c8_p802_1 = c8_p802_RadioGroup_1.indexOfChild(c8_p802_RadioGroup_1.findViewById(c8_p802_RadioGroup_1.getCheckedRadioButtonId()))+"";
        c8_p802_2 = c8_p802_RadioGroup_2.indexOfChild(c8_p802_RadioGroup_2.findViewById(c8_p802_RadioGroup_2.getCheckedRadioButtonId()))+"";
        c8_p802_3 = c8_p802_RadioGroup_3.indexOfChild(c8_p802_RadioGroup_3.findViewById(c8_p802_RadioGroup_3.getCheckedRadioButtonId()))+"";

        //  c8_p802_a = c8_p802a_Spinner.getSelectedItemPosition();
        //Anthony M 04/05/2021
        /*c8_p802a_1 = c8_p802a_1_Spinner.getSelectedItemPosition()+"";
        c8_p802a_2 = c8_p802a_2_Spinner.getSelectedItemPosition()+"";
        c8_p802a_3 = c8_p802a_3_Spinner.getSelectedItemPosition()+"";*/

        if (c8_p802a_1_1_Checkbox.isChecked()) c8_p802a_1_1 = "1"; else c8_p802a_1_1 = "0";
        if (c8_p802a_1_2_Checkbox.isChecked()) c8_p802a_1_2 = "1"; else c8_p802a_1_2 = "0";
        if (c8_p802a_1_3_Checkbox.isChecked()) c8_p802a_1_3 = "1"; else c8_p802a_1_3 = "0";
        if (c8_p802a_1_4_Checkbox.isChecked()) c8_p802a_1_4 = "1"; else c8_p802a_1_4 = "0";
        if (c8_p802a_1_5_Checkbox.isChecked()) c8_p802a_1_5 = "1"; else c8_p802a_1_5 = "0";
        if (c8_p802a_1_6_Checkbox.isChecked()) c8_p802a_1_6 = "1"; else c8_p802a_1_6 = "0";
        if (c8_p802a_1_7_Checkbox.isChecked()) c8_p802a_1_7 = "1"; else c8_p802a_1_7 = "0";
        if (c8_p802a_1_8_Checkbox.isChecked()) c8_p802a_1_8 = "1"; else c8_p802a_1_8 = "0";
        c8_p802a_1_o = c8_p802a_1_o_EditText.getText().toString();


        if (c8_p802a_2_1_Checkbox.isChecked()) c8_p802a_2_1 = "1"; else c8_p802a_2_1 = "0";
        if (c8_p802a_2_2_Checkbox.isChecked()) c8_p802a_2_2 = "1"; else c8_p802a_2_2 = "0";
        if (c8_p802a_2_3_Checkbox.isChecked()) c8_p802a_2_3 = "1"; else c8_p802a_2_3 = "0";
        if (c8_p802a_2_4_Checkbox.isChecked()) c8_p802a_2_4 = "1"; else c8_p802a_2_4 = "0";
        if (c8_p802a_2_5_Checkbox.isChecked()) c8_p802a_2_5 = "1"; else c8_p802a_2_5 = "0";
        if (c8_p802a_2_6_Checkbox.isChecked()) c8_p802a_2_6 = "1"; else c8_p802a_2_6 = "0";
        if (c8_p802a_2_7_Checkbox.isChecked()) c8_p802a_2_7 = "1"; else c8_p802a_2_7 = "0";
        if (c8_p802a_2_8_Checkbox.isChecked()) c8_p802a_2_8 = "1"; else c8_p802a_2_8 = "0";
        c8_p802a_2_o = c8_p802a_2_o_EditText.getText().toString();


        if (c8_p802a_3_1_Checkbox.isChecked()) c8_p802a_3_1 = "1"; else c8_p802a_3_1 = "0";
        if (c8_p802a_3_2_Checkbox.isChecked()) c8_p802a_3_2 = "1"; else c8_p802a_3_2 = "0";
        if (c8_p802a_3_3_Checkbox.isChecked()) c8_p802a_3_3 = "1"; else c8_p802a_3_3 = "0";
        if (c8_p802a_3_4_Checkbox.isChecked()) c8_p802a_3_4 = "1"; else c8_p802a_3_4 = "0";
        if (c8_p802a_3_5_Checkbox.isChecked()) c8_p802a_3_5 = "1"; else c8_p802a_3_5 = "0";
        if (c8_p802a_3_6_Checkbox.isChecked()) c8_p802a_3_6 = "1"; else c8_p802a_3_6 = "0";
        if (c8_p802a_3_7_Checkbox.isChecked()) c8_p802a_3_7 = "1"; else c8_p802a_3_7 = "0";
        if (c8_p802a_3_8_Checkbox.isChecked()) c8_p802a_3_8 = "1"; else c8_p802a_3_8 = "0";
        c8_p802a_3_o = c8_p802a_3_o_EditText.getText().toString();






//        c8_p803 = c8_p803_RadioGroup.indexOfChild(c8_p803_RadioGroup.findViewById(c8_p803_RadioGroup.getCheckedRadioButtonId()))+"";
//
//        if(c8_p804_1_CheckBox.isChecked()) c8_p804_1 = "1"; else c8_p804_1 = "0";
//        if(c8_p804_2_CheckBox.isChecked()) c8_p804_2 = "1"; else c8_p804_2 = "0";
//        if(c8_p804_3_CheckBox.isChecked()) c8_p804_3 = "1"; else c8_p804_3 = "0";
//        if(c8_p804_4_CheckBox.isChecked()) c8_p804_4 = "1"; else c8_p804_4 = "0";
//        if(c8_p804_5_CheckBox.isChecked()) c8_p804_5 = "1"; else c8_p804_5 = "0";
//        if(c8_p804_6_CheckBox.isChecked()) c8_p804_6 = "1"; else c8_p804_6 = "0";
//        if(c8_p804_7_CheckBox.isChecked()) c8_p804_7 = "1"; else c8_p804_7 = "0";
//        if(c8_p804_8_CheckBox.isChecked()) c8_p804_8 = "1"; else c8_p804_8 = "0";
//        if(c8_p804_9_CheckBox.isChecked()) c8_p804_9 = "1"; else c8_p804_9 = "0";
//        if(c8_p804_10_CheckBox.isChecked()) c8_p804_10 = "1"; else c8_p804_10 = "0";
//        if(c8_p804_11_CheckBox.isChecked()) c8_p804_11 = "1"; else c8_p804_11 = "0";
//        if(c8_p804_12_CheckBox.isChecked()) c8_p804_12 = "1"; else c8_p804_12 = "0";
//        if(c8_p804_13_CheckBox.isChecked()) c8_p804_13 = "1"; else c8_p804_13 = "0";
//        if(c8_p804_14_CheckBox.isChecked()) c8_p804_14 = "1"; else c8_p804_14 = "0";
//        c8_p804_o = c8_p804_o_EditText.getText().toString();

//        c8_p801 = c8_p801_RadioGroup.indexOfChild(c8_p801_RadioGroup.findViewById(c8_p801_RadioGroup.getCheckedRadioButtonId()))+"";
//        c8_p802 = c8_p802_RadioGroup.indexOfChild(c8_p802_RadioGroup.findViewById(c8_p802_RadioGroup.getCheckedRadioButtonId()))+"";
//        c8_p803 = c8_p803_RadioGroup.indexOfChild(c8_p803_RadioGroup.findViewById(c8_p803_RadioGroup.getCheckedRadioButtonId()))+"";
//        c8_p804 = c8_p804_RadioGroup.indexOfChild(c8_p804_RadioGroup.findViewById(c8_p804_RadioGroup.getCheckedRadioButtonId()))+"";
    }

    @Override
    public void cargarDatos() {

        Data data =  new Data(context);
        data.open();
        if(data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo8 modulo8 = data.getModulo8(idEncuestado);
//            ArrayList<String> residentes = data.getListaSpinnerResidentesHogar(idHogar);
//            data.close();
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,residentes);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            informanteSpinner.setAdapter(adapter);
            ArrayList<String> informantes = data.getListaInformantesMayor18(idHogar);
            UtilsMethodsInputs.loadSpinner(informantes,informanteSpinner,context);
            if(modulo8.getIdInformante() != null && !modulo8.getIdInformante().equals("")) informanteSpinner.setSelection(obtener_posicion(modulo8.getIdInformante(),informanteSpinner));

            if(!modulo8.getC8_p801().equals("-1") && !modulo8.getC8_p801().equals(""))((RadioButton)c8_p801_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p801()))).setChecked(true);

            if(!modulo8.getC8_p802_1().equals("-1") && !modulo8.getC8_p802_1().equals(""))((RadioButton)c8_p802_RadioGroup_1.getChildAt(Integer.parseInt(modulo8.getC8_p802_1()))).setChecked(true);
            if(!modulo8.getC8_p802_2().equals("-1") && !modulo8.getC8_p802_2().equals(""))((RadioButton)c8_p802_RadioGroup_2.getChildAt(Integer.parseInt(modulo8.getC8_p802_2()))).setChecked(true);
            if(!modulo8.getC8_p802_3().equals("-1") && !modulo8.getC8_p802_3().equals(""))((RadioButton)c8_p802_RadioGroup_3.getChildAt(Integer.parseInt(modulo8.getC8_p802_3()))).setChecked(true);

            //Anthony M 04/05/2021
            /*if(modulo8.getC8_p802a_1() != null && !modulo8.getC8_p802a_1().equals("") && !modulo8.getC8_p802a_1().equals("0")) c8_p802a_1_Spinner.setSelection(Integer.parseInt(modulo8.getC8_p802a_1()));
            else {c8_p802a_1_Spinner.setEnabled(false);}
            if(modulo8.getC8_p802a_2() != null && !modulo8.getC8_p802a_2().equals("") && !modulo8.getC8_p802a_2().equals("0")) c8_p802a_2_Spinner.setSelection(Integer.parseInt(modulo8.getC8_p802a_2()));
            else {c8_p802a_2_Spinner.setEnabled(false);}
            if(modulo8.getC8_p802a_3() != null && !modulo8.getC8_p802a_3().equals("") && !modulo8.getC8_p802a_3().equals("0")) c8_p802a_3_Spinner.setSelection(Integer.parseInt(modulo8.getC8_p802a_3()));
            else {c8_p802a_3_Spinner.setEnabled(false);}*/


            if(modulo8.getC8_p802a_1_1().equals("1")) c8_p802a_1_1_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_1_2().equals("1")) c8_p802a_1_2_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_1_3().equals("1")) c8_p802a_1_3_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_1_4().equals("1")) c8_p802a_1_4_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_1_5().equals("1")) c8_p802a_1_5_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_1_6().equals("1")) c8_p802a_1_6_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_1_7().equals("1")) c8_p802a_1_7_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_1_8().equals("1")) c8_p802a_1_8_Checkbox.setChecked(true);
            c8_p802a_1_o_EditText.setText(modulo8.getC8_p802a_1_o());


            if(modulo8.getC8_p802a_2_1().equals("1")) c8_p802a_2_1_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_2_2().equals("1")) c8_p802a_2_2_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_2_3().equals("1")) c8_p802a_2_3_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_2_4().equals("1")) c8_p802a_2_4_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_2_5().equals("1")) c8_p802a_2_5_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_2_6().equals("1")) c8_p802a_2_6_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_2_7().equals("1")) c8_p802a_2_7_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_2_8().equals("1")) c8_p802a_2_8_Checkbox.setChecked(true);
            c8_p802a_2_o_EditText.setText(modulo8.getC8_p802a_2_o());


            if(modulo8.getC8_p802a_3_1().equals("1")) c8_p802a_3_1_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_3_2().equals("1")) c8_p802a_3_2_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_3_3().equals("1")) c8_p802a_3_3_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_3_4().equals("1")) c8_p802a_3_4_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_3_5().equals("1")) c8_p802a_3_5_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_3_6().equals("1")) c8_p802a_3_6_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_3_7().equals("1")) c8_p802a_3_7_Checkbox.setChecked(true);
            if(modulo8.getC8_p802a_3_8().equals("1")) c8_p802a_3_8_Checkbox.setChecked(true);
            c8_p802a_3_o_EditText.setText(modulo8.getC8_p802a_3_o());



//            if(!modulo8.getC8_p803().equals("-1") && !modulo8.getC8_p803().equals(""))((RadioButton)c8_p803_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p803()))).setChecked(true);
//
//            if(modulo8.getC8_p804_1().equals("1")) c8_p804_1_CheckBox.setChecked(true);
//            if(modulo8.getC8_p804_2().equals("1")) c8_p804_2_CheckBox.setChecked(true);
//            if(modulo8.getC8_p804_3().equals("1")) c8_p804_3_CheckBox.setChecked(true);
//            if(modulo8.getC8_p804_4().equals("1")) c8_p804_4_CheckBox.setChecked(true);
//            if(modulo8.getC8_p804_5().equals("1")) c8_p804_5_CheckBox.setChecked(true);
//            if(modulo8.getC8_p804_6().equals("1")) c8_p804_6_CheckBox.setChecked(true);
//            if(modulo8.getC8_p804_7().equals("1")) c8_p804_7_CheckBox.setChecked(true);
//            if(modulo8.getC8_p804_8().equals("1")) c8_p804_8_CheckBox.setChecked(true);
//            if(modulo8.getC8_p804_9().equals("1")) c8_p804_9_CheckBox.setChecked(true);
//            if(modulo8.getC8_p804_10().equals("1")) c8_p804_10_CheckBox.setChecked(true);
//            if(modulo8.getC8_p804_11().equals("1")) c8_p804_11_CheckBox.setChecked(true);
//            if(modulo8.getC8_p804_12().equals("1")) c8_p804_12_CheckBox.setChecked(true);
//            if(modulo8.getC8_p804_13().equals("1")) c8_p804_13_CheckBox.setChecked(true);
//            if(modulo8.getC8_p804_14().equals("1")) c8_p804_14_CheckBox.setChecked(true);
//            c8_p804_o_EditText.setText(modulo8.getC8_p804_o());

        }
        //ocupacion();
//        cognitiva();
        data.close();

//        Data data =  new Data(context);
//        data.open();
//        if(data.existeElemento(getNombreTabla(),idEncuestado)){
//            Modulo8 modulo8 = data.getModulo8(idEncuestado);
//            ArrayList<String> residentes = data.getListaSpinnerResidentesHogar(idHogar);
//            data.close();
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,residentes);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            informanteSpinner.setAdapter(adapter);
//            if(!modulo8.getIdInformante().equals(""))informanteSpinner.setSelection(Integer.parseInt(modulo8.getIdInformante()));
//            if(!modulo8.getC8_p801().equals("-1") && !modulo8.getC8_p801().equals(""))((RadioButton)c8_p801_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p801()))).setChecked(true);
//            if(!modulo8.getC8_p802().equals("-1") && !modulo8.getC8_p802().equals(""))((RadioButton)c8_p802_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p802()))).setChecked(true);
//            if(!modulo8.getC8_p803().equals("-1") && !modulo8.getC8_p803().equals(""))((RadioButton)c8_p803_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p803()))).setChecked(true);
//            if(!modulo8.getC8_p804().equals("-1") && !modulo8.getC8_p804().equals(""))((RadioButton)c8_p804_RadioGroup.getChildAt(Integer.parseInt(modulo8.getC8_p804()))).setChecked(true);
//        }
//        data.close();

    }



//    private void ocupacion() {
//        Data data = new Data(context);
//        data.open();
//        Residente residente = data.getResidente(idEncuestado);
//        idHogar = residente.getId_hogar();
//        idVivienda = residente.getId_vivienda();
//        idInformante = "";
//
//        Modulo8 modulo8 = data.getModulo8(idEncuestado);
//        String p802_1 = modulo8.getC8_p802_1();
//        String enunciado_p802 = tv802atitulo.getText().toString()+"";
//        enunciado_p802 = enunciado_p802.replace("OCUPACION", p802_1);
//        tv802atitulo.setText(enunciado_p802);
//        data.close();
//    }


    @Override
    public void llenarVista() {

    }

    @Override
    public boolean validarDatos() {
        llenarVariables();

        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        //PREGUNTA 801
        if (m8_p801_linearlayout.getVisibility() == View.VISIBLE){
            if (c8_p801.equals("-1")){mostrarMensaje("PREGUNTA 801: DEBE MARCAR UNA OPCIÓN"); return false;}
        }else{
            c8_p801 ="";
        }

        //PREGUNTA 802 Y 802A 1
        if (m8_p802_linearlayout.getVisibility() == View.VISIBLE){
            if (c8_p802_1.equals("-1")){
                mostrarMensaje("PREGUNTA 802: DEBE MARCAR UNA OPCIÓN 802-1");
                return false;
            }else{
                if (c8_p802_1.equals("1")){
                   /* if (c8_p802a_1_Spinner.getSelectedItemPosition() == 0){
                        mostrarMensaje("PREGUNTA 802A-1: DEBE SELECCIONAR QUIEN LLEVÓ A CABO EL MALTRATO FÍSICO");
                        return false;
                    } else if (c8_p802a_1_Spinner.getSelectedItemPosition() == 8){
                        if (c8_p802a_1_o.equals("")){
                            mostrarMensaje("PREGUNTA 802A-1: DEBE ESPECIFICAR OTRO");
                            return false;
                        }
                        if (c8_p802a_1_o.length()<3){
                            mostrarMensaje("ERROR  “P802A_1. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                        }
                    }*/
                    if (c8_p802a_1_1.equals("0") && c8_p802a_1_2.equals("0") &&c8_p802a_1_3.equals("0") &&
                            c8_p802a_1_4.equals("0") &&c8_p802a_1_5.equals("0") &&c8_p802a_1_6.equals("0") &&
                            c8_p802a_1_7.equals("0") &&c8_p802a_1_8.equals("0")){
                        mostrarMensaje("PREGUNTA 802A-1: DEBE SELECCIONAR UNA RESPUESTA");return false;
                    }else if (c8_p802a_1_8.equals("1")){
                        if(c8_p802a_1_o.equals("")){
                            mostrarMensaje("PREGUNTA 802A-1: DEBE ESPECIFICAR OTRO");
                            return false;
                        }
                        if (c8_p802a_1_o.length()<3){
                            mostrarMensaje("ERROR  “P802A_1. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                        }
                        }


                }
            }



        }else{
            c8_p802_1 ="";
        }

        //PREGUNTA 802 Y 802A 2
        if (m8_p802_linearlayout.getVisibility() == View.VISIBLE){
            if (c8_p802_2.equals("-1")){
                mostrarMensaje("PREGUNTA 802: DEBE MARCAR UNA OPCIÓN 802-2");
                return false;
            }else{
                if (c8_p802_2.equals("1")){
                 /*   if (c8_p802a_2_Spinner.getSelectedItemPosition() == 0){
                        mostrarMensaje("PREGUNTA 802A-2: DEBE SELECCIONAR QUIEN LLEVÓ A CABO EL MALTRATO PSICOLÓGICO");
                        return false;
                    } else if (c8_p802a_2_Spinner.getSelectedItemPosition() == 8){

                        if (c8_p802a_2_o.equals("")){
                            mostrarMensaje("PREGUNTA 802A-2: DEBE ESPECIFICAR OTRO");
                            return false;
                        }

                        if (c8_p802a_2_o.length()<3){
                            mostrarMensaje("ERROR  “P802A_2. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                        }
                    }

                  */
                    if (c8_p802a_2_1.equals("0") && c8_p802a_2_2.equals("0") &&c8_p802a_2_3.equals("0") &&
                            c8_p802a_2_4.equals("0") &&c8_p802a_2_5.equals("0") &&c8_p802a_2_6.equals("0") &&
                            c8_p802a_2_7.equals("0") &&c8_p802a_2_8.equals("0")){
                        mostrarMensaje("PREGUNTA 802A-2: DEBE SELECCIONAR UNA RESPUESTA");return false;
                    }else if (c8_p802a_2_8.equals("1")){
                        if(c8_p802a_2_o.equals("")){
                            mostrarMensaje("PREGUNTA 802A-2: DEBE ESPECIFICAR OTRO");
                            return false;
                        }
                        if (c8_p802a_2_o.length()<3){
                            mostrarMensaje("ERROR  “P802A_2. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                        }
                    }


                }
            }
        }else{
            c8_p802_2 ="";
        }


        //PREGUNTA 802 Y 802A 3
        if (m8_p802_linearlayout.getVisibility() == View.VISIBLE){
            if (c8_p802_3.equals("-1")){
                mostrarMensaje("PREGUNTA 802: DEBE MARCAR UNA OPCIÓN 802-3");
                return false;
            }else{
                if (c8_p802_3.equals("1")){
                   /* if (c8_p802a_3_Spinner.getSelectedItemPosition() == 0){
                        mostrarMensaje("PREGUNTA 802A-3: DEBE SELECCIONAR QUIEN LLEVÓ A CABO LA VIOLENCIA SEXUAL");
                        return false;
                    } else if (c8_p802a_3_Spinner.getSelectedItemPosition() == 8){
                        if (c8_p802a_3_o.equals("")){
                            mostrarMensaje("PREGUNTA 802A-3: DEBE ESPECIFICAR OTRO");
                            return false;
                        }

                        if (c8_p802a_3_o.length()<3){
                            mostrarMensaje("ERROR  “P802A_3. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                        }
                    }

*/
                    if (c8_p802a_3_1.equals("0") && c8_p802a_3_2.equals("0") &&c8_p802a_3_3.equals("0") &&
                            c8_p802a_3_4.equals("0") &&c8_p802a_3_5.equals("0") &&c8_p802a_3_6.equals("0") &&
                            c8_p802a_3_7.equals("0") &&c8_p802a_3_8.equals("0")){
                        mostrarMensaje("PREGUNTA 802A-3: DEBE SELECCIONAR UNA RESPUESTA");return false;
                    }else if (c8_p802a_3_8.equals("1")){
                        if(c8_p802a_3_o.equals("")){
                            mostrarMensaje("PREGUNTA 802A-3: DEBE ESPECIFICAR OTRO");
                            return false;
                        }
                        if (c8_p802a_3_o.length()<3){
                            mostrarMensaje("ERROR  “P802A-3. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                        }
                    }
                }
            }

//            //--- PREGUNTA COGNITIVA  p212 != null && p212.equal(1)--//
//            //UNIVERSO	:Todas las personas AND P205_A>=18 AND P212=1
//            //VERIFICACIÓN	: Sí (P802_3=2 OR P802A_3<>8 OR P802A_3_O<>VACIO)
            if( (p212.equals("1") && !(p212.equals(""))) &&  (c8_p802_3.equals("2") || (c8_p802a_3_Spinner.getSelectedItemPosition() != 8) || (!c8_p802a_3_o.equals("")) )){
                mostrarMensaje("“SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
            }

        }else{
            c8_p802_3 ="";
        }

//        //PREGUNTA 803
//        if (m8_p803_linearlayout.getVisibility() == View.VISIBLE){
//            if (c8_p803.equals("-1")){mostrarMensaje("PREGUNTA 803: DEBE MARCAR UNA OPCIÓN"); return false;}
//        }else{
//            c8_p803 ="";
//        }
//
//        //PREGUNTA 804
//        if (m8_p804_linearlayout.getVisibility() == View.VISIBLE){
//                if (c8_p804_1.equals("0") && c8_p804_2.equals("0") &&
//                    c8_p804_3.equals("0") && c8_p804_4.equals("0") &&
//                    c8_p804_5.equals("0") && c8_p804_6.equals("0") &&
//                    c8_p804_7.equals("0") && c8_p804_8.equals("0") &&
//                    c8_p804_9.equals("0") && c8_p804_10.equals("0") &&
//                    c8_p804_11.equals("0") && c8_p804_12.equals("0") &&
//                    c8_p804_13.equals("0") && c8_p804_14.equals("0")){
//                mostrarMensaje("PREGUNTA 804: DEBE SELECCIONAR ALGUNA OPCION");return false;
//            }
//            if (c8_p804_14.equals("1")){
//                if (c8_p804_o.trim().equals("")){ mostrarMensaje("PREGUNTA 804 - OPCION 14: DEBE ESPECIFICAR OTRO");return false;}
//            }
//        }else{
//            c8_p804_1 = "";
//            c8_p804_2 = "";
//            c8_p804_3 = "";
//            c8_p804_4 = "";
//            c8_p804_5 = "";
//            c8_p804_6= "";
//            c8_p804_7 = "";
//            c8_p804_8 = "";
//            c8_p804_9 = "";
//            c8_p804_10 = "";
//            c8_p804_11 = "";
//            c8_p804_12 = "";
//            c8_p804_13 = "";
//            c8_p804_14 = "";
//            c8_p804_o = "";
//        }


//        if (c8_p801.equals("-1")){mostrarMensaje("PREGUNTA 801: DEBE MARCAR UNA OPCIÓN"); return false;}
//        if (c8_p802.equals("-1")){mostrarMensaje("PREGUNTA 802: DEBE MARCAR UNA OPCIÓN"); return false;}
//        if (c8_p803.equals("-1")){mostrarMensaje("PREGUNTA 803: DEBE MARCAR UNA OPCIÓN"); return false;}
//        if (c8_p804.equals("-1")){mostrarMensaje("PREGUNTA 804: DEBE MARCAR UNA OPCIÓN"); return false;}

        ///ESTO SE VA A CAMBIAR DEL 808 AL 804----------->>>>>>>>>>>>>NO OLVIDAR
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


    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
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

}
