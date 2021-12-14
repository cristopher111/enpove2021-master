package com.example.ricindigus.enpove2021.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDecorator {

    private final Date componente;

    public static final DateFormat FORMAT_ANHO = new SimpleDateFormat("yyyy");
    public static final DateFormat FORMAT_HORA_HM = new SimpleDateFormat("HH:mm");
    public static final DateFormat FORMAT_FECHA_DMA = new SimpleDateFormat("dd/MM/yyyy");
    public static final DateFormat FORMAT_FECHA_DMA_HORA_HMS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public DateDecorator(Date componente) {
        this.componente = componente;
    }

    public DateDecorator(String dia, String mes, String anho, String hora, String min, String seg) throws ParseException {
        this.componente = FORMAT_FECHA_DMA_HORA_HMS.parse(
                new StringDecorator(dia).completarIzquierda("0", 2)
                        +"/"+new StringDecorator(mes).completarIzquierda("0", 2)
                        +"/"+anho
                        +" "+hora+":"+min+":"+(seg==null?"00":seg));
    }

    public DateDecorator(Date fecha, String hora, String min, String seg) throws ParseException {
        this.componente = FORMAT_FECHA_DMA_HORA_HMS.parse(FORMAT_FECHA_DMA.format(fecha)+" "+hora+":"+min+":"+(seg==null?"00":seg));
    }

    public String getAnhoString() {
        return FORMAT_ANHO.format(componente);
    }

    public Date getComponente() {
        return componente;
    }

    public String getString(DateFormat dateFormat) {
        Date date = getComponente();
        return date==null ? "" : dateFormat.format(date);
    }
}

