package com.example.ricindigus.enpove2021.util;

import android.text.InputFilter;
import android.text.Spanned;

public class InputFilterSoloLetras implements InputFilter {
    @Override
    public CharSequence filter(CharSequence src, int start,
                               int end, Spanned dst, int dstart, int dend) {
        if(src.toString().matches("[a-zA-Z\u00f1\u00d1 ]+")){
            return src;
        }
        String letras=src.toString();

        return letras.replaceAll("[^A-Za-z\u00f1\u00d1 ]", "");//src.subSequence(start,end-1);//src.substring(1,src.length()-1)
    }
}