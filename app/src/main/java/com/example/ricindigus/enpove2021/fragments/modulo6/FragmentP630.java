package com.example.ricindigus.enpove2021.fragments.modulo6;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo6;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterMinMax;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethods;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP630 extends FragmentPagina {
    String idEncuestado;
    String idVivienda, idHogar, idInformante, id_informante="";
    Context context;

    Spinner informanteSpinner;
    RadioGroup c6_p635_RadioGroup, c6_p636_RadioGroup;
    EditText c6_p637_EditText;
    RadioGroup c6_p638_1_RadioGroup, c6_p638_2_RadioGroup, c6_p638_3_RadioGroup, c6_p638_4_RadioGroup;
    Spinner c6_p638_1_frec_Spinner, c6_p638_2_frec_Spinner, c6_p638_3_frec_Spinner, c6_p638_4_frec_Spinner;
    EditText c6_p638_1_monto_EditText, c6_p638_2_monto_EditText, c6_p638_3_monto_EditText, c6_p638_4_monto_EditText;
    RadioGroup c6_p639_1_RadioGroup, c6_p639_2_RadioGroup, c6_p639_1_med_RadioGroup, c6_p639_2_med_RadioGroup;
    Spinner c6_p639_1_frec_Spinner, c6_p639_1_monto_Spinner, c6_p639_2_frec_Spinner, c6_p639_2_monto_Spinner;
    EditText c6_p639_1_med_o_EditText, c6_p639_1_frec_o_EditText, c6_p639_2_med_o_EditText, c6_p639_2_frec_o_EditText;
    LinearLayout m6_p635_linearlayout, m6_p636_linearlayout, m6_p637_linearlayout, m6_p638_linearlayout, m6_p639_linearlayout;
    LinearLayout m6_p639_1_linearlayout, m6_p639_2_linearlayout;

    TextView tv638periodo;

    private String c6_p635;
    private String c6_p636;
    private String c6_p637;
    private String c6_p638_1;
    private String c6_p638_2;
    private String c6_p638_3;
    private String c6_p638_4;
    private String c6_p638_1_frec;
    private String c6_p638_2_frec;
    private String c6_p638_3_frec;
    private String c6_p638_4_frec;
    private String c6_p638_1_monto;
    private String c6_p638_2_monto;
    private String c6_p638_3_monto;
    private String c6_p638_4_monto;
    private String c6_p639_1;
    private String c6_p639_1_med;
    private String c6_p639_1_med_o;
    private String c6_p639_1_frec;
    private String c6_p639_1_frec_o;
    private String c6_p639_1_monto;
    private String c6_p639_2;
    private String c6_p639_2_med;
    private String c6_p639_2_med_o;
    private String c6_p639_2_frec;
    private String c6_p639_2_frec_o;
    private String c6_p639_2_monto;

    int edad=0;

    private String p629,p632_11,p630,p633;
    private int p631;
    private String p634_1,p634_2,p634_3,p634_4,p634_5,p634_6,p634_7;
    int p622_1,p622_2,p625;

    @SuppressLint("ValidFragment")
    public FragmentP630(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idHogar = residente.getId_hogar();
        idVivienda = residente.getId_vivienda();
        idInformante = "";
        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        p629 = data.getModulo6(idEncuestado).getC6_p629();
        p630 = data.getModulo6(idEncuestado).getC6_p630();
        try{
            p622_1 = Integer.parseInt(data.getModulo6(idEncuestado).getC6_p622_mon());
        }catch (Exception e){p622_1 = 0;}
        try{
            p622_2 = Integer.parseInt(data.getModulo6(idEncuestado).getC6_p622_esp());
        }catch (Exception e){p622_2 = 0;}
        try{
            p631 = Integer.parseInt(data.getModulo6(idEncuestado).getC6_p631());
        }catch (Exception e){p631 = 0;}
        p632_11 = data.getModulo6(idEncuestado).getC6_p632_11();
        p633 = data.getModulo6(idEncuestado).getC6_p633();
        p634_1 = data.getModulo6(idEncuestado).getC6_p634_1();
        p634_2 = data.getModulo6(idEncuestado).getC6_p634_2();
        p634_3 = data.getModulo6(idEncuestado).getC6_p634_3();
        p634_4 = data.getModulo6(idEncuestado).getC6_p634_4();
        p634_5 = data.getModulo6(idEncuestado).getC6_p634_5();
        p634_6 = data.getModulo6(idEncuestado).getC6_p634_6();
        p634_7 = data.getModulo6(idEncuestado).getC6_p634_7();
        try{
            p625 = Integer.parseInt(data.getModulo6(idEncuestado).getC6_p625());
        }catch (Exception e){p625 = 0;}
        data.close();
    }

    public FragmentP630() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p630, container, false);
        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);
        //Anthony M
        c6_p635_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_635_radiogroup_C6_P635);

        c6_p636_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_636_radiogroup_C6_P636);

        c6_p637_EditText = (EditText) rootView.findViewById(R.id.mod6_637_edittext_C6_P637);

        c6_p638_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_638_radiogroup_C6_P638_1);
        c6_p638_2_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_638_radiogroup_C6_P638_2);
        c6_p638_3_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_638_radiogroup_C6_P638_3);
        c6_p638_4_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_638_radiogroup_C6_P638_4);
        c6_p638_1_frec_Spinner = (Spinner) rootView.findViewById(R.id.mod6_638_spinner_C6_P638_1_F);
        c6_p638_2_frec_Spinner = (Spinner) rootView.findViewById(R.id.mod6_638_spinner_C6_P638_2_F);
        c6_p638_3_frec_Spinner = (Spinner) rootView.findViewById(R.id.mod6_638_spinner_C6_P638_3_F);
        c6_p638_4_frec_Spinner = (Spinner) rootView.findViewById(R.id.mod6_638_spinner_C6_P638_4_F);
        c6_p638_1_monto_EditText = (EditText) rootView.findViewById(R.id.mod6_638_edittext_C6_P638_1_M);
        c6_p638_2_monto_EditText = (EditText) rootView.findViewById(R.id.mod6_638_edittext_C6_P638_2_M);
        c6_p638_3_monto_EditText = (EditText) rootView.findViewById(R.id.mod6_638_edittext_C6_P638_3_M);
        c6_p638_4_monto_EditText = (EditText) rootView.findViewById(R.id.mod6_638_edittext_C6_P638_4_M);

        c6_p639_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_639_radiogroup_C6_P639_1);
        c6_p639_1_med_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_639_radiogroup_C6_P639_1_MED);
        c6_p639_1_med_o_EditText = (EditText) rootView.findViewById(R.id.mod6_639_edittext_C6_P639_1_O);
        c6_p639_1_frec_Spinner = (Spinner) rootView.findViewById(R.id.mod6_639_spinner_C6_P639_1_FREC);
        c6_p639_1_frec_o_EditText = (EditText) rootView.findViewById(R.id.mod6_639_edittext_C6_P639_1_FREC_O);
        c6_p639_1_monto_Spinner = (Spinner) rootView.findViewById(R.id.mod6_639_spinner_C6_P639_1_MONT);
        c6_p639_2_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_639_radiogroup_C6_P639_2);
        c6_p639_2_med_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_639_radiogroup_C6_P639_2_MED);
        c6_p639_2_med_o_EditText = (EditText) rootView.findViewById(R.id.mod6_639_edittext_C6_P639_2_O);
        c6_p639_2_frec_Spinner = (Spinner) rootView.findViewById(R.id.mod6_639_spinner_C6_P639_2_FREC);
        c6_p639_2_frec_o_EditText = (EditText) rootView.findViewById(R.id.mod6_639_edittext_C6_P639_2_FREC_O);
        c6_p639_2_monto_Spinner = (Spinner) rootView.findViewById(R.id.mod6_639_spinner_C6_P639_2_MONT);

        tv638periodo  = (TextView) rootView.findViewById(R.id.tv638periodo);

        m6_p635_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p635);
        m6_p636_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p636);
        m6_p637_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p637);
        m6_p638_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p638);
        m6_p639_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p639);
        m6_p639_1_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_639_1);
        m6_p639_2_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_639_2);

        return rootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String text638 = getString(R.string.modulo_6_p638, UtilsMethods.getPeriodoReferenciaMes(6));
        tv638periodo.setText(text638);


        c6_p638_1_monto_EditText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "9999")});
        c6_p638_2_monto_EditText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "9999")});
        c6_p638_3_monto_EditText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "9999")});
        c6_p638_4_monto_EditText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "9999")});

        configurarEditText(c6_p637_EditText,m6_p637_linearlayout,0,50);

