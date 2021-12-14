package com.example.ricindigus.enpove2021.fragments.modulo6;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
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
import android.text.TextWatcher;
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
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP609P612 extends FragmentPagina {
    String idEncuestado;
    String idVivienda, idHogar, idInformante, id_informante="";
    Context context;
    Spinner informanteSpinner;

    //PREGUNTA 9
    EditText c6_p609_o_EditText;
    LinearLayout m6_p609_linearlayout;
    private String c6_p609;

    //PREGUNTA 10
    EditText c6_p610_o_EditText;
    private String c6_p610_pd;
    LinearLayout m6_p610_linearlayout;

    //PREGUNTA 11
    EditText c6_p611_o_EditText;
    private String c6_p611;
    LinearLayout m6_p611_linearlayout;

    //PREGUNTA 12
    RadioGroup c6_p612_RadioGroup;
    private String c6_p612;
    LinearLayout m6_p612_linearlayout;

    //PREGUNTA 13
    RadioGroup c6_p613_RadioGroup;
    private String c6_p613;
    LinearLayout m6_p613_linearlayout;

    //PREGUNTA 14
    RadioGroup c6_p614_RadioGroup;
    EditText c6_p614_o_EditText;
    private String c6_p614_mon;
    private String c6_p614_esp;
    LinearLayout m6_p614_linearlayout;



//    TextView c6_p610_TextView;
//
//    RadioGroup c6_p609_RadioGroup;
//    EditText c6_p610_pd_EditText, c6_p610_pl_EditText, c6_p610_pm_EditText, c6_p610_pmi_EditText,
//            c6_p610_pj_EditText, c6_p610_pv_EditText, c6_p610_ps_EditText,
//            c6_p610_sd_EditText, c6_p610_sl_EditText, c6_p610_sm_EditText, c6_p610_smi_EditText,
//            c6_p610_sj_EditText, c6_p610_sv_EditText, c6_p610_ss_EditText;
//    TextView c6_p610_t_TextView;
//    EditText c6_p611_EditText;
//    RadioGroup c6_p611a_RadioGroup;
//    RadioGroup c6_p611b_RadioGroup;
//    RadioGroup c6_p612_RadioGroup;
//    EditText c6_p612_nro_EditText;
//    LinearLayout m6_p609_linearlayout, m6_p610_linearlayout, m6_p611_linearlayout, m6_p611a_linearlayout,
//            m6_p611b_linearlayout, m6_p612_linearlayout;

//    private String c6_p604_1;
//    private String c6_p608;
//    private String c6_p609;
//    private String c6_p610_pd;
//    private String c6_p610_pl;
//    private String c6_p610_pm;
//    private String c6_p610_pmi;
//    private String c6_p610_pj;
//    private String c6_p610_pv;
//    private String c6_p610_ps;
//    private String c6_p610_pt;
//    private String c6_p610_sd;
//    private String c6_p610_sl;
//    private String c6_p610_sm;
//    private String c6_p610_smi;
//    private String c6_p610_sj;
//    private String c6_p610_sv;
//    private String c6_p610_ss;
//    private String c6_p610_st;
//    private String c6_p610_t;
//    private String c6_p611;
//    private String c6_p611a;
//    private String c6_p611b;

//    private String c6_p612;
//    private String c6_p612_nro;

//    private int p610_pd=0;
//    private int p610_pl=0;
//    private int p610_pm=0;
//    private int p610_pmi=0;
//    private int p610_pj=0;
//    private int p610_pv=0;
//    private int p610_ps=0;
//    private int p610_pt=0;
//    private int p610_sd=0;
//    private int p610_sl=0;
//    private int p610_sm=0;
//    private int p610_smi=0;
//    private int p610_sj=0;
//    private int p610_sv=0;
//    private int p610_ss=0;
//    private int p610_st=0;
//    private int p610_t=0;
    private String p203,p604,p603;

    int edad=0;

    @SuppressLint("ValidFragment")
    public FragmentP609P612(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idHogar = residente.getId_hogar();
        idVivienda = residente.getId_vivienda();
        idInformante = "";
        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());

        p203 = data.getResidente(idEncuestado).getC2_p203();
        p603 = data.getModulo6(idEncuestado).getC6_p603();
        p604 = data.getModulo6(idEncuestado).getC6_p604_1();

        data.close();
    }

    public FragmentP609P612() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p609_p612, container, false);
        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

        c6_p610_o_EditText =  (EditText) rootView.findViewById(R.id.mod6_610_edittext_C6_P610);

        c6_p611_o_EditText =  (EditText) rootView.findViewById(R.id.mod6_611_edittext_C6_P611);

        c6_p612_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_612_radiogroup_C6_P612);

        c6_p613_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_613_radiogroup_C6_P613);

        c6_p614_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_614_radiogroup_C6_P614);
        c6_p614_o_EditText =  (EditText) rootView.findViewById(R.id.mod6_614_edittext_C6_P614);

        c6_p609_o_EditText = (EditText) rootView.findViewById(R.id.mod6_609_edittext_C6_P609);
        m6_p609_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p609);

        m6_p610_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p610);
        m6_p611_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p611);
        m6_p612_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p612);
        m6_p613_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p613);
        m6_p614_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p614);

        // c6_p609_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_609_radiogroup_C6_P609);

        //   c6_p610_TextView = (TextView) rootView.findViewById(R.id.mod6_601_textview_C6_P610);
        //   c6_p610_pd_EditText = (EditText) rootView.findViewById(R.id.mod6_610_edittext_C6_P610_PD);
        //  c6_p610_pl_EditText = (EditText) rootView.findViewById(R.id.mod6_610_edittext_C6_P610_PL);
        //   c6_p610_pm_EditText = (EditText) rootView.findViewById(R.id.mod6_610_edittext_C6_P610_PM);
        //  c6_p610_pmi_EditText = (EditText) rootView.findViewById(R.id.mod6_610_edittext_C6_P610_PMI);
        //  c6_p610_pj_EditText = (EditText) rootView.findViewById(R.id.mod6_610_edittext_C6_P610_PJ);
        //  c6_p610_pv_EditText = (EditText) rootView.findViewById(R.id.mod6_610_edittext_C6_P610_PV);
        //  c6_p610_ps_EditText = (EditText) rootView.findViewById(R.id.mod6_610_edittext_C6_P610_PS);

        // c6_p610_sd_EditText = (EditText) rootView.findViewById(R.id.mod6_610_edittext_C6_P610_SD);
        // c6_p610_sl_EditText = (EditText) rootView.findViewById(R.id.mod6_610_edittext_C6_P610_SL);
        //  c6_p610_sm_EditText = (EditText) rootView.findViewById(R.id.mod6_610_edittext_C6_P610_SM);
        //  c6_p610_smi_EditText = (EditText) rootView.findViewById(R.id.mod6_610_edittext_C6_P610_SMI);
        //  c6_p610_sj_EditText = (EditText) rootView.findViewById(R.id.mod6_610_edittext_C6_P610_SJ);
        //  //  c6_p610_sv_EditText = (EditText) rootView.findViewById(R.id.mod6_610_edittext_C6_P610_SV);
        //  c6_p610_ss_EditText = (EditText) rootView.findViewById(R.id.mod6_610_edittext_C6_P610_SS);

        //  c6_p610_t_TextView = (TextView) rootView.findViewById(R.id.mod6_610_textview_C6_P610_T);

