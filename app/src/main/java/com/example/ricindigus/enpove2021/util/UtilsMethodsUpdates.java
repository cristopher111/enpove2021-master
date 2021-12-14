package com.example.ricindigus.enpove2021.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo4;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;

import java.util.ArrayList;

public class UtilsMethodsUpdates {

    @NonNull
    public static String getHogarP16(Context context, String idHogar){
        String total = "";
        Data data =  new Data(context);
        data.open();
        ArrayList<Residente> residents = data.getAllResidentesHogar(idHogar);
        total = residents.size()+"";
        data.close();
        return total;
    }

    @NonNull
    public static String getHogarP17(Context context, String idHogar){
        String total ="";
        Data data =  new Data(context);
        data.open();
        ArrayList<Residente> residents = data.getAllResidentesVenezolanosHogar(idHogar,"1");
        total = residents.size()+"";
        data.close();
        return total;
    }

    @NonNull
    public static String getHogarP18(Context context, String idHogar){
        int contador = 0;
        Data data =  new Data(context);
        data.open();
        ArrayList<Residente> residents = data.getAllResidentesVenezolanosHogar(idHogar,"1");
        for (int i = 0; i < residents.size(); i++) {
            int edad= Integer.parseInt(residents.get(i).getC2_p205_a());
            if(edad>=0 && edad<=17){
                contador++;
            }
        }
        data.close();
        return contador+"";
    }

    @NonNull
    public static String getHogarP19(Context context, String idHogar){
        int contador = 0;
        Data data =  new Data(context);
        data.open();
        ArrayList<Residente> residents = data.getAllResidentesVenezolanosHogar(idHogar,"1");
        for (int i = 0; i < residents.size(); i++) {
            int edad= Integer.parseInt(residents.get(i).getC2_p205_a());
            if(edad>=3 && edad<=17){
                contador++;
            }
        }
        data.close();
        return contador+"";
    }


    public static String getHogarP20(Context context, String idHogar){
        int contador = 0;
        int p408_1= 0;
        int p408_2= 0;
        int p408_3= 0;
        int p408_4= 0;
        int p408_5= 0;
        int p408_6= 0;
        Data data =  new Data(context);
        data.open();
        if(data.existeElementos(SQLConstantes.tablamodulo4,idHogar)){
            ArrayList<Modulo4> discapacitados = data.getAllModulo4P408(idHogar);
            for (int i = 0; i < discapacitados.size(); i++) {
                if(!discapacitados.get(i).getC4_p408_1().equals("")){p408_1= Integer.parseInt(discapacitados.get(i).getC4_p408_1());}
                if(!discapacitados.get(i).getC4_p408_2().equals("")){p408_2= Integer.parseInt(discapacitados.get(i).getC4_p408_2());}
                if(!discapacitados.get(i).getC4_p408_3().equals("")){p408_3= Integer.parseInt(discapacitados.get(i).getC4_p408_3());}
                if(!discapacitados.get(i).getC4_p408_4().equals("")){p408_4= Integer.parseInt(discapacitados.get(i).getC4_p408_4());}
                if(!discapacitados.get(i).getC4_p408_5().equals("")){p408_5= Integer.parseInt(discapacitados.get(i).getC4_p408_5());}
                if(!discapacitados.get(i).getC4_p408_6().equals("")){p408_6= Integer.parseInt(discapacitados.get(i).getC4_p408_6());}

                if(p408_1==1 || p408_2==1 || p408_3==1 || p408_4==1 || p408_5==1 || p408_6==1){
                    contador++;
                }
            }
        }
        data.close();
        return contador+"";
    }

    public static String getJefeVenezolanoHogar(Context context,String idHogar){
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
}
