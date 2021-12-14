package com.example.ricindigus.enpove2021.util;

public class StringDecorator {
    private final String componente;

    public StringDecorator(String componente) {
        this.componente = componente;
    }

    public int toInt() {
        return toInt(null);
    }

    //Nulo y vacio --> 0
    //Otro caso --> NumberFormatException
    public int toInt(String exceptoPatron) {
        String numero = componente;
        if(numero!=null) {
            numero = numero.trim();
            if(!numero.isEmpty() && (exceptoPatron==null || !numero.matches(exceptoPatron) )) {
                try {
                    return Integer.parseInt(numero);
                }
                catch(NumberFormatException e) {
                    return (int)Double.parseDouble(numero);
                }
            }
        }
        return 0;
    }

    public String completarIzquierda(String relleno, int tamanho) {
        String texto = componente;
        if(texto==null)
            texto="";
        return new StringDecorator(relleno).repetir(tamanho-texto.length()) + texto;
    }

    public String repetir(int nVeces) {
        if(nVeces>0) {
            return new String(new char[nVeces]).replace("\0", componente);
        }
        return "";
    }
}

