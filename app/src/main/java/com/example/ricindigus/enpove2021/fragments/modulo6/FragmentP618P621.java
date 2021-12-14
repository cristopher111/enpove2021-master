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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo6;
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
public class FragmentP618P621 extends FragmentPagina {
    String idEncuestado;
    String idVivienda, idHogar, idInformante, id_informante="";
    Context context;

    Spinner informanteSpinner;

    //PREGUNTA 20
    RadioGroup c6_p620_RadioGroup;
    EditText c6_p620_o_EditText;
    private String c6_p620;
    private String c6_p620_o;
    LinearLayout m6_p620_linearlayout;


    //PREGUNTA 21
    RadioGroup c6_p621_RadioGroup;
    private String c6_p621;
    LinearLayout m6_p621_linearlayout;

    //PREGUNTA 22 ---14
    TextView c6_p622_TextView;
    EditText c6_p622_mon_EditText, c6_p622_esp_EditText;
    private String c6_p622_mon;
    private String c6_p622_esp;
    LinearLayout m6_p622_linearlayout;


    //PREGUNTA 23 ---15
    EditText c6_p623_mon_EditText, c6_p623_esp_EditText;
    CheckBox c6_p623_nas_Checkbox;
    private String c6_p623_mon;
    private String c6_p623_esp;
    private String c6_p623_nas;
    LinearLayout m6_p623_linearlayout;

    //PREGUNTA 24 ---16
    EditText c6_p624_mon_EditText, c6_p624_esp_EditText;
    CheckBox c6_p624_nas_Checkbox;
    CheckBox c6_p624_nas2_Checkbox;
    private String c6_p624_mon;
    private String c6_p624_esp;
    private String c6_p624_nas;
    private String c6_p624_nas2;
    LinearLayout m6_p624_linearlayout;

    private String p612,p615_t;


//    RadioGroup c6_p618_RadioGroup, c6_p619_RadioGroup;
//    EditText c6_p619_o_EditText;
//    RadioGroup c6_p620_RadioGroup, c6_p621_RadioGroup;
//    LinearLayout m6_p618_linearlayout, m6_p619_linearlayout, m6_p620_linearlayout, m6_p621_linearlayout;
//
//    private boolean c6_604=true;
//
//    private String c6_p618;
//    private String c6_p619;
//    private String c6_p619_o;
//    private String c6_p620;
//    private String c6_p621;

    int edad=0;
    private String p607,p608,p619,p629,p628,p627;
    int p18 =0;
    private String p630,p631,p632i,p633;
    private String p632_1,p632_2,p632_3,p632_4,p632_5,p632_6,p632_7,p632_8,p632_9,p632_10;

    private  int p622_1,p622_2;//NUEVOS HECTOR
    int p625=0;
    private String p212,p203;
    private String p417_1,p417_2,p417_3,p417_4,p417_5,p417_6,p417_7,p417_8,p417_9;

    @SuppressLint("ValidFragment")
    public FragmentP618P621(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idHogar = residente.getId_hogar();
        idVivienda = residente.getId_vivienda();
        idInformante = "";

        //NUEVOS HECTOR
        try{
            p622_1 =  Integer.parseInt(data.getModulo6(idEncuestado).getC6_p622_mon());
        }catch (Exception e){
            p622_1 = 0;
        }
        try{
            p622_2 =  Integer.parseInt(data.getModulo6(idEncuestado).getC6_p622_esp());
        }catch (Exception e){
            p622_2 = 0;
        }

        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        p607 = data.getModulo6(idEncuestado).getC6_p607();
        p608 = data.getModulo6(idEncuestado).getC6_p608();
        p612 = data.getModulo6(idEncuestado).getC6_p612();
        p615_t = data.getModulo6(idEncuestado).getC6_p615_t();
        p619 = data.getModulo6(idEncuestado).getC6_p619();
        p627 = data.getModulo6(idEncuestado).getC6_p627();
        p628 = data.getModulo6(idEncuestado).getC6_p628();
        p629 = data.getModulo6(idEncuestado).getC6_p629();
        p630 = data.getModulo6(idEncuestado).getC6_p630();
        p631 = data.getModulo6(idEncuestado).getC6_p631();
        p633 = data.getModulo6(idEncuestado).getC6_p633();
        p632i = data.getModulo6(idEncuestado).getC6_p632i();
        p632_1 = data.getModulo6(idEncuestado).getC6_p632_1();
        p632_2 = data.getModulo6(idEncuestado).getC6_p632_2();
        p632_3 = data.getModulo6(idEncuestado).getC6_p632_3();
        p632_4 = data.getModulo6(idEncuestado).getC6_p632_4();
        p632_5 = data.getModulo6(idEncuestado).getC6_p632_5();
        p632_6 = data.getModulo6(idEncuestado).getC6_p632_6();
        p632_7 = data.getModulo6(idEncuestado).getC6_p632_7();
        p632_8 = data.getModulo6(idEncuestado).getC6_p632_8();
        p632_9 = data.getModulo6(idEncuestado).getC6_p632_9();
        p632_10 = data.getModulo6(idEncuestado).getC6_p632_10();
        try{
            p18 = Integer.parseInt(data.getHogar(idHogar).getP18());
        }catch (Exception ignore){}
        try{
            p625 = Integer.parseInt(data.getModulo6(idEncuestado).getC6_p625());
        }catch (Exception ignore){}
        p212 = residente.getC2_p212();
        p203 = residente.getC2_p203();
        p417_1 = data.getModulo4(idEncuestado).getC4_p417_1();
        p417_2 = data.getModulo4(idEncuestado).getC4_p417_2();
        p417_3 = data.getModulo4(idEncuestado).getC4_p417_3();
        p417_4 = data.getModulo4(idEncuestado).getC4_p417_4();
        p417_5 = data.getModulo4(idEncuestado).getC4_p417_5();
        p417_6 = data.getModulo4(idEncuestado).getC4_p417_6();
        p417_7 = data.getModulo4(idEncuestado).getC4_p417_7();
        p417_8 = data.getModulo4(idEncuestado).getC4_p417_8();
        data.close();
    }

