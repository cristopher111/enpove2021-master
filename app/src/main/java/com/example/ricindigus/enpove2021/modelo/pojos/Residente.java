package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class Residente {
    private String _id;
    private String id_informante;
    private String id_hogar;
    private String id_vivienda;
    private String numero;
    private String c2_p202;
    private String c2_p202_pat;
    private String c2_p202_mat;
    private String c2_p203;
    private String c2_p204;
    private String c2_p205_a;
    private String c2_p205_m;
    private String c2_p206;
    private String c2_p207;
    private String c2_p207_o;
    private String c2_p208;
    private String c2_p209;
    private String c2_p209_n;
    private String c2_p209_p;
    private String c2_p209_pos;
    private String c2_p210;
    private String c2_p210_n;
    private String c2_p210_p;
    private String c2_p210_pos;
    private String c2_p211;
    private String c2_p211_nom;
    private String c2_p211_pos;
    private String c2_p211_1;
    private String c2_p211_1_o;
    private String c2_p212;
    private String OBS200;
    private String COB200;
    private String encuestado_cobertura;

    public Residente() {
        _id = "";
        id_informante = "";
        id_hogar = "";
        id_vivienda = "";
        c2_p202 = "";
        c2_p202_pat = "";
        c2_p202_mat = "";
        c2_p203 = "";
        c2_p204 = "";
        c2_p205_a = "";
        c2_p205_m = "";
        c2_p206 = "0";
        c2_p207 = "";
        c2_p207_o = "";
        c2_p208 = "";
        c2_p209 = "";
        c2_p209_n = "";
        c2_p209_p = "";
        c2_p209_pos = "";
        c2_p210 = "";
        c2_p210_n = "";
        c2_p210_p = "";
        c2_p210_pos = "";
        c2_p211 = "";
        c2_p211_1 = "";
        c2_p211_1_o = "";
        c2_p212 = "";
        c2_p211_nom = "";
        c2_p211_pos = "";
        OBS200 = "";
        COB200 = "0";
        encuestado_cobertura   = "";
    }

    public Residente(String _id, String id_informante, String id_hogar, String id_vivienda, String numero, String c2_p202, String c2_p202_pat, String c2_p202_mat, String c2_p203, String c2_p204, String c2_p205_a, String c2_p205_m, String c2_p206, String c2_p207, String c2_p207_o, String c2_p208, String c2_p209, String c2_p209_n, String c2_p209_p, String c2_p209_pos, String c2_p210, String c2_p210_n, String c2_p210_p, String c2_p210_pos, String c2_p211, String c2_p211_nom, String c2_p211_pos, String c2_p211_1, String c2_p211_1_o, String c2_p212, String COB200, String encuestado_cobertura) {
        this._id = _id;
        this.id_informante = id_informante;
        this.id_hogar = id_hogar;
        this.id_vivienda = id_vivienda;
        this.numero = numero;
        this.c2_p202 = c2_p202;
        this.c2_p202_pat = c2_p202_pat;
        this.c2_p202_mat = c2_p202_mat;
        this.c2_p203 = c2_p203;
        this.c2_p204 = c2_p204;
        this.c2_p205_a = c2_p205_a;
        this.c2_p205_m = c2_p205_m;
        this.c2_p206 = c2_p206;
        this.c2_p207 = c2_p207;
        this.c2_p207_o = c2_p207_o;
        this.c2_p208 = c2_p208;
        this.c2_p209 = c2_p209;
        this.c2_p209_n = c2_p209_n;
        this.c2_p209_p = c2_p209_p;
        this.c2_p209_pos = c2_p209_pos;
        this.c2_p210 = c2_p210;
        this.c2_p210_n = c2_p210_n;
        this.c2_p210_p = c2_p210_p;
        this.c2_p210_pos = c2_p210_pos;
        this.c2_p211 = c2_p211;
        this.c2_p211_nom = c2_p211_nom;
        this.c2_p211_pos = c2_p211_pos;
        this.c2_p211_1 = c2_p211_1;
        this.c2_p211_1_o = c2_p211_1_o;
        this.c2_p212 = c2_p212;
        this.COB200 = COB200;
        this.encuestado_cobertura = encuestado_cobertura;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getId_informante() {
        return id_informante;
    }

    public void setId_informante(String id_informante) {
        this.id_informante = id_informante;
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

    public String getC2_p202() {
        return c2_p202;
    }

    public void setC2_p202(String c2_p202) {
        this.c2_p202 = c2_p202;
    }

    public String getC2_p202_pat() {
        return c2_p202_pat;
    }

    public void setC2_p202_pat(String c2_p202_pat) {
        this.c2_p202_pat = c2_p202_pat;
    }

    public String getC2_p202_mat() {
        return c2_p202_mat;
    }

    public void setC2_p202_mat(String c2_p202_mat) {
        this.c2_p202_mat = c2_p202_mat;
    }

    public String getC2_p203() {
        return c2_p203;
    }

    public void setC2_p203(String c2_p203) {
        this.c2_p203 = c2_p203;
    }

    public String getC2_p204() {
        return c2_p204;
    }

    public void setC2_p204(String c2_p204) {
        this.c2_p204 = c2_p204;
    }

    public String getC2_p205_a() {
        if(c2_p205_a==null){
            return "0";
        }else if(c2_p205_a.trim().equals("")){
            return "0";
        }else  return c2_p205_a;
    }

    public String getC2_p205_a2() {
        return c2_p205_a;
    }

    public void setC2_p205_a(String c2_p205_a) {
        this.c2_p205_a = c2_p205_a;
    }

    public String getC2_p205_m() {
        return c2_p205_m;
    }

    public void setC2_p205_m(String c2_p205_m) {
        this.c2_p205_m = c2_p205_m;
    }

    public String getC2_p206() {
        return c2_p206;
    }

    public void setC2_p206(String c2_p206) {
        this.c2_p206 = c2_p206;
    }

    public String getC2_p207() {
        return c2_p207;
    }

    public void setC2_p207(String c2_p207) {
        this.c2_p207 = c2_p207;
    }

    public String getC2_p207_o() {
        return c2_p207_o;
    }

    public void setC2_p207_o(String c2_p207_o) {
        this.c2_p207_o = c2_p207_o;
    }

    public String getC2_p208() {
        return c2_p208;
    }

    public void setC2_p208(String c2_p208) {
        this.c2_p208 = c2_p208;
    }

    public String getC2_p209() {
        return c2_p209;
    }

    public void setC2_p209(String c2_p209) {
        this.c2_p209 = c2_p209;
    }

    public String getC2_p209_n() {
        return c2_p209_n;
    }

    public void setC2_p209_n(String c2_p209_n) {
        this.c2_p209_n = c2_p209_n;
    }

    public String getC2_p209_p() {
        return c2_p209_p;
    }

    public String getC2_p210_n() {
        return c2_p210_n;
    }

    public void setC2_p210_n(String c2_p210_n) {
        this.c2_p210_n = c2_p210_n;
    }

    public void setC2_p209_p(String c2_p209_p) {
        this.c2_p209_p = c2_p209_p;
    }

    public String getC2_p210() {
        return c2_p210;
    }

    public void setC2_p210(String c2_p210) {
        this.c2_p210 = c2_p210;
    }

    public String getC2_p210_p() {
        return c2_p210_p;
    }

    public void setC2_p210_p(String c2_p210_p) {
        this.c2_p210_p = c2_p210_p;
    }

    public String getC2_p211() {
        return c2_p211;
    }

    public void setC2_p211(String c2_p211) {
        this.c2_p211 = c2_p211;
    }

    public String getC2_p211_nom() {
        return c2_p211_nom;
    }

    public void setC2_p211_nom(String c2_p211_nom) {
        this.c2_p211_nom = c2_p211_nom;
    }

    public String getC2_p211_1() {
        return c2_p211_1;
    }

    public void setC2_p211_1(String c2_p211_1) {
        this.c2_p211_1 = c2_p211_1;
    }

    public String getC2_p211_1_o() {
        return c2_p211_1_o;
    }

    public void setC2_p211_1_o(String c2_p211_1_o) {
        this.c2_p211_1_o = c2_p211_1_o;
    }

    public String getC2_p212() {
        return c2_p212;
    }

    public void setC2_p212(String c2_p212) {
        this.c2_p212 = c2_p212;
    }

    public String getOBS200() {
        return OBS200;
    }

    public void setOBS200(String OBS200) {
        this.OBS200 = OBS200;
    }

    public String getCOB200() {
        return COB200;
    }

    public void setCOB200(String COB200) {
        this.COB200 = COB200;
    }

    public String getEncuestado_cobertura() {
        return encuestado_cobertura;
    }

    public void setEncuestado_cobertura(String encuestado_cobertura) {
        this.encuestado_cobertura = encuestado_cobertura;
    }

    public String getC2_p209_pos() {
        return c2_p209_pos;
    }

    public void setC2_p209_pos(String c2_p209_pos) {
        this.c2_p209_pos = c2_p209_pos;
    }

    public String getC2_p210_pos() {
        return c2_p210_pos;
    }

    public void setC2_p210_pos(String c2_p210_pos) {
        this.c2_p210_pos = c2_p210_pos;
    }

    public String getC2_p211_pos() {
        return c2_p211_pos;
    }

    public void setC2_p211_pos(String c2_p211_pos) {
        this.c2_p211_pos = c2_p211_pos;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.residentes_id,_id);
        contentValues.put(SQLConstantes.residentes_numero,numero);
        contentValues.put(SQLConstantes.residentes_id_informante, id_informante);
        contentValues.put(SQLConstantes.residentes_id_hogar, id_hogar);
        contentValues.put(SQLConstantes.residentes_id_vivienda, id_vivienda);
        contentValues.put(SQLConstantes.residentes_c2_p202,c2_p202);
        contentValues.put(SQLConstantes.residentes_c2_p202_pat,c2_p202_pat);
        contentValues.put(SQLConstantes.residentes_c2_p202_mat,c2_p202_mat);
        contentValues.put(SQLConstantes.residentes_c2_p203,c2_p203);
        contentValues.put(SQLConstantes.residentes_c2_p204,c2_p204);
        contentValues.put(SQLConstantes.residentes_c2_p205_a,c2_p205_a);
        contentValues.put(SQLConstantes.residentes_c2_p205_m,c2_p205_m);
        contentValues.put(SQLConstantes.residentes_c2_p206,c2_p206);
        contentValues.put(SQLConstantes.residentes_c2_p207,c2_p207);
        contentValues.put(SQLConstantes.residentes_c2_p207_o,c2_p207_o);
        contentValues.put(SQLConstantes.residentes_c2_p208,c2_p208);
        contentValues.put(SQLConstantes.residentes_c2_p209,c2_p209);
        contentValues.put(SQLConstantes.residentes_c2_p209_n,c2_p209_n);
        contentValues.put(SQLConstantes.residentes_c2_p209_p,c2_p209_p);
        contentValues.put(SQLConstantes.residentes_c2_p209_pos,c2_p209_pos);
        contentValues.put(SQLConstantes.residentes_c2_p210,c2_p210);
        contentValues.put(SQLConstantes.residentes_c2_p210_n,c2_p210_n);
        contentValues.put(SQLConstantes.residentes_c2_p210_p,c2_p210_p);
        contentValues.put(SQLConstantes.residentes_c2_p210_pos,c2_p210_pos);
        contentValues.put(SQLConstantes.residentes_c2_p211,c2_p211);
        contentValues.put(SQLConstantes.residentes_c2_p211_nom,c2_p211_nom);
        contentValues.put(SQLConstantes.residentes_c2_p211_pos,c2_p211_pos);
        contentValues.put(SQLConstantes.residentes_c2_p211_1,c2_p211_1);
        contentValues.put(SQLConstantes.residentes_c2_p211_1_o,c2_p211_1_o);
        contentValues.put(SQLConstantes.residentes_c2_p212,c2_p212);
        contentValues.put(SQLConstantes.residentes_c2_OBS200,OBS200);
        contentValues.put(SQLConstantes.residentes_COB200,COB200);
        contentValues.put(SQLConstantes.residentes_encuestado_cobertura,encuestado_cobertura);
        return contentValues;
    }
}
