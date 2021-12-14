package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class Pregunta {
    private String id;
    private String idUsuario;
    private String usuario;
    private String nombres;
    private String fecha;
    private String hora;
    private String pregunta;

    public Pregunta() {
        id        = "";
        idUsuario = "";
        usuario   = "";
        nombres   = "";
        fecha     = "";
        hora      = "";
        pregunta  = "";
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.bpr_id,id);
        contentValues.put(SQLConstantes.bpr_idUsuario,idUsuario);
        contentValues.put(SQLConstantes.bpr_usuario,usuario);
        contentValues.put(SQLConstantes.bpr_nombres,nombres);
        contentValues.put(SQLConstantes.bpr_fecha,fecha);
        contentValues.put(SQLConstantes.bpr_hora,hora);
        contentValues.put(SQLConstantes.bpr_pregunta,pregunta);
        return contentValues;
    }

}
