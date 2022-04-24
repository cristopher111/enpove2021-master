package com.example.ricindigus.enpove2021.fragments.modulo1;


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
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo1H;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo1V;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethods;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentP108P113 extends FragmentPagina {
    TextInputEditText c1_p111a_EditText;
    String idHogar;
    String idVivienda;
    Context context;
    TextView titulo_P111_tv,titulo_P112_tv;

    RadioGroup c1_p107_RadioGroup,c1_p108_1_RadioGroup,c1_p108_2_RadioGroup,c1_p108_3_RadioGroup,c1_p108_4_RadioGroup,
               c1_p109_RadioGroup, c1_p110_1_RadioGroup, c1_p110_2_RadioGroup, c1_p110_3_RadioGroup, c1_p110_4_RadioGroup, c1_p110_5_RadioGroup,
               c1_p110_6_RadioGroup, c1_p110_7_RadioGroup, c1_p110_8_RadioGroup, c1_p110_9_RadioGroup, c1_p110_10_RadioGroup, c1_p110_11_RadioGroup,c1_p110_12_RadioGroup,c1_p110_13_RadioGroup,
               c1_p111_RadioGroup, c1_p112_1_RadioGroup, c1_p112_2_RadioGroup, c1_p112_3_RadioGroup, c1_p112_4_RadioGroup, c1_p112_5_RadioGroup, c1_p112_6_RadioGroup, c1_p112_7_RadioGroup, c1_p112_8_RadioGroup;
    EditText c1_p107_o_EditText, c1_p109_o_EditText, c1_p110_11_o_EditText, c1_p110_12_o_EditText, c1_p110_13_o_EditText,observaciones_edt;
    EditText c1_p108_1_o_EditText,c1_p108_2_o_EditText,c1_p108_3_o_EditText;

    LinearLayout m1_p107_linearlayout,m1_p108_linearlayout, m1_p109_linearlayout, m1_p110_linearlayout,
            m1_p111_linearlayout,m1_p111a_linearlayout, m1_p112_linearlayout;

    private int c1_p101=0;
    private String c1_p101_o="";
    private int c1_p107;
    private String c1_p107_o;
    private int c1_p108_1;
    private int c1_p108_2;
    private int c1_p108_3;
    private int c1_p108_4;
    private String c1_p108_1_o;
    private String c1_p108_2_o;
    private String c1_p108_3_o;
    private int c1_p109;
    private String c1_p109_o;
    private int c1_p110_1;
    private int c1_p110_2;
    private int c1_p110_3;
    private int c1_p110_4;
    private int c1_p110_5;
    private int c1_p110_6;
    private int c1_p110_7;
    private int c1_p110_8;
    private int c1_p110_9;
    private int c1_p110_10;
    private int c1_p110_11;
    private int c1_p110_12;
    private int c1_p110_13;
    private String c1_p110_13_o;
    private String c1_p110_12_o;
    private String c1_p110_11_o;
    private int c1_p111;
    private String c1_p111a;
    private int c1_p112_1;
    private int c1_p112_2;
    private int c1_p112_3;
    private int c1_p112_4;
    private int c1_p112_5;
    private int c1_p112_6;
    private int c1_p112_7;
    private int c1_p112_8;
    private String observaciones;


    public FragmentP108P113() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentP108P113(String idHogar, String idVivienda, Context context) {
        this.idHogar = idHogar;
        this.idVivienda = idVivienda;

        Data data = new Data(context);
        data.open();
        Modulo1V modulo1V = data.getModulo1V(idVivienda);
        if(modulo1V!=null) {
            if (!modulo1V.getC1_p101().equals("")) {
                c1_p101 = Integer.parseInt(modulo1V.getC1_p101());
            }
            if (!modulo1V.getC1_p101_o().equals("")) {
                c1_p101_o = modulo1V.getC1_p101_o();
            }
        }
        data.close();
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_p108_p113, container, false);
        titulo_P111_tv   = (TextView) rootView.findViewById(R.id.modulo1_p111_titulo_tv);
        titulo_P112_tv   = (TextView) rootView.findViewById(R.id.modulo1_p112_titulo_tv);

        c1_p107_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_107_radiogroup_C1_P107);
        c1_p107_o_EditText = (EditText) rootView.findViewById(R.id.mod1_107_edittext_C1_P107_O);
        c1_p108_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_108_radiogroup_C1_P108_1);
        c1_p108_2_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_108_radiogroup_C1_P108_2);
        c1_p108_3_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_108_radiogroup_C1_P108_3);
        c1_p108_4_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_108_radiogroup_C1_P108_4);
        c1_p108_1_o_EditText = (EditText) rootView.findViewById(R.id.mod1_108_edittext_C1_P108_1_O);
        c1_p108_2_o_EditText = (EditText) rootView.findViewById(R.id.mod1_108_edittext_C1_P108_2_O);
        c1_p108_3_o_EditText = (EditText) rootView.findViewById(R.id.mod1_108_edittext_C1_P108_3_O);
        c1_p109_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_109_radiogroup_C1_P109);
        c1_p109_o_EditText = (EditText) rootView.findViewById(R.id.mod1_109_edittext_C1_P109_O);
        c1_p110_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_110_radiogroup_C1_P110_1);
        c1_p110_2_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_110_radiogroup_C1_P110_2);
        c1_p110_3_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_110_radiogroup_C1_P110_3);
        c1_p110_4_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_110_radiogroup_C1_P110_4);
        c1_p110_5_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_110_radiogroup_C1_P110_5);
        c1_p110_6_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_110_radiogroup_C1_P110_6);
        c1_p110_7_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_110_radiogroup_C1_P110_7);
        c1_p110_8_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_110_radiogroup_C1_P110_8);
        c1_p110_9_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_110_radiogroup_C1_P110_9);
        c1_p110_10_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_110_radiogroup_C1_P110_10);
        c1_p110_11_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_110_radiogroup_C1_P110_11);

        c1_p110_12_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_110_radiogroup_C1_P110_12);
        c1_p110_13_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_110_radiogroup_C1_P110_13);

        c1_p110_11_o_EditText = (EditText) rootView.findViewById(R.id.mod1_110_edittext_C1_P110_11);
        c1_p110_12_o_EditText = (EditText) rootView.findViewById(R.id.mod1_110_edittext_C1_P110_12);
        c1_p110_13_o_EditText = (EditText) rootView.findViewById(R.id.mod1_110_edittext_C1_P110_13);
        c1_p111_RadioGroup   = (RadioGroup) rootView.findViewById(R.id.mod1_p111_radiogroup_C1_P111);
        c1_p111a_EditText    = (TextInputEditText) rootView.findViewById(R.id.mod1_111a_edittext_C1_P111a);
        c1_p112_1_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_112_radiogroup_C1_P112_1);
        c1_p112_2_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_112_radiogroup_C1_P112_2);
        c1_p112_3_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_112_radiogroup_C1_P112_3);
        c1_p112_4_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_112_radiogroup_C1_P112_4);
        c1_p112_5_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_112_radiogroup_C1_P112_5);
        c1_p112_6_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_112_radiogroup_C1_P112_6);
        c1_p112_7_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_112_radiogroup_C1_P112_7);
        c1_p112_8_RadioGroup = (RadioGroup) rootView.findViewById(R.id.mod1_112_radiogroup_C1_P112_8);
        observaciones_edt = (EditText) rootView.findViewById(R.id.edtObservaciones);

        m1_p107_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m1_p107);
        m1_p108_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m1_p108);
        m1_p109_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m1_p109);
        m1_p110_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m1_p110);
        m1_p111_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m1_p111);
        m1_p111a_linearlayout = (LinearLayout) rootView.findViewById(R.id.mod1_p111a_ly);
        m1_p112_linearlayout = (LinearLayout) rootView.findViewById(R.id.layout_m1_p112);


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UtilsMethodsInputs.setupEditText(c1_p107_o_EditText,getContext(),0,50);
        UtilsMethodsInputs.setupEditText(c1_p108_1_o_EditText,getContext(),0,50);
        UtilsMethodsInputs.setupEditText(c1_p108_2_o_EditText,getContext(),0,50);
        UtilsMethodsInputs.setupEditText(c1_p108_3_o_EditText,getContext(),0,50);
        UtilsMethodsInputs.setupEditText(c1_p109_o_EditText,getContext(),0,50);
        UtilsMethodsInputs.setupEditText(c1_p110_11_o_EditText,getContext(),0,50);
        UtilsMethodsInputs.setupEditText(c1_p110_12_o_EditText,getContext(),0,50);
        UtilsMethodsInputs.setupEditText(c1_p110_13_o_EditText,getContext(),0,50);
        UtilsMethodsInputs.setupEditText(c1_p111a_EditText,getContext(),2,2);
        UtilsMethodsInputs.setupEditText(observaciones_edt,getContext(),4,2000);


        c1_p107_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,8,c1_p107_o_EditText);
            }
        });
        c1_p108_1_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,5,c1_p108_1_o_EditText);
            }
        });
        c1_p108_2_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,5,c1_p108_2_o_EditText);
            }
        });
        c1_p108_3_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,5,c1_p108_3_o_EditText);
            }
        });
        c1_p109_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,4,c1_p109_o_EditText);
            }
        });
        c1_p110_11_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,1,c1_p110_11_o_EditText);
            }
        });
        c1_p110_12_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,1,c1_p110_12_o_EditText);
            }
        });
        c1_p110_13_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,1,c1_p110_13_o_EditText);
            }
        });
        c1_p111_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,1,c1_p111a_EditText);
            }
        });
        c1_p111a_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().equals("")){
                    if (Integer.parseInt(editable.toString()) < 1 || Integer.parseInt(editable.toString()) > 98 ){
                        c1_p111a_EditText.setText("");
                    }
                }
            }
        });

        String text111 = getString(R.string.modulo_1_p111, UtilsMethods.getPeriodoReferenciaMes(6));
        String text112 = getString(R.string.modulo_1_p112, UtilsMethods.getPeriodoReferenciaMesPasado());
        titulo_P111_tv.setText(text111);
        titulo_P112_tv.setText(text112);
        
        cargarDatos();
    }

    @Override
    public void guardarDatos() {
        Data data = new Data(context);
        data.open();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SQLConstantes.modulo1_h_c1_p107,c1_p107+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p107_o,c1_p107_o);
        contentValues.put(SQLConstantes.modulo1_h_c1_p108_1,c1_p108_1+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p108_2,c1_p108_2+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p108_3,c1_p108_3+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p108_4,c1_p108_4+"");
        Log.e("p108_1_o",""+c1_p108_1_o);
        Log.e("p108_2_o",""+c1_p108_2_o);
        Log.e("p108_3_o",""+c1_p108_3_o);

        contentValues.put(SQLConstantes.modulo1_h_c1_p108_1_o,c1_p108_1_o);
        contentValues.put(SQLConstantes.modulo1_h_c1_p108_2_o,c1_p108_2_o);
        contentValues.put(SQLConstantes.modulo1_h_c1_p108_3_o,c1_p108_3_o);
        contentValues.put(SQLConstantes.modulo1_h_c1_p109,c1_p109+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p109_o,c1_p109_o);
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_1,c1_p110_1+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_2,c1_p110_2+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_3,c1_p110_3+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_4,c1_p110_4+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_5,c1_p110_5+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_6,c1_p110_6+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_7,c1_p110_7+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_8,c1_p110_8+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_9,c1_p110_9+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_10,c1_p110_10+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_11,c1_p110_11+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_11_o,c1_p110_11_o);
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_12,c1_p110_12+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_12_o,c1_p110_12_o);
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_13,c1_p110_13+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p110_13_o,c1_p110_13_o);
        contentValues.put(SQLConstantes.modulo1_h_c1_p111,c1_p111+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p111a,c1_p111a);
        contentValues.put(SQLConstantes.modulo1_h_c1_p112_1,c1_p112_1+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p112_2,c1_p112_2+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p112_3,c1_p112_3+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p112_4,c1_p112_4+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p112_5,c1_p112_5+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p112_6,c1_p112_6+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p112_7,c1_p112_7+"");
        contentValues.put(SQLConstantes.modulo1_h_c1_p112_8,c1_p112_8+"");
        contentValues.put(SQLConstantes.modulo1_h_COB100B,observaciones);
        if(!data.existeElemento(getNombreTabla(),idHogar)){
            Modulo1H modulo1H = new Modulo1H();
            modulo1H.set_id(idHogar);
            modulo1H.setIdVivienda(idVivienda);
            data.insertarElemento(getNombreTabla(), modulo1H.toValues());
        }
        data.actualizarElemento(getNombreTabla(),contentValues,idHogar);
        data.close();
    }

    @Override
    public void llenarVariables() {
        c1_p107 = c1_p107_RadioGroup.indexOfChild(c1_p107_RadioGroup.findViewById(c1_p107_RadioGroup.getCheckedRadioButtonId()));
        c1_p107_o = c1_p107_o_EditText.getText().toString();
        c1_p108_1 = c1_p108_1_RadioGroup.indexOfChild(c1_p108_1_RadioGroup.findViewById(c1_p108_1_RadioGroup.getCheckedRadioButtonId()));
        c1_p108_2 = c1_p108_2_RadioGroup.indexOfChild(c1_p108_2_RadioGroup.findViewById(c1_p108_2_RadioGroup.getCheckedRadioButtonId()));
        c1_p108_3 = c1_p108_3_RadioGroup.indexOfChild(c1_p108_3_RadioGroup.findViewById(c1_p108_3_RadioGroup.getCheckedRadioButtonId()));
        c1_p108_4 = c1_p108_4_RadioGroup.indexOfChild(c1_p108_4_RadioGroup.findViewById(c1_p108_4_RadioGroup.getCheckedRadioButtonId()));
        c1_p108_1_o = c1_p108_1_o_EditText.getText().toString();
        c1_p108_2_o = c1_p108_2_o_EditText.getText().toString();
        c1_p108_3_o = c1_p108_3_o_EditText.getText().toString();
        c1_p109 = c1_p109_RadioGroup.indexOfChild(c1_p109_RadioGroup.findViewById(c1_p109_RadioGroup.getCheckedRadioButtonId()));
        c1_p109_o = c1_p109_o_EditText.getText().toString();
        c1_p110_1 = c1_p110_1_RadioGroup.indexOfChild(c1_p110_1_RadioGroup.findViewById(c1_p110_1_RadioGroup.getCheckedRadioButtonId()));
        c1_p110_2 = c1_p110_2_RadioGroup.indexOfChild(c1_p110_2_RadioGroup.findViewById(c1_p110_2_RadioGroup.getCheckedRadioButtonId()));
        c1_p110_3 = c1_p110_3_RadioGroup.indexOfChild(c1_p110_3_RadioGroup.findViewById(c1_p110_3_RadioGroup.getCheckedRadioButtonId()));
        c1_p110_4 = c1_p110_4_RadioGroup.indexOfChild(c1_p110_4_RadioGroup.findViewById(c1_p110_4_RadioGroup.getCheckedRadioButtonId()));
        c1_p110_5 = c1_p110_5_RadioGroup.indexOfChild(c1_p110_5_RadioGroup.findViewById(c1_p110_5_RadioGroup.getCheckedRadioButtonId()));
        c1_p110_6 = c1_p110_6_RadioGroup.indexOfChild(c1_p110_6_RadioGroup.findViewById(c1_p110_6_RadioGroup.getCheckedRadioButtonId()));
        c1_p110_7 = c1_p110_7_RadioGroup.indexOfChild(c1_p110_7_RadioGroup.findViewById(c1_p110_7_RadioGroup.getCheckedRadioButtonId()));
        c1_p110_8 = c1_p110_8_RadioGroup.indexOfChild(c1_p110_8_RadioGroup.findViewById(c1_p110_8_RadioGroup.getCheckedRadioButtonId()));
        c1_p110_9 = c1_p110_9_RadioGroup.indexOfChild(c1_p110_9_RadioGroup.findViewById(c1_p110_9_RadioGroup.getCheckedRadioButtonId()));
        c1_p110_10 = c1_p110_10_RadioGroup.indexOfChild(c1_p110_10_RadioGroup.findViewById(c1_p110_10_RadioGroup.getCheckedRadioButtonId()));
        c1_p110_11 = c1_p110_11_RadioGroup.indexOfChild(c1_p110_11_RadioGroup.findViewById(c1_p110_11_RadioGroup.getCheckedRadioButtonId()));
        c1_p110_12 = c1_p110_12_RadioGroup.indexOfChild(c1_p110_12_RadioGroup.findViewById(c1_p110_12_RadioGroup.getCheckedRadioButtonId()));
        c1_p110_13 = c1_p110_13_RadioGroup.indexOfChild(c1_p110_13_RadioGroup.findViewById(c1_p110_13_RadioGroup.getCheckedRadioButtonId()));
        c1_p110_11_o = c1_p110_11_o_EditText.getText().toString();
        c1_p110_12_o = c1_p110_12_o_EditText.getText().toString();
        c1_p110_13_o = c1_p110_13_o_EditText.getText().toString();
        c1_p111   = c1_p111_RadioGroup.indexOfChild(c1_p111_RadioGroup.findViewById(c1_p111_RadioGroup.getCheckedRadioButtonId()));
        c1_p111a  = c1_p111a_EditText.getText().toString();
        c1_p112_1 = c1_p112_1_RadioGroup.indexOfChild(c1_p112_1_RadioGroup.findViewById(c1_p112_1_RadioGroup.getCheckedRadioButtonId()));
        c1_p112_2 = c1_p112_2_RadioGroup.indexOfChild(c1_p112_2_RadioGroup.findViewById(c1_p112_2_RadioGroup.getCheckedRadioButtonId()));
        c1_p112_3 = c1_p112_3_RadioGroup.indexOfChild(c1_p112_3_RadioGroup.findViewById(c1_p112_3_RadioGroup.getCheckedRadioButtonId()));
        c1_p112_4 = c1_p112_4_RadioGroup.indexOfChild(c1_p112_4_RadioGroup.findViewById(c1_p112_4_RadioGroup.getCheckedRadioButtonId()));
        c1_p112_5 = c1_p112_5_RadioGroup.indexOfChild(c1_p112_5_RadioGroup.findViewById(c1_p112_5_RadioGroup.getCheckedRadioButtonId()));
        c1_p112_6 = c1_p112_6_RadioGroup.indexOfChild(c1_p112_6_RadioGroup.findViewById(c1_p112_6_RadioGroup.getCheckedRadioButtonId()));
        c1_p112_7 = c1_p112_7_RadioGroup.indexOfChild(c1_p112_7_RadioGroup.findViewById(c1_p112_7_RadioGroup.getCheckedRadioButtonId()));
        c1_p112_8 = c1_p112_8_RadioGroup.indexOfChild(c1_p112_8_RadioGroup.findViewById(c1_p112_8_RadioGroup.getCheckedRadioButtonId()));
        observaciones = observaciones_edt.getText().toString().trim();
    }

    @Override
    public void cargarDatos() {
        Data data = new Data(context);
        data.open();
        if (data.existeElemento(getNombreTabla(),idHogar)){
            Modulo1H modulo1H = data.getModulo1H(idHogar);
            if(!modulo1H.getC1_p107().equals("-1") && !modulo1H.getC1_p107().equals(""))((RadioButton)c1_p107_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p107()))).setChecked(true);
            c1_p107_o_EditText.setText(modulo1H.getC1_p107_o());

            if(!modulo1H.getC1_p108_1().equals("-1") && !modulo1H.getC1_p108_1().equals(""))((RadioButton)c1_p108_1_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p108_1()))).setChecked(true);
            if(!modulo1H.getC1_p108_2().equals("-1") && !modulo1H.getC1_p108_2().equals(""))((RadioButton)c1_p108_2_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p108_2()))).setChecked(true);
            if(!modulo1H.getC1_p108_3().equals("-1") && !modulo1H.getC1_p108_3().equals(""))((RadioButton)c1_p108_3_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p108_3()))).setChecked(true);
            if(!modulo1H.getC1_p108_4().equals("-1") && !modulo1H.getC1_p108_4().equals(""))((RadioButton)c1_p108_4_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p108_4()))).setChecked(true);
            c1_p108_1_o_EditText.setText(modulo1H.getC1_p108_1_o());
            c1_p108_2_o_EditText.setText(modulo1H.getC1_p108_2_o());
            c1_p108_3_o_EditText.setText(modulo1H.getC1_p108_3_o());

            if(!modulo1H.getC1_p109().equals("-1") && !modulo1H.getC1_p109().equals(""))((RadioButton)c1_p109_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p109()))).setChecked(true);
            c1_p109_o_EditText.setText(modulo1H.getC1_p109_o());

            if(!modulo1H.getC1_p110_1().equals("-1") && !modulo1H.getC1_p110_1().equals(""))((RadioButton)c1_p110_1_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p110_1()))).setChecked(true);
            if(!modulo1H.getC1_p110_2().equals("-1") && !modulo1H.getC1_p110_2().equals(""))((RadioButton)c1_p110_2_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p110_2()))).setChecked(true);
            if(!modulo1H.getC1_p110_3().equals("-1") && !modulo1H.getC1_p110_3().equals(""))((RadioButton)c1_p110_3_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p110_3()))).setChecked(true);
            if(!modulo1H.getC1_p110_4().equals("-1") && !modulo1H.getC1_p110_4().equals(""))((RadioButton)c1_p110_4_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p110_4()))).setChecked(true);
            if(!modulo1H.getC1_p110_5().equals("-1") && !modulo1H.getC1_p110_5().equals(""))((RadioButton)c1_p110_5_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p110_5()))).setChecked(true);
            if(!modulo1H.getC1_p110_6().equals("-1") && !modulo1H.getC1_p110_6().equals(""))((RadioButton)c1_p110_6_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p110_6()))).setChecked(true);
            if(!modulo1H.getC1_p110_7().equals("-1") && !modulo1H.getC1_p110_7().equals(""))((RadioButton)c1_p110_7_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p110_7()))).setChecked(true);
            if(!modulo1H.getC1_p110_8().equals("-1") && !modulo1H.getC1_p110_8().equals(""))((RadioButton)c1_p110_8_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p110_8()))).setChecked(true);
            if(!modulo1H.getC1_p110_9().equals("-1") && !modulo1H.getC1_p110_9().equals(""))((RadioButton)c1_p110_9_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p110_9()))).setChecked(true);
            if(!modulo1H.getC1_p110_10().equals("-1") && !modulo1H.getC1_p110_10().equals(""))((RadioButton)c1_p110_10_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p110_10()))).setChecked(true);
            if(!modulo1H.getC1_p110_11().equals("-1") && !modulo1H.getC1_p110_11().equals(""))((RadioButton)c1_p110_11_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p110_11()))).setChecked(true);
            if(!modulo1H.getC1_p110_12().equals("-1") && !modulo1H.getC1_p110_12().equals(""))((RadioButton)c1_p110_12_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p110_12()))).setChecked(true);
            if(!modulo1H.getC1_p110_13().equals("-1") && !modulo1H.getC1_p110_13().equals(""))((RadioButton)c1_p110_13_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p110_13()))).setChecked(true);

            c1_p110_11_o_EditText.setText(modulo1H.getC1_p110_11_o());
            c1_p110_12_o_EditText.setText(modulo1H.getC1_p110_12_o());
            c1_p110_13_o_EditText.setText(modulo1H.getC1_p110_13_o());

            if(!modulo1H.getC1_p111().equals("-1") && !modulo1H.getC1_p111().equals(""))((RadioButton)c1_p111_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p111()))).setChecked(true);
            c1_p111a_EditText.setText(modulo1H.getC1_p111a());

            if(!modulo1H.getC1_p112_1().equals("-1") && !modulo1H.getC1_p112_1().equals(""))((RadioButton)c1_p112_1_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p112_1()))).setChecked(true);
            if(!modulo1H.getC1_p112_2().equals("-1") && !modulo1H.getC1_p112_2().equals(""))((RadioButton)c1_p112_2_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p112_2()))).setChecked(true);
            if(!modulo1H.getC1_p112_3().equals("-1") && !modulo1H.getC1_p112_3().equals(""))((RadioButton)c1_p112_3_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p112_3()))).setChecked(true);
            if(!modulo1H.getC1_p112_4().equals("-1") && !modulo1H.getC1_p112_4().equals(""))((RadioButton)c1_p112_4_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p112_4()))).setChecked(true);
            if(!modulo1H.getC1_p112_5().equals("-1") && !modulo1H.getC1_p112_5().equals(""))((RadioButton)c1_p112_5_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p112_5()))).setChecked(true);
            if(!modulo1H.getC1_p112_6().equals("-1") && !modulo1H.getC1_p112_6().equals(""))((RadioButton)c1_p112_6_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p112_6()))).setChecked(true);
            if(!modulo1H.getC1_p112_7().equals("-1") && !modulo1H.getC1_p112_7().equals(""))((RadioButton)c1_p112_7_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p112_7()))).setChecked(true);
            if(!modulo1H.getC1_p112_8().equals("-1") && !modulo1H.getC1_p112_8().equals(""))((RadioButton)c1_p112_8_RadioGroup.getChildAt(Integer.parseInt(modulo1H.getC1_p112_8()))).setChecked(true);
            observaciones_edt.setText(modulo1H.getCOB100B());


        }
        data.close();
    }

    @Override
    public void llenarVista() {

    }

    @Override
    public boolean validarDatos() {
        llenarVariables();
        if (c1_p107 == -1){mostrarMensaje("PREGUNTA 107: DEBE MARCAR UNA OPCIÓN"); return false;}
        else{
            if (c1_p107 == 8){
                if (c1_p107_o.trim().equals("")){mostrarMensaje("PREGUNTA 107: DEBE ESPECIFICAR");return false;}
                if (!c1_p107_o.trim().equals("") && c1_p107_o.length()<3){mostrarMensaje("PREGUNTA 107: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}
            }
        }

        if (c1_p108_1 == -1){mostrarMensaje("PREGUNTA 108A: DEBE MARCAR UNA OPCIÓN"); return false;}
        else{
            if (c1_p108_1 == 5){
                if (c1_p108_1_o.trim().equals("")){mostrarMensaje("PREGUNTA 108A: DEBE ESPECIFICAR");return false;}
                if (!c1_p108_1_o.trim().equals("") && c1_p108_1_o.length()<3 ){mostrarMensaje("PREGUNTA 108A: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}
            }
        }
        if (c1_p108_2 == -1){mostrarMensaje("PREGUNTA 108B: DEBE MARCAR UNA OPCIÓN"); return false;}
        else{
            if (c1_p108_2 == 5){
                if (c1_p108_2_o.trim().equals("")){mostrarMensaje("PREGUNTA 108B: DEBE ESPECIFICAR");return false;}
                if (!c1_p108_2_o.trim().equals("") && c1_p108_2_o.length()<3 ){mostrarMensaje("PREGUNTA 108B: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}
            }
        }
        if (c1_p108_3 == -1){mostrarMensaje("PREGUNTA 108C: DEBE MARCAR UNA OPCIÓN"); return false;}
        else{
            if (c1_p108_3 == 5){
                if (c1_p108_3_o.trim().equals("")){mostrarMensaje("PREGUNTA 108C: DEBE ESPECIFICAR");return false;}
                if (!c1_p108_3_o.trim().equals("") && c1_p108_3_o.length()<3 ){mostrarMensaje("PREGUNTA 108C: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}
            }
        }

        if (c1_p108_4 == -1){mostrarMensaje("PREGUNTA 108D: DEBE MARCAR UNA OPCIÓN"); return false;}

        if (c1_p109 == -1){mostrarMensaje("PREGUNTA 109: DEBE MARCAR UNA OPCIÓN"); return false;}
        else{
            if (c1_p109 == 4){
                if (c1_p109_o.trim().equals("")){mostrarMensaje("PREGUNTA 109: DEBE ESPECIFICAR");return false;}
                if (!c1_p109_o.trim().equals("") && c1_p109_o.length()<3 ){mostrarMensaje("PREGUNTA 109: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}
            }
        }

        if (c1_p110_1 == -1){mostrarMensaje("PREGUNTA 110-1: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p110_2 == -1){mostrarMensaje("PREGUNTA 110-2: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p110_3 == -1){mostrarMensaje("PREGUNTA 110-3: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p110_4 == -1){mostrarMensaje("PREGUNTA 110-4: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p110_5 == -1){mostrarMensaje("PREGUNTA 110-5: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p110_6 == -1){mostrarMensaje("PREGUNTA 110-6: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p110_7 == -1){mostrarMensaje("PREGUNTA 110-7: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p110_8 == -1){mostrarMensaje("PREGUNTA 110-8: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p110_9 == -1){mostrarMensaje("PREGUNTA 110-9: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p110_10 == -1){mostrarMensaje("PREGUNTA 110-10: DEBE MARCAR UNA OPCIÓN"); return false;}
       /* else{
            if (c1_p110_9 == 1){
                if (c1_p110_9_o.trim().equals("")){mostrarMensaje("PREGUNTA 110-9: DEBE ESPECIFICAR");return false;}
                if (!c1_p110_9_o.trim().equals("") && c1_p110_9_o.length()<3){mostrarMensaje("PREGUNTA 110-9: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}
            }
        }
        if (c1_p110_10 == -1){mostrarMensaje("PREGUNTA 110-10: DEBE MARCAR UNA OPCIÓN"); return false;}
        else{
            if (c1_p110_10 == 1){
                if (c1_p110_10_o.trim().equals("")){mostrarMensaje("PREGUNTA 110-10: DEBE ESPECIFICAR");return false;}
                if (!c1_p110_10_o.trim().equals("") && c1_p110_10_o.length()<3){mostrarMensaje("PREGUNTA 110-10: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}
            }
        }*/
        if (c1_p110_11 == -1){mostrarMensaje("PREGUNTA 110-11: DEBE MARCAR UNA OPCIÓN"); return false;}
        else{
            if (c1_p110_11 == 1){
                if (c1_p110_11_o.trim().equals("")){mostrarMensaje("PREGUNTA 110-11: DEBE ESPECIFICAR");return false;}
                if (!c1_p110_11_o.trim().equals("") && c1_p110_11_o.length()<3){mostrarMensaje("PREGUNTA 110-11: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}

            }
        }
        if (c1_p110_12 == -1){mostrarMensaje("PREGUNTA 110-12: DEBE MARCAR UNA OPCIÓN"); return false;}
        else{
            if (c1_p110_12 == 1){
                if (c1_p110_12_o.trim().equals("")){mostrarMensaje("PREGUNTA 110-12: DEBE ESPECIFICAR");return false;}
                if (!c1_p110_12_o.trim().equals("") && c1_p110_12_o.length()<3){mostrarMensaje("PREGUNTA 110-12: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}

            }
        }
        if (c1_p110_13 == -1){mostrarMensaje("PREGUNTA 110-13: DEBE MARCAR UNA OPCIÓN"); return false;}
        else{
            if (c1_p110_13 == 1){
                if (c1_p110_13_o.trim().equals("")){mostrarMensaje("PREGUNTA 110-13: DEBE ESPECIFICAR");return false;}
                if (!c1_p110_13_o.trim().equals("") && c1_p110_13_o.length()<3){mostrarMensaje("PREGUNTA 110-13: EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES");return false;}

            }
        }

        if (c1_p111 == -1){mostrarMensaje("PREGUNTA 111: DEBE MARCAR UNA OPCIÓN"); return false;}
        else{
            if (c1_p111 == 1){
                if (c1_p111a.trim().equals("")){mostrarMensaje("PREGUNTA 111A: DEBE ESPECIFICAR");return false;}
            }
        }
        if (c1_p112_1 == -1){mostrarMensaje("PREGUNTA 112-1: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p112_2 == -1){mostrarMensaje("PREGUNTA 112-2: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p112_3 == -1){mostrarMensaje("PREGUNTA 112-3: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p112_4 == -1){mostrarMensaje("PREGUNTA 112-4: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p112_5 == -1){mostrarMensaje("PREGUNTA 112-5: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p112_6 == -1){mostrarMensaje("PREGUNTA 112-6: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p112_7 == -1){mostrarMensaje("PREGUNTA 112-7: DEBE MARCAR UNA OPCIÓN"); return false;}
        if (c1_p112_8 == -1){mostrarMensaje("PREGUNTA 112-8: DEBE MARCAR UNA OPCIÓN"); return false;}

//        if (c1_p112_1 != -1 && c1_p112_2 != -1 && c1_p112_3 != -1 && c1_p112_4 != -1 && c1_p112_5 != -1 && c1_p112_6 != -1 && c1_p112_7 != -1 && c1_p112_8 != -1 ) {
//            mostrarMensaje("SEÑOR/A, SEÑORITA: AHORA ME GUSTARÍA HACERLE UNAS PREGUNTAS ADICIONALES PARA SABER QUE LE PARECIERON ESTAS PREGUNTAS");
//        }

        if ((c1_p107 == 2 ||c1_p107 == 3 ||c1_p107 == 4) && c1_p101 ==8  && (c1_p101_o.equals("HOSTAL") ||c1_p101_o.equals("HOSPEDAJE") ||c1_p101_o.equals("ALBERGUE"))){mostrarMensaje("PREGUNTA 107: EL TIPO DE VIVIENDA (P101) NO CORRESPONDE CON LA OCUPACIÓN DE LA VIVIENDA (P107)"); return true;}


        if (c1_p110_2 == 2 && (c1_p109 ==2 || c1_p109 ==3)){mostrarMensaje("PREGUNTA 110-2: UTILIZAN GAS PARA COCINAR SUS ALIMENTOS (P109) PERO NO TIENEN COCINA A GAS (P110)"); return true;}


        return true;
    }

    @Override
    public String getNombreTabla() {
        return SQLConstantes.tablamodulo1h;
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

    public void mostrarMensajeCognitiva(String m){
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

    public void mostrarTeclado(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}
