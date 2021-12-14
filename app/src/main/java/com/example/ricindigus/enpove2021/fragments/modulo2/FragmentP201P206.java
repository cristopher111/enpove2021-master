package com.example.ricindigus.enpove2021.fragments.modulo2;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.activities.EncuestaActivity;
import com.example.ricindigus.enpove2021.activities.agregacion.AgregarResidenteActivity;
import com.example.ricindigus.enpove2021.adapters.ResidenteAdapter;
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Hogar;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.modelo.pojos.ResultadoResidente;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.UtilsMethods;
import com.example.ricindigus.enpove2021.util.UtilsMethodsUpdates;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP201P206 extends FragmentPagina {

    String idHogar;
    String idVivienda;
    String idInformante;
    Context context;
    Spinner informanteSpinner;
    TextView txtNroPersonas;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Residente> residentes;
    ResidenteAdapter residenteAdapter;

    public FragmentP201P206() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentP201P206(String idHogar, String idVivienda, Context context) {
        this.idHogar = idHogar;
        this.idVivienda = idVivienda;
        this.context = context;
        idInformante = "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView     = inflater.inflate(R.layout.fragment_p201_p206, container, false);
        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);
        fab               = (FloatingActionButton) rootView.findViewById(R.id.mod2_fab);
        txtNroPersonas    = (TextView) rootView.findViewById(R.id.mod2_txtNumeroPersonas);
        recyclerView      = (RecyclerView) rootView.findViewById(R.id.mod2_recyclerview);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = (residentes.size()+1);
 //               if (residentes.get(0).getCOB200().equals("1")){
                    Intent intent = new Intent(context, AgregarResidenteActivity.class);
                    intent.putExtra("idEncuestado",idHogar + "_" + num);
                    intent.putExtra("numero", num + "");
                    intent.putExtra("idHogar", idHogar);
                    intent.putExtra("idVivienda", idVivienda);
                    intent.putExtra("idJefeHogar", residentes.get(0).get_id());
                    intent.putExtra("vista", "0");
                    startActivity(intent);
//                }else { mostrarMensaje("ANTES DE INGRESAR ALGÚN MIEMBRO DEL HOGAR, DEBE COMPLETAR LA INFORMACIÓN DEL JEFE DE HOGAR");}

            }
        });
        cargarDatos();
        cargarInformante();
        updateHogarP16aP20();
        //updateCoberturaCapitulos(residentes);
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
        if (data.existeElemento(SQLConstantes.tablahogares,idHogar)){
            Hogar hogar = data.getHogar(idHogar);
            txtNroPersonas.setText(hogar.getNroviven());
        }
        data.close();
    }

    @Override
    public void llenarVista() {

    }

    public void mostrarMensaje(String m){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(m);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean validarDatos() {
        return true;
    }

    @Override
    public String getNombreTabla() {
        return null;
    }

    public void inicializarDatos(){
        residentes = new ArrayList<>();
        Data data =  new Data(context);
        data.open();
        residentes = data.getAllResidentesHogar(idHogar);
        txtNroPersonas.setText(residentes.size()+"");
        data.close();
    }

    public void setearAdapter(){
        residenteAdapter = new ResidenteAdapter(residentes,context, new ResidenteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {

                    final PopupMenu popupMenu = new PopupMenu(context,view);
                    if (residentes.size() == position + 1){
                        popupMenu.getMenuInflater().inflate(R.menu.menu_residente_1,popupMenu.getMenu());
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch(item.getItemId()){
                                    case R.id.opcion_iniciar_encuesta:
                                        Residente residente = residentes.get(0);
                                        if(informanteSpinner.getSelectedItemPosition()==0) {
                                            mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");
                                        } else{
                                        if(residente.getC2_p204().equals("")){
                                            Toast.makeText(context, "DEBE COMPLETAR LOS DATOS DEL RESIDENTE ANTES DE INICIAR LA ENCUESTA", Toast.LENGTH_SHORT).show();
                                        }else{
                                            if (residentes.get(position).getC2_p208().equals("2") || residentes.get(position).getC2_p208().equals("")){
                                                Toast.makeText(context, "LA ENCUESTA ESTA DIRIGIDA SOLO A POBLACION VENEZOLANA", Toast.LENGTH_SHORT).show();
                                            }else{
                                                String idEncuestado = residentes.get(position).get_id()+"";
                                                //ocultar_mostrar_p625(idEncuestado);
                                                updateInformante();
                                                Intent intent1 = new Intent(context, EncuestaActivity.class);
                                                intent1.putExtra("idEncuestado",idEncuestado);
                                                intent1.putExtra("numero", residentes.get(position).getNumero() + "");
                                                intent1.putExtra("idHogar", idHogar);
                                                intent1.putExtra("idVivienda", idVivienda);
                                                iniciarResultadoResidente(idEncuestado);
                                                startActivity(intent1);
                                            }
                                        }
                                        }
                                        break;
                                    case R.id.opcion_editar:
//                                        Residente resident = residentes.get(0);
//                                        if(resident.getC2_p204().equals("")  && !residentes.get(position).getC2_p203().equals("1") ){
//                                            Toast.makeText(context, "DEBE INGRESAR LOS DATOS DEL JEFE DE HOGAR..", Toast.LENGTH_SHORT).show();
//                                        }else {
                                            Intent intent2 = new Intent(context, AgregarResidenteActivity.class);
                                            intent2.putExtra("idEncuestado", residentes.get(position).get_id() + "");
                                            intent2.putExtra("numero", residentes.get(position).getNumero() + "");
                                            intent2.putExtra("idHogar", idHogar);
                                            intent2.putExtra("idVivienda", idVivienda);
                                            intent2.putExtra("idJefeHogar", residentes.get(0).get_id());
                                            intent2.putExtra("vista", "0");
                                            startActivity(intent2);
                                      //  }
                                        break;
                                    case R.id.opcion_eliminar:
                                        if (position > 0) deseaEliminarDatos(position);
                                        else
                                            Toast.makeText(context, "NO PUEDE ELIMINAR AL JEFE DE HOGAR", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                                return true;
                            }
                        });
                        popupMenu.show();
                    }else{
                        popupMenu.getMenuInflater().inflate(R.menu.menu_residente_2,popupMenu.getMenu());
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch(item.getItemId()){
                                    case R.id.opcion_iniciar_encuesta:
                                        Residente residente = residentes.get(0);
                                        if(informanteSpinner.getSelectedItemPosition()==0) {
                                            mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");
                                        } else{
                                        if(residente.getC2_p204().equals("")){
                                            Toast.makeText(context, "DEBE COMPLETAR LOS DATOS DEL RESIDENTE DE HOGAR ANTES DE INICIAR LA ENCUESTA", Toast.LENGTH_SHORT).show();
                                        }else{
                                            if (residentes.get(position).getC2_p208().equals("2")){
                                                Toast.makeText(context, "LA ENCUESTA ESTA DIRIGIDA SOLO A POBLACION VENEZOLANA", Toast.LENGTH_SHORT).show();
                                            }else{
                                                String idEncuestado = residentes.get(position).get_id()+"";
                                                updateInformante();
                                                Intent intent1 = new Intent(context, EncuestaActivity.class);
                                                intent1.putExtra("idEncuestado",idEncuestado);
                                                intent1.putExtra("numero", residentes.get(position).getNumero() + "");
                                                intent1.putExtra("idHogar", idHogar);
                                                intent1.putExtra("idVivienda", idVivienda);
                                                iniciarResultadoResidente(idEncuestado);
                                                startActivity(intent1);
                                            }
                                        }
                                        }
                                        break;
                                    case R.id.opcion_editar:
//                                        Residente resident = residentes.get(0);
//                                        if(resident.getC2_p204().equals("")   && !residentes.get(position).getC2_p203().equals("1")){
//                                            Toast.makeText(context, "DEBE INGRESAR LOS DATOS DEL JEFE DE HOGAR", Toast.LENGTH_SHORT).show();
//                                        }else {
                                            Intent intent2 = new Intent(context, AgregarResidenteActivity.class);
                                            intent2.putExtra("idEncuestado", residentes.get(position).get_id() + "");
                                            intent2.putExtra("numero", residentes.get(position).getNumero() + "");
                                            intent2.putExtra("idHogar", idHogar);
                                            intent2.putExtra("idVivienda", idVivienda);
                                            intent2.putExtra("idJefeHogar", residentes.get(0).get_id());
                                            intent2.putExtra("vista", "0");
                                            startActivity(intent2);
 //                                       }
//                                        }
                                        break;
                                }

                                return true;
                            }
                        });
                        popupMenu.show();
                    }
                }
        });
        recyclerView.setAdapter(residenteAdapter);
    }

    public void eliminarEncuestado(int position){
        Data data = new Data(context);
        data.open();
        String idDelEncuestado = residentes.get(position).get_id();
        data.eliminarDato(SQLConstantes.tablaresidentes,idDelEncuestado);
        data.eliminarDato(SQLConstantes.tablamodulo3,idDelEncuestado);
        data.eliminarDatos(SQLConstantes.tablam3p309rutas,SQLConstantes.modulo3_p309_id_encuestado,idDelEncuestado);
        data.eliminarDatos(SQLConstantes.tablam3p318personas,SQLConstantes.modulo3_p318_idEncuestado,idDelEncuestado);
        data.eliminarDato(SQLConstantes.tablamodulo4,idDelEncuestado);
        data.eliminarDato(SQLConstantes.tablamodulo5,idDelEncuestado);
        data.eliminarDato(SQLConstantes.tablamodulo6,idDelEncuestado);
        data.eliminarDato(SQLConstantes.tablamodulo7,idDelEncuestado);
        data.eliminarDato(SQLConstantes.tablamodulo8,idDelEncuestado);
        inicializarDatos();
        setearAdapter();
        data.close();
    }

    public void deseaEliminarDatos(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("¿Está seguro que desea eliminar el residente?, se perderán todos los datos asociados al encuestado/a")
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
                                eliminarEncuestado(position);
                                updateHogarP16aP20();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void cargarInformante() {
        Data data =  new Data(context);
        data.open();
        ArrayList<String> residentes1 = data.getListaSpinnerResidentesHogarMayor(idHogar);
        Hogar hogar = data.getHogar(idHogar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,residentes1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        informanteSpinner.setAdapter(adapter);

        if(!hogar.getId_informante().equals("")){
            informanteSpinner.setSelection(Integer.parseInt(hogar.getId_informante()));
        }
        data.close();
    }

    public boolean validarListaInformante() {
        boolean estado = false;
        Data data =  new Data(context);
        data.open();
        ArrayList<String> residentes1 = data.getListaSpinnerResidentesHogar(idHogar);
        if (residentes1.size()>1){
            estado= true;
        }
        return estado;

    }

    public void updateInformante(){
        Data data = new Data(context);
        data.open();
        data.actualizarValor(SQLConstantes.tablahogares,SQLConstantes.hogar_id_informante,informanteSpinner.getSelectedItemPosition()+"",idHogar);
        data.close();
    }

    public void updateHogarP16aP20(){
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.hogar_nroviven,UtilsMethodsUpdates.getHogarP16(context,idHogar));
        contentValues.put(SQLConstantes.hogar_vive,UtilsMethodsUpdates.getJefeVenezolanoHogar(context,idHogar));
        contentValues.put(SQLConstantes.hogar_p16,UtilsMethodsUpdates.getHogarP16(context,idHogar));
        contentValues.put(SQLConstantes.hogar_p17,UtilsMethodsUpdates.getHogarP17(context,idHogar));
        contentValues.put(SQLConstantes.hogar_p18,UtilsMethodsUpdates.getHogarP18(context,idHogar));
        contentValues.put(SQLConstantes.hogar_p19,UtilsMethodsUpdates.getHogarP19(context,idHogar));
        contentValues.put(SQLConstantes.hogar_p20,UtilsMethodsUpdates.getHogarP20(context,idHogar));
        data.actualizarElemento(SQLConstantes.tablahogares,contentValues,idHogar);
        data.close();
    }

    public void updateJefeHogarVenezolano(){
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.hogar_p16,UtilsMethodsUpdates.getHogarP16(context,idHogar));
        data.actualizarElemento(SQLConstantes.tablahogares,contentValues,idHogar);
        data.close();
    }


    public void iniciarResultadoResidente(String idEncuestado) {
        Data data = new Data(context);
        data.open();
        if(!data.existeElemento(SQLConstantes.tablaresultadoresidente,idEncuestado)){
            ResultadoResidente resultadoResidente = new ResultadoResidente();
            resultadoResidente.set_id(idEncuestado);
            resultadoResidente.setId_hogar(idHogar);
            resultadoResidente.setId_vivienda(idVivienda);
            resultadoResidente.setFecha_dd(UtilsMethods.getFechaActual().getDiaInicio());
            resultadoResidente.setFecha_mm(UtilsMethods.getFechaActual().getMesInicio());
            resultadoResidente.setFecha_aa(UtilsMethods.getFechaActual().getAnioInicio());
            resultadoResidente.setHor_ini(UtilsMethods.getFechaActual().getHoraInicio());
            resultadoResidente.setMin_ini(UtilsMethods.getFechaActual().getMinutoInicio());
            data.insertarElemento(SQLConstantes.tablaresultadoresidente,resultadoResidente.toValues());
        }
        data.close();
    }

    public void  updateCoberturaCapitulos(ArrayList<Residente> listaResidentes){
            Data data = new Data(context);
            data.open();
        for (Residente residente : listaResidentes) {
            int cobertura200 =  DAOUtils.getCoberturaModulo200(residente.get_id(), context);
            int cobertura300 =  DAOUtils.getCoberturaModulo300(residente.get_id(), context);
            int cobertura400 =  DAOUtils.getCoberturaModulo400(residente.get_id(), context);
            int cobertura500 =  DAOUtils.getCoberturaModulo500(residente.get_id(), context);
            int cobertura600 =  DAOUtils.getCoberturaModulo600(residente.get_id(), context);
            int cobertura700 =  DAOUtils.getCoberturaModulo700(residente.get_id(), context);
            int cobertura800 =  DAOUtils.getCoberturaModulo800(residente.get_id(), context);

            data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_COB200,cobertura200+"",idInformante);
            data.actualizarValor(SQLConstantes.tablamodulo3,SQLConstantes.modulo3_COB300,cobertura300+"",idInformante);
            data.actualizarValor(SQLConstantes.tablamodulo4,SQLConstantes.modulo4_COB400,cobertura400+"",idInformante);
            data.actualizarValor(SQLConstantes.tablamodulo5,SQLConstantes.modulo5_COB500,cobertura500+"",idInformante);
            data.actualizarValor(SQLConstantes.tablamodulo6,SQLConstantes.modulo6_COB600,cobertura600+"",idInformante);
            data.actualizarValor(SQLConstantes.tablamodulo7,SQLConstantes.modulo7_COB700,cobertura700+"",idInformante);
            data.actualizarValor(SQLConstantes.tablamodulo8,SQLConstantes.modulo8_COB800,cobertura800+"",idInformante);
        }

            data.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        inicializarDatos();
        setearAdapter();
        cargarInformante();
        updateHogarP16aP20();
 //       updateCoberturaCapitulos(residentes);
    }

