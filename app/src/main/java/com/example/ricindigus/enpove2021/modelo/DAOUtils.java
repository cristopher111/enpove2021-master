package com.example.ricindigus.enpove2021.modelo;

import android.content.Context;
import android.util.Log;

import com.example.ricindigus.enpove2021.modelo.pojos.Caratula;
import com.example.ricindigus.enpove2021.modelo.pojos.Estado;
import com.example.ricindigus.enpove2021.modelo.pojos.Hogar;
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
import com.example.ricindigus.enpove2021.modelo.pojos.Municipio;
import com.example.ricindigus.enpove2021.modelo.pojos.Pais;
import com.example.ricindigus.enpove2021.modelo.pojos.Pregunta;
import com.example.ricindigus.enpove2021.modelo.pojos.ResVisitaEncuestador;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.modelo.pojos.ResultadoResidente;
import com.example.ricindigus.enpove2021.modelo.pojos.Usuario;
import com.example.ricindigus.enpove2021.modelo.pojos.VisitaEncuestador;
import com.example.ricindigus.enpove2021.util.UtilsMethods;

import java.util.ArrayList;

public class DAOUtils {
    //MARCO
    public static Marco getMarco(String idVivienda, Context context){
        Marco marco = null;
        Data data = new Data(context);
        data.open();
        marco = data.getMarco(idVivienda);
        data.close();
        return marco;
    }

    public static ArrayList<Marco> getListaSegmentoViviendas(String idSegmento,Context context){
        ArrayList<Marco> viviendas ;
        Data data = new Data(context);
        data.open();
        viviendas = data.getAllSegmentoViviendas(idSegmento);
        data.close();
        return viviendas;
    }

    //USUARIO
    public static Usuario getUsuario(String user, Context context){
        Usuario usuario = null;
        Data data = new Data(context);
        data.open();
        usuario = data.getUsuario(user);
        data.close();
        return usuario;
    }

    public static Usuario getUsuarioId(String id, Context context){
        Usuario usuario = null;
        Data data = new Data(context);
        data.open();
        usuario = data.getUsuario2(id);
        data.close();
        return usuario;
    }

    //VISITA
    public static VisitaEncuestador getVisitaEncuestador(String id, Context context){
        VisitaEncuestador visitaEncuestador = null;
        Data data = new Data(context);
        data.open();
        visitaEncuestador = data.getVisitaEncuestador(id);
        data.close();
        return visitaEncuestador;
    }

    public static VisitaEncuestador getPrimeraVisitaEncuestador(String idVivienda,String idHogar,String numero, Context context){
        VisitaEncuestador visitaEncuestador = null;
        Data data = new Data(context);
        data.open();
        visitaEncuestador = data.getPrimeraVisitaEncuestador(idVivienda,idHogar,numero);
        data.close();
        return visitaEncuestador;
    }

    //VIVIENDA
    public static Caratula getVivienda(String idVivienda, Context context){
        Data data = new Data(context);
        data.open();
        Caratula vivienda = data.getCaratula(idVivienda);
        data.close();
        return vivienda;
    }

    public static ArrayList<Caratula> getViviendas(Context context){
        ArrayList<Caratula> viviendas = new ArrayList<>();
        Data data = new Data(context);
        data.open();
        viviendas = data.getAllCaratulas();
        data.close();
        return viviendas;
    }

    //PREGUNTAS
    public static ArrayList<Pregunta> getPreguntas(String idUsuario,Context context){
        ArrayList<Pregunta> preguntas ;
        Data data = new Data(context);
        data.open();
        preguntas = data.getAllPreguntasUsuario(idUsuario);
        data.close();
        return preguntas;
    }

    //HOGAR
    public static Hogar getHogar(String idHogar, Context context){
        Data data = new Data(context);
        data.open();
        Hogar hogar = data.getHogar(idHogar);
        data.close();
        return hogar;
    }

    public static Hogar getHogarNumero(String idVivienda,String numero, Context context){
        Data data = new Data(context);
        data.open();
        Hogar hogar = data.getHogarNumero(idVivienda,numero);
        data.close();
        return hogar;
    }

    public static ArrayList<ResVisitaEncuestador> getListaResultadoVisitas(String idVivienda,Context context){
        ArrayList<ResVisitaEncuestador> hogares ;
        Data data = new Data(context);
        data.open();
        hogares = data.getAllResultadoVisitaEncuestador(idVivienda);
        data.close();
        return hogares;
    }

    public static ArrayList<Modulo6> getListaHogar600(String idHogar,Context context){
        ArrayList<Modulo6> lista ;
        Data data = new Data(context);
        data.open();
        lista = data.getAllModulo6Hogar(idHogar);
        data.close();
        return lista;
    }

    //MODULO 100
    public static Modulo1V getModulo100V(String idVivienda, Context context){
        Data data = new Data(context);
        data.open();
        Modulo1V vivienda = data.getModulo1V(idVivienda);
        data.close();
        return vivienda;
    }

    public static Modulo1H getModulo100H(String idHogar, Context context){
        Data data = new Data(context);
        data.open();
        Modulo1H hogar = data.getModulo1H(idHogar);
        data.close();
        return hogar;
    }

    public static Modulo1H getModulo100HNumero(String idVivienda,String idHogar, Context context){
        Data data = new Data(context);
        data.open();
        Modulo1H hogar = data.getModulo1HNumero(idVivienda,idHogar);
        data.close();
        return hogar;
    }


    //REDIDENTE
    public static ResultadoResidente getResultadoRedidente(String id, Context context){
        ResultadoResidente resultadoResidente = null;
        Data data = new Data(context);
        data.open();
        resultadoResidente = data.getResultadoResidente(id);
        data.close();
        return resultadoResidente;
    }

