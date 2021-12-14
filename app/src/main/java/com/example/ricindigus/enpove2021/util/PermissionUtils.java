package com.example.ricindigus.enpove2021.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;


import java.util.Calendar;

public class PermissionUtils {

    private PermissionUtils() {
    }

    @SuppressWarnings("deprecation")
    public static NetworkInfo checkInternet(Context context){
        ConnectivityManager con = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = con.getActiveNetworkInfo();
        return networkInfo;
    }

    public static boolean checkVersion(){
        boolean estado = false;
        /* Version del Equipo >= Version 6.0 Marshmallow */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            estado = true;
        }
        return estado;
    }

    public static boolean checkPermits(Context context){
        boolean estado = false;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            estado = true;
        }
        return estado;
    }

    public static void askPermits(Context context){
        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    public static String getDate(){
        final String fechahora;
        Calendar calendario = Calendar.getInstance();
        int yy = calendario.get(Calendar.YEAR);
        int mm = calendario.get(Calendar.MONTH)+1;
        int dd = calendario.get(Calendar.DAY_OF_MONTH);
        fechahora = dd+"/"+mm+"/"+yy;
        return fechahora;
    }

}
