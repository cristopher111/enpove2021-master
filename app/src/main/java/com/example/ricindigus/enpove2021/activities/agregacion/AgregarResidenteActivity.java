package com.example.ricindigus.enpove2021.activities.agregacion;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.CoberturaFragment;
import com.example.ricindigus.enpove2021.modelo.pojos.Hogar;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo3;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo4;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo5;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo6;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo7;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo8;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOFragment;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOLayout;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.InterfazOperaciones;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethods;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

import java.util.ArrayList;

public class
AgregarResidenteActivity extends AppCompatActivity implements InterfazOperaciones{

    TextInputEditText c2_p202_TextInputET,c2_p202_TextInputET_pat;
    Spinner c2_p205_a_sp, c2_p205_m_sp;
    Spinner c2_p203_Spinner,c2_p206_Spinner,c2_p209_or_Spinner,c2_p210_or_Spinner,c2_p211_Spinner;
    EditText c2_p207_Edt,c2_p211_1_o_Edt,observaciones_edt_cap2;
    RadioGroup c2_p204_RadioGroup,c2_p207_RadioGroup,c2_p208_RadioGroup,c2_p209_RadioGroup,c2_p209_p_RadioGroup,c2_p210_RadioGroup,c2_p210_p_RadioGroup,c2_p211_1_RadioGroup;
    Toolbar toolbar;
    FloatingActionButton fb_guardar;
    RadioButton rb_207_1,rb_207_2,rb_207_3,rb_207_4,rb_209_1,rb_209_2,rb_209_p_1,rb_209_p_2,rb_209_p_3,rb_209_p_4,rb_210_1,rb_210_2,rb_210_p_1,rb_210_p_2,rb_210_p_3,rb_210_p_4,rb_211_p_1,rb_211_p_2,rb_211_p_3,rb_211_p_4;
    CheckBox cb_p212;

    private String _id;
    private String id_hogar;
    private String id_vivienda;
    private String numero;
    private String idJefeHogar;
    private String id_informante = "";
    private String idResidente;

    private String c2_p202;
    private String c2_p202_pat;
    private String c2_p202_mat = "";
    private int c2_p203;
    private int c2_p204;
    private int c2_p205_a;
    private int c2_p205_m;
    private String edadJefeHogar="0";
    private String sexoJefeHogar="0";
    private String estadoCivilJefeHogar="0";
    private String estadoCivilConyuge="0";
    private String c2_p206;
    private int c2_p207;
    private String c2_p207_o;
    private int c2_p208;
    private String c2_p209;
    private String c2_p209_or="";
    private String c2_p209_p;
    private String c2_p209_pos;
    private String c2_p210;
    private String c2_p210_or="";
    private String c2_p210_p;
    private String c2_p210_pos;
    private String c2_p211="";
    private String c2_p212;
    private String c2_p211_nom="";
    private String c2_p211_pos;
    private int c2_p211_1;
    private String c2_p211_1_o;
    private int cant_p_s_h=0,cant_p_s_m=0;

    private String observaciones_cap2;

    boolean jefe_hogar=false,existe_conyuge=false;
    int cant_padres_suegros=0,edad_jefe_hogar=0;

    boolean editar=true;

    int sexo_residente=-1;
    String vista = "0";

    private LinearLayout linearLayout202,linearLayout203,linearLayout204,linearLayout205,linearLayout206,linearLayout207,linearLayout208,linearLayout209,linearLayout209_or,linearLayout209_p,linearLayout210,linearLayout210_or,linearLayout210_p,linearLayout211,linearLayout212,linearLayoutobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_residente);

        _id             = getIntent().getExtras().getString("idEncuestado");
        id_hogar        = getIntent().getExtras().getString("idHogar");
        id_vivienda     = getIntent().getExtras().getString("idVivienda");
        numero          = getIntent().getExtras().getString("numero");
        idJefeHogar     = getIntent().getExtras().getString("idJefeHogar");
        vista           = getIntent().getExtras().getString("vista");


        linearLayout202 = (LinearLayout) findViewById(R.id.layout_m2_p202);
        linearLayout203 = (LinearLayout) findViewById(R.id.layout_m2_p203);
        linearLayout204 = (LinearLayout) findViewById(R.id.layout_m2_p204);
        linearLayout205 = (LinearLayout) findViewById(R.id.layout_m2_p205);
        linearLayout206 = (LinearLayout) findViewById(R.id.layout_m2_p206);
        linearLayout207 = (LinearLayout) findViewById(R.id.layout_m2_p207);
        linearLayout208 = (LinearLayout) findViewById(R.id.layout_m2_p208);
        linearLayout209 = (LinearLayout) findViewById(R.id.layout_m2_p209);
        linearLayout209_or = (LinearLayout) findViewById(R.id.mod2_p209_orden_ly);
        linearLayout209_p = (LinearLayout) findViewById(R.id.mod2_p209_pais_ly);
        linearLayout210 = (LinearLayout) findViewById(R.id.layout_m2_p210);
        linearLayout210_or = (LinearLayout) findViewById(R.id.mod2_p210_orden_ly);
        linearLayout210_p = (LinearLayout) findViewById(R.id.mod2_p210_pais_ly);
        linearLayout211 = (LinearLayout) findViewById(R.id.layout_m2_p211);
        linearLayout212 = (LinearLayout) findViewById(R.id.layout_m2_p212);
        linearLayoutobs = (LinearLayout) findViewById(R.id.layout_m2_obs);

        fb_guardar = (FloatingActionButton) findViewById(R.id.mod2_fab_guardar);
        c2_p202_TextInputET = (TextInputEditText) findViewById(R.id.mod2_202_textinputedittext_C2_P202);
        c2_p202_TextInputET_pat = (TextInputEditText) findViewById(R.id.mod2_202_textinputedittext_C2_P202_paterno);
        c2_p203_Spinner = (Spinner) findViewById(R.id.mod2_203_spinner_C2_P203);
        c2_p204_RadioGroup = (RadioGroup) findViewById(R.id.mod2_204_radiogroup_C2_P204);
        c2_p205_a_sp = (Spinner) findViewById(R.id.mod2_205a_spinner_C2_P205a);
        c2_p205_m_sp = (Spinner) findViewById(R.id.mod2_205b_spinner_C2_P205b);
        c2_p206_Spinner = (Spinner) findViewById(R.id.mod2_206_spinner_C2_P206);
        c2_p207_RadioGroup = (RadioGroup) findViewById(R.id.mod2_207_radiogroup_C2_P207);
        rb_207_1 = (RadioButton) findViewById(R.id.mo2_207_rb1);
        rb_207_2 = (RadioButton) findViewById(R.id.mo2_207_rb2);
        rb_207_3 = (RadioButton) findViewById(R.id.mo2_207_rb3);
        rb_207_4 = (RadioButton) findViewById(R.id.mo2_207_rb4);
        rb_209_1 = (RadioButton) findViewById(R.id.rb_p209_1);
        rb_209_2 = (RadioButton) findViewById(R.id.rb_p209_2);
        rb_209_p_1 = (RadioButton) findViewById(R.id.rb_p209_p_1);
        rb_209_p_2 = (RadioButton) findViewById(R.id.rb_p209_p_2);
        rb_209_p_3 = (RadioButton) findViewById(R.id.rb_p209_p_3);
        rb_209_p_4 = (RadioButton) findViewById(R.id.rb_p209_p_4);
        rb_210_1 = (RadioButton) findViewById(R.id.rb_p210_1);
        rb_210_2 = (RadioButton) findViewById(R.id.rb_p210_2);
        rb_210_p_1 = (RadioButton) findViewById(R.id.rb_p210_p_1);
        rb_210_p_2 = (RadioButton) findViewById(R.id.rb_p210_p_2);
        rb_210_p_3 = (RadioButton) findViewById(R.id.rb_p210_p_3);
        rb_210_p_4 = (RadioButton) findViewById(R.id.rb_p210_p_4);

        c2_p207_Edt = (EditText) findViewById(R.id.mod2_207_edittext_C2_P207_O);
        c2_p208_RadioGroup = (RadioGroup) findViewById(R.id.mod2_208_radiogroup_C2_P208);
        c2_p209_RadioGroup = (RadioGroup) findViewById(R.id.mod2_p209_radiogroup_C2_P209);
        c2_p209_or_Spinner = (Spinner) findViewById(R.id.mod2_p209_spinner_C2_P209_orden);
        c2_p209_p_RadioGroup = (RadioGroup) findViewById(R.id.mod2_p209_radiogroup_C2_P209_pais);
        c2_p210_RadioGroup = (RadioGroup) findViewById(R.id.mod2_p210_radiogroup_C2_P210);
        c2_p210_or_Spinner = (Spinner) findViewById(R.id.mod2_p210_spinner_C2_P210_orden);
        c2_p210_p_RadioGroup = (RadioGroup) findViewById(R.id.mod2_p210_radiogroup_C2_P210_pais);
        c2_p211_Spinner = (Spinner) findViewById(R.id.mod2_211_spinner_C2_P211);
        c2_p211_1_RadioGroup = (RadioGroup) findViewById(R.id.mod2_211_radiogroup_C2_P211_1);
        rb_211_p_1 = (RadioButton) findViewById(R.id.mod2_211_rb1);
        rb_211_p_2 = (RadioButton) findViewById(R.id.mod2_211_rb2);
        rb_211_p_3 = (RadioButton) findViewById(R.id.mod2_211_rb3);
        rb_211_p_4 = (RadioButton) findViewById(R.id.mod2_211_rb4);
        c2_p211_1_o_Edt = (EditText) findViewById(R.id.mod2_211_edittext_C2_P211_O);

        observaciones_edt_cap2 = (EditText) findViewById(R.id.edtObservaciones_cap2);



        cb_p212 = (CheckBox) findViewById(R.id.mod2_212_checkbox_C2_P212);
        toolbar = (Toolbar) findViewById(R.id.agregar_residente_tb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("AGREGAR RESIDENTE");
        getSupportActionBar().setSubtitle("RESIDENTE Nº " + numero);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        UtilsMethodsInputs.setupEditText(c2_p202_TextInputET,getApplicationContext(),0,50);
        UtilsMethodsInputs.setupEditText(c2_p202_TextInputET_pat,getApplicationContext(),0,80);
        //configurarEditText(c2_p205_a_sp,linearLayout205,2,2);
        //configurarEditText(c2_p205_m_sp,linearLayout205,2,2);
        UtilsMethodsInputs.setupEditText(c2_p207_Edt,getApplicationContext(),2,9);
        UtilsMethodsInputs.setupEditText(observaciones_edt_cap2,getApplicationContext(),4,2000);

        linearLayout204.setVisibility(View.GONE);
        linearLayout205.setVisibility(View.GONE);
        linearLayout206.setVisibility(View.GONE);
        linearLayout207.setVisibility(View.GONE);
        linearLayout208.setVisibility(View.GONE);
        linearLayout209.setVisibility(View.GONE);
        //linearLayout209_or.setVisibility(View.GONE);
        linearLayout209_p.setVisibility(View.GONE);
        linearLayout210.setVisibility(View.GONE);
        //linearLayout210_or.setVisibility(View.GONE);
        linearLayout210_p.setVisibility(View.GONE);
        linearLayout211.setVisibility(View.GONE);
        linearLayout212.setVisibility(View.GONE);
        linearLayoutobs.setVisibility(View.GONE);

        c2_p205_a_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >0) {
                    c2_p205_m_sp.setEnabled(false);
                } else {
                    c2_p205_m_sp.setEnabled(true);
                }

                if (i >= 12) {
                    setUnlockingP206();
                } else {
                    setBlockingP206();
                }

                if (i >= 14) {
                    setUnlockingP207();

                } else {
                    setBlockingP207();
                }

                if (i> 17) {
                    setBlockingP209();
                    setBlockingP210();
                    setBlockingP211();
                    linearLayout209_or.setVisibility(View.VISIBLE);
                    linearLayout209_p.setVisibility(View.GONE);
                    linearLayout210_or.setVisibility(View.VISIBLE);
                    linearLayout210_p.setVisibility(View.GONE);
                }else{
                    setUnlockingP209();
                    setUnlockingP210();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        c2_p205_m_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    c2_p205_a_sp.setEnabled(true);
                }else {
                    c2_p205_a_sp.setEnabled(false);
                    setBlockingP206();
                    setBlockingP207();
                    if(i>12){
                        c2_p205_m_sp.setSelection(1);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

//        c2_p205_a_sp.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (editable.toString().equals("")){
//                    c2_p205_m_sp.setEnabled(true);
//                }else{
//
//                    if (Integer.parseInt(editable.toString()) < 1){
//                        c2_p205_a_sp.setText("");
//                    }else {
//                        c2_p205_m_sp.setEnabled(false);
//                        if (Integer.parseInt(editable.toString()) >= 12) {
//                            setUnlockingP206();
//                        } else {
//                            setBlockingP206();
//                        }
//
//                        if (Integer.parseInt(editable.toString()) >= 14) {
//                            setUnlockingP207();
//
//                        } else {
//                            setBlockingP207();
//                        }
//
//                        if (Integer.parseInt(editable.toString()) > 17) {
//                            setBlockingP209();
//                            setBlockingP210();
//                            setBlockingP211();
//                            linearLayout209_or.setVisibility(View.VISIBLE);
//                            linearLayout209_p.setVisibility(View.GONE);
//                            linearLayout210_or.setVisibility(View.VISIBLE);
//                            linearLayout210_p.setVisibility(View.GONE);
//                        }else{
//                            setUnlockingP209();
//                            setUnlockingP210();
//                        }
//                    }
//                }
//            }
//        });
//
//        c2_p205_m_sp.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (editable.toString().equals("")){
//                    c2_p205_a_sp.setEnabled(true);
//                }else {
//                    c2_p205_a_sp.setEnabled(false);
//                    setBlockingP206();
//                    setBlockingP207();
//                    if(Integer.parseInt(editable.toString())>11){
//                        c2_p205_m_sp.setText("1");
//                    }
//                }
//            }
//        });

        c2_p207_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,1,c2_p207_Edt);
            }
        });

        c2_p208_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                if(seleccionado == 1 && c2_p205_a_sp.getSelectedItemPosition()>0){
                    if(c2_p205_a_sp.getSelectedItemPosition()<18){
                        setUnlockingP209();
                        setUnlockingP210();
                        setUnlockingP211();
                    }else {
                        setBlockingP209();
                        setBlockingP210();
                        setBlockingP211();
                        linearLayout209_or.setVisibility(View.VISIBLE);
                        linearLayout209_p.setVisibility(View.GONE);
                        linearLayout210_or.setVisibility(View.VISIBLE);
                        linearLayout210_p.setVisibility(View.GONE);
                    }
                }else if(seleccionado == 1 && c2_p205_m_sp.getSelectedItemPosition()>0){
                    //estuvo en 12 , el anterior tambien sume 1
                    if(c2_p205_m_sp.getSelectedItemPosition()<13){
                        setUnlockingP209();
                        setUnlockingP210();
                        setUnlockingP211();
                    }else {
                        setBlockingP209();
                        setBlockingP210();
                        setBlockingP211();
                        linearLayout209_or.setVisibility(View.VISIBLE);
                        linearLayout209_p.setVisibility(View.GONE);
                        linearLayout210_or.setVisibility(View.VISIBLE);
                        linearLayout210_p.setVisibility(View.GONE);
                    }
                } else{
                    setBlockingP209();
                    setBlockingP210();
                    setBlockingP211();
                    linearLayout209_or.setVisibility(View.VISIBLE);
                    linearLayout209_p.setVisibility(View.GONE);
                    linearLayout210_or.setVisibility(View.VISIBLE);
                    linearLayout210_p.setVisibility(View.GONE);
                }
            }
        });

        c2_p209_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                switch (seleccionado){
                    case 1:
                        linearLayout209_or.setVisibility(View.VISIBLE);
                        linearLayout209_p.setVisibility(View.GONE);
                        c2_p209_p_RadioGroup.clearCheck();
                        setBlockingP211();
                        break;
                    case 2:
                        linearLayout209_or.setVisibility(View.GONE);
                        linearLayout209_p.setVisibility(View.VISIBLE);
                        c2_p209_or_Spinner.setSelection(0);
                        String estado = c2_p210_RadioGroup.indexOfChild(c2_p210_RadioGroup.findViewById(c2_p210_RadioGroup.getCheckedRadioButtonId()))+"";
                        if(!estado.equals("-1")){
                            if(estado.equals("2")){
                                setUnlockingP211();
                            }
                        }
                        break;
                    default:
                        setBlockingP211();
                        break;
                }
            }
        });

        c2_p210_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                switch (seleccionado){
                    case 1:
                        linearLayout210_or.setVisibility(View.VISIBLE);
                        linearLayout210_p.setVisibility(View.GONE);
                        c2_p210_p_RadioGroup.clearCheck();
                        setBlockingP211();
                        break;
                    case 2:
                        linearLayout210_or.setVisibility(View.GONE);
                        linearLayout210_p.setVisibility(View.VISIBLE);
                        c2_p210_or_Spinner.setSelection(0);
                        String estado = c2_p209_RadioGroup.indexOfChild(c2_p209_RadioGroup.findViewById(c2_p209_RadioGroup.getCheckedRadioButtonId()))+"";
                        if(!estado.equals("-1")){
                            if(estado.equals("2")){
                                setUnlockingP211();
                            }
                        }
                        break;
                    default:
                        setBlockingP211();
                        break;
                }
            }
        });

        c2_p211_1_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,4,c2_p211_1_o_Edt);
            }
        });

        UtilsMethodsInputs.setupEditText(c2_p211_1_o_Edt,getApplicationContext(),0,50);

