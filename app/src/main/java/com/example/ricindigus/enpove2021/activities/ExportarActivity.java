package com.example.ricindigus.enpove2021.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.adapters.ViviendaAdapter;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.XMLConstantes;
import com.example.ricindigus.enpove2021.modelo.comandos.EnviarAServidorCommand;
import com.example.ricindigus.enpove2021.modelo.pojos.Caratula;
import com.example.ricindigus.enpove2021.modelo.pojos.CoberturaFragment;
import com.example.ricindigus.enpove2021.modelo.pojos.ExportarItem;
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
import com.example.ricindigus.enpove2021.modelo.pojos.ViviendaItem;
import com.example.ricindigus.enpove2021.util.UtilsMethods;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.getExternalStorageDirectory;

public class ExportarActivity extends AppCompatActivity {

    ArrayList<Marco> marco;
    ArrayList<Caratula> marcos;
    ArrayList<VisitaEncuestador> visitas;
    ArrayList<ExportarItem> exportarItems;
    ArrayList<ViviendaItem> listaViviendas;
    private String idUsuario;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ViviendaAdapter viviendaAdapter;
    private Button btnTablet,btnServidor;
    private TextView txtMensaje,txtTitulo,txtSubtitulo;
    private ProgressBar progreso;
    private FloatingActionButton btnCerrar,btnInfo;
    List<File> servidorItems;
    private TextView txtResultado;
    private TextView txtEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exportar);
        recyclerView = (RecyclerView) findViewById(R.id.exportacion_recycler);
        btnTablet    = (Button) findViewById(R.id.exportacion_tablet_btn);
        btnServidor  = (Button) findViewById(R.id.exportacion_servidor_btn);
        txtMensaje   = (TextView) findViewById(R.id.exportacion_txtMensaje);
        progreso     = (ProgressBar) findViewById(R.id.exportacion_progreso);
        txtTitulo    = (TextView) findViewById(R.id.txt_titulo_exportar);
        txtSubtitulo = (TextView) findViewById(R.id.txt_subtitulo_exportar);
        btnCerrar    = (FloatingActionButton) findViewById(R.id.btn_exportacion_cerrar);
        btnInfo      = (FloatingActionButton) findViewById(R.id.btn_exportacion_info);
        txtResultado   = (TextView)  findViewById(R.id.vivienda_header_resultado_txt);
        txtEstado      = (TextView)  findViewById(R.id.vivienda_header_estado_txt);

        txtResultado.setVisibility(View.GONE);
        txtEstado.setVisibility(View.GONE);

        progreso.setMax(4200);
        txtMensaje.setVisibility(View.GONE);
        progreso.setVisibility(View.GONE);

        final Bundle recupera = getIntent().getExtras();
        if(recupera != null){
            idUsuario = recupera.getString("idUsuario");
        }
        inicializarDatos();
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viviendaAdapter = new ViviendaAdapter(listaViviendas, this, new ViviendaAdapter.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b, int pos) {
                if(b){
                    listaViviendas.get(pos).setSeleccionado(1);
                }else{
                    listaViviendas.get(pos).setSeleccionado(0);
                }
            }
        },null,2);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(viviendaAdapter);

        btnTablet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servidorItems.clear();
                new generateXML().execute(100);
            }
        });

        btnServidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnviarAServidorCommand c = new EnviarAServidorCommand(txtMensaje,progreso,servidorItems,idUsuario);
                c.execute();
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void inicializarDatos() {
        boolean con_resultado=true;
        visitas  = new ArrayList<VisitaEncuestador>();

        marcos = new ArrayList<Caratula>();
        exportarItems = new ArrayList<>();
        listaViviendas = new ArrayList<>();
        servidorItems = new ArrayList<File>();
        Data data = new Data(this);
        data.open();
        marcos = data.getAllCaratulas();

        for(Caratula caratula: marcos){
            visitas = data.getAllVisitasReult(caratula.get_id()+"");
            con_resultado = false;
            for(VisitaEncuestador visita: visitas){
                con_resultado = true;
                if(visita.getVis_resu()==null){
                    con_resultado = false;
                    break;
                }
            }
            if(con_resultado) {
                ViviendaItem viviendaItem = new ViviendaItem(
                        caratula.get_id()+"",
                        0,
                        caratula.getVivienda(),
                        caratula.getConglomerado(),
                        caratula.getNro_selec_vivienda(),
                        caratula.getTipo_selec_vivienda(),
                        caratula.getVivienda_reemplazo(),
                        caratula.getNom_ccpp(),
                        caratula.getPeriodo(),
                        "",
                        "No",
                        "",
                        "",
                        caratula.getNroSegmento()
                );
                listaViviendas.add(viviendaItem);
            }
        }
        data.close();
    }



//    public void mostrarMensaje(String m){
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage(m);
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.dismiss();
//            }
//        });
//        final AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }

    public void exportarViviendaTablet(String idVivienda){
        Data data =  new Data(this);
        data.open();
        String nombreArchivo = idVivienda + ".xml";
        Marco marco = data.getMarco(idVivienda);
        Caratula caratula = data.getCaratula(idVivienda);
        ArrayList<Hogar> hogares = data.getAllHogaresVivienda(idVivienda);
        ArrayList<VisitaEncuestador> visitaEncuestadors = data.getAllVisitasEncuestadorVivienda(idVivienda);
        ArrayList<VisitaSupervisor> visitaSupervisors = data.getAllVisitasSupervisorVivienda(idVivienda);
        ArrayList<ResVisitaEncuestador> resVisitaEncuestadors = data.getAllResultadoVisitaEncuestador(idVivienda);
        ArrayList<ResVisitaSupervisor> resVisitaSupervisors = data.getAllResultadoVisitaSupervisor(idVivienda);
        ArrayList<ResultadoResidente> resultadoResidentef = data.getAllResultadoResidente(idVivienda);
        Funcionario funcionario = data.getFuncionario(idVivienda);
        Modulo1V modulo1V = data.getModulo1V(idVivienda);
        ArrayList<Modulo1H> modulo1HS = data.getAllModulo1H(idVivienda);
        ArrayList<Residente> residentes = data.getAllResidentesVivienda(idVivienda);
        ArrayList<Modulo3> modulo3s = data.getAllModulo3(idVivienda);
        ArrayList<M3Pregunta309> m3Pregunta309s = data.getAllM3Pregunta309Vivienda(idVivienda);
        ArrayList<M3Pregunta318> m3Pregunta318s = data.getAllM3Pregunta318Vivienda(idVivienda);
        ArrayList<Modulo4> modulo4s = data.getAllModulo4(idVivienda);
        ArrayList<Modulo5> modulo5s = data.getAllModulo5(idVivienda);
        ArrayList<Modulo6> modulo6s = data.getAllModulo6(idVivienda);
        ArrayList<Modulo7> modulo7s = data.getAllModulo7(idVivienda);
        ArrayList<Modulo8> modulo8s = data.getAllModulo8(idVivienda);
        ArrayList<POJOFragment> pojoFragments = new ArrayList<>();
        ArrayList<POJOFragmentVivienda> pojoFragmentViviendas = new ArrayList<>();
        ArrayList<POJOFragmentHogar> pojoFragmentHogars = new ArrayList<>();
        ArrayList<POJOLayout> pojoLayouts = new ArrayList<>();
        ArrayList<CoberturaFragment> coberturaFragments = new ArrayList<>();
        for (Residente residente : residentes){
            if (residente.getC2_p208().equals("1")){
                pojoFragments.add(data.getFragmentsLayouts(residente.get_id()));
                pojoFragmentViviendas.add(data.getFragmentsVivienda(residente.getId_vivienda()));
                pojoFragmentHogars.add(data.getFragmentsHogar(residente.getId_hogar()));
                pojoLayouts.add(data.getLayouts(residente.get_id()));
                coberturaFragments.add(data.getCoberturaFragments(residente.get_id()));
            }
        }

        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("utf-8", true);
            serializer.startTag("", "ENPOVE");
            serializer.attribute("", "id",idVivienda);
            serializer.attribute("", "version",UtilsMethods.getVersion(getApplicationContext()));
            if (marco != null){
                serializer.startTag("", "MARCO");
                escribirCampoXml(serializer, SQLConstantes.marco_id,marco.get_id()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_anio,marco.getAnio()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_mes,marco.getMes()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_periodo,marco.getPeriodo()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_zona,marco.getZona()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_ccdd,marco.getCcdd()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_departamento,marco.getDepartamento()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_ccpp,marco.getCcpp()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_provincia,marco.getProvincia()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_ccdi,marco.getCcdi()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_distrito,marco.getDistrito()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_codccpp,marco.getCodccpp()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_nomccpp,marco.getNomccpp()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_conglomerado,marco.getConglomerado()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_norden,marco.getNorden()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_manzana_id,marco.getManzana_id()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_tipvia,marco.getTipvia()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_nomvia,marco.getNomvia()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_nropta,marco.getNropta()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_lote,marco.getLote()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_piso,marco.getPiso()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_mza,marco.getMza()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_block,marco.getBlock()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_interior,marco.getInterior()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_km,marco.getKm()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_usuario_id,marco.getUsuario_id()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_usuario,marco.getUsuario()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_nombre,marco.getNombre()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_dni,marco.getDni()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_usuario_sup_id,marco.getUsuario_sup_id()+"");
               // escribirCampoXml(serializer, SQLConstantes.marco_usuario_sup_id,marco.getNorden()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_estado,marco.getEstado()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_nro_selec_vivienda,marco.getNro_selec_vivienda()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_tipo_selec_vivienda,marco.getTipo_selec_vivienda()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_vivienda_reemplazo,marco.getVivienda_reemplazo()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_tipo,marco.getTipo()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_nroVivienda,marco.getNroVivienda()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_nroSegmento,marco.getNroSegmento()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_marcoProviene,marco.getMarcoProviene()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_estrato,marco.getEstado()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_observaciones,marco.getObservaciones()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_nroPuerta2,marco.getNroPuerta2()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_jefeHogar,marco.getJefeHogar()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_telefono,marco.getTelefono()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_correo,marco.getCorreo()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_aerInicial,marco.getAerInicial()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_aerFinal,marco.getAerFinal()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_cono,marco.getCono()+"");
                Log.e("CONO", ""+marco.getCono());
                escribirCampoXml(serializer, SQLConstantes.marco_area,marco.getArea()+"");
                Log.e("AREA", ""+marco.getArea());
                escribirCampoXml(serializer, SQLConstantes.marco_areaEncuesta,marco.getAreaEncuesta()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_region,marco.getRegion()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_dominio,marco.getDominio()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_idCarga,marco.getIdCarga()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_frente,marco.getFrente()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_latitud,marco.getLatitud()+"");
                escribirCampoXml(serializer, SQLConstantes.marco_longitud,marco.getLongitud()+"");
                serializer.endTag("", "MARCO");
            }

            if(caratula != null) {
                serializer.startTag("", "CARATULA");
                escribirCampoXml(serializer, SQLConstantes.caratula_id,caratula.get_id()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_anio,caratula.getAnio()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_mes,caratula.getMes()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_periodo,caratula.getPeriodo()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_conglomerado,caratula.getConglomerado()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_nom_dep,caratula.getNom_dep()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_nom_prov,caratula.getNom_prov()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_nom_dist ,caratula.getNom_dist()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_nom_ccpp,caratula.getNom_ccpp()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_zona ,caratula.getZona()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_manzana_id ,caratula.getManzana_id()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_vivienda ,caratula.getVivienda()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_latitud,caratula.getLatitud()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_longitud ,caratula.getLongitud()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_altitud ,caratula.getAltitud()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_tipvia ,caratula.getTipvia()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_tipvia_o ,caratula.getTipvia_o()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_nomvia ,caratula.getNomvia()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_nropta ,caratula.getNropta()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_block ,caratula.getBlock()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_interior ,caratula.getInterior()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_piso ,caratula.getPiso()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_mza,caratula.getMza()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_lote ,caratula.getLote()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_km ,caratula.getKm()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_telefono ,caratula.getTelefono()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_t_hogar ,caratula.getT_hogar()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_usuario ,caratula.getUsuario()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_observaciones ,caratula.getObservaciones()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_cobertura,caratula.getCobertura()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_nro_selec_vivienda,caratula.getNro_selec_vivienda()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_tipo_selec_vivienda,caratula.getTipo_selec_vivienda()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_vivienda_reemplazo,caratula.getVivienda_reemplazo()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_nro_vivienda_reemplazo,caratula.getNro_vivienda_reemplazo()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_p21,caratula.getP21()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_p21_o,caratula.getP21_o()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_resultado_final,caratula.getResultado_final()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_resultado_final_o,caratula.getResultado_final_o()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_nroSegmento,caratula.getNroSegmento()+"");
                escribirCampoXml(serializer, SQLConstantes.caratula_nroPta2,caratula.getNroPuerta2()+"");

                serializer.endTag("", "CARATULA");
            }

            if(hogares.size()>0) {
                serializer.startTag("", "HOGARES");
                for (Hogar hogar : hogares) {
                    serializer.startTag("", "HOGAR");
                    escribirCampoXml(serializer, SQLConstantes.hogar_id, hogar.get_id());
                    escribirCampoXml(serializer, SQLConstantes.hogar_id_informante, hogar.getId_informante());
                    escribirCampoXml(serializer, SQLConstantes.hogar_id_vivienda,hogar.getId_vivienda());
                    escribirCampoXml(serializer, SQLConstantes.hogar_numero,hogar.getNumero());
                    escribirCampoXml(serializer, SQLConstantes.hogar_nom_ape,hogar.getNom_ape());
                    escribirCampoXml(serializer, SQLConstantes.hogar_ape_paterno,hogar.getApe_paterno());
                    escribirCampoXml(serializer, SQLConstantes.hogar_ape_materno,hogar.getApe_materno());
                    escribirCampoXml(serializer, SQLConstantes.hogar_estado,hogar.getEstado());
                    escribirCampoXml(serializer, SQLConstantes.hogar_nropersonas,hogar.getNropersonas());
                    escribirCampoXml(serializer, SQLConstantes.hogar_vive,hogar.getVive());
                    escribirCampoXml(serializer, SQLConstantes.hogar_nroviven,hogar.getNroviven());
                    escribirCampoXml(serializer, SQLConstantes.hogar_principal,hogar.getPrincipal());
                    escribirCampoXml(serializer, SQLConstantes.hogar_cobertura,hogar.getCobertura());
                    escribirCampoXml(serializer, SQLConstantes.hogar_p15,hogar.getP15());
                    escribirCampoXml(serializer, SQLConstantes.hogar_p15_o,hogar.getP15_o());
                    escribirCampoXml(serializer, SQLConstantes.hogar_p16,hogar.getP16());
                    escribirCampoXml(serializer, SQLConstantes.hogar_p17,hogar.getP17());
                    escribirCampoXml(serializer, SQLConstantes.hogar_p18,hogar.getP18());
                    escribirCampoXml(serializer, SQLConstantes.hogar_p19,hogar.getP19());
                    escribirCampoXml(serializer, SQLConstantes.hogar_p20,hogar.getP20());
                    serializer.endTag("", "HOGAR");
                }
                serializer.endTag("", "HOGARES");
            }

            if(visitaEncuestadors.size()>0) {
                serializer.startTag("", "VISITAS_ENCUESTADOR");
                for (VisitaEncuestador visita : visitaEncuestadors) {
                    serializer.startTag("", "VISITA_ENCUESTADOR");
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_id, visita.get_id());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_id_vivienda, visita.getId_vivienda());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_id_hogar, visita.getId_hogar());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_numero, visita.getNumero());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_vis_fecha_dd, visita.getVis_fecha_dd());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_vis_fecha_mm, visita.getVis_fecha_mm());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_vis_fecha_aa, visita.getVis_fecha_aa());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_vis_hor_ini, visita.getVis_hor_ini());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_vis_min_ini, visita.getVis_min_ini());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_vis_hor_fin, visita.getVis_hor_fin());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_vis_min_fin, visita.getVis_min_fin());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_prox_vis_fecha_dd, visita.getProx_vis_fecha_dd());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_prox_vis_fecha_mm, visita.getProx_vis_fecha_mm());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_prox_vis_fecha_aa, visita.getProx_vis_fecha_aa());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_prox_vis_hor, visita.getProx_vis_hor());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_prox_vis_min, visita.getProx_vis_min());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_vis_resu, visita.getVis_resu());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_vis_resu_esp, visita.getVis_resu_esp());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_latitud, visita.getLatitud());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_longitud, visita.getLongitud());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_altura, visita.getAltura());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_tipo_entrevista, visita.getTipo_ent());
                    escribirCampoXml(serializer, SQLConstantes.visita_encuestador_tipo_entrevista_o, visita.getTipo_ent_o());
                    serializer.endTag("", "VISITA_ENCUESTADOR");
                }
                serializer.endTag("", "VISITAS_ENCUESTADOR");
            }

            if(resVisitaEncuestadors.size()>0) {
                serializer.startTag("", "RESULTADOS_VISITA_ENCUESTADOR");
                for (ResVisitaEncuestador resVisitaEncuestador : resVisitaEncuestadors) {
                    serializer.startTag("", "RESULTADO_VISITA_ENCUESTADOR");
                    escribirCampoXml(serializer, SQLConstantes.resultado_encuestador_id, resVisitaEncuestador.get_id());
                    escribirCampoXml(serializer, SQLConstantes.resultado_encuestador_id_vivienda, resVisitaEncuestador.getId_vivienda());
                    escribirCampoXml(serializer, SQLConstantes.resultado_encuestador_vis_resultado_final, resVisitaEncuestador.getVis_resultado_final());
                    escribirCampoXml(serializer, SQLConstantes.resultado_encuestador_vis_fecha_final_dd, resVisitaEncuestador.getVis_fecha_final_dd());
                    escribirCampoXml(serializer, SQLConstantes.resultado_encuestador_vis_fecha_final_mm, resVisitaEncuestador.getVis_fecha_final_mm());
                    escribirCampoXml(serializer, SQLConstantes.resultado_encuestador_vis_fecha_final_aa, resVisitaEncuestador.getVis_fecha_final_aa());
                    serializer.endTag("", "RESULTADO_VISITA_ENCUESTADOR");
                }
                serializer.endTag("", "RESULTADOS_VISITA_ENCUESTADOR");
            }

            if(visitaSupervisors.size()>0) {
                serializer.startTag("", "VISITAS_SUPERVISOR");
                for (VisitaSupervisor visitaSupervisor : visitaSupervisors) {
                    serializer.startTag("", "VISITA_SUPERVISOR");
                    escribirCampoXml(serializer, SQLConstantes.visita_supervisor_id, visitaSupervisor.get_id());
                    escribirCampoXml(serializer, SQLConstantes.visita_supervisor_id_vivienda, visitaSupervisor.getId_vivienda());
                    escribirCampoXml(serializer, SQLConstantes.visita_supervisor_id_hogar , visitaSupervisor.getId_hogar());
                    escribirCampoXml(serializer, SQLConstantes.visita_supervisor_numero , visitaSupervisor.getNumero());
                    escribirCampoXml(serializer, SQLConstantes.visita_supervisor_vis_fecha_dd , visitaSupervisor.getVis_fecha_dd());
                    escribirCampoXml(serializer, SQLConstantes.visita_supervisor_vis_fecha_mm , visitaSupervisor.getVis_fecha_mm());
                    escribirCampoXml(serializer, SQLConstantes.visita_supervisor_vis_fecha_aa , visitaSupervisor.getVis_fecha_aa());
                    escribirCampoXml(serializer, SQLConstantes.visita_supervisor_vis_hor_ini , visitaSupervisor.getVis_hor_ini());
                    escribirCampoXml(serializer, SQLConstantes.visita_supervisor_vis_min_ini , visitaSupervisor.getVis_min_ini());
                    escribirCampoXml(serializer, SQLConstantes.visita_supervisor_vis_hor_fin , visitaSupervisor.getVis_hor_fin());
                    escribirCampoXml(serializer, SQLConstantes.visita_supervisor_vis_min_fin , visitaSupervisor.getVis_min_fin());
                    escribirCampoXml(serializer, SQLConstantes.visita_supervisor_vis_resu , visitaSupervisor.getVis_resu());
                    escribirCampoXml(serializer, SQLConstantes.visita_supervisor_vis_resu_esp , visitaSupervisor.getVis_resu_esp());
                    serializer.endTag("", "VISITA_SUPERVISOR");
                }
                serializer.endTag("", "VISITAS_SUPERVISOR");
            }

            if(resVisitaSupervisors.size()>0) {
                serializer.startTag("", "RESULTADOS_VISITA_SUPERVISOR");
                for (ResVisitaSupervisor resVisitaSupervisor : resVisitaSupervisors) {
                    serializer.startTag("", "RESULTADO_VISITA_SUPERVISOR");
                    escribirCampoXml(serializer, SQLConstantes.resultado_supervisor_id, resVisitaSupervisor.get_id());
                    escribirCampoXml(serializer, SQLConstantes.resultado_supervisor_id_vivienda, resVisitaSupervisor.getId_vivienda());
                    escribirCampoXml(serializer, SQLConstantes.resultado_supervisor_vis_resultado_final, resVisitaSupervisor.getVis_resultado_final());
                    escribirCampoXml(serializer, SQLConstantes.resultado_supervisor_vis_fecha_final_dd, resVisitaSupervisor.getVis_fecha_final_dd());
                    escribirCampoXml(serializer, SQLConstantes.resultado_supervisor_vis_fecha_final_mm, resVisitaSupervisor.getVis_fecha_final_mm());
                    escribirCampoXml(serializer, SQLConstantes.resultado_supervisor_vis_fecha_final_aa, resVisitaSupervisor.getVis_fecha_final_aa());
                    serializer.endTag("", "RESULTADO_VISITA_SUPERVISOR");
                }
                serializer.endTag("", "RESULTADOS_VISITA_SUPERVISOR");
            }

            if(resultadoResidentef.size()>0) {
                serializer.startTag("", "RESULTADO_ENTREVISTA_RESIDENTE");
                for (ResultadoResidente resultadoResidentes : resultadoResidentef) {
                    serializer.startTag("", "ITEM_RESULTADO_RESIDENTE");
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_id,resultadoResidentes.get_id());
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_id_vivienda,resultadoResidentes.getId_vivienda());
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_id_hogar,resultadoResidentes.getId_hogar());
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_fecha_dd,resultadoResidentes.getFecha_dd());
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_fecha_mm,resultadoResidentes.getFecha_mm());
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_fecha_aa,resultadoResidentes.getFecha_aa());
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_hor_ini,resultadoResidentes.getHor_ini());
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_min_ini,resultadoResidentes.getMin_ini());
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_hor_fin,resultadoResidentes.getHor_fin());
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_min_fin,resultadoResidentes.getMin_fin());
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_resultado_entrevista,resultadoResidentes.getResultado_entrevista());
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_resultado_entrevista_o,resultadoResidentes.getResultado_entrevista_o());
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_tipo_entrevista,resultadoResidentes.getTipo_entrevista());
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_tipo_entrevista_o,resultadoResidentes.getTipo_entrevista_o());
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_donde_entrevista,resultadoResidentes.getDonde_entrevista());
                    escribirCampoXml(serializer, SQLConstantes.resultado_residente_donde_entrevista_o,resultadoResidentes.getDonde_entrevista_o());
                    serializer.endTag("", "ITEM_RESULTADO_RESIDENTE");
                }
                serializer.endTag("", "RESULTADO_ENTREVISTA_RESIDENTE");
            }

            if(funcionario != null) {
                serializer.startTag("", "FUNCIONARIO");
                escribirCampoXml(serializer, SQLConstantes.funcionarios_id, funcionario.get_id());
                escribirCampoXml(serializer, SQLConstantes.funcionarios_dni_encu, funcionario.getDni_encu());
                escribirCampoXml(serializer, SQLConstantes.funcionarios_dni_sup, funcionario.getDni_sup());
                escribirCampoXml(serializer, SQLConstantes.funcionarios_dni_supn, funcionario.getDni_supn());
                escribirCampoXml(serializer, SQLConstantes.funcionarios_dni_coord, funcionario.getDni_coor());
                escribirCampoXml(serializer, SQLConstantes.funcionarios_nombre_encu, funcionario.getNombre_encu());
                escribirCampoXml(serializer, SQLConstantes.funcionarios_nombre_sup, funcionario.getNombre_sup());
                escribirCampoXml(serializer, SQLConstantes.funcionarios_nombre_supn, funcionario.getNombre_supn());
                escribirCampoXml(serializer, SQLConstantes.funcionarios_nombre_coord, funcionario.getNombre_coord());
                serializer.endTag("", "FUNCIONARIO");
            }

            if(modulo1V != null) {
                serializer.startTag("", "MODULO1V");
                escribirCampoXml(serializer, SQLConstantes.modulo1_v_id, modulo1V.get_id());
                escribirCampoXml(serializer, SQLConstantes.modulo1_v_c1_p101, modulo1V.getC1_p101());
                escribirCampoXml(serializer, SQLConstantes.modulo1_v_c1_p101_o, modulo1V.getC1_p101_o());
                escribirCampoXml(serializer, SQLConstantes.modulo1_v_c1_p102, modulo1V.getC1_p102());
                escribirCampoXml(serializer, SQLConstantes.modulo1_v_c1_p102_o, modulo1V.getC1_p102_o());
                escribirCampoXml(serializer, SQLConstantes.modulo1_v_c1_p103, modulo1V.getC1_p103());
                escribirCampoXml(serializer, SQLConstantes.modulo1_v_c1_p103_o, modulo1V.getC1_p103_o());
                escribirCampoXml(serializer, SQLConstantes.modulo1_v_c1_p104, modulo1V.getC1_p104());
                escribirCampoXml(serializer, SQLConstantes.modulo1_v_c1_p104_o, modulo1V.getC1_p104_o());
                escribirCampoXml(serializer, SQLConstantes.modulo1_v_c1_p105, modulo1V.getC1_p105());
                escribirCampoXml(serializer, SQLConstantes.modulo1_v_c1_p106, modulo1V.getC1_p106());
                //escribirCampoXml(serializer, SQLConstantes.modulo1_v_c1_p107, modulo1V.getC1_p107());
                escribirCampoXml(serializer, SQLConstantes.modulo1_v_COB100A, modulo1V.getCOB100A());
                serializer.endTag("", "MODULO1V");
            }

            if(modulo1HS.size()>0) {
                serializer.startTag("", "MODULO1_HOGARES");
                for (Modulo1H modulo1H : modulo1HS) {
                    serializer.startTag("", "MODULO1_HOGAR");
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_id, modulo1H.get_id());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_idVivienda, modulo1H.getIdVivienda());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p107, modulo1H.getC1_p107());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p107_o, modulo1H.getC1_p107_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p108_1, modulo1H.getC1_p108_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p108_2, modulo1H.getC1_p108_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p108_3, modulo1H.getC1_p108_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p108_1_o, modulo1H.getC1_p108_1_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p108_2_o, modulo1H.getC1_p108_2_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p108_3_o, modulo1H.getC1_p108_3_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p108_4, modulo1H.getC1_p108_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p109, modulo1H.getC1_p109());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p109_o, modulo1H.getC1_p109_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_1, modulo1H.getC1_p110_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_2, modulo1H.getC1_p110_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_3, modulo1H.getC1_p110_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_4, modulo1H.getC1_p110_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_5, modulo1H.getC1_p110_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_6, modulo1H.getC1_p110_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_7, modulo1H.getC1_p110_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_8, modulo1H.getC1_p110_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_9, modulo1H.getC1_p110_9());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_10, modulo1H.getC1_p110_10());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_11, modulo1H.getC1_p110_11());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_11_o, modulo1H.getC1_p110_11_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_12, modulo1H.getC1_p110_12());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_12_o, modulo1H.getC1_p110_12_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_13, modulo1H.getC1_p110_13());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p110_13_o, modulo1H.getC1_p110_13_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p111, modulo1H.getC1_p111());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p111a, modulo1H.getC1_p111a());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p112_1, modulo1H.getC1_p112_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p112_2, modulo1H.getC1_p112_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p112_3, modulo1H.getC1_p112_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p112_4, modulo1H.getC1_p112_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p112_5, modulo1H.getC1_p112_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p112_6, modulo1H.getC1_p112_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p112_7, modulo1H.getC1_p112_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_c1_p112_8, modulo1H.getC1_p112_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo1_h_COB100B, modulo1H.getCOB100B());
                    serializer.endTag("", "MODULO1_HOGAR");
                }
                serializer.endTag("", "MODULO1_HOGARES");
            }

            if(residentes.size()>0) {
                serializer.startTag("", "MODULO2_RESIDENTES");
                for (Residente residente : residentes) {
                    serializer.startTag("", "MODULO2_RESIDENTE");
                    escribirCampoXml(serializer, SQLConstantes.residentes_id, residente.get_id());
                    escribirCampoXml(serializer, SQLConstantes.residentes_id_informante, residente.getId_informante());
                    escribirCampoXml(serializer, SQLConstantes.residentes_id_hogar, residente.getId_hogar());
                    escribirCampoXml(serializer, SQLConstantes.residentes_id_vivienda, residente.getId_vivienda());
                    escribirCampoXml(serializer, SQLConstantes.residentes_numero, residente.getNumero());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p202, residente.getC2_p202());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p202_pat, residente.getC2_p202_pat());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p202_mat, residente.getC2_p202_mat());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p203, residente.getC2_p203());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p204, residente.getC2_p204());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p205_a, residente.getC2_p205_a());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p205_m, residente.getC2_p205_m());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p206, residente.getC2_p206());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p207, residente.getC2_p207());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p207_o, residente.getC2_p207_o());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p208, residente.getC2_p208());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p209, residente.getC2_p209());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p209_n, residente.getC2_p209_n());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p209_p, residente.getC2_p209_p());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p209_pos, residente.getC2_p209_pos());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p210, residente.getC2_p210());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p210_n, residente.getC2_p210_n());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p210_p, residente.getC2_p210_p());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p210_pos, residente.getC2_p210_pos());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p211, residente.getC2_p211());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p211_1, residente.getC2_p211_1());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p211_1_o, residente.getC2_p211_1_o());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p211_nom, residente.getC2_p211_nom());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p211_pos, residente.getC2_p211_pos());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_p212, residente.getC2_p212());
                    escribirCampoXml(serializer, SQLConstantes.residentes_p200_aportante, residente.getP200_aportante());
                    escribirCampoXml(serializer, SQLConstantes.residentes_c2_OBS200, residente.getOBS200());
                    escribirCampoXml(serializer, SQLConstantes.residentes_COB200, residente.getCOB200());
                    escribirCampoXml(serializer, SQLConstantes.residentes_encuestado_cobertura, residente.getEncuestado_cobertura());
                    serializer.endTag("", "MODULO2_RESIDENTE");
                }
                serializer.endTag("", "MODULO2_RESIDENTES");
            }

            if(modulo3s.size()>0) {
                serializer.startTag("", "MODULO3S");
                for (Modulo3 modulo3 : modulo3s) {
                    serializer.startTag("", "MODULO3");
                    escribirCampoXml(serializer, SQLConstantes.modulo3_id, modulo3.get_id());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_id_informante, modulo3.getIdInformante());//esta que toma idformante y deberia ser id_informante; debere cambiar la bd principal x idinformante
                    escribirCampoXml(serializer, SQLConstantes.modulo3_id_hogar, modulo3.getIdHogar());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_id_vivienda, modulo3.getIdVivienda());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p301_d, modulo3.getC3_p301_d());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p301_m, modulo3.getC3_p301_m());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p301_a, modulo3.getC3_p301_a());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p302, modulo3.getC3_p302());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p302_o, modulo3.getC3_p302_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p303_m, modulo3.getC3_p303_m());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p303_a, modulo3.getC3_p303_a());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p303_no_nacio, modulo3.getC3_p303_no_nacio());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p304, modulo3.getC3_p304());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p304_o, modulo3.getC3_p304_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p305, modulo3.getC3_p305());
                    //escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p305_o, modulo3.getC3_p305_o());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p306, modulo3.getC3_p306());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p306_1, modulo3.getC3_p306_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p306_2, modulo3.getC3_p306_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p306_3, modulo3.getC3_p306_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p306_4, modulo3.getC3_p306_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p306_5, modulo3.getC3_p306_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p306_6, modulo3.getC3_p306_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p306_7, modulo3.getC3_p306_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p306_o, modulo3.getC3_p306_o());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307, modulo3.getC3_p307());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_1, modulo3.getC3_p307_1());
                /*    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_2, modulo3.getC3_p307_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_3, modulo3.getC3_p307_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_4, modulo3.getC3_p307_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_5, modulo3.getC3_p307_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_6, modulo3.getC3_p307_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_7, modulo3.getC3_p307_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_8, modulo3.getC3_p307_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_9, modulo3.getC3_p307_9());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_10, modulo3.getC3_p307_10());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_11, modulo3.getC3_p307_11());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_12, modulo3.getC3_p307_12());*/
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_o6, modulo3.getC3_p307_o6());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_a, modulo3.getC3_p307_a());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307a_o, modulo3.getC3_p307a_o());
                   /* escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_o12, modulo3.getC3_p307_o12());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_13, modulo3.getC3_p307_13());*/
                    //escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_d, modulo3.getC3_p307_d());
                    // escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_m, modulo3.getC3_p307_m());
                    // escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p307_a, modulo3.getC3_p307_a());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p308, modulo3.getC3_p308());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p309, modulo3.getC3_p309());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p309_o, modulo3.getC3_p309_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p309_1, modulo3.getC3_p309_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p309_1_o, modulo3.getC3_p309_1_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p310_e, modulo3.getC3_p310_e());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p310_e_o, modulo3.getC3_p310_e_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p310_m, modulo3.getC3_p310_m());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p310_m_o, modulo3.getC3_p310_m_o());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p310_e_seleccion, modulo3.getC3_p310_e_seleccion());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p310_m_seleccion, modulo3.getC3_p310_m_seleccion());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p310_1, modulo3.getC3_p310_1());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p310_2, modulo3.getC3_p310_2());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p310_3, modulo3.getC3_p310_3());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p310_4, modulo3.getC3_p310_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p311, modulo3.getC3_p311());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p312, modulo3.getC3_p312());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p312_o, modulo3.getC3_p312_o());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p312_dist, modulo3.getC3_p312_dist());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p312_prov, modulo3.getC3_p312_prov());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p312_dep, modulo3.getC3_p312_dep());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p313, modulo3.getC3_p313());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p314, modulo3.getC3_p314());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p314_o, modulo3.getC3_p314_o());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p315_1, modulo3.getC3_p315_1());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p315_2, modulo3.getC3_p315_2());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p315_3, modulo3.getC3_p315_3());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p315_4, modulo3.getC3_p315_4());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p315_5, modulo3.getC3_p315_5());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p315_6, modulo3.getC3_p315_6());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p315_7, modulo3.getC3_p315_7());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p315_8, modulo3.getC3_p315_8());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p315_9, modulo3.getC3_p315_9());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p315_10, modulo3.getC3_p315_10());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p315_10_o, modulo3.getC3_p315_10_o());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p316, modulo3.getC3_p316());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p316_o, modulo3.getC3_p316_o());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p317, modulo3.getC3_p317());