    public FragmentP618P621() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_p618_p621, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

        c6_p620_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_620_radiogroup_C6_P620);
        c6_p620_o_EditText = (EditText) rootView.findViewById(R.id.mod6_620_edittext_C6_P620_O);

        c6_p621_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_621_radiogroup_C6_P621);

        c6_p622_TextView = (TextView) rootView.findViewById(R.id.mod6_622_TextView_C6_P622);
        c6_p622_mon_EditText = (EditText) rootView.findViewById(R.id.mod6_622_edittext_C6_P622_MON);
        c6_p622_esp_EditText = (EditText) rootView.findViewById(R.id.mod6_622_edittext_C6_P622_ESP);

        c6_p623_mon_EditText = (EditText) rootView.findViewById(R.id.mod6_623_edittext_C6_P623_MON);
        c6_p623_esp_EditText = (EditText) rootView.findViewById(R.id.mod6_623_edittext_C6_P623_ESP);
        c6_p623_nas_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_623_checkbox_C6_P623_NAS);

        c6_p624_mon_EditText = (EditText) rootView.findViewById(R.id.mod6_624_edittext_C6_P624_MON);
        c6_p624_esp_EditText = (EditText) rootView.findViewById(R.id.mod6_624_edittext_C6_P624_ESP);
        c6_p624_nas_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_624_checkbox_C6_P624_NAS);
        c6_p624_nas2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_624_checkbox_C6_P624_NAS2);



        m6_p620_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p620);
        m6_p621_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p621);
        m6_p622_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p622);
        m6_p623_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p623);
        m6_p624_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p624);

//        c6_p618_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_619_radiogroup_C6_P619);
//
//        c6_p619_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_620_radiogroup_C6_P620);
//        c6_p619_o_EditText = (EditText) rootView.findViewById(R.id.mod6_620_edittext_C6_P620_O);
//
//        //  c6_p620_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_621_radiogroup_C6_P621);
//        c6_p621_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_622_radiogroup_C6_P622);
//
//        m6_p618_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p619);
//        m6_p619_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p620);
//        m6_p620_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p621);
//        m6_p621_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p622);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        configurarEditText(c6_p620_o_EditText,m6_p620_linearlayout,0,100);
//        c6_p620_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                controlarEspecifiqueRadio(group,checkedId,7,c6_p620_o_EditText);
//            }
//        });

        c6_p620_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                controlarEspecifiqueRadio(radioGroup,checkedId,7,c6_p620_o_EditText);
                int pos = radioGroup.indexOfChild(c6_p620_RadioGroup.findViewById(c6_p620_RadioGroup.getCheckedRadioButtonId()));
                ocupacion(pos);
//                if(pos != -1 ){
                if(validar_P621()){
                    m6_p621_linearlayout.setVisibility(View.VISIBLE);
                }else {
                    limpiarp621();
                    m6_p621_linearlayout.setVisibility(View.GONE);
                }
            }
        });

        c6_p621_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                int pos = radioGroup.indexOfChild(c6_p621_RadioGroup.findViewById(c6_p621_RadioGroup.getCheckedRadioButtonId()));
                ocupacion(pos);
//                if(pos == 5 ){
//                    c6_p622_mon_EditText.setText("0");
//                    c6_p622_esp_EditText.setText("0");
//                }else {
//                    c6_p622_mon_EditText.setText("");
//                    c6_p622_esp_EditText.setText("");
//                }
//                if(pos > 0){
                if(validar_P622()){
                    m6_p622_linearlayout.setVisibility(View.VISIBLE);
                }else {
                    limpiarp622();
                    m6_p622_linearlayout.setVisibility(View.GONE);
                }
                if(validar_P624()){
                    m6_p624_linearlayout.setVisibility(View.VISIBLE);
                }else {
                    limpiarp624();
                    m6_p624_linearlayout.setVisibility(View.GONE);
                }
            }
        });
        c6_p623_mon_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                int p624_1 = 0;
