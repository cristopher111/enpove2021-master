package com.example.ricindigus.enpove2021.fragments.modulo3;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.activities.agregacion.AgregarRutaActivity;
import com.example.ricindigus.enpove2021.adapters.M3Pregunta309Adapter;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.M3Pregunta309;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo3;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP309 extends FragmentPagina {
    String idEncuestado;
    String idInformante;
    String idVivienda;
    String idHogar;
    Context contexto;

    Spinner informanteSpinner;
    RadioGroup mod3_309_radiogroup_C3_P309,mod3_309_1_radiogroup_C3_P309;
    EditText mod3_309_edittext_C3_P309_O,mod3_309_1_edittext_C3_P309_O;
    LinearLayout layoutp309, layoutp309_1;

    String c3_p309;
    String c3_p309_o;
    String c3_p309_1;
    String c3_p309_1_o;
    /*
    LinearLayout layoutp309;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    M3Pregunta309Adapter m3Pregunta309Adapter;
    ArrayList<M3Pregunta309> m3Pregunta309s;
    Spinner informanteSpinner;

     */

    public FragmentP309() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentP309(String idEncuestado, String idVivienda,Context contexto) {
        this.idEncuestado = idEncuestado;
        this.idVivienda = idVivienda;
        this.contexto = contexto;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_p309, container, false);
        // fab = (FloatingActionButton) rootView.findViewById(R.id.rutas_fab);
        //  recyclerView = (RecyclerView) rootView.findViewById(R.id.rutas_recyclerview);
        /*
        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);
        layoutp309 = (LinearLayout) rootView.findViewById(R.id.layout_m3_p309);
         */
        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);
        mod3_309_radiogroup_C3_P309 = (RadioGroup) rootView.findViewById(R.id.mod3_309_radiogroup_C3_P309);
        mod3_309_edittext_C3_P309_O = (EditText)   rootView.findViewById(R.id.mod3_309_edittext_C3_P309_O);
        mod3_309_1_radiogroup_C3_P309 = (RadioGroup)   rootView.findViewById(R.id.mod3_309_1_radiogroup_C3_P309);
        mod3_309_1_edittext_C3_P309_O = (EditText)   rootView.findViewById(R.id.mod3_309_1_edittext_C3_P309_O);
        layoutp309 = (LinearLayout) rootView.findViewById(R.id.layout_m3_p309);
        layoutp309_1 = (LinearLayout) rootView.findViewById(R.id.layout_m3_p309_1);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(contexto);