//                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p318, modulo3.getC3_p318());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_obs_cap3, modulo3.getObs_cap3());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_COB300, modulo3.getCOB300());
                    serializer.endTag("", "MODULO3");
                }
                serializer.endTag("", "MODULO3S");
            }

            if(m3Pregunta309s.size()>0) {
                serializer.startTag("", "M3P309RUTAS");
                for (M3Pregunta309 m3Pregunta309 : m3Pregunta309s) {
                    serializer.startTag("", "M3P309RUTA");
                    escribirCampoXml(serializer, SQLConstantes.modulo3_p309_id, m3Pregunta309.get_id());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_p309_id_encuestado, m3Pregunta309.getId_encuestado());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_p309_id_vivienda, m3Pregunta309.getId_vivienda());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_p309_numero, m3Pregunta309.getNumero());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p309_p, m3Pregunta309.getC3_p309_p());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p309_p_nom, m3Pregunta309.getC3_p309_p_nom());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p309_c, m3Pregunta309.getC3_p309_c());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p309_mod, m3Pregunta309.getC3_p309_mod());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p309_m, m3Pregunta309.getC3_p309_m());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p309_a, m3Pregunta309.getC3_p309_a());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p309_m_cod, m3Pregunta309.getC3_p309_m_cod());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p309_a_cod, m3Pregunta309.getC3_p309_a_cod());
                    serializer.endTag("", "M3P309RUTA");
                }
                serializer.endTag("", "M3P309RUTAS");
            }

            if(m3Pregunta318s.size()>0) {
                serializer.startTag("", "M3P318PERSONAS");
                for (M3Pregunta318 m3Pregunta318 : m3Pregunta318s) {
                    serializer.startTag("", "M3P318PERSONA");
                    escribirCampoXml(serializer, SQLConstantes.modulo3_p318_id, m3Pregunta318.get_id());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_p318_idEncuestado, m3Pregunta318.getIdEncuestado());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_p318_id_vivienda, m3Pregunta318.getId_vivienda());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_p318_numero, m3Pregunta318.getNumero());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p318_f, m3Pregunta318.getC3_p318_f());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p318_s, m3Pregunta318.getC3_p318_s());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p318_e, m3Pregunta318.getC3_p318_e());
                    escribirCampoXml(serializer, SQLConstantes.modulo3_c3_p318_p, m3Pregunta318.getC3_p318_p());
                    serializer.endTag("", "M3P318PERSONA");
                }
                serializer.endTag("", "M3P318PERSONAS");
            }

            if(modulo4s.size()>0) {
                serializer.startTag("", "MODULO4S");
                for (Modulo4 modulo4 : modulo4s) {
                    serializer.startTag("", "MODULO4");
                    escribirCampoXml(serializer, SQLConstantes.modulo4_id, modulo4.get_id());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_id_informante, modulo4.getIdInformante());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_id_hogar, modulo4.getIdHogar());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_id_vivienda, modulo4.getIdVivienda());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p401_1, modulo4.getC4_p401_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p401_2, modulo4.getC4_p401_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p401_3, modulo4.getC4_p401_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p401_4, modulo4.getC4_p401_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p401_4_o, modulo4.getC4_p401_4_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p401_5, modulo4.getC4_p401_5());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p401_o, modulo4.getC4_p401_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p402, modulo4.getC4_p402());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_1, modulo4.getC4_p403_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_2, modulo4.getC4_p403_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_3, modulo4.getC4_p403_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_4, modulo4.getC4_p403_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_5, modulo4.getC4_p403_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_6, modulo4.getC4_p403_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_7, modulo4.getC4_p403_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_8, modulo4.getC4_p403_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_9, modulo4.getC4_p403_9());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_10, modulo4.getC4_p403_10());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_11, modulo4.getC4_p403_11());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_12, modulo4.getC4_p403_12());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_13, modulo4.getC4_p403_13());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_14, modulo4.getC4_p403_14());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_14_o, modulo4.getC4_p403_14_o());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p403_o, modulo4.getC4_p403_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p404, modulo4.getC4_p404());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p405_1, modulo4.getC4_p405_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p405_2, modulo4.getC4_p405_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p405_3, modulo4.getC4_p405_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p405_4, modulo4.getC4_p405_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p405_5, modulo4.getC4_p405_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p405_6, modulo4.getC4_p405_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p405_7, modulo4.getC4_p405_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p406_1, modulo4.getC4_p406_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p406_2, modulo4.getC4_p406_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p406_3, modulo4.getC4_p406_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p406_4, modulo4.getC4_p406_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p406_5, modulo4.getC4_p406_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p406_6, modulo4.getC4_p406_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p406_7, modulo4.getC4_p406_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p406_7_o, modulo4.getC4_p406_7_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p406_8, modulo4.getC4_p406_8());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p406_o, modulo4.getC4_p406_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p407_1, modulo4.getC4_p407_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p407_2, modulo4.getC4_p407_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p407_3, modulo4.getC4_p407_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p407_4, modulo4.getC4_p407_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p407_5, modulo4.getC4_p407_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p407_6, modulo4.getC4_p407_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p407_7, modulo4.getC4_p407_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p407_8, modulo4.getC4_p407_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p407_9, modulo4.getC4_p407_9());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p407_10, modulo4.getC4_p407_10());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p407_11, modulo4.getC4_p407_11());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p407_12, modulo4.getC4_p407_12());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p407_13, modulo4.getC4_p407_13());
                    //escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p407_o, modulo4.getC4_p407_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p407_13_o, modulo4.getC4_p407_13_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p408_1, modulo4.getC4_p408_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p408_2, modulo4.getC4_p408_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p408_3, modulo4.getC4_p408_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p408_4, modulo4.getC4_p408_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p408_5, modulo4.getC4_p408_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p408_6, modulo4.getC4_p408_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p409, modulo4.getC4_p409());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p409_1, modulo4.getC4_p409_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p409_o, modulo4.getC4_p409_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p409_2, modulo4.getC4_p409_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p409_nom, modulo4.getC4_p409_nom());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p410a, modulo4.getC4_p410a());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p410b, modulo4.getC4_p410b());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p410, modulo4.getC4_p410());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411, modulo4.getC4_p411());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411_1, modulo4.getC4_p411_1());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411_2, modulo4.getC4_p411_2());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411_3, modulo4.getC4_p411_3());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411_4, modulo4.getC4_p411_4());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411_5, modulo4.getC4_p411_5());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411_6, modulo4.getC4_p411_6());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411_7, modulo4.getC4_p411_7());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411_8, modulo4.getC4_p411_8());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411_9, modulo4.getC4_p411_9());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411_10, modulo4.getC4_p411_10());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411_11, modulo4.getC4_p411_11());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411_12, modulo4.getC4_p411_12());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411_13, modulo4.getC4_p411_13());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411_14, modulo4.getC4_p411_14());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p411_o, modulo4.getC4_p411_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p412, modulo4.getC4_p412());
                   // escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p413, modulo4.getC4_p413());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p413_1, modulo4.getC4_p413_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p413_2, modulo4.getC4_p413_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p413_3, modulo4.getC4_p413_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p413_4, modulo4.getC4_p413_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p413_5, modulo4.getC4_p413_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p414, modulo4.getC4_p414());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p415, modulo4.getC4_p415());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p416_1, modulo4.getC4_p416_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p416_2, modulo4.getC4_p416_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p416_3, modulo4.getC4_p416_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p416_4, modulo4.getC4_p416_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p416_5, modulo4.getC4_p416_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p416_5_o, modulo4.getC4_p416_5_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p416_6, modulo4.getC4_p416_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1, modulo4.getC4_p417_1());
                    //escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1a, modulo4.getC4_p417_1a());
                    //escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1a_o, modulo4.getC4_p417_1a_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1_1, modulo4.getC4_p417_1_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1_2, modulo4.getC4_p417_1_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1_3, modulo4.getC4_p417_1_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1_4, modulo4.getC4_p417_1_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1_5, modulo4.getC4_p417_1_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1_6, modulo4.getC4_p417_1_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1_7, modulo4.getC4_p417_1_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1_8, modulo4.getC4_p417_1_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1_9, modulo4.getC4_p417_1_9());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1_10, modulo4.getC4_p417_1_10());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1_11, modulo4.getC4_p417_1_11());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1_12, modulo4.getC4_p417_1_12());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1_13, modulo4.getC4_p417_1_13());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_1_13_o, modulo4.getC4_p417_1_13_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_2, modulo4.getC4_p417_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_3, modulo4.getC4_p417_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_4, modulo4.getC4_p417_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_4_o, modulo4.getC4_p417_4_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_5, modulo4.getC4_p417_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_6, modulo4.getC4_p417_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_7, modulo4.getC4_p417_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_8, modulo4.getC4_p417_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_9, modulo4.getC4_p417_9());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p417_7_o, modulo4.getC4_p417_7_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p418, modulo4.getC4_p418());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p418a, modulo4.getC4_p418a());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p416_7, modulo4.getC4_p416_7());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p416_8, modulo4.getC4_p416_8());