//        cb_p212.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (linearLayout212.getVisibility() == View.VISIBLE && isChecked==true){
//                    mostrarMensaje("SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
//                }
//            }
//        });

        fb_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llenarVariables();
                if (validarDatos()){
                    guardarDatos();
                    Data data = new Data(AgregarResidenteActivity.this);
                    data.open();

                    String val = data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p401,_id);
                    String frag = data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p501p505,_id);
                    ArrayList<Residente> residentes = data.getAllResidentesHogar(id_hogar);
                    for (Residente r: residentes){
                        POJOLayout pojoLayout = data.getLayouts(r.get_id());
                        POJOFragment pojoFragment = data.getFragmentsLayouts(r.get_id());
                    }
                    data.close();
                    finish();
                }
            }
        });

//        Log.e("Julio1:",""+DAOUtils.getJefeVenezolanoHogar(id_hogar,this).get_id());
//        Log.e("Julio2:",""+DAOUtils.getJefeVenezolanoHogar(id_hogar,this).getC2_p205_a());
//        Log.e("Julio3:",""+DAOUtils.getJefeVenezolanoHogar(id_hogar,this).getC2_p208());


        cargarDatos();
        DAOUtils.getJefeVenezolanoHogar(id_hogar,this);
        //showVista(vista);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_guardar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_guardar:
                llenarVariables();
                if (validarDatos()){
                    guardarDatos();
                    Data data = new Data(AgregarResidenteActivity.this);
                    data.open();

                    String val = data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p401,_id);
                    String frag = data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p501p505,_id);
                    ArrayList<Residente> residentes = data.getAllResidentesHogar(id_hogar);
                    for (Residente r: residentes){
                        POJOLayout pojoLayout = data.getLayouts(r.get_id());
                        POJOFragment pojoFragment = data.getFragmentsLayouts(r.get_id());
                    }
                    data.close();
                    finish();
                }

