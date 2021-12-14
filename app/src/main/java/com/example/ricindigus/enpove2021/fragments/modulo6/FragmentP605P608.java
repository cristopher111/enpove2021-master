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
import android.widget.TextView;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo6;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOFragment;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOLayout;
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
public class FragmentP605P608 extends FragmentPagina {
    String idEncuestado;
    String idVivienda, idHogar, idInformante, id_informante="";
    Context context;
    Spinner informanteSpinner;

    //PREGUNTA 5
    RadioGroup c6_p605_1_RadioGroup, c6_p605_2_RadioGroup, c6_p605_3_RadioGroup, c6_p605_4_RadioGroup,
            c6_p605_5_RadioGroup, c6_p605_6_RadioGroup, c6_p605_7_RadioGroup, c6_p605_8_RadioGroup,
            c6_p605_9_RadioGroup, c6_p605_10_RadioGroup, c6_p605_11_RadioGroup, c6_p605_12_RadioGroup;
    EditText c6_p605_o_EditText;
    LinearLayout m6_p605_linearlayout;

    TextView tv606periodo;

    private String c6_p605_1;
    private String c6_p605_2;
    private String c6_p605_3;
    private String c6_p605_4;
    private String c6_p605_5;
    private String c6_p605_6;
    private String c6_p605_7;
    private String c6_p605_8;
    private String c6_p605_9;
    private String c6_p605_10;
    private String c6_p605_11;
    private String c6_p605_12;
    private String c6_p605_o;

    //PREGUNTA 6
    RadioGroup c6_p606_1_RadioGroup;
    EditText  c6_p606_o_EditText;
    LinearLayout m6_p606_linearlayout;

    private String c6_p606;
    private String c6_p606_o;

    //PREGUNTA 7
    RadioGroup c6_p607_1_RadioGroup;
    LinearLayout m6_p607_linearlayout;
    private String c6_p607;

    //PREGUNTA 8
    RadioGroup c6_p608_1_RadioGroup;
    LinearLayout m6_p608_linearlayout;
    private String c6_p608;

//    //PREGUNTA 9
//    EditText c6_p609_o_EditText;
//    LinearLayout m6_p609_linearlayout;
//    private String c6_p609;

    private String c2_p203="",c6_p601="",c6_p602="",c6_p603="";
//    EditText c6_p605_EditText, c6_p606_EditText, c6_p607_EditText;
//    RadioGroup c6_p608_RadioGroup;
//    EditText c6_p608_o_EditText;
//    LinearLayout m6_p605_linearlayout, m6_p606_linearlayout, m6_p607_linearlayout, m6_p608_linearlayout;

//    private String c2_p203="",c6_p601="",c6_p602="",c6_p603="";
//    private String c6_p604_1="", c6_p604_4="", c6_p604_6="", c6_p604_8="", c6_p604_9="", c6_p604_10="";
//    private String c6_p605;
//    private String c6_p606;
//    private String c6_p607;
//    private String c6_p608;
//    private String c6_p608_o;
    int edad=0;
    private String p604,p603,p608;

    @SuppressLint("ValidFragment")
    public FragmentP605P608(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idHogar = residente.getId_hogar();
        idVivienda = residente.getId_vivienda();
        idInformante = "";
        c2_p203 = residente.getC2_p203();
        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());

        p604 = data.getModulo6(idEncuestado).getC6_p604_1();
        p603 = data.getModulo6(idEncuestado).getC6_p603();

        data.close();
    }

    public FragmentP605P608() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p605_p608, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

         c6_p605_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_605_radiogroup_C6_P605_1);
         c6_p605_2_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_605_radiogroup_C6_P605_2);
         c6_p605_3_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_605_radiogroup_C6_P605_3);
         c6_p605_4_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_605_radiogroup_C6_P605_4);
         c6_p605_5_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_605_radiogroup_C6_P605_5);
         c6_p605_6_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_605_radiogroup_C6_P605_6);
         c6_p605_7_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_605_radiogroup_C6_P605_7);
         c6_p605_8_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_605_radiogroup_C6_P605_8);
         c6_p605_9_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_605_radiogroup_C6_P605_9);
         c6_p605_10_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_605_radiogroup_C6_P605_10);
         c6_p605_11_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_605_radiogroup_C6_P605_11);
         c6_p605_12_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_605_radiogroup_C6_P605_12);
         c6_p605_o_EditText = (EditText) rootView.findViewById(R.id.mod6_605_edittext_C6_P605_O);

         c6_p606_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_606_radiogroup_C6_P606);
         c6_p606_o_EditText = (EditText) rootView.findViewById(R.id.mod6_606_edittext_C6_P606_O);

         c6_p607_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_607_radiogroup_C6_P607);

        c6_p608_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_608_radiogroup_C6_P608);

        tv606periodo = (TextView) rootView.findViewById(R.id.tv606periodo);

