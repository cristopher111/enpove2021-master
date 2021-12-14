package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class POJOFragmentHogar {
    private String _id;
    private String id_vivienda;
    private String visitas_encuestador;
    private String visitas_supervisor;
    private String funcionarios;
    private String p101p107;
    private String p108p113;
    private String p201p207;

    public POJOFragmentHogar(String _id) {
        this._id= _id;
        id_vivienda = "";
        visitas_encuestador="1";
        visitas_supervisor="-1";
        funcionarios="0";
        p101p107="0";
        p108p113="0";
        p201p207="0";
    }

    public POJOFragmentHogar() {
        this._id= "";
        id_vivienda = "";
        visitas_encuestador="";
        visitas_supervisor="";
        funcionarios="";
        p101p107="";
        p108p113="";
        p201p207="";
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

    public String getVisitas_encuestador() {
        return visitas_encuestador;
    }

    public void setVisitas_encuestador(String visitas_encuestador) {
        this.visitas_encuestador = visitas_encuestador;
    }

    public String getVisitas_supervisor() {
        return visitas_supervisor;
    }

    public void setVisitas_supervisor(String visitas_supervisor) {
        this.visitas_supervisor = visitas_supervisor;
    }

    public String getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(String funcionarios) {
        this.funcionarios = funcionarios;
    }

    public String getP101p107() {
        return p101p107;
    }

    public void setP101p107(String p101p107) {
        this.p101p107 = p101p107;
    }

    public String getP108p113() {
        return p108p113;
    }

    public void setP108p113(String p108p113) {
        this.p108p113 = p108p113;
    }

    public String getP201p207() {
        return p201p207;
    }

    public void setP201p207(String p201p207) {
        this.p201p207 = p201p207;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.fragments_hogar_id ,_id);
        contentValues.put(SQLConstantes.fragments_hogar_id_vivienda ,id_vivienda);
        contentValues.put(SQLConstantes.fragments_hogar_visitas_encuestador ,visitas_encuestador);
        contentValues.put(SQLConstantes.fragments_hogar_visitas_supervisor ,visitas_supervisor);
        contentValues.put(SQLConstantes.fragments_hogar_funcionarios ,funcionarios);
        contentValues.put(SQLConstantes.fragments_hogar_p101p107 ,p101p107);
        contentValues.put(SQLConstantes.fragments_hogar_p108p113 ,p108p113);
        contentValues.put(SQLConstantes.fragments_hogar_p201p207 , p201p207);
        return contentValues;
    }
}