    public static Residente getResidente(String id, Context context){
        Residente residente = null;
        Data data = new Data(context);
        data.open();
        residente = data.getResidente(id);
        data.close();
        return residente;
    }

    public static Residente getResidenteNumero(String idVivienda, String idHogar, String numero, Context context){
        Residente residente = null;
        Data data = new Data(context);
        data.open();
        residente = data.getResidenteNumero(idVivienda,idHogar,numero);
        data.close();
        return residente;
    }

    public static ArrayList<Residente> getRedidentes(String idHogar, Context context){
        ArrayList<Residente> residentes = new ArrayList<>();
        Data data = new Data(context);
        data.open();
        residentes = data.getAllResidentesHogar(idHogar);
        data.close();
        return residentes;
    }

    public static Residente getResidenteNroResidente(String idHogar,String nroResidente,Context context){
        Residente residente = null;
        String numero=nroResidente.replaceAll(" ","");
        Data data =  new Data(context);
        data.open();
        residente = data.getNombreResidente(idHogar,Integer.parseInt(UtilsMethods.getSinExpacios(UtilsMethods.getDosDigitos(nroResidente.trim())))+"");
        data.close();
        return residente;
    }

    public static String getJefeVenezolanoHogar(String idHogar, Context context){
        ArrayList<Residente> listaVenezolanos = new ArrayList<>();
        int inicioEdad = 0;
        Residente inicioResidente = new Residente();
        Data data = new Data(context);
        data.open();
        listaVenezolanos = data.getAllResidentesVenezolanosHogar(idHogar,"1");
        data.close();
        if(listaVenezolanos.size()>0){
            inicioResidente = listaVenezolanos.get(0);
            if(!listaVenezolanos.get(0).getC2_p205_a().equals("")){inicioEdad = Integer.parseInt(listaVenezolanos.get(0).getC2_p205_a());}
            for (Residente persona: listaVenezolanos) {
                Residente residente = persona;
                int edad = 0;
                if(!persona.getC2_p205_a().equals("")){edad=Integer.parseInt(persona.getC2_p205_a());};
                if(edad>inicioEdad){
                    inicioEdad = edad;
                    inicioResidente = residente;
                }
            }
        }
        return inicioResidente.get_id();
    }

    //MODULOS
    public static Modulo3 getModulo300(String id, Context context){
        Modulo3 modulo3 = new Modulo3();
        Data data = new Data(context);
        data.open();
        if(data.getModulo3(id)!=null){
            modulo3 = data.getModulo3(id);
        }
        data.close();
        return modulo3;
    }

    public static M3Pregunta318 getModulo300_P313(String idEncuestado,String numero, Context context){
        M3Pregunta318 modulo3P313= new M3Pregunta318();
        Data data = new Data(context);
        data.open();
        if(data.getM3Pregunta318Numero(idEncuestado,numero)!=null){
            modulo3P313 = data.getM3Pregunta318Numero(idEncuestado,numero);
        }
        data.close();
        return modulo3P313;
    }
    ////////////////////////////////////////////////////AGREGADO 29/01/2022////////////////////////////////////
    public static int getModulo300_P313_1(String idEncuestado, Context context){
        int conteoP313_1=0;
        Data data = new Data(context);
        data.open();
        ArrayList<M3Pregunta318> modulo3P313_list= data.getAllM3Pregunta318(idEncuestado);
        for (int i = 0; i < modulo3P313_list.size(); i++) {
            if (modulo3P313_list.get(i).getC3_p318_f().toString().compareTo("-1")!=0) {
                Log.e("getC3_p318_f",""+modulo3P313_list.get(i).getC3_p318_f().toString());
                conteoP313_1++;
            }
        }
        data.close();
        Log.e("conteoP313_1",""+conteoP313_1);
        return conteoP313_1;
    }

