package com.example.ricindigus.enpove2021.modelo.pojos;

public class Carga {
    private String _id;
    private String idCarga;
    private String idUsuario;
    private String rol;

    public Carga() {
    }

    public Carga(String _id, String idCarga, String idUsuario, String rol) {
        this._id = _id;
        this.idCarga = idCarga;
        this.idUsuario = idUsuario;
        this.rol = rol;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIdCarga() {
        return idCarga;
    }

    public void setIdCarga(String idCarga) {
        this.idCarga = idCarga;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
