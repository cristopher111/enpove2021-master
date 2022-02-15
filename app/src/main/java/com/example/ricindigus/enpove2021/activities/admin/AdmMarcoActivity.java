package com.example.ricindigus.enpove2021.activities.admin;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.activities.SplashActivity;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Marco;
import com.example.ricindigus.enpove2021.modelo.pojos.Usuario;
import com.example.ricindigus.enpove2021.util.MarcoPullParser;
import com.example.ricindigus.enpove2021.util.UtilsMethods;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class AdmMarcoActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView txtMensaje;
    String filename = "";
    int tipoCarga;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_marco);
        progressBar = (ProgressBar) findViewById(R.id.progreso_admin);
        txtMensaje = (TextView) findViewById(R.id.mensaje_admin);
        filename = getIntent().getExtras().getString("filename");
        tipoCarga = getIntent().getExtras().getInt("tipo_carga");

        if (tipoCarga == 1)
            new MyAsyncTaskCargarMarco().execute();
        else
            new MyAsyncTaskExportarBD().execute();

    }

    public void exportarBD()throws IOException {
        String inFileName = SQLConstantes.DB_PATH + SQLConstantes.DB_NAME;
        InputStream myInput = new FileInputStream(inFileName);
        String outFileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/bdENPOVE2018.sqlite";
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) != -1){
            if (length > 0){
                myOutput.write(buffer,0,length);
            }
        }
        myOutput.flush();
        myInput.close();
        myOutput.close();
    }

    public class MyAsyncTaskCargarMarco extends AsyncTask<Integer,Integer,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            txtMensaje.setText("CARGANDO MARCO...");
        }

        @Override
        protected String doInBackground(Integer... integers) {
            Data data = new Data(AdmMarcoActivity.this);
            data.open();
            MarcoPullParser marcoPullParser = new MarcoPullParser();
            ArrayList<Marco> marcos = marcoPullParser.parseXML(AdmMarcoActivity.this,filename);
            String nombreUsuario = marcos.get(0).getNombre();
            String dniUsuario = marcos.get(0).getDni();
            //data.actualizarValor(SQLConstantes.tablausuario,SQLConstantes.usuario_nombre,nombreUsuario,marcos.get(0).getUsuario_id());
            //data.actualizarValor(SQLConstantes.tablausuario,SQLConstantes.usuario_dni,dniUsuario,marcos.get(0).getUsuario_id());
            Usuario user = data.getUsuario2("1");
            for(Marco marco:marcos){
                if (!data.existeElemento(SQLConstantes.tablamarco,marco.get_id())){
                    String idVivienda = UtilsMethods.setearConglomeradoFull(marco.getConglomerado())+marco.getCodccpp()+ UtilsMethods.setearManzanaFull(marco.getManzana_id())+marco.getNro_selec_vivienda();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(SQLConstantes.marco_id,idVivienda);
                    contentValues.put(SQLConstantes.marco_anio,marco.getAnio());
                    contentValues.put(SQLConstantes.marco_mes,marco.getMes());
                    contentValues.put(SQLConstantes.marco_periodo,marco.getPeriodo());
                    contentValues.put(SQLConstantes.marco_zona,marco.getZona());
                    contentValues.put(SQLConstantes.marco_ccdd,marco.getCcdd());
                    contentValues.put(SQLConstantes.marco_departamento,marco.getDepartamento());
                    contentValues.put(SQLConstantes.marco_ccpp,marco.getCcpp());
                    contentValues.put(SQLConstantes.marco_provincia,marco.getProvincia());
                    contentValues.put(SQLConstantes.marco_ccdi,marco.getCcdi());
                    contentValues.put(SQLConstantes.marco_distrito,marco.getDistrito());
                    contentValues.put(SQLConstantes.marco_codccpp,marco.getCodccpp());
                    contentValues.put(SQLConstantes.marco_nomccpp,marco.getNomccpp());
                    contentValues.put(SQLConstantes.marco_conglomerado,marco.getConglomerado());
                    contentValues.put(SQLConstantes.marco_norden,marco.getNorden());
                    contentValues.put(SQLConstantes.marco_manzana_id,marco.getManzana_id());
                    contentValues.put(SQLConstantes.marco_tipvia,marco.getTipvia());
                    contentValues.put(SQLConstantes.marco_nomvia,marco.getNomvia());
                    contentValues.put(SQLConstantes.marco_nropta,marco.getNropta());
                    contentValues.put(SQLConstantes.marco_lote,marco.getLote());
                    contentValues.put(SQLConstantes.marco_piso,marco.getPiso());
                    contentValues.put(SQLConstantes.marco_mza,marco.getMza());
                    contentValues.put(SQLConstantes.marco_block,marco.getBlock());
                    contentValues.put(SQLConstantes.marco_interior,marco.getInterior());
                    contentValues.put(SQLConstantes.marco_km,marco.getKm());
                    contentValues.put(SQLConstantes.marco_usuario_id,marco.getUsuario_id());
                    contentValues.put(SQLConstantes.marco_usuario_sup_id,marco.getUsuario_sup_id());
                    contentValues.put(SQLConstantes.marco_estado,marco.getEstado());
                    contentValues.put(SQLConstantes.marco_nro_selec_vivienda,marco.getNro_selec_vivienda());
                    contentValues.put(SQLConstantes.marco_tipo_selec_vivienda,marco.getTipo_selec_vivienda());
                    contentValues.put(SQLConstantes.marco_vivienda_reemplazo,marco.getVivienda_reemplazo());
                    contentValues.put(SQLConstantes.marco_tipo,marco.getTipo());
                    contentValues.put(SQLConstantes.marco_nroVivienda,marco.getNroVivienda());
                    contentValues.put(SQLConstantes.marco_nroSegmento,marco.getNroSegmento());
                    contentValues.put(SQLConstantes.marco_marcoProviene,marco.getMarcoProviene());
                    contentValues.put(SQLConstantes.marco_estrato,marco.getEstrato());
                    contentValues.put(SQLConstantes.marco_observaciones,marco.getObservaciones());
                    contentValues.put(SQLConstantes.marco_nroPuerta2,marco.getNroPuerta2());
                    contentValues.put(SQLConstantes.marco_jefeHogar,marco.getJefeHogar());
                    contentValues.put(SQLConstantes.marco_telefono,marco.getTelefono());
                    contentValues.put(SQLConstantes.marco_correo,marco.getCorreo());
                    contentValues.put(SQLConstantes.marco_aerInicial,marco.getAerInicial());
                    contentValues.put(SQLConstantes.marco_aerFinal,marco.getAerFinal());
                    contentValues.put(SQLConstantes.marco_cono,marco.getCono());
                    contentValues.put(SQLConstantes.marco_area,marco.getArea());
                    contentValues.put(SQLConstantes.marco_areaEncuesta,marco.getAreaEncuesta());
                    contentValues.put(SQLConstantes.marco_region,marco.getRegion());
                    contentValues.put(SQLConstantes.marco_dominio,marco.getDominio());
                    contentValues.put(SQLConstantes.marco_idCarga,marco.getIdCarga());
                    contentValues.put(SQLConstantes.marco_frente,marco.getFrente());
                    contentValues.put(SQLConstantes.marco_latitud,marco.getLatitud());
                    contentValues.put(SQLConstantes.marco_longitud,marco.getLongitud());
                    data.insertarElemento(SQLConstantes.tablamarco,contentValues);
                }
            }
            data.close();
            return "LISTO";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String mensaje) {
            super.onPostExecute(mensaje);
            txtMensaje.setText(mensaje);
            progressBar.setVisibility(View.GONE);
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(AdmMarcoActivity.this, SplashActivity.class);
                    startActivity(intent);
                    finish();
                }
            };
            Timer timer = new Timer();
            timer.schedule(timerTask, 1000);
        }
    }

    public class MyAsyncTaskExportarBD extends AsyncTask<Integer,Integer,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            txtMensaje.setText("EXPORTANDO BD...");
        }

        @Override
        protected String doInBackground(Integer... integers) {
            try {
                exportarBD();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "LISTO";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String mensaje) {
            super.onPostExecute(mensaje);
            txtMensaje.setText(mensaje);
            progressBar.setVisibility(View.GONE);
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    finish();
                }
            };
            Timer timer = new Timer();
            timer.schedule(timerTask, 1000);
        }
    }
}