    public static int getModulo300_P313_4(String idEncuestado, Context context){
        int conteoP313_4=0;
        Data data = new Data(context);
        data.open();
        ArrayList<M3Pregunta318> modulo3P313_list= data.getAllM3Pregunta318(idEncuestado);
        for (int i = 0; i < modulo3P313_list.size(); i++) {
            if (modulo3P313_list.get(i).getC3_p318_p().toString().compareTo("-1")!=0) {
                Log.e("getC3_p318_p",""+modulo3P313_list.get(i).getC3_p318_p().toString());
                conteoP313_4++;
            }
        }
        data.close();
        Log.e("conteoP313_4",""+conteoP313_4);
        return conteoP313_4;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static Modulo4 getModulo400(String id, Context context){
        Modulo4 modulo4 = new Modulo4();
        Data data = new Data(context);
        data.open();
        if(data.getModulo4(id)!=null){
            modulo4 = data.getModulo4(id);
        }
        data.close();
        return modulo4;
    }

    public static Modulo5 getModulo500(String id, Context context){
        Modulo5 modulo5 = new Modulo5();
        Data data = new Data(context);
        data.open();
        if(data.getModulo5(id)!=null){
            modulo5 = data.getModulo5(id);
        }
        data.close();
        return modulo5;
    }

    public static Modulo6 getModulo600(String id, Context context){
        Modulo6 modulo6 = new Modulo6();
        Data data = new Data(context);
        data.open();
        if(data.getModulo6(id)!=null){
            modulo6 = data.getModulo6(id);
        }
        data.close();
        return modulo6;
    }

    public static Modulo7 getModulo700(String id, Context context){
        Modulo7 modulo7 = new Modulo7();
        Data data = new Data(context);
        data.open();
        if(data.getModulo7(id)!=null){
            modulo7 = data.getModulo7(id);
        }
        data.close();
        return modulo7;
    }

    public static Modulo8 getModulo800(String id, Context context){
        Modulo8 modulo8 = new Modulo8();
        Data data = new Data(context);
        data.open();
        if(data.getModulo8(id)!=null){
            modulo8 = data.getModulo8(id);
        }
        data.close();
        return modulo8;
    }

    public static int convertNumber(String valor){
        int numero=0 ;
        try {
                numero = Integer.parseInt(valor.trim());

        }catch (NullPointerException e){
            Log.e("e:",""+e);
        }catch (NumberFormatException f){
            Log.e("f:",""+f);
        }
        return numero;
    }

    public static int convertNumberNegativo(String valor){
        int numero= -1 ;
        try {
            numero = Integer.parseInt(valor.trim());

        }catch (NullPointerException e){
            Log.e("e:",""+e);
        }catch (NumberFormatException f){
            Log.e("f:",""+f);
        }
        return numero;
    }

    public static int convertNumberEdad(String valor){
        int numero= -1 ;
        try {
            numero = Integer.parseInt(valor.trim());

        }catch (NullPointerException e){
            Log.e("ee:",""+e);
        }catch (NumberFormatException f){
            Log.e("ff:",""+f);
        }
        return numero;
    }

    //COBERTURA

    public static int getCoberturaModulo200(String id, Context context){
        int cobertura = 0;
        Residente modulo200 = getResidente(id, context);
        int P205_A = convertNumberEdad(modulo200.getC2_p205_a());
        int P205_B = convertNumberEdad(modulo200.getC2_p205_m());
        int P209   = convertNumber(modulo200.getC2_p209());
        int P210   = convertNumber(modulo200.getC2_p210());
        if (((P205_A<18 || P205_B>=0) && P209>0 && P210>0) || P205_A>=18){
            cobertura = 1;
        }
        return cobertura;
    }

    public static int getCoberturaModulo300(String id, Context context){
        int cobertura = 0;
        Residente modulo200 = getResidente(id, context);
        Modulo3 modulo300 = getModulo300(id, context);
        //M3Pregunta318 m3Pregunta318 = getModulo300_P313(modulo200.get_id(),id,context);
        int ConteoP313_1 = getModulo300_P313_1(modulo200.get_id(),context);
        int ConteoP313_4 = getModulo300_P313_4(modulo200.get_id(),context);
        int P301_D = convertNumber(modulo300.getC3_p301_d());
        int P308   = convertNumber(modulo300.getC3_p308());
        int P311   = convertNumber(modulo300.getC3_p311());
        int P313   = convertNumber(modulo300.getC3_p313());
        //int P313_1 = convertNumber(m3Pregunta318.getC3_p318_f());
        //int P313_4 = convertNumber(m3Pregunta318.getC3_p318_p());
        int P205_A = convertNumberEdad(modulo200.getC2_p205_a());

////////////////////////BACKUP////////////////////////
       /* if (P301_D>0 && P308>0 && P311>0 && ((P313>0 && P205_A >= 12) || (P205_A<12))){
            cobertura = 1;
        }¨*/

//////////////////////////MODIFICADO 27/01/22///////////
        if (P301_D>0 && P308>0 && P311>0 && ((P313>0 && P205_A >= 12 && (ConteoP313_1 == ConteoP313_4))) || (P205_A<12)){
            cobertura = 1;
        }

        return cobertura;
    }

    public static int getCoberturaModulo400(String id, Context context){
        int cobertura = 0;
        Residente modulo200 = getResidente(id, context);
        Modulo4 modulo400 = getModulo400(id, context);
        int P204   = convertNumber(modulo200.getC2_p204());
        int P205_A = convertNumberEdad(modulo200.getC2_p205_a());
        int P205_B = convertNumberEdad(modulo200.getC2_p205_m());
        int P402   = convertNumber(modulo400.getC4_p402());
        int P405_7 = convertNumber(modulo400.getC4_p405_7());
        int P408_1 = convertNumber(modulo400.getC4_p408_1());
        int P408_2 = convertNumber(modulo400.getC4_p408_2());
        int P408_3 = convertNumber(modulo400.getC4_p408_3());
        int P408_4 = convertNumber(modulo400.getC4_p408_4());
        int P408_5 = convertNumber(modulo400.getC4_p408_5());
        int P408_6 = convertNumber(modulo400.getC4_p408_6());
        int P410   = convertNumber(modulo400.getC4_p410());
        int P413_1 = convertNumber(modulo400.getC4_p413_1());
        int P413_2 = convertNumber(modulo400.getC4_p413_2());
        int P413_3 = convertNumber(modulo400.getC4_p413_3());
        int P413_4 = convertNumber(modulo400.getC4_p413_4());
        int P414   = convertNumber(modulo400.getC4_p414());
        int P417_4 = convertNumber(modulo400.getC4_p417_4());
        int P417_9 = convertNumber(modulo400.getC4_p417_9());
        if (P402>0 && P405_7>=0 && P408_1>0 && P408_2>0 && P408_3>0 && P408_4>0 && P408_5>0 && P408_6>0 && P410>0 &&
            ( ((P205_A<5 || P205_B>=0) && P413_1>0 && P413_2>0 && P413_3>0 && P413_4>0)
              || (P205_A>=5 && P205_A<=11)
              || (P204==1 && (P205_A>=12 && P205_A<=14))
              || (P204==2 && (P205_A>=12 && P205_A<=14) && P414>0)
              || (P204==2 && (P205_A>=15 && P205_A<=49) && P414>0 && P417_4>0)
              || (P204==2 &&  P205_A>49  && P417_4>0)
              || (P204==1 &&  P205_A>=15 && P417_4>0)
            )
          ) {
            cobertura = 1;
        }

        return cobertura;
    }

    public static int getCoberturaModulo500(String id, Context context){
        int cobertura = 0;
        Residente modulo200 = getResidente(id, context);
        Modulo5 modulo500 = getModulo500(id, context);
        int P205_A = convertNumberEdad(modulo200.getC2_p205_a());
        int P205_B = convertNumberEdad(modulo200.getC2_p205_m());
        int P501   = convertNumber(modulo500.getC5_p501());
        int P501A   = convertNumber(modulo500.getC5_p501a());
        int P501B   = convertNumber(modulo500.getC5_p501b());
        int P503   = convertNumber(modulo500.getC5_p503());
        int P505   = convertNumber(modulo500.getC5_p505());
        int P506   = convertNumber(modulo500.getC5_p506());
        int P506a   = convertNumber(modulo500.getC5_p506a());
        int P510   = convertNumber(modulo500.getC5_p510());
        int P511   = convertNumber(modulo500.getC5_p511());
        int P512   = convertNumber(modulo500.getC5_p512());
        int P515   = convertNumber(modulo500.getC5_p515());
        int P516   = convertNumber(modulo500.getC5_p516());

        if (( P205_A>=3 && P501A>0 && ( (P503==1 && P505>0 && P510>0) || (P503==2 && P505>0 )) &&
            ((P505==1 && P506==1) || ((P505==2 || P506==2) && (P205_A>=3 && P205_A<=25) && P511>0) || ((P505==2 || P506==2) && (P205_A>25)))
            && ( ((P501==10 || P501==11 ) && P512>0) ||  (P501>=1 && P501<=9) || P501B>0 || P501A==3)
            && ( (P205_A>=14 && P515>0 && P516>0) || (P205_A>=3 && P205_A<=13) ) )
            || (P205_A<3 || P205_B>=0)
        ){
            cobertura = 1;
        }

        return cobertura;
    }

    public static int getCoberturaModulo600(String id, Context context){
        int cobertura = 0;
        Residente modulo200 = getResidente(id, context);
        Modulo6 modulo600 = getModulo600(id, context);
        int P205_A = convertNumberEdad(modulo200.getC2_p205_a());
        int P205_B = convertNumberEdad(modulo200.getC2_p205_m());
        int P601 = convertNumber(modulo600.getC6_p601());
        int P603 = convertNumber(modulo600.getC6_p603());
        int P604 = convertNumber(modulo600.getC6_p604_1());
        int P605_1 = convertNumber(modulo600.getC6_p605_1());
        int P605_2 = convertNumber(modulo600.getC6_p605_2());
        int P605_3 = convertNumber(modulo600.getC6_p605_3());
        int P605_4 = convertNumber(modulo600.getC6_p605_4());
        int P605_5 = convertNumber(modulo600.getC6_p605_5());
        int P605_6 = convertNumber(modulo600.getC6_p605_6());
        int P605_7 = convertNumber(modulo600.getC6_p605_7());
        int P605_8 = convertNumber(modulo600.getC6_p605_8());
        int P605_9 = convertNumber(modulo600.getC6_p605_9());
        int P605_10 = convertNumber(modulo600.getC6_p605_10());
        int P605_11 = convertNumber(modulo600.getC6_p605_11());
        int P605_12 = convertNumber(modulo600.getC6_p605_12());
        int P607 = convertNumber(modulo600.getC6_p607());
        int P608 = convertNumber(modulo600.getC6_p608());
        String P609 = modulo600.getC6_p609();
        String P610 = modulo600.getC6_p610_pd();
        String P611 = modulo600.getC6_p611();
        int P612 = convertNumber(modulo600.getC6_p612());
        int P614 = convertNumber(modulo600.getC6_p614_mon());
        int P615_T = convertNumber(modulo600.getC6_p615_t());
        int P617 = convertNumber(modulo600.getC6_p617());
        int P619 = convertNumber(modulo600.getC6_p619());
        int P620 = convertNumber(modulo600.getC6_p620());
        int P621 = convertNumber(modulo600.getC6_p621());
        int P622 = convertNumber(modulo600.getC6_p622());
        int P623_1 = convertNumber(modulo600.getC6_p623_mon());
        int P623_2 = convertNumber(modulo600.getC6_p623_esp());
        int P623_3 = convertNumber(modulo600.getC6_p623_nas());
        int P624_1 = convertNumber(modulo600.getC6_p624_mon());
        int P624_2 = convertNumber(modulo600.getC6_p624_esp());
        int P624_3 = convertNumber(modulo600.getC6_p624_nas());
        int P624_4 = convertNumber(modulo600.getC6_p624_nas2());
        int P625 = convertNumber(modulo600.getC6_p625());
        int P626 = convertNumber(modulo600.getC6_p626());
        int P627 = convertNumber(modulo600.getC6_p627());
        int P628 = convertNumber(modulo600.getC6_p628());
        int P629 = convertNumber(modulo600.getC6_p629());
        int P630 = convertNumber(modulo600.getC6_p630());
        int P631 = convertNumber(modulo600.getC6_p631());
        int P632_11 = convertNumber(modulo600.getC6_p632_11());
        int P632_10 = convertNumber(modulo600.getC6_p632_10());
        int P633 = convertNumber(modulo600.getC6_p633());
        int P632i = convertNumber(modulo600.getC6_p632i());
        //int P634 = convertNumber(modulo600.getC6_p634());
        int P635 = convertNumber(modulo600.getC6_p635());
        int P636 = convertNumber(modulo600.getC6_p636());
        int P639_1 = convertNumber(modulo600.getC6_p639_1());
        int P639_2 = convertNumber(modulo600.getC6_p639_2());

        Log.e("datoxs:"," P205_A :"+ convertNumberEdad(modulo200.getC2_p205_a()));
        Log.e("datoxs:"," P205_B :"+ convertNumberEdad(modulo200.getC2_p205_m()));
        Log.e("datoxs:"," P601 :"+ convertNumber(modulo600.getC6_p601()));
        Log.e("datoxs:"," P603 :"+ convertNumber(modulo600.getC6_p603()));
        Log.e("datoxs:"," P604 :"+ convertNumber(modulo600.getC6_p604_1()));
        Log.e("datoxs:"," P605_1 :"+ convertNumber(modulo600.getC6_p605_1()));
        Log.e("datoxs:"," P605_2 :"+ convertNumber(modulo600.getC6_p605_2()));
        Log.e("datoxs:"," P605_3 :"+ convertNumber(modulo600.getC6_p605_3()));
        Log.e("datoxs:"," P605_4 :"+ convertNumber(modulo600.getC6_p605_4()));
        Log.e("datoxs:"," P605_5 :"+ convertNumber(modulo600.getC6_p605_5()));
        Log.e("datoxs:"," P605_6 :"+ convertNumber(modulo600.getC6_p605_6()));
        Log.e("datoxs:"," P605_7 :"+ convertNumber(modulo600.getC6_p605_7()));
        Log.e("datoxs:"," P605_8 :"+ convertNumber(modulo600.getC6_p605_8()));
        Log.e("datoxs:"," P605_9 :"+ convertNumber(modulo600.getC6_p605_9()));
        Log.e("datoxs:"," P605_10 :"+ convertNumber(modulo600.getC6_p605_10()));
        Log.e("datoxs:"," P605_11 :"+ convertNumber(modulo600.getC6_p605_11()));
        Log.e("datoxs:"," P605_12 :"+ convertNumber(modulo600.getC6_p605_12()));
        Log.e("datoxs:"," P607 :"+ convertNumber(modulo600.getC6_p607()));
        Log.e("datoxs:"," P608 :"+ convertNumber(modulo600.getC6_p608()));
        Log.e("datoxs:","P609 :"+ modulo600.getC6_p609());
        Log.e("datoxs:"," P610 :"+ modulo600.getC6_p610_pd());
        Log.e("datoxs:"," P611 :"+ modulo600.getC6_p611());
        Log.e("datoxs:"," P612 :"+ convertNumber(modulo600.getC6_p612()));
        Log.e("datoxs:"," P614 :"+ convertNumber(modulo600.getC6_p614_mon()));
        Log.e("datoxs:"," P615_T :"+ convertNumber(modulo600.getC6_p615_t()));
        Log.e("datoxs:"," P617 :"+ convertNumber(modulo600.getC6_p617()));
        Log.e("datoxs:"," P619 :"+ convertNumber(modulo600.getC6_p619()));
        Log.e("datoxs:"," P620 :"+ convertNumber(modulo600.getC6_p620()));
        Log.e("datoxs:"," P621 :"+ convertNumber(modulo600.getC6_p621()));
        Log.e("datoxs:"," P622 :"+ convertNumber(modulo600.getC6_p622()));
        Log.e("datoxs:"," P623_1 :"+ convertNumber(modulo600.getC6_p623_mon()));
        Log.e("datoxs:"," P623_2 :"+ convertNumber(modulo600.getC6_p623_esp()));
        Log.e("datoxs:"," P623_3 :"+ convertNumber(modulo600.getC6_p623_nas()));
        Log.e("datoxs:"," P624_1 :"+ convertNumber(modulo600.getC6_p624_mon()));
        Log.e("datoxs:"," P624_2 :"+ convertNumber(modulo600.getC6_p624_esp()));
        Log.e("datoxs:"," P624_3 :"+ convertNumber(modulo600.getC6_p624_nas()));
        Log.e("datoxs:"," P624_4 :"+ convertNumber(modulo600.getC6_p624_nas2()));
        Log.e("datoxs:"," P625 :"+ convertNumber(modulo600.getC6_p625()));
        Log.e("datoxs:"," P626 :"+ convertNumber(modulo600.getC6_p626()));
        Log.e("datoxs:"," P627 :"+ convertNumber(modulo600.getC6_p627()));
        Log.e("datoxs:"," P628 :"+ convertNumber(modulo600.getC6_p628()));
        Log.e("datoxs:"," P629 :"+ convertNumber(modulo600.getC6_p629()));
        Log.e("datoxs:"," P630 :"+ convertNumber(modulo600.getC6_p630()));
        Log.e("datoxs:"," P631 :"+ convertNumber(modulo600.getC6_p631()));
        Log.e("datoxs:"," P632_11 :"+ convertNumber(modulo600.getC6_p632_11()));
        Log.e("datoxs:"," P633 :"+ convertNumber(modulo600.getC6_p633()));
        //Log.e("datoxs:"," P634 :"+ convertNumber(modulo600.getC6_p634()));
        Log.e("datoxs:"," P635 :"+ convertNumber(modulo600.getC6_p635()));
        Log.e("datoxs:"," P636 :"+ convertNumber(modulo600.getC6_p636()));
        Log.e("datoxs:"," P639_1 :"+ convertNumber(modulo600.getC6_p639_1()));
        Log.e("datoxs:"," P639_2 :"+ convertNumber(modulo600.getC6_p639_2()));

////////////////////////////////////////////////////////BACKUP COBERTURA CAP600 /////////////////////////////////////////////////////////
      /*  if (
           (
                P205_A>=5 && (P601==1 || P603==1 || P604==1 || P605_1==1 || P605_2==1 || P605_3==1 || P605_4==1 || P605_5==1 || P605_6==1 || P605_7==1 || P605_8==1 || P605_9==1 || P605_10==1 || P605_11==1 || P605_12==1) &&
                P609.length()>3 && P610.length()>3  && P611.length()>3 && P612>0 && P614>0 && P617>0 &&
               (
                   ((P612==4 || P612==5) && P615_T<15 && P626>0 && P627==1 && P632_11>=0 && P635>0)
                || ((P612==4 || P612==5) && P615_T<15 && P626>0 && P627==2 && (P628==1 || P628==2) && P633>0 && P635>0)
                || ((P612==4 || P612==5) && P615_T<15 && P626>0 && P627==2 && (P628==3 || P628==4 || P628==5 || P628==6 || P628==7 || P628==8) && P629>0 && P635>0)
                || ((P612==4 || P612==5) && P615_T>=15 && P635>0)
                || ((P612==1 || P612==2) && P619>0 && ((P623_1>=0 && P623_2>=0) || P623_3>0) && ((P624_1>=0 && P624_2>=0) || P624_3>0 || P624_4>0) && P625>0 && P607==2 && (P608==2 || P608==3) && P626>0 && P627==1 && P632_11>=0 && P635>0)
                || ((P612==1 || P612==2) && P619>0 && ((P623_1>=0 && P623_2>=0) || P623_3>0) && ((P624_1>=0 && P624_2>=0) || P624_3>0 || P624_4>0) && P625>0 && P607==2 && (P608==2 || P608==3) && P626>0 && P627==2 && (P628==1 || P628==2) && P633>0 && P635>0)
                || ((P612==1 || P612==2) && P619>0 && ((P623_1>=0 && P623_2>=0) || P623_3>0) && ((P624_1>=0 && P624_2>=0) || P624_3>0 || P624_4>0) && P625>0 && P607==2 && (P608==2 || P608==3) && P626>0 && P627==2 && (P628>=3 && P628<=8) && P629>0 && P635>0)
                || ((P612==1 || P612==2) && P619>0 && ((P623_1>=0 && P623_2>=0) || P623_3>0) && ((P624_1>=0 && P624_2>=0) || P624_3>0 || P624_4>0) && P625>0 && (P607==1 || P608==1 || (P607==0 && P608==0)) && P635>0)
                || ((P612==3 || P612==6 || P612==7 || P612==8) && P619>0 && P620>0 && P621>0 && P625>0 && P607==2 && (P608==2 ||P608==3) && P626>0 && P627==1 && P632_11>=0 && P635>0)
                || ((P612==3 || P612==6 || P612==7 || P612==8) && P619>0 && P620>0 && P621>0 && P625>0 && P607==2 && (P608==2 ||P608==3) && P626>0 && P627==2 && (P628==1 || P628==2) && P633>0 && P635>0)
                || ((P612==3 || P612==6 || P612==7 || P612==8) && P619>0 && P620>0 && P621>0 && P625>0 && P607==2 && (P608==2 ||P608==3) && P626>0 && P627==2 && (P628>=3 && P628<=8) && P629>0 && P635>0)
                || ((P612==3 || P612==6 || P612==7 || P612==8) && P619>0 && P620>0 && P621>0 && P625>0 && (P607==1 || P608==1 || (P607==0 && P608==0)) && P635>0)
               )
                && P636>0 && ((P205_A>=14 && P639_1>0 && P639_2>0) || P205_A<14)
           ) || (
                   P205_A>=5 &&
                   (P601==2 && P603==2 && P604==2 && P605_1==2 && P605_2==2 && P605_3==2 && P605_4==2 && P605_5==2 && P605_6==2 && P605_7==2 && P605_8==2 && P605_9==2 && P605_10==2 && P605_11==2 && P605_12==2) &&
                   P626>0 && P627==1 && P632_11>=0 && P635>0
           ) || (
                   P205_A>=5 &&
                   (P601==2 && P603==2 && P604==2 && P605_1==2 && P605_2==2 && P605_3==2 && P605_4==2 && P605_5==2 && P605_6==2 && P605_7==2 && P605_8==2 && P605_9==2 && P605_10==2 && P605_11==2 && P605_12==2) &&
                   P626>0 && P627==2 && (P628==1 || P628==2) && P633>0 && P635>0
           ) || (
                   P205_A>=5 &&
                   (P601==2 && P603==2 && P604==2 && P605_1==2 && P605_2==2 && P605_3==2 && P605_4==2 && P605_5==2 && P605_6==2 && P605_7==2 && P605_8==2 && P605_9==2 && P605_10==2 && P605_11==2 && P605_12==2) &&
                   P626>0 && P627==2 && (P628==3 || P628==4 || P628==5 || P628==6 || P628==7 || P628==8) && P629>0 && P635>0
           ) || (
                   P205_A<5 || P205_B>=0
           )
        ){
            cobertura = 1;
        }
        return cobertura;*/
////////////////////////////////////////////////////////MODIFICADO 10/11/21 OBSERVACION 58 CAPITULO600 COBERTURA/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (
                (
                        P205_A>=5 && (P601==1 || P603==1 || P604==1 || P605_1==1 || P605_2==1 || P605_3==1 || P605_4==1 || P605_5==1 || P605_6==1 || P605_7==1 || P605_8==1 || P605_9==1 || P605_10==1 || P605_11==1 || P605_12==1) &&
                                P609.length()>3 && P610.length()>3  && P611.length()>3 && P612>0 && P614>0 && P617>0 &&
                                (
                                        ((P612==4  && P615_T<15) || P612==5 && P626>0 && P627==1 && P632_10>=0 && P633>0 && P635>0)
                                                || ((P612==4 && P615_T<15) || P612==5 && P626>0 && P627==2 && (P628==1 || P628==2) && P633>0 && P635>0)
                                                || ((P612==4 && P615_T<15) || P612==5 && P626>0 && P627==2 && (P628==3 || P628==4 || P628==5 || P628==6 || P628==7 || P628==8) && P629>0 && P635>0)
                                                || (P612==4  && P615_T>=15 && P635>0)
                                                || ((P612==1 || P612==2) && P619>0 && ((P623_1>=0 && P623_2>=0) || P623_3>0) && ((P624_1>=0 && P624_2>=0) || P624_3>0 || P624_4>0) && P625>0 && P607==2 && (P608==2 || P608==3) && P626>0 && P627==1 && P632_10>=0 && P633>0 && P635>0)
                                                || ((P612==1 || P612==2) && P619>0 && ((P623_1>=0 && P623_2>=0) || P623_3>0) && ((P624_1>=0 && P624_2>=0) || P624_3>0 || P624_4>0) && P625>0 && P607==2 && (P608==2 || P608==3) && P626>0 && P627==2 && (P628==1 || P628==2) && P633>0 && P635>0)
                                                || ((P612==1 || P612==2) && P619>0 && ((P623_1>=0 && P623_2>=0) || P623_3>0) && ((P624_1>=0 && P624_2>=0) || P624_3>0 || P624_4>0) && P625>0 && P607==2 && (P608==2 || P608==3) && P626>0 && P627==2 && (P628>=3 && P628<=8) && P629>0 && P635>0)
                                                || ((P612==1 || P612==2) && P619>0 && ((P623_1>=0 && P623_2>=0) || P623_3>0) && ((P624_1>=0 && P624_2>=0) || P624_3>0 || P624_4>0) && P625>0 && (P607==1 || P608==1 || (P607==0 && P608==0)) && P635>0)
                                                || ((P612==3 || P612==6 || P612==7 || P612==8) && P619>0 && P620>0 && P621>0 && P625>0 && P607==2 && (P608==2 ||P608==3) && P626>0 && P627==1 && P632_10>=0 && P633>0 && P635>0)
                                                || ((P612==3 || P612==6 || P612==7 || P612==8) && P619>0 && P620>0 && P621>0 && P625>0 && P607==2 && (P608==2 ||P608==3) && P626>0 && P627==2 && (P628==1 || P628==2) && P633>0 && P635>0)
                                                || ((P612==3 || P612==6 || P612==7 || P612==8) && P619>0 && P620>0 && P621>0 && P625>0 && P607==2 && (P608==2 ||P608==3) && P626>0 && P627==2 && (P628>=3 && P628<=8) && P629>0 && P635>0)
                                                || ((P612==3 || P612==6 || P612==7 || P612==8) && P619>0 && P620>0 && P621>0 && P625>0 && (P607==1 || P608==1 || (P607==0 && P608==0)) && P635>0)
                                )
                                && P636>0 && ((P205_A>=14 && P639_1>0 && P639_2>0) || P205_A<14)
                ) || (
                        P205_A>=5 &&
                                (P601==2 && P603==2 && P604==2 && P605_1==2 && P605_2==2 && P605_3==2 && P605_4==2 && P605_5==2 && P605_6==2 && P605_7==2 && P605_8==2 && P605_9==2 && P605_10==2 && P605_11==2 && P605_12==2) &&
                                P626>0 && P627==1 && P632_11>=0 && P635>0
                ) || (
                        P205_A>=5 &&
                                (P601==2 && P603==2 && P604==2 && P605_1==2 && P605_2==2 && P605_3==2 && P605_4==2 && P605_5==2 && P605_6==2 && P605_7==2 && P605_8==2 && P605_9==2 && P605_10==2 && P605_11==2 && P605_12==2) &&
                                P626>0 && P627==2 && (P628==1 || P628==2) && P633>0 && P635>0
                ) || (
                        P205_A>=5 &&
                                (P601==2 && P603==2 && P604==2 && P605_1==2 && P605_2==2 && P605_3==2 && P605_4==2 && P605_5==2 && P605_6==2 && P605_7==2 && P605_8==2 && P605_9==2 && P605_10==2 && P605_11==2 && P605_12==2) &&
                                P626>0 && P627==2 && (P628==3 || P628==4 || P628==5 || P628==6 || P628==7 || P628==8) && P629>0 && P635>0
                ) || (
                        P205_A<5 || P205_B>=0
                )
        ){
            cobertura = 1;
        }
        return cobertura;
    }

