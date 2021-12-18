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
import android.support.v4.app.NotificationCompatSideChannelService;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo6;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterMinMax;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP626P629 extends FragmentPagina {
    String idEncuestado;
    String idVivienda, idHogar, idInformante, id_informante="";
    Context context;
    Spinner informanteSpinner;

    //Anthony M
    RadioGroup c6_p630_RadioGroup, c6_p631_RadioGroup;
    EditText c6_p631_o_EditText, c6_p632_10_o_EditText, c6_p632i_EditText,c6_p633_EditText, c6_p634_o_EditText;
    CheckBox c6_p632_1_Checkbox, c6_p632_2_Checkbox, c6_p632_3_Checkbox, c6_p632_4_Checkbox, c6_p632_5_Checkbox,
            c6_p632_6_Checkbox, c6_p632_7_Checkbox, c6_p632_8_Checkbox, c6_p632_9_Checkbox, c6_p632_10_Checkbox;
    CheckBox c6_p632_11_Checkbox;//nuevo campo
    CheckBox c6_p634_1_Checkbox, c6_p634_2_Checkbox, c6_p634_3_Checkbox, c6_p634_4_Checkbox, c6_p634_5_Checkbox,
            c6_p634_6_Checkbox, c6_p634_7_Checkbox;
    RelativeLayout m6_p631_linearlayout;
    LinearLayout m6_p630_linearlayout, m6_p632_linearlayout, m6_p633_linearlayout, m6_p634_linearlayout;

    private String c6_p630;
    private String c6_p631;
    private String c6_p631_o;
    private String c6_p632_1;
    private String c6_p632_2;
    private String c6_p632_3;
    private String c6_p632_4;
    private String c6_p632_5;
    private String c6_p632_6;
    private String c6_p632_7;
    private String c6_p632_8;
    private String c6_p632_9;
    private String c6_p632_10;
    private String c6_p632_10_o;
    private String c6_p632_11;//nuevo campo
    private String c6_p632i;
    private String c6_p633;
    private String c6_p634_1;
    private String c6_p634_2;
    private String c6_p634_3;
    private String c6_p634_4;
    private String c6_p634_5;
    private String c6_p634_6;
    private String c6_p634_7;
    private String c6_p634_6_o;
    private String c6_p634_7_o;

    int edad=0;
    private String p208,p506,p607,p608,p612,p615_t,p629,p627,p628;

    private  int p622_1,p622_2;//NUEVOS HECTOR

    String p212,p203;
    String p601,p602,p603,p604;
    String p605_1,p605_2,p605_3,p605_4,p605_5,p605_6,p605_7,p605_8,p605_9,p605_10,p605_11,p605_12;
    boolean p634_contestado = false;

    int p18=0;
    int p625=0;
    @SuppressLint("ValidFragment")
    public FragmentP626P629(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idHogar = residente.getId_hogar();
        idVivienda = residente.getId_vivienda();
        idInformante = "";
        p212 =  data.getResidente(idEncuestado).getC2_p212();
        p203 = residente.getC2_p203();
        p208 = residente.getC2_p208();

        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        p506 = data.getModulo5(idEncuestado).getC5_p506();
        p607 = data.getModulo6(idEncuestado).getC6_p607();
        p608 = data.getModulo6(idEncuestado).getC6_p608();
        p612 = data.getModulo6(idEncuestado).getC6_p612();
        p615_t = data.getModulo6(idEncuestado).getC6_p615_t();
        p627 = data.getModulo6(idEncuestado).getC6_p627();
        p628 = data.getModulo6(idEncuestado).getC6_p628();
        p629 = data.getModulo6(idEncuestado).getC6_p629();
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
        try{
            p18 = Integer.parseInt(data.getHogar(idHogar).getP18());
        }catch (Exception ignore){}
        try{
            p625 = Integer.parseInt(data.getModulo6(idEncuestado).getC6_p625());
        }catch (Exception ignore){}
        p601 = data.getModulo6(idEncuestado).getC6_p601();
        p602 = data.getModulo6(idEncuestado).getC6_p602();
        p603 = data.getModulo6(idEncuestado).getC6_p603();
        p604 = data.getModulo6(idEncuestado).getC6_p604_1();

        p605_1 = data.getModulo6(idEncuestado).getC6_p605_1();
        p605_2 = data.getModulo6(idEncuestado).getC6_p605_2();
        p605_3 = data.getModulo6(idEncuestado).getC6_p605_3();
        p605_4 = data.getModulo6(idEncuestado).getC6_p605_4();
        p605_5 = data.getModulo6(idEncuestado).getC6_p605_5();
        p605_6 = data.getModulo6(idEncuestado).getC6_p605_6();
        p605_7 = data.getModulo6(idEncuestado).getC6_p605_7();
        p605_8 = data.getModulo6(idEncuestado).getC6_p605_8();
        p605_9 = data.getModulo6(idEncuestado).getC6_p605_9();
        p605_10 = data.getModulo6(idEncuestado).getC6_p605_10();
        p605_11 = data.getModulo6(idEncuestado).getC6_p605_11();
        p605_12 = data.getModulo6(idEncuestado).getC6_p605_12();
        if(data.getAllResidentesHogar(idHogar).size() > 0){
            for(int i=0 ; i < data.getAllResidentesHogar(idHogar).size() ; i++){
                if(!data.getAllResidentesHogar(idHogar).get(i).get_id().equals(idEncuestado)){
                    String cadena = "";
                    try{
                        cadena = data.getModulo6(data.getAllResidentesHogar(idHogar).get(i).get_id()).getC6_p634_1();
                    }catch (Exception e){}
                    if(!cadena.equals("")) {
                        p634_contestado = true;
                        break;
                    }
                }
            }
        }
        data.close();
    }

    public FragmentP626P629() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p626_p629, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);
        //Anthony M
        c6_p630_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_630_radiogroup_C6_P630);

        c6_p631_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_631_radiogroup_C6_P631);
        c6_p631_o_EditText = (EditText) rootView.findViewById(R.id.mod6_631_edittext_C6_P631_O);

        c6_p632_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_632_checkbox_C5_P632_1);
        c6_p632_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_632_checkbox_C5_P632_2);
        c6_p632_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_632_checkbox_C5_P632_3);
        c6_p632_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_632_checkbox_C5_P632_4);
        c6_p632_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_632_checkbox_C5_P632_5);
        c6_p632_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_632_checkbox_C5_P632_6);
        c6_p632_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_632_checkbox_C5_P632_7);
        c6_p632_8_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_632_checkbox_C5_P632_8);
        c6_p632_9_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_632_checkbox_C5_P632_9);
        c6_p632_10_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_632_checkbox_C5_P632_10);
        c6_p632_10_o_EditText = (EditText) rootView.findViewById(R.id.mod6_632_edittext_C5_P632_O);
        c6_p632_11_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_632_checkbox_C5_P632_11);//nuevo campo

        c6_p632i_EditText = (EditText) rootView.findViewById(R.id.mod6_632_i_edittext_C6_P632);

        c6_p633_EditText = (EditText) rootView.findViewById(R.id.mod6_633_edittext_C6_P633);

        c6_p634_1_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_634_checkbox_C6_P634_1);
        c6_p634_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_634_checkbox_C6_P634_2);
        c6_p634_3_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_634_checkbox_C6_P634_3);
        c6_p634_4_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_634_checkbox_C6_P634_4);
        c6_p634_5_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_634_checkbox_C6_P634_5);
        c6_p634_6_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_634_checkbox_C6_P634_6);
        c6_p634_7_Checkbox = (CheckBox) rootView.findViewById(R.id.mod6_634_checkbox_C6_P634_7);
        c6_p634_o_EditText = (EditText) rootView.findViewById(R.id.mod6_634_edittext_C6_P634_O);

        m6_p630_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p630);
        m6_p631_linearlayout = (RelativeLayout) rootView.findViewById(R.id.layout_m6_p631);
        m6_p632_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p632);
        m6_p633_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p633);
        m6_p634_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p634);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        configurarEditText(c6_p631_o_EditText,m6_p631_linearlayout,0,100);
        configurarEditText(c6_p632_10_o_EditText,m6_p632_linearlayout,0,100);
        configurarEditText(c6_p633_EditText,m6_p633_linearlayout,2,2);
        configurarEditText(c6_p632i_EditText,m6_p632_linearlayout,2,2);
        configurarEditText(c6_p634_o_EditText,m6_p634_linearlayout,0,30);

        //controlarChecked(c6_p634_6_Checkbox,c6_p634_o_EditText);//Anthony M
        controlarChecked(c6_p634_7_Checkbox,c6_p634_o_EditText);//Anthony M

        c6_p632i_EditText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "10")});
        c6_p632i_EditText.setEnabled(false);
        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);


        c6_p630_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                if(validar_P631()){ m6_p631_linearlayout.setVisibility(View.VISIBLE); }
                else{ limpiar631();m6_p631_linearlayout.setVisibility(View.GONE); }

                if(validar_P632()){ m6_p632_linearlayout.setVisibility(View.VISIBLE); }
                else{ limpiar632();m6_p632_linearlayout.setVisibility(View.GONE); }

                if(validar_P633()){ m6_p633_linearlayout.setVisibility(View.VISIBLE); }
                else{ limpiar633();m6_p633_linearlayout.setVisibility(View.GONE); }

                if(validar_P634()){ m6_p633_linearlayout.setVisibility(View.VISIBLE); }
                else{ limpiar634();m6_p633_linearlayout.setVisibility(View.GONE); }