//                    escribirCampoXml(serializer, SQLConstantes.modulo4_c4_p416_o, modulo4.getC4_p416_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_obs_cap4, modulo4.getObs_cap4());
                    escribirCampoXml(serializer, SQLConstantes.modulo4_COB400, modulo4.getCOB400());
                    serializer.endTag("", "MODULO4");
                }
                serializer.endTag("", "MODULO4S");
            }

            if(modulo5s.size()>0) {
                serializer.startTag("", "MODULO5S");
                for (Modulo5 modulo5 : modulo5s) {
                    serializer.startTag("", "MODULO5");
                    escribirCampoXml(serializer, SQLConstantes.modulo5_id, modulo5.get_id());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_id_informante, modulo5.getIdInformante());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_id_hogar, modulo5.getIdHogar());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_id_vivienda, modulo5.getIdVivienda());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p501a, modulo5.getC5_p501a());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p501, modulo5.getC5_p501());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p501b, modulo5.getC5_p501b());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p502_c, modulo5.getC5_p502_c());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p502, modulo5.getC5_p502());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p502_eleccion, modulo5.getC5_p502_eleccion());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p502_o, modulo5.getC5_p502_o());

                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p503, modulo5.getC5_p503());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p504, modulo5.getC5_p504());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p504_anio, modulo5.getC5_p504_anio());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p504_grado, modulo5.getC5_p504_grado());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p504_ce, modulo5.getC5_p504_ce());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p505, modulo5.getC5_p505());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p506, modulo5.getC5_p506());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p506a, modulo5.getC5_p506a());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p506_1, modulo5.getC5_p506_1());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p506_2, modulo5.getC5_p506_2());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p506_3, modulo5.getC5_p506_3());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p506_4, modulo5.getC5_p506_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p507, modulo5.getC5_p507());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p507_anio, modulo5.getC5_p507_anio());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p507_grado, modulo5.getC5_p507_grado());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p507_ce, modulo5.getC5_p507_ce());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p507_dist, modulo5.getC5_p507_dist());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p507_prov, modulo5.getC5_p507_prov());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p507_dep, modulo5.getC5_p507_dep());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p508_1, modulo5.getC5_p508_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p508_2, modulo5.getC5_p508_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p508_3, modulo5.getC5_p508_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p508_4, modulo5.getC5_p508_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p508_4_o, modulo5.getC5_p508_4_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p508_5, modulo5.getC5_p508_5());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p508_6, modulo5.getC5_p508_6());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p508_7, modulo5.getC5_p508_7());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p508_8, modulo5.getC5_p508_8());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p508_9, modulo5.getC5_p508_9());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p508_10, modulo5.getC5_p508_10());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p508_11, modulo5.getC5_p508_11());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p508_o, modulo5.getC5_p508_o());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p509, modulo5.getC5_p509());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p509_1, modulo5.getC5_p509_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p509_2, modulo5.getC5_p509_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p509_3, modulo5.getC5_p509_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p509_4, modulo5.getC5_p509_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p509_5, modulo5.getC5_p509_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p509_6, modulo5.getC5_p509_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p509_7, modulo5.getC5_p509_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p509_7_o, modulo5.getC5_p509_7_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p509_8, modulo5.getC5_p509_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p510, modulo5.getC5_p510());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p510_o, modulo5.getC5_p510_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p511, modulo5.getC5_p511());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p511_o, modulo5.getC5_p511_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p512, modulo5.getC5_p512());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p512_o, modulo5.getC5_p512_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p513, modulo5.getC5_p513());
//                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p513_o, modulo5.getC5_p513_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p514, modulo5.getC5_p514());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p514_o, modulo5.getC5_p514_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p515, modulo5.getC5_p515());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p515_o, modulo5.getC5_p515_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p516, modulo5.getC5_p516());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_c5_p516_o, modulo5.getC5_p516_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_obs_cap5, modulo5.getObs_cap5());
                    escribirCampoXml(serializer, SQLConstantes.modulo5_COB500, modulo5.getCOB500());
                    serializer.endTag("", "MODULO5");
                }
                serializer.endTag("", "MODULO5S");
            }

            if(modulo6s.size()>0) {
                serializer.startTag("", "MODULO6S");
                for (Modulo6 modulo6 : modulo6s) {
                    serializer.startTag("", "MODULO6");
                    escribirCampoXml(serializer, SQLConstantes.modulo6_id, modulo6.get_id());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_id_informante, modulo6.getIdInformante());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_id_hogar, modulo6.getIdHogar());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_id_vivienda, modulo6.getIdVivienda());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p601, modulo6.getC6_p601());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p602, modulo6.getC6_p602());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p603, modulo6.getC6_p603());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p604_1, modulo6.getC6_p604_1());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p604_2, modulo6.getC6_p604_2());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p604_3, modulo6.getC6_p604_3());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p604_4, modulo6.getC6_p604_4());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p604_5, modulo6.getC6_p604_5());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p604_6, modulo6.getC6_p604_6());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p604_7, modulo6.getC6_p604_7());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p604_8, modulo6.getC6_p604_8());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p604_9, modulo6.getC6_p604_9());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p604_10, modulo6.getC6_p604_10());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p604_11, modulo6.getC6_p604_11());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p604_o, modulo6.getC6_p604_o());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p605_1, modulo6.getC6_p605_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p605_2, modulo6.getC6_p605_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p605_3, modulo6.getC6_p605_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p605_4, modulo6.getC6_p605_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p605_5, modulo6.getC6_p605_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p605_6, modulo6.getC6_p605_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p605_7, modulo6.getC6_p605_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p605_8, modulo6.getC6_p605_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p605_9, modulo6.getC6_p605_9());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p605_10, modulo6.getC6_p605_10());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p605_11, modulo6.getC6_p605_11());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p605_12, modulo6.getC6_p605_12());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p605_o, modulo6.getC6_p605_o());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p605, modulo6.getC6_p605());//----
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p606, modulo6.getC6_p606());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p606_o, modulo6.getC6_p606_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p607, modulo6.getC6_p607());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p608, modulo6.getC6_p608());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p608_o, modulo6.getC6_p608_o());//-----
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p609, modulo6.getC6_p609());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p609_cod, modulo6.getC6_p609_cod());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_pd, modulo6.getC6_p610_pd());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_pl, modulo6.getC6_p610_pl());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_pm, modulo6.getC6_p610_pm());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_pmi, modulo6.getC6_p610_pmi());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_pj, modulo6.getC6_p610_pj());//--
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_pv, modulo6.getC6_p610_pv());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_ps, modulo6.getC6_p610_ps());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_pt, modulo6.getC6_p610_pt());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_sd, modulo6.getC6_p610_sd());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_sl, modulo6.getC6_p610_sl());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_sm, modulo6.getC6_p610_sm());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_smi, modulo6.getC6_p610_smi());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_sj, modulo6.getC6_p610_sj());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_sv, modulo6.getC6_p610_sv());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_ss, modulo6.getC6_p610_ss());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_st, modulo6.getC6_p610_st());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p610_t, modulo6.getC6_p610_t());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p611, modulo6.getC6_p611());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p611_cod, modulo6.getC6_p611_cod());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p611a, modulo6.getC6_p611a());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p611b, modulo6.getC6_p611b());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p612, modulo6.getC6_p612());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p612_nro, modulo6.getC6_p612_nro());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p613, modulo6.getC6_p613());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p614_mon, modulo6.getC6_p614_mon());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p614_esp, modulo6.getC6_p614_esp());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_pd, modulo6.getC6_p615_pd());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_pl, modulo6.getC6_p615_pl());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_pm, modulo6.getC6_p615_pm());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_pmi, modulo6.getC6_p615_pmi());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_pj, modulo6.getC6_p615_pj());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_pv, modulo6.getC6_p615_pv());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_ps, modulo6.getC6_p615_ps());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_pt, modulo6.getC6_p615_pt());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_sd, modulo6.getC6_p615_sd());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_sl, modulo6.getC6_p615_sl());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_sm, modulo6.getC6_p615_sm());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_smi, modulo6.getC6_p615_smi());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_sj, modulo6.getC6_p615_sj());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_sv, modulo6.getC6_p615_sv());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_ss, modulo6.getC6_p615_ss());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_st, modulo6.getC6_p615_st());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_t, modulo6.getC6_p615_t());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_mon, modulo6.getC6_p615_mon());//--
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p615_esp, modulo6.getC6_p615_esp());//--
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p616, modulo6.getC6_p616());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p616_a, modulo6.getC6_p616_a());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p616_mon, modulo6.getC6_p616_mon());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p616_esp, modulo6.getC6_p616_esp());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p616_nas, modulo6.getC6_p616_nas());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p617, modulo6.getC6_p617());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p617_dist, modulo6.getC6_p617_dist());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p617_prov, modulo6.getC6_p617_prov());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p617_dep, modulo6.getC6_p617_dep());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p618, modulo6.getC6_p618());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p619, modulo6.getC6_p619());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p619_o, modulo6.getC6_p619_o());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p620, modulo6.getC6_p620());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p620_o, modulo6.getC6_p620_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p621, modulo6.getC6_p621());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p622_mon, modulo6.getC6_p622_mon());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p622_esp, modulo6.getC6_p622_esp());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p622, modulo6.getC6_p622());//--
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p622_o, modulo6.getC6_p622_o());//--
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p623_mon, modulo6.getC6_p623_mon());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p623_esp, modulo6.getC6_p623_esp());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p623_nas, modulo6.getC6_p623_nas());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p623, modulo6.getC6_p623());//----
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p623_o, modulo6.getC6_p623_o());//----
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p624_mon, modulo6.getC6_p624_mon());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p624_esp, modulo6.getC6_p624_esp());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p624_nas, modulo6.getC6_p624_nas());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p624_nas2, modulo6.getC6_p624_nas2());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p624, modulo6.getC6_p624());//-----
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p625, modulo6.getC6_p625());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p625_cod_dist, modulo6.getC6_p625_cod_dist());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p625_dist, modulo6.getC6_p625_dist());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p625_cod_prov, modulo6.getC6_p625_cod_prov());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p625_prov, modulo6.getC6_p625_prov());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p625_cod_depa, modulo6.getC6_p625_cod_depa());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p625_depa, modulo6.getC6_p625_depa());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p625_1, modulo6.getC6_p625_1());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p625_2, modulo6.getC6_p625_2());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p625_3, modulo6.getC6_p625_3());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p625_4, modulo6.getC6_p625_4());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p625_5, modulo6.getC6_p625_5());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p625_6, modulo6.getC6_p625_6());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p625_o, modulo6.getC6_p625_o());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p626, modulo6.getC6_p626());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p627, modulo6.getC6_p627());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p628, modulo6.getC6_p628());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p628_o, modulo6.getC6_p628_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p629, modulo6.getC6_p629());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p630, modulo6.getC6_p630());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p631, modulo6.getC6_p631());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p631_o, modulo6.getC6_p631_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p632_1, modulo6.getC6_p632_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p632_2, modulo6.getC6_p632_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p632_3, modulo6.getC6_p632_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p632_4, modulo6.getC6_p632_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p632_5, modulo6.getC6_p632_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p632_6, modulo6.getC6_p632_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p632_7, modulo6.getC6_p632_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p632_8, modulo6.getC6_p632_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p632_9, modulo6.getC6_p632_9());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p632_10, modulo6.getC6_p632_10());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p632_10_o, modulo6.getC6_p632_10_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p632i, modulo6.getC6_p632i());
                    //escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p632_11, modulo6.getC6_p632_11());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p633, modulo6.getC6_p633());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p634_1, modulo6.getC6_p634_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p634_2, modulo6.getC6_p634_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p634_3, modulo6.getC6_p634_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p634_4, modulo6.getC6_p634_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p634_5, modulo6.getC6_p634_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p634_6, modulo6.getC6_p634_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p634_7, modulo6.getC6_p634_7());
                    //escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p634_6_o, modulo6.getC6_p634_6_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p634_7_o, modulo6.getC6_p634_7_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p635, modulo6.getC6_p635());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p636, modulo6.getC6_p636());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p637, modulo6.getC6_p637());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p638_1, modulo6.getC6_p638_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p638_1_frec, modulo6.getC6_p638_1_frec());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p638_1_monto, modulo6.getC6_p638_1_monto());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p638_2, modulo6.getC6_p638_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p638_2_frec, modulo6.getC6_p638_2_frec());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p638_2_monto, modulo6.getC6_p638_2_monto());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p638_3, modulo6.getC6_p638_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p638_3_frec, modulo6.getC6_p638_3_frec());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p638_3_monto, modulo6.getC6_p638_3_monto());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p638_4, modulo6.getC6_p638_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p638_4_frec, modulo6.getC6_p638_4_frec());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p638_4_monto, modulo6.getC6_p638_4_monto());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p639_1, modulo6.getC6_p639_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p639_1_med, modulo6.getC6_p639_1_med());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p639_1_med_o, modulo6.getC6_p639_1_med_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p639_1_frec, modulo6.getC6_p639_1_frec());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p639_1_frec_o, modulo6.getC6_p639_1_frec_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p639_1_monto, modulo6.getC6_p639_1_monto());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p639_2, modulo6.getC6_p639_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p639_2_med, modulo6.getC6_p639_2_med());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p639_2_med_o, modulo6.getC6_p639_2_med_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p639_2_frec, modulo6.getC6_p639_2_frec());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p639_2_frec_o, modulo6.getC6_p639_2_frec_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p639_2_monto, modulo6.getC6_p639_2_monto());
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p629_1, modulo6.getC6_p629_1());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p629_2, modulo6.getC6_p629_2());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p629_3, modulo6.getC6_p629_3());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p629_4, modulo6.getC6_p629_4());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p629_o, modulo6.getC6_p629_o());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p629_1_f, modulo6.getC6_p629_1_f());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p629_1_m, modulo6.getC6_p629_1_m());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p629_2_f, modulo6.getC6_p629_2_f());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p629_2_m, modulo6.getC6_p629_2_m());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p629_3_f, modulo6.getC6_p629_3_f());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p629_3_m, modulo6.getC6_p629_3_m());////------
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p629_4_f, modulo6.getC6_p629_4_f());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p629_4_m, modulo6.getC6_p629_4_m());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p630_1, modulo6.getC6_p630_1());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p630_1med, modulo6.getC6_p630_1med());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p630_1o, modulo6.getC6_p630_1o());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p630_1frec, modulo6.getC6_p630_1frec());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p630_1frec, modulo6.getC6_p630_1frec_o());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p630_1mont, modulo6.getC6_p630_1mont());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p630_2, modulo6.getC6_p630_2());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p630_2med, modulo6.getC6_p630_2med());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p630_2o, modulo6.getC6_p630_2o());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p630_2mont, modulo6.getC6_p630_2mont());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p630_2frec, modulo6.getC6_p630_2frec());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo6_c6_p630_2frec_o, modulo6.getC6_p630_2frec_o());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo6_obs_cap6, modulo6.getObs_cap6());
                    escribirCampoXml(serializer, SQLConstantes.modulo6_COB600, modulo6.getCOB600());
                    serializer.endTag("", "MODULO6");
                }
                serializer.endTag("", "MODULO6S");
            }

            if(modulo7s.size()>0) {
                serializer.startTag("", "MODULO7S");
                for (Modulo7 modulo7 : modulo7s) {
                    serializer.startTag("", "MODULO7");
                    escribirCampoXml(serializer, SQLConstantes.modulo7_id, modulo7.get_id());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_id_informante, modulo7.getIdInformante());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_id_hogar, modulo7.getIdHogar());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_id_vivienda, modulo7.getIdVivienda());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p701, modulo7.getC7_p701());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p702_1, modulo7.getC7_p702_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p702_2, modulo7.getC7_p702_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p702_3, modulo7.getC7_p702_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p702_4, modulo7.getC7_p702_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p702_5, modulo7.getC7_p702_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p702_6, modulo7.getC7_p702_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p702_7, modulo7.getC7_p702_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p702_7_o, modulo7.getC7_p702_7_o());
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p702_8, modulo7.getC7_p702_8());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p702_9, modulo7.getC7_p702_9());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p702_10, modulo7.getC7_p702_10());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p702_o, modulo7.getC7_p702_o());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p703, modulo7.getC7_p703());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p703_1, modulo7.getC7_p703_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p703_2, modulo7.getC7_p703_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p703_3, modulo7.getC7_p703_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p703_4, modulo7.getC7_p703_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p703_5, modulo7.getC7_p703_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p703_6, modulo7.getC7_p703_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p703_7, modulo7.getC7_p703_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p703_8, modulo7.getC7_p703_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p703_9, modulo7.getC7_p703_9());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p703_10, modulo7.getC7_p703_10());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p703_10_o, modulo7.getC7_p703_10_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p704_1, modulo7.getC7_p704_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p704_2, modulo7.getC7_p704_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p704_3, modulo7.getC7_p704_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p704_4, modulo7.getC7_p704_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p704_5, modulo7.getC7_p704_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p704_6, modulo7.getC7_p704_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p704_7, modulo7.getC7_p704_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p704_8, modulo7.getC7_p704_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p704_9, modulo7.getC7_p704_9());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p704_8_o, modulo7.getC7_p704_8_o());
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p704_o, modulo7.getC7_p704_o());//--
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p705, modulo7.getC7_p705());
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p705_1, modulo7.getC7_p705_1());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p705_2, modulo7.getC7_p705_2());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p705_3, modulo7.getC7_p705_3());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p705_4, modulo7.getC7_p705_4());//---//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p705_5, modulo7.getC7_p705_5());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p705_6, modulo7.getC7_p705_6());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p705_7, modulo7.getC7_p705_7());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p705_o, modulo7.getC7_p705_o());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p706_1, modulo7.getC7_p706_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p706_2, modulo7.getC7_p706_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p706_3, modulo7.getC7_p706_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p706_4, modulo7.getC7_p706_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p706_5, modulo7.getC7_p706_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p706_6, modulo7.getC7_p706_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p706_6_o, modulo7.getC7_p706_6_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p706_7, modulo7.getC7_p706_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p706_8, modulo7.getC7_p706_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p706_9, modulo7.getC7_p706_9());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p706_8_o, modulo7.getC7_p706_8_o());
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p706, modulo7.getC7_p706());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p707_1, modulo7.getC7_p707_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p707_2, modulo7.getC7_p707_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p707_3, modulo7.getC7_p707_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p707_4, modulo7.getC7_p707_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p707_5, modulo7.getC7_p707_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p707_6, modulo7.getC7_p707_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p707_7, modulo7.getC7_p707_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p707_8, modulo7.getC7_p707_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p707_8_o, modulo7.getC7_p707_8_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p707_9, modulo7.getC7_p707_9());
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p707_o, modulo7.getC7_p707_8_o());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p708_1, modulo7.getC7_p708_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p708_2, modulo7.getC7_p708_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p708_3, modulo7.getC7_p708_3());
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p708_4, modulo7.getC7_p708_4());//----
//                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p708_5, modulo7.getC7_p708_5());//----
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p709_1, modulo7.getC7_p709_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p709_2, modulo7.getC7_p709_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p709_3, modulo7.getC7_p709_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p709_4, modulo7.getC7_p709_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p709_5, modulo7.getC7_p709_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p709_6, modulo7.getC7_p709_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p709_7, modulo7.getC7_p709_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p709_8, modulo7.getC7_p709_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p709_9, modulo7.getC7_p709_9());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p709_10a, modulo7.getC7_p709_10a());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p709_10, modulo7.getC7_p709_10());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p709_10_o, modulo7.getC7_p709_10_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_c7_p709_11, modulo7.getC7_p709_11());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_obs_cap7, modulo7.getObs_cap7());
                    escribirCampoXml(serializer, SQLConstantes.modulo7_COB700, modulo7.getCOB700());
                    serializer.endTag("", "MODULO7");
                }
                serializer.endTag("", "MODULO7S");
            }

            if(modulo8s.size()>0) {
                serializer.startTag("", "MODULO8S");
                for (Modulo8 modulo8 : modulo8s) {
                    serializer.startTag("", "MODULO8");
                    escribirCampoXml(serializer, SQLConstantes.modulo8_id, modulo8.get_id());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_id_informante, modulo8.getIdInformante());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_id_hogar, modulo8.getIdHogar());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_id_vivienda, modulo8.getIdVivienda());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p801, modulo8.getC8_p801());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802_1, modulo8.getC8_p802_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802_2, modulo8.getC8_p802_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802_3, modulo8.getC8_p802_3());
                    //escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_1, modulo8.getC8_p802a_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_1_1, modulo8.getC8_p802a_1_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_1_2, modulo8.getC8_p802a_1_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_1_3, modulo8.getC8_p802a_1_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_1_4, modulo8.getC8_p802a_1_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_1_5, modulo8.getC8_p802a_1_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_1_6, modulo8.getC8_p802a_1_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_1_7, modulo8.getC8_p802a_1_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_1_8, modulo8.getC8_p802a_1_8());
                    //escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_2, modulo8.getC8_p802a_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_2_1, modulo8.getC8_p802a_2_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_2_2, modulo8.getC8_p802a_2_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_2_3, modulo8.getC8_p802a_2_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_2_4, modulo8.getC8_p802a_2_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_2_5, modulo8.getC8_p802a_2_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_2_6, modulo8.getC8_p802a_2_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_2_7, modulo8.getC8_p802a_2_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_2_8, modulo8.getC8_p802a_2_8());
                    //escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_3, modulo8.getC8_p802a_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_3_1, modulo8.getC8_p802a_3_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_3_2, modulo8.getC8_p802a_3_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_3_3, modulo8.getC8_p802a_3_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_3_4, modulo8.getC8_p802a_3_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_3_5, modulo8.getC8_p802a_3_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_3_6, modulo8.getC8_p802a_3_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_3_7, modulo8.getC8_p802a_3_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_3_8, modulo8.getC8_p802a_3_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_1_o, modulo8.getC8_p802a_1_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_2_o, modulo8.getC8_p802a_2_o());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802a_3_o, modulo8.getC8_p802a_3_o());
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p802, modulo8.getC8_p802());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p803, modulo8.getC8_p803());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804_1, modulo8.getC8_p804_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804_2, modulo8.getC8_p804_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804_3, modulo8.getC8_p804_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804_4, modulo8.getC8_p804_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804_5, modulo8.getC8_p804_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804_6, modulo8.getC8_p804_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804_7, modulo8.getC8_p804_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804_8, modulo8.getC8_p804_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804_9, modulo8.getC8_p804_9());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804_10, modulo8.getC8_p804_10());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804_11, modulo8.getC8_p804_11());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804_12, modulo8.getC8_p804_12());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804_13, modulo8.getC8_p804_13());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804_14, modulo8.getC8_p804_14());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804_o, modulo8.getC8_p804_o());
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p804, modulo8.getC8_p804());//-----
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p805_1, modulo8.getC8_p805_1());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p805_2, modulo8.getC8_p805_2());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p805_3, modulo8.getC8_p805_3());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p805_4, modulo8.getC8_p805_4());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p805_5, modulo8.getC8_p805_5());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p805_6, modulo8.getC8_p805_6());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p805_7, modulo8.getC8_p805_7());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p805_8, modulo8.getC8_p805_8());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p805_9, modulo8.getC8_p805_9());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p806_1, modulo8.getC8_p806_1());
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p806_2, modulo8.getC8_p806_2());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p806_3, modulo8.getC8_p806_3());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p806_4, modulo8.getC8_p806_4());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p806_5, modulo8.getC8_p806_5());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p806_6, modulo8.getC8_p806_6());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p807, modulo8.getC8_p807());
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p808_1, modulo8.getC8_p808_1());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p808_2, modulo8.getC8_p808_2());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p808_3, modulo8.getC8_p808_3());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p808_4, modulo8.getC8_p808_4());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p808_5, modulo8.getC8_p808_5());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p808_6, modulo8.getC8_p808_6());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p808_7, modulo8.getC8_p808_7());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p808_8, modulo8.getC8_p808_8());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p808_9, modulo8.getC8_p808_9());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p808_10, modulo8.getC8_p808_10());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p808_11, modulo8.getC8_p808_11());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p808_12, modulo8.getC8_p808_12());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p808_13, modulo8.getC8_p808_13());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p808_o, modulo8.getC8_p808_o());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p809, modulo8.getC8_p809());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p810_1, modulo8.getC8_p810_1());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p810_2, modulo8.getC8_p810_2());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p810_3, modulo8.getC8_p810_3());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p810_4, modulo8.getC8_p810_4());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p810_5, modulo8.getC8_p810_5());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p810_6, modulo8.getC8_p810_6());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p810_7, modulo8.getC8_p810_7());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p810_8, modulo8.getC8_p810_8());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p810_9, modulo8.getC8_p810_9());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p810_10, modulo8.getC8_p810_10());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p810_11, modulo8.getC8_p810_11());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p810_12, modulo8.getC8_p810_12());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p810_13, modulo8.getC8_p810_13());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p810_o, modulo8.getC8_p810_o());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p811, modulo8.getC8_p811());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p812, modulo8.getC8_p812());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p813_1, modulo8.getC8_p813_1());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p813_2, modulo8.getC8_p813_2());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p813_3, modulo8.getC8_p813_3());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p813_4, modulo8.getC8_p813_4());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p813_5, modulo8.getC8_p813_5());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p813_6, modulo8.getC8_p813_6());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p813_7, modulo8.getC8_p813_7());//---//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p813_8, modulo8.getC8_p813_8());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p813_9, modulo8.getC8_p813_9());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p813_10, modulo8.getC8_p813_10());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p813_11, modulo8.getC8_p813_11());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p813_12, modulo8.getC8_p813_12());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p813_13, modulo8.getC8_p813_13());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p813_14, modulo8.getC8_p813_14());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p813_o, modulo8.getC8_p813_o());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p814_1, modulo8.getC8_p814_1());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p814_2, modulo8.getC8_p814_2());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p814_3, modulo8.getC8_p814_3());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p814_4, modulo8.getC8_p814_4());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p814_5, modulo8.getC8_p814_5());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p814_6, modulo8.getC8_p814_6());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p814_7, modulo8.getC8_p814_7());//---//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p814_8, modulo8.getC8_p814_8());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p815, modulo8.getC8_p815());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p816_1, modulo8.getC8_p816_1());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p816_2, modulo8.getC8_p816_2());//---//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p816_3, modulo8.getC8_p816_3());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p816_4, modulo8.getC8_p816_4());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p816_5, modulo8.getC8_p816_5());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p816_6, modulo8.getC8_p816_6());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p816_7, modulo8.getC8_p816_7());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p816_8, modulo8.getC8_p816_8());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p816_9, modulo8.getC8_p816_9());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p816_10, modulo8.getC8_p816_10());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p816_11, modulo8.getC8_p816_11());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p816_12, modulo8.getC8_p816_12());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p816_13, modulo8.getC8_p816_13());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p816_o, modulo8.getC8_p816_o());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p817, modulo8.getC8_p817());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p818, modulo8.getC8_p818());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p819_1, modulo8.getC8_p819_1());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p819_2, modulo8.getC8_p819_2());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p819_3, modulo8.getC8_p819_3());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p819_4, modulo8.getC8_p819_4());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p819_5, modulo8.getC8_p819_5());//---//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p819_6, modulo8.getC8_p819_6());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p819_7, modulo8.getC8_p819_7());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p819_8, modulo8.getC8_p819_8());//---//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p819_9, modulo8.getC8_p819_9());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p819_10, modulo8.getC8_p819_10());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p819_11, modulo8.getC8_p819_11());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p819_12, modulo8.getC8_p819_12());//---//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p819_13, modulo8.getC8_p819_13());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p819_14, modulo8.getC8_p819_14());//---//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p819_o, modulo8.getC8_p819_o());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p820_1, modulo8.getC8_p820_1());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p820_2, modulo8.getC8_p820_2());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p820_3, modulo8.getC8_p820_3());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p820_4, modulo8.getC8_p820_4());//---//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p820_5, modulo8.getC8_p820_5());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p820_6, modulo8.getC8_p820_6());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p820_7, modulo8.getC8_p820_7());//---//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p820_8, modulo8.getC8_p820_8());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p820_9, modulo8.getC8_p820_9());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p820_10, modulo8.getC8_p820_10());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p820_11, modulo8.getC8_p820_11());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p820_o, modulo8.getC8_p820_o());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p821_1, modulo8.getC8_p821_1());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p821_2, modulo8.getC8_p821_2());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p821_3, modulo8.getC8_p821_3());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p821_4, modulo8.getC8_p821_4());//---//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p821_5, modulo8.getC8_p821_5());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p821_6, modulo8.getC8_p821_6());//---//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p821_7, modulo8.getC8_p821_7());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p821_8, modulo8.getC8_p821_8());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p822, modulo8.getC8_p822());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p823_1, modulo8.getC8_p823_1());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p823_2, modulo8.getC8_p823_2());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p823_3, modulo8.getC8_p823_3());//---//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p823_4, modulo8.getC8_p823_4());//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p823_5, modulo8.getC8_p823_5());//---//---
//                    escribirCampoXml(serializer, SQLConstantes.modulo8_c8_p823_o, modulo8.getC8_p823_o());//---
                    escribirCampoXml(serializer, SQLConstantes.modulo8_obs_cap8, modulo8.getObs_cap8());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_COB800, modulo8.getCOB800());
                    escribirCampoXml(serializer, SQLConstantes.modulo8_email, modulo8.getEmail());

                    serializer.endTag("", "MODULO8");
                }
                serializer.endTag("", "MODULO8S");
            }

            if(pojoFragmentViviendas.size()>0) {
                serializer.startTag("", "FRAGMENTS_VIVIENDA");
                for (POJOFragmentVivienda pojoFragmentVivienda : pojoFragmentViviendas) {
                    if (pojoFragmentVivienda != null){
                        serializer.startTag("", "FRAGMENT_VIVIENDA");
                        escribirCampoXml(serializer, SQLConstantes.fragments_vivienda_id, pojoFragmentVivienda.get_id());
                        escribirCampoXml(serializer, SQLConstantes.fragments_vivienda_caratula, pojoFragmentVivienda.getCaratula());
                        escribirCampoXml(serializer, SQLConstantes.fragments_vivienda_hogares, pojoFragmentVivienda.getHogares());
                        serializer.endTag("", "FRAGMENT_VIVIENDA");
                    }
                }
                serializer.endTag("", "FRAGMENTS_VIVIENDA");
            }

            if(pojoFragmentHogars.size()>0) {
                serializer.startTag("", "FRAGMENTS_HOGAR");
                for (POJOFragmentHogar pojoFragmentHogar : pojoFragmentHogars) {
                    if (pojoFragmentHogar != null){
                        serializer.startTag("", "FRAGMENT_HOGAR");
                        escribirCampoXml(serializer, SQLConstantes.fragments_hogar_id, pojoFragmentHogar.get_id());
                        escribirCampoXml(serializer, SQLConstantes.fragments_hogar_id_vivienda, pojoFragmentHogar.getId_vivienda());
                        escribirCampoXml(serializer, SQLConstantes.fragments_hogar_visitas_encuestador, pojoFragmentHogar.getVisitas_encuestador());
                        escribirCampoXml(serializer, SQLConstantes.fragments_hogar_visitas_supervisor, pojoFragmentHogar.getVisitas_supervisor());
                        escribirCampoXml(serializer, SQLConstantes.fragments_hogar_funcionarios, pojoFragmentHogar.getFuncionarios());
                        escribirCampoXml(serializer, SQLConstantes.fragments_hogar_p101p107, pojoFragmentHogar.getP101p107());
                        escribirCampoXml(serializer, SQLConstantes.fragments_hogar_p108p113, pojoFragmentHogar.getP108p113());
                        escribirCampoXml(serializer, SQLConstantes.fragments_hogar_p201p207, pojoFragmentHogar.getP201p207());
                        serializer.endTag("", "FRAGMENT_HOGAR");
                    }

                }
                serializer.endTag("", "FRAGMENTS_HOGAR");
            }

            if(pojoFragments.size()>0) {
                serializer.startTag("", "FRAGMENTS_ENCUESTADO");
                for (POJOFragment pojoFragment : pojoFragments) {
                    if (pojoFragment != null){
                        serializer.startTag("", "FRAGMENT_ENCUESTADO");
                        escribirCampoXml(serializer, SQLConstantes.fragments_id, pojoFragment.get_id());
                        escribirCampoXml(serializer, SQLConstantes.fragments_id_vivienda, pojoFragment.getId_vivienda());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p301p305, pojoFragment.getP301p305());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p306p308, pojoFragment.getP306p308());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p309, pojoFragment.getP309());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p310p312, pojoFragment.getP310p312());