//        c6_p611_EditText = (EditText) rootView.findViewById(R.id.mod6_611_edittext_C6_P611);
//
//        c6_p611a_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_611a_radiogroup_C6_P611a);
//        c6_p611b_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_611b_radiogroup_C6_P611b);
//
//        c6_p612_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_612_radiogroup_C6_P612);
//
//        //c6_p612_nro_EditText = (EditText) rootView.findViewById(R.id.mod6_612_edittext_C6_P612_NRO);
//
//        m6_p609_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p609);
//        m6_p610_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p610);
//        m6_p611_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p611);
//        //  m6_p611a_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p611a);
//        //  m6_p611b_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p611b);
//        m6_p612_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p612);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        configurarEditText(c6_p610_o_EditText,m6_p610_linearlayout,0,100);

        configurarEditText(c6_p611_o_EditText,m6_p611_linearlayout,0,100);

        configurarEditText(c6_p609_o_EditText,m6_p609_linearlayout,0,100);

        configurarEditText(c6_p614_o_EditText,m6_p614_linearlayout,2,4);

        c6_p612_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                validarFlujo74();
            }
        });

        llenarVista();
        cargarDatos();
//
//        configurarEditText(c6_p610_pd_EditText,m6_p610_linearlayout,3,2);
//        configurarEditText(c6_p610_pl_EditText,m6_p610_linearlayout,3,2);
//        configurarEditText(c6_p610_pm_EditText,m6_p610_linearlayout,3,2);
//        configurarEditText(c6_p610_pmi_EditText,m6_p610_linearlayout,3,2);
//        configurarEditText(c6_p610_pj_EditText,m6_p610_linearlayout,3,2);
//        configurarEditText(c6_p610_pv_EditText,m6_p610_linearlayout,3,2);
//        configurarEditText(c6_p610_ps_EditText,m6_p610_linearlayout,3,2);
//
//        configurarEditText(c6_p610_sd_EditText,m6_p610_linearlayout,3,2);
//        configurarEditText(c6_p610_sl_EditText,m6_p610_linearlayout,3,2);
//        configurarEditText(c6_p610_sm_EditText,m6_p610_linearlayout,3,2);
//        configurarEditText(c6_p610_smi_EditText,m6_p610_linearlayout,3,2);
//        configurarEditText(c6_p610_sj_EditText,m6_p610_linearlayout,3,2);
//        configurarEditText(c6_p610_sv_EditText,m6_p610_linearlayout,3,2);
//        configurarEditText(c6_p610_ss_EditText,m6_p610_linearlayout,3,2);
//
//
//        configurarEditText(c6_p611_EditText,m6_p611_linearlayout,2,3);
//        configurarEditText(c6_p612_nro_EditText,m6_p612_linearlayout,2,2);
//
//
//        configurarTextWatcher(c6_p610_pd_EditText);
//        configurarTextWatcher(c6_p610_pl_EditText);
//        configurarTextWatcher(c6_p610_pm_EditText);
//        configurarTextWatcher(c6_p610_pmi_EditText);
//        configurarTextWatcher(c6_p610_pj_EditText);
//        configurarTextWatcher(c6_p610_pv_EditText);
//        configurarTextWatcher(c6_p610_ps_EditText);
//        configurarTextWatcher(c6_p610_sd_EditText);
//        configurarTextWatcher(c6_p610_sl_EditText);
//        configurarTextWatcher(c6_p610_sm_EditText);
//        configurarTextWatcher(c6_p610_smi_EditText);
//        configurarTextWatcher(c6_p610_sj_EditText);
//        configurarTextWatcher(c6_p610_sv_EditText);
//        configurarTextWatcher(c6_p610_ss_EditText);
//
//        c6_p612_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                controlarEspecifiqueRadio(group,checkedId,1,c6_p612_nro_EditText);
//            }
//        });
//        fecha();
//        llenarVista();
//        cargarDatos();
    }

    @Override
    public void guardarDatos() {

        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo6_id_informante,idInformante);

        contentValues.put(SQLConstantes.modulo6_c6_p609,c6_p609);

        contentValues.put(SQLConstantes.modulo6_c6_p610_pd,c6_p610_pd);

        contentValues.put(SQLConstantes.modulo6_c6_p611,c6_p611);

        contentValues.put(SQLConstantes.modulo6_c6_p612,c6_p612);

        contentValues.put(SQLConstantes.modulo6_c6_p613,c6_p613);

        contentValues.put(SQLConstantes.modulo6_c6_p614_esp,c6_p614_esp);
        contentValues.put(SQLConstantes.modulo6_c6_p614_mon,c6_p614_mon);


        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp609p612,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,"1",idEncuestado); //Muestra el siguiente fragment
        data.close();

