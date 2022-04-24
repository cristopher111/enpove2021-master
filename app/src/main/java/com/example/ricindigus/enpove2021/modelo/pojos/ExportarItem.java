package com.example.ricindigus.enpove2021.modelo.pojos;



public class ExportarItem {
    private int seleccionado;
    private String idVivienda;
    private String anio;
    private String mes;
    private String periodo;
    private String conglomerado;


    public ExportarItem(int seleccionado, String idVivienda, String anio, String mes, String periodo, String conglomerado) {
        this.seleccionado = seleccionado;
        this.idVivienda = idVivienda;
        this.anio = anio;
        this.mes = mes;
        this.periodo = periodo;
        this.conglomerado = conglomerado;
    }

    public int getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(int seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(String idVivienda) {
        this.idVivienda = idVivienda;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
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