//    public void ocultar_mostrar_p625(String idEncuestado){
//        Data data = new Data(context);
//        data.open();
//        ContentValues contentValues = new ContentValues();
//        Log.e("idEncuestado", "ocultar_mostrar_p625: "+idEncuestado );
//        if(data.menor_edad_hogar(idHogar)) {
//            Log.e("menor de edad", "ocultar_mostrar_p625: "+idEncuestado );
//            if(data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p625,idEncuestado).equals("0")){
//                Log.e("menor de edad entro", "ocultar_mostrar_p625: "+idEncuestado );
//                contentValues = new ContentValues();
//                contentValues.put(SQLConstantes.layouts_p625,"1");
//                data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
//                data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp622p625,"0",idEncuestado);
//            }
//        }else{
//            Log.e("mayor de edad", "ocultar_mostrar_p625: "+idEncuestado );
//            if(data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p625,idEncuestado).equals("1")){
//                Log.e("mayor de edad entro", "ocultar_mostrar_p625: "+idEncuestado );
//                contentValues = new ContentValues();
//                contentValues.put(SQLConstantes.layouts_p625,"0");
//                data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
//                contentValues = new ContentValues();
//                contentValues.put(SQLConstantes.modulo6_c6_p625_1,"");
//                contentValues.put(SQLConstantes.modulo6_c6_p625_2,"");
//                contentValues.put(SQLConstantes.modulo6_c6_p625_3,"");
//                contentValues.put(SQLConstantes.modulo6_c6_p625_4,"");
//                contentValues.put(SQLConstantes.modulo6_c6_p625_5,"");
//                contentValues.put(SQLConstantes.modulo6_c6_p625_6,"");
//                contentValues.put(SQLConstantes.modulo6_c6_p625_o,"");
//                data.actualizarElemento(SQLConstantes.tablamodulo6,contentValues,idEncuestado);
//            }
//        }
//        data.close();
//    }
}