    public static int getCoberturaModulo700(String id, Context context){
        int cobertura = 0;
        Residente modulo200 = getResidente(id, context);
        Modulo7 modulo700 = getModulo700(id, context);

        int P205_A = convertNumberEdad(modulo200.getC2_p205_a());
        int P205_B = convertNumberEdad(modulo200.getC2_p205_m());
        int P701 = convertNumber(modulo700.getC7_p701());
        int P705 = convertNumber(modulo700.getC7_p705());
        int P707_9 = convertNumber(modulo700.getC7_p707_9());
        int P708_1 = convertNumber(modulo700.getC7_p708_1());
        int P708_2 = convertNumber(modulo700.getC7_p708_2());
        int P708_3 = convertNumber(modulo700.getC7_p708_3());
        int P709_1 = convertNumber(modulo700.getC7_p709_1());
        int P709_2 = convertNumber(modulo700.getC7_p709_2());
        int P709_3 = convertNumber(modulo700.getC7_p709_3());
        int P709_4 = convertNumber(modulo700.getC7_p709_4());
        int P709_5 = convertNumber(modulo700.getC7_p709_5());
        int P709_6 = convertNumber(modulo700.getC7_p709_6());
        int P709_7 = convertNumber(modulo700.getC7_p709_7());
        int P709_8 = convertNumber(modulo700.getC7_p709_8());
        int P709_9 = convertNumber(modulo700.getC7_p709_9());
        int P709_10A = convertNumber(modulo700.getC7_p709_10a());
        int P709_10 = convertNumber(modulo700.getC7_p709_10());
        int P709_11 = convertNumber(modulo700.getC7_p709_11());



        if(
                P205_A>=5 && P701>0 &&
                P705>0 && P707_9>=0 && P708_1>0 && P708_2>0 && P708_3>0 && ((P709_1 + P709_2+P709_3 + P709_4 + P709_5 + P709_6 + P709_7 + P709_8 + P709_9 + P709_10A + P709_10)>0 || P709_11==1)
                || (P205_A<5 || P205_B>=0)
        ){
            cobertura = 1;
        }
        return cobertura;
    }