//        recyclerView.setLayoutManager(layoutManager);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =  new Intent(contexto, AgregarRutaActivity.class);
//                intent.putExtra("idEncuestado",idEncuestado);
//                intent.putExtra("idVivienda",idVivienda);
//                intent.putExtra("numero",(m3Pregunta309s.size()+1)+"");
//                intent.putExtra("idRuta",idEncuestado + "_" +(m3Pregunta309s.size()+1));
//                startActivity(intent);
//            }
//        });
//        llenarVista();
//        cargarDatos();



        configurarEditText(mod3_309_edittext_C3_P309_O,layoutp309,0,50);
        configurarEditText(mod3_309_1_edittext_C3_P309_O,layoutp309_1,0,50);

        mod3_309_radiogroup_C3_P309.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                switch (seleccionado){
                    case 1:
                        layoutp309_1.setVisibility(View.GONE);
                        limpiar_p312();
                        break;
                    case 2:
                        layoutp309_1.setVisibility(View.GONE);
                        limpiar_p312();
                        break;
                    case 3:
                        layoutp309_1.setVisibility(View.GONE);
                        limpiar_p312();
                        break;
                    case 4:
                        layoutp309_1.setVisibility(View.GONE);
                        limpiar_p312();
                        break;
                    case 5:
                        layoutp309_1.setVisibility(View.GONE);
                        limpiar_p312();
                        break;
                    case 6:
                        layoutp309_1.setVisibility(View.GONE);
                        limpiar_p312();
                        break;
                    case 7:
                        layoutp309_1.setVisibility(View.GONE);
                        limpiar_p312();
                        break;
                    case 8:
                        layoutp309_1.setVisibility(View.VISIBLE);break;
                    case 9:
                        layoutp309_1.setVisibility(View.GONE);
                        limpiar_p312();
                        break;
                }
                controlarEspecifiqueRadio(group, checkedId,9,mod3_309_edittext_C3_P309_O);
            }
        });

        mod3_309_1_radiogroup_C3_P309.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,3,mod3_309_1_edittext_C3_P309_O);
            }
        });

        llenarVista();
        cargarDatos();
    }

    private void limpiar_p312(){
        mod3_309_1_radiogroup_C3_P309.clearCheck();
        mod3_309_1_edittext_C3_P309_O.setText("");
    }

    @Override
    public void guardarDatos() {
        /*
        Data data = new Data(contexto);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo3_id_informante,idInformante);
        data.actualizarElemento(SQLConstantes.tablamodulo3,contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp309,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla_(),SQLConstantes.modulo3_COB300,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla_(),SQLConstantes.modulo3_COB300,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();
         */

        Data data = new Data(contexto);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo3_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo3_c3_p309,c3_p309);
        contentValues.put(SQLConstantes.modulo3_c3_p309_o,c3_p309_o);
        contentValues.put(SQLConstantes.modulo3_c3_p309_1,c3_p309_1);
        contentValues.put(SQLConstantes.modulo3_c3_p309_1_o,c3_p309_1_o);
        data.actualizarElemento(SQLConstantes.tablamodulo3,contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp309,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla_(),SQLConstantes.modulo3_COB300,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla_(),SQLConstantes.modulo3_COB300,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();



    }

    @Override
    public void llenarVariables() {

        idInformante = obtener_Nresidente(informanteSpinner);
        c3_p309 = mod3_309_radiogroup_C3_P309.indexOfChild(mod3_309_radiogroup_C3_P309.findViewById(mod3_309_radiogroup_C3_P309.getCheckedRadioButtonId())) +"";
        c3_p309_o  = mod3_309_edittext_C3_P309_O.getText().toString();

        c3_p309_1 = mod3_309_1_radiogroup_C3_P309.indexOfChild(mod3_309_1_radiogroup_C3_P309.findViewById(mod3_309_1_radiogroup_C3_P309.getCheckedRadioButtonId())) +"";
        c3_p309_1_o  = mod3_309_1_edittext_C3_P309_O.getText().toString();

    }

    @Override
    public void cargarDatos() {
        Data data = new Data(contexto);
        data.open();
        if (data.existeElemento(getNombreTabla_(),idEncuestado)){
            Modulo3 modulo3 = data.getModulo3(idEncuestado);
            idHogar = modulo3.getIdHogar();

            idInformante = modulo3.getIdInformante();
            ArrayList<String> informantes = data.getListaInformantesMayor12(modulo3.getIdHogar());
            UtilsMethodsInputs.loadSpinner(informantes,informanteSpinner,contexto);
            informanteSpinner.setSelection(obtener_posicion(modulo3.getIdInformante(),informanteSpinner));

            if(!modulo3.getC3_p309().equals("-1") && !modulo3.getC3_p309().equals(""))((RadioButton)mod3_309_radiogroup_C3_P309.getChildAt(Integer.parseInt(modulo3.getC3_p309()))).setChecked(true);
            mod3_309_edittext_C3_P309_O.setText(modulo3.getC3_p309_o());
//
            if(!modulo3.getC3_p309_1().equals("-1") && !modulo3.getC3_p309_1().equals(""))((RadioButton)mod3_309_1_radiogroup_C3_P309.getChildAt(Integer.parseInt(modulo3.getC3_p309_1()))).setChecked(true);
            mod3_309_1_edittext_C3_P309_O.setText(modulo3.getC3_p309_1_o());

        }
        data.close();
        /*
        Data data = new Data(contexto);
        data.open();
        if (data.existeElemento(SQLConstantes.tablamodulo3,idEncuestado)){
            Modulo3 modulo3 = data.getModulo3(idEncuestado);
            idHogar = modulo3.getIdHogar();
            idInformante = modulo3.getIdInformante();
            ArrayList<String> residentes = data.getListaSpinnerResidentesHogar(idHogar);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(contexto, android.R.layout.simple_spinner_item,residentes);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            informanteSpinner.setAdapter(adapter);
            informanteSpinner.setSelection(Integer.parseInt(idInformante));
        }
        data.close();
         */

    }

    @Override
    public void llenarVista() {
        /*
        Data data = new Data(contexto);
        data.open();
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p309,idEncuestado)) layoutp309.setVisibility(View.GONE);
        data.close();
        /*
         */
    }

    @Override
    public boolean validarDatos() {
        llenarVariables();
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}

        //PREGUNTA 309
        if(layoutp309.getVisibility()==View.VISIBLE) {
            if(c3_p309.equals("-1")){ mostrarMensaje("PREGUNTA 309: DEBE SELECCIONAR UNA OPCION");return false; }
            if(c3_p309.equals("9")){
                if(c3_p309_o.trim().equals("")){ mostrarMensaje("PREGUNTA 309: DEBE ESPECIFICAR OTRO");return false; }

                if (c3_p309_o.length()<3){
                    mostrarMensaje("ERROR  “P309. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }
            }

        }else{
            c3_p309 = "";
            c3_p309_o = "";
        }

        //PREGUNTA 309_1
        if(layoutp309_1.getVisibility()==View.VISIBLE) {
            if(c3_p309_1.equals("-1")){ mostrarMensaje("PREGUNTA 309A: DEBE SELECCIONAR UNA OPCION");return false; }
            if(c3_p309_1.equals("3")){
                if(c3_p309_1_o.trim().equals("")){ mostrarMensaje("PREGUNTA 309A: DEBE ESPECIFICAR OTRO");return false; }

                if (c3_p309_1_o.length()<3){
                    mostrarMensaje("ERROR  “P309A. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }
            }

        }else{
            c3_p309_1 = "";
            c3_p309_1_o = "";
        }

        /*
        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}
        if(m3Pregunta309s.size() == 0){ mostrarMensaje("DEBE AGREGAR RUTAS");return false;}
        if(!m3Pregunta309s.get(m3Pregunta309s.size()-1).getC3_p309_p_nom().equals("5.PERÚ")) {
            mostrarMensaje("EL PAIS DE LA ULTIMA RUTA DEBE SER PERÚ");return false;
        }
        if(!(m3Pregunta309s.get(m3Pregunta309s.size()-1).getC3_p309_mod().equals(m3Pregunta309s.get(m3Pregunta309s.size()-2).getC3_p309_mod()) &&
            m3Pregunta309s.get(m3Pregunta309s.size()-1).getC3_p309_m().equals(m3Pregunta309s.get(m3Pregunta309s.size()-2).getC3_p309_m()) &&
            m3Pregunta309s.get(m3Pregunta309s.size()-1).getC3_p309_a().equals(m3Pregunta309s.get(m3Pregunta309s.size()-2).getC3_p309_a()))){
            mostrarMensaje("EL MODO DE TRÁNSITO Y FECHA DE LAS ULTIMAS DOS RUTAS DEBEN SER IGUALES");return false;
        }

         */
        return true;
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablam3p309rutas;
    }

    public String getNombreTabla_() {
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

    public void inicializarDatos(){
        /*
        m3Pregunta309s = new ArrayList<>();
        Data data =  new Data(contexto);
        data.open();
        m3Pregunta309s = data.getAllM3Pregunta309(idEncuestado);
        data.close();
        if(m3Pregunta309s.size()>0){
//            Log.e("p309_p_nom()", "inicializarDatos: "+ m3Pregunta309s.get(m3Pregunta309s.size()-1).getC3_p309_p_nom());
//            Log.e("p309_mod()", "inicializarDatos: "+ m3Pregunta309s.get(m3Pregunta309s.size()-1).getC3_p309_mod());
//            Log.e("p309_m()", "inicializarDatos: "+ m3Pregunta309s.get(m3Pregunta309s.size()-1).getC3_p309_m());
//            Log.e("p309_a()", "inicializarDatos: "+ m3Pregunta309s.get(m3Pregunta309s.size()-1).getC3_p309_a());
        }

         */
    }

    public void setearAdapter(){
        /*
        m3Pregunta309Adapter =  new M3Pregunta309Adapter(m3Pregunta309s, contexto, new M3Pregunta309Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                final PopupMenu popupMenu = new PopupMenu(contexto,view);
                if (m3Pregunta309s.size() == position + 1){
                    popupMenu.getMenuInflater().inflate(R.menu.menu_rutas_1,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch(item.getItemId()){
//                                case R.id.opcion_editar:
//                                    Intent intent =  new Intent(contexto, AgregarRutaActivity.class);
//                                    intent.putExtra("idEncuestado",idEncuestado);
//                                    intent.putExtra("idVivienda",idVivienda);
//                                    intent.putExtra("numero",m3Pregunta309s.get(position).getNumero());
//                                    intent.putExtra("idRuta",m3Pregunta309s.get(position).get_id());
//                                    startActivity(intent);
//                                    break;
                                case R.id.opcion_eliminar:
                                    eliminarRuta(position);
                                    break;
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                }
//                else{
//                    popupMenu.getMenuInflater().inflate(R.menu.menu_rutas_2,popupMenu.getMenu());
//                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem item) {
//                            switch(item.getItemId()){
//                                case R.id.opcion_editar:
//                                    Intent intent =  new Intent(contexto, AgregarRutaActivity.class);
//                                    intent.putExtra("idEncuestado",idEncuestado);
//                                    intent.putExtra("idVivienda",idVivienda);
//                                    intent.putExtra("numero",m3Pregunta309s.get(position).getNumero());
//                                    intent.putExtra("idRuta",m3Pregunta309s.get(position).get_id());
//                                    startActivity(intent);
//                                    break;
//                            }
//
//                            return true;
//                        }
//                    });
//                    popupMenu.show();
//                }
            }
        });
        recyclerView.setAdapter(m3Pregunta309Adapter);
        */
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

    public void eliminarRuta(int position){
        /*
        Data data = new Data(contexto);
        data.open();
        data.eliminarDato(SQLConstantes.tablam3p309rutas,m3Pregunta309s.get(position).get_id());
        inicializarDatos();
        setearAdapter();
        data.close();

         */
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

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public void onResume() {
        super.onResume();
        /*
        inicializarDatos();
        setearAdapter();

         */
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
