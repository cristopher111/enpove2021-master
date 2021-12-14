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
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethods;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP622P625 extends FragmentPagina {
    String idEncuestado;
    String idVivienda, idHogar, idInformante, id_informante="";
    Context context;

    Spinner informanteSpinner;

    RadioGroup c6_p625_RadioGroup, c6_p626_RadioGroup, c6_p627_RadioGroup, c6_p628_RadioGroup, c6_p629_RadioGroup;
    TextView c6_p625_dist_TextView, c6_p625_prov_TextView, c6_p625_depa_TextView;
    AutoCompleteTextView c6_p625_Autocomplete;
    EditText c6_p628_o_EditText;
    LinearLayout m6_p625_linearlayout, m6_625_linearlayout_subpregunta, m6_p626_linearlayout, m6_p627_linearlayout, m6_p628_linearlayout, m6_p629_linearlayout;

    private String c6_p625;
    private String c6_p625_cod_dist;
    private String c6_p625_dist;
    private String c6_p625_cod_prov;
    private String c6_p625_prov;
    private String c6_p625_cod_depa;
    private String c6_p625_depa;
    private String c6_p626;
    private String c6_p627;
    private String c6_p628;
    private String c6_p628_o;
    private String c6_p629;

    TextView tv626periodo;

    int edad=0;
    private String p203;
    private String p506,p511;
    private String p617,p612,p615_t,p607,p608;
    private String p605_1,p605_2,p605_3,p605_4,p605_5,p605_6,p605_7,p605_8,p605_9,p605_10,p605_11,p605_12;
    int p18=0,p622_1=0,p622_2=0;
    private int p624_1,p624_2;
    private String p624_3,p633,p624_4;
    private boolean p634_contestado = false;

    @SuppressLint("ValidFragment")
    public FragmentP622P625(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idHogar = residente.getId_hogar();
        idVivienda = residente.getId_vivienda();
        idInformante = "";
        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        p203 = residente.getC2_p203();

        p506 = data.getModulo5(idEncuestado).getC5_p506();
        p511 = data.getModulo5(idEncuestado).getC5_p511();
        p617 = data.getModulo6(idEncuestado).getC6_p617();
        p612 = data.getModulo6(idEncuestado).getC6_p612();
        p615_t = data.getModulo6(idEncuestado).getC6_p615_t();
        p607 = data.getModulo6(idEncuestado).getC6_p607();
        p608 = data.getModulo6(idEncuestado).getC6_p608();
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
        try{
            p18 = Integer.parseInt(data.getHogar(idHogar).getP18());
        }catch (Exception ignore){}
        try{ p624_1 = Integer.parseInt( data.getModulo6(idEncuestado).getC6_p624_mon()); }
        catch (Exception e){ p624_1 = 0; }
        try{ p624_2 = Integer.parseInt( data.getModulo6(idEncuestado).getC6_p624_mon()); }
        catch (Exception e){ p624_2 = 0; }
        p624_3 = data.getModulo6(idEncuestado).getC6_p624_nas();
        p624_4 = data.getModulo6(idEncuestado).getC6_p624_nas2();
        try{
            p622_1 = Integer.parseInt( data.getModulo6(idEncuestado).getC6_p622_mon());
        }catch (Exception ignore){}
        try{
            p622_2 = Integer.parseInt( data.getModulo6(idEncuestado).getC6_p622_esp());
        }catch (Exception ignore){}
        p633 = data.getModulo6(idEncuestado).getC6_p633();

        if(data.getAllResidentesHogar(idHogar).size() > 0){
            for(int i=0 ; i < data.getAllResidentesHogar(idHogar).size() ; i++){
                if(!data.getAllResidentesHogar(idHogar).get(i).get_id().equals(idEncuestado)){
                    String cadena = "";
                    try{
                        cadena = data.getModulo6(data.getAllResidentesHogar(idHogar).get(i).get_id()).getC6_p634_1();
                    }catch (Exception e){}
                    if(!cadena.equals("")) {
                        p634_contestado = true;
                    }
                }
            }
        }
        data.close();
    }

    public FragmentP622P625() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p622_p625, container, false);
        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

        //Anthony M
        c6_p625_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_625_radiogroup_C6_P625);
        c6_p625_Autocomplete = (AutoCompleteTextView) rootView.findViewById(R.id.mod6_625_autocompletetextview);
        c6_p625_dist_TextView = (TextView) rootView.findViewById(R.id.mod6_c625_txtDistrito);
        c6_p625_prov_TextView = (TextView) rootView.findViewById(R.id.mod6_c625_txtProvincia);
        c6_p625_depa_TextView = (TextView) rootView.findViewById(R.id.mod6_c625_txtDepartamento);

        c6_p626_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_626_radiogroup_C6_P626);
        c6_p627_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_627_radiogroup_C6_P627);
        c6_p628_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_628_radiogroup_C6_P628);
        c6_p628_o_EditText = (EditText) rootView.findViewById(R.id.mod6_628_edittext_C6_P628_O);
        c6_p629_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod6_629_radiogroup_C6_P629);

        tv626periodo  = (TextView) rootView.findViewById(R.id.tv626periodo);

        m6_p625_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p625);
        m6_625_linearlayout_subpregunta = (LinearLayout) rootView.findViewById(R.id.mod6_625_layout_subpregunta);
        m6_p626_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p626);
        m6_p627_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p627);
        m6_p628_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p628);
        m6_p629_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m6_p629);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String text626 = getString(R.string.modulo_6_p626, UtilsMethods.getPeriodoReferenciaSemana(4));
        tv626periodo.setText(text626);


        configurarEditText(c6_p628_o_EditText,m6_p628_linearlayout,0,100);
        //Anthony M
        c6_p625_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if(validar_P626()) m6_p626_linearlayout.setVisibility(View.VISIBLE);