//                        escribirCampoXml(serializer, SQLConstantes.fragments_p313p317, pojoFragment.getP313p317());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p318, pojoFragment.getP318());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p401p404, pojoFragment.getP401p404());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p405p407, pojoFragment.getP405p407());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p408p410, pojoFragment.getP408p410());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p411p416, pojoFragment.getP411p416());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p501p505, pojoFragment.getP501p505());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p506p507, pojoFragment.getP506p507());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p508p511, pojoFragment.getP508p511());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p512p513, pojoFragment.getP512p513());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p601p604, pojoFragment.getP601p604());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p605p608, pojoFragment.getP605p608());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p609p612, pojoFragment.getP609p612());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p613p617, pojoFragment.getP613p617());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p618p621, pojoFragment.getP618p621());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p622p625, pojoFragment.getP622p625());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p626p629, pojoFragment.getP626p629());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p630, pojoFragment.getP630());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p701p705, pojoFragment.getP701p705());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p706p709, pojoFragment.getP706p709());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p801p804, pojoFragment.getP801p804());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p805p808, pojoFragment.getP805p808());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p809p812, pojoFragment.getP809p812());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p813p816, pojoFragment.getP813p816());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p817p820, pojoFragment.getP817p820());
                        escribirCampoXml(serializer, SQLConstantes.fragments_p821p823, pojoFragment.getP821p823());
                        serializer.endTag("", "FRAGMENT_ENCUESTADO");
                    }
                }
                serializer.endTag("", "FRAGMENTS_ENCUESTADO");
            }

            if(pojoLayouts.size()>0) {
                serializer.startTag("", "LAYOUTS_ENCUESTADO");
                for (POJOLayout pojoLayout : pojoLayouts) {
                    if (pojoLayout != null){
                        serializer.startTag("", "LAYOUT_ENCUESTADO");
                        escribirCampoXml(serializer, SQLConstantes.layouts_id, pojoLayout.get_id());
                        escribirCampoXml(serializer, SQLConstantes.layouts_id_vivienda, pojoLayout.getId_vivienda());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p301, pojoLayout.getP301());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p302, pojoLayout.getP302());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p303, pojoLayout.getP303());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p304, pojoLayout.getP304());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p305, pojoLayout.getP305());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p306, pojoLayout.getP306());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p307, pojoLayout.getP307());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p308, pojoLayout.getP308());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p309, pojoLayout.getP309());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p309_1, pojoLayout.getP309_1());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p310, pojoLayout.getP310());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p311, pojoLayout.getP311());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p312, pojoLayout.getP312());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p313, pojoLayout.getP313());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p314, pojoLayout.getP314());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p315, pojoLayout.getP315());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p316, pojoLayout.getP316());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p317, pojoLayout.getP317());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p318, pojoLayout.getP318());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p401, pojoLayout.getP401());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p402, pojoLayout.getP402());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p403, pojoLayout.getP403());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p404, pojoLayout.getP404());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p405, pojoLayout.getP405());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p406, pojoLayout.getP406());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p407, pojoLayout.getP407());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p408, pojoLayout.getP408());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p409, pojoLayout.getP409());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p410, pojoLayout.getP410());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p411, pojoLayout.getP411());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p412, pojoLayout.getP412());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p413, pojoLayout.getP413());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p414, pojoLayout.getP414());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p415, pojoLayout.getP415());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p416, pojoLayout.getP416());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p501, pojoLayout.getP501());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p502, pojoLayout.getP502());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p503, pojoLayout.getP503());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p504, pojoLayout.getP504());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p505, pojoLayout.getP505());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p506, pojoLayout.getP506());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p507, pojoLayout.getP507());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p508, pojoLayout.getP508());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p509, pojoLayout.getP509());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p510, pojoLayout.getP510());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p511, pojoLayout.getP511());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p512, pojoLayout.getP512());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p513, pojoLayout.getP513());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p601, pojoLayout.getP601());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p602, pojoLayout.getP602());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p603, pojoLayout.getP603());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p604, pojoLayout.getP604());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p605, pojoLayout.getP605());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p606, pojoLayout.getP606());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p607, pojoLayout.getP607());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p608, pojoLayout.getP608());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p609, pojoLayout.getP609());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p610, pojoLayout.getP610());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p611, pojoLayout.getP611());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p611a, pojoLayout.getP611a());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p611b, pojoLayout.getP611b());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p612, pojoLayout.getP612());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p613, pojoLayout.getP613());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p614, pojoLayout.getP614());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p615, pojoLayout.getP615());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p616, pojoLayout.getP616());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p617, pojoLayout.getP617());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p618, pojoLayout.getP618());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p619, pojoLayout.getP619());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p620, pojoLayout.getP620());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p621, pojoLayout.getP621());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p622, pojoLayout.getP622());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p623, pojoLayout.getP623());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p624, pojoLayout.getP624());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p625, pojoLayout.getP625());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p626, pojoLayout.getP626());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p627, pojoLayout.getP627());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p628, pojoLayout.getP628());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p629, pojoLayout.getP629());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p630, pojoLayout.getP630());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p701, pojoLayout.getP701());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p702, pojoLayout.getP702());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p703, pojoLayout.getP703());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p704, pojoLayout.getP704());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p705, pojoLayout.getP705());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p706, pojoLayout.getP706());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p707, pojoLayout.getP707());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p708, pojoLayout.getP708());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p709, pojoLayout.getP709());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p801, pojoLayout.getP801());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p802, pojoLayout.getP802());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p803, pojoLayout.getP803());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p804, pojoLayout.getP804());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p805, pojoLayout.getP805());
                        escribirCampoXml(serializer, SQLConstantes.layouts_p806, pojoLayout.getP806());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p807, pojoLayout.getP807());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p808, pojoLayout.getP808());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p809, pojoLayout.getP809());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p810, pojoLayout.getP810());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p811, pojoLayout.getP811());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p812, pojoLayout.getP812());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p813, pojoLayout.getP813());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p814, pojoLayout.getP814());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p815, pojoLayout.getP815());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p816, pojoLayout.getP816());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p817, pojoLayout.getP817());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p818, pojoLayout.getP818());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p819, pojoLayout.getP819());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p820, pojoLayout.getP820());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p821, pojoLayout.getP821());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p822, pojoLayout.getP822());
