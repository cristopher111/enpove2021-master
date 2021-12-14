package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class VisitaEncuestador {
    private String _id;
    private String id_vivienda;
    private String id_hogar;
    private String numero;
    private String vis_fecha_dd;
    private String vis_fecha_mm;
    private String vis_fecha_aa;
    private String vis_hor_ini;
    private String vis_min_ini;
    private String vis_hor_fin;
    private String vis_min_fin;
    private String prox_vis_fecha_dd;
    private String prox_vis_fecha_mm;
    private String prox_vis_fecha_aa;
    private String prox_vis_hor;
    private String prox_vis_min;
    private String vis_resu;
    private String vis_resu_esp;
    private String latitud;
    private String longitud;
    private String altura;
    private String tipo_ent;
    private String tipo_ent_o;

    public VisitaEncuestador() {
        _id = "";
        id_vivienda = "";
        id_hogar = "";
        numero = "";
        vis_fecha_dd = "";
        vis_fecha_mm = "";
        vis_fecha_aa = "";
        vis_hor_ini = "";
        vis_min_ini = "";
        vis_hor_fin = "";
        vis_min_fin = "";
        prox_vis_fecha_dd = "";
        prox_vis_fecha_mm = "";
        prox_vis_fecha_aa = "";
        prox_vis_hor = "";
        prox_vis_min = "";
        vis_resu = "";
        vis_resu_esp = "";
        latitud = "";
        longitud = "";
        altura = "";
        tipo_ent = "";
        tipo_ent_o = "";
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId_vivienda() {
        return id_vivienda;
    }

    public void setId_vivienda(String id_vivienda) {
        this.id_vivienda = id_vivienda;
    }

    public String getId_hogar() {
        return id_hogar;
    }

    public void setId_hogar(String id_hogar) {
        this.id_hogar = id_hogar;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getVis_fecha_dd() {
        return vis_fecha_dd;
    }

    public void setVis_fecha_dd(String vis_fecha_dd) {
        this.vis_fecha_dd = vis_fecha_dd;
    }

    public String getVis_fecha_mm() {
        return vis_fecha_mm;
    }

    public void setVis_fecha_mm(String vis_fecha_mm) {
        this.vis_fecha_mm = vis_fecha_mm;
    }

    public String getVis_fecha_aa() {
        return vis_fecha_aa;
    }

    public void setVis_fecha_aa(String vis_fecha_aa) {
        this.vis_fecha_aa = vis_fecha_aa;
    }

    public String getVis_hor_ini() {
        return vis_hor_ini;
    }

    public void setVis_hor_ini(String vis_hor_ini) {
        this.vis_hor_ini = vis_hor_ini;
    }

    public String getVis_min_ini() {
        return vis_min_ini;
    }

    public void setVis_min_ini(String vis_min_ini) {
        this.vis_min_ini = vis_min_ini;
    }

    public String getVis_hor_fin() {
        return vis_hor_fin;
    }

    public void setVis_hor_fin(String vis_hor_fin) {
        this.vis_hor_fin = vis_hor_fin;
    }

    public String getVis_min_fin() {
        return vis_min_fin;
    }

    public void setVis_min_fin(String vis_min_fin) {
        this.vis_min_fin = vis_min_fin;
    }

    public String getProx_vis_fecha_dd() {
        return prox_vis_fecha_dd;
    }

    public void setProx_vis_fecha_dd(String vprox_vis_fecha_dd) {
        this.prox_vis_fecha_dd = vprox_vis_fecha_dd;
    }

    public String getProx_vis_fecha_mm() {
        return prox_vis_fecha_mm;
    }

    public void setProx_vis_fecha_mm(String prox_vis_fecha_mm) {
        this.prox_vis_fecha_mm = prox_vis_fecha_mm;
    }

    public String getProx_vis_fecha_aa() {
        return prox_vis_fecha_aa;
    }

    public void setProx_vis_fecha_aa(String prox_vis_fecha_aa) {
        this.prox_vis_fecha_aa = prox_vis_fecha_aa;
    }

    public String getProx_vis_hor() {
        return prox_vis_hor;
    }

    public void setProx_vis_hor(String prox_vis_hor) {
        this.prox_vis_hor = prox_vis_hor;
    }

    public String getProx_vis_min() {
        return prox_vis_min;
    }

    public void setProx_vis_min(String prox_vis_min) {
        this.prox_vis_min = prox_vis_min;
    }

    public String getVis_resu() {
        return vis_resu;
    }

    public void setVis_resu(String vis_resu) {
        this.vis_resu = vis_resu;
    }

    public String getVis_resu_esp() {
        return vis_resu_esp;
    }

    public void setVis_resu_esp(String vis_resu_esp) {
        this.vis_resu_esp = vis_resu_esp;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getTipo_ent() {
        return tipo_ent;
    }

    public void setTipo_ent(String tipo_ent) {
        this.tipo_ent = tipo_ent;
    }

    public String getTipo_ent_o() {
        return tipo_ent_o;
    }

    public void setTipo_ent_o(String tipo_ent_o) {
        this.tipo_ent_o = tipo_ent_o;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.visita_encuestador_id,_id);
        contentValues.put(SQLConstantes.visita_encuestador_id_vivienda,id_vivienda);
        contentValues.put(SQLConstantes.visita_encuestador_id_hogar,id_hogar);
        contentValues.put(SQLConstantes.visita_encuestador_numero,numero);
        contentValues.put(SQLConstantes.visita_encuestador_vis_fecha_dd,vis_fecha_dd);
        contentValues.put(SQLConstantes.visita_encuestador_vis_fecha_mm,vis_fecha_mm);
        contentValues.put(SQLConstantes.visita_encuestador_vis_fecha_aa,vis_fecha_aa);
        contentValues.put(SQLConstantes.visita_encuestador_vis_hor_ini,vis_hor_ini);
        contentValues.put(SQLConstantes.visita_encuestador_vis_min_ini,vis_min_ini);
        contentValues.put(SQLConstantes.visita_encuestador_vis_hor_fin,vis_hor_fin);
        contentValues.put(SQLConstantes.visita_encuestador_vis_min_fin,vis_min_fin);
        contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_dd,prox_vis_fecha_dd);
        contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_mm,prox_vis_fecha_mm);
        contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_aa,prox_vis_fecha_aa);
        contentValues.put(SQLConstantes.visita_encuestador_prox_vis_hor,prox_vis_hor);
        contentValues.put(SQLConstantes.visita_encuestador_prox_vis_min,prox_vis_min);
        contentValues.put(SQLConstantes.visita_encuestador_vis_resu,vis_resu);
        contentValues.put(SQLConstantes.visita_encuestador_vis_resu_esp,vis_resu_esp);
        contentValues.put(SQLConstantes.visita_encuestador_latitud,latitud);
        contentValues.put(SQLConstantes.visita_encuestador_longitud,longitud);
        contentValues.put(SQLConstantes.visita_encuestador_altura,altura);
        contentValues.put(SQLConstantes.visita_encuestador_tipo_entrevista,tipo_ent);
        contentValues.put(SQLConstantes.visita_encuestador_tipo_entrevista_o,tipo_ent_o);
        return contentValues;
    }
}
