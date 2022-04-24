package com.example.ricindigus.enpove2021.fragments.modulo4;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo4;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethods;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;
import com.example.ricindigus.enpove2021.util.UtilsMethodsUpdates;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP408P410 extends FragmentPagina {
    String idEncuestado;
    String idInformante;
    Context context;

    Spinner informanteSpinner;

    RadioGroup c4_p408_1_RadioGroup, c4_p408_2_RadioGroup, c4_p408_3_RadioGroup, c4_p408_4_RadioGroup,
            c4_p408_5_RadioGroup, c4_p408_6_RadioGroup;
    //    RadioGroup c4_p409_RadioGroup, c4_p410_RadioGroup;
    CheckBox c4_p409_2_Checkbox;
    Spinner c4_p409_Spinner,c4_p409_Spinner_parentesco;
    EditText text1_Editext;
    RadioGroup c4_p410b_RadioGroup,c4_p410a_RadioGroup, c4_p410_RadioGroup, c4_p411_RadioGroup, c4_p412_RadioGroup; // Anthony 30/04/2021
    //    LinearLayout m4_p408_linearlayout, m4_p409_linearlayout, m4_p410_linearlayout;
    LinearLayout m4_p408_linearlayout, m4_p409_linearlayout, m4_p410_linearlayout,m4_p410a_linearlayout,m4_p410b_linearlayout, m4_p411_linearlayout, m4_p412_linearlayout; // Anthony 30/04/2021
    private String c4_p408_1;
    private String c4_p408_2;
    private String c4_p408_3;
    private String c4_p408_4;
    private String c4_p408_5;
    private String c4_p408_6;
    private String c4_p409;
    private String c4_p409_nom;
    private String c4_p409_parentesco;
    private String c4_p409_1_o;
    private String c4_p409_2;
    private String c4_p410a;
    private String c4_p410b;
    private String c4_p410;
    private String c4_p411;// Anthony 30/04/2021
    private String c4_p412;// Anthony 30/04/2021

    private int edad, sexo, edadotro;
    private ArrayList<String> residentes_lista;//Anthony M 07/05/2021
    private ArrayList<Residente> residentes_2;//Anthony M 07/05/2021
    private String p203,p204;
    private String idHogar;

    String p212,p200_N;

    @SuppressLint("ValidFragment")
    public FragmentP408P410(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        p200_N = residente.getNumero();
        p203 = residente.getC2_p203();
        p204 = residente.getC2_p204();
        p212 = residente.getC2_p212();
        idHogar = residente.getId_hogar();
        if(residente.getC2_p204()=="") sexo = -1; else sexo = Integer.parseInt(residente.getC2_p204());
        if(residente.getC2_p205_a()=="") edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        data.close();
    }

    public FragmentP408P410() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p408_p410, container, false);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

        c4_p408_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_408_radiogroup_C4_P408_1);
        c4_p408_2_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_408_radiogroup_C4_P408_2);
        c4_p408_3_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_408_radiogroup_C4_P408_3);
        c4_p408_4_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_408_radiogroup_C4_P408_4);
        c4_p408_5_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_408_radiogroup_C4_P408_5);
        c4_p408_6_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_408_radiogroup_C4_P408_6);

        c4_p409_Spinner = (Spinner) rootView.findViewById(R.id.mod4_409_spinner_C2_P409);
        c4_p409_Spinner_parentesco = (Spinner) rootView.findViewById(R.id.mod4_409_spinner_C2_P409_parentesco);
        text1_Editext  = (EditText) rootView.findViewById(R.id.text1_Editext);
        c4_p409_2_Checkbox = (CheckBox) rootView.findViewById(R.id.mod4_409_2_checkbox_C4_P409_2);

        c4_p410a_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_410_radiogroup_C4_P410_A);
        c4_p410b_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_410_radiogroup_C4_P410_B);
        c4_p410_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_410_radiogroup_C4_P410);
        c4_p411_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_411_radiogroup_C4_P411); // Anthony 30/04/2021
        c4_p412_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod4_412_radiogroup_C4_P412); // Anthony 30/04/2021

        m4_p408_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p408);
        m4_p409_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p409);
        m4_p410a_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p410_a);
        m4_p410b_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p410_b);
        m4_p410_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p410);
        m4_p411_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p411); // Anthony 30/04/2021
        m4_p412_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m4_p412); // Anthony 30/04/2021

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        configurarEditText(text1_Editext,m4_p409_linearlayout,0,80);

        c4_p408_1_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                validarFlujo43();
            }
        });
        c4_p408_2_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                validarFlujo43();
            }
        });
        c4_p408_3_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                validarFlujo43();
            }
        });
        c4_p408_4_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                validarFlujo43();
            }
        });
        c4_p408_5_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                validarFlujo43();
            }
        });
        c4_p408_6_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                validarFlujo43();
            }
        });

        c4_p409_2_Checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){

                    c4_p409_Spinner_parentesco.setEnabled(false);
                    c4_p409_Spinner_parentesco.setSelection(0);

                    c4_p409_Spinner.setEnabled(false);
                    c4_p409_Spinner.setSelection(0);
                }else{
                    c4_p409_Spinner_parentesco.setEnabled(true);
                    c4_p409_Spinner.setEnabled(true);

                }
            }
        });

        c4_p410a_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                switch (seleccionado){
                    case 1:
                        m4_p410b_linearlayout.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        limpiar_p410B();
                        m4_p410b_linearlayout.setVisibility(View.GONE);
                        break;
                }

            }
        });



        c4_p410_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                validarFlujo44();
            }
        });

        c4_p409_Spinner_parentesco.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos == 6){
                    text1_Editext.setEnabled(true);
                    text1_Editext.setBackgroundResource(R.drawable.input_text_enabled);
                }else{
                    text1_Editext.setEnabled(false);
                    text1_Editext.setText("");
                    text1_Editext.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


        llenarVista();
        cargarDatos();
    }

    @Override
    public void guardarDatos() {

        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo4_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo4_c4_p408_1,c4_p408_1);
        contentValues.put(SQLConstantes.modulo4_c4_p408_2,c4_p408_2);
        contentValues.put(SQLConstantes.modulo4_c4_p408_3,c4_p408_3);
        contentValues.put(SQLConstantes.modulo4_c4_p408_4,c4_p408_4);
        contentValues.put(SQLConstantes.modulo4_c4_p408_5,c4_p408_5);
        contentValues.put(SQLConstantes.modulo4_c4_p408_6,c4_p408_6);
        contentValues.put(SQLConstantes.modulo4_c4_p409,c4_p409);
        contentValues.put(SQLConstantes.modulo4_c4_p409_nom,c4_p409_nom);//Anthony M 07/05/2021
        contentValues.put(SQLConstantes.modulo4_c4_p409_1,c4_p409_parentesco);
        contentValues.put(SQLConstantes.modulo4_c4_p409_o,c4_p409_1_o);
        contentValues.put(SQLConstantes.modulo4_c4_p409_2,c4_p409_2);
        contentValues.put(SQLConstantes.modulo4_c4_p410a,c4_p410a);
        contentValues.put(SQLConstantes.modulo4_c4_p410b,c4_p410b);
        contentValues.put(SQLConstantes.modulo4_c4_p410,c4_p410);
        contentValues.put(SQLConstantes.modulo4_c4_p411,c4_p411); // Anthony 30/04/2021
        contentValues.put(SQLConstantes.modulo4_c4_p412,c4_p412); // Anthony 30/04/2021
        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp408p410,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo4_COB400,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo4_COB400,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();
        ocultarOtrosLayouts();
        updateHogarP20();
    }

    private void ocultarOtrosLayouts() {
        llenarVariables();
        /** FLUJO 44 **/
        if(edad <= 4 && (c4_p410.equals("3") || c4_p410.equals("4"))){
            ocultarFragmentP411P416(false); //Pasa al fragment de la P413
        }else
        if(((p204.equals("1") && edad >= 5 && edad <= 14) || (p204.equals("2") && edad >= 5 && edad <= 11)) &&
                ( c4_p410.equals("3") || c4_p410.equals("4") )) {
            ocultarFragmentP411P416(true); //Pasa a Capitulo 500
        }else
        if(p204.equals("2") && edad >= 12 && edad <= 49 &&
                c4_p410.equals("3") || c4_p410.equals("4")){
            ocultarFragmentP411P416(false); //Pasa al fragment de la P414
        }else
        if( ( (p204.equals("1") && edad >= 15) || (p204.equals("2") && edad >= 50) ) &&
                ( c4_p410.equals("3") || c4_p410.equals("4") ) ){
            ocultarFragmentP411P416(false); //Pasa al fragment de la P417
        }else {
            Log.e("P408P410", "No cumple validacion de flujo ocultarOtrosLayouts()");
            //Parche - Valida los univeros del siguiente fragment para saber si lo oculta
            boolean ocultar = true;
            if(edad <= 4) ocultar = false; // UNIVERSO FLUJO 45
            if(sexo == 2 && edad >= 12 && edad <= 49) ocultar = false;  // UNIVERSO FLUJO 46, 47, 48 y 48A
            if(edad >= 15) ocultar = false; // UNIVERSO FLUJO 49
            if(p203.equals("1")) ocultar=false; // UNIVERSO FLUJO 50
            ocultarFragmentP411P416(ocultar);
        }
    }

    private void ocultarFragmentP411P416(boolean ocultar) {
        Data data1 = new Data(context);
        data1.open();
        if(ocultar){
            ContentValues contentValues1 = new ContentValues();
            contentValues1.put(SQLConstantes.modulo4_c4_p413_1,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p413_2,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p413_3,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p413_4,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p413_5,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p414,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p415,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p416_1,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p416_2,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p416_3,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p416_4,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p416_5,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p416_5_o,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p416_6,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p417_1,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p417_2,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p417_3,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p417_4,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p417_5,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p417_6,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p417_7,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p417_7_o,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p417_8,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p417_9,"");
            contentValues1.put(SQLConstantes.modulo4_c4_p418,"");
            data1.actualizarElemento(getNombreTabla(),contentValues1,idEncuestado);
            data1.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p411p416,"-1",idEncuestado);
            data1.close();
        }else{
            data1.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p411p416,"1",idEncuestado);
        }
    }
    @Override
    public void llenarVariables() {

        idInformante = obtener_Nresidente(informanteSpinner);
        c4_p408_1 = c4_p408_1_RadioGroup.indexOfChild(c4_p408_1_RadioGroup.findViewById(c4_p408_1_RadioGroup.getCheckedRadioButtonId())) + "";
        c4_p408_2 = c4_p408_2_RadioGroup.indexOfChild(c4_p408_2_RadioGroup.findViewById(c4_p408_2_RadioGroup.getCheckedRadioButtonId())) + "";
        c4_p408_3 = c4_p408_3_RadioGroup.indexOfChild(c4_p408_3_RadioGroup.findViewById(c4_p408_3_RadioGroup.getCheckedRadioButtonId())) + "";
        c4_p408_4 = c4_p408_4_RadioGroup.indexOfChild(c4_p408_4_RadioGroup.findViewById(c4_p408_4_RadioGroup.getCheckedRadioButtonId())) + "";
        c4_p408_5 = c4_p408_5_RadioGroup.indexOfChild(c4_p408_5_RadioGroup.findViewById(c4_p408_5_RadioGroup.getCheckedRadioButtonId())) + "";
        c4_p408_6 = c4_p408_6_RadioGroup.indexOfChild(c4_p408_6_RadioGroup.findViewById(c4_p408_6_RadioGroup.getCheckedRadioButtonId())) + "";
//        c4_p409 = c4_p409_RadioGroup.indexOfChild(c4_p409_RadioGroup.findViewById(c4_p409_RadioGroup.getCheckedRadioButtonId()))+"";
//        c4_p409 = c4_p409_Spinner.getSelectedItemPosition()+""; // Anthony 30/04/2021
        if (c4_p409_Spinner.getSelectedItemPosition() != 0) {
            c4_p409 = obtener_id_residente(c4_p409_Spinner.getSelectedItem() + ""); // Anthony 30/04/2021
        } else {
            c4_p409 = "0";
        }
        c4_p409_nom = obtener_residente();



        if(c4_p409_Spinner_parentesco.getSelectedItemPosition() == 6){
            c4_p409_1_o = text1_Editext.getText()+"";
            try{
                Integer.parseInt(c4_p409_parentesco);
                c4_p409_parentesco = "0";
            }catch (Exception e){}
        }else{
            c4_p409_parentesco = c4_p409_Spinner_parentesco.getSelectedItemPosition() + "";
        }

//        if(c4_p409_Spinner_parentesco.getSelectedItemPosition() != 0){
//            c4_p409_parentesco = c4_p409_Spinner_parentesco.getSelectedItem().toString();
//        }else
//        if(c4_p409_Spinner_parentesco != null && c4_p409_Spinner_parentesco.equals("11")){
//            c4_p409_parentesco_seleccion = text1_Editext.getText()+"";
//        }

        if(c4_p409_2_Checkbox.isChecked()) c4_p409_2 = "1"; else c4_p409_2 = "0";

        c4_p410a = c4_p410a_RadioGroup.indexOfChild(c4_p410a_RadioGroup.findViewById(c4_p410a_RadioGroup.getCheckedRadioButtonId()))+"";
        c4_p410b = c4_p410b_RadioGroup.indexOfChild(c4_p410b_RadioGroup.findViewById(c4_p410b_RadioGroup.getCheckedRadioButtonId()))+"";
        c4_p410 = c4_p410_RadioGroup.indexOfChild(c4_p410_RadioGroup.findViewById(c4_p410_RadioGroup.getCheckedRadioButtonId()))+"";
        c4_p411 = c4_p411_RadioGroup.indexOfChild(c4_p411_RadioGroup.findViewById(c4_p411_RadioGroup.getCheckedRadioButtonId()))+""; // Anthony 30/04/2021
        c4_p412 = c4_p412_RadioGroup.indexOfChild(c4_p412_RadioGroup.findViewById(c4_p412_RadioGroup.getCheckedRadioButtonId()))+""; // Anthony 30/04/2021

    }
    public String obtener_id_residente(String item){
        String cadena = "";
        try{
            cadena = item.substring(0,item.indexOf(" ")); //obtiene el ID del item
        }catch (Exception ignore){}

        return cadena;
    }
    public String obtener_residente() {
        String cadena="";
        try{ //REGLA 55
            int posicion = c4_p409_Spinner.getSelectedItemPosition();
            cadena = residentes_2.get(posicion).getC2_p202()+" "+
                    residentes_2.get(posicion).getC2_p202_mat()+" "+
                    residentes_2.get(posicion).getC2_p202_pat();
//            Log.e("c4_p409_nom",""+c4_p409_nom);
        }catch (Exception e){}
        return cadena;
    }

    @Override
    public void cargarDatos() {

        Data data = new Data(context);
        data.open();
        if (data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo4 modulo4 = data.getModulo4(idEncuestado);
            ArrayList<String> residentes = data.getListaSpinnerResidentesHogar(modulo4.getIdHogar());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,residentes);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            informanteSpinner.setAdapter(adapter);
            ArrayList<String> informantes = data.getListaInformantesMayor12(modulo4.getIdHogar());
            UtilsMethodsInputs.loadSpinner(informantes,informanteSpinner,context);
            //Anthony M 07/05/2021
            residentes_2 = data.getAllResidentesHogar(modulo4.getIdHogar());
            residentes_lista = new ArrayList<>();
            residentes_lista.add("Seleccione...");
            for (Residente r : residentes_2){
//                Log.e("prrr",""+r.getC2_p205_a());
                 edadotro = Integer.parseInt(r.getC2_p205_a());
                if( edadotro>= 12){
                    residentes_lista.add(r.getNumero()+" "+r.getC2_p202()+" "+r.getC2_p202_pat()+" "+r.getC2_p202_mat()+" ("+r.getC2_p205_a()+" años)");
                }
            }
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,residentes_lista);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            c4_p409_Spinner.setAdapter(null);
            c4_p409_Spinner.setAdapter(adapter2); //Anthony M 07/05/2021
