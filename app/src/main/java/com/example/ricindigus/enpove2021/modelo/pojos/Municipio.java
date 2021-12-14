package com.example.ricindigus.enpove2021.modelo.pojos;

public class Municipio {
    private String _id;
    private String cod_municipio;
    private String nom_municipio;
    private String num_municipio;
    private String cod_estado;
    private String nom_estado;

    public Municipio(String _id, String cod_municipio, String nom_municipio, String num_municipio, String cod_estado, String nom_estado) {
        this._id = _id;
        this.cod_municipio = cod_municipio;
        this.nom_municipio = nom_municipio;
        this.num_municipio = num_municipio;
        this.cod_estado = cod_estado;
        this.nom_estado = nom_estado;
    }

    public Municipio() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCod_municipio() {
        return cod_municipio;
    }

    public void setCod_municipio(String cod_municipio) {
        this.cod_municipio = cod_municipio;
    }

    public String getNom_municipio() {
        return nom_municipio;
    }

    public void setNom_municipio(String nom_municipio) {
        this.nom_municipio = nom_municipio;
    }

    public String getNum_municipio() {
        return num_municipio;
    }

    public void setNum_municipio(String num_municipio) {
        this.num_municipio = num_municipio;
    }

    public String getCod_estado() {
        return cod_estado;
    }

    public void setCod_estado(String cod_estado) {
        this.cod_estado = cod_estado;
    }

    public String getNom_estado() {
        return nom_estado;
    }

    public void setNom_estado(String nom_estado) {
        this.nom_estado = nom_estado;
    }
}
