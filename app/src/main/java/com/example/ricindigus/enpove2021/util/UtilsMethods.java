package com.example.ricindigus.enpove2021.util;

import android.content.Context;
import android.content.pm.PackageManager;

import com.example.ricindigus.enpove2021.modelo.pojos.FechaActual;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class UtilsMethods {
    //OBTENER VERSION

    public  static  String getVersion(Context context){
        String versionName="";
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    //MARCO
    public static String setearConglomeradoFull(String conglomerado){
        String newConglomerado=conglomerado;
        if (conglomerado.length()==5){
            newConglomerado = "00"+conglomerado;
        }
        return newConglomerado;
    }

    public static String setearManzanaFull(String manzana){
        String newManzana = manzana;
        if(manzana.trim().equals("") || manzana.trim().isEmpty() || manzana.trim().equals("0") || manzana.trim().equals("0000")){
            newManzana = "0000";
        } else
        if (manzana.trim().length()==3){
            newManzana = "0"+manzana;
        }
        return newManzana;
    }

    //CARATULA

    public static String getTipoSeleccionVivienda(String entrada) {
        String salida = "";
        if (entrada.equals("1")) {
            salida = "SEDES";
        }else if (entrada.equals("2")) {
            salida = "RESTO URBANO";
        }else if (entrada.equals("3")) {
            salida = "RURAL";
        }else{
            salida = "NO DEFINIDO";
        }
        return salida;
    }

    public static String getViviendaReemplazo(String entrada) {
        String salida = "";
        if (entrada.equals("1")) {
            salida = "SI";
        } else {
            salida = "NO";
        }
        return salida;
    }

    public static String setTipoSeleccionVivienda(String entrada) {
        String salida = "";
        if (entrada.equals("SEDES")) {
            salida = "1";
        }else if (entrada.equals("RESTO URBANO")) {
            salida = "2";
        }else if (entrada.equals("RURAL")) {
            salida = "3";
        }else{
            salida = "0";
        }
        return salida;
    }

    public static String setViviendaReemplazo(String entrada) {
        String salida = "";
        if (entrada.equals("SI")) {
            salida = "1";
        } else {
            salida = "2";
        }
        return salida;
    }

    //FECHA
    public static boolean validateNumber(String valor){
        boolean estado = valor.matches("[+-]?\\d*(\\.\\d+)?");
        return estado;
    }

    public static FechaActual getFechaActual(){
        int diaInicio;
        int mesInicio;
        int anioInicio;
        int horaInicio;
        int minutoInicio;
        FechaActual fechaActual = new FechaActual();
        Calendar c = Calendar.getInstance();
        diaInicio = c.get(Calendar.DAY_OF_MONTH);
        mesInicio = c.get(Calendar.MONTH) + 1;
        anioInicio = c.get(Calendar.YEAR);
        horaInicio = c.get(Calendar.HOUR_OF_DAY);
        minutoInicio = c.get(Calendar.MINUTE);

        fechaActual.setDiaInicio(checkDigito(diaInicio));
        fechaActual.setMesInicio(checkDigito(mesInicio));
        fechaActual.setAnioInicio(checkDigito(anioInicio));
        fechaActual.setHoraInicio(checkDigito(horaInicio));
        fechaActual.setMinutoInicio(checkDigito(minutoInicio));

        return fechaActual;

    }

    public static String checkDigito (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    public static String getDosDigitos(String cadena){
        String valor = cadena.substring(0,2);
        return  valor;
    }

    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }

    public static double fijarNumero(double numero, int digitos) {
        double resultado;
        resultado = numero * Math.pow(10, digitos);
        resultado = Math.round(resultado);
        resultado = resultado/Math.pow(10, digitos);
        return resultado;
    }

    public static String getSinExpacios(String cadena){
        String valor = cadena.replaceAll(" ","");
        return  valor;
    }

    public static int validarTelefono(String cadena){
        int contador = cadena.length();
        int contadorCaracteres = 0;
        for (int i = 0; i < contador; i++) {
            String dato=cadena.substring(i,i+1);
            if (dato.equals("*") || dato.equals("#")) {
                contadorCaracteres++;
            }
        }
        return contadorCaracteres;
    }

    /*public static boolean validarPiso(String cadena){
        boolean estado=false;
        if(!validateNumber(cadena) && cadena.length()==2){
            String dato1=cadena.substring(0,1);
            String dato2=cadena.substring(1,2);
            if(dato1.equals("A") || dato1.equals("S")){
                if(validateNumber(dato2)){
                    estado = false;
                }else{
                    estado =true;
                }
            }else{
                estado = true;
            }
        }else if(!validateNumber(cadena) && cadena.length()==1){
            estado = true;
        }
        return estado;
    }*/


    //PERIODO DE TIEMPOS
    public static FechaActual getFechaNow(){
        int dia;
        int mes;
        int anio;
        int hora;
        int minuto;
        FechaActual fechaActual = new FechaActual();
        Calendar   c = Calendar.getInstance();
        dia    = c.get(Calendar.DAY_OF_MONTH);
        mes    = c.get(Calendar.MONTH) + 1;
        anio   = c.get(Calendar.YEAR);
        hora   = c.get(Calendar.HOUR_OF_DAY);
        minuto = c.get(Calendar.MINUTE);

        fechaActual.setDiaInicio(checkDigito(dia));
        fechaActual.setMesInicio(checkDigito(mes));
        fechaActual.setAnioInicio(checkDigito(anio));
        fechaActual.setHoraInicio(checkDigito(hora));
        fechaActual.setMinutoInicio(checkDigito(minuto));
        return fechaActual;
    }

    public static String getMes(int mes){
        String nombreMes="";
        switch (mes){
            case 1:
                nombreMes="ENERO";break;
            case 2:
                nombreMes="FEBRERO";break;
            case 3:
                nombreMes="MARZO";break;
            case 4:
                nombreMes="ABRIL";break;
            case 5:
                nombreMes="MAYO";break;
            case 6:
                nombreMes="JUNIO";break;
            case 7:
                nombreMes="JULIO";break;
            case 8:
                nombreMes="AGOSTO";break;
            case 9:
                nombreMes="SETIEMBRE";break;
            case 10:
                nombreMes="OCTUBRE";break;
            case 11:
                nombreMes="NOVIEMBRE";break;
            case 12:
            case 0:
                nombreMes="DICIEMBRE";break;
        }
        return nombreMes;
    }
//////////////ANTIGU0////////////////////////////////////////////////////////////
   /* public static String getPeriodoReferenciaMes(int nroMeses){
        //PERIODO SOLO ADMITE VALORES DE 1 A 11
        String mensaje="";
        int mesInicio=0;
        int mesFinal = Integer.parseInt(getFechaNow().getMesInicio())-1;
        if(Integer.parseInt(getFechaNow().getMesInicio())>nroMeses && nroMeses>0 && nroMeses<12){
            mesInicio = Integer.parseInt(getFechaNow().getMesInicio())-nroMeses;
            mensaje   = getMes(mesInicio)+"-"+getFechaNow().getAnioInicio()+" A "+ getMes(mesFinal)+"-"+getFechaNow().getAnioInicio();
        }else if(Integer.parseInt(getFechaNow().getMesInicio())<=nroMeses && nroMeses>0 && nroMeses<12){
            mesInicio = Integer.parseInt(getFechaNow().getMesInicio())-nroMeses+12;
            mensaje   = getMes(mesInicio)+"-"+(Integer.parseInt(getFechaNow().getAnioInicio())-1)+" A "+ getMes(mesFinal)+"-"+getFechaNow().getAnioInicio();
        }else {
            mensaje   = "Ingrese solo valores de 1 a 11";
        }
        return mensaje;
    }*/

 /////////////////NUEVO 26/01/2022/////////////////////////////////////////////

    public static String getPeriodoReferenciaMes(int nroMeses){
        //PERIODO SOLO ADMITE VALORES DE 1 A 11
        String mensaje="";
        int mesInicio=0;
        int mesFinal = Integer.parseInt(getFechaNow().getMesInicio())-1;
        if(Integer.parseInt(getFechaNow().getMesInicio())>nroMeses && nroMeses>0 && nroMeses<12){
            mesInicio = Integer.parseInt(getFechaNow().getMesInicio())-nroMeses;
            mensaje   = getMes(mesInicio)+"-"+getFechaNow().getAnioInicio()+" A "+ getMes(mesFinal)+"-"+2021;
        }else if(Integer.parseInt(getFechaNow().getMesInicio())<=nroMeses && nroMeses>0 && nroMeses<12){
            mesInicio = Integer.parseInt(getFechaNow().getMesInicio())-nroMeses+12;
            mensaje   = getMes(mesInicio)+"-"+(Integer.parseInt(getFechaNow().getAnioInicio())-1)+" A "+ getMes(mesFinal)+"-"+2021;
        }else {
            mensaje   = "Ingrese solo valores de 1 a 11";
        }
        return mensaje;
    }
///////////////////////////////////////////////////////////////////////////////////////////
    public static String getPeriodoReferenciaMesPasado(){
        String mensaje="";
        int mesInicio= Integer.parseInt(getFechaNow().getMesInicio())-1;
        if (mesInicio>0){
            mensaje   = getMes(mesInicio)+"-"+getFechaNow().getAnioInicio();
        }else{
            mensaje   = getMes(mesInicio)+"-"+(Integer.parseInt(getFechaNow().getAnioInicio())-1);
        }

        return mensaje;
    }

    public static String getPeriodoReferenciaSemana(int nroSemanas){
        int semanas = nroSemanas*7;
        String mensaje   = "";
        Date date = new Date();
        Calendar hoy1 = Calendar.getInstance();
        Calendar hoy2 = Calendar.getInstance();
        Calendar hoy3 = Calendar.getInstance();
        hoy1.setTime(date);
        int dayOfWeek = hoy1.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek==7 && hoy1.get(Calendar.HOUR_OF_DAY)>12){
            hoy2.add(hoy1.DATE,-6);
            hoy3.add(hoy2.DATE,-(dayOfWeek-1+(semanas-7)));
            mensaje = hoy3.get(Calendar.DAY_OF_MONTH)+"-"+getMes(hoy3.get(Calendar.MONTH)+1)+" AL "+(hoy1.get(Calendar.DAY_OF_MONTH))+"-"+getMes(hoy1.get(Calendar.MONTH)+1);
        }else {
            hoy2.add(hoy1.DATE,-(dayOfWeek));
            hoy3.add(hoy2.DATE,-(dayOfWeek-1+semanas));
            mensaje = hoy3.get(Calendar.DAY_OF_MONTH)+"-"+getMes(hoy3.get(Calendar.MONTH)+1)+" AL "+(hoy2.get(Calendar.DAY_OF_MONTH))+"-"+getMes(hoy2.get(Calendar.MONTH)+1);
        }
        return mensaje;
    }

    public static String getNameResult(int resultado){
        String nombreResultado="";
        switch (resultado){
            case 1:
                nombreResultado="1.Completa";break;
            case 2:
                nombreResultado="2.Incompleta";break;
            case 3:
                nombreResultado="3.Rechazo";break;
            case 4:
                nombreResultado="4.Ausente";break;
            case 5:
                nombreResultado="5.Telf. Apagado";break;
            case 6:
                nombreResultado="6.Timbra,pero no contesta";break;
            case 7:
                nombreResultado="7.Telf. fuera de servicio";break;
            case 8:
                nombreResultado="8.Telf. no existe";break;
            case 9:
                nombreResultado="9.Telf. no disponible";break;
            case 10:
                nombreResultado="10.Telf. equivocado";break;
            case 11:
                nombreResultado="11.No hay cobertura";break;
            case 12:
                nombreResultado="12.No tiene telf.";break;
            case 13:
                nombreResultado="13.Otro";break;
            default:
                nombreResultado="-----";break;
        }
        return nombreResultado;
    }

    public static int validateFechas(String dia,String mes,String anio){
        int estado = 2;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date hoy = sdformat.parse(sdformat.format(c.getTime()));
            Date fecha = sdformat.parse(dia+"/"+mes+"/"+anio);
            if (hoy.compareTo(fecha) > 0) {
                estado = 1;
            } else if (hoy.compareTo(fecha) < 0) {
                estado = -1;
            } else if (hoy.compareTo(fecha) == 0) {
                estado = 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return estado;
    }

    public static int getResultadoVivienda(ArrayList<String> lista){
        int resultado = 0;
        String x = lista.get(0);
        int contador1 = 0;
        int contador2 = 0;
        int contador3 = 0;
        for (int i = 0; i < lista.size() ; i++) {
            System.out.println(""+x+"=="+lista.get(i));
            if(x==lista.get(i)){
                contador1++;
            }
            if("1"==lista.get(i)){
                contador2++;
            }
            if("2"==lista.get(i)){
                contador3++;
            }
        }
        if(contador1==lista.size()){
            resultado = Integer.parseInt(lista.get(0));
        }else if(contador2==1){
            resultado = 2;
        }else if(contador3==1){
            resultado = 2;
        }else { resultado = getMenorValor(lista);
        }
        return resultado;
    }

    public static int getMenorValor(ArrayList<String> lista){
        int menor;
        if(!lista.isEmpty())
        { menor = Integer.parseInt(lista.get(0));
            for (int i = 0; i < lista.size() ; i++) {
                int numero = Integer.parseInt(lista.get(i));
                if (numero<menor)
                {menor =numero;}
            }
        }
        else{menor=0;}
        return menor;
    }


}