//        c6_p609_o_EditText = (EditText) rootView.findViewById(R.id.mod6_609_edittext_C6_P609);

         m6_p605_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p605);
         m6_p606_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p606);
         m6_p607_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p607);
         m6_p608_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p608);
//         m6_p609_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p609);
//     //   c6_p605_EditText = (EditText) rootView.findViewById(R.id.mod6_605_edittext_C6_P605);
//        // c6_p606_EditText = (EditText) rootView.findViewById(R.id.mod6_606_edittext_C6_P606);
//        //  c6_p607_EditText = (EditText) rootView.findViewById(R.id.mod6_607_edittext_C6_P607);
//        c6_p608_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_608_radiogroup_C6_P608);
//        //   c6_p608_o_EditText = (EditText) rootView.findViewById(R.id.mod6_608_edittext_C6_P608_O);
//
//        m6_p605_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p605);
//        m6_p606_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p606);
//        m6_p607_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p607);
//        m6_p608_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p608);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String text606 = getString(R.string.modulo_6_p606, UtilsMethods.getPeriodoReferenciaSemana(1));
        tv606periodo.setText(text606);

        c6_p605_12_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c6_p605_12_RadioGroup.findViewById(c6_p605_12_RadioGroup.getCheckedRadioButtonId()));
                if(pos==1){
                    c6_p605_o_EditText.setEnabled(true);
                    c6_p605_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    c6_p605_o_EditText.setText("");
                    c6_p605_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    c6_p605_o_EditText.setEnabled(false);
                }
            }
        });

        configurarEditText(c6_p605_o_EditText,m6_p605_linearlayout,0,100);


        c6_p606_1_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,12,c6_p606_o_EditText);
                validarFlujo72();
            }
        });

        c6_p607_1_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c6_p607_1_RadioGroup.findViewById(c6_p607_1_RadioGroup.getCheckedRadioButtonId()));
//                SI P607=1 Entonces pase a P609
//                SI P607=2 Entonces pase a P608
                if(pos==1){
                    limpiar_p608();
                    m6_p608_linearlayout.setVisibility(View.GONE);
                }else{
                    m6_p608_linearlayout.setVisibility(View.VISIBLE);
                }
            }
        });


        configurarEditText(c6_p606_o_EditText,m6_p606_linearlayout,0,50);

//        configurarEditText(c6_p609_o_EditText,m6_p609_linearlayout,1,50);

//        configurarEditText(c6_p605_EditText,m6_p605_linearlayout,1,50);
//        configurarEditText(c6_p606_EditText,m6_p606_linearlayout,1,500);
//        configurarEditText(c6_p607_EditText,m6_p607_linearlayout,1,50);
//        configurarEditText(c6_p608_o_EditText,m6_p608_linearlayout,0,30);
//
//        c6_p608_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
//                int pos = radioGroup.indexOfChild(c6_p608_RadioGroup.findViewById(c6_p608_RadioGroup.getCheckedRadioButtonId()));
//                if(pos==7){
//                    c6_p608_o_EditText.setEnabled(true);
//                    c6_p608_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
//                }else{
//                    c6_p608_o_EditText.setText("");
//                    c6_p608_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
//                    c6_p608_o_EditText.setEnabled(false);
//                }
//            }
//        });
//        llenarVista();
//        cargarDatos();

        llenarVista();
        cargarDatos();

    }

    private void validarFlujo72() {
        llenarVariables();
//        : SI P606=i Entonces pase a P609, para cualquier i=1,2,3,4
//        SI P606=j Entonces pase a P607, para cualquier j=5,6,7,8,9,10,11

        if(c6_p606.equals("1") || c6_p606.equals("2") || c6_p606.equals("3") || c6_p606.equals("4")){
            limpiar_p607();
            m6_p607_linearlayout.setVisibility(View.GONE);
            limpiar_p608();
            m6_p608_linearlayout.setVisibility(View.GONE);
        }else if(c6_p606.equals("5") || c6_p606.equals("6")
                || c6_p606.equals("7") || c6_p606.equals("8")
                || c6_p606.equals("9") || c6_p606.equals("10")
                || c6_p606.equals("11") || c6_p606.equals("12")){
            m6_p607_linearlayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo6_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo6_c6_p605_1,c6_p605_1);
        contentValues.put(SQLConstantes.modulo6_c6_p605_2,c6_p605_2);
        contentValues.put(SQLConstantes.modulo6_c6_p605_3,c6_p605_3);
        contentValues.put(SQLConstantes.modulo6_c6_p605_4,c6_p605_4);
        contentValues.put(SQLConstantes.modulo6_c6_p605_5,c6_p605_5);
        contentValues.put(SQLConstantes.modulo6_c6_p605_6,c6_p605_6);
        contentValues.put(SQLConstantes.modulo6_c6_p605_7,c6_p605_7);
        contentValues.put(SQLConstantes.modulo6_c6_p605_8,c6_p605_8);
        contentValues.put(SQLConstantes.modulo6_c6_p605_9,c6_p605_9);
        contentValues.put(SQLConstantes.modulo6_c6_p605_10,c6_p605_10);
        contentValues.put(SQLConstantes.modulo6_c6_p605_11,c6_p605_11);
        contentValues.put(SQLConstantes.modulo6_c6_p605_12,c6_p605_12);
        contentValues.put(SQLConstantes.modulo6_c6_p605_o,c6_p605_o);

        contentValues.put(SQLConstantes.modulo6_c6_p606,c6_p606);
        contentValues.put(SQLConstantes.modulo6_c6_p606_o,c6_p606_o);

        contentValues.put(SQLConstantes.modulo6_c6_p607,c6_p607);

        contentValues.put(SQLConstantes.modulo6_c6_p608,c6_p608);

//        contentValues.put(SQLConstantes.modulo6_c6_p609,c6_p609);

        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp605p608,"1",idEncuestado);
        //ocultamos o mostramos preguntas o fragments}

        //ocultarOtrosLayouts();  ----------------->>>>>>>>>>>>>>>MIRAR Y LUEGO DESCOMENTAR PARA OCUALTAR LOS LAYOUT

        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();
        ocultarOtrosLayouts();