    public static int getCoberturaModulo800(String id, Context context){
        int cobertura = 0;
        Residente modulo200 = getResidente(id, context);
        Modulo8 modulo800 = getModulo800(id, context);
        int P205_A = convertNumberEdad(modulo200.getC2_p205_a());
        int P205_B = convertNumberEdad(modulo200.getC2_p205_m());
        int P801   = convertNumber(modulo800.getC8_p801());
        int P802_1 = convertNumber(modulo800.getC8_p802_1());
        int P802_2 = convertNumber(modulo800.getC8_p802_2());
        int P802_3 = convertNumber(modulo800.getC8_p802_3());
        int P803   = convertNumber(modulo800.getC8_p803());
        int P805   = convertNumber(modulo800.getC8_p806_1());
        int P806   = convertNumber(modulo800.getC8_p806_1());
        int P807   = convertNumber(modulo800.getC8_p807());

        if (
            (P205_A>=18 && P801>0 &&
            (P802_1==2 && P802_2==2 && P802_3==2 && P806>0 || (P802_3==1 && (P803>0 && P806>0 && P807>0))
            || (P802_3==2 && P805>0 && P806>0 && P807>0)  ) )
            || (P205_A<18 || P205_B>=0)
        ){
            cobertura = 1;
        }


        return cobertura;
    }

