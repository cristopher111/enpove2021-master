package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class POJOFragmentVivienda {
    private String _id;
    private String caratula;
    private String hogares;

    public POJOFragmentVivienda(String _id) {
        this._id= _id;
        caratula="1";
        hogares = "0";
    }

    public POJOFragmentVivienda() {
        this._id= "";
        caratula="";
        hogares = "";
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCaratula() {
        return caratula;
    }

    public void setCaratula(String caratula) {
        this.caratula = caratula;
    }

    public String getHogares() {
        return hogares;
    }

    public void setHogares(String hogares) {
        this.hogares = hogares;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.fragments_vivienda_id ,_id);
        contentValues.put(SQLConstantes.fragments_vivienda_caratula ,caratula);
        contentValues.put(SQLConstantes.fragments_vivienda_hogares ,hogares);
        return contentValues;
    }
}
