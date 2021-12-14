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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.activities.agregacion.AgregarPersonaActivity;
import com.example.ricindigus.enpove2021.adapters.M3Pregunta318Adapter;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.M3Pregunta318;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo3;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP318 extends FragmentPagina {
    String idEncuestado;
    String idVivienda;
    Context contexto;
    String idInformante;
    String idHogar;

    ArrayList<M3Pregunta318> m3Pregunta318s = new ArrayList<>();
    Spinner spInformante;
    RecyclerView recyclerView;
    RadioGroup radioGroup;
    LinearLayout lytp318;

    FloatingActionButton fab;
    LinearLayout lytRecyclerP318;
    M3Pregunta318Adapter m3Pregunta318Adapter;
    RecyclerView.LayoutManager layoutManager;

    private int c3_p313;




    public FragmentP318() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentP318(String idEncuestado,String idVivienda, Context contexto) {
        this.idEncuestado = idEncuestado;
        this.idVivienda = idVivienda;
        this.contexto = contexto;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_p318, container, false);

        spInformante = (Spinner) rootview.findViewById(R.id.cabecera_spinner_informante);
        radioGroup = (RadioGroup) rootview.findViewById(R.id.mod3_318_radiogroup_C3_P318);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler_p318);
        lytp318 = (LinearLayout) rootview.findViewById(R.id.layout_m3_p318);
        fab = (FloatingActionButton) rootview.findViewById(R.id.fab_p318);
        lytRecyclerP318 = (LinearLayout) rootview.findViewById(R.id.layout_recycler_p318);


        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setHasFixedSize(true);
        layoutManager =  new LinearLayoutManager(contexto);
        recyclerView.setLayoutManager(layoutManager);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                if(seleccionado == 1) lytRecyclerP318.setVisibility(View.VISIBLE);
                else {
                    ocultarYEliminarRecycler();
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexto, AgregarPersonaActivity.class);
                intent.putExtra("numero",(m3Pregunta318s.size()+1)+"");
                intent.putExtra("idEncuestado",idEncuestado);
                intent.putExtra("idVivienda",idVivienda);
                intent.putExtra("id",idEncuestado+"_persona"+(m3Pregunta318s.size()+1)+"");
                startActivity(intent);
            }
        });
        llenarVista();
        cargarDatos();



    }

    public void ocultarYEliminarRecycler(){

        if (m3Pregunta318s.size() > 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
            builder.setMessage("¿Está seguro?, si elige esta opcion se eliminaran las personas registradas")
                    .setTitle("Aviso")
                    .setCancelable(false)
                    .setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ((RadioButton)radioGroup.getChildAt(1)).setChecked(true);
                                    dialog.cancel();
                                }
                            })
                    .setPositiveButton("Sí",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Data data = new Data(contexto);
                                    data.open();
                                    data.deleteTable(SQLConstantes.tablam3p318personas,idEncuestado);
                                    data.actualizarValor(SQLConstantes.tablamodulo3,SQLConstantes.modulo3_c3_p313,"2",idEncuestado);
                                    data.open();
                                    inicializarDatos();
                                    setearAdapter();
                                    lytRecyclerP318.setVisibility(View.GONE);
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }else {
            lytRecyclerP318.setVisibility(View.GONE);
        }




    }



    public void inicializarDatos(){

        m3Pregunta318s = new ArrayList<>();
        Data data =  new Data(contexto);
        data.open();
        m3Pregunta318s = data.getAllM3Pregunta318(idEncuestado);
        data.close();


    }

    public void setearAdapter(){

        m3Pregunta318Adapter =  new M3Pregunta318Adapter(m3Pregunta318s, contexto, new M3Pregunta318Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                final PopupMenu popupMenu = new PopupMenu(contexto,view);
                if (m3Pregunta318s.size() == position + 1){
                    popupMenu.getMenuInflater().inflate(R.menu.menu_personas_1,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch(item.getItemId()){
                                case R.id.opcion_editar:
                                    Intent intent =  new Intent(contexto, AgregarPersonaActivity.class);
                                    intent.putExtra("idEncuestado",idEncuestado);
                                    intent.putExtra("idVivienda",idVivienda);
                                    intent.putExtra("numero",m3Pregunta318s.get(position).getNumero());
                                    intent.putExtra("id",m3Pregunta318s.get(position).get_id());
                                    startActivity(intent);
                                    break;
                                case R.id.opcion_eliminar:
                                    eliminarPersona(position);
                                    break;
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                }else{
                    popupMenu.getMenuInflater().inflate(R.menu.menu_personas_2,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch(item.getItemId()){
                                case R.id.opcion_editar:
                                    Intent intent =  new Intent(contexto, AgregarPersonaActivity.class);
                                    intent.putExtra("idEncuestado",idEncuestado);
                                    intent.putExtra("idVivienda",idVivienda);
                                    intent.putExtra("numero",m3Pregunta318s.get(position).getNumero());
                                    intent.putExtra("id",m3Pregunta318s.get(position).get_id());
                                    startActivity(intent);
                                    break;
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                }

            }
        });
        recyclerView.setAdapter(m3Pregunta318Adapter);


    }

    public void eliminarPersona(int position){

        Data data = new Data(contexto);
        data.open();
        data.eliminarDato(SQLConstantes.tablam3p318personas,m3Pregunta318s.get(position).get_id());
        inicializarDatos();
        setearAdapter();
        data.close();


    }

    @Override
    public void onResume() {
        super.onResume();

        inicializarDatos();
        setearAdapter();


    }

    @Override
    public void guardarDatos() {

        Data data = new Data(contexto);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo3_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo3_c3_p313,c3_p313+"");
        data.actualizarElemento(SQLConstantes.tablamodulo3,contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp318,"1",idEncuestado);
        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo3_COB300,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo3_COB300,"0",idEncuestado);
        data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();


    }

    @Override
    public void llenarVariables() {

        idInformante = obtener_Nresidente(spInformante);
        c3_p313 = radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()));


    }

    @Override
    public void cargarDatos() {

        Data data = new Data(contexto);
        data.open();
        if (data.existeElemento(SQLConstantes.tablamodulo3,idEncuestado)){
            Modulo3 modulo3 = data.getModulo3(idEncuestado);
            idHogar = modulo3.getIdHogar();
            idInformante = modulo3.getIdInformante();
            ArrayList<String> informantes = data.getListaInformantesMayor12(modulo3.getIdHogar());
            UtilsMethodsInputs.loadSpinner(informantes,spInformante,contexto);
            spInformante.setSelection(obtener_posicion(modulo3.getIdInformante(),spInformante));
            if(!modulo3.getC3_p313().equals("-1") && !modulo3.getC3_p313().equals(""))((RadioButton)radioGroup.getChildAt(Integer.parseInt(modulo3.getC3_p313()))).setChecked(true);
        }
        data.close();


    }

    @Override
    public void llenarVista() {

        Data data = new Data(contexto);
        data.open();
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p318,idEncuestado)) lytp318.setVisibility(View.GONE);
        data.close();


    }

    @Override
    public boolean validarDatos() {

        llenarVariables();
        if(spInformante.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}
        if (c3_p313 == -1){mostrarMensaje("PREGUNTA 313: DEBE SELECCIONAR UNA OPCION");return false;}
        else{
            if (c3_p313 == 1 && m3Pregunta318s.size() == 0) { mostrarMensaje("DEBE AGREGAR PERSONAS");return false;}
        }


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

    public boolean coberturaCompleta(){
        boolean cobertura=false;
        Data data = new Data(contexto);
        data.open();
        Modulo3 modulo3 = data.getModulo3(idEncuestado);
        data.close();
        return cobertura;
    }

    public boolean verificarCoberturaCapitulo(){
        /*
        Data data = new Data(contexto);
        data.open();
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p301p305,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp301p305,idEncuestado).equals("0")){
            mostrarMensaje("Falta coberturar p301 - p305");return false;
        }
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p306p308,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp306p308,idEncuestado).equals("0")){
//            mostrarMensaje("Falta coberturar p306 - p308");return false;
        }
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p309,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp309,idEncuestado).equals("0")){
//            mostrarMensaje("Falta coberturar p309");return false;
        }
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p310p312,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp310p312,idEncuestado).equals("0")){
//            mostrarMensaje("Falta coberturar p310 - p312");return false;
        }
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p313p317,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp313p317,idEncuestado).equals("0")){
//            mostrarMensaje("Falta coberturar p313 - p317");return false;
        }
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p318,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp318,idEncuestado).equals("0")){
//            mostrarMensaje("Falta coberturar p318");return false;
        }
        data.close();

         */
        return true;
    }
}