    public static int getCoberturaPersona(String id, Context context){
        int cobertura = 2;
        if(getCoberturaModulo200(id, context)==1 && getCoberturaModulo300(id, context)==1 && getCoberturaModulo400(id, context)==1 && getCoberturaModulo500(id, context)==1 &&
           getCoberturaModulo600(id, context)==1 && getCoberturaModulo700(id, context)==1 && getCoberturaModulo800(id, context)==1 ){
            cobertura = 1;
        }
       return cobertura;
    }

    //VISITA

    public static ArrayList<Residente> getResidentesVenezolanosHogar(String idHogar,Context context){
        ArrayList<Residente> residentes;
        Data data =  new Data(context);
        data.open();
        residentes = data.getAllResidentesVenezolanosHogar(idHogar,"1");
        data.close();
        return residentes;
    }

/*    public static int getValidacionCoberturaPersona(String idHogar, Context context){
        ArrayList<Residente> residentes = getResidentesVenezolanosHogar(idHogar, context);
        ArrayList<String> lista = new ArrayList<>();
        int cont = 0;
        for(Residente residente: residentes){

            if(residente.getEncuestado_cobertura().equals("2") || residente.getEncuestado_cobertura().equals(""))
            {
                lista.add("2");

                //cont++;
                //Log.e("coberturasincompletas",""+cont);
            }
        }
        return lista.size();
    }*/