//        Data data = new Data(context);
//        data.open();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(SQLConstantes.modulo6_id_informante,idInformante);
//        contentValues.put(SQLConstantes.modulo6_c6_p605,c6_p605);
//        contentValues.put(SQLConstantes.modulo6_c6_p606,c6_p606);
//        contentValues.put(SQLConstantes.modulo6_c6_p607,c6_p607);
//        contentValues.put(SQLConstantes.modulo6_c6_p608,c6_p608+"");
//        contentValues.put(SQLConstantes.modulo6_c6_p608_o,c6_p608_o);
//        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
//        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
//        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp605p608,"1",idEncuestado);
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
//        id_informante = idHogar + "_" + infor_id[0];

        c6_p605_1 = c6_p605_1_RadioGroup.indexOfChild(c6_p605_1_RadioGroup.findViewById(c6_p605_1_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p605_2 = c6_p605_2_RadioGroup.indexOfChild(c6_p605_2_RadioGroup.findViewById(c6_p605_2_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p605_3 = c6_p605_3_RadioGroup.indexOfChild(c6_p605_3_RadioGroup.findViewById(c6_p605_3_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p605_4 = c6_p605_4_RadioGroup.indexOfChild(c6_p605_4_RadioGroup.findViewById(c6_p605_4_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p605_5 = c6_p605_5_RadioGroup.indexOfChild(c6_p605_5_RadioGroup.findViewById(c6_p605_5_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p605_6 = c6_p605_6_RadioGroup.indexOfChild(c6_p605_6_RadioGroup.findViewById(c6_p605_6_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p605_7 = c6_p605_7_RadioGroup.indexOfChild(c6_p605_7_RadioGroup.findViewById(c6_p605_7_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p605_8 = c6_p605_8_RadioGroup.indexOfChild(c6_p605_8_RadioGroup.findViewById(c6_p605_8_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p605_9 = c6_p605_9_RadioGroup.indexOfChild(c6_p605_9_RadioGroup.findViewById(c6_p605_9_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p605_10 = c6_p605_10_RadioGroup.indexOfChild(c6_p605_10_RadioGroup.findViewById(c6_p605_10_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p605_11 = c6_p605_11_RadioGroup.indexOfChild(c6_p605_11_RadioGroup.findViewById(c6_p605_11_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p605_12 = c6_p605_12_RadioGroup.indexOfChild(c6_p605_12_RadioGroup.findViewById(c6_p605_12_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p605_o = c6_p605_o_EditText.getText().toString();

        c6_p606 = c6_p606_1_RadioGroup.indexOfChild(c6_p606_1_RadioGroup.findViewById(c6_p606_1_RadioGroup.getCheckedRadioButtonId()))+"";
        c6_p606_o = c6_p606_o_EditText.getText().toString();

        c6_p607 = c6_p607_1_RadioGroup.indexOfChild(c6_p607_1_RadioGroup.findViewById(c6_p607_1_RadioGroup.getCheckedRadioButtonId()))+"";

        c6_p608 = c6_p608_1_RadioGroup.indexOfChild(c6_p608_1_RadioGroup.findViewById(c6_p608_1_RadioGroup.getCheckedRadioButtonId()))+"";

//        c6_p609 = c6_p609_o_EditText.getText().toString();


//        idInformante = informanteSpinner.getSelectedItemPosition()+"";
//        String[] infor_id = (informanteSpinner.getItemAtPosition(informanteSpinner.getSelectedItemPosition()).toString()).split("-");
//        id_informante = idHogar + "_" + infor_id[0];
//        c6_p605 = c6_p605_EditText.getText().toString();
//        c6_p606 = c6_p606_EditText.getText().toString();
//        c6_p607 = c6_p607_EditText.getText().toString();
//        c6_p608 = c6_p608_RadioGroup.indexOfChild(c6_p608_RadioGroup.findViewById(c6_p608_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p608_o = c6_p608_o_EditText.getText().toString();
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

            if(!modulo6.getC6_p605_1().equals("-1") && !modulo6.getC6_p605_1().equals(""))((RadioButton)c6_p605_1_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p605_1()))).setChecked(true);
            if(!modulo6.getC6_p605_2().equals("-1") && !modulo6.getC6_p605_2().equals(""))((RadioButton)c6_p605_2_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p605_2()))).setChecked(true);
            if(!modulo6.getC6_p605_3().equals("-1") && !modulo6.getC6_p605_3().equals(""))((RadioButton)c6_p605_3_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p605_3()))).setChecked(true);
            if(!modulo6.getC6_p605_4().equals("-1") && !modulo6.getC6_p605_4().equals(""))((RadioButton)c6_p605_4_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p605_4()))).setChecked(true);
            if(!modulo6.getC6_p605_5().equals("-1") && !modulo6.getC6_p605_5().equals(""))((RadioButton)c6_p605_5_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p605_5()))).setChecked(true);
            if(!modulo6.getC6_p605_6().equals("-1") && !modulo6.getC6_p605_6().equals(""))((RadioButton)c6_p605_6_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p605_6()))).setChecked(true);
            if(!modulo6.getC6_p605_7().equals("-1") && !modulo6.getC6_p605_7().equals(""))((RadioButton)c6_p605_7_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p605_7()))).setChecked(true);
            if(!modulo6.getC6_p605_8().equals("-1") && !modulo6.getC6_p605_8().equals(""))((RadioButton)c6_p605_8_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p605_8()))).setChecked(true);
            if(!modulo6.getC6_p605_9().equals("-1") && !modulo6.getC6_p605_9().equals(""))((RadioButton)c6_p605_9_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p605_9()))).setChecked(true);
            if(!modulo6.getC6_p605_10().equals("-1") && !modulo6.getC6_p605_10().equals(""))((RadioButton)c6_p605_10_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p605_10()))).setChecked(true);
            if(!modulo6.getC6_p605_11().equals("-1") && !modulo6.getC6_p605_11().equals(""))((RadioButton)c6_p605_11_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p605_11()))).setChecked(true);
            if(!modulo6.getC6_p605_12().equals("-1") && !modulo6.getC6_p605_12().equals(""))((RadioButton)c6_p605_12_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p605_12()))).setChecked(true);
            c6_p605_o_EditText.setText(modulo6.getC6_p605_o());

            if(!modulo6.getC6_p606().equals("-1") && !modulo6.getC6_p606().equals(""))((RadioButton)c6_p606_1_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p606()))).setChecked(true);
            c6_p606_o_EditText.setText(modulo6.getC6_p606_o());

            if(!modulo6.getC6_p607().equals("-1") && !modulo6.getC6_p607().equals(""))((RadioButton)c6_p607_1_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p607()))).setChecked(true);

            if(!modulo6.getC6_p608().equals("-1") && !modulo6.getC6_p608().equals(""))((RadioButton)c6_p608_1_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p608()))).setChecked(true);

