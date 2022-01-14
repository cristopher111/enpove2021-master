package com.example.ricindigus.enpove2021.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Caratula;
import com.example.ricindigus.enpove2021.modelo.pojos.CoberturaFragment;
import com.example.ricindigus.enpove2021.modelo.pojos.Funcionario;
import com.example.ricindigus.enpove2021.modelo.pojos.Hogar;
import com.example.ricindigus.enpove2021.modelo.pojos.M3Pregunta309;
import com.example.ricindigus.enpove2021.modelo.pojos.M3Pregunta318;
import com.example.ricindigus.enpove2021.modelo.pojos.Marco;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo1H;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo1V;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo3;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo4;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo5;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo6;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo7;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo8;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOFragment;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOFragmentHogar;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOFragmentVivienda;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOLayout;
import com.example.ricindigus.enpove2021.modelo.pojos.ResVisitaEncuestador;
import com.example.ricindigus.enpove2021.modelo.pojos.ResVisitaSupervisor;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.modelo.pojos.ResultadoResidente;
import com.example.ricindigus.enpove2021.modelo.pojos.VisitaEncuestador;
import com.example.ricindigus.enpove2021.modelo.pojos.VisitaSupervisor;
import com.example.ricindigus.enpove2021.util.FileChooser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class ImportarActivity extends AppCompatActivity {
    private TextView txtImportar;
    private Button btnImportar;
    private Button btnVolver;
    private Button btnBuscar;
    private TextView txtArchivo;


    private Caratula caratula;
    private Marco marco;
    private ArrayList<Hogar> hogares;
    private Hogar currentHogar;
    private ArrayList<VisitaEncuestador> visitaEncuestadors;
    private VisitaEncuestador currentVisitaEncuestador;
    private ArrayList<VisitaSupervisor> visitaSupervisors;
    private VisitaSupervisor currentVisitaSupervisor;
    private ArrayList<ResVisitaEncuestador> resVisitaEncuestadors;
    private ResVisitaEncuestador currentResVisitaEncuestador;
    private ArrayList<ResVisitaSupervisor> resVisitaSupervisors;
    private ResVisitaSupervisor currentResVisitaSupervisor;
    private ArrayList<ResultadoResidente> resultadoResidentes;
    private ResultadoResidente currentResultadoResidente;
    private Funcionario funcionario;
    private Modulo1V modulo1V;
    private ArrayList<Modulo1H> modulo1HS;
    private Modulo1H currentModulo1H;
    private ArrayList<Residente> residentes;
    private Residente currentResidente;
    private ArrayList<Modulo3> modulo3s;
    private Modulo3 currentModulo3;
    private ArrayList<M3Pregunta309> m3Pregunta309s;
    private M3Pregunta309 currentM3Pregunta309;
    private ArrayList<M3Pregunta318> m3Pregunta318s;
    private M3Pregunta318 currentM3Pregunta318;
    private ArrayList<Modulo4> modulo4s;
    private Modulo4 currentModulo4;
    private ArrayList<Modulo5> modulo5s;
    private Modulo5 currentModulo5;
    private ArrayList<Modulo6> modulo6s;
    private Modulo6 currentModulo6;
    private ArrayList<Modulo7> modulo7s;
    private Modulo7 currentModulo7;
    private ArrayList<Modulo8> modulo8s;
    private Modulo8 currentModulo8;
    private POJOFragmentVivienda pojoFragmentVivienda;
    private ArrayList<POJOFragmentHogar> pojoFragmentHogars;
    private POJOFragmentHogar currentPojoFragmentHogar;
    private ArrayList<POJOFragment> pojoFragments;
    private POJOFragment currentPojoFragment;
    private ArrayList<POJOLayout> pojoLayouts;
    private POJOLayout currentPojoLayout;
    private ArrayList<CoberturaFragment> coberturaFragments;
    private CoberturaFragment currentCoberturaFragment;

    private String currentTag = null;
    private String currentVariable = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importar);

        txtImportar = (TextView) findViewById(R.id.txtImportar);
        btnImportar = (Button) findViewById(R.id.importacion_btnImportar);
        btnVolver = (Button) findViewById(R.id.importacion_btnVolver);
        btnBuscar = findViewById(R.id.btnBuscarArchivo);
        txtArchivo = findViewById(R.id.importacion_edtArchivo);


        btnImportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ImportarActivity.this);
                builder.setMessage("¿Está seguro que desea importar el archivo?")
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
                                        ///////////AGREGADO 6/01/21////////
                                        Data data = new Data(getApplicationContext());
                                        data.open();
                                     /* data.eliminarTabla(SQLConstantes.tablacaratula);
                                        data.eliminarTabla(SQLConstantes.tablamarco);
                                        data.eliminarTabla(SQLConstantes.tablahogares);
                                        data.eliminarTabla(SQLConstantes.tablavisitasencuestador);
                                        data.eliminarTabla(SQLConstantes.tablavisitassupervisor);
                                        data.eliminarTabla(SQLConstantes.tablaresultadoencuestador);
                                        data.eliminarTabla(SQLConstantes.tablaresultadosupervisor);
                                        data.eliminarTabla(SQLConstantes.tablaresultadoresidente);
                                        data.eliminarTabla(SQLConstantes.tablafuncionarios);
                                        data.eliminarTabla(SQLConstantes.tablamodulo1h);
                                        data.eliminarTabla(SQLConstantes.tablamodulo1v);
                                        data.eliminarTabla(SQLConstantes.tablaresidentes);
                                        data.eliminarTabla(SQLConstantes.tablamodulo3);
                                        data.eliminarTabla(SQLConstantes.tablam3p309rutas);
                                        data.eliminarTabla(SQLConstantes.tablam3p318personas);
                                        data.eliminarTabla(SQLConstantes.tablamodulo4);
                                        data.eliminarTabla(SQLConstantes.tablamodulo5);
                                        data.eliminarTabla(SQLConstantes.tablamodulo6);
                                        data.eliminarTabla(SQLConstantes.tablamodulo7);
                                        data.eliminarTabla(SQLConstantes.tablamodulo8);
                                        data.eliminarTabla(SQLConstantes.tablafragmentsvivienda);
                                        data.eliminarTabla(SQLConstantes.tablafragmentshogar);
                                        data.eliminarTabla(SQLConstantes.tablafragments);
                                        data.eliminarTabla(SQLConstantes.tablalayouts);
                                        data.eliminarTabla(SQLConstantes.tablacoberturafragments);*/

                                        //
                                        String idVivienda = txtArchivo.getText().toString();

                                        data.eliminarDato(SQLConstantes.tablacaratula,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablamarco,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablahogares,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablavisitasencuestador,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablavisitassupervisor,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablaresultadoencuestador,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablaresultadosupervisor,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablaresultadoresidente,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablafuncionarios,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablamodulo1h,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablamodulo1v,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablaresidentes,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablamodulo3,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablam3p309rutas,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablam3p318personas,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablamodulo4,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablamodulo5,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablamodulo6,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablamodulo7,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablamodulo8,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablafragmentsvivienda,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablafragmentshogar,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablafragments,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablalayouts,idVivienda);
                                        data.eliminarDato(SQLConstantes.tablacoberturafragments,idVivienda);
                                        data.close();
                                        ////////////////////////////////////////////
                                        String nombreArchivo = txtArchivo.getText().toString();
                                        parseXMLImportar(nombreArchivo);
                                        dialog.dismiss();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ImportarActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ImportarActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }else{
                    FileChooser fileChooser = new FileChooser(ImportarActivity.this);
                    fileChooser.setFileListener(new FileChooser.FileSelectedListener() {
                        @Override
                        public void fileSelected(File file) {
                            String filename = file.getAbsolutePath();
                            if(filename.substring(filename.length()-4,filename.length()).toLowerCase().equals(".xml")){
                                txtArchivo.setText(filename);
                            }else{
                                Toast.makeText(ImportarActivity.this, "archivo de tipo incorrecto", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    fileChooser.showDialog();
                }
            }
        });
    }
    public void parseXMLImportar(String nombreArchivo){
        caratula = new Caratula();
        marco = new Marco();
        hogares = new ArrayList<>();
        visitaEncuestadors = new ArrayList<>();
        visitaSupervisors = new ArrayList<>();
        resVisitaEncuestadors = new ArrayList<>();
        resVisitaSupervisors = new ArrayList<>();
        resultadoResidentes = new ArrayList<>();
        funcionario = new Funcionario();
        modulo1V = new Modulo1V();
        modulo1HS = new ArrayList<>();
        residentes = new ArrayList<>();
        modulo3s = new ArrayList<>();
        m3Pregunta309s = new ArrayList<>();
        m3Pregunta318s = new ArrayList<>();
        modulo4s = new ArrayList<>();
        modulo5s = new ArrayList<>();
        modulo6s = new ArrayList<>();
        modulo7s = new ArrayList<>();
        modulo8s = new ArrayList<>();
        pojoFragmentVivienda = new POJOFragmentVivienda();
        pojoFragmentHogars = new ArrayList<>();
        pojoFragments = new ArrayList<>();
        pojoLayouts = new ArrayList<>();
        coberturaFragments = new ArrayList<>();

        XmlPullParserFactory factory;
        FileInputStream fis = null;
        try {
            StringBuilder sb = new StringBuilder();
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
//            File nuevaCarpeta = new File(getExternalStorageDirectory(), "ENPOVE2018");
//            File file = new File(nuevaCarpeta, nombreArchivo);
            File file = new File(nombreArchivo);
            FileInputStream fileInputStream = new FileInputStream(file);
            fis = new FileInputStream(file);


            xpp.setInput(fis, null);
            int eventType = xpp.getEventType();

            //LEE DOCUMENTO XML Y PARSEA LOS DATOS , Y LLENA LAS VARIABLES
            while(eventType != XmlPullParser.END_DOCUMENT){
                if(eventType == XmlPullParser.START_TAG){
                    handleStarTag(xpp.getName());
                }else if(eventType == XmlPullParser.END_TAG){
                    handleEndTag(xpp.getName());
                }else if(eventType == XmlPullParser.TEXT){
                    handleText(xpp.getText());
                }
                eventType = xpp.next();
            }
            //VARIABLES LLENAS
            //GUARDAR VARIABLES LLENADAS EN EL PARSEO
            Data data = new Data(this);
            data.open();
            String idVivienda = txtArchivo.getText().toString();
            //insertar la caratula
            if(!caratula.get_id().equals("")){ data.eliminarDato(SQLConstantes.tablacaratula,idVivienda);data.insertarElemento(SQLConstantes.tablacaratula,caratula.toValues()); }

            if(!marco.get_id().equals("0")){
                data.eliminarDato(SQLConstantes.tablamarco,idVivienda);data.insertarElemento(SQLConstantes.tablamarco,marco.toValues());
            }
            //insertar datos hogares
            if(hogares.size()>0){data.deleteAllHogares(idVivienda);data.insertarHogares(hogares);}
            //insertar datos visitas encuestador
            if(visitaEncuestadors.size()>0){data.deleteAllVisitasEncuestador(idVivienda);data.insertarVisitasEncuestador(visitaEncuestadors);}
            //insertar datos resultados encuestador
            if(resVisitaEncuestadors.size()>0){
                data.eliminarDatos(SQLConstantes.tablaresultadoencuestador,SQLConstantes.resultado_supervisor_id_vivienda,idVivienda);
                for (ResVisitaEncuestador resVisitaEncuestador : resVisitaEncuestadors)
                    data.insertarElemento(SQLConstantes.tablaresultadoencuestador,resVisitaEncuestador.toValues());
            }
            //insertar datos visitas supervisor
            if(visitaSupervisors.size()>0){data.deleteAllVisitasSupervisor(idVivienda);data.insertarVisitasSupervisor(visitaSupervisors);}
            //insertar datos resultados encuestador
            if(resVisitaSupervisors.size()>0){
                data.eliminarDatos(SQLConstantes.tablaresultadosupervisor,SQLConstantes.resultado_supervisor_id_vivienda,idVivienda);
                for (ResVisitaSupervisor resVisitaSupervisor : resVisitaSupervisors)
                    data.insertarElemento(SQLConstantes.tablaresultadosupervisor,resVisitaSupervisor.toValues());
            }
            //insertar datos resultado_residente
            if(resultadoResidentes.size()>0){
                data.eliminarDatos(SQLConstantes.tablaresultadoresidente,SQLConstantes.resultado_residente_id_vivienda,idVivienda);
                for (ResultadoResidente resultadoResidente : resultadoResidentes)
                    data.insertarElemento(SQLConstantes.tablaresultadoresidente,resultadoResidente.toValues());
            }

            //insertar el funcionario
            if(!funcionario.get_id().equals("")){ data.eliminarDato(SQLConstantes.tablafuncionarios,idVivienda);data.insertarElemento(SQLConstantes.tablafuncionarios,funcionario.toValues()); }
            //insertar el modulo 1 Vivienda
            if(!modulo1V.get_id().equals("")){ data.eliminarDato(SQLConstantes.tablamodulo1v,idVivienda);data.insertarElemento(SQLConstantes.tablamodulo1v,modulo1V.toValues()); }
            //insertar datos modulo1 Hogares
            if(modulo1HS.size()>0){
                data.eliminarDatos(SQLConstantes.tablamodulo1h,SQLConstantes.modulo1_h_idVivienda,idVivienda);
                for (Modulo1H modulo1H : modulo1HS)
                    data.insertarElemento(SQLConstantes.tablamodulo1h,modulo1H.toValues());
            }
            //insertar datos residentes
            if(residentes.size()>0){
                data.eliminarDatos(SQLConstantes.tablaresidentes,SQLConstantes.residentes_id_vivienda,idVivienda);
                for (Residente residente : residentes)
                    data.insertarElemento(SQLConstantes.tablaresidentes,residente.toValues());
            }

            //insertar modulos 3
            if(modulo3s.size()>0){
                data.eliminarDatos(SQLConstantes.tablamodulo3,SQLConstantes.modulo3_id_vivienda,idVivienda);
                for (Modulo3 modulo3 : modulo3s)
                    data.insertarElemento(SQLConstantes.tablamodulo3,modulo3.toValues());
            }

            //insertar modulos 3 pregunta 309 rutas
            if(m3Pregunta309s.size()>0){
                data.eliminarDatos(SQLConstantes.tablam3p309rutas,SQLConstantes.modulo3_p309_id_vivienda,idVivienda);
                for (M3Pregunta309 m3Pregunta309 : m3Pregunta309s)
                    data.insertarElemento(SQLConstantes.tablam3p309rutas,m3Pregunta309.toValues());
            }

            //insertar modulos 3 pregunta 309 rutas
            if(m3Pregunta318s.size()>0){
                data.eliminarDatos(SQLConstantes.tablam3p318personas,SQLConstantes.modulo3_p318_id_vivienda,idVivienda);
                for (M3Pregunta318 m3Pregunta318 : m3Pregunta318s)
                    data.insertarElemento(SQLConstantes.tablam3p318personas,m3Pregunta318.toValues());
            }

            //insertar modulos 4
            if(modulo4s.size()>0){
                data.eliminarDatos(SQLConstantes.tablamodulo4,SQLConstantes.modulo4_id_vivienda,idVivienda);
                for (Modulo4 modulo4 : modulo4s)
                    data.insertarElemento(SQLConstantes.tablamodulo4,modulo4.toValues());
            }
            //insertar modulos 5
            if(modulo5s.size()>0){
                data.eliminarDatos(SQLConstantes.tablamodulo5,SQLConstantes.modulo5_id_vivienda,idVivienda);
                for (Modulo5 modulo5 : modulo5s)
                    data.insertarElemento(SQLConstantes.tablamodulo5,modulo5.toValues());
            }
            //insertar modulos 6
            if(modulo4s.size()>0){
                data.eliminarDatos(SQLConstantes.tablamodulo6,SQLConstantes.modulo6_id_vivienda,idVivienda);
                for (Modulo6 modulo6 : modulo6s)
                    data.insertarElemento(SQLConstantes.tablamodulo6,modulo6.toValues());
            }
            //insertar modulos 7
            if(modulo7s.size()>0){
                data.eliminarDatos(SQLConstantes.tablamodulo7,SQLConstantes.modulo7_id_vivienda,idVivienda);
                for (Modulo7 modulo7 : modulo7s)
                    data.insertarElemento(SQLConstantes.tablamodulo7,modulo7.toValues());
            }
            //insertar modulos 8
            if(modulo8s.size()>0){
                data.eliminarDatos(SQLConstantes.tablamodulo8,SQLConstantes.modulo8_id_vivienda,idVivienda);
                for (Modulo8 modulo8 : modulo8s)
                    data.insertarElemento(SQLConstantes.tablamodulo8,modulo8.toValues());
            }
            //insertar el fragment vivienda
            if(!pojoFragmentVivienda.get_id().equals("")){ data.eliminarDato(SQLConstantes.tablafragmentsvivienda,idVivienda);data.insertarElemento(SQLConstantes.tablafragmentsvivienda,pojoFragmentVivienda.toValues()); }

            // insertar fragments hogares
            if(pojoFragmentHogars.size()>0){
                data.eliminarDatos(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_id_vivienda,idVivienda);
                for (POJOFragmentHogar fragmentHogar : pojoFragmentHogars){
                    data.insertarElemento(SQLConstantes.tablafragmentshogar,fragmentHogar.toValues());
                }
            }

            // insertar fragments
            if(pojoFragments.size()>0){
                data.eliminarDatos(SQLConstantes.tablafragments,SQLConstantes.fragments_id_vivienda,idVivienda);
                for (POJOFragment pojoFragment : pojoFragments){
                    data.insertarElemento(SQLConstantes.tablafragments,pojoFragment.toValues());
                }
            }

            // insertar fragments
            if(pojoLayouts.size()>0){
                data.eliminarDatos(SQLConstantes.tablalayouts,SQLConstantes.layouts_id_vivienda,idVivienda);
                for (POJOLayout pojoLayout : pojoLayouts){
                    data.insertarElemento(SQLConstantes.tablalayouts,pojoLayout.toValues());
                }
            }

            // insertar cobertura fragments
            if(coberturaFragments.size()>0){
                data.eliminarDatos(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_id,idVivienda);
                for (CoberturaFragment coberturaFragment : coberturaFragments){
                    data.insertarElemento(SQLConstantes.tablacoberturafragments,coberturaFragment.toValues());
                }
            }

            data.close();
            Toast.makeText(ImportarActivity.this, "Vivienda importada", Toast.LENGTH_SHORT).show();
//            txtImportar.setText(sb.toString());
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(ImportarActivity.this, "No existe el archivo", Toast.LENGTH_SHORT).show();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//FALTA AGREGAR RESULTADO_PERSONA
    private void handleText(String text) {
        switch (currentTag){
            case "CARATULA":                     agregarVariableCaratula(currentVariable,text);break;
            case "MARCO":                        agregarVariableMarco(currentVariable,text);break;
            case "HOGAR":                        agregarVariableHogar(currentVariable,text);break;
            case "VISITA_ENCUESTADOR":           agregarVariableVisitaEncuestador(currentVariable,text);break;
            case "VISITA_SUPERVISOR":            agregarVariableVisitaSupervisor(currentVariable,text);break;
            case "RESULTADO_VISITA_ENCUESTADOR": agregarVariableResultadoEncuestador(currentVariable,text);break;
            case "RESULTADO_VISITA_SUPERVISOR":  agregarVariableResultadoSupervisor(currentVariable,text);break;
            case "ITEM_RESULTADO_RESIDENTE":     agregarVariableResultadoResidente(currentVariable,text);break;
            case "FUNCIONARIO":                  agregarVariableFuncionario(currentVariable,text);break;
            case "MODULO1V":                     agregarVariableModulo1V(currentVariable,text);break;
            case "MODULO1_HOGAR":                agregarVariableModulo1H(currentVariable,text);break;
            case "MODULO2_RESIDENTE":            agregarVariableResidente(currentVariable,text);break;
            case "MODULO3":                      agregarVariableModulo3(currentVariable,text);break;
            case "M3P309RUTA":                     agregarVariableM3P309(currentVariable,text);break;
            case "M3P318PERSONA":agregarVariableM3P318(currentVariable,text);break;
            case "MODULO4":agregarVariableModulo4(currentVariable,text);break;
            case "MODULO5":agregarVariableModulo5(currentVariable,text);break;
            case "MODULO6":agregarVariableModulo6(currentVariable,text);break;
            case "MODULO7":agregarVariableModulo7(currentVariable,text);break;
            case "MODULO8":agregarVariableModulo8(currentVariable,text);break;
            case "FRAGMENT_VIVIENDA":agregarVariableFragmentVivienda(currentVariable,text);break;
            case "FRAGMENT_HOGAR":agregarVariableFragmentHogar(currentVariable,text);break;
            case "FRAGMENT_ENCUESTADO":agregarVariableFragmentEncuestado(currentVariable,text);break;
            case "LAYOUT_ENCUESTADO":agregarVariableLayout(currentVariable,text);break;
            case "COBERTURA_FRAGMENT":agregarVariableCoberturaFragment(currentVariable,text);break;
        }
    }

    private void handleStarTag(String name) {
        switch (name){
            case "CARATULA":currentTag = "CARATULA";break;
            case "MARCO":currentTag = "MARCO";break;
            case "HOGAR":currentTag = "HOGAR";currentHogar = new Hogar();break;
            case "VISITA_ENCUESTADOR":currentTag = "VISITA_ENCUESTADOR";currentVisitaEncuestador = new VisitaEncuestador();break;
            case "VISITA_SUPERVISOR":currentTag = "VISITA_SUPERVISOR";currentVisitaSupervisor = new VisitaSupervisor();break;
            case "RESULTADO_VISITA_ENCUESTADOR":currentTag = "RESULTADO_VISITA_ENCUESTADOR";currentResVisitaEncuestador = new ResVisitaEncuestador();break;
            case "RESULTADO_VISITA_SUPERVISOR":currentTag = "RESULTADO_VISITA_SUPERVISOR";currentResVisitaSupervisor= new ResVisitaSupervisor();break;
            case "ITEM_RESULTADO_RESIDENTE":currentTag = "ITEM_RESULTADO_RESIDENTE";currentResultadoResidente = new ResultadoResidente();break;
            case "FUNCIONARIO":currentTag = "FUNCIONARIO";break;
            case "MODULO1V":currentTag = "MODULO1V";break;
            case "MODULO1_HOGAR":currentTag = "MODULO1_HOGAR";currentModulo1H = new Modulo1H();break;
            case "MODULO2_RESIDENTE":currentTag = "MODULO2_RESIDENTE";currentResidente = new Residente();break;
            case "MODULO3":currentTag = "MODULO3";currentModulo3 = new Modulo3();break;
            case "M3P309RUTA":currentTag = "M3P309RUTA";currentM3Pregunta309 = new M3Pregunta309();break;
            case "M3P318PERSONA":currentTag = "M3P318PERSONA";currentM3Pregunta318 = new M3Pregunta318();break;
            case "MODULO4":currentTag = "MODULO4";currentModulo4 = new Modulo4();break;
            case "MODULO5":currentTag = "MODULO5";currentModulo5 = new Modulo5();break;
            case "MODULO6":currentTag = "MODULO6";currentModulo6 = new Modulo6();break;
            case "MODULO7":currentTag = "MODULO7";currentModulo7 = new Modulo7();break;
            case "MODULO8":currentTag = "MODULO8";currentModulo8 = new Modulo8();break;
            case "FRAGMENT_VIVIENDA":currentTag = "FRAGMENT_VIVIENDA";break;
            case "FRAGMENT_HOGAR":currentTag = "FRAGMENT_HOGAR";currentPojoFragmentHogar = new POJOFragmentHogar();break;
            case "FRAGMENT_ENCUESTADO":currentTag = "FRAGMENT_ENCUESTADO";currentPojoFragment = new POJOFragment();break;
            case "LAYOUT_ENCUESTADO":currentTag = "LAYOUT_ENCUESTADO";currentPojoLayout = new POJOLayout();break;
            case "COBERTURA_FRAGMENT":currentTag = "COBERTURA_FRAGMENT";currentCoberturaFragment = new CoberturaFragment();break;
            default: currentVariable = name;break;
        }
    }
    public void handleEndTag(String name){
        switch (name){
            case "HOGAR": hogares.add(currentHogar);break;
            case "VISITA_ENCUESTADOR": visitaEncuestadors.add(currentVisitaEncuestador);break;
            case "VISITA_SUPERISOR": visitaSupervisors.add(currentVisitaSupervisor);break;
            case "RESULTADO_VISITA_ENCUESTADOR": resVisitaEncuestadors.add(currentResVisitaEncuestador);break;
            case "RESULTADO_VISITA_SUPERVISOR": resVisitaSupervisors.add(currentResVisitaSupervisor);break;
            case "ITEM_RESULTADO_RESIDENTE": resultadoResidentes.add(currentResultadoResidente);break;
            case "MODULO1_HOGAR": modulo1HS.add(currentModulo1H);break;
            case "MODULO2_RESIDENTE": residentes.add(currentResidente);break;
            case "MODULO3": modulo3s.add(currentModulo3);break;
            case "M3P309RUTA": m3Pregunta309s.add(currentM3Pregunta309);break;
            case "M3P318PERSONA": m3Pregunta318s.add(currentM3Pregunta318);break;
            case "MODULO4": modulo4s.add(currentModulo4);break;
            case "MODULO5": modulo5s.add(currentModulo5);break;
            case "MODULO6": modulo6s.add(currentModulo6);break;
            case "MODULO7": modulo7s.add(currentModulo7);break;
            case "MODULO8": modulo8s.add(currentModulo8);break;
            case "FRAGMENT_HOGAR": pojoFragmentHogars.add(currentPojoFragmentHogar);break;
            case "FRAGMENT_ENCUESTADO": pojoFragments.add(currentPojoFragment);break;
            case "LAYOUT_ENCUESTADO": pojoLayouts.add(currentPojoLayout);break;
            case "COBERTURA_FRAGMENT": coberturaFragments.add(currentCoberturaFragment);break;
        }
    }

    ///////////////////////
    public void agregarVariableMarco(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.marco_id:marco.set_id(valor);break;
                case SQLConstantes.marco_anio:marco.setAnio(valor);break;
                case SQLConstantes.marco_mes:marco.setMes(valor);break;
                case SQLConstantes.marco_periodo:marco.setPeriodo(valor);break;
                case SQLConstantes.marco_zona:marco.setZona(valor);break;
                case SQLConstantes.marco_ccdd:marco.setCcdd(valor);break;
                case SQLConstantes.marco_departamento:marco.setDepartamento(valor);break;
                case SQLConstantes.marco_ccpp:marco.setCcpp(valor);break;
                case SQLConstantes.marco_provincia:marco.setProvincia(valor);break;
                case SQLConstantes.marco_ccdi:marco.setCcdi(valor);break;
                case SQLConstantes.marco_distrito:marco.setDistrito(valor);break;
                case SQLConstantes.marco_codccpp:marco.setCodccpp(valor);break;
                case SQLConstantes.marco_nomccpp:marco.setNomccpp(valor);break;
                case SQLConstantes.marco_conglomerado:marco.setConglomerado(valor);break;
                case SQLConstantes.marco_norden:marco.setNorden(valor);break;
                case SQLConstantes.marco_manzana_id:marco.setManzana_id(valor);break;
                case SQLConstantes.marco_tipvia:marco.setTipvia(valor);break;
                case SQLConstantes.marco_nomvia:marco.setNomvia(valor);break;
                case SQLConstantes.marco_nropta:marco.setNropta(valor);break;
                case SQLConstantes.marco_lote:marco.setLote(valor);break;
                case SQLConstantes.marco_piso:marco.setPiso(valor);break;
                case SQLConstantes.marco_mza:marco.setMza(valor);break;
                case SQLConstantes.marco_block:marco.setBlock(valor);break;
                case SQLConstantes.marco_interior:marco.setInterior(valor);break;
                case SQLConstantes.marco_km:marco.setKm(valor);break;
                case SQLConstantes.marco_usuario_id:marco.setUsuario_id(valor);break;
                case SQLConstantes.marco_usuario:marco.setUsuario(valor);break;
                case SQLConstantes.marco_nombre:marco.setNombre(valor);break;
                case SQLConstantes.marco_dni:marco.setDni(valor);break;
                case SQLConstantes.marco_usuario_sup_id:marco.setUsuario_sup_id(valor);break;
                case SQLConstantes.marco_estado:marco.setEstado(valor);break;
                case SQLConstantes.marco_nro_selec_vivienda:marco.setNro_selec_vivienda(valor);break;
                case SQLConstantes.marco_tipo_selec_vivienda:marco.setTipo_selec_vivienda(valor);break;
                case SQLConstantes.marco_vivienda_reemplazo:marco.setVivienda_reemplazo(valor);break;
                case SQLConstantes.marco_tipo:marco.setTipo(valor);break;
                case SQLConstantes.marco_nroVivienda:marco.setNroVivienda(valor);break;
                case SQLConstantes.marco_nroSegmento:marco.setNroSegmento(valor);break;
                case SQLConstantes.marco_marcoProviene:marco.setMarcoProviene(valor);break;
                case SQLConstantes.marco_estrato:marco.setEstrato(valor);break;
                case SQLConstantes.marco_observaciones:marco.setObservaciones(valor);break;
                case SQLConstantes.marco_nroPuerta2:marco.setNroPuerta2(valor);break;
                case SQLConstantes.marco_jefeHogar:marco.setJefeHogar(valor);break;
                case SQLConstantes.marco_telefono:marco.setTelefono(valor);break;
                case SQLConstantes.marco_correo:marco.setCorreo(valor);break;
                case SQLConstantes.marco_aerInicial:marco.setAerInicial(valor);break;
                case SQLConstantes.marco_aerFinal:marco.setAerFinal(valor);break;
                case SQLConstantes.marco_cono:marco.setCono(valor);break;
                case SQLConstantes.marco_area:marco.setArea(valor);break;
                case SQLConstantes.marco_areaEncuesta:marco.setAreaEncuesta(valor);break;
                case SQLConstantes.marco_region:marco.setRegion(valor);break;
                case SQLConstantes.marco_dominio:marco.setDominio(valor);break;
                case SQLConstantes.marco_idCarga:marco.setIdCarga(valor);break;
                case SQLConstantes.marco_frente:marco.setFrente(valor);break;
                case SQLConstantes.marco_latitud:marco.setLatitud(valor);break;
                case SQLConstantes.marco_longitud:marco.setLongitud(valor);break;
            }
        }
    }


    ///////////////////////

    public void agregarVariableCaratula(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.caratula_id:caratula.set_id(valor);break;
                case SQLConstantes.caratula_anio:caratula.setAnio(valor);break;
                case SQLConstantes.caratula_mes:caratula.setMes(valor);break;
                case SQLConstantes.caratula_periodo:caratula.setPeriodo(valor);break;
                case SQLConstantes.caratula_conglomerado:caratula.setConglomerado(valor);break;
                case SQLConstantes.caratula_nom_dep:caratula.setNom_dep(valor);break;
                case SQLConstantes.caratula_nom_prov:caratula.setNom_prov(valor);break;
                case SQLConstantes.caratula_nom_dist:caratula.setNom_dist(valor);break;
                case SQLConstantes.caratula_nom_ccpp:caratula.setNom_ccpp(valor);break;
                case SQLConstantes.caratula_zona:caratula.setZona(valor);break;
                case SQLConstantes.caratula_manzana_id:caratula.setManzana_id(valor);break;
                case SQLConstantes.caratula_vivienda:caratula.setVivienda(valor);break;
                case SQLConstantes.caratula_latitud:caratula.setLatitud(valor);break;
                case SQLConstantes.caratula_longitud:caratula.setLongitud(valor);break;
                case SQLConstantes.caratula_altitud:caratula.setAltitud(valor);break;
                case SQLConstantes.caratula_tipvia:caratula.setTipvia(valor);break;
                case SQLConstantes.caratula_tipvia_o:caratula.setTipvia_o(valor);break;
                case SQLConstantes.caratula_nomvia:caratula.setNomvia(valor);break;
                case SQLConstantes.caratula_nropta:caratula.setNropta(valor);break;
                case SQLConstantes.caratula_block:caratula.setBlock(valor);break;
                case SQLConstantes.caratula_interior:caratula.setInterior(valor);break;
                case SQLConstantes.caratula_piso:caratula.setPiso(valor);break;
                case SQLConstantes.caratula_mza:caratula.setMza(valor);break;
                case SQLConstantes.caratula_lote:caratula.setLote(valor);break;
                case SQLConstantes.caratula_km:caratula.setKm(valor);break;
                case SQLConstantes.caratula_telefono:caratula.setTelefono(valor);break;
                case SQLConstantes.caratula_t_hogar:caratula.setT_hogar(valor);break;
                case SQLConstantes.caratula_usuario:caratula.setUsuario(valor);break;
                case SQLConstantes.caratula_observaciones:caratula.setObservaciones(valor);break;
                case SQLConstantes.caratula_cobertura:caratula.setCobertura(valor);break;
                case SQLConstantes.caratula_nro_selec_vivienda:caratula.setNro_selec_vivienda(valor);break;
                case SQLConstantes.caratula_tipo_selec_vivienda:caratula.setTipo_selec_vivienda(valor);break;
                case SQLConstantes.caratula_vivienda_reemplazo:caratula.setVivienda_reemplazo(valor);break;
                case SQLConstantes.caratula_nro_vivienda_reemplazo:caratula.setNro_vivienda_reemplazo(valor);break;
                case SQLConstantes.caratula_p21:caratula.setP21(valor);break;//----
                case SQLConstantes.caratula_p21_o:caratula.setP21_o(valor);break;//----
                case SQLConstantes.caratula_resultado_final:caratula.setResultado_final(valor);break;//----
                case SQLConstantes.caratula_resultado_final_o:caratula.setResultado_final_o(valor);break;//----
                case SQLConstantes.caratula_nroSegmento:caratula.setNroSegmento(valor);break;//----
                case SQLConstantes.caratula_nroPta2:caratula.setNroPuerta2(valor);break;//----
            }
        }
    }

    public void agregarVariableHogar(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.hogar_id:currentHogar.set_id(valor);break;
                case SQLConstantes.hogar_id_informante:currentHogar.setId_informante(valor);break;
                case SQLConstantes.hogar_id_vivienda:currentHogar.setId_vivienda(valor);break;
                case SQLConstantes.hogar_numero:currentHogar.setNumero(valor);break;
                case SQLConstantes.hogar_nom_ape:currentHogar.setNom_ape(valor);break;
                case SQLConstantes.hogar_ape_paterno:currentHogar.setApe_paterno(valor);break;
                case SQLConstantes.hogar_ape_materno:currentHogar.setApe_materno(valor);break;
                case SQLConstantes.hogar_estado:currentHogar.setEstado(valor);break;
                case SQLConstantes.hogar_nroviven:currentHogar.setNroviven(valor);break;
                case SQLConstantes.hogar_nropersonas:currentHogar.setNropersonas(valor);break;
                case SQLConstantes.hogar_vive:currentHogar.setVive(valor);break;
                case SQLConstantes.hogar_principal:currentHogar.setPrincipal(valor);break;
                case SQLConstantes.hogar_cobertura:currentHogar.setCobertura(valor);break;
                case SQLConstantes.hogar_p15:currentHogar.setP15(valor);break;
                case SQLConstantes.hogar_p15_o:currentHogar.setP15_o(valor);break;
                case SQLConstantes.hogar_p16:currentHogar.setP16(valor);break;
                case SQLConstantes.hogar_p17:currentHogar.setP17(valor);break;
                case SQLConstantes.hogar_p18:currentHogar.setP18(valor);break;
                case SQLConstantes.hogar_p19:currentHogar.setP19(valor);break;
                case SQLConstantes.hogar_p20:currentHogar.setP20(valor);break;
            }
        }
    }

    public void agregarVariableVisitaEncuestador(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.visita_encuestador_id: currentVisitaEncuestador.set_id(valor);break;
                case SQLConstantes.visita_encuestador_id_vivienda: currentVisitaEncuestador.setId_vivienda(valor);break;
                case SQLConstantes.visita_encuestador_id_hogar: currentVisitaEncuestador.setId_hogar(valor);break;
                case SQLConstantes.visita_encuestador_numero: currentVisitaEncuestador.setNumero(valor);break;
                case SQLConstantes.visita_encuestador_vis_fecha_dd: currentVisitaEncuestador.setVis_fecha_dd(valor);break;
                case SQLConstantes.visita_encuestador_vis_fecha_mm: currentVisitaEncuestador.setVis_fecha_mm(valor);break;
                case SQLConstantes.visita_encuestador_vis_fecha_aa: currentVisitaEncuestador.setVis_fecha_aa(valor);break;
                case SQLConstantes.visita_encuestador_vis_hor_ini: currentVisitaEncuestador.setVis_hor_ini(valor);break;
                case SQLConstantes.visita_encuestador_vis_min_ini: currentVisitaEncuestador.setVis_min_ini(valor);break;
                case SQLConstantes.visita_encuestador_vis_hor_fin: currentVisitaEncuestador.setVis_hor_fin(valor);break;
                case SQLConstantes.visita_encuestador_vis_min_fin: currentVisitaEncuestador.setVis_min_fin(valor);break;
                case SQLConstantes.visita_encuestador_prox_vis_fecha_dd: currentVisitaEncuestador.setProx_vis_fecha_dd(valor);break;
                case SQLConstantes.visita_encuestador_prox_vis_fecha_mm: currentVisitaEncuestador.setProx_vis_fecha_mm(valor);break;
                case SQLConstantes.visita_encuestador_prox_vis_fecha_aa: currentVisitaEncuestador.setProx_vis_fecha_aa(valor);break;
                case SQLConstantes.visita_encuestador_prox_vis_hor: currentVisitaEncuestador.setProx_vis_hor(valor);break;
                case SQLConstantes.visita_encuestador_prox_vis_min: currentVisitaEncuestador.setProx_vis_min(valor);break;
                case SQLConstantes.visita_encuestador_vis_resu: currentVisitaEncuestador.setVis_resu(valor);break;
                case SQLConstantes.visita_encuestador_vis_resu_esp: currentVisitaEncuestador.setVis_resu_esp(valor);break;
                case SQLConstantes.visita_encuestador_latitud: currentVisitaEncuestador.setLatitud(valor);break;
                case SQLConstantes.visita_encuestador_longitud: currentVisitaEncuestador.setLongitud(valor);break;
                case SQLConstantes.visita_encuestador_altura: currentVisitaEncuestador.setAltura(valor);break;
                case SQLConstantes.visita_encuestador_tipo_entrevista: currentVisitaEncuestador.setTipo_ent(valor);break;
                case SQLConstantes.visita_encuestador_tipo_entrevista_o: currentVisitaEncuestador.setTipo_ent_o(valor);break;
            }
        }

    }

    public void agregarVariableResultadoEncuestador(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.resultado_encuestador_id:currentResVisitaEncuestador.set_id(valor);break;
                case SQLConstantes.resultado_encuestador_id_vivienda:currentResVisitaEncuestador.setId_vivienda(valor);break;
                case SQLConstantes.resultado_encuestador_vis_resultado_final:currentResVisitaEncuestador.setVis_resultado_final(valor);break;
                case SQLConstantes.resultado_encuestador_vis_fecha_final_dd:currentResVisitaEncuestador.setVis_fecha_final_dd(valor);break;
                case SQLConstantes.resultado_encuestador_vis_fecha_final_mm:currentResVisitaEncuestador.setVis_fecha_final_mm(valor);break;
                case SQLConstantes.resultado_encuestador_vis_fecha_final_aa:currentResVisitaEncuestador.setVis_fecha_final_aa(valor);break;
            }
        }
    }


    public void agregarVariableVisitaSupervisor(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.visita_encuestador_id: currentVisitaEncuestador.set_id(valor);break;
                case SQLConstantes.visita_encuestador_id_vivienda: currentVisitaEncuestador.setId_vivienda(valor);break;
                case SQLConstantes.visita_encuestador_id_hogar: currentVisitaEncuestador.setId_hogar(valor);break;
                case SQLConstantes.visita_encuestador_numero: currentVisitaEncuestador.setNumero(valor);break;
                case SQLConstantes.visita_encuestador_vis_fecha_dd: currentVisitaEncuestador.setVis_fecha_dd(valor);break;
                case SQLConstantes.visita_encuestador_vis_fecha_mm: currentVisitaEncuestador.setVis_fecha_mm(valor);break;
                case SQLConstantes.visita_encuestador_vis_fecha_aa: currentVisitaEncuestador.setVis_fecha_aa(valor);break;
                case SQLConstantes.visita_encuestador_vis_hor_ini: currentVisitaEncuestador.setVis_hor_ini(valor);break;
                case SQLConstantes.visita_encuestador_vis_min_ini: currentVisitaEncuestador.setVis_min_ini(valor);break;
                case SQLConstantes.visita_encuestador_vis_hor_fin: currentVisitaEncuestador.setVis_hor_fin(valor);break;
                case SQLConstantes.visita_encuestador_vis_min_fin: currentVisitaEncuestador.setVis_min_fin(valor);break;
                case SQLConstantes.visita_encuestador_vis_resu: currentVisitaEncuestador.setVis_resu(valor);break;
                case SQLConstantes.visita_encuestador_vis_resu_esp: currentVisitaEncuestador.setVis_resu_esp(valor);break;
            }
        }
    }

    public void agregarVariableResultadoSupervisor(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.resultado_supervisor_id:currentResVisitaSupervisor.set_id(valor);break;
                case SQLConstantes.resultado_supervisor_id_vivienda:currentResVisitaSupervisor.setId_vivienda(valor);break;
                case SQLConstantes.resultado_supervisor_vis_resultado_final:currentResVisitaSupervisor.setVis_resultado_final(valor);break;
                case SQLConstantes.resultado_supervisor_vis_fecha_final_dd:currentResVisitaSupervisor.setVis_fecha_final_dd(valor);break;
                case SQLConstantes.resultado_supervisor_vis_fecha_final_mm:currentResVisitaSupervisor.setVis_fecha_final_mm(valor);break;
                case SQLConstantes.resultado_supervisor_vis_fecha_final_aa:currentResVisitaSupervisor.setVis_fecha_final_aa(valor);break;
            }
        }
    }


    public void agregarVariableFuncionario(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.funcionarios_id:funcionario.set_id(valor);break;
                case SQLConstantes.funcionarios_dni_encu:funcionario.setDni_encu(valor);break;
                case SQLConstantes.funcionarios_dni_sup:funcionario.setDni_sup(valor);break;
                case SQLConstantes.funcionarios_dni_supn:funcionario.setDni_supn(valor);break;
                case SQLConstantes.funcionarios_dni_coord:funcionario.setDni_coor(valor);break;
                case SQLConstantes.funcionarios_nombre_encu:funcionario.setNombre_encu(valor);break;
                case SQLConstantes.funcionarios_nombre_sup:funcionario.setNombre_sup(valor);break;
                case SQLConstantes.funcionarios_nombre_supn:funcionario.setNombre_supn(valor);break;
                case SQLConstantes.funcionarios_nombre_coord:funcionario.setNombre_coord(valor);break;
            }
        }
    }


    public void agregarVariableModulo1V(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.modulo1_v_id:modulo1V.set_id(valor);break;
                case SQLConstantes.modulo1_v_c1_p101:modulo1V.setC1_p101(valor);break;
                case SQLConstantes.modulo1_v_c1_p101_o:modulo1V.setC1_p101_o(valor);break;
                case SQLConstantes.modulo1_v_c1_p102:modulo1V.setC1_p102(valor);break;
                case SQLConstantes.modulo1_v_c1_p102_o:modulo1V.setC1_p102_o(valor);break;
                case SQLConstantes.modulo1_v_c1_p103:modulo1V.setC1_p103(valor);break;
                case SQLConstantes.modulo1_v_c1_p103_o:modulo1V.setC1_p103_o(valor);break;
                case SQLConstantes.modulo1_v_c1_p104:modulo1V.setC1_p104(valor);break;
                case SQLConstantes.modulo1_v_c1_p104_o:modulo1V.setC1_p104_o(valor);break;
                case SQLConstantes.modulo1_v_c1_p105:modulo1V.setC1_p105(valor);break;
                case SQLConstantes.modulo1_v_c1_p106:modulo1V.setC1_p106(valor);break;
                //case SQLConstantes.modulo1_v_c1_p107:modulo1V.setC1_p107(valor);break;
                case SQLConstantes.modulo1_v_COB100A:modulo1V.setCOB100A(valor);break;
            }
        }

    }

    public void agregarVariableModulo1H(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.modulo1_h_id:currentModulo1H.set_id(valor);break;
                case SQLConstantes.modulo1_h_idVivienda:currentModulo1H.setIdVivienda(valor);break;
                case SQLConstantes.modulo1_h_c1_p107:currentModulo1H.setC1_p107(valor);break;
                case SQLConstantes.modulo1_h_c1_p107_o:currentModulo1H.setC1_p107_o(valor);break;
                case SQLConstantes.modulo1_h_c1_p108_1:currentModulo1H.setC1_p108_1(valor);break;
                case SQLConstantes.modulo1_h_c1_p108_2:currentModulo1H.setC1_p108_2(valor);break;
                case SQLConstantes.modulo1_h_c1_p108_3:currentModulo1H.setC1_p108_3(valor);break;
                case SQLConstantes.modulo1_h_c1_p108_4:currentModulo1H.setC1_p108_4(valor);break;
                case SQLConstantes.modulo1_h_c1_p109:currentModulo1H.setC1_p109(valor);break;
                case SQLConstantes.modulo1_h_c1_p109_o:currentModulo1H.setC1_p109_o(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_1:currentModulo1H.setC1_p110_1(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_2:currentModulo1H.setC1_p110_2(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_3:currentModulo1H.setC1_p110_3(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_4:currentModulo1H.setC1_p110_4(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_5:currentModulo1H.setC1_p110_5(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_6:currentModulo1H.setC1_p110_6(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_7:currentModulo1H.setC1_p110_7(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_8:currentModulo1H.setC1_p110_8(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_9:currentModulo1H.setC1_p110_9(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_10:currentModulo1H.setC1_p110_10(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_11:currentModulo1H.setC1_p110_11(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_12:currentModulo1H.setC1_p110_12(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_13:currentModulo1H.setC1_p110_13(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_11_o:currentModulo1H.setC1_p110_11_o(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_12_o:currentModulo1H.setC1_p110_12_o(valor);break;
                case SQLConstantes.modulo1_h_c1_p110_13_o:currentModulo1H.setC1_p110_13_o(valor);break;
                case SQLConstantes.modulo1_h_c1_p111:currentModulo1H.setC1_p111(valor);break;
                case SQLConstantes.modulo1_h_c1_p111a:currentModulo1H.setC1_p111a(valor);break;
                case SQLConstantes.modulo1_h_c1_p112_1:currentModulo1H.setC1_p112_1(valor);break;
                case SQLConstantes.modulo1_h_c1_p112_2:currentModulo1H.setC1_p112_2(valor);break;
                case SQLConstantes.modulo1_h_c1_p112_3:currentModulo1H.setC1_p112_3(valor);break;
                case SQLConstantes.modulo1_h_c1_p112_4:currentModulo1H.setC1_p112_4(valor);break;
                case SQLConstantes.modulo1_h_c1_p112_5:currentModulo1H.setC1_p112_5(valor);break;
                case SQLConstantes.modulo1_h_c1_p112_6:currentModulo1H.setC1_p112_6(valor);break;
                case SQLConstantes.modulo1_h_c1_p112_7:currentModulo1H.setC1_p112_7(valor);break;
                case SQLConstantes.modulo1_h_c1_p112_8:currentModulo1H.setC1_p112_8(valor);break;
                case SQLConstantes.modulo1_h_COB100B:currentModulo1H.setCOB100B(valor);break;
            }
        }
    }

    public void agregarVariableResultadoResidente(String campo, String valor){
        if (valor !=  null){
            switch (campo){
                case SQLConstantes.resultado_residente_id:currentResultadoResidente.set_id(valor);break;
                case SQLConstantes.resultado_residente_id_hogar:currentResultadoResidente.setId_hogar(valor);break;
                case SQLConstantes.resultado_residente_id_vivienda:currentResultadoResidente.setId_vivienda(valor);break;
                case SQLConstantes.resultado_residente_fecha_dd:currentResultadoResidente.setFecha_dd(valor);break;
                case SQLConstantes.resultado_residente_fecha_mm:currentResultadoResidente.setFecha_mm(valor);break;
                case SQLConstantes.resultado_residente_fecha_aa:currentResultadoResidente.setFecha_aa(valor);break;
                case SQLConstantes.resultado_residente_hor_ini:currentResultadoResidente.setHor_ini(valor);break;
                case SQLConstantes.resultado_residente_min_ini:currentResultadoResidente.setMin_ini(valor);break;
                case SQLConstantes.resultado_residente_hor_fin:currentResultadoResidente.setHor_fin(valor);break;
                case SQLConstantes.resultado_residente_min_fin:currentResultadoResidente.setMin_fin(valor);break;
                case SQLConstantes.resultado_residente_resultado_entrevista:currentResultadoResidente.setResultado_entrevista(valor);break;
                case SQLConstantes.resultado_residente_resultado_entrevista_o:currentResultadoResidente.setResultado_entrevista_o(valor);break;
                case SQLConstantes.resultado_residente_tipo_entrevista:currentResultadoResidente.setTipo_entrevista(valor);break;
                case SQLConstantes.resultado_residente_tipo_entrevista_o:currentResultadoResidente.setTipo_entrevista_o(valor);break;//---
                case SQLConstantes.resultado_residente_donde_entrevista:currentResultadoResidente.setDonde_entrevista(valor);break;//---
                case SQLConstantes.resultado_residente_donde_entrevista_o:currentResultadoResidente.setDonde_entrevista_o(valor);break;//---
            }
        }
    }

    public void agregarVariableResidente(String campo, String valor){
        if (valor !=  null){
            switch (campo){
                case SQLConstantes.residentes_id:currentResidente.set_id(valor);break;
                case SQLConstantes.residentes_id_informante:currentResidente.setId_informante(valor);break;
                case SQLConstantes.residentes_id_hogar:currentResidente.setId_hogar(valor);break;
                case SQLConstantes.residentes_id_vivienda:currentResidente.setId_vivienda(valor);break;
                case SQLConstantes.residentes_numero:currentResidente.setNumero(valor);break;
                case SQLConstantes.residentes_c2_p202:currentResidente.setC2_p202(valor);break;
                case SQLConstantes.residentes_c2_p202_pat:currentResidente.setC2_p202_pat(valor);break;
                case SQLConstantes.residentes_c2_p202_mat:currentResidente.setC2_p202_mat(valor);break;
                case SQLConstantes.residentes_c2_p203:currentResidente.setC2_p203(valor);break;
                case SQLConstantes.residentes_c2_p204:currentResidente.setC2_p204(valor);break;
                case SQLConstantes.residentes_c2_p205_a:currentResidente.setC2_p205_a(valor);break;
                case SQLConstantes.residentes_c2_p205_m:currentResidente.setC2_p205_m(valor);break;
                case SQLConstantes.residentes_c2_p206:currentResidente.setC2_p206(valor);break;
                case SQLConstantes.residentes_c2_p207:currentResidente.setC2_p207(valor);break;
                case SQLConstantes.residentes_c2_p207_o:currentResidente.setC2_p207_o(valor);break;
                case SQLConstantes.residentes_c2_p208:currentResidente.setC2_p208(valor);break;//---
                case SQLConstantes.residentes_c2_p209:currentResidente.setC2_p209(valor);break;//---
                case SQLConstantes.residentes_c2_p209_n:currentResidente.setC2_p209_n(valor);break;//---
                case SQLConstantes.residentes_c2_p209_p:currentResidente.setC2_p209_p(valor);break;//---
                case SQLConstantes.residentes_c2_p209_pos:currentResidente.setC2_p209_pos(valor);break;//---
                case SQLConstantes.residentes_c2_p210:currentResidente.setC2_p210(valor);break;//---
                case SQLConstantes.residentes_c2_p210_n:currentResidente.setC2_p210_n(valor);break;//---
                case SQLConstantes.residentes_c2_p210_p:currentResidente.setC2_p210_p(valor);break;//---
                case SQLConstantes.residentes_c2_p210_pos:currentResidente.setC2_p210_pos(valor);break;//---
                case SQLConstantes.residentes_c2_p211:currentResidente.setC2_p211(valor);break;//---
                case SQLConstantes.residentes_c2_p211_1:currentResidente.setC2_p211_1(valor);break;//---
                case SQLConstantes.residentes_c2_p211_1_o:currentResidente.setC2_p211_1_o(valor);break;//---
                case SQLConstantes.residentes_c2_p211_nom:currentResidente.setC2_p211_nom(valor);break;//---
                case SQLConstantes.residentes_c2_p211_pos:currentResidente.setC2_p211_pos(valor);break;//---
                case SQLConstantes.residentes_c2_p212:currentResidente.setC2_p212(valor);break;//---
                case SQLConstantes.residentes_p200_aportante:currentResidente.setP200_aportante(valor);break;//--- MODIFICADO 30/12/21
                case SQLConstantes.residentes_c2_OBS200:currentResidente.setOBS200(valor);break;//---
                case SQLConstantes.residentes_COB200:currentResidente.setCOB200(valor);break;
                case SQLConstantes.residentes_encuestado_cobertura:currentResidente.setEncuestado_cobertura(valor);break;
            }
        }
    }

    public void agregarVariableModulo3(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.modulo3_id:currentModulo3.set_id(valor);break;
                case SQLConstantes.modulo3_id_informante:currentModulo3.setIdInformante(valor);break;
                case SQLConstantes.modulo3_id_hogar:currentModulo3.setIdHogar(valor);break;
                case SQLConstantes.modulo3_id_vivienda:currentModulo3.setIdVivienda(valor);break;
                case SQLConstantes.modulo3_c3_p301_d:currentModulo3.setC3_p301_d(valor);break;
                case SQLConstantes.modulo3_c3_p301_m:currentModulo3.setC3_p301_m(valor);break;
                case SQLConstantes.modulo3_c3_p301_a:currentModulo3.setC3_p301_a(valor);break;
                case SQLConstantes.modulo3_c3_p302:currentModulo3.setC3_p302(valor);break;
                case SQLConstantes.modulo3_c3_p302_o:currentModulo3.setC3_p302_o(valor);break;
                case SQLConstantes.modulo3_c3_p303_m:currentModulo3.setC3_p303_m(valor);break;
                case SQLConstantes.modulo3_c3_p303_a:currentModulo3.setC3_p303_a(valor);break;
                case SQLConstantes.modulo3_c3_p303_no_nacio:currentModulo3.setC3_p303_no_nacio(valor);break;
                case SQLConstantes.modulo3_c3_p304:currentModulo3.setC3_p304(valor);break;
                case SQLConstantes.modulo3_c3_p304_o:currentModulo3.setC3_p304_o(valor);break;
                case SQLConstantes.modulo3_c3_p305:currentModulo3.setC3_p305(valor);break;
               // case SQLConstantes.modulo3_c3_p305_o:currentModulo3.setC3_p305_o(valor);break;
                case SQLConstantes.modulo3_c3_p306_1:currentModulo3.setC3_p306_1(valor);break;
                case SQLConstantes.modulo3_c3_p306_2:currentModulo3.setC3_p306_2(valor);break;
                case SQLConstantes.modulo3_c3_p306_3:currentModulo3.setC3_p306_3(valor);break;
                case SQLConstantes.modulo3_c3_p306_4:currentModulo3.setC3_p306_4(valor);break;
                case SQLConstantes.modulo3_c3_p306_5:currentModulo3.setC3_p306_5(valor);break;
                case SQLConstantes.modulo3_c3_p306_6:currentModulo3.setC3_p306_6(valor);break;
                case SQLConstantes.modulo3_c3_p306_7:currentModulo3.setC3_p306_7(valor);break;
                case SQLConstantes.modulo3_c3_p306_o:currentModulo3.setC3_p306_o(valor);break;
                //case SQLConstantes.modulo3_c3_p306:currentModulo3.setC3_p306(valor);break;//---
                //case SQLConstantes.modulo3_c3_p307:currentModulo3.setC3_p307(valor);break;
                case SQLConstantes.modulo3_c3_p307_1:currentModulo3.setC3_p307_1(valor);break;
                /*case SQLConstantes.modulo3_c3_p307_2:currentModulo3.setC3_p307_2(valor);break;
                case SQLConstantes.modulo3_c3_p307_3:currentModulo3.setC3_p307_3(valor);break;
                case SQLConstantes.modulo3_c3_p307_4:currentModulo3.setC3_p307_4(valor);break;
                case SQLConstantes.modulo3_c3_p307_5:currentModulo3.setC3_p307_5(valor);break;
                case SQLConstantes.modulo3_c3_p307_6:currentModulo3.setC3_p307_6(valor);break;
                case SQLConstantes.modulo3_c3_p307_7:currentModulo3.setC3_p307_7(valor);break;
                case SQLConstantes.modulo3_c3_p307_8:currentModulo3.setC3_p307_8(valor);break;
                case SQLConstantes.modulo3_c3_p307_9:currentModulo3.setC3_p307_9(valor);break;
                case SQLConstantes.modulo3_c3_p307_10:currentModulo3.setC3_p307_10(valor);break;
                case SQLConstantes.modulo3_c3_p307_11:currentModulo3.setC3_p307_11(valor);break;
                case SQLConstantes.modulo3_c3_p307_12:currentModulo3.setC3_p307_12(valor);break;
                case SQLConstantes.modulo3_c3_p307_13:currentModulo3.setC3_p307_13(valor);break;*/
                case SQLConstantes.modulo3_c3_p307_o6:currentModulo3.setC3_p307_o6(valor);break;
                case SQLConstantes.modulo3_c3_p307_a:currentModulo3.setC3_p307_a(valor);break;
                case SQLConstantes.modulo3_c3_p307a_o:currentModulo3.setC3_p307a_o(valor);break;
               // case SQLConstantes.modulo3_c3_p307_o12:currentModulo3.setC3_p307_o12(valor);break;
                // case SQLConstantes.modulo3_c3_p307_d:currentModulo3.setC3_p307_d(valor);break;
                //  case SQLConstantes.modulo3_c3_p307_m:currentModulo3.setC3_p307_m(valor);break;
                //  case SQLConstantes.modulo3_c3_p307_a:currentModulo3.setC3_p307_a(valor);break;
                case SQLConstantes.modulo3_c3_p308:currentModulo3.setC3_p308(valor);break;
                //   case SQLConstantes.modulo3_c3_p308_e:currentModulo3.setC3_p308_e(valor);break;
                //    case SQLConstantes.modulo3_c3_p308_m:currentModulo3.setC3_p308_m(valor);break;
                //   case SQLConstantes.modulo3_c3_p308_e_seleccion:currentModulo3.setC3_p308_e_seleccion(valor);break;
                //   case SQLConstantes.modulo3_c3_p308_m_seleccion:currentModulo3.setC3_p308_m_seleccion(valor);break;
                case SQLConstantes.modulo3_c3_p309:currentModulo3.setC3_p309(valor);break;
                case SQLConstantes.modulo3_c3_p309_o:currentModulo3.setC3_p309_o(valor);break;
                case SQLConstantes.modulo3_c3_p309_1:currentModulo3.setC3_p309_1(valor);break;
                case SQLConstantes.modulo3_c3_p309_1_o:currentModulo3.setC3_p309_1_o(valor);break;
                case SQLConstantes.modulo3_c3_p310_e:currentModulo3.setC3_p310_e(valor);break;
                case SQLConstantes.modulo3_c3_p310_e_o:currentModulo3.setC3_p310_e_o(valor);break;
                case SQLConstantes.modulo3_c3_p310_m:currentModulo3.setC3_p310_m(valor);break;
                case SQLConstantes.modulo3_c3_p310_m_o:currentModulo3.setC3_p310_m_o(valor);break;
                case SQLConstantes.modulo3_c3_p310_e_seleccion:currentModulo3.setC3_p310_e_seleccion(valor);break;
                case SQLConstantes.modulo3_c3_p310_m_seleccion:currentModulo3.setC3_p310_m_seleccion(valor);break;
//                case SQLConstantes.modulo3_c3_p310_1:currentModulo3.setC3_p310_1(valor);break;
//                case SQLConstantes.modulo3_c3_p310_2:currentModulo3.setC3_p310_2(valor);break;
//                case SQLConstantes.modulo3_c3_p310_3:currentModulo3.setC3_p310_3(valor);break;
//                case SQLConstantes.modulo3_c3_p310_4:currentModulo3.setC3_p310_4(valor);break;
                case SQLConstantes.modulo3_c3_p311:currentModulo3.setC3_p311(valor);break;
                case SQLConstantes.modulo3_c3_p312:currentModulo3.setC3_p312(valor);break;
                case SQLConstantes.modulo3_c3_p312_o:currentModulo3.setC3_p312_o(valor);break;
//                case SQLConstantes.modulo3_c3_p312_dist:currentModulo3.setC3_p312_dist(valor);break;
//                case SQLConstantes.modulo3_c3_p312_prov:currentModulo3.setC3_p312_prov(valor);break;
//                case SQLConstantes.modulo3_c3_p312_dep:currentModulo3.setC3_p312_dep(valor);break;
                case SQLConstantes.modulo3_c3_p313:currentModulo3.setC3_p313(valor);break;
//                case SQLConstantes.modulo3_c3_p314:currentModulo3.setC3_p314(valor);break;
//                case SQLConstantes.modulo3_c3_p314_o:currentModulo3.setC3_p314_o(valor);break;
//                case SQLConstantes.modulo3_c3_p315_1:currentModulo3.setC3_p315_1(valor);break;
//                case SQLConstantes.modulo3_c3_p315_2:currentModulo3.setC3_p315_2(valor);break;
//                case SQLConstantes.modulo3_c3_p315_3:currentModulo3.setC3_p315_3(valor);break;
//                case SQLConstantes.modulo3_c3_p315_4:currentModulo3.setC3_p315_4(valor);break;
//                case SQLConstantes.modulo3_c3_p315_5:currentModulo3.setC3_p315_5(valor);break;
//                case SQLConstantes.modulo3_c3_p315_6:currentModulo3.setC3_p315_6(valor);break;
//                case SQLConstantes.modulo3_c3_p315_7:currentModulo3.setC3_p315_7(valor);break;
//                case SQLConstantes.modulo3_c3_p315_8:currentModulo3.setC3_p315_8(valor);break;
//                case SQLConstantes.modulo3_c3_p315_9:currentModulo3.setC3_p315_9(valor);break;
//                case SQLConstantes.modulo3_c3_p315_10:currentModulo3.setC3_p315_10(valor);break;
//                case SQLConstantes.modulo3_c3_p315_10_o:currentModulo3.setC3_p315_10_o(valor);break;
//                case SQLConstantes.modulo3_c3_p316:currentModulo3.setC3_p316(valor);break;
//                case SQLConstantes.modulo3_c3_p316_o:currentModulo3.setC3_p316_o(valor);break;
//                case SQLConstantes.modulo3_c3_p317:currentModulo3.setC3_p317(valor);break;
//                case SQLConstantes.modulo3_c3_p318:currentModulo3.setC3_p318(valor);break;
                case SQLConstantes.modulo3_obs_cap3:currentModulo3.setObs_cap3(valor);break;
                case SQLConstantes.modulo3_COB300:currentModulo3.setCOB300(valor);break;
            }
        }
    }

    //ESTE YA NO SE USA , TENEMOS QUE CORROBORRAR
    public void agregarVariableM3P309(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.modulo3_p309_id:currentM3Pregunta309.set_id(valor);break;
                case SQLConstantes.modulo3_p309_id_encuestado:currentM3Pregunta309.setId_encuestado(valor);break;
                case SQLConstantes.modulo3_p309_id_vivienda:currentM3Pregunta309.setId_vivienda(valor);break;
                case SQLConstantes.modulo3_p309_numero:currentM3Pregunta309.setNumero(valor);break;
                case SQLConstantes.modulo3_c3_p309_p:currentM3Pregunta309.setC3_p309_p(valor);break;
                case SQLConstantes.modulo3_c3_p309_p_nom:currentM3Pregunta309.setC3_p309_p_nom(valor);break;
                case SQLConstantes.modulo3_c3_p309_c:currentM3Pregunta309.setC3_p309_c(valor);break;
                case SQLConstantes.modulo3_c3_p309_mod:currentM3Pregunta309.setC3_p309_mod(valor);break;
                case SQLConstantes.modulo3_c3_p309_m:currentM3Pregunta309.setC3_p309_m(valor);break;
                case SQLConstantes.modulo3_c3_p309_a:currentM3Pregunta309.setC3_p309_a(valor);break;
                case SQLConstantes.modulo3_c3_p309_m_cod:currentM3Pregunta309.setC3_p309_m_cod(valor);break;
                case SQLConstantes.modulo3_c3_p309_a_cod:currentM3Pregunta309.setC3_p309_a_cod(valor);break;
            }
        }
    }

    public void agregarVariableM3P318(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.modulo3_p318_id:currentM3Pregunta318.set_id(valor);break;
                case SQLConstantes.modulo3_p318_idEncuestado:currentM3Pregunta318.setIdEncuestado(valor);break;
                case SQLConstantes.modulo3_p318_id_vivienda:currentM3Pregunta318.setId_vivienda(valor);break;
                case SQLConstantes.modulo3_p318_numero:currentM3Pregunta318.setNumero(valor);break;
                case SQLConstantes.modulo3_c3_p318_f:currentM3Pregunta318.setC3_p318_f(valor);break;
                case SQLConstantes.modulo3_c3_p318_s:currentM3Pregunta318.setC3_p318_s(valor);break;
                case SQLConstantes.modulo3_c3_p318_e:currentM3Pregunta318.setC3_p318_e(valor);break;
                case SQLConstantes.modulo3_c3_p318_p:currentM3Pregunta318.setC3_p318_p(valor);break;
            }
        }
    }

    public void agregarVariableModulo4(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.modulo4_id:currentModulo4.set_id(valor);break;
                case SQLConstantes.modulo4_id_informante:currentModulo4.setIdInformante(valor);break;
                case SQLConstantes.modulo4_id_hogar:currentModulo4.setIdHogar(valor);break;
                case SQLConstantes.modulo4_id_vivienda:currentModulo4.setIdVivienda(valor);break;
                case SQLConstantes.modulo4_c4_p401_1:currentModulo4.setC4_p401_1(valor);break;
                case SQLConstantes.modulo4_c4_p401_2:currentModulo4.setC4_p401_2(valor);break;
                case SQLConstantes.modulo4_c4_p401_3:currentModulo4.setC4_p401_3(valor);break;
                case SQLConstantes.modulo4_c4_p401_4:currentModulo4.setC4_p401_4(valor);break;
                case SQLConstantes.modulo4_c4_p401_4_o:currentModulo4.setC4_p401_4_o(valor);break;
                case SQLConstantes.modulo4_c4_p401_5:currentModulo4.setC4_p401_5(valor);break;
                case SQLConstantes.modulo4_c4_p402:currentModulo4.setC4_p402(valor);break;
                case SQLConstantes.modulo4_c4_p403_1:currentModulo4.setC4_p403_1(valor);break;
                case SQLConstantes.modulo4_c4_p403_2:currentModulo4.setC4_p403_2(valor);break;
                case SQLConstantes.modulo4_c4_p403_3:currentModulo4.setC4_p403_3(valor);break;
                case SQLConstantes.modulo4_c4_p403_4:currentModulo4.setC4_p403_4(valor);break;
                case SQLConstantes.modulo4_c4_p403_5:currentModulo4.setC4_p403_5(valor);break;
                case SQLConstantes.modulo4_c4_p403_6:currentModulo4.setC4_p403_6(valor);break;
                case SQLConstantes.modulo4_c4_p403_7:currentModulo4.setC4_p403_7(valor);break;
                case SQLConstantes.modulo4_c4_p403_8:currentModulo4.setC4_p403_8(valor);break;
                case SQLConstantes.modulo4_c4_p403_9:currentModulo4.setC4_p403_9(valor);break;
                case SQLConstantes.modulo4_c4_p403_10:currentModulo4.setC4_p403_10(valor);break;
                case SQLConstantes.modulo4_c4_p403_11:currentModulo4.setC4_p403_11(valor);break;
                case SQLConstantes.modulo4_c4_p403_12:currentModulo4.setC4_p403_12(valor);break;
                case SQLConstantes.modulo4_c4_p403_13:currentModulo4.setC4_p403_13(valor);break;
                case SQLConstantes.modulo4_c4_p403_14:currentModulo4.setC4_p403_14(valor);break;
                case SQLConstantes.modulo4_c4_p403_14_o:currentModulo4.setC4_p403_14_o(valor);break;
                //case SQLConstantes.modulo4_c4_p403_o:currentModulo4.setC4_p403_o(valor);break;
                case SQLConstantes.modulo4_c4_p404:currentModulo4.setC4_p404(valor);break;
                case SQLConstantes.modulo4_c4_p405_1:currentModulo4.setC4_p405_1(valor);break;
                case SQLConstantes.modulo4_c4_p405_2:currentModulo4.setC4_p405_2(valor);break;
                case SQLConstantes.modulo4_c4_p405_3:currentModulo4.setC4_p405_3(valor);break;
                case SQLConstantes.modulo4_c4_p405_4:currentModulo4.setC4_p405_4(valor);break;
                case SQLConstantes.modulo4_c4_p405_5:currentModulo4.setC4_p405_5(valor);break;
//                case SQLConstantes.modulo4_c4_p405_6:currentModulo4.setC4_p405_6(valor);break;
//                case SQLConstantes.modulo4_c4_p405_7:currentModulo4.setC4_p405_7(valor);break;
                case SQLConstantes.modulo4_c4_p406_1:currentModulo4.setC4_p406_1(valor);break;
                case SQLConstantes.modulo4_c4_p406_2:currentModulo4.setC4_p406_2(valor);break;
                case SQLConstantes.modulo4_c4_p406_3:currentModulo4.setC4_p406_3(valor);break;
                case SQLConstantes.modulo4_c4_p406_4:currentModulo4.setC4_p406_4(valor);break;
                case SQLConstantes.modulo4_c4_p406_5:currentModulo4.setC4_p406_5(valor);break;
                case SQLConstantes.modulo4_c4_p406_6:currentModulo4.setC4_p406_6(valor);break;
                case SQLConstantes.modulo4_c4_p406_7:currentModulo4.setC4_p406_7(valor);break;
                case SQLConstantes.modulo4_c4_p406_7_o:currentModulo4.setC4_p406_7_o(valor);break;
                case SQLConstantes.modulo4_c4_p406_8:currentModulo4.setC4_p406_8(valor);break;
//                case SQLConstantes.modulo4_c4_p406_o:currentModulo4.setC4_p406_o(valor);break;
                case SQLConstantes.modulo4_c4_p407_1:currentModulo4.setC4_p407_1(valor);break;
                case SQLConstantes.modulo4_c4_p407_2:currentModulo4.setC4_p407_2(valor);break;
                case SQLConstantes.modulo4_c4_p407_3:currentModulo4.setC4_p407_3(valor);break;
                case SQLConstantes.modulo4_c4_p407_4:currentModulo4.setC4_p407_4(valor);break;
                case SQLConstantes.modulo4_c4_p407_5:currentModulo4.setC4_p407_5(valor);break;
                case SQLConstantes.modulo4_c4_p407_6:currentModulo4.setC4_p407_6(valor);break;
                case SQLConstantes.modulo4_c4_p407_7:currentModulo4.setC4_p407_7(valor);break;
                case SQLConstantes.modulo4_c4_p407_8:currentModulo4.setC4_p407_8(valor);break;
                case SQLConstantes.modulo4_c4_p407_9:currentModulo4.setC4_p407_9(valor);break;
                case SQLConstantes.modulo4_c4_p407_10:currentModulo4.setC4_p407_10(valor);break;
                case SQLConstantes.modulo4_c4_p407_11:currentModulo4.setC4_p407_11(valor);break;
                case SQLConstantes.modulo4_c4_p407_12:currentModulo4.setC4_p407_12(valor);break;
                case SQLConstantes.modulo4_c4_p407_13:currentModulo4.setC4_p407_13(valor);break;
                case SQLConstantes.modulo4_c4_p407_13_o:currentModulo4.setC4_p407_13_o(valor);break;
                //case SQLConstantes.modulo4_c4_p407_o:currentModulo4.setC4_p407_o(valor);break;
                case SQLConstantes.modulo4_c4_p408_1:currentModulo4.setC4_p408_1(valor);break;
                case SQLConstantes.modulo4_c4_p408_2:currentModulo4.setC4_p408_2(valor);break;
                case SQLConstantes.modulo4_c4_p408_3:currentModulo4.setC4_p408_3(valor);break;
                case SQLConstantes.modulo4_c4_p408_4:currentModulo4.setC4_p408_4(valor);break;
                case SQLConstantes.modulo4_c4_p408_5:currentModulo4.setC4_p408_5(valor);break;
                case SQLConstantes.modulo4_c4_p408_6:currentModulo4.setC4_p408_6(valor);break;
                case SQLConstantes.modulo4_c4_p409:currentModulo4.setC4_p409(valor);break;
                case SQLConstantes.modulo4_c4_p409_1:currentModulo4.setC4_p409_1(valor);break;
                case SQLConstantes.modulo4_c4_p409_o:currentModulo4.setC4_p409_o(valor);break;
                case SQLConstantes.modulo4_c4_p409_2:currentModulo4.setC4_p409_2(valor);break;
                case SQLConstantes.modulo4_c4_p409_nom:currentModulo4.setC4_p409_nom(valor);break;
                case SQLConstantes.modulo4_c4_p410a:currentModulo4.setC4_p410a(valor);break;
                case SQLConstantes.modulo4_c4_p410b:currentModulo4.setC4_p410b(valor);break;
                case SQLConstantes.modulo4_c4_p410:currentModulo4.setC4_p410(valor);break;
                case SQLConstantes.modulo4_c4_p411:currentModulo4.setC4_p411(valor);break;
//                case SQLConstantes.modulo4_c4_p411_1:currentModulo4.setC4_p411_1(valor);break;
//                case SQLConstantes.modulo4_c4_p411_2:currentModulo4.setC4_p411_2(valor);break;
//                case SQLConstantes.modulo4_c4_p411_3:currentModulo4.setC4_p411_3(valor);break;
//                case SQLConstantes.modulo4_c4_p411_4:currentModulo4.setC4_p411_4(valor);break;
//                case SQLConstantes.modulo4_c4_p411_5:currentModulo4.setC4_p411_5(valor);break;
//                case SQLConstantes.modulo4_c4_p411_6:currentModulo4.setC4_p411_6(valor);break;
//                case SQLConstantes.modulo4_c4_p411_7:currentModulo4.setC4_p411_7(valor);break;
//                case SQLConstantes.modulo4_c4_p411_8:currentModulo4.setC4_p411_8(valor);break;
//                case SQLConstantes.modulo4_c4_p411_9:currentModulo4.setC4_p411_9(valor);break;
//                case SQLConstantes.modulo4_c4_p411_10:currentModulo4.setC4_p411_10(valor);break;
//                case SQLConstantes.modulo4_c4_p411_11:currentModulo4.setC4_p411_11(valor);break;
//                case SQLConstantes.modulo4_c4_p411_12:currentModulo4.setC4_p411_12(valor);break;
//                case SQLConstantes.modulo4_c4_p411_13:currentModulo4.setC4_p411_13(valor);break;
//                case SQLConstantes.modulo4_c4_p411_14:currentModulo4.setC4_p411_14(valor);break;
//                case SQLConstantes.modulo4_c4_p411_o:currentModulo4.setC4_p411_o(valor);break;
                case SQLConstantes.modulo4_c4_p412:currentModulo4.setC4_p412(valor);break;
                //case SQLConstantes.modulo4_c4_p413:currentModulo4.setC4_p413(valor);break;
                case SQLConstantes.modulo4_c4_p413_1:currentModulo4.setC4_p413_1(valor);break;
                case SQLConstantes.modulo4_c4_p413_2:currentModulo4.setC4_p413_2(valor);break;
                case SQLConstantes.modulo4_c4_p413_3:currentModulo4.setC4_p413_3(valor);break;
                case SQLConstantes.modulo4_c4_p413_4:currentModulo4.setC4_p413_4(valor);break;
                case SQLConstantes.modulo4_c4_p413_5:currentModulo4.setC4_p413_5(valor);break;
                case SQLConstantes.modulo4_c4_p414:currentModulo4.setC4_p414(valor);break;
                case SQLConstantes.modulo4_c4_p415:currentModulo4.setC4_p415(valor);break;
                case SQLConstantes.modulo4_c4_p416_1:currentModulo4.setC4_p416_1(valor);break;
                case SQLConstantes.modulo4_c4_p416_2:currentModulo4.setC4_p416_2(valor);break;
                case SQLConstantes.modulo4_c4_p416_3:currentModulo4.setC4_p416_3(valor);break;
                case SQLConstantes.modulo4_c4_p416_4:currentModulo4.setC4_p416_4(valor);break;
                case SQLConstantes.modulo4_c4_p416_5:currentModulo4.setC4_p416_5(valor);break;
                case SQLConstantes.modulo4_c4_p416_5_o:currentModulo4.setC4_p416_5_o(valor);break;
                case SQLConstantes.modulo4_c4_p416_6:currentModulo4.setC4_p416_6(valor);break;
//                case SQLConstantes.modulo4_c4_p416_7:currentModulo4.setC4_p416_7(valor);break;
//                case SQLConstantes.modulo4_c4_p416_8:currentModulo4.setC4_p416_8(valor);break;
//                case SQLConstantes.modulo4_c4_p416_o:currentModulo4.setC4_p416_o(valor);break;
                case SQLConstantes.modulo4_c4_p417_1:currentModulo4.setC4_p417_1(valor);break;
                case SQLConstantes.modulo4_c4_p417_1_1:currentModulo4.setC4_p417_1_1(valor);break;
                case SQLConstantes.modulo4_c4_p417_1_2:currentModulo4.setC4_p417_1_2(valor);break;
                case SQLConstantes.modulo4_c4_p417_1_3:currentModulo4.setC4_p417_1_3(valor);break;
                case SQLConstantes.modulo4_c4_p417_1_4:currentModulo4.setC4_p417_1_4(valor);break;
                case SQLConstantes.modulo4_c4_p417_1_5:currentModulo4.setC4_p417_1_5(valor);break;
                case SQLConstantes.modulo4_c4_p417_1_6:currentModulo4.setC4_p417_1_6(valor);break;
                case SQLConstantes.modulo4_c4_p417_1_7:currentModulo4.setC4_p417_1_7(valor);break;
                case SQLConstantes.modulo4_c4_p417_1_8:currentModulo4.setC4_p417_1_8(valor);break;
                case SQLConstantes.modulo4_c4_p417_1_9:currentModulo4.setC4_p417_1_9(valor);break;
                case SQLConstantes.modulo4_c4_p417_1_10:currentModulo4.setC4_p417_1_10(valor);break;
                case SQLConstantes.modulo4_c4_p417_1_11:currentModulo4.setC4_p417_1_11(valor);break;
                case SQLConstantes.modulo4_c4_p417_1_12:currentModulo4.setC4_p417_1_12(valor);break;
                case SQLConstantes.modulo4_c4_p417_1_13:currentModulo4.setC4_p417_1_13(valor);break;
                case SQLConstantes.modulo4_c4_p417_1_13_o:currentModulo4.setC4_p417_1_13_o(valor);break;
               // case SQLConstantes.modulo4_c4_p417_1a:currentModulo4.setC4_p417_1a(valor);break;
               // case SQLConstantes.modulo4_c4_p417_1a_o:currentModulo4.setC4_p417_1a_o(valor);break;
                case SQLConstantes.modulo4_c4_p417_2:currentModulo4.setC4_p417_2(valor);break;
                case SQLConstantes.modulo4_c4_p417_3:currentModulo4.setC4_p417_3(valor);break;
                case SQLConstantes.modulo4_c4_p417_4:currentModulo4.setC4_p417_4(valor);break;
                case SQLConstantes.modulo4_c4_p417_4_o:currentModulo4.setC4_p417_4_o(valor);break;
                case SQLConstantes.modulo4_c4_p417_5:currentModulo4.setC4_p417_5(valor);break;
                case SQLConstantes.modulo4_c4_p417_6:currentModulo4.setC4_p417_6(valor);break;
                case SQLConstantes.modulo4_c4_p417_7:currentModulo4.setC4_p417_7(valor);break;
                case SQLConstantes.modulo4_c4_p417_8:currentModulo4.setC4_p417_8(valor);break;
                case SQLConstantes.modulo4_c4_p417_9:currentModulo4.setC4_p417_9(valor);break;
                case SQLConstantes.modulo4_c4_p417_7_o:currentModulo4.setC4_p417_7_o(valor);break;
                case SQLConstantes.modulo4_c4_p418:currentModulo4.setC4_p418(valor);break;
                case SQLConstantes.modulo4_c4_p418a:currentModulo4.setC4_p418a(valor);break;
                case SQLConstantes.modulo4_obs_cap4:currentModulo4.setObs_cap4(valor);break;
                case SQLConstantes.modulo4_COB400:currentModulo4.setCOB400(valor);break;
            }
        }
    }

    public void agregarVariableModulo5(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.modulo5_id:currentModulo5.set_id(valor);break;
                case SQLConstantes.modulo5_id_informante:currentModulo5.setIdInformante(valor);break;
                case SQLConstantes.modulo5_id_hogar:currentModulo5.setIdHogar(valor);break;
                case SQLConstantes.modulo5_id_vivienda:currentModulo5.setIdVivienda(valor);break;
                case SQLConstantes.modulo5_c5_p501a:currentModulo5.setC5_p501a(valor);break;
                case SQLConstantes.modulo5_c5_p501:currentModulo5.setC5_p501(valor);break;
                case SQLConstantes.modulo5_c5_p501b:currentModulo5.setC5_p501b(valor);break;
//                case SQLConstantes.modulo5_c5_p502_c:currentModulo5.setC5_p502_c(valor);break;
                case SQLConstantes.modulo5_c5_p502:currentModulo5.setC5_p502(valor);break;
               // case SQLConstantes.modulo5_c5_p502_eleccion:currentModulo5.setC5_p502_eleccion(valor);break;
                case SQLConstantes.modulo5_c5_p502_o:currentModulo5.setC5_p502_o(valor);break;
                case SQLConstantes.modulo5_c5_p503:currentModulo5.setC5_p503(valor);break;
                case SQLConstantes.modulo5_c5_p504:currentModulo5.setC5_p504(valor);break;
                case SQLConstantes.modulo5_c5_p504_anio:currentModulo5.setC5_p504_anio(valor);break;
                case SQLConstantes.modulo5_c5_p504_grado:currentModulo5.setC5_p504_grado(valor);break;
                case SQLConstantes.modulo5_c5_p504_ce:currentModulo5.setC5_p504_ce(valor);break;
                case SQLConstantes.modulo5_c5_p505:currentModulo5.setC5_p505(valor);break;
                case SQLConstantes.modulo5_c5_p506:currentModulo5.setC5_p506(valor);break;
                case SQLConstantes.modulo5_c5_p506a:currentModulo5.setC5_p506a(valor);break;
//                case SQLConstantes.modulo5_c5_p506_1:currentModulo5.setC5_p506_1(valor);break;
//                case SQLConstantes.modulo5_c5_p506_2:currentModulo5.setC5_p506_2(valor);break;
//                case SQLConstantes.modulo5_c5_p506_3:currentModulo5.setC5_p506_3(valor);break;
//                case SQLConstantes.modulo5_c5_p506_4:currentModulo5.setC5_p506_4(valor);break;
                case SQLConstantes.modulo5_c5_p507:currentModulo5.setC5_p507(valor);break;
                case SQLConstantes.modulo5_c5_p507_anio:currentModulo5.setC5_p507_anio(valor);break;
                case SQLConstantes.modulo5_c5_p507_grado:currentModulo5.setC5_p507_grado(valor);break;
                case SQLConstantes.modulo5_c5_p507_ce:currentModulo5.setC5_p507_ce(valor);break;
//                case SQLConstantes.modulo5_c5_p507_dist:currentModulo5.setC5_p507_dist(valor);break;
//                case SQLConstantes.modulo5_c5_p507_prov:currentModulo5.setC5_p507_prov(valor);break;
//                case SQLConstantes.modulo5_c5_p507_dep:currentModulo5.setC5_p507_dep(valor);break;
                case SQLConstantes.modulo5_c5_p508_1:currentModulo5.setC5_p508_1(valor);break;
                case SQLConstantes.modulo5_c5_p508_2:currentModulo5.setC5_p508_2(valor);break;
                case SQLConstantes.modulo5_c5_p508_3:currentModulo5.setC5_p508_3(valor);break;
                case SQLConstantes.modulo5_c5_p508_4:currentModulo5.setC5_p508_4(valor);break;
                case SQLConstantes.modulo5_c5_p508_4_o:currentModulo5.setC5_p508_4_o(valor);break;
                case SQLConstantes.modulo5_c5_p508_5:currentModulo5.setC5_p508_5(valor);break;
//                case SQLConstantes.modulo5_c5_p508_5:currentModulo5.setC5_p508_5(valor);break;
//                case SQLConstantes.modulo5_c5_p508_6:currentModulo5.setC5_p508_6(valor);break;
//                case SQLConstantes.modulo5_c5_p508_7:currentModulo5.setC5_p508_7(valor);break;
//                case SQLConstantes.modulo5_c5_p508_8:currentModulo5.setC5_p508_8(valor);break;
//                case SQLConstantes.modulo5_c5_p508_9:currentModulo5.setC5_p508_9(valor);break;
//                case SQLConstantes.modulo5_c5_p508_10:currentModulo5.setC5_p508_10(valor);break;
//                case SQLConstantes.modulo5_c5_p508_11:currentModulo5.setC5_p508_11(valor);break;
//                case SQLConstantes.modulo5_c5_p508_o:currentModulo5.setC5_p508_o(valor);break;
                case SQLConstantes.modulo5_c5_p509_1:currentModulo5.setC5_p509_1(valor);break;
                case SQLConstantes.modulo5_c5_p509_2:currentModulo5.setC5_p509_2(valor);break;
                case SQLConstantes.modulo5_c5_p509_3:currentModulo5.setC5_p509_3(valor);break;
                case SQLConstantes.modulo5_c5_p509_4:currentModulo5.setC5_p509_4(valor);break;
                case SQLConstantes.modulo5_c5_p509_5:currentModulo5.setC5_p509_5(valor);break;
                case SQLConstantes.modulo5_c5_p509_6:currentModulo5.setC5_p509_6(valor);break;
                case SQLConstantes.modulo5_c5_p509_7:currentModulo5.setC5_p509_7(valor);break;
                case SQLConstantes.modulo5_c5_p509_7_o:currentModulo5.setC5_p509_7_o(valor);break;
                case SQLConstantes.modulo5_c5_p509_8:currentModulo5.setC5_p509_8(valor);break;
                //case SQLConstantes.modulo5_c5_p509:currentModulo5.setC5_p509(valor);break;
                case SQLConstantes.modulo5_c5_p510_o:currentModulo5.setC5_p510_o(valor);break;
                case SQLConstantes.modulo5_c5_p510:currentModulo5.setC5_p510(valor);break;
                case SQLConstantes.modulo5_c5_p511:currentModulo5.setC5_p511(valor);break;
                case SQLConstantes.modulo5_c5_p511_o:currentModulo5.setC5_p511_o(valor);break;
                case SQLConstantes.modulo5_c5_p512:currentModulo5.setC5_p512(valor);break;
               // case SQLConstantes.modulo5_c5_p512_o:currentModulo5.setC5_p512_o(valor);break;
                case SQLConstantes.modulo5_c5_p513:currentModulo5.setC5_p513(valor);break;
               // case SQLConstantes.modulo5_c5_p513_o:currentModulo5.setC5_p513_o(valor);break;
                case SQLConstantes.modulo5_c5_p514:currentModulo5.setC5_p514(valor);break;
                case SQLConstantes.modulo5_c5_p514_o:currentModulo5.setC5_p514_o(valor);break;
                case SQLConstantes.modulo5_c5_p515:currentModulo5.setC5_p515(valor);break;
                case SQLConstantes.modulo5_c5_p515_o:currentModulo5.setC5_p515_o(valor);break;
                case SQLConstantes.modulo5_c5_p516:currentModulo5.setC5_p516(valor);break;
                case SQLConstantes.modulo5_c5_p516_o:currentModulo5.setC5_p516_o(valor);break;
                case SQLConstantes.modulo5_obs_cap5:currentModulo5.setObs_cap5(valor);break;
                case SQLConstantes.modulo5_COB500:currentModulo5.setCOB500(valor);break;
            }
        }
    }

    public void agregarVariableModulo6(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.modulo6_id:currentModulo6.set_id(valor);break;
                case SQLConstantes.modulo6_id_informante:currentModulo6.setIdInformante(valor);break;
                case SQLConstantes.modulo6_id_hogar:currentModulo6.setIdHogar(valor);break;
                case SQLConstantes.modulo6_id_vivienda:currentModulo6.setIdVivienda(valor);break;
                case SQLConstantes.modulo6_c6_p601:currentModulo6.setC6_p601(valor);break;
                case SQLConstantes.modulo6_c6_p602:currentModulo6.setC6_p602(valor);break;
                case SQLConstantes.modulo6_c6_p603:currentModulo6.setC6_p603(valor);break;
                case SQLConstantes.modulo6_c6_p604_1:currentModulo6.setC6_p604_1(valor);break;
//                case SQLConstantes.modulo6_c6_p604_2:currentModulo6.setC6_p604_2(valor);break;
//                case SQLConstantes.modulo6_c6_p604_3:currentModulo6.setC6_p604_3(valor);break;
//                case SQLConstantes.modulo6_c6_p604_4:currentModulo6.setC6_p604_4(valor);break;
//                case SQLConstantes.modulo6_c6_p604_5:currentModulo6.setC6_p604_5(valor);break;
//                case SQLConstantes.modulo6_c6_p604_6:currentModulo6.setC6_p604_6(valor);break;
//                case SQLConstantes.modulo6_c6_p604_7:currentModulo6.setC6_p604_7(valor);break;
//                case SQLConstantes.modulo6_c6_p604_8:currentModulo6.setC6_p604_8(valor);break;
//                case SQLConstantes.modulo6_c6_p604_9:currentModulo6.setC6_p604_9(valor);break;
//                case SQLConstantes.modulo6_c6_p604_10:currentModulo6.setC6_p604_10(valor);break;
//                case SQLConstantes.modulo6_c6_p604_11:currentModulo6.setC6_p604_11(valor);break;
//                case SQLConstantes.modulo6_c6_p604_o:currentModulo6.setC6_p604_o(valor);break;
                case SQLConstantes.modulo6_c6_p605_1:currentModulo6.setC6_p605_1(valor);break;
                case SQLConstantes.modulo6_c6_p605_2:currentModulo6.setC6_p605_2(valor);break;
                case SQLConstantes.modulo6_c6_p605_3:currentModulo6.setC6_p605_3(valor);break;
                case SQLConstantes.modulo6_c6_p605_4:currentModulo6.setC6_p605_4(valor);break;
                case SQLConstantes.modulo6_c6_p605_5:currentModulo6.setC6_p605_5(valor);break;
                case SQLConstantes.modulo6_c6_p605_6:currentModulo6.setC6_p605_6(valor);break;
                case SQLConstantes.modulo6_c6_p605_7:currentModulo6.setC6_p605_7(valor);break;
                case SQLConstantes.modulo6_c6_p605_8:currentModulo6.setC6_p605_8(valor);break;
                case SQLConstantes.modulo6_c6_p605_9:currentModulo6.setC6_p605_9(valor);break;
                case SQLConstantes.modulo6_c6_p605_10:currentModulo6.setC6_p605_10(valor);break;
                case SQLConstantes.modulo6_c6_p605_11:currentModulo6.setC6_p605_11(valor);break;
                case SQLConstantes.modulo6_c6_p605_12:currentModulo6.setC6_p605_12(valor);break;
                case SQLConstantes.modulo6_c6_p605_o:currentModulo6.setC6_p605_o(valor);break;
               // case SQLConstantes.modulo6_c6_p605:currentModulo6.setC6_p605(valor);break;
                case SQLConstantes.modulo6_c6_p606:currentModulo6.setC6_p606(valor);break;
                case SQLConstantes.modulo6_c6_p606_o:currentModulo6.setC6_p606_o(valor);break;
                case SQLConstantes.modulo6_c6_p607:currentModulo6.setC6_p607(valor);break;
                case SQLConstantes.modulo6_c6_p608:currentModulo6.setC6_p608(valor);break;
               // case SQLConstantes.modulo6_c6_p608_o:currentModulo6.setC6_p608_o(valor);break;
                case SQLConstantes.modulo6_c6_p609:currentModulo6.setC6_p609(valor);break;
                case SQLConstantes.modulo6_c6_p609_cod:currentModulo6.setC6_p609_cod(valor);break;
                case SQLConstantes.modulo6_c6_p610_pd:currentModulo6.setC6_p610_pd(valor);break;
//                case SQLConstantes.modulo6_c6_p610_pl:currentModulo6.setC6_p610_pl(valor);break;
//                case SQLConstantes.modulo6_c6_p610_pm:currentModulo6.setC6_p610_pm(valor);break;
//                case SQLConstantes.modulo6_c6_p610_pmi:currentModulo6.setC6_p610_pmi(valor);break;
//                case SQLConstantes.modulo6_c6_p610_pj:currentModulo6.setC6_p610_pj(valor);break;
//                case SQLConstantes.modulo6_c6_p610_pv:currentModulo6.setC6_p610_pv(valor);break;
//                case SQLConstantes.modulo6_c6_p610_ps:currentModulo6.setC6_p610_ps(valor);break;
//                case SQLConstantes.modulo6_c6_p610_pt:currentModulo6.setC6_p610_pt(valor);break;
//                case SQLConstantes.modulo6_c6_p610_sd:currentModulo6.setC6_p610_sd(valor);break;
//                case SQLConstantes.modulo6_c6_p610_sl:currentModulo6.setC6_p610_sl(valor);break;
//                case SQLConstantes.modulo6_c6_p610_sm:currentModulo6.setC6_p610_sm(valor);break;
//                case SQLConstantes.modulo6_c6_p610_smi:currentModulo6.setC6_p610_smi(valor);break;
//                case SQLConstantes.modulo6_c6_p610_sj:currentModulo6.setC6_p610_sj(valor);break;
//                case SQLConstantes.modulo6_c6_p610_sv:currentModulo6.setC6_p610_sv(valor);break;
//                case SQLConstantes.modulo6_c6_p610_ss:currentModulo6.setC6_p610_ss(valor);break;
//                case SQLConstantes.modulo6_c6_p610_st:currentModulo6.setC6_p610_st(valor);break;
//                case SQLConstantes.modulo6_c6_p610_t:currentModulo6.setC6_p610_t(valor);break;
                case SQLConstantes.modulo6_c6_p611:currentModulo6.setC6_p611(valor);break;
                case SQLConstantes.modulo6_c6_p611_cod:currentModulo6.setC6_p611_cod(valor);break;
//                case SQLConstantes.modulo6_c6_p611a:currentModulo6.setC6_p611a(valor);break;
//                case SQLConstantes.modulo6_c6_p611b:currentModulo6.setC6_p611b(valor);break;
                case SQLConstantes.modulo6_c6_p612:currentModulo6.setC6_p612(valor);break;
//                case SQLConstantes.modulo6_c6_p612_nro:currentModulo6.setC6_p612_nro(valor);break;
                case SQLConstantes.modulo6_c6_p613:currentModulo6.setC6_p613(valor);break;
                case SQLConstantes.modulo6_c6_p614_mon:currentModulo6.setC6_p614_mon(valor);break;
                case SQLConstantes.modulo6_c6_p614_esp:currentModulo6.setC6_p614_esp(valor);break;
                case SQLConstantes.modulo6_c6_p615_pd:currentModulo6.setC6_p615_pd(valor);break;
                case SQLConstantes.modulo6_c6_p615_pl:currentModulo6.setC6_p615_pl(valor);break;
                case SQLConstantes.modulo6_c6_p615_pm:currentModulo6.setC6_p615_pm(valor);break;
                case SQLConstantes.modulo6_c6_p615_pmi:currentModulo6.setC6_p615_pmi(valor);break;
                case SQLConstantes.modulo6_c6_p615_pj:currentModulo6.setC6_p615_pj(valor);break;
                case SQLConstantes.modulo6_c6_p615_pv:currentModulo6.setC6_p615_pv(valor);break;
                case SQLConstantes.modulo6_c6_p615_ps:currentModulo6.setC6_p615_ps(valor);break;
                case SQLConstantes.modulo6_c6_p615_pt:currentModulo6.setC6_p615_pt(valor);break;
                case SQLConstantes.modulo6_c6_p615_sd:currentModulo6.setC6_p615_sd(valor);break;
                case SQLConstantes.modulo6_c6_p615_sl:currentModulo6.setC6_p615_sl(valor);break;
                case SQLConstantes.modulo6_c6_p615_sm:currentModulo6.setC6_p615_sm(valor);break;
                case SQLConstantes.modulo6_c6_p615_smi:currentModulo6.setC6_p615_smi(valor);break;
                case SQLConstantes.modulo6_c6_p615_sj:currentModulo6.setC6_p615_sj(valor);break;
                case SQLConstantes.modulo6_c6_p615_sv:currentModulo6.setC6_p615_sv(valor);break;
                case SQLConstantes.modulo6_c6_p615_ss:currentModulo6.setC6_p615_ss(valor);break;
                case SQLConstantes.modulo6_c6_p615_st:currentModulo6.setC6_p615_st(valor);break;
                case SQLConstantes.modulo6_c6_p615_t:currentModulo6.setC6_p615_t(valor);break;
//                case SQLConstantes.modulo6_c6_p615_mon:currentModulo6.setC6_p615_mon(valor);break;
//                case SQLConstantes.modulo6_c6_p615_esp:currentModulo6.setC6_p615_esp(valor);break;
                case SQLConstantes.modulo6_c6_p616:currentModulo6.setC6_p616(valor);break;
                case SQLConstantes.modulo6_c6_p616_a:currentModulo6.setC6_p616_a(valor);break;
//                case SQLConstantes.modulo6_c6_p616_mon:currentModulo6.setC6_p616_mon(valor);break;
//                case SQLConstantes.modulo6_c6_p616_esp:currentModulo6.setC6_p616_esp(valor);break;
//                case SQLConstantes.modulo6_c6_p616_nas:currentModulo6.setC6_p616_nas(valor);break;
                case SQLConstantes.modulo6_c6_p617:currentModulo6.setC6_p617(valor);break;
//                case SQLConstantes.modulo6_c6_p617_dist:currentModulo6.setC6_p617_dist(valor);break;
//                case SQLConstantes.modulo6_c6_p617_prov:currentModulo6.setC6_p617_prov(valor);break;
//                case SQLConstantes.modulo6_c6_p617_dep:currentModulo6.setC6_p617_dep(valor);break;
                case SQLConstantes.modulo6_c6_p618:currentModulo6.setC6_p618(valor);break;
                case SQLConstantes.modulo6_c6_p619:currentModulo6.setC6_p619(valor);break;
//                case SQLConstantes.modulo6_c6_p619_o:currentModulo6.setC6_p619_o(valor);break;
                case SQLConstantes.modulo6_c6_p620:currentModulo6.setC6_p620(valor);break;
                case SQLConstantes.modulo6_c6_p620_o:currentModulo6.setC6_p620_o(valor);break;
                case SQLConstantes.modulo6_c6_p621:currentModulo6.setC6_p621(valor);break;
                case SQLConstantes.modulo6_c6_p622_mon:currentModulo6.setC6_p622_mon(valor);break;
                case SQLConstantes.modulo6_c6_p622_esp:currentModulo6.setC6_p622_esp(valor);break;
//                case SQLConstantes.modulo6_c6_p622:currentModulo6.setC6_p622(valor);break;
//                case SQLConstantes.modulo6_c6_p622_o:currentModulo6.setC6_p622_o(valor);break;
                case SQLConstantes.modulo6_c6_p623_mon:currentModulo6.setC6_p623_mon(valor);break;
                case SQLConstantes.modulo6_c6_p623_esp:currentModulo6.setC6_p623_esp(valor);break;
                case SQLConstantes.modulo6_c6_p623_nas:currentModulo6.setC6_p623_nas(valor);break;
//                case SQLConstantes.modulo6_c6_p623:currentModulo6.setC6_p623(valor);break;
//                case SQLConstantes.modulo6_c6_p623_o:currentModulo6.setC6_p623_o(valor);break;
                case SQLConstantes.modulo6_c6_p624_mon:currentModulo6.setC6_p624_mon(valor);break;
                case SQLConstantes.modulo6_c6_p624_esp:currentModulo6.setC6_p624_esp(valor);break;
                case SQLConstantes.modulo6_c6_p624_nas:currentModulo6.setC6_p624_nas(valor);break;
                case SQLConstantes.modulo6_c6_p624_nas2:currentModulo6.setC6_p624_nas2(valor);break;
//                case SQLConstantes.modulo6_c6_p624:currentModulo6.setC6_p624(valor);break;
                case SQLConstantes.modulo6_c6_p625:currentModulo6.setC6_p625(valor);break;
                case SQLConstantes.modulo6_c6_p625_cod_dist:currentModulo6.setC6_p625_cod_dist(valor);break;
                case SQLConstantes.modulo6_c6_p625_dist:currentModulo6.setC6_p625_dist(valor);break;
                case SQLConstantes.modulo6_c6_p625_cod_prov:currentModulo6.setC6_p625_cod_prov(valor);break;
                case SQLConstantes.modulo6_c6_p625_prov:currentModulo6.setC6_p625_prov(valor);break;
                case SQLConstantes.modulo6_c6_p625_cod_depa:currentModulo6.setC6_p625_cod_depa(valor);break;
                case SQLConstantes.modulo6_c6_p625_depa:currentModulo6.setC6_p625_depa(valor);break;
//                case SQLConstantes.modulo6_c6_p625_1:currentModulo6.setC6_p625_1(valor);break;
//                case SQLConstantes.modulo6_c6_p625_2:currentModulo6.setC6_p625_2(valor);break;
//                case SQLConstantes.modulo6_c6_p625_3:currentModulo6.setC6_p625_3(valor);break;
//                case SQLConstantes.modulo6_c6_p625_4:currentModulo6.setC6_p625_4(valor);break;
//                case SQLConstantes.modulo6_c6_p625_5:currentModulo6.setC6_p625_5(valor);break;
//                case SQLConstantes.modulo6_c6_p625_6:currentModulo6.setC6_p625_6(valor);break;
//                case SQLConstantes.modulo6_c6_p625_o:currentModulo6.setC6_p625_o(valor);break;
                case SQLConstantes.modulo6_c6_p626:currentModulo6.setC6_p626(valor);break;
                case SQLConstantes.modulo6_c6_p627:currentModulo6.setC6_p627(valor);break;
                case SQLConstantes.modulo6_c6_p628:currentModulo6.setC6_p628(valor);break;
                case SQLConstantes.modulo6_c6_p628_o:currentModulo6.setC6_p628_o(valor);break;
                case SQLConstantes.modulo6_c6_p629:currentModulo6.setC6_p629(valor);break;
//                case SQLConstantes.modulo6_c6_p629_1:currentModulo6.setC6_p629_1(valor);break;
//                case SQLConstantes.modulo6_c6_p629_2:currentModulo6.setC6_p629_2(valor);break;
//                case SQLConstantes.modulo6_c6_p629_3:currentModulo6.setC6_p629_3(valor);break;
//                case SQLConstantes.modulo6_c6_p629_4:currentModulo6.setC6_p629_4(valor);break;
//                case SQLConstantes.modulo6_c6_p629_o:currentModulo6.setC6_p629_o(valor);break;
//                case SQLConstantes.modulo6_c6_p629_1_f:currentModulo6.setC6_p629_1_f(valor);break;
//                case SQLConstantes.modulo6_c6_p629_1_m:currentModulo6.setC6_p629_1_m(valor);break;
//                case SQLConstantes.modulo6_c6_p629_2_f:currentModulo6.setC6_p629_2_f(valor);break;
//                case SQLConstantes.modulo6_c6_p629_2_m:currentModulo6.setC6_p629_2_m(valor);break;
//                case SQLConstantes.modulo6_c6_p629_3_f:currentModulo6.setC6_p629_3_f(valor);break;
//                case SQLConstantes.modulo6_c6_p629_3_m:currentModulo6.setC6_p629_3_m(valor);break;
//                case SQLConstantes.modulo6_c6_p629_4_f:currentModulo6.setC6_p629_4_f(valor);break;
//                case SQLConstantes.modulo6_c6_p629_4_m:currentModulo6.setC6_p629_4_m(valor);break;
                case SQLConstantes.modulo6_c6_p630:currentModulo6.setC6_p630(valor);break;
                case SQLConstantes.modulo6_c6_p631:currentModulo6.setC6_p631(valor);break;
                case SQLConstantes.modulo6_c6_p631_o:currentModulo6.setC6_p631_o(valor);break;
                case SQLConstantes.modulo6_c6_p632_1:currentModulo6.setC6_p632_1(valor);break;
                case SQLConstantes.modulo6_c6_p632_2:currentModulo6.setC6_p632_2(valor);break;
                case SQLConstantes.modulo6_c6_p632_3:currentModulo6.setC6_p632_3(valor);break;
                case SQLConstantes.modulo6_c6_p632_4:currentModulo6.setC6_p632_4(valor);break;
                case SQLConstantes.modulo6_c6_p632_5:currentModulo6.setC6_p632_5(valor);break;
                case SQLConstantes.modulo6_c6_p632_6:currentModulo6.setC6_p632_6(valor);break;
                case SQLConstantes.modulo6_c6_p632_7:currentModulo6.setC6_p632_7(valor);break;
                case SQLConstantes.modulo6_c6_p632_8:currentModulo6.setC6_p632_8(valor);break;
                case SQLConstantes.modulo6_c6_p632_9:currentModulo6.setC6_p632_9(valor);break;
                case SQLConstantes.modulo6_c6_p632_10:currentModulo6.setC6_p632_10(valor);break;
                case SQLConstantes.modulo6_c6_p632_10_o:currentModulo6.setC6_p632_10_o(valor);break;
                case SQLConstantes.modulo6_c6_p632i:currentModulo6.setC6_p632i(valor);break;
                //case SQLConstantes.modulo6_c6_p632_11:currentModulo6.setC6_p632_11(valor);break;
                case SQLConstantes.modulo6_c6_p633:currentModulo6.setC6_p633(valor);break;
                case SQLConstantes.modulo6_c6_p634_1:currentModulo6.setC6_p634_1(valor);break;
                case SQLConstantes.modulo6_c6_p634_2:currentModulo6.setC6_p634_2(valor);break;
                case SQLConstantes.modulo6_c6_p634_3:currentModulo6.setC6_p634_3(valor);break;
                case SQLConstantes.modulo6_c6_p634_4:currentModulo6.setC6_p634_4(valor);break;
                case SQLConstantes.modulo6_c6_p634_5:currentModulo6.setC6_p634_5(valor);break;
                case SQLConstantes.modulo6_c6_p634_6:currentModulo6.setC6_p634_6(valor);break;
                case SQLConstantes.modulo6_c6_p634_7:currentModulo6.setC6_p634_7(valor);break;
                //case SQLConstantes.modulo6_c6_p634_6_o:currentModulo6.setC6_p634_6_o(valor);break;
                case SQLConstantes.modulo6_c6_p634_7_o:currentModulo6.setC6_p634_7_o(valor);break;
//                case SQLConstantes.modulo6_c6_p630_1:currentModulo6.setC6_p630_1(valor);break;
//                case SQLConstantes.modulo6_c6_p630_1med:currentModulo6.setC6_p630_1med(valor);break;
//                case SQLConstantes.modulo6_c6_p630_1o:currentModulo6.setC6_p630_1o(valor);break;
//                case SQLConstantes.modulo6_c6_p630_1frec:currentModulo6.setC6_p630_1frec(valor);break;
//                case SQLConstantes.modulo6_c6_p630_1frec_o:currentModulo6.setC6_p630_1frec_o(valor);break;
//                case SQLConstantes.modulo6_c6_p630_1mont:currentModulo6.setC6_p630_1mont(valor);break;
//                case SQLConstantes.modulo6_c6_p630_2:currentModulo6.setC6_p630_2(valor);break;
//                case SQLConstantes.modulo6_c6_p630_2med:currentModulo6.setC6_p630_2med(valor);break;
//                case SQLConstantes.modulo6_c6_p630_2o:currentModulo6.setC6_p630_2o(valor);break;
//                case SQLConstantes.modulo6_c6_p630_2mont:currentModulo6.setC6_p630_2mont(valor);break;
//                case SQLConstantes.modulo6_c6_p630_2frec:currentModulo6.setC6_p630_2frec(valor);break;
//                case SQLConstantes.modulo6_c6_p630_2frec_o:currentModulo6.setC6_p630_2frec_o(valor);break;
                case SQLConstantes.modulo6_c6_p635:currentModulo6.setC6_p635(valor);break;
                case SQLConstantes.modulo6_c6_p636:currentModulo6.setC6_p636(valor);break;
                case SQLConstantes.modulo6_c6_p637:currentModulo6.setC6_p637(valor);break;
                case SQLConstantes.modulo6_c6_p638_1:currentModulo6.setC6_p638_1(valor);break;
                case SQLConstantes.modulo6_c6_p638_1_frec:currentModulo6.setC6_p638_1_frec(valor);break;
                case SQLConstantes.modulo6_c6_p638_1_monto:currentModulo6.setC6_p638_1_monto(valor);break;
                case SQLConstantes.modulo6_c6_p638_2:currentModulo6.setC6_p638_2(valor);break;
                case SQLConstantes.modulo6_c6_p638_2_frec:currentModulo6.setC6_p638_2_frec(valor);break;
                case SQLConstantes.modulo6_c6_p638_2_monto:currentModulo6.setC6_p638_2_monto(valor);break;
                case SQLConstantes.modulo6_c6_p638_3:currentModulo6.setC6_p638_3(valor);break;
                case SQLConstantes.modulo6_c6_p638_3_frec:currentModulo6.setC6_p638_3_frec(valor);break;
                case SQLConstantes.modulo6_c6_p638_3_monto:currentModulo6.setC6_p638_3_monto(valor);break;
                case SQLConstantes.modulo6_c6_p638_4:currentModulo6.setC6_p638_4(valor);break;
                case SQLConstantes.modulo6_c6_p638_4_frec:currentModulo6.setC6_p638_4_frec(valor);break;
                case SQLConstantes.modulo6_c6_p638_4_monto:currentModulo6.setC6_p638_4_monto(valor);break;

                case SQLConstantes.modulo6_c6_p639_1:currentModulo6.setC6_p639_1(valor);break;
                case SQLConstantes.modulo6_c6_p639_1_med:currentModulo6.setC6_p639_1_med(valor);break;
                case SQLConstantes.modulo6_c6_p639_1_med_o:currentModulo6.setC6_p639_1_med_o(valor);break;
                case SQLConstantes.modulo6_c6_p639_1_frec:currentModulo6.setC6_p639_1_frec(valor);break;
                case SQLConstantes.modulo6_c6_p639_1_frec_o:currentModulo6.setC6_p639_1_frec_o(valor);break;
                case SQLConstantes.modulo6_c6_p639_1_monto:currentModulo6.setC6_p639_1_monto(valor);break;

                case SQLConstantes.modulo6_c6_p639_2:currentModulo6.setC6_p639_2(valor);break;
                case SQLConstantes.modulo6_c6_p639_2_med:currentModulo6.setC6_p639_2_med(valor);break;
                case SQLConstantes.modulo6_c6_p639_2_med_o:currentModulo6.setC6_p639_2_med_o(valor);break;
                case SQLConstantes.modulo6_c6_p639_2_frec:currentModulo6.setC6_p639_2_frec(valor);break;
                case SQLConstantes.modulo6_c6_p639_2_frec_o:currentModulo6.setC6_p639_2_frec_o(valor);break;
                case SQLConstantes.modulo6_c6_p639_2_monto:currentModulo6.setC6_p639_2_monto(valor);break;

                case SQLConstantes.modulo6_obs_cap6:currentModulo6.setObs_cap6(valor);break;
                case SQLConstantes.modulo6_COB600:currentModulo6.setCOB600(valor);break;
            }
        }
    }

    public void agregarVariableModulo7(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.modulo7_id:currentModulo7.set_id(valor);break;
                case SQLConstantes.modulo7_id_informante:currentModulo7.setIdInformante(valor);break;
                case SQLConstantes.modulo7_id_hogar:currentModulo7.setIdHogar(valor);break;
                case SQLConstantes.modulo7_id_vivienda:currentModulo7.setIdVivienda(valor);break;
                case SQLConstantes.modulo7_c7_p701:currentModulo7.setC7_p701(valor);break;
                case SQLConstantes.modulo7_c7_p702_1:currentModulo7.setC7_p702_1(valor);break;
                case SQLConstantes.modulo7_c7_p702_2:currentModulo7.setC7_p702_2(valor);break;
                case SQLConstantes.modulo7_c7_p702_3:currentModulo7.setC7_p702_3(valor);break;
                case SQLConstantes.modulo7_c7_p702_4:currentModulo7.setC7_p702_4(valor);break;
                case SQLConstantes.modulo7_c7_p702_5:currentModulo7.setC7_p702_5(valor);break;
                case SQLConstantes.modulo7_c7_p702_6:currentModulo7.setC7_p702_6(valor);break;
                case SQLConstantes.modulo7_c7_p702_7:currentModulo7.setC7_p702_7(valor);break;
                case SQLConstantes.modulo7_c7_p702_7_o:currentModulo7.setC7_p702_7_o(valor);break;
//                case SQLConstantes.modulo7_c7_p702_8:currentModulo7.setC7_p702_8(valor);break;
//                case SQLConstantes.modulo7_c7_p702_9:currentModulo7.setC7_p702_9(valor);break;
//                case SQLConstantes.modulo7_c7_p702_10:currentModulo7.setC7_p702_10(valor);break;
//                case SQLConstantes.modulo7_c7_p702_o:currentModulo7.setC7_p702_o(valor);break;
                case SQLConstantes.modulo7_c7_p703_1:currentModulo7.setC7_p703_1(valor);break;
                case SQLConstantes.modulo7_c7_p703_2:currentModulo7.setC7_p703_2(valor);break;
                case SQLConstantes.modulo7_c7_p703_3:currentModulo7.setC7_p703_3(valor);break;
                case SQLConstantes.modulo7_c7_p703_4:currentModulo7.setC7_p703_4(valor);break;
                case SQLConstantes.modulo7_c7_p703_5:currentModulo7.setC7_p703_5(valor);break;
                case SQLConstantes.modulo7_c7_p703_6:currentModulo7.setC7_p703_6(valor);break;
                case SQLConstantes.modulo7_c7_p703_7:currentModulo7.setC7_p703_7(valor);break;
                case SQLConstantes.modulo7_c7_p703_8:currentModulo7.setC7_p703_8(valor);break;
                case SQLConstantes.modulo7_c7_p703_9:currentModulo7.setC7_p703_9(valor);break;
                case SQLConstantes.modulo7_c7_p703_10:currentModulo7.setC7_p703_10(valor);break;
                case SQLConstantes.modulo7_c7_p703_10_o:currentModulo7.setC7_p703_10_o(valor);break;
//                case SQLConstantes.modulo7_c7_p703:currentModulo7.setC7_p703(valor);break;
                case SQLConstantes.modulo7_c7_p704_1:currentModulo7.setC7_p704_1(valor);break;
                case SQLConstantes.modulo7_c7_p704_2:currentModulo7.setC7_p704_2(valor);break;
                case SQLConstantes.modulo7_c7_p704_3:currentModulo7.setC7_p704_3(valor);break;
                case SQLConstantes.modulo7_c7_p704_4:currentModulo7.setC7_p704_4(valor);break;
                case SQLConstantes.modulo7_c7_p704_5:currentModulo7.setC7_p704_5(valor);break;
                case SQLConstantes.modulo7_c7_p704_6:currentModulo7.setC7_p704_6(valor);break;
                case SQLConstantes.modulo7_c7_p704_7:currentModulo7.setC7_p704_7(valor);break;
                case SQLConstantes.modulo7_c7_p704_8:currentModulo7.setC7_p704_8(valor);break;
                case SQLConstantes.modulo7_c7_p704_9:currentModulo7.setC7_p704_9(valor);break;
                case SQLConstantes.modulo7_c7_p704_8_o:currentModulo7.setC7_p704_8_o(valor);break;
               // case SQLConstantes.modulo7_c7_p704_o:currentModulo7.setC7_p704_o(valor);break;
                case SQLConstantes.modulo7_c7_p705:currentModulo7.setC7_p705(valor);break;
//                case SQLConstantes.modulo7_c7_p705_1:currentModulo7.setC7_p705_1(valor);break;
//                case SQLConstantes.modulo7_c7_p705_2:currentModulo7.setC7_p705_2(valor);break;
//                case SQLConstantes.modulo7_c7_p705_3:currentModulo7.setC7_p705_3(valor);break;
//                case SQLConstantes.modulo7_c7_p705_4:currentModulo7.setC7_p705_4(valor);break;
//                case SQLConstantes.modulo7_c7_p705_5:currentModulo7.setC7_p705_5(valor);break;
//                case SQLConstantes.modulo7_c7_p705_6:currentModulo7.setC7_p705_6(valor);break;
//                case SQLConstantes.modulo7_c7_p705_7:currentModulo7.setC7_p705_7(valor);break;
//                case SQLConstantes.modulo7_c7_p705_o:currentModulo7.setC7_p705_o(valor);break;
                case SQLConstantes.modulo7_c7_p706_1:currentModulo7.setC7_p706_1(valor);break;
                case SQLConstantes.modulo7_c7_p706_2:currentModulo7.setC7_p706_2(valor);break;
                case SQLConstantes.modulo7_c7_p706_3:currentModulo7.setC7_p706_3(valor);break;
                case SQLConstantes.modulo7_c7_p706_4:currentModulo7.setC7_p706_4(valor);break;
                case SQLConstantes.modulo7_c7_p706_5:currentModulo7.setC7_p706_5(valor);break;
                case SQLConstantes.modulo7_c7_p706_6:currentModulo7.setC7_p706_6(valor);break;
                case SQLConstantes.modulo7_c7_p706_6_o:currentModulo7.setC7_p706_6_o(valor);break;
                case SQLConstantes.modulo7_c7_p706_7:currentModulo7.setC7_p706_7(valor);break;
                case SQLConstantes.modulo7_c7_p706_8:currentModulo7.setC7_p706_8(valor);break;
                case SQLConstantes.modulo7_c7_p706_9:currentModulo7.setC7_p706_9(valor);break;
                case SQLConstantes.modulo7_c7_p706_8_o:currentModulo7.setC7_p706_8_o(valor);break;
//                case SQLConstantes.modulo7_c7_p706:currentModulo7.setC7_p706(valor);break;
                case SQLConstantes.modulo7_c7_p707_1:currentModulo7.setC7_p707_1(valor);break;
                case SQLConstantes.modulo7_c7_p707_2:currentModulo7.setC7_p707_2(valor);break;
                case SQLConstantes.modulo7_c7_p707_3:currentModulo7.setC7_p707_3(valor);break;
                case SQLConstantes.modulo7_c7_p707_4:currentModulo7.setC7_p707_4(valor);break;
                case SQLConstantes.modulo7_c7_p707_5:currentModulo7.setC7_p707_5(valor);break;
                case SQLConstantes.modulo7_c7_p707_6:currentModulo7.setC7_p707_6(valor);break;
                case SQLConstantes.modulo7_c7_p707_7:currentModulo7.setC7_p707_7(valor);break;
                case SQLConstantes.modulo7_c7_p707_8:currentModulo7.setC7_p707_8(valor);break;
                case SQLConstantes.modulo7_c7_p707_8_o:currentModulo7.setC7_p707_8_o(valor);break;
                case SQLConstantes.modulo7_c7_p707_9:currentModulo7.setC7_p707_9(valor);break;
               // case SQLConstantes.modulo7_c7_p707_o:currentModulo7.setC7_p707_o(valor);break;
                case SQLConstantes.modulo7_c7_p708_1:currentModulo7.setC7_p708_1(valor);break;
                case SQLConstantes.modulo7_c7_p708_2:currentModulo7.setC7_p708_2(valor);break;
                case SQLConstantes.modulo7_c7_p708_3:currentModulo7.setC7_p708_3(valor);break;
//                case SQLConstantes.modulo7_c7_p708_4:currentModulo7.setC7_p708_4(valor);break;
//                case SQLConstantes.modulo7_c7_p708_5:currentModulo7.setC7_p708_5(valor);break;
                case SQLConstantes.modulo7_c7_p709_1:currentModulo7.setC7_p709_1(valor);break;
                case SQLConstantes.modulo7_c7_p709_2:currentModulo7.setC7_p709_2(valor);break;
                case SQLConstantes.modulo7_c7_p709_3:currentModulo7.setC7_p709_3(valor);break;
                case SQLConstantes.modulo7_c7_p709_4:currentModulo7.setC7_p709_4(valor);break;
                case SQLConstantes.modulo7_c7_p709_5:currentModulo7.setC7_p709_5(valor);break;
                case SQLConstantes.modulo7_c7_p709_6:currentModulo7.setC7_p709_6(valor);break;
                case SQLConstantes.modulo7_c7_p709_7:currentModulo7.setC7_p709_7(valor);break;
                case SQLConstantes.modulo7_c7_p709_8:currentModulo7.setC7_p709_8(valor);break;

                case SQLConstantes.modulo7_c7_p709_9:currentModulo7.setC7_p709_9(valor);break;
                case SQLConstantes.modulo7_c7_p709_10a:currentModulo7.setC7_p709_10a(valor);break;
                case SQLConstantes.modulo7_c7_p709_10:currentModulo7.setC7_p709_10(valor);break;
                case SQLConstantes.modulo7_c7_p709_10_o:currentModulo7.setC7_p709_10_o(valor);break;
                case SQLConstantes.modulo7_c7_p709_11:currentModulo7.setC7_p709_11(valor);break;
               // case SQLConstantes.modulo7_c7_p709_o:currentModulo7.setC7_p709_o(valor);break;
                case SQLConstantes.modulo7_obs_cap7:currentModulo7.setObs_cap7(valor);break;
                case SQLConstantes.modulo7_COB700:currentModulo7.setCOB700(valor);break;
            }
        }
    }

    public void agregarVariableModulo8(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.modulo8_id:currentModulo8.set_id(valor);break;
                case SQLConstantes.modulo8_id_informante:currentModulo8.setIdInformante(valor);break;
                case SQLConstantes.modulo8_id_hogar:currentModulo8.setIdHogar(valor);break;
                case SQLConstantes.modulo8_id_vivienda:currentModulo8.setIdVivienda(valor);break;
                case SQLConstantes.modulo8_c8_p801:currentModulo8.setC8_p801(valor);break;
                case SQLConstantes.modulo8_c8_p802_1:currentModulo8.setC8_p802_1(valor);break;
                case SQLConstantes.modulo8_c8_p802_2:currentModulo8.setC8_p802_2(valor);break;
                case SQLConstantes.modulo8_c8_p802_3:currentModulo8.setC8_p802_3(valor);break;
                //case SQLConstantes.modulo8_c8_p802a_1:currentModulo8.setC8_p802a_1(valor);break;
                case SQLConstantes.modulo8_c8_p802a_1_1:currentModulo8.setC8_p802a_1_1(valor);break;
                case SQLConstantes.modulo8_c8_p802a_1_2:currentModulo8.setC8_p802a_1_2(valor);break;
                case SQLConstantes.modulo8_c8_p802a_1_3:currentModulo8.setC8_p802a_1_3(valor);break;
                case SQLConstantes.modulo8_c8_p802a_1_4:currentModulo8.setC8_p802a_1_4(valor);break;
                case SQLConstantes.modulo8_c8_p802a_1_5:currentModulo8.setC8_p802a_1_5(valor);break;
                case SQLConstantes.modulo8_c8_p802a_1_6:currentModulo8.setC8_p802a_1_6(valor);break;
                case SQLConstantes.modulo8_c8_p802a_1_7:currentModulo8.setC8_p802a_1_7(valor);break;
                case SQLConstantes.modulo8_c8_p802a_1_8:currentModulo8.setC8_p802a_1_8(valor);break;
                //case SQLConstantes.modulo8_c8_p802a_2:currentModulo8.setC8_p802a_2(valor);break;
                case SQLConstantes.modulo8_c8_p802a_2_1:currentModulo8.setC8_p802a_2_1(valor);break;
                case SQLConstantes.modulo8_c8_p802a_2_2:currentModulo8.setC8_p802a_2_2(valor);break;
                case SQLConstantes.modulo8_c8_p802a_2_3:currentModulo8.setC8_p802a_2_3(valor);break;
                case SQLConstantes.modulo8_c8_p802a_2_4:currentModulo8.setC8_p802a_2_4(valor);break;
                case SQLConstantes.modulo8_c8_p802a_2_5:currentModulo8.setC8_p802a_2_5(valor);break;
                case SQLConstantes.modulo8_c8_p802a_2_6:currentModulo8.setC8_p802a_2_6(valor);break;
                case SQLConstantes.modulo8_c8_p802a_2_7:currentModulo8.setC8_p802a_2_7(valor);break;
                case SQLConstantes.modulo8_c8_p802a_2_8:currentModulo8.setC8_p802a_2_8(valor);break;
                //case SQLConstantes.modulo8_c8_p802a_3:currentModulo8.setC8_p802a_3(valor);break;
                case SQLConstantes.modulo8_c8_p802a_3_1:currentModulo8.setC8_p802a_3_1(valor);break;
                case SQLConstantes.modulo8_c8_p802a_3_2:currentModulo8.setC8_p802a_3_2(valor);break;
                case SQLConstantes.modulo8_c8_p802a_3_3:currentModulo8.setC8_p802a_3_3(valor);break;
                case SQLConstantes.modulo8_c8_p802a_3_4:currentModulo8.setC8_p802a_3_4(valor);break;
                case SQLConstantes.modulo8_c8_p802a_3_5:currentModulo8.setC8_p802a_3_5(valor);break;
                case SQLConstantes.modulo8_c8_p802a_3_6:currentModulo8.setC8_p802a_3_6(valor);break;
                case SQLConstantes.modulo8_c8_p802a_3_7:currentModulo8.setC8_p802a_3_7(valor);break;
                case SQLConstantes.modulo8_c8_p802a_3_8:currentModulo8.setC8_p802a_3_8(valor);break;
                case SQLConstantes.modulo8_c8_p802a_1_o:currentModulo8.setC8_p802a_1_o(valor);break;
                case SQLConstantes.modulo8_c8_p802a_2_o:currentModulo8.setC8_p802a_2_o(valor);break;
                case SQLConstantes.modulo8_c8_p802a_3_o:currentModulo8.setC8_p802a_3_o(valor);break;
                // case SQLConstantes.modulo8_c8_p802:currentModulo8.setC8_p802(valor);break;
                case SQLConstantes.modulo8_c8_p803:currentModulo8.setC8_p803(valor);break;
                case SQLConstantes.modulo8_c8_p804_1:currentModulo8.setC8_p804_1(valor);break;
                case SQLConstantes.modulo8_c8_p804_2:currentModulo8.setC8_p804_2(valor);break;
                case SQLConstantes.modulo8_c8_p804_3:currentModulo8.setC8_p804_3(valor);break;
                case SQLConstantes.modulo8_c8_p804_4:currentModulo8.setC8_p804_4(valor);break;
                case SQLConstantes.modulo8_c8_p804_5:currentModulo8.setC8_p804_5(valor);break;
                case SQLConstantes.modulo8_c8_p804_6:currentModulo8.setC8_p804_6(valor);break;
                case SQLConstantes.modulo8_c8_p804_7:currentModulo8.setC8_p804_7(valor);break;
                case SQLConstantes.modulo8_c8_p804_8:currentModulo8.setC8_p804_8(valor);break;
                case SQLConstantes.modulo8_c8_p804_9:currentModulo8.setC8_p804_9(valor);break;
                case SQLConstantes.modulo8_c8_p804_10:currentModulo8.setC8_p804_10(valor);break;
                case SQLConstantes.modulo8_c8_p804_11:currentModulo8.setC8_p804_11(valor);break;
                case SQLConstantes.modulo8_c8_p804_12:currentModulo8.setC8_p804_12(valor);break;
                case SQLConstantes.modulo8_c8_p804_13:currentModulo8.setC8_p804_13(valor);break;
                case SQLConstantes.modulo8_c8_p804_14:currentModulo8.setC8_p804_14(valor);break;
                case SQLConstantes.modulo8_c8_p804_o:currentModulo8.setC8_p804_o(valor);break;
//                case SQLConstantes.modulo8_c8_p804:currentModulo8.setC8_p804(valor);break;
                case SQLConstantes.modulo8_c8_p805_1:currentModulo8.setC8_p805_1(valor);break;
                case SQLConstantes.modulo8_c8_p805_2:currentModulo8.setC8_p805_2(valor);break;
                case SQLConstantes.modulo8_c8_p805_3:currentModulo8.setC8_p805_3(valor);break;
                case SQLConstantes.modulo8_c8_p805_4:currentModulo8.setC8_p805_4(valor);break;
                case SQLConstantes.modulo8_c8_p805_5:currentModulo8.setC8_p805_5(valor);break;
                case SQLConstantes.modulo8_c8_p805_6:currentModulo8.setC8_p805_6(valor);break;
                case SQLConstantes.modulo8_c8_p805_7:currentModulo8.setC8_p805_7(valor);break;
                case SQLConstantes.modulo8_c8_p805_8:currentModulo8.setC8_p805_8(valor);break;
                case SQLConstantes.modulo8_c8_p805_9:currentModulo8.setC8_p805_9(valor);break;

                case SQLConstantes.modulo8_c8_p806_1:currentModulo8.setC8_p806_1(valor);break;
//                case SQLConstantes.modulo8_c8_p806_2:currentModulo8.setC8_p806_2(valor);break;
//                case SQLConstantes.modulo8_c8_p806_3:currentModulo8.setC8_p806_3(valor);break;
//                case SQLConstantes.modulo8_c8_p806_4:currentModulo8.setC8_p806_4(valor);break;
//                case SQLConstantes.modulo8_c8_p806_5:currentModulo8.setC8_p806_5(valor);break;
//                case SQLConstantes.modulo8_c8_p806_6:currentModulo8.setC8_p806_6(valor);break;
                case SQLConstantes.modulo8_c8_p807:currentModulo8.setC8_p807(valor);break;
//                case SQLConstantes.modulo8_c8_p808_1:currentModulo8.setC8_p808_1(valor);break;
//                case SQLConstantes.modulo8_c8_p808_2:currentModulo8.setC8_p808_2(valor);break;
//                case SQLConstantes.modulo8_c8_p808_3:currentModulo8.setC8_p808_3(valor);break;
//                case SQLConstantes.modulo8_c8_p808_4:currentModulo8.setC8_p808_4(valor);break;
//                case SQLConstantes.modulo8_c8_p808_5:currentModulo8.setC8_p808_5(valor);break;
//                case SQLConstantes.modulo8_c8_p808_6:currentModulo8.setC8_p808_6(valor);break;
//                case SQLConstantes.modulo8_c8_p808_7:currentModulo8.setC8_p808_7(valor);break;
//                case SQLConstantes.modulo8_c8_p808_8:currentModulo8.setC8_p808_8(valor);break;
//                case SQLConstantes.modulo8_c8_p808_9:currentModulo8.setC8_p808_9(valor);break;
//                case SQLConstantes.modulo8_c8_p808_10:currentModulo8.setC8_p808_10(valor);break;
//                case SQLConstantes.modulo8_c8_p808_11:currentModulo8.setC8_p808_11(valor);break;
//                case SQLConstantes.modulo8_c8_p808_12:currentModulo8.setC8_p808_12(valor);break;
//                case SQLConstantes.modulo8_c8_p808_13:currentModulo8.setC8_p808_13(valor);break;
//                case SQLConstantes.modulo8_c8_p808_o:currentModulo8.setC8_p808_o(valor);break;
//                case SQLConstantes.modulo8_c8_p809:currentModulo8.setC8_p809(valor);break;
//                case SQLConstantes.modulo8_c8_p810_1:currentModulo8.setC8_p810_1(valor);break;
//                case SQLConstantes.modulo8_c8_p810_2:currentModulo8.setC8_p810_2(valor);break;
//                case SQLConstantes.modulo8_c8_p810_3:currentModulo8.setC8_p810_3(valor);break;
//                case SQLConstantes.modulo8_c8_p810_4:currentModulo8.setC8_p810_4(valor);break;
//                case SQLConstantes.modulo8_c8_p810_5:currentModulo8.setC8_p810_5(valor);break;
//                case SQLConstantes.modulo8_c8_p810_6:currentModulo8.setC8_p810_6(valor);break;
//                case SQLConstantes.modulo8_c8_p810_7:currentModulo8.setC8_p810_7(valor);break;
//                case SQLConstantes.modulo8_c8_p810_8:currentModulo8.setC8_p810_8(valor);break;
//                case SQLConstantes.modulo8_c8_p810_9:currentModulo8.setC8_p810_9(valor);break;
//                case SQLConstantes.modulo8_c8_p810_10:currentModulo8.setC8_p810_10(valor);break;
//                case SQLConstantes.modulo8_c8_p810_11:currentModulo8.setC8_p810_11(valor);break;
//                case SQLConstantes.modulo8_c8_p810_12:currentModulo8.setC8_p810_12(valor);break;
//                case SQLConstantes.modulo8_c8_p810_13:currentModulo8.setC8_p810_13(valor);break;
//                case SQLConstantes.modulo8_c8_p810_o:currentModulo8.setC8_p810_o(valor);break;
//                case SQLConstantes.modulo8_c8_p811:currentModulo8.setC8_p811(valor);break;
//                case SQLConstantes.modulo8_c8_p812:currentModulo8.setC8_p812(valor);break;
//                case SQLConstantes.modulo8_c8_p813_1:currentModulo8.setC8_p813_1(valor);break;
//                case SQLConstantes.modulo8_c8_p813_2:currentModulo8.setC8_p813_2(valor);break;
//                case SQLConstantes.modulo8_c8_p813_3:currentModulo8.setC8_p813_3(valor);break;
//                case SQLConstantes.modulo8_c8_p813_4:currentModulo8.setC8_p813_4(valor);break;
//                case SQLConstantes.modulo8_c8_p813_5:currentModulo8.setC8_p813_5(valor);break;
//                case SQLConstantes.modulo8_c8_p813_6:currentModulo8.setC8_p813_6(valor);break;
//                case SQLConstantes.modulo8_c8_p813_7:currentModulo8.setC8_p813_7(valor);break;
//                case SQLConstantes.modulo8_c8_p813_8:currentModulo8.setC8_p813_8(valor);break;
//                case SQLConstantes.modulo8_c8_p813_9:currentModulo8.setC8_p813_9(valor);break;
//                case SQLConstantes.modulo8_c8_p813_10:currentModulo8.setC8_p813_10(valor);break;
//                case SQLConstantes.modulo8_c8_p813_11:currentModulo8.setC8_p813_11(valor);break;
//                case SQLConstantes.modulo8_c8_p813_12:currentModulo8.setC8_p813_12(valor);break;
//                case SQLConstantes.modulo8_c8_p813_13:currentModulo8.setC8_p813_13(valor);break;
//                case SQLConstantes.modulo8_c8_p813_14:currentModulo8.setC8_p813_14(valor);break;
//                case SQLConstantes.modulo8_c8_p813_o:currentModulo8.setC8_p813_o(valor);break;
//                case SQLConstantes.modulo8_c8_p814_1:currentModulo8.setC8_p814_1(valor);break;
//                case SQLConstantes.modulo8_c8_p814_2:currentModulo8.setC8_p814_2(valor);break;
//                case SQLConstantes.modulo8_c8_p814_3:currentModulo8.setC8_p814_3(valor);break;
//                case SQLConstantes.modulo8_c8_p814_4:currentModulo8.setC8_p814_4(valor);break;
//                case SQLConstantes.modulo8_c8_p814_5:currentModulo8.setC8_p814_5(valor);break;
//                case SQLConstantes.modulo8_c8_p814_6:currentModulo8.setC8_p814_6(valor);break;
//                case SQLConstantes.modulo8_c8_p814_7:currentModulo8.setC8_p814_7(valor);break;
//                case SQLConstantes.modulo8_c8_p814_8:currentModulo8.setC8_p814_8(valor);break;
//                case SQLConstantes.modulo8_c8_p815:currentModulo8.setC8_p815(valor);break;
//                case SQLConstantes.modulo8_c8_p816_1:currentModulo8.setC8_p816_1(valor);break;
//                case SQLConstantes.modulo8_c8_p816_2:currentModulo8.setC8_p816_2(valor);break;
//                case SQLConstantes.modulo8_c8_p816_3:currentModulo8.setC8_p816_3(valor);break;
//                case SQLConstantes.modulo8_c8_p816_4:currentModulo8.setC8_p816_4(valor);break;
//                case SQLConstantes.modulo8_c8_p816_5:currentModulo8.setC8_p816_5(valor);break;
//                case SQLConstantes.modulo8_c8_p816_6:currentModulo8.setC8_p816_6(valor);break;
//                case SQLConstantes.modulo8_c8_p816_7:currentModulo8.setC8_p816_7(valor);break;
//                case SQLConstantes.modulo8_c8_p816_8:currentModulo8.setC8_p816_8(valor);break;
//                case SQLConstantes.modulo8_c8_p816_9:currentModulo8.setC8_p816_9(valor);break;
//                case SQLConstantes.modulo8_c8_p816_10:currentModulo8.setC8_p816_10(valor);break;
//                case SQLConstantes.modulo8_c8_p816_11:currentModulo8.setC8_p816_11(valor);break;
//                case SQLConstantes.modulo8_c8_p816_12:currentModulo8.setC8_p816_12(valor);break;
//                case SQLConstantes.modulo8_c8_p816_13:currentModulo8.setC8_p816_13(valor);break;
//                case SQLConstantes.modulo8_c8_p816_o:currentModulo8.setC8_p816_o(valor);break;
//                case SQLConstantes.modulo8_c8_p817:currentModulo8.setC8_p817(valor);break;
//                case SQLConstantes.modulo8_c8_p818:currentModulo8.setC8_p818(valor);break;
//                case SQLConstantes.modulo8_c8_p819_1:currentModulo8.setC8_p819_1(valor);break;
//                case SQLConstantes.modulo8_c8_p819_2:currentModulo8.setC8_p819_2(valor);break;
//                case SQLConstantes.modulo8_c8_p819_3:currentModulo8.setC8_p819_3(valor);break;
//                case SQLConstantes.modulo8_c8_p819_4:currentModulo8.setC8_p819_4(valor);break;
//                case SQLConstantes.modulo8_c8_p819_5:currentModulo8.setC8_p819_5(valor);break;
//                case SQLConstantes.modulo8_c8_p819_6:currentModulo8.setC8_p819_6(valor);break;
//                case SQLConstantes.modulo8_c8_p819_7:currentModulo8.setC8_p819_7(valor);break;
//                case SQLConstantes.modulo8_c8_p819_8:currentModulo8.setC8_p819_8(valor);break;
//                case SQLConstantes.modulo8_c8_p819_9:currentModulo8.setC8_p819_9(valor);break;
//                case SQLConstantes.modulo8_c8_p819_10:currentModulo8.setC8_p819_10(valor);break;
//                case SQLConstantes.modulo8_c8_p819_11:currentModulo8.setC8_p819_11(valor);break;
//                case SQLConstantes.modulo8_c8_p819_12:currentModulo8.setC8_p819_12(valor);break;
//                case SQLConstantes.modulo8_c8_p819_13:currentModulo8.setC8_p819_13(valor);break;
//                case SQLConstantes.modulo8_c8_p819_14:currentModulo8.setC8_p819_14(valor);break;
//                case SQLConstantes.modulo8_c8_p819_o:currentModulo8.setC8_p819_o(valor);break;
//                case SQLConstantes.modulo8_c8_p820_1:currentModulo8.setC8_p820_1(valor);break;
//                case SQLConstantes.modulo8_c8_p820_2:currentModulo8.setC8_p820_2(valor);break;
//                case SQLConstantes.modulo8_c8_p820_3:currentModulo8.setC8_p820_3(valor);break;
//                case SQLConstantes.modulo8_c8_p820_4:currentModulo8.setC8_p820_4(valor);break;
//                case SQLConstantes.modulo8_c8_p820_5:currentModulo8.setC8_p820_5(valor);break;
//                case SQLConstantes.modulo8_c8_p820_6:currentModulo8.setC8_p820_6(valor);break;
//                case SQLConstantes.modulo8_c8_p820_7:currentModulo8.setC8_p820_7(valor);break;
//                case SQLConstantes.modulo8_c8_p820_8:currentModulo8.setC8_p820_8(valor);break;
//                case SQLConstantes.modulo8_c8_p820_9:currentModulo8.setC8_p820_9(valor);break;
//                case SQLConstantes.modulo8_c8_p820_10:currentModulo8.setC8_p820_10(valor);break;
//                case SQLConstantes.modulo8_c8_p820_11:currentModulo8.setC8_p820_11(valor);break;
//                case SQLConstantes.modulo8_c8_p820_o:currentModulo8.setC8_p820_o(valor);break;
//                case SQLConstantes.modulo8_c8_p821_1:currentModulo8.setC8_p821_1(valor);break;
//                case SQLConstantes.modulo8_c8_p821_2:currentModulo8.setC8_p821_2(valor);break;
//                case SQLConstantes.modulo8_c8_p821_3:currentModulo8.setC8_p821_3(valor);break;
//                case SQLConstantes.modulo8_c8_p821_4:currentModulo8.setC8_p821_4(valor);break;
//                case SQLConstantes.modulo8_c8_p821_5:currentModulo8.setC8_p821_5(valor);break;
//                case SQLConstantes.modulo8_c8_p821_6:currentModulo8.setC8_p821_6(valor);break;
//                case SQLConstantes.modulo8_c8_p821_7:currentModulo8.setC8_p821_7(valor);break;
//                case SQLConstantes.modulo8_c8_p821_8:currentModulo8.setC8_p821_8(valor);break;
//                case SQLConstantes.modulo8_c8_p822:currentModulo8.setC8_p822(valor);break;
//                case SQLConstantes.modulo8_c8_p823_1:currentModulo8.setC8_p823_1(valor);break;
//                case SQLConstantes.modulo8_c8_p823_2:currentModulo8.setC8_p823_2(valor);break;
//                case SQLConstantes.modulo8_c8_p823_3:currentModulo8.setC8_p823_3(valor);break;
//                case SQLConstantes.modulo8_c8_p823_4:currentModulo8.setC8_p823_4(valor);break;
//                case SQLConstantes.modulo8_c8_p823_5:currentModulo8.setC8_p823_5(valor);break;
//                case SQLConstantes.modulo8_c8_p823_o:currentModulo8.setC8_p823_o(valor);break;
                case SQLConstantes.modulo8_obs_cap8:currentModulo8.setObs_cap8(valor);break;
                case SQLConstantes.modulo8_email:currentModulo8.setEmail(valor);break;
                case SQLConstantes.modulo8_COB800:currentModulo8.setCOB800(valor);break;
            }
        }
    }

    public void agregarVariableFragmentVivienda(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.fragments_vivienda_id: pojoFragmentVivienda.set_id(valor);break;
                case SQLConstantes.fragments_vivienda_caratula: pojoFragmentVivienda.setCaratula(valor);break;
                case SQLConstantes.fragments_vivienda_hogares: pojoFragmentVivienda.setHogares(valor);break;
            }
        }
    }

    public void agregarVariableFragmentHogar(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.fragments_hogar_id:currentPojoFragmentHogar.set_id(valor);break;
                case SQLConstantes.fragments_hogar_id_vivienda:currentPojoFragmentHogar.setId_vivienda(valor);break;
                case SQLConstantes.fragments_hogar_visitas_encuestador:currentPojoFragmentHogar.setVisitas_encuestador(valor);break;
                case SQLConstantes.fragments_hogar_visitas_supervisor:currentPojoFragmentHogar.setVisitas_supervisor(valor);break;
                case SQLConstantes.fragments_hogar_funcionarios:currentPojoFragmentHogar.setFuncionarios(valor);break;
                case SQLConstantes.fragments_hogar_p101p107:currentPojoFragmentHogar.setP101p107(valor);break;
                case SQLConstantes.fragments_hogar_p108p113:currentPojoFragmentHogar.setP108p113(valor);break;
                case SQLConstantes.fragments_hogar_p201p207:currentPojoFragmentHogar.setP201p207(valor);break;
            }
        }
    }

    public void agregarVariableFragmentEncuestado(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.fragments_id:currentPojoFragment.set_id(valor);break;
                case SQLConstantes.fragments_id_vivienda:currentPojoFragment.setId_vivienda(valor);break;
                case SQLConstantes.fragments_p301p305:currentPojoFragment.setP301p305(valor);break;
                case SQLConstantes.fragments_p306p308:currentPojoFragment.setP306p308(valor);break;
                case SQLConstantes.fragments_p309:currentPojoFragment.setP309(valor);break;
                case SQLConstantes.fragments_p310p312:currentPojoFragment.setP310p312(valor);break;
                case SQLConstantes.fragments_p313p317:currentPojoFragment.setP313p317(valor);break;
                case SQLConstantes.fragments_p318:currentPojoFragment.setP318(valor);break;
                case SQLConstantes.fragments_p401p404:currentPojoFragment.setP401p404(valor);break;
                case SQLConstantes.fragments_p405p407:currentPojoFragment.setP405p407(valor);break;
                case SQLConstantes.fragments_p408p410:currentPojoFragment.setP408p410(valor);break;
                case SQLConstantes.fragments_p411p416:currentPojoFragment.setP411p416(valor);break;
                case SQLConstantes.fragments_p501p505:currentPojoFragment.setP501p505(valor);break;
                case SQLConstantes.fragments_p506p507:currentPojoFragment.setP506p507(valor);break;
                case SQLConstantes.fragments_p508p511:currentPojoFragment.setP508p511(valor);break;
                case SQLConstantes.fragments_p512p513:currentPojoFragment.setP512p513(valor);break;
                case SQLConstantes.fragments_p601p604:currentPojoFragment.setP601p604(valor);break;
                case SQLConstantes.fragments_p605p608:currentPojoFragment.setP605p608(valor);break;
                case SQLConstantes.fragments_p609p612:currentPojoFragment.setP609p612(valor);break;
                case SQLConstantes.fragments_p613p617:currentPojoFragment.setP613p617(valor);break;
                case SQLConstantes.fragments_p618p621:currentPojoFragment.setP618p621(valor);break;
                case SQLConstantes.fragments_p622p625:currentPojoFragment.setP622p625(valor);break;
                case SQLConstantes.fragments_p626p629:currentPojoFragment.setP626p629(valor);break;
                case SQLConstantes.fragments_p630:currentPojoFragment.setP630(valor);break;
                case SQLConstantes.fragments_p701p705:currentPojoFragment.setP701p705(valor);break;
                case SQLConstantes.fragments_p706p709:currentPojoFragment.setP706p709(valor);break;
                case SQLConstantes.fragments_p801p804:currentPojoFragment.setP801p804(valor);break;
                case SQLConstantes.fragments_p805p808:currentPojoFragment.setP805p808(valor);break;
                case SQLConstantes.fragments_p809p812:currentPojoFragment.setP809p812(valor);break;
                case SQLConstantes.fragments_p813p816:currentPojoFragment.setP813p816(valor);break;
                case SQLConstantes.fragments_p817p820:currentPojoFragment.setP817p820(valor);break;
                case SQLConstantes.fragments_p821p823:currentPojoFragment.setP821p823(valor);break;
            }
        }
    }

    public void agregarVariableLayout(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.layouts_id:currentPojoLayout.set_id(valor);break;
                case SQLConstantes.layouts_id_vivienda:currentPojoLayout.setId_vivienda(valor);break;
                case SQLConstantes.layouts_p301:currentPojoLayout.setP301(valor);break;
                case SQLConstantes.layouts_p302:currentPojoLayout.setP302(valor);break;
                case SQLConstantes.layouts_p303:currentPojoLayout.setP303(valor);break;
                case SQLConstantes.layouts_p304:currentPojoLayout.setP304(valor);break;
                case SQLConstantes.layouts_p305:currentPojoLayout.setP305(valor);break;
                case SQLConstantes.layouts_p306:currentPojoLayout.setP306(valor);break;
                case SQLConstantes.layouts_p307:currentPojoLayout.setP307(valor);break;
                case SQLConstantes.layouts_p308:currentPojoLayout.setP308(valor);break;
                case SQLConstantes.layouts_p309:currentPojoLayout.setP309(valor);break;
                case SQLConstantes.layouts_p309_1:currentPojoLayout.setP309_1(valor);break;
                case SQLConstantes.layouts_p310:currentPojoLayout.setP310(valor);break;
                case SQLConstantes.layouts_p311:currentPojoLayout.setP311(valor);break;
                case SQLConstantes.layouts_p312:currentPojoLayout.setP312(valor);break;
                case SQLConstantes.layouts_p313:currentPojoLayout.setP313(valor);break;
                case SQLConstantes.layouts_p314:currentPojoLayout.setP314(valor);break;
                case SQLConstantes.layouts_p315:currentPojoLayout.setP315(valor);break;
                case SQLConstantes.layouts_p316:currentPojoLayout.setP316(valor);break;
                case SQLConstantes.layouts_p317:currentPojoLayout.setP317(valor);break;
                case SQLConstantes.layouts_p318:currentPojoLayout.setP318(valor);break;
                case SQLConstantes.layouts_p401:currentPojoLayout.setP401(valor);break;
                case SQLConstantes.layouts_p402:currentPojoLayout.setP402(valor);break;
                case SQLConstantes.layouts_p403:currentPojoLayout.setP403(valor);break;
                case SQLConstantes.layouts_p404:currentPojoLayout.setP404(valor);break;
                case SQLConstantes.layouts_p405:currentPojoLayout.setP405(valor);break;
                case SQLConstantes.layouts_p406:currentPojoLayout.setP406(valor);break;
                case SQLConstantes.layouts_p407:currentPojoLayout.setP407(valor);break;
                case SQLConstantes.layouts_p408:currentPojoLayout.setP408(valor);break;
                case SQLConstantes.layouts_p409:currentPojoLayout.setP409(valor);break;
                case SQLConstantes.layouts_p410:currentPojoLayout.setP410(valor);break;
                case SQLConstantes.layouts_p411:currentPojoLayout.setP411(valor);break;
                case SQLConstantes.layouts_p412:currentPojoLayout.setP412(valor);break;
                case SQLConstantes.layouts_p413:currentPojoLayout.setP413(valor);break;
                case SQLConstantes.layouts_p414:currentPojoLayout.setP414(valor);break;
                case SQLConstantes.layouts_p415:currentPojoLayout.setP415(valor);break;
                case SQLConstantes.layouts_p416:currentPojoLayout.setP416(valor);break;
                case SQLConstantes.layouts_p501:currentPojoLayout.setP501(valor);break;
                case SQLConstantes.layouts_p502:currentPojoLayout.setP502(valor);break;
                case SQLConstantes.layouts_p503:currentPojoLayout.setP503(valor);break;
                case SQLConstantes.layouts_p504:currentPojoLayout.setP504(valor);break;
                case SQLConstantes.layouts_p505:currentPojoLayout.setP505(valor);break;
                case SQLConstantes.layouts_p506:currentPojoLayout.setP506(valor);break;
                case SQLConstantes.layouts_p507:currentPojoLayout.setP507(valor);break;
                case SQLConstantes.layouts_p508:currentPojoLayout.setP508(valor);break;
                case SQLConstantes.layouts_p509:currentPojoLayout.setP509(valor);break;
                case SQLConstantes.layouts_p510:currentPojoLayout.setP510(valor);break;
                case SQLConstantes.layouts_p511:currentPojoLayout.setP511(valor);break;
                case SQLConstantes.layouts_p512:currentPojoLayout.setP512(valor);break;
                case SQLConstantes.layouts_p513:currentPojoLayout.setP513(valor);break;
                case SQLConstantes.layouts_p601:currentPojoLayout.setP601(valor);break;
                case SQLConstantes.layouts_p602:currentPojoLayout.setP602(valor);break;
                case SQLConstantes.layouts_p603:currentPojoLayout.setP603(valor);break;
                case SQLConstantes.layouts_p604:currentPojoLayout.setP604(valor);break;
                case SQLConstantes.layouts_p605:currentPojoLayout.setP605(valor);break;
                case SQLConstantes.layouts_p606:currentPojoLayout.setP606(valor);break;
                case SQLConstantes.layouts_p607:currentPojoLayout.setP607(valor);break;
                case SQLConstantes.layouts_p608:currentPojoLayout.setP608(valor);break;
                case SQLConstantes.layouts_p609:currentPojoLayout.setP609(valor);break;
                case SQLConstantes.layouts_p610:currentPojoLayout.setP610(valor);break;
                case SQLConstantes.layouts_p611:currentPojoLayout.setP611(valor);break;
                case SQLConstantes.layouts_p611a:currentPojoLayout.setP611a(valor);break;
                case SQLConstantes.layouts_p611b:currentPojoLayout.setP611b(valor);break;
                case SQLConstantes.layouts_p612:currentPojoLayout.setP612(valor);break;
                case SQLConstantes.layouts_p613:currentPojoLayout.setP613(valor);break;
                case SQLConstantes.layouts_p614:currentPojoLayout.setP614(valor);break;
                case SQLConstantes.layouts_p615:currentPojoLayout.setP615(valor);break;
                case SQLConstantes.layouts_p616:currentPojoLayout.setP616(valor);break;
                case SQLConstantes.layouts_p617:currentPojoLayout.setP617(valor);break;
                case SQLConstantes.layouts_p618:currentPojoLayout.setP618(valor);break;
                case SQLConstantes.layouts_p619:currentPojoLayout.setP619(valor);break;
                case SQLConstantes.layouts_p620:currentPojoLayout.setP620(valor);break;
                case SQLConstantes.layouts_p621:currentPojoLayout.setP621(valor);break;
                case SQLConstantes.layouts_p622:currentPojoLayout.setP622(valor);break;
                case SQLConstantes.layouts_p623:currentPojoLayout.setP623(valor);break;
                case SQLConstantes.layouts_p624:currentPojoLayout.setP624(valor);break;
                case SQLConstantes.layouts_p625:currentPojoLayout.setP625(valor);break;
                case SQLConstantes.layouts_p626:currentPojoLayout.setP626(valor);break;
                case SQLConstantes.layouts_p627:currentPojoLayout.setP627(valor);break;
                case SQLConstantes.layouts_p628:currentPojoLayout.setP628(valor);break;
                case SQLConstantes.layouts_p629:currentPojoLayout.setP629(valor);break;
                case SQLConstantes.layouts_p630:currentPojoLayout.setP630(valor);break;
                case SQLConstantes.layouts_p701:currentPojoLayout.setP701(valor);break;
                case SQLConstantes.layouts_p702:currentPojoLayout.setP702(valor);break;
                case SQLConstantes.layouts_p703:currentPojoLayout.setP703(valor);break;
                case SQLConstantes.layouts_p704:currentPojoLayout.setP704(valor);break;
                case SQLConstantes.layouts_p705:currentPojoLayout.setP705(valor);break;
                case SQLConstantes.layouts_p706:currentPojoLayout.setP706(valor);break;
                case SQLConstantes.layouts_p707:currentPojoLayout.setP707(valor);break;
                case SQLConstantes.layouts_p708:currentPojoLayout.setP708(valor);break;
                case SQLConstantes.layouts_p709:currentPojoLayout.setP709(valor);break;
                case SQLConstantes.layouts_p801:currentPojoLayout.setP801(valor);break;
                case SQLConstantes.layouts_p802:currentPojoLayout.setP802(valor);break;
                case SQLConstantes.layouts_p803:currentPojoLayout.setP803(valor);break;
                case SQLConstantes.layouts_p804:currentPojoLayout.setP804(valor);break;
                case SQLConstantes.layouts_p805:currentPojoLayout.setP805(valor);break;
                case SQLConstantes.layouts_p806:currentPojoLayout.setP806(valor);break;
                case SQLConstantes.layouts_p807:currentPojoLayout.setP807(valor);break;
                case SQLConstantes.layouts_p808:currentPojoLayout.setP808(valor);break;
                case SQLConstantes.layouts_p809:currentPojoLayout.setP809(valor);break;
                case SQLConstantes.layouts_p810:currentPojoLayout.setP810(valor);break;
                case SQLConstantes.layouts_p811:currentPojoLayout.setP811(valor);break;
                case SQLConstantes.layouts_p812:currentPojoLayout.setP812(valor);break;
                case SQLConstantes.layouts_p813:currentPojoLayout.setP813(valor);break;
                case SQLConstantes.layouts_p814:currentPojoLayout.setP814(valor);break;
                case SQLConstantes.layouts_p815:currentPojoLayout.setP815(valor);break;
                case SQLConstantes.layouts_p816:currentPojoLayout.setP816(valor);break;
                case SQLConstantes.layouts_p817:currentPojoLayout.setP817(valor);break;
                case SQLConstantes.layouts_p818:currentPojoLayout.setP818(valor);break;
                case SQLConstantes.layouts_p819:currentPojoLayout.setP819(valor);break;
                case SQLConstantes.layouts_p820:currentPojoLayout.setP820(valor);break;
                case SQLConstantes.layouts_p821:currentPojoLayout.setP821(valor);break;
                case SQLConstantes.layouts_p822:currentPojoLayout.setP822(valor);break;
                case SQLConstantes.layouts_p823:currentPojoLayout.setP823(valor);break;
            }
        }
    }

    public void agregarVariableCoberturaFragment(String campo, String valor){
        if (valor != null){
            switch (campo){
                case SQLConstantes.cobertura_fragments_id:currentCoberturaFragment.set_id(valor);break;
                case SQLConstantes.cobertura_fragments_id_vivienda:currentCoberturaFragment.setId_vivienda(valor);break;
                case SQLConstantes.cobertura_fragments_cp301p305:currentCoberturaFragment.setCp301p305(valor);break;
                case SQLConstantes.cobertura_fragments_cp306p308:currentCoberturaFragment.setCp306p308(valor);break;
                case SQLConstantes.cobertura_fragments_cp309:currentCoberturaFragment.setCp309(valor);break;
                case SQLConstantes.cobertura_fragments_cp310p312:currentCoberturaFragment.setCp310p312(valor);break;
                case SQLConstantes.cobertura_fragments_cp313p317:currentCoberturaFragment.setCp313p317(valor);break;
                case SQLConstantes.cobertura_fragments_cp318:currentCoberturaFragment.setCp318(valor);break;
                case SQLConstantes.cobertura_fragments_cp401p404:currentCoberturaFragment.setCp401p404(valor);break;
                case SQLConstantes.cobertura_fragments_cp405p407:currentCoberturaFragment.setCp405p407(valor);break;
                case SQLConstantes.cobertura_fragments_cp408p410:currentCoberturaFragment.setCp408p410(valor);break;
                case SQLConstantes.cobertura_fragments_cp411p416:currentCoberturaFragment.setCp411p416(valor);break;
                case SQLConstantes.cobertura_fragments_cp501p505:currentCoberturaFragment.setCp501p505(valor);break;
                case SQLConstantes.cobertura_fragments_cp506p507:currentCoberturaFragment.setCp506p507(valor);break;
                case SQLConstantes.cobertura_fragments_cp508p511:currentCoberturaFragment.setCp508p511(valor);break;
                case SQLConstantes.cobertura_fragments_cp512p513:currentCoberturaFragment.setCp512p513(valor);break;
                case SQLConstantes.cobertura_fragments_cp601p604:currentCoberturaFragment.setCp601p604(valor);break;
                case SQLConstantes.cobertura_fragments_cp605p608:currentCoberturaFragment.setCp605p608(valor);break;
                case SQLConstantes.cobertura_fragments_cp609p612:currentCoberturaFragment.setCp609p612(valor);break;
                case SQLConstantes.cobertura_fragments_cp613p617:currentCoberturaFragment.setCp613p617(valor);break;
                case SQLConstantes.cobertura_fragments_cp618p621:currentCoberturaFragment.setCp618p621(valor);break;
                case SQLConstantes.cobertura_fragments_cp622p625:currentCoberturaFragment.setCp622p625(valor);break;
                case SQLConstantes.cobertura_fragments_cp626p629:currentCoberturaFragment.setCp626p629(valor);break;
                case SQLConstantes.cobertura_fragments_cp630:currentCoberturaFragment.setCp630(valor);break;
                case SQLConstantes.cobertura_fragments_cp701p705:currentCoberturaFragment.setCp701p705(valor);break;
                case SQLConstantes.cobertura_fragments_cp706p709:currentCoberturaFragment.setCp706p709(valor);break;
                case SQLConstantes.cobertura_fragments_cp801p804:currentCoberturaFragment.setCp801p804(valor);break;
                case SQLConstantes.cobertura_fragments_cp805p808:currentCoberturaFragment.setCp805p808(valor);break;
                case SQLConstantes.cobertura_fragments_cp809p812:currentCoberturaFragment.setCp809p812(valor);break;
                case SQLConstantes.cobertura_fragments_cp813p816:currentCoberturaFragment.setCp813p816(valor);break;
                case SQLConstantes.cobertura_fragments_cp817p820:currentCoberturaFragment.setCp817p820(valor);break;
                case SQLConstantes.cobertura_fragments_cp821p823:currentCoberturaFragment.setCp821p823(valor);break;
            }
        }
    }
}
