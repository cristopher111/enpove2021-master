package com.example.ricindigus.enpove2021.fragments.modulo5;


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
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo5;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP501P505 extends FragmentPagina {
    String idEncuestado;
    String idVivienda, idHogar, idInformante;
    Context context;
    Spinner informanteSpinner;


    RadioGroup c5_p501A_RadioGroup, c5_p501_RadioGroup, c5_p501B_RadioGroup, c5_p503_RadioGroup, c5_p504_RadioGroup, c5_p505_RadioGroup;
    RadioGroup c5_p504_ce_RadioGroup; //Anthony M
    TextView txtNombreCarrera, txtCodigoCarrera;
    EditText c5_p502_o_EditText;
    EditText c5_p504_anio_EditText, c5_p504_grado_EditText; //Anthony M
    AutoCompleteTextView c5_p502_Autocomplete;
    //    CheckBox c5_p502_c_CheckBox;
    LinearLayout m5_p501A_linearlayout, m5_p501_linearlayout, m5_p501B_linearlayout, m5_p502_linearlayout, m5_p503_linearlayout, m5_p504_linearlayout,
            m5_p505_linearlayout;

    private String c5_p501A;
    private String c5_p501;
    private String c5_p501B;
    private String c5_p502;
    private String c5_p502_eleccion;
    private String c5_p502_o;
    private String c5_p503;
    private String c5_p504;
    private String c5_p504_anio; //Anthony M
    private String c5_p504_grado; //Anthony M
    private String c5_p504_ce; //Anthony M
    private String c5_p505;
    int edad=0;



    @SuppressLint("ValidFragment")
    public FragmentP501P505(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;

        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idHogar = residente.getId_hogar();
        idVivienda = residente.getId_vivienda();
        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        idInformante = "";
        data.close();
    }

    public FragmentP501P505() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p501_p505, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

        c5_p501A_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_501A_radiogroup_C5_P501A);
        c5_p501_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_501_radiogroup_C5_P501);
        c5_p501B_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_501B_radiogroup_C5_P501B);

        c5_p502_Autocomplete = (AutoCompleteTextView) rootView.findViewById(R.id.mod5_502_autocompletetextview);
        txtCodigoCarrera = (TextView) rootView.findViewById(R.id.mod5_c502_txtCodigoCarrera);
        txtNombreCarrera = (TextView) rootView.findViewById(R.id.mod5_c502_txtNombreCarrera);
        c5_p502_o_EditText = (EditText) rootView.findViewById(R.id.mod5_502_edittext_C5_P502_o);
//        c5_p502_c_CheckBox = (CheckBox) rootView.findViewById(R.id.mod5_502_checkbox_C5_P502_C);

        c5_p503_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_503_radiogroup_C5_P503);
        //   c5_p504_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_504_radiogroup_C5_P504);
        c5_p504_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_504_radiogroup_C5_P504_1);//Anthony M.
        c5_p504_anio_EditText = (EditText) rootView.findViewById(R.id.mod5_504_año_edittext_C5_P504_año);//Anthony M.
        c5_p504_grado_EditText = (EditText) rootView.findViewById(R.id.mod5_504_grado_edittext_C5_P504_grado);//Anthony M.
        c5_p504_ce_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_506_radiogroup_C5_P506_4);//Anthony M.
        c5_p505_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod5_505_radiogroup_C5_P505);

        m5_p501A_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p501A);
        m5_p501_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p501);
        m5_p501B_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p501B);
        m5_p502_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p502);
        m5_p503_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p503);
        m5_p504_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p504);
        m5_p505_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m5_p505);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configurarEditText(c5_p502_o_EditText,m5_p502_linearlayout,0,30);
        configurarEditText(c5_p504_anio_EditText,m5_p504_linearlayout,2,1);
        configurarEditText(c5_p504_grado_EditText,m5_p504_linearlayout,2,1);

        //Anthony M. 14/07/2021
        c5_p501A_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                validar_p501();validar_p501B();validar_p502();
            }
        });
        c5_p501_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                c5_p502_o_EditText.setText("");
                int pos = group.indexOfChild(c5_p501_RadioGroup.findViewById(c5_p501_RadioGroup.getCheckedRadioButtonId()));
                if(pos >= 7 && pos <= 8){
                    ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.lista_item,R.id.item, getResources().getStringArray(R.array.array_carreras_tecnicas));
                    c5_p502_Autocomplete.setAdapter(adapter);
                    txtCodigoCarrera.setText("");txtNombreCarrera.setText("");
                }
                if(pos >= 9 && pos <= 11){
                    ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.lista_item,R.id.item, getResources().getStringArray(R.array.array_carreras_universitarias));
                    c5_p502_Autocomplete.setAdapter(adapter);
                    txtCodigoCarrera.setText("");txtNombreCarrera.setText("");
                }
                validar_p501B();validar_p502();validar_p503();
            }
        });
        c5_p501B_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                c5_p502_o_EditText.setText("");
                int pos = group.indexOfChild(c5_p501B_RadioGroup.findViewById(c5_p501B_RadioGroup.getCheckedRadioButtonId()));
                if(pos >=8 && pos<=9){
                    ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.lista_item,R.id.item, getResources().getStringArray(R.array.array_carreras_tecnicas));
                    c5_p502_Autocomplete.setAdapter(adapter);
                    txtCodigoCarrera.setText("");txtNombreCarrera.setText("");
                }
                if(pos >= 10 && pos <= 12){
                    ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.lista_item,R.id.item, getResources().getStringArray(R.array.array_carreras_universitarias));
                    c5_p502_Autocomplete.setAdapter(adapter);
                    txtCodigoCarrera.setText("");txtNombreCarrera.setText("");
                }
                validar_p502();validar_p503();
            }
        });
        c5_p503_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                validar_p504();validar_p505();
            }
        });
        c5_p504_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                validarFlujo54();
                validar_p505();
            }
        });
        c5_p504_anio_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int p507_sel = c5_p504_RadioGroup.indexOfChild(c5_p504_RadioGroup.findViewById(c5_p504_RadioGroup.getCheckedRadioButtonId()));
                if(p507_sel == 4){
                    if(c5_p504_anio_EditText.getText().toString().equals("6")) {
                        c5_p504_grado_EditText.setText("");
                        c5_p504_grado_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                        c5_p504_grado_EditText.setEnabled(false);
                    }else {
                        c5_p504_grado_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                        c5_p504_grado_EditText.setEnabled(true);
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        c5_p502_Autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String textoCarrera = c5_p502_Autocomplete.getText().toString();
                String codigo = textoCarrera.substring(0,textoCarrera.indexOf("-"));
                if (codigo.equals("99999")){
                    c5_p502_o_EditText.setEnabled(true);
                    c5_p502_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                } else {
                    c5_p502_o_EditText.setText("");
                    c5_p502_o_EditText.setEnabled(false);
                    c5_p502_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
                String nombre = textoCarrera.substring(textoCarrera.indexOf("-")+2);
                txtCodigoCarrera.setText(codigo);
                txtNombreCarrera.setText(nombre);
                c5_p502_Autocomplete.setText("");
                ocultarTeclado(c5_p502_Autocomplete);
                m5_p502_linearlayout.requestFocus();
            }
        });
        cargarDatos();


    }

    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo5_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo5_c5_p501a,c5_p501A);
        contentValues.put(SQLConstantes.modulo5_c5_p501,c5_p501);
        contentValues.put(SQLConstantes.modulo5_c5_p501b,c5_p501B);
