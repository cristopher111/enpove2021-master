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

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo3;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP313P317 extends FragmentPagina {
    String idEncuestado;
    Context contexto;
    String idInformante;
    Spinner spInformante;


    RadioGroup rgp313,rgp314,rgp316,rgp317;
    CheckBox ck1p315,ck2p315,ck3p315,ck4p315,ck5p315,ck6p315,ck7p315,ck8p315,ck9p315,ck10p315;
    EditText edtp314Especifique,edtp315Especifique,edtp316Especifique;
    LinearLayout lytp313,lytp314,lytp315,lytp316,lytp317;

    private int c3_p313;
    private String c3_p314;
    private String c3_p314_o;
    private String c3_p315_1;
    private String c3_p315_2;
    private String c3_p315_3;
    private String c3_p315_4;
    private String c3_p315_5;
    private String c3_p315_6;
    private String c3_p315_7;
    private String c3_p315_8;
    private String c3_p315_9;
    private String c3_p315_10;
    private String c3_p315_10_o;
    private int c3_p316;
    private String c3_p316_o;
    private int c3_p317;

    private int edad=0;



    public FragmentP313P317() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentP313P317(String idEncuestado, Context contexto) {
        this.idEncuestado = idEncuestado;
        this.contexto = contexto;

        Data data = new Data(contexto);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        if(residente.getC2_p205_a().equals("")) edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        data.close();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p313_p317, container, false);

        spInformante = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);
 /*
        rgp313 =  (RadioGroup) rootView.findViewById(R.id.mod3_313_radiogroup_C3_P313);
        rgp314 =  (RadioGroup) rootView.findViewById(R.id.mod3_314_radiogroup_C3_P314);
        rgp316 =  (RadioGroup) rootView.findViewById(R.id.mod3_316_radiogroup_C3_P316);
        rgp317 =  (RadioGroup) rootView.findViewById(R.id.mod3_317_radiogroup_C3_P317);

        ck1p315 = (CheckBox) rootView.findViewById(R.id.mod3_315_checkbox_C3_P315_1);
        ck2p315 = (CheckBox) rootView.findViewById(R.id.mod3_315_checkbox_C3_P315_2);
        ck3p315 = (CheckBox) rootView.findViewById(R.id.mod3_315_checkbox_C3_P315_3);
        ck4p315 = (CheckBox) rootView.findViewById(R.id.mod3_315_checkbox_C3_P315_4);
        ck5p315 = (CheckBox) rootView.findViewById(R.id.mod3_315_checkbox_C3_P315_5);
        ck6p315 = (CheckBox) rootView.findViewById(R.id.mod3_315_checkbox_C3_P315_6);
        ck7p315 = (CheckBox) rootView.findViewById(R.id.mod3_315_checkbox_C3_P315_7);
        ck8p315 = (CheckBox) rootView.findViewById(R.id.mod3_315_checkbox_C3_P315_8);
        ck9p315 = (CheckBox) rootView.findViewById(R.id.mod3_315_checkbox_C3_P315_9);
        ck10p315 = (CheckBox) rootView.findViewById(R.id.mod3_315_checkbox_C3_P315_10);


        lytp314 =  (LinearLayout) rootView.findViewById(R.id.layout_m3_p314);
        lytp315 =  (LinearLayout) rootView.findViewById(R.id.layout_m3_p315);
        lytp316 =  (LinearLayout) rootView.findViewById(R.id.layout_m3_p316);
        lytp317 =  (LinearLayout) rootView.findViewById(R.id.layout_m3_p317);

        edtp314Especifique = (EditText) rootView.findViewById(R.id.mod3_314_edittext_C3_P314_O);
        edtp315Especifique = (EditText) rootView.findViewById(R.id.mod3_315_edittext_C3_P315_O);
        edtp316Especifique = (EditText) rootView.findViewById(R.id.mod3_316_edittext_C3_P316_O);


         */
        lytp313 =  (LinearLayout) rootView.findViewById(R.id.layout_m3_p313);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       /*
        configurarEditText(edtp314Especifique,lytp314,0,30);
        configurarEditText(edtp315Especifique,lytp315,0,30);
        configurarEditText(edtp316Especifique,lytp316,0,30);

        rgp313.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                switch (seleccionado){
                    case 1:rgp314.clearCheck();edtp314Especifique.setText("");lytp314.setVisibility(View.GONE);break;
                    case 2: lytp314.setVisibility(View.VISIBLE);break;
                }
            }
        });

        rgp314.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,3,edtp314Especifique);
            }
        });

        rgp316.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,5,edtp316Especifique);
            }
        });



        controlarChecked(ck10p315,edtp315Especifique);


        */

        llenarVista();
        cargarDatos();
    }

    @Override
    public void guardarDatos() {
        /*
        Data data = new Data(contexto);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo3_id_informante,idInformante+"");
        contentValues.put(SQLConstantes.modulo3_c3_p313,c3_p313+"");
        contentValues.put(SQLConstantes.modulo3_c3_p314,c3_p314+"");
        contentValues.put(SQLConstantes.modulo3_c3_p314_o,c3_p314_o);
        contentValues.put(SQLConstantes.modulo3_c3_p315_1,c3_p315_1);
        contentValues.put(SQLConstantes.modulo3_c3_p315_2,c3_p315_2);
        contentValues.put(SQLConstantes.modulo3_c3_p315_3,c3_p315_3);
        contentValues.put(SQLConstantes.modulo3_c3_p315_4,c3_p315_4);
        contentValues.put(SQLConstantes.modulo3_c3_p315_5,c3_p315_5);
        contentValues.put(SQLConstantes.modulo3_c3_p315_6,c3_p315_6);
        contentValues.put(SQLConstantes.modulo3_c3_p315_7,c3_p315_7);

        contentValues.put(SQLConstantes.modulo3_c3_p315_8,c3_p315_8);
        contentValues.put(SQLConstantes.modulo3_c3_p315_9,c3_p315_9);
        contentValues.put(SQLConstantes.modulo3_c3_p315_10,c3_p315_10);
        contentValues.put(SQLConstantes.modulo3_c3_p315_10_o,c3_p315_10_o);

        contentValues.put(SQLConstantes.modulo3_c3_p316,c3_p316+"");
        contentValues.put(SQLConstantes.modulo3_c3_p316_o,c3_p316_o);
        contentValues.put(SQLConstantes.modulo3_c3_p317,c3_p317+"");
        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp313p317,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo3_COB300,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo3_COB300,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();

         */
    }

    @Override
    public void llenarVariables() {
        /*
        idInformante = spInformante.getSelectedItemPosition() + "";
        c3_p313  = rgp313.indexOfChild(rgp313.findViewById(rgp313.getCheckedRadioButtonId()));
        c3_p314 = rgp314.indexOfChild(rgp314.findViewById(rgp314.getCheckedRadioButtonId()))+"";
        c3_p314_o = edtp314Especifique.getText().toString().trim();
        if (ck1p315.isChecked())c3_p315_1 = "1";else c3_p315_1 = "0";
        if (ck2p315.isChecked())c3_p315_2 = "1";else c3_p315_2 = "0";
        if (ck3p315.isChecked())c3_p315_3 = "1";else c3_p315_3 = "0";
        if (ck4p315.isChecked())c3_p315_4 = "1";else c3_p315_4 = "0";
        if (ck5p315.isChecked())c3_p315_5 = "1";else c3_p315_5 = "0";
        if (ck6p315.isChecked())c3_p315_6 = "1";else c3_p315_6 = "0";
        if (ck7p315.isChecked())c3_p315_7 = "1";else c3_p315_7 = "0";

        if (ck8p315.isChecked())c3_p315_8 = "1";else c3_p315_8 = "0";
        if (ck9p315.isChecked())c3_p315_9 = "1";else c3_p315_9 = "0";
        if (ck10p315.isChecked())c3_p315_10 = "1";else c3_p315_10 = "0";
        c3_p315_10_o = edtp315Especifique.getText().toString().trim();
        c3_p316 = rgp316.indexOfChild(rgp316.findViewById(rgp316.getCheckedRadioButtonId()));
        c3_p316_o = edtp316Especifique.getText().toString().trim();
        c3_p317 = rgp317.indexOfChild(rgp317.findViewById(rgp317.getCheckedRadioButtonId()));

         */

    }

    @Override
    public void cargarDatos() {
        /*
        Data data = new Data(contexto);
        data.open();
        if (data.existeElemento(getNombreTabla(),idEncuestado)){
            Modulo3 modulo3 = data.getModulo3(idEncuestado);
            ArrayList<String> residentes = data.getListaSpinnerResidentesHogar(modulo3.getIdHogar());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(contexto, android.R.layout.simple_spinner_item,residentes);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spInformante.setAdapter(adapter);
            spInformante.setSelection(Integer.parseInt(modulo3.getIdInformante()));
            if(!modulo3.getC3_p313().equals("-1") && !modulo3.getC3_p313().equals(""))
                ((RadioButton)rgp313.getChildAt(Integer.parseInt(modulo3.getC3_p313()))).setChecked(true);
            if(!modulo3.getC3_p314().equals("-1") && !modulo3.getC3_p314().equals(""))
                ((RadioButton)rgp314.getChildAt(Integer.parseInt(modulo3.getC3_p314()))).setChecked(true);
            edtp314Especifique.setText(modulo3.getC3_p314_o());
            if(modulo3.getC3_p315_1().equals("1")) ck1p315.setChecked(true);
            if(modulo3.getC3_p315_2().equals("1")) ck2p315.setChecked(true);
            if(modulo3.getC3_p315_3().equals("1")) ck3p315.setChecked(true);
            if(modulo3.getC3_p315_4().equals("1")) ck4p315.setChecked(true);
            if(modulo3.getC3_p315_5().equals("1")) ck5p315.setChecked(true);
            if(modulo3.getC3_p315_6().equals("1")) ck6p315.setChecked(true);
            if(modulo3.getC3_p315_7().equals("1")) ck7p315.setChecked(true);

            if(modulo3.getC3_p315_8().equals("1")) ck8p315.setChecked(true);
            if(modulo3.getC3_p315_9().equals("1")) ck9p315.setChecked(true);
            if(modulo3.getC3_p315_10().equals("1")) ck10p315.setChecked(true);

            edtp315Especifique.setText(modulo3.getC3_p315_10_o());
            if(!modulo3.getC3_p316().equals("-1") && !modulo3.getC3_p316().equals(""))
                ((RadioButton)rgp316.getChildAt(Integer.parseInt(modulo3.getC3_p316()))).setChecked(true);
            if(!modulo3.getC3_p317().equals("-1") && !modulo3.getC3_p317().equals(""))
                ((RadioButton)rgp317.getChildAt(Integer.parseInt(modulo3.getC3_p317()))).setChecked(true);
            edtp316Especifique.setText(modulo3.getC3_p316_o());
        }
        data.close();

         */
    }

    @Override
    public void llenarVista() {
        //TODO COMENTADO
       /*
        Data data = new Data(contexto);
        data.open();
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p313,idEncuestado)) lytp313.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p314,idEncuestado)) lytp314.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p315,idEncuestado)) lytp315.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p316,idEncuestado)) lytp316.setVisibility(View.GONE);
        data.close();*/


    }

    @Override
    public boolean validarDatos() {
        /*
        llenarVariables();
        if(spInformante.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}
        if (c3_p313 == -1){mostrarMensaje("PREGUNTA 314: DEBE MARCAR UNA OPCIÓN"); return false;}

        if (lytp314.getVisibility() == View.VISIBLE){
            if (c3_p314.equals("-1")){mostrarMensaje("PREGUNTA 314: DEBE MARCAR UNA OPCIÓN"); return false;}
            if (c3_p314.equals("3")){
                if (c3_p314_o.trim().equals("")){mostrarMensaje("PREGUNTA 314: DEBE ESPECIFICAR");return false;}
            }
        }else{
            c3_p314 = "";
            c3_p314_o = "";
        }

        if (c3_p315_1.equals("0") && c3_p315_2.equals("0") && c3_p315_3.equals("0")
                && c3_p315_4.equals("0") && c3_p315_5.equals("0")&& c3_p315_6.equals("0")
                && c3_p315_7.equals("0")&& c3_p315_8.equals("0") && c3_p315_9.equals("0")
                && c3_p315_10.equals("0")) {mostrarMensaje("PREGUNTA 315: DEBE MARCAR AL MENOS UNA OPCION");return false;}
        if (c3_p315_10.equals("1") && c3_p315_10_o.trim().equals("")){
            mostrarMensaje("PREGUNTA 315: DEBE ESPECIFICAR LA RESPUESTA");return false;
        }
        if (c3_p315_10.equals("1") && c3_p315_10_o.trim().equals("CARTA ANDINA")){
            mostrarMensaje("PREGUNTA 315: EL ESPECIFICAR NO PUEDE SER (CARTA ANDINA)");return false;
        }
        if (c3_p316 == -1){mostrarMensaje("PREGUNTA 316: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c3_p316==4 && edad<18){mostrarMensaje("PREGUNTA 316: DEBE MARCAR OTRA OPCIÓN PORQUE ES MENOR DE EDDAD"); return false;}
        if (c3_p316 == 5){
            if (c3_p316_o.trim().equals("")){mostrarMensaje("PREGUNTA 316: DEBE ESPECIFICAR");return false;}
        }
        if (c3_p317 == -1){mostrarMensaje("PREGUNTA 317: DEBE MARCAR UNA OPCIÓN"); return false;}
        if(c3_p316 == 4 && c3_p317!=1){
            mostrarMensaje("PREGUNTA 317: DEBE SELECCIONAR LA OPCIÓN(1.Duerme solo en la habitación)"); return false;
        }

         */
        return true;
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablamodulo3;
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
}