//                int p624_2 = 0;
//                if(!c6_p623_mon_EditText.getText().toString().equals("")) {p624_1 = Integer.parseInt(c6_p623_mon_EditText.getText().toString());}
//                if(!c6_p623_esp_EditText.getText().toString().equals("")) {p624_2 = Integer.parseInt(c6_p623_esp_EditText.getText().toString());}
//                if(p624_1 > 0 || p624_2 > 0){
//                    m6_p624_linearlayout.setVisibility(View.VISIBLE);
//                }else {
//                    limpiarp624();
//                    m6_p624_linearlayout.setVisibility(View.GONE);
//                }
                if(validar_P624()){
                    m6_p624_linearlayout.setVisibility(View.VISIBLE);
                }else {
                    limpiarp624();
                    m6_p624_linearlayout.setVisibility(View.GONE);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        c6_p623_esp_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                int p624_1 = 0;
//                int p624_2 = 0;
//                if(!c6_p623_mon_EditText.getText().toString().equals("")) {p624_1 = Integer.parseInt(c6_p623_mon_EditText.getText().toString());}
//                if(!c6_p623_esp_EditText.getText().toString().equals("")) {p624_2 = Integer.parseInt(c6_p623_esp_EditText.getText().toString());}
//                if(p624_1 > 0 || p624_2 > 0){
//                    m6_p624_linearlayout.setVisibility(View.VISIBLE);
//                }else {
//                    limpiarp624();
//                    m6_p624_linearlayout.setVisibility(View.GONE);
//                }
                if(validar_P624()){
                    m6_p624_linearlayout.setVisibility(View.VISIBLE);
                }else {
                    limpiarp624();
                    m6_p624_linearlayout.setVisibility(View.GONE);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        configurarEditText(c6_p622_esp_EditText,m6_p622_linearlayout,2,4);
        configurarEditText(c6_p622_mon_EditText,m6_p622_linearlayout,2,4);

        configurarEditText(c6_p623_esp_EditText,m6_p623_linearlayout,2,4);
        configurarEditText(c6_p623_mon_EditText,m6_p623_linearlayout,2,4);

        configurarEditText(c6_p624_esp_EditText,m6_p624_linearlayout,2,4);
        configurarEditText(c6_p624_mon_EditText,m6_p624_linearlayout,2,4);

        c6_p624_nas_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){

                    c6_p624_mon_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    c6_p624_esp_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    c6_p624_mon_EditText.setEnabled(false);
                    c6_p624_mon_EditText.setText("");
                    c6_p624_esp_EditText.setText("");
                    c6_p624_esp_EditText.setEnabled(false);
                    c6_p624_nas2_Checkbox.setEnabled(false);
                }else{
                    c6_p624_mon_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    c6_p624_esp_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    c6_p624_mon_EditText.setEnabled(true);
                    c6_p624_esp_EditText.setEnabled(true);
                    c6_p624_nas2_Checkbox.setEnabled(true);
                }
            }
        });

        c6_p624_nas2_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){

                    c6_p624_mon_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    c6_p624_esp_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    c6_p624_mon_EditText.setEnabled(false);
                    c6_p624_mon_EditText.setText("");
                    c6_p624_esp_EditText.setText("");
                    c6_p624_esp_EditText.setEnabled(false);
                    c6_p624_nas_Checkbox.setEnabled(false);
                }else{
                    c6_p624_mon_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    c6_p624_esp_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    c6_p624_mon_EditText.setEnabled(true);
                    c6_p624_esp_EditText.setEnabled(true);
                    c6_p624_nas_Checkbox.setEnabled(true);
                }
            }
        });


        c6_p623_nas_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){

                    c6_p623_mon_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    c6_p623_esp_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    c6_p623_mon_EditText.setEnabled(false);
                    c6_p623_mon_EditText.setText("");
                    c6_p623_esp_EditText.setText("");
                    c6_p623_esp_EditText.setEnabled(false);
                }else{
                    c6_p623_mon_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    c6_p623_esp_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    c6_p623_mon_EditText.setEnabled(true);
                    c6_p623_esp_EditText.setEnabled(true);
                }
            }
        });



        llenarVista();
        cargarDatos();
    }

    private void ocupacion(int pos) {
        String enunciado_p629 = c6_p622_TextView.getText().toString()+"";
        switch (pos){
            case 1:
                c6_p621="DIA";
                enunciado_p629 = enunciado_p629.replace("OCUPACION", c6_p621);
                enunciado_p629 = enunciado_p629.replace("SEMANA", c6_p621);
                enunciado_p629 = enunciado_p629.replace("QUINCENA", c6_p621);
                enunciado_p629 = enunciado_p629.replace("MES", c6_p621);
                break;
            case 2:
                c6_p621="SEMANA";
                enunciado_p629 = enunciado_p629.replace("OCUPACION", c6_p621);
                enunciado_p629 = enunciado_p629.replace("DIA", c6_p621);
                enunciado_p629 = enunciado_p629.replace("QUINCENA", c6_p621);
                enunciado_p629 = enunciado_p629.replace("MES", c6_p621);
                break;
            case 3:
                c6_p621="QUINCENA";
                enunciado_p629 = enunciado_p629.replace("OCUPACION", c6_p621);
                enunciado_p629 = enunciado_p629.replace("DIA", c6_p621);
                enunciado_p629 = enunciado_p629.replace("SEMANA", c6_p621);
                enunciado_p629 = enunciado_p629.replace("MES", c6_p621);
                break;
            case 4:
                c6_p621="MES";
                enunciado_p629 = enunciado_p629.replace("OCUPACION", c6_p621);
                enunciado_p629 = enunciado_p629.replace("DIA", c6_p621);
                enunciado_p629 = enunciado_p629.replace("SEMANA", c6_p621);
                enunciado_p629 = enunciado_p629.replace("QUINCENA", c6_p621);
                break;
            default:
                c6_p621="OCUPACION";
                enunciado_p629 = enunciado_p629.replace("DIA", c6_p621);
                enunciado_p629 = enunciado_p629.replace("SEMANA", c6_p621);
                enunciado_p629 = enunciado_p629.replace("QUINCENA", c6_p621);
                enunciado_p629 = enunciado_p629.replace("MES", c6_p621);
                break;
        }
        c6_p622_TextView.setText(enunciado_p629);
    }

    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo6_id_informante,idInformante);

        contentValues.put(SQLConstantes.modulo6_c6_p620,c6_p620);
        contentValues.put(SQLConstantes.modulo6_c6_p620_o,c6_p620_o);

        contentValues.put(SQLConstantes.modulo6_c6_p621,c6_p621);

        contentValues.put(SQLConstantes.modulo6_c6_p622_mon,c6_p622_mon);
        contentValues.put(SQLConstantes.modulo6_c6_p622_esp,c6_p622_esp);

        contentValues.put(SQLConstantes.modulo6_c6_p623_mon,c6_p623_mon);
        contentValues.put(SQLConstantes.modulo6_c6_p623_esp,c6_p623_esp);
        contentValues.put(SQLConstantes.modulo6_c6_p623_nas,c6_p623_nas);

        contentValues.put(SQLConstantes.modulo6_c6_p624_mon,c6_p624_mon);
        contentValues.put(SQLConstantes.modulo6_c6_p624_esp,c6_p624_esp);
        contentValues.put(SQLConstantes.modulo6_c6_p624_nas,c6_p624_nas);
        contentValues.put(SQLConstantes.modulo6_c6_p624_nas2,c6_p624_nas2);


        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp618p621,"1",idEncuestado);
        //ocultamos o mostramos preguntas o fragments

        ocultarOtrosLayouts(); //-->>>>>>>>>>>>>>>>>>>>>>>>>>>>ESTO LUEGO MIRARA PARA OCULTAR LOS LAYOUT

        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();