//                llenarVariables();
//                if (validarDatos()){
//                    guardarDatos();
//                    Data data = new Data(AgregarResidenteActivity.this);
//                    data.open();
//
//                    String val = data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p401,_id);
//                    String frag = data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p501p505,_id);
//                    ArrayList<Residente> residentes = data.getAllResidentesHogar(id_hogar);
//                    for (Residente r: residentes){
//                        POJOLayout pojoLayout = data.getLayouts(r.get_id());
//                        POJOFragment pojoFragment = data.getFragmentsLayouts(r.get_id());
//                    }
//                    data.close();
//                    finish();
//                }
                return true;
            case R.id.action_anterior:
                linearLayout202.setVisibility(View.VISIBLE);
                linearLayout203.setVisibility(View.VISIBLE);
                linearLayout204.setVisibility(View.GONE);
                linearLayout205.setVisibility(View.GONE);
                linearLayout206.setVisibility(View.GONE);
                linearLayout207.setVisibility(View.GONE);
                linearLayout208.setVisibility(View.GONE);
                linearLayout209.setVisibility(View.GONE);
                linearLayout210.setVisibility(View.GONE);
                linearLayout211.setVisibility(View.GONE);
                linearLayout212.setVisibility(View.GONE);
                linearLayoutobs.setVisibility(View.GONE);
                return true;
            case R.id.action_siguiente:
                if (!getExisteResidente(_id)){
                    Toast.makeText(this,"DEBE COMPLETAR P202,P203 Y GUARDAR PARA CONTINUAR",Toast.LENGTH_SHORT).show();
                }else if((getExisteResidente(_id) && !getJefeHogar().getC2_p204().equals("")) || (_id.equals(getJefeHogar().get_id()))){
                    linearLayout202.setVisibility(View.GONE);
                    linearLayout203.setVisibility(View.GONE);
                    linearLayout204.setVisibility(View.VISIBLE);
                    linearLayout205.setVisibility(View.VISIBLE);
                    linearLayout206.setVisibility(View.VISIBLE);
                    linearLayout207.setVisibility(View.VISIBLE);
                    linearLayout208.setVisibility(View.VISIBLE);
                    linearLayout209.setVisibility(View.VISIBLE);
                    linearLayout210.setVisibility(View.VISIBLE);
                    linearLayout211.setVisibility(View.VISIBLE);
                    linearLayoutobs.setVisibility(View.VISIBLE);
                    //linearLayout212.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(this,"PRIMERO DEBE INGRESAR LOS DATOS DEL JEFE DE HOGAR",Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablaresidentes;
    }

    @Override
    public void llenarVariables(){
        //id_informante = "";
        c2_p202 = c2_p202_TextInputET.getText().toString();
        c2_p202_pat = c2_p202_TextInputET_pat.getText().toString();
        c2_p203 = c2_p203_Spinner.getSelectedItemPosition();
        c2_p204 = c2_p204_RadioGroup.indexOfChild(c2_p204_RadioGroup.findViewById(c2_p204_RadioGroup.getCheckedRadioButtonId()));
        c2_p205_a = c2_p205_a_sp.getSelectedItemPosition();
        c2_p205_m = DAOUtils.convertNumberNegativo(c2_p205_m_sp.getSelectedItem()+"");
        c2_p206 = c2_p206_Spinner.getSelectedItemPosition() + "";
        c2_p207 = c2_p207_RadioGroup.indexOfChild(c2_p207_RadioGroup.findViewById(c2_p207_RadioGroup.getCheckedRadioButtonId()));
        c2_p207_o = c2_p207_Edt.getText().toString();
        c2_p208 = c2_p208_RadioGroup.indexOfChild(c2_p208_RadioGroup.findViewById(c2_p208_RadioGroup.getCheckedRadioButtonId()));
        c2_p209 = c2_p209_RadioGroup.indexOfChild(c2_p209_RadioGroup.findViewById(c2_p209_RadioGroup.getCheckedRadioButtonId()))+"";
        c2_p209_or = c2_p209_or_Spinner.getSelectedItemPosition()+"";
        c2_p209_p = c2_p209_p_RadioGroup.indexOfChild(c2_p209_p_RadioGroup.findViewById(c2_p209_p_RadioGroup.getCheckedRadioButtonId()))+"";
        c2_p209_pos = c2_p209_or_Spinner.getSelectedItem().toString()+"";
        c2_p210 = c2_p210_RadioGroup.indexOfChild(c2_p210_RadioGroup.findViewById(c2_p210_RadioGroup.getCheckedRadioButtonId()))+"";
        c2_p210_or = c2_p210_or_Spinner.getSelectedItemPosition()+"";
        c2_p210_p = c2_p210_p_RadioGroup.indexOfChild(c2_p210_p_RadioGroup.findViewById(c2_p210_p_RadioGroup.getCheckedRadioButtonId()))+"";
        c2_p210_pos = c2_p210_or_Spinner.getSelectedItem().toString()+"";
        c2_p211 = c2_p211_Spinner.getSelectedItemPosition()+"";
        //c2_p211_nom = c2_p211_Spinner.getSelectedItem().toString()+"";
        c2_p211_pos = c2_p211_Spinner.getSelectedItem().toString()+"";
        c2_p211_1 = c2_p211_1_RadioGroup.indexOfChild(c2_p211_1_RadioGroup.findViewById(c2_p211_1_RadioGroup.getCheckedRadioButtonId()));
        c2_p211_1_o = c2_p211_1_o_Edt.getText().toString();

        if(cb_p212.isChecked()) c2_p212 = "1"; else c2_p212 = "0";

        observaciones_cap2 = observaciones_edt_cap2.getText().toString().trim();
    }

    public boolean validarDatos() {
        llenarVariables();
        if (linearLayout202.getVisibility() == View.VISIBLE && linearLayout203.getVisibility() == View.VISIBLE) {
            //P202
            if (c2_p202.trim().equals("")) {
                mostrarMensaje("PREGUNTA 202: DEBE INDICAR EL NOMBRE");
                return false;
            }
            if (!c2_p202.trim().equals("") && c2_p202.trim().length()<3) {
                mostrarMensaje("PREGUNTA 202: EL NOMBRE NO PUEDE SER MENOR A 3 DIGITOS");
                return false;
            }
            if (c2_p202_pat.trim().equals("")) {
                mostrarMensaje("PREGUNTA 202: DEBE INDICAR APELLIDO PATERNO");
                return false;
            }
            if (!c2_p202_pat.trim().equals("") && c2_p202_pat.trim().length()<3) {
                mostrarMensaje("PREGUNTA 202: LOS APELLIDOS NO PUEDEN SER MENOR DE 3 DIGITOS");
                return false;
            }
            //P203
            if (c2_p203 == 0) {
                mostrarMensaje("PREGUNTA 203: DEBE INDICAR EL PARENTESCO");
                return false;
            }
            if (c2_p203 == 1 && !jefe_hogar) {
                mostrarMensaje("PREGUNTA 203: EXISTE MÁS DE UN JEFE DE HOGAR");
                return false;
            }

//            if (c2_p203 == 2 && existe_conyuge && DAOUtils.getRedidente(_id,getApplicationContext())==null) {
            if (c2_p203 == 2 && getPareja().getC2_p203().equals("2") && !getPareja().get_id().equals(_id) ) {
                mostrarMensaje("PREGUNTA 203: EXISTE MÁS DE UN(A) ESPOSA(O) /COMPAÑERO(A) EN EL HOGAR");
                return false;
            }
        }

        if (linearLayout204.getVisibility() == View.VISIBLE) {
            //P204
            if (c2_p204 == -1) {
                mostrarMensaje("PREGUNTA 204: DEBE INDICAR EL SEXO");
                return false;
            }
           /* if (Integer.parseInt(numero) > 1) {
                if (c2_p204 == Integer.parseInt(sexoJefeHogar) && c2_p203 == 2) {
                    mostrarMensaje("PREGUNTA 204: EL SEXO DEL JEFE(A) DEL HOGAR Y ESPOSO(A) /COMPAÑERO(A) DEBEN SER DIFERENTES");
                    return false;
                }
            }*/
            //P205
            if (c2_p205_a==0 && c2_p205_m== -1) {
                mostrarMensaje("PREGUNTA 205: SOLO DEBE HABER INFORMACIÓN DE LA EDAD EN AÑOS O MESES");
                return false;
            }
            int p205_a_edad = 0;
            if (c2_p205_a>0) p205_a_edad = c2_p205_a;
            //////MODIFICADO DE 14 A P205 >=12
            if (p205_a_edad < 11 && c2_p203 == 1) {
                mostrarMensaje("PREGUNTA 205: LA EDAD DEL JEFE DEL HOGAR DEBE SER MAYOR O IGUAL A 14 AÑOS");
                return false;
            }

            if (p205_a_edad < 12 && c2_p203 == 2) {
                mostrarMensaje("PREGUNTA 205: EL ESPOSO/A O COMPAÑERO/A  DEBE SER MAYOR O IGUAL A 12 AÑOS");
                return false;
            }
            if (p205_a_edad >= 90 && c2_p203 == 3) {
                mostrarMensaje("PREGUNTA 205: LAS EDADES NO CORRESPONDEN SEGÚN LA RELACIÓN DE PARENTESCO CON EL JEFE DEL HOGAR");
                return false;
            }
            if ((p205_a_edad < 12 || p205_a_edad >= 90) && c2_p203 == 4) {
                mostrarMensaje("PREGUNTA 205: LAS EDADES NO CORRESPONDEN SEGÚN LA RELACIÓN DE PARENTESCO CON EL JEFE DEL HOGAR");
                return false;
            }
            if (p205_a_edad < 25 && c2_p203 == 6) {
                mostrarMensaje("PREGUNTA 205: LAS EDADES NO CORRESPONDEN SEGÚN LA RELACIÓN DE PARENTESCO CON EL JEFE DEL HOGAR");
                return false;
            }
            if (p205_a_edad < 6 && c2_p203 == 9) {
                mostrarMensaje("PREGUNTA 205: LAS EDADES NO CORRESPONDEN SEGÚN LA RELACIÓN DE PARENTESCO CON EL JEFE DEL HOGAR");
                return false;
            }
            if (p205_a_edad < 12 && c2_p203 == 10) {
                mostrarMensaje("PREGUNTA 205: LAS EDADES NO CORRESPONDEN SEGÚN LA RELACIÓN DE PARENTESCO CON EL JEFE DEL HOGAR");
                return false;
            }
            if (c2_p205_a>0&& c2_p203 != 0) {
                switch (c2_p203) {
                    case 2:
                        if (diferenciaEdad(edad_jefe_hogar, c2_p205_a, -80, 80)) {
                            mostrarMensaje("PREGUNTA 205: LA DIFERENCIA DE EDADES ENTRE EL JEFE/A DEL HOGAR CON ESPOSO/A  NO CORRESPONDEN");
                            return false;
                        }
                        break;
                    case 3:
                        if (diferenciaEdad(edad_jefe_hogar, c2_p205_a, 12, 65)) {
                            mostrarMensaje2("PREGUNTA 205: LA DIFERENCIA DE EDADES ENTRE EL JEFE/A DEL HOGAR CON HIJO/A  NO CORRESPONDEN");
                            return false;
                        }
                        break;
                    case 4:
                        if (diferenciaEdad(edad_jefe_hogar, c2_p205_a, -12, 65)) {
                            mostrarMensaje("PREGUNTA 205: LA DIFERENCIA DE EDADES ENTRE EL JEFE/A DEL HOGAR CON YERNO/NUERA NO CORRESPONDEN");
                            return false;
                        }
                        break;
                    case 5:
                        if (diferenciaEdad(edad_jefe_hogar, c2_p205_a, 24, 100)) {
                            mostrarMensaje("PREGUNTA 205: LA DIFERENCIA DE EDADES ENTRE EL JEFE/A DEL HOGAR CON NIETO/A  NO CORRESPONDEN");
                            return false;
                        }
                        break;
                    case 6:
                        if (diferenciaEdad(edad_jefe_hogar, c2_p205_a, -60, -12)) {
                            mostrarMensaje2("PREGUNTA 205: LA DIFERENCIA DE EDADES ENTRE EL JEFE/A DEL HOGAR CON PADRES/SUEGROS  NO CORRESPONDEN.");
                            return false;
                        }
                        break;
                }
            }

            //P206
            if ( c2_p206_Spinner.isEnabled() == true) {
                if (c2_p206.equals("0")) {
                    mostrarMensaje("PREGUNTA 206: DEBE INDICAR EL ESTADO CIVIL");
                    return false;
                }
                if((c2_p203==2 && !c2_p206.equals(getJefeHogar().getC2_p206()))||(c2_p203==1 && !c2_p206.equals(getPareja().getC2_p206()) && !getPareja().getC2_p206().equals("0"))){
                    mostrarMensaje("PREGUNTA 206: EL ESTADO CIVIL DEL JEFE(A) DEL HOGAR Y ESPOSO(A) O COMPAÑERO(A) SON DIFERENTES:");
                    return false;}
                if (Integer.parseInt(c2_p206) > 2 && c2_p203 == 2 && !jefe_hogar) {
                    mostrarMensaje("PREGUNTA 206: NO CORRESPONDE EL ESTADO CIVIL ESPOSA(O) /COMPAÑERO(A)");
                    return false;
                }
                if (c2_p206.equals("6") && c2_p203 == 4 && !jefe_hogar) {
                    mostrarMensaje("PREGUNTA 206: NO CORRESPONDE EL ESTADO CIVIL DEL YERNO/NUERA");
                    return false;
                }
            } else {
                c2_p206 = "0";
            }
            //P207
            if (rb_207_1.isEnabled() ==true) {
                if (c2_p207 == -1) {
                    mostrarMensaje("PREGUNTA 207: DEBE COMPLETAR LA PREGUNTA");
                    return false;
                } else {
                    if (c2_p207 == 1) {
                        if (c2_p207_o.trim().equals("")) {
                            mostrarMensaje("PREGUNTA 207: DEBE INGRESAR TELEFONO");
                            return false;
                        }
                        if (!c2_p207_o.trim().equals("") && c2_p207_o.length() < 9) {
                            mostrarMensaje("PREGUNTA 207: EL NÚMERO DE TELEFONO NO DEBE TENER MENOS DE 9 DIGITOS");
                            return false;
                        }
                        if (!c2_p207_o.substring(0,1).equals("9")) {
                            mostrarMensaje("PREGUNTA 207: EL NUMERO DE CELULAR DEBE EMPEZAR CON 9");
                            return false;
                        }
                    }
                }
            }
            //P208
            if (c2_p208 == -1) {
                mostrarMensaje("PREGUNTA 208: DEBE INDICAR SI LLEGÓ DE VENEZUELA");
                return false;
            }
            //P209
            if (rb_209_1.isEnabled() ==true || rb_210_1.isEnabled() ==true || rb_211_p_1.isEnabled() ==true) {
                if (c2_p209.equals("-1")) {
                    mostrarMensaje("PREGUNTA 209: DEBE MARCAR UNA OPCIÓN");
                    return false;
                } else if (c2_p209.equals("1") && c2_p209_or.equals("0")) {
                    mostrarMensaje("PREGUNTA 209: DEBE INGRESAR N° DE ORDEN DE RESIDENTE");
                    return false;
                } else if (c2_p209.equals("2") && c2_p209_p.equals("-1")) {
                    mostrarMensaje("PREGUNTA 209: DEBE ESPECIFICAR PAIS");
                    return false;
                } else if (c2_p210.equals("-1")) {
                    mostrarMensaje("PREGUNTA 210: DEBE MARCAR UNA OPCIÓN");
                    return false;
                } else if (c2_p210.equals("1") && c2_p210_or.equals("0")) {
                    mostrarMensaje("PREGUNTA 210: DEBE INGRESAR N° DE ORDEN DE RESIDENTE");
                    return false;
                } else if (c2_p210.equals("2") && c2_p210_p.equals("-1")) {
                    mostrarMensaje("PREGUNTA 210: DEBE ESPECIFICAR PAIS");
                    return false;
                } else if (c2_p209.equals("2") && c2_p210.equals("2") && c2_p211.equals("0")) {
                    mostrarMensaje("PREGUNTA 211: DEBE SELECCIONAR UN RESPONSABLE");
                    return false;
                } else if (c2_p209.equals("2") && c2_p210.equals("2") && c2_p211_1==-1) {
                    mostrarMensaje("PREGUNTA 211: DEBE SELECCIONAR UNA RELACION");
                    return false;
                } else if (c2_p209.equals("2") && c2_p210.equals("2") && c2_p211_1==4 && c2_p211_1_o.trim().equals("")) {
                    mostrarMensaje("PREGUNTA 211-4: DEBE ESPECIFICAR");
                    return false;
                }else if (c2_p209.equals("2") && c2_p210.equals("2") && !c2_p211_1_o.trim().equals("") && c2_p211_1_o.length()<3) {
                    mostrarMensaje("PREGUNTA 211: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");
                    return false;
                }else if(c2_p209.equals("2") && c2_p210.equals("2") && c2_p211_1==2 && getValidateRegla0040D(c2_p211_Spinner.getSelectedItem().toString(),c2_p205_a+"")){
                    mostrarMensaje("PREGUNTA 211: NO COINCIDE LA EDAD DEL MENOR DE EDAD Y LA DE SU ABUELO");
                    return false;
                }else if(c2_p209.equals("2") && c2_p210.equals("2") && c2_p211_1<4 && getValidateRegla0040F(c2_p211_Spinner.getSelectedItem().toString())){
                    mostrarMensaje("PREGUNTA 211: SI LA PERSONA RESPONSABLE DEL CUIDADO SOY YO MISMO, DEBE ELEGIR PARENTESCO OTRO (ESPECIFICAR)");
                    return false;
                }else if(c2_p209.equals("1") && getValidateEdadMayorMenor(c2_p209_or_Spinner.getSelectedItem().toString(),c2_p205_a+"")){
                    mostrarMensaje("PREGUNTA 209: LA DIFERENCIA DE EDAD ENTRE SU PADRE Y EL ES MENOR A 12 AÑOS");
                    return false;
                }else if(c2_p210.equals("1") && getValidateEdadMayorMenor(c2_p210_or_Spinner.getSelectedItem().toString(),c2_p205_a+"")){
                    mostrarMensaje("PREGUNTA 210: LA DIFERENCIA DE EDAD ENTRE SU MADRE Y EL ES MENOR A 12 AÑOS");
                    return false;
                }else if(c2_p209.equals("2") && c2_p210.equals("2") && getValidateRegla0040E(c2_p211_Spinner.getSelectedItem().toString(),c2_p205_a+"")){
                    mostrarMensaje2("PREGUNTA 211: LA EDAD DEL RESPONSABLE DE SU CUIDADO ES MENOR");
                    return false;
                }
            }

            if (c2_p203 == 6 && cant_padres_suegros >= 4) {
                mostrarMensaje2("PREGUNTA 203: EXISTE MÁS DE DOS PADRES Y DOS SUEGROS EN EL HOGAR");
                return false;
            }
//            if (c2_p206_Spinner.isEnabled() ==true) {
//                if (c2_p206 != estadoCivilJefeHogar && c2_p203 == 2 && !jefe_hogar) {
//                    mostrarMensaje2x("PREGUNTA 206: EL ESTADO CIVIL DEL JEFE(A) DEL HOGAR Y ESPOSO(A) O COMPAÑERO(A) SON DIFERENTES");
//                    return false;
//                }
//            }
        }

        return true;
    }

    @Override
    public void cargarDatos() {
        editar=false;
        int edad_p=0;
        Data data = new Data(this);
        data.open();
        jefe_hogar = false;
        existe_conyuge = false;
        cant_padres_suegros = 0;
        edad_jefe_hogar = 0;

        ArrayList<String> responsables1 = data.getListaSpinnerResidentesHogarXSexo(id_hogar,"1");
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(AgregarResidenteActivity.this, android.R.layout.simple_spinner_dropdown_item,responsables1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayList<String> responsables2 = data.getListaSpinnerResidentesHogarXSexo(id_hogar,"2");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(AgregarResidenteActivity.this, android.R.layout.simple_spinner_dropdown_item,responsables2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayList<String> responsables3 = data.getListaSpinnerResidentes(id_hogar);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(AgregarResidenteActivity.this, android.R.layout.simple_spinner_dropdown_item,responsables3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        c2_p211_Spinner.setAdapter(adapter3);
        c2_p209_or_Spinner.setAdapter(adapter1);
        c2_p210_or_Spinner.setAdapter(adapter2);

        if(data.existeElemento(getNombreTabla(),_id)){
            Residente residente = data.getResidente(_id);
            if (residente.getNumero().equals("1")){
                c2_p202_TextInputET.setEnabled(false);
                c2_p202_TextInputET_pat.setEnabled(false);
                c2_p203_Spinner.setEnabled(false);
                jefe_hogar = true;
            }else {
                jefe_hogar = false;
            }
            c2_p202_TextInputET.setText(residente.getC2_p202());
            c2_p202_TextInputET_pat.setText(residente.getC2_p202_pat());
            c2_p203_Spinner.setSelection(Integer.parseInt(residente.getC2_p203()));
            observaciones_edt_cap2.setText(residente.getOBS200());

            if (!residente.getC2_p204().equals("")){
                ((RadioButton)c2_p204_RadioGroup.getChildAt(Integer.parseInt(residente.getC2_p204()))).setChecked(true);
                sexo_residente = Integer.parseInt(residente.getC2_p204());
            }
            if(!residente.getC2_p205_a2().equals(""))
                //c2_p205_a_TextInputET.setText(residente.getC2_p205_a2());
//            c2_p205_a_sp.setText(setValidateEdad(residente.getC2_p205_a2()));
//            c2_p205_m_sp.setText(residente.getC2_p205_m());
            c2_p205_a_sp.setSelection(DAOUtils.convertNumber(setValidateEdad(residente.getC2_p205_a2())));
            if(residente.getC2_p205_m().equals("")){c2_p205_m_sp.setSelection(0);}
            else{c2_p205_m_sp.setSelection(DAOUtils.convertNumber(residente.getC2_p205_m())+1);}


            if(!residente.getC2_p206().equals(""))c2_p206_Spinner.setSelection(Integer.parseInt(residente.getC2_p206()));
            if (!residente.getC2_p207().equals(""))((RadioButton)c2_p207_RadioGroup.getChildAt(Integer.parseInt(residente.getC2_p207()))).setChecked(true);
            c2_p207_Edt.setText(residente.getC2_p207_o());
            if (!residente.getC2_p208().equals(""))((RadioButton)c2_p208_RadioGroup.getChildAt(Integer.parseInt(residente.getC2_p208()))).setChecked(true);
            if (!residente.getC2_p209().equals(""))((RadioButton)c2_p209_RadioGroup.getChildAt(Integer.parseInt(residente.getC2_p209()))).setChecked(true);
            if (!residente.getC2_p209_p().equals("")) ((RadioButton)c2_p209_p_RadioGroup.getChildAt(Integer.parseInt(residente.getC2_p209_p()))).setChecked(true);
            if (!residente.getC2_p210().equals(""))((RadioButton)c2_p210_RadioGroup.getChildAt(Integer.parseInt(residente.getC2_p210()))).setChecked(true);
            if (!residente.getC2_p210_p().equals(""))((RadioButton)c2_p210_p_RadioGroup.getChildAt(Integer.parseInt(residente.getC2_p210_p()))).setChecked(true);
            //c2_p211_Spinner.setSelection(Integer.parseInt(residente.getC2_p211()));

            if(!residente.getC2_p209_pos().equals("")){
                c2_p209_or_Spinner.setSelection(Integer.parseInt(residente.getC2_p209_pos()));
            }
            if(!residente.getC2_p210_pos().equals("")){
                c2_p210_or_Spinner.setSelection(Integer.parseInt(residente.getC2_p210_pos()));
            }
            if(!residente.getC2_p211_pos().equals("")){
                c2_p211_Spinner.setSelection(Integer.parseInt(residente.getC2_p211_pos()));
            }
            if (!residente.getC2_p211_1().equals(""))((RadioButton)c2_p211_1_RadioGroup.getChildAt(Integer.parseInt(residente.getC2_p211_1()))).setChecked(true);
            c2_p211_1_o_Edt.setText(residente.getC2_p211_1_o());

            if(residente.getC2_p212().equals("1")){ cb_p212.setChecked(true);}

            if(!residente.getC2_p205_a().equals("")) edad_p = Integer.parseInt(residente.getC2_p205_a());
        }
        if(data.getResidente(idJefeHogar).getC2_p205_a()==null) {
            edadJefeHogar = "0";
        }else if(data.getResidente(idJefeHogar).getC2_p205_a().equals("")){
            edadJefeHogar = "0";
        }else {
            edadJefeHogar = data.getResidente(idJefeHogar).getC2_p205_a();
            sexoJefeHogar = data.getResidente(idJefeHogar).getC2_p204();
        }
        ArrayList<Residente> residentes = new ArrayList<>();
        residentes = data.getAllResidentesHogar(id_hogar);
        if(Integer.parseInt(numero)<=residentes.size()){
            editar = true;
        }
        cant_p_s_h=0; cant_p_s_m = 0;
        if(residentes.size()>0){
            for(Residente r: residentes){
                if(r.getC2_p203().equals("1")){
                    if(r.getC2_p205_a().equals("")){r.setC2_p205_a("0");}
                    edad_jefe_hogar = Integer.parseInt(r.getC2_p205_a());
                    estadoCivilJefeHogar = r.getC2_p206();
                }
                if(r.getC2_p203().equals("2")){
                    existe_conyuge = true;
                    estadoCivilConyuge = r.getC2_p206();
                }
                if(r.getC2_p203().equals("6")){
                    cant_padres_suegros++;
                    if(r.getC2_p204().equals("1")){
                        cant_p_s_m++;
                    }
                    if(r.getC2_p204().equals("2")){
                        cant_p_s_m++;
                    }
                }
            }
        }
        data.close();
    }

    @Override
    public void llenarVista() {

    }

    @Override
    public void guardarDatos(){
        Data data = new Data(this);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.residentes_c2_p202,c2_p202);
        contentValues.put(SQLConstantes.residentes_c2_p202_pat,c2_p202_pat);
        contentValues.put(SQLConstantes.residentes_c2_p202_mat,c2_p202_mat);
        contentValues.put(SQLConstantes.residentes_c2_p203,c2_p203);
        contentValues.put(SQLConstantes.residentes_c2_OBS200,observaciones_cap2);
        if(c2_p204==-1)contentValues.put(SQLConstantes.residentes_c2_p204,"");else contentValues.put(SQLConstantes.residentes_c2_p204,c2_p204);
        if(c2_p205_a>0){contentValues.put(SQLConstantes.residentes_c2_p205_a,c2_p205_a); }else {contentValues.put(SQLConstantes.residentes_c2_p205_a,"");}
        if(c2_p205_m>-1){contentValues.put(SQLConstantes.residentes_c2_p205_m,c2_p205_m); }else {contentValues.put(SQLConstantes.residentes_c2_p205_m,"");}
        //contentValues.put(SQLConstantes.residentes_c2_p205_a,c2_p205_a);
        //contentValues.put(SQLConstantes.residentes_c2_p205_m,c2_p205_m);
        contentValues.put(SQLConstantes.residentes_c2_p206,c2_p206);
        if(c2_p207==-1)contentValues.put(SQLConstantes.residentes_c2_p207,"");else contentValues.put(SQLConstantes.residentes_c2_p207,c2_p207+"");
        contentValues.put(SQLConstantes.residentes_c2_p207_o,c2_p207_o);
        if(c2_p208==-1)contentValues.put(SQLConstantes.residentes_c2_p208,"");else contentValues.put(SQLConstantes.residentes_c2_p208,c2_p208 +"");
        if(c2_p209.equals("-1"))contentValues.put(SQLConstantes.residentes_c2_p209,"");else contentValues.put(SQLConstantes.residentes_c2_p209,c2_p209 +"");
        if(c2_p209_p.equals("-1"))contentValues.put(SQLConstantes.residentes_c2_p209_p,"");else contentValues.put(SQLConstantes.residentes_c2_p209_p,c2_p209_p +"");
        if(c2_p210.equals("-1"))contentValues.put(SQLConstantes.residentes_c2_p210,"");else contentValues.put(SQLConstantes.residentes_c2_p210,c2_p210 +"");
        if(c2_p210_p.equals("-1"))contentValues.put(SQLConstantes.residentes_c2_p210_p,"");else contentValues.put(SQLConstantes.residentes_c2_p210_p,c2_p210_p +"");
        if(!c2_p209_or.equals("0")){
            contentValues.put(SQLConstantes.residentes_c2_p209_n,Integer.parseInt(UtilsMethods.getDosDigitos(c2_p209_pos)));
            contentValues.put(SQLConstantes.residentes_c2_p209_pos,c2_p209_or);
        }else {
            contentValues.put(SQLConstantes.residentes_c2_p209_n,"");
            contentValues.put(SQLConstantes.residentes_c2_p209_pos,"");}

        if(!c2_p210_or.equals("0")){
            contentValues.put(SQLConstantes.residentes_c2_p210_n,Integer.parseInt(UtilsMethods.getDosDigitos(c2_p210_pos)));
            contentValues.put(SQLConstantes.residentes_c2_p210_pos,c2_p210_or);
        }else {
            contentValues.put(SQLConstantes.residentes_c2_p210_n,"");
            contentValues.put(SQLConstantes.residentes_c2_p210_pos,"");
        }

        if(!c2_p211.equals("0")){
            contentValues.put(SQLConstantes.residentes_c2_p211,Integer.parseInt(UtilsMethods.getDosDigitos(c2_p211_pos)));
            contentValues.put(SQLConstantes.residentes_c2_p211_nom,getNombreResidente(Integer.parseInt(UtilsMethods.getDosDigitos(c2_p211_pos))+""));
            contentValues.put(SQLConstantes.residentes_c2_p211_pos,c2_p211);
        }else {
            contentValues.put(SQLConstantes.residentes_c2_p211,"");
            contentValues.put(SQLConstantes.residentes_c2_p211_nom,"");
            contentValues.put(SQLConstantes.residentes_c2_p211_pos,"");
        }
        if(c2_p211_1==-1)contentValues.put(SQLConstantes.residentes_c2_p211_1,"");else contentValues.put(SQLConstantes.residentes_c2_p211_1,c2_p211_1+"");
        contentValues.put(SQLConstantes.residentes_c2_p211_1_o,c2_p211_1_o);

        contentValues.put(SQLConstantes.residentes_c2_p212,c2_p212);


        if(!data.existeElemento(getNombreTabla(),_id)){
            contentValues.put(SQLConstantes.residentes_id,_id);
            contentValues.put(SQLConstantes.residentes_id_hogar, id_hogar);
            //contentValues.put(SQLConstantes.residentes_id_informante, id_informante);
            contentValues.put(SQLConstantes.residentes_id_vivienda, id_vivienda);
            contentValues.put(SQLConstantes.residentes_numero,numero);
            contentValues.put(SQLConstantes.residentes_COB200,"1");
            contentValues.put(SQLConstantes.residentes_encuestado_cobertura,"");
            data.insertarElemento(getNombreTabla(),contentValues);
        }
        data.actualizarElemento(getNombreTabla(),contentValues,_id);
        //data.actualizarValor(SQLConstantes.tablahogares,SQLConstantes.hogar_nroviven,numero,id_hogar);

        //SI ES MIGRANTE
        if (c2_p208 == 1){
            if(!data.existeElemento(SQLConstantes.tablalayouts,_id)){
                POJOLayout pojoLayout = new POJOLayout();
                pojoLayout.set_id(_id);
                pojoLayout.setId_vivienda(id_vivienda);
                data.insertarElemento(SQLConstantes.tablalayouts,pojoLayout.toValues());
                POJOFragment pojoFragment = new POJOFragment(_id);
                pojoFragment.setId_vivienda(id_vivienda);
                CoberturaFragment coberturaFragment = new CoberturaFragment(_id);
                coberturaFragment.setId_vivienda(id_vivienda);
                data.insertarElemento(SQLConstantes.tablafragments,pojoFragment.toValues());
                data.insertarElemento(SQLConstantes.tablacoberturafragments,coberturaFragment.toValues());
            }
            crearModulos();
            ocultarOtrosLayouts(c2_p205_a+"",c2_p204+"");
        }


        data.close();
    }

    public void crearModulos(){
        Data data = new Data(this);
        data.open();
        Modulo3 modulo3 = new Modulo3();
        modulo3.set_id(_id);
        modulo3.setIdHogar(id_hogar);
        modulo3.setIdVivienda(id_vivienda);
        Modulo4 modulo4 = new Modulo4();
        modulo4.set_id(_id);
        modulo4.setIdHogar(id_hogar);
        modulo4.setIdVivienda(id_vivienda);
        Modulo5 modulo5 = new Modulo5();
        modulo5.set_id(_id);
        modulo5.setIdHogar(id_hogar);
        modulo5.setIdVivienda(id_vivienda);
        Modulo6 modulo6 = new Modulo6();
        modulo6.set_id(_id);
        modulo6.setIdHogar(id_hogar);
        modulo6.setIdVivienda(id_vivienda);
        Modulo7 modulo7 = new Modulo7();
        modulo7.set_id(_id);
        modulo7.setIdHogar(id_hogar);
        modulo7.setIdVivienda(id_vivienda);
        Modulo8 modulo8 = new Modulo8();
        modulo8.set_id(_id);
        modulo8.setIdHogar(id_hogar);
        modulo8.setIdVivienda(id_vivienda);
        if (!data.existeElemento(SQLConstantes.tablamodulo3,_id)) data.insertarElemento(SQLConstantes.tablamodulo3,modulo3.toValues());
        if (!data.existeElemento(SQLConstantes.tablamodulo4,_id)) data.insertarElemento(SQLConstantes.tablamodulo4,modulo4.toValues());
        if (!data.existeElemento(SQLConstantes.tablamodulo5,_id)) data.insertarElemento(SQLConstantes.tablamodulo5,modulo5.toValues());
        if (!data.existeElemento(SQLConstantes.tablamodulo6,_id)) data.insertarElemento(SQLConstantes.tablamodulo6,modulo6.toValues());
        if (!data.existeElemento(SQLConstantes.tablamodulo7,_id)) data.insertarElemento(SQLConstantes.tablamodulo7,modulo7.toValues());
        if (!data.existeElemento(SQLConstantes.tablamodulo8,_id)) data.insertarElemento(SQLConstantes.tablamodulo8,modulo8.toValues());
        data.close();
    }

    public void ocultarOtrosLayouts(String edad, String sexo){
        String ids_residentes="";
        Data data = new Data(AgregarResidenteActivity.this);
        data.open();
        int iEdad = 0;
        int iSexo = 0;
        boolean entro=false;
        if (!edad.equals("")) iEdad = Integer.parseInt(edad);
        iSexo = Integer.parseInt(sexo);

        ArrayList<Residente> residentes = data.getAllResidentesHogar(id_hogar);
        boolean todosMayoresEdad = true;
        for (Residente r : residentes){
            int ed = 0;
            if(!entro){//SOLO ID DE >=12
                if(Integer.parseInt(r.getC2_p205_a())>=12){
                    ids_residentes += r.get_id();entro=true;
                }
            }else{
                if(Integer.parseInt(r.getC2_p205_a())>=12){
                    ids_residentes += "," +r.get_id();
                }
            }
            //ids_residentes += r.get_id();
            if (!r.getC2_p205_a().equals("")) ed = Integer.parseInt(r.getC2_p205_a());
            if (ed < 18) todosMayoresEdad = false;
        }

//        if (todosMayoresEdad){
//            for (Residente r : residentes){
//                ocultarP409(r.get_id());
//                ocultarP410(r.get_id());
//            }
//        }else{
//            for (Residente r : residentes){
//                int ed = 0;
//                if (!r.getC2_p205_a().equals("")) ed = Integer.parseInt(r.getC2_p205_a());
//                if (ed >= 18 || r.getNumero().equals("1")) {
//                    mostrarLayoutPregunta(SQLConstantes.layouts_p409,r.get_id());
//                    mostrarLayoutPregunta(SQLConstantes.layouts_p410,r.get_id());
//                }else{
//                    ocultarP409(r.get_id());
//                    ocultarP410(r.get_id());
//                }
//            }
//        }

        if (iEdad <= 17)mostrarLayoutPregunta(SQLConstantes.layouts_p411);
        else ocultarP411();

//        if (iEdad >= 12 && iEdad <= 49 && iSexo == 2){
//            mostrarLayoutPregunta(SQLConstantes.layouts_p412);
//            mostrarLayoutPregunta(SQLConstantes.layouts_p413);
//            mostrarLayoutPregunta(SQLConstantes.layouts_p414);
//            mostrarLayoutPregunta(SQLConstantes.layouts_p415);
//        } else{ ocultarP412();ocultarP413();ocultarP414();ocultarP415(); }

        if (iEdad >= 15) mostrarLayoutPregunta(SQLConstantes.layouts_p416);
        else ocultarP416();



        if (iEdad >= 3) mostrarCapitulo5();
        else ocultarCapitulo5();

        if (iEdad >= 5) { mostrarCapitulo6();mostrarCapitulo7(); }
        else { ocultarCapitulo6();ocultarCapitulo7(); }

        if (iEdad >= 18) mostrarCapitulo8();
        else ocultarCapitulo8();


        if (iEdad >= 3 && iEdad <=25){
            mostrarLayoutPregunta(SQLConstantes.layouts_p508);
            //mostrarLayoutPregunta(SQLConstantes.layouts_p509);
            //mostrarLayoutPregunta(SQLConstantes.layouts_p510);
            //mostrarLayoutPregunta(SQLConstantes.layouts_p511);
            //if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p508p511,_id).equals("-1"))
            //    data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p508p511,"1",_id);
        } else {
            ocultarP508();//ocultarP509();ocultarP510();ocultarP511();
            //data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p508p511,"-1",_id);
        }

        if (iEdad >= 14){
            mostrarLayoutPregunta(SQLConstantes.layouts_p512);
            mostrarLayoutPregunta(SQLConstantes.layouts_p513);
            if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p512p513,_id).equals("-1"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p512p513,"1",_id);
            mostrarLayoutPregunta(SQLConstantes.layouts_p629);
            mostrarLayoutPregunta(SQLConstantes.layouts_p630);
        } else {
            ocultarP512();ocultarP513();
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p512p513,"-1",_id);
            ocultarP629();ocultarP630(); }

        if (iEdad >= 12){
            if(!todosMayoresEdad) {
                Log.e("algun menor de edad", "ocultarOtrosLayouts: " + ids_residentes);
                String[] residente_id = ids_residentes.split(",");
                for(String id_residente:residente_id){
                    Log.e("mostrar", "ocultarOtrosLayouts: "+ id_residente);
                    mostrarLayoutPregunta(SQLConstantes.layouts_p625,id_residente);
                    if (data.getValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p622p625, id_residente).equals("-1"))
                        data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p622p625, "1", id_residente);
                }
            }else{
                Log.e("ningun menor de edad", "ocultarOtrosLayouts: " + ids_residentes);
                String[] residente_id = ids_residentes.split(",");
                for(String id_residente:residente_id){
                    Log.e("ocultar", "ocultarOtrosLayouts: "+ id_residente);
                    ocultarP625_2(id_residente);
                    if(data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p622,id_residente).equals("0") &&
                            data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p623,id_residente).equals("0") &&
                            data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p624,id_residente).equals("0") &&
                            data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p625,id_residente).equals("0")){
                        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,"-1",id_residente);
                    }
                }