//        Data data = new Data(context);
//        data.open();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(SQLConstantes.modulo6_id_informante,idInformante);
//        contentValues.put(SQLConstantes.modulo6_c6_p609,c6_p609);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_pd,c6_p610_pd);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_pl,c6_p610_pl);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_pm,c6_p610_pm);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_pmi,c6_p610_pmi);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_pj,c6_p610_pj);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_pv,c6_p610_pv);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_ps,c6_p610_ps);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_pt,c6_p610_pt);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_sd,c6_p610_sd);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_sl,c6_p610_sl);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_sm,c6_p610_sm);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_smi,c6_p610_smi);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_sj,c6_p610_sj);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_sv,c6_p610_sv);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_ss,c6_p610_ss);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_st,c6_p610_st);
//        contentValues.put(SQLConstantes.modulo6_c6_p610_t,c6_p610_t);
//        contentValues.put(SQLConstantes.modulo6_c6_p611,c6_p611);
//        contentValues.put(SQLConstantes.modulo6_c6_p611a,c6_p611a);
//        contentValues.put(SQLConstantes.modulo6_c6_p611b,c6_p611b);
//        contentValues.put(SQLConstantes.modulo6_c6_p612,c6_p612);
//        contentValues.put(SQLConstantes.modulo6_c6_p612_nro,c6_p612_nro);
//        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
//        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
//        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp609p612,"1",idEncuestado);
//        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
//        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"1",idEncuestado);
//        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"0",idEncuestado);
//        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
//        data.close();
    }

    @Override
    public void llenarVariables() {

        idInformante = obtener_Nresidente(informanteSpinner);

          c6_p610_pd = c6_p610_o_EditText.getText().toString();

          c6_p611 = c6_p611_o_EditText.getText().toString();

          c6_p612 = c6_p612_RadioGroup.indexOfChild(c6_p612_RadioGroup.findViewById(c6_p612_RadioGroup.getCheckedRadioButtonId()))+"";

          c6_p613 = c6_p613_RadioGroup.indexOfChild(c6_p613_RadioGroup.findViewById(c6_p613_RadioGroup.getCheckedRadioButtonId()))+"";

          c6_p614_esp = c6_p614_RadioGroup.indexOfChild(c6_p614_RadioGroup.findViewById(c6_p614_RadioGroup.getCheckedRadioButtonId()))+"";
          c6_p614_mon  = c6_p614_o_EditText.getText().toString();

        c6_p609 = c6_p609_o_EditText.getText().toString();


//        idInformante = informanteSpinner.getSelectedItemPosition() + "";
//        String[] infor_id = (informanteSpinner.getItemAtPosition(informanteSpinner.getSelectedItemPosition()).toString()).split("-");
//        id_informante = idHogar + "_" + infor_id[0];
//        c6_p609 = c6_p609_RadioGroup.indexOfChild(c6_p609_RadioGroup.findViewById(c6_p609_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p610_pd = c6_p610_pd_EditText.getText().toString();
//        c6_p610_pl = c6_p610_pl_EditText.getText().toString();
//        c6_p610_pm = c6_p610_pm_EditText.getText().toString();
//        c6_p610_pmi = c6_p610_pmi_EditText.getText().toString();
//        c6_p610_pj = c6_p610_pj_EditText.getText().toString();
//        c6_p610_pv = c6_p610_pv_EditText.getText().toString();
//        c6_p610_ps = c6_p610_ps_EditText.getText().toString();
//        c6_p610_sd = c6_p610_sd_EditText.getText().toString();
//        c6_p610_sl = c6_p610_sl_EditText.getText().toString();
//        c6_p610_sm = c6_p610_sm_EditText.getText().toString();
//        c6_p610_smi = c6_p610_smi_EditText.getText().toString();
//        c6_p610_sj = c6_p610_sj_EditText.getText().toString();
//        c6_p610_sv = c6_p610_sv_EditText.getText().toString();
//        c6_p610_ss = c6_p610_ss_EditText.getText().toString();
//        c6_p610_t = c6_p610_t_TextView.getText().toString();
//        c6_p611 = c6_p611_EditText.getText().toString();
//        c6_p611a = c6_p611a_RadioGroup.indexOfChild(c6_p611a_RadioGroup.findViewById(c6_p611a_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p611b = c6_p611b_RadioGroup.indexOfChild(c6_p611b_RadioGroup.findViewById(c6_p611b_RadioGroup.getCheckedRadioButtonId()))+"";
//        c6_p612 = c6_p612_RadioGroup.indexOfChild(c6_p612_RadioGroup.findViewById(c6_p612_RadioGroup.getCheckedRadioButtonId()))+"";
//        if(c6_p612.equals("3")){ c6_p612 = "2";}
//        c6_p612_nro = c6_p612_nro_EditText.getText().toString();
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

            c6_p610_o_EditText.setText(modulo6.getC6_p610_pd());

            c6_p611_o_EditText.setText(modulo6.getC6_p611());

            if(!modulo6.getC6_p612().equals("-1") && !modulo6.getC6_p612().equals(""))((RadioButton)c6_p612_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p612()))).setChecked(true);

            if(!modulo6.getC6_p612().equals("-1") && !modulo6.getC6_p612().equals(""))((RadioButton)c6_p612_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p612()))).setChecked(true);

            if(!modulo6.getC6_p613().equals("-1") && !modulo6.getC6_p613().equals(""))((RadioButton)c6_p613_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p613()))).setChecked(true);

            if(!modulo6.getC6_p614_esp().equals("-1") && !modulo6.getC6_p614_esp().equals(""))((RadioButton)c6_p614_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p614_esp()))).setChecked(true);
            c6_p614_o_EditText.setText(modulo6.getC6_p614_mon());

            c6_p609_o_EditText.setText(modulo6.getC6_p609());

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
//            c6_p604_1  = modulo6.getC6_p604_1();
//            c6_p608 = modulo6.getC6_p608();
//            if(!modulo6.getC6_p609().equals("-1") && !modulo6.getC6_p609().equals(""))((RadioButton)c6_p609_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p609()))).setChecked(true);
//            c6_p610_pd_EditText.setText(modulo6.getC6_p610_pd());
//            c6_p610_pl_EditText.setText(modulo6.getC6_p610_pl());
//            c6_p610_pm_EditText.setText(modulo6.getC6_p610_pm());
//            c6_p610_pmi_EditText.setText(modulo6.getC6_p610_pmi());
//            c6_p610_pj_EditText.setText(modulo6.getC6_p610_pj());
//            c6_p610_pv_EditText.setText(modulo6.getC6_p610_pv());
//            c6_p610_ps_EditText.setText(modulo6.getC6_p610_ps());
//            c6_p610_sd_EditText.setText(modulo6.getC6_p610_sd());
//            c6_p610_sl_EditText.setText(modulo6.getC6_p610_sl());
//            c6_p610_sm_EditText.setText(modulo6.getC6_p610_sm());
//            c6_p610_smi_EditText.setText(modulo6.getC6_p610_smi());
//            c6_p610_sj_EditText.setText(modulo6.getC6_p610_sj());
//            c6_p610_sv_EditText.setText(modulo6.getC6_p610_sv());
//            c6_p610_ss_EditText.setText(modulo6.getC6_p610_ss());
//            c6_p611_EditText.setText(modulo6.getC6_p611());
//            if(!modulo6.getC6_p611a().equals("-1") && !modulo6.getC6_p611a().equals(""))((RadioButton)c6_p611a_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p611a()))).setChecked(true);
//            if(!modulo6.getC6_p611b().equals("-1") && !modulo6.getC6_p611b().equals(""))((RadioButton)c6_p611b_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p611b()))).setChecked(true);
//            if(!modulo6.getC6_p612().equals("-1") && !modulo6.getC6_p612().equals("")){
//                if(modulo6.getC6_p612().equals("2")) ((RadioButton)c6_p612_RadioGroup.getChildAt(3)).setChecked(true);
//                else ((RadioButton)c6_p612_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p612()))).setChecked(true);
//            }
//            c6_p612_nro_EditText.setText(modulo6.getC6_p612_nro());
//        }
//        data.close();
    }

    private void inicio() {
//        Todas las personas AND P205_A>=5 AND P609<>VACIO
//:       SI P612=i Entonces pase a P614, para cualquier i=1,2,4,5
//		  SI P612=j Entonces pase a P613, para cualquier j=3,6,7,8
        if(edad >= 5 ){
            m6_p612_linearlayout.setVisibility(View.VISIBLE);
            validarFlujo74();
        } else {
            limpiar_p612();
            m6_p612_linearlayout.setVisibility(View.GONE);

        }

    }

    private void validarFlujo74() {
        llenarVariables();
//        Todas las personas AND P205_A>=5 AND P609<>VACIO
//:       SI P612=i Entonces pase a P614, para cualquier i=1,2,4,5
//		  SI P612=j Entonces pase a P613, para cualquier j=3,6,7,8

        if(c6_p612.equals("1") || c6_p612.equals("2") || c6_p612.equals("4") || c6_p612.equals("5")){
            limpiar_p613();
            m6_p613_linearlayout.setVisibility(View.GONE);
            m6_p614_linearlayout.setVisibility(View.VISIBLE);
        }else if(c6_p612.equals("3") || c6_p612.equals("6")
                || c6_p612.equals("7") || c6_p612.equals("8")){
            m6_p613_linearlayout.setVisibility(View.VISIBLE);
            m6_p614_linearlayout.setVisibility(View.VISIBLE);
        }
    }

    private void limpiar_p613() {
        c6_p613_RadioGroup.clearCheck();
    }

    private void limpiar_p612() {
        c6_p612_RadioGroup.clearCheck();
    }

    @Override
    public void llenarVista() {
//        Data data = new Data(context);
//        data.open();
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p609,idEncuestado)) m6_p609_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p610,idEncuestado)) m6_p610_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p611,idEncuestado)) m6_p611_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p611a,idEncuestado)) m6_p611a_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p611b,idEncuestado)) m6_p611b_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p612,idEncuestado)) m6_p612_linearlayout.setVisibility(View.GONE);
//        data.close();
    }

    @Override
    public boolean validarDatos() {
        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        if (m6_p609_linearlayout.getVisibility()==View.VISIBLE) {
            if ((c6_p609.equals("") || c6_p609.length() < 5)) {
                c6_p609_o_EditText.requestFocus();
                mostrarMensaje("ERROR PREGUNTA 609 – DEBE DE TENER MÁS DE 4 CARACTERES"); return false;
            }
        }else{
            c6_p609="";
        }

        if (m6_p610_linearlayout.getVisibility()==View.VISIBLE) {
            if ((c6_p610_pd.equals("") || c6_p610_pd.length() < 5)) {
                c6_p610_o_EditText.requestFocus();
                mostrarMensaje("ERROR PREGUNTA 610 – DEBE DE TENER MÁS DE 4 CARACTERES"); return false;
            }
        }else{
            c6_p610_pd = "";
        }

        if (m6_p611_linearlayout.getVisibility()==View.VISIBLE) {
            if ((c6_p611.equals("") || c6_p611.length() < 5)) {
                c6_p611_o_EditText.requestFocus();
                mostrarMensaje("ERROR PREGUNTA 611 – DEBE DE TENER MÁS DE 4 CARACTERES"); return false;
            }
        }else{
            c6_p611 = "";
        }

        if (m6_p612_linearlayout.getVisibility() == View.VISIBLE){
            if(c6_p612.equals("-1")){
                mostrarMensaje("PREGUNTA 612: DEBE SELECCIONAR UNA OPCION");
                return false;
            }

            //REGLA 0070
            if (p203.equals("9")){
                if(!c6_p612.equals("6")){
                    mostrarMensaje("ERROR PREGUNTA 612 – CATEGORÍA DE OCUPACIÓN NO SE RELACIONA CON LA PREGUNTA 203");
                    return false;
                }
            }else

            //REGLA 0071
            if (p604.equals("1") && (c6_p612.equals("3") || c6_p612.equals("4") || c6_p612.equals("5") || c6_p612.equals("6") || c6_p612.equals("7") || c6_p612.equals("8"))){
                mostrarMensaje("ERROR PREGUNTA 612 – CATEGORÍA DE OCUPACIÓN NO SE RELACIONA CON LA PREGUNTA 604");
                return false;
            }

            //REGLA 0072 HA SIDO CAMBIADO POR EL USUARIO RODOLFO
            if (p603.equals("1") && (c6_p612.equals("1") || c6_p612.equals("2"))){
                mostrarMensaje("ERROR PREGUNTA 612 – CATEGORÍA DE OCUPACIÓN NO SE RELACIONA CON LA PREGUNTA 603");
                return false;
            }

            //REGLA 0073
            if ((p603.equals("1") || p604.equals("1")) && (c6_p612.equals("4") || c6_p612.equals("5"))){
                mostrarMensaje("ERROR PREGUNTA 612 – CATEGORÍA DE OCUPACIÓN NO SE RELACIONA CON LA PREGUNTA 603 O PREGUNTA 604");
                return false;
            }


        }else{
            c6_p612="";
        }

        if (m6_p613_linearlayout.getVisibility() == View.VISIBLE){
            if(c6_p613.equals("-1")){
                mostrarMensaje("PREGUNTA 613: DEBE SELECCIONAR UNA OPCION");
                return false;
            }

            //REGLA 0074
            if ((c6_p612.equals("8") && c6_p613.equals("1"))){
                mostrarMensaje("ERROR PREGUNTA 613 – TIENE FIRMADO CONTRATO LABORAL O EMITE COMPROBANTE DE PAGO POR SUS SERVICIOS Y ES PRACTICANTE SIN REMUNERACIÓN EN PREGUNTA 612");
                return false;
            }

        }else{
            c6_p613="";
        }

        if (m6_p614_linearlayout.getVisibility() == View.VISIBLE) {
            if (c6_p614_esp.equals("-1")) {
                mostrarMensaje("PREGUNTA 614: DEBE SELECCIONAR UNA OPCION");
                return false;
            }
            if (c6_p614_mon.equals("0")) {
                mostrarMensaje("PREGUNTA 614: Nº PERSONAS NO DEBE SER 0");
                return false;
            }

            //REGLA 0075
//            : Si P614=1 AND P614_T>20 Entonces ERROR
//            ERROR “PREGUNTA 614 – NRO DE PERSONAS DEBE SER DE 1 A 20”
            if ((c6_p614_esp.equals("1") && (c6_p614_mon.trim().equals("") || Integer.parseInt(c6_p614_mon) > 20) )) {
                mostrarMensaje("ERROR “PREGUNTA 614 – NRO DE PERSONAS DEBE SER DE 1 A 20”");
                return false;
            }
//                   Si P614=2 AND (P614_T<21 OR P614_T>50) Entonces ERROR
//                  ERROR “PREGUNTA 614 – NRO DE PERSONAS DEBE SER DE 21 A 50”
            if ((c6_p614_esp.equals("2") && (c6_p614_mon.trim().equals("") || (Integer.parseInt(c6_p614_mon) >50 || Integer.parseInt(c6_p614_mon)<21)))) {
                mostrarMensaje("ERROR “PREGUNTA 614 – NRO DE PERSONAS DEBE SER DE 21 A 50”");
                return false;
            }

//            Si P614=3 AND (P614_T<51 OR P614_T>100) Entonces ERROR
//            ERROR “PREGUNTA 614 – NRO DE PERSONAS DEBE SER DE 51 A 100”
            if ((c6_p614_esp.equals("3") && (c6_p614_mon.trim().equals("") || (Integer.parseInt(c6_p614_mon) >100 || Integer.parseInt(c6_p614_mon)<51)))) {
                mostrarMensaje("ERROR “PREGUNTA 614 – NRO DE PERSONAS DEBE SER DE 51 A 100”");
                return false;
            }

//            Si P614=4 AND (P614_T<101 OR P614_T>500) Entonces ERROR
//            ERROR “PREGUNTA 614 – NRO DE PERSONAS DEBE SER DE 101 A 500”
            if ((c6_p614_esp.equals("4") && (c6_p614_mon.trim().equals("") || (Integer.parseInt(c6_p614_mon) >500 || Integer.parseInt(c6_p614_mon)<101)))) {
                mostrarMensaje("ERROR “PREGUNTA 614 – NRO DE PERSONAS DEBE SER DE 101 A 500”");
                return false;
            }

//            Si P614=5 AND P614_T<=500 Entonces ERROR
//            ERROR “PREGUNTA 614 – NRO DE PERSONAS DEBE SER MÁS DE 500”
            if ((c6_p614_esp.equals("5") && (c6_p614_mon.trim().equals("") || (Integer.parseInt(c6_p614_mon) <=500)))) {
                mostrarMensaje("ERROR “PREGUNTA 614 – NRO DE PERSONAS DEBE SER MÁS DE 500”");
                return false;
            }

            //REGLA 0076
//            Si P612 = 1 AND P614 = 1 AND P614_T < 2
//            ERROR “PREGUNTA 614 – NRO DE PERSONAS NO SE RELACIONA CON LA PREGUNTA 612 (EMPLEADOR O PATRONO)”
            if (c6_p612.equals("1")  && c6_p614_esp.equals("1") && (Integer.parseInt(c6_p614_mon) <2)){
                mostrarMensaje("ERROR “PREGUNTA 614 – NRO DE PERSONAS NO SE RELACIONA CON LA PREGUNTA 612 (EMPLEADOR O PATRONO)”");
                return false;
            }

            // Si P612 = 2 AND P614 <> 1
            //ERROR “PREGUNTA 614 – NRO DE PERSONAS NO SE RELACIONA CON LA PREGUNTA 612 (TRABAJADOR/A INDEPENDIENTE)”
            if (c6_p612.equals("2")  && !(c6_p614_esp.equals("1"))){
                mostrarMensaje("ERROR “PREGUNTA 614 – NRO DE PERSONAS NO SE RELACIONA CON LA PREGUNTA 612 (TRABAJADOR/A INDEPENDIENTE)”");
                return false;
            }

            //Si P612 = 6 AND P614 <> 1
            //ERROR “PREGUNTA 614 – NO SE RELACIONA CON LA PREGUNTA 612 (TRABAJADOR/A DEL HOGAR)”
            if (c6_p612.equals("6")  && !(c6_p614_esp.equals("1"))){
                mostrarMensaje("ERROR “PREGUNTA 614 – NO SE RELACIONA CON LA PREGUNTA 612 (TRABAJADOR/A DEL HOGAR)”");
                return false;
            }

            //Si P612 = 4 OR P612 = 5 AND P614 = 1 AND P614_T < 2
            //ERROR “PREGUNTA 614 – NRO DE PERSONAS NO SE RELACIONA CON LA PREGUNTA 612 (AYUDANTE EN UN NEGOCIO DE LA FAMILIA O AYUDANTE EN EL EMPLEO DE UN FAMILIAR)”
            if ( (c6_p612.equals("4") || c6_p612.equals("5"))  && (c6_p614_esp.equals("1")) && (Integer.parseInt(c6_p614_mon)<2)){
                mostrarMensaje("ERROR “PREGUNTA 614 – NRO DE PERSONAS NO SE RELACIONA CON LA PREGUNTA 612 (AYUDANTE EN UN NEGOCIO DE LA FAMILIA O AYUDANTE EN EL EMPLEO DE UN FAMILIAR)”");
                return false;
            }

        }else{
            c6_p614_esp="";
            c6_p614_mon = "";
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

//    public String NombreMes(int mes){
//        String nom_mes="";
//        switch(mes){
//            case 0: nom_mes="ENERO"; break;
//            case 1: nom_mes="FEBRERO"; break;
//            case 2: nom_mes="MARZO"; break;
//            case 3: nom_mes="ABRIL"; break;
//            case 4: nom_mes="MAYO"; break;
//            case 5: nom_mes="JUNIO"; break;
//            case 6: nom_mes="JULIO"; break;
//            case 7: nom_mes="AGOSTO"; break;
//            case 8: nom_mes="SETIEMBRE"; break;
//            case 9: nom_mes="OCTUBRE"; break;
//            case 10: nom_mes="NOVIEMBRE"; break;
//            case 11: nom_mes="DICIEMBRE"; break;
//        }
//        return nom_mes;
//    }
//    public void fecha(){
//        Calendar calendario;
//        int mm=0, dd=0;
//        String fecha_inicial="", fecha_final="";
//        calendario = Calendar.getInstance();
//        mm = calendario.get(Calendar.MONTH);
//        dd = calendario.get(Calendar.DAY_OF_MONTH);
//        fecha_final = "" + dd + " DE " + NombreMes(mm);
//        calendario.add(Calendar.DAY_OF_MONTH,-7);
//        mm = calendario.get(Calendar.MONTH);
//        dd = calendario.get(Calendar.DAY_OF_MONTH);
//        fecha_inicial = "" + dd + " DE " + NombreMes(mm);
//        String enunciado_p610 = c6_p610_TextView.getText()+"";
//        enunciado_p610 = enunciado_p610.replace("FECHAINI", fecha_inicial);
//        enunciado_p610 = enunciado_p610.replace("FECHAFIN", fecha_final);
//        c6_p610_TextView.setText(enunciado_p610);
//    }

    public boolean rango(int ini, int fin, String numero){
        if(Integer.parseInt(numero)>=ini && Integer.parseInt(numero)<=fin) return true; else return false;
    }

//    public void configurarTextWatcher(EditText editText){
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(!charSequence.toString().equals("")){
//                    c6_p610_t_TextView.setText((Integer.parseInt(c6_p610_t_TextView.getText().toString()) - Integer.parseInt(charSequence.toString()))+"");
//                }
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                int despues = 0;
//                if(!editable.toString().equals("")) despues = Integer.parseInt(editable.toString());
//                if(!(Integer.parseInt(c6_p610_t_TextView.getText().toString())== 0)){
//                    c6_p610_t_TextView.setText((Integer.parseInt(c6_p610_t_TextView.getText().toString()) + despues) +"");
//                }else{
//                    c6_p610_t_TextView.setText(despues+"");
//                }
//            }
//        });
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

    private void configurarEditText1(final EditText editText, final View view, int tipo,int longitud){
        if (tipo == 1) editText.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(longitud)});

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
        if (tipo == 2) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(longitud)});
            editText.setTransformationMethod(new NumericKeyBoardTransformationMethod());
//            editText.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(longitud)});
//            editText.setInputType(18);
//            editText.setTransformationMethod(null);
        }

        if (tipo == 3){
            editText.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "23")});
            editText.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        }
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

//    public int horas_tot_p610(){
//        return Integer.parseInt(c6_p610_pd.trim())+Integer.parseInt(c6_p610_pl.trim())+Integer.parseInt(c6_p610_pm.trim())+
//                Integer.parseInt(c6_p610_pmi.trim())+Integer.parseInt(c6_p610_pj.trim())+Integer.parseInt(c6_p610_pv.trim())+
//                Integer.parseInt(c6_p610_ps.trim())+
//                Integer.parseInt(c6_p610_sd.trim())+Integer.parseInt(c6_p610_sl.trim())+Integer.parseInt(c6_p610_sm.trim())+
//                Integer.parseInt(c6_p610_smi.trim())+Integer.parseInt(c6_p610_sj.trim())+Integer.parseInt(c6_p610_sv.trim())+
//                Integer.parseInt(c6_p610_ss.trim());
//    }
}