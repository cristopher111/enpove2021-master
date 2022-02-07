package com.example.ricindigus.enpove2021.fragments.modulo6;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.location.SettingInjectorService;
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
import android.widget.AutoCompleteTextView;
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
import com.example.ricindigus.enpove2021.modelo.pojos.Ubigeo;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterMinMax;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethods;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP613P617 extends FragmentPagina {
    String idEncuestado;
    String idVivienda, idHogar, idInformante, id_informante="";
    Context context;

    Spinner informanteSpinner;

    //PREGUNTA 15
    //  TextView c6_p615_TextView;
    EditText c6_p615_pd_EditText, c6_p615_pl_EditText, c6_p615_pm_EditText, c6_p615_pmi_EditText,
            c6_p615_pj_EditText, c6_p615_pv_EditText, c6_p615_ps_EditText,
            c6_p615_sd_EditText, c6_p615_sl_EditText, c6_p615_sm_EditText, c6_p615_smi_EditText,
            c6_p615_sj_EditText, c6_p615_sv_EditText, c6_p615_ss_EditText;
    TextView c6_p615_t_TextView;

    TextView tv615periodo;

    LinearLayout m6_p615_linearlayout;

    private String c6_p615_pd;
    private String c6_p615_pl;
    private String c6_p615_pm;
    private String c6_p615_pmi;
    private String c6_p615_pj;
    private String c6_p615_pv;
    private String c6_p615_ps;
    private String c6_p615_pt;
    private String c6_p615_sd;
    private String c6_p615_sl;
    private String c6_p615_sm;
    private String c6_p615_smi;
    private String c6_p615_sj;
    private String c6_p615_sv;
    private String c6_p615_ss;
    private String c6_p615_st;
    private String c6_p615_t;

    private int p615_pd=0;
    private int p615_pl=0;
    private int p615_pm=0;
    private int p615_pmi=0;
    private int p615_pj=0;
    private int p615_pv=0;
    private int p615_ps=0;
    private int p615_pt=0;
    private int p615_sd=0;
    private int p615_sl=0;
    private int p615_sm=0;
    private int p615_smi=0;
    private int p615_sj=0;
    private int p615_sv=0;
    private int p615_ss=0;
    private int p615_st=0;


    private int p615_op=0;
//    private int p615_t=0;

    //PREGUNTA 16
    //EditText c6_p616_EditText;
    RadioGroup c6_p616_RadioGroup;
    TextView c6_p616_TextView;
    private String c6_p616;
    LinearLayout m6_p616_linearlayout;
    RadioButton mod6_616_rb2;

    //PREGUNTA 16_A
    EditText c6_p616_a_EditText;
    private String c6_p616_a;
    LinearLayout m6_p616_a_linearlayout;


    //PREGUNTA 17
    RadioGroup c6_p617_RadioGroup;
    private String c6_p617;
    LinearLayout m6_p617_linearlayout;

    //PREGUNTA 18
    RadioGroup c6_p618_RadioGroup;
    private String c6_p618;
    LinearLayout m6_p618_linearlayout;

    //PREGUNTA 19
    RadioGroup c6_p619_RadioGroup;
    private String c6_p619;
    LinearLayout m6_p619_linearlayout;

    int edad=0;
    private String p601,p603,p604,p203;
    private String p605_1,p605_2,p605_3,p605_4,p605_5,p605_6,p605_7,p605_8,p605_9,p605_10,p605_11,p605_12;
    private String p612,p607,p608,p629,p628,p627,p632i,p633,p631;
    int p18 = 0;
    private String p630;
    private String p632_1,p632_2,p632_3,p632_4,p632_5,p632_6,p632_7,p632_8,p632_9,p632_10;

    private  int p622_1,p622_2;//NUEVOS HECTOR
    int p625=0;

    @SuppressLint("ValidFragment")
    public FragmentP613P617(String idEncuestado, Context context) {
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
        p203 = residente.getC2_p203();
        p601 = data.getModulo6(idEncuestado).getC6_p601();
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
        p612 = data.getModulo6(idEncuestado).getC6_p612();
        p627 = data.getModulo6(idEncuestado).getC6_p627();
        p628 = data.getModulo6(idEncuestado).getC6_p628();
        p629 = data.getModulo6(idEncuestado).getC6_p629();
        p630 = data.getModulo6(idEncuestado).getC6_p630();
        p631 = data.getModulo6(idEncuestado).getC6_p631();
        try{
            p18 = Integer.parseInt(data.getHogar(idHogar).getP18());
        }catch (Exception ignore){}
        p632i = data.getModulo6(idEncuestado).getC6_p632i();
        p633 = data.getModulo6(idEncuestado).getC6_p633();
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

        p607 = data.getModulo6(idEncuestado).getC6_p607();
        p608 = data.getModulo6(idEncuestado).getC6_p608();
        try{
            p625 = Integer.parseInt(data.getModulo6(idEncuestado).getC6_p625());
        }catch (Exception ignore){}
        data.close();
    }

    public FragmentP613P617() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p613_p617, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

        //c6_p615_TextView = (TextView) rootView.findViewById(R.id.mod6_615_textview_C6_P615);
        c6_p615_pd_EditText = (EditText) rootView.findViewById(R.id.mod6_615_edittext_C6_P615_PD);
        c6_p615_pl_EditText = (EditText) rootView.findViewById(R.id.mod6_615_edittext_C6_P615_PL);
        c6_p615_pm_EditText = (EditText) rootView.findViewById(R.id.mod6_615_edittext_C6_P615_PM);
        c6_p615_pmi_EditText = (EditText) rootView.findViewById(R.id.mod6_615_edittext_C6_P615_PMI);
        c6_p615_pj_EditText = (EditText) rootView.findViewById(R.id.mod6_615_edittext_C6_P615_PJ);
        c6_p615_pv_EditText = (EditText) rootView.findViewById(R.id.mod6_615_edittext_C6_P615_PV);
        c6_p615_ps_EditText = (EditText) rootView.findViewById(R.id.mod6_615_edittext_C6_P615_PS);

        c6_p615_sd_EditText = (EditText) rootView.findViewById(R.id.mod6_615_edittext_C6_P615_SD);
        c6_p615_sl_EditText = (EditText) rootView.findViewById(R.id.mod6_615_edittext_C6_P615_SL);
        c6_p615_sm_EditText = (EditText) rootView.findViewById(R.id.mod6_615_edittext_C6_P615_SM);
        c6_p615_smi_EditText = (EditText) rootView.findViewById(R.id.mod6_615_edittext_C6_P615_SMI);
        c6_p615_sj_EditText = (EditText) rootView.findViewById(R.id.mod6_615_edittext_C6_P615_SJ);
        c6_p615_sv_EditText = (EditText) rootView.findViewById(R.id.mod6_615_edittext_C6_P615_SV);
        c6_p615_ss_EditText = (EditText) rootView.findViewById(R.id.mod6_615_edittext_C6_P615_SS);

        c6_p615_t_TextView = (TextView) rootView.findViewById(R.id.mod6_615_textview_C6_P615_T);

        //c6_p616_EditText = (EditText) rootView.findViewById(R.id.mod6_616_edittext_C6_P616);
        c6_p616_TextView = (TextView) rootView.findViewById(R.id.mod6_616_textview_C6_P616);
        c6_p616_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_616_radiogroup_C6_P616);
        mod6_616_rb2 = (RadioButton) rootView.findViewById(R.id.mod6_616_rb2);


        c6_p616_a_EditText = (EditText) rootView.findViewById(R.id.mod6_616_a_edittext_C6_P616_A);


        c6_p617_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_617_radiogroup_C6_P617);

        c6_p618_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_618_radiogroup_C6_P618);

        c6_p619_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_619_radiogroup_C6_P619);

        tv615periodo = (TextView) rootView.findViewById(R.id.tv615periodo);

        m6_p615_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p615);
        m6_p616_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p616);
        m6_p616_a_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p616_a);
        m6_p617_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p617);
        m6_p618_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p618);
        m6_p619_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p619);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String text615 = getString(R.string.modulo_6_p615, UtilsMethods.getPeriodoReferenciaSemana(1));
        tv615periodo.setText(text615);

        c6_p615_pd_EditText.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        c6_p615_pl_EditText.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        c6_p615_pm_EditText.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        c6_p615_pmi_EditText.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        c6_p615_pj_EditText.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        c6_p615_pv_EditText.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        c6_p615_ps_EditText.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});

        c6_p615_sd_EditText.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        c6_p615_sl_EditText.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        c6_p615_sm_EditText.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        c6_p615_smi_EditText.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        c6_p615_sj_EditText.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        c6_p615_sv_EditText.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});
        c6_p615_ss_EditText.setFilters(new InputFilter[]{new InputFilterMinMax("0", "24")});

        //configurarEditText(c6_p616_EditText,m6_p616_linearlayout,2,3);
        configurarEditText(c6_p616_a_EditText,m6_p616_a_linearlayout,2,3);

        configurarTextWatcher(c6_p615_pd_EditText);
        configurarTextWatcher(c6_p615_pl_EditText);
        configurarTextWatcher(c6_p615_pm_EditText);
        configurarTextWatcher(c6_p615_pmi_EditText);
        configurarTextWatcher(c6_p615_pj_EditText);
        configurarTextWatcher(c6_p615_pv_EditText);
        configurarTextWatcher(c6_p615_ps_EditText);
        configurarTextWatcher(c6_p615_sd_EditText);
        configurarTextWatcher(c6_p615_sl_EditText);
        configurarTextWatcher(c6_p615_sm_EditText);
        configurarTextWatcher(c6_p615_smi_EditText);
        configurarTextWatcher(c6_p615_sj_EditText);
        configurarTextWatcher(c6_p615_sv_EditText);
        configurarTextWatcher(c6_p615_ss_EditText);

        quitaceros(c6_p615_pd_EditText);
        quitaceros(c6_p615_pl_EditText);
        quitaceros(c6_p615_pm_EditText);
        quitaceros(c6_p615_pmi_EditText);
        quitaceros(c6_p615_pj_EditText);
        quitaceros(c6_p615_pv_EditText);
        quitaceros(c6_p615_ps_EditText);
        quitaceros(c6_p615_sd_EditText);
        quitaceros(c6_p615_sl_EditText);
        quitaceros(c6_p615_sm_EditText);
        quitaceros(c6_p615_smi_EditText);
        quitaceros(c6_p615_sj_EditText);
        quitaceros(c6_p615_sv_EditText);
        quitaceros(c6_p615_ss_EditText);

        c6_p616_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                switch (seleccionado) {
                    case 1:
                        m6_p616_a_linearlayout.setVisibility(View.GONE);
                        limpiar_p616_a();
                        break;
                    case 2:
                        m6_p616_a_linearlayout.setVisibility(View.VISIBLE);

                        break;
                }
            }
        });

        c6_p617_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                validarFlujo76();
                if(validar_P618()){m6_p618_linearlayout.setVisibility(View.VISIBLE);}
                else {limpiarP618();m6_p618_linearlayout.setVisibility(View.GONE);}

                if(validar_P619()){m6_p619_linearlayout.setVisibility(View.VISIBLE);}
                else {limpiarP619();m6_p619_linearlayout.setVisibility(View.GONE);}
            }
        });
        c6_p618_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                validarFlujo76A();
                if(validar_P619()){m6_p619_linearlayout.setVisibility(View.VISIBLE);}
                else {limpiarP619();m6_p619_linearlayout.setVisibility(View.GONE);}
            }
        });
        // fecha();  ------->>despues vamos a poner las fechas
        llenarVista();
        cargarDatos();
    }

    private void quitaceros(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() > 1){
                    if(charSequence.equals("00")) {
                        editText.setText("0");
                    }
                    if(charSequence.toString().substring(0,1).equals("0")) {
                        editText.setText(charSequence.toString().substring(1, charSequence.length()));
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private boolean combprobarNuero(final String cadena) {
        boolean escero = false;
        int horas;
        if(!cadena.equals("")){
            try{
                horas = Integer.parseInt(cadena);
            }catch (Exception e){
                horas = 0;
            }
            Log.e("Trato",""+String.valueOf(horas));
            if(cadena.length() > 0 && horas==0) {
                escero = true;
            }
        }
        return escero;
    }

   private void total() {
        String enunciado_p616 = c6_p616_TextView.getText().toString()+"";
        String c6_p616_text = c6_p615_t;
        enunciado_p616 = enunciado_p616.replace("_", c6_p616_text);
        c6_p616_TextView.setText(enunciado_p616);

    }


    @Override
    public void guardarDatos() {

        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo6_id_informante,idInformante);

        contentValues.put(SQLConstantes.modulo6_c6_p615_pd,c6_p615_pd);
        contentValues.put(SQLConstantes.modulo6_c6_p615_pl,c6_p615_pl);
        contentValues.put(SQLConstantes.modulo6_c6_p615_pm,c6_p615_pm);
        contentValues.put(SQLConstantes.modulo6_c6_p615_pmi,c6_p615_pmi);
        contentValues.put(SQLConstantes.modulo6_c6_p615_pj,c6_p615_pj);
        contentValues.put(SQLConstantes.modulo6_c6_p615_pv,c6_p615_pv);
        contentValues.put(SQLConstantes.modulo6_c6_p615_ps,c6_p615_ps);
        contentValues.put(SQLConstantes.modulo6_c6_p615_pt,c6_p615_pt);
        contentValues.put(SQLConstantes.modulo6_c6_p615_sd,c6_p615_sd);
        contentValues.put(SQLConstantes.modulo6_c6_p615_sl,c6_p615_sl);
        contentValues.put(SQLConstantes.modulo6_c6_p615_sm,c6_p615_sm);
        contentValues.put(SQLConstantes.modulo6_c6_p615_smi,c6_p615_smi);
        contentValues.put(SQLConstantes.modulo6_c6_p615_sj,c6_p615_sj);
        contentValues.put(SQLConstantes.modulo6_c6_p615_sv,c6_p615_sv);
        contentValues.put(SQLConstantes.modulo6_c6_p615_ss,c6_p615_ss);
        contentValues.put(SQLConstantes.modulo6_c6_p615_st,c6_p615_st);
        contentValues.put(SQLConstantes.modulo6_c6_p615_t,c6_p615_t);

        contentValues.put(SQLConstantes.modulo6_c6_p616,c6_p616);
        contentValues.put(SQLConstantes.modulo6_c6_p616_a,c6_p616_a);



        contentValues.put(SQLConstantes.modulo6_c6_p617,c6_p617);

        contentValues.put(SQLConstantes.modulo6_c6_p618,c6_p618);

        contentValues.put(SQLConstantes.modulo6_c6_p619,c6_p619);

        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp613p617,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();
        OcultarOtrosLayouts();
    }

    @Override
    public void llenarVariables() {
        idInformante = obtener_Nresidente(informanteSpinner);
//        String[] infor_id = (informanteSpinner.getItemAtPosition(informanteSpinner.getSelectedItemPosition()).toString()).split("-");
//        id_informante = idHogar + "_" + infor_id[0];

        c6_p615_pd = c6_p615_pd_EditText.getText().toString();
        c6_p615_pl = c6_p615_pl_EditText.getText().toString();
        c6_p615_pm = c6_p615_pm_EditText.getText().toString();
        c6_p615_pmi = c6_p615_pmi_EditText.getText().toString();
        c6_p615_pj = c6_p615_pj_EditText.getText().toString();
        c6_p615_pv = c6_p615_pv_EditText.getText().toString();
        c6_p615_ps = c6_p615_ps_EditText.getText().toString();
        c6_p615_sd = c6_p615_sd_EditText.getText().toString();
        c6_p615_sl = c6_p615_sl_EditText.getText().toString();
        c6_p615_sm = c6_p615_sm_EditText.getText().toString();
        c6_p615_smi = c6_p615_smi_EditText.getText().toString();
        c6_p615_sj = c6_p615_sj_EditText.getText().toString();
        c6_p615_sv = c6_p615_sv_EditText.getText().toString();
        c6_p615_ss = c6_p615_ss_EditText.getText().toString();
        c6_p615_t = c6_p615_t_TextView.getText().toString();

        //c6_p616 = c6_p616_EditText.getText().toString();
        c6_p616 = c6_p616_RadioGroup.indexOfChild(c6_p616_RadioGroup.findViewById(c6_p616_RadioGroup.getCheckedRadioButtonId()))+"";

        c6_p616_a = c6_p616_a_EditText.getText().toString();

        c6_p617 = c6_p617_RadioGroup.indexOfChild(c6_p617_RadioGroup.findViewById(c6_p617_RadioGroup.getCheckedRadioButtonId())) + "";

        c6_p618 = c6_p618_RadioGroup.indexOfChild(c6_p618_RadioGroup.findViewById(c6_p618_RadioGroup.getCheckedRadioButtonId())) + "";

        c6_p619 = c6_p619_RadioGroup.indexOfChild(c6_p619_RadioGroup.findViewById(c6_p619_RadioGroup.getCheckedRadioButtonId())) + "";

//        idInformante = informanteSpinner.getSelectedItemPosition()+"";
//        String[] infor_id = (informanteSpinner.getItemAtPosition(informanteSpinner.getSelectedItemPosition()).toString()).split("-");
//        id_informante = idHogar + "_" + infor_id[0];
//        c6_p613 = c6_p613_RadioGroup.indexOfChild(c6_p613_RadioGroup.findViewById(c6_p613_RadioGroup.getCheckedRadioButtonId())) + "";
//        c6_p614_mon = c6_p614_mon_EditText.getText().toString();
//        c6_p614_esp = c6_p614_esp_EditText.getText().toString();
//        c6_p615_mon = c6_p615_mon_EditText.getText().toString();
//        c6_p615_esp = c6_p615_esp_EditText.getText().toString();
//        c6_p616_mon = c6_p616_mon_EditText.getText().toString();
//        c6_p616_esp = c6_p616_esp_EditText.getText().toString();
//        if(c6_p616_nas_Checkbox.isChecked()) c6_p616_nas = "1"; else c6_p616_nas = "0";
//        c6_p617 = c6_p617_RadioGroup.indexOfChild(c6_p617_RadioGroup.findViewById(c6_p617_RadioGroup.getCheckedRadioButtonId())) + "";
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
            c6_p615_pd_EditText.setText(modulo6.getC6_p615_pd());
            c6_p615_pl_EditText.setText(modulo6.getC6_p615_pl());
            c6_p615_pm_EditText.setText(modulo6.getC6_p615_pm());
            c6_p615_pmi_EditText.setText(modulo6.getC6_p615_pmi());
            c6_p615_pj_EditText.setText(modulo6.getC6_p615_pj());
            c6_p615_pv_EditText.setText(modulo6.getC6_p615_pv());
            c6_p615_ps_EditText.setText(modulo6.getC6_p615_ps());
            c6_p615_sd_EditText.setText(modulo6.getC6_p615_sd());
            c6_p615_sl_EditText.setText(modulo6.getC6_p615_sl());
            c6_p615_sm_EditText.setText(modulo6.getC6_p615_sm());
            c6_p615_smi_EditText.setText(modulo6.getC6_p615_smi());
            c6_p615_sj_EditText.setText(modulo6.getC6_p615_sj());
            c6_p615_sv_EditText.setText(modulo6.getC6_p615_sv());
            c6_p615_ss_EditText.setText(modulo6.getC6_p615_ss());

           // c6_p616_EditText.setText(modulo6.getC6_p616());

            if(!modulo6.getC6_p616().equals("-1") && !modulo6.getC6_p616().equals(""))((RadioButton)c6_p616_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p616()))).setChecked(true);

            c6_p616_a_EditText.setText(modulo6.getC6_p616_a());

            if(!modulo6.getC6_p617().equals("-1") && !modulo6.getC6_p617().equals(""))((RadioButton)c6_p617_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p617()))).setChecked(true);

            if(!modulo6.getC6_p618().equals("-1") && !modulo6.getC6_p618().equals(""))((RadioButton)c6_p618_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p618()))).setChecked(true);

            if(!modulo6.getC6_p619().equals("-1") && !modulo6.getC6_p619().equals(""))((RadioButton)c6_p619_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p619()))).setChecked(true);


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
//            if(!modulo6.getC6_p613().equals("-1") && !modulo6.getC6_p613().equals(""))((RadioButton)c6_p613_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p613()))).setChecked(true);
//            c6_p614_mon_EditText.setText(modulo6.getC6_p614_mon());
//            c6_p614_esp_EditText.setText(modulo6.getC6_p614_esp());
//            c6_p615_mon_EditText.setText(modulo6.getC6_p615_mon());
//            c6_p615_esp_EditText.setText(modulo6.getC6_p615_esp());
//            c6_p610_tot_sec = modulo6.getC6_p610_total_horas_secundarias();
//            c6_p616_mon_EditText.setText(modulo6.getC6_p616_mon());
//            c6_p616_esp_EditText.setText(modulo6.getC6_p616_esp());
//            if(modulo6.getC6_p616_nas().equals("1")) c6_p616_nas_Checkbox.setChecked(true);
//            if(!modulo6.getC6_p617().equals("-1") && !modulo6.getC6_p617().equals(""))((RadioButton)c6_p617_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p617()))).setChecked(true);
//            if(!modulo6.getC6_p617_dep().equals("")){
//                String codUbigeo = modulo6.getC6_p617_dep()+modulo6.getC6_p617_prov()+modulo6.getC6_p617_dist();
//                Ubigeo ubigeo = data.getUbigeoxId(codUbigeo);
//                c6_p617_dep = ubigeo.getCod_departamento();
//                c6_p617_prov = ubigeo.getCod_provincia();
//                c6_p617_dist = ubigeo.getCod_distrito();
//                c6_p617_dep_TextView.setText(ubigeo.getNom_departamento());
//                c6_p617_prov_TextView.setText(ubigeo.getNom_provincia());
//                c6_p617_dist_TextView.setText(ubigeo.getNom_distrito());
//            }
//        }
//        data.close();
    }

    private void inicio() {


        //total
        //P616_A
        if(p603.equals("1")){mod6_616_rb2.setChecked(true);}

        if(validar_P616_a()){m6_p616_a_linearlayout.setVisibility(View.VISIBLE); }
        else { limpiar_p616_a();m6_p616_a_linearlayout.setVisibility(View.GONE); }
        //P617
        if(validar_P617()){ m6_p617_linearlayout.setVisibility(View.VISIBLE); }
        else { limpiarP617();m6_p617_linearlayout.setVisibility(View.GONE); }
        //P618
        if(validar_P618()){ m6_p618_linearlayout.setVisibility(View.VISIBLE); }
        else { limpiarP618();m6_p618_linearlayout.setVisibility(View.GONE); }
        //P619
        if(validar_P619()){ m6_p619_linearlayout.setVisibility(View.VISIBLE); }
        else { limpiarP619();m6_p619_linearlayout.setVisibility(View.GONE); }
    }

    private void limpiar_p616_a() {
        //c6_p616_a_EditText.setBackgroundResource(R.drawable.input_text_disabled);
       // c6_p616_a_EditText.setEnabled(false);
        c6_p616_a_EditText.setText("");

    }

    @Override
    public void llenarVista() {
//        Data data = new Data(context);
//        data.open();
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p613,idEncuestado)) m6_p613_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p614,idEncuestado)) m6_p614_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p615,idEncuestado)) m6_p615_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p616,idEncuestado)) m6_p616_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p617,idEncuestado)) m6_p617_linearlayout.setVisibility(View.GONE);
//        data.close();
    }

    @Override
    public boolean validarDatos() {
        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        //REGLA 77
        if(m6_p615_linearlayout.getVisibility() == View.VISIBLE){
            if(p601.equals("1") && c6_p615_t.equals("0"))
            {mostrarMensaje("ERROR “PREGUNTA 615 – EL TOTAL DE HORAS TRABAJADAS DURANTE LA SEMANA PASADA NO PUEDE SER CERO PORQUE SI TUVO TRABAJO LA SEMANA PASADA (P601)”");return false;}

            if(c6_p615_pd.equals("")) {p615_pd=0;}
            else{p615_pd = Integer.parseInt(c6_p615_pd.trim());}

            if(c6_p615_pl.equals("")) {p615_pl=0;}
            else{p615_pd = Integer.parseInt(c6_p615_pl.trim());}

            if(c6_p615_pm.equals("")) {p615_pm=0;}
            else{p615_pm = Integer.parseInt(c6_p615_pm.trim());}

            if(c6_p615_pmi.equals("")) {p615_pmi=0;}
            else{p615_pmi = Integer.parseInt(c6_p615_pmi.trim());}

            if(c6_p615_pj.equals("")) {p615_pj=0;}
            else{p615_pj = Integer.parseInt(c6_p615_pj.trim());}

            if(c6_p615_pv.equals("")) {p615_pv=0;}
            else{p615_pv = Integer.parseInt(c6_p615_pv.trim());}

            if(c6_p615_ps.equals("")) {p615_ps=0;}
            else{p615_ps = Integer.parseInt(c6_p615_ps.trim());}

           /* p615_pd = Integer.parseInt(c6_p615_pd.trim());
            p615_pl = Integer.parseInt(c6_p615_pl.trim());
            p615_pm = Integer.parseInt(c6_p615_pm.trim());
            p615_pmi = Integer.parseInt(c6_p615_pmi.trim());
            p615_pj = Integer.parseInt(c6_p615_pj.trim());
            p615_pv = Integer.parseInt(c6_p615_pv.trim());
            p615_ps = Integer.parseInt(c6_p615_ps.trim());*/

            p615_op = p615_pd + p615_pl + p615_pm+ p615_pmi + p615_pj + p615_pv + p615_ps;

            Log.e("TOTALPRINCIPALOCUPACION", p615_op+"");

          //  if((p603.equals("1") || p604.equals("1")) && !c6_p615_t.equals("0"))
                if((p603.equals("1") || p604.equals("1")) && p615_op>0)
            {mostrarMensaje("ERROR “PREGUNTA 615 – NO LE CORRESPONDE HORAS TRABAJADAS PORQUE NO TRABAJO LA SEMANA PASADA (P603 O P604)”");return false;}

            if(c6_p615_pd.trim().equals("")){ mostrarMensaje("PREGUNTA 615 PRINCIPAL - DOMINGO: DEBE INGRESAR HORAS TRABAJADAS");return false; }
            if(c6_p615_pl.trim().equals("")){ mostrarMensaje("PREGUNTA 615 PRINCIPAL - LUNES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
            if(c6_p615_pm.trim().equals("")){ mostrarMensaje("PREGUNTA 615 PRINCIPAL - MARTES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
            if(c6_p615_pmi.trim().equals("")){ mostrarMensaje("PREGUNTA 615 PRINCIPAL - MIERCOLES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
            if(c6_p615_pj.trim().equals("")){ mostrarMensaje("PREGUNTA 615 PRINCIPAL - JUEVES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
            if(c6_p615_pv.trim().equals("")){ mostrarMensaje("PREGUNTA 615 PRINCIPAL - VIERNES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
            if(c6_p615_ps.trim().equals("")){ mostrarMensaje("PREGUNTA 615 PRINCIPAL - SABADO: DEBE INGRESAR HORAS TRABAJADAS");return false; }


            if(c6_p615_sd.trim().equals("")){ mostrarMensaje("PREGUNTA 615 SECUNDARIA - DOMINGO: DEBE INGRESAR HORAS TRABAJADAS");return false; }
            if(c6_p615_sl.trim().equals("")){ mostrarMensaje("PREGUNTA 615 SECUNDARIA - LUNES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
            if(c6_p615_sm.trim().equals("")){ mostrarMensaje("PREGUNTA 615 SECUNDARIA - MARTES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
            if(c6_p615_smi.trim().equals("")){ mostrarMensaje("PREGUNTA 615 SECUNDARIA - MIERCOLES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
            if(c6_p615_sj.trim().equals("")){ mostrarMensaje("PREGUNTA 615 SECUNDARIA - JUEVES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
            if(c6_p615_sv.trim().equals("")){ mostrarMensaje("PREGUNTA 615 SECUNDARIA - VIERNES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
            if(c6_p615_ss.trim().equals("")){ mostrarMensaje("PREGUNTA 615 SECUNDARIA - SABADO: DEBE INGRESAR HORAS TRABAJADAS");return false; }

            if(c6_p615_t.equals("0") && c6_p616.equals("1")){mostrarMensaje("PREGUNTA 615 - TOTAL DE HORAS TRABAJADAS EN LA SEMANA PASADA ES CERO Y P616 - NORMALMENTE TRABAJA ESAS HORAS A LA SEMANA?");}


//            if((p605_1.equals("1")||p605_2.equals("1")||p605_3.equals("1")||p605_4.equals("1")||p605_5.equals("1")||p605_6.equals("1")||
//                    p605_7.equals("1")||p605_8.equals("1")||p605_9.equals("1")||p605_10.equals("1")||p605_11.equals("1")||p605_12.equals("1")) && c6_p615_t.equals("0"))
//            {mostrarMensaje("ERROR “PREGUNTA 318 – EL TOTAL DE HORAS TRABAJADAS DURANTE LA SEMANA PASADA NO PUEDE SER CERO”");return false;} //No va segun dijeron
        }

        /*PREGUNTA 616 EDITTEXT, EDITADO 27/10/21
        if (m6_p616_linearlayout.getVisibility()==View.VISIBLE){
            if(c6_p616.trim().equals("")){ mostrarMensaje("PREGUNTA 616: Nº DE HORAS");return false;}

            //REGLA 0079
            if (Integer.parseInt(c6_p616)>112){
                mostrarMensaje("VERIFICAR: PREGUNTA 616 - EL NÚMERO DE HORAS TRABAJADAS A LA SEMANA EXCEDE LAS 112 HORAS");
            }

//            if (Integer.parseInt(c6_p616) == Integer.parseInt(c6_p615_t)){
//                mostrarMensaje("VERIFICAR: PREGUNTA 616 – NO PUEDE SER IGUAL AL TOTAL DE HORAS TRABAJADAS EN LA SEMANA PASADA");
//            }


        }else {
            c6_p616="";
        }*/


        //PREGUNTA 616 RADIOBUTTON
        if (m6_p616_linearlayout.getVisibility() == View.VISIBLE){
            if(c6_p616.equals("-1")){
                mostrarMensaje("PREGUNTA 616: DEBE SELECCIONAR UNA OPCION");
                return false;
            }
        }else{
            c6_p616="";
        }

        //PREGUNTA 616_A
        if (m6_p616_a_linearlayout.getVisibility()==View.VISIBLE){
            if(c6_p616_a.trim().equals("")){ mostrarMensaje("PREGUNTA 616: Nº DE HORAS");return false;}

            //REGLA 0079
            if (Integer.parseInt(c6_p616_a)>168){
                mostrarMensaje("VERIFICAR: PREGUNTA 616A - EL NÚMERO DE HORAS TRABAJADAS A LA SEMANA EXCEDE LAS 168 HORAS");return false;}

//            if (Integer.parseInt(c6_p616) == Integer.parseInt(c6_p615_t)){
//                mostrarMensaje("VERIFICAR: PREGUNTA 616 – NO PUEDE SER IGUAL AL TOTAL DE HORAS TRABAJADAS EN LA SEMANA PASADA");
//            }


        }else {
            c6_p616_a="";
        }

        //PREGUNTA 617
        if (m6_p617_linearlayout.getVisibility() == View.VISIBLE){
            if (c6_p617.equals("-1")){mostrarMensaje("PREGUNTA 617: DEBE MARCAR UNA OPCIÓN"); return false;}
        }else{
            c6_p617 ="";
        }

        //PREGUNTA 618
        if (m6_p618_linearlayout.getVisibility() == View.VISIBLE){
            if (c6_p618.equals("-1")){mostrarMensaje("PREGUNTA 618: DEBE MARCAR UNA OPCIÓN"); return false;}

            //REGLA 0080
            if (c6_p618.equals("1") && (Integer.parseInt(c6_p615_t) == 112)){
                mostrarMensaje("VERIFICAR “PREGUNTA 618 – LA SEMANA PASADA TENIA MÁS TIEMPO DISPONIBLE PARA TRABAJAR PERO SOLO TRABAJO EN TOTAL 112 HORAS (P615)”");
            }

        }else{
            c6_p618 ="";
        }

        //PREGUNTA 619
        if (m6_p619_linearlayout.getVisibility() == View.VISIBLE){
            if (c6_p619.equals("-1")){mostrarMensaje("PREGUNTA 619: DEBE MARCAR UNA OPCIÓN"); return false;}
        }else{
            c6_p619 ="";
        }

        //cambiar por el valor de la 615
//        if(c6_p610_pd.trim().equals("")){ mostrarMensaje("PREGUNTA 610 PRINCIPAL - DOMINGO: DEBE INGRESAR HORAS TRABAJADAS");return false; }
//        if(c6_p610_pl.trim().equals("")){ mostrarMensaje("PREGUNTA 610 PRINCIPAL - LUNES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
//        if(c6_p610_pm.trim().equals("")){ mostrarMensaje("PREGUNTA 610 PRINCIPAL - MARTES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
//        if(c6_p610_pmi.trim().equals("")){ mostrarMensaje("PREGUNTA 610 PRINCIPAL - MIERCOLES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
//        if(c6_p610_pj.trim().equals("")){ mostrarMensaje("PREGUNTA 610 PRINCIPAL - JUEVES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
//        if(c6_p610_pv.trim().equals("")){ mostrarMensaje("PREGUNTA 610 PRINCIPAL - VIERNES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
//        if(c6_p610_ps.trim().equals("")){ mostrarMensaje("PREGUNTA 610 PRINCIPAL - SABADO: DEBE INGRESAR HORAS TRABAJADAS");return false; }
//
        //cambiar por el 616
        //if(c6_p611.trim().equals("")){ mostrarMensaje("PREGUNTA 611: Nº DE HORAS");return false;}

//        if(c6_p610_sd.trim().equals("")){ mostrarMensaje("PREGUNTA 610 SECUNDARIA - DOMINGO: DEBE INGRESAR HORAS TRABAJADAS");return false; }
//        if(c6_p610_sl.trim().equals("")){ mostrarMensaje("PREGUNTA 610 SECUNDARIA - LUNES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
//        if(c6_p610_sm.trim().equals("")){ mostrarMensaje("PREGUNTA 610 SECUNDARIA - MARTES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
//        if(c6_p610_smi.trim().equals("")){ mostrarMensaje("PREGUNTA 610 SECUNDARIA - MIERCOLES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
//        if(c6_p610_sj.trim().equals("")){ mostrarMensaje("PREGUNTA 610 SECUNDARIA - JUEVES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
//        if(c6_p610_sv.trim().equals("")){ mostrarMensaje("PREGUNTA 610 SECUNDARIA - VIERNES: DEBE INGRESAR HORAS TRABAJADAS");return false; }
//        if(c6_p610_ss.trim().equals("")){ mostrarMensaje("PREGUNTA 610 SECUNDARIA - SABADO: DEBE INGRESAR HORAS TRABAJADAS");return false; }


//        llenarVariables();
//        if(idInformante.equals("0")) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}
//        if(!id_informante.equals(idEncuestado) && edad>=12){mostrarMensaje("NÚMERO INFORMANTE: NO ES EL MISMO QUE ESTA SIENDO ENTREVISTADO");return false;}
//        if (m6_p613_linearlayout.getVisibility()==View.VISIBLE){
//            if(c6_p613.equals("-1")){ mostrarMensaje("PREGUNTA 613: DEBE SELECCIONAR UNA OPCION");return false; }
//        } else c6_p613 = "";
//
//        if(m6_p614_linearlayout.getVisibility()==View.VISIBLE){
//            if(c6_p614_mon.trim().equals("")){
//                mostrarMensaje("PREGUNTA 614 - DINERO: DEBE ESPECIFICAR");
//                return false;
//            }
////            if(c6_p614_esp.trim().equals("")){
////                mostrarMensaje("PREGUNTA 614 - ESPECIE: DEBE ESPECIFICAR");
////                return false;
////            }
//        }else{
//            c6_p614_mon = "";c6_p614_esp = "";
//        }
//
//        if(m6_p615_linearlayout.getVisibility()==View.VISIBLE){
//            if(c6_p615_mon.trim().equals("")){ mostrarMensaje("PREGUNTA 615 - DINERO: DEBE ESPECIFICAR");return false; }
////            if(c6_p615_esp.trim().equals("")){ mostrarMensaje("PREGUNTA 615 - ESPECIE: DEBE ESPECIFICAR");return false; }
//        }else{
//            c6_p615_mon = ""; c6_p615_esp = "";
//        }
//
//        if(m6_p616_linearlayout.getVisibility()==View.VISIBLE){
//            if(c6_p616_nas.equals("0")){
//                if(c6_p616_mon.trim().equals("")){ mostrarMensaje("PREGUNTA 616 - DINERO: DEBE ESPECIFICAR");return false; }
////                if(c6_p616_esp.trim().equals("")){ mostrarMensaje("PREGUNTA 616 - ESPECIE: DEBE ESPECIFICAR");return false; }
//            }else{
//                if(c6_p610_tot_sec>0){ mostrarMensaje("PREGUNTA 616: VERIFICAR PREGUNTA 610 HORAS TRABAJADAS ACTIVIDAD SECUNDARIA");return false; }
//            }
//        }else{ c6_p616_nas=""; c6_p616_mon = ""; c6_p616_esp ="";}
//
//        if(m6_p617_linearlayout.getVisibility()==View.VISIBLE) {
//            if(c6_p617.equals("-1")){ mostrarMensaje("PREGUNTA 617: DEBE SELECCIONAR UNA OPCION");return false; }
//            if(c6_p617.equals("2")){
//                if(c6_p617_dist.equals("")){ mostrarMensaje("PREGUNTA 617: DEBE INDICAR DISTRITO");return false; }
//            }
//        }else {c6_p617="";c6_p617_dist="";c6_p617_prov="";c6_p617_dep="";}
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

//    public void limpiar_p616(){
//        c6_p616_mon_EditText.setText("");
//        c6_p616_esp_EditText.setText("");
//    }

//    public void limpiar_p617(){
//        c6_p617_dist_TextView.setText("");
//        c6_p617_prov_TextView.setText("");
//        c6_p617_dep_TextView.setText("");
//    }

    public boolean rango(int ini, int fin, String numero){
        if(Integer.parseInt(numero)>=ini && Integer.parseInt(numero)<=fin) return true; else return false;
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

    private void configurarEditText(final EditText editText, final View view, int tipo,int longitud){
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

    public void verificar_Fragament618_621(){
        Data data = new Data(context);
        data.open();

        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p618p621,idEncuestado).equals("1")){
            if(data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p618,idEncuestado).equals("0") &&
                    data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p619,idEncuestado).equals("0") &&
                    data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p620,idEncuestado).equals("0") &&
                    data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p621,idEncuestado).equals("0")) {
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p618p621,"-1",idEncuestado);
            }
        }

        data.close();
    }

    public void verificar_Fragament622_625(){
        Data data = new Data(context);
        data.open();

        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,idEncuestado).equals("1")){
            if(data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p622,idEncuestado).equals("0") &&
                    data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p623,idEncuestado).equals("0") &&
                    data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p624,idEncuestado).equals("0") &&
                    data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p625,idEncuestado).equals("0")) {
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,"-1",idEncuestado);
            }
        }

        data.close();
    }

    public void configurarTextWatcher(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().equals("")){
                    c6_p615_t_TextView.setText((Integer.parseInt(c6_p615_t_TextView.getText().toString()) - Integer.parseInt(charSequence.toString()))+"");

                    /*int c_p616 = Integer.parseInt(c6_p615_t_TextView.getText().toString()) - Integer.parseInt(charSequence.toString());
                    String c6_P616_text = String.valueOf(c_p616);
                    String enunciado_p616 = c6_p616_TextView.getText().toString()+"";
                    enunciado_p616 = enunciado_p616.replace("ASD", c6_P616_text);
                    c6_p616_TextView.setText(enunciado_p616);*/

                    }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*String c6_P616_text = c6_p615_t_TextView.getText().toString();
                String enunciado_p616 = c6_p616_TextView.getText().toString()+"";
                enunciado_p616 = enunciado_p616.replace("ASD", c6_P616_text);
                c6_p616_TextView.setText(enunciado_p616);
                Log.i(enunciado_p616,"la variable capturada");*/
            }

            @Override
            public void afterTextChanged(Editable editable) {
                int despues = 0;
                if(!editable.toString().equals("")) despues = Integer.parseInt(editable.toString());
                if(!(Integer.parseInt(c6_p615_t_TextView.getText().toString())== 0)){
                    c6_p615_t_TextView.setText((Integer.parseInt(c6_p615_t_TextView.getText().toString()) + despues) +"");

                   /* int c_p616 = Integer.parseInt(c6_p615_t_TextView.getText().toString()) + despues;
                    String c6_P616_text = String.valueOf(c_p616);
                    //String c6_P616_text = c6_p615_t_TextView.getText().toString();
                    String enunciado_p616 = c6_p616_TextView.getText().toString()+"";
                    enunciado_p616 = enunciado_p616.replace("ASD", c6_P616_text);
                    c6_p616_TextView.setText(enunciado_p616);

                    Log.i(enunciado_p616,"la variable capturada");*/


                }else{
                    c6_p615_t_TextView.setText(despues+"");

                }
            }
        });
    }

    public void limpiarP617(){
        c6_p617_RadioGroup.clearCheck();
    }
    public void limpiarP618(){
        c6_p618_RadioGroup.clearCheck();
    }
    public void limpiarP619(){
        c6_p619_RadioGroup.clearCheck();
    }
    private void OcultarOtrosLayouts() {//FLUJO 0076 //FLUJO 76
        llenarVariables();
        int h_t=0;
        try{ h_t = Integer.parseInt(c6_p615_t); }catch (Exception ignore){}

        //Valida el flujo desde la ultima pregunta del fragment a la primera, para evitar conflictos
        /** FLUJO 76 B **/
        if(Integer.parseInt(c6_p619) > 0 &&
                (p612.equals("1") || p612.equals("2"))
        ){
            ocultarFragmentP618P621(false); //pasa al siguiente fragment
        }else
        if(Integer.parseInt(c6_p619) > 0 &&
                (p612.equals("3") || p612.equals("6") || p612.equals("7") || p612.equals("8"))
        ){
            ocultarFragmentP618P621(false); //pasa al siguiente fragment
        }else
            /** FLUJO 76 A **/
//            if(Integer.parseInt(c6_p618) > 0 &&
//                    (p612.equals("1") || p612.equals("2") || p612.equals("3") || p612.equals("6") || p612.equals("7") || p612.equals("8"))
//            ){
//                Log.e("ddd","nada -1"); //Muestra la pregunta P619 (No hace saltos)
//            }else

            ///////////////////////BACKUP (ARRIBA YA ESTABA COMENTADO) //////////////////////////////////////////
           /* if(Integer.parseInt(c6_p618) > 0 &&
                    (p612.equals("4") || p612.equals("5")) && h_t < 15
            ){
                ocultarFragmentP618P621(true);
                ocultarFragmentP622P625(false); //Saltea hasta el fragment de la pregunta P626
            }else
            if(Integer.parseInt(c6_p618) > 0 && p18 > 0 &&
                    (p612.equals("4") || p612.equals("5")) && h_t >= 15 &&
                    p203.equals("1")
            ){
                ocultarFragmentP618P621(true);
                ocultarFragmentP622P625(true);
                ocultarFragmentP626P629(false); //Saltea hasta el fragment de la pregunta 634
            }else
            if( Integer.parseInt(c6_p618) > 0 && p18 > 0 &&
                    (p612.equals("4") || p612.equals("5")) && h_t >= 15 &&
                    !p203.equals("1") && edad >= 18
            ){
                ocultarFragmentP618P621(true);
                ocultarFragmentP622P625(true);
                ocultarFragmentP626P629(false); //Saltea hasta el fragment de la pregunta 634
            }else
            if( Integer.parseInt(c6_p618) > 0 && p18 > 0 &&
                    (p612.equals("4") || p612.equals("5")) && h_t >= 15 &&
                    !p203.equals("1") && (edad >= 5 && edad <= 17)
            ){
                ocultarFragmentP618P621(true);
                ocultarFragmentP622P625(true);
//                ocultarFragmentP626P629(false); //Saltea hasta el fragment de la pregunta 634
                ocultarFragmentP626P629(true); //Saltea hasta el fragment de la pregunta 635
            }else
            if( Integer.parseInt(c6_p618) > 0 && p18 == 0 &&
                    (p612.equals("4") || p612.equals("5")) && h_t >= 15
            ){
                ocultarFragmentP618P621(true);
                ocultarFragmentP622P625(true);
//                ocultarFragmentP626P629(false); //Saltea hasta el fragment de la pregunta 634
                ocultarFragmentP626P629(true); //Saltea hasta el fragment de la pregunta 635
                */

           /*SI P618>0 AND P612=i => pase a P619, para cualquier i=1,2,3,6,7,8
SI P618>0 AND (P612=4 AND P615_T<15 OR P612=5) => pase a P626
SI P618>0 AND P612=4 AND P615_T>=15 AND P203=1 AND (NINGUN MIEMBRO DEL HOGAR INGRESO INFORMACION EN P634) => pase a P634_1
SI CONTEO[PERSONAS ((P205_A<18 OR P205_B>=0) AND P208=1)]>0 AND P618>0 AND P612=4 AND P615_T>=15 AND P203<>1 AND P208_A>=18 AND (NINGUN MIEMBRO DEL HOGAR INGRESO INFORMACION EN P634) => pase a P634_1
SI CONTEO[PERSONAS ((P205_A<18 OR P205_B>=0) AND P208=1)]>0 AND P618>0 AND P612=4 AND P615_T>=15 AND P203<>1 AND P208_A>=18 AND (ALGÚN MIEMBRO DEL HOGAR INGRESO INFORMACION EN P634) => Achure P634 y pase a P635_1
SI CONTEO[PERSONAS ((P205_A<18 OR P205_B>=0) AND P208=1)]>0 AND P618>0 AND P612=4 AND P615_T>=15 AND P203<>1 AND (P208_A=5:17) => Achure P634 y pase a P635_1
SI CONTEO[PERSONAS ((P205_A<18 OR P205_B>=0) AND P208=1)]=0 AND P618>0 AND P612=4 AND P615_T>=15 => Achure P634 y pase a P635_1
*/
        ///////////////SE ESTA ELIMINANDO EL || P612.EQUALS ("5")///////////////////////////////////////////////////////////////////////////
                if(Integer.parseInt(c6_p618) > 0 &&
                        (p612.equals("4")  && h_t < 15) || p612.equals("5")
                ){
                    ocultarFragmentP618P621(true);
                    ocultarFragmentP622P625(false); //Saltea hasta el fragment de la pregunta P626
                }else
                if(Integer.parseInt(c6_p618) > 0 && p18 > 0 &&
                        p612.equals("4") && h_t >= 15 &&
                        p203.equals("1")
                ){
                    ocultarFragmentP618P621(true);
                    ocultarFragmentP622P625(true);
                    ocultarFragmentP626P629(false); //Saltea hasta el fragment de la pregunta 634
                }else
                if( Integer.parseInt(c6_p618) > 0 && p18 > 0 &&
                        p612.equals("4")  && h_t >= 15 &&
                        !p203.equals("1") && edad >= 18
                ){
                    ocultarFragmentP618P621(true);
                    ocultarFragmentP622P625(true);
                    ocultarFragmentP626P629(false); //Saltea hasta el fragment de la pregunta 634
                }else
                if( Integer.parseInt(c6_p618) > 0 && p18 > 0 &&
                        p612.equals("4")  && h_t >= 15 &&
                        !p203.equals("1") && (edad >= 5 && edad <= 17)
                ){
                    ocultarFragmentP618P621(true);
                    ocultarFragmentP622P625(true);
//                ocultarFragmentP626P629(false); //Saltea hasta el fragment de la pregunta 634
                    ocultarFragmentP626P629(true); //Saltea hasta el fragment de la pregunta 635
                }else
                if( Integer.parseInt(c6_p618) > 0 && p18 == 0 &&
                        p612.equals("4") && h_t >= 15
                ){
                    ocultarFragmentP618P621(true);
                    ocultarFragmentP622P625(true);
//                ocultarFragmentP626P629(false); //Saltea hasta el fragment de la pregunta 634
                    ocultarFragmentP626P629(true); //Saltea hasta el fragment de la pregunta 635
            }else
                /** FLUJO 76 **/
//                if(c6_p617.equals("2") &&
//                        (p612.equals("1") || p612.equals("2") || p612.equals("3") || p612.equals("6") || p612.equals("7") || p612.equals("8"))
//                ){
//                    Log.e("dd","muestra P619"); //Muestra la pregunta P619 (No hace saltos)
//                }else
                if(c6_p617.equals("2") &&
                        (p612.equals("4") || p612.equals("5")) && h_t < 15
                ){
                    ocultarFragmentP618P621(true);
                    ocultarFragmentP622P625(false); //Saltea hasta el fragment de la pregunta P626
                }else
                if(c6_p617.equals("2") &&
                        (p612.equals("4") || p612.equals("5")) && h_t >= 15 &&
                        p18 > 0 && p203.equals("1")
                ){
                    ocultarFragmentP618P621(true);
                    ocultarFragmentP622P625(true);
                    ocultarFragmentP626P629(false); //Saltea hasta el fragment de la pregunta 634
                } else
                if(c6_p617.equals("2") &&
                        (p612.equals("4") || p612.equals("5")) && h_t >= 15 && //verificacion 4 y 5 del flujo 76
                        edad >= 18 && p18 > 0 && !p203.equals("1")
                ){
                    ocultarFragmentP618P621(true);
                    ocultarFragmentP622P625(true);
                    ocultarFragmentP626P629(false); //Saltea hasta el fragment de la pregunta 634
                } else
                if(c6_p617.equals("2") &&
                        (p612.equals("4") || p612.equals("5")) && h_t >= 15 && //verificacion 4 y 5 del flujo 76
                        edad >= 5 && edad <=17 && p18 > 0 && !p203.equals("1")
                ){
                    ocultarFragmentP618P621(true);
                    ocultarFragmentP622P625(true);
                    ocultarFragmentP626P629(true); //Saltea hasta el fragment de la pregunta 635
                } else
                if(c6_p617.equals("2") &&
                        p18 == 0
                ){
                    ocultarFragmentP618P621(true);
                    ocultarFragmentP622P625(true);
                    ocultarFragmentP626P629(true); //Saltea hasta el fragment de la pregunta 635
                }else{
                    Log.e("P613P613","No cumple condiciones OcultarOtrosLayouts()");
                }
    }
    public void ocultarFragmentP618P621(boolean ocultar){
        Data data = new Data(context);
        data.open();
        if(ocultar) {
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
            data.actualizarElemento(getNombreTabla(), contentValues, idEncuestado);
            data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p618p621, "-1", idEncuestado);
        }else {
            data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p618p621, "1", idEncuestado);
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
        if(ocultar){
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.modulo6_c6_p630, "");
            contentValues.put(SQLConstantes.modulo6_c6_p631, "");
            contentValues.put(SQLConstantes.modulo6_c6_p631_o, "");
            contentValues.put(SQLConstantes.modulo6_c6_p632_1, "");
            contentValues.put(SQLConstantes.modulo6_c6_p632_2, "");
            contentValues.put(SQLConstantes.modulo6_c6_p632_3, "");
            contentValues.put(SQLConstantes.modulo6_c6_p632_4, "");
            contentValues.put(SQLConstantes.modulo6_c6_p632_5, "");
            contentValues.put(SQLConstantes.modulo6_c6_p632_6, "");
            contentValues.put(SQLConstantes.modulo6_c6_p632_7, "");
            contentValues.put(SQLConstantes.modulo6_c6_p632_8, "");
            contentValues.put(SQLConstantes.modulo6_c6_p632_9, "");
            contentValues.put(SQLConstantes.modulo6_c6_p632_10, "");
            contentValues.put(SQLConstantes.modulo6_c6_p632_10_o, "");
            contentValues.put(SQLConstantes.modulo6_c6_p632_11, "");
            contentValues.put(SQLConstantes.modulo6_c6_p633, "");
            contentValues.put(SQLConstantes.modulo6_c6_p632i, "");
            contentValues.put(SQLConstantes.modulo6_c6_p634_1, "");
            contentValues.put(SQLConstantes.modulo6_c6_p634_2, "");
            contentValues.put(SQLConstantes.modulo6_c6_p634_3, "");
            contentValues.put(SQLConstantes.modulo6_c6_p634_4, "");
            contentValues.put(SQLConstantes.modulo6_c6_p634_5, "");
            contentValues.put(SQLConstantes.modulo6_c6_p634_6, "");
            contentValues.put(SQLConstantes.modulo6_c6_p634_7, "");
            //contentValues.put(SQLConstantes.modulo6_c6_p634_6_o, "");
            contentValues.put(SQLConstantes.modulo6_c6_p634_7_o, "");
            data.actualizarElemento(getNombreTabla(), contentValues, idEncuestado);
            data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p626p629, "-1", idEncuestado);
        }else {
            data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p626p629, "1", idEncuestado);
        }
        data.close();
    }

    public boolean validar_P616_a(){
        llenarVariables();
        boolean valido = false;
        if(c6_p616.equals("2")){
            valido = true;
        }
        return  valido;
    }

    public boolean validar_P617(){
        llenarVariables();
        boolean valido = false;
        if(edad >= 5 && Integer.parseInt(p612) > 0){
            valido = true;
        }
        return  valido;
    }
    public boolean validar_P618(){
        llenarVariables();
        boolean valido = false;
        if(edad >= 5 && c6_p617.equals("1")){
            valido = true;
        }
        return  valido;
    }
    public boolean validar_P619(){
        llenarVariables();
        boolean valido = false;
        if(edad >= 5 &&
                (Integer.parseInt(c6_p618) > 0 || c6_p617.equals("2")) &&
                (p612.equals("1") || p612.equals("2") || p612.equals("3") || p612.equals("6") || p612.equals("7") || p612.equals("8"))
        ){
            valido = true;
        }
        return  valido;
    }
}