//        contentValues.put(SQLConstantes.modulo5_c5_p502_c,c5_p502_c);
        contentValues.put(SQLConstantes.modulo5_c5_p502,c5_p502);
//        contentValues.put(SQLConstantes.modulo5_c5_p502_eleccion,c5_p502_eleccion);
        contentValues.put(SQLConstantes.modulo5_c5_p502_o,c5_p502_o);
        contentValues.put(SQLConstantes.modulo5_c5_p503,c5_p503);
        contentValues.put(SQLConstantes.modulo5_c5_p504,c5_p504);
        contentValues.put(SQLConstantes.modulo5_c5_p504_anio,c5_p504_anio); // Anthony M
        contentValues.put(SQLConstantes.modulo5_c5_p504_grado,c5_p504_grado); // Anthony M
        contentValues.put(SQLConstantes.modulo5_c5_p504_ce,c5_p504_ce); // Anthony M
        contentValues.put(SQLConstantes.modulo5_c5_p505,c5_p505);

        if(!data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo5 modulo5 = new Modulo5();
            modulo5.set_id(idEncuestado);
            modulo5.setIdHogar(idHogar);
            modulo5.setIdVivienda(idVivienda);
            data.insertarElemento(getNombreTabla(),modulo5.toValues());
        }
        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp501p505,"1",idEncuestado);
        //ocultamos o mostramos preguntas o fragments
        ocultarOtrosLayouts();
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo5_COB500,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo5_COB500,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();

    }

    @Override
    public void llenarVariables() {

        idInformante = obtener_Nresidente(informanteSpinner);
        c5_p501A = c5_p501A_RadioGroup.indexOfChild(c5_p501A_RadioGroup.findViewById(c5_p501A_RadioGroup.getCheckedRadioButtonId()))+"";
        c5_p501 = c5_p501_RadioGroup.indexOfChild(c5_p501_RadioGroup.findViewById(c5_p501_RadioGroup.getCheckedRadioButtonId()))+"";
        c5_p501B = c5_p501B_RadioGroup.indexOfChild(c5_p501B_RadioGroup.findViewById(c5_p501B_RadioGroup.getCheckedRadioButtonId()))+"";
        c5_p502 = txtCodigoCarrera.getText().toString();
        if(c5_p502.equals("99999"))
            c5_p502_o = c5_p502_o_EditText.getText().toString();
        else
            c5_p502_o = txtNombreCarrera.getText().toString();
        c5_p503 = c5_p503_RadioGroup.indexOfChild(c5_p503_RadioGroup.findViewById(c5_p503_RadioGroup.getCheckedRadioButtonId()))+"";
        c5_p504 = c5_p504_RadioGroup.indexOfChild(c5_p504_RadioGroup.findViewById(c5_p504_RadioGroup.getCheckedRadioButtonId()))+""; // Anthony M
        c5_p504_anio = c5_p504_anio_EditText.getText().toString(); // Anthony M
        c5_p504_grado = c5_p504_grado_EditText.getText().toString(); // Anthony M
        c5_p504_ce = c5_p504_ce_RadioGroup.indexOfChild(c5_p504_ce_RadioGroup.findViewById(c5_p504_ce_RadioGroup.getCheckedRadioButtonId()))+""; // Anthony M
        c5_p505 = c5_p505_RadioGroup.indexOfChild(c5_p505_RadioGroup.findViewById(c5_p505_RadioGroup.getCheckedRadioButtonId()))+"";

    }

    @Override
    public void cargarDatos() {
        Data data = new Data(context);
        data.open();
        if (data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo5 modulo5 = data.getModulo5(idEncuestado);
//            ArrayList<String> residentes = data.getListaSpinnerResidentesHogar(modulo5.getIdHogar());
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,residentes);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            informanteSpinner.setAdapter(adapter);
            ArrayList<String> informantes = data.getListaInformantesMayor12(modulo5.getIdHogar());
            UtilsMethodsInputs.loadSpinner(informantes,informanteSpinner,context);
            if(modulo5.getIdInformante() != null && !modulo5.getIdInformante().equals("")) informanteSpinner.setSelection(obtener_posicion(modulo5.getIdInformante(),informanteSpinner));
            if(modulo5.getC5_p501a() != null && !modulo5.getC5_p501a().equals("-1") && !modulo5.getC5_p501a().equals(""))((RadioButton)c5_p501A_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p501a()))).setChecked(true);
            if(modulo5.getC5_p501() != null && !modulo5.getC5_p501().equals("-1") && !modulo5.getC5_p501().equals(""))((RadioButton)c5_p501_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p501()))).setChecked(true);
            if(modulo5.getC5_p501b() != null && !modulo5.getC5_p501b().equals("-1") && !modulo5.getC5_p501b().equals(""))((RadioButton)c5_p501B_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p501b()))).setChecked(true);
