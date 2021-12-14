package com.example.ricindigus.enpove2021.modelo.pojos;

public class Ubigeo {
    private String _id;
    private String cod_departamento;
    private String nom_departamento;
    private String cod_provincia;
    private String nom_provincia;
    private String cod_distrito;
    private String nom_distrito;
    private String descripcion;

    public Ubigeo(String _id, String cod_departamento, String nom_departamento, String cod_provincia, String nom_provincia, String cod_distrito, String nom_distrito, String descripcion) {
        this._id = _id;
        this.cod_departamento = cod_departamento;
        this.nom_departamento = nom_departamento;
        this.cod_provincia = cod_provincia;
        this.nom_provincia = nom_provincia;
        this.cod_distrito = cod_distrito;
        this.nom_distrito = nom_distrito;
        this.descripcion = descripcion;
    }

    public Ubigeo() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCod_departamento() {
        return cod_departamento;
    }

    public void setCod_departamento(String cod_departamento) {
        this.cod_departamento = cod_departamento;
    }

    public String getNom_departamento() {
        return nom_departamento;
    }

    public void setNom_departamento(String nom_departamento) {
        this.nom_departamento = nom_departamento;
    }

    public String getCod_provincia() {
        return cod_provincia;
    }

    public void setCod_provincia(String cod_provincia) {
        this.cod_provincia = cod_provincia;
    }

    public String getNom_provincia() {
        return nom_provincia;
    }

    public void setNom_provincia(String nom_provincia) {
        this.nom_provincia = nom_provincia;
    }

    public String getCod_distrito() {
        return cod_distrito;
    }

    public void setCod_distrito(String cod_distrito) {
        this.cod_distrito = cod_distrito;
    }

    public String getNom_distrito() {
        return nom_distrito;
    }

    public void setNom_distrito(String nom_distrito) {
        this.nom_distrito = nom_distrito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
