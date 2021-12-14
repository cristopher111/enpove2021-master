package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class ResultadoResidente {
    private String _id;
    private String id_hogar;
    private String id_vivienda;
    private String fecha_dd;
    private String fecha_mm;
    private String fecha_aa;
    private String hor_ini;
    private String min_ini;
    private String hor_fin;
    private String min_fin;
    private String resultado_entrevista;
    private String resultado_entrevista_o;
    private String tipo_entrevista;
    private String tipo_entrevista_o;
    private String donde_entrevista;
    private String donde_entrevista_o;

    public ResultadoResidente() {
        _id = "";
        id_hogar = "";
        id_vivienda = "";
        fecha_dd = "";
        fecha_mm = "";
        fecha_aa = "";
        hor_ini = "";
        min_ini = "";
        hor_fin = "";
        min_fin = "";
        resultado_entrevista = "";
        resultado_entrevista_o = "";
        tipo_entrevista = "";
        tipo_entrevista_o = "";
        donde_entrevista = "";
        donde_entrevista_o = "";
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId_hogar() {
        return id_hogar;
    }

    public void setId_hogar(String id_hogar) {
        this.id_hogar = id_hogar;
    }

    public String getId_vivienda() {
        return id_vivienda;
    }

    public void setId_vivienda(String id_vivienda) {
        this.id_vivienda = id_vivienda;
    }

    public String getFecha_dd() {
        return fecha_dd;
    }

    public void setFecha_dd(String fecha_dd) {
        this.fecha_dd = fecha_dd;
    }

    public String getFecha_mm() {
        return fecha_mm;
    }

    public void setFecha_mm(String fecha_mm) {
        this.fecha_mm = fecha_mm;
    }

    public String getFecha_aa() {
        return fecha_aa;
    }

    public void setFecha_aa(String fecha_aa) {
        this.fecha_aa = fecha_aa;
    }

    public String getHor_ini() {
        return hor_ini;
    }

    public void setHor_ini(String hor_ini) {
        this.hor_ini = hor_ini;
    }

    public String getMin_ini() {
        return min_ini;
    }

    public void setMin_ini(String min_ini) {
        this.min_ini = min_ini;
    }

    public String getHor_fin() {
        return hor_fin;
    }

    public void setHor_fin(String hor_fin) {
        this.hor_fin = hor_fin;
    }

    public String getMin_fin() {
        return min_fin;
    }

    public void setMin_fin(String min_fin) {
        this.min_fin = min_fin;
    }

    public String getResultado_entrevista() {
        return resultado_entrevista;
    }

    public void setResultado_entrevista(String resultado_entrevista) {
        this.resultado_entrevista = resultado_entrevista;
    }

    public String getResultado_entrevista_o() {
        return resultado_entrevista_o;
    }

    public void setResultado_entrevista_o(String resultado_entrevista_o) {
        this.resultado_entrevista_o = resultado_entrevista_o;
    }

    public String getTipo_entrevista() {
        return tipo_entrevista;
    }

    public void setTipo_entrevista(String tipo_entrevista) {
        this.tipo_entrevista = tipo_entrevista;
    }

    public String getTipo_entrevista_o() {
        return tipo_entrevista_o;
    }

    public void setTipo_entrevista_o(String tipo_entrevista_o) {
        this.tipo_entrevista_o = tipo_entrevista_o;
    }

    public String getDonde_entrevista() {
        return donde_entrevista;
    }

    public void setDonde_entrevista(String donde_entrevista) {
        this.donde_entrevista = donde_entrevista;
    }

    public String getDonde_entrevista_o() {
        return donde_entrevista_o;
    }

    public void setDonde_entrevista_o(String donde_entrevista_o) {
        this.donde_entrevista_o = donde_entrevista_o;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.resultado_residente_id,_id);
        contentValues.put(SQLConstantes.resultado_residente_id_hogar, id_hogar);
        contentValues.put(SQLConstantes.resultado_residente_id_vivienda, id_vivienda);
        contentValues.put(SQLConstantes.resultado_residente_fecha_dd,fecha_dd);
        contentValues.put(SQLConstantes.resultado_residente_fecha_mm,fecha_mm);
        contentValues.put(SQLConstantes.resultado_residente_fecha_aa,fecha_aa);
        contentValues.put(SQLConstantes.resultado_residente_hor_ini,hor_ini);
        contentValues.put(SQLConstantes.resultado_residente_min_ini,min_ini);
        contentValues.put(SQLConstantes.resultado_residente_hor_fin,hor_fin);
        contentValues.put(SQLConstantes.resultado_residente_min_fin,min_fin);
        contentValues.put(SQLConstantes.resultado_residente_resultado_entrevista,resultado_entrevista);
        contentValues.put(SQLConstantes.resultado_residente_resultado_entrevista_o,resultado_entrevista_o);
        contentValues.put(SQLConstantes.resultado_residente_tipo_entrevista,tipo_entrevista);
        contentValues.put(SQLConstantes.resultado_residente_tipo_entrevista_o,tipo_entrevista_o);
        contentValues.put(SQLConstantes.resultado_residente_donde_entrevista,donde_entrevista);
        contentValues.put(SQLConstantes.resultado_residente_donde_entrevista_o,donde_entrevista_o);
        return contentValues;
    }
}
