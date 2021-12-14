package com.example.ricindigus.enpove2021.modelo.pojos;

public class ViviendaItem {
    private String idVivienda;
    private int seleccionado;
    private String nroVivienda;
    private String conglomerado;
    private String nroSelecVivienda;
    private String tipoSelecVivienda;
    private String reemplazoVivienda;
    private String centroPoblado;
    private String periodo;
    private String resultado;
    private String estado;
    private String anio;
    private String mes;
    private String nrosegmento;

    public ViviendaItem() {
    }

    public ViviendaItem(String idVivienda, int seleccionado, String nroVivienda, String conglomerado, String nroSelecVivienda, String tipoSelecVivienda, String reemplazoVivienda, String centroPoblado, String periodo, String resultado, String estado, String anio, String mes, String nrosegmento) {
        this.idVivienda = idVivienda;
        this.seleccionado = seleccionado;
        this.nroVivienda = nroVivienda;
        this.conglomerado = conglomerado;
        this.nroSelecVivienda = nroSelecVivienda;
        this.tipoSelecVivienda = tipoSelecVivienda;
        this.reemplazoVivienda = reemplazoVivienda;
        this.centroPoblado = centroPoblado;
        this.periodo = periodo;
        this.resultado = resultado;
        this.estado = estado;
        this.anio = anio;
        this.mes = mes;
        this.nrosegmento = nrosegmento;
    }

    public String getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(String idVivienda) {
        this.idVivienda = idVivienda;
    }

    public int getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(int seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String getNroVivienda() {
        return nroVivienda;
    }

    public void setNroVivienda(String nroVivienda) {
        this.nroVivienda = nroVivienda;
    }

    public String getConglomerado() {
        return conglomerado;
    }

    public void setConglomerado(String conglomerado) {
        this.conglomerado = conglomerado;
    }

    public String getNroSelecVivienda() {
        return nroSelecVivienda;
    }

    public void setNroSelecVivienda(String nroSelecVivienda) {
        this.nroSelecVivienda = nroSelecVivienda;
    }

    public String getTipoSelecVivienda() {
        return tipoSelecVivienda;
    }

    public void setTipoSelecVivienda(String tipoSelecVivienda) {
        this.tipoSelecVivienda = tipoSelecVivienda;
    }

    public String getReemplazoVivienda() {
        return reemplazoVivienda;
    }

    public void setReemplazoVivienda(String reemplazoVivienda) {
        this.reemplazoVivienda = reemplazoVivienda;
    }

    public String getCentroPoblado() {
        return centroPoblado;
    }

    public void setCentroPoblado(String centroPoblado) {
        this.centroPoblado = centroPoblado;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getNrosegmento() {
        return nrosegmento;
    }

    public void setNrosegmento(String nrosegmento) {
        this.nrosegmento = nrosegmento;
    }
}
