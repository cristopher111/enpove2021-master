package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class Funcionario {
   private String _id;
    private String dni_encu;
    private String dni_sup;
    private String dni_supn;
    private  String dni_coor;
    private  String nombre_encu;
    private String nombre_sup;
    private String nombre_supn;
    private String nombre_coord;

    public Funcionario() {
        _id = "";
        dni_encu= "";
        dni_sup= "";
        dni_supn= "";
        dni_coor= "";
        nombre_encu= "";
        nombre_sup= "";
        nombre_supn= "";
        nombre_coord= "";
    }



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
    public String getDni_encu() {
        return dni_encu;
    }

    public void setDni_encu(String dni_encu) {
        this.dni_encu = dni_encu;
    }

    public String getDni_sup() {
        return dni_sup;
    }

    public void setDni_sup(String dni_sup) {
        this.dni_sup = dni_sup;
    }

    public String getDni_supn() {
        return dni_supn;
    }

    public void setDni_supn(String dni_supn) {
        this.dni_supn = dni_supn;
    }

    public String getDni_coor() {
        return dni_coor;
    }

    public void setDni_coor(String dni_coor) {
        this.dni_coor = dni_coor;
    }

    public String getNombre_encu() {
        return nombre_encu;
    }

    public void setNombre_encu(String nombre_encu) {
        this.nombre_encu = nombre_encu;
    }

    public String getNombre_sup() {
        return nombre_sup;
    }

    public void setNombre_sup(String nombre_sup) {
        this.nombre_sup = nombre_sup;
    }

    public String getNombre_supn() {
        return nombre_supn;
    }

    public void setNombre_supn(String nombre_supn) {
        this.nombre_supn = nombre_supn;
    }

    public String getNombre_coord() {
        return nombre_coord;
    }

    public void setNombre_coord(String nombre_coord) {
        this.nombre_coord = nombre_coord;
    }


    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.funcionarios_id,_id);
        contentValues.put(SQLConstantes.funcionarios_dni_encu,dni_encu);
        contentValues.put(SQLConstantes.funcionarios_dni_sup,dni_sup);
        contentValues.put(SQLConstantes.funcionarios_dni_supn,dni_supn);
        contentValues.put(SQLConstantes.funcionarios_dni_coord,dni_coor);
        contentValues.put(SQLConstantes.funcionarios_nombre_encu,nombre_encu);
        contentValues.put(SQLConstantes.funcionarios_nombre_sup,nombre_sup);
        contentValues.put(SQLConstantes.funcionarios_nombre_supn,nombre_supn);
        contentValues.put(SQLConstantes.funcionarios_nombre_coord,nombre_coord);
        return contentValues;
    }
}