    public static int getValidacionCoberturaPersona(String idHogar, Context context){
        Data data =  new Data(context);
        data.open();
        ArrayList<Residente> residentes;
        residentes = data.getAllResidentesHogar(idHogar);
        ArrayList<String> lista = new ArrayList<>();
        for(Residente residente: residentes){

            String s = residente.getEncuestado_cobertura();
            String a = residente.getC2_p208();
            Log.e("validarCob",""+s);
            Log.e("validarP208",""+a);



            if((residente.getEncuestado_cobertura().equals("0") && residente.getC2_p208().equals("1")) || (residente.getEncuestado_cobertura().equals("") && residente.getC2_p208().equals("1")) || residente.getC2_p208().equals(""))
            {
                lista.add("2");
                //cont++;
                //Log.e("coberturasincompletas",""+cont);
            }
        }
        data.close();
        return lista.size();

    }

    //OTROS

    public static Pais getPais(String id, Context context){
        Pais pais = null;
        Data data = new Data(context);
        data.open();
        pais = data.getPais(id);
        data.close();
        return pais;
    }

    public static Estado getEstado(String id, Context context){
        Estado estado = null;
        Data data = new Data(context);
        data.open();
        estado = data.getEstado(id);
        data.close();
        return estado;
    }

    public static Municipio getMunicipio(String id, Context context){
        Municipio municipio = null;
        Data data = new Data(context);
        data.open();
        municipio = data.getMunicipio(id);
        data.close();
        return municipio;
    }


}
