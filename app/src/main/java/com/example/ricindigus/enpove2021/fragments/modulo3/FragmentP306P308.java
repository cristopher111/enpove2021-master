package com.example.ricindigus.enpove2021.fragments.modulo3;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo3;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.modelo.pojos.VisitaEncuestador;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP306P308 extends FragmentPagina {
    String idEncuestado;
    String idInformante;
    Context context;

    String idHogar;
    String idVivienda;

    private static final String CERO = "0";
    public final Calendar c = Calendar.getInstance();
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    Spinner informanteSpinner;

    //RadioGroup mod3_306_radiogroup_C3_P306;
    CheckBox c3_p306_1_CheckBox, c3_p306_2_CheckBox, c3_p306_3_CheckBox, c3_p306_4_CheckBox, c3_p306_5_CheckBox,c3_p306_6_CheckBox,c3_p306_7_CheckBox;
    EditText c3_p306_6_o_EditText;
    private String c3_p306_1;
    private String c3_p306_2;
    private String c3_p306_3;
    private String c3_p306_4;
    private String c3_p306_5;
    private String c3_p306_6;
    private String c3_p306_7;
    private String c3_p306_6_o;

    //RadioGroup mod3_307_radiogroup_C3_P307;
    TextView c3_p307_TextView;
    CheckBox c3_p307_1_CheckBox, c3_p307_2_CheckBox, c3_p307_3_CheckBox, c3_p307_4_CheckBox, c3_p307_5_CheckBox,c3_p307_6_CheckBox,c3_p307_7_CheckBox,
            c3_p307_8_CheckBox,c3_p307_9_CheckBox,c3_p307_10_CheckBox,c3_p307_11_CheckBox,c3_p307_12_CheckBox,c3_p307_13_CheckBox;
    EditText mod3_307_edittext_C3_P307_O6,mod3_307_edittext_C3_P307_O12,mod3_307A_edittext_C3_P307A_O;
    private String c3_p307_1;
    private String c3_p307_2;
    private String c3_p307_3;
    private String c3_p307_4;
    private String c3_p307_5;
    private String c3_p307_6;
    private String c3_p307_7;
    private String c3_p307_8;
    private String c3_p307_9;
    private String c3_p307_10;
    private String c3_p307_11;
    private String c3_p307_12;
    private String c3_p307_13;
    String c3_p307_o6;
    String c3_p307_o12;

    private  String c3_p307_a;
    String c3_p307a_o;


    RadioGroup mod3_308_radiogroup_C3_P308, mod3_307_radiogroup_C3_P307, mod3_307A_radiogroup_C3_P307A;

    LinearLayout layoutp306, layoutp307, layoutp307_a, layoutp308;

    String fecha_301="",fecha_303="",fecha_307="";
    String c3_p301_d;
    String c3_p301_m;
    String c3_p301_a;
    String c3_p303_d;
    String c3_p303_m;
    String c3_p303_a;
//    String c3_p306;
//    String c3_p306_o;

//    String c3_p307;
//    String c3_p307_o6;
//    String c3_p307_o12;

    String c3_p308;
    // String c3_p308_m;
    // String c3_p308_e_seleccion;
    // String c3_p308_m_seleccion;

//ESTAS YA NO IRIAN
    //  TextView c3_p307_TextViewDia, c3_p307_TextViewMes, c3_p307_TextViewAnio;
    // Button c3_p307_d_f_Button;
    //  RadioGroup c3_p306_RadioGroup;
    //  EditText c3_p306_EditText;
    //  Spinner c3_p308_estado_Spinner, c3_p308_municipio_Spinner;

    String p212;
    public FragmentP306P308() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentP306P308(String idEncuestado, Context context) {
        this.idEncuestado = idEncuestado;
        this.context = context;
        /////
        Data data = new Data(context);
        data.open();
        Residente residente = data.getResidente(idEncuestado);
        p212 = residente.getC2_p212();
        data.close();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p306_p308, container, false);
        // c3_p307_TextViewDia = (TextView) rootView.findViewById(R.id.mod3_307_textview_C3_P307_D);
        // c3_p307_TextViewMes = (TextView) rootView.findViewById(R.id.mod3_307_textview_C3_P307_M);
        // c3_p307_TextViewAnio = (TextView) rootView.findViewById(R.id.mod3_307_textview_C3_P307_A);
        // c3_p307_d_f_Button = (Button) rootView.findViewById(R.id.mod3_307_button_C3_P307_F);
        // c3_p306_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod3_306_radiogroup_C3_P306);
        // c3_p306_EditText = (EditText) rootView.findViewById(R.id.mod3_306_edittext_C3_P306_O);
        // c3_p308_estado_Spinner = (Spinner) rootView.findViewById(R.id.mod3_308_spinner_C3_P308_E);
        // c3_p308_municipio_Spinner = (Spinner) rootView.findViewById(R.id.mod3_308_spinner_C3_P308_M);

        informanteSpinner = (Spinner) rootView.findViewById(R.id.cabecera_spinner_informante);

//        mod3_306_radiogroup_C3_P306 = (RadioGroup) rootView.findViewById(R.id.mod3_306_radiogroup_C3_P306);
//        mod3_306_edittext_C3_P306_O = (EditText) rootView.findViewById(R.id.mod3_306_edittext_C3_P306_O);
        c3_p306_1_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_306_checkbox_C3_P306_1);
        c3_p306_2_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_306_checkbox_C3_P306_2);
        c3_p306_3_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_306_checkbox_C3_P306_3);
        c3_p306_4_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_306_checkbox_C3_P306_4);
        c3_p306_5_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_306_checkbox_C3_P306_5);
        c3_p306_6_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_306_checkbox_C3_P306_6);
        c3_p306_7_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_306_checkbox_C3_P306_7);
        c3_p306_6_o_EditText = (EditText) rootView.findViewById(R.id.mod3_306_edittext_C3_P306_O);

        c3_p307_1_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_307_checkbox_C3_P307_1);
        c3_p307_2_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_307_checkbox_C3_P307_2);
        c3_p307_3_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_307_checkbox_C3_P307_3);
        c3_p307_4_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_307_checkbox_C3_P307_4);
        c3_p307_5_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_307_checkbox_C3_P307_5);
        c3_p307_6_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_307_checkbox_C3_P307_6);
        c3_p307_7_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_307_checkbox_C3_P307_7);
        c3_p307_8_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_307_checkbox_C3_P307_8);
        c3_p307_9_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_307_checkbox_C3_P307_9);
        c3_p307_10_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_307_checkbox_C3_P307_10);
        c3_p307_11_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_307_checkbox_C3_P307_11);
        c3_p307_12_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_307_checkbox_C3_P307_12);
        c3_p307_13_CheckBox = (CheckBox) rootView.findViewById(R.id.mod3_307_checkbox_C3_P307_13);

        mod3_307_edittext_C3_P307_O6 = (EditText) rootView.findViewById(R.id.mod3_307_edittext_C3_P307_O6);
        mod3_307_edittext_C3_P307_O12 = (EditText) rootView.findViewById(R.id.mod3_307_edittext_C3_P307_O12);
       // mod3_307_edittext_C3_P307_O = (EditText) rootView.findViewById(R.id.mod3_307_edittext_C3_P307_O);

        c3_p307_TextView = (TextView)rootView.findViewById(R.id.txv_Especifique);

        mod3_308_radiogroup_C3_P308 = (RadioGroup) rootView.findViewById(R.id.mod3_308_radiogroup_C3_P308);
        mod3_307_radiogroup_C3_P307 = (RadioGroup) rootView.findViewById(R.id.mod3_307_radiogroup_C3_P307);
        mod3_307A_radiogroup_C3_P307A = (RadioGroup) rootView.findViewById(R.id.mod3_307A_radiogroup_C3_P307A);
        mod3_307A_edittext_C3_P307A_O = (EditText) rootView.findViewById(R.id.mod3_307A_edittext_C3_P307A_O);


        layoutp306 = (LinearLayout) rootView.findViewById(R.id.layout_m3_p306);
        layoutp307 = (LinearLayout) rootView.findViewById(R.id.layout_m3_p307);
        layoutp307_a = (LinearLayout) rootView.findViewById(R.id.layout_m3_p307_a);
        layoutp308 = (LinearLayout) rootView.findViewById(R.id.layout_m3_p308);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        controlarChecked(c3_p306_6_CheckBox,c3_p306_6_o_EditText);

        configurarEditText(c3_p306_6_o_EditText, layoutp306, 0, 50);

        configurarEditText(mod3_307_edittext_C3_P307_O6, layoutp307, 0, 50);
        //controlarChecked(c3_p307_6_CheckBox,mod3_307_edittext_C3_P307_O6);

        configurarEditText(mod3_307A_edittext_C3_P307A_O, layoutp307_a, 0, 50);

        configurarEditText(mod3_307_edittext_C3_P307_O12, layoutp307, 0, 50);
        controlarChecked(c3_p307_12_CheckBox,mod3_307_edittext_C3_P307_O12);

        c3_p306_1_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar();
            }
        });
        c3_p306_2_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar();
            }
        });
        c3_p306_3_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar();
            }
        });
        c3_p306_4_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar();
            }
        });
        c3_p306_5_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar();
            }
        });
        c3_p306_6_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                validarAchurar();
                if(isChecked){
                    c3_p306_6_o_EditText.setEnabled(true);
                    c3_p306_6_o_EditText.setBackgroundResource(R.drawable.input_text_enabled);
                }else {
                    c3_p306_6_o_EditText.setText("");
                    c3_p306_6_o_EditText.setEnabled(false);
                    c3_p306_6_o_EditText.setBackgroundResource(R.drawable.input_text_disabled);
                }
            }
        });


        c3_p306_7_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    c3_p306_1_CheckBox.setChecked(false);
                    c3_p306_2_CheckBox.setChecked(false);
                    c3_p306_3_CheckBox.setChecked(false);
                    c3_p306_4_CheckBox.setChecked(false);
                    c3_p306_5_CheckBox.setChecked(false);
                    c3_p306_6_CheckBox.setChecked(false);
                    c3_p306_6_o_EditText.setText(""); //Anthony M.

                    c3_p306_1_CheckBox.setEnabled(false);
                    c3_p306_2_CheckBox.setEnabled(false);
                    c3_p306_3_CheckBox.setEnabled(false);
                    c3_p306_4_CheckBox.setEnabled(false);
                    c3_p306_5_CheckBox.setEnabled(false);
                    c3_p306_6_CheckBox.setEnabled(false);
                    c3_p306_6_o_EditText.setEnabled(false); //Anthony M.

                }else{
                    c3_p306_1_CheckBox.setEnabled(true);
                    c3_p306_2_CheckBox.setEnabled(true);
                    c3_p306_3_CheckBox.setEnabled(true);
                    c3_p306_4_CheckBox.setEnabled(true);
                    c3_p306_5_CheckBox.setEnabled(true);
                    c3_p306_6_CheckBox.setEnabled(true);
                }
            }
        });


       /* c3_p307_13_CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    c3_p307_1_CheckBox.setChecked(false);
                    c3_p307_2_CheckBox.setChecked(false);
                    c3_p307_3_CheckBox.setChecked(false);
                    c3_p307_4_CheckBox.setChecked(false);
                    c3_p307_5_CheckBox.setChecked(false);
                    c3_p307_6_CheckBox.setChecked(false);
                    mod3_307_edittext_C3_P307_O6.setText("");
                    c3_p307_7_CheckBox.setChecked(false);
                    c3_p307_8_CheckBox.setChecked(false);
                    c3_p307_9_CheckBox.setChecked(false);
                    c3_p307_10_CheckBox.setChecked(false);
                    c3_p307_11_CheckBox.setChecked(false);
                    c3_p307_12_CheckBox.setChecked(false);
                    mod3_307_edittext_C3_P307_O12.setText("");

                    c3_p307_1_CheckBox.setEnabled(false);
                    c3_p307_2_CheckBox.setEnabled(false);
                    c3_p307_3_CheckBox.setEnabled(false);
                    c3_p307_4_CheckBox.setEnabled(false);
                    c3_p307_5_CheckBox.setEnabled(false);
                    c3_p307_6_CheckBox.setEnabled(false);
                    mod3_307_edittext_C3_P307_O6.setEnabled(false);
                    c3_p307_7_CheckBox.setEnabled(false);
                    c3_p307_8_CheckBox.setEnabled(false);
                    c3_p307_9_CheckBox.setEnabled(false);
                    c3_p307_10_CheckBox.setEnabled(false);
                    c3_p307_11_CheckBox.setEnabled(false);
                    c3_p307_12_CheckBox.setEnabled(false);
                    mod3_307_edittext_C3_P307_O12.setEnabled(false);

                }else{
                    c3_p307_1_CheckBox.setEnabled(true);
                    c3_p307_2_CheckBox.setEnabled(true);
                    c3_p307_3_CheckBox.setEnabled(true);
                    c3_p307_4_CheckBox.setEnabled(true);
                    c3_p307_5_CheckBox.setEnabled(true);
                    c3_p307_6_CheckBox.setEnabled(true);
                    c3_p307_7_CheckBox.setEnabled(true);
                    c3_p307_8_CheckBox.setEnabled(true);
                    c3_p307_9_CheckBox.setEnabled(true);
                    c3_p307_10_CheckBox.setEnabled(true);
                    c3_p307_11_CheckBox.setEnabled(true);
                    c3_p307_12_CheckBox.setEnabled(true);
                }
            }
        });*/

        mod3_307_radiogroup_C3_P307.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                switch (seleccionado){
                    case 8:
                        mod3_307_edittext_C3_P307_O6.setEnabled(true);
                        mod3_307_edittext_C3_P307_O6.setBackgroundResource(R.drawable.input_text_enabled);
                        layoutp307_a.setVisibility(View.GONE);
                        break;
                    case 14:
                        mod3_307_edittext_C3_P307_O6.setEnabled(true);
                        mod3_307_edittext_C3_P307_O6.setBackgroundResource(R.drawable.input_text_enabled);
                        layoutp307_a.setVisibility(View.GONE);
                        break;
                    case 15:
                        layoutp307_a.setVisibility(View.VISIBLE);
                        mod3_307_edittext_C3_P307_O6.setText("");
                        mod3_307_edittext_C3_P307_O6.setBackgroundResource(R.drawable.input_text_disabled);
                        mod3_307_edittext_C3_P307_O6.setEnabled(false);
                        break;
                    default:
                        mod3_307_edittext_C3_P307_O6.setText("");
                        mod3_307_edittext_C3_P307_O6.setBackgroundResource(R.drawable.input_text_disabled);
                        mod3_307_edittext_C3_P307_O6.setEnabled(false);
                        layoutp307_a.setVisibility(View.GONE);
                        break;
//                    case 1:layout305.setVisibility(View.VISIBLE);break;
                    //  case 2: c3_p305_RadioGroup.clearCheck();c3_p305_o_EditText.setText("");layout305.setVisibility(View.GONE);break;
                }
            }
        });

        mod3_307A_radiogroup_C3_P307A.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                switch (seleccionado){
                    case 7:
                        mod3_307A_edittext_C3_P307A_O.setEnabled(true);
                        mod3_307A_edittext_C3_P307A_O.setBackgroundResource(R.drawable.input_text_enabled);
                        break;
                    default:
                        mod3_307A_edittext_C3_P307A_O.setText("");
                        mod3_307A_edittext_C3_P307A_O.setBackgroundResource(R.drawable.input_text_disabled);
                        mod3_307A_edittext_C3_P307A_O.setEnabled(false);
                        break;
//                    case 1:layout305.setVisibility(View.VISIBLE);break;
                    //  case 2: c3_p305_RadioGroup.clearCheck();c3_p305_o_EditText.setText("");layout305.setVisibility(View.GONE);break;
                }
            }
        });


        /*
        c3_p307_d_f_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog recogeFecha = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        final int mesActual = month + 1;
                        String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                        String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                        c3_p307_TextViewDia.setText(""+diaFormateado);
                        c3_p307_TextViewMes.setText(""+mesFormateado);
                        c3_p307_TextViewAnio.setText(""+year);
                    }
                },anio,mes,dia);
                recogeFecha.show();
            }
        });

         */

        /*
        configurarEditText(c3_p306_EditText,layoutp306,0,30);

        c3_p306_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,5,c3_p306_EditText);
            }
        });

         */

        /*
        c3_p308_estado_Spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    c3_p308_estado_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, final int pos, long l) {
                            Data data = new Data(context);
                            data.open();
                            ArrayList<String> municipios = new ArrayList<>();
                            if(pos != 0) municipios = data.getMunicipios(data.getCodEstado(pos+""));
                            data.close();
                            cargarSpinerMunicipios(municipios);
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {}
                    });
                }
                return false;
            }
        });

        */
        llenarVista();
        cargarDatos();

    }

    /*
    public void cargarSpinerMunicipios(ArrayList<String> datos){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,datos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        c3_p308_municipio_Spinner.setAdapter(adapter);
    }

     */

    public String checkDigito (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    @Override
    public void guardarDatos() {

        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo3_id_informante,idInformante);
        contentValues.put(SQLConstantes.modulo3_c3_p306_1,c3_p306_1);
        contentValues.put(SQLConstantes.modulo3_c3_p306_2,c3_p306_2);
        contentValues.put(SQLConstantes.modulo3_c3_p306_3,c3_p306_3);
        contentValues.put(SQLConstantes.modulo3_c3_p306_4,c3_p306_4);
        contentValues.put(SQLConstantes.modulo3_c3_p306_5,c3_p306_5);
        contentValues.put(SQLConstantes.modulo3_c3_p306_6,c3_p306_6);
        contentValues.put(SQLConstantes.modulo3_c3_p306_7,c3_p306_7);
        contentValues.put(SQLConstantes.modulo3_c3_p306_o,c3_p306_6_o);

        contentValues.put(SQLConstantes.modulo3_c3_p307_1,c3_p307_1);
      /*  contentValues.put(SQLConstantes.modulo3_c3_p307_2,c3_p307_2);
        contentValues.put(SQLConstantes.modulo3_c3_p307_3,c3_p307_3);
        contentValues.put(SQLConstantes.modulo3_c3_p307_4,c3_p307_4);
        contentValues.put(SQLConstantes.modulo3_c3_p307_5,c3_p307_5);
        contentValues.put(SQLConstantes.modulo3_c3_p307_6,c3_p307_6);
        contentValues.put(SQLConstantes.modulo3_c3_p307_7,c3_p307_7);
        contentValues.put(SQLConstantes.modulo3_c3_p307_8,c3_p307_8);
        contentValues.put(SQLConstantes.modulo3_c3_p307_9,c3_p307_9);
        contentValues.put(SQLConstantes.modulo3_c3_p307_10,c3_p307_10);
        contentValues.put(SQLConstantes.modulo3_c3_p307_11,c3_p307_11);
        contentValues.put(SQLConstantes.modulo3_c3_p307_12,c3_p307_12);*/
        contentValues.put(SQLConstantes.modulo3_c3_p307_o6,c3_p307_o6);
        contentValues.put(SQLConstantes.modulo3_c3_p307_a,c3_p307_a);
        contentValues.put(SQLConstantes.modulo3_c3_p307a_o,c3_p307a_o);
      //  contentValues.put(SQLConstantes.modulo3_c3_p307_o12,c3_p307_o12);
       // contentValues.put(SQLConstantes.modulo3_c3_p307_13,c3_p307_13);
        //contentValues.put(SQLConstantes.modulo3_c3_p307_d,c3_p307_d);
        //contentValues.put(SQLConstantes.modulo3_c3_p307_m,c3_p307_m);
        //contentValues.put(SQLConstantes.modulo3_c3_p307_a,c3_p307_a);

        contentValues.put(SQLConstantes.modulo3_c3_p308,c3_p308);
        // contentValues.put(SQLConstantes.modulo3_c3_p308_e,c3_p308_e);
        // contentValues.put(SQLConstantes.modulo3_c3_p308_m,c3_p308_m);
        // contentValues.put(SQLConstantes.modulo3_c3_p308_e_seleccion,c3_p308_e_seleccion);
        // contentValues.put(SQLConstantes.modulo3_c3_p308_m_seleccion,c3_p308_m_seleccion);

        data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);
        //Ya valido y guardo correctamente el fragment, ahora actualizamos el valor de la cobertura del fragment a correcto(1)
        data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp306p308,"1",idEncuestado);

        ocultarOtrosLayouts();

        //verificamos la cobertura del capitulo y actualizamos su valor de cobertura.
        if (verificarCoberturaCapitulo()) data.actualizarValor(getNombreTabla(),SQLConstantes.modulo3_COB300,"1",idEncuestado);
        else data.actualizarValor(getNombreTabla(),SQLConstantes.modulo3_COB300,"0",idEncuestado);
        //data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"0",idEncuestado);
        data.close();

    }

    private void ocultarOtrosLayouts() {
        Data data = new Data(context);
        data.open();
        if(c3_p308.equals("1") || c3_p308.equals("2") || c3_p308.equals("3") || c3_p308.equals("4")){
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.modulo3_c3_p309,"");
            contentValues.put(SQLConstantes.modulo3_c3_p309_o,"");
            contentValues.put(SQLConstantes.modulo3_c3_p309_1,"");
            contentValues.put(SQLConstantes.modulo3_c3_p309_1_o,"");
            data.actualizarElemento(getNombreTabla(),contentValues,idEncuestado);

            contentValues = new ContentValues();
            contentValues.put(SQLConstantes.layouts_p309,"0");
            contentValues.put(SQLConstantes.layouts_p309_1,"0");

            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p309,"-1",idEncuestado);
        }else{
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.layouts_p309,"1");
            contentValues.put(SQLConstantes.layouts_p309_1,"1");

            data.actualizarElemento(SQLConstantes.tablalayouts, contentValues, idEncuestado);
            if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p309,idEncuestado).equals("-1"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p309,"1",idEncuestado);
            data.actualizarValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp309,"0",idEncuestado);
        }
        data.close();
    }

    @Override
    public void llenarVariables() {
        idInformante = obtener_Nresidente(informanteSpinner);

        if(c3_p306_1_CheckBox.isChecked()) c3_p306_1 = "1"; else c3_p306_1 = "0";
        if(c3_p306_2_CheckBox.isChecked()) c3_p306_2 = "1"; else c3_p306_2 = "0";
        if(c3_p306_3_CheckBox.isChecked()) c3_p306_3 = "1"; else c3_p306_3 = "0";
        if(c3_p306_4_CheckBox.isChecked()) c3_p306_4 = "1"; else c3_p306_4 = "0";
        if(c3_p306_5_CheckBox.isChecked()) c3_p306_5 = "1"; else c3_p306_5 = "0";
        if(c3_p306_6_CheckBox.isChecked()) c3_p306_6 = "1"; else c3_p306_6 = "0";
        if(c3_p306_7_CheckBox.isChecked()) c3_p306_7 = "1"; else c3_p306_7 = "0";
        c3_p306_6_o = c3_p306_6_o_EditText.getText().toString();

       // if(c3_p307_1_CheckBox.isChecked()) c3_p307_1 = "1"; else c3_p307_1 = "0";
       /* if(c3_p307_2_CheckBox.isChecked()) c3_p307_2 = "1"; else c3_p307_2 = "0";
        if(c3_p307_3_CheckBox.isChecked()) c3_p307_3 = "1"; else c3_p307_3 = "0";
        if(c3_p307_4_CheckBox.isChecked()) c3_p307_4 = "1"; else c3_p307_4 = "0";
        if(c3_p307_5_CheckBox.isChecked()) c3_p307_5 = "1"; else c3_p307_5 = "0";
        if(c3_p307_6_CheckBox.isChecked()) c3_p307_6 = "1"; else c3_p307_6 = "0";
        if(c3_p307_7_CheckBox.isChecked()) c3_p307_7 = "1"; else c3_p307_7 = "0";
        if(c3_p307_8_CheckBox.isChecked()) c3_p307_8 = "1"; else c3_p307_8 = "0";
        if(c3_p307_9_CheckBox.isChecked()) c3_p307_9 = "1"; else c3_p307_9 = "0";
        if(c3_p307_10_CheckBox.isChecked()) c3_p307_10 = "1"; else c3_p307_10 = "0";
        if(c3_p307_11_CheckBox.isChecked()) c3_p307_11 = "1"; else c3_p307_11 = "0";
        if(c3_p307_12_CheckBox.isChecked()) c3_p307_12 = "1"; else c3_p307_12 = "0";
        if(c3_p307_13_CheckBox.isChecked()) c3_p307_13 = "1"; else c3_p307_13 = "0";*/

        c3_p307_o6  = mod3_307_edittext_C3_P307_O6.getText().toString();
       // c3_p307_o12  = mod3_307_edittext_C3_P307_O12.getText().toString();

        c3_p308  =  mod3_308_radiogroup_C3_P308.indexOfChild(mod3_308_radiogroup_C3_P308.findViewById(mod3_308_radiogroup_C3_P308.getCheckedRadioButtonId())) +"";
        c3_p307_1 =  mod3_307_radiogroup_C3_P307.indexOfChild(mod3_307_radiogroup_C3_P307.findViewById(mod3_307_radiogroup_C3_P307.getCheckedRadioButtonId())) +"";
        c3_p307_a =  mod3_307A_radiogroup_C3_P307A.indexOfChild(mod3_307A_radiogroup_C3_P307A.findViewById(mod3_307A_radiogroup_C3_P307A.getCheckedRadioButtonId())) +"";
        c3_p307a_o = mod3_307A_edittext_C3_P307A_O.getText().toString();
        /*
        idInformante = informanteSpinner.getSelectedItemPosition()+"";
        c3_p306 = c3_p306_RadioGroup.indexOfChild(c3_p306_RadioGroup.findViewById(c3_p306_RadioGroup.getCheckedRadioButtonId())) +"";
        c3_p306_o  = c3_p306_EditText.getText().toString();
        c3_p307_d  = c3_p307_TextViewDia.getText().toString();
        c3_p307_m  = c3_p307_TextViewMes.getText().toString();
        c3_p307_a  = c3_p307_TextViewAnio.getText().toString();
        if (c3_p308_estado_Spinner.getSelectedItemPosition() > 0) c3_p308_e  = getCodigoEstMun(c3_p308_estado_Spinner.getSelectedItem().toString());
        if (c3_p308_municipio_Spinner.getSelectedItemPosition() > 0) c3_p308_m  = getCodigoEstMun(c3_p308_municipio_Spinner.getSelectedItem().toString());
        c3_p308_e_seleccion = c3_p308_estado_Spinner.getSelectedItemPosition()+"";
        c3_p308_m_seleccion = c3_p308_municipio_Spinner.getSelectedItemPosition()+"";

        fecha_301 = c3_p301_a + c3_p301_m + c3_p301_d;
        fecha_303 = c3_p303_a + c3_p303_m + c3_p303_d;
        fecha_307 = c3_p307_a + c3_p307_m + c3_p307_d;

         */
    }

    public String getCodigoEstMun(String item){
        return item.substring(0,item.indexOf('.'));
    }

    @Override
    public void cargarDatos() {
        Data data = new Data(context);
        data.open();
        if (data.existeElemento(getNombreTabla(),idEncuestado)){
            Log.e("idEncuestado", "cargarDatos: "+idEncuestado );
            Modulo3 modulo3 = data.getModulo3(idEncuestado);
            ArrayList<String> informantes = data.getListaInformantesMayor12(modulo3.getIdHogar());
            UtilsMethodsInputs.loadSpinner(informantes,informanteSpinner,context);
            if(!modulo3.getIdInformante().equals("")) informanteSpinner.setSelection(obtener_posicion(modulo3.getIdInformante(),informanteSpinner));

//            c3_p301_d = modulo3.getC3_p301_d();
//            c3_p301_m = modulo3.getC3_p301_m();
//            c3_p301_a = modulo3.getC3_p301_a();
//            c3_p303_m = modulo3.getC3_p303_m();
//            if(c3_p303_m.length()==1) c3_p303_m = "0" + c3_p303_m;
//            c3_p303_a = modulo3.getC3_p303_a();
//            c3_p303_d = dia(c3_p301_a,c3_p303_m);

            //PROBLEMAS CON EL SPINNER
//            ArrayList<String> residentes = data.getListaSpinnerResidentesHogar(modulo3.getIdHogar());
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,residentes);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            informanteSpinner.setAdapter(adapter);
//            if(!modulo3.getIdInformante().equals(""))informanteSpinner.setSelection(Integer.parseInt(modulo3.getIdInformante()));

            if(modulo3.getC3_p306_1().equals("1")) c3_p306_1_CheckBox.setChecked(true);
            if(modulo3.getC3_p306_2().equals("1")) c3_p306_2_CheckBox.setChecked(true);
            if(modulo3.getC3_p306_3().equals("1")) c3_p306_3_CheckBox.setChecked(true);
            if(modulo3.getC3_p306_4().equals("1")) c3_p306_4_CheckBox.setChecked(true);
            if(modulo3.getC3_p306_5().equals("1")) c3_p306_5_CheckBox.setChecked(true);
            if(modulo3.getC3_p306_6().equals("1")) c3_p306_6_CheckBox.setChecked(true);
            if(modulo3.getC3_p306_7().equals("1")) c3_p306_7_CheckBox.setChecked(true);
            c3_p306_6_o_EditText.setText(modulo3.getC3_p306_o());

          /*  if(modulo3.getC3_p307_1().equals("1")) c3_p307_1_CheckBox.setChecked(true);
            if(modulo3.getC3_p307_2().equals("1")) c3_p307_2_CheckBox.setChecked(true);
            if(modulo3.getC3_p307_3().equals("1")) c3_p307_3_CheckBox.setChecked(true);
            if(modulo3.getC3_p307_4().equals("1")) c3_p307_4_CheckBox.setChecked(true);
            if(modulo3.getC3_p307_5().equals("1")) c3_p307_5_CheckBox.setChecked(true);
            if(modulo3.getC3_p307_6().equals("1")) c3_p307_6_CheckBox.setChecked(true);
            if(modulo3.getC3_p307_7().equals("1")) c3_p307_7_CheckBox.setChecked(true);
            if(modulo3.getC3_p307_8().equals("1")) c3_p307_8_CheckBox.setChecked(true);
            if(modulo3.getC3_p307_9().equals("1")) c3_p307_9_CheckBox.setChecked(true);
            if(modulo3.getC3_p307_10().equals("1")) c3_p307_10_CheckBox.setChecked(true);
            if(modulo3.getC3_p307_11().equals("1")) c3_p307_11_CheckBox.setChecked(true);
            if(modulo3.getC3_p307_12().equals("1")) c3_p307_12_CheckBox.setChecked(true);
            if(modulo3.getC3_p307_13().equals("1")) c3_p307_13_CheckBox.setChecked(true);*/


           // mod3_307_edittext_C3_P307_O12.setText(modulo3.getC3_p307_o12());

            if(!modulo3.getC3_p308().equals("-1") && !modulo3.getC3_p308().equals(""))((RadioButton)mod3_308_radiogroup_C3_P308.getChildAt(Integer.parseInt(modulo3.getC3_p308()))).setChecked(true);
            if(!modulo3.getC3_p307_1().equals("-1") && !modulo3.getC3_p307_1().equals(""))((RadioButton)mod3_307_radiogroup_C3_P307.getChildAt(Integer.parseInt(modulo3.getC3_p307_1()))).setChecked(true);
            mod3_307_edittext_C3_P307_O6.setText(modulo3.getC3_p307_o6());

            if(!modulo3.getC3_p307_a().equals("-1") && !modulo3.getC3_p307_a().equals(""))((RadioButton)mod3_307A_radiogroup_C3_P307A.getChildAt(Integer.parseInt(modulo3.getC3_p307_a()))).setChecked(true);
            mod3_307A_edittext_C3_P307A_O.setText(modulo3.getC3_p307a_o());
//            c3_p307_TextViewDia.setText(modulo3.getC3_p307_d());
//            c3_p307_TextViewMes.setText(modulo3.getC3_p307_m());
//            c3_p307_TextViewAnio.setText(modulo3.getC3_p307_a());
//            if (!modulo3.getC3_p308_e().equals(""))c3_p308_estado_Spinner.setSelection(Integer.parseInt(modulo3.getC3_p308_e()));
//            ArrayList<String> municipios = new ArrayList<>();
//            Log.e("tC3_p308_e", "cargarDatos: "+modulo3.getC3_p308_e() );
//            Log.e("getCodEs", "cargarDatos: "+data.getCodEstado(modulo3.getC3_p308_e() ));
//            if (!modulo3.getC3_p308_e().equals(""))  municipios = data.getMunicipios(data.getCodEstado(Integer.parseInt(modulo3.getC3_p308_e())+""));
//            Log.e("308_e", "cargarDatos: "+modulo3.getC3_p308_e() );
//            Log.e("308_m", "cargarDatos: "+modulo3.getC3_p308_m());
//            data.close();
//            cargarSpinerMunicipios(municipios);
//            if (!modulo3.getC3_p308_m().equals(""))c3_p308_municipio_Spinner.setSelection(Integer.parseInt(modulo3.getC3_p308_m()));
//
        }
        data.close();


    }

    @Override
    public void llenarVista() {
        /*
        Data data = new Data(context);
        data.open();
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p306,idEncuestado)) layoutp306.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p307,idEncuestado)) layoutp307.setVisibility(View.GONE);
        if(data.ocultarLayoutPregunta(SQLConstantes.layouts_p308,idEncuestado)) layoutp308.setVisibility(View.GONE);
        data.close();

         */
    }

    @Override
    public boolean validarDatos() {
        llenarVariables();

        if(informanteSpinner.getSelectedItemPosition() == 0) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}


        //PREGUNTA 306
        if (c3_p306_1.equals("0") && c3_p306_2.equals("0") && c3_p306_3.equals("0")
                && c3_p306_4.equals("0") && c3_p306_5.equals("0")&& c3_p306_6.equals("0")
                && c3_p306_7.equals("0")) {
            mostrarMensaje("PREGUNTA 306: DEBE MARCAR AL MENOS UNA OPCION");
                return false;
        }
        if (c3_p306_6.equals("1") ){
            if (c3_p306_6_o.trim().equals("")){
                mostrarMensaje("PREGUNTA 306: DEBE ESPECIFICAR LA RESPUESTA");return false;
            }
            if (c3_p306_6_o.length()<3){
                mostrarMensaje("ERROR  “P306. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
            }
        }



        //PREGUNTA 307
       /* if (c3_p307_1.equals("0") && c3_p307_2.equals("0") && c3_p307_3.equals("0")
                && c3_p307_4.equals("0") && c3_p307_5.equals("0")&& c3_p307_6.equals("0")
                && c3_p307_7.equals("0") && c3_p307_8.equals("0") && c3_p307_9.equals("0")
                && c3_p307_10.equals("0") && c3_p307_11.equals("0") && c3_p307_12.equals("0") && c3_p307_13.equals("0")) {
            mostrarMensaje("PREGUNTA 307: DEBE MARCAR AL MENOS UNA OPCION");
            return false;
        }

        if (c3_p307_6.equals("1")){
            if (c3_p307_o6.trim().equals("")){
                mostrarMensaje("PREGUNTA 307: DEBE ESPECIFICAR LA RESPUESTA 06");return false;
            }
            if (c3_p307_o6.length()<3){
                mostrarMensaje("ERROR  “P307-6. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
            }
        }



        if (c3_p307_12.equals("1")){
            if (c3_p307_o12.trim().equals("")){
                mostrarMensaje("PREGUNTA 307: DEBE ESPECIFICAR LA RESPUESTA 12");return false;
            }
            if (c3_p307_o12.length()<3){
                mostrarMensaje("ERROR  “P307-12. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
            }

        } */

        if (layoutp307.getVisibility() == View.VISIBLE){
            if (c3_p307_1.equals("-1")){
                mostrarMensaje("PREGUNTA 307: DEBE MARCAR UNA OPCIÓN");
                return false;
            }

            if (c3_p307_1.equals("8")){
                if (c3_p307_o6.trim().equals("")){
                    mostrarMensaje("PREGUNTA 307: DEBE ESPECIFICAR LA RESPUESTA ");return false;
                }
                if (c3_p307_o6.length()<3){
                    mostrarMensaje("ERROR  “P307. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }

            }

            if (c3_p307_1.equals("14")){
                if (c3_p307_o6.trim().equals("")){
                    mostrarMensaje("PREGUNTA 307: DEBE ESPECIFICAR LA RESPUESTA ");return false;
                }
                if (c3_p307_o6.length()<3){
                    mostrarMensaje("ERROR  “P307. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }

            }


        }else{
            c3_p307_1 = "";
            c3_p307_o6 = "";
        }

         ///PREGUNTA P307A

        if (layoutp307_a.getVisibility() == View.VISIBLE){
            if (c3_p307_a.equals("-1")){
                mostrarMensaje("PREGUNTA 307A: DEBE MARCAR UNA OPCIÓN");
                return false;
            }

            if (c3_p307_a.equals("7")){
                if (c3_p307a_o.trim().equals("")){
                    mostrarMensaje("PREGUNTA 307A: DEBE ESPECIFICAR LA RESPUESTA ");return false;
                }
                if (c3_p307a_o.length()<3){
                    mostrarMensaje("ERROR  “P307A. EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES”");return false;
                }

            }

            int suma = Integer.parseInt(c3_p306_1)+Integer.parseInt(c3_p306_2)+Integer.parseInt(c3_p306_3)+Integer.parseInt(c3_p306_4)+Integer.parseInt(c3_p306_5)+
                    Integer.parseInt(c3_p306_6);

            if (c3_p307_a.equals("2")&& suma > 0 ){
                mostrarMensaje("ERROR  “P307A. HA INDICADO QUE TIENE DOCUMENTOS DE SU PAIS EN LA P306”");
            }



        }else{
            c3_p307_a = "";
            c3_p307a_o = "";
        }

//        //--- PREGUNTA COGNITIVA  p212 != null && p212.equal(1)--//
//        if ( (p212.equals("1") && !(p212.equals(""))) && (c3_p307_1.equals("1") || c3_p307_2.equals("1") || c3_p307_3.equals("1")
//                || c3_p307_4.equals("1") || c3_p307_5.equals("1") || c3_p307_6.equals("1")
//                || c3_p307_7.equals("1") || c3_p307_8.equals("1") || c3_p307_9.equals("1")
//                || c3_p307_10.equals("1") || c3_p307_11.equals("1") || c3_p307_12.equals("1") || c3_p307_13.equals("1"))) {
//            mostrarMensaje("“SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
//        }

        //PREGUNTA 38
        if (layoutp308.getVisibility() == View.VISIBLE){
            if (c3_p308.equals("-1")){
                mostrarMensaje("PREGUNTA 308: DEBE MARCAR UNA OPCIÓN");
                return false;
            }
        }else{
            c3_p308 = "";
        }

        /*
        llenarVariables();
        if(idInformante.equals("0")) {mostrarMensaje("NÚMERO INFORMANTE: DEBE INDICAR INFORMANTE");return false;}
        if (c3_p306.equals("-1")){mostrarMensaje("PREGUNTA 306: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c3_p306.equals("5")){
            if (c3_p306_o.trim().equals("")){mostrarMensaje("PREGUNTA 306: DEBE ESPECIFICAR");return false;}
            if (c3_p306_o.trim().equals("CARTA ANDINA")){mostrarMensaje("PREGUNTA 306: EL ESPECIFICAR NO PUEDE SER (CARTA ANDINA)");return false;}
        }
        if (c3_p307_d.trim().equals("")){mostrarMensaje("PREGUNTA 307: DEBE AGREGAR FECHA");return false;}
        if(Integer.parseInt(fecha_307)<Integer.parseInt(fecha_301)){
            mostrarMensaje("PREGUNTA 307: DEBE SER MAYOR O IGUAL A LA FECHA DE NACIMIENTO ("+c3_p301_d+"/"+c3_p301_m+"/"+c3_p301_a+")");return false;
        }
        Log.e("fecha_307", "validarDatos: "+ fecha_307);
        Log.e("fecha_303", "validarDatos: "+ fecha_303);
        if(Integer.parseInt(fecha_307)>Integer.parseInt(fecha_303)){
            mostrarMensaje("PREGUNTA 307: DEBE SER MENOR O IGUAL A LA FECHA DE INGRESO ("+c3_p303_d+"/"+c3_p303_m+"/"+c3_p303_a+")");return false;
        }
        if (c3_p308_estado_Spinner.getSelectedItemPosition() == 0){mostrarMensaje("PREGUNTA 308: DEBE INDICAR ESTADO");return false;}
        if (c3_p308_municipio_Spinner.getSelectedItemPosition() == 0){mostrarMensaje("PREGUNTA 308: DEBE INDICAR MUNICIPIO");return false;}
        return true;

         */
        return true;
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablamodulo3;
    }

    public String obtener_Nresidente(Spinner spinner) {
        String cadena="";
        try{
            cadena = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
            cadena = cadena.substring(0,cadena.indexOf("-"));
        }catch (Exception e){}
        return cadena;
    }
    private int obtener_posicion(String Nresidente,Spinner spinner) {
        int posicion=0;
        try{
            for(int i=0; i<spinner.getCount();i++){
                if(Nresidente.equals(spinner.getItemAtPosition(i).toString().substring(0,spinner.getItemAtPosition(i).toString().indexOf("-")))){
                    posicion = i;
                }
            }
        }catch (Exception e){posicion = 0;}
        return posicion;
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

    private void configurarEditText(final EditText editText, final View view, int tipo,int longitud){
        switch (tipo){
            case 0:editText.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(longitud), new InputFilterSoloLetras()});break;
            case 1:editText.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(longitud)});break;
            case 2:editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(longitud)});
                editText.setTransformationMethod(new NumericKeyBoardTransformationMethod());break;
        }

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    ocultarTeclado(editText);
                    view.requestFocus();
                    return true;
                }
                return false;
            }
        });
    }

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    private void controlarEspecifiqueRadio(RadioGroup group, int checkedId, int opcionEsp, EditText editTextEspecifique) {
        int seleccionado = group.indexOfChild(group.findViewById(checkedId));
        if(seleccionado == opcionEsp){
            editTextEspecifique.setBackgroundResource(R.drawable.input_text_enabled);
            editTextEspecifique.setEnabled(true);
        }else{
            editTextEspecifique.setText("");
            editTextEspecifique.setBackgroundResource(R.drawable.input_text_disabled);
            editTextEspecifique.setEnabled(false);
        }
    }

    public boolean verificarCoberturaCapitulo(){
        /*
        Data data = new Data(context);
        data.open();
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p301p305,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp301p305,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p306p308,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp306p308,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p309,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp309,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p310p312,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp310p312,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p313p317,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp313p317,idEncuestado).equals("0")) return false;
        if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p318,idEncuestado).equals("1") &&
                data.getValor(SQLConstantes.tablacoberturafragments,SQLConstantes.cobertura_fragments_cp318,idEncuestado).equals("0")) return false;
        data.close();
        return true;

         */
        return true;
    }

    public String dia(String anio, String mes){
        String dia="";
        int _anio = Integer.parseInt(anio),_mes=Integer.parseInt(mes);
        switch (_mes){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12: dia = "31"; break;
            case 4: case 6: case 9: case 11: dia = "30"; break;
            case 2: dia = "28"; break;
        }
        if (((_anio % 4 == 0) && ((_anio % 100 != 0) || (_anio % 400 == 0))) && _mes==2){
            dia = "29";
        }
        return dia;
    }
    public void controlarChecked(CheckBox checkBox,final EditText editText){
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    editText.setBackgroundResource(R.drawable.input_text_enabled);
                    editText.setEnabled(true);
                }else{
                    editText.setText("");
                    editText.setBackgroundResource(R.drawable.input_text_disabled);
                    editText.setEnabled(false);
                }
            }
        });
    }
    public void validarAchurar(){
        llenarVariables();
        if(c3_p306_1.equals("1") || c3_p306_2.equals("1") || c3_p306_3.equals("1") || c3_p306_4.equals("1") || c3_p306_5.equals("1") || c3_p306_6.equals("1")){
            c3_p306_7_CheckBox.setChecked(false);c3_p306_7_CheckBox.setEnabled(false);
        }else {
            c3_p306_7_CheckBox.setEnabled(true);
        }
    }
}