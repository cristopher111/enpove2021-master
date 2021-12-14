package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class Hogar {
    private String _id;
    private String id_vivienda;
    private String id_informante;
    private String numero;
    private String nom_ape;
    private String ape_paterno;
    private String ape_materno;
    private String estado;
    private String nropersonas;
    private String vive;
    private String nroviven;
    private String principal;
    private String cobertura;
    private String p15;
    private String p15_o;
    private String p16;
    private String p17;
    private String p18;
    private String p19;
    private String p20;



    public Hogar() {
        _id = "";
        id_vivienda = "";
        id_informante = "";
        numero = "";
        nom_ape = "";
        ape_paterno = "";
        ape_materno = "";
        estado = "";
        nropersonas = "";
        vive = "";
        nroviven = "0";
        principal = "";
        cobertura = "0";
        p15 = "";
        p15_o = "";
        p16 = "";
        p17 = "";
        p18 = "";
        p19 = "";
        p20 = "";
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

    public String getId_informante() {
        return id_informante;
    }

    public void setId_informante(String id_informante) {
        this.id_informante = id_informante;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom_ape() {
        return nom_ape;
    }

    public void setNom_ape(String nom_ape) {
        this.nom_ape = nom_ape;
    }

    public String getApe_paterno() {
        return ape_paterno;
    }

    public void setApe_paterno(String ape_paterno) {
        this.ape_paterno = ape_paterno;
    }

    public String getApe_materno() {
        return ape_materno;
    }

    public void setApe_materno(String ape_materno) {
        this.ape_materno = ape_materno;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNropersonas() {
        return nropersonas;
    }

    public void setNropersonas(String nropersonas) {
        this.nropersonas = nropersonas;
    }

    public String getVive() {
        return vive;
    }

    public void setVive(String vive) {
        this.vive = vive;
    }

    public String getNroviven() {
        return nroviven;
    }

    public void setNroviven(String nroviven) {
        this.nroviven = nroviven;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public String getP15() {
        return p15;
    }

    public void setP15(String p15) {
        this.p15 = p15;
    }

    public String getP15_o() {
        return p15_o;
    }

    public void setP15_o(String p15_o) {
        this.p15_o = p15_o;
    }

    public String getP16() {
        return p16;
    }

    public void setP16(String p16) {
        this.p16 = p16;
    }

    public String getP17() {
        return p17;
    }

    public void setP17(String p17) {
        this.p17 = p17;
    }

    public String getP18() {
        return p18;
    }

    public void setP18(String p18) {
        this.p18 = p18;
    }

    public String getP19() {
        return p19;
    }

    public void setP19(String p19) {
        this.p19 = p19;
    }

    public String getP20() {
        return p20;
    }

    public void setP20(String p20) {
        this.p20 = p20;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.hogar_id,_id);
        contentValues.put(SQLConstantes.hogar_id_vivienda,id_vivienda);
        contentValues.put(SQLConstantes.hogar_id_informante,id_informante);
        contentValues.put(SQLConstantes.hogar_numero,numero);
        contentValues.put(SQLConstantes.hogar_nom_ape,nom_ape);
        contentValues.put(SQLConstantes.hogar_ape_paterno,ape_paterno);
        contentValues.put(SQLConstantes.hogar_ape_materno,ape_materno);
        contentValues.put(SQLConstantes.hogar_estado,estado);
        contentValues.put(SQLConstantes.hogar_nropersonas,nropersonas);
        contentValues.put(SQLConstantes.hogar_vive,vive);
        contentValues.put(SQLConstantes.hogar_nroviven,nroviven);
        contentValues.put(SQLConstantes.hogar_principal,principal);
        contentValues.put(SQLConstantes.hogar_cobertura,cobertura);
        contentValues.put(SQLConstantes.hogar_p15,p15);
        contentValues.put(SQLConstantes.hogar_p15_o,p15_o);
        contentValues.put(SQLConstantes.hogar_p16,p16);
        contentValues.put(SQLConstantes.hogar_p17,p17);
        contentValues.put(SQLConstantes.hogar_p18,p18);
        contentValues.put(SQLConstantes.hogar_p19,p19);
        contentValues.put(SQLConstantes.hogar_p20,p20);
        return contentValues;
    }
}

