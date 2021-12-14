package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class Caratula {
    private String _id;
    private String anio;
    private String mes;
    private String periodo;
    private String conglomerado;
    private String norden;
    private String ccdd;
    private String nom_dep;
    private String ccpp;
    private String nom_prov;
    private String ccdi;
    private String nom_dist;
    private String codccpp;
    private String nom_ccpp;
    private String zona;
    private String manzana_id;
    private String vivienda;
    private String latitud;
    private String longitud;
    private String altitud;
    private String tipvia;
    private String tipvia_o;
    private String nomvia;
    private String nropta;
    private String block;
    private String interior;
    private String piso;
    private String mza;
    private String lote;
    private String km;
    private String telefono;
    private String t_hogar;
    private String usuario;
    private String observaciones;
    private String cobertura;
    private String nro_selec_vivienda;
    private String tipo_selec_vivienda;
    private String vivienda_reemplazo;
    private String nro_vivienda_reemplazo;
    private String p21;
    private String p21_o;
    private String resultado_final;
    private String resultado_final_o;
    private String fecha_inicio_aa;
    private String fecha_inicio_mm;
    private String fecha_inicio_dd;
    private String fecha_final_aa;
    private String fecha_final_mm;
    private String fecha_final_dd;
    private String nroSegmento;
    private String nroPuerta2;

    public Caratula() {
        _id = "";
        anio="";
        mes="";
        periodo="";
        conglomerado="";
        ccdd="";
        nom_dep="";
        ccpp="";
        nom_prov="";
        ccdi="";
        nom_dist="";
        codccpp="";
        nom_ccpp="";
        zona="";
        manzana_id="";
        vivienda="";
        latitud="";
        longitud="";
        altitud = "";
        tipvia="";
        tipvia_o="";
        nomvia="";
        nropta="";
        block="";
        interior="";
        piso="";
        mza="";
        lote="";
        km="";
        telefono="";
        t_hogar="0";
        usuario="";
        observaciones="";
        cobertura = "0";
        nro_selec_vivienda  = "";
        tipo_selec_vivienda = "";
        vivienda_reemplazo  = "";
        nro_vivienda_reemplazo  = "";
        p21 = "";
        p21_o = "";
        resultado_final = "";
        resultado_final_o = "";
        fecha_inicio_aa = "";
        fecha_inicio_mm = "";
        fecha_inicio_dd = "";
        fecha_final_aa = "";
        fecha_final_mm = "";
        fecha_final_dd = "";
        nroSegmento  = "";
        nroPuerta2  = "";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNorden() {
        return norden;
    }

    public void setNorden(String norden) {
        this.norden = norden;
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

    public String getCcdd() {
        return ccdd;
    }

    public void setCcdd(String ccdd) {
        this.ccdd = ccdd;
    }

    public String getCcpp() {
        return ccpp;
    }

    public void setCcpp(String ccpp) {
        this.ccpp = ccpp;
    }

    public String getCcdi() {
        return ccdi;
    }

    public void setCcdi(String ccdi) {
        this.ccdi = ccdi;
    }

    public String getCodccpp() {
        return codccpp;
    }

    public void setCodccpp(String codccpp) {
        this.codccpp = codccpp;
    }

    public String getNom_dep() {
        return nom_dep;
    }

    public void setNom_dep(String nom_dep) {
        this.nom_dep = nom_dep;
    }

    public String getNom_prov() {
        return nom_prov;
    }

    public void setNom_prov(String nom_prov) {
        this.nom_prov = nom_prov;
    }

    public String getNom_dist() {
        return nom_dist;
    }

    public void setNom_dist(String nom_dist) {
        this.nom_dist = nom_dist;
    }

    public String getNom_ccpp() {
        return nom_ccpp;
    }

    public void setNom_ccpp(String nom_ccpp) {
        this.nom_ccpp = nom_ccpp;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getManzana_id() {
        return manzana_id;
    }

    public void setManzana_id(String manzana_id) {
        this.manzana_id = manzana_id;
    }

    public String getVivienda() {
        return vivienda;
    }

    public void setVivienda(String vivienda) {
        this.vivienda = vivienda;
    }

    public String getTipvia() {
        return tipvia;
    }

    public void setTipvia(String tipvia) {
        this.tipvia = tipvia;
    }

    public String getTipvia_o() {
        return tipvia_o;
    }

    public void setTipvia_o(String tipvia_o) {
        this.tipvia_o = tipvia_o;
    }

    public String getNomvia() {
        return nomvia;
    }

    public void setNomvia(String nomvia) {
        this.nomvia = nomvia;
    }

    public String getNropta() {
        return nropta;
    }

    public void setNropta(String nropta) {
        this.nropta = nropta;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getMza() {
        return mza;
    }

    public void setMza(String mza) {
        this.mza = mza;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getT_hogar() {
        return t_hogar;
    }

    public void setT_hogar(String t_hogar) {
        this.t_hogar = t_hogar;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getAltitud() {
        return altitud;
    }

    public void setAltitud(String altitud) {
        this.altitud = altitud;
    }

    public String getObservaciones() {
        if(observaciones==null) return ""; else return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public String getNro_selec_vivienda() {
        return nro_selec_vivienda;
    }

    public void setNro_selec_vivienda(String nro_selec_vivienda) {
        this.nro_selec_vivienda = nro_selec_vivienda;
    }

    public String getTipo_selec_vivienda() {
        return tipo_selec_vivienda;
    }

    public void setTipo_selec_vivienda(String tipo_selec_vivienda) {
        this.tipo_selec_vivienda = tipo_selec_vivienda;
    }

    public String getVivienda_reemplazo() {
        return vivienda_reemplazo;
    }

    public void setVivienda_reemplazo(String vivienda_reemplazo) {
        this.vivienda_reemplazo = vivienda_reemplazo;
    }

    public String getNro_vivienda_reemplazo() {
        return nro_vivienda_reemplazo;
    }

    public void setNro_vivienda_reemplazo(String nro_vivienda_reemplazo) {
        this.nro_vivienda_reemplazo = nro_vivienda_reemplazo;
    }

    public String getP21() {
        return p21;
    }

    public void setP21(String p21) {
        this.p21 = p21;
    }

    public String getP21_o() {
        return p21_o;
    }

    public void setP21_o(String p21_o) {
        this.p21_o = p21_o;
    }

    public String getResultado_final() {
        return resultado_final;
    }

    public void setResultado_final(String resultado_final) {
        this.resultado_final = resultado_final;
    }

    public String getResultado_final_o() {
        return resultado_final_o;
    }

    public void setResultado_final_o(String resultado_final_o) {
        this.resultado_final_o = resultado_final_o;
    }

    public String getFecha_inicio_dd() {
        return fecha_inicio_dd;
    }

    public void setFecha_inicio_dd(String fecha_inicio_dd) {
        this.fecha_inicio_dd = fecha_inicio_dd;
    }

    public String getFecha_inicio_mm() {
        return fecha_inicio_mm;
    }

    public void setFecha_inicio_mm(String fecha_inicio_mm) {
        this.fecha_inicio_mm = fecha_inicio_mm;
    }

    public String getFecha_inicio_aa() {
        return fecha_inicio_aa;
    }

    public void setFecha_inicio_aa(String fecha_inicio_aa) {
        this.fecha_inicio_aa = fecha_inicio_aa;
    }

    public String getFecha_final_dd() {
        return fecha_final_dd;
    }

    public void setFecha_final_dd(String fecha_final_dd) {
        this.fecha_final_dd = fecha_final_dd;
    }

    public String getFecha_final_mm() {
        return fecha_final_mm;
    }

    public void setFecha_final_mm(String fecha_final_mm) {
        this.fecha_final_mm = fecha_final_mm;
    }

    public String getFecha_final_aa() {
        return fecha_final_aa;
    }

    public void setFecha_final_aa(String fecha_final_aa) {
        this.fecha_final_aa = fecha_final_aa;
    }

    public String getNroSegmento() {
        return nroSegmento;
    }

    public void setNroSegmento(String nroSegmento) {
        this.nroSegmento = nroSegmento;
    }

    public String getNroPuerta2() {
        return nroPuerta2;
    }

    public void setNroPuerta2(String nroPuerta2) {
        this.nroPuerta2 = nroPuerta2;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.caratula_id,_id);
        contentValues.put(SQLConstantes.caratula_mes,mes);
        contentValues.put(SQLConstantes.caratula_anio,anio);
        contentValues.put(SQLConstantes.caratula_periodo,periodo);
        contentValues.put(SQLConstantes.caratula_conglomerado,conglomerado);
        contentValues.put(SQLConstantes.caratula_ccdd,ccdd);
        contentValues.put(SQLConstantes.caratula_nom_dep,nom_dep);
        contentValues.put(SQLConstantes.caratula_ccpp,ccpp);
        contentValues.put(SQLConstantes.caratula_nom_prov,nom_prov);
        contentValues.put(SQLConstantes.caratula_ccdi,ccdi);
        contentValues.put(SQLConstantes.caratula_nom_dist,nom_dist);
        contentValues.put(SQLConstantes.caratula_codccpp,codccpp);
        contentValues.put(SQLConstantes.caratula_nom_ccpp,nom_ccpp);
        contentValues.put(SQLConstantes.caratula_zona,zona);
        contentValues.put(SQLConstantes.caratula_manzana_id,manzana_id);
        contentValues.put(SQLConstantes.caratula_vivienda,vivienda);
        contentValues.put(SQLConstantes.caratula_longitud,longitud);
        contentValues.put(SQLConstantes.caratula_latitud,latitud);
        contentValues.put(SQLConstantes.caratula_altitud,altitud);
        contentValues.put(SQLConstantes.caratula_tipvia,tipvia);
        contentValues.put(SQLConstantes.caratula_tipvia_o,tipvia_o);
        contentValues.put(SQLConstantes.caratula_nomvia,nomvia);
        contentValues.put(SQLConstantes.caratula_nropta,nropta);
        contentValues.put(SQLConstantes.caratula_block,block);
        contentValues.put(SQLConstantes.caratula_interior,interior);
        contentValues.put(SQLConstantes.caratula_piso,piso);
        contentValues.put(SQLConstantes.caratula_mza,mza);
        contentValues.put(SQLConstantes.caratula_lote,lote);
        contentValues.put(SQLConstantes.caratula_km,km);
        contentValues.put(SQLConstantes.caratula_telefono,telefono);
        contentValues.put(SQLConstantes.caratula_t_hogar,t_hogar);
        contentValues.put(SQLConstantes.caratula_usuario,usuario);
        contentValues.put(SQLConstantes.caratula_observaciones,observaciones);
        contentValues.put(SQLConstantes.caratula_cobertura,cobertura);
        contentValues.put(SQLConstantes.caratula_nro_selec_vivienda,nro_selec_vivienda);
        contentValues.put(SQLConstantes.caratula_tipo_selec_vivienda,tipo_selec_vivienda);
        contentValues.put(SQLConstantes.caratula_vivienda_reemplazo,vivienda_reemplazo);
        contentValues.put(SQLConstantes.caratula_nro_vivienda_reemplazo,nro_vivienda_reemplazo);
        contentValues.put(SQLConstantes.caratula_p21,p21);
        contentValues.put(SQLConstantes.caratula_p21_o,p21_o);
        contentValues.put(SQLConstantes.caratula_resultado_final,resultado_final);
        contentValues.put(SQLConstantes.caratula_resultado_final_o,resultado_final_o);
        contentValues.put(SQLConstantes.caratula_fecha_inicio_aa,fecha_inicio_aa);
        contentValues.put(SQLConstantes.caratula_fecha_inicio_mm,fecha_inicio_mm);
        contentValues.put(SQLConstantes.caratula_fecha_inicio_dd,fecha_inicio_dd);
        contentValues.put(SQLConstantes.caratula_fecha_final_aa,fecha_final_aa);
        contentValues.put(SQLConstantes.caratula_fecha_final_mm,fecha_final_mm);
        contentValues.put(SQLConstantes.caratula_fecha_final_dd,fecha_final_dd);
        contentValues.put(SQLConstantes.caratula_nroSegmento,nroSegmento);
        contentValues.put(SQLConstantes.caratula_nroPta2,nroPuerta2);
        return contentValues;
    }

}