//            c6_p609_o_EditText.setText(modulo6.getC6_p609());

        }
        // inicio();  CREO QUE ESTE ES PARA LAS FECHAS
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
//            c6_p601 = modulo6.getC6_p601();
//            c6_p602 = modulo6.getC6_p602();
//            c6_p603 = modulo6.getC6_p603();
//            c6_p604_1 = modulo6.getC6_p604_1(); c6_p604_4 = modulo6.getC6_p604_4();
//            c6_p604_6 = modulo6.getC6_p604_6(); c6_p604_8 = modulo6.getC6_p604_8();
//            c6_p604_9 = modulo6.getC6_p604_9(); c6_p604_10 = modulo6.getC6_p604_10();
//            c6_p605_EditText.setText(modulo6.getC6_p605());
//            c6_p606_EditText.setText(modulo6.getC6_p606());
//            c6_p607_EditText.setText(modulo6.getC6_p607());
//            if(!modulo6.getC6_p608().equals("-1") && !modulo6.getC6_p608().equals(""))((RadioButton)c6_p608_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p608()))).setChecked(true);
//            c6_p608_o_EditText.setText(modulo6.getC6_p608_o());
//        }
//        data.close();
    }

    private void ocultarOtrosLayouts() {
        llenarVariables();
        Data data = new Data(context); //FLUJO 71
        data.open();
        //FLUJO 73
        if(c6_p607.equals("1") || c6_p607.equals("2")){
            ocultarFragmentP609P612(false); //Pasa al fargment de P609
        }else
        //FLUJO 72
        if(!c6_p606.equals("-1")){
            ocultarFragmentP609P612(false); //Pasa al fargment de P609
        }else
        //FLUJO 71
        if(c6_p605_1.equals("2") && c6_p605_2.equals("2") && c6_p605_3.equals("2") && c6_p605_4.equals("2") &&
                c6_p605_5.equals("2") && c6_p605_6.equals("2") && c6_p605_7.equals("2") && c6_p605_8.equals("2") &&
                c6_p605_9.equals("2") && c6_p605_10.equals("2") && c6_p605_11.equals("2") && c6_p605_12.equals("2")){
            ocultarFragmentP609P612(true);
            ocultarFragmentP613P617(true);
            ocultarFragmentP618P621(true);
            ocultarFragmentP622P625(false); //Pasa al fargment de P626
        }else
        if(c6_p605_1.equals("2") || c6_p605_2.equals("2") || c6_p605_3.equals("2") || c6_p605_4.equals("2") ||
                c6_p605_5.equals("2") || c6_p605_6.equals("2") || c6_p605_7.equals("2") || c6_p605_8.equals("2") ||
                c6_p605_9.equals("2") || c6_p605_10.equals("2") || c6_p605_11.equals("2")){
            ocultarFragmentP609P612(false); //Pasa al fargment de P609
        }else Log.e("FragmentP605P608","No cumplen condiciones en ocultarOtrosLayouts()");
        data.close();
    }

    private void inicio() {

        if(edad >= 5  && (p604.equals("2"))){
            m6_p605_linearlayout.setVisibility(View.VISIBLE);
        } else {
            limpiar_p605();
            m6_p605_linearlayout.setVisibility(View.GONE);

        }

       // AND P205_A>=5 AND (P603=1 OR P604=1)

        if(edad >= 5  && (p603.equals("1") || p604.equals("1"))){
            m6_p606_linearlayout.setVisibility(View.VISIBLE);
            validarFlujo72();
        } else {
            limpiar_p606();
            m6_p606_linearlayout.setVisibility(View.GONE);
            limpiar_p607();
            m6_p607_linearlayout.setVisibility(View.GONE);
            limpiar_p608();
            m6_p608_linearlayout.setVisibility(View.GONE);
        }



    }

    private void limpiar_p608() {
        c6_p608_1_RadioGroup.clearCheck();
    }

    private void limpiar_p607() {
        c6_p607_1_RadioGroup.clearCheck();
    }

    private void limpiar_p606() {
        c6_p606_1_RadioGroup.clearCheck();
        c6_p606_o_EditText.setText("");
    }

    private void limpiar_p605() {
        c6_p605_1_RadioGroup.clearCheck();
        c6_p605_2_RadioGroup.clearCheck();
        c6_p605_3_RadioGroup.clearCheck();
        c6_p605_4_RadioGroup.clearCheck();
        c6_p605_5_RadioGroup.clearCheck();
        c6_p605_6_RadioGroup.clearCheck();
        c6_p605_7_RadioGroup.clearCheck();
        c6_p605_8_RadioGroup.clearCheck();
        c6_p605_9_RadioGroup.clearCheck();
        c6_p605_10_RadioGroup.clearCheck();
        c6_p605_11_RadioGroup.clearCheck();
        c6_p605_12_RadioGroup.clearCheck();
        c6_p605_o_EditText.setText("");
    }

    @Override
    public void llenarVista() {
        Data data = new Data(context);
        data.open();
        POJOFragment pojoFragment = data.getFragmentsLayouts(idEncuestado);
        POJOLayout pojoLayout = data.getLayouts(idEncuestado);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p605,idEncuestado)) m6_p605_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p606,idEncuestado)) m6_p605_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p607,idEncuestado)) m6_p605_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p608,idEncuestado)) m6_p605_linearlayout.setVisibility(View.GONE);

        data.close();
    }

    @Override
    public boolean validarDatos() {
        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}


        //PREGUNTA 605
        if (m6_p605_linearlayout.getVisibility() == View.VISIBLE){
            if (c6_p605_1.equals("-1")){ mostrarMensaje("PREGUNTA 605: DEBE SELECCIONAR ALGUNA OPCION 1");return false; }
            if (c6_p605_2.equals("-1")){ mostrarMensaje("PREGUNTA 605: DEBE SELECCIONAR ALGUNA OPCION 2");return false; }
            if (c6_p605_3.equals("-1")){ mostrarMensaje("PREGUNTA 605: DEBE SELECCIONAR ALGUNA OPCION 3");return false; }
            if (c6_p605_4.equals("-1")){ mostrarMensaje("PREGUNTA 605: DEBE SELECCIONAR ALGUNA OPCION 4");return false; }
            if (c6_p605_5.equals("-1")){ mostrarMensaje("PREGUNTA 605: DEBE SELECCIONAR ALGUNA OPCION 5");return false; }
            if (c6_p605_6.equals("-1")){ mostrarMensaje("PREGUNTA 605: DEBE SELECCIONAR ALGUNA OPCION 6");return false; }
            if (c6_p605_7.equals("-1")){ mostrarMensaje("PREGUNTA 605: DEBE SELECCIONAR ALGUNA OPCION 7");return false; }
            if (c6_p605_8.equals("-1")){ mostrarMensaje("PREGUNTA 605: DEBE SELECCIONAR ALGUNA OPCION 8");return false; }
            if (c6_p605_9.equals("-1")){ mostrarMensaje("PREGUNTA 605: DEBE SELECCIONAR ALGUNA OPCION 9");return false; }
            if (c6_p605_10.equals("-1")){ mostrarMensaje("PREGUNTA 605: DEBE SELECCIONAR ALGUNA OPCION 10");return false; }
            if (c6_p605_11.equals("-1")){ mostrarMensaje("PREGUNTA 605: DEBE SELECCIONAR ALGUNA OPCION 11");return false; }
            if (c6_p605_12.equals("-1")){ mostrarMensaje("PREGUNTA 605: DEBE SELECCIONAR ALGUNA OPCION 12");return false; }
            if (c6_p605_12.equals("1")){
                if (c6_p605_o.trim().equals("")){ mostrarMensaje("PREGUNTA 605 - OPCION 12: DEBE ESPECIFICAR OTRO");return false;}
            }
            if(c6_p605_12.equals("1") && !c6_p605_o.equals("") && c6_p605_o.length() < 3){mostrarMensaje("PREGUNTA 605: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}
        }else{
            c6_p605_1 = "";
            c6_p605_2 = "";
            c6_p605_3 = "";
            c6_p605_4 = "";
            c6_p605_5 = "";
            c6_p605_6 = "";
            c6_p605_7 = "";
            c6_p605_8 = "";
            c6_p605_9 = "";
            c6_p605_10 = "";
            c6_p605_11 = "";
            c6_p605_12 = "";
            c6_p605_o = "";
        }

        //PREGUNTA 606
        if (m6_p606_linearlayout.getVisibility() == View.VISIBLE){
            if (c6_p606.equals("-1")){mostrarMensaje("PREGUNTA 606: DEBE MARCAR UNA OPCIÓN"); return false;}
            if (c6_p606.equals("12")){
                if (c6_p606_o.trim().equals("")){ mostrarMensaje("PREGUNTA 606 - OPCION 12: DEBE ESPECIFICAR OTRO");return false;}
            }
            if(c6_p606.equals("12") && !c6_p606_o.equals("") && c6_p606_o.length() < 3){mostrarMensaje("PREGUNTA 606: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}
        }else{
            c6_p606 ="";
            c6_p606_o="";
        }

        //PREGUNTA 607
        if (m6_p607_linearlayout.getVisibility() == View.VISIBLE){
            if (c6_p607.equals("-1")){mostrarMensaje("PREGUNTA 607: DEBE MARCAR UNA OPCIÓN"); return false;}
        }else{
            c6_p607 ="";
        }

        //PREGUNTA 608
        if (m6_p608_linearlayout.getVisibility() == View.VISIBLE){
            if (c6_p608.equals("-1")){mostrarMensaje("PREGUNTA 608: DEBE MARCAR UNA OPCIÓN"); return false;}
        }else{
            c6_p608 ="";
        }

//
//        if(idInformante.equals("0")) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}
//        if(!id_informante.equals(idEncuestado) && edad>=12){mostrarMensaje("NÚMERO INFORMANTE: NO ES EL MISMO QUE ESTA SIENDO ENTREVISTADO");return false;}
//        if(c6_p605.trim().equals("") && m6_p605_linearlayout.getVisibility()==View.VISIBLE){
//            mostrarMensaje("PREGUNTA 605: DEBE ESPECIFICAR");return false;
//        }
//        if(c6_p606.trim().equals("")&& m6_p606_linearlayout.getVisibility()==View.VISIBLE){
//            mostrarMensaje("PREGUNTA 606: DEBE ESPECIFICAR");return false;
//        }
//        if(c6_p607.trim().equals("") && m6_p607_linearlayout.getVisibility()==View.VISIBLE){
//            mostrarMensaje("PREGUNTA 607: DEBE ESPECIFICAR");return false;
//        }
//        if(m6_p608_linearlayout.getVisibility()==View.VISIBLE){
//            if(c6_p608.equals("-1")){ mostrarMensaje("PREGUNTA 608: DEBE SELECCIONAR UNA OPCION");return false; }
//            int p608=Integer.parseInt(c6_p608);
//            if(c6_p601.equals("2") && c6_p602.equals("1") && p608<3){ mostrarMensaje("PREGUNTA 608: ES EMPLEADOR O INDEPENDIENTE, NO ES DEPENDIENTE PAGADO");return false; }
//            if(c6_p601.equals("2") && c6_p602.equals("2") && c6_p603.equals("1") && (p608==3 || p608==4 || p608==6)){
//                mostrarMensaje("PREGUNTA 608: ES EMPLEADO/ OBRERO/EMPLEADA DEL HOGAR NO TIENE NEGOCIO PROPIO");
//                return false;
//            }
//            if(c6_p601.equals("2") && (c6_p602.equals("1") || c6_p603.equals("1")) && (p608==5 || p608==7)){
//                mostrarMensaje("PREGUNTA 608: ES FAMILIAR NO REMUNERADO DEL HOGAR / ES FAMILIAR NO REMUNERADO DE OTRO HOGAR, ¿TIENE VACACIONES?");
//                return false;
//            }
//            if(c2_p203.equals("9") && (p608!=6)){
//                mostrarMensaje("PREGUNTA 608: P203 =9 PORQUE LA P608 ES DIFERENTE A 6 TRABAJADORA DEL HOGAR");
//            }
//            if(((p608==1 || p608==2) && (c6_p604_1.equals("0") || c6_p604_4.equals("0") || c6_p604_8.equals("0"))) ||
//                    ((p608==3 || p608==4) && (c6_p604_1.equals("0") || c6_p604_6.equals("0") || c6_p604_9.equals("0"))) ||
//                    ((p608==5) && (c6_p604_8.equals("0") || c6_p604_9.equals("0"))) ||
//                    ((p608==6) && (c6_p604_6.equals("0"))) ||
//                    ((p608==7) && (c6_p604_8.equals("0") || c6_p604_10.equals("0")))){
//                mostrarMensaje("PREGUNTA 608: CATEGORÍA OCUPACIONAL NO CORRESPONDE A LA ACTIVIDAD QUE REALIZÓ LA SEMANA PASADA");
//                return false;
//            }
//
//            if(c6_p608.equals("7")){
//                if(c6_p608_o.trim().equals("")){ mostrarMensaje("PREGUNTA 608 - OPCION 7: DEBE ESPECIFICAR OTRO");return false;}
//            }
//        }else{
//            c6_p608 = "";
//            c6_p608_o = "";
//        }
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

//    public void ocultarOtrosLayouts() {
//        Data data = new Data(context);
//        data.open();
//        if (c6_p608.equals("1") || c6_p608.equals("2") || c6_p608.equals("5")){
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.modulo6_c6_p609,"");
//            data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
//
//            contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.layouts_p609,"0");
//            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
//
//        }else{
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.layouts_p609,"1");
//            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
//            data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp609p612,"0",idEncuestado);
//        }
//        POJOLayout pojoLayout = data.getLayouts(idEncuestado);
//
//        if (!c6_p608.equals("3") && !c6_p608.equals("4") && !c6_p608.equals("6")){
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.modulo6_c6_p613,"");
//            contentValues.put(SQLConstantes.modulo6_c6_p614_mon,"");
//            contentValues.put(SQLConstantes.modulo6_c6_p614_esp,"");
//            data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
//
//            contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.layouts_p613,"0");
//            contentValues.put(SQLConstantes.layouts_p614,"0");
//            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
//        }else{
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.layouts_p613,"1");
//            contentValues.put(SQLConstantes.layouts_p614,"1");
//            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
//            data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp613p617,"0",idEncuestado);
//        }
//        POJOLayout pojoLayout1 = data.getLayouts(idEncuestado);
//
//        if (c6_p608.equals("1") || c6_p608.equals("2")){
//            ContentValues contentValues = new ContentValues();
//            if(data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p615,idEncuestado).equals("0")) {
//                contentValues.put(SQLConstantes.layouts_p615, "1");
//                data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
//                data.actualizarValor(SQLConstantes.tablacoberturafragments, SQLConstantes.cobertura_fragments_cp613p617, "0", idEncuestado);
//            }
//        }else{
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.modulo6_c6_p615_esp,"");
//            contentValues.put(SQLConstantes.modulo6_c6_p615_mon,"");
//            data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
//
//            contentValues = new ContentValues();
//            contentValues.put(SQLConstantes.layouts_p615,"0");
//            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
//
//        }
//        POJOLayout pojoLayoutll = data.getLayouts(idEncuestado);
//
//        data.close();
//        ocultar_mostrar_fragments_cp613p617();
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

    public void ocultar_mostrar_fragments_cp613p617(){
        Data data = new Data(context);
        data.open();
        if(data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p613,idEncuestado).equals("0") &&
                data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p614,idEncuestado).equals("0") &&
                data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p615,idEncuestado).equals("0") &&
                data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p616,idEncuestado).equals("0") &&
                data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p617,idEncuestado).equals("0")) {
            if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,idEncuestado).equals("1")){
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,"-1",idEncuestado);
            }
        }else{
            if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,idEncuestado).equals("-1")){
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,"1",idEncuestado);
            }
        }
        data.close();
    }
    private void ocultarFragmentP609P612(Boolean ocultar){
        Data data = new Data(context);
        data.open();
        if(ocultar){
            ContentValues contentValues1 = new ContentValues();
            contentValues1.put(SQLConstantes.modulo6_c6_p609, "");
            contentValues1.put(SQLConstantes.modulo6_c6_p610_pd, "");
            contentValues1.put(SQLConstantes.modulo6_c6_p611, "");
            contentValues1.put(SQLConstantes.modulo6_c6_p612, "");
            contentValues1.put(SQLConstantes.modulo6_c6_p613, "");
            contentValues1.put(SQLConstantes.modulo6_c6_p614_esp, "");
            contentValues1.put(SQLConstantes.modulo6_c6_p614_mon, "");
            data.actualizarElemento(getNombreTabla(),contentValues1,idEncuestado);
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p609p612,"-1", idEncuestado);
        }else {
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p609p612,"1",idEncuestado); //Muestra e Fragment de la P609
        }
        data.close();
    }
    private void ocultarFragmentP613P617(Boolean ocultar){
        Data data = new Data(context);
        data.open();
        if(ocultar){
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.modulo6_c6_p615_pd,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_pl,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_pm,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_pmi,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_pj,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_pv,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_ps,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_pt,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_sd,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_sl,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_sm,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_smi,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_sj,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_sv,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_ss,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_st,"");
            contentValues.put(SQLConstantes.modulo6_c6_p615_t,"");
            contentValues.put(SQLConstantes.modulo6_c6_p616,"");
            contentValues.put(SQLConstantes.modulo6_c6_p616_a,"");
            contentValues.put(SQLConstantes.modulo6_c6_p617,"");
            contentValues.put(SQLConstantes.modulo6_c6_p618,"");
            contentValues.put(SQLConstantes.modulo6_c6_p619,"");
            data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,"-1", idEncuestado);
        }else {
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,"1",idEncuestado); //Muestra e Fragment de la P609
        }
        data.close();
    }
    private void ocultarFragmentP618P621(Boolean ocultar){
        Data data = new Data(context);
        data.open();
        if(ocultar){
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.modulo6_c6_p620, "");
            contentValues.put(SQLConstantes.modulo6_c6_p620_o, "");
            contentValues.put(SQLConstantes.modulo6_c6_p621, "");
            contentValues.put(SQLConstantes.modulo6_c6_p622_mon, "");
            contentValues.put(SQLConstantes.modulo6_c6_p622_esp, "");
            contentValues.put(SQLConstantes.modulo6_c6_p623_mon, "");
            contentValues.put(SQLConstantes.modulo6_c6_p623_esp, "");
            contentValues.put(SQLConstantes.modulo6_c6_p623_nas, "");
            contentValues.put(SQLConstantes.modulo6_c6_p624_mon, "");
            contentValues.put(SQLConstantes.modulo6_c6_p624_esp, "");
            contentValues.put(SQLConstantes.modulo6_c6_p624_nas, "");
            contentValues.put(SQLConstantes.modulo6_c6_p624_nas2, "");
            data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
            data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p618p621, "-1", idEncuestado);
        }else {
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p618p621,"1",idEncuestado); //Muestra e Fragment de la P609
        }
        data.close();
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
}