//                int pos = radioGroup.indexOfChild(c6_p630_RadioGroup.findViewById(c6_p630_RadioGroup.getCheckedRadioButtonId()));
//                if(pos==2){
//                    limpiar631();
//                    m6_p631_linearlayout.setVisibility(View.GONE);
//                    limpiar632();
//                    m6_p632_linearlayout.setVisibility(View.GONE);
//                    limpiar633();
//                    m6_p633_linearlayout.setVisibility(View.GONE);
//                }else{
//                    m6_p631_linearlayout.setVisibility(View.VISIBLE);
//                }
            }
        });




        c6_p631_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(validar_P632()){ m6_p632_linearlayout.setVisibility(View.VISIBLE); }
                else{ limpiar632();m6_p632_linearlayout.setVisibility(View.GONE); }

                if(validar_P633()){ m6_p633_linearlayout.setVisibility(View.VISIBLE); }
                else{ limpiar633();m6_p633_linearlayout.setVisibility(View.GONE); }

                if(validar_P634()){ m6_p634_linearlayout.setVisibility(View.VISIBLE); }
                else{ limpiar634();m6_p634_linearlayout.setVisibility(View.GONE); }
                int pos = radioGroup.indexOfChild(c6_p631_RadioGroup.findViewById(c6_p631_RadioGroup.getCheckedRadioButtonId()));

                if(pos==11){
                    c6_p631_o_EditText.setEnabled(true);
                    c6_p631_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    c6_p631_o_EditText.setText("");
                    c6_p631_o_EditText.setEnabled(false);
                    c6_p631_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });
        c6_p632_1_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                flujo86();achurar_p632();
                validacionedittextp632i();

                int cant_p2_respondidos = Integer.parseInt(c6_p632_1)+Integer.parseInt(c6_p632_2)+Integer.parseInt(c6_p632_3)+Integer.parseInt(c6_p632_4)+ Integer.parseInt(c6_p632_5)+
                        Integer.parseInt(c6_p632_6)+Integer.parseInt(c6_p632_7)+Integer.parseInt(c6_p632_8)+Integer.parseInt(c6_p632_9)+Integer.parseInt(c6_p632_10);
                if(b){
                    if(cant_p2_respondidos > 1){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }
                 }else{
                    if(cant_p2_respondidos <= 1){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setText("");
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }

                /*
                if(b){
                    if(c6_p632_2_Checkbox.isChecked()|| c6_p632_3_Checkbox.isChecked()|| c6_p632_4_Checkbox.isChecked()|| c6_p632_5_Checkbox.isChecked()
                            || c6_p632_6_Checkbox.isChecked() || c6_p632_7_Checkbox.isChecked() || c6_p632_8_Checkbox.isChecked() || c6_p632_9_Checkbox.isChecked()
                            || c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }else{
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }else{
                    if(c6_p632_2_Checkbox.isChecked()|| c6_p632_3_Checkbox.isChecked()|| c6_p632_4_Checkbox.isChecked()|| c6_p632_5_Checkbox.isChecked()
                            || c6_p632_6_Checkbox.isChecked() || c6_p632_7_Checkbox.isChecked() || c6_p632_8_Checkbox.isChecked() || c6_p632_9_Checkbox.isChecked()
                            || c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }else{
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }

                }*/
            }
        });
        c6_p632_2_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                flujo86();achurar_p632();

                validacionedittextp632i();

                int cant_p2_respondidos = Integer.parseInt(c6_p632_1)+Integer.parseInt(c6_p632_2)+Integer.parseInt(c6_p632_3)+Integer.parseInt(c6_p632_4)+ Integer.parseInt(c6_p632_5)+
                        Integer.parseInt(c6_p632_6)+Integer.parseInt(c6_p632_7)+Integer.parseInt(c6_p632_8)+Integer.parseInt(c6_p632_9)+Integer.parseInt(c6_p632_10);
                if(b){
                    if(cant_p2_respondidos > 1){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }
                }else{
                    if(cant_p2_respondidos <= 1){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setText("");
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }


               /* if(b){
                    if(c6_p632_1_Checkbox.isChecked()|| c6_p632_3_Checkbox.isChecked()|| c6_p632_4_Checkbox.isChecked()|| c6_p632_5_Checkbox.isChecked()
                            || c6_p632_6_Checkbox.isChecked() || c6_p632_7_Checkbox.isChecked() || c6_p632_8_Checkbox.isChecked() || c6_p632_9_Checkbox.isChecked()
                            || c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }else{
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }else{
                    if(!c6_p632_1_Checkbox.isChecked()|| !c6_p632_3_Checkbox.isChecked()|| !c6_p632_4_Checkbox.isChecked()|| !c6_p632_5_Checkbox.isChecked()
                            || !c6_p632_6_Checkbox.isChecked() || !c6_p632_7_Checkbox.isChecked() || !c6_p632_8_Checkbox.isChecked() || !c6_p632_9_Checkbox.isChecked()
                            || !c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }else{
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }

                }*/
            }
        });
        c6_p632_3_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                flujo86();achurar_p632();
                validacionedittextp632i();

                int cant_p2_respondidos = Integer.parseInt(c6_p632_1)+Integer.parseInt(c6_p632_2)+Integer.parseInt(c6_p632_3)+Integer.parseInt(c6_p632_4)+ Integer.parseInt(c6_p632_5)+
                        Integer.parseInt(c6_p632_6)+Integer.parseInt(c6_p632_7)+Integer.parseInt(c6_p632_8)+Integer.parseInt(c6_p632_9)+Integer.parseInt(c6_p632_10);
                if(b){
                    if(cant_p2_respondidos > 1){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }
                }else{
                    if(cant_p2_respondidos <= 1){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setText("");
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }

               /* if(b){
                    if(c6_p632_1_Checkbox.isChecked()|| c6_p632_2_Checkbox.isChecked()|| c6_p632_4_Checkbox.isChecked()|| c6_p632_5_Checkbox.isChecked()
                            || c6_p632_6_Checkbox.isChecked() || c6_p632_7_Checkbox.isChecked() || c6_p632_8_Checkbox.isChecked() || c6_p632_9_Checkbox.isChecked()
                            || c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }else{
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }else{
                    if(!c6_p632_1_Checkbox.isChecked()|| !c6_p632_2_Checkbox.isChecked()|| !c6_p632_4_Checkbox.isChecked()|| !c6_p632_5_Checkbox.isChecked()
                            || !c6_p632_6_Checkbox.isChecked() || !c6_p632_7_Checkbox.isChecked() || !c6_p632_8_Checkbox.isChecked() || !c6_p632_9_Checkbox.isChecked()
                            || !c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }else{
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }

                }*/
            }
        });
        c6_p632_4_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                flujo86();achurar_p632();
                validacionedittextp632i();

                int cant_p2_respondidos = Integer.parseInt(c6_p632_1)+Integer.parseInt(c6_p632_2)+Integer.parseInt(c6_p632_3)+Integer.parseInt(c6_p632_4)+ Integer.parseInt(c6_p632_5)+
                        Integer.parseInt(c6_p632_6)+Integer.parseInt(c6_p632_7)+Integer.parseInt(c6_p632_8)+Integer.parseInt(c6_p632_9)+Integer.parseInt(c6_p632_10);
                if(b){
                    if(cant_p2_respondidos > 1){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }
                }else{
                    if(cant_p2_respondidos <= 1){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setText("");
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }

                /*if(b){
                    if(c6_p632_1_Checkbox.isChecked()|| c6_p632_2_Checkbox.isChecked()|| c6_p632_3_Checkbox.isChecked()|| c6_p632_5_Checkbox.isChecked()
                            || c6_p632_6_Checkbox.isChecked() || c6_p632_7_Checkbox.isChecked() || c6_p632_8_Checkbox.isChecked() || c6_p632_9_Checkbox.isChecked()
                            || c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }else{
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }else{
                    if(!c6_p632_1_Checkbox.isChecked()|| !c6_p632_2_Checkbox.isChecked()|| !c6_p632_3_Checkbox.isChecked()|| !c6_p632_5_Checkbox.isChecked()
                            || !c6_p632_6_Checkbox.isChecked() || !c6_p632_7_Checkbox.isChecked() || !c6_p632_8_Checkbox.isChecked() || !c6_p632_9_Checkbox.isChecked()
                            || !c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }else{
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }

                }*/
            }
        });
        c6_p632_5_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                flujo86();achurar_p632();
                validacionedittextp632i();

                int cant_p2_respondidos = Integer.parseInt(c6_p632_1)+Integer.parseInt(c6_p632_2)+Integer.parseInt(c6_p632_3)+Integer.parseInt(c6_p632_4)+ Integer.parseInt(c6_p632_5)+
                        Integer.parseInt(c6_p632_6)+Integer.parseInt(c6_p632_7)+Integer.parseInt(c6_p632_8)+Integer.parseInt(c6_p632_9)+Integer.parseInt(c6_p632_10);
                if(b){
                    if(cant_p2_respondidos > 1){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }
                }else{
                    if(cant_p2_respondidos <= 1){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setText("");
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }

               /* if(b){
                    if(c6_p632_1_Checkbox.isChecked()|| c6_p632_2_Checkbox.isChecked()|| c6_p632_3_Checkbox.isChecked()|| c6_p632_4_Checkbox.isChecked()
                            || c6_p632_6_Checkbox.isChecked() || c6_p632_7_Checkbox.isChecked() || c6_p632_8_Checkbox.isChecked() || c6_p632_9_Checkbox.isChecked()
                            || c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }else{
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }else{
                    if(!c6_p632_1_Checkbox.isChecked()|| !c6_p632_2_Checkbox.isChecked()|| !c6_p632_3_Checkbox.isChecked()|| !c6_p632_4_Checkbox.isChecked()
                            || !c6_p632_6_Checkbox.isChecked() || !c6_p632_7_Checkbox.isChecked() || !c6_p632_8_Checkbox.isChecked() || !c6_p632_9_Checkbox.isChecked()
                            || !c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }else{
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }

                }*/
            }
        });
        c6_p632_6_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                flujo86();achurar_p632();
                validacionedittextp632i();

                int cant_p2_respondidos = Integer.parseInt(c6_p632_1)+Integer.parseInt(c6_p632_2)+Integer.parseInt(c6_p632_3)+Integer.parseInt(c6_p632_4)+ Integer.parseInt(c6_p632_5)+
                        Integer.parseInt(c6_p632_6)+Integer.parseInt(c6_p632_7)+Integer.parseInt(c6_p632_8)+Integer.parseInt(c6_p632_9)+Integer.parseInt(c6_p632_10);
                if(b){
                    if(cant_p2_respondidos > 1){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }
                }else{
                    if(cant_p2_respondidos <= 1){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setText("");
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }

              /*  if(b){
                    if(c6_p632_1_Checkbox.isChecked()|| c6_p632_2_Checkbox.isChecked()|| c6_p632_3_Checkbox.isChecked()|| c6_p632_4_Checkbox.isChecked()
                            || c6_p632_5_Checkbox.isChecked() || c6_p632_7_Checkbox.isChecked() || c6_p632_8_Checkbox.isChecked() || c6_p632_9_Checkbox.isChecked()
                            || c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }else{
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }else{
                    if(!c6_p632_1_Checkbox.isChecked()|| !c6_p632_2_Checkbox.isChecked()|| !c6_p632_3_Checkbox.isChecked()|| !c6_p632_4_Checkbox.isChecked()
                            || !c6_p632_5_Checkbox.isChecked() || !c6_p632_7_Checkbox.isChecked() || !c6_p632_8_Checkbox.isChecked() || !c6_p632_9_Checkbox.isChecked()
                            || !c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }else{
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }

                }*/
            }
        });
        c6_p632_7_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                flujo86();achurar_p632();
                validacionedittextp632i();

                int cant_p2_respondidos = Integer.parseInt(c6_p632_1)+Integer.parseInt(c6_p632_2)+Integer.parseInt(c6_p632_3)+Integer.parseInt(c6_p632_4)+ Integer.parseInt(c6_p632_5)+
                        Integer.parseInt(c6_p632_6)+Integer.parseInt(c6_p632_7)+Integer.parseInt(c6_p632_8)+Integer.parseInt(c6_p632_9)+Integer.parseInt(c6_p632_10);
                if(b){
                    if(cant_p2_respondidos > 1){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }
                }else{
                    if(cant_p2_respondidos <= 1){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setText("");
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }

                /*if(b){
                    if(c6_p632_1_Checkbox.isChecked()|| c6_p632_2_Checkbox.isChecked()|| c6_p632_3_Checkbox.isChecked()|| c6_p632_4_Checkbox.isChecked()
                            || c6_p632_5_Checkbox.isChecked() || c6_p632_6_Checkbox.isChecked() || c6_p632_8_Checkbox.isChecked() || c6_p632_9_Checkbox.isChecked()
                            || c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }else{
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }else{
                    if(!c6_p632_1_Checkbox.isChecked()|| !c6_p632_2_Checkbox.isChecked()|| !c6_p632_3_Checkbox.isChecked()|| !c6_p632_4_Checkbox.isChecked()
                            || !c6_p632_5_Checkbox.isChecked() || !c6_p632_6_Checkbox.isChecked() || !c6_p632_8_Checkbox.isChecked() || !c6_p632_9_Checkbox.isChecked()
                            || !c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }else{
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }

                }*/
            }
        });
        c6_p632_8_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                flujo86();achurar_p632();
                validacionedittextp632i();

                int cant_p2_respondidos = Integer.parseInt(c6_p632_1)+Integer.parseInt(c6_p632_2)+Integer.parseInt(c6_p632_3)+Integer.parseInt(c6_p632_4)+ Integer.parseInt(c6_p632_5)+
                        Integer.parseInt(c6_p632_6)+Integer.parseInt(c6_p632_7)+Integer.parseInt(c6_p632_8)+Integer.parseInt(c6_p632_9)+Integer.parseInt(c6_p632_10);
                if(b){
                    if(cant_p2_respondidos > 1){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }
                }else{
                    if(cant_p2_respondidos <= 1){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setText("");
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }
                /*if(b){
                    if(c6_p632_1_Checkbox.isChecked()|| c6_p632_2_Checkbox.isChecked()|| c6_p632_3_Checkbox.isChecked()|| c6_p632_4_Checkbox.isChecked()
                            || c6_p632_5_Checkbox.isChecked() || c6_p632_6_Checkbox.isChecked() || c6_p632_7_Checkbox.isChecked() || c6_p632_9_Checkbox.isChecked()
                            || c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }else{
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }else{
                    if(!c6_p632_1_Checkbox.isChecked()|| !c6_p632_2_Checkbox.isChecked()|| !c6_p632_3_Checkbox.isChecked()|| !c6_p632_4_Checkbox.isChecked()
                            || !c6_p632_5_Checkbox.isChecked() || !c6_p632_6_Checkbox.isChecked() || !c6_p632_7_Checkbox.isChecked() || !c6_p632_9_Checkbox.isChecked()
                            || !c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }else{
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }

                }*/
            }
        });
        c6_p632_9_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                flujo86();achurar_p632();
                validacionedittextp632i();

                int cant_p2_respondidos = Integer.parseInt(c6_p632_1)+Integer.parseInt(c6_p632_2)+Integer.parseInt(c6_p632_3)+Integer.parseInt(c6_p632_4)+ Integer.parseInt(c6_p632_5)+
                        Integer.parseInt(c6_p632_6)+Integer.parseInt(c6_p632_7)+Integer.parseInt(c6_p632_8)+Integer.parseInt(c6_p632_9)+Integer.parseInt(c6_p632_10);
                if(b){
                    if(cant_p2_respondidos > 1){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }
                }else{
                    if(cant_p2_respondidos <= 1){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setText("");
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }

               /* if(b){
                    if(c6_p632_1_Checkbox.isChecked()|| c6_p632_2_Checkbox.isChecked()|| c6_p632_3_Checkbox.isChecked()|| c6_p632_4_Checkbox.isChecked()
                            || c6_p632_5_Checkbox.isChecked() || c6_p632_6_Checkbox.isChecked() || c6_p632_7_Checkbox.isChecked() || c6_p632_8_Checkbox.isChecked()
                            || c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }else{
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                }else{
                    if(!c6_p632_1_Checkbox.isChecked()|| !c6_p632_2_Checkbox.isChecked()|| !c6_p632_3_Checkbox.isChecked()|| !c6_p632_4_Checkbox.isChecked()
                            || !c6_p632_5_Checkbox.isChecked() || !c6_p632_6_Checkbox.isChecked() || !c6_p632_7_Checkbox.isChecked() || !c6_p632_8_Checkbox.isChecked()
                            || !c6_p632_10_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }else{
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }

                }*/
            }
        });
        c6_p632_10_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                flujo86();achurar_p632();
                validacionedittextp632i();

                int cant_p2_respondidos = Integer.parseInt(c6_p632_1)+Integer.parseInt(c6_p632_2)+Integer.parseInt(c6_p632_3)+Integer.parseInt(c6_p632_4)+ Integer.parseInt(c6_p632_5)+
                        Integer.parseInt(c6_p632_6)+Integer.parseInt(c6_p632_7)+Integer.parseInt(c6_p632_8)+Integer.parseInt(c6_p632_9)+Integer.parseInt(c6_p632_10);

                if(isChecked){
                    c6_p632_10_o_EditText.setEnabled(true);
                    c6_p632_10_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);

                    if(cant_p2_respondidos > 1){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }

                    /*if(c6_p632_1_Checkbox.isChecked()|| c6_p632_2_Checkbox.isChecked()|| c6_p632_3_Checkbox.isChecked()|| c6_p632_4_Checkbox.isChecked()
                            || c6_p632_5_Checkbox.isChecked() || c6_p632_6_Checkbox.isChecked() || c6_p632_7_Checkbox.isChecked() || c6_p632_8_Checkbox.isChecked()
                            || c6_p632_9_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }else{
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }*/
                }else {
                    c6_p632_10_o_EditText.setText("");
                    c6_p632_10_o_EditText.setEnabled(false);
                    c6_p632_10_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);

                    if(cant_p2_respondidos <= 1){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setText("");
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }

                    /*if(!c6_p632_1_Checkbox.isChecked()|| !c6_p632_2_Checkbox.isChecked()|| !c6_p632_3_Checkbox.isChecked()|| !c6_p632_4_Checkbox.isChecked()
                            || !c6_p632_5_Checkbox.isChecked() || !c6_p632_6_Checkbox.isChecked() || !c6_p632_7_Checkbox.isChecked() || !c6_p632_8_Checkbox.isChecked()
                            || !c6_p632_9_Checkbox.isChecked()){
                        c6_p632i_EditText.setEnabled(false);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    }else{
                        c6_p632i_EditText.setEnabled(true);
                        c6_p632i_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                    }*/
                }
            }
        });
        c6_p632_11_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                flujo86();achurar_p632();
            }
        });
        c6_p633_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("0")) { limpiar633(); }
                if(validar_P634()){
                    m6_p634_linearlayout.setVisibility(View.VISIBLE);
                }else {
                    limpiar634();
                    m6_p634_linearlayout.setVisibility(View.GONE);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        llenarVista();
        cargarDatos();
    }
    private void limpiar630() {
        c6_p630_RadioGroup.clearCheck();
    }
    private void limpiar631() {
        c6_p631_RadioGroup.clearCheck();
        c6_p631_o_EditText.setText("");
    }

    private void limpiar632() {
        c6_p632_1_Checkbox.setChecked(false);
        c6_p632_2_Checkbox.setChecked(false);
        c6_p632_3_Checkbox.setChecked(false);
        c6_p632_4_Checkbox.setChecked(false);
        c6_p632_5_Checkbox.setChecked(false);
        c6_p632_6_Checkbox.setChecked(false);
        c6_p632_7_Checkbox.setChecked(false);
        c6_p632_8_Checkbox.setChecked(false);
        c6_p632_9_Checkbox.setChecked(false);
        c6_p632_10_Checkbox.setChecked(false);
        c6_p632_10_o_EditText.setText("");
        c6_p632_11_Checkbox.setChecked(false);
        c6_p632i_EditText.setText("");

    }

    private void limpiar633() {
        c6_p633_EditText.setText("");
    }


    private void limpiar634() {
        c6_p634_1_Checkbox.setChecked(false);
        c6_p634_2_Checkbox.setChecked(false);
        c6_p634_3_Checkbox.setChecked(false);
        c6_p634_4_Checkbox.setChecked(false);
        c6_p634_5_Checkbox.setChecked(false);
        c6_p634_6_Checkbox.setChecked(false);
        c6_p634_7_Checkbox.setChecked(false);
        c6_p634_o_EditText.setText("");
    }

    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo6_id_informante,idInformante);
        //Anthony M
        contentValues.put(SQLConstantes.modulo6_c6_p630,c6_p630);
        contentValues.put(SQLConstantes.modulo6_c6_p631,c6_p631);
        contentValues.put(SQLConstantes.modulo6_c6_p631_o,c6_p631_o);
        contentValues.put(SQLConstantes.modulo6_c6_p632_1,c6_p632_1);
        contentValues.put(SQLConstantes.modulo6_c6_p632_2,c6_p632_2);
        contentValues.put(SQLConstantes.modulo6_c6_p632_3,c6_p632_3);
        contentValues.put(SQLConstantes.modulo6_c6_p632_4,c6_p632_4);
        contentValues.put(SQLConstantes.modulo6_c6_p632_5,c6_p632_5);
        contentValues.put(SQLConstantes.modulo6_c6_p632_6,c6_p632_6);
        contentValues.put(SQLConstantes.modulo6_c6_p632_7,c6_p632_7);
        contentValues.put(SQLConstantes.modulo6_c6_p632_8,c6_p632_8);
        contentValues.put(SQLConstantes.modulo6_c6_p632_9,c6_p632_9);
        contentValues.put(SQLConstantes.modulo6_c6_p632_10,c6_p632_10);
        contentValues.put(SQLConstantes.modulo6_c6_p632_10_o,c6_p632_10_o);
        contentValues.put(SQLConstantes.modulo6_c6_p632_11,c6_p632_11);//campo nuevo
        contentValues.put(SQLConstantes.modulo6_c6_p633,c6_p633);
        contentValues.put(SQLConstantes.modulo6_c6_p632i,c6_p632i);
        contentValues.put(SQLConstantes.modulo6_c6_p634_1,c6_p634_1);
        contentValues.put(SQLConstantes.modulo6_c6_p634_2,c6_p634_2);
        contentValues.put(SQLConstantes.modulo6_c6_p634_3,c6_p634_3);
        contentValues.put(SQLConstantes.modulo6_c6_p634_4,c6_p634_4);
        contentValues.put(SQLConstantes.modulo6_c6_p634_5,c6_p634_5);
        contentValues.put(SQLConstantes.modulo6_c6_p634_6,c6_p634_6);
        contentValues.put(SQLConstantes.modulo6_c6_p634_7,c6_p634_7);
        //contentValues.put(SQLConstantes.modulo6_c6_p634_6_o,c6_p634_6_o);
        contentValues.put(SQLConstantes.modulo6_c6_p634_7_o,c6_p634_7_o);

        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp626p629,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();
    }

    @Override
    public void llenarVariables() {
        idInformante = obtener_Nresidente(informanteSpinner);
        c6_p630 = c6_p630_RadioGroup.indexOfChild(c6_p630_RadioGroup.findViewById(c6_p630_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p631 = c6_p631_RadioGroup.indexOfChild(c6_p631_RadioGroup.findViewById(c6_p631_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p631_o = c6_p631_o_EditText.getText().toString();
        if(c6_p632_1_Checkbox.isChecked()) c6_p632_1 = "1"; else c6_p632_1 = "0";
        if(c6_p632_2_Checkbox.isChecked()) c6_p632_2 = "1"; else c6_p632_2 = "0";
        if(c6_p632_3_Checkbox.isChecked()) c6_p632_3 = "1"; else c6_p632_3 = "0";
        if(c6_p632_4_Checkbox.isChecked()) c6_p632_4 = "1"; else c6_p632_4 = "0";
        if(c6_p632_5_Checkbox.isChecked()) c6_p632_5 = "1"; else c6_p632_5 = "0";
        if(c6_p632_6_Checkbox.isChecked()) c6_p632_6 = "1"; else c6_p632_6 = "0";
        if(c6_p632_7_Checkbox.isChecked()) c6_p632_7 = "1"; else c6_p632_7 = "0";
        if(c6_p632_8_Checkbox.isChecked()) c6_p632_8 = "1"; else c6_p632_8 = "0";
        if(c6_p632_9_Checkbox.isChecked()) c6_p632_9 = "1"; else c6_p632_9 = "0";
        if(c6_p632_10_Checkbox.isChecked()) c6_p632_10 = "1"; else c6_p632_10 = "0";
        c6_p632_10_o = c6_p632_10_o_EditText.getText().toString();
        if(c6_p632_11_Checkbox.isChecked()) c6_p632_11 = "1"; else c6_p632_11 = "0"; //campo nuevo
        c6_p633 = c6_p633_EditText.getText().toString();
        c6_p632i = c6_p632i_EditText.getText().toString();
        if(c6_p634_1_Checkbox.isChecked()) c6_p634_1 = "1"; else c6_p634_1 = "0";
        if(c6_p634_2_Checkbox.isChecked()) c6_p634_2 = "1"; else c6_p634_2 = "0";
        if(c6_p634_3_Checkbox.isChecked()) c6_p634_3 = "1"; else c6_p634_3 = "0";
        if(c6_p634_4_Checkbox.isChecked()) c6_p634_4 = "1"; else c6_p634_4 = "0";
        if(c6_p634_5_Checkbox.isChecked()) c6_p634_5 = "1"; else c6_p634_5 = "0";
        if(c6_p634_6_Checkbox.isChecked()) c6_p634_6 = "1"; else c6_p634_6 = "0";
        if(c6_p634_7_Checkbox.isChecked()) c6_p634_7 = "1"; else c6_p634_7 = "0";
        //c6_p634_6_o = c6_p634_o_EditText.getText().toString();
        c6_p634_7_o = c6_p634_o_EditText.getText().toString();
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
            //Anthony M
            if(modulo6.getC6_p630() != null && !modulo6.getC6_p630().equals("-1") && !modulo6.getC6_p630().equals(""))((RadioButton)c6_p630_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p630()))).setChecked(true);
            if(modulo6.getC6_p631() != null && !modulo6.getC6_p631().equals("-1") && !modulo6.getC6_p631().equals(""))((RadioButton)c6_p631_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p631()))).setChecked(true);
            c6_p631_o_EditText.setText(modulo6.getC6_p631_o());
            if(modulo6.getC6_p632_1().equals("1")) c6_p632_1_Checkbox.setChecked(true);
            if(modulo6.getC6_p632_2().equals("1")) c6_p632_2_Checkbox.setChecked(true);
            if(modulo6.getC6_p632_3().equals("1")) c6_p632_3_Checkbox.setChecked(true);
            if(modulo6.getC6_p632_4().equals("1")) c6_p632_4_Checkbox.setChecked(true);
            if(modulo6.getC6_p632_5().equals("1")) c6_p632_5_Checkbox.setChecked(true);
            if(modulo6.getC6_p632_6().equals("1")) c6_p632_6_Checkbox.setChecked(true);
            if(modulo6.getC6_p632_7().equals("1")) c6_p632_7_Checkbox.setChecked(true);
            if(modulo6.getC6_p632_8().equals("1")) c6_p632_8_Checkbox.setChecked(true);
            if(modulo6.getC6_p632_9().equals("1")) c6_p632_9_Checkbox.setChecked(true);
            if(modulo6.getC6_p632_10().equals("1")) c6_p632_10_Checkbox.setChecked(true);
            c6_p632i_EditText.setText(modulo6.getC6_p632i());
            c6_p632_10_o_EditText.setText(modulo6.getC6_p632_10_o());
            if(modulo6.getC6_p632_11().equals("1")) c6_p632_11_Checkbox.setChecked(true);//campo nuevo
            c6_p633_EditText.setText(modulo6.getC6_p633());

            if(!p634_contestado){
                c6_p634_1_Checkbox.setEnabled(true);
                c6_p634_2_Checkbox.setEnabled(true);
                c6_p634_3_Checkbox.setEnabled(true);
                c6_p634_4_Checkbox.setEnabled(true);
                c6_p634_5_Checkbox.setEnabled(true);
                c6_p634_6_Checkbox.setEnabled(true);
                c6_p634_7_Checkbox.setEnabled(true);

                if(modulo6.getC6_p634_1().equals("1")) c6_p634_1_Checkbox.setChecked(true);
                if(modulo6.getC6_p634_2().equals("1")) c6_p634_2_Checkbox.setChecked(true);
                if(modulo6.getC6_p634_3().equals("1")) c6_p634_3_Checkbox.setChecked(true);
                if(modulo6.getC6_p634_4().equals("1")) c6_p634_4_Checkbox.setChecked(true);
                if(modulo6.getC6_p634_5().equals("1")) c6_p634_5_Checkbox.setChecked(true);
                if(modulo6.getC6_p634_6().equals("1")) c6_p634_6_Checkbox.setChecked(true);
                if(modulo6.getC6_p634_7().equals("1")) c6_p634_7_Checkbox.setChecked(true);
               //c6_p634_o_EditText.setText(modulo6.getC6_p634_6_o());
                c6_p634_o_EditText.setText(modulo6.getC6_p634_7_o());
            }else {
                c6_p634_1_Checkbox.setEnabled(false);
                c6_p634_2_Checkbox.setEnabled(false);
                c6_p634_3_Checkbox.setEnabled(false);
                c6_p634_4_Checkbox.setEnabled(false);
                c6_p634_5_Checkbox.setEnabled(false);
                c6_p634_6_Checkbox.setEnabled(false);
                c6_p634_7_Checkbox.setEnabled(false);
                c6_p634_o_EditText.setEnabled(false);
            }

        }
