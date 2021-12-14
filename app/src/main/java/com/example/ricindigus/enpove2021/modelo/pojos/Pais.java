package com.example.ricindigus.enpove2021.modelo.pojos;

public class Pais {
    private String _id;
    private String nombre;
    private String numero;

    public Pais(String _id, String nombre, String numero) {
        this._id = _id;
        this.nombre = nombre;
        this.numero = numero;
    }

    public Pais() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
