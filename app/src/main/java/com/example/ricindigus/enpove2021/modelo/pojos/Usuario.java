package com.example.ricindigus.enpove2021.modelo.pojos;

public class Usuario {
    private String _id;
    private String usuario;
    private String clave;
    private String dni;
    private String nombre;
    private String cargo_id;
    private String telefono;
    private String nro_encuestador;
    private String area_trabajo;
    private String sede;


    public Usuario() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(String cargo_id) {
        this.cargo_id = cargo_id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNro_encuestador() {
        return nro_encuestador;
    }

    public void setNro_encuestador(String nro_encuestador) {
        this.nro_encuestador = nro_encuestador;
    }

    public String getArea_trabajo() {
        return area_trabajo;
    }

    public void setArea_trabajo(String area_trabajo) {
        this.area_trabajo = area_trabajo;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }
}
