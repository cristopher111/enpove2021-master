package com.example.ricindigus.enpove2021.fragments.modulo5;


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
import android.text.InputType;
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
import com.example.ricindigus.enpove2021.modelo.pojos.Marco;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo5;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.modelo.pojos.Ubigeo;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP506P507 extends FragmentPagina {
    String idEncuestado;
    String idVivienda, idHogar, idInformante;
    Context context;

    Spinner informanteSpinner;

    //    RadioGroup c5_p506_1_RadioGroup, c5_p506_4_RadioGroup;
//    TextView c5_p506_2o3_TextView;
//    EditText c5_p506_2o3_EditText;
    RadioGroup c5_p506_RadioGroup,c5_p506a_RadioGroup;
    RadioGroup c5_p507_RadioGroup;
    EditText c5_p507_anio_EditText, c5_p507_grado_EditText;
    RadioGroup c5_p507_ce_RadioGroup;
    //    AutoCompleteTextView autoCompleteTextView;
//    TextView txtDistrito, txtProvincia, txtDepartamento;
//    LinearLayout m5_p506_linearlayout,m5_p506_subpregunta_linearlayout, m5_p507_linearlayout, m5_p507_subpregunta_linearlayout;
    LinearLayout m5_p506_linearlayout, m5_p507_linearlayout,m5_p506a_linearlayout;

    //    int c5_p501=0;
//    private String c5_p506_1;
//    private String c5_p506_2;
//    private String c5_p506_3;
//    private String c5_p506_4;
//    private String c5_p507;
//    private String c5_p507_dist;
//    private String c5_p507_prov;
//    private String c5_p507_dep;
    private String marco_dist;
    private String marco_prov;
    private String marco_dep;
    //Anthony M
    private String c5_p506;
    private String c5_p506a;
    private String c5_p507;
    private String c5_p507_anio;
    private String c5_p507_grado;
    private String c5_p507_ce;

    private int edad=0, sexo;
    private String p505,p501,p503;


    @SuppressLint("ValidFragment")
    public FragmentP506P507(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idHogar = residente.getId_hogar();
        idVivienda = residente.getId_vivienda();
        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        p505 = data.getModulo5(idEncuestado).getC5_p505();
        p501 = data.getModulo5(idEncuestado).getC5_p501();
        p503 = data.getModulo5(idEncuestado).getC5_p503();
        idInformante = "";
        data.close();
    }

    public FragmentP506P507() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p506_p507, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

        // c5_p506_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_506_radiogroup_C5_P506_1);
//        c5_p506_2o3_EditText = (EditText) rootView.findViewById(R.id.mod5_506_sp6_edittext_C5_P506_2o3);
        // c5_p506_2o3_TextView = (TextView) rootView.findViewById(R.id.mod5_506_sp6_TextView_C5_P506_2o3);
//        c5_p506_4_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_506_radiogroup_C5_P506_4);

        // c5_p507_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_507_radiogroup_C5_P507);
        //autoCompleteTextView =  (AutoCompleteTextView) rootView.findViewById(R.id.mod5_507_autocompletetextview);
        // txtDepartamento = (TextView) rootView.findViewById(R.id.mod5_c507_txtDepartamento);
        // txtProvincia = (TextView) rootView.findViewById(R.id.mod5_c507_txtProvincia);
        //  txtDistrito = (TextView) rootView.findViewById(R.id.mod5_c507_txtDistrito);

        //Anthony M
        c5_p506_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_506_radiogroup_C5_P506);
        c5_p506a_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_506a_radiogroup_C5_P506);
        c5_p507_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_507_radiogroup_C5_P507_1);
        c5_p507_anio_EditText = (EditText) rootView.findViewById(R.id.mod5_507_grado_edittext_C5_P507_año);
        c5_p507_grado_EditText = (EditText) rootView.findViewById(R.id.mod5_507_grado_edittext_C5_P507_grado);
        c5_p507_ce_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_507_radiogroup_C5_P507_4);

        m5_p506_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p506);
        m5_p506a_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p506a);
        //m5_p506_subpregunta_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_506_subpregunta);
        m5_p507_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p507);
        // m5_p507_subpregunta_linearlayout= (LinearLayout) rootView.findViewById(R.id.mod5_507_layout_subpregunta);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        configurarEditText(c5_p507_anio_EditText,m5_p507_linearlayout,2,1);
        configurarEditText(c5_p507_grado_EditText,m5_p507_linearlayout,2,1);
        //Anthony 07/05/2021
        c5_p506_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                validarFlujo55_1();
                if(validar_P507())m5_p507_linearlayout.setVisibility(View.VISIBLE);
                else {limpiar_p507();m5_p507_linearlayout.setVisibility(View.GONE);}

                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                switch (seleccionado) {
                    case 1:
                        m5_p506a_linearlayout.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        m5_p506a_linearlayout.setVisibility(View.GONE);
                        limpiar_p506a();
                        break;
                }

            }
        });
        c5_p507_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                validarFlujo56();
            }
        });
        c5_p507_anio_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int p507_sel = c5_p507_RadioGroup.indexOfChild(c5_p507_RadioGroup.findViewById(c5_p507_RadioGroup.getCheckedRadioButtonId()));
                if(p507_sel == 4){
                    if(c5_p507_anio_EditText.getText().toString().equals("6")) {
                        c5_p507_grado_EditText.setText("");
                        c5_p507_grado_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                        c5_p507_grado_EditText.setEnabled(false);
                    }else {
                        c5_p507_grado_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                        c5_p507_grado_EditText.setEnabled(true);
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        cargarDatos();
    }
    //Anthony 07/05/2021
    private void inicio() {
        Log.e("Fragment","Estas en P506P507");
        //P506
        if(validar_P506()) m5_p506_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p506();m5_p506_linearlayout.setVisibility(View.GONE);}
        //P506A
        m5_p506a_linearlayout.setVisibility(View.GONE);
        //P507
        if(validar_P507()) m5_p507_linearlayout.setVisibility(View.VISIBLE);
        else {limpiar_p507();m5_p507_linearlayout.setVisibility(View.GONE);}
//        if(edad >=3){
//            m5_p506_linearlayout.setVisibility(View.VISIBLE);
////            m5_p507_linearlayout.setVisibility(View.VISIBLE);
//            validarFlujo55_1();
//        }else {
//            limpiar_p506();limpiar_p507();
//            m5_p506_linearlayout.setVisibility(View.GONE);
//            m5_p507_linearlayout.setVisibility(View.GONE);
//        }
    }

    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo5_id_informante,idInformante);
