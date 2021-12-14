package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class VisitaSupervisor {
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
    private String vis_resu;
    private String vis_resu_esp;

    public VisitaSupervisor() {
        _id="";
        id_vivienda="";
        id_hogar="";
        numero="";
        vis_fecha_dd="";
        vis_fecha_mm="";
        vis_fecha_aa="";
        vis_hor_ini="";
        vis_min_ini="";
        vis_hor_fin="";
        vis_min_fin="";
        vis_resu="";
        vis_resu_esp="";
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

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.visita_supervisor_id ,_id);
        contentValues.put(SQLConstantes.visita_supervisor_id_vivienda ,id_vivienda);
        contentValues.put(SQLConstantes.visita_supervisor_id_hogar ,id_hogar);
        contentValues.put(SQLConstantes.visita_supervisor_numero ,numero);
        contentValues.put(SQLConstantes.visita_supervisor_vis_fecha_dd ,vis_fecha_dd);
        contentValues.put(SQLConstantes.visita_supervisor_vis_fecha_mm ,vis_fecha_mm);
        contentValues.put(SQLConstantes.visita_supervisor_vis_fecha_aa ,vis_fecha_aa);
        contentValues.put(SQLConstantes.visita_supervisor_vis_hor_ini ,vis_hor_ini);
        contentValues.put(SQLConstantes.visita_supervisor_vis_min_ini ,vis_min_ini);
        contentValues.put(SQLConstantes.visita_supervisor_vis_hor_fin ,vis_hor_fin);
        contentValues.put(SQLConstantes.visita_supervisor_vis_min_fin ,vis_min_fin);
        contentValues.put(SQLConstantes.visita_supervisor_vis_resu ,vis_resu);
        contentValues.put(SQLConstantes.visita_supervisor_vis_resu_esp ,vis_resu_esp);
        return contentValues;
    }
}