//        Data data = new Data(context);
//        data.open();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(SQLConstantes.modulo6_id_informante,idInformante);
//        contentValues.put(SQLConstantes.modulo6_c6_p618,c6_p618);
//        contentValues.put(SQLConstantes.modulo6_c6_p619,c6_p619);
//        contentValues.put(SQLConstantes.modulo6_c6_p619_o,c6_p619_o);
//        contentValues.put(SQLConstantes.modulo6_c6_p620,c6_p620);
//        contentValues.put(SQLConstantes.modulo6_c6_p621,c6_p621);
//        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
//        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
//        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp618p621,"1",idEncuestado);
//        //ocultamos o mostramos preguntas o fragments
//        ocultarOtrosLayouts();
//        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
//        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"1",idEncuestado);
//        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"0",idEncuestado);
//        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
//        data.close();
    }

    @Override
    public void llenarVariables() {

        idInformante = obtener_Nresidente(informanteSpinner);
//        String[] infor_id = (informanteSpinner.getItemAtPosition(informanteSpinner.getSelectedItemPosition()).toString()).split("-");
//        Log.e("averrr","infor_id"+infor_id);
//        id_informante = idHogar + "_" + infor_id[0];

        c6_p620 = c6_p620_RadioGroup.indexOfChild(c6_p620_RadioGroup.findViewById(c6_p620_RadioGroup.getCheckedRadioButtonId())) + "";
        c6_p620_o = c6_p620_o_EditText.getText().toString();

        c6_p621 = c6_p621_RadioGroup.indexOfChild(c6_p621_RadioGroup.findViewById(c6_p621_RadioGroup.getCheckedRadioButtonId())) + "";

        c6_p622_mon = c6_p622_mon_EditText.getText().toString();
        c6_p622_esp = c6_p622_esp_EditText.getText().toString();

        c6_p623_mon = c6_p623_mon_EditText.getText().toString();
        c6_p623_esp = c6_p623_esp_EditText.getText().toString();
        if(c6_p623_nas_Checkbox.isChecked()) c6_p623_nas = "1"; else c6_p623_nas = "0";

        c6_p624_mon = c6_p624_mon_EditText.getText().toString();
        c6_p624_esp = c6_p624_esp_EditText.getText().toString();
        if(c6_p624_nas_Checkbox.isChecked()) c6_p624_nas = "1"; else c6_p624_nas = "0";
        if(c6_p624_nas2_Checkbox.isChecked()) c6_p624_nas2 = "1"; else c6_p624_nas2 = "0";


//        idInformante = informanteSpinner.getSelectedItemPosition()+"";
//        String[] infor_id = (informanteSpinner.getItemAtPosition(informanteSpinner.getSelectedItemPosition()).toString()).split("-");
//        id_informante = idHogar + "_" + infor_id[0];
//        c6_p618 = c6_p618_RadioGroup.indexOfChild(c6_p618_RadioGroup.findViewById(c6_p618_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p619 = c6_p619_RadioGroup.indexOfChild(c6_p619_RadioGroup.findViewById(c6_p619_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p619_o = c6_p619_o_EditText.getText().toString();
//        c6_p620 = c6_p620_RadioGroup.indexOfChild(c6_p620_RadioGroup.findViewById(c6_p620_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p621 = c6_p621_RadioGroup.indexOfChild(c6_p621_RadioGroup.findViewById(c6_p621_RadioGroup.getCheckedRadioButtonId()))+"";
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

            if(!modulo6.getC6_p620().equals("-1") && !modulo6.getC6_p620().equals(""))((RadioButton)c6_p620_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p620()))).setChecked(true);
            c6_p620_o_EditText.setText(modulo6.getC6_p620_o());

            if(!modulo6.getC6_p621().equals("-1") && !modulo6.getC6_p621().equals(""))((RadioButton)c6_p621_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p621()))).setChecked(true);

            c6_p622_mon_EditText.setText(modulo6.getC6_p622_mon());
            c6_p622_esp_EditText.setText(modulo6.getC6_p622_esp());

            c6_p623_mon_EditText.setText(modulo6.getC6_p623_mon());
            c6_p623_esp_EditText.setText(modulo6.getC6_p623_esp());
            if(modulo6.getC6_p623_nas().equals("1")) c6_p623_nas_Checkbox.setChecked(true);

            c6_p624_mon_EditText.setText(modulo6.getC6_p624_mon());
            c6_p624_esp_EditText.setText(modulo6.getC6_p624_esp());
            if(modulo6.getC6_p624_nas().equals("1")) c6_p624_nas_Checkbox.setChecked(true);
            if(modulo6.getC6_p624_nas2().equals("1")) c6_p624_nas2_Checkbox.setChecked(true);


        }
        inicio();
        data.close();

