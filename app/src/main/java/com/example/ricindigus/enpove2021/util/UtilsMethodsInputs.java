package com.example.ricindigus.enpove2021.util;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilsMethodsInputs {

    public static void setupEditText(final EditText editText, final Context context, int tipo, int longitud){
        switch (tipo){
            case 0://Texto (Text-(A:Z))
                editText.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(longitud), new InputFilterSoloLetras()});
                editText.setInputType(InputType.TYPE_CLASS_TEXT);break;
            case 1://Texto y Numeros (Text-(A:Z);(0:9))
                editText.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(longitud), new InputFilterLetrasNumeros()});
                editText.setInputType(InputType.TYPE_CLASS_TEXT);break;
            case 2://Numeros (NumberPassword-(0:9))
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(longitud)});
                editText.setTransformationMethod(new NumericKeyBoardTransformationMethod());break;
            case 3://Telefono (Phone -(0-9);*;#
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(longitud)});
                editText.setInputType(InputType.TYPE_CLASS_PHONE);break;
            case 4://Texto (Text-(A:Z)-simbolos)
                editText.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(longitud)});
                editText.setInputType(InputType.TYPE_CLASS_TEXT);break;
        }
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    hideTeclado(editText,context);
                    return true;
                }
                return false;
            }
        });
    }

    public static void setupRadioGroupEspecifique(RadioGroup group, int checkedId, int opcionEsp, EditText editTextEspecifique) {
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

    public static void setupSpinnerEspecifique(int item, int opcionEsp, EditText editTextEspecifique) {
        if(item == opcionEsp){
            editTextEspecifique.setBackgroundResource(R.drawable.input_text_enabled);
            editTextEspecifique.setEnabled(true);
        }else{
            editTextEspecifique.setText("");
            editTextEspecifique.setBackgroundResource(R.drawable.input_text_disabled);
            editTextEspecifique.setEnabled(false);
        }
    }

    public static void setupSpinnerEspecifique2(int item, int opcionEsp1, int opcionEsp2, EditText editTextEspecifique) {
        if(item == opcionEsp1 || item == opcionEsp2  ){
            editTextEspecifique.setBackgroundResource(R.drawable.input_text_enabled);
            editTextEspecifique.setEnabled(true);
        }else{
            editTextEspecifique.setText("");
            editTextEspecifique.setBackgroundResource(R.drawable.input_text_disabled);
            editTextEspecifique.setEnabled(false);
        }
    }

    public static void setupRadioGroupVisibleLayout(RadioGroup group, int checkedId, int opcionEsp, LinearLayout linearLayout) {
        int seleccionado = group.indexOfChild(group.findViewById(checkedId));
        if(seleccionado == opcionEsp){
            linearLayout.setVisibility(View.VISIBLE);
        }else{
            linearLayout.setVisibility(View.GONE);
        }
    }

    public static void hideTeclado(View view,Context context){
        InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showMessage(String message,Context context){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void loadSpinner(List<String> datos, Spinner spinner, Context context){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item,datos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


}