//            txtNombreCarrera.setText(modulo5.getC5_p502_eleccion());
            txtCodigoCarrera.setText(modulo5.getC5_p502());
            if (modulo5.getC5_p502() != null && modulo5.getC5_p502().equals("99999")) {
                c5_p502_o_EditText.setText(modulo5.getC5_p502_o());
                c5_p502_o_EditText.setEnabled(true);
                c5_p502_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                if(modulo5.getC5_p501().equals("7") || modulo5.getC5_p501().equals("8"))
                    txtNombreCarrera.setText("OTRA CARRERA TÉCNICA");
                if(modulo5.getC5_p501().equals("9") || modulo5.getC5_p501().equals("10") || modulo5.getC5_p501().equals("11"))
                    txtNombreCarrera.setText("OTRA CARRERA UNIVERSITARIA");
            }else{
                txtNombreCarrera.setText(modulo5.getC5_p502_o());
            }
//            if (modulo5.getC5_p502().equals("99999")) {
//                c5_p502_o_EditText.setText(modulo5.getC5_p502_o());
//                c5_p502_o_EditText.setEnabled(true);
//                c5_p502_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
//            }
//            if(modulo5.getC5_p502_c().equals("1")) c5_p502_c_CheckBox.setChecked(true);
            if(modulo5.getC5_p503() != null && !modulo5.getC5_p503().equals("-1") && ! modulo5.getC5_p503().equals(""))((RadioButton)c5_p503_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p503()))).setChecked(true);
            if(modulo5.getC5_p504() != null && !modulo5.getC5_p504().equals("-1") && !modulo5.getC5_p504().equals(""))((RadioButton)c5_p504_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p504()))).setChecked(true);
            c5_p504_anio_EditText.setText(modulo5.getC5_p504_anio()); //Anthony M
            c5_p504_grado_EditText.setText(modulo5.getC5_p504_grado());//Anthony M
            if(modulo5.getC5_p504_ce() != null && !modulo5.getC5_p504_ce().equals("-1") && !modulo5.getC5_p504_ce().equals(""))((RadioButton)c5_p504_ce_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p504_ce()))).setChecked(true);//Anthony M
            if(modulo5.getC5_p505() != null && !modulo5.getC5_p505().equals("-1") && !modulo5.getC5_p505().equals(""))((RadioButton)c5_p505_RadioGroup.getChildAt(Integer.parseInt(modulo5.getC5_p505()))).setChecked(true);
        }
        inicio();
        data.close();
    }

    private void inicio() {
        Log.e("Fragment","Estas en P501P505");
        validar_p501A();
        validar_p501();
        validar_p501B();
        validar_p502();
        validar_p503();
        validar_p504();
        validar_p505();
    }

    @Override
    public void llenarVista() {

    }

    @Override
    public boolean validarDatos() {
        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        if (m5_p501A_linearlayout.getVisibility()==View.VISIBLE){
            if(c5_p501A.equals("-1")){ mostrarMensaje("PREGUNTA 501A: DEBE SELECCIONAR UNA OPCION");return false; }
            }else{
            c5_p501A ="";
        }

        if (m5_p501_linearlayout.getVisibility()==View.VISIBLE){
            int p501 = Integer.parseInt(c5_p501);
            if(c5_p501.equals("-1")){ mostrarMensaje("PREGUNTA 501: DEBE SELECCIONAR UNA OPCION");return false; }
            //nuevo Obs Excel REGLA 60A//
            if(p501 == 2 && edad < 3){ mostrarMensaje("ERROR: ULTIMO NIVEL DE ESTUDIO (P501) NO CORRESPONDE A LA EDAD");return false;}
            if(p501 >= 3 && p501 <= 4 && edad < 6){ mostrarMensaje("ERROR: ULTIMO NIVEL DE ESTUDIO (P501) NO CORRESPONDE A LA EDAD");return false;}
            if(p501 >= 5 && p501 <= 6 && edad < 11){ mostrarMensaje("ERROR: ULTIMO NIVEL DE ESTUDIO (P501) NO CORRESPONDE A LA EDAD");return false;}
            if(p501 >= 7 && p501 <= 10 && edad < 16){ mostrarMensaje("ERROR: ULTIMO NIVEL DE ESTUDIO (P501) NO CORRESPONDE A LA EDAD");return false;}
            if(p501 == 11 && edad < 21){ mostrarMensaje("ERROR: ULTIMO NIVEL DE ESTUDIO (P501) NO CORRESPONDE A LA EDAD");return false;}
        }else{
            c5_p501 ="";
        }

        if (m5_p501B_linearlayout.getVisibility()==View.VISIBLE){
            int p501B = Integer.parseInt(c5_p501B);
            if(c5_p501B.equals("-1")){ mostrarMensaje("PREGUNTA 501B: DEBE SELECCIONAR UNA OPCION");return false; }
            //nuevo Obs Excel REGLA 60A//
            if(p501B == 2 && edad < 3){ mostrarMensaje("ERROR: ULTIMO NIVEL DE ESTUDIO (P501B) NO CORRESPONDE A LA EDAD");return false;}
            if(p501B >= 3 && p501B <= 4 && edad < 6){ mostrarMensaje("ERROR: ULTIMO NIVEL DE ESTUDIO (P501B) NO CORRESPONDE A LA EDAD");return false;}
            if(p501B >= 5 && p501B <= 6 && edad < 11){ mostrarMensaje("ERROR: ULTIMO NIVEL DE ESTUDIO (P501B) NO CORRESPONDE A LA EDAD");return false;}
            if(p501B == 7 &&  (edad < 3 || edad > 20) ){ mostrarMensaje("ERROR: ULTIMO NIVEL DE ESTUDIO (P501B) NO CORRESPONDE A LA EDAD");return false;}
            if(p501B >= 8 && p501B <= 11 &&  edad < 16 ){ mostrarMensaje("ERROR: ULTIMO NIVEL DE ESTUDIO (P501B) NO CORRESPONDE A LA EDAD");return false;}
            if(p501B == 12 && edad < 21){ mostrarMensaje("ERROR: ULTIMO NIVEL DE ESTUDIO (P501B) NO CORRESPONDE A LA EDAD");return false;}
        }else{
            c5_p501B ="";
        }

        if (m5_p502_linearlayout.getVisibility()==View.VISIBLE){
            if(c5_p502_o.equals("") || c5_p502_o.length()<3){mostrarMensaje("ERROR  “P502. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;}
        }else{
            c5_p502 = "";
            c5_p502_o = "";
//            c5_p502_c = "";
//            c5_p502_eleccion = "";
        }

        if(m5_p503_linearlayout.getVisibility() == View.VISIBLE && c5_p503.equals("-1")){ mostrarMensaje("PREGUNTA 503: DEBE SELECCIONAR UNA OPCION");return false; }
        if(m5_p504_linearlayout.getVisibility() == View.VISIBLE){
            if(c5_p504.equals("-1")) { mostrarMensaje("PREGUNTA 504: DEBE SELECCIONAR UNA OPCION");return false;}
            //nuevo Obs Excel// Modificado 05/06/2021
            if(c5_p501.equals("1") && !(c5_p504.equals("1") || c5_p504.equals("4"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P504");}
            if(c5_p501.equals("2") && !(c5_p504.equals("1") || c5_p504.equals("4"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P504");}
            if(c5_p501.equals("3") && !(c5_p504.equals("1") || c5_p504.equals("2") || c5_p504.equals("4"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P504");}
            if(c5_p501.equals("4") && !(c5_p504.equals("2") || c5_p504.equals("4"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P504");}
            if(c5_p501.equals("5") && !(c5_p504.equals("2") || c5_p504.equals("3") || c5_p504.equals("4"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P504");}
            if(c5_p501.equals("6") && !(c5_p504.equals("3") || c5_p504.equals("4"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P504");}
            if(c5_p501.equals("7") && !(c5_p504.equals("3") || c5_p504.equals("4") || c5_p504.equals("5"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P504");}
            if(c5_p501.equals("8") && !(c5_p504.equals("5"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P504");}
            if(c5_p501.equals("9") && !(Integer.parseInt(c5_p504) >= 3 && Integer.parseInt(c5_p504) <= 6)) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P504");}
            if(c5_p501.equals("10") && !(Integer.parseInt(c5_p504) >= 5 && Integer.parseInt(c5_p504) <= 6)) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P504");}
            if(c5_p501.equals("11") && !(Integer.parseInt(c5_p504) >= 5 && Integer.parseInt(c5_p504) <= 7)) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P504");}

            /////////////////AGREGADO 24/11/21 OBSERVACION 105////////////////////////////////////

            if(c5_p501B.equals("1") && !(c5_p504.equals("1") || c5_p504.equals("4"))) {mostrarMensaje("VERIFICAR: P501B NO COINCIDE CON P504");}
            if(c5_p501B.equals("2") && !(c5_p504.equals("1") || c5_p504.equals("4"))) {mostrarMensaje("VERIFICAR: P501B NO COINCIDE CON P504");}
            if(c5_p501B.equals("3") && !(c5_p504.equals("1") || c5_p504.equals("2") || c5_p504.equals("4"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P504");}
            if(c5_p501B.equals("4") && !(c5_p504.equals("2") || c5_p504.equals("4"))) {mostrarMensaje("VERIFICAR: P501B NO COINCIDE CON P504");}
            if(c5_p501B.equals("5") && !(c5_p504.equals("2") || c5_p504.equals("3") || c5_p504.equals("4"))) {mostrarMensaje("VERIFICAR: P501 NO COINCIDE CON P504");}
            if(c5_p501B.equals("6") && !(c5_p504.equals("3") || c5_p504.equals("4"))) {mostrarMensaje("VERIFICAR: P501B NO COINCIDE CON P504");}
            if(c5_p501B.equals("7") && !(c5_p504.equals("3") || c5_p504.equals("4") || c5_p504.equals("5"))) {mostrarMensaje("VERIFICAR: P501B NO COINCIDE CON P504");}
            if(c5_p501B.equals("8") && !(c5_p504.equals("5"))) {mostrarMensaje("VERIFICAR: P501B NO COINCIDE CON P504");}
            if(c5_p501B.equals("9") && !(Integer.parseInt(c5_p504) >= 3 && Integer.parseInt(c5_p504) <= 6)) {mostrarMensaje("VERIFICAR: P501B NO COINCIDE CON P504");}
            if(c5_p501B.equals("10") && !(Integer.parseInt(c5_p504) >= 5 && Integer.parseInt(c5_p504) <= 6)) {mostrarMensaje("VERIFICAR: P501B NO COINCIDE CON P504");}
            if(c5_p501B.equals("11") && !(Integer.parseInt(c5_p504) >= 5 && Integer.parseInt(c5_p504) <= 7)) {mostrarMensaje("VERIFICAR: P501B NO COINCIDE CON P504");}
            if(c5_p501B.equals("12") && !(Integer.parseInt(c5_p504) >= 5 && Integer.parseInt(c5_p504) <= 7)) {mostrarMensaje("VERIFICAR: P501B NO COINCIDE CON P504");}


            //////////////AGREGADO 25/11/21 OBSERVACION 106

            if(c5_p501B.equals("2") && c5_p504.equals("2") && !c5_p504_ce.equals("1")) {mostrarMensaje("VERIFICAR: P501B NO COINCIDE CON P504 Y EL AÑO O GRADO DETALLADO");}
            if(c5_p501B.equals("3") && c5_p504.equals("2") && c5_p504_ce.equals("1")) {mostrarMensaje("VERIFICAR: P501B NO COINCIDE CON P504 Y EL AÑO O GRADO DETALLADO");}
            if(c5_p501B.equals("4") && c5_p504.equals("3")) {mostrarMensaje("VERIFICAR: P501B NO COINCIDE CON P504 Y EL AÑO O GRADO DETALLADO");}






//            if(c5_p501.equals("1") && c5_p504.equals("1")){ mostrarMensaje("ERROR: P501 NO COINCIDE CON P504");return false;}
//            if(c5_p501.equals("2") && c5_p504.equals("2")){ mostrarMensaje("ERROR: P501 NO COINCIDE CON P504");return false;}
//            if(c5_p501.equals("3") && c5_p504.equals("3")){ mostrarMensaje("ERROR: P501 NO COINCIDE CON P504");return false;}
//            if(c5_p501.equals("4") && c5_p504.equals("4")){ mostrarMensaje("ERROR: P501 NO COINCIDE CON P504");return false;}
            if(c5_p504_anio_EditText.isEnabled() && c5_p504_anio.equals("") && !c5_p504_grado_EditText.isEnabled()) { mostrarMensaje("PREGUNTA 504: DEBE INGRESAR AÑO");return false;}
            if(c5_p504_grado_EditText.isEnabled() && c5_p504_grado.equals("") && !c5_p504_anio_EditText.isEnabled()) { mostrarMensaje("PREGUNTA 504: DEBE INGRESAR GRADO");return false;}
            if(c5_p504_anio_EditText.isEnabled() && c5_p504_anio.equals("") && c5_p504_grado_EditText.isEnabled() && c5_p504_grado.equals("")) { mostrarMensaje("PREGUNTA 504: DEBE INGRESAR AÑO o GRADO");return false;}
            if(c5_p504_ce.equals("-1")) { mostrarMensaje("PREGUNTA 504: DEBE SELECCIONAR UNA OPCION CENTRO DE ESTUDIOS");return false;}
            Log.e("p504","edad = "+edad+"   año = "+c5_p504_anio+"     grado = "+c5_p504_grado);
            switch (c5_p504){
                case "1":
                    if(edad >= 3 && edad <= 99){
                        if(!c5_p504_anio.equals("0") && !c5_p504_anio.equals("1") &&  !c5_p504_anio.equals("2") &&  !c5_p504_anio.equals("3"))
                        {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 0:3");return false;}
                    }else {mostrarMensaje("PREGUNTA 504: OPCION 1 NO CORRESPONDE SEGUN EDAD");return false;}
/*                    if(edad == 3 &&
                            !c5_p504_anio.equals("0") && !c5_p504_anio.equals("1"))
                    {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 0:1");return false;}
                    if(edad == 4 &&
                            !c5_p504_anio.equals("0") && !c5_p504_anio.equals("1") && !c5_p504_anio.equals("2"))
                    {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 0:2");return false;}
                    if(edad == 5 &&
                            !c5_p504_anio.equals("1") && !c5_p504_anio.equals("2") && !c5_p504_anio.equals("3"))
                    {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:3");return false;}
                    if(edad < 3 || edad > 5)
                    {mostrarMensaje("PREGUNTA 504: OPCION 1 NO CORRESPONDE SEGUN EDAD");return false;}*/
                    break;
                case "2":
                    if(edad >= 6 && edad <= 46){
                        if(!c5_p504_grado.equals("1") && !c5_p504_grado.equals("2") && !c5_p504_grado.equals("3") && !c5_p504_grado.equals("4") && !c5_p504_grado.equals("5") && !c5_p504_grado.equals("6"))
                        {mostrarMensaje("PREGUNTA 504: GRADO DEBE SER 1:6");return false;}
                    }else {mostrarMensaje("PREGUNTA 504: OPCION 2 NO CORRESPONDE SEGUN EDAD");return false;}
/*                    if(edad >= 5 && edad <= 46){
                        if(edad >= 10){
                            if(!c5_p504_grado.equals("1") && !c5_p504_grado.equals("2") && !c5_p504_grado.equals("3") && !c5_p504_grado.equals("4") && !c5_p504_grado.equals("5") && !c5_p504_grado.equals("6"))
                            {mostrarMensaje("PREGUNTA 504: GRADO DEBE SER 1:6");return false;}
                        }else
                        if(edad >= 9){
                            if(!c5_p504_grado.equals("1") && !c5_p504_grado.equals("2") && !c5_p504_grado.equals("3") && !c5_p504_grado.equals("4") && !c5_p504_grado.equals("5"))
                            {mostrarMensaje("PREGUNTA 504: GRADO DEBE SER 1:5");return false;}
                        }else
                        if(edad >= 8){
                             if(!c5_p504_grado.equals("1") && !c5_p504_grado.equals("2") && !c5_p504_grado.equals("3") && !c5_p504_grado.equals("4"))
                             {mostrarMensaje("PREGUNTA 504: GRADO DEBE SER 1:4");return false;}
                        }else
                        if(edad >= 7){
                            if(!c5_p504_grado.equals("1") && !c5_p504_grado.equals("2") && !c5_p504_grado.equals("3"))
                            {mostrarMensaje("PREGUNTA 504: GRADO DEBE SER 1:3");return false;}
                        }else
                        if(edad >= 6){
                            if(!c5_p504_grado.equals("1") && !c5_p504_grado.equals("2"))
                            {mostrarMensaje("PREGUNTA 504: GRADO DEBE SER 1:2");return false;}
                        }else
                        if(edad >= 5){
                            if(!c5_p504_grado.equals("1"))
                            {mostrarMensaje("PREGUNTA 504: GRADO DEBE SER 1");return false;}
                        }
                    }
                    if(edad < 5 || edad > 46)
                    {mostrarMensaje("PREGUNTA 504: OPCION 2 NO CORRESPONDE SEGUN EDAD");return false;}*/
                    break;
                case "3":
                    if(edad >= 11 && edad <= 99){
                        if(!c5_p504_anio.equals("1") && !c5_p504_anio.equals("2") && !c5_p504_anio.equals("3") && !c5_p504_anio.equals("4") && !c5_p504_anio.equals("5") && !c5_p504_anio.equals("6"))
                        {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:6");return false;}
                    }else {mostrarMensaje("PREGUNTA 504: OPCION 3 NO CORRESPONDE SEGUN EDAD");return false;}
                    /*if(edad >= 15){
                        if(!c5_p504_anio.equals("1") && !c5_p504_anio.equals("2") && !c5_p504_anio.equals("3") && !c5_p504_anio.equals("4") && !c5_p504_anio.equals("5") && !c5_p504_anio.equals("6"))
                        {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:6");return false;}
                    }else
                    if(edad >= 14){
                        if(!c5_p504_anio.equals("1") && !c5_p504_anio.equals("2") && !c5_p504_anio.equals("3") && !c5_p504_anio.equals("4"))
                        {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:4");return false;}
                    }else
                    if(edad >= 13){
                        if(!c5_p504_anio.equals("1") && !c5_p504_anio.equals("2") && !c5_p504_anio.equals("3"))
                        {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:3");return false;}
                    }else
                    if(edad >= 12){
                        if(!c5_p504_anio.equals("1") && !c5_p504_anio.equals("2"))
                        {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:2");return false;}
                    }else
                    if(edad >= 11){
                        if(!c5_p504_anio.equals("1"))
                        {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1");return false;}
                    }
                    if(edad <= 11)
                    {mostrarMensaje("PREGUNTA 504: OPCION 3 NO CORRESPONDE SEGUN EDAD");return false;}*/
                    break;
                case "4":
                    if(edad >= 3 && edad <= 20){
                        if(c5_p504_grado.equals("") && !c5_p504_anio.equals("1") && !c5_p504_anio.equals("2") && !c5_p504_anio.equals("3") && !c5_p504_anio.equals("4") && !c5_p504_anio.equals("5") && !c5_p504_anio.equals("6"))
                        {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:6");return false;}
                        if(c5_p504_anio.equals("") && !c5_p504_grado.equals("1") && !c5_p504_grado.equals("2") && !c5_p504_grado.equals("3") && !c5_p504_grado.equals("4") && !c5_p504_grado.equals("5"))
                        {mostrarMensaje("PREGUNTA 504: GRADO DEBE SER 1:5");return false;}
                    }else {mostrarMensaje("PREGUNTA 504: OPCION 4 NO CORRESPONDE SEGUN EDAD");return false;}
                    break;
                case "5":
                    if(edad <= 99) {
                        if(edad >= 20){
                            if(!c5_p504_anio.equals("1") && !c5_p504_anio.equals("2") && !c5_p504_anio.equals("3") && !c5_p504_anio.equals("4") && !c5_p504_anio.equals("5"))
                            {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:5");return false;}
                        }else
                        if(edad >= 19){
                            if(!c5_p504_anio.equals("1") && !c5_p504_anio.equals("2") && !c5_p504_anio.equals("3") && !c5_p504_anio.equals("4"))
                            {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:4");return false;}
                        }else
                        if(edad >= 18){
                            if(!c5_p504_anio.equals("1") && !c5_p504_anio.equals("2") && !c5_p504_anio.equals("3"))
                            {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:3");return false;}
                        }else
                        if(edad >= 17){
                            if(!c5_p504_anio.equals("1") && !c5_p504_anio.equals("2"))
                            {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:2");return false;}
                        }else
                        if(edad >= 16){
                            if(!c5_p504_anio.equals("1"))
                            {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1");return false;}
                        }
                        if(edad < 16)
                        {mostrarMensaje("PREGUNTA 504: OPCION 5 NO CORRESPONDE SEGUN EDAD");return false;}
                    }else {  mostrarMensaje("PREGUNTA 504: OPCION 5 NO CORRESPONDE SEGUN EDAD");return false;}
                    break;
                case "6":
                    if(edad <= 99){
                        if(edad >= 21){
                            if(!c5_p504_anio.equals("1") && !c5_p504_anio.equals("2") && !c5_p504_anio.equals("3") && !c5_p504_anio.equals("4") && !c5_p504_anio.equals("5") && !c5_p504_anio.equals("6"))
                            {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:6");return false;}
                        }else

                        if(edad >= 20){
                            if(!c5_p504_anio.equals("1") && !c5_p504_anio.equals("2") && !c5_p504_anio.equals("3") && !c5_p504_anio.equals("4") && !c5_p504_anio.equals("5"))
                            {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:5");return false;}
                        }else
                        if(edad >= 19){
                            if(!c5_p504_anio.equals("1") && !c5_p504_anio.equals("2") && !c5_p504_anio.equals("3") && !c5_p504_anio.equals("4"))
                            {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:5");return false;}
                        }else
                        if(edad >= 18){
                            if(!c5_p504_anio.equals("1") && !c5_p504_anio.equals("2") && !c5_p504_anio.equals("3"))
                            {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:3");return false;}
                        }else
                        if(edad >= 17){
                            if(!c5_p504_anio.equals("1") && !c5_p504_anio.equals("2"))
                            {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:2");return false;}
                        }else
                        if(edad >= 16){
                            if(!c5_p504_anio.equals("1"))
                            {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1");return false;}
                        }
                        if(edad < 16)
                        {mostrarMensaje("PREGUNTA 504: OPCION 6 NO CORRESPONDE SEGUN EDAD");return false;}
                    }else {mostrarMensaje("PREGUNTA 504: OPCION 6 NO CORRESPONDE SEGUN EDAD");return false;}
                    break;
                case "7":
                    if(edad <= 99){
                        if(edad >= 22){
                            if(!c5_p504_anio.equals("1") && !c5_p504_anio.equals("2"))
                            {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1:2");return false;}
                        }else
                        if(edad >= 21){
                            if(!c5_p504_anio.equals("1"))
                            {mostrarMensaje("PREGUNTA 504: AÑO DEBE SER 1");return false;}
                        }
                        if(edad < 21)
                        {mostrarMensaje("PREGUNTA 504: OPCION 7 NO CORRESPONDE SEGUN EDAD");return false;}
                    }else {mostrarMensaje("PREGUNTA 507: OPCION 7 NO CORRESPONDE SEGUN EDAD");return false;}
                    break;
            }

        }
        else{
            c5_p504 = "";
            c5_p504_anio = "";
            c5_p504_grado= "";
            c5_p504_ce = "";
        }
        if(m5_p505_linearlayout.getVisibility() == View.VISIBLE){
            if(c5_p505.equals("-1")) {mostrarMensaje("PREGUNTA 505: DEBE SELECCIONAR UNA OPCION");return false; }
        }
        else{c5_p505 = "";}

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

    /*
    public void limpiar_p502(){
        txtCodigoCarrera.setText("");
        txtNombreCarrera.setText("");
        c5_p502_o_EditText.setText("");
        c5_p502_o_EditText.setEnabled(false);
        c5_p502_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
        c5_p502_c_CheckBox.setEnabled(true);
        c5_p502_Autocomplete.setEnabled(true);
    }

     */



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


    public void ocultarOtrosLayouts(){
        llenarVariables();
        //FLUJO 54 A
        if(c5_p505.equals("1")){
            ocultarFragmentP506P507(false); //Continua con el siguiente Fragment
        }else
//        if(c5_p505.equals("2") && c5_p503.equals("1") && edad >= 3 && edad <= 17){
        if(c5_p505.equals("2") && c5_p503.equals("1")){
            ocultarFragmentP506P507(true);
            ocultarFragmentP508P511(false); //Salta al fragment de la P510
        }else
//        if(c5_p505.equals("2") && c5_p503.equals("1") && edad >= 18 && edad <= 25){
//            ocultarFragmentP506P507(true);
//            ocultarFragmentP508P511(false); //Salta al fragment de la P511
//        }else
//        if(c5_p505.equals("2") && c5_p503.equals("1") && edad > 25 && (c5_p501.equals("10") || c5_p501.equals("11"))){
//            ocultarFragmentP506P507(true);
//            ocultarFragmentP508P511(true); //Salta al fragment de la P512
//        }else
//        if(c5_p505.equals("2") && c5_p503.equals("1") && edad > 25 && !(c5_p501.equals("10") || c5_p501.equals("11"))){
//            ocultarFragmentP506P507(true);
//            ocultarFragmentP508P511(true); //Salta al fragment de la P515
//        }else
        if(c5_p505.equals("2") && c5_p503.equals("2") && edad >= 3 && edad <= 25){
            ocultarFragmentP506P507(true);
            ocultarFragmentP508P511(false); //Salta al fragment de la P511
        }else
        if(c5_p505.equals("2") && c5_p503.equals("2") && edad > 25 && (c5_p501.equals("10") || c5_p501.equals("11"))){
            ocultarFragmentP506P507(true);
            ocultarFragmentP508P511(true); //Salta al fragment de la P512
        }else
        if(c5_p505.equals("2") && c5_p503.equals("2") && edad > 25 && !(c5_p501.equals("10") || c5_p501.equals("11"))){
            ocultarFragmentP506P507(true);
            ocultarFragmentP508P511(true); //Salta al fragment de la P515
        }else Log.e("P501P505","No cumple ninguna condicion ocultarOtrosLayouts");
    }

    public void ocultarFragmentP506P507(boolean ocultar) {
        Data data = new Data(context);
        data.open();
        if(ocultar) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.modulo5_c5_p506, "");
            contentValues.put(SQLConstantes.modulo5_c5_p506a, "");
            contentValues.put(SQLConstantes.modulo5_c5_p507, "");
            contentValues.put(SQLConstantes.modulo5_c5_p507_anio, "");
            contentValues.put(SQLConstantes.modulo5_c5_p507_grado, "");
            contentValues.put(SQLConstantes.modulo5_c5_p507_ce, "");
            data.actualizarElemento(getNombreTabla(), contentValues, idEncuestado);
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p506p507,"-1",idEncuestado);
        }else {
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p506p507,"1",idEncuestado);
        }
        data.close();
    }
    public void ocultarFragmentP508P511(boolean ocultar) {
        Data data = new Data(context);
        data.open();
        if(ocultar) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.modulo5_c5_p508_1,"");
            contentValues.put(SQLConstantes.modulo5_c5_p508_2,"");
            contentValues.put(SQLConstantes.modulo5_c5_p508_3,"");
            contentValues.put(SQLConstantes.modulo5_c5_p508_4,"");
            contentValues.put(SQLConstantes.modulo5_c5_p508_5,"");
            contentValues.put(SQLConstantes.modulo5_c5_p508_4_o,"");
            contentValues.put(SQLConstantes.modulo5_c5_p509_1,"");
            contentValues.put(SQLConstantes.modulo5_c5_p509_2,"");
            contentValues.put(SQLConstantes.modulo5_c5_p509_3,"");
            contentValues.put(SQLConstantes.modulo5_c5_p509_4,"");
            contentValues.put(SQLConstantes.modulo5_c5_p509_5,"");
            contentValues.put(SQLConstantes.modulo5_c5_p509_6,"");
            contentValues.put(SQLConstantes.modulo5_c5_p509_7,"");
            contentValues.put(SQLConstantes.modulo5_c5_p509_7_o,"");
            contentValues.put(SQLConstantes.modulo5_c5_p509_8,"");
            contentValues.put(SQLConstantes.modulo5_c5_p510_o,"");
            contentValues.put(SQLConstantes.modulo5_c5_p510,"");
            contentValues.put(SQLConstantes.modulo5_c5_p511,"");
            contentValues.put(SQLConstantes.modulo5_c5_p511_o,"");
            data.actualizarElemento(getNombreTabla(), contentValues, idEncuestado);
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p508p511,"-1",idEncuestado);
        }else {
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p508p511,"1",idEncuestado);
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
    //Anthony M 07/05/2021
    public void limpiarP501A(){
        c5_p501A_RadioGroup.clearCheck();
    }
    public void limpiarP501(){
        c5_p501_RadioGroup.clearCheck();
    }
    public void limpiarP501B(){
        c5_p501B_RadioGroup.clearCheck();
    }
    public void limpiarP502(){
        c5_p502_Autocomplete.setText("");
        txtCodigoCarrera.setText("");
        txtNombreCarrera.setText("");
        c5_p502_o_EditText.setText("");
    }
    public void limpiarP503(){
        c5_p503_RadioGroup.clearCheck();
    }
    public void limpiarP504(){
        c5_p504_RadioGroup.clearCheck();
        c5_p504_anio_EditText.setText("");
        c5_p504_grado_EditText.setText("");
        c5_p504_ce_RadioGroup.clearCheck();
    }
    public void limpiarP505(){
        c5_p505_RadioGroup.clearCheck();
    }
    public void validarFlujo54(){ //FLUJO 54
        llenarVariables();
        Log.e("c5_p504",""+c5_p504);
        switch (c5_p504){
            case "1": case "3": case "5": case "6": case "7":
                c5_p504_anio_EditText.setEnabled(true);
                c5_p504_anio_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                c5_p504_grado_EditText.setText("");
                c5_p504_grado_EditText.setEnabled(false);
                c5_p504_grado_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                break;
            case "2":
                c5_p504_anio_EditText.setText("");
                c5_p504_anio_EditText.setEnabled(false);
                c5_p504_anio_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                c5_p504_grado_EditText.setEnabled(true);
                c5_p504_grado_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                break;
            case "4":
                c5_p504_anio_EditText.setEnabled(true);
                c5_p504_anio_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                c5_p504_grado_EditText.setEnabled(true);
                c5_p504_grado_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                break;
        }
    }
    public void validar_p501A(){
        llenarVariables();
        if(edad >=3)
            m5_p501A_linearlayout.setVisibility(View.VISIBLE);
        else {
            limpiarP501A();
            m5_p501A_linearlayout.setVisibility(View.GONE);
        }
    }
    public void validar_p501(){
        llenarVariables();
        if(edad >=3 && c5_p501A.equals("1"))
            m5_p501_linearlayout.setVisibility(View.VISIBLE);
        else {
            limpiarP501();
            m5_p501_linearlayout.setVisibility(View.GONE);
        }
    }
    public void validar_p501B(){
        llenarVariables();
        if(edad >=3 && c5_p501A.equals("2"))
            m5_p501B_linearlayout.setVisibility(View.VISIBLE);
        else {
            limpiarP501B();
            m5_p501B_linearlayout.setVisibility(View.GONE);
        }
    }
    public void validar_p502(){
        llenarVariables();
        int n=-1,n2=-1;
        try{
            n = Integer.parseInt(c5_p501);
            n2 = Integer.parseInt(c5_p501B);
        }catch (Exception e){}

        if(edad >=3 && ((n >=7 && n <=11) || (n2 >=8 && n2 <=12)))
            m5_p502_linearlayout.setVisibility(View.VISIBLE);
        else {
            limpiarP502();
            m5_p502_linearlayout.setVisibility(View.GONE);
        }
    }
    public void validar_p503(){
        llenarVariables();
        if(edad >=3)
            m5_p503_linearlayout.setVisibility(View.VISIBLE);
        else {
            limpiarP503();
            m5_p503_linearlayout.setVisibility(View.GONE);
        }
    }
    public void validar_p504(){
        llenarVariables();
        if(edad >=3 && c5_p503.equals("1"))
            m5_p504_linearlayout.setVisibility(View.VISIBLE);
        else {
            limpiarP504();
            m5_p504_linearlayout.setVisibility(View.GONE);
        }
    }
    public void validar_p505(){
        llenarVariables();
        if(edad >=3)
            m5_p505_linearlayout.setVisibility(View.VISIBLE);
        else {
            limpiarP505();
            m5_p505_linearlayout.setVisibility(View.GONE);
        }
    }
}
