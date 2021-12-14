package com.example.ricindigus.enpove2021.fragments.vivienda;


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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.activities.HogarActivity;
import com.example.ricindigus.enpove2021.activities.ViviendaActivity;
import com.example.ricindigus.enpove2021.adapters.HogarAdapter;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Hogar;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo1V;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOFragmentHogar;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHogares extends FragmentPagina {

    String idVivienda;
    Context context;
    TextView numeroHogaresTextView;
    RecyclerView hogaresRecyclerView;
    FloatingActionButton agregarHogarFAB;
    RecyclerView.LayoutManager layoutManager;
    HogarAdapter hogarAdapter;
    ArrayList<Hogar> hogares;


    @SuppressLint("ValidFragment")
    public FragmentHogares(String idVivienda, Context context) {
        this.idVivienda = idVivienda;
        this.context = context;
    }

    public FragmentHogares() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_hogares, container, false);
        numeroHogaresTextView = (TextView) rootView.findViewById(R.id.hogares_textview_numero_hogares);
        hogaresRecyclerView = (RecyclerView) rootView.findViewById(R.id.hogares_recyclerview);
        agregarHogarFAB = (FloatingActionButton) rootView.findViewById(R.id.hogares_fab);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hogaresRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        hogaresRecyclerView.setLayoutManager(layoutManager);
        cargarDatos();
        inicializarDatos();
        setearAdapter();
        agregarHogarFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarHogar();
            }
        });

    }

    private void inicializarDatos() {
        hogares = new ArrayList<Hogar>();
        Data data = new Data(context);
        data.open();
        hogares = data.getAllHogaresVivienda(idVivienda);
        data.close();
    }

    public void setearAdapter(){
        hogarAdapter = new HogarAdapter(hogares, context,new HogarAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                if(hogares.size() == position + 1){
                    PopupMenu popupMenu = new PopupMenu(context,view);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_hogar_2,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch(item.getItemId()){
                                case R.id.opcion_hogar_iniciar:
                                    iniciarHogar(position);
                                    break;
                                case R.id.opcion_hogar_editar:
                                    editarHogar(hogares.get(position));
                                    break;
                                case R.id.opcion_hogar_eliminar:
                                    deseaEliminarDatos(position);
                                    break;
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                }else{
                    PopupMenu popupMenu = new PopupMenu(context,view);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_hogar_1,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch(item.getItemId()){
                                case R.id.opcion_hogar_iniciar:
                                    iniciarHogar(position);
                                    break;
                                case R.id.opcion_hogar_editar:
                                    editarHogar(hogares.get(position));
                                    break;
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                }


            }
        });
        hogaresRecyclerView.setAdapter(hogarAdapter);
    }

    @Override
    public void guardarDatos() {

    }

    @Override
    public void llenarVariables() {

    }

    @Override
    public void cargarDatos() {
        Data data = new Data(context);
        data.open();
        if (data.existeElemento(SQLConstantes.tablacaratula,idVivienda)){
            numeroHogaresTextView.setText(data.getCaratula(idVivienda).getT_hogar());
        }
        data.close();
    }

    @Override
    public void llenarVista() {

    }

    @Override
    public boolean validarDatos() {
        return true;
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablahogares;
    }

    public void agregarHogar(){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_jefe_hogar, null);
        final EditText jefeEditText = (EditText) dialogView.findViewById(R.id.dialog_jefe_edittext_nombre);
        final EditText paternoEditText = (EditText) dialogView.findViewById(R.id.dialog_jefe_edittext_paterno);
        final EditText maternoEditText = (EditText) dialogView.findViewById(R.id.dialog_jefe_edittext_materno);

        jefeEditText.setFilters(new InputFilter[] {new InputFilter.AllCaps(),new InputFilter.LengthFilter(100),new InputFilterSoloLetras()});
        paternoEditText.setFilters(new InputFilter[] {new InputFilter.AllCaps(),new InputFilter.LengthFilter(100),new InputFilterSoloLetras()});
        maternoEditText.setFilters(new InputFilter[] {new InputFilter.AllCaps(),new InputFilter.LengthFilter(100),new InputFilterSoloLetras()});
        alert.setTitle("AGREGAR HOGAR");
        alert.setView(dialogView);
        alert.setPositiveButton("Agregar",null);
        alert.setNegativeButton("Cancelar",null);
        final AlertDialog alertDialog = alert.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button btnAdd = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!jefeEditText.getText().toString().trim().equals("") && !paternoEditText.getText().toString().trim().equals("") && !maternoEditText.getText().toString().trim().equals("")){
                            if(jefeEditText.getText().toString().trim().length()>2){
                                //if(jefeEditText.getText().toString().substring(0,1).equals(" ")){mostrarMensaje("EL NOMBRE NO DEBE TENER UN ESPACIO VACIO AL INICIO");}
                                if(paternoEditText.getText().toString().trim().length()<3){mostrarMensaje("EL APELLIDO PATERNO ES MENOR DE 3 DIGITOS");}
                                if(maternoEditText.getText().toString().trim().length()<3){mostrarMensaje("EL APELLIDO MATERNO ES MENOR DE 3 DIGITOS");}
                                int numero = hogares.size()+1;
                                String id = idVivienda+"_"+numero;
                                Hogar hogar = new Hogar();
                                hogar.set_id(id);
                                hogar.setId_vivienda(idVivienda);
                                hogar.setNumero((hogares.size()+1)+"");
                                hogar.setNom_ape(jefeEditText.getText().toString().trim());
                                hogar.setApe_paterno(paternoEditText.getText().toString());
                                hogar.setApe_materno(maternoEditText.getText().toString());
                                hogar.setEstado("0");
                                POJOFragmentHogar pojoFragmentHogar = new POJOFragmentHogar(id);
                                pojoFragmentHogar.setId_vivienda(idVivienda);
                                if (numero == 1) {
                                    hogar.setPrincipal("1");
                                }
                                else {
                                    hogar.setPrincipal("0");
                                    if(validateExisteVivienda(idVivienda)){
                                        pojoFragmentHogar.setP101p107("-1");
                                    }

                                }
                                Data data = new Data(context);
                                data.open();
                                data.insertarElemento(getNombreTabla(),hogar.toValues());
                                data.insertarElemento(SQLConstantes.tablafragmentshogar,pojoFragmentHogar.toValues());
                                data.actualizarValor(SQLConstantes.tablamarco,SQLConstantes.marco_estado,"0",idVivienda);

                                Residente residente = new Residente();
                                residente.set_id(id + "_1");
                                residente.setId_hogar(id);
                                residente.setId_vivienda(idVivienda);
                                residente.setNumero("1");
                                String nombre = jefeEditText.getText().toString().trim();
                                String paterno = paternoEditText.getText().toString().trim();
                                String materno = maternoEditText.getText().toString().trim();
                                if(nombre.indexOf(" ") > -1){
                                    nombre = nombre.substring(0,nombre.indexOf(" "));
                                }
                                residente.setC2_p202(nombre);
                                residente.setC2_p202_pat(paterno);
                                residente.setC2_p202_mat(materno);
                                residente.setC2_p203("1");
                                data.insertarElemento(SQLConstantes.tablaresidentes,residente.toValues());
                                data.close();
                                inicializarDatos();
                                setearAdapter();
                                actualizarNumeroHogares();
                                alertDialog.dismiss();

                            }else{
                                mostrarMensaje("EL NOMBRE NO PUEDE SER MENOR A 3 DIGITOS");
                            }
                        }else mostrarMensaje("EL NOMBRE O APELLIDO DEL/LA JEFE/A DEL HOGAR NO DEBE DE ESTAR VACIO");
                    }
                });
            }
        });
        alertDialog.show();
    }

    public void editarHogar(final Hogar hogar){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_jefe_hogar, null);
        final EditText jefeEditText = (EditText) dialogView.findViewById(R.id.dialog_jefe_edittext_nombre);
        final EditText paternoEditText = (EditText) dialogView.findViewById(R.id.dialog_jefe_edittext_paterno);
        final EditText maternoEditText = (EditText) dialogView.findViewById(R.id.dialog_jefe_edittext_materno);

        jefeEditText.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(40), new InputFilterSoloLetras()});
        paternoEditText.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(40), new InputFilterSoloLetras()});
        maternoEditText.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(40), new InputFilterSoloLetras()});
        alert.setTitle("EDITAR HOGAR");
        alert.setView(dialogView);
        alert.setPositiveButton("Guardar",null);
        alert.setNegativeButton("Cancelar",null);
        final AlertDialog alertDialog = alert.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                jefeEditText.setText(hogar.getNom_ape());
                paternoEditText.setText(hogar.getApe_paterno());
                maternoEditText.setText(hogar.getApe_materno());
                Button btnAdd = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                        if(!jefeEditText.getText().toString().trim().equals("")){
                            hogar.setNom_ape(jefeEditText.getText().toString());
                            hogar.setApe_paterno(paternoEditText.getText().toString());
                            hogar.setApe_materno(maternoEditText.getText().toString());
                            Data data = new Data(context);
                            data.open();
                            String nombre = jefeEditText.getText().toString().trim();
                            String paterno = paternoEditText.getText().toString().trim();
                            String materno = maternoEditText.getText().toString().trim();
                            if(nombre.indexOf(" ") > -1){
                                nombre = nombre.substring(0,nombre.indexOf(" "));
                            }
                            data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_c2_p202,nombre,hogar.get_id()+ "_1");
                            data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_c2_p202_pat,paterno,hogar.get_id()+ "_1");
                            data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_c2_p202_mat,materno,hogar.get_id()+ "_1");
                            data.actualizarValor(getNombreTabla(),SQLConstantes.hogar_nom_ape,jefeEditText.getText().toString(),hogar.get_id());
                            data.actualizarValor(getNombreTabla(),SQLConstantes.hogar_ape_paterno,paternoEditText.getText().toString(),hogar.get_id());
                            data.actualizarValor(getNombreTabla(),SQLConstantes.hogar_ape_materno,maternoEditText.getText().toString(),hogar.get_id());
                            data.close();
                            inicializarDatos();
                            setearAdapter();
                            alertDialog.dismiss();
                        }else Toast.makeText(context, "DEBE INDICAR NOMBRES Y APELLIDOS DEL JEDE DE HOGAR", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        alertDialog.show();
    }

    public void iniciarHogar(int position){
        Intent intent = new Intent(context, HogarActivity.class);
        intent.putExtra("idHogar",hogares.get(position).get_id());
        ViviendaActivity viviendaActivity = (ViviendaActivity)getActivity();
        intent.putExtra("nickUsuario", viviendaActivity.getNickUsuario());
        startActivity(intent);
    }

    public void eliminarhogar(int position){
        Data data = new Data(context);
        data.open();
        String idDelHogar = hogares.get(position).get_id();
        ArrayList<Residente> residentes = data.getAllResidentesHogar(idDelHogar);
        for (Residente residente: residentes){
            String idDelEncuestado = residente.get_id();
            data.eliminarDato(SQLConstantes.tablaresidentes,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablamodulo3,idDelEncuestado);
            data.eliminarDatos(SQLConstantes.tablam3p309rutas,SQLConstantes.modulo3_p309_id_encuestado,idDelEncuestado);
            data.eliminarDatos(SQLConstantes.tablam3p318personas,SQLConstantes.modulo3_p318_idEncuestado,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablamodulo4,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablamodulo5,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablamodulo6,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablamodulo7,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablamodulo8,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablafragments,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablacoberturafragments,idDelEncuestado);
        }
        data.eliminarDato(getNombreTabla(),idDelHogar);
        data.eliminarDato(SQLConstantes.tablamodulo1h,idDelHogar);
        data.eliminarDatos(SQLConstantes.tablavisitasencuestador,SQLConstantes.visita_encuestador_id_hogar,idDelHogar);
        data.eliminarDato(SQLConstantes.tablaresultadoencuestador,idDelHogar);
        data.eliminarDato(SQLConstantes.tablafragmentshogar,idDelHogar);
        data.eliminarDato(SQLConstantes.tablafragmentshogar,idDelHogar+"_sup");
        inicializarDatos();
        setearAdapter();
        data.close();
        actualizarNumeroHogares();
    }

    public void actualizarNumeroHogares(){
        Data data =  new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.caratula_t_hogar,hogares.size()+"");
        data.actualizarElemento(SQLConstantes.tablacaratula,contentValues,idVivienda);
        data.close();
        numeroHogaresTextView.setText(hogares.size()+"");
    }

    public void deseaEliminarDatos(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("¿Está seguro que desea eliminar el hogar?, se perderán todos los datos asociados al hogar y sus residentes")
                .setTitle("Aviso")
                .setCancelable(false)
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton("Sí",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                eliminarhogar(position);
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
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

    public boolean validateExisteVivienda(String idVivienda){
        boolean estado = false;
        Data data = new Data(context);
        data.open();
        Modulo1V modulo1V = data.getModulo1V(idVivienda);
        if(modulo1V!=null) {
           estado = true;
        }
        data.close();
        return  estado;
    }

    @Override
    public void onResume() {
        super.onResume();
        inicializarDatos();
        setearAdapter();
    }
}