//        configurarEditText(c6_p638_1_monto_EditText,m6_p638_linearlayout,2,4);
//        configurarEditText(c6_p638_2_monto_EditText,m6_p638_linearlayout,2,4);
//        configurarEditText(c6_p638_3_monto_EditText,m6_p638_linearlayout,2,4);
//        configurarEditText(c6_p638_4_monto_EditText,m6_p638_linearlayout,2,4);

        configurarEditText(c6_p639_1_med_o_EditText,m6_p639_1_linearlayout,0,50);
        configurarEditText(c6_p639_2_med_o_EditText,m6_p639_2_linearlayout,0,50);
        configurarEditText(c6_p639_1_frec_o_EditText,m6_p639_1_linearlayout,0,50);
        configurarEditText(c6_p639_2_frec_o_EditText,m6_p639_2_linearlayout,0,50);

        c6_p638_1_frec_Spinner.setSelection(0);
        c6_p638_1_frec_Spinner.setEnabled(false);
        c6_p638_2_frec_Spinner.setSelection(0);
        c6_p638_2_frec_Spinner.setEnabled(false);
        c6_p638_3_frec_Spinner.setSelection(0);
        c6_p638_3_frec_Spinner.setEnabled(false);
        c6_p638_4_frec_Spinner.setSelection(0);
        c6_p638_4_frec_Spinner.setEnabled(false);

        //PREGUNTA 635
        c6_p635_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(validar_P636()){
                    m6_p636_linearlayout.setVisibility(View.VISIBLE);
                }else{
                    limpiar636();
                    m6_p636_linearlayout.setVisibility(View.GONE);
                }
//                int pos = radioGroup.indexOfChild(c6_p635_RadioGroup.findViewById(c6_p635_RadioGroup.getCheckedRadioButtonId()));
//                if(pos > 0){
//                    m6_p636_linearlayout.setVisibility(View.VISIBLE);
//                }else{
//                    limpiar636();
//                    m6_p636_linearlayout.setVisibility(View.GONE);
//                }
            }
        });
        //PREGUNTA 636
        c6_p636_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
//                int pos = radioGroup.indexOfChild(c6_p636_RadioGroup.findViewById(c6_p636_RadioGroup.getCheckedRadioButtonId()));
//                SI P636=2 Entonces pase a P638_1
//                SI P636=1 Entonces pase a P637
                if(validar_P637()){
                    m6_p637_linearlayout.setVisibility(View.VISIBLE);
                }else {
                    limpiar637();
                    m6_p637_linearlayout.setVisibility(View.GONE);
                }

                if(validar_P638()){
                    m6_p638_linearlayout.setVisibility(View.VISIBLE);
                }else {
                    limpiar638();
                    m6_p638_linearlayout.setVisibility(View.GONE);
                }
//                if(pos==1){
//                     m6_p637_linearlayout.setVisibility(View.VISIBLE);
//                }else{
//                    limpiar637();
//                    m6_p637_linearlayout.setVisibility(View.GONE);
//                    if(validar_P638()){
//                        m6_p638_linearlayout.setVisibility(View.VISIBLE);
//                    }else {
//                        limpiar638();
//                        m6_p638_linearlayout.setVisibility(View.GONE);
//                    }
//                }
            }
        });
        c6_p637_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(validar_P638()){
                    m6_p638_linearlayout.setVisibility(View.VISIBLE);
                }else {
                    limpiar638();
                    m6_p638_linearlayout.setVisibility(View.GONE);
                }