//        Data data = new Data(context);
//        data.open();
//        if (data.existeElemento(getNombreTabla(),idEncuestado)){
//            Modulo6 modulo6 = data.getModulo6(idEncuestado);
//            ArrayList<String> residentes = data.getListaSpinnerResidentesHogar(modulo6.getIdHogar());
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,residentes);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            informanteSpinner.setAdapter(adapter);
//            if(!modulo6.getIdInformante().equals(""))informanteSpinner.setSelection(Integer.parseInt(modulo6.getIdInformante()));
//            if(!modulo6.getC6_p618().equals("-1") && !modulo6.getC6_p618().equals(""))((RadioButton)c6_p618_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p618()))).setChecked(true);
//            if(!modulo6.getC6_p619().equals("-1") && !modulo6.getC6_p619().equals(""))((RadioButton)c6_p619_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p619()))).setChecked(true);
//            c6_p619_o_EditText.setText(modulo6.getC6_p619_o());
//            if(!modulo6.getC6_p620().equals("-1") && !modulo6.getC6_p620().equals(""))((RadioButton)c6_p620_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p620()))).setChecked(true);
//            if(!modulo6.getC6_p621().equals("-1") && !modulo6.getC6_p621().equals(""))((RadioButton)c6_p621_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p621()))).setChecked(true);
//        }
//        data.close();
    }

    private void inicio() {
        Log.e("Fragment","estas en P618P621");
        //P620
        if(validar_P620()) m6_p620_linearlayout.setVisibility(View.VISIBLE);
        else {limpiarp620();m6_p620_linearlayout.setVisibility(View.GONE);}
        //P621
        if(validar_P621()) m6_p621_linearlayout.setVisibility(View.VISIBLE);
        else {limpiarp621();m6_p621_linearlayout.setVisibility(View.GONE);}
        //P622
        if(validar_P622()) m6_p622_linearlayout.setVisibility(View.VISIBLE);
        else {limpiarp622();m6_p622_linearlayout.setVisibility(View.GONE);}
        //P623
        if(validar_P623()) m6_p623_linearlayout.setVisibility(View.VISIBLE);
        else {limpiarp623();m6_p623_linearlayout.setVisibility(View.GONE);}
        //P624
        if(validar_P624()) m6_p624_linearlayout.setVisibility(View.VISIBLE);
        else {limpiarp624();m6_p624_linearlayout.setVisibility(View.GONE);}

//            if((p612.equals("1") || p612.equals("2")) && ( p623_1 > 0 || p623_2 > 0)){
//                m6_p624_linearlayout.setVisibility(View.VISIBLE);
//            }else {
//                limpiarp624();
//                m6_p624_linearlayout.setVisibility(View.GONE);
//            }
    }

    @Override
    public void llenarVista() {

    }

    @Override
    public boolean validarDatos() {
        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        //PREGUNTA 620
        if(m6_p620_linearlayout.getVisibility()==View.VISIBLE) {
            if(c6_p620.equals("-1")){mostrarMensaje("PREGUNTA 620 DEBE SELECCIONAR UNA OPCION");return false;}
            if(c6_p620.equals("7")){
                if(c6_p620_o.trim().equals("")){ mostrarMensaje("PREGUNTA 620-7: DEBE ESPECIFICAR OTRO");return false; }
                if(c6_p620_o.trim().length()<3){ mostrarMensaje("PREGUNTA 620-7: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false; }
            }
        }else{
            c6_p620 = "";
            c6_p620_o = "";
        }

        //PREGUNTA 621
        if(m6_p621_linearlayout.getVisibility()==View.VISIBLE) {
            if(c6_p621.equals("-1")){mostrarMensaje("PREGUNTA 621 DEBE SELECCIONAR UNA OPCION");return false;}
        }else{
            c6_p621 = "";
        }

        //PREGUNTA 622
        if(m6_p622_linearlayout.getVisibility()==View.VISIBLE){
            if(c6_p622_mon.trim().equals("") || c6_p622_esp.trim().equals("")) //REGLA 80   //ACA CAMBIE LA REGLA POR UN "OR" ANTES ESTABA UN "&&"
            {mostrarMensaje("ERROR “DEBE REGISTRAR INFORMACIÓN EN PREGUNTA 622”");return false;}
//            if(c6_p622_mon.trim().equals("")){
//                mostrarMensaje("PREGUNTA 622 - DINERO: DEBE ESPECIFICAR");
//                return false;
//            }
//            if(c6_p622_esp.trim().equals("")){
//                mostrarMensaje("PREGUNTA 622 - ESPECIE: DEBE ESPECIFICAR");
//                return false;
//            }

            if(c6_p622_mon.trim().equals("0") && c6_p622_esp.trim().equals("0"))  //AGREGADO 17/11/21
            {mostrarMensaje("EL INGRESO TOTAL NO PUEDE SER CERO EN PREGUNTA 622”");return false;}

            //REGLA 0081 modificada el dia 23 05 2021 a las 7 pm el dia domingo
           /* if ( !(p612.equals("8")) && c6_p622_mon.equals("0") && c6_p622_esp.equals("0")){
                mostrarMensaje("ERROR “EL INGRESO TOTAL NO PUEDE SER CERO EN PREGUNTA 622”");return false;
            }*/

        }else{
            c6_p622_mon = "";c6_p622_esp = "";
        }

        //PREGUNTA 623
        if(m6_p623_linearlayout.getVisibility()==View.VISIBLE){
            if(c6_p623_nas.equals("0")) {
                if (c6_p623_mon.trim().equals("") || c6_p623_esp.trim().equals("")) //REGLA 81
                {
                    mostrarMensaje("ERROR “DEBE REGISTRAR INFORMACIÓN EN PREGUNTA 623”");
                    return false;
                }
                //Anthony M. 03/06/2021
//            if(!c6_p623_mon.equals("") && !c6_p623_mon.equals("0") && c6_p623_esp.equals("") )
//            { mostrarMensaje("ERROR: DEBE INGRESAR EL MONTO EN ESPECIE");return false; }
//            if(!c6_p623_esp.equals("") && !c6_p623_esp.equals("0") && c6_p623_mon.equals(""))
//            { mostrarMensaje("ERROR: DEBE INGRESAR EL MONTO EN DINERO");return false; }
//            if(c6_p623_mon.trim().equals("")){ mostrarMensaje("PREGUNTA 623 - DINERO: DEBE ESPECIFICAR");return false; }
//            if(c6_p623_esp.trim().equals("")){ mostrarMensaje("PREGUNTA 623 - ESPECIE: DEBE ESPECIFICAR");return false; }

                //REGLA 0082
                if (c6_p623_mon.trim().equals("0") && c6_p623_esp.trim().equals("0")) {
                    mostrarMensaje("ERROR “LA GANANCIA NETA DEL MES ANTERIOR NO PUEDE SER CERO EN PREGUNTA 623”");
                    return false;
                }
            }

        }else{
            c6_p623_mon = ""; c6_p623_esp = ""; c6_p623_nas = "";
        }

        //PREGUNTA 624
        if(m6_p624_linearlayout.getVisibility()==View.VISIBLE){
            if(c6_p624_nas.equals("0") && c6_p624_nas2.equals("0")  ){
                if( c6_p624_mon.trim().equals("") && c6_p624_esp.trim().equals("") ) //REGLA 82
                { mostrarMensaje("ERROR “DEBE REGISTRAR INFORMACIÓN EN PREGUNTA 624”");return false; }

                if( c6_p624_mon.trim().equals("0") && c6_p624_esp.trim().equals("0") ) //AGREGADO 17/11/21
                { mostrarMensaje("ERROR “EL INGRESO TOTAL NO PUEDE SER CERO EN PREGUNTA 624");return false; }
                /*if(c6_p624_mon.trim().equals("0") && c6_p624_esp.trim().equals("0"))
                { mostrarMensaje("ERROR “EL INGRESO DEL MES ANTERIOR NO PUEDE SER CERO EN PREGUNTA 624”");return false; }*/
                //Anthony M. 03/06/2021
                if(!c6_p624_mon.equals("") && !c6_p624_mon.equals("0") && c6_p624_esp.equals("") )
                { mostrarMensaje("ERROR: DEBE INGRESAR EL MONTO EN ESPECIE");return false; }
                if(!c6_p624_esp.equals("") && !c6_p624_esp.equals("0") && c6_p624_mon.equals(""))
                { mostrarMensaje("ERROR: DEBE INGRESAR EL MONTO EN DINERO");return false; }
                if(c6_p624_mon.trim().equals("") && c6_p624_esp.trim().equals("0"))
                { mostrarMensaje("ERROR “EL INGRESO DEL MES ANTERIOR NO PUEDE SER CERO EN PREGUNTA 624”");return false; }
                if(c6_p624_mon.trim().equals("0") && c6_p624_esp.trim().equals(""))
                { mostrarMensaje("ERROR “EL INGRESO DEL MES ANTERIOR NO PUEDE SER CERO EN PREGUNTA 624”");return false; }
            }else{
                if(!c6_p624_mon.trim().equals("0") && !c6_p624_esp.trim().equals("0") && !c6_p624_mon.trim().equals("") && !c6_p624_esp.trim().equals(""))
                { mostrarMensaje("ERROR “NO TUVO ACTIVIDAD SECUNDARIA Y TIENE INGRESO DEL MES ANTERIOR EN PREGUNTA 624”");return false; }
            }

            //COGNITIVA
            int suma = 0;
            try{
                suma = Integer.parseInt(p417_1)+Integer.parseInt(p417_2)+Integer.parseInt(p417_3)+Integer.parseInt(p417_4)+Integer.parseInt(p417_5)+
                        Integer.parseInt(p417_6)+Integer.parseInt(p417_7)+Integer.parseInt(p417_8)+Integer.parseInt(p417_9);
            }catch (Exception ignore){}
            if(p212.equals("1") && !p203.equals("1") && suma > 0)
            { mostrarMensaje("“SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS”");}

            //REGLA 0083
//            if ((Integer.parseInt(c6_p624_mon) == 0) || (Integer.parseInt(c6_p624_esp)==0) ){
//                mostrarMensaje("ERROR “EL INGRESO DEL MES ANTERIOR NO PUEDE SER CERO EN PREGUNTA 624”");return false;
//            }


        }else{ c6_p624_nas2=""; c6_p624_nas=""; c6_p624_mon = ""; c6_p624_esp =""; }



//        llenarVariables();
//        if(idInformante.equals("0")) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}
//
//        if(!id_informante.equals(idEncuestado) && edad>=12){mostrarMensaje("NÚMERO INFORMANTE: NO ES EL MISMO QUE ESTA SIENDO ENTREVISTADO");return false;}
//
//        if(c6_p618.equals("-1")){ mostrarMensaje("PREGUNTA 618: DEBE SELECCIONAR UNA OPCION");return false;}
//        if(m6_p619_linearlayout.getVisibility()==View.VISIBLE) {
//            if(c6_p619.equals("-1")){mostrarMensaje("PREGUNTA 619-1: DEBE SELECCIONAR UNA OPCION");return false;}
//            if(c6_p619.equals("9")){
//                if(c6_p619_o.trim().equals("")){ mostrarMensaje("PREGUNTA 619 - OPCION 9: DEBE ESPECIFICAR OTRO");return false; }
//            }
//        }else{
//            c6_p619 = "";
//            c6_p619_o = "";
//        }
//
//        if (m6_p620_linearlayout.getVisibility()==View.VISIBLE){
//            if(c6_p620.equals("-1")){ mostrarMensaje("PREGUNTA 620: DEBE SELECCIONAR UNA OPCION");return false; }
//        }else c6_p620 = "";
//
//        if (m6_p621_linearlayout.getVisibility()==View.VISIBLE){
//            if(c6_p621.equals("-1")){ mostrarMensaje("PREGUNTA 621: DEBE SELECCIONAR UNA OPCION");return false; }
//        }else c6_p621 = "";

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

//    public void limpiar_p618(){
//        c6_p618_RadioGroup.clearCheck();
//    }
//
//    public void limpiar_p619(){
//        c6_p619_RadioGroup.clearCheck();
//        c6_p619_o_EditText.setText("");
//    }

//    public void limpiar_p620(){
//        c6_p620_RadioGroup.clearCheck();
//    }
//
//    public void limpiar_p621(){
//        c6_p621_RadioGroup.clearCheck();
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

    public void ocultarOtrosLayouts() {
//        Data data = new Data(context);
//        data.open();
        //FLUJO 0078 FLUJO 78 - UNIVERSO P622
//        if(m6_p622_linearlayout.getVisibility() == View.VISIBLE){
//            if((!c6_p622_mon.equals("0") || c6_p622_esp.equals("") || c6_p622_mon.equals("") || !c6_p622_esp.equals("0")) &&
//                    !(p607.equals("2") && p608.equals("1"))){
//                ocultarFragmentP622P625(true);
//                validarOcultarFragmentP626P629();
//            }else{
        ocultarFragmentP622P625(false);
//            }
//        }
//
//        if(p612.equals("1") || p612.equals("2")){ //Valida se se mostrará la P625
//            ocultarFragmentP622P625(false);
//        }else {
//            //Valida si se mostrará de P626 a P627 (la P628 y P629 tienen la misma validacion que P627)
//            if(
//                    ((p612.equals("4") || p612.equals("5")) && Integer.parseInt(p615_t) <= 15) ||
//                            ((!p612.equals("4") && !p612.equals("5")) && p607.equals("2") && !p608.equals("1"))
//            ){
//                ocultarFragmentP622P625(false);
//            }else {
//                ocultarFragmentP622P625(true);
//            }
//        }
//        data.close();
    }
    public void validarOcultarFragmentP626P629(){
        boolean ocultar = true;
        //universo del P630
        if(p629.equals("1")){
            ocultar = false;
        }
        //universo del P631
        if(p630.equals("1")){
            ocultar = false;
        }
        //universo del P632
        if(p627.equals("1") || p631.equals("13")){
            ocultar = false;
        }
        //universo del P633
        if(
                (p628.equals("1") ||p628.equals("2")) || p631.equals("12") ||
                        (p632_1.equals("1") || p632_2.equals("1") || p632_3.equals("1") || p632_4.equals("1") || p632_5.equals("1") ||
                                p632_6.equals("1") || p632_7.equals("1") || p632_8.equals("1") || p632_9.equals("1") || p632_10.equals("1"))
        ){
            ocultar = false;
        }
        //universo del P634
        if(edad >= 12 && p18 > 0 &&
                (
                        ((p612.equals("4") || p612.equals("5")) && Integer.parseInt(p615_t) >= 15) ||
                                ((!p612.equals("4") && !p612.equals("5")) && !(p607.equals("2") && !p608.equals("1"))) ||
                                ( p622_1>=0 ||  p622_2>=0) //NUEVOS HECTOR
                                || p625 > 0
                                || !p633.equals("")
                )
        ){
            ocultar = false;
        }
        if(ocultar){
            Log.e("P618P621","oculta fragment p634");
            ocultarFragmentP626P629(true);}
        else {
            Log.e("P618P621","muestra fragment p634");
            ocultarFragmentP626P629(false);}
    }

    public boolean verificarCoberturaCapitulo(){
//        Data data = new Data(context);
//        data.open();
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p601p604,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp601p604,idEncuestado).equals("0")) return false;
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p605p608,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp605p608,idEncuestado).equals("0")) return false;
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p609p612,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp609p612,idEncuestado).equals("0")) return false;
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp613p617,idEncuestado).equals("0")) return false;
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p618p621,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp618p621,idEncuestado).equals("0")) return false;
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp622p625,idEncuestado).equals("0")) return false;
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p626p629,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp626p629,idEncuestado).equals("0")) return false;
//        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p630,idEncuestado).equals("1") &&
//                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp630,idEncuestado).equals("0")) return false;
//        data.close();
        return true;
    }
    public void limpiarp620(){
        c6_p620_RadioGroup.clearCheck();
        c6_p620_o_EditText.setText("");
    }
    public void limpiarp621(){
        c6_p621_RadioGroup.clearCheck();
    }
    public void limpiarp622(){
        c6_p622_mon_EditText.setText("");
        c6_p622_esp_EditText.setText("");
    }
    public void limpiarp623(){
        c6_p623_mon_EditText.setText("");
        c6_p623_esp_EditText.setText("");
        c6_p623_nas_Checkbox.setChecked(false);
    }
    public void limpiarp624(){
        c6_p624_mon_EditText.setText("");
        c6_p624_esp_EditText.setText("");
        c6_p624_nas_Checkbox.setChecked(false);
        c6_p624_nas2_Checkbox.setChecked(false);
    }
    public void ocultarFragmentP622P625(boolean ocultar){
        Data data = new Data(context);
        data.open();
        if(ocultar) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.modulo6_c6_p625, "");
            contentValues.put(SQLConstantes.modulo6_c6_p625_cod_dist, "");
            contentValues.put(SQLConstantes.modulo6_c6_p625_dist, "");
            contentValues.put(SQLConstantes.modulo6_c6_p625_cod_prov, "");
            contentValues.put(SQLConstantes.modulo6_c6_p625_prov, "");
            contentValues.put(SQLConstantes.modulo6_c6_p625_cod_depa, "");
            contentValues.put(SQLConstantes.modulo6_c6_p625_depa, "");
            contentValues.put(SQLConstantes.modulo6_c6_p626, "");
            contentValues.put(SQLConstantes.modulo6_c6_p627, "");
            contentValues.put(SQLConstantes.modulo6_c6_p628, "");
            contentValues.put(SQLConstantes.modulo6_c6_p628_o, "");
            contentValues.put(SQLConstantes.modulo6_c6_p629, "");
            data.actualizarElemento(getNombreTabla(), contentValues, idEncuestado);
            data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p622p625, "-1", idEncuestado);
        }else {
            data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p622p625, "1", idEncuestado);
        }
        data.close();
    }
    public void ocultarFragmentP626P629(boolean ocultar){
        Data data = new Data(context);
        data.open();
        if(ocultar) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.modulo6_c6_p630,"");
            contentValues.put(SQLConstantes.modulo6_c6_p631,"");
            contentValues.put(SQLConstantes.modulo6_c6_p631_o,"");
            contentValues.put(SQLConstantes.modulo6_c6_p632_1,"");
            contentValues.put(SQLConstantes.modulo6_c6_p632_2,"");
            contentValues.put(SQLConstantes.modulo6_c6_p632_3,"");
            contentValues.put(SQLConstantes.modulo6_c6_p632_4,"");
            contentValues.put(SQLConstantes.modulo6_c6_p632_5,"");
            contentValues.put(SQLConstantes.modulo6_c6_p632_6,"");
            contentValues.put(SQLConstantes.modulo6_c6_p632_7,"");
            contentValues.put(SQLConstantes.modulo6_c6_p632_8,"");
            contentValues.put(SQLConstantes.modulo6_c6_p632_9,"");
            contentValues.put(SQLConstantes.modulo6_c6_p632_10,"");
            contentValues.put(SQLConstantes.modulo6_c6_p632_10_o,"");
            contentValues.put(SQLConstantes.modulo6_c6_p632_11,"");
            contentValues.put(SQLConstantes.modulo6_c6_p633,"");
            contentValues.put(SQLConstantes.modulo6_c6_p632i,"");
            contentValues.put(SQLConstantes.modulo6_c6_p634_1,"");
            contentValues.put(SQLConstantes.modulo6_c6_p634_2,"");
            contentValues.put(SQLConstantes.modulo6_c6_p634_3,"");
            contentValues.put(SQLConstantes.modulo6_c6_p634_4,"");
            contentValues.put(SQLConstantes.modulo6_c6_p634_5,"");
            contentValues.put(SQLConstantes.modulo6_c6_p634_6,"");
            contentValues.put(SQLConstantes.modulo6_c6_p634_7,"");
            //contentValues.put(SQLConstantes.modulo6_c6_p634_6_o,"");
            contentValues.put(SQLConstantes.modulo6_c6_p634_7_o,"");
            data.actualizarElemento(getNombreTabla(), contentValues, idEncuestado);
            data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p626p629, "-1", idEncuestado);
        }else {
            data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p626p629, "1", idEncuestado);
        }
        data.close();
    }
    public boolean validar_P620(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 &&
                !p619.equals("") && (p612.equals("3") || p612.equals("6") || p612.equals("7") || p612.equals("8"))){
            valido= true;
        }
        return valido;
    }
    public boolean validar_P621(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 &&
                !c6_p620.equals("") && !c6_p620.equals("-1") && (p612.equals("3") || p612.equals("6") || p612.equals("7") || p612.equals("8"))){
            valido = true;
        }
        return valido;
    }
    public boolean validar_P622(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 &&
                !c6_p621.equals("") && !c6_p621.equals("-1") &&
                !c6_p621.equals("5") && //agregado par que tenga coherencia
                (p612.equals("3") || p612.equals("6") || p612.equals("7") || p612.equals("8"))){
            valido = true;
        }
        return valido;
    }
    public boolean validar_P623(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 &&
                !p619.equals("") && !p619.equals("-1") && (p612.equals("1") || p612.equals("2"))){
            valido = true;
        }
        return valido;
    }
    public boolean validar_P624(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 &&
                !c6_p621.equals("5") && //agregado par que tenga coherencia
                (p612.equals("1") || p612.equals("2") || p612.equals("3") || p612.equals("6") || p612.equals("7") || p612.equals("8"))
//                && ((!c6_p623_mon.equals("") && !c6_p623_mon.equals("0")) || (!c6_p623_esp.equals("") && !c6_p623_esp.equals("0"))) //para modificar
        ){
            valido = true;
        }
        return valido;
    }
}
