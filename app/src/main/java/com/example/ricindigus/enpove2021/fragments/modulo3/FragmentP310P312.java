package com.example.ricindigus.enpove2021.fragments.modulo3;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import android.widget.AutoCompleteTextView;
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
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Marco;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo3;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.modelo.pojos.Ubigeo;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethods;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP310P312 extends FragmentPagina {
    String idEncuestado;
    Context contexto;
    String idInformante;

    Spinner spInformante;

    Spinner c3_p310_estado_Spinner, c3_p310_municipio_Spinner;
    String c3_p310_e;
    String c3_p310_m;
    String c3_p310_e_o;
    String c3_p310_m_o;
    String c3_p310_e_seleccion;
    String c3_p310_m_seleccion;
    String p208;

    RadioGroup mod3_311_radiogroup_C3_P311;
    String c3_p311;

    RadioGroup rgp312;
    EditText edtp312Especifique;
    private String c3_p312;
    private String c3_p312_o;

    EditText text1_Editext,text2_Editext;

    LinearLayout lytp310,lytp311,lytp312;

    /*
    Spinner spInformante;
    CheckBox ckp310_1,ckp310_2,ckp310_3,ckp310_4;
    RadioGroup rgp311;
    AutoCompleteTextView autoCompleteTextView;
    TextView txtDistrito,txtProvincia,txtDepartamento;

    LinearLayout lytp310,lytp311,lytp312;
    private String c3_p310_1;
    private String c3_p310_2;
    private String c3_p310_3;
    private String c3_p310_4;
    private String c3_p311;
    private String c3_p312_dist;
    private String c3_p312_prov;
    private String c3_p312_dep;

*/

    String p212;

    String idHogar;
    String idVivienda;
    String cod_dd="", cod_pp="", cod_di="",depa="",prov="",dist="";
    private int edad=0;

    public FragmentP310P312() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentP310P312(String idEncuestado, Context contexto) {
        this.idEncuestado = idEncuestado;
        this.contexto = contexto;

        Data data = new Data(contexto);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        idHogar = residente.getId_hogar();
        idVivienda = residente.getId_vivienda();
        idInformante = "";
        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        Marco marco = data.getMarco(idVivienda);
//        Log.e("marco.getCcdd", "FragmentP310P312: "+ marco.getDepartamento());
//        Log.e("marco.getCcpp", "FragmentP310P312: "+ marco.getProvincia());
//        Log.e("marco.getCcdi", "FragmentP310P312: "+ marco.getDistrito());
        cod_dd = marco.getCcdd(); depa = marco.getDepartamento();
        cod_pp = marco.getCcpp(); prov = marco.getProvincia();
        cod_di = marco.getCcdi(); dist  = marco.getDistrito();
        p208 = residente.getC2_p208();

        p212 = residente.getC2_p212();
        data.close();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p310_p312, container, false);

        c3_p310_estado_Spinner = (Spinner) rootView.findViewById(R.id.mod3_310_spinner_C3_P310_E);
        c3_p310_municipio_Spinner = (Spinner) rootView.findViewById(R.id.mod3_310_spinner_C3_P310_M);
        mod3_311_radiogroup_C3_P311 = (RadioGroup) rootView.findViewById(R.id.mod3_311_radiogroup_C3_P311);

        rgp312 =  (RadioGroup) rootView.findViewById(R.id.mod3_312_radiogroup_C3_P312);
        edtp312Especifique = (EditText) rootView.findViewById(R.id.mod3_312_edittext_C3_P312_O);


        lytp310 =  (LinearLayout) rootView.findViewById(R.id.layout_m3_p310);
        lytp311 =  (LinearLayout) rootView.findViewById(R.id.layout_m3_p311);
        lytp312 =  (LinearLayout) rootView.findViewById(R.id.layout_m3_p312);

        text1_Editext  = (EditText) rootView.findViewById(R.id.text1_Editext);
        text2_Editext  = (EditText) rootView.findViewById(R.id.text2_Editext);

        spInformante = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);
       /*

        //ckp310_1 = (CheckBox) rootView.findViewById(R.id.mod3_310_checkbox_C3_P310_1);
        //ckp310_2 = (CheckBox) rootView.findViewById(R.id.mod3_310_checkbox_C3_P310_2);
        //ckp310_3 = (CheckBox) rootView.findViewById(R.id.mod3_310_checkbox_C3_P310_3);
        // ckp310_4 = (CheckBox) rootView.findViewById(R.id.mod3_310_checkbox_C3_P310_4);
        autoCompleteTextView = (AutoCompleteTextView) rootView.findViewById(R.id.mod3_312_autocompletetextview);
        txtDistrito = (TextView) rootView.findViewById(R.id.mod3_c312_txtDistrito);
        txtProvincia = (TextView) rootView.findViewById(R.id.mod3_c312_txtProvincia);
        txtDepartamento = (TextView) rootView.findViewById(R.id.mod3_c312_txtDepartamento);
        rgp311 =  (RadioGroup) rootView.findViewById(R.id.mod3_311_radiogroup_C3_P311);
        lytp310 =  (LinearLayout) rootView.findViewById(R.id.layout_m3_p310);
        lytp311 =  (LinearLayout) rootView.findViewById(R.id.layout_m3_p311);
        lytp312 =  (LinearLayout) rootView.findViewById(R.id.layout_m3_p312);


        */
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        configurarEditText(text1_Editext,lytp310,0,80);
        configurarEditText(text2_Editext,lytp310,0,80);

        mod3_311_radiogroup_C3_P311.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                switch (seleccionado){
                    case 1:
                        lytp312.setVisibility(View.GONE);
                        limpiar_p312();
                        break;
                    case 2:lytp312.setVisibility(View.VISIBLE);break;
                }
            }
        });

        configurarEditText(edtp312Especifique,lytp312,0,50);

        rgp312.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,3,edtp312Especifique);
            }
        });

        c3_p310_estado_Spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
        c3_p310_estado_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    blockigP310Especifique();
                    c3_p310_municipio_Spinner.setAdapter(null);
                }else if(i>0 && i<25){
                    blockigP310Especifique();
                    String codEstado = UtilsMethods.getDosDigitos(c3_p310_estado_Spinner.getSelectedItem().toString());
                    UtilsMethodsInputs.loadSpinner(getMunicipios(codEstado),c3_p310_municipio_Spinner,contexto);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
                }
                return false;
            }
        });

        c3_p310_municipio_Spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    c3_p310_municipio_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            Log.e("tobi",""+i+"/"+c3_p310_municipio_Spinner.getAdapter().getCount());
                            if(i==c3_p310_municipio_Spinner.getAdapter().getCount()-1){
                                unblockigP310Especifique();
                            }else{
                                blockigP310Especifique();
                            }