//            if(!modulo4.getIdInformante().equals(""))informanteSpinner.setSelection(Integer.parseInt(modulo4.getIdInformante())); // Anthony 30/04/2021
            if(modulo4.getIdInformante() != null && !modulo4.getIdInformante().equals("")) informanteSpinner.setSelection(obtener_posicion(modulo4.getIdInformante(),informanteSpinner));
            if(modulo4.getC4_p408_1() != null && !modulo4.getC4_p408_1().equals("-1") && !modulo4.getC4_p408_1().equals(""))((RadioButton)c4_p408_1_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p408_1()))).setChecked(true);
            if(modulo4.getC4_p408_2() != null && !modulo4.getC4_p408_2().equals("-1") && !modulo4.getC4_p408_2().equals(""))((RadioButton)c4_p408_2_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p408_2()))).setChecked(true);
            if(modulo4.getC4_p408_3() != null && !modulo4.getC4_p408_3().equals("-1") && !modulo4.getC4_p408_3().equals(""))((RadioButton)c4_p408_3_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p408_3()))).setChecked(true);
            if(modulo4.getC4_p408_4() != null && !modulo4.getC4_p408_4().equals("-1") && !modulo4.getC4_p408_4().equals(""))((RadioButton)c4_p408_4_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p408_4()))).setChecked(true);
            if(modulo4.getC4_p408_5() != null && !modulo4.getC4_p408_5().equals("-1") && !modulo4.getC4_p408_5().equals(""))((RadioButton)c4_p408_5_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p408_5()))).setChecked(true);
            if(modulo4.getC4_p408_6() != null && !modulo4.getC4_p408_6().equals("-1") && !modulo4.getC4_p408_6().equals(""))((RadioButton)c4_p408_6_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p408_6()))).setChecked(true);