//                else {
//                    limpiarP626(); m6_p626_linearlayout.setVisibility(View.GONE);
//                }
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                switch (seleccionado){
                    case 1:
                        c6_p625_depa_TextView.setText("");c6_p625_prov_TextView.setText("");c6_p625_dist_TextView.setText("");
                        c6_p625_cod_depa = ""; c6_p625_cod_prov = ""; c6_p625_cod_dist = "";
                        m6_625_linearlayout_subpregunta.setVisibility(View.GONE);
//                        validarFlujo80();
                        break;
                    case 2:m6_625_linearlayout_subpregunta.setVisibility(View.VISIBLE);break;
                }
            }
        });
        Data data = new Data(context);
        data.open();
        ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(),R.layout.lista_item,R.id.item,data.getUbigeos());
        data.close();
        c6_p625_Autocomplete.setAdapter(adapter);
        c6_p625_Autocomplete.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    ocultarTeclado(c6_p625_Autocomplete);
                    m6_p625_linearlayout.requestFocus();
                    return true;
                }
                return false;
            }
        });
        c6_p625_Autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Data data1 = new Data(context);
                data1.open();
                Ubigeo ubigeo = data1.getUbigeo(c6_p625_Autocomplete.getText().toString());
                data1.close();
                c6_p625_depa_TextView.setText(ubigeo.getNom_departamento());
                c6_p625_prov_TextView.setText(ubigeo.getNom_provincia());
                c6_p625_dist_TextView.setText(ubigeo.getNom_distrito());
                c6_p625_cod_depa = ubigeo.getCod_departamento();
                c6_p625_cod_prov = ubigeo.getCod_provincia();
                c6_p625_cod_dist = ubigeo.getCod_distrito();
                c6_p625_Autocomplete.setText("");
                ocultarTeclado(c6_p625_Autocomplete);
                m6_p625_linearlayout.requestFocus();
            }
        });
        c6_p626_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(validar_P627()){ //FLUJO 0081 FLUJO 81
                    m6_p627_linearlayout.setVisibility(View.VISIBLE);
                }else{
                    limpiarP627();
                    m6_p627_linearlayout.setVisibility(View.GONE);
                }
            }
        });
        c6_p627_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(validar_P628()){ //FLUJO 0081 FLUJO 81
                    m6_p628_linearlayout.setVisibility(View.VISIBLE);
                }else{
                    limpiarP628();limpiarP629();
                    m6_p628_linearlayout.setVisibility(View.GONE);
                    m6_p629_linearlayout.setVisibility(View.GONE);
                }
            }
        });
        c6_p628_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int pos = radioGroup.indexOfChild(c6_p628_RadioGroup.findViewById(c6_p628_RadioGroup.getCheckedRadioButtonId()));
                if(validar_P629()){ //FLUJO 0082 FLUJO 82
                    m6_p629_linearlayout.setVisibility(View.VISIBLE);
                }else {
                    limpiarP629();
                    m6_p629_linearlayout.setVisibility(View.GONE);
                }
                if(pos==8){
                    c6_p628_o_EditText.setEnabled(true);
                    c6_p628_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    c6_p628_o_EditText.setText("");
                    c6_p628_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                    c6_p628_o_EditText.setEnabled(false);
                }

            }
        });