//                            if(i == 0){
//                                blockigP310Especifique();
//                                c3_p310_municipio_Spinner.setAdapter(null);
//                            }else if(i>0 && i<25){
//                                blockigP310Especifique();
//                                String codEstado = UtilsMethods.getDosDigitos(c3_p310_estado_Spinner.getSelectedItem().toString());
//                                UtilsMethodsInputs.loadSpinner(getMunicipios(codEstado),c3_p310_municipio_Spinner,contexto);
//                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {}
                    });
                }
                return false;
            }
        });



        llenarVista();
        cargarDatos();

    }

    private void limpiar_p312() {
        rgp312.clearCheck();
        edtp312Especifique.setText("");
    }


    public void cargarSpinerMunicipios(ArrayList<String> datos){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(contexto, android.R.layout.simple_spinner_item,datos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        c3_p310_municipio_Spinner.setAdapter(adapter);
    }

    public String checkDigito (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }




    @Override
    public void guardarDatos() {

        Data data = new Data(contexto);
        data.open();
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p313p317,"-1",idEncuestado); //SALTA UN FRAGMENT de la pregunta 313
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo3_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo3_c3_p310_e,c3_p310_e);
        contentValues.put(SQLConstantes.modulo3_c3_p310_m,c3_p310_m);
        contentValues.put(SQLConstantes.modulo3_c3_p310_e_o,c3_p310_e_o);
        contentValues.put(SQLConstantes.modulo3_c3_p310_m_o,c3_p310_m_o);
        contentValues.put(SQLConstantes.modulo3_c3_p310_e_seleccion,c3_p310_e_seleccion);
        contentValues.put(SQLConstantes.modulo3_c3_p310_m_seleccion,c3_p310_m_seleccion);
        contentValues.put(SQLConstantes.modulo3_c3_p311,c3_p311);
        contentValues.put(SQLConstantes.modulo3_c3_p312,c3_p312+"");
        contentValues.put(SQLConstantes.modulo3_c3_p312_o,c3_p312_o);


        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp310p312,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo3_COB300,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo3_COB300,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        ocultarOtrosLayouts();
        data.close();

        /*
        Data data = new Data(contexto);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo3_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo3_c3_p310_1,c3_p310_1);
        contentValues.put(SQLConstantes.modulo3_c3_p310_2,c3_p310_2);
        contentValues.put(SQLConstantes.modulo3_c3_p310_3,c3_p310_3);
        contentValues.put(SQLConstantes.modulo3_c3_p310_4,c3_p310_4);
        contentValues.put(SQLConstantes.modulo3_c3_p311,c3_p311);
        contentValues.put(SQLConstantes.modulo3_c3_p312_dist,c3_p312_dist);
        contentValues.put(SQLConstantes.modulo3_c3_p312_prov,c3_p312_prov);
        contentValues.put(SQLConstantes.modulo3_c3_p312_dep,c3_p312_dep);
        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp310p312,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo3_COB300,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo3_COB300,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();

         */



    }

    private void ocultarOtrosLayouts() {
        llenarVariables();
        if( p208.equals("1") &&  edad <12){
            ocultarFragmentP318(true); //Pasa al fragment de la P413
        }
    }

    @Override
    public void llenarVariables() {

        idInformante = obtener_Nresidente(spInformante);
//        if (c3_p310_estado_Spinner.getSelectedItemPosition() > 0) c3_p310_e  = getCodigoEstMun(c3_p310_estado_Spinner.getSelectedItem().toString());
//        if (c3_p310_municipio_Spinner.getSelectedItemPosition() > 0) c3_p310_m  = getCodigoEstMun(c3_p310_municipio_Spinner.getSelectedItem().toString());
//
//        if(c3_p310_e != null && c3_p310_e.equals("9999")){
//            c3_p310_e = text1_Editext.getText()+"";
//            c3_p310_m = text2_Editext.getText()+"";
//        }

        if(UtilsMethods.getDosDigitos(c3_p310_estado_Spinner.getSelectedItem()+"").equals("99")){
            c3_p310_e = "99";
            c3_p310_e_o = text1_Editext.getText()+"";
        }else{
            c3_p310_e  = UtilsMethods.getDosDigitos(c3_p310_estado_Spinner.getSelectedItem()+"");
            try{
                c3_p310_e_o = DAOUtils.getEstado(c3_p310_e,contexto).getNombre();
            }catch (Exception e){
                c3_p310_e_o = "0";
            }
        }

        if(UtilsMethods.getDosDigitos(c3_p310_municipio_Spinner.getSelectedItem()+"").equals("99")){
            c3_p310_m = "99";
            c3_p310_m_o= text2_Editext.getText()+"";
        }else{
            c3_p310_m  = UtilsMethods.getDosDigitos(c3_p310_municipio_Spinner.getSelectedItem()+"");
            try{
                c3_p310_m_o = DAOUtils.getMunicipio(c3_p310_e+""+c3_p310_m,contexto).getNom_municipio();
            }catch (Exception e){
                c3_p310_m_o = "0";
            }
        }

        c3_p311 = mod3_311_radiogroup_C3_P311.indexOfChild(mod3_311_radiogroup_C3_P311.findViewById(mod3_311_radiogroup_C3_P311.getCheckedRadioButtonId())) +"";

        c3_p312 = rgp312.indexOfChild(rgp312.findViewById(rgp312.getCheckedRadioButtonId()))+"";
        c3_p312_o = edtp312Especifique.getText().toString().trim();


    }

    public String getCodigoEstMun(String item){
        return item.substring(0,item.indexOf('.'));
    }


    @Override
    public void cargarDatos() {
        Data data = new Data(contexto);
        data.open();
        if (data.existeElemento(getNombreTabla(),idEncuestado)){

            Modulo3 modulo3 = data.getModulo3(idEncuestado);
            ArrayList<String> informantes = data.getListaInformantesMayor12(idHogar);
            UtilsMethodsInputs.loadSpinner(informantes,spInformante,contexto);
            if(!modulo3.getIdInformante().equals("")) spInformante.setSelection(obtener_posicion(modulo3.getIdInformante(),spInformante));

            //P310
            ArrayList<String> municipioss = data.getListaEstados();
            UtilsMethodsInputs.loadSpinner(municipioss,c3_p310_estado_Spinner,contexto);

            if(modulo3.getC3_p310_e() !=null && !modulo3.getC3_p310_e().equals("")){
                int p = Integer.parseInt(modulo3.getC3_p310_e());
                if(p <= 25) {
                    UtilsMethodsInputs.loadSpinner(getMunicipios(modulo3.getC3_p310_e()),c3_p310_municipio_Spinner,contexto);
                    c3_p310_estado_Spinner.setSelection(p);
                }
//                else {
//                    UtilsMethodsInputs.loadSpinner(getMunicipios("99"),c3_p310_municipio_Spinner,contexto);
//                    c3_p310_estado_Spinner.setSelection(25);
//                    text1_Editext.setText(modulo3.getC3_p310_e_o());
//                    text1_Editext.setEnabled(true);
//                    text2_Editext.setEnabled(true);
//                    text1_Editext.setBackgroundResource(R.drawable.input_text_enabled);
//                    text2_Editext.setBackgroundResource(R.drawable.input_text_enabled);
//                    //String codEstado = UtilsMethods.getDosDigitos(c3_p310_estado_Spinner.getSelectedItem().toString());
//
//                }
            }
            if(modulo3.getC3_p310_m() !=null && !modulo3.getC3_p310_m().equals("")){
                int p = Integer.parseInt(modulo3.getC3_p310_m());
                if(p < 99) {
                    c3_p310_municipio_Spinner.setSelection(p);}
                else {
                    c3_p310_municipio_Spinner.setSelection(c3_p310_municipio_Spinner.getAdapter().getCount()-1);
                    text2_Editext.setText(modulo3.getC3_p310_m_o());
                    text2_Editext.setEnabled(true);
                    text2_Editext.setBackgroundResource(R.drawable.input_text_enabled);
                }
            }

            //P311
            if(!modulo3.getC3_p311().equals("-1") && !modulo3.getC3_p311().equals(""))((RadioButton)mod3_311_radiogroup_C3_P311.getChildAt(Integer.parseInt(modulo3.getC3_p311()))).setChecked(true);

            if(!modulo3.getC3_p312().equals("-1") && !modulo3.getC3_p312().equals(""))((RadioButton)rgp312.getChildAt(Integer.parseInt(modulo3.getC3_p312()))).setChecked(true);
            edtp312Especifique.setText(modulo3.getC3_p312_o());

//            if (modulo3.getC3_p310_e() != null && !modulo3.getC3_p310_e().equals("")){
//                int ultimo_item_spinner_estado = c3_p310_estado_Spinner.getAdapter().getCount()-1;
//                try{
//                    int v=0;
//                    v = Integer.parseInt(modulo3.getC3_p310_e());
//                    if(v > 0 && v < ultimo_item_spinner_estado){
//                        c3_p310_estado_Spinner.setSelection(v);
//                        Log.e("uff","setearia seleccion = "+v);
//                        ArrayList<String> municipios = new ArrayList<>();
//                        municipios = data.getMunicipios(data.getCodEstado(v+""));
//                        cargarSpinerMunicipios(municipios);
//                        for(int i=1;i < c3_p310_municipio_Spinner.getAdapter().getCount();i++){
//                            String item_pos = c3_p310_municipio_Spinner.getItemAtPosition(i)+"";
//                            if(modulo3.getC3_p310_m().equals(getCodigoEstMun(item_pos))){
//                                c3_p310_municipio_Spinner.setSelection(i);
//                            }
//                        }
////                        c3_p310_municipio_Spinner.setSelection(Integer.parseInt(modulo3.getC3_p310_m()));
//                    }
//                    else {
//                        Log.e("uff", "setearia seleccion 9999 :1 ");
//                        c3_p310_estado_Spinner.setSelection(ultimo_item_spinner_estado);
//                        ArrayList<String> municipios = new ArrayList<>();
//                        municipios = data.getMunicipios(data.getCodEstado(ultimo_item_spinner_estado+""));
//                        cargarSpinerMunicipios(municipios);
//                        c3_p310_municipio_Spinner.setSelection(c3_p310_municipio_Spinner.getAdapter().getCount()-1);
//                        text1_Editext.setEnabled(true);text1_Editext.setBackgroundResource(R.drawable.input_text_enabled);
//                        text1_Editext.setText(modulo3.getC3_p310_e());
//                        text2_Editext.setEnabled(true);text2_Editext.setBackgroundResource(R.drawable.input_text_enabled);
//                        text2_Editext.setText(modulo3.getC3_p310_m());
//                    }
//                }catch (Exception e){
//                    Log.e("uff","setearia seleccion 9999 :2 ");
//                    c3_p310_estado_Spinner.setSelection(ultimo_item_spinner_estado);
//                    ArrayList<String> municipios = new ArrayList<>();
//                    municipios = data.getMunicipios(data.getCodEstado(ultimo_item_spinner_estado+""));
//                    cargarSpinerMunicipios(municipios);
//                    c3_p310_municipio_Spinner.setSelection(c3_p310_municipio_Spinner.getAdapter().getCount()-1);
//                    text1_Editext.setEnabled(true);text1_Editext.setBackgroundResource(R.drawable.input_text_enabled);
//                    text1_Editext.setText(modulo3.getC3_p310_e());
//                    text2_Editext.setEnabled(true);text2_Editext.setBackgroundResource(R.drawable.input_text_enabled);
//                    text2_Editext.setText(modulo3.getC3_p310_m());
//                }
//            }
        }
        data.close();

    }

    @Override
    public void llenarVista() {
        /*
        Data data = new Data(contexto);
        data.open();
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p310,idEncuestado)) lytp310.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p311,idEncuestado)) lytp311.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p312,idEncuestado)) lytp312.setVisibility(View.GONE);
        data.close();

         */
    }

    @Override
    public boolean validarDatos() {
        llenarVariables();
        if(spInformante.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        //PREGUNTA 310
        if (lytp310.getVisibility() == View.VISIBLE){
            if (c3_p310_estado_Spinner.getSelectedItemPosition() == 0){mostrarMensaje("PREGUNTA 310: DEBE SELECCIONAR UNA OPCION DE ESTADO");return false;}
            if (text1_Editext.isEnabled() && (c3_p310_e_o.equals("") || c3_p310_e_o.length()<3) ) {
                mostrarMensaje("ERROR: P310 (ESTADO) EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;
            }
            if (c3_p310_municipio_Spinner.getSelectedItemPosition() == 0){mostrarMensaje("PREGUNTA 310: DEBE SELECCIONAR UNA OPCION DE MUNICIPIO");return false;}
            if (text2_Editext.isEnabled() && (c3_p310_m_o.equals("") || c3_p310_m_o.length()<3)){
                mostrarMensaje("ERROR: P310 (MUNICIPIO) EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;
            }

//            //--- PREGUNTA COGNITIVA  p212 != null && p212.equal(1)--//
////            UNIVERSO		: Todas las personas AND P212=1
////            VERIFICACIÓN	: Sí P310_M<>VACIO Entonces mostrar mensaje:
//
//            if ( (p212.equals("1") && !(p212.equals(""))) &&  (c3_p310_municipio_Spinner.getSelectedItemPosition() !=0 && !(c3_p310_m.equals("")))) {
//                mostrarMensaje("“SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
//            }

            //Este falta creo


        }else{
            c3_p310_e_seleccion ="";
            c3_p310_m_seleccion="";
        }


        //PREGUNTA 311
        if (lytp311.getVisibility() == View.VISIBLE){
            if (c3_p311.equals("-1")){mostrarMensaje("PREGUNTA 311: DEBE MARCAR UNA OPCIÓN"); return false;}
        }else{
            c3_p311 = "";
        }

        //PREGUNTA 312
        if (lytp312.getVisibility() == View.VISIBLE){
            if (c3_p312.equals("-1")){mostrarMensaje("PREGUNTA 312: DEBE MARCAR UNA OPCIÓN"); return false;}
            if (c3_p312.equals("3")){
                if (c3_p312_o.trim().equals("")){mostrarMensaje("PREGUNTA 312: DEBE ESPECIFICAR");return false;}
                if (c3_p312_o.length()<3){
                    mostrarMensaje("ERROR  “P312. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }
            }



        }else{
            c3_p312 = "";
            c3_p312_o = "";
        }


        /*
        if (lytp314.getVisibility() == View.VISIBLE){
            if (c3_p314.equals("-1")){mostrarMensaje("PREGUNTA 314: DEBE MARCAR UNA OPCIÓN"); return false;}
            if (c3_p314.equals("3")){
                if (c3_p314_o.trim().equals("")){mostrarMensaje("PREGUNTA 314: DEBE ESPECIFICAR");return false;}
            }
        }else{
            c3_p314 = "";
            c3_p314_o = "";
        }


        llenarVariables();
        if(spInformante.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}
        if (lytp310.getVisibility() == View.VISIBLE){
            if (c3_p310_1.equals("0") && c3_p310_2.equals("0") && c3_p310_3.equals("0")
                    && c3_p310_4.equals("0")) {mostrarMensaje("PREGUNTA 310: DEBE MARCAR AL MENOS UNA OPCION");return false;}
            if (c3_p310_3.equals("1") && edad<12){
                mostrarMensaje("PREGUNTA 310: UN MENOR DE 12 AÑOS NO PUEDE VIAJAR SOLO");return false;
            }
        }else{
            c3_p310_1 = "";
            c3_p310_2 = "";
            c3_p310_3 = "";
            c3_p310_4 = "";
        }
        if (c3_p311.equals("-1")){mostrarMensaje("PREGUNTA 311: DEBE MARCAR UNA OPCIÓN"); return false;}
        if(lytp312.getVisibility() == View.VISIBLE){
            if (txtDepartamento.getText().toString().equals("")){mostrarMensaje("PREGUNTA 312: DEBE INDICAR EL UBIGEO"); return false;}
            if(c3_p312_dep.equals(cod_dd) && c3_p312_prov.equals(cod_pp) && c3_p312_dist.equals(cod_di)){
                mostrarMensaje("PREGUNTA 312: DEBE SER DIFERENTE A SU UBICACIÓN ACTUAL, DISTRITO/PROVINCIA/DEPARTAMENTO ("+dist+"/"+prov+"/"+depa+")"); return false;
            }
        }else{
            c3_p312_dist = "";
            c3_p312_prov = "";
            c3_p312_dep = "";
        }

         */
        return true;
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablamodulo3;
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

    private void ocultarFragmentP318(boolean ocultar) {

        Data data1 = new Data(contexto);
        data1.open();
        if(ocultar){
            ContentValues contentValues1 = new ContentValues();

            contentValues1.put(SQLConstantes.modulo3_c3_p313,"");

            data1.actualizarElemento(getNombreTabla(),contentValues1,idEncuestado);
            data1.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p318,"-1",idEncuestado);
            data1.close();
        }else{
            data1.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p318,"1",idEncuestado);
        }

    }

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public void mostrarMensaje(String m){
        final AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
        builder.setMessage(m);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
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

    public boolean verificarCoberturaCapitulo(){
        /*
        Data data = new Data(contexto);
        data.open();
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p301p305,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp301p305,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p306p308,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp306p308,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p309,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp309,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p310p312,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp310p312,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p313p317,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp313p317,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p318,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp318,idEncuestado).equals("0")) return false;
        data.close();

         */
        return true;
    }

    public ArrayList<String> getMunicipios(String codEstado){
        Data data = new Data(contexto);
        data.open();
        ArrayList<String> municipios = data.getListaMunicipios(codEstado);
        municipios.add("99-Otro Municipio");
        data.close();
        return municipios;
    }

    public ArrayList<String> getEstados(){
        Data data = new Data(contexto);
        data.open();
        ArrayList<String> municipios = data.getListaEstados();
        data.close();
        return municipios;
    }

    public void blockigP310Especifique(){
        text1_Editext.setText("");
        text2_Editext.setText("");
        text1_Editext.setEnabled(false);
        text2_Editext.setEnabled(false);
        text1_Editext.setBackgroundResource(R.drawable.input_text_disabled);
        text2_Editext.setBackgroundResource(R.drawable.input_text_disabled);
    }

    public void unblockigP310Especifique(){
        text1_Editext.setText("");
        text2_Editext.setText("");
        text1_Editext.setEnabled(true);
        text2_Editext.setEnabled(true);
        text1_Editext.setBackgroundResource(R.drawable.input_text_enabled);
        text2_Editext.setBackgroundResource(R.drawable.input_text_enabled);
    }


    public void loadSpinner(){
        String codEstado = UtilsMethods.getDosDigitos(c3_p310_estado_Spinner.getSelectedItem().toString());
        UtilsMethodsInputs.loadSpinner(getMunicipios(codEstado),c3_p310_municipio_Spinner,contexto);
    }

}
