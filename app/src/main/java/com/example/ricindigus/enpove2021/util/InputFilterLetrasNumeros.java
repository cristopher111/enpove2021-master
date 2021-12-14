package com.example.ricindigus.enpove2021.util;

import android.text.InputFilter;
import android.text.Spanned;

public class InputFilterLetrasNumeros implements InputFilter {
    @Override
    public CharSequence filter(CharSequence src, int start,
                               int end, Spanned dst, int dstart, int dend) {
        return src.toString().replaceAll("[^A-Za-z0-9 ]", "");
    }
//    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//        // Only keep characters that are alphanumeric
//        StringBuilder builder = new StringBuilder();
//        for (int i = start; i < end; i++) {
//            char c = source.charAt(i);
//            if (Character.isLetterOrDigit(c)) {
//                builder.append(c); }
//        }
//        // If all characters are valid, return null, otherwise only return the filtered characters
//        boolean allCharactersValid = (builder.length() == end - start);
//        return allCharactersValid ? null : builder.toString();
//    }
}
