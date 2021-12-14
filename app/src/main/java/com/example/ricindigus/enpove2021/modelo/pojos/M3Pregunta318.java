package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class M3Pregunta318 {
    private String _id;
    private String idEncuestado;
    private String id_vivienda;
    private String numero;
    private String c3_p318_f;
    private String c3_p318_s;
    private String c3_p318_e;
    private String c3_p318_p;

    public M3Pregunta318() {
        _id = "";
        idEncuestado = "";
        id_vivienda = "";
        numero = "";
        c3_p318_f = "";
        c3_p318_s = "";
        c3_p318_e = "";
        c3_p318_p = "";
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

    public String getIdEncuestado() {
        return idEncuestado;
    }

    public void setIdEncuestado(String idEncuestado) {
        this.idEncuestado = idEncuestado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getC3_p318_f() {
        return c3_p318_f;
    }

    public void setC3_p318_f(String c3_p318_f) {
        this.c3_p318_f = c3_p318_f;
    }

    public String getC3_p318_s() {
        return c3_p318_s;
    }

    public void setC3_p318_s(String c3_p318_s) {
        this.c3_p318_s = c3_p318_s;
    }

    public String getC3_p318_e() {
        return c3_p318_e;
    }

    public void setC3_p318_e(String c3_p318_e) {
        this.c3_p318_e = c3_p318_e;
    }

    public String getC3_p318_p() {
        return c3_p318_p;
    }

    public void setC3_p318_p(String c3_p318_p) {
        this.c3_p318_p = c3_p318_p;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo3_p318_id,_id);
        contentValues.put(SQLConstantes.modulo3_p318_idEncuestado,idEncuestado);
        contentValues.put(SQLConstantes.modulo3_p318_id_vivienda,id_vivienda);
        contentValues.put(SQLConstantes.modulo3_p318_numero,numero);
        contentValues.put(SQLConstantes.modulo3_c3_p318_f,c3_p318_f);
        contentValues.put(SQLConstantes.modulo3_c3_p318_s,c3_p318_s);
        contentValues.put(SQLConstantes.modulo3_c3_p318_e,c3_p318_e);
        contentValues.put(SQLConstantes.modulo3_c3_p318_p,c3_p318_p);
        return contentValues;
    }
}