//        contentValues.put(SQLConstantes.modulo5_c5_p506_1,c5_p506_1);
//        contentValues.put(SQLConstantes.modulo5_c5_p506_2,c5_p506_2);
//        contentValues.put(SQLConstantes.modulo5_c5_p506_3,c5_p506_3);
//        contentValues.put(SQLConstantes.modulo5_c5_p506_4,c5_p506_4);
//        contentValues.put(SQLConstantes.modulo5_c5_p507,c5_p507);
//        contentValues.put(SQLConstantes.modulo5_c5_p507_dist,c5_p507_dist);
//        contentValues.put(SQLConstantes.modulo5_c5_p507_prov,c5_p507_prov);
//        contentValues.put(SQLConstantes.modulo5_c5_p507_dep,c5_p507_dep);
        //Anthony M
        contentValues.put(SQLConstantes.modulo5_c5_p506,c5_p506);
        contentValues.put(SQLConstantes.modulo5_c5_p506a,c5_p506a);
        contentValues.put(SQLConstantes.modulo5_c5_p507,c5_p507);
        contentValues.put(SQLConstantes.modulo5_c5_p507_anio,c5_p507_anio);
        contentValues.put(SQLConstantes.modulo5_c5_p507_grado,c5_p507_grado);
        contentValues.put(SQLConstantes.modulo5_c5_p507_ce,c5_p507_ce);

        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp506p507,"1",idEncuestado);
        //ocultamos o mostramos preguntas o fragments
        ocultarOtrosLayouts();
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo5_COB500,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo5_COB500,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);

        data.close();

    }

    private void ocultarFragmentP508P511(boolean ocultar) {
        Data data = new Data(context);
        data.open();
        if(ocultar) {
            ContentValues contentValues1 = new ContentValues();
            contentValues1.put(SQLConstantes.modulo5_c5_p508_1, "");
            contentValues1.put(SQLConstantes.modulo5_c5_p508_2, "");
            contentValues1.put(SQLConstantes.modulo5_c5_p508_3, "");
            contentValues1.put(SQLConstantes.modulo5_c5_p508_4, "");
            contentValues1.put(SQLConstantes.modulo5_c5_p508_4_o, "");
            contentValues1.put(SQLConstantes.modulo5_c5_p508_5, "");

            contentValues1.put(SQLConstantes.modulo5_c5_p509_1, "");
            contentValues1.put(SQLConstantes.modulo5_c5_p509_2, "");
            contentValues1.put(SQLConstantes.modulo5_c5_p509_3, "");
            contentValues1.put(SQLConstantes.modulo5_c5_p509_4, "");
            contentValues1.put(SQLConstantes.modulo5_c5_p509_5, "");
            contentValues1.put(SQLConstantes.modulo5_c5_p509_6, "");
            contentValues1.put(SQLConstantes.modulo5_c5_p509_7, "");
            contentValues1.put(SQLConstantes.modulo5_c5_p509_7_o, "");
            contentValues1.put(SQLConstantes.modulo5_c5_p509_8, "");

            contentValues1.put(SQLConstantes.modulo5_c5_p510, "");
            contentValues1.put(SQLConstantes.modulo5_c5_p510_o, "");

            contentValues1.put(SQLConstantes.modulo5_c5_p511, "");
            contentValues1.put(SQLConstantes.modulo5_c5_p511_o, "");
            //Setea valores string vacio en los campos del fragment
            data.actualizarElemento(getNombreTabla(), contentValues1, idEncuestado);
            //Oculta el fragment
            data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p508p511, "-1", idEncuestado);
        }else {
            data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p508p511, "1", idEncuestado);
        }
        data.close();
    }

    @Override
    public void llenarVariables() {

        idInformante = obtener_Nresidente(informanteSpinner);
        //Anthony M
        c5_p506 = c5_p506_RadioGroup.indexOfChild(c5_p506_RadioGroup.findViewById(c5_p506_RadioGroup.getCheckedRadioButtonId()))+"";
        c5_p506a = c5_p506a_RadioGroup.indexOfChild(c5_p506a_RadioGroup.findViewById(c5_p506a_RadioGroup.getCheckedRadioButtonId()))+"";
        c5_p507 = c5_p507_RadioGroup.indexOfChild(c5_p507_RadioGroup.findViewById(c5_p507_RadioGroup.getCheckedRadioButtonId()))+"";
        c5_p507_anio = c5_p507_anio_EditText.getText().toString();
        c5_p507_grado = c5_p507_grado_EditText.getText().toString();
        c5_p507_ce = c5_p507_ce_RadioGroup.indexOfChild(c5_p507_ce_RadioGroup.findViewById(c5_p507_ce_RadioGroup.getCheckedRadioButtonId()))+"";
    }

    @Override
    public void cargarDatos() {
        Data data = new Data(context);
        data.open();
        if (data.existeElemento(getNombreTabla(),idEncuestado)) {
            Modulo5 modulo5 = data.getModulo5(idEncuestado);
            ArrayList<String> informantes = data.getListaInformantesMayor12(modulo5.getIdHogar());
            UtilsMethodsInputs.loadSpinner(informantes,informanteSpinner,context);
            if(modulo5.getIdInformante() != null && !modulo5.getIdInformante().equals("")) informanteSpinner.setSelection(obtener_posicion(modulo5.getIdInformante(),informanteSpinner));

            if (modulo5.getC5_p506() != null && !modulo5.getC5_p506().equals("-1") && !modulo5.getC5_p506().equals(""))
                ((RadioButton) c5_p506_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p506()))).setChecked(true); //Anthony M
            if (modulo5.getC5_p506a() != null && !modulo5.getC5_p506a().equals("-1") && !modulo5.getC5_p506a().equals(""))
                ((RadioButton) c5_p506a_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p506a()))).setChecked(true);
            if (modulo5.getC5_p507() != null) {
                if (!modulo5.getC5_p507().equals("-1") && !modulo5.getC5_p507().equals(""))
                    ((RadioButton) c5_p507_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p507()))).setChecked(true);

            }
            //Anthony M
            c5_p507_anio_EditText.setText(modulo5.getC5_p507_anio());
            c5_p507_grado_EditText.setText(modulo5.getC5_p507_grado());
            if (modulo5.getC5_p507_ce() != null && !modulo5.getC5_p507_ce().equals("-1") && !modulo5.getC5_p507_ce().equals(""))
                ((RadioButton) c5_p507_ce_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p507_ce()))).setChecked(true);
        }
        inicio();
        data.close();
    }

    @Override
    public void llenarVista() {
        /*
        Data data = new Data(context);
        data.open();
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p506,idEncuestado)) m5_p506_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p507,idEncuestado)) m5_p507_linearlayout.setVisibility(View.GONE);
        data.close();

         */
    }

    @Override
    public boolean validarDatos() {

        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        if(m5_p506_linearlayout.getVisibility() == View.VISIBLE){
            if(c5_p506.equals("-1")){mostrarMensaje("PREGUNTA 506: DEBE SELECCIONAR UNA OPCION");return false;}
        }else {
            c5_p506 = "";
        }

        if(m5_p506a_linearlayout.getVisibility() == View.VISIBLE){
            if(c5_p506a.equals("-1")){mostrarMensaje("PREGUNTA 506A: DEBE SELECCIONAR UNA OPCION");return false;}
        }else {
            c5_p506a = "";
        }

        if(m5_p507_linearlayout.getVisibility() == View.VISIBLE){
            if(c5_p507.equals("-1")) { mostrarMensaje("PREGUNTA 507: DEBE SELECCIONAR UNA OPCION");return false;}
            //nuevo Obs Excel// Modificado 05/06/2021
            if(p501.equals("1") &&
                    !(c5_p507.equals("1") || c5_p507.equals("4"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P507");}
            if(p501.equals("2") &&
                    !(c5_p507.equals("1") || c5_p507.equals("2") || c5_p507.equals("4"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P507");}
            if(p501.equals("3") &&
                    !(c5_p507.equals("2") || c5_p507.equals("4"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P507");}
            if(p501.equals("4") &&
                    !(c5_p507.equals("3") || c5_p507.equals("4"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P507");}
            if(p501.equals("5") &&
                    !(c5_p507.equals("3") || c5_p507.equals("4"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P507");}
            if(p501.equals("6") &&
                    !(c5_p507.equals("5") || c5_p507.equals("6"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P507");}
            if(p501.equals("7") &&
                    !(c5_p507.equals("5") || c5_p507.equals("6"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P507");}
            if(p501.equals("8") &&
                    !(c5_p507.equals("5") || c5_p507.equals("6"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P507");}
            if(p501.equals("9") &&
                    !(c5_p507.equals("5") || c5_p507.equals("6"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P507");}
            if(p501.equals("10") &&
                    !(Integer.parseInt(c5_p507) >= 5 && Integer.parseInt(c5_p507) <= 7)) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P507");}
            if(p501.equals("11") &&
                    !(Integer.parseInt(c5_p507) >= 5 && Integer.parseInt(c5_p507) <= 7)) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P507");}

            if(c5_p507_anio_EditText.isEnabled() && c5_p507_anio.equals("") && !c5_p507_grado_EditText.isEnabled()) { mostrarMensaje("PREGUNTA 507: DEBE INGRESAR AÑO");return false;}
            if(c5_p507_grado_EditText.isEnabled() && c5_p507_grado.equals("") && !c5_p507_anio_EditText.isEnabled()) { mostrarMensaje("PREGUNTA 507: DEBE INGRESAR GRADO");return false;}
            if(c5_p507_anio_EditText.isEnabled() && c5_p507_anio.equals("") && c5_p507_grado_EditText.isEnabled() && c5_p507_grado.equals("")) { mostrarMensaje("PREGUNTA 507: DEBE INGRESAR AÑO o GRADO");return false;}
            if(c5_p507_ce.equals("-1")) { mostrarMensaje("PREGUNTA 507: DEBE SELECCIONAR UNA OPCION CENTRO DE ESTUDIOS");return false;}
            Log.e("p507","edad = "+edad+"   año = "+c5_p507_anio+"     grado = "+c5_p507_grado);
            switch (c5_p507){
                case "1":
                    if(edad >= 3 && edad <= 99){
                        if(!c5_p507_anio.equals("0") && !c5_p507_anio.equals("1") &&  !c5_p507_anio.equals("2") &&  !c5_p507_anio.equals("3"))
                        {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 0:3");return false;}
                    }else {mostrarMensaje("PREGUNTA 507: OPCION 1 NO CORRESPONDE SEGUN EDAD");return false;}
/*                    if(edad == 3 &&
                            !c5_p507_anio.equals("0") && !c5_p507_anio.equals("1"))
                    {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 0:1");return false;}
                    if(edad == 4 &&
                            !c5_p507_anio.equals("0") && !c5_p507_anio.equals("1") && !c5_p507_anio.equals("2"))
                    {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 0:2");return false;}
                    if(edad == 5 &&
                            !c5_p507_anio.equals("1") && !c5_p507_anio.equals("2") && !c5_p507_anio.equals("3"))
                    {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:3");return false;}
                    if(edad < 3 || edad > 5)
                    {mostrarMensaje("PREGUNTA 507: OPCION 1 NO CORRESPONDE SEGUN EDAD");return false;}*/
                    break;
                case "2":
                    if(edad >= 6 && edad <= 46){
                        if(!c5_p507_grado.equals("1") && !c5_p507_grado.equals("2") && !c5_p507_grado.equals("3") && !c5_p507_grado.equals("4") && !c5_p507_grado.equals("5") && !c5_p507_grado.equals("6"))
                        {mostrarMensaje("PREGUNTA 507: GRADO DEBE SER 1:6");return false;}
                    }else {mostrarMensaje("PREGUNTA 507: OPCION 2 NO CORRESPONDE SEGUN EDAD");return false;}
/*                    if(edad >= 5 && edad <= 46){
                        if(edad >= 10){
                            if(!c5_p507_grado.equals("1") && !c5_p507_grado.equals("2") && !c5_p507_grado.equals("3") && !c5_p507_grado.equals("4") && !c5_p507_grado.equals("5") && !c5_p507_grado.equals("6"))
                            {mostrarMensaje("PREGUNTA 507: GRADO DEBE SER 1:6");return false;}
                        }else
                        if(edad >= 9){
                            if(!c5_p507_grado.equals("1") && !c5_p507_grado.equals("2") && !c5_p507_grado.equals("3") && !c5_p507_grado.equals("4") && !c5_p507_grado.equals("5"))
                            {mostrarMensaje("PREGUNTA 507: GRADO DEBE SER 1:5");return false;}
                        }else
                        if(edad >= 8){
                            if(!c5_p507_grado.equals("1") && !c5_p507_grado.equals("2") && !c5_p507_grado.equals("3") && !c5_p507_grado.equals("4"))
                            {mostrarMensaje("PREGUNTA 507: GRADO DEBE SER 1:4");return false;}
                        }else
                        if(edad >= 7){
                            if(!c5_p507_grado.equals("1") && !c5_p507_grado.equals("2") && !c5_p507_grado.equals("3"))
                            {mostrarMensaje("PREGUNTA 507: GRADO DEBE SER 1:3");return false;}
                        }else
                        if(edad >= 6){
                            if(!c5_p507_grado.equals("1") && !c5_p507_grado.equals("2"))
                            {mostrarMensaje("PREGUNTA 507: GRADO DEBE SER 1:2");return false;}
                        }else
                        if(edad >= 5){
                            if(!c5_p507_grado.equals("1"))
                            {mostrarMensaje("PREGUNTA 507: GRADO DEBE SER 1");return false;}
                        }
                    }
                    if(edad < 5 || edad > 46)
                    {mostrarMensaje("PREGUNTA 507: OPCION 2 NO CORRESPONDE SEGUN EDAD");return false;}*/
                    break;
                case "3":
                    if(edad >= 11 && edad <= 99){
                        if(!c5_p507_anio.equals("1") && !c5_p507_anio.equals("2") && !c5_p507_anio.equals("3") && !c5_p507_anio.equals("4") && !c5_p507_anio.equals("5") && !c5_p507_anio.equals("6"))
                        {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:6");return false;}
                    }else {mostrarMensaje("PREGUNTA 507: OPCION 3 NO CORRESPONDE SEGUN EDAD");return false;}
/*                    if(edad >= 15){
                        if(!c5_p507_anio.equals("1") && !c5_p507_anio.equals("2") && !c5_p507_anio.equals("3") && !c5_p507_anio.equals("4") && !c5_p507_anio.equals("5") && !c5_p507_anio.equals("6"))
                        {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:6");return false;}
                    }else
                    if(edad >= 14){
                        if(!c5_p507_anio.equals("1") && !c5_p507_anio.equals("2") && !c5_p507_anio.equals("3") && !c5_p507_anio.equals("4"))
                        {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:4");return false;}
                    }else
                    if(edad >= 13){
                        if(!c5_p507_anio.equals("1") && !c5_p507_anio.equals("2") && !c5_p507_anio.equals("3"))
                        {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:3");return false;}
                    }else
                    if(edad >= 12){
                        if(!c5_p507_anio.equals("1") && !c5_p507_anio.equals("2"))
                        {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:2");return false;}
                    }else
                    if(edad >= 11){
                        if(!c5_p507_anio.equals("1"))
                        {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1");return false;}
                    }
                    if(edad < 11)
                    {mostrarMensaje("PREGUNTA 507: OPCION 3 NO CORRESPONDE SEGUN EDAD");return false;}*/
                    break;
                case "4":
                    if(edad >= 3 && edad <= 20){
                        if(c5_p507_grado.equals("") && !c5_p507_anio.equals("1") && !c5_p507_anio.equals("2") && !c5_p507_anio.equals("3") && !c5_p507_anio.equals("4") && !c5_p507_anio.equals("5") && !c5_p507_anio.equals("6"))
                        {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:6");return false;}
                        if(c5_p507_anio.equals("") && !c5_p507_grado.equals("1") && !c5_p507_grado.equals("2") && !c5_p507_grado.equals("3") && !c5_p507_grado.equals("4") && !c5_p507_grado.equals("5"))
                        {mostrarMensaje("PREGUNTA 507: GRADO DEBE SER 1:5");return false;}
                    }else {mostrarMensaje("PREGUNTA 507: OPCION 4 NO CORRESPONDE SEGUN EDAD");return false;}
                    break;
                case "5":
                    if(edad <= 99) {
                        if (edad >= 20) {
                            if (!c5_p507_anio.equals("1") && !c5_p507_anio.equals("2") && !c5_p507_anio.equals("3") && !c5_p507_anio.equals("4") && !c5_p507_anio.equals("5"))
                            {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:5");return false;}
                        } else
                        if (edad >= 19) {
                            if (!c5_p507_anio.equals("1") && !c5_p507_anio.equals("2") && !c5_p507_anio.equals("3") && !c5_p507_anio.equals("4"))
                            {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:4");return false;}
                        } else
                        if (edad >= 18) {
                            if (!c5_p507_anio.equals("1") && !c5_p507_anio.equals("2") && !c5_p507_anio.equals("3"))
                            {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:3");return false;}
                        } else
                        if (edad >= 17) {
                            if (!c5_p507_anio.equals("1") && !c5_p507_anio.equals("2"))
                            {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:2");return false;}
                        } else
                        if (edad >= 16) {
                            if (!c5_p507_anio.equals("1"))
                            {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1");return false; }}
                        if (edad < 16) {mostrarMensaje("PREGUNTA 507: OPCION 5 NO CORRESPONDE SEGUN EDAD");return false; }
                    }else {  mostrarMensaje("PREGUNTA 507: OPCION 5 NO CORRESPONDE SEGUN EDAD");return false;}
                    break;
                case "6":
                    if(edad <= 99){
                        if(edad >= 21){
                            if(!c5_p507_anio.equals("1") && !c5_p507_anio.equals("2") && !c5_p507_anio.equals("3") && !c5_p507_anio.equals("4") && !c5_p507_anio.equals("5") && !c5_p507_anio.equals("6"))
                            {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:6");return false;}
                        }else
                        if(edad >= 20){
                            if(!c5_p507_anio.equals("1") && !c5_p507_anio.equals("2") && !c5_p507_anio.equals("3") && !c5_p507_anio.equals("4") && !c5_p507_anio.equals("5"))
                            {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:5");return false;}
                        }else
                        if(edad >= 19){
                            if(!c5_p507_anio.equals("1") && !c5_p507_anio.equals("2") && !c5_p507_anio.equals("3") && !c5_p507_anio.equals("4"))
                            {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:5");return false;}
                        }else
                        if(edad >= 18){
                            if(!c5_p507_anio.equals("1") && !c5_p507_anio.equals("2") && !c5_p507_anio.equals("3"))
                            {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:3");return false;}
                        }else
                        if(edad >= 17){
                            if(!c5_p507_anio.equals("1") && !c5_p507_anio.equals("2"))
                            {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:2");return false;}
                        }else
                        if(edad >= 16){
                            if(!c5_p507_anio.equals("1"))
                            {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1");return false;}
                        }
                        if(edad < 16)
                        {mostrarMensaje("PREGUNTA 507: OPCION 6 NO CORRESPONDE SEGUN EDAD");return false;}
                    }else {mostrarMensaje("PREGUNTA 507: OPCION 6 NO CORRESPONDE SEGUN EDAD");return false;}
                    break;
                case "7":
                    if(edad <= 99){
                        if(edad >= 22){
                            if(!c5_p507_anio.equals("1") && !c5_p507_anio.equals("2"))
                            {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1:2");return false;}
                        }else
                        if(edad >= 21){
                            if(!c5_p507_anio.equals("1"))
                            {mostrarMensaje("PREGUNTA 507: AÑO DEBE SER 1");return false;}
                        }
                        if(edad < 21)
                        {mostrarMensaje("PREGUNTA 507: OPCION 7 NO CORRESPONDE SEGUN EDAD");return false;}
                    }else {mostrarMensaje("PREGUNTA 507: OPCION 7 NO CORRESPONDE SEGUN EDAD");return false;}
                    break;
            }
        }
        else{
            c5_p507 = "";
            c5_p507_anio = "";
            c5_p507_grado= "";
            c5_p507_ce = "";
        }
        return true;
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablamodulo5;
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

    private void configurarEditText(final EditText editText, final View layoutView, int tipo,int longitud){
        if (tipo == 1) editText.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(longitud)});

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    ocultarTeclado(editText);
                    layoutView.requestFocus();
                    return true;
                }
                return false;
            }
        });
        if (tipo == 2) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(longitud)});
//            editText.setTransformationMethod(new NumericKeyBoardTransformationMethod());
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
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

    public void ocultarOtrosLayouts() {
        //Anthony M
        Data data = new Data(context);
        data.open();
        llenarVariables();
        if(m5_p507_linearlayout.getVisibility() == View.VISIBLE){
            //FLUJO 56 A
            if(p501.equals("")) p501 = "-1";

            if(edad >= 3 && edad <= 17){
                ocultarFragmentP508P511(false);} else
//            if(edad >= 18 && edad <= 25){ ocultarFragmentP508P511(false);} else
            if(edad > 17 && p503.equals("1")){
                ocultarFragmentP508P511(false);} else
            if(edad > 17 && p503.equals("2") && (p501.equals("10") || p501.equals("11"))){
                ocultarFragmentP508P511(true);} else
//            if(edad > 17 && p503.equals("2") && (Integer.parseInt(p501) > 0 && Integer.parseInt(p501) <10)){
            if(edad > 17 && p503.equals("2") && !(p501.equals("10") || p501.equals("11"))){
                ocultarFragmentP508P511(true);} else
            {ocultarFragmentP508P511(true);
                Log.e("P506P507","No cumple condiciones P507 ocultarOtrosLayouts()");}
        }else {
            //FLUJO 55
            if (c5_p506.equals("1")) {
                ocultarFragmentP508P511(false);
            } else
            //if (c5_p506.equals("2") && p503.equals("1") && edad >= 3 && edad <= 17) {
            if (c5_p506.equals("2") && p503.equals("1")) {
                ocultarFragmentP508P511(false); //Pasa siguiente fragment de P510
            } else
//            if (c5_p506.equals("2") && p503.equals("1") && edad >= 18 && edad <= 25) {
//                ocultarFragmentP508P511(false); //Pasa siguiente fragment de P511
//            } else
//            if (c5_p506.equals("2") && p503.equals("1") && edad > 25 && (p501.equals("10") || p501.equals("11"))) {
//                ocultarFragmentP508P511(true); //Salta al fragment de P512
//            } else
//            if (c5_p506.equals("2") && p503.equals("1") && edad > 25 && !(p501.equals("10") || p501.equals("11"))) {
//                ocultarFragmentP508P511(true); //Salta al fragment de P515
//            } else
            if (c5_p506.equals("2") && p503.equals("2") && edad >= 3 && edad <= 25) {
                ocultarFragmentP508P511(false); //Pasa siguiente fragment de P511
            } else
            if (c5_p506.equals("2") && p503.equals("2") && edad > 25 && (p501.equals("10") || p501.equals("11"))) {
                ocultarFragmentP508P511(true); //Salta al fragment de P512
            } else
            if (c5_p506.equals("2") && p503.equals("2") && edad > 25 && !(p501.equals("10") || p501.equals("11"))) {
                ocultarFragmentP508P511(true); //Salta al fragment de P515
            }
        }

        data.close();
    }

    public boolean verificarCoberturaCapitulo(){
        /*
        Data data = new Data(context);
        data.open();
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p501p505,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp501p505,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p506p507,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp506p507,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p508p511,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp508p511,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p512p513,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp512p513,idEncuestado).equals("0")) return false;
        data.close();

         */
        return true;
    }
    public  void limpiar_p506(){
        c5_p506_RadioGroup.clearCheck();
    }
    public  void limpiar_p506a(){
        c5_p506a_RadioGroup.clearCheck();
    }
    public  void limpiar_p507(){
        c5_p507_RadioGroup.clearCheck();
        c5_p507_anio_EditText.setText("");
        c5_p507_grado_EditText.setText("");
        c5_p507_ce_RadioGroup.clearCheck();
    }
    public void validarFlujo55_1(){ //FLUJO 55 - PRIMERA PARTE
        llenarVariables();
        if(p505.equals("2") && c5_p506.equals("2")){
            limpiar_p507();
            m5_p507_linearlayout.setVisibility(View.GONE);
        }else if(p505.equals("1") || c5_p506.equals("1")){
            m5_p507_linearlayout.setVisibility(View.VISIBLE);
        }
    }
    public void validarFlujo56(){ //FLUJO 56
        llenarVariables();
        switch (c5_p507){
            case "1": case "3": case "5": case "6": case "7":
                c5_p507_anio_EditText.setEnabled(true);
                c5_p507_anio_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                c5_p507_grado_EditText.setText("");
                c5_p507_grado_EditText.setEnabled(false);
                c5_p507_grado_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                break;
            case "2":
                c5_p507_anio_EditText.setText("");
                c5_p507_anio_EditText.setEnabled(false);
                c5_p507_anio_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                c5_p507_grado_EditText.setEnabled(true);
                c5_p507_grado_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                break;
            case "4":
                c5_p507_anio_EditText.setEnabled(true);
                c5_p507_grado_EditText.setEnabled(true);
                c5_p507_anio_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                c5_p507_grado_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                break;
        }
    }
    public boolean validar_P506(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 3 && p505.equals("1"))  //UNIVERSO
            valido = true;
        return valido;
    }
    public boolean validar_P506a(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 3 && p505.equals("1"))  //UNIVERSO
            valido = true;
        return valido;
    }
    public boolean validar_P507(){
        boolean valido = false;
        llenarVariables();
        if(edad >= 3 && c5_p506.equals("1"))  //UNIVERSO
            valido = true;
        return valido;
    }
}
