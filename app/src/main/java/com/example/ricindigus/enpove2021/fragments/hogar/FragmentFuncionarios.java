package com.example.ricindigus.enpove2021.fragments.hogar;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Caratula;
import com.example.ricindigus.enpove2021.modelo.pojos.Funcionario;
import com.example.ricindigus.enpove2021.modelo.pojos.Hogar;
import com.example.ricindigus.enpove2021.modelo.pojos.Marco;
import com.example.ricindigus.enpove2021.modelo.pojos.Usuario;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFuncionarios extends FragmentPagina {
    TextInputEditText caratula_p15_o_edt;
    EditText nomEncuestador, nomSupervisor, nomSupervisor_n,nomCoordinador;
    EditText dniEncuestador, dniSupervisor, dniSupervisor_n,dniCoordinador;
    EditText caratula_p1_edt,caratula_p16_edt,caratula_p17_edt,caratula_p18_edt,caratula_p19_edt,caratula_p20_edt,caratula_p21_edt,observaciones_edt;

    RadioGroup radiogroupPersonas,caratula_p15_rg,caratula_p21_rg;
    EditText numeroPersonas;

    String idHogar;
    String idVivienda;
    Context context;

    CardView cvEncuestador, cvsupervisor, cvCoordinador;
    LinearLayout layoutPersonas;

    private String dni_encu;
    private String dni_sup;
    private String dni_sup_n;
    private  String dni_coor;
    private  String nombre_encu;
    private String nombre_sup;
    private String nombre_sup_n;
    private String nombre_coord;
    private int p15;
    private String p15_o;
    private int p21;
    private String p21_o;
    private String observaciones;

    private String nropersonas;
    private int vive;



    @SuppressLint("ValidFragment")
    public FragmentFuncionarios(String idHogar, String idVivienda, Context context) {
        this.idHogar = idHogar;
        this.idVivienda = idVivienda;
        this.context = context;
    }

    public FragmentFuncionarios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_funcionarios, container, false);
        nomEncuestador = (EditText)rootView.findViewById(R.id.funcionarios_edtnombre_encuestador);
        nomSupervisor = (EditText)rootView.findViewById(R.id.funcionarios_edtnombre_supervisor);
        nomSupervisor_n = (EditText)rootView.findViewById(R.id.funcionarios_edtnombre_supervisor_n);
        nomCoordinador = (EditText)rootView.findViewById(R.id.funcionarios_edtnombre_coordinador);
        dniEncuestador = (EditText)rootView.findViewById(R.id.funcionarios_edtdni_encuestador);
        dniSupervisor = (EditText)rootView.findViewById(R.id.funcionarios_edtdni_supervisor);
        dniSupervisor_n = (EditText)rootView.findViewById(R.id.funcionarios_edtdni_supervisor_n);
        dniCoordinador = (EditText)rootView.findViewById(R.id.funcionarios_edtdni_coordinador);
        numeroPersonas = (EditText)rootView.findViewById(R.id.funcionarios_npersonas_hogar);
        radiogroupPersonas = (RadioGroup) rootView.findViewById(R.id.funcionarios_radiogroup_personas);

        cvEncuestador = (CardView) rootView.findViewById(R.id.funcionarios_cvEncuestador);
        cvCoordinador = (CardView) rootView.findViewById(R.id.funcionarios_cvCoordinador);
        cvsupervisor = (CardView) rootView.findViewById(R.id.funcionarios_cvSupervisor);

        caratula_p15_rg = (RadioGroup) rootView.findViewById(R.id.caratula_rg_p15);
        caratula_p15_o_edt = (TextInputEditText)rootView.findViewById(R.id.caratula_edt_p15);

        caratula_p16_edt = (EditText)rootView.findViewById(R.id.caratula_p16);
        caratula_p17_edt = (EditText)rootView.findViewById(R.id.caratula_p17);
        caratula_p18_edt = (EditText)rootView.findViewById(R.id.caratula_p18);
        caratula_p19_edt = (EditText)rootView.findViewById(R.id.caratula_p19);
        caratula_p20_edt = (EditText)rootView.findViewById(R.id.caratula_p20);
        caratula_p21_rg = (RadioGroup) rootView.findViewById(R.id.caratula_rg_p21);
        caratula_p21_edt = (EditText)rootView.findViewById(R.id.caratula_edt_P21_o);
        observaciones_edt = (EditText)rootView.findViewById(R.id.edtObservaciones);

        layoutPersonas = (LinearLayout) rootView.findViewById(R.id.layout_pregunta15);

        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UtilsMethodsInputs.setupEditText(dniCoordinador,getContext(),2,8);
        UtilsMethodsInputs.setupEditText(dniSupervisor,getContext(),2,8);
        UtilsMethodsInputs.setupEditText(dniSupervisor_n,getContext(),2,8);
        UtilsMethodsInputs.setupEditText(dniEncuestador,getContext(),2,8);
        UtilsMethodsInputs.setupEditText(nomEncuestador,getContext(),0,100);
        UtilsMethodsInputs.setupEditText(nomSupervisor,getContext(),0,100);
        UtilsMethodsInputs.setupEditText(nomSupervisor_n,getContext(),0,100);
        UtilsMethodsInputs.setupEditText(nomCoordinador,getContext(),0,100);

        UtilsMethodsInputs.setupEditText(caratula_p15_o_edt,getContext(),2,2);
        UtilsMethodsInputs.setupEditText(caratula_p21_edt,getContext(),0,50);
        UtilsMethodsInputs.setupEditText(observaciones_edt,getContext(),4,2000);

        caratula_p15_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                UtilsMethodsInputs.setupRadioGroupEspecifique(group, checkedId,1,caratula_p15_o_edt);
            }
        });

        caratula_p15_o_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().equals("")){
                    if (Integer.parseInt(editable.toString()) < 1 || Integer.parseInt(editable.toString()) > 30 ){
                        caratula_p15_o_edt.setText("");
                    }
                }
            }
        });

        caratula_p21_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                UtilsMethodsInputs.setupRadioGroupEspecifique(group, checkedId,4,caratula_p21_edt);
            }
        });

        cargarDatos();
    }

    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.funcionarios_dni_encu,dni_encu);
        contentValues.put(SQLConstantes.funcionarios_dni_sup,dni_sup);
        contentValues.put(SQLConstantes.funcionarios_dni_supn,dni_sup_n);
        contentValues.put(SQLConstantes.funcionarios_dni_coord,dni_coor);
        contentValues.put(SQLConstantes.funcionarios_nombre_encu,nombre_encu);
        contentValues.put(SQLConstantes.funcionarios_nombre_sup,nombre_sup);
        contentValues.put(SQLConstantes.funcionarios_nombre_supn,nombre_sup_n);
        contentValues.put(SQLConstantes.funcionarios_nombre_coord,nombre_coord);
        if(!data.existeElemento(getNombreTabla(),idVivienda)){
            Funcionario funcionario = new Funcionario();
            funcionario.set_id(idVivienda);
            data.insertarElemento(getNombreTabla(),funcionario.toValues());
        }
        data.actualizarElemento(getNombreTabla(),contentValues,idVivienda);

        //Hogar
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(SQLConstantes.hogar_p15,p15+"");
        contentValues1.put(SQLConstantes.hogar_p15_o,p15_o);
        data.actualizarElemento(SQLConstantes.tablahogares,contentValues1,idHogar);

        //Caratula
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(SQLConstantes.caratula_observaciones,observaciones);
        contentValues2.put(SQLConstantes.caratula_p21,p21+"");
        contentValues2.put(SQLConstantes.caratula_p21_o,p21_o);
        data.actualizarElemento(SQLConstantes.tablacaratula,contentValues2,idVivienda);
        data.close();
    }

    @Override
    public void llenarVariables() {
        dni_encu = dniEncuestador.getText().toString().trim();
        dni_coor = dniCoordinador.getText().toString().trim();
        dni_sup = dniSupervisor.getText().toString().trim();
        dni_sup_n = dniSupervisor_n.getText().toString().trim();

        nombre_coord = nomCoordinador.getText().toString();
        nombre_sup = nomSupervisor.getText().toString();
        nombre_sup_n = nomSupervisor_n.getText().toString();
        nombre_encu = nomEncuestador.getText().toString();

        p15 = caratula_p15_rg.indexOfChild(caratula_p15_rg.findViewById(caratula_p15_rg.getCheckedRadioButtonId()));
        //p15 = 1;
        p15_o = caratula_p15_o_edt.getText().toString().trim();
        p21 = caratula_p21_rg.indexOfChild(caratula_p21_rg.findViewById(caratula_p21_rg.getCheckedRadioButtonId()));
        p21_o = caratula_p21_edt.getText().toString().trim();
        observaciones = observaciones_edt.getText().toString().trim();

    }

    @Override
    public void cargarDatos() {
        Data data = new Data(context);
        data.open();
        if (data.existeElemento(getNombreTabla(),idVivienda)){
            Funcionario funcionario = data.getFuncionario(idVivienda);

            dniSupervisor.setText(funcionario.getDni_sup());
            dniSupervisor_n.setText(funcionario.getDni_supn());
            dniCoordinador.setText(funcionario.getDni_coor());
            dniEncuestador.setText(funcionario.getDni_encu());

            nomSupervisor.setText(funcionario.getNombre_sup());
            nomSupervisor_n.setText(funcionario.getNombre_supn());
            nomCoordinador.setText(funcionario.getNombre_coord());
            nomEncuestador.setText(funcionario.getNombre_encu());

            Hogar hogar = data.getHogar(idHogar);
            if(!hogar.getP15().equals("-1") && !hogar.getP15().equals(""))((RadioButton)caratula_p15_rg.getChildAt(Integer.parseInt(hogar.getP15()))).setChecked(true);
            caratula_p15_o_edt.setText(hogar.getP15_o());
            caratula_p16_edt.setText(hogar.getP16());
            caratula_p17_edt.setText(hogar.getP17());
            caratula_p18_edt.setText(hogar.getP18());
            caratula_p19_edt.setText(hogar.getP19());
            caratula_p20_edt.setText(hogar.getP20());

            Caratula caratula = data.getCaratula(idVivienda);
            if(!caratula.getP21().equals("-1") && !caratula.getP21().equals(""))((RadioButton)caratula_p21_rg.getChildAt(Integer.parseInt(caratula.getP21()))).setChecked(true);
            caratula_p21_edt.setText(caratula.getP21_o());
            observaciones_edt.setText(caratula.getObservaciones());
        }else{
            Caratula caratula = data.getCaratula(idVivienda);
            //Marco marco = data.getMarco(idVivienda);
            Usuario user = data.getUsuario2(caratula.getUsuario());
            if(user.getCargo_id().equals("1")){
                nomEncuestador.setText(user.getNombre());
                dniEncuestador.setText(user.getDni());
            }
            if(user.getCargo_id().equals("3")){
                nomSupervisor.setText(user.getNombre());
                dniSupervisor.setText(user.getDni());
            }
        }
        data.close();
        //layoutPersonas.setVisibility(View.GONE);
    }

    @Override
    public void llenarVista() {

    }

    @Override
    public boolean validarDatos() {
        llenarVariables();
        if (dni_encu.trim().equals("") && nombre_encu.trim().equals("")){mostrarMensaje("EL DNI Y NOMBRE DEL ENTREVISTADOR/A NO DEBEN DE ESTAR VACIO");return false;}
        if (dni_encu.length()!=8){mostrarMensaje("EL DNI DEL ENTREVISTADOR/A DEBE DE TENER 8 DÍGITOS");return false;}
        if (nombre_encu.trim().length()<4){mostrarMensaje("EL NOMBRE DEL ENTREVISTADOR/A DEBE DE TENER MÁS DE 3 DÍGITOS");return false;}
        if(dni_encu.trim().equals("00000000")){mostrarMensaje("EL DNI DEL ENTREVISTADOR/A ES INVÁLIDO");return false;}

        if(!dni_sup.equals("") || !nombre_sup.equals("")){
            if (dni_sup.length()!=8){mostrarMensaje("EL DNI DEL SUPERVISOR LOCAL / OBSERVADOR/A DEBE DE TENER 8 DÍGITOS");return false;}
            if (nombre_sup.trim().length()<4){mostrarMensaje("EL NOMBRE DEL SUPERVISOR/A  LOCAL / OBSERVADOR/A  DEBE DE TENER MÁS DE 3 DÍGITOS");return false;}
            if (dni_sup.trim().equals("")){mostrarMensaje("SI INGRESO EL DNI DEL SUPERVISOR/A  LOCAL / OBSERVADOR/A DEBE DE INGRESAR SU NOMBRE O VICEVERSA");return false;}
            if (nombre_sup.trim().equals("")){mostrarMensaje("SI INGRESO EL DNI DEL SUPERVISOR/A  LOCAL / OBSERVADOR/A  DEBE DE INGRESAR SU NOMBRE O VICEVERSA");return false;}
            if(dni_sup.trim().equals("00000000")){mostrarMensaje("EL DNI DEL SUPERVISOR/A ES INVÁLIDO");return false;}
        }

        if(!dni_sup_n.equals("") || !nombre_sup_n.equals("")){
            if (dni_sup_n.length()!=8){mostrarMensaje("EL DNI DEL SUPERVISOR/A NACIONAL / OBSERVADOR/A DEBE DE TENER 8 DÍGITOS");return false;}
            if (nombre_sup_n.trim().length()<4){mostrarMensaje("EL NOMBRE DEL SUPERVISOR/A  NACIONAL / OBSERVADOR/A  DEBE DE TENER MÁS DE 3 DÍGITOS");return false;}
            if (dni_sup_n.trim().equals("")){mostrarMensaje("SI INGRESO EL DNI DEL SUPERVISOR/A  NACIONAL / OBSERVADOR/A DEBE DE INGRESAR SU NOMBRE O VICEVERSA");return false;}
            if (nombre_sup_n.trim().equals("")){mostrarMensaje("SI INGRESO EL DNI DEL SUPERVISOR/A  NACIONAL / OBSERVADOR/A  DEBE DE INGRESAR SU NOMBRE O VICEVERSA");return false;}
            if(dni_sup_n.trim().equals("00000000")){mostrarMensaje("EL DNI DEL SUPERVISOR/A NACIONAL ES INVÁLIDO");return false;}
        }

        if(!dni_coor.equals("") || !nombre_coord.equals("")) {
            if (dni_coor.length()!=8){mostrarMensaje("EL DNI DEL COORDINADOR/A DEBE DE TENER 8 DÍGITOS");return false;}
            if (nombre_coord.trim().length()<4){mostrarMensaje("EL NOMBRE DEL COORDINADOR/A DEBE DE TENER MÁS DE 3 DÍGITOS");return false;}
            if (dni_coor.trim().equals("")){mostrarMensaje("SI INGRESO EL DNI DEL COORDINADOR/A DEBE DE INGRESAR SU NOMBRE O VICEVERSA");return false;}
            if (nombre_coord.trim().equals("")){mostrarMensaje("SI INGRESO EL DNI DEL COORDINADOR/A DEBE DE INGRESAR SU NOMBRE O VICEVERSA");return false;}
            if(dni_coor.trim().equals("00000000")){mostrarMensaje("EL DNI DEL COORDINADOR/A ES INVÁLIDO");return false;}
        }

        if (p15 == -1){mostrarMensaje("PREGUNTA 15: DEBE MARCAR UNA OPCIÓN"); return false;}
        else{
            if (p15 == 1){
                if (p15_o.trim().equals("")){mostrarMensaje("PREGUNTA 15: DEBE ESPECIFICAR NUMERO DE PERSONAS");return false;}
//                if (p15_o.trim().equals("01") || p15_o.trim().equals("02") || p15_o.trim().equals("03") || p15_o.trim().equals("04") || p15_o.trim().equals("05") ||
//                    p15_o.trim().equals("06") || p15_o.trim().equals("07") || p15_o.trim().equals("08") || p15_o.trim().equals("09") || p15_o.trim().equals("00")
//                ){mostrarMensaje("PREGUNTA 15: DEBE INGRESAR CIFRA CORRRECTA");return false;}
//                if (Integer.parseInt(p15_o.trim())<1 || Integer.parseInt(p15_o.trim())>30 ){mostrarMensaje("PREGUNTA 15: DEBE INGRESAR VALORES DE 1 A 30");return false;}
            }
        }
        if (p21 == -1){mostrarMensaje("PREGUNTA 21: DEBE MARCAR UNA OPCIÓN"); return false;}
        else{
            if (p21 == 4){
                if (p21_o.trim().equals("")){mostrarMensaje("PREGUNTA 21: DEBE ESPECIFICAR");return false;}
                if(!p21_o.equals("") && p21_o.trim().length()<3){
                    mostrarMensaje("PREGUNTA 21: OTRO ESTABLECIMIENTO (ESPECIFIQUE) NO DEBE SER MENOR A 3 CARACTERES"); return false;
                }
            }
        }
        return true;
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablafuncionarios;
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

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
