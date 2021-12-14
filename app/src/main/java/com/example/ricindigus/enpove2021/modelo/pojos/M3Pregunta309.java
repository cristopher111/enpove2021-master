package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class M3Pregunta309 {
    private String _id;
    private String id_encuestado;
    private String id_vivienda;
    private String numero;
    private String c3_p309_p;
    private String c3_p309_p_nom;
    private String c3_p309_c;
    private String c3_p309_mod;
    private String c3_p309_m;
    private String c3_p309_a;
    private String c3_p309_m_cod;
    private String c3_p309_a_cod;



    public M3Pregunta309() {
        _id = "";
        id_encuestado = "";
        id_vivienda = "";
        numero = "";
        c3_p309_p = "";
        c3_p309_p_nom = "";
        c3_p309_c = "";
        c3_p309_mod = "";
        c3_p309_m = "";
        c3_p309_a = "";
        c3_p309_m_cod = "";
        c3_p309_a_cod = "";
    }

    public String getId_vivienda() {
        return id_vivienda;
    }

    public void setId_vivienda(String id_vivienda) {
        this.id_vivienda = id_vivienda;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId_encuestado() {
        return id_encuestado;
    }

    public void setId_encuestado(String id_encuestado) {
        this.id_encuestado = id_encuestado;
    }

    public String getC3_p309_p() {
        return c3_p309_p;
    }

    public void setC3_p309_p(String c3_p309_p) {
        this.c3_p309_p = c3_p309_p;
    }

    public String getC3_p309_c() {
        return c3_p309_c;
    }

    public void setC3_p309_c(String c3_p309_c) {
        this.c3_p309_c = c3_p309_c;
    }

    public String getC3_p309_mod() {
        return c3_p309_mod;
    }

    public void setC3_p309_mod(String c3_p309_mod) {
        this.c3_p309_mod = c3_p309_mod;
    }

    public String getC3_p309_m() {
        return c3_p309_m;
    }

    public void setC3_p309_m(String c3_p309_m) {
        this.c3_p309_m = c3_p309_m;
    }

    public String getC3_p309_a() {
        return c3_p309_a;
    }

    public void setC3_p309_a(String c3_p309_a) {
        this.c3_p309_a = c3_p309_a;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getC3_p309_p_nom() {
        return c3_p309_p_nom;
    }

    public void setC3_p309_p_nom(String c3_p309_p_nom) {
        this.c3_p309_p_nom = c3_p309_p_nom;
    }

    public String getC3_p309_m_cod() {
        return c3_p309_m_cod;
    }

    public void setC3_p309_m_cod(String c3_p309_m_cod) {
        this.c3_p309_m_cod = c3_p309_m_cod;
    }

    public String getC3_p309_a_cod() {
        return c3_p309_a_cod;
    }

    public void setC3_p309_a_cod(String c3_p309_a_cod) {
        this.c3_p309_a_cod = c3_p309_a_cod;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo3_p309_id,_id);
        contentValues.put(SQLConstantes.modulo3_p309_id_encuestado,id_encuestado);
        contentValues.put(SQLConstantes.modulo3_p309_id_vivienda,id_vivienda);
        contentValues.put(SQLConstantes.modulo3_p309_numero,numero);
        contentValues.put(SQLConstantes.modulo3_c3_p309_p,c3_p309_p);
        contentValues.put(SQLConstantes.modulo3_c3_p309_p_nom,c3_p309_p_nom);
        contentValues.put(SQLConstantes.modulo3_c3_p309_c,c3_p309_c);
        contentValues.put(SQLConstantes.modulo3_c3_p309_mod,c3_p309_mod);
        contentValues.put(SQLConstantes.modulo3_c3_p309_m,c3_p309_m);
        contentValues.put(SQLConstantes.modulo3_c3_p309_a,c3_p309_a);
        contentValues.put(SQLConstantes.modulo3_c3_p309_m_cod,c3_p309_m_cod);
        contentValues.put(SQLConstantes.modulo3_c3_p309_a_cod,c3_p309_a_cod);
        return contentValues;
    }
}