//                ocultarP625();
//                if(data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p622,_id).equals("0") &&
//                        data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p623,_id).equals("0") &&
//                        data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p624,_id).equals("0") &&
//                        data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p625,_id).equals("0")){
//                    data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,"-1",_id);
//                }
            }
        } else {
//            String[] residente_id = ids_residentes.split(",");
//            for(String id_residente:residente_id){
//                ocultarP625_2(id_residente);
//                if(data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p622,id_residente).equals("0") &&
//                        data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p623,id_residente).equals("0") &&
//                        data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p624,id_residente).equals("0") &&
//                        data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p625,id_residente).equals("0")){
//                    data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,"-1",id_residente);
//                }
//            }
            ocultarP625();
            if(data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p622,_id).equals("0") &&
                    data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p623,_id).equals("0") &&
                    data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p624,_id).equals("0") &&
                    data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p625,_id).equals("0")){
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,"-1",_id);
            }
        }

        /////////////////////////////AGREGADO 10/11/21 OBSERVACION8 !C205_A >= 12 (OCULTA FRAGMENTP313P317 => P303)////////////////////////////////////

        /*if(iEdad < 12){
            ocultarFragmentP313P317(false);
            }*/
        Log.e("SPINNERMES",""+c2_p205_m);
        Log.e("SPINNERAÑO",""+c2_p205_a);

        if(data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p411,_id).equals("0") &&
                data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p412,_id).equals("0") &&
                data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p413,_id).equals("0") &&
                data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p414,_id).equals("0") &&
                data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p415,_id).equals("0") &&
                data.getValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p416,_id).equals("0")){
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p411p416,"-1",_id);
        }else {
            if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p411p416,_id).equals("-1"))
               data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p411p416,"1",_id);
        }
        data.close();
    }

    public void mostrarMensaje(String m){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(m);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void mostrarMensaje2(String m){
        guardarDatos();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(m);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                finish();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void configurarEditText(final EditText editText, final View view, int tipo,int longitud){
        if (tipo == 1) editText.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(longitud)});

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
        if (tipo == 2) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(longitud)});
            editText.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        }
    }

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    /////////////////////////////AGREGADO 10/11/21 OBSERVACION8 !C205_A >= 12 (OCULTA FRAGMENTP313P317 => P303)////////////////////////////////////

    public void ocultarFragmentP313P317(boolean ocultar){
        Data data = new Data(this);
        data.open();
        if(ocultar){
            ContentValues contentValues = new ContentValues();
            contentValues.put(SQLConstantes.modulo3_c3_p313,"");

            //Setea valores string vacio en los campos del fragment
            data.actualizarElemento(getNombreTabla(), contentValues, _id);
            //Oculta el fragment
            data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p313p317, "-1", _id);
        }else {
            data.actualizarValor(SQLConstantes.tablafragments, SQLConstantes.fragments_p313p317, "1", _id);
        }
        data.close();
    }

    public void ocultarP409(){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablamodulo4,SQLConstantes.modulo4_c4_p409,"",_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p409,"0",_id);
        data.close();
    }

    public void ocultarP409(String idEncuestado){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablamodulo4,SQLConstantes.modulo4_c4_p409,"",idEncuestado);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p409,"0",idEncuestado);
        data.close();
    }

    public void ocultarP410(){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablamodulo4,SQLConstantes.modulo4_c4_p410,"",_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p410,"0",_id);
        data.close();
    }

    public void ocultarP410(String idEncuestado){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablamodulo4,SQLConstantes.modulo4_c4_p410,"",idEncuestado);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p410,"0",idEncuestado);
        data.close();
    }

    public void ocultarP411(){
        Data data = new Data(this);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo4_c4_p411_1,"");
        contentValues.put(SQLConstantes.modulo4_c4_p411_2,"");
        contentValues.put(SQLConstantes.modulo4_c4_p411_3,"");
        contentValues.put(SQLConstantes.modulo4_c4_p411_4,"");
        contentValues.put(SQLConstantes.modulo4_c4_p411_5,"");
        contentValues.put(SQLConstantes.modulo4_c4_p411_6,"");
        contentValues.put(SQLConstantes.modulo4_c4_p411_7,"");
        contentValues.put(SQLConstantes.modulo4_c4_p411_8,"");
        contentValues.put(SQLConstantes.modulo4_c4_p411_9,"");
        contentValues.put(SQLConstantes.modulo4_c4_p411_10,"");
        contentValues.put(SQLConstantes.modulo4_c4_p411_11,"");
        contentValues.put(SQLConstantes.modulo4_c4_p411_12,"");
        contentValues.put(SQLConstantes.modulo4_c4_p411_13,"");
        contentValues.put(SQLConstantes.modulo4_c4_p411_14,"");
        contentValues.put(SQLConstantes.modulo4_c4_p411_o,"");
        data.actualizarElemento(SQLConstantes.tablamodulo4,contentValues,_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p411,"0",_id);
        data.close();

    }

    public void ocultarP412(){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablamodulo4,SQLConstantes.modulo4_c4_p412,"",_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p412,"0",_id);
        data.close();
    }

    public void ocultarP413(){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablamodulo4,SQLConstantes.modulo4_c4_p413,"",_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p413,"0",_id);
        data.close();
    }
    public void ocultarP414(){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablamodulo4,SQLConstantes.modulo4_c4_p414,"",_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p414,"0",_id);
        data.close();
    }
    public void ocultarP415(){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablamodulo4,SQLConstantes.modulo4_c4_p415,"",_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p415,"0",_id);
        data.close();
    }
    public void ocultarP416(){
        Data data = new Data(this);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo4_c4_p416_1,"");
        contentValues.put(SQLConstantes.modulo4_c4_p416_2,"");
        contentValues.put(SQLConstantes.modulo4_c4_p416_3,"");
        contentValues.put(SQLConstantes.modulo4_c4_p416_4,"");
        contentValues.put(SQLConstantes.modulo4_c4_p416_5,"");
        contentValues.put(SQLConstantes.modulo4_c4_p416_6,"");
        contentValues.put(SQLConstantes.modulo4_c4_p416_7,"");
        contentValues.put(SQLConstantes.modulo4_c4_p416_8,"");
        contentValues.put(SQLConstantes.modulo4_c4_p416_o,"");
        data.actualizarElemento(SQLConstantes.tablamodulo4,contentValues,_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p416,"0",_id);
        data.close();
    }

    public void ocultarP508(){
        Data data = new Data(this);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo5_c5_p508_1,"");
        contentValues.put(SQLConstantes.modulo5_c5_p508_2,"");
        contentValues.put(SQLConstantes.modulo5_c5_p508_3,"");
        contentValues.put(SQLConstantes.modulo5_c5_p508_4,"");
        contentValues.put(SQLConstantes.modulo5_c5_p508_5,"");
        contentValues.put(SQLConstantes.modulo5_c5_p508_6,"");
        contentValues.put(SQLConstantes.modulo5_c5_p508_7,"");
        contentValues.put(SQLConstantes.modulo5_c5_p508_8,"");
        contentValues.put(SQLConstantes.modulo5_c5_p508_9,"");
        contentValues.put(SQLConstantes.modulo5_c5_p508_10,"");
        contentValues.put(SQLConstantes.modulo5_c5_p508_11,"");
        contentValues.put(SQLConstantes.modulo5_c5_p508_o,"");
        data.actualizarElemento(SQLConstantes.tablamodulo5,contentValues,_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p508,"0",_id);
        data.close();

    }
    public void ocultarP509(){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablamodulo5,SQLConstantes.modulo5_c5_p509,"",_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p509,"0",_id);
        data.close();
    }
    public void ocultarP510(){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablamodulo5,SQLConstantes.modulo5_c5_p510,"",_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p510,"0",_id);
        data.close();
    }
    public void ocultarP511(){
        Data data = new Data(this);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo5_c5_p511,"");
        contentValues.put(SQLConstantes.modulo5_c5_p511_o,"");
        data.actualizarElemento(SQLConstantes.tablamodulo5,contentValues,_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p511,"0",_id);
        data.close();
    }
    public void ocultarP512(){
        Data data = new Data(this);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo5_c5_p512,"");
        contentValues.put(SQLConstantes.modulo5_c5_p512_o,"");
        data.actualizarElemento(SQLConstantes.tablamodulo5,contentValues,_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p512,"0",_id);
        data.close();
    }
    public void ocultarP513(){
        Data data = new Data(this);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo5_c5_p512,"");
        contentValues.put(SQLConstantes.modulo5_c5_p513_o,"");
        data.actualizarElemento(SQLConstantes.tablamodulo5,contentValues,_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p513,"0",_id);
        data.close();
    }

    public void ocultarP625(){
        Data data = new Data(this);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo6_c6_p625_1,"");
        contentValues.put(SQLConstantes.modulo6_c6_p625_2,"");
        contentValues.put(SQLConstantes.modulo6_c6_p625_3,"");
        contentValues.put(SQLConstantes.modulo6_c6_p625_4,"");
        contentValues.put(SQLConstantes.modulo6_c6_p625_5,"");
        contentValues.put(SQLConstantes.modulo6_c6_p625_6,"");
        contentValues.put(SQLConstantes.modulo6_c6_p625_o,"");
        data.actualizarElemento(SQLConstantes.tablamodulo6,contentValues,_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p625,"0",_id);
        data.close();
    }

    public void ocultarP625_2(String id_residente){
        Data data = new Data(this);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo6_c6_p625_1,"");
        contentValues.put(SQLConstantes.modulo6_c6_p625_2,"");
        contentValues.put(SQLConstantes.modulo6_c6_p625_3,"");
        contentValues.put(SQLConstantes.modulo6_c6_p625_4,"");
        contentValues.put(SQLConstantes.modulo6_c6_p625_5,"");
        contentValues.put(SQLConstantes.modulo6_c6_p625_6,"");
        contentValues.put(SQLConstantes.modulo6_c6_p625_o,"");
        data.actualizarElemento(SQLConstantes.tablamodulo6,contentValues,id_residente);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p625,"0",id_residente);
        data.close();
    }

    public void ocultarP629(){
        Data data = new Data(this);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo6_c6_p629_1,"");
        contentValues.put(SQLConstantes.modulo6_c6_p629_2,"");
        contentValues.put(SQLConstantes.modulo6_c6_p629_3,"");
        contentValues.put(SQLConstantes.modulo6_c6_p629_4,"");
        contentValues.put(SQLConstantes.modulo6_c6_p629_o,"");
        contentValues.put(SQLConstantes.modulo6_c6_p629_1_f,"");
        contentValues.put(SQLConstantes.modulo6_c6_p629_1_m,"");
        contentValues.put(SQLConstantes.modulo6_c6_p629_2_f,"");
        contentValues.put(SQLConstantes.modulo6_c6_p629_2_m,"");
        contentValues.put(SQLConstantes.modulo6_c6_p629_3_f,"");
        contentValues.put(SQLConstantes.modulo6_c6_p629_3_m,"");
        contentValues.put(SQLConstantes.modulo6_c6_p629_4_f,"");
        contentValues.put(SQLConstantes.modulo6_c6_p629_4_m,"");
        data.actualizarElemento(SQLConstantes.tablamodulo6,contentValues,_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p629,"0",_id);
        data.close();
    }
    public void ocultarP630(){
        Data data = new Data(this);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo6_c6_p630_1,"");
        contentValues.put(SQLConstantes.modulo6_c6_p630_1med,"");
        contentValues.put(SQLConstantes.modulo6_c6_p630_1o,"");
        contentValues.put(SQLConstantes.modulo6_c6_p630_1frec,"");
        contentValues.put(SQLConstantes.modulo6_c6_p630_1mont,"");
        contentValues.put(SQLConstantes.modulo6_c6_p630_2,"");
        contentValues.put(SQLConstantes.modulo6_c6_p630_2med,"");
        contentValues.put(SQLConstantes.modulo6_c6_p630_2o,"");
        contentValues.put(SQLConstantes.modulo6_c6_p630_2frec,"");
        contentValues.put(SQLConstantes.modulo6_c6_p630_2mont,"");
        data.actualizarElemento(SQLConstantes.tablamodulo6,contentValues,_id);
        data.actualizarValor(SQLConstantes.tablalayouts,SQLConstantes.layouts_p630,"0",_id);
        data.close();
    }

    public void mostrarLayoutPregunta(String varLayout){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablalayouts,varLayout,"1",_id);
        data.close();
    }

    public void mostrarLayoutPregunta(String varLayout, String idEncuestado){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablalayouts,varLayout,"1",idEncuestado);
        data.close();
    }

    public void ocultarCapitulo5(){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p501p505,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p506p507,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p508p511,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p512p513,"-1",_id);
        data.eliminarDato(SQLConstantes.tablamodulo5,_id);
        data.close();
    }
    public void mostrarCapitulo5(){
        Data data = new Data(this);
        data.open();
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p501p505,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p501p505,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p506p507,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p506p507,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p508p511,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p508p511,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p512p513,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p512p513,"1",_id);
        Modulo5 modulo5 = new Modulo5();
        modulo5.set_id(_id);
        modulo5.setIdHogar(id_hogar);
        modulo5.setIdVivienda(id_vivienda);
        if (!data.existeElemento(SQLConstantes.tablamodulo5,_id)) data.insertarElemento(SQLConstantes.tablamodulo5,modulo5.toValues());
        data.close();
    }
    public void ocultarCapitulo6(){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p601p604,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p605p608,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p609p612,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p618p621,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p626p629,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p630,"-1",_id);
        data.eliminarDato(SQLConstantes.tablamodulo6,_id);

        data.close();
    }
    public void mostrarCapitulo6(){
        Data data = new Data(this);
        data.open();
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p601p604,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p601p604,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p605p608,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p605p608,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p609p612,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p609p612,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p618p621,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p618p621,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p626p629,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p626p629,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p630,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p630,"1",_id);

        Modulo6 modulo6 = new Modulo6();
        modulo6.set_id(_id);
        modulo6.setIdHogar(id_hogar);
        modulo6.setIdVivienda(id_vivienda);
        if (!data.existeElemento(SQLConstantes.tablamodulo6,_id)) data.insertarElemento(SQLConstantes.tablamodulo6,modulo6.toValues());
        data.close();
    }

    public void ocultarCapitulo7(){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p701p705,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p706p709,"-1",_id);
        data.eliminarDato(SQLConstantes.tablamodulo7,_id);
        data.close();
    }
    public void mostrarCapitulo7(){
        Data data = new Data(this);
        data.open();
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p701p705,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p701p705,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p706p709,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p706p709,"1",_id);

        Modulo7 modulo7 = new Modulo7();
        modulo7.set_id(_id);
        modulo7.setIdHogar(id_hogar);
        modulo7.setIdVivienda(id_vivienda);
        if (!data.existeElemento(SQLConstantes.tablamodulo7,_id)) data.insertarElemento(SQLConstantes.tablamodulo7,modulo7.toValues());
        data.close();
    }
    public void ocultarCapitulo8(){
        Data data = new Data(this);
        data.open();
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p801p804,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p805p808,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p809p812,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p813p816,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p817p820,"-1",_id);
        data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p821p823,"-1",_id);
        data.eliminarDato(SQLConstantes.tablamodulo8,_id);
        data.close();
    }
    public void mostrarCapitulo8(){
        Data data = new Data(this);
        data.open();
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p801p804,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p801p804,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p805p808,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p805p808,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p809p812,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p809p812,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p813p816,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p813p816,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p817p820,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p817p820,"1",_id);
        if(data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p821p823,_id).equals("-1"))
            data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p821p823,"1",_id);
        Modulo8 modulo8 = new Modulo8();
        modulo8.set_id(_id);
        modulo8.setIdHogar(id_hogar);
        modulo8.setIdVivienda(id_vivienda);
        if (!data.existeElemento(SQLConstantes.tablamodulo8,_id)) data.insertarElemento(SQLConstantes.tablamodulo8,modulo8.toValues());
        data.close();
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

    public void cargarResidentes() {
        Data data =  new Data(getApplicationContext());
        data.open();
        ArrayList<String> residentes1 = data.getListaSpinnerResidentesHogar(id_hogar);
        Hogar hogar = data.getHogar(id_hogar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,residentes1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        c2_p209_or_Spinner.setAdapter(adapter);
        c2_p210_or_Spinner.setAdapter(adapter);

        if(!hogar.getId_informante().equals("")){
            c2_p209_or_Spinner.setSelection(Integer.parseInt(hogar.getId_informante()));
        }
        data.close();
    }

    public boolean diferenciaEdad(int edadJefe,int edadParentesco,int minima,int maxima){
        Log.e("valorex:",""+edadJefe+"/"+edadParentesco);
        boolean estado = false;
        if(minima>(edadJefe-edadParentesco) || (edadJefe-edadParentesco)>maxima){
            estado = true;
        }
        return estado;
    }

    public String getNombreResidente(String numero) {
        String nombre = "";
        Data data =  new Data(getApplicationContext());
        data.open();
        Residente residente = data.getNombreResidente(id_hogar,numero);
        nombre = residente.getC2_p202();
        data.close();

        return nombre;
    }

    public boolean getExisteResidente(String idResidente){
        boolean respuesta = false;
        Data data = new Data(this);
        data.open();
        if(data.existeElemento(getNombreTabla(),idResidente)){
            respuesta = true;
        }
        data.close();
        return respuesta;
    }

    public boolean getValidateEdadMayorMenor(String nroResidente,String edad){
        boolean estado = false;
        int edadPadre = 0;
        int edadMenor = 0;
        Log.e("pepex:",""+nroResidente);
        Data data =  new Data(getApplicationContext());
        data.open();
        Residente residente = data.getNombreResidente(id_hogar,Integer.parseInt(UtilsMethods.getDosDigitos(nroResidente))+"");
        Log.e("pepe:",""+residente.getC2_p205_a());
        if(residente!=null){
            if(!residente.getC2_p205_a().equals("")){
                edadPadre = Integer.parseInt(residente.getC2_p205_a());
            }
        }
        if(!edad.equals("")){
            edadMenor = Integer.parseInt(edad);
        }
        data.close();
        if(edadPadre-edadMenor<12){
            estado = true;
        }
        return estado;
    }

    public boolean getValidateRegla0040D(String nroResidente,String edad){
        boolean estado = false;
        int edadResponsable = 0;
        int edadMenor = 0;
        Data data =  new Data(getApplicationContext());
        data.open();
        Residente residente = data.getNombreResidente(id_hogar,Integer.parseInt(UtilsMethods.getDosDigitos(nroResidente))+"");
        if(residente!=null){
            if(!residente.getC2_p205_a().equals("")){
                edadResponsable = Integer.parseInt(residente.getC2_p205_a());
            }
        }
        if(!edad.equals("")){
            edadMenor = Integer.parseInt(edad);
        }
        data.close();
        if(edadResponsable-edadMenor<24){
            estado = true;
        }
        return estado;
    }

    public boolean getValidateRegla0040E(String nroResidente,String edad){
        boolean estado = false;
        int edadResponsable = 0;
        int edadMenor = 0;
        Data data =  new Data(getApplicationContext());
        data.open();
        Residente residente = data.getNombreResidente(id_hogar,Integer.parseInt(UtilsMethods.getDosDigitos(nroResidente))+"");
        if(residente!=null){
            if(!residente.getC2_p205_a().equals("")){
                edadResponsable = Integer.parseInt(residente.getC2_p205_a());
            }
        }
        if(!edad.equals("")){
            edadMenor = Integer.parseInt(edad);
        }
        data.close();
        if(edadResponsable-edadMenor<0 && Integer.parseInt(UtilsMethods.getDosDigitos(nroResidente))!=Integer.parseInt(numero)){
            estado = true;
        }
        return estado;
    }

    public boolean getValidateRegla0040F(String nroResidente){
        boolean estado = false;
        Data data =  new Data(getApplicationContext());
        data.open();
        Residente residente = data.getNombreResidente(id_hogar,numero+"");
        if(residente!=null){
            Log.e("valo1:",""+Integer.parseInt(UtilsMethods.getDosDigitos(nroResidente)));
            Log.e("valo2:",""+residente.getNumero());
            if(residente.getNumero().equals(""+Integer.parseInt(UtilsMethods.getDosDigitos(nroResidente)))){
                estado = true;
                Log.e("valo1x:",""+Integer.parseInt(UtilsMethods.getDosDigitos(nroResidente)));
                Log.e("valo2x:",""+residente.getNumero());
            }
        }
        data.close();
        return estado;
    }



    public Residente getJefeHogar() {
        Residente residente = new Residente();
        Data data =  new Data(getApplicationContext());
        data.open();
        residente = data.getNombreResidente(id_hogar,"1");
        data.close();
        return residente;
    }

    public Residente getPareja() {
        ArrayList<Residente> listaResidentesHogar = new ArrayList<>();
        Residente residentePareja = new Residente();
        Data data =  new Data(getApplicationContext());
        data.open();
        listaResidentesHogar = data.getAllResidentesHogar(id_hogar);
        for (Residente residente: listaResidentesHogar) {
            if(residente.getC2_p203().equals("2")){
                residentePareja = residente;
            }
        }
        data.close();
        return residentePareja;
    }


//    public Residente getResidente(){
//        Residente residente = new Residente();
//        Data data = new Data(this);
//        data.open();
//        if(data.existeElemento(getNombreTabla(),_id)){
//            residente = data.getResidente(_id);
//        }
//        data.close();
//        return residente;
//    }

    public String setValidateEdad(String anios){
        String edadAnios = "0";
        if (!anios.equals("")){
            if (Integer.parseInt(anios)<12){
                setBlockingP206();
                setBlockingP207();
            }else {
                setUnlockingP206();
                setUnlockingP207();
            }
            edadAnios = anios;
        }
        return edadAnios;
    }

    public void setBlockingP206(){
        c2_p206_Spinner.setSelection(0);
        c2_p206_Spinner.setEnabled(false);

    }
    public void setUnlockingP206(){
        c2_p206_Spinner.setEnabled(true);
    }

    public void setBlockingP207(){
        c2_p207_RadioGroup.clearCheck();
        rb_207_1.setEnabled(false);
        rb_207_2.setEnabled(false);
        rb_207_3.setEnabled(false);
        rb_207_4.setEnabled(false);
        c2_p207_Edt.setText("");
    }
    public void setUnlockingP207(){
        rb_207_1.setEnabled(true);
        rb_207_2.setEnabled(true);
        rb_207_3.setEnabled(true);
        rb_207_4.setEnabled(true);
    }

    public void setBlockingP209(){
        c2_p209_RadioGroup.clearCheck();
        c2_p209_p_RadioGroup.clearCheck();
        rb_209_1.setEnabled(false);
        rb_209_2.setEnabled(false);
        c2_p209_or_Spinner.setSelection(0);
        c2_p209_or_Spinner.setEnabled(false);
    }
    public void setUnlockingP209(){
        rb_209_1.setEnabled(true);
        rb_209_2.setEnabled(true);
        c2_p209_or_Spinner.setEnabled(true);
    }

    public void setBlockingP210(){
        c2_p210_RadioGroup.clearCheck();
        c2_p210_p_RadioGroup.clearCheck();
        rb_210_1.setEnabled(false);
        rb_210_2.setEnabled(false);
        c2_p210_or_Spinner.setSelection(0);
        c2_p210_or_Spinner.setEnabled(false);
    }
    public void setUnlockingP210(){
        rb_210_1.setEnabled(true);
        rb_210_2.setEnabled(true);
        c2_p210_or_Spinner.setEnabled(true);
    }

    public void setBlockingP211(){
        c2_p211_Spinner.setSelection(0);
        c2_p211_Spinner.setEnabled(false);
        c2_p211_1_RadioGroup.clearCheck();
        rb_211_p_1.setEnabled(false);
        rb_211_p_2.setEnabled(false);
        rb_211_p_3.setEnabled(false);
        rb_211_p_4.setEnabled(false);
        c2_p211_1_o_Edt.setText("");

    }
    public void setUnlockingP211(){
        c2_p211_Spinner.setEnabled(true);
        rb_211_p_1.setEnabled(true);
        rb_211_p_2.setEnabled(true);
        rb_211_p_3.setEnabled(true);
        rb_211_p_4.setEnabled(true);
    }


    public void showVista(String vista){
        int valor = Integer.parseInt(vista);
        switch (valor){
            case 0:
                break;
            case 1:
                linearLayout202.setVisibility(View.VISIBLE);
                linearLayout203.setVisibility(View.VISIBLE);
                linearLayout204.setVisibility(View.GONE);
                linearLayout205.setVisibility(View.GONE);
                linearLayout206.setVisibility(View.GONE);
                linearLayout207.setVisibility(View.GONE);
                linearLayout208.setVisibility(View.GONE);
                linearLayout209.setVisibility(View.GONE);
                linearLayout210.setVisibility(View.GONE);
                linearLayout211.setVisibility(View.GONE);
                linearLayout212.setVisibility(View.GONE);
                linearLayoutobs.setVisibility(View.GONE);
                break;
            case 2:
                if(getExisteResidente(_id)){
                    linearLayout202.setVisibility(View.GONE);
                    linearLayout203.setVisibility(View.GONE);
                    linearLayout204.setVisibility(View.VISIBLE);
                    linearLayout205.setVisibility(View.VISIBLE);
                    linearLayout206.setVisibility(View.VISIBLE);
                    linearLayout207.setVisibility(View.VISIBLE);
                    linearLayout208.setVisibility(View.VISIBLE);
                    linearLayout209.setVisibility(View.VISIBLE);
                    linearLayout210.setVisibility(View.VISIBLE);
                    linearLayout211.setVisibility(View.VISIBLE);
                    linearLayoutobs.setVisibility(View.VISIBLE);
                    //linearLayout212.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(this,"DEBE COMPLETAR P202,P203 Y GUARDAR PARA CONTINUAR",Toast.LENGTH_SHORT).show();
                }
                break;
        }


    }

}
