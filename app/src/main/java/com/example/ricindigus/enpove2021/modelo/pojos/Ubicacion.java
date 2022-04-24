package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.provider.Settings;
import android.util.Log;

public class Ubicacion {
    private String id;
    private String idUsuario;
    private String usuario;
    private String dni;
    private String cargo;
    private String idDispositivo;
    private String serial;
    private String longitud;
    private String latitud;
    private String fechaTablet;
    private String versionApk;
    private String bateria;
    private String conexion;
    private String segmento;
    private String periodo;
    private String conglomerado;

    public Ubicacion() {
    }

    public Ubicacion(String id, String idUsuario, String usuario,String dni,String cargo, String idDispositivo, String serial, String longitud, String latitud, String fechaTablet, String versionApk, String bateria, String conexion, String segmento, String periodo, String conglomerado) {

        this.id = id;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.dni = dni;
        this.cargo = cargo;
        this.idDispositivo = idDispositivo;
        this.serial = serial;
        this.longitud = longitud;
        this.latitud = latitud;
        this.fechaTablet = fechaTablet;
        this.versionApk = versionApk;
        this.bateria = bateria;
        this.conexion = conexion;
        this.segmento = segmento;
        this.periodo = periodo;
        this.conglomerado = conglomerado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getFechaTablet() {
        return fechaTablet;
    }

    public void setFechaTablet(String fechaTablet) {
        this.fechaTablet = fechaTablet;
    }

    public String getVersionApk() {
        return versionApk;
    }

    public void setVersionApk(String versionApk) {
        this.versionApk = versionApk;
    }

    public String getBateria() {
        return bateria;
    }

    public void setBateria(String bateria) {
        this.bateria = bateria;
    }

    public String getConexion() {
        return conexion;
    }

    public void setConexion(String conexion) {
        this.conexion = conexion;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getConglomerado() {
        return conglomerado;
    }

    public void setConglomerado(String conglomerado) {
        this.conglomerado = conglomerado;
    }


}
