package com.example.ricindigus.enpove2021.modelo.pojos;

public class FechaActual {

    String diaInicio;
    String mesInicio;
    String anioInicio;
    String horaInicio;
    String minutoInicio;

    public FechaActual() {
        diaInicio    = "00";
        mesInicio    = "00";
        anioInicio   = "00";
        horaInicio   = "00";
        minutoInicio = "00";
    }

    public String getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(String diaInicio) {
        this.diaInicio = diaInicio;
    }

    public String getMesInicio() {
        return mesInicio;
    }

    public void setMesInicio(String mesInicio) {
        this.mesInicio = mesInicio;
    }

    public String getAnioInicio() {
        return anioInicio;
    }

    public void setAnioInicio(String anioInicio) {
        this.anioInicio = anioInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getMinutoInicio() {
        return minutoInicio;
    }

    public void setMinutoInicio(String minutoInicio) {
        this.minutoInicio = minutoInicio;
    }
}