//        llenarVista();
        llenarVista();
        cargarDatos();
    }

    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo6_id_informante,idInformante);
        //Anthony M
        contentValues.put(SQLConstantes.modulo6_c6_p625,c6_p625);
        contentValues.put(SQLConstantes.modulo6_c6_p625_cod_dist,c6_p625_cod_dist);
        contentValues.put(SQLConstantes.modulo6_c6_p625_dist,c6_p625_dist);
        contentValues.put(SQLConstantes.modulo6_c6_p625_cod_prov,c6_p625_cod_prov);
        contentValues.put(SQLConstantes.modulo6_c6_p625_prov,c6_p625_prov);
        contentValues.put(SQLConstantes.modulo6_c6_p625_cod_depa,c6_p625_cod_depa);
        contentValues.put(SQLConstantes.modulo6_c6_p625_depa,c6_p625_depa);
        contentValues.put(SQLConstantes.modulo6_c6_p626,c6_p626);
        contentValues.put(SQLConstantes.modulo6_c6_p627,c6_p627);
        contentValues.put(SQLConstantes.modulo6_c6_p628,c6_p628);
        contentValues.put(SQLConstantes.modulo6_c6_p628_o,c6_p628_o);
        contentValues.put(SQLConstantes.modulo6_c6_p629,c6_p629);

        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp622p625,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo6_COB600,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();
        ocultarOtrosLayouts();
    }
    public void ocultarOtrosLayouts(){
        llenarVariables();
        Log.e("eee","p634_contestado = "+p634_contestado);
        /** FLUJO 83 **/
        if(m6_p629_linearlayout.getVisibility() == View.VISIBLE){
            if(c6_p629.equals("2")){ //FLUJO 0083 //FLUJO 83
                Log.e("P622P625","oculta fragment p634 desde p629");
                ocultarFragmentP626P629(true);
            }else{
                Log.e("P622P625","muestra fragment p634 desde p629");
                ocultarFragmentP626P629(false);
            }
        }else
        /** FLUJO 82 **/
        if(c6_p628.equals("1") || c6_p628.equals("2")){
            ocultarFragmentP626P629(false); //Pasa a fragment del P633
        }else
        /** FLUJO 81 **/
        if(c6_p627.equals("1")){
            ocultarFragmentP626P629(false); //Pasa a fragment del P632
        }else
        /** FLUJO 80 **/
        if(p18 > 0 && (c6_p625.equals("1") || c6_p625.equals("2")) &&
                ((p607.equals("") && p608.equals("")) || p607.equals("1") || p608.equals("1"))
                && p203.equals("1")
        ){
            Log.e("condicion","0-");
            ocultarFragmentP626P629(false); //Pasa a fragment del P634
        }else
        if(p18 > 0 && (c6_p625.equals("1") || c6_p625.equals("2")) &&
                ((p607.equals("") && p608.equals("")) || p607.equals("1") || p608.equals("1"))
                && !p203.equals("1") && edad >= 18
        ){
            Log.e("condicion","1");
            ocultarFragmentP626P629(false); //Pasa a fragment del P634
        }else
        if(p18 > 0 && (c6_p625.equals("1") || c6_p625.equals("2")) &&
                ((p607.equals("") && p608.equals("")) || p607.equals("1") || p608.equals("1"))
                && !p203.equals("1") && edad < 18
        ){
            Log.e("condicion","2");
//            ocultarFragmentP626P629(false); //Pasa a fragment del P634
            ocultarFragmentP626P629(true); //Pasa a fragment del P635
        }else
        if(p18 > 0 && (c6_p625.equals("1") || c6_p625.equals("2")) &&
                ((p607.equals("") && p608.equals("")) || p607.equals("1") || p608.equals("1"))
        ){
            Log.e("condicion","2");
            ocultarFragmentP626P629(false); //Pasa a fragment del P634
        }else
        {
            Log.e("P626P629","No cumple condiciones ocultarOtrosLayouts(), Saltará por defecto al fragment de la pregunta p635");
            ocultarFragmentP626P629(true); //Pasa a fragment del P635
        }
    }

    @Override
    public void llenarVariables() {
        idInformante = obtener_Nresidente(informanteSpinner);
        c6_p625 = c6_p625_RadioGroup.indexOfChild(c6_p625_RadioGroup.findViewById(c6_p625_RadioGroup.getCheckedRadioButtonId())) +"";
        c6_p625_cod_dist = obtener_codigoUbigeo(1);
        c6_p625_dist = c6_p625_dist_TextView.getText()+"";
        c6_p625_cod_prov = obtener_codigoUbigeo(2);
        c6_p625_prov = c6_p625_prov_TextView.getText()+"";
        c6_p625_cod_depa = obtener_codigoUbigeo(3);
        c6_p625_depa = c6_p625_depa_TextView.getText()+"";
        c6_p626 = c6_p626_RadioGroup.indexOfChild(c6_p626_RadioGroup.findViewById(c6_p626_RadioGroup.getCheckedRadioButtonId())) +"";
        c6_p627 = c6_p627_RadioGroup.indexOfChild(c6_p627_RadioGroup.findViewById(c6_p627_RadioGroup.getCheckedRadioButtonId())) +"";
        c6_p628 = c6_p628_RadioGroup.indexOfChild(c6_p628_RadioGroup.findViewById(c6_p628_RadioGroup.getCheckedRadioButtonId())) +"";
        c6_p628_o = c6_p628_o_EditText.getText().toString();
        c6_p629 = c6_p629_RadioGroup.indexOfChild(c6_p629_RadioGroup.findViewById(c6_p629_RadioGroup.getCheckedRadioButtonId())) +"";
    }
    public String obtener_codigoUbigeo(int v){
        String c = c6_p625_dist_TextView.getText()+" ("+c6_p625_prov_TextView.getText()+" - "+c6_p625_depa_TextView.getText()+")";
        String cadena="";
        try{
            Data data1 = new Data(context);
            data1.open();
            Ubigeo ubigeo = data1.getUbigeo(c);
            data1.close();
            if(v == 1) cadena = ubigeo.getCod_distrito();
            if(v == 2) cadena = ubigeo.getCod_provincia();
            if(v == 3) cadena = ubigeo.getCod_departamento();
        }catch (Exception e){
//            Log.e("error","ubigeo P622P625");
            cadena = "";
        }
        return cadena;
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
            if(modulo6.getC6_p625() != null && !modulo6.getC6_p625().equals("-1") && !modulo6.getC6_p625().equals(""))((RadioButton)c6_p625_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p625()))).setChecked(true);
            c6_p625_dist_TextView.setText(modulo6.getC6_p625_dist());
            c6_p625_prov_TextView.setText(modulo6.getC6_p625_prov());
            c6_p625_depa_TextView.setText(modulo6.getC6_p625_depa());
            if(modulo6.getC6_p626() != null && !modulo6.getC6_p626().equals("-1") && !modulo6.getC6_p626().equals(""))((RadioButton)c6_p626_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p626()))).setChecked(true);
            if(modulo6.getC6_p627() != null && !modulo6.getC6_p627().equals("-1") && !modulo6.getC6_p627().equals(""))((RadioButton)c6_p627_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p627()))).setChecked(true);
            if(modulo6.getC6_p628() != null && !modulo6.getC6_p628().equals("-1") && !modulo6.getC6_p628().equals(""))((RadioButton)c6_p628_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p628()))).setChecked(true);
            c6_p628_o_EditText.setText(modulo6.getC6_p628_o());
            if(modulo6.getC6_p629() != null && !modulo6.getC6_p629().equals("-1") && !modulo6.getC6_p629().equals(""))((RadioButton)c6_p629_RadioGroup.getChildAt(Integer.parseInt(modulo6.getC6_p629()))).setChecked(true);
        }
        inicio();
        data.close();
    }

    private void inicio() {
        Log.e("Fragment","estas en P622P625");
        //p625
        if(validar_P625()) m6_p625_linearlayout.setVisibility(View.VISIBLE);
        else {
            limpiarP625();
            m6_p625_linearlayout.setVisibility(View.GONE);
        }
        //p626
        if(validar_P626()) m6_p626_linearlayout.setVisibility(View.VISIBLE);
        else {
            limpiarP626();
            m6_p626_linearlayout.setVisibility(View.GONE);
        }
        //p627
        if(validar_P627()) m6_p627_linearlayout.setVisibility(View.VISIBLE);
        else {
            limpiarP627();
            m6_p627_linearlayout.setVisibility(View.GONE);
        }
        //p628
        if(validar_P628()) m6_p628_linearlayout.setVisibility(View.VISIBLE);
        else {
            limpiarP628();
            m6_p628_linearlayout.setVisibility(View.GONE);
        }
        //p629
        if(validar_P629()) m6_p629_linearlayout.setVisibility(View.VISIBLE);
        else {
            limpiarP629();
            m6_p629_linearlayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void llenarVista() {
//        Data data = new Data(context);
//        data.open();
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p622,idEncuestado)) m6_p622_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p623,idEncuestado)) m6_p623_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p624,idEncuestado)) m6_p624_linearlayout.setVisibility(View.GONE);
//        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p625,idEncuestado)) m6_p625_linearlayout.setVisibility(View.GONE);
//        data.close();
    }

    @Override
    public boolean validarDatos() {
        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        //PREGUNTA 625
        if(m6_p625_linearlayout.getVisibility()==View.VISIBLE) {
            if(c6_p625.equals("-1")){ mostrarMensaje("PREGUNTA 625: DEBE SELECCIONAR UNA OPCION");return false; }
            if(c6_p625.equals("2")){
                if(c6_p625_dist.equals("")){ mostrarMensaje("PREGUNTA 625: DEBE INDICAR DISTRITO");return false; }
            }
        }else {
            c6_p625="";
            c6_p625_cod_dist="";
            c6_p625_dist="";
            c6_p625_cod_prov="";
            c6_p625_prov="";
            c6_p625_cod_depa="";
            c6_p625_depa="";
        }

        //PREGUNTA 626
        if(m6_p626_linearlayout.getVisibility()==View.VISIBLE){
            if(c6_p626.equals("-1")){ mostrarMensaje("PREGUNTA 626: DEBE SELECCIONAR UNA OPCION");return false; }

            //REGLA 0085
            if ((c6_p626.equals("1") || c6_p626.equals("2")) && p506.equals("2") && p511.equals("2")){
                mostrarMensaje("VERIFICAR “EN LA PREGUNTA 506 DICE QUE NO ESTUDIA POR ESTAR TRABAJANDO (P511) PERO SE ENCUENTRA   DESOCUPADO LAS ÚLTIMAS CUATRO SEMANAS (P626)”");
            }

        }else{
            c6_p626 = "";
        }

        //PREGUNTA 627
        if(m6_p627_linearlayout.getVisibility()==View.VISIBLE){
            if(c6_p627.equals("-1")){ mostrarMensaje("PREGUNTA 627: DEBE SELECCIONAR UNA OPCION");return false; }
        }else{
            c6_p627 = "";
        }

        //PREGUNTA 628
        if(m6_p628_linearlayout.getVisibility()==View.VISIBLE){
            if(c6_p628.equals("-1")){ mostrarMensaje("PREGUNTA 628: DEBE SELECCIONAR UNA OPCION");return false; }
            if(c6_p628.equals("8")){
                if(c6_p628_o.trim().equals("")){ mostrarMensaje("PREGUNTA 628 - OPCION 8: DEBE ESPECIFICAR OTRO");return false; }
            }
            if(c6_p628.equals("8") && !c6_p628_o.equals("") && c6_p628_o.length() < 3){mostrarMensaje("PREGUNTA 628: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}

            //REGLA 0086
            if (c6_p628.equals("4") && p506.equals("2")){
                mostrarMensaje("ERROR “EN LA PREGUNTA 628 INDICA QUE LA SEMANA PASADA ESTUVO ESTUDIANDO PERO EN LA PREGUNTA 506 DICE QUE ACTUALMENTE NO ASISTE A ALGÚN CENTRO DE EDUCACIÓN BÁSICA O SUPERIOR”");return false;
            }

        }else{
            c6_p628 = "";
            c6_p628_o = "";
        }

        //PREGUNTA 629
        if(m6_p629_linearlayout.getVisibility()==View.VISIBLE){
            if(c6_p629.equals("-1")){ mostrarMensaje("PREGUNTA 629: DEBE SELECCIONAR UNA OPCION");return false; }
        }else{
            c6_p629 = "";
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
    public void limpiarP625(){
        c6_p625_RadioGroup.clearCheck();
        c6_p625_dist_TextView.setText("");
        c6_p625_prov_TextView.setText("");
        c6_p625_depa_TextView.setText("");
    }
    public void limpiarP626(){
        c6_p626_RadioGroup.clearCheck();
    }
    public void limpiarP627(){
        c6_p627_RadioGroup.clearCheck();
    }
    public void limpiarP628(){
        c6_p628_RadioGroup.clearCheck();
        c6_p628_o_EditText.setText("");
    }
    public void limpiarP629(){
        c6_p629_RadioGroup.clearCheck();
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
    public boolean validar_P625(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 &&
                (p612.equals("1") || p612.equals("2") || p612.equals("3") || p612.equals("6") || p612.equals("7") || p612.equals("8")
                && (p624_1 >0 || p624_2 >0 || !p624_3.equals("") || !p624_4.equals("") ) //para modificar
                )
        ){
            valido = true;
        }
        return valido;
    }
    public boolean validar_P626(){
        //////////////////////////////BACKUP SIN MODIFICACIONES/////
        /*boolean valido = false;
        llenarVariables();
        if(edad >= 5 &&
                (p605_1.equals("2") && p605_2.equals("2") && p605_3.equals("2") && p605_4.equals("2") && p605_5.equals("2") && p605_6.equals("2") &&
                        p605_7.equals("2") && p605_8.equals("2") && p605_9.equals("2") && p605_10.equals("2") && p605_11.equals("2") && p605_12.equals("2")) ||
                (((p612.equals("4") || p612.equals("5")) && Integer.parseInt(p615_t) < 15) ||
                        ((!p612.equals("4") && !p612.equals("5")) && p607.equals("2") && !p608.equals("1")))
        ){
            valido = true;
        }
        return valido;*/

        ///////////////////////////OBSERVACION 26 (CORREGIO PANTALLA BLANCA AL MARCA P612 =5) 19/11/21

        boolean valido = false;
        llenarVariables();
        if(edad >= 5 &&
                (p605_1.equals("2") && p605_2.equals("2") && p605_3.equals("2") && p605_4.equals("2") && p605_5.equals("2") && p605_6.equals("2") &&
                        p605_7.equals("2") && p605_8.equals("2") && p605_9.equals("2") && p605_10.equals("2") && p605_11.equals("2") && p605_12.equals("2")) ||
                (((p612.equals("4")  && Integer.parseInt(p615_t) < 15)|| p612.equals("5")) ||
                        ((!p612.equals("4") && !p612.equals("5")) && p607.equals("2") && !p608.equals("1")))
        ){
            valido = true;
        }
        return valido;

    }
    public boolean validar_P627(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 && Integer.parseInt(c6_p626) > 0){
            valido = true;
        }
        return valido;
    }
    public boolean validar_P628(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 && Integer.parseInt(c6_p627) == 2){
            valido = true;
        }
        return valido;
    }
    public boolean validar_P629(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 5 && Integer.parseInt(c6_p628) >= 3 && Integer.parseInt(c6_p628) <=8){
            valido = true;
        }
        return valido;
    }
}
