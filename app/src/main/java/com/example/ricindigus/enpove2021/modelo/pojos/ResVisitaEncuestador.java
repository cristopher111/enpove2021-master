package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class ResVisitaEncuestador {
    private String _id;
    private String id_vivienda;
    private String vis_resultado_final;
    private String vis_resultado_final_o;
    private String vis_fecha_final_dd;
    private String vis_fecha_final_mm;
    private String vis_fecha_final_aa;

    public ResVisitaEncuestador() {
        _id = "";
        id_vivienda = "";
        vis_resultado_final = "";
        vis_resultado_final_o = "";
        vis_fecha_final_dd = "";
        vis_fecha_final_mm = "";
        vis_fecha_final_aa = "";
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

    public String getVis_resultado_final() {
        return vis_resultado_final;
    }

    public void setVis_resultado_final(String vis_resultado_final) {
        this.vis_resultado_final = vis_resultado_final;
    }

    public String getVis_resultado_final_o() {
        return vis_resultado_final_o;
    }

    public void setVis_resultado_final_o(String vis_resultado_final_o) {
        this.vis_resultado_final_o = vis_resultado_final_o;
    }

    public String getVis_fecha_final_dd() {
        return vis_fecha_final_dd;
    }

    public void setVis_fecha_final_dd(String vis_fecha_final_dd) {
        this.vis_fecha_final_dd = vis_fecha_final_dd;
    }

    public String getVis_fecha_final_mm() {
        return vis_fecha_final_mm;
    }

    public void setVis_fecha_final_mm(String vis_fecha_final_mm) {
        this.vis_fecha_final_mm = vis_fecha_final_mm;
    }

    public String getVis_fecha_final_aa() {
        return vis_fecha_final_aa;
    }

    public void setVis_fecha_final_aa(String vis_fecha_final_aa) {
        this.vis_fecha_final_aa = vis_fecha_final_aa;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.resultado_encuestador_id ,_id);
        contentValues.put(SQLConstantes.resultado_encuestador_id_vivienda ,id_vivienda);
        contentValues.put(SQLConstantes.resultado_encuestador_vis_resultado_final ,vis_resultado_final);
        contentValues.put(SQLConstantes.resultado_encuestador_vis_resultado_final_o ,vis_resultado_final_o);
        contentValues.put(SQLConstantes.resultado_encuestador_vis_fecha_final_dd ,vis_fecha_final_dd);
        contentValues.put(SQLConstantes.resultado_encuestador_vis_fecha_final_mm ,vis_fecha_final_mm);
        contentValues.put(SQLConstantes.resultado_encuestador_vis_fecha_final_aa ,vis_fecha_final_aa);
        return contentValues;
    }
}