//                        escribirCampoXml(serializer, SQLConstantes.layouts_p823, pojoLayout.getP823());
                        serializer.endTag("", "LAYOUT_ENCUESTADO");

                    }

                }
                serializer.endTag("", "LAYOUTS_ENCUESTADO");
            }

            if(coberturaFragments.size()>0) {
                serializer.startTag("", "COBERTURAS_FRAGMENT");
                for (CoberturaFragment coberturaFragment : coberturaFragments) {
                    if (coberturaFragment != null){
                        serializer.startTag("", "COBERTURA_FRAGMENT");
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_id, coberturaFragment.get_id());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_id_vivienda, coberturaFragment.getId_vivienda());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp301p305, coberturaFragment.getCp301p305());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp306p308, coberturaFragment.getCp306p308());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp309, coberturaFragment.getCp309());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp310p312, coberturaFragment.getCp310p312());
//                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp313p317, coberturaFragment.getCp313p317());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp318, coberturaFragment.getCp318());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp401p404, coberturaFragment.getCp401p404());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp405p407, coberturaFragment.getCp405p407());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp408p410, coberturaFragment.getCp408p410());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp411p416, coberturaFragment.getCp411p416());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp501p505, coberturaFragment.getCp501p505());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp506p507, coberturaFragment.getCp506p507());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp508p511, coberturaFragment.getCp508p511());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp512p513, coberturaFragment.getCp512p513());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp601p604, coberturaFragment.getCp601p604());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp605p608, coberturaFragment.getCp605p608());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp609p612, coberturaFragment.getCp609p612());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp613p617, coberturaFragment.getCp613p617());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp618p621, coberturaFragment.getCp618p621());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp622p625, coberturaFragment.getCp622p625());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp626p629, coberturaFragment.getCp626p629());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp630, coberturaFragment.getCp630());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp701p705, coberturaFragment.getCp701p705());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp706p709, coberturaFragment.getCp706p709());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp801p804, coberturaFragment.getCp801p804());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp805p808, coberturaFragment.getCp805p808());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp809p812, coberturaFragment.getCp809p812());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp813p816, coberturaFragment.getCp813p816());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp817p820, coberturaFragment.getCp817p820());
                        escribirCampoXml(serializer, SQLConstantes.cobertura_fragments_cp821p823, coberturaFragment.getCp821p823());
                        serializer.endTag("", "COBERTURA_FRAGMENT");
                    }

                }
                serializer.endTag("", "COBERTURAS_FRAGMENT");
            }

            serializer.endTag("", "ENPOVE");
            serializer.endDocument();
            String result = writer.toString();
            File nuevaCarpeta = new File(getExternalStorageDirectory(), "ENPOVE2022");
            nuevaCarpeta.mkdirs();
            File file = new File(nuevaCarpeta, nombreArchivo);
            IOHelper.writeToFile(file,result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void exportarViviendaServidor(String idVivienda){
        Data data =  new Data(this);
        data.open();

        Marco marco = data.getMarco(idVivienda);
        Caratula caratula = data.getCaratula(idVivienda);
        String nombreArchivo = idVivienda + ".xml";
        if(caratula!=null) {
            nombreArchivo = idVivienda+"-"+caratula.getAnio()+"-"+caratula.getVivienda()+".xml" ;
        }
        ArrayList<Hogar> hogares = data.getAllHogaresVivienda(idVivienda);
        ArrayList<VisitaEncuestador> visitaEncuestadors = data.getAllVisitasEncuestadorVivienda(idVivienda);
        ArrayList<VisitaSupervisor> visitaSupervisors = data.getAllVisitasSupervisorVivienda(idVivienda);
        ArrayList<ResVisitaEncuestador> resVisitaEncuestadors = data.getAllResultadoVisitaEncuestador(idVivienda);
        ArrayList<ResVisitaSupervisor> resVisitaSupervisors = data.getAllResultadoVisitaSupervisor(idVivienda);
        ArrayList<ResultadoResidente> resultadoResidentes = data.getAllResultadoResidente(idVivienda);
        Funcionario funcionario = data.getFuncionario(idVivienda);
        Modulo1V modulo1V = data.getModulo1V(idVivienda);
        ArrayList<Modulo1H> modulo1HS = data.getAllModulo1H(idVivienda);
        ArrayList<Residente> residentes = data.getAllResidentesVivienda(idVivienda);
        ArrayList<Modulo3> modulo3s = data.getAllModulo3(idVivienda);
        ArrayList<M3Pregunta318> m3Pregunta318s = data.getAllM3Pregunta318Vivienda(idVivienda);
        ArrayList<Modulo4> modulo4s = data.getAllModulo4(idVivienda);
        ArrayList<Modulo5> modulo5s = data.getAllModulo5(idVivienda);
        ArrayList<Modulo6> modulo6s = data.getAllModulo6(idVivienda);
        ArrayList<Modulo7> modulo7s = data.getAllModulo7(idVivienda);
        ArrayList<Modulo8> modulo8s = data.getAllModulo8(idVivienda);
        ArrayList<POJOFragment> pojoFragments = new ArrayList<>();
        ArrayList<POJOFragmentVivienda> pojoFragmentViviendas = new ArrayList<>();
        ArrayList<POJOFragmentHogar> pojoFragmentHogars = new ArrayList<>();
        ArrayList<POJOLayout> pojoLayouts = new ArrayList<>();
        ArrayList<CoberturaFragment> coberturaFragments = new ArrayList<>();
        for (Residente residente : residentes){
            if (residente.getC2_p207().equals("1")){
                pojoFragments.add(data.getFragmentsLayouts(residente.get_id()));
                pojoFragmentViviendas.add(data.getFragmentsVivienda(residente.getId_vivienda()));
                pojoFragmentHogars.add(data.getFragmentsHogar(residente.getId_hogar()));
                pojoLayouts.add(data.getLayouts(residente.get_id()));
                coberturaFragments.add(data.getCoberturaFragments(residente.get_id()));
            }
        }

        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("utf-8", true);
            serializer.startTag("", "ENPOVE");
            serializer.attribute("", "id",idVivienda);
            serializer.attribute("", "version",UtilsMethods.getVersion(getApplicationContext()));

            if(marco != null) {
                serializer.startTag("", "MARCO");
                escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA,marco.get_id()+"");
                escribirCampoXml(serializer, XMLConstantes.ANIO,marco.getAnio()+"");
                escribirCampoXml(serializer, XMLConstantes.MES,marco.getMes()+"");
                escribirCampoXml(serializer, XMLConstantes.PERIODO,marco.getPeriodo()+"");
                escribirCampoXml(serializer, XMLConstantes.ZONA,marco.getZona()+"");
                escribirCampoXml(serializer, XMLConstantes.CCDD,marco.getCcdd()+"");
                escribirCampoXml(serializer, XMLConstantes.DEPARTAMENTO,marco.getDepartamento()+"");
                escribirCampoXml(serializer, XMLConstantes.CCPP,marco.getCcpp()+"");
                escribirCampoXml(serializer, XMLConstantes.PROVINCIA,marco.getProvincia()+"");
                escribirCampoXml(serializer, XMLConstantes.CCDI,marco.getCcdi()+"");
                escribirCampoXml(serializer, XMLConstantes.DISTRITO,marco.getDistrito()+"");
                escribirCampoXml(serializer, XMLConstantes.CODCCPP,marco.getCodccpp()+"");
                escribirCampoXml(serializer, XMLConstantes.NOMCCPP,marco.getNomccpp()+"");
                escribirCampoXml(serializer, XMLConstantes.CONGLOMERADO,marco.getConglomerado()+"");
                escribirCampoXml(serializer, XMLConstantes.NOR_ORDEN,marco.getNorden()+"");
                escribirCampoXml(serializer, XMLConstantes.MANZANA,marco.getManzana_id()+"");
                escribirCampoXml(serializer, XMLConstantes.TIPVIA,marco.getTipvia()+"");
                escribirCampoXml(serializer, XMLConstantes.NOMVIA,marco.getNomvia()+"");
                escribirCampoXml(serializer, XMLConstantes.NRO_PUERTA,marco.getNropta()+"");
                escribirCampoXml(serializer, XMLConstantes.LOTE,marco.getLote()+"");
                escribirCampoXml(serializer, XMLConstantes.PISO,marco.getPiso()+"");
                escribirCampoXml(serializer, XMLConstantes.MANZ,marco.getMza()+"");
                escribirCampoXml(serializer, XMLConstantes.BLOCK,marco.getBlock()+"");
                escribirCampoXml(serializer, XMLConstantes.INTERIOR,marco.getInterior()+"");
                escribirCampoXml(serializer, XMLConstantes.KM,marco.getKm()+"");
                escribirCampoXml(serializer, XMLConstantes.USUARIO_ID,marco.getUsuario_id()+"");
                escribirCampoXml(serializer, XMLConstantes.USUARIO,marco.getUsuario()+"");
                escribirCampoXml(serializer, XMLConstantes.NOMBRE,marco.getNombre()+"");
                escribirCampoXml(serializer, XMLConstantes.DNI,marco.getDni()+"");
                escribirCampoXml(serializer, XMLConstantes.USUARIO_SUP_ID,marco.getUsuario_sup_id()+"");
                escribirCampoXml(serializer, XMLConstantes.ESTADO,marco.getEstado()+"");
                escribirCampoXml(serializer, XMLConstantes.NRO_SELEC_VIVIENDA,marco.getNro_selec_vivienda()+"");
                escribirCampoXml(serializer, XMLConstantes.TIPO_SELEC_VIVIENDA,marco.getTipo_selec_vivienda()+"");
                escribirCampoXml(serializer, XMLConstantes.VIVIENDA_REEMPLAZO,marco.getVivienda_reemplazo()+"");
                escribirCampoXml(serializer, XMLConstantes.TIPO,marco.getTipo()+"");
                escribirCampoXml(serializer, XMLConstantes.NRO_VIVIENDA,marco.getNroVivienda()+"");
                escribirCampoXml(serializer, XMLConstantes.NRO_SEGMENTO,marco.getNroSegmento()+"");
                escribirCampoXml(serializer, XMLConstantes.MARCO_PROVIENE,marco.getMarcoProviene()+"");
                escribirCampoXml(serializer, XMLConstantes.ESTRATO,marco.getEstrato()+"");
                escribirCampoXml(serializer, XMLConstantes.OBSERVACIONES,marco.getObservaciones()+"");
                escribirCampoXml(serializer, XMLConstantes.NRO_PUERTA2,marco.getNroPuerta2()+"");
                escribirCampoXml(serializer, XMLConstantes.JEFE_HOGAR,marco.getJefeHogar()+"");
                escribirCampoXml(serializer, XMLConstantes.TELEFONO,marco.getTelefono()+"");
                escribirCampoXml(serializer, XMLConstantes.CORREO,marco.getCorreo()+"");
                escribirCampoXml(serializer, XMLConstantes.AER_INICIAL,marco.getAerInicial()+"");
                escribirCampoXml(serializer, XMLConstantes.AER_FINAL,marco.getAerFinal()+"");
                escribirCampoXml(serializer, XMLConstantes.CONO,marco.getCono()+"");
                escribirCampoXml(serializer, XMLConstantes.AREA,marco.getArea()+"");
                escribirCampoXml(serializer, XMLConstantes.AREA_ENCUESTADA,marco.getAreaEncuesta()+"");
                escribirCampoXml(serializer, XMLConstantes.REGION,marco.getRegion()+"");
                escribirCampoXml(serializer, XMLConstantes.DOMINIO,marco.getDominio()+"");
                escribirCampoXml(serializer, XMLConstantes.FRENTE,marco.getFrente()+"");
                escribirCampoXml(serializer, XMLConstantes.LATITUD,marco.getLatitud()+"");
                escribirCampoXml(serializer, XMLConstantes.LONGITUD,marco.getLongitud()+"");
                serializer.endTag("", "MARCO");
            }

            if(caratula != null) {
                serializer.startTag("", "VIVIENDA");
                escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA,caratula.get_id()+"");
                escribirCampoXml(serializer, XMLConstantes.CONGLOMERADO,caratula.getConglomerado());
                escribirCampoXml(serializer, XMLConstantes.NSELV,caratula.getNro_selec_vivienda());
                escribirCampoXml(serializer, XMLConstantes.TSEL,caratula.getTipo_selec_vivienda());
                escribirCampoXml(serializer, XMLConstantes.VIV_REM,caratula.getVivienda_reemplazo());
                escribirCampoXml(serializer, XMLConstantes.N_VIV_REM,caratula.getNro_vivienda_reemplazo());
                escribirCampoXml(serializer, XMLConstantes.CCDD,caratula.getCcdd());
                escribirCampoXml(serializer, XMLConstantes.DEPARTAMENTO,caratula.getNom_dep());
                escribirCampoXml(serializer, XMLConstantes.CCPP,caratula.getCcpp());
                escribirCampoXml(serializer, XMLConstantes.PROVINCIA,caratula.getNom_prov());
                escribirCampoXml(serializer, XMLConstantes.CCDI,caratula.getCcdi());
                escribirCampoXml(serializer, XMLConstantes.DISTRITO,caratula.getNom_dist());
                escribirCampoXml(serializer, XMLConstantes.CODCCPP,caratula.getCodccpp());
                escribirCampoXml(serializer, XMLConstantes.NOMCCPP,caratula.getNom_ccpp());
                escribirCampoXml(serializer, XMLConstantes.ZONA ,caratula.getZona());
                escribirCampoXml(serializer, XMLConstantes.MANZANA ,caratula.getManzana_id());
                escribirCampoXml(serializer, XMLConstantes.VIVIENDA ,caratula.getVivienda());
                escribirCampoXml(serializer, XMLConstantes.LATI,caratula.getLatitud());
                escribirCampoXml(serializer, XMLConstantes.LONG,caratula.getLongitud());
                escribirCampoXml(serializer, XMLConstantes.ALTI,caratula.getAltitud());
                escribirCampoXml(serializer, XMLConstantes.TIPVIA ,caratula.getTipvia());
                escribirCampoXml(serializer, XMLConstantes.TIPVIA_O ,caratula.getTipvia_o());
                escribirCampoXml(serializer, XMLConstantes.NOMVIA ,caratula.getNomvia());
                escribirCampoXml(serializer, XMLConstantes.PTANUM ,caratula.getNropta());
                escribirCampoXml(serializer, XMLConstantes.BLOCK ,caratula.getBlock());
                escribirCampoXml(serializer, XMLConstantes.INTNUM ,caratula.getInterior());
                escribirCampoXml(serializer, XMLConstantes.PISO ,caratula.getPiso());
                escribirCampoXml(serializer, XMLConstantes.MANZ,caratula.getMza());
                escribirCampoXml(serializer, XMLConstantes.LOTE ,caratula.getLote());
                escribirCampoXml(serializer, XMLConstantes.KM ,caratula.getKm());
                escribirCampoXml(serializer, XMLConstantes.TELEFONO ,caratula.getTelefono());
                escribirCampoXml(serializer, XMLConstantes.THOGAR ,caratula.getT_hogar());
                escribirCampoXml(serializer, XMLConstantes.USUARIO_ID ,caratula.getUsuario());
                escribirCampoXml(serializer, XMLConstantes.VRESFIN,caratula.getResultado_final());
                escribirCampoXml(serializer, XMLConstantes.VRESFIN_O,"");
                escribirCampoXml(serializer, XMLConstantes.P21 ,caratula.getP21());
                escribirCampoXml(serializer, XMLConstantes.P21_O ,caratula.getP21_o());
                escribirCampoXml(serializer, XMLConstantes.OBS ,caratula.getObservaciones());
                escribirCampoXml(serializer, XMLConstantes.COBERTURA,caratula.getCobertura());
                escribirCampoXml(serializer, XMLConstantes.NRO_SEGMENTO,caratula.getNroSegmento());
                escribirCampoXml(serializer, XMLConstantes.NRO_PUERTA2,caratula.getNroPuerta2());
                serializer.endTag("", "VIVIENDA");
            }

            if(hogares.size()>0) {
                serializer.startTag("", "HOGAR");
                for (Hogar hogar : hogares) {
                    serializer.startTag("", "ITEM_HOGAR");
                    escribirCampoXml(serializer, XMLConstantes.ID_HOGAR, hogar.get_id());
                    escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA,hogar.getId_vivienda());
                    escribirCampoXml(serializer, XMLConstantes.INF_200,hogar.getId_informante());
                    escribirCampoXml(serializer, XMLConstantes.NHOGAR,hogar.getNumero());
                    escribirCampoXml(serializer, XMLConstantes.P11_NOM,hogar.getNom_ape());
                    escribirCampoXml(serializer, XMLConstantes.P11_APAT,hogar.getApe_paterno());
                    escribirCampoXml(serializer, XMLConstantes.P11_AMAT,hogar.getApe_materno());
                    escribirCampoXml(serializer, XMLConstantes.P15,hogar.getP15());
                    escribirCampoXml(serializer, XMLConstantes.P15_N,hogar.getP15_o());
                    escribirCampoXml(serializer, XMLConstantes.P16,hogar.getP16());
                    escribirCampoXml(serializer, XMLConstantes.P17,hogar.getP17());
                    escribirCampoXml(serializer, XMLConstantes.P18,hogar.getP18());
                    escribirCampoXml(serializer, XMLConstantes.P19,hogar.getP19());
                    escribirCampoXml(serializer, XMLConstantes.P20,hogar.getP20());
                    serializer.endTag("", "ITEM_HOGAR");
                }
                serializer.endTag("", "HOGAR");
            }

            if(visitaEncuestadors.size()>0) {
                serializer.startTag("", "VISITA_ENCUESTADOR_HOGAR");
                for (VisitaEncuestador visita : visitaEncuestadors) {
                    serializer.startTag("", "ITEM_VISITA_ENCUESTADOR");
                    escribirCampoXml(serializer, XMLConstantes.ID, visita.get_id());
                    escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA, visita.getId_vivienda());
                    escribirCampoXml(serializer, XMLConstantes.ID_HOGAR, visita.getId_hogar());
                    escribirCampoXml(serializer, XMLConstantes.VISITA_ID, visita.getNumero());
                    escribirCampoXml(serializer, XMLConstantes.FEC_ENC_DIA, visita.getVis_fecha_dd());
                    escribirCampoXml(serializer, XMLConstantes.FEC_ENC_MES, visita.getVis_fecha_mm());
                    escribirCampoXml(serializer, XMLConstantes.FEC_ENC_ANIO, visita.getVis_fecha_aa());
                    escribirCampoXml(serializer, XMLConstantes.FEC_ENC_HIN, visita.getVis_hor_ini());
                    escribirCampoXml(serializer, XMLConstantes.FEC_ENC_MIN, visita.getVis_min_ini());
                    escribirCampoXml(serializer, XMLConstantes.LATI_2,visita.getLatitud());
                    escribirCampoXml(serializer, XMLConstantes.LONG_2,visita.getLongitud());
                    escribirCampoXml(serializer, XMLConstantes.ALTI_2,visita.getAltura());
                    escribirCampoXml(serializer, XMLConstantes.FEC_ENC_HFI, visita.getVis_hor_fin());
                    escribirCampoXml(serializer, XMLConstantes.FEC_ENC_MFI, visita.getVis_min_fin());
                    escribirCampoXml(serializer, XMLConstantes.PROX_ENC_DIA, visita.getProx_vis_fecha_dd());
                    escribirCampoXml(serializer, XMLConstantes.PROX_ENC_MES, visita.getProx_vis_fecha_mm());
                    escribirCampoXml(serializer, XMLConstantes.PROX_ENC_ANIO, visita.getProx_vis_fecha_aa());
                    escribirCampoXml(serializer, XMLConstantes.PROX_ENC_HOR, visita.getProx_vis_hor());
                    escribirCampoXml(serializer, XMLConstantes.PROX_ENC_MIN, visita.getProx_vis_min());
                    escribirCampoXml(serializer, XMLConstantes.RESVIS, visita.getVis_resu());
                    escribirCampoXml(serializer, XMLConstantes.RESVIS_O, visita.getVis_resu_esp());
                    escribirCampoXml(serializer, XMLConstantes.TIPOENT, visita.getTipo_ent());
                    escribirCampoXml(serializer, XMLConstantes.TIPOENT_O, visita.getTipo_ent_o());
                    serializer.endTag("", "ITEM_VISITA_ENCUESTADOR");
                }
                serializer.endTag("", "VISITA_ENCUESTADOR_HOGAR");
            }

            if(resVisitaEncuestadors.size()>0) {
                serializer.startTag("", "RESULTADO_VISITA_ENCUESTADOR_HOGAR");
                for (ResVisitaEncuestador resVisitaEncuestador : resVisitaEncuestadors) {
                    serializer.startTag("", "ITEM_RESULTADO_VISITA_ENCUESTADOR");
                    escribirCampoXml(serializer, XMLConstantes.ID_HOGAR, resVisitaEncuestador.get_id());
                    escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA, resVisitaEncuestador.getId_vivienda());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_DIA, resVisitaEncuestador.getVis_fecha_final_dd());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_MES, resVisitaEncuestador.getVis_fecha_final_mm());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_ANIO, resVisitaEncuestador.getVis_fecha_final_aa());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN, resVisitaEncuestador.getVis_resultado_final());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_O, resVisitaEncuestador.getVis_resultado_final_o());
                    serializer.endTag("", "ITEM_RESULTADO_VISITA_ENCUESTADOR");
                }
                serializer.endTag("", "RESULTADO_VISITA_ENCUESTADOR_HOGAR");
            }

            if(visitaSupervisors.size()>0) {
                serializer.startTag("", "VISITA_SUPERVISOR_HOGAR");
                for (VisitaSupervisor visitaSupervisor : visitaSupervisors) {
                    serializer.startTag("", "ITEM_VISITA_SUPERVISOR");
                    escribirCampoXml(serializer, XMLConstantes.ID, visitaSupervisor.get_id());
                    escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA, visitaSupervisor.getId_vivienda());
                    escribirCampoXml(serializer, XMLConstantes.ID_HOGAR, visitaSupervisor.getId_hogar());
                    escribirCampoXml(serializer, XMLConstantes.VISITA_ID, visitaSupervisor.getNumero());
                    escribirCampoXml(serializer, XMLConstantes.FEC_SUP_DIA , visitaSupervisor.getVis_fecha_dd());
                    escribirCampoXml(serializer, XMLConstantes.FEC_SUP_MES , visitaSupervisor.getVis_fecha_mm());
                    escribirCampoXml(serializer, XMLConstantes.FEC_SUP_ANIO , visitaSupervisor.getVis_fecha_aa());
                    escribirCampoXml(serializer, XMLConstantes.FEC_SUP_HIN , visitaSupervisor.getVis_hor_ini());
                    escribirCampoXml(serializer, XMLConstantes.FEC_SUP_MIN , visitaSupervisor.getVis_min_ini());
                    escribirCampoXml(serializer, XMLConstantes.FEC_SUP_HFI , visitaSupervisor.getVis_hor_fin());
                    escribirCampoXml(serializer, XMLConstantes.FEC_SUP_MFI , visitaSupervisor.getVis_min_fin());
                    escribirCampoXml(serializer, XMLConstantes.RESVIS_SUP , visitaSupervisor.getVis_resu());
                    serializer.endTag("", "ITEM_VISITA_SUPERVISOR");
                }
                serializer.endTag("", "VISITA_SUPERVISOR_HOGAR");
            }

            if(resVisitaSupervisors.size()>0) {
                serializer.startTag("", "RESULTADO_VISITA_SUPERVISOR_HOGAR");
                for (ResVisitaSupervisor resVisitaSupervisor : resVisitaSupervisors) {
                    serializer.startTag("", "ITEM_RESULTADO_VISITA_SUPERVISOR");
                    escribirCampoXml(serializer, XMLConstantes.ID_HOGAR, resVisitaSupervisor.get_id());
                    escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA, resVisitaSupervisor.getId_vivienda());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_SUP_DIA, resVisitaSupervisor.getVis_fecha_final_dd());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_SUP_MES, resVisitaSupervisor.getVis_fecha_final_mm());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_SUP_ANIO, resVisitaSupervisor.getVis_fecha_final_aa());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_SUP, resVisitaSupervisor.getVis_resultado_final());
                    serializer.endTag("", "ITEM_RESULTADO_VISITA_SUPERVISOR");
                }
                serializer.endTag("", "RESULTADOS_VISITA_SUPERVISOR_HOGAR");
            }

            if(resultadoResidentes.size()>0) {
                serializer.startTag("", "RESULTADO_ENTREVISTA_RESIDENTE");
                for (ResultadoResidente resultadoResidente : resultadoResidentes) {
                    serializer.startTag("", "ITEM_RESULTADO_RESIDENTE");
                    escribirCampoXml(serializer, XMLConstantes.ID,resultadoResidente.get_id());
                    escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA,resultadoResidente.getId_vivienda());
                    escribirCampoXml(serializer, XMLConstantes.ID_HOGAR,resultadoResidente.getId_hogar());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_PER_DIA,resultadoResidente.getFecha_dd());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_PER_MES,resultadoResidente.getFecha_mm());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_PER_ANIO,resultadoResidente.getFecha_aa());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_PER_HIN,resultadoResidente.getHor_ini());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_PER_MIN,resultadoResidente.getMin_ini());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_PER_HFI,resultadoResidente.getHor_fin());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_PER_MFI,resultadoResidente.getMin_fin());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_PER_PRESFIN,resultadoResidente.getResultado_entrevista());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_PER_PRESFIN_O,resultadoResidente.getResultado_entrevista_o());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_PER_PTIPOENT,resultadoResidente.getTipo_entrevista());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_PER_PTIPOENT_O,resultadoResidente.getTipo_entrevista_o());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_PER_PTIPOENT_1,resultadoResidente.getDonde_entrevista());
                    escribirCampoXml(serializer, XMLConstantes.RESFIN_PER_PTIPOENT_1_O,resultadoResidente.getDonde_entrevista_o());
                    serializer.endTag("", "ITEM_RESULTADO_RESIDENTE");
                }
                serializer.endTag("", "RESULTADO_ENTREVISTA_RESIDENTE");
            }

            if(funcionario != null) {
                serializer.startTag("", "FUNCIONARIO");
                escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA, funcionario.get_id());
                escribirCampoXml(serializer, XMLConstantes.ENC_DNI, funcionario.getDni_encu());
                escribirCampoXml(serializer, XMLConstantes.SUP_DNI, funcionario.getDni_sup());
                escribirCampoXml(serializer, XMLConstantes.SUPN_DNI, funcionario.getDni_supn());
                escribirCampoXml(serializer, XMLConstantes.COOR_DNI, funcionario.getDni_coor());
                escribirCampoXml(serializer, XMLConstantes.ENC_NOM, funcionario.getNombre_encu());
                escribirCampoXml(serializer, XMLConstantes.SUP_NOM, funcionario.getNombre_sup());
                escribirCampoXml(serializer, XMLConstantes.SUPN_NOM, funcionario.getNombre_supn());
                escribirCampoXml(serializer, XMLConstantes.COOR_NOM, funcionario.getNombre_coord());
                serializer.endTag("", "FUNCIONARIO");
            }

            if(modulo1V != null) {
                serializer.startTag("", "MODULO1_VIVIENDA");
                escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA, modulo1V.get_id());
                escribirCampoXml(serializer, XMLConstantes.P101, modulo1V.getC1_p101());
                escribirCampoXml(serializer, XMLConstantes.P101_O, modulo1V.getC1_p101_o());
                escribirCampoXml(serializer, XMLConstantes.P102, modulo1V.getC1_p102());
                escribirCampoXml(serializer, XMLConstantes.P102_O, modulo1V.getC1_p102_o());
                escribirCampoXml(serializer, XMLConstantes.P103, modulo1V.getC1_p103());
                escribirCampoXml(serializer, XMLConstantes.P103_O, modulo1V.getC1_p103_o());
                escribirCampoXml(serializer, XMLConstantes.P104, modulo1V.getC1_p104());
                escribirCampoXml(serializer, XMLConstantes.P104_O, modulo1V.getC1_p104_o());
                escribirCampoXml(serializer, XMLConstantes.P105, modulo1V.getC1_p105());
                escribirCampoXml(serializer, XMLConstantes.P106, modulo1V.getC1_p106());
                serializer.endTag("", "MODULO1_VIVIENDA");
            }

            if(modulo1HS.size()>0) {
                serializer.startTag("", "MODULO1_HOGAR");
                for (Modulo1H modulo1H : modulo1HS) {
                    serializer.startTag("", "ITEM_MODULO1_HOGAR");
                    escribirCampoXml(serializer, XMLConstantes.ID_HOGAR, modulo1H.get_id());
                    escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA, modulo1H.getIdVivienda());
                    escribirCampoXml(serializer, XMLConstantes.P107, modulo1H.getC1_p107());
                    escribirCampoXml(serializer, XMLConstantes.P107_O, modulo1H.getC1_p107_o());
                    escribirCampoXml(serializer, XMLConstantes.P108_1, modulo1H.getC1_p108_1());
                    escribirCampoXml(serializer, XMLConstantes.P108_2, modulo1H.getC1_p108_2());
                    escribirCampoXml(serializer, XMLConstantes.P108_3, modulo1H.getC1_p108_3());
                    escribirCampoXml(serializer, XMLConstantes.P108_1_O, modulo1H.getC1_p108_1_o());
                    escribirCampoXml(serializer, XMLConstantes.P108_2_O, modulo1H.getC1_p108_2_o());
                    escribirCampoXml(serializer, XMLConstantes.P108_3_O, modulo1H.getC1_p108_3_o());
                    escribirCampoXml(serializer, XMLConstantes.P108_4, modulo1H.getC1_p108_4());
                    escribirCampoXml(serializer, XMLConstantes.P109, modulo1H.getC1_p109());
                    escribirCampoXml(serializer, XMLConstantes.P109_O, modulo1H.getC1_p109_o());
                    escribirCampoXml(serializer, XMLConstantes.P110_1, modulo1H.getC1_p110_1());
                    escribirCampoXml(serializer, XMLConstantes.P110_2, modulo1H.getC1_p110_2());
                    escribirCampoXml(serializer, XMLConstantes.P110_3, modulo1H.getC1_p110_3());
                    escribirCampoXml(serializer, XMLConstantes.P110_4, modulo1H.getC1_p110_4());
                    escribirCampoXml(serializer, XMLConstantes.P110_5, modulo1H.getC1_p110_5());
                    escribirCampoXml(serializer, XMLConstantes.P110_6, modulo1H.getC1_p110_6());
                    escribirCampoXml(serializer, XMLConstantes.P110_7, modulo1H.getC1_p110_7());
                    escribirCampoXml(serializer, XMLConstantes.P110_8, modulo1H.getC1_p110_8());
                    escribirCampoXml(serializer, XMLConstantes.P110_9, modulo1H.getC1_p110_9());
                    escribirCampoXml(serializer, XMLConstantes.P110_10, modulo1H.getC1_p110_10());
                    escribirCampoXml(serializer, XMLConstantes.P110_11, modulo1H.getC1_p110_11());
                    escribirCampoXml(serializer, XMLConstantes.P110_11_O, modulo1H.getC1_p110_11_o());
                    escribirCampoXml(serializer, XMLConstantes.P110_12, modulo1H.getC1_p110_12());
                    escribirCampoXml(serializer, XMLConstantes.P110_12_O, modulo1H.getC1_p110_12_o());
                    escribirCampoXml(serializer, XMLConstantes.P110_13, modulo1H.getC1_p110_13());
                    escribirCampoXml(serializer, XMLConstantes.P110_13_O, modulo1H.getC1_p110_13_o());
                    escribirCampoXml(serializer, XMLConstantes.P111, modulo1H.getC1_p111());
                    escribirCampoXml(serializer, XMLConstantes.P111A, modulo1H.getC1_p111a());
                    escribirCampoXml(serializer, XMLConstantes.P112_1, modulo1H.getC1_p112_1());
                    escribirCampoXml(serializer, XMLConstantes.P112_2, modulo1H.getC1_p112_2());
                    escribirCampoXml(serializer, XMLConstantes.P112_3, modulo1H.getC1_p112_3());
                    escribirCampoXml(serializer, XMLConstantes.P112_4, modulo1H.getC1_p112_4());
                    escribirCampoXml(serializer, XMLConstantes.P112_5, modulo1H.getC1_p112_5());
                    escribirCampoXml(serializer, XMLConstantes.P112_6, modulo1H.getC1_p112_6());
                    escribirCampoXml(serializer, XMLConstantes.P112_7, modulo1H.getC1_p112_7());
                    escribirCampoXml(serializer, XMLConstantes.P112_8, modulo1H.getC1_p112_8());
                    escribirCampoXml(serializer, XMLConstantes.OBS_100, modulo1H.getCOB100B());
                    serializer.endTag("", "ITEM_MODULO1_HOGAR");
                }
                serializer.endTag("", "MODULO1_HOGAR");
            }

            if(residentes.size()>0) {
                serializer.startTag("", "MODULO2_RESIDENTE");
                for (Residente residente : residentes) {
                    serializer.startTag("", "ITEM_MODULO2_RESIDENTE");
                    escribirCampoXml(serializer, XMLConstantes.ID_RESIDENTE, residente.get_id());
                    escribirCampoXml(serializer, XMLConstantes.ID_HOGAR, residente.getId_hogar());
                    escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA, residente.getId_vivienda());
                    escribirCampoXml(serializer, XMLConstantes.P200_N, residente.getNumero());
                    escribirCampoXml(serializer, XMLConstantes.P202A, residente.getC2_p202());
                    escribirCampoXml(serializer, XMLConstantes.P202B, residente.getC2_p202_pat());
                    escribirCampoXml(serializer, XMLConstantes.P203, residente.getC2_p203());
                    escribirCampoXml(serializer, XMLConstantes.P204, residente.getC2_p204());
                    escribirCampoXml(serializer, XMLConstantes.P205_A, residente.getC2_p205_a());
                    escribirCampoXml(serializer, XMLConstantes.P205_B, residente.getC2_p205_m());
                    escribirCampoXml(serializer, XMLConstantes.P206, residente.getC2_p206());
                    escribirCampoXml(serializer, XMLConstantes.P207_1, residente.getC2_p207());
                    escribirCampoXml(serializer, XMLConstantes.P207_2, residente.getC2_p207_o());
                    escribirCampoXml(serializer, XMLConstantes.P208, residente.getC2_p208());
                    escribirCampoXml(serializer, XMLConstantes.P209, residente.getC2_p209());
                    escribirCampoXml(serializer, XMLConstantes.P209_N, residente.getC2_p209_n());
                    escribirCampoXml(serializer, XMLConstantes.P209_1, residente.getC2_p209_p());
                    escribirCampoXml(serializer, XMLConstantes.P210, residente.getC2_p210());
                    escribirCampoXml(serializer, XMLConstantes.P210_N, residente.getC2_p210_n());
                    escribirCampoXml(serializer, XMLConstantes.P210_1, residente.getC2_p210_p());
                    escribirCampoXml(serializer, XMLConstantes.P211_N, residente.getC2_p211());
                    escribirCampoXml(serializer, XMLConstantes.P211, residente.getC2_p211_nom());
                    escribirCampoXml(serializer, XMLConstantes.P211_1, residente.getC2_p211_1());
                    escribirCampoXml(serializer, XMLConstantes.P211_1_O, residente.getC2_p211_1_o());
                    escribirCampoXml(serializer, XMLConstantes.P200_APORTANTE, residente.getP200_aportante());
                    escribirCampoXml(serializer, XMLConstantes.OBS_200, residente.getOBS200());
//                    escribirCampoXml(serializer, XMLConstantes.P212, residente.getC2_p212());
                    escribirCampoXml(serializer, XMLConstantes.EMAIL, "");
                    serializer.endTag("", "ITEM_MODULO2_RESIDENTE");
                }
                serializer.endTag("", "MODULO2_RESIDENTE");
            }

            if(modulo3s.size()>0) {
                serializer.startTag("", "MODULO3");
                for (Modulo3 modulo3 : modulo3s) {
                    serializer.startTag("", "ITEM_MODULO3");
                    escribirCampoXml(serializer, XMLConstantes.ID_RESIDENTE, modulo3.get_id());
                    escribirCampoXml(serializer, XMLConstantes.ID_HOGAR, modulo3.getIdHogar());
                    escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA, modulo3.getIdVivienda());
                    escribirCampoXml(serializer, XMLConstantes.INF_300, modulo3.getIdInformante());//esta que toma idformante y deberia ser id_informante; debere cambiar la bd principal x idinformante
                    escribirCampoXml(serializer, XMLConstantes.P301_D, modulo3.getC3_p301_d());
                    escribirCampoXml(serializer, XMLConstantes.P301_M, modulo3.getC3_p301_m());
                    escribirCampoXml(serializer, XMLConstantes.P301_A, modulo3.getC3_p301_a());
                    escribirCampoXml(serializer, XMLConstantes.P302, modulo3.getC3_p302());
                    escribirCampoXml(serializer, XMLConstantes.P302_O, modulo3.getC3_p302_o());
                    escribirCampoXml(serializer, XMLConstantes.P303_MES, modulo3.getC3_p303_m());
                    escribirCampoXml(serializer, XMLConstantes.P303_ANIO, modulo3.getC3_p303_a());
                    escribirCampoXml(serializer, XMLConstantes.P304, modulo3.getC3_p304());
                    escribirCampoXml(serializer, XMLConstantes.P304_O, modulo3.getC3_p304_o());
                    escribirCampoXml(serializer, XMLConstantes.P305, modulo3.getC3_p305());
                    escribirCampoXml(serializer, XMLConstantes.P306_1, modulo3.getC3_p306_1());
                    escribirCampoXml(serializer, XMLConstantes.P306_2, modulo3.getC3_p306_2());
                    escribirCampoXml(serializer, XMLConstantes.P306_3, modulo3.getC3_p306_3());
                    escribirCampoXml(serializer, XMLConstantes.P306_4, modulo3.getC3_p306_4());
                    escribirCampoXml(serializer, XMLConstantes.P306_5, modulo3.getC3_p306_5());
                    escribirCampoXml(serializer, XMLConstantes.P306_6, modulo3.getC3_p306_6());
                    escribirCampoXml(serializer, XMLConstantes.P306_6_O, modulo3.getC3_p306_o());
                    escribirCampoXml(serializer, XMLConstantes.P306_7, modulo3.getC3_p306_7());
                    escribirCampoXml(serializer, XMLConstantes.P307_1, modulo3.getC3_p307_1());
                  /*  escribirCampoXml(serializer, XMLConstantes.P307_2, modulo3.getC3_p307_2());
                    escribirCampoXml(serializer, XMLConstantes.P307_3, modulo3.getC3_p307_3());
                    escribirCampoXml(serializer, XMLConstantes.P307_4, modulo3.getC3_p307_4());
                    escribirCampoXml(serializer, XMLConstantes.P307_5, modulo3.getC3_p307_5());
                    escribirCampoXml(serializer, XMLConstantes.P307_6, modulo3.getC3_p307_6());*/
                    escribirCampoXml(serializer, XMLConstantes.P307_6_O, modulo3.getC3_p307_o6());
                    escribirCampoXml(serializer, XMLConstantes.P307A, modulo3.getC3_p307_a());
                    escribirCampoXml(serializer, XMLConstantes.P307A_O, modulo3.getC3_p307a_o());
                 /*   escribirCampoXml(serializer, XMLConstantes.P307_7, modulo3.getC3_p307_7());
                    escribirCampoXml(serializer, XMLConstantes.P307_8, modulo3.getC3_p307_8());
                    escribirCampoXml(serializer, XMLConstantes.P307_9, modulo3.getC3_p307_9());
                    escribirCampoXml(serializer, XMLConstantes.P307_10, modulo3.getC3_p307_10());
                    escribirCampoXml(serializer, XMLConstantes.P307_11, modulo3.getC3_p307_11());
                    escribirCampoXml(serializer, XMLConstantes.P307_12, modulo3.getC3_p307_12());
                    escribirCampoXml(serializer, XMLConstantes.P307_12_O, modulo3.getC3_p307_o12());
                    escribirCampoXml(serializer, XMLConstantes.P307_13, modulo3.getC3_p307_13());*/
                    escribirCampoXml(serializer, XMLConstantes.P308, modulo3.getC3_p308());
                    escribirCampoXml(serializer, XMLConstantes.P309, modulo3.getC3_p309());
                    escribirCampoXml(serializer, XMLConstantes.P309_O, modulo3.getC3_p309_o());
                    escribirCampoXml(serializer, XMLConstantes.P309_1, modulo3.getC3_p309_1());
                    escribirCampoXml(serializer, XMLConstantes.P309_1_O, modulo3.getC3_p309_1_o());
                    escribirCampoXml(serializer, XMLConstantes.P310_E, modulo3.getC3_p310_e());
                    escribirCampoXml(serializer, XMLConstantes.P310_E_O, modulo3.getC3_p310_e_o());
                    escribirCampoXml(serializer, XMLConstantes.P310_M, modulo3.getC3_p310_m());
                    escribirCampoXml(serializer, XMLConstantes.P310_M_O, modulo3.getC3_p310_m_o());
                    escribirCampoXml(serializer, XMLConstantes.P311, modulo3.getC3_p311());
                    escribirCampoXml(serializer, XMLConstantes.P312, modulo3.getC3_p312());
                    escribirCampoXml(serializer, XMLConstantes.P312_O, modulo3.getC3_p312_o());
                    escribirCampoXml(serializer, XMLConstantes.P313, modulo3.getC3_p313());
                    escribirCampoXml(serializer,XMLConstantes.OBS_300,modulo3.getObs_cap3());
                    serializer.endTag("", "ITEM_MODULO3");
                }
                serializer.endTag("", "MODULO3");
            }

            if(m3Pregunta318s.size()>0) {
                serializer.startTag("", "MODULO3_P313");
                for (M3Pregunta318 m3Pregunta318 : m3Pregunta318s) {
                    serializer.startTag("", "ITEM_MODULO3_P313");
                    escribirCampoXml(serializer, XMLConstantes.ID, m3Pregunta318.get_id());
                    escribirCampoXml(serializer, XMLConstantes.ID_RESIDENTE, m3Pregunta318.getIdEncuestado());
                    escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA, m3Pregunta318.getId_vivienda());
                    escribirCampoXml(serializer, XMLConstantes.P313_N, m3Pregunta318.getNumero());
                    escribirCampoXml(serializer, XMLConstantes.P313_1, m3Pregunta318.getC3_p318_f());
                    escribirCampoXml(serializer, XMLConstantes.P313_2, m3Pregunta318.getC3_p318_s());
                    escribirCampoXml(serializer, XMLConstantes.P313_3, m3Pregunta318.getC3_p318_e());
                    escribirCampoXml(serializer, XMLConstantes.P313_4, m3Pregunta318.getC3_p318_p());
                    serializer.endTag("", "ITEM_MODULO3_P313");
                }
                serializer.endTag("", "MODULO3_P313");
            }

            if(modulo4s.size()>0) {
                serializer.startTag("", "MODULO4");
                for (Modulo4 modulo4 : modulo4s) {
                    serializer.startTag("", "ITEM_MODULO4");
                    escribirCampoXml(serializer, XMLConstantes.ID_RESIDENTE, modulo4.get_id());
                    escribirCampoXml(serializer, XMLConstantes.ID_HOGAR, modulo4.getIdHogar());
                    escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA, modulo4.getIdVivienda());
                    escribirCampoXml(serializer, XMLConstantes.INF_400, modulo4.getIdInformante());
                    escribirCampoXml(serializer, XMLConstantes.P401_1, modulo4.getC4_p401_1());
                    escribirCampoXml(serializer, XMLConstantes.P401_2, modulo4.getC4_p401_2());
                    escribirCampoXml(serializer, XMLConstantes.P401_3, modulo4.getC4_p401_3());
                    escribirCampoXml(serializer, XMLConstantes.P401_4, modulo4.getC4_p401_4());
                    escribirCampoXml(serializer, XMLConstantes.P401_4_O, modulo4.getC4_p401_4_o());
                    escribirCampoXml(serializer, XMLConstantes.P401_5, modulo4.getC4_p401_5());
                    escribirCampoXml(serializer, XMLConstantes.P402, modulo4.getC4_p402());
                    escribirCampoXml(serializer, XMLConstantes.P403_1, modulo4.getC4_p403_1());
                    escribirCampoXml(serializer, XMLConstantes.P403_2, modulo4.getC4_p403_2());
                    escribirCampoXml(serializer, XMLConstantes.P403_3, modulo4.getC4_p403_3());
                    escribirCampoXml(serializer, XMLConstantes.P403_4, modulo4.getC4_p403_4());
                    escribirCampoXml(serializer, XMLConstantes.P403_5, modulo4.getC4_p403_5());
                    escribirCampoXml(serializer, XMLConstantes.P403_6, modulo4.getC4_p403_6());
                    escribirCampoXml(serializer, XMLConstantes.P403_7, modulo4.getC4_p403_7());
                    escribirCampoXml(serializer, XMLConstantes.P403_8, modulo4.getC4_p403_8());
                    escribirCampoXml(serializer, XMLConstantes.P403_9, modulo4.getC4_p403_9());
                    escribirCampoXml(serializer, XMLConstantes.P403_10, modulo4.getC4_p403_10());
                    escribirCampoXml(serializer, XMLConstantes.P403_11, modulo4.getC4_p403_11());
                    escribirCampoXml(serializer, XMLConstantes.P403_12, modulo4.getC4_p403_12());
                    escribirCampoXml(serializer, XMLConstantes.P403_13, modulo4.getC4_p403_13());
                    escribirCampoXml(serializer, XMLConstantes.P403_14, modulo4.getC4_p403_14());
                    escribirCampoXml(serializer, XMLConstantes.P403_14_O, modulo4.getC4_p403_14_o());
                    escribirCampoXml(serializer, XMLConstantes.P404, modulo4.getC4_p404());
                    escribirCampoXml(serializer, XMLConstantes.P405_1, modulo4.getC4_p405_1());
                    escribirCampoXml(serializer, XMLConstantes.P405_2, modulo4.getC4_p405_2());
                    escribirCampoXml(serializer, XMLConstantes.P405_3, modulo4.getC4_p405_3());
                    escribirCampoXml(serializer, XMLConstantes.P405_4, modulo4.getC4_p405_4());
                    escribirCampoXml(serializer, XMLConstantes.P405_5, modulo4.getC4_p405_5());
                    escribirCampoXml(serializer, XMLConstantes.P405_6, modulo4.getC4_p405_6());
                    escribirCampoXml(serializer, XMLConstantes.P405_7, modulo4.getC4_p405_7());
                    escribirCampoXml(serializer, XMLConstantes.P406_1, modulo4.getC4_p406_1());
                    escribirCampoXml(serializer, XMLConstantes.P406_2, modulo4.getC4_p406_2());
                    escribirCampoXml(serializer, XMLConstantes.P406_3, modulo4.getC4_p406_3());
                    escribirCampoXml(serializer, XMLConstantes.P406_4, modulo4.getC4_p406_4());
                    escribirCampoXml(serializer, XMLConstantes.P406_5, modulo4.getC4_p406_5());
                    escribirCampoXml(serializer, XMLConstantes.P406_6, modulo4.getC4_p406_6());
                    escribirCampoXml(serializer, XMLConstantes.P406_7, modulo4.getC4_p406_7());
                    escribirCampoXml(serializer, XMLConstantes.P406_7_O, modulo4.getC4_p406_7_o());
                    escribirCampoXml(serializer, XMLConstantes.P406_8, modulo4.getC4_p406_8());
                    escribirCampoXml(serializer, XMLConstantes.P407_1, modulo4.getC4_p407_1());
                    escribirCampoXml(serializer, XMLConstantes.P407_2, modulo4.getC4_p407_2());
                    escribirCampoXml(serializer, XMLConstantes.P407_3, modulo4.getC4_p407_3());
                    escribirCampoXml(serializer, XMLConstantes.P407_4, modulo4.getC4_p407_4());
                    escribirCampoXml(serializer, XMLConstantes.P407_5, modulo4.getC4_p407_5());
                    escribirCampoXml(serializer, XMLConstantes.P407_6, modulo4.getC4_p407_6());
                    escribirCampoXml(serializer, XMLConstantes.P407_7, modulo4.getC4_p407_7());
                    escribirCampoXml(serializer, XMLConstantes.P407_8, modulo4.getC4_p407_8());
                    escribirCampoXml(serializer, XMLConstantes.P407_9, modulo4.getC4_p407_9());
                    escribirCampoXml(serializer, XMLConstantes.P407_10, modulo4.getC4_p407_10());
                    escribirCampoXml(serializer, XMLConstantes.P407_11, modulo4.getC4_p407_11());
                    escribirCampoXml(serializer, XMLConstantes.P407_12, modulo4.getC4_p407_12());
                    escribirCampoXml(serializer, XMLConstantes.P407_13, modulo4.getC4_p407_13());
                    escribirCampoXml(serializer, XMLConstantes.P407_13_O, modulo4.getC4_p407_13_o());
                    escribirCampoXml(serializer, XMLConstantes.P408_1, modulo4.getC4_p408_1());
                    escribirCampoXml(serializer, XMLConstantes.P408_2, modulo4.getC4_p408_2());
                    escribirCampoXml(serializer, XMLConstantes.P408_3, modulo4.getC4_p408_3());
                    escribirCampoXml(serializer, XMLConstantes.P408_4, modulo4.getC4_p408_4());
                    escribirCampoXml(serializer, XMLConstantes.P408_5, modulo4.getC4_p408_5());
                    escribirCampoXml(serializer, XMLConstantes.P408_6, modulo4.getC4_p408_6());
                    escribirCampoXml(serializer, XMLConstantes.P409_N, modulo4.getC4_p409());
                    escribirCampoXml(serializer, XMLConstantes.P409_1, modulo4.getC4_p409_1());
                    escribirCampoXml(serializer, XMLConstantes.P409_O, modulo4.getC4_p409_o());
                    escribirCampoXml(serializer, XMLConstantes.P409_2, modulo4.getC4_p409_2());
                    escribirCampoXml(serializer, XMLConstantes.P409_NOM, modulo4.getC4_p409_nom());
                    escribirCampoXml(serializer, XMLConstantes.P410_A, modulo4.getC4_p410a());
                    escribirCampoXml(serializer, XMLConstantes.P410_B, modulo4.getC4_p410b());
                    escribirCampoXml(serializer, XMLConstantes.P410, modulo4.getC4_p410());
                    escribirCampoXml(serializer, XMLConstantes.P411, modulo4.getC4_p411());
                    escribirCampoXml(serializer, XMLConstantes.P412, modulo4.getC4_p412());
                    escribirCampoXml(serializer, XMLConstantes.P413_1, modulo4.getC4_p413_1());
                    escribirCampoXml(serializer, XMLConstantes.P413_2, modulo4.getC4_p413_2());
                    escribirCampoXml(serializer, XMLConstantes.P413_3, modulo4.getC4_p413_3());
                    escribirCampoXml(serializer, XMLConstantes.P413_4, modulo4.getC4_p413_4());
                    escribirCampoXml(serializer, XMLConstantes.P413_5, modulo4.getC4_p413_5());
                    escribirCampoXml(serializer, XMLConstantes.P414, modulo4.getC4_p414());
                    escribirCampoXml(serializer, XMLConstantes.P415, modulo4.getC4_p415());
                    escribirCampoXml(serializer, XMLConstantes.P416_1, modulo4.getC4_p416_1());
                    escribirCampoXml(serializer, XMLConstantes.P416_2, modulo4.getC4_p416_2());
                    escribirCampoXml(serializer, XMLConstantes.P416_3, modulo4.getC4_p416_3());
                    escribirCampoXml(serializer, XMLConstantes.P416_4, modulo4.getC4_p416_4());
                    escribirCampoXml(serializer, XMLConstantes.P416_5, modulo4.getC4_p416_5());
                    escribirCampoXml(serializer, XMLConstantes.P416_5_O, modulo4.getC4_p416_5_o());
                    escribirCampoXml(serializer, XMLConstantes.P416_6, modulo4.getC4_p416_6());
                    escribirCampoXml(serializer, XMLConstantes.P417_1, modulo4.getC4_p417_1());
                    escribirCampoXml(serializer, XMLConstantes.P417_1_1, modulo4.getC4_p417_1_1());
                    escribirCampoXml(serializer, XMLConstantes.P417_1_2, modulo4.getC4_p417_1_2());
                    escribirCampoXml(serializer, XMLConstantes.P417_1_3, modulo4.getC4_p417_1_3());
                    escribirCampoXml(serializer, XMLConstantes.P417_1_4, modulo4.getC4_p417_1_4());
                    escribirCampoXml(serializer, XMLConstantes.P417_1_5, modulo4.getC4_p417_1_5());
                    escribirCampoXml(serializer, XMLConstantes.P417_1_6, modulo4.getC4_p417_1_6());
                    escribirCampoXml(serializer, XMLConstantes.P417_1_7, modulo4.getC4_p417_1_7());
                    escribirCampoXml(serializer, XMLConstantes.P417_1_8, modulo4.getC4_p417_1_8());
                    escribirCampoXml(serializer, XMLConstantes.P417_1_9, modulo4.getC4_p417_1_9());
                    escribirCampoXml(serializer, XMLConstantes.P417_1_10, modulo4.getC4_p417_1_10());
                    escribirCampoXml(serializer, XMLConstantes.P417_1_11, modulo4.getC4_p417_1_11());
                    escribirCampoXml(serializer, XMLConstantes.P417_1_12, modulo4.getC4_p417_1_12());
                    escribirCampoXml(serializer, XMLConstantes.P417_1_13, modulo4.getC4_p417_1_13());
                    escribirCampoXml(serializer, XMLConstantes.P417_1_13_O, modulo4.getC4_p417_1_13_o());
                    //escribirCampoXml(serializer, XMLConstantes.P417_1A, modulo4.getC4_p417_1a());
                    //escribirCampoXml(serializer, XMLConstantes.P417_1A_O, modulo4.getC4_p417_1a_o());
                    escribirCampoXml(serializer, XMLConstantes.P417_2, modulo4.getC4_p417_2());
                    escribirCampoXml(serializer, XMLConstantes.P417_3, modulo4.getC4_p417_3());
                    escribirCampoXml(serializer, XMLConstantes.P417_4, modulo4.getC4_p417_4());
                    escribirCampoXml(serializer, XMLConstantes.P417_4_O, modulo4.getC4_p417_4_o());
//                    escribirCampoXml(serializer, XMLConstantes.P417_5, modulo4.getC4_p417_5());
//                    escribirCampoXml(serializer, XMLConstantes.P417_6, modulo4.getC4_p417_6());
//                    escribirCampoXml(serializer, XMLConstantes.P417_7, modulo4.getC4_p417_7());
//                    escribirCampoXml(serializer, XMLConstantes.P417_7_O, modulo4.getC4_p417_7_o());
//                    escribirCampoXml(serializer, XMLConstantes.P417_8, modulo4.getC4_p417_8());
//                    escribirCampoXml(serializer, XMLConstantes.P417_9, modulo4.getC4_p417_9());
                    escribirCampoXml(serializer, XMLConstantes.P418, modulo4.getC4_p418());
                    escribirCampoXml(serializer, XMLConstantes.P418A, modulo4.getC4_p418a());
                    escribirCampoXml(serializer, XMLConstantes.OBS_400, modulo4.getObs_cap4());
                    serializer.endTag("", "ITEM_MODULO4");
                }
                serializer.endTag("", "MODULO4");
            }

            if(modulo5s.size()>0) {
                serializer.startTag("", "MODULO5");
                for (Modulo5 modulo5 : modulo5s) {
                    serializer.startTag("", "ITEM_MODULO5");
                    escribirCampoXml(serializer, XMLConstantes.ID_RESIDENTE, modulo5.get_id());
                    escribirCampoXml(serializer, XMLConstantes.ID_HOGAR, modulo5.getIdHogar());
                    escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA, modulo5.getIdVivienda());
                    escribirCampoXml(serializer, XMLConstantes.INF_500, modulo5.getIdInformante());
                    escribirCampoXml(serializer, XMLConstantes.P501A, modulo5.getC5_p501a());
                    escribirCampoXml(serializer, XMLConstantes.P501, modulo5.getC5_p501());
                    escribirCampoXml(serializer, XMLConstantes.P501B, modulo5.getC5_p501b());
                    escribirCampoXml(serializer, XMLConstantes.P502, modulo5.getC5_p502());
                    escribirCampoXml(serializer, XMLConstantes.P502_O, modulo5.getC5_p502_o());
                    escribirCampoXml(serializer, XMLConstantes.P503, modulo5.getC5_p503());
                    escribirCampoXml(serializer, XMLConstantes.P504_N, modulo5.getC5_p504());
                    escribirCampoXml(serializer, XMLConstantes.P504_A, modulo5.getC5_p504_anio());
                    escribirCampoXml(serializer, XMLConstantes.P504_G, modulo5.getC5_p504_grado());
                    escribirCampoXml(serializer, XMLConstantes.P504_CE, modulo5.getC5_p504_ce());
                    escribirCampoXml(serializer, XMLConstantes.P505, modulo5.getC5_p505());
                    escribirCampoXml(serializer, XMLConstantes.P506, modulo5.getC5_p506());
                    escribirCampoXml(serializer, XMLConstantes.P506A, modulo5.getC5_p506a());
                    escribirCampoXml(serializer, XMLConstantes.P507_N, modulo5.getC5_p507());
                    escribirCampoXml(serializer, XMLConstantes.P507_A, modulo5.getC5_p507_anio());
                    escribirCampoXml(serializer, XMLConstantes.P507_G, modulo5.getC5_p507_grado());
                    escribirCampoXml(serializer, XMLConstantes.P507_CE, modulo5.getC5_p507_ce());
                    escribirCampoXml(serializer, XMLConstantes.P508_1, modulo5.getC5_p508_1());
                    escribirCampoXml(serializer, XMLConstantes.P508_2, modulo5.getC5_p508_2());
                    escribirCampoXml(serializer, XMLConstantes.P508_3, modulo5.getC5_p508_3());
                    escribirCampoXml(serializer, XMLConstantes.P508_4, modulo5.getC5_p508_4());
                    escribirCampoXml(serializer, XMLConstantes.P508_4_O, modulo5.getC5_p508_4_o());
                    //escribirCampoXml(serializer, XMLConstantes.P508_5, modulo5.getC5_p508_5());
                    escribirCampoXml(serializer, XMLConstantes.P509_1, modulo5.getC5_p509_1());
                    escribirCampoXml(serializer, XMLConstantes.P509_2, modulo5.getC5_p509_2());
                    escribirCampoXml(serializer, XMLConstantes.P509_3, modulo5.getC5_p509_3());
                    escribirCampoXml(serializer, XMLConstantes.P509_4, modulo5.getC5_p509_4());
                    escribirCampoXml(serializer, XMLConstantes.P509_5, modulo5.getC5_p509_5());
                    escribirCampoXml(serializer, XMLConstantes.P509_6, modulo5.getC5_p509_6());
                    escribirCampoXml(serializer, XMLConstantes.P509_7, modulo5.getC5_p509_7());
                    escribirCampoXml(serializer, XMLConstantes.P509_8, modulo5.getC5_p509_8());
                    escribirCampoXml(serializer, XMLConstantes.P509_7_O, modulo5.getC5_p509_7_o());
                    escribirCampoXml(serializer, XMLConstantes.P510, modulo5.getC5_p510());
                    escribirCampoXml(serializer, XMLConstantes.P510_O, modulo5.getC5_p510_o());
                    escribirCampoXml(serializer, XMLConstantes.P511, modulo5.getC5_p511());
                    escribirCampoXml(serializer, XMLConstantes.P511_O, modulo5.getC5_p511_o());
                    escribirCampoXml(serializer, XMLConstantes.P512, modulo5.getC5_p512());
                    escribirCampoXml(serializer, XMLConstantes.P513, modulo5.getC5_p513());
                    escribirCampoXml(serializer, XMLConstantes.P514, modulo5.getC5_p514());
                    escribirCampoXml(serializer, XMLConstantes.P514_O, modulo5.getC5_p514_o());
                    escribirCampoXml(serializer, XMLConstantes.P515, modulo5.getC5_p515());
                    escribirCampoXml(serializer, XMLConstantes.P515_O, modulo5.getC5_p515_o());
                    escribirCampoXml(serializer, XMLConstantes.P516, modulo5.getC5_p516());
                    escribirCampoXml(serializer, XMLConstantes.P516_O, modulo5.getC5_p516_o());
                    escribirCampoXml(serializer, XMLConstantes.OBS_500, modulo5.getObs_cap5());
                    serializer.endTag("", "ITEM_MODULO5");
                }
                serializer.endTag("", "MODULO5");
            }

            if(modulo6s.size()>0) {
                serializer.startTag("", "MODULO6");
                for (Modulo6 modulo6 : modulo6s) {
                    serializer.startTag("", "ITEM_MODULO6");
                    escribirCampoXml(serializer, XMLConstantes.ID_RESIDENTE, modulo6.get_id());
                    escribirCampoXml(serializer, XMLConstantes.ID_HOGAR, modulo6.getIdHogar());
                    escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA, modulo6.getIdVivienda());
                    escribirCampoXml(serializer, XMLConstantes.INF_600, modulo6.getIdInformante());
                    escribirCampoXml(serializer, XMLConstantes.P601, modulo6.getC6_p601());
                    escribirCampoXml(serializer, XMLConstantes.P602, modulo6.getC6_p602());
                    escribirCampoXml(serializer, XMLConstantes.P603, modulo6.getC6_p603());
                    escribirCampoXml(serializer, XMLConstantes.P604, modulo6.getC6_p604_1());
                    escribirCampoXml(serializer, XMLConstantes.P605_1, modulo6.getC6_p605_1());
                    escribirCampoXml(serializer, XMLConstantes.P605_2, modulo6.getC6_p605_2());
                    escribirCampoXml(serializer, XMLConstantes.P605_3, modulo6.getC6_p605_3());
                    escribirCampoXml(serializer, XMLConstantes.P605_4, modulo6.getC6_p605_4());
                    escribirCampoXml(serializer, XMLConstantes.P605_5, modulo6.getC6_p605_5());
                    escribirCampoXml(serializer, XMLConstantes.P605_6, modulo6.getC6_p605_6());
                    escribirCampoXml(serializer, XMLConstantes.P605_7, modulo6.getC6_p605_7());
                    escribirCampoXml(serializer, XMLConstantes.P605_8, modulo6.getC6_p605_8());
                    escribirCampoXml(serializer, XMLConstantes.P605_9, modulo6.getC6_p605_9());
                    escribirCampoXml(serializer, XMLConstantes.P605_10, modulo6.getC6_p605_10());
                    escribirCampoXml(serializer, XMLConstantes.P605_11, modulo6.getC6_p605_11());
                    escribirCampoXml(serializer, XMLConstantes.P605_12, modulo6.getC6_p605_12());
                    escribirCampoXml(serializer, XMLConstantes.P605_12_O, modulo6.getC6_p605_o());
                    escribirCampoXml(serializer, XMLConstantes.P606, modulo6.getC6_p606());
                    escribirCampoXml(serializer, XMLConstantes.P606_O, modulo6.getC6_p606_o());
                    escribirCampoXml(serializer, XMLConstantes.P607, modulo6.getC6_p607());
                    escribirCampoXml(serializer, XMLConstantes.P608, modulo6.getC6_p608());
                    escribirCampoXml(serializer, XMLConstantes.P609, modulo6.getC6_p609());
                    escribirCampoXml(serializer, XMLConstantes.P609_COD, modulo6.getC6_p609_cod());
                    escribirCampoXml(serializer, XMLConstantes.P610, modulo6.getC6_p610_pd());
                    escribirCampoXml(serializer, XMLConstantes.P611, modulo6.getC6_p611());
                    escribirCampoXml(serializer, XMLConstantes.P611_COD, modulo6.getC6_p611_cod());
                    escribirCampoXml(serializer, XMLConstantes.P612, modulo6.getC6_p612());
                    escribirCampoXml(serializer, XMLConstantes.P613, modulo6.getC6_p613());
                    escribirCampoXml(serializer, XMLConstantes.P614, modulo6.getC6_p614_esp());
                    escribirCampoXml(serializer, XMLConstantes.P614_T, modulo6.getC6_p614_mon());
                    escribirCampoXml(serializer, XMLConstantes.P615_1,modulo6.getC6_p615_pd());
                    escribirCampoXml(serializer, XMLConstantes.P615_2,modulo6.getC6_p615_pl());
                    escribirCampoXml(serializer, XMLConstantes.P615_3,modulo6.getC6_p615_pm());
                    escribirCampoXml(serializer, XMLConstantes.P615_4,modulo6.getC6_p615_pmi());
                    escribirCampoXml(serializer, XMLConstantes.P615_5,modulo6.getC6_p615_pj());
                    escribirCampoXml(serializer, XMLConstantes.P615_6,modulo6.getC6_p615_pv());
                    escribirCampoXml(serializer, XMLConstantes.P615_7,modulo6.getC6_p615_ps());
                    escribirCampoXml(serializer, XMLConstantes.P615_8,modulo6.getC6_p615_sd());
                    escribirCampoXml(serializer, XMLConstantes.P615_9,modulo6.getC6_p615_sl());
                    escribirCampoXml(serializer, XMLConstantes.P615_10,modulo6.getC6_p615_sm());
                    escribirCampoXml(serializer, XMLConstantes.P615_11,modulo6.getC6_p615_smi());
                    escribirCampoXml(serializer, XMLConstantes.P615_12,modulo6.getC6_p615_sj());
                    escribirCampoXml(serializer, XMLConstantes.P615_13,modulo6.getC6_p615_sv());
                    escribirCampoXml(serializer, XMLConstantes.P615_14,modulo6.getC6_p615_ss());
                    escribirCampoXml(serializer, XMLConstantes.P615_T,modulo6.getC6_p615_t());
                    escribirCampoXml(serializer, XMLConstantes.P616, modulo6.getC6_p616());
                    escribirCampoXml(serializer, XMLConstantes.P616_A, modulo6.getC6_p616_a());
                    escribirCampoXml(serializer, XMLConstantes.P617, modulo6.getC6_p617());
                    escribirCampoXml(serializer, XMLConstantes.P618, modulo6.getC6_p618());
                    escribirCampoXml(serializer, XMLConstantes.P619, modulo6.getC6_p619());
                    escribirCampoXml(serializer, XMLConstantes.P620, modulo6.getC6_p620());
                    escribirCampoXml(serializer, XMLConstantes.P620_O, modulo6.getC6_p620_o());
                    escribirCampoXml(serializer, XMLConstantes.P621, modulo6.getC6_p621());
                    escribirCampoXml(serializer, XMLConstantes.P622_1, modulo6.getC6_p622_mon());
                    escribirCampoXml(serializer, XMLConstantes.P622_2, modulo6.getC6_p622_esp());
                    escribirCampoXml(serializer, XMLConstantes.P623_1, modulo6.getC6_p623_mon());
                    escribirCampoXml(serializer, XMLConstantes.P623_2, modulo6.getC6_p623_esp());
                    escribirCampoXml(serializer, XMLConstantes.P623_3, modulo6.getC6_p623_nas());
                    escribirCampoXml(serializer, XMLConstantes.P624_1, modulo6.getC6_p624_mon());
                    escribirCampoXml(serializer, XMLConstantes.P624_2, modulo6.getC6_p624_esp());
                    escribirCampoXml(serializer, XMLConstantes.P624_3, modulo6.getC6_p624_nas());
                    escribirCampoXml(serializer, XMLConstantes.P624_4, modulo6.getC6_p624_nas2());
                    escribirCampoXml(serializer, XMLConstantes.P625, modulo6.getC6_p625());
                    escribirCampoXml(serializer, XMLConstantes.P625_COD_DIST, modulo6.getC6_p625_cod_dist());
                    escribirCampoXml(serializer, XMLConstantes.P625_DIST, modulo6.getC6_p625_dist());
                    escribirCampoXml(serializer, XMLConstantes.P625_COD_PROV, modulo6.getC6_p625_cod_prov());
                    escribirCampoXml(serializer, XMLConstantes.P625_PROV, modulo6.getC6_p625_prov());
                    escribirCampoXml(serializer, XMLConstantes.P625_COD_DEPA, modulo6.getC6_p625_cod_depa());
                    escribirCampoXml(serializer, XMLConstantes.P625_DEPA, modulo6.getC6_p625_depa());
                    escribirCampoXml(serializer, XMLConstantes.P626, modulo6.getC6_p626());
                    escribirCampoXml(serializer, XMLConstantes.P627, modulo6.getC6_p627());
                    escribirCampoXml(serializer, XMLConstantes.P628, modulo6.getC6_p628());
                    escribirCampoXml(serializer, XMLConstantes.P628_O, modulo6.getC6_p628_o());
                    escribirCampoXml(serializer, XMLConstantes.P629, modulo6.getC6_p629());
                    escribirCampoXml(serializer, XMLConstantes.P630, modulo6.getC6_p630());
                    escribirCampoXml(serializer, XMLConstantes.P631, modulo6.getC6_p631());
                    escribirCampoXml(serializer, XMLConstantes.P631_O, modulo6.getC6_p631_o());
                    escribirCampoXml(serializer, XMLConstantes.P632_1, modulo6.getC6_p632_1());
                    escribirCampoXml(serializer, XMLConstantes.P632_2, modulo6.getC6_p632_2());
                    escribirCampoXml(serializer, XMLConstantes.P632_3, modulo6.getC6_p632_3());
                    escribirCampoXml(serializer, XMLConstantes.P632_4, modulo6.getC6_p632_4());
                    escribirCampoXml(serializer, XMLConstantes.P632_5, modulo6.getC6_p632_5());
                    escribirCampoXml(serializer, XMLConstantes.P632_6, modulo6.getC6_p632_6());
                    escribirCampoXml(serializer, XMLConstantes.P632_7, modulo6.getC6_p632_7());
                    escribirCampoXml(serializer, XMLConstantes.P632_8, modulo6.getC6_p632_8());
                    escribirCampoXml(serializer, XMLConstantes.P632_9, modulo6.getC6_p632_9());
                    escribirCampoXml(serializer, XMLConstantes.P632_10, modulo6.getC6_p632_10());
                    escribirCampoXml(serializer, XMLConstantes.P632_10_O, modulo6.getC6_p632_10_o());
                    //escribirCampoXml(serializer, XMLConstantes.P632_11, modulo6.getC6_p632_11());
                    escribirCampoXml(serializer, XMLConstantes.P632_I, modulo6.getC6_p632i());
                    escribirCampoXml(serializer, XMLConstantes.P633, modulo6.getC6_p633());
                    escribirCampoXml(serializer, XMLConstantes.P634_1, modulo6.getC6_p634_1());
                    escribirCampoXml(serializer, XMLConstantes.P634_2, modulo6.getC6_p634_2());
                    escribirCampoXml(serializer, XMLConstantes.P634_3, modulo6.getC6_p634_3());
                    escribirCampoXml(serializer, XMLConstantes.P634_4, modulo6.getC6_p634_4());
                    escribirCampoXml(serializer, XMLConstantes.P634_5, modulo6.getC6_p634_5());
                    escribirCampoXml(serializer, XMLConstantes.P634_6, modulo6.getC6_p634_6());
                    escribirCampoXml(serializer, XMLConstantes.P634_7, modulo6.getC6_p634_7());
                    //escribirCampoXml(serializer, XMLConstantes.P634_6_O, modulo6.getC6_p634_6_o());
                    escribirCampoXml(serializer, XMLConstantes.P634_7_O, modulo6.getC6_p634_7_o());
                    escribirCampoXml(serializer, XMLConstantes.P635, modulo6.getC6_p635());
                    escribirCampoXml(serializer, XMLConstantes.P636, modulo6.getC6_p636());
                    escribirCampoXml(serializer, XMLConstantes.P637, modulo6.getC6_p637());
                    escribirCampoXml(serializer, XMLConstantes.P638_1, modulo6.getC6_p638_1());
                    escribirCampoXml(serializer, XMLConstantes.P638_1_1, modulo6.getC6_p638_1_frec());
                    escribirCampoXml(serializer, XMLConstantes.P638_1_2, modulo6.getC6_p638_1_monto());
                    escribirCampoXml(serializer, XMLConstantes.P638_2, modulo6.getC6_p638_2());
                    escribirCampoXml(serializer, XMLConstantes.P638_2_1, modulo6.getC6_p638_2_frec());
                    escribirCampoXml(serializer, XMLConstantes.P638_2_2, modulo6.getC6_p638_2_monto());
                    escribirCampoXml(serializer, XMLConstantes.P638_3, modulo6.getC6_p638_3());
                    escribirCampoXml(serializer, XMLConstantes.P638_3_1, modulo6.getC6_p638_3_frec());
                    escribirCampoXml(serializer, XMLConstantes.P638_3_2, modulo6.getC6_p638_3_monto());
                    escribirCampoXml(serializer, XMLConstantes.P638_4, modulo6.getC6_p638_4());
                    escribirCampoXml(serializer, XMLConstantes.P638_4_1, modulo6.getC6_p638_4_frec());
                    escribirCampoXml(serializer, XMLConstantes.P638_4_2, modulo6.getC6_p638_4_monto());
                    escribirCampoXml(serializer, XMLConstantes.P639_1, modulo6.getC6_p639_1());
                    escribirCampoXml(serializer, XMLConstantes.P639_1_1, modulo6.getC6_p639_1_med());
                    escribirCampoXml(serializer, XMLConstantes.P639_1_1_O, modulo6.getC6_p639_1_med_o());
                    escribirCampoXml(serializer, XMLConstantes.P639_1_2, modulo6.getC6_p639_1_frec());
                    escribirCampoXml(serializer, XMLConstantes.P639_1_2_O, modulo6.getC6_p639_1_frec_o());
                    escribirCampoXml(serializer, XMLConstantes.P639_1_3, modulo6.getC6_p639_1_monto());
                    escribirCampoXml(serializer, XMLConstantes.P639_2, modulo6.getC6_p639_2());
                    escribirCampoXml(serializer, XMLConstantes.P639_2_1, modulo6.getC6_p639_2_med());
                    escribirCampoXml(serializer, XMLConstantes.P639_2_1_O, modulo6.getC6_p639_2_med_o());
                    escribirCampoXml(serializer, XMLConstantes.P639_2_2, modulo6.getC6_p639_2_frec());
                    escribirCampoXml(serializer, XMLConstantes.P639_2_2_O, modulo6.getC6_p639_2_frec_o());
                    escribirCampoXml(serializer, XMLConstantes.P639_2_3, modulo6.getC6_p639_2_monto());

                    escribirCampoXml(serializer, XMLConstantes.OBS_600, modulo6.getObs_cap6());
                    serializer.endTag("", "ITEM_MODULO6");
                }
                serializer.endTag("", "MODULO6");
            }

            if(modulo7s.size()>0) {
                serializer.startTag("", "MODULO7");
                for (Modulo7 modulo7 : modulo7s) {
                    serializer.startTag("", "ITEM_MODULO7");
                    escribirCampoXml(serializer, XMLConstantes.ID_RESIDENTE, modulo7.get_id());
                    escribirCampoXml(serializer, XMLConstantes.ID_HOGAR, modulo7.getIdHogar());
                    escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA, modulo7.getIdVivienda());
                    escribirCampoXml(serializer, XMLConstantes.INF_700, modulo7.getIdInformante());
                    escribirCampoXml(serializer, XMLConstantes.P701, modulo7.getC7_p701());
                    escribirCampoXml(serializer, XMLConstantes.P702_1, modulo7.getC7_p702_1());
                    escribirCampoXml(serializer, XMLConstantes.P702_2, modulo7.getC7_p702_2());
                    escribirCampoXml(serializer, XMLConstantes.P702_3, modulo7.getC7_p702_3());
                    escribirCampoXml(serializer, XMLConstantes.P702_4, modulo7.getC7_p702_4());
                    escribirCampoXml(serializer, XMLConstantes.P702_5, modulo7.getC7_p702_5());
                    escribirCampoXml(serializer, XMLConstantes.P702_6, modulo7.getC7_p702_6());
                    escribirCampoXml(serializer, XMLConstantes.P702_7, modulo7.getC7_p702_7());
                    escribirCampoXml(serializer, XMLConstantes.P702_7_O, modulo7.getC7_p702_7_o());
                    escribirCampoXml(serializer, XMLConstantes.P703_1, modulo7.getC7_p703_1());
                    escribirCampoXml(serializer, XMLConstantes.P703_2, modulo7.getC7_p703_2());
                    escribirCampoXml(serializer, XMLConstantes.P703_3, modulo7.getC7_p703_3());
                    escribirCampoXml(serializer, XMLConstantes.P703_4, modulo7.getC7_p703_4());
                    escribirCampoXml(serializer, XMLConstantes.P703_5, modulo7.getC7_p703_5());
                    escribirCampoXml(serializer, XMLConstantes.P703_6, modulo7.getC7_p703_6());
                    escribirCampoXml(serializer, XMLConstantes.P703_7, modulo7.getC7_p703_7());
                    escribirCampoXml(serializer, XMLConstantes.P703_8, modulo7.getC7_p703_8());
                    escribirCampoXml(serializer, XMLConstantes.P703_9, modulo7.getC7_p703_9());
                    escribirCampoXml(serializer, XMLConstantes.P703_10, modulo7.getC7_p703_10());
                    escribirCampoXml(serializer, XMLConstantes.P703_10_O, modulo7.getC7_p703_10_o());
                    escribirCampoXml(serializer, XMLConstantes.P704_1, modulo7.getC7_p704_1());
                    escribirCampoXml(serializer, XMLConstantes.P704_2, modulo7.getC7_p704_2());
                    escribirCampoXml(serializer, XMLConstantes.P704_3, modulo7.getC7_p704_3());
                    escribirCampoXml(serializer, XMLConstantes.P704_4, modulo7.getC7_p704_4());
                    escribirCampoXml(serializer, XMLConstantes.P704_5, modulo7.getC7_p704_5());
                    escribirCampoXml(serializer, XMLConstantes.P704_6, modulo7.getC7_p704_6());
                    escribirCampoXml(serializer, XMLConstantes.P704_7, modulo7.getC7_p704_7());
                    escribirCampoXml(serializer, XMLConstantes.P704_8, modulo7.getC7_p704_8());
                    escribirCampoXml(serializer, XMLConstantes.P704_9, modulo7.getC7_p704_9());
                    escribirCampoXml(serializer, XMLConstantes.P704_8_O, modulo7.getC7_p704_8_o());
                    escribirCampoXml(serializer, XMLConstantes.P705, modulo7.getC7_p705());
                    escribirCampoXml(serializer, XMLConstantes.P706_1, modulo7.getC7_p706_1());
                    escribirCampoXml(serializer, XMLConstantes.P706_2, modulo7.getC7_p706_2());
                    escribirCampoXml(serializer, XMLConstantes.P706_3, modulo7.getC7_p706_3());
                    escribirCampoXml(serializer, XMLConstantes.P706_4, modulo7.getC7_p706_4());
                    escribirCampoXml(serializer, XMLConstantes.P706_5, modulo7.getC7_p706_5());
                    escribirCampoXml(serializer, XMLConstantes.P706_6, modulo7.getC7_p706_6());
//                    escribirCampoXml(serializer, XMLConstantes.P706_6_O, modulo7.getC7_p706_6_o());
                    escribirCampoXml(serializer, XMLConstantes.P706_7, modulo7.getC7_p706_7());
                    escribirCampoXml(serializer, XMLConstantes.P706_8, modulo7.getC7_p706_8());
                    escribirCampoXml(serializer, XMLConstantes.P706_9, modulo7.getC7_p706_9());
                    escribirCampoXml(serializer, XMLConstantes.P706_8_O, modulo7.getC7_p706_8_o());
                    escribirCampoXml(serializer, XMLConstantes.P707_1, modulo7.getC7_p707_1());
                    escribirCampoXml(serializer, XMLConstantes.P707_2, modulo7.getC7_p707_2());
                    escribirCampoXml(serializer, XMLConstantes.P707_3, modulo7.getC7_p707_3());
                    escribirCampoXml(serializer, XMLConstantes.P707_4, modulo7.getC7_p707_4());
                    escribirCampoXml(serializer, XMLConstantes.P707_5, modulo7.getC7_p707_5());
                    escribirCampoXml(serializer, XMLConstantes.P707_6, modulo7.getC7_p707_6());
                    escribirCampoXml(serializer, XMLConstantes.P707_7, modulo7.getC7_p707_7());
                    escribirCampoXml(serializer, XMLConstantes.P707_8, modulo7.getC7_p707_8());
                    escribirCampoXml(serializer, XMLConstantes.P707_8_O, modulo7.getC7_p707_8_o());
                    escribirCampoXml(serializer, XMLConstantes.P707_9, modulo7.getC7_p707_9());
                    escribirCampoXml(serializer, XMLConstantes.P708_1, modulo7.getC7_p708_1());
                    escribirCampoXml(serializer, XMLConstantes.P708_2, modulo7.getC7_p708_2());
                    escribirCampoXml(serializer, XMLConstantes.P708_3, modulo7.getC7_p708_3());
                    escribirCampoXml(serializer, XMLConstantes.P709_1, modulo7.getC7_p709_1());
                    escribirCampoXml(serializer, XMLConstantes.P709_2, modulo7.getC7_p709_2());
                    escribirCampoXml(serializer, XMLConstantes.P709_3, modulo7.getC7_p709_3());
                    escribirCampoXml(serializer, XMLConstantes.P709_4, modulo7.getC7_p709_4());
                    escribirCampoXml(serializer, XMLConstantes.P709_5, modulo7.getC7_p709_5());
                    escribirCampoXml(serializer, XMLConstantes.P709_6, modulo7.getC7_p709_6());
                    escribirCampoXml(serializer, XMLConstantes.P709_7, modulo7.getC7_p709_7());
                    escribirCampoXml(serializer, XMLConstantes.P709_8, modulo7.getC7_p709_8());
                    escribirCampoXml(serializer, XMLConstantes.P709_9, modulo7.getC7_p709_9());
                    escribirCampoXml(serializer, XMLConstantes.P709_10A, modulo7.getC7_p709_10a());
                    escribirCampoXml(serializer, XMLConstantes.P709_10, modulo7.getC7_p709_10());
                    escribirCampoXml(serializer, XMLConstantes.P709_10_O, modulo7.getC7_p709_10_o());
                    escribirCampoXml(serializer, XMLConstantes.P709_11, modulo7.getC7_p709_11());
                    escribirCampoXml(serializer, XMLConstantes.OBS_700, modulo7.getObs_cap7());
                    serializer.endTag("", "ITEM_MODULO7");
                }
                serializer.endTag("", "MODULO7");
            }

            if(modulo8s.size()>0) {
                serializer.startTag("", "MODULO8");
                for (Modulo8 modulo8 : modulo8s) {
                    serializer.startTag("", "ITEM_MODULO8");
                    escribirCampoXml(serializer, XMLConstantes.ID_RESIDENTE, modulo8.get_id());
                    escribirCampoXml(serializer, XMLConstantes.INF_800, modulo8.getIdInformante());
                    escribirCampoXml(serializer, XMLConstantes.ID_HOGAR, modulo8.getIdHogar());
                    escribirCampoXml(serializer, XMLConstantes.ID_VIVIENDA, modulo8.getIdVivienda());
                    escribirCampoXml(serializer, XMLConstantes.P801, modulo8.getC8_p801());
                    escribirCampoXml(serializer, XMLConstantes.P802_1, modulo8.getC8_p802_1());
                    //escribirCampoXml(serializer, XMLConstantes.P802A_1, modulo8.getC8_p802a_1());
                    escribirCampoXml(serializer, XMLConstantes.P802A_1_1, modulo8.getC8_p802a_1_1());
                    escribirCampoXml(serializer, XMLConstantes.P802A_1_2, modulo8.getC8_p802a_1_2());
                    escribirCampoXml(serializer, XMLConstantes.P802A_1_3, modulo8.getC8_p802a_1_3());
                    escribirCampoXml(serializer, XMLConstantes.P802A_1_4, modulo8.getC8_p802a_1_4());
                    escribirCampoXml(serializer, XMLConstantes.P802A_1_5, modulo8.getC8_p802a_1_5());
                    escribirCampoXml(serializer, XMLConstantes.P802A_1_6, modulo8.getC8_p802a_1_6());
                    escribirCampoXml(serializer, XMLConstantes.P802A_1_7, modulo8.getC8_p802a_1_7());
                    escribirCampoXml(serializer, XMLConstantes.P802A_1_8, modulo8.getC8_p802a_1_8());
                    escribirCampoXml(serializer, XMLConstantes.P802A_1_O, modulo8.getC8_p802a_1_o());
                    escribirCampoXml(serializer, XMLConstantes.P802_2, modulo8.getC8_p802_2());
                    //escribirCampoXml(serializer, XMLConstantes.P802A_2, modulo8.getC8_p802a_2());
                    escribirCampoXml(serializer, XMLConstantes.P802A_2_1, modulo8.getC8_p802a_2_1());
                    escribirCampoXml(serializer, XMLConstantes.P802A_2_2, modulo8.getC8_p802a_2_2());
                    escribirCampoXml(serializer, XMLConstantes.P802A_2_3, modulo8.getC8_p802a_2_3());
                    escribirCampoXml(serializer, XMLConstantes.P802A_2_4, modulo8.getC8_p802a_2_4());
                    escribirCampoXml(serializer, XMLConstantes.P802A_2_5, modulo8.getC8_p802a_2_5());
                    escribirCampoXml(serializer, XMLConstantes.P802A_2_6, modulo8.getC8_p802a_2_6());
                    escribirCampoXml(serializer, XMLConstantes.P802A_2_7, modulo8.getC8_p802a_2_7());
                    escribirCampoXml(serializer, XMLConstantes.P802A_2_8, modulo8.getC8_p802a_2_8());
                    escribirCampoXml(serializer, XMLConstantes.P802A_2_O, modulo8.getC8_p802a_2_o());
                    escribirCampoXml(serializer, XMLConstantes.P802_3, modulo8.getC8_p802_3());
                    //escribirCampoXml(serializer, XMLConstantes.P802A_3, modulo8.getC8_p802a_3());
                    escribirCampoXml(serializer, XMLConstantes.P802A_3_1, modulo8.getC8_p802a_3_1());
                    escribirCampoXml(serializer, XMLConstantes.P802A_3_2, modulo8.getC8_p802a_3_2());
                    escribirCampoXml(serializer, XMLConstantes.P802A_3_3, modulo8.getC8_p802a_3_3());
                    escribirCampoXml(serializer, XMLConstantes.P802A_3_4, modulo8.getC8_p802a_3_4());
                    escribirCampoXml(serializer, XMLConstantes.P802A_3_5, modulo8.getC8_p802a_3_5());
                    escribirCampoXml(serializer, XMLConstantes.P802A_3_6, modulo8.getC8_p802a_3_6());
                    escribirCampoXml(serializer, XMLConstantes.P802A_3_7, modulo8.getC8_p802a_3_7());
                    escribirCampoXml(serializer, XMLConstantes.P802A_3_8, modulo8.getC8_p802a_3_8());
                    escribirCampoXml(serializer, XMLConstantes.P802A_3_O, modulo8.getC8_p802a_3_o());
                    escribirCampoXml(serializer, XMLConstantes.P803, modulo8.getC8_p803());
                    escribirCampoXml(serializer, XMLConstantes.P804_1, modulo8.getC8_p804_1());
                    escribirCampoXml(serializer, XMLConstantes.P804_2, modulo8.getC8_p804_2());
                    escribirCampoXml(serializer, XMLConstantes.P804_3, modulo8.getC8_p804_3());
                    escribirCampoXml(serializer, XMLConstantes.P804_4, modulo8.getC8_p804_4());
                    escribirCampoXml(serializer, XMLConstantes.P804_5, modulo8.getC8_p804_5());
                    escribirCampoXml(serializer, XMLConstantes.P804_6, modulo8.getC8_p804_6());
                    escribirCampoXml(serializer, XMLConstantes.P804_7, modulo8.getC8_p804_7());
                    escribirCampoXml(serializer, XMLConstantes.P804_8, modulo8.getC8_p804_8());
                    escribirCampoXml(serializer, XMLConstantes.P804_9, modulo8.getC8_p804_9());
                    escribirCampoXml(serializer, XMLConstantes.P804_10, modulo8.getC8_p804_10());
                    escribirCampoXml(serializer, XMLConstantes.P804_11, modulo8.getC8_p804_11());
                    escribirCampoXml(serializer, XMLConstantes.P804_12, modulo8.getC8_p804_12());
                    escribirCampoXml(serializer, XMLConstantes.P804_13, modulo8.getC8_p804_13());
                    escribirCampoXml(serializer, XMLConstantes.P804_14, modulo8.getC8_p804_14());
                    escribirCampoXml(serializer, XMLConstantes.P804_14_O, modulo8.getC8_p804_o());
                    escribirCampoXml(serializer, XMLConstantes.P805_1, modulo8.getC8_p805_1());
                    escribirCampoXml(serializer, XMLConstantes.P805_2, modulo8.getC8_p805_2());
                    escribirCampoXml(serializer, XMLConstantes.P805_3, modulo8.getC8_p805_3());
                    escribirCampoXml(serializer, XMLConstantes.P805_4, modulo8.getC8_p805_4());
                    escribirCampoXml(serializer, XMLConstantes.P805_5, modulo8.getC8_p805_5());
                    escribirCampoXml(serializer, XMLConstantes.P805_6, modulo8.getC8_p805_6());
                    escribirCampoXml(serializer, XMLConstantes.P805_7, modulo8.getC8_p805_7());
                    escribirCampoXml(serializer, XMLConstantes.P805_8, modulo8.getC8_p805_8());
                    escribirCampoXml(serializer, XMLConstantes.P805_9, modulo8.getC8_p805_9());
                    escribirCampoXml(serializer, XMLConstantes.P806, modulo8.getC8_p806_1());
                    escribirCampoXml(serializer, XMLConstantes.P807, modulo8.getC8_p807());
                    escribirCampoXml(serializer,XMLConstantes.EMAIL,modulo8.getEmail());
                    escribirCampoXml(serializer, XMLConstantes.OBS_800, modulo8.getObs_cap8());
                    serializer.endTag("", "ITEM_MODULO8");
                }
                serializer.endTag("", "MODULO8");
            }

            serializer.endTag("", "ENPOVE");
            serializer.endDocument();
            String result = writer.toString();
            File nuevaCarpeta = new File(getExternalStorageDirectory(), "Setting-e21");
            nuevaCarpeta.mkdirs();
            File file = new File(nuevaCarpeta, nombreArchivo);
            IOHelper.writeToFile(file,result);
            servidorItems.add(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void escribirCampoXml(XmlSerializer s, String campo,String valor){
        try {
            s.startTag("", campo);
            if(valor != null) s.text(valor);
            else s.text("");
            s.endTag("", campo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class generateXML extends AsyncTask<Integer,Integer,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtMensaje.setText("Exportando...");
            txtMensaje.setVisibility(View.VISIBLE);
            progreso.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            String mensaje = "";
            if(listaViviendas.size()>0){
                if(getTotalSeleccionadas(listaViviendas).size()>0){
                    for (int i = 0; i < getTotalSeleccionadas(listaViviendas).size(); i++) {
                        exportarViviendaTablet(getTotalSeleccionadas(listaViviendas).get(i).getIdVivienda());
                        exportarViviendaServidor(getTotalSeleccionadas(listaViviendas).get(i).getIdVivienda());
                        if((getTotalSeleccionadas(listaViviendas).size()-1)==i){
                            mensaje = "SE EXPORTO "+getTotalSeleccionadas(listaViviendas).size()+" VIVIENDA(S) EN LA CARPETA ENPOVE2022 DE LA TABLET";
                        }
                    }
                }else{
                    mensaje="DEBE SELECCIONAR UNA VIVIENDA PARA EXPORTAR";
                }
            }else{
                mensaje="NO EXISTE NINGUNA VIVIENDA,DEBE FINALIZAR LA VISITA PARA EXPORTAR";
            }
            return mensaje;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progreso.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);
            txtMensaje.setText(string);
            progreso.setVisibility(View.GONE);
            txtMensaje.setVisibility(View.GONE);
            Toast.makeText(ExportarActivity.this, ""+string, Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<ViviendaItem> getTotalSeleccionadas(ArrayList<ViviendaItem> lista){
        ArrayList<ViviendaItem> viviendas = new ArrayList<>();
        for (ViviendaItem viviendaItem: lista) {
            if (viviendaItem.getSeleccionado()==1){
                viviendas.add(viviendaItem);
            }
        }
        return viviendas;
    }
}
