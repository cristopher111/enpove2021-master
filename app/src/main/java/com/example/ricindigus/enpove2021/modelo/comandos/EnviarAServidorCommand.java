package com.example.ricindigus.enpove2021.modelo.comandos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.modelo.Configuracion;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.pojos.Usuario;
import com.example.ricindigus.enpove2021.util.DateDecorator;
import com.example.ricindigus.enpove2021.util.MyUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class EnviarAServidorCommand {
    //EL DE TRABAJO FORZOSO FUNCIONA CON NORMALIDAD
    // private static final String SERVICE_URL_EXPORTACION = Configuracion.URL_SERVIDOR_WEB_DESARROLLO+"/webresources/files/uploadMultiple";
//------EL DE TRABAJO FORZOSO /webresources/files/uploadMultiple
   // http://webapp.inei.gob.pe:8080/ENPOVE2021_TRANSFERENCIA_DEV/webresources/files/uploadMultiple
    private static final String SERVICE_URL_EXPORTACION = Configuracion.URL_SERVIDOR_WEB_DESARROLLO+"/webresources/files/uploadMultiple";

    private final Context context;
    private final TextView txtMensaje;
    private final ProgressBar progreso;
    private final List<File> servidorItems;
    private final String idUsuario;

    public EnviarAServidorCommand(@NonNull TextView txtMensaje,
                                  ProgressBar progreso,
                                  List<File> servidorItems,
                                  String idUsuario
    ) {
        this.txtMensaje = txtMensaje;
        this.progreso = progreso;
        this.servidorItems = servidorItems;
        this.idUsuario = idUsuario;
        this.context = txtMensaje.getContext();
    }

    public void execute() {
        List<File> fileList = servidorItems;
        try {
            if(fileList.isEmpty()) {
                MyUtil.logm(context, "Aviso:", "Primero debe Exportar en Tablet");
            }
            else {
                enviarViviendasAServidor(fileList);
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "ERROR: "+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void enviarViviendasAServidor(List<File> fileList) throws FileNotFoundException {
        RequestParams params = new RequestParams();
        File[] files = fileList.toArray(new File[fileList.size()]);
        params.put("file", files);

        Data data =  new Data(context);
        data.open();
        Usuario user = data.getUsuario2(idUsuario);
        data.close();
        params.put("usuario", user==null? null : user.getUsuario());

        String conexion = MyUtil.getNetworkClass(context);
        params.put("conexion", conexion);

        params.put("anho", new DateDecorator(new Date()).getAnhoString());

        invokeWSUploadFileExportacion(params);
    }

    private void invokeWSUploadFileExportacion(RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();
        //client.setBasicAuth("miusuario","miclave");
        final int DEFAULT_TIMEOUT = 30 * 1000;
        final int CONECTION_TIMEOUT = 20 * 1000;
        client.setConnectTimeout(CONECTION_TIMEOUT);
        client.setResponseTimeout(DEFAULT_TIMEOUT);
        client.setMaxRetriesAndTimeout(2, 5000);

        txtMensaje.setText("Enviando al servidor...");
        txtMensaje.setVisibility(View.VISIBLE);
        progreso.setVisibility(View.VISIBLE);

        client.post(SERVICE_URL_EXPORTACION,params ,new AsyncHttpResponseHandler() {

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                long progressPercentage = (long) 100 * bytesWritten / totalSize;
                progreso.setProgress((int) progressPercentage);
                super.onProgress(bytesWritten, totalSize);
            }

            @Override
            public void onSuccess(int statusCode, Header[] arg1, byte[] arg2) {
                //prgDialog.hide();
                String str = "";
                String mensaje = "";
                try {
                    if (arg2 != null) {
                        str = new String(arg2, "UTF-8");
                        Log.d("json: ", "json: " + str);
                        Log.d("statusCode: ", "statusCode: " + statusCode);
                        JSONObject obj = new JSONObject(str);
                        mensaje = obj.getString("error_msg");
                    }
                    if (statusCode == 200) {
                        MyUtil.logm(context, "Archivos subidos correctamente:", mensaje);
                    } else {
                        Toast.makeText(context, "Hubo un error vuelva ha intentarlo en unos instantes", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(context.getApplicationContext(), "Hubo un error en el servidor!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                progreso.setVisibility(View.GONE);
                txtMensaje.setVisibility(View.GONE);
            }



            @Override
            public void onFailure(int statusCode, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
                //prgDialog.hide();
                String str = null;
                //HttpResponseException hre = (HttpResponseException) arg3;
                //int statusCodeError = hre.getStatusCode();
                Log.d("status: ", "status: " + statusCode);
                Log.d("error: ", "error: " + arg3.toString());
                //Log.d("status error: ","status code: "+statusCodeError);

                try {
                    if (arg2 != null) {
                        str = new String(arg2, "UTF-8");
                        Log.d("json: ", "json: " + str);
                        Log.d("statusCode: ", "statusCode: " + statusCode);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                if (statusCode == 404)
                    Toast.makeText(context, "Recurso no encontrado", Toast.LENGTH_LONG).show();
                else if (statusCode == 500) {
                    String mensaje = "Servidor";
                    if (str != null && str.contains("No space left on device"))
                        mensaje += ": Falta espacio de almacenamiento.";
                    else
                        mensaje += ": "+arg3.getMessage();
                    Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();
                } else {
                    MyUtil.logm(context, "Error "+statusCode, "Verifique su Conexión a Internet o Servidor.\n"+arg3.getMessage());
                }
            }
        });
    }



}