//                int pos = c6_p636_RadioGroup.indexOfChild(c6_p636_RadioGroup.findViewById(c6_p636_RadioGroup.getCheckedRadioButtonId()));
//                if(edad >= 14 && (!c6_p637_EditText.getText().toString().equals("") || pos == 2)){
//                    m6_p638_linearlayout.setVisibility(View.VISIBLE);
//                }else {
//                    limpiar638();
//                    m6_p638_linearlayout.setVisibility(View.GONE);
//                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        //PREGUNTA 638
        c6_p638_1_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c6_p638_1_RadioGroup.findViewById(c6_p638_1_RadioGroup.getCheckedRadioButtonId()));
                if(pos==1){
                    c6_p638_1_frec_Spinner.setEnabled(true);
                    c6_p638_1_monto_EditText.setEnabled(true);
                    c6_p638_1_monto_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    c6_p638_1_frec_Spinner.setSelection(0);
                    c6_p638_1_frec_Spinner.setEnabled(false);
                    c6_p638_1_monto_EditText.setText("");
                    c6_p638_1_monto_EditText.setEnabled(false);
                    c6_p638_1_monto_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });
        c6_p638_2_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c6_p638_2_RadioGroup.findViewById(c6_p638_2_RadioGroup.getCheckedRadioButtonId()));
                if(pos==1){
                    c6_p638_2_frec_Spinner.setEnabled(true);
                    c6_p638_2_monto_EditText.setEnabled(true);
                    c6_p638_2_monto_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    c6_p638_2_frec_Spinner.setSelection(0);
                    c6_p638_2_frec_Spinner.setEnabled(false);
                    c6_p638_2_monto_EditText.setText("");
                    c6_p638_2_monto_EditText.setEnabled(false);
                    c6_p638_2_monto_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });
        c6_p638_3_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c6_p638_3_RadioGroup.findViewById(c6_p638_3_RadioGroup.getCheckedRadioButtonId()));
                if(pos==1){
                    c6_p638_3_frec_Spinner.setEnabled(true);
                    c6_p638_3_monto_EditText.setEnabled(true);
                    c6_p638_3_monto_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    c6_p638_3_frec_Spinner.setSelection(0);
                    c6_p638_3_frec_Spinner.setEnabled(false);
                    c6_p638_3_monto_EditText.setText("");
                    c6_p638_3_monto_EditText.setEnabled(false);
                    c6_p638_3_monto_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });
        c6_p638_4_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c6_p638_4_RadioGroup.findViewById(c6_p638_4_RadioGroup.getCheckedRadioButtonId()));
                if(pos==1){
                    c6_p638_4_frec_Spinner.setEnabled(true);
                    c6_p638_4_monto_EditText.setEnabled(true);
                    c6_p638_4_monto_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    c6_p638_4_frec_Spinner.setSelection(0);
                    c6_p638_4_frec_Spinner.setEnabled(false);
                    c6_p638_4_monto_EditText.setText("");
                    c6_p638_4_monto_EditText.setEnabled(false);
                    c6_p638_4_monto_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });

        //PREGUNTA 639
        c6_p639_1_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c6_p639_1_RadioGroup.findViewById(c6_p639_1_RadioGroup.getCheckedRadioButtonId()));
                if(pos==1){
                    m6_p639_1_linearlayout.setVisibility(View.VISIBLE);
                    c6_p639_1_med_RadioGroup.setEnabled(true);
//                    c6_p639_1_med_o_EditText.setEnabled(true);
                    c6_p639_1_frec_Spinner.setEnabled(true);
                    c6_p639_1_monto_Spinner.setEnabled(true);
                }else{
                    m6_p639_1_linearlayout.setVisibility(View.GONE);
                    c6_p639_1_med_RadioGroup.clearCheck();
                    c6_p639_1_med_RadioGroup.setEnabled(false);
                    c6_p639_1_med_o_EditText.setText("");
                    c6_p639_1_med_o_EditText.setEnabled(false);
                    c6_p639_1_frec_Spinner.setSelection(0);
                    c6_p639_1_frec_Spinner.setEnabled(false);
                    c6_p639_1_frec_o_EditText.setText("");
                    c6_p639_1_monto_Spinner.setSelection(0);
                    c6_p639_1_monto_Spinner.setEnabled(false);
                }
            }
        });
        c6_p639_2_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c6_p639_2_RadioGroup.findViewById(c6_p639_2_RadioGroup.getCheckedRadioButtonId()));
                if(pos==1){
                    m6_p639_2_linearlayout.setVisibility(View.VISIBLE);
                    c6_p639_2_med_RadioGroup.setEnabled(true);
//                    c6_p639_2_med_o_EditText.setEnabled(true);
                    c6_p639_2_frec_Spinner.setEnabled(true);
                    c6_p639_2_monto_Spinner.setEnabled(true);
                }else{
                    m6_p639_2_linearlayout.setVisibility(View.GONE);
                    c6_p639_2_med_RadioGroup.clearCheck();
                    c6_p639_2_med_RadioGroup.setEnabled(false);
                    c6_p639_2_med_o_EditText.setText("");
                    c6_p639_2_med_o_EditText.setEnabled(false);
                    c6_p639_2_frec_Spinner.setSelection(0);
                    c6_p639_2_frec_Spinner.setEnabled(false);
                    c6_p639_2_frec_o_EditText.setText("");
                    c6_p639_2_monto_Spinner.setSelection(0);
                    c6_p639_2_monto_Spinner.setEnabled(false);
                }
            }
        });
        c6_p639_1_med_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c6_p639_1_med_RadioGroup.findViewById(c6_p639_1_med_RadioGroup.getCheckedRadioButtonId()));
                if(pos==3){
                    c6_p639_1_med_o_EditText.setEnabled(true);
                    c6_p639_1_med_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    c6_p639_1_med_o_EditText.setText("");
                    c6_p639_1_med_o_EditText.setEnabled(false);
                    c6_p639_1_med_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });
        c6_p639_2_med_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c6_p639_2_med_RadioGroup.findViewById(c6_p639_2_med_RadioGroup.getCheckedRadioButtonId()));
                if(pos==3){
                    c6_p639_2_med_o_EditText.setEnabled(true);
                    c6_p639_2_med_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    c6_p639_2_med_o_EditText.setText("");
                    c6_p639_2_med_o_EditText.setEnabled(false);
                    c6_p639_2_med_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });
        c6_p639_1_frec_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 7){
                    c6_p639_1_frec_o_EditText.setEnabled(true);
                    c6_p639_1_frec_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else {
                    c6_p639_1_frec_o_EditText.setText("");
                    c6_p639_1_frec_o_EditText.setEnabled(false);
                    c6_p639_1_frec_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        c6_p639_2_frec_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 7){
                    c6_p639_2_frec_o_EditText.setEnabled(true);
                    c6_p639_2_frec_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else {
                    c6_p639_2_frec_o_EditText.setText("");
                    c6_p639_2_frec_o_EditText.setEnabled(false);
                    c6_p639_2_frec_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        llenarVista();
        cargarDatos();
    }

    private void limpiar637() {
        c6_p637_EditText.setText("");
    }

    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        //Anthony M
        contentValues.put(SQLConstantes.modulo6_c6_p635,c6_p635);
        contentValues.put(SQLConstantes.modulo6_c6_p636,c6_p636);
        contentValues.put(SQLConstantes.modulo6_c6_p637,c6_p637);
        contentValues.put(SQLConstantes.modulo6_c6_p638_1,c6_p638_1);
        contentValues.put(SQLConstantes.modulo6_c6_p638_1_frec,c6_p638_1_frec);
        contentValues.put(SQLConstantes.modulo6_c6_p638_1_monto,c6_p638_1_monto);
        contentValues.put(SQLConstantes.modulo6_c6_p638_2,c6_p638_2);
        contentValues.put(SQLConstantes.modulo6_c6_p638_2_frec,c6_p638_2_frec);
        contentValues.put(SQLConstantes.modulo6_c6_p638_2_monto,c6_p638_2_monto);
        contentValues.put(SQLConstantes.modulo6_c6_p638_3,c6_p638_3);
        contentValues.put(SQLConstantes.modulo6_c6_p638_3_frec,c6_p638_3_frec);
        contentValues.put(SQLConstantes.modulo6_c6_p638_3_monto,c6_p638_3_monto);
        contentValues.put(SQLConstantes.modulo6_c6_p638_4,c6_p638_4);
        contentValues.put(SQLConstantes.modulo6_c6_p638_4_frec,c6_p638_4_frec);
        contentValues.put(SQLConstantes.modulo6_c6_p638_4_monto,c6_p638_4_monto);
        contentValues.put(SQLConstantes.modulo6_c6_p639_1,c6_p639_1);
        contentValues.put(SQLConstantes.modulo6_c6_p639_1_med,c6_p639_1_med);
        contentValues.put(SQLConstantes.modulo6_c6_p639_1_med_o,c6_p639_1_med_o);
        contentValues.put(SQLConstantes.modulo6_c6_p639_1_frec,c6_p639_1_frec);
        contentValues.put(SQLConstantes.modulo6_c6_p639_1_frec_o,c6_p639_1_frec_o);
        contentValues.put(SQLConstantes.modulo6_c6_p639_1_monto,c6_p639_1_monto);
        contentValues.put(SQLConstantes.modulo6_c6_p639_2,c6_p639_2);
        contentValues.put(SQLConstantes.modulo6_c6_p639_2_med,c6_p639_2_med);
        contentValues.put(SQLConstantes.modulo6_c6_p639_2_med_o,c6_p639_2_med_o);
        contentValues.put(SQLConstantes.modulo6_c6_p639_2_frec,c6_p639_2_frec);
        contentValues.put(SQLConstantes.modulo6_c6_p639_2_frec_o,c6_p639_2_frec_o);
        contentValues.put(SQLConstantes.modulo6_c6_p639_2_monto,c6_p639_2_monto);


        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp630,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();
    }

    @Override
    public void llenarVariables() {
        idInformante = obtener_Nresidente(informanteSpinner);
        //Anthony M
        c6_p635 = c6_p635_RadioGroup.indexOfChild(c6_p635_RadioGroup.findViewById(c6_p635_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p636 = c6_p636_RadioGroup.indexOfChild(c6_p636_RadioGroup.findViewById(c6_p636_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p637 = c6_p637_EditText.getText().toString();
        c6_p638_1 = c6_p638_1_RadioGroup.indexOfChild(c6_p638_1_RadioGroup.findViewById(c6_p638_1_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p638_2 = c6_p638_2_RadioGroup.indexOfChild(c6_p638_2_RadioGroup.findViewById(c6_p638_2_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p638_3 = c6_p638_3_RadioGroup.indexOfChild(c6_p638_3_RadioGroup.findViewById(c6_p638_3_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p638_4 = c6_p638_4_RadioGroup.indexOfChild(c6_p638_4_RadioGroup.findViewById(c6_p638_4_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p638_1_frec = c6_p638_1_frec_Spinner.getSelectedItemPosition()+ "";
        c6_p638_2_frec = c6_p638_2_frec_Spinner.getSelectedItemPosition()+ "";
        c6_p638_3_frec = c6_p638_3_frec_Spinner.getSelectedItemPosition()+ "";
        c6_p638_4_frec = c6_p638_4_frec_Spinner.getSelectedItemPosition()+ "";
        c6_p638_1_monto = c6_p638_1_monto_EditText.getText().toString();
        c6_p638_2_monto = c6_p638_2_monto_EditText.getText().toString();
        c6_p638_3_monto = c6_p638_3_monto_EditText.getText().toString();
        c6_p638_4_monto = c6_p638_4_monto_EditText.getText().toString();
        c6_p639_1 = c6_p639_1_RadioGroup.indexOfChild(c6_p639_1_RadioGroup.findViewById(c6_p639_1_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p639_1_med = c6_p639_1_med_RadioGroup.indexOfChild(c6_p639_1_med_RadioGroup.findViewById(c6_p639_1_med_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p639_1_med_o = c6_p639_1_med_o_EditText.getText().toString();
        c6_p639_1_frec = c6_p639_1_frec_Spinner.getSelectedItemPosition()+ "";
        c6_p639_1_frec_o = c6_p639_1_frec_o_EditText.getText().toString();
        c6_p639_1_monto = c6_p639_1_monto_Spinner.getSelectedItemPosition()+ "";
        c6_p639_2 = c6_p639_2_RadioGroup.indexOfChild(c6_p639_2_RadioGroup.findViewById(c6_p639_2_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p639_2_med = c6_p639_2_med_RadioGroup.indexOfChild(c6_p639_2_med_RadioGroup.findViewById(c6_p639_2_med_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p639_2_med_o = c6_p639_2_med_o_EditText.getText().toString();
        c6_p639_2_frec = c6_p639_2_frec_Spinner.getSelectedItemPosition()+ "";
        c6_p639_2_frec_o = c6_p639_2_frec_o_EditText.getText().toString();
        c6_p639_2_monto = c6_p639_2_monto_Spinner.getSelectedItemPosition()+ "";
    }

    @Override
    public void cargarDatos() {
        Data data = new Data(context);
        data.open();
        if (data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo6 modulo6 = data.getModulo6(idEncuestado);
            ArrayList<String> informantes = data.getListaInformantesMayor12(modulo6.getIdHogar());
            UtilsMethodsInputs.loadSpinner(informantes,informanteSpinner,context);
            if(modulo6.getIdInformante() != null && !modulo6.getIdInformante().equals("")) informanteSpinner.setSelection(obtener_posicion(modulo6.getIdInformante(),informanteSpinner));

//            if(!modulo6.getC6_p630_1().equals("-1") && !modulo6.getC6_p630_1().equals(""))((RadioButton)c6_p630_1_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p630_1()))).setChecked(true);
//            if(!modulo6.getC6_p630_1med().equals("-1") && !modulo6.getC6_p630_1med().equals(""))((RadioButton)c6_p630_1med_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p630_1med()))).setChecked(true);
//            c6_p630_1o_EditText.setText(modulo6.getC6_p630_1o());
//            if(!modulo6.getC6_p630_1frec().equals("")) c6_p630_1frec_Spinner.setSelection(Integer.parseInt(modulo6.getC6_p630_1frec()));
//            c6_p630_1frec_o_EditText.setText(modulo6.getC6_p630_1frec_o());
//            if(!modulo6.getC6_p630_1mont().equals("")) c6_p630_1mont_Spinner.setSelection(Integer.parseInt(modulo6.getC6_p630_1mont()));
//            if(!modulo6.getC6_p630_2().equals("-1") && !modulo6.getC6_p630_2().equals(""))((RadioButton)c6_p630_2_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p630_2()))).setChecked(true);
//            if(!modulo6.getC6_p630_2med().equals("-1") && !modulo6.getC6_p630_2med().equals(""))((RadioButton)c6_p630_2med_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p630_2med()))).setChecked(true);
//            c6_p630_2o_EditText.setText(modulo6.getC6_p630_2o());
//            if(!modulo6.getC6_p630_2frec().equals("")) c6_p630_2frec_Spinner.setSelection(Integer.parseInt(modulo6.getC6_p630_2frec()));
//            c6_p630_2frec_o_EditText.setText(modulo6.getC6_p630_2frec_o());
//            if(!modulo6.getC6_p630_2mont().equals("")) c6_p630_2mont_Spinner.setSelection(Integer.parseInt(modulo6.getC6_p630_2mont()));

            //Anthony M
            if(modulo6.getC6_p635() != null && !modulo6.getC6_p635().equals("-1") && !modulo6.getC6_p635().equals(""))((RadioButton)c6_p635_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p635()))).setChecked(true);
            if(modulo6.getC6_p636() != null && !modulo6.getC6_p636().equals("-1") && !modulo6.getC6_p636().equals(""))((RadioButton)c6_p636_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p636()))).setChecked(true);
            c6_p637_EditText.setText(modulo6.getC6_p637());
            if(modulo6.getC6_p638_1() != null && !modulo6.getC6_p638_1().equals("-1") && !modulo6.getC6_p638_1().equals(""))((RadioButton)c6_p638_1_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p638_1()))).setChecked(true);
            if(modulo6.getC6_p638_2() != null && !modulo6.getC6_p638_2().equals("-1") && !modulo6.getC6_p638_2().equals(""))((RadioButton)c6_p638_2_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p638_2()))).setChecked(true);
            if(modulo6.getC6_p638_3() != null && !modulo6.getC6_p638_3().equals("-1") && !modulo6.getC6_p638_3().equals(""))((RadioButton)c6_p638_3_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p638_3()))).setChecked(true);
            if(modulo6.getC6_p638_4() != null && !modulo6.getC6_p638_4().equals("-1") && !modulo6.getC6_p638_4().equals(""))((RadioButton)c6_p638_4_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p638_4()))).setChecked(true);
            if(!modulo6.getC6_p638_1_frec().equals("")) c6_p638_1_frec_Spinner.setSelection(Integer.parseInt(modulo6.getC6_p638_1_frec()));
            if(!modulo6.getC6_p638_2_frec().equals("")) c6_p638_2_frec_Spinner.setSelection(Integer.parseInt(modulo6.getC6_p638_2_frec()));
            if(!modulo6.getC6_p638_3_frec().equals("")) c6_p638_3_frec_Spinner.setSelection(Integer.parseInt(modulo6.getC6_p638_3_frec()));
            if(!modulo6.getC6_p638_4_frec().equals("")) c6_p638_4_frec_Spinner.setSelection(Integer.parseInt(modulo6.getC6_p638_4_frec()));
            c6_p638_1_monto_EditText.setText(modulo6.getC6_p638_1_monto());
            c6_p638_2_monto_EditText.setText(modulo6.getC6_p638_2_monto());
            c6_p638_3_monto_EditText.setText(modulo6.getC6_p638_3_monto());
            c6_p638_4_monto_EditText.setText(modulo6.getC6_p638_4_monto());
            if(modulo6.getC6_p639_1() != null && !modulo6.getC6_p639_1().equals("-1") && !modulo6.getC6_p639_1().equals(""))((RadioButton)c6_p639_1_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p639_1()))).setChecked(true);
            if(modulo6.getC6_p639_1_med() != null && !modulo6.getC6_p639_1_med().equals("-1") && !modulo6.getC6_p639_1_med().equals(""))((RadioButton)c6_p639_1_med_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p639_1_med()))).setChecked(true);
            c6_p639_1_med_o_EditText.setText(modulo6.getC6_p639_1_med_o());
            if(!modulo6.getC6_p639_1_frec().equals("")) c6_p639_1_frec_Spinner.setSelection(Integer.parseInt(modulo6.getC6_p639_1_frec()));
            c6_p639_1_frec_o_EditText.setText(modulo6.getC6_p639_1_frec_o());
            if(!modulo6.getC6_p639_1_monto().equals("")) c6_p639_1_monto_Spinner.setSelection(Integer.parseInt(modulo6.getC6_p639_1_monto()));
            if(modulo6.getC6_p639_2() != null && !modulo6.getC6_p639_2().equals("-1") && !modulo6.getC6_p639_2().equals(""))((RadioButton)c6_p639_2_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p639_2()))).setChecked(true);
            if(modulo6.getC6_p639_2_med() != null && !modulo6.getC6_p639_2_med().equals("-1") && !modulo6.getC6_p639_2_med().equals(""))((RadioButton)c6_p639_2_med_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p639_2_med()))).setChecked(true);
            c6_p639_2_med_o_EditText.setText(modulo6.getC6_p639_2_med_o());
            if(!modulo6.getC6_p639_2_frec().equals("")) c6_p639_2_frec_Spinner.setSelection(Integer.parseInt(modulo6.getC6_p639_2_frec()));
            c6_p639_2_frec_o_EditText.setText(modulo6.getC6_p639_2_frec_o());
            if(!modulo6.getC6_p639_2_monto().equals("")) c6_p639_2_monto_Spinner.setSelection(Integer.parseInt(modulo6.getC6_p639_2_monto()));

        }
        inicio();
        data.close();
    }

    private void inicio() {
        Log.e("Fragment","estas en P630");
        //P635
        if(validar_P635()) {
            m6_p635_linearlayout.setVisibility(View.VISIBLE);
        }
        else {
            limpiar635();
            m6_p635_linearlayout.setVisibility(View.GONE);
        }
        //P636
        if(validar_P636()) {
            m6_p636_linearlayout.setVisibility(View.VISIBLE);
        }
        else {
            limpiar636();
            m6_p636_linearlayout.setVisibility(View.GONE);
        }
        //P637
        if(validar_P637()) {
            m6_p637_linearlayout.setVisibility(View.VISIBLE);
        }
        else {
            limpiar637();
            m6_p637_linearlayout.setVisibility(View.GONE);
        }
        //P638
        if(validar_P638()) {
            m6_p638_linearlayout.setVisibility(View.VISIBLE);
        }
        else {
            limpiar638();
            m6_p638_linearlayout.setVisibility(View.GONE);
        }
        //P639
        if(validar_P639()) {
            m6_p639_linearlayout.setVisibility(View.VISIBLE);
        }
        else {
            limpiar639();
            m6_p639_linearlayout.setVisibility(View.GONE);
        }
//        llenarVariables();
//        if(edad >= 5 ){
//            //FLUJO 87A - UNIVERSO P635
//            if(
//                    (p622_1 > 0 || p622_2 > 0) ||
//                            p629.equals("2") || p630.equals("2") ||
//                            (p631 > 0 && p631 <= 11) || p632_11.equals("1") || !p633.equals("") ||
//                            (p634_1.equals("1") || p634_2.equals("1") || p634_3.equals("1") || p634_4.equals("1") || p634_5.equals("1") || p634_6.equals("1"))
//            ){
//                m6_p635_linearlayout.setVisibility(View.VISIBLE);
//            }else {
//                limpiar635();
//                m6_p635_linearlayout.setVisibility(View.GONE);
//            }
//            //FLUJO 88 - UNIVERSO P636
//            if(Integer.parseInt(c6_p635) > 0){
//                m6_p636_linearlayout.setVisibility(View.VISIBLE);
//            }else {
//                limpiar636();
//                m6_p636_linearlayout.setVisibility(View.GONE);
//            }
//            //FLUJO 88A - UNIVERSO P637
//            if(c6_p636.equals("1")){
//                m6_p637_linearlayout.setVisibility(View.VISIBLE);
//            }else {
//                limpiar637();
//                m6_p637_linearlayout.setVisibility(View.GONE);
//            }
//        }else {
//            limpiar635();limpiar636();limpiar637();
//            m6_p635_linearlayout.setVisibility(View.GONE);
//            m6_p636_linearlayout.setVisibility(View.GONE);
//            m6_p637_linearlayout.setVisibility(View.GONE);
//        }
//
//        if(edad >= 14){
//            //FLUJO 89 - UNIVERSO P638
//            if(c6_p636.equals("2") || !c6_p637.equals("")){
//                m6_p638_linearlayout.setVisibility(View.VISIBLE);
//            }else {
//                limpiar638();
//                m6_p638_linearlayout.setVisibility(View.GONE);
//            }
//            //FLUJO 90 - UNIVERSO P639
//            m6_p639_linearlayout.setVisibility(View.VISIBLE);
//        }else {
//            limpiar638();limpiar639();
//            m6_p638_linearlayout.setVisibility(View.GONE);
//            m6_p639_linearlayout.setVisibility(View.GONE);
//        }
    }

    @Override
    public void llenarVista() {

    }

    @Override
    public boolean validarDatos() {
        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        if (m6_p635_linearlayout.getVisibility()== View.VISIBLE){
            if (c6_p635.equals("-1")) {
                mostrarMensaje("PREGUNTA 635: DEBE SELECCIONAR UNA OPCION");
                return false;
            }
        }else{
            c6_p635 = "";
        }

        if (m6_p636_linearlayout.getVisibility()== View.VISIBLE){
            if (c6_p636.equals("-1")) {
                mostrarMensaje("PREGUNTA 636: DEBE SELECCIONAR UNA OPCION");
                return false;
            }
        }else{
            c6_p636 = "";
        }

        //REGLA 0090 Si Length(trim(P637)) < 5
        if (m6_p637_linearlayout.getVisibility()== View.VISIBLE){
            if (c6_p637.equals("") || c6_p637.length()<5) {
                mostrarMensaje("ERROR “PREGUNTA 637 – DEBE DE TENER MÁS DE 4 CARACTERES”");
                return false;
            }
        }else{
            c6_p637 = "";
        }

        if (m6_p638_linearlayout.getVisibility()==View.VISIBLE) {
            if (c6_p638_1.equals("-1")) {
                mostrarMensaje("PREGUNTA 638-A: DEBE SELECCIONAR UNA OPCION");
                return false;
            }
            if (c6_p638_1.equals("1")) {
                if (c6_p638_1_frec.equals("0")) {
                    mostrarMensaje("PREGUNTA 638-A: DEBE SELECCIONAR FRECUENCIA");
                    return false;
                }
                if (c6_p638_1_monto.trim().equals("")) {
                    mostrarMensaje("PREGUNTA 638-A: DEBE INGRESAR MONTO");
                    return false;
                }
            }

            if (c6_p638_2.equals("-1")) {
                mostrarMensaje("PREGUNTA 638-B: DEBE SELECCIONAR UNA OPCION");
                return false;
            }
            if (c6_p638_2.equals("1")) {
                if (c6_p638_2_frec.equals("0")) {
                    mostrarMensaje("PREGUNTA 638-B: DEBE SELECCIONAR FRECUENCIA");
                    return false;
                }
                if (c6_p638_2_monto.trim().equals("")) {
                    mostrarMensaje("PREGUNTA 638-B: DEBE INGRESAR MONTO");
                    return false;
                }
            }

            if (c6_p638_3.equals("-1")) {
                mostrarMensaje("PREGUNTA 638-C: DEBE SELECCIONAR UNA OPCION");
                return false;
            }
            if (c6_p638_3.equals("1")) {
                if (c6_p638_3_frec.equals("0")) {
                    mostrarMensaje("PREGUNTA 638-C: DEBE SELECCIONAR FRECUENCIA");
                    return false;
                }
                if (c6_p638_3_monto.trim().equals("")) {
                    mostrarMensaje("PREGUNTA 638-C: DEBE INGRESAR MONTO");
                    return false;
                }
            }
            if (c6_p638_4.equals("-1")) {
                mostrarMensaje("PREGUNTA 638-D: DEBE SELECCIONAR UNA OPCION");
                return false;
            }

            if (c6_p638_4.equals("1")) {
//                if (c6_p629_o.trim().equals("")) {
//                    mostrarMensaje("PREGUNTA 629-D: DEBE ESPECIFICAR OTRO");
//                    return false;
//                }
                if (c6_p638_4_frec.equals("0")) {
                    mostrarMensaje("PREGUNTA 638-D: DEBE SELECCIONAR FRECUENCIA");
                    return false;
                }
                if (c6_p638_4_monto.trim().equals("")) {
                    mostrarMensaje("PREGUNTA 638-D: DEBE INGRESAR MONTO");
                    return false;
                }
            }
        }else{
            c6_p638_1 = ""; c6_p638_2 = ""; c6_p638_3 = ""; c6_p638_4 = "";
            c6_p638_1_frec="";c6_p638_2_frec="";c6_p638_3_frec="";c6_p638_4_frec="";
            c6_p638_1_monto="";c6_p638_2_monto="";c6_p638_3_monto="";c6_p638_4_monto="";
        }

        if(m6_p639_linearlayout.getVisibility() == View.VISIBLE){
            if (c6_p639_1.equals("-1")) { mostrarMensaje("PREGUNTA 639-A: DEBE SELECCIONAR UNA OPCION");return false;}
            if (c6_p639_1.equals("1")) {
                if(c6_p639_1_med.equals("-1")){ mostrarMensaje("PREGUNTA 639-A: DEBE ESCOGER UN MEDIO DE ENVIO");return false; }
                if (c6_p639_1_med.equals("3")) {
                    if (c6_p639_1_med_o.trim().equals("")){ mostrarMensaje("PREGUNTA 639-A: DEBE ESPECIFICAR OTRO MEDIO DE ENVÍO");return false; }
                    if (c6_p639_1_med_o.length() < 3) { mostrarMensaje("ERROR  “P639_1. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false; }
                }
                if (c6_p639_1_frec.equals("0")) { mostrarMensaje("PREGUNTA 639-A: DEBE SELECCIONAR FRECUENCIA");return false; }
                if (c6_p639_1_frec.equals("7") && c6_p639_1_frec_o.length()<3) { mostrarMensaje("PREGUNTA P639. Venezuela, Frecuencia – EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false; }
//                }
                if (c6_p639_1_monto.equals("0")) { mostrarMensaje("PREGUNTA 639-A: DEBE SELECCIONAR MONTO");return false; }
            }else c6_p639_1_med = "";

            if (c6_p639_2.equals("-1")) { mostrarMensaje("PREGUNTA 639-B: DEBE SELECCIONAR UNA OPCION");return false; }
            if (c6_p639_2.equals("1")) {
                if (c6_p639_2_med.equals("-1")) { mostrarMensaje("PREGUNTA 639-B: DEBE SELECCIONAR MEDIO DE ENVIO");return false; }
                if (c6_p639_2_med.equals("3")) {
                    if (c6_p639_2_med_o.trim().equals("")) { mostrarMensaje("PREGUNTA 639-B: DEBE ESPECIFICAR OTRO MEDIO DE ENVÍO");return false; }
                    if (c6_p639_2_med_o.length() < 3) { mostrarMensaje("ERROR  P639_2. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false; }
                }
                if (c6_p639_2_frec.equals("0")) { mostrarMensaje("PREGUNTA 639-B: DEBE SELECCIONAR FRECUENCIA");return false; }
                if (c6_p639_2_frec.equals("7") && c6_p639_2_frec_o.length()<3) { mostrarMensaje("PREGUNTA P639. Otro país, Frecuencia – EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false; }
                if(c6_p639_2.equals("2"))
                {mostrarMensaje("EN LA PREGUNTA 639_2 SELECCIONÓ LA OPCIÓN 639_2 (NO), DEBERÁ REGISTRAR OBSERVACIÓN DEL MÓDULO 6");}
                if (c6_p639_2_monto.equals("0")) { mostrarMensaje("PREGUNTA 639-B: DEBE SELECCIONAR MONTO");return false; }
            }else c6_p639_2_med = "";
        }else {
            c6_p639_1="";
            c6_p639_1_med="";
            c6_p639_1_med_o="";
            c6_p639_1_frec="";
            c6_p639_1_frec_o="";
            c6_p639_1_monto="";
            c6_p639_2="";
            c6_p639_2_med="";
            c6_p639_2_med_o="";
            c6_p639_2_frec="";
            c6_p639_2_frec_o="";
            c6_p639_2_monto="";
        }
        return true;
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablamodulo6;
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

//    public void limpiar_p630_1(){
//        c6_p630_1med_RadioGroup.clearCheck();
//        c6_p630_1o_EditText.setText("");
//        c6_p630_1frec_Spinner.setSelection(0);
//        c6_p630_1mont_Spinner.setSelection(0);
//    }
//
//    public void limpiar_p630_2(){
//        c6_p630_2med_RadioGroup.clearCheck();
//        c6_p630_2o_EditText.setText("");
//        c6_p630_2frec_Spinner.setSelection(0);
//        c6_p630_2mont_Spinner.setSelection(0);
//    }



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

    public void controlarChecked(CheckBox checkBox, final EditText editText){
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
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p601p604,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp601p604,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p605p608,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp605p608,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p609p612,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp609p612,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp613p617,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p618p621,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp618p621,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp622p625,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p626p629,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp626p629,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p630,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp630,idEncuestado).equals("0")) return false;
        data.close();
        return true;
    }
    private void limpiar635() {
        c6_p635_RadioGroup.clearCheck();
    }
    private void limpiar636() {
        c6_p636_RadioGroup.clearCheck();
    }
    private void limpiar638() {
        c6_p638_1_RadioGroup.clearCheck();
        c6_p638_2_RadioGroup.clearCheck();
        c6_p638_3_RadioGroup.clearCheck();
        c6_p638_4_RadioGroup.clearCheck();
        c6_p638_1_frec_Spinner.setSelection(0);
        c6_p638_2_frec_Spinner.setSelection(0);
        c6_p638_3_frec_Spinner.setSelection(0);
        c6_p638_4_frec_Spinner.setSelection(0);
        c6_p638_1_monto_EditText.setText("");
        c6_p638_2_monto_EditText.setText("");
        c6_p638_3_monto_EditText.setText("");
        c6_p638_4_monto_EditText.setText("");
    }
    private void limpiar639() {
        c6_p639_1_RadioGroup.clearCheck();
        c6_p639_1_med_RadioGroup.clearCheck();
        c6_p639_1_med_o_EditText.setText("");
        c6_p639_1_frec_Spinner.setSelection(0);
        c6_p639_1_frec_o_EditText.setText("");
        c6_p639_1_monto_Spinner.setSelection(0);

        c6_p639_2_RadioGroup.clearCheck();
        c6_p639_2_med_RadioGroup.clearCheck();
        c6_p639_2_med_o_EditText.setText("");
        c6_p639_2_frec_Spinner.setSelection(0);
        c6_p639_2_frec_o_EditText.setText("");
        c6_p639_2_monto_Spinner.setSelection(0);
    }


    public boolean validar_P635(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 //&&
//                (p622_1 > 0 || p622_2 > 0) &&
//                ( p629.equals("2")
//                        || p630.equals("2")
//                        || (p631 >= 1 && p631 <= 11)
//                        || p632_11.equals("1")
//                        || !p633.equals("")
//                        || p634_1.equals("1")
//                        || p634_2.equals("1")
//                        || p634_3.equals("1")
//                        || p634_4.equals("1")
//                        || p634_5.equals("1"))
        ){ //FLUJO 87 A - UNIVERSO
            valido = true;
        }
//        if(edad >= 5 &&
//                (
//                    (p622_1 > 0 || p622_2 > 0) ||
////                    p625 > 0 ||
//                    p629.equals("2") || p630.equals("2") ||
//                    (p631 > 0 && p631 <= 11) || p632_11.equals("1") || !p633.equals("") ||
//                    (p634_1.equals("1") || p634_2.equals("1") || p634_3.equals("1") || p634_4.equals("1") || p634_5.equals("1")
////                            || p634_6.equals("1")
//                    )
//                )
//        ){ //FLUJO 87 A - UNIVERSO
//            valido = true;
//        }
        return valido;
    }
    public boolean validar_P636(){
        boolean valido = false;
        llenarVariables();
//        if(edad >= 5 && !c6_p635.equals("") && !c6_p635.equals("-1")){ //FLUJO 88 - UNIVERSO
        if(edad >= 5){ //FLUJO 88 - UNIVERSO
            valido = true;
        }
        return valido;
    }
    public boolean validar_P637(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 && c6_p636.equals("1")){ //FLUJO 88 A - UNIVERSO
            valido = true;
        }
        return valido;
    }
    public boolean validar_P638(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 14 && (c6_p636.equals("2") || !c6_p637.equals(""))){ //FLUJO 89 - UNIVERSO
            valido = true;
        }
        return valido;
    }
    public boolean validar_P639(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 14){ //FLUJO 90 - UNIVERSO
            valido = true;
        }
        return valido;
    }
}