//            if(!modulo4.getC4_p409().equals("-1") && !modulo4.getC4_p409().equals(""))((RadioButton)c4_p409_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p409()))).setChecked(true);
//            if(modulo4.getC4_p409() != null && !modulo4.getC4_p409().equals(""))c4_p409_Spinner.setSelection(Integer.parseInt(modulo4.getC4_p409())); // Anthony 30/04/2021
            if(modulo4.getC4_p409() != null && !modulo4.getC4_p409().equals("")){
                c4_p409_Spinner.setSelection(Integer.parseInt(obtener_pos(modulo4.getC4_p409()))); // Anthony 30/04/2021
            }


            if(modulo4.getC4_p409_1() !=null && !modulo4.getC4_p409_1().equals("")){
                try{
                    int p = Integer.parseInt(modulo4.getC4_p409_1());
                    if(p < 6 ) c4_p409_Spinner_parentesco.setSelection(p);
                }catch (Exception e){
                    c4_p409_Spinner_parentesco.setSelection(6);
                    text1_Editext.setText(modulo4.getC4_p409_o());
                }
            }

//            if(modulo4.getC4_p409_1() != null && !modulo4.getC4_p409_1().equals("")) {
//                c4_p409_Spinner_parentesco.setSelection(Integer.parseInt(modulo4.getC4_p409_1()));
//            }
//
//            if(c4_p409_Spinner_parentesco != null && c4_p409_Spinner_parentesco.equals(11)){
//                text1_Editext.setEnabled(true);text1_Editext.setBackgroundResource(R.drawable.input_text_enabled);
//                text1_Editext.setText(modulo4.getC4_p409_o());
//            }

            Log.e("cargar","p409_nom = "+modulo4.getC4_p409_nom());
            Log.e("cargar","p409_2 = "+modulo4.getC4_p409_2());
            if(modulo4.getC4_p410a() != null && !modulo4.getC4_p410a().equals("-1") && !modulo4.getC4_p410a().equals(""))((RadioButton)c4_p410a_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p410a()))).setChecked(true);
            if(modulo4.getC4_p410b() != null && !modulo4.getC4_p410b().equals("-1") && !modulo4.getC4_p410b().equals(""))((RadioButton)c4_p410b_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p410b()))).setChecked(true);
            if(modulo4.getC4_p410() != null && !modulo4.getC4_p410().equals("-1") && !modulo4.getC4_p410().equals(""))((RadioButton)c4_p410_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p410()))).setChecked(true); // Anthony 30/04/2021
            if(modulo4.getC4_p411() != null && !modulo4.getC4_p411().equals("-1") && !modulo4.getC4_p411().equals(""))((RadioButton)c4_p411_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p411()))).setChecked(true); // Anthony 30/04/2021
            if(modulo4.getC4_p412() != null && !modulo4.getC4_p412().equals("-1") && !modulo4.getC4_p412().equals(""))((RadioButton)c4_p412_RadioGroup.getChildAt(Integer.parseInt(modulo4.getC4_p412()))).setChecked(true); // Anthony 30/04/2021
            if(modulo4.getC4_p409_2().equals("1")) c4_p409_2_Checkbox.setChecked(true);
        }
        inicio();
        data.close();


    }
    private String obtener_pos(String id) {
        String posicion="0";
        try{
            for(int i=0; i<c4_p409_Spinner.getCount();i++){

                if(id.equals(obtener_id_residente(c4_p409_Spinner.getItemAtPosition(i)+""))){
                    posicion = i+"";
                }

            }
        }catch (Exception e){posicion = "0";}
        return posicion;
    }

    @Override
    public void llenarVista() {
        /*
        Data data = new Data(context);
        data.open();
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p408,idEncuestado)) m4_p408_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p409,idEncuestado)) m4_p409_linearlayout.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p410,idEncuestado)) m4_p410_linearlayout.setVisibility(View.GONE);
        data.close();


         */

    }

    @Override
    public boolean validarDatos() {
        llenarVariables();

        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        if(m4_p408_linearlayout.getVisibility()==View.VISIBLE){
            if(c4_p408_1.equals("-1")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR UNA RESPUESTA EN LA PREGUNTA 408 ITEM 1");
                return false;
            }
            if(c4_p408_2.equals("-1")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR UNA RESPUESTA EN LA PREGUNTA 408 ITEM 2");
                return false;
            }
            if(c4_p408_3.equals("-1")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR UNA RESPUESTA EN LA PREGUNTA 408 ITEM 3");
                return false;
            }
            if(c4_p408_4.equals("-1")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR UNA RESPUESTA EN LA PREGUNTA 408 ITEM 4");
                return false;
            }
            if(c4_p408_5.equals("-1")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR UNA RESPUESTA EN LA PREGUNTA 408 ITEM 5");
                return false;
            }
            if(c4_p408_6.equals("-1")){
                mostrarMensaje("ERROR  “DEBE SELECCIONAR UNA RESPUESTA EN LA PREGUNTA 408 ITEM 6");
                return false;
            }
        } else{
            c4_p408_1 = "";
            c4_p408_2 = "";
            c4_p408_3 = "";
            c4_p408_4 = "";
            c4_p408_5 = "";
            c4_p408_6 = "";
        }
        if (m4_p409_linearlayout.getVisibility()==View.VISIBLE) {


             if(c4_p409_2.equals("0")) {

                 if (c4_p409.equals("0")) {
                     mostrarMensaje("PREGUNTA 409: DEBE SELECCIONAR UNA OPCION");
                     return false;
                 }

                 if (c4_p409_parentesco.equals("0")) {
                     mostrarMensaje("PREGUNTA 409: DEBE INDICAR EL PARENTESCO");
                     return false;
                 }
                 if (text1_Editext.isEnabled() && (text1_Editext.getText().toString().equals("") || text1_Editext.length() < 3)) {
                     mostrarMensaje("ERROR  “P409. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");
                     return false;
                 }

                 Residente residente = DAOUtils.getResidenteNroResidente(idHogar, c4_p409_Spinner.getSelectedItem() + "", context);
                 int edadencargado = Integer.parseInt(residente.getC2_p205_a());

                 //nuevas reglas
                 // Si P409_1=1 AND {EDAD(PERSONA) – EDAD(P409_N)< 12 OR EDAD(PERSONA) – EDAD(P409_N)> 65 } => VERIFICAR
                 if (c4_p409_parentesco.equals("1") && ((edadencargado - edad) < 12 || (edadencargado - edad) > 65)) {
                     mostrarMensaje("VERIFICAR: VERIFICAR LA RELACIÓN DE PARENTESCO DEL RESPONSABLE DE SU CUIDADO.");
                 }
                 //Si P409_1=2 AND {EDAD(PERSONA) – EDAD(P409_N)< -80 OR EDAD(PERSONA) – EDAD(P409_N)> 80 } => VERIFICAR
                 if (c4_p409_parentesco.equals("2") && ((edadencargado - edad) < 80 || (edadencargado - edad) > 80)) {
                     mostrarMensaje("VERIFICAR: VERIFICAR LA RELACIÓN DE PARENTESCO DEL RESPONSABLE DE SU CUIDADO.");
                 }
                 //Si P409_1=3 AND {EDAD(PERSONA) – EDAD(P409_N)> -24} => VERIFICAR
                 if (c4_p409_parentesco.equals("3") && ((edadencargado - edad) < 24)) {
                     mostrarMensaje("VERIFICAR: VERIFICAR LA RELACIÓN DE PARENTESCO DEL RESPONSABLE DE SU CUIDADO.");
                 }
                 //Si P409_1=4 AND {EDAD(PERSONA) – EDAD(P409_N)< -60 OR EDAD(PERSONA) – EDAD(P409_N)> -12} => VERIFICAR
                 if (c4_p409_parentesco.equals("4") && ((edadencargado - edad) < 60 || (edadencargado - edad) > 12)) {
                     mostrarMensaje("VERIFICAR: VERIFICAR LA RELACIÓN DE PARENTESCO DEL RESPONSABLE DE SU CUIDADO.");
                 }
                 //Anthony M 19/07/2021
                 ///////////////////COMENTADO 26/10/2021 //////////////////////////
                 /*if (p200_N.equals(c4_p409) && !c4_p409_parentesco.equals("7")) {
                     mostrarMensaje("ERROR: P409: SI ME ELEGÍ COMO RESPONSABLE EL PARENTESCO DEBE SER LA OPCIÓN 7");
                     return false;
                 }*/
                 if (!p200_N.equals(c4_p409) && c4_p409_parentesco.equals("7")) {
                     mostrarMensaje("ERROR: P409: EL PARENTESCO NO COINCIDE CON LA PERSONA ELEGIDA");
                     return false;
                 }

                 if(p200_N.equals(c4_p409) && !c4_p409_parentesco.equals("0")){
                     mostrarMensaje("ERROR: P409: SI ME ELEGÍ COMO RESPONSABLE, NO DEBE SELECCIONAR PARENTESCO.");
                     return false;
                 }
             }

            if(c4_p409_2.equals("1") && edad < 12) {
                mostrarMensaje("VERIFICAR: P409: ES MENOR DE 12 AÑOS Y ES RESPONSABLE DE SU CUIDADO");
            }



           /* if (!p200_N.equals(c4_p409) && c4_p409_2.equals("1")) {
                mostrarMensaje("ERROR: P409: SI ELEGÍ A OTRA PERSONA COMO RESPONSABLE NO PUEDE MARCAR SOY RESPONSABLE DE MI CUIDAD");
                return false;
            }*/


        }else {
            c4_p409 = "";
            c4_p409_nom = "";
            c4_p409_parentesco="";
            c4_p409_2 = "";
            c4_p409_1_o = "";
        }

        if (m4_p410a_linearlayout.getVisibility()==View.VISIBLE){
            if(c4_p410a.equals("-1")){
                mostrarMensaje("PREGUNTA 410A: DEBE SELECCIONAR UNA OPCION");
                return false;
            }

        }else c4_p410a = "";

        if (m4_p410b_linearlayout.getVisibility()==View.VISIBLE){
            if(c4_p410b.equals("-1")){
                mostrarMensaje("PREGUNTA 410B: DEBE SELECCIONAR UNA OPCION");
                return false;
            }

        }else c4_p410b = "";


        if (m4_p410_linearlayout.getVisibility()==View.VISIBLE){
            if(c4_p410.equals("-1")){
                mostrarMensaje("PREGUNTA 410: DEBE SELECCIONAR UNA OPCION");
                return false;
            }

            //--- PREGUNTA COGNITIVA  p212 != null && p212.equal(1)--//
            //P212=1 AND P410=3 AND P205_A=[5:11] AND P204=2=> Mostrar siguiente Mensaje
            if ((p212.equals("1") && !(p212.equals(""))) &&
                    (c4_p410.equals("3")) && ( edad<=11 && edad>=5) && sexo==2) {
                mostrarMensaje("“SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
            }

        }else c4_p410 = "";
        if(m4_p411_linearlayout.getVisibility()==View.VISIBLE){
            if(c4_p411.equals("-1")){
                mostrarMensaje("PREGUNTA 411: DEBE SELECCIONAR UNA OPCION");
                return false;
            }
        }else c4_p411="";
        if(m4_p412_linearlayout.getVisibility()==View.VISIBLE){
            if(c4_p412.equals("-1")){
                mostrarMensaje("PREGUNTA 412: DEBE SELECCIONAR UNA OPCION");
                return false;
            }
        }else c4_p412="";

        return true;
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablamodulo4;
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


    public void limpiar_p409(){
        c4_p409_Spinner.setSelection(0);
//        c4_p409_RadioGroup.clearCheck();
    }

    public void limpiar_p410a(){
        c4_p410a_RadioGroup.clearCheck();
    }

    public void limpiar_p410B(){
        c4_p410b_RadioGroup.clearCheck();
    }

    public void limpiar_p410(){
        c4_p410_RadioGroup.clearCheck();
    }
    public void limpiar_p411(){
        c4_p411_RadioGroup.clearCheck();
    }
    public void limpiar_p412(){
        c4_p412_RadioGroup.clearCheck();
    }


    public void inicio(){
        validarFlujo43();
    }

    public boolean verificarCoberturaCapitulo(){
        /*
        Data data = new Data(context);
        data.open();
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p401p404,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp401p404,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p405p407,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp405p407,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p408p410,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp408p410,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p411p416,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp411p416,idEncuestado).equals("0")) return false;
        data.close();

         */
        return true;
    }
    //Anthony M 06/05/2021
    public void validarFlujo43(){ //FLUJO 43
        llenarVariables();
        if(validar_P409())
            m4_p409_linearlayout.setVisibility(View.VISIBLE);
        else {
            m4_p409_linearlayout.setVisibility(View.GONE);
            limpiar_p409();
        }
    }
    public void validarFlujo44(){ //FLUJO 44
        if(validar_P410_P411()){
            m4_p411_linearlayout.setVisibility(View.VISIBLE);
            m4_p412_linearlayout.setVisibility(View.VISIBLE);
        }
        else {
            limpiar_p411();
            m4_p411_linearlayout.setVisibility(View.GONE);
            limpiar_p412();
            m4_p412_linearlayout.setVisibility(View.GONE);
        }
    }
    public boolean validar_P409(){
        llenarVariables();
        boolean valido = false;
        if(c4_p408_1.equals("1") || c4_p408_2.equals("1") || c4_p408_3.equals("1") ||
                c4_p408_4.equals("1") || c4_p408_5.equals("1") || c4_p408_6.equals("1"))
            valido = true;
        return valido;
    }
    public boolean validar_P410_P411(){
        llenarVariables();
        boolean valido = false;
        //if(c4_p410.equals("3") || c4_p410.equals("4"))
        if(c4_p410.equals("1") || c4_p410.equals("2"))
            valido = true;
        return valido;
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

    public void updateHogarP20(){
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.hogar_p20,UtilsMethodsUpdates.getHogarP20(context,idHogar));
        data.actualizarElemento(SQLConstantes.tablahogares,contentValues,idHogar);
        data.close();
    }
}