//        fecha();
        inicio();
        data.close();
    }
    private void validacionedittextp632i(){
        if(c6_p632_1_Checkbox.isChecked()) c6_p632_1 = "1"; else c6_p632_1 = "0";
        if(c6_p632_2_Checkbox.isChecked()) c6_p632_2 = "1"; else c6_p632_2 = "0";
        if(c6_p632_3_Checkbox.isChecked()) c6_p632_3 = "1"; else c6_p632_3 = "0";
        if(c6_p632_4_Checkbox.isChecked()) c6_p632_4 = "1"; else c6_p632_4 = "0";
        if(c6_p632_5_Checkbox.isChecked()) c6_p632_5 = "1"; else c6_p632_5 = "0";
        if(c6_p632_6_Checkbox.isChecked()) c6_p632_6 = "1"; else c6_p632_6 = "0";
        if(c6_p632_7_Checkbox.isChecked()) c6_p632_7 = "1"; else c6_p632_7 = "0";
        if(c6_p632_8_Checkbox.isChecked()) c6_p632_8 = "1"; else c6_p632_8 = "0";
        if(c6_p632_9_Checkbox.isChecked()) c6_p632_9 = "1"; else c6_p632_9 = "0";
        if(c6_p632_10_Checkbox.isChecked()) c6_p632_10 = "1"; else c6_p632_10 = "0";
    }
    private void inicio() {
        llenarVariables();
        Log.e("Fragment","estas en P626P629");
        //P630
        if(validar_P630()) m6_p630_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar630();m6_p630_linearlayout.setVisibility(View.GONE);}
        //P631
        if(validar_P631()) m6_p631_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar631();m6_p631_linearlayout.setVisibility(View.GONE);}
        //P632
        if(validar_P632()) m6_p632_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar632();m6_p632_linearlayout.setVisibility(View.GONE);}
        //P633
        if(validar_P633()) m6_p633_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar633();m6_p633_linearlayout.setVisibility(View.GONE);}
        //P634
        if(validar_P634()) m6_p634_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar634();m6_p634_linearlayout.setVisibility(View.GONE);}

        Log.e("ee","p18 = "+p18);
        Log.e("ee","p203 = "+p203);
        Log.e("ee","p208 = "+p208);
        Log.e("ee","p601 = "+p601);
        Log.e("ee","p603 = "+p603);
        Log.e("ee","p604 = "+p604);
        Log.e("ee","valid_p605_todos = "+valid_p605_todos("2"));
        Log.e("ee","p627 = "+p627);
        Log.e("ee","valid_p632_cualquiera = "+valid_p632_cualquiera());
        Log.e("ee","p628 = "+p628);
        Log.e("ee","p629 = "+p629);
        Log.e("ee","c6_p630 = "+c6_p630);
        Log.e("ee","c6_p631 = "+c6_p631);
    }



    @Override
    public void llenarVista() {
//        Data data = new Data(context);
//        data.open();
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p626,idEncuestado)) m6_p626_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p627,idEncuestado)) m6_p627_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p628,idEncuestado)) m6_p628_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p629,idEncuestado)) m6_p629_linearlayout.setVisibility(View.GONE);
//        data.close();
    }

    @Override
    public boolean validarDatos() {
        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        int cant_p2_respondidos = Integer.parseInt(c6_p632_1)+Integer.parseInt(c6_p632_2)+Integer.parseInt(c6_p632_3)+Integer.parseInt(c6_p632_4)+ Integer.parseInt(c6_p632_5)+
                Integer.parseInt(c6_p632_6)+Integer.parseInt(c6_p632_7)+Integer.parseInt(c6_p632_8)+Integer.parseInt(c6_p632_9)+Integer.parseInt(c6_p632_10);

        //PREGUNTA 630
        if (m6_p630_linearlayout.getVisibility() == View.VISIBLE) {
            if (c6_p630.equals("-1")) {
                mostrarMensaje("PREGUNTA 630: DEBE SELECCIONAR UNA OPCION");
                return false;
            }
        } else {
            c6_p630 = "";
        }

        //PREGUNTA 631
        if (m6_p631_linearlayout.getVisibility() == View.VISIBLE) {
            if (c6_p631.equals("-1")) {
                mostrarMensaje("ERROR “PREGUNTA 631 – DEBE SELECCIONAR ALGUNA ALTERNATIVA”");
                return false;
            }
            if(c6_p631.equals("3") && edad >= 18 && edad <= 50)
            {mostrarMensaje("VERIFICAR “PREGUNTA 631 – INDICA QUE EL MOTIVO POR EL CUAL NO BUSCO TRABAJO ES POR SU EDAD ( " +edad+" AÑOS)");}

            if(c6_p631.equals("5") && p506.equals("2"))
            {mostrarMensaje("VERIFICAR “EN LA PREGUNTA 631 INDICA QUE NO BUSCO TRABAJO PORQUE SUS ESTUDIOS NO LO PERMITEN PERO EN LA PREGUNTA 506 DICE QUE ACTUALMENTE NO ASISTE A ALGÚN CENTRO DE EDUCACIÓN BÁSICA O SUPERIOR”");}
            if (c6_p631.equals("11")) {
                if (c6_p631_o.trim().equals("")) {mostrarMensaje("PREGUNTA 631: DEBE ESPECIFICAR");return false;}
                if (c6_p631_o.length() <3) {mostrarMensaje("ERROR  : P631. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;}
            }
        } else {
            c6_p631 = "";
            c6_p631_o = "";
        }

        //PREGUNTA 632
        if (m6_p632_linearlayout.getVisibility() == View.VISIBLE) {
            if (c6_p632_1.equals("0") && c6_p632_2.equals("0") &&
                    c6_p632_3.equals("0") && c6_p632_4.equals("0") &&
                    c6_p632_5.equals("0") && c6_p632_6.equals("0") &&
                    c6_p632_7.equals("0") && c6_p632_8.equals("0") &&
                    c6_p632_9.equals("0") && c6_p632_10.equals("0") &&
                    c6_p632_11.equals("0")
            ) {
                mostrarMensaje("ERROR “PREGUNTA 632 – DEBE SELECCIONAR ALGUNA ALTERNATIVA”");
                return false;
            }
            if (c6_p632_10.equals("1")) {
                if (c6_p632_10_o.trim().equals("")) {
                    mostrarMensaje("PREGUNTA 632 - OPCION 10: DEBE ESPECIFICAR OTRO");
                    return false;
                }
                if (c6_p632_10_o.trim().length()<3) {
                    mostrarMensaje("ERROR  : P632. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");
                    return false;
                }

            }

            ///AGREGADO 12/11/21
            if (c6_p632i.equals("") && cant_p2_respondidos > 1 ){
                c6_p632i_EditText.requestFocus();
                mostrarMensaje("ERROR PREGUNTA 632 – DEBE INGRESAR EL CÓDIGO DE LA GESTIÓN MÁS IMPORTANTE"); return false;

            }

            ///AGREGADO 14/12/21
            LinkedList<String> listP632 = new LinkedList<String>();
            if (c6_p632_1.equals("1")) listP632.add("1");
            if (c6_p632_2.equals("1")) listP632.add("2");
            if (c6_p632_3.equals("1")) listP632.add("3");
            if (c6_p632_4.equals("1")) listP632.add("4");
            if (c6_p632_5.equals("1")) listP632.add("5");
            if (c6_p632_6.equals("1")) listP632.add("6");
            if (c6_p632_7.equals("1")) listP632.add("7");
            if (c6_p632_8.equals("1")) listP632.add("8");
            if (c6_p632_9.equals("1")) listP632.add("9");
            if (c6_p632_10.equals("1")) listP632.add("10");
            boolean estaEnP632 = false;
            for (int i = 0; i < listP632.size(); i++) {
                if (listP632.get(i).equals(c6_p632i.trim())) {
                    estaEnP632=true;
                }
            }
            if(estaEnP632==false && cant_p2_respondidos > 1){
                mostrarMensaje("ERROR – EL CÓDIGO INGRESADO DEBE ESTAR ENTRE LOS CÓDIGOS SELECCIONADOS DE LA P632"); return false;
            }
           /* if (Integer.parseInt(c6_p632i) > 10){
                mostrarMensaje("ERROR PREGUNTA 632 – EL CÓDIGO DE LA GESTIÓN MAS IMPORTANTE NO PUEDE SER MAYOR A 10"); return false;
            }*/

            ////////////////////////////
        } else {
            c6_p632_1 = "";
            c6_p632_2 = "";
            c6_p632_3 = "";
            c6_p632_4 = "";
            c6_p632_5 = "";
            c6_p632_6 = "";
            c6_p632_7 = "";
            c6_p632_8 = "";
            c6_p632_9 = "";
            c6_p632_10 = "";
            c6_p632_10_o = "";
            c6_p632_11 = "";
            c6_p632i = "";
        }

        //PREGUNTA 633
        if (m6_p633_linearlayout.getVisibility()==View.VISIBLE){
            if (c6_p633.equals("")){
                c6_p633_EditText.requestFocus();
                mostrarMensaje("ERROR PREGUNTA 633 – DEBE INGRESAR CANTIDAD DE SEMANAS"); return false;
            }
        }

        //PREGUNTA 634
        if (m6_p634_linearlayout.getVisibility()==View.VISIBLE && !p634_contestado){
            if (c6_p634_1.equals("0") && c6_p634_2.equals("0") &&
                    c6_p634_3.equals("0") && c6_p634_4.equals("0") &&
                    c6_p634_5.equals("0") && c6_p634_6.equals("0") && c6_p634_7.equals("0"))
            //agregar c8_634_7
                {
                mostrarMensaje("ERROR “PREGUNTA 634 – DEBE SELECCIONAR ALGUNA ALTERNATIVA”");
                return false;
            }
            ////////COMENTADO 27/10/21 OBSERVACION 23
            /*if (c6_p634_6.equals("1")) {
                if (c6_p634_6_o.trim().equals("")) {
                    mostrarMensaje("PREGUNTA 634 - OPCION 06: DEBE ESPECIFICAR OTRO");
                    return false;
                }
                if (c6_p634_6_o.length() < 3) { mostrarMensaje("ERROR  “P643. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false; }
            }*/

            if (c6_p634_7.equals("1")) {
                if (c6_p634_7_o.trim().equals("")) {
                    mostrarMensaje("PREGUNTA 634 - OPCION 07: DEBE ESPECIFICAR OTRO");
                    return false;
                }
                if (c6_p634_7_o.length() < 3) { mostrarMensaje("ERROR  “P634. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false; }
            }
//            //--- PREGUNTA COGNITIVA  p212 != null && p212.equal(1)--//
//            if ( (p212.equals("1") && !(p212.equals(""))) &&
//                    (c6_p634_1.equals("1") || c6_p634_2.equals("1") ||
//                            c6_p634_3.equals("1") || c6_p634_4.equals("1") ||
//                            c6_p634_5.equals("1") || c6_p634_6.equals("1"))) {
//                mostrarMensaje("“SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
//            }


        }else{
            c6_p634_1 = "";
            c6_p634_2 = "";
            c6_p634_3 = "";
            c6_p634_4 = "";
            c6_p634_5 = "";
            c6_p634_6 = "";
            c6_p634_6_o = "";
            c6_p634_7_o = "";
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

    public String NombreMes(int mes){
        String nom_mes="";
        switch(mes){
            case 0: nom_mes="ENERO"; break;
            case 1: nom_mes="FEBRERO"; break;
            case 2: nom_mes="MARZO"; break;
            case 3: nom_mes="ABRIL"; break;
            case 4: nom_mes="MAYO"; break;
            case 5: nom_mes="JUNIO"; break;
            case 6: nom_mes="JULIO"; break;
            case 7: nom_mes="AGOSTO"; break;
            case 8: nom_mes="SETIEMBRE"; break;
            case 9: nom_mes="OCTUBRE"; break;
            case 10: nom_mes="NOVIEMBRE"; break;
            case 11: nom_mes="DICIEMBRE"; break;
        }
        return nom_mes;
    }

//    public void fecha(){
//        Calendar calendario;
//        int mm=0;
//        String fecha_inicial="", fecha_final="";
//        calendario = Calendar.getInstance();
//        mm = calendario.get(Calendar.MONTH);
//        fecha_final = "" + NombreMes(mm);
//        calendario.add(Calendar.MONTH,-6);
//        mm = calendario.get(Calendar.MONTH);
//        fecha_inicial = "" + NombreMes(mm);
//        String enunciado_p629 = c6_p629_TextView.getText()+"";
//        enunciado_p629 = enunciado_p629.replace("FECHAINI", fecha_inicial);
//        enunciado_p629 = enunciado_p629.replace("FECHAFIN", fecha_final);
//        c6_p629_TextView.setText(enunciado_p629);
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
    public void validarOcultarP34(){
        if(edad >= 12 && p18 > 0){
            Log.e("pr","Es mayor a 12 años");
            if(
                    ((p612.equals("4") || p612.equals("5")) && Integer.parseInt(p615_t) >= 15)
                            || (!p612.equals("4") && !p612.equals("5") && !(p607.equals("2") && !p608.equals("1")))
            ){
                Log.e("pr","Muestra p34 - 1");
                m6_p634_linearlayout.setVisibility(View.VISIBLE);
            }else {
                Log.e("pr","OCULTA p34 - 1");
                limpiar634();
                m6_p634_linearlayout.setVisibility(View.GONE);
            }
        }else {
            Log.e("pr","OCULTA p34 - 2");
            limpiar634();
            m6_p634_linearlayout.setVisibility(View.GONE);
        }
    }
    public void flujo86(){
        llenarVariables();
        if(validar_P633()){
            m6_p633_linearlayout.setVisibility(View.VISIBLE);
        }else {
            limpiar633();
            m6_p633_linearlayout.setVisibility(View.GONE);
        }
        if(validar_P634()){
            m6_p634_linearlayout.setVisibility(View.VISIBLE);
        }else{
            limpiar634();
            m6_p634_linearlayout.setVisibility(View.GONE);
        }
    }
    public boolean validar_P630(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 && p629.equals("1")){
            valido= true;
        }
        return valido;
    }
    public boolean validar_P631(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 && c6_p630.equals("1")){
            valido= true;
        }
        return valido;
    }
    public boolean validar_P632(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 && (p627.equals("1") || c6_p631.equals("13"))){
            valido= true;
        }
        return valido;
    }
    public boolean validar_P633(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 &&
                ((p627.equals("1") && c6_p632_11.equals("0") && valid_p632_cualquiera())
                || (p627.equals("2") && (Integer.parseInt(p628) == 1 || Integer.parseInt(p628) == 2))
                || (p627.equals("2") && (Integer.parseInt(p628) >= 3 && Integer.parseInt(p628) <= 8) && c6_p631.equals("13") && c6_p632_11.equals("0") && valid_p632_cualquiera())
                || (p627.equals("2") && (Integer.parseInt(p628) >= 3 && Integer.parseInt(p628) <= 8) && p629.equals("1") && c6_p630.equals("1") && c6_p631.equals("12")))
        ){
            valido= true;
        }
        return valido;
    }
    public boolean validar_P634(){
        boolean valido = false;
        llenarVariables();
        if(p628.equals("")) p628 = "0";
        //Anthony M. 03/06/2021 -> 14/07/2021
        if(p18 > 0 &&
                (p203.equals("1") || edad >= 18)&& (
                (p601.equals("2") && p603.equals("2") && p604.equals("2") && valid_p605_todos("2") &&
                        (   (p627.equals("1") && valid_p632_cualquiera())
                                || (p627.equals("2") && (Integer.parseInt(p628) == 1 || Integer.parseInt(p628) == 2))
                                || (p627.equals("2") && (Integer.parseInt(p628) >= 3 && Integer.parseInt(p628) <= 8) && p629.equals("1") && c6_p630.equals("1") && (
                                c6_p631.equals("12") || (c6_p631.equals("13") && valid_p632_cualquiera())))
                        ))
                        ||
                        (   (p601.equals("1") || p603.equals("1") || p604.equals("1") || valid_p605_alguno("1")) &&
                                (	((p612.equals("1") || p612.equals("2")|| p612.equals("3")|| p612.equals("6")|| p612.equals("7")|| p612.equals("8")) && (
                                        p607.equals("1") || p608.equals("1") || (p607.equals("") && p608.equals(""))))
                                        || ((p612.equals("4") || p612.equals("5")) && Integer.parseInt(p615_t) >= 15)
                                        || ((p612.equals("1") || p612.equals("2")|| p612.equals("3")|| p612.equals("6")|| p612.equals("7")|| p612.equals("8")) &&
                                        p607.equals("2") && (p608.equals("2") || p608.equals("3")) &&
                                        (	(p627.equals("1") && valid_p632_cualquiera())
                                                || (p627.equals("2") && (Integer.parseInt(p628) == 1 || Integer.parseInt(p628) == 2))
                                                || (p627.equals("2") && (Integer.parseInt(p628) >= 3 && Integer.parseInt(p628) <= 8) && p629.equals("1") && c6_p630.equals("1") && (
                                                c6_p631.equals("12") || (c6_p631.equals("13") && valid_p632_cualquiera())))
                                        )
                                )
                                        || ((p612.equals("4") || p612.equals("5") ) && Integer.parseInt(p615_t) < 15 &&
                                        ( 	(p627.equals("1") && valid_p632_cualquiera())
                                                || (p627.equals("2") && (Integer.parseInt(p628) == 1 || Integer.parseInt(p628) == 2))
                                                || (p627.equals("2") && (Integer.parseInt(p628) >= 3 && Integer.parseInt(p628) <= 8) && p629.equals("1") && c6_p630.equals("1") && (
                                                c6_p631.equals("12") || (c6_p631.equals("13") && valid_p632_cualquiera())))
                                        )
                                )
                                )
                        )
           )
        ){
            valido= true;
        }
        return valido;
    }

    public boolean valid_p632_cualquiera(){
        if((c6_p632_1.equals("1") || c6_p632_2.equals("1") || c6_p632_3.equals("1") || c6_p632_4.equals("1") || c6_p632_5.equals("1") ||
                c6_p632_6.equals("1") || c6_p632_7.equals("1") || c6_p632_8.equals("1") || c6_p632_9.equals("1") || c6_p632_10.equals("1"))

        )
            return true;
        else
            return false;
    }
    public boolean valid_p605_todos(String valor){
        if(p605_1.equals(valor) && p605_2.equals(valor) && p605_3.equals(valor) && p605_4.equals(valor) && p605_5.equals(valor) && p605_6.equals(valor) &&
                p605_7.equals(valor) && p605_8.equals(valor) && p605_9.equals(valor) && p605_10.equals(valor) && p605_11.equals(valor) && p605_12.equals(valor))
            return true;
        else
            return false;
    }
    public boolean valid_p605_alguno(String valor){
        if(p605_1.equals(valor) || p605_2.equals(valor) || p605_3.equals(valor) || p605_4.equals(valor) || p605_5.equals(valor) || p605_6.equals(valor) ||
                p605_7.equals(valor) || p605_8.equals(valor) || p605_9.equals(valor) || p605_10.equals(valor) || p605_11.equals(valor) || p605_12.equals(valor))
            return true;
        else
            return false;
    }
    public void achurar_p632(){
        llenarVariables();
        if(c6_p632_1.equals("1") || c6_p632_2.equals("1") || c6_p632_3.equals("1") || c6_p632_4.equals("1") || c6_p632_5.equals("1") ||
                c6_p632_6.equals("1") || c6_p632_7.equals("1") || c6_p632_8.equals("1") || c6_p632_9.equals("1") || c6_p632_10.equals("1")) {
            c6_p632_11_Checkbox.setChecked(false); c6_p632_11_Checkbox.setEnabled(false);
        }else{
            c6_p632_11_Checkbox.setEnabled(true);
        }
        if(c6_p632_11.equals("1")){
            c6_p632_1_Checkbox.setChecked(false); c6_p632_1_Checkbox.setEnabled(false);
            c6_p632_2_Checkbox.setChecked(false); c6_p632_2_Checkbox.setEnabled(false);
            c6_p632_3_Checkbox.setChecked(false); c6_p632_3_Checkbox.setEnabled(false);
            c6_p632_4_Checkbox.setChecked(false); c6_p632_4_Checkbox.setEnabled(false);
            c6_p632_5_Checkbox.setChecked(false); c6_p632_5_Checkbox.setEnabled(false);
            c6_p632_6_Checkbox.setChecked(false); c6_p632_6_Checkbox.setEnabled(false);
            c6_p632_7_Checkbox.setChecked(false); c6_p632_7_Checkbox.setEnabled(false);
            c6_p632_8_Checkbox.setChecked(false); c6_p632_8_Checkbox.setEnabled(false);
            c6_p632_9_Checkbox.setChecked(false); c6_p632_9_Checkbox.setEnabled(false);
            c6_p632_10_Checkbox.setChecked(false); c6_p632_10_Checkbox.setEnabled(false);
        }else{
            c6_p632_1_Checkbox.setEnabled(true);
            c6_p632_2_Checkbox.setEnabled(true);
            c6_p632_3_Checkbox.setEnabled(true);
            c6_p632_4_Checkbox.setEnabled(true);
            c6_p632_5_Checkbox.setEnabled(true);
            c6_p632_6_Checkbox.setEnabled(true);
            c6_p632_7_Checkbox.setEnabled(true);
            c6_p632_8_Checkbox.setEnabled(true);
            c6_p632_9_Checkbox.setEnabled(true);
            c6_p632_10_Checkbox.setEnabled(true);
        }
    }
}