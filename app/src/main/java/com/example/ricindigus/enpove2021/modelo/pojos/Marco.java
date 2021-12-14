package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class Marco {
    private String _id;
    private String anio;
    private String mes;
    private String periodo;
    private String zona;
    private String ccdd;
    private String departamento;
    private String ccpp;
    private String provincia;
    private String ccdi;
    private String distrito;
    private String codccpp;
    private String nomccpp;
    private String conglomerado;
    private String norden;
    private String manzana_id;
    private String tipvia;
    private String nomvia;
    private String nropta;
    private String lote;
    private String piso;
    private String mza;
    private String block;
    private String interior;
    private String km;
    private String usuario_id;
    private String usuario;
    private String nombre;
    private String dni;
    private String usuario_sup_id;
    private String estado;
    private String nro_selec_vivienda;
    private String tipo_selec_vivienda;
    private String vivienda_reemplazo;
    private String tipo;
    private String nroVivienda;
    private String nroSegmento;
    private String marcoProviene;
    private String estrato;
    private String observaciones;
    private String nroPuerta2;
    private String jefeHogar;
    private String telefono;
    private String correo;
    private String aerInicial;
    private String aerFinal;
    private String cono;
    private String area;
    private String areaEncuesta;
    private String region;
    private String dominio;
    private String idCarga;
    private String frente;
    private String latitud;
    private String longitud;

    public Marco() {
        _id="";
        anio="";
        mes="";
        periodo="";
        zona="";
        ccdd="";
        departamento="";
        ccpp="";
        provincia="";
        ccdi="";
        distrito="";
        codccpp="";
        nomccpp="";
        conglomerado="";
        norden="";
        manzana_id="";
        tipvia="";
        nomvia="";
        nropta="";
        lote="";
        piso="";
        mza="";
        block="";
        interior="";
        km="";
        usuario_id="";
        usuario="";
        nombre="";
        dni="";
        usuario_sup_id="";
        estado = "0";
        nro_selec_vivienda  = "";
        tipo_selec_vivienda = "";
        vivienda_reemplazo  = "";
        tipo = "";
        nroVivienda  = "";
        nroSegmento  = "";
        marcoProviene  = "";
        estrato  = "";
        observaciones  = "";
        nroPuerta2  = "";
        jefeHogar  = "";
        telefono  = "";
        correo  = "";
        aerInicial  = "";
        aerFinal  = "";
        cono  = "";
        area  = "";
        areaEncuesta  = "";
        region  = "";
        dominio  = "";
        idCarga  = "";
        frente = "";
        latitud = "";
        longitud = "";
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getCcdd() {
        return ccdd;
    }

    public void setCcdd(String ccdd) {
        this.ccdd = ccdd;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCcpp() {
        return ccpp;
    }

    public void setCcpp(String ccpp) {
        this.ccpp = ccpp;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCcdi() {
        return ccdi;
    }

    public void setCcdi(String ccdi) {
        this.ccdi = ccdi;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCodccpp() {
        return codccpp;
    }

    public void setCodccpp(String codccpp) {
        this.codccpp = codccpp;
    }

    public String getNomccpp() {
        return nomccpp;
    }

    public void setNomccpp(String nomccpp) {
        this.nomccpp = nomccpp;
    }

    public String getConglomerado() {
        return conglomerado;
    }

    public void setConglomerado(String conglomerado) {
        this.conglomerado = conglomerado;
    }

    public String getNorden() {
        return norden;
    }

    public void setNorden(String norden) {
        this.norden = norden;
    }

    public String getManzana_id() {
        return manzana_id;
    }

    public void setManzana_id(String manzana_id) {
        this.manzana_id = manzana_id;
    }

    public String getTipvia() {
        return tipvia;
    }

    public void setTipvia(String tipvia) {
        this.tipvia = tipvia;
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

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
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

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsuario_sup_id() {
        return usuario_sup_id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public void setUsuario_sup_id(String usuario_sup_id) {
        this.usuario_sup_id = usuario_sup_id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNroVivienda() {
        return nroVivienda;
    }

    public void setNroVivienda(String nroVivienda) {
        this.nroVivienda = nroVivienda;
    }

    public String getNroSegmento() {
        return nroSegmento;
    }

    public void setNroSegmento(String nroSegmento) {
        this.nroSegmento = nroSegmento;
    }

    public String getMarcoProviene() {
        return marcoProviene;
    }

    public void setMarcoProviene(String marcoProviene) {
        this.marcoProviene = marcoProviene;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNroPuerta2() {
        return nroPuerta2;
    }

    public void setNroPuerta2(String nroPuerta2) {
        this.nroPuerta2 = nroPuerta2;
    }

    public String getJefeHogar() {
        return jefeHogar;
    }

    public void setJefeHogar(String jefeHogar) {
        this.jefeHogar = jefeHogar;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getAerInicial() {
        return aerInicial;
    }

    public void setAerInicial(String aerInicial) {
        this.aerInicial = aerInicial;
    }

    public String getAerFinal() {
        return aerFinal;
    }

    public void setAerFinal(String aerFinal) {
        this.aerFinal = aerFinal;
    }

    public String getCono() {
        return cono;
    }

    public void setCono(String cono) {
        this.cono = cono;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaEncuesta() {
        return areaEncuesta;
    }

    public void setAreaEncuesta(String areaEncuesta) {
        this.areaEncuesta = areaEncuesta;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getIdCarga() {
        return idCarga;
    }

    public void setIdCarga(String idCarga) {
        this.idCarga = idCarga;
    }

    public String getFrente() {
        return frente;
    }

    public void setFrente(String frente) {
        this.frente = frente;
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

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.marco_id,_id);
        contentValues.put(SQLConstantes.marco_anio,anio);
        contentValues.put(SQLConstantes.marco_mes,mes);
        contentValues.put(SQLConstantes.marco_periodo,periodo);
        contentValues.put(SQLConstantes.marco_zona,zona);
        contentValues.put(SQLConstantes.marco_ccdd,ccdd);
        contentValues.put(SQLConstantes.marco_departamento,departamento);
        contentValues.put(SQLConstantes.marco_ccpp,ccpp);
        contentValues.put(SQLConstantes.marco_provincia,provincia);
        contentValues.put(SQLConstantes.marco_ccdi,ccdi);
        contentValues.put(SQLConstantes.marco_distrito,distrito);
        contentValues.put(SQLConstantes.marco_codccpp,codccpp);
        contentValues.put(SQLConstantes.marco_nomccpp,nomccpp);
        contentValues.put(SQLConstantes.marco_conglomerado,conglomerado);
        contentValues.put(SQLConstantes.marco_norden,norden);
        contentValues.put(SQLConstantes.marco_manzana_id,manzana_id);
        contentValues.put(SQLConstantes.marco_tipvia,tipvia);
        contentValues.put(SQLConstantes.marco_nomvia,nomvia);
        contentValues.put(SQLConstantes.marco_nropta,nropta);
        contentValues.put(SQLConstantes.marco_lote,lote);
        contentValues.put(SQLConstantes.marco_piso,piso);
        contentValues.put(SQLConstantes.marco_mza,mza);
        contentValues.put(SQLConstantes.marco_block,block);
        contentValues.put(SQLConstantes.marco_interior,interior);
        contentValues.put(SQLConstantes.marco_km,km);
        contentValues.put(SQLConstantes.marco_usuario_id,usuario_id);
        contentValues.put(SQLConstantes.marco_usuario_sup_id,usuario_sup_id);
        contentValues.put(SQLConstantes.marco_estado,estado);
        contentValues.put(SQLConstantes.marco_nro_selec_vivienda,nro_selec_vivienda);
        contentValues.put(SQLConstantes.marco_tipo_selec_vivienda,tipo_selec_vivienda);
        contentValues.put(SQLConstantes.marco_vivienda_reemplazo,vivienda_reemplazo);
        contentValues.put(SQLConstantes.marco_tipo,tipo);
        contentValues.put(SQLConstantes.marco_nroVivienda,nroVivienda);
        contentValues.put(SQLConstantes.marco_nroSegmento,nroSegmento);
        contentValues.put(SQLConstantes.marco_marcoProviene,marcoProviene);
        contentValues.put(SQLConstantes.marco_estrato,estrato);
        contentValues.put(SQLConstantes.marco_observaciones,observaciones);
        contentValues.put(SQLConstantes.marco_nroPuerta2,nroPuerta2);
        contentValues.put(SQLConstantes.marco_jefeHogar,jefeHogar);
        contentValues.put(SQLConstantes.marco_telefono,telefono);
        contentValues.put(SQLConstantes.marco_correo,correo);
        contentValues.put(SQLConstantes.marco_aerInicial,aerInicial);
        contentValues.put(SQLConstantes.marco_aerFinal,aerFinal);
        contentValues.put(SQLConstantes.marco_cono,cono);
        contentValues.put(SQLConstantes.marco_area,area);
        contentValues.put(SQLConstantes.marco_areaEncuesta,areaEncuesta);
        contentValues.put(SQLConstantes.marco_region,region);
        contentValues.put(SQLConstantes.marco_dominio,dominio);
        contentValues.put(SQLConstantes.marco_idCarga,idCarga);
        contentValues.put(SQLConstantes.marco_frente,frente);
        contentValues.put(SQLConstantes.marco_latitud,latitud);
        contentValues.put(SQLConstantes.marco_longitud,latitud);
        return contentValues;
    }
}
