package com.example.ricindigus.enpove2021.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ricindigus.enpove2021.modelo.pojos.Caratula;
import com.example.ricindigus.enpove2021.modelo.pojos.Carga;
import com.example.ricindigus.enpove2021.modelo.pojos.CoberturaFragment;
import com.example.ricindigus.enpove2021.modelo.pojos.Estado;
import com.example.ricindigus.enpove2021.modelo.pojos.Funcionario;
import com.example.ricindigus.enpove2021.modelo.pojos.Hogar;
import com.example.ricindigus.enpove2021.modelo.pojos.ItemMarco;
import com.example.ricindigus.enpove2021.modelo.pojos.M3Pregunta309;
import com.example.ricindigus.enpove2021.modelo.pojos.M3Pregunta318;
import com.example.ricindigus.enpove2021.modelo.pojos.Marco;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo1H;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo1V;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo3;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo4;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo5;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo6;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo7;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo8;
import com.example.ricindigus.enpove2021.modelo.pojos.Municipio;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOFragment;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOFragmentHogar;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOFragmentVivienda;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOLayout;
import com.example.ricindigus.enpove2021.modelo.pojos.Pais;
import com.example.ricindigus.enpove2021.modelo.pojos.Pregunta;
import com.example.ricindigus.enpove2021.modelo.pojos.ResVisitaEncuestador;
import com.example.ricindigus.enpove2021.modelo.pojos.ResVisitaSupervisor;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.modelo.pojos.ResultadoResidente;
import com.example.ricindigus.enpove2021.modelo.pojos.Ubigeo;
import com.example.ricindigus.enpove2021.modelo.pojos.Usuario;
import com.example.ricindigus.enpove2021.modelo.pojos.VisitaEncuestador;
import com.example.ricindigus.enpove2021.modelo.pojos.VisitaSupervisor;
import com.example.ricindigus.enpove2021.modelo.pojos.ViviendaItem;
import com.example.ricindigus.enpove2021.util.UtilsMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Data {
    Context contexto;
    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    public Data(Context contexto){
        this.contexto = contexto;
        sqLiteOpenHelper = new DataBaseHelper(contexto);
    }

    public Data(Context contexto,int flag) throws IOException {
        this.contexto = contexto;
        sqLiteOpenHelper = new DataBaseHelper(contexto);
        createDataBase();
    }

    public Data(Context contexto, String inputPath) throws IOException {
        this.contexto = contexto;
        sqLiteOpenHelper = new DataBaseHelper(contexto);
        createDataBase(inputPath);
    }


    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if(!dbExist){
            sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
            sqLiteDatabase.close();
            try{
                copyDataBase();
                sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_CARATULA);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_FUNCIONARIOS);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_HOGARES);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_VISITA_ENCUESTADOR);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_VISITA_SUPERVISOR);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_RESULTADO_ENCUESTADOR);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_RESULTADO_SUPERVISOR);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_RESULTADO_RESIDENTE);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO1H);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO1V);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO2);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO3);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO3_P309_RUTAS);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO3_P318_PERSONAS);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO4);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO5);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO6);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO7);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO8);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_LAYOUTS);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_FRAGMENTS);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_COBERTURA_FRAGMENTS);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_FRAGMENTS_HOGAR);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_FRAGMENTS_VIVIENDA);
                sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_BPR);
                sqLiteDatabase.close();
            }catch (IOException e){
                throw new Error("Error: copiando base de datos");
            }
        }

    }


    public void createDataBase(String inputPath) throws IOException {
        boolean dbExist = checkDataBase();
        if(dbExist){
            File dbFile = new File(SQLConstantes.DB_PATH + SQLConstantes.DB_NAME);
            SQLiteDatabase.deleteDatabase(dbFile);
        }
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
        sqLiteDatabase.close();
        try{
            copyDataBase(inputPath);
            sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_CARATULA);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_FUNCIONARIOS);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_HOGARES);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_VISITA_ENCUESTADOR);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_VISITA_SUPERVISOR);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_RESULTADO_ENCUESTADOR);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_RESULTADO_SUPERVISOR);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_RESULTADO_RESIDENTE);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO1H);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO1V);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO2);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO3);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO3_P309_RUTAS);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO3_P318_PERSONAS);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO4);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO5);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO6);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO7);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_MODULO8);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_LAYOUTS);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_FRAGMENTS);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_COBERTURA_FRAGMENTS);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_FRAGMENTS_HOGAR);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_FRAGMENTS_VIVIENDA);
            sqLiteDatabase.execSQL(SQLConstantes.SQL_CREATE_TABLA_BPR);
            sqLiteDatabase.close();
        }catch (IOException e){
            throw new Error("Error: copiando base de datos");
        }
    }


    public void copyDataBase() throws IOException{
        InputStream myInput = contexto.getAssets().open(SQLConstantes.DB_NAME);
        String outFileName = SQLConstantes.DB_PATH + SQLConstantes.DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) != -1){
            if (length > 0){
                myOutput.write(buffer,0,length);
            }
        }
        myOutput.flush();
        myInput.close();
        myOutput.close();
    }


    public void copyDataBase(String inputPath) throws IOException{
        InputStream myInput = new FileInputStream(inputPath);
        String outFileName = SQLConstantes.DB_PATH + SQLConstantes.DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) != -1){
            if (length > 0){
                myOutput.write(buffer,0,length);
            }
        }
        myOutput.flush();
        myInput.close();
        myOutput.close();

    }

    public void open() throws SQLException {
        String myPath = SQLConstantes.DB_PATH + SQLConstantes.DB_NAME;
        sqLiteDatabase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close(){
        if(sqLiteDatabase != null){
            sqLiteDatabase.close();
        }
    }

    public boolean checkDataBase(){
        try{
            String myPath = SQLConstantes.DB_PATH + SQLConstantes.DB_NAME;
            sqLiteDatabase = SQLiteDatabase.openDatabase(myPath,null, SQLiteDatabase.OPEN_READWRITE);
        }catch (Exception e){
            File dbFile = new File(SQLConstantes.DB_PATH + SQLConstantes.DB_NAME);
            return dbFile.exists();
        }
        if (sqLiteDatabase != null) sqLiteDatabase.close();

        return sqLiteDatabase != null ? true : false;
    }


    public int getNumeroPais(String id){
        int numero = 0;
        String[] whereArgs = new String[]{id};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablapaises,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                numero = cursor.getInt(cursor.getColumnIndex(SQLConstantes.paises_numero));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return numero;
    }

    public String getCodigoPais(int numero){
        String id = "";
        String[] whereArgs = new String[]{String.valueOf(numero)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablapaises,
                    null,SQLConstantes.WHERE_CLAUSE_NUMERO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                id = cursor.getString(cursor.getColumnIndex(SQLConstantes.paises_id));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return id;
    }

    public int getNumeroRutaPais(String id){
        int numero = 0;
        String[] whereArgs = new String[]{id};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablarutas,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                numero = cursor.getInt(cursor.getColumnIndex(SQLConstantes.rutas_numero));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return numero;
    }



    public String getCodigoRutaPais(int numero){
        String id = "";
        String[] whereArgs = new String[]{String.valueOf(numero)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablarutas,
                    null,SQLConstantes.WHERE_CLAUSE_NUMERO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                id = cursor.getString(cursor.getColumnIndex(SQLConstantes.rutas_id));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return id;
    }



    public Marco getMarco(String idVivienda){
        Marco marco = null;
        String[] whereArgs = new String[]{idVivienda};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamarco,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                marco = new Marco();
                marco.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_id)));
                marco.setAnio(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_anio)));
                marco.setMes(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_mes)));
                marco.setPeriodo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_periodo)));
                marco.setConglomerado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_conglomerado)));
                marco.setCodccpp(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_codccpp)));
                marco.setNomccpp(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nomccpp)));
                marco.setNorden(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_norden)));
                marco.setZona(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_zona)));
                marco.setManzana_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_manzana_id)));
                marco.setTipvia(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_tipvia)));
                marco.setNomvia(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nomvia)));
                marco.setNropta(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nropta)));
                marco.setLote(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_lote)));
                marco.setPiso(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_piso)));
                marco.setMza(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_mza)));
                marco.setBlock(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_block)));
                marco.setInterior(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_interior)));
                marco.setKm(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_km)));
                marco.setCcdd(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_ccdd)));
                marco.setDepartamento(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_departamento)));
                marco.setCcpp(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_ccpp)));
                marco.setProvincia(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_provincia)));
                marco.setCcdi(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_ccdi)));
                marco.setDistrito(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_distrito)));
                marco.setUsuario_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_usuario_id)));
                marco.setUsuario_sup_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_usuario_sup_id)));
                marco.setEstado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_estado)));
                marco.setNro_selec_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nro_selec_vivienda)));
                marco.setTipo_selec_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_tipo_selec_vivienda)));
                marco.setVivienda_reemplazo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_vivienda_reemplazo)));
                marco.setTipo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_tipo)));
                marco.setNroVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroVivienda)));
                marco.setNroSegmento(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroSegmento)));
                marco.setMarcoProviene(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_marcoProviene)));
                marco.setEstrato(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_estrato)));
                marco.setObservaciones(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_observaciones)));
                marco.setNroPuerta2(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroPuerta2)));
                marco.setJefeHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_jefeHogar)));
                marco.setTelefono(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_telefono)));
                marco.setCorreo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_correo)));
                marco.setAerInicial(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_aerInicial)));
                marco.setAerFinal(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_aerFinal)));
                marco.setCono(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_cono)));
                marco.setArea(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_area)));
                marco.setAreaEncuesta(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_areaEncuesta)));
                marco.setRegion(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_region)));
                marco.setDominio(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_dominio)));
                marco.setIdCarga(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_idCarga)));
                marco.setFrente(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_frente)));
                marco.setLatitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_latitud)));
                marco.setLongitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_longitud)));
//                marco.setNombre(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nombre)));
//                marco.setDni(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_dni)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return marco;
    }

    public ArrayList<Marco> getAllSegmentoViviendas(String idSegmento){
        ArrayList<Marco> listaViviendas= new ArrayList<>();
        String[] whereArgs = new String[]{idSegmento};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamarco,null,SQLConstantes.WHERE_CLAUSE_SEGMENTO,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                Marco marco = new Marco();
                marco.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_id)));
                marco.setAnio(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_anio)));
                marco.setMes(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_mes)));
                marco.setPeriodo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_periodo)));
                marco.setConglomerado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_conglomerado)));
                marco.setCodccpp(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_codccpp)));
                marco.setNomccpp(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nomccpp)));
                marco.setNorden(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_norden)));
                marco.setZona(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_zona)));
                marco.setManzana_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_manzana_id)));
                marco.setTipvia(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_tipvia)));
                marco.setNomvia(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nomvia)));
                marco.setNropta(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nropta)));
                marco.setLote(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_lote)));
                marco.setPiso(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_piso)));
                marco.setMza(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_mza)));
                marco.setBlock(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_block)));
                marco.setInterior(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_interior)));
                marco.setKm(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_km)));
                marco.setCcdd(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_ccdd)));
                marco.setDepartamento(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_departamento)));
                marco.setCcpp(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_ccpp)));
                marco.setProvincia(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_provincia)));
                marco.setCcdi(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_ccdi)));
                marco.setDistrito(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_distrito)));
                marco.setUsuario_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_usuario_id)));
                marco.setUsuario_sup_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_usuario_sup_id)));
                marco.setEstado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_estado)));
                marco.setNro_selec_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nro_selec_vivienda)));
                marco.setTipo_selec_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_tipo_selec_vivienda)));
                marco.setVivienda_reemplazo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_vivienda_reemplazo)));
                marco.setTipo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_tipo)));
                marco.setNroVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroVivienda)));
                marco.setNroSegmento(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroSegmento)));
                marco.setMarcoProviene(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_marcoProviene)));
                marco.setEstrato(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_estrato)));
                marco.setObservaciones(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_observaciones)));
                marco.setNroPuerta2(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroPuerta2)));
                marco.setJefeHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_jefeHogar)));
                marco.setTelefono(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_telefono)));
                marco.setCorreo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_correo)));
                marco.setAerInicial(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_aerInicial)));
                marco.setAerFinal(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_aerFinal)));
                marco.setCono(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_cono)));
                marco.setArea(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_area)));
                marco.setAreaEncuesta(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_areaEncuesta)));
                marco.setRegion(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_region)));
                marco.setDominio(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_dominio)));
                marco.setIdCarga(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_idCarga)));
                marco.setFrente(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_frente)));
                marco.setLatitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_latitud)));
                marco.setLongitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_longitud)));
                listaViviendas.add(marco);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return listaViviendas;
    }


    public ArrayList<ItemMarco> getListMarco(String idUsuario){
        ArrayList<ItemMarco> itemMarcos = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idUsuario)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamarco,
                    null,SQLConstantes.WHERE_CLAUSE_USUARIO_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                ItemMarco itemMarco = new ItemMarco();
                itemMarco.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_id)));
                itemMarco.setAnio(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_anio)));
                itemMarco.setMes(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_mes)));
                itemMarco.setPeriodo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_periodo)));
                itemMarco.setZona(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_zona)));
                itemMarco.setNorden(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_norden)));
                itemMarco.setEstado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_estado)));
                itemMarcos.add(itemMarco);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return itemMarcos;
    }

    public ArrayList<ViviendaItem> getListViviendas(String idUsuario){
        ArrayList<ViviendaItem> itemMarcos = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idUsuario)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamarco,
                    null,SQLConstantes.WHERE_CLAUSE_USUARIO_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                ViviendaItem itemVivienda = new ViviendaItem();
                itemVivienda.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_id)));
                itemVivienda.setSeleccionado(0);
                itemVivienda.setNroVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroVivienda)));
                itemVivienda.setConglomerado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_conglomerado)));
                itemVivienda.setNroSelecVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nro_selec_vivienda)));
                itemVivienda.setTipoSelecVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_tipo_selec_vivienda)));
                itemVivienda.setReemplazoVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_vivienda_reemplazo)));
                itemVivienda.setCentroPoblado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nomccpp)));
                itemVivienda.setPeriodo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_periodo)));
                itemVivienda.setResultado("");
                itemVivienda.setEstado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_estado)));
                itemVivienda.setAnio(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_anio)));
                itemVivienda.setMes(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_mes)));
                itemVivienda.setNrosegmento(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroSegmento)));
                itemMarcos.add(itemVivienda);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return itemMarcos;
    }

    public ArrayList<Carga> getListCarga(String idUsuario){
        ArrayList<Carga> lista = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idUsuario)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablausuariocarga,
                    null,SQLConstantes.WHERE_CLAUSE_USUARIO_CARGA,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                Carga carga = new Carga();
                carga.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.carga_id)));
                carga.setIdUsuario(cursor.getString(cursor.getColumnIndex(SQLConstantes.carga_idUsuario)));
                carga.setIdCarga(cursor.getString(cursor.getColumnIndex(SQLConstantes.carga_idCarga)));
                carga.setRol(cursor.getString(cursor.getColumnIndex(SQLConstantes.carga_rol)));
                lista.add(carga);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return lista;
    }

    public ArrayList<ViviendaItem> getListViviendasXCarga(ArrayList<Carga> listaCarga){
        ArrayList<ViviendaItem> itemMarcos = new ArrayList<>();

        for (Carga carga : listaCarga) {
            String[] whereArgs = new String[]{String.valueOf(carga.getIdCarga())};
            Cursor cursor = null;
            try{
                cursor = sqLiteDatabase.query(SQLConstantes.tablamarco,
                        null,SQLConstantes.WHERE_CLAUSE_USUARIO_ID_CARGA,whereArgs,null,null,null);
                while (cursor.moveToNext()){
                    ViviendaItem itemVivienda = new ViviendaItem();
                    itemVivienda.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_id)));
                    itemVivienda.setSeleccionado(0);
                    itemVivienda.setNroVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroVivienda)));
                    itemVivienda.setConglomerado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_conglomerado)));
                    itemVivienda.setNroSelecVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nro_selec_vivienda)));
                    itemVivienda.setTipoSelecVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_tipo_selec_vivienda)));
                    itemVivienda.setReemplazoVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_vivienda_reemplazo)));
                    itemVivienda.setCentroPoblado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nomccpp)));
                    itemVivienda.setPeriodo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_periodo)));
                    itemVivienda.setResultado("");
                    itemVivienda.setEstado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_estado)));
                    itemVivienda.setAnio(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_anio)));
                    itemVivienda.setMes(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_mes)));
                    itemVivienda.setNrosegmento(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroSegmento)));
                    itemMarcos.add(itemVivienda);
                }
            }finally{
                if(cursor != null) cursor.close();
            }
        }
        return itemMarcos;
    }

    public ArrayList<ViviendaItem> getListViviendasXCargaXReemplazo(ArrayList<Carga> listaCarga){
        ArrayList<ViviendaItem> itemMarcos = new ArrayList<>();

        for (Carga carga : listaCarga) {
            String[] whereArgs = new String[]{String.valueOf(carga.getIdCarga()),"2"};
            Cursor cursor = null;
            try{
                cursor = sqLiteDatabase.query(SQLConstantes.tablamarco,
                        null,SQLConstantes.WHERE_CLAUSE_USUARIO_ID_CARGA_VIVREEMPLAZO,whereArgs,null,null,null);
                while (cursor.moveToNext()){
                    ViviendaItem itemVivienda = new ViviendaItem();
                    itemVivienda.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_id)));
                    itemVivienda.setSeleccionado(0);
                    itemVivienda.setNroVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroVivienda)));
                    itemVivienda.setConglomerado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_conglomerado)));
                    itemVivienda.setNroSelecVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nro_selec_vivienda)));
                    itemVivienda.setTipoSelecVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_tipo_selec_vivienda)));
                    itemVivienda.setReemplazoVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_vivienda_reemplazo)));
                    itemVivienda.setCentroPoblado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nomccpp)));
                    itemVivienda.setPeriodo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_periodo)));
                    itemVivienda.setResultado("");
                    itemVivienda.setEstado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_estado)));
                    itemVivienda.setAnio(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_anio)));
                    itemVivienda.setMes(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_mes)));
                    itemVivienda.setNrosegmento(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroSegmento)));
                    itemMarcos.add(itemVivienda);
                }
            }finally{
                if(cursor != null) cursor.close();
            }
        }
        return itemMarcos;
    }

    public ArrayList<ViviendaItem> getListMarcoFiltradoXCarga(String anio, String mes, String periodo, String nroSegmento,ArrayList<Carga> listaCarga){
        ArrayList<ViviendaItem> viviendaItems = new ArrayList<>();
        for (Carga carga : listaCarga) {
            String[] whereArgs = new String[]{String.valueOf(anio), String.valueOf(mes), String.valueOf(periodo), String.valueOf(nroSegmento),String.valueOf(carga.getIdCarga())};
            Cursor cursor = null;
            try {
                cursor = sqLiteDatabase.query(SQLConstantes.tablamarco,
                        null, SQLConstantes.WHERE_CLAUSE_ANIO + " AND " +
                                SQLConstantes.WHERE_CLAUSE_MES + " AND " +
                                SQLConstantes.WHERE_CLAUSE_PERIODO + " AND " +
                                SQLConstantes.WHERE_CLAUSE_SEGMENTO + " AND " +
                                SQLConstantes.WHERE_CLAUSE_USUARIO_ID, whereArgs, null, null, null);
                while (cursor.moveToNext()) {
                    ViviendaItem itemVivienda = new ViviendaItem();
                    itemVivienda.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_id)));
                    itemVivienda.setSeleccionado(0);
                    itemVivienda.setNroVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroVivienda)));
                    itemVivienda.setConglomerado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_conglomerado)));
                    itemVivienda.setNroSelecVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nro_selec_vivienda)));
                    itemVivienda.setTipoSelecVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_tipo_selec_vivienda)));
                    itemVivienda.setReemplazoVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_vivienda_reemplazo)));
                    itemVivienda.setCentroPoblado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nomccpp)));
                    itemVivienda.setPeriodo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_periodo)));
                    itemVivienda.setResultado("");
                    itemVivienda.setEstado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_estado)));
                    itemVivienda.setAnio(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_anio)));
                    itemVivienda.setMes(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_mes)));
                    itemVivienda.setNrosegmento(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroSegmento)));
                    viviendaItems.add(itemVivienda);
                }
            } finally {
                if (cursor != null) cursor.close();
            }
        }
        return viviendaItems;
    }

    public ArrayList<ItemMarco> getListMarcoSupervisor(String idUsuario){
        ArrayList<ItemMarco> itemMarcos = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idUsuario)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamarco,
                    null,SQLConstantes.WHERE_CLAUSE_USUARIO_SUP_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                ItemMarco itemMarco = new ItemMarco();
                itemMarco.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_id)));
                itemMarco.setAnio(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_anio)));
                itemMarco.setMes(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_mes)));
                itemMarco.setPeriodo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_periodo)));
                itemMarco.setZona(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_zona)));
                itemMarco.setNorden(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_norden)));
                itemMarco.setEstado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_estado)));
                itemMarcos.add(itemMarco);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return itemMarcos;
    }

    public ArrayList<ItemMarco> getListMarcoFiltrado(int anio, int mes, int periodo, int zona){
        ArrayList<ItemMarco> itemMarcos = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(anio), String.valueOf(mes),String.valueOf(periodo),String.valueOf(zona)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamarco,
                    null,SQLConstantes.WHERE_CLAUSE_ANIO + " AND " +
                            SQLConstantes.WHERE_CLAUSE_MES + " AND " +
                            SQLConstantes.WHERE_CLAUSE_PERIODO + " AND " +
                            SQLConstantes.WHERE_CLAUSE_ZONA,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                ItemMarco itemMarco = new ItemMarco();
                itemMarco.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_id)));
                itemMarco.setAnio(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_anio)));
                itemMarco.setMes(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_mes)));
                itemMarco.setPeriodo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_periodo)));
                itemMarco.setZona(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_zona)));
                itemMarco.setNorden(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_norden)));
                itemMarco.setEstado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_estado)));
                itemMarcos.add(itemMarco);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return itemMarcos;
    }

    public ArrayList<ItemMarco> getListMarcoFiltrado2(String anio, String mes, String periodo, String zona){
        ArrayList<ItemMarco> itemMarcos = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(anio), String.valueOf(mes),String.valueOf(periodo),String.valueOf(zona)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamarco,
                    null,SQLConstantes.WHERE_CLAUSE_ANIO + " AND " +
                            SQLConstantes.WHERE_CLAUSE_MES + " AND " +
                            SQLConstantes.WHERE_CLAUSE_PERIODO + " AND " +
                            SQLConstantes.WHERE_CLAUSE_ZONA,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                ItemMarco itemMarco = new ItemMarco();
                itemMarco.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_id)));
                itemMarco.setAnio(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_anio)));
                itemMarco.setMes(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_mes)));
                itemMarco.setPeriodo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_periodo)));
                itemMarco.setZona(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_zona)));
                itemMarco.setNorden(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_norden)));
                itemMarco.setEstado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_estado)));
                itemMarcos.add(itemMarco);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return itemMarcos;
    }

    public ArrayList<ViviendaItem> getListMarcoFiltrado(String anio, String mes, String periodo, String nroSegmento){
        ArrayList<ViviendaItem> viviendaItems = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(anio), String.valueOf(mes),String.valueOf(periodo),String.valueOf(nroSegmento)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamarco,
                    null,SQLConstantes.WHERE_CLAUSE_ANIO + " AND " +
                            SQLConstantes.WHERE_CLAUSE_MES + " AND " +
                            SQLConstantes.WHERE_CLAUSE_PERIODO + " AND " +
                            SQLConstantes.WHERE_CLAUSE_SEGMENTO,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                ViviendaItem itemVivienda = new ViviendaItem();
                itemVivienda.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_id)));
                itemVivienda.setSeleccionado(0);
                itemVivienda.setNroVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroVivienda)));
                itemVivienda.setConglomerado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_conglomerado)));
                itemVivienda.setNroSelecVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nro_selec_vivienda)));
                itemVivienda.setTipoSelecVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_tipo_selec_vivienda)));
                itemVivienda.setReemplazoVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_vivienda_reemplazo)));
                itemVivienda.setCentroPoblado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nomccpp)));
                itemVivienda.setPeriodo(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_periodo)));
                itemVivienda.setResultado("");
                itemVivienda.setEstado(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_estado)));
                itemVivienda.setAnio(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_anio)));
                itemVivienda.setMes(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_mes)));
                itemVivienda.setNrosegmento(cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nroSegmento)));
                viviendaItems.add(itemVivienda);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return viviendaItems;
    }

    public void insertarVisitaEncuestador(VisitaEncuestador visita){
        ContentValues contentValues = visita.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablavisitasencuestador,null,contentValues);
    }

    public void insertarVisitasEncuestador(ArrayList<VisitaEncuestador> visitas){
        for (VisitaEncuestador visita : visitas) {
            try {
                insertarVisitaEncuestador(visita);
            }catch (SQLiteException e){
                e.printStackTrace();
            }
        }
    }

    public void insertarVisitaSupervisor(VisitaSupervisor visitaSupervisor){
        ContentValues contentValues = visitaSupervisor.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablavisitassupervisor,null,contentValues);
    }

    public void insertarVisitasSupervisor(ArrayList<VisitaSupervisor> visitaSupervisors){
        for (VisitaSupervisor visitaSupervisor : visitaSupervisors) {
            try {
                insertarVisitaSupervisor(visitaSupervisor);
            }catch (SQLiteException e){
                e.printStackTrace();
            }
        }
    }

    public void insertarHogar(Hogar hogar){
        ContentValues contentValues = hogar.toValues();
        sqLiteDatabase.insert(SQLConstantes.tablahogares,null,contentValues);
    }

    public void insertarHogares(ArrayList<Hogar> hogares){
        for (Hogar hogar : hogares) {
            try {
                insertarHogar(hogar);
            }catch (SQLiteException e){
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Hogar> getAllHogaresVivienda(String idVivienda){
        ArrayList<Hogar> hogars = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idVivienda)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablahogares,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                Hogar hogar = new Hogar();
                hogar.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_id)));
                hogar.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_id_vivienda)));
                hogar.setId_informante(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_id_informante)));
                hogar.setNom_ape(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_nom_ape)));
                hogar.setApe_paterno(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_ape_paterno)));
                hogar.setApe_materno(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_ape_materno)));
                hogar.setEstado(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_estado)));
                hogar.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_numero)));
                hogar.setNroviven(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_nroviven)));
                hogar.setVive(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_vive)));
                hogar.setNropersonas(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_nropersonas)));
                hogar.setPrincipal(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_principal)));
                hogar.setCobertura(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_cobertura)));
                hogar.setP15(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p15)));
                hogar.setP15_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p15_o)));
                hogar.setP16(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p16)));
                hogar.setP17(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p17)));
                hogar.setP18(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p18)));
                hogar.setP19(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p19)));
                hogar.setP20(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p20)));
                hogars.add(hogar);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return hogars;
    }

    public Hogar getHogar(String id){
        Hogar hogar = null;
        String[] whereArgs = new String[]{String.valueOf(id)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablahogares,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if (cursor.getCount() == 1){
                cursor.moveToFirst();
                hogar = new Hogar();
                hogar.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_id)));
                hogar.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_id_vivienda)));
                hogar.setId_informante(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_id_informante)));
                hogar.setNom_ape(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_nom_ape)));
                hogar.setApe_paterno(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_ape_paterno)));
                hogar.setApe_materno(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_ape_materno)));
                hogar.setEstado(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_estado)));
                hogar.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_numero)));
                hogar.setNroviven(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_nroviven)));
                hogar.setNropersonas(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_nropersonas)));
                hogar.setVive(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_vive)));
                hogar.setPrincipal(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_principal)));
                hogar.setCobertura(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_cobertura)));
                hogar.setP15(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p15)));
                hogar.setP15_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p15_o)));
                hogar.setP16(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p16)));
                hogar.setP17(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p17)));
                hogar.setP18(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p18)));
                hogar.setP19(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p19)));
                hogar.setP20(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p20)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return hogar;
    }

    public Hogar getHogarNumero(String idVivienda,String numero){
        Hogar hogar = null;
        String[] whereArgs = new String[]{String.valueOf(idVivienda),String.valueOf(numero)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablahogares,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_NUMERO,whereArgs,null,null,null);
            if (cursor.getCount() == 1){
                cursor.moveToFirst();
                hogar = new Hogar();
                hogar.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_id)));
                hogar.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_id_vivienda)));
                hogar.setId_informante(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_id_informante)));
                hogar.setNom_ape(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_nom_ape)));
                hogar.setApe_paterno(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_ape_paterno)));
                hogar.setApe_materno(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_ape_materno)));
                hogar.setEstado(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_estado)));
                hogar.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_numero)));
                hogar.setNroviven(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_nroviven)));
                hogar.setNropersonas(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_nropersonas)));
                hogar.setVive(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_vive)));
                hogar.setPrincipal(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_principal)));
                hogar.setCobertura(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_cobertura)));
                hogar.setP15(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p15)));
                hogar.setP15_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p15_o)));
                hogar.setP16(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p16)));
                hogar.setP17(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p17)));
                hogar.setP18(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p18)));
                hogar.setP19(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p19)));
                hogar.setP20(cursor.getString(cursor.getColumnIndex(SQLConstantes.hogar_p20)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return hogar;
    }

    public Funcionario getFuncionario(String id){
        Funcionario funcionario = null;
        String[] whereArgs = new String[]{String.valueOf(id)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafuncionarios,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if (cursor.getCount() == 1){
                cursor.moveToFirst();
                funcionario = new Funcionario();
                funcionario.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.funcionarios_id)));
                funcionario.setDni_coor(cursor.getString(cursor.getColumnIndex(SQLConstantes.funcionarios_dni_coord)));
                funcionario.setDni_encu(cursor.getString(cursor.getColumnIndex(SQLConstantes.funcionarios_dni_encu)));
                funcionario.setDni_sup(cursor.getString(cursor.getColumnIndex(SQLConstantes.funcionarios_dni_sup)));
                funcionario.setDni_supn(cursor.getString(cursor.getColumnIndex(SQLConstantes.funcionarios_dni_supn)));
                funcionario.setNombre_coord(cursor.getString(cursor.getColumnIndex(SQLConstantes.funcionarios_nombre_coord)));
                funcionario.setNombre_encu(cursor.getString(cursor.getColumnIndex(SQLConstantes.funcionarios_nombre_encu)));
                funcionario.setNombre_sup(cursor.getString(cursor.getColumnIndex(SQLConstantes.funcionarios_nombre_sup)));
                funcionario.setNombre_supn(cursor.getString(cursor.getColumnIndex(SQLConstantes.funcionarios_nombre_supn)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return funcionario;
    }


    public ArrayList<VisitaEncuestador> getAllVisitasHogar(String idHogar){
        ArrayList<VisitaEncuestador> visitaEncuestadors = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idHogar)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablavisitasencuestador,
                    null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                VisitaEncuestador visitaEncuestador = new VisitaEncuestador();
                visitaEncuestador.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id)));
                visitaEncuestador.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_vivienda)));
                visitaEncuestador.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_hogar)));
                visitaEncuestador.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_numero)));
                visitaEncuestador.setVis_fecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));
                visitaEncuestador.setVis_fecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                visitaEncuestador.setVis_fecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                visitaEncuestador.setVis_hor_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_ini)));
                visitaEncuestador.setVis_min_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_ini)));
                visitaEncuestador.setVis_hor_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_fin)));
                visitaEncuestador.setVis_min_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_fin)));
                visitaEncuestador.setProx_vis_fecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_dd)));
                visitaEncuestador.setProx_vis_fecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_mm)));
                visitaEncuestador.setProx_vis_fecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_aa)));
                visitaEncuestador.setProx_vis_hor(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_hor)));
                visitaEncuestador.setProx_vis_min(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_min)));
                visitaEncuestador.setVis_resu(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu)));
                visitaEncuestador.setVis_resu_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu_esp)));
                visitaEncuestador.setLatitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_latitud)));
                visitaEncuestador.setLongitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_longitud)));
                visitaEncuestador.setAltura(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_altura)));
                visitaEncuestador.setTipo_ent(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_tipo_entrevista)));
                visitaEncuestador.setTipo_ent_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_tipo_entrevista_o)));
                visitaEncuestadors.add(visitaEncuestador);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return visitaEncuestadors;
    }

    public ArrayList<VisitaEncuestador> getAllVisitasReult(String idVivienda){
        ArrayList<VisitaEncuestador> visitaEncuestadors = new ArrayList<>();;
        String[] whereArgs = new String[]{idVivienda};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablavisitasencuestador,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                VisitaEncuestador visitaEncuestador = new VisitaEncuestador();
                visitaEncuestador.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id)));
                visitaEncuestador.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_vivienda)));
                visitaEncuestador.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_hogar)));
                visitaEncuestador.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_numero)));
                visitaEncuestador.setVis_fecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));
                visitaEncuestador.setVis_fecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                visitaEncuestador.setVis_fecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                visitaEncuestador.setVis_hor_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_ini)));
                visitaEncuestador.setVis_min_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_ini)));
                visitaEncuestador.setVis_hor_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_fin)));
                visitaEncuestador.setVis_min_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_fin)));
                visitaEncuestador.setProx_vis_fecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_dd)));
                visitaEncuestador.setProx_vis_fecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_mm)));
                visitaEncuestador.setProx_vis_fecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_aa)));
                visitaEncuestador.setProx_vis_hor(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_hor)));
                visitaEncuestador.setProx_vis_min(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_min)));
                visitaEncuestador.setVis_resu(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu)));
                visitaEncuestador.setVis_resu_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu_esp)));
                visitaEncuestador.setLatitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_latitud)));
                visitaEncuestador.setLongitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_longitud)));
                visitaEncuestador.setAltura(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_altura)));
                visitaEncuestador.setTipo_ent(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_tipo_entrevista)));
                visitaEncuestador.setTipo_ent_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_tipo_entrevista_o)));
                visitaEncuestadors.add(visitaEncuestador);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return visitaEncuestadors;
    }

    public VisitaEncuestador getUltimaVisitasHogar(String idHogar){
        VisitaEncuestador visitaEncuestador=null;
        String[] whereArgs = new String[]{String.valueOf(idHogar)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablavisitasencuestador,
                    null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                visitaEncuestador = new VisitaEncuestador();
                visitaEncuestador.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id)));
                visitaEncuestador.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_vivienda)));
                visitaEncuestador.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_hogar)));
                visitaEncuestador.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_numero)));
                visitaEncuestador.setVis_fecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));
                visitaEncuestador.setVis_fecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                visitaEncuestador.setVis_fecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                visitaEncuestador.setVis_hor_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_ini)));
                visitaEncuestador.setVis_min_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_ini)));
                visitaEncuestador.setVis_hor_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_fin)));
                visitaEncuestador.setVis_min_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_fin)));
                visitaEncuestador.setProx_vis_fecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_dd)));
                visitaEncuestador.setProx_vis_fecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_mm)));
                visitaEncuestador.setProx_vis_fecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_aa)));
                visitaEncuestador.setProx_vis_hor(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_hor)));
                visitaEncuestador.setProx_vis_min(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_min)));
                visitaEncuestador.setVis_resu(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu)));
                visitaEncuestador.setVis_resu_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu_esp)));
                visitaEncuestador.setLatitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_latitud)));
                visitaEncuestador.setLongitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_longitud)));
                visitaEncuestador.setAltura(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_altura)));
                visitaEncuestador.setTipo_ent(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_tipo_entrevista)));
                visitaEncuestador.setTipo_ent_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_tipo_entrevista_o)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return visitaEncuestador;
    }

    public VisitaEncuestador getVisitaEncuestador(String id){
        VisitaEncuestador visitaEncuestador=null;
        String[] whereArgs = new String[]{String.valueOf(id)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablavisitasencuestador,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                visitaEncuestador = new VisitaEncuestador();
                visitaEncuestador.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id)));
                visitaEncuestador.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_vivienda)));
                visitaEncuestador.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_hogar)));
                visitaEncuestador.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_numero)));
                visitaEncuestador.setVis_fecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));
                visitaEncuestador.setVis_fecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                visitaEncuestador.setVis_fecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                visitaEncuestador.setVis_hor_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_ini)));
                visitaEncuestador.setVis_min_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_ini)));
                visitaEncuestador.setVis_hor_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_fin)));
                visitaEncuestador.setVis_min_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_fin)));
                visitaEncuestador.setProx_vis_fecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_dd)));
                visitaEncuestador.setProx_vis_fecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_mm)));
                visitaEncuestador.setProx_vis_fecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_aa)));
                visitaEncuestador.setProx_vis_hor(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_hor)));
                visitaEncuestador.setProx_vis_min(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_min)));
                visitaEncuestador.setVis_resu(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu)));
                visitaEncuestador.setVis_resu_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu_esp)));
                visitaEncuestador.setLatitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_latitud)));
                visitaEncuestador.setLongitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_longitud)));
                visitaEncuestador.setAltura(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_altura)));
                visitaEncuestador.setTipo_ent(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_tipo_entrevista)));
                visitaEncuestador.setTipo_ent_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_tipo_entrevista_o)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return visitaEncuestador;
    }

    public VisitaEncuestador getPrimeraVisitaEncuestador(String idVivienda,String idhogar,String numero){
        VisitaEncuestador visitaEncuestador=null;
        String[] whereArgs = new String[]{String.valueOf(idVivienda),String.valueOf(idhogar),String.valueOf(numero)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablavisitasencuestador,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_HOGAR_NUMERO,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                visitaEncuestador = new VisitaEncuestador();
                visitaEncuestador.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id)));
                visitaEncuestador.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_vivienda)));
                visitaEncuestador.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_hogar)));
                visitaEncuestador.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_numero)));
                visitaEncuestador.setVis_fecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));
                visitaEncuestador.setVis_fecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                visitaEncuestador.setVis_fecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                visitaEncuestador.setVis_hor_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_ini)));
                visitaEncuestador.setVis_min_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_ini)));
                visitaEncuestador.setVis_hor_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_fin)));
                visitaEncuestador.setVis_min_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_fin)));
                visitaEncuestador.setProx_vis_fecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_dd)));
                visitaEncuestador.setProx_vis_fecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_mm)));
                visitaEncuestador.setProx_vis_fecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_aa)));
                visitaEncuestador.setProx_vis_hor(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_hor)));
                visitaEncuestador.setProx_vis_min(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_min)));
                visitaEncuestador.setVis_resu(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu)));
                visitaEncuestador.setVis_resu_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu_esp)));
                visitaEncuestador.setLatitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_latitud)));
                visitaEncuestador.setLongitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_longitud)));
                visitaEncuestador.setAltura(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_altura)));
                visitaEncuestador.setTipo_ent(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_tipo_entrevista)));
                visitaEncuestador.setTipo_ent_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_tipo_entrevista_o)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return visitaEncuestador;
    }

    public void deleteAllVisitasEncuestador(String idVivienda){
        String[] whereArgs = new String[]{idVivienda};
        sqLiteDatabase.delete(SQLConstantes.tablavisitasencuestador,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs);
    }

    public void deleteAllVisitasSupervisor(String idVivienda){
        String[] whereArgs = new String[]{idVivienda};
        sqLiteDatabase.delete(SQLConstantes.tablavisitassupervisor,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs);
    }

    public void deleteAllHogares(String idVivienda){
        String[] whereArgs = new String[]{idVivienda};
        sqLiteDatabase.delete(SQLConstantes.tablahogares,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs);
    }

    public ArrayList<Pregunta> getAllPreguntasUsuario(String idUsuario){
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idUsuario)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablabpr,null,SQLConstantes.WHERE_CLAUSE_USUARIO_CARGA,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                Pregunta preguntasbpr = new Pregunta();
                preguntasbpr.setId(cursor.getString(cursor.getColumnIndex(SQLConstantes.bpr_id)));
                preguntasbpr.setIdUsuario(cursor.getString(cursor.getColumnIndex(SQLConstantes.bpr_idUsuario)));
                preguntasbpr.setUsuario(cursor.getString(cursor.getColumnIndex(SQLConstantes.bpr_usuario)));
                preguntasbpr.setNombres(cursor.getString(cursor.getColumnIndex(SQLConstantes.bpr_nombres)));
                preguntasbpr.setFecha(cursor.getString(cursor.getColumnIndex(SQLConstantes.bpr_fecha)));
                preguntasbpr.setHora(cursor.getString(cursor.getColumnIndex(SQLConstantes.bpr_hora)));
                preguntasbpr.setPregunta(cursor.getString(cursor.getColumnIndex(SQLConstantes.bpr_pregunta)));
                preguntas.add(preguntasbpr);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return preguntas;
    }

    public ArrayList<VisitaEncuestador> getAllVisitasEncuestadorVivienda(String idVivienda){
        ArrayList<VisitaEncuestador> visitaEncuestadors = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idVivienda)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablavisitasencuestador,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                VisitaEncuestador visitaEncuestador = new VisitaEncuestador();
                visitaEncuestador.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id)));
                visitaEncuestador.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_vivienda)));
                visitaEncuestador.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_hogar)));
                visitaEncuestador.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_numero)));
                visitaEncuestador.setVis_fecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));
                visitaEncuestador.setVis_fecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                visitaEncuestador.setVis_fecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                visitaEncuestador.setVis_hor_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_ini)));
                visitaEncuestador.setVis_min_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_ini)));
                visitaEncuestador.setVis_hor_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_fin)));
                visitaEncuestador.setVis_min_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_fin)));
                visitaEncuestador.setProx_vis_fecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_dd)));
                visitaEncuestador.setProx_vis_fecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_mm)));
                visitaEncuestador.setProx_vis_fecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_aa)));
                visitaEncuestador.setProx_vis_hor(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_hor)));
                visitaEncuestador.setProx_vis_min(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_min)));
                visitaEncuestador.setVis_resu(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu)));
                visitaEncuestador.setVis_resu_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu_esp)));
                visitaEncuestador.setLatitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_latitud)));
                visitaEncuestador.setLongitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_longitud)));
                visitaEncuestador.setAltura(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_altura)));
                visitaEncuestador.setTipo_ent(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_tipo_entrevista)));
                visitaEncuestador.setTipo_ent_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_tipo_entrevista_o)));
                visitaEncuestadors.add(visitaEncuestador);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return visitaEncuestadors;
    }

    public ArrayList<VisitaSupervisor> getAllVisitasSupervisorHogar(String idHogar){
        ArrayList<VisitaSupervisor> visitaSupervisors = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idHogar)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablavisitassupervisor,
                    null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                VisitaSupervisor visitaSupervisor = new VisitaSupervisor();
                visitaSupervisor.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id)));
                visitaSupervisor.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_vivienda)));
                visitaSupervisor.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_hogar)));
                visitaSupervisor.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_numero)));
                visitaSupervisor.setVis_fecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));
                visitaSupervisor.setVis_fecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                visitaSupervisor.setVis_fecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                visitaSupervisor.setVis_hor_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_ini)));
                visitaSupervisor.setVis_min_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_ini)));
                visitaSupervisor.setVis_hor_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_fin)));
                visitaSupervisor.setVis_min_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_fin)));
                visitaSupervisor.setVis_resu(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu)));
                visitaSupervisor.setVis_resu_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu_esp)));
                visitaSupervisors.add(visitaSupervisor);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return visitaSupervisors;
    }

    public ArrayList<VisitaSupervisor> getAllVisitasSupervisorVivienda(String idVivienda){
        ArrayList<VisitaSupervisor> visitaSupervisors = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idVivienda)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablavisitassupervisor,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                VisitaSupervisor visitaSupervisor = new VisitaSupervisor();
                visitaSupervisor.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id)));
                visitaSupervisor.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_vivienda)));
                visitaSupervisor.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id_hogar)));
                visitaSupervisor.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_numero)));
                visitaSupervisor.setVis_fecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));
                visitaSupervisor.setVis_fecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                visitaSupervisor.setVis_fecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                visitaSupervisor.setVis_hor_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_ini)));
                visitaSupervisor.setVis_min_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_ini)));
                visitaSupervisor.setVis_hor_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_fin)));
                visitaSupervisor.setVis_min_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_fin)));
                visitaSupervisor.setVis_resu(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu)));
                visitaSupervisor.setVis_resu_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu_esp)));
                visitaSupervisors.add(visitaSupervisor);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return visitaSupervisors;
    }

    public ArrayList<ResVisitaEncuestador> getAllResultadoVisitaEncuestador(String idVivienda){
        ArrayList<ResVisitaEncuestador> resVisitaEncuestadors= new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idVivienda)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresultadoencuestador,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                ResVisitaEncuestador resVisitaEncuestador = new ResVisitaEncuestador();
                resVisitaEncuestador.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_encuestador_id)));
                resVisitaEncuestador.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_encuestador_id_vivienda)));
                resVisitaEncuestador.setVis_resultado_final(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_encuestador_vis_resultado_final)));
                resVisitaEncuestador.setVis_resultado_final_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_encuestador_vis_resultado_final_o)));
                resVisitaEncuestador.setVis_fecha_final_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_encuestador_vis_fecha_final_dd)));
                resVisitaEncuestador.setVis_fecha_final_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_encuestador_vis_fecha_final_mm)));
                resVisitaEncuestador.setVis_fecha_final_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_encuestador_vis_fecha_final_aa)));
                resVisitaEncuestadors.add(resVisitaEncuestador);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return resVisitaEncuestadors;
    }

    public ArrayList<ResVisitaSupervisor> getAllResultadoVisitaSupervisor(String id){
        ArrayList<ResVisitaSupervisor> resVisitaSupervisors = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(id)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresultadosupervisor,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                ResVisitaSupervisor resVisitaSupervisor = new ResVisitaSupervisor();
                resVisitaSupervisor.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_supervisor_id)));
                resVisitaSupervisor.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_supervisor_id_vivienda)));
                resVisitaSupervisor.setVis_resultado_final(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_supervisor_vis_resultado_final)));
                resVisitaSupervisor.setVis_resultado_final_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_supervisor_vis_resultado_final_o)));
                resVisitaSupervisor.setVis_fecha_final_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_supervisor_vis_fecha_final_dd)));
                resVisitaSupervisor.setVis_fecha_final_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_supervisor_vis_fecha_final_mm)));
                resVisitaSupervisor.setVis_fecha_final_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_supervisor_vis_fecha_final_aa)));
                resVisitaSupervisors.add(resVisitaSupervisor);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return resVisitaSupervisors;
    }

    public ArrayList<ResultadoResidente> getAllResultadoResidente(String id){
        ArrayList<ResultadoResidente> resultadoResidentes = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(id)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresultadoresidente,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                ResultadoResidente resultadoResidente = new ResultadoResidente();
                resultadoResidente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_id)));
                resultadoResidente.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_id_hogar)));
                resultadoResidente.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_id_vivienda)));
                resultadoResidente.setFecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_fecha_dd)));
                resultadoResidente.setFecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_fecha_mm)));
                resultadoResidente.setFecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_fecha_aa)));
                resultadoResidente.setHor_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_hor_ini)));
                resultadoResidente.setMin_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_min_ini)));
                resultadoResidente.setHor_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_hor_fin)));
                resultadoResidente.setMin_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_min_fin)));
                resultadoResidente.setResultado_entrevista(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_resultado_entrevista)));
                resultadoResidente.setResultado_entrevista_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_resultado_entrevista_o)));
                resultadoResidente.setTipo_entrevista(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_tipo_entrevista)));
                resultadoResidente.setTipo_entrevista_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_tipo_entrevista_o)));
                resultadoResidente.setDonde_entrevista(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_donde_entrevista)));
                resultadoResidente.setDonde_entrevista_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_donde_entrevista_o)));
                resultadoResidentes.add(resultadoResidente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return resultadoResidentes;
    }

    public ResultadoResidente getResultadoResidente(String id){
        ResultadoResidente resultadoResidente = null;
        String[] whereArgs = new String[]{String.valueOf(id)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresultadoresidente,null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if (cursor.getCount() == 1){
                cursor.moveToFirst();
                resultadoResidente = new ResultadoResidente();
                resultadoResidente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_id)));
                resultadoResidente.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_id_hogar)));
                resultadoResidente.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_id_vivienda)));
                resultadoResidente.setFecha_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_fecha_dd)));
                resultadoResidente.setFecha_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_fecha_mm)));
                resultadoResidente.setFecha_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_fecha_aa)));
                resultadoResidente.setHor_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_hor_ini)));
                resultadoResidente.setMin_ini(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_min_ini)));
                resultadoResidente.setHor_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_hor_fin)));
                resultadoResidente.setMin_fin(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_min_fin)));
                resultadoResidente.setResultado_entrevista(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_resultado_entrevista)));
                resultadoResidente.setResultado_entrevista_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_resultado_entrevista_o)));
                resultadoResidente.setTipo_entrevista(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_tipo_entrevista)));
                resultadoResidente.setTipo_entrevista_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_tipo_entrevista_o)));
                resultadoResidente.setDonde_entrevista(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_donde_entrevista)));
                resultadoResidente.setDonde_entrevista_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.resultado_residente_donde_entrevista_o)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return resultadoResidente;
    }

    public Residente getResidente(String id){
        Residente residente = null;
        String[] whereArgs = new String[]{String.valueOf(id)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if (cursor.getCount() == 1){
                cursor.moveToFirst();
                residente = new Residente();
                residente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id)));
                residente.setId_informante(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_informante)));
                residente.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_hogar)));
                residente.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_vivienda)));
                residente.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_numero)));
                residente.setC2_p202(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202)));
                residente.setC2_p202_pat(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_pat)));
                residente.setC2_p202_mat(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_mat)));
                residente.setC2_p203(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p203)));
                residente.setC2_p204(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p204)));
                residente.setC2_p205_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_a)));
                residente.setC2_p205_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_m)));
                residente.setC2_p206(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p206)));
                residente.setC2_p207(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p207)));
                residente.setC2_p207_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p207_o)));
                residente.setC2_p208(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p208)));
                residente.setC2_p209(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209)));
                residente.setC2_p209_n(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_n)));
                residente.setC2_p209_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_p)));
                residente.setC2_p209_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_pos)));
                residente.setC2_p210(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210)));
                residente.setC2_p210_n(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_n)));
                residente.setC2_p210_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_p)));
                residente.setC2_p210_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_pos)));
                residente.setC2_p211(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211)));
                residente.setC2_p211_nom(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_nom)));
                residente.setC2_p211_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_pos)));
                residente.setC2_p211_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_1)));
                residente.setC2_p211_1_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_1_o)));
                residente.setC2_p212(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p212)));
                residente.setP200_aportante(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_p200_aportante)));
                residente.setOBS200(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_OBS200)));
                residente.setCOB200(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_COB200)));
                residente.setEncuestado_cobertura(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_encuestado_cobertura)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return residente;
    }

    public Residente getResidenteNumero(String idVivienda,String idHogar,String numero){
        Residente residente = null;
        String[] whereArgs = new String[]{String.valueOf(idVivienda),String.valueOf(idHogar),String.valueOf(numero)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_HOGAR_NUMERO,whereArgs,null,null,null);
            if (cursor.getCount() == 1){
                cursor.moveToFirst();
                residente = new Residente();
                residente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id)));
                residente.setId_informante(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_informante)));
                residente.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_hogar)));
                residente.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_vivienda)));
                residente.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_numero)));
                residente.setC2_p202(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202)));
                residente.setC2_p202_pat(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_pat)));
                residente.setC2_p202_mat(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_mat)));
                residente.setC2_p203(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p203)));
                residente.setC2_p204(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p204)));
                residente.setC2_p205_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_a)));
                residente.setC2_p205_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_m)));
                residente.setC2_p206(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p206)));
                residente.setC2_p207(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p207)));
                residente.setC2_p207_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p207_o)));
                residente.setC2_p208(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p208)));
                residente.setC2_p209(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209)));
                residente.setC2_p209_n(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_n)));
                residente.setC2_p209_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_p)));
                residente.setC2_p209_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_pos)));
                residente.setC2_p210(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210)));
                residente.setC2_p210_n(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_n)));
                residente.setC2_p210_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_p)));
                residente.setC2_p210_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_pos)));
                residente.setC2_p211(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211)));
                residente.setC2_p211_nom(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_nom)));
                residente.setC2_p211_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_pos)));
                residente.setC2_p211_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_1)));
                residente.setC2_p211_1_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_1_o)));
                residente.setC2_p212(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p212)));
                residente.setP200_aportante(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_p200_aportante)));
                residente.setOBS200(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_OBS200)));
                residente.setCOB200(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_COB200)));
                residente.setEncuestado_cobertura(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_encuestado_cobertura)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return residente;
    }

    public Cursor getCursorVisitas(String tablaVisitas, String idHogar){
        String[] whereArgs = new String[]{idHogar};
        Cursor cursor = null;
        cursor = sqLiteDatabase.query(tablaVisitas,null, SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
        if(cursor != null) cursor.moveToFirst();
        return cursor;
    }

    public ArrayList<Residente> getAllResidentesHogar(String idHogar){
        ArrayList<Residente> residentes = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idHogar)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,
                    null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                Residente residente = new Residente();
                residente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id)));
                residente.setId_informante(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_informante)));
                residente.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_hogar)));
                residente.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_vivienda)));
                residente.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_numero)));
                residente.setC2_p202(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202)));
                residente.setC2_p202_pat(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_pat)));
                residente.setC2_p202_mat(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_mat)));
                residente.setC2_p203(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p203)));
                residente.setC2_p204(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p204)));
                residente.setC2_p205_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_a)));
                residente.setC2_p205_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_m)));
                residente.setC2_p206(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p206)));
                residente.setC2_p207(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p207)));
                residente.setC2_p207_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p207_o)));
                residente.setC2_p208(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p208)));
                residente.setC2_p209(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209)));
                residente.setC2_p209_n(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_n)));
                residente.setC2_p209_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_p)));
                residente.setC2_p209_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_pos)));
                residente.setC2_p210(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210)));
                residente.setC2_p210_n(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_n)));
                residente.setC2_p210_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_p)));
                residente.setC2_p210_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_pos)));
                residente.setC2_p211(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211)));
                residente.setC2_p211_nom(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_nom)));
                residente.setC2_p211_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_pos)));
                residente.setC2_p211_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_1)));
                residente.setC2_p211_1_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_1_o)));
                residente.setC2_p212(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p212)));
                residente.setP200_aportante(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_p200_aportante)));
                residente.setOBS200(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_OBS200)));
                residente.setCOB200(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_COB200)));
                residente.setEncuestado_cobertura(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_encuestado_cobertura)));
                residentes.add(residente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return residentes;
    }

    public ArrayList<Residente> getAllResidentesVenezolanosHogar(String idHogar,String p208){
        ArrayList<Residente> residentes = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idHogar),String.valueOf(p208)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID_P208,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                Residente residente = new Residente();
                residente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id)));
                residente.setId_informante(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_informante)));
                residente.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_hogar)));
                residente.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_vivienda)));
                residente.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_numero)));
                residente.setC2_p202(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202)));
                residente.setC2_p202_pat(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_pat)));
                residente.setC2_p202_mat(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_mat)));
                residente.setC2_p203(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p203)));
                residente.setC2_p204(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p204)));
                residente.setC2_p205_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_a)));
                residente.setC2_p205_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_m)));
                residente.setC2_p206(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p206)));
                residente.setC2_p207(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p207)));
                residente.setC2_p207_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p207_o)));
                residente.setC2_p208(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p208)));
                residente.setC2_p209(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209)));
                residente.setC2_p209_n(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_n)));
                residente.setC2_p209_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_p)));
                residente.setC2_p209_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_pos)));
                residente.setC2_p210(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210)));
                residente.setC2_p210_n(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_n)));
                residente.setC2_p210_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_p)));
                residente.setC2_p210_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_pos)));
                residente.setC2_p211(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211)));
                residente.setC2_p211_nom(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_nom)));
                residente.setC2_p211_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_pos)));
                residente.setC2_p211_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_1)));
                residente.setC2_p211_1_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_1_o)));
                residente.setC2_p212(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p212)));
                residente.setOBS200(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_OBS200)));
                residente.setCOB200(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_COB200)));
                residente.setEncuestado_cobertura(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_encuestado_cobertura)));
                residentes.add(residente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return residentes;
    }

    public ArrayList<Residente> getAllResidentesVivienda(String idVivienda){
        ArrayList<Residente> residentes = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idVivienda)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                Residente residente = new Residente();
                residente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id)));
                residente.setId_informante(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_informante)));
                residente.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_hogar)));
                residente.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_vivienda)));
                residente.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_numero)));
                residente.setC2_p202(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202)));
                residente.setC2_p202_pat(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_pat)));
                residente.setC2_p202_mat(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_mat)));
                residente.setC2_p203(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p203)));
                residente.setC2_p204(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p204)));
                residente.setC2_p205_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_a)));
                residente.setC2_p205_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_m)));
                residente.setC2_p206(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p206)));
                residente.setC2_p207(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p207)));
                residente.setC2_p207_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p207_o)));
                residente.setC2_p208(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p208)));
                residente.setC2_p209(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209)));
                residente.setC2_p209(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209)));
                residente.setC2_p209_n(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_n)));
                residente.setC2_p209_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_p)));
                residente.setC2_p209_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_pos)));
                residente.setC2_p210(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210)));
                residente.setC2_p210_n(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_n)));
                residente.setC2_p210_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_p)));
                residente.setC2_p210_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_pos)));
                residente.setC2_p211(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211)));
                residente.setC2_p211_nom(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_nom)));
                residente.setC2_p211_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_pos)));
                residente.setC2_p211_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_1)));
                residente.setC2_p211_1_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_1_o)));
                residente.setC2_p212(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p212)));
                residente.setP200_aportante(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_p200_aportante)));
                residente.setOBS200(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_OBS200)));
                residente.setCOB200(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_COB200)));
                residente.setEncuestado_cobertura(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_encuestado_cobertura)));
                residentes.add(residente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return residentes;
    }

    public ArrayList<String> getListaSpinnerResidentesHogar(String idHogar){
        ArrayList<String> residentes = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idHogar)};
        residentes.add("-- SELECCIONE INFORMANTE --");
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                String numero = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_numero));
                String nombre = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202));
                String apepat =cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_pat));
                String apemat =cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_mat));
                String edad_s = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_a));
                String relacion = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p203));
                String residente = numero + "-" + nombre+ "-" +apepat;
                int edad=0;
                int parentesco = 0;
                if(!edad_s.equals("")) {edad = Integer.parseInt(edad_s);}
                if(!relacion.equals("")) {parentesco = Integer.parseInt(relacion);}
                if((edad>=12 && parentesco<=8) || (edad>=12 && parentesco>=11) || relacion.equals("1")){
                    residentes.add(residente);
                }

            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return residentes;
    }

    public ArrayList<String> getListaInformantes(String idHogar){
        ArrayList<String> residentes = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idHogar)};
        residentes.add("--- SELECCIONE INFORMANTE ---");
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                int edad=0;
                String numero = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_numero));
                String nombre = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202));
                String apepat =cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_pat));
                String edad_s = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_a));
                String relacion = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p203));
                String residente = numero + "-" + nombre+ "-" +apepat;
                if(!edad_s.equals("")) {edad = Integer.parseInt(edad_s);}
                if(edad>=14 || relacion.equals("1")){
                    residentes.add(residente);
                }
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return residentes;
    }

    public ArrayList<String> getListaInformantesMayor18(String idHogar){
        ArrayList<String> residentes = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idHogar)};
        residentes.add("--- SELECCIONE INFORMANTE ---");
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                int edad=0;
                String numero = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_numero));
                String nombre = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202));
                String apepat =cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_pat));
                String edad_s = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_a));
                String relacion = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p203));
                String venezolano = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p208));
                String residente = numero + "-" + nombre+ "-" +apepat+ " ("+edad_s+" Años)";
                if(!edad_s.equals("")) {edad = Integer.parseInt(edad_s);}
                if((edad>=18 || relacion.equals("1")) && venezolano.equals("1")){
                    residentes.add(residente);
                }
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return residentes;
    }

    public ArrayList<String> getListaInformantesMayor12(String idHogar){
        ArrayList<String> residentes = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idHogar)};
        residentes.add("--- SELECCIONE INFORMANTE ---");
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                int edad=0;
                String numero = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_numero));
                String nombre = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202));
                String apepat =cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_pat));
                String edad_s = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_a));
                String relacion = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p203));
                String venezolano = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p208));
                String residente = numero + "-" + nombre+ "-" +apepat+ " ("+edad_s+" Años)";
                if(!edad_s.equals("")) {edad = Integer.parseInt(edad_s);}
                if((edad>=12 || relacion.equals("1")) && venezolano.equals("1")){
                    residentes.add(residente);
                }
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return residentes;
    }


    public ArrayList<String> getListaSpinnerResidentesHogarMayor12(String idHogar){
        ArrayList<String> residentes = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idHogar)};
        residentes.add("-- SELECCIONE INFORMANTE --");
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                String numero = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_numero));
                String nombre = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202));
                String apepat =cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_pat));
                String apemat =cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_mat));
                String edad_s = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_a));
                String relacion = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p203));
                String residente = UtilsMethods.checkDigito(Integer.parseInt(numero)) + "-" + nombre+ "-" +apepat;
                int edad=0;
                int parentesco = 0;
                if(!edad_s.equals("")) {edad = Integer.parseInt(edad_s);}
                if(!relacion.equals("")) {parentesco = Integer.parseInt(relacion);}
//                if(edad>=14 || relacion.equals("1")) residentes.add(residente);
                if((edad>=12 && parentesco<=8) || (edad>=12 && parentesco>=11) || relacion.equals("1")){
                    residentes.add(residente);
                }

            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return residentes;
    }


    public ArrayList<String> getListaSpinnerResidentesHogarMayor(String idHogar){
        ArrayList<String> residentes = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idHogar)};
        residentes.add("-- SELECCIONE INFORMANTE --");
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                String numero = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_numero));
                String nombre = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202));
                String apepat =cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_pat));
                String apemat =cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_mat));
                String edad_s = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_a));
                String relacion = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p203));
                String residente = numero + "-" + nombre+ "-" +apepat;
                int edad=0;
                int parentesco = 0;
                if(!edad_s.equals("")) {edad = Integer.parseInt(edad_s);}
                if(!relacion.equals("")) {parentesco = Integer.parseInt(relacion);}
//                if(edad>=14 || relacion.equals("1")) residentes.add(residente);
                if((edad>=18 && parentesco<=8) || (edad>=18 && parentesco>=11) || relacion.equals("1")){
                    residentes.add(residente);
                }

            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return residentes;
    }


    public ArrayList<String> getListaSpinnerResidentesHogarXSexo(String idHogar,String sexo){
        ArrayList<String> residentes = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idHogar)};
        residentes.add("-- SELECCIONE INFORMANTE --");
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                String numero = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_numero));
                String nombre = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202));
                String apepat =cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_pat));
                String apemat =cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_mat));
                String genero   =cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p204));
                String edad_s = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_a));
                String relacion = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p203));
                String residente = UtilsMethods.checkDigito(Integer.parseInt(numero)) + "-" + nombre+ "-" +apepat;
                int edad=0;
                int parentesco = 0;
                if(!edad_s.equals("")) {edad = Integer.parseInt(edad_s);}
                if(!relacion.equals("")) {parentesco = Integer.parseInt(relacion);}
                if(((edad>=12 && parentesco<=8) || (edad>=12 && parentesco>=11) || relacion.equals("1")) && sexo.equals(genero)){
                    residentes.add(residente);
                }
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return residentes;
    }

    public ArrayList<String> getListaSpinnerResidentes(String idHogar){
        ArrayList<String> residentes = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idHogar)};
        residentes.add("-- SELECCIONE INFORMANTE --");
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                String numero = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_numero));
                String nombre = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202));
                String apepat =cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_pat));
                String edad = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_a));
                String etiquetaEdad = "";
                if(DAOUtils.convertNumber(edad)>0){
                    etiquetaEdad = "("+edad+" Años)";
                }else{
                    etiquetaEdad = "(0 Años)";
                }
                String residente = UtilsMethods.checkDigito(Integer.parseInt(numero)) + " - " + nombre+ " " +apepat+ " "+etiquetaEdad;
                residentes.add(residente);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return residentes;
    }


    public ArrayList<String> getListaSpinnerResidentesMayores(String idHogar,String nroResidente){
        ArrayList<String> residentes = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(idHogar)};
        residentes.add("-- SELECCIONE INFORMANTE --");
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                String numero = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_numero));
                String nombre = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202));
                String apepat =cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_pat));
                String apemat =cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_mat));
                String edad_s = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_a));
                String relacion = cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p203));
                String residente = UtilsMethods.checkDigito(Integer.parseInt(numero)) + "-" + nombre+ "-" +apepat;
                int edad=0;
                int parentesco = 0;
                if(!edad_s.equals("")) {edad = Integer.parseInt(edad_s);}
                if(!relacion.equals("")) {parentesco = Integer.parseInt(relacion);}
                if(((edad>=12 && parentesco<=8) || (edad>=12 && parentesco>=11) || relacion.equals("1")) && !numero.equals(nroResidente)){
                    residentes.add(residente);
                }

            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return residentes;
    }


    public Residente getNombreResidente(String idHogar,String numero){
        Residente residente = null;
        String[] whereArgs = new String[]{String.valueOf(idHogar),String.valueOf(numero)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,
                    null,SQLConstantes.WHERE_CLAUSE_ID_NUMERO,whereArgs,null,null,null);
            if (cursor.getCount() == 1){
                cursor.moveToFirst();
                residente = new Residente();
                residente.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_id)));
                residente.setId_informante(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_informante)));
                residente.setId_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_hogar)));
                residente.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_id_vivienda)));
                residente.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_numero)));
                residente.setC2_p202(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202)));
                residente.setC2_p202_pat(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_pat)));
                residente.setC2_p202_mat(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p202_mat)));
                residente.setC2_p203(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p203)));
                residente.setC2_p204(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p204)));
                residente.setC2_p205_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_a)));
                residente.setC2_p205_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p205_m)));
                residente.setC2_p206(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p206)));
                residente.setC2_p207(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p207)));
                residente.setC2_p207_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p207_o)));
                residente.setC2_p208(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p208)));
                residente.setC2_p209(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209)));
                residente.setC2_p209_n(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_n)));
                residente.setC2_p209_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_p)));
                residente.setC2_p209_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p209_pos)));
                residente.setC2_p210(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210)));
                residente.setC2_p210_n(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_n)));
                residente.setC2_p210_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_p)));
                residente.setC2_p210_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p210_pos)));
                residente.setC2_p211(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211)));
                residente.setC2_p211_nom(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_nom)));
                residente.setC2_p211_pos(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_pos)));
                residente.setC2_p211_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_1)));
                residente.setC2_p211_1_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p211_1_o)));
                residente.setC2_p212(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_p212)));
                residente.setP200_aportante(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_p200_aportante)));
                residente.setOBS200(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_c2_OBS200)));
                residente.setCOB200(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_COB200)));
                residente.setEncuestado_cobertura(cursor.getString(cursor.getColumnIndex(SQLConstantes.residentes_encuestado_cobertura)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return residente;
    }


    public Usuario getUsuario(String user){
        Usuario usuario = null;
        String[] whereArgs = new String[]{user};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablausuario,
                    null,SQLConstantes.WHERE_CLAUSE_USUARIO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                usuario = new Usuario();
                usuario.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_id)));
                usuario.setUsuario(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_usuario)));
                usuario.setClave(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_clave)));
                usuario.setDni(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_dni)));
                usuario.setNombre(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_nombre)));
                usuario.setCargo_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_cargo_id)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return usuario;
    }

    public Usuario getUsuario2(String user){
        Usuario usuario = null;
        String[] whereArgs = new String[]{user};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablausuario,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                usuario = new Usuario();
                usuario.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_id)));
                usuario.setUsuario(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_usuario)));
                usuario.setClave(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_clave)));
                usuario.setDni(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_dni)));
                usuario.setNombre(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_nombre)));
                usuario.setCargo_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.usuario_cargo_id)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return usuario;
    }

    public String getCodEstado(String numero){
        String codigo = null;
        String[] whereArgs = new String[]{numero};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaestados,
                    null,SQLConstantes.WHERE_CLAUSE_NUMERO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                codigo = cursor.getString(cursor.getColumnIndex(SQLConstantes.estado_id));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return codigo;
    }


    public String getCodMunicipio(String numero,String codEstado){
        String codigo = null;
        String[] whereArgs = new String[]{numero,codEstado};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamunicipios,
                    null,SQLConstantes.WHERE_CLAUSE_NUMERO + " AND " + SQLConstantes.WHERE_CLAUSE_ESTADO_COD,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                codigo = cursor.getString(cursor.getColumnIndex(SQLConstantes.municipios_cod_municipio));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return codigo;
    }

    public ArrayList<String> getMunicipios(String idEstado){
        ArrayList<String> municipios = new ArrayList<>();
        municipios.add("--SELECCIONE MUNICIPIO--");
        String[] whereArgs = new String[]{String.valueOf(idEstado)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamunicipios,
                    null,SQLConstantes.WHERE_CLAUSE_ESTADO_COD,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                String municipio = cursor.getString(cursor.getColumnIndex(SQLConstantes.municipios_nom_municipio));
                municipios.add(municipio);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return municipios;
    }


    public ArrayList<String> getUbigeos(){
        ArrayList<String> ubigeos = new ArrayList<>();
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaubigeo, null,null,null,null,null,null);
            while (cursor.moveToNext()){
                String ubigeo = cursor.getString(cursor.getColumnIndex(SQLConstantes.ubigeo_descripcion));
                ubigeos.add(ubigeo);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return ubigeos;
    }

    public Ubigeo getUbigeo(String descripcion){
        Ubigeo ubigeo = null;
        String[] whereArgs = new String[]{descripcion};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaubigeo,
                    null,SQLConstantes.WHERE_CLAUSE_DESCRIPCION,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                ubigeo = new Ubigeo();
                ubigeo.setCod_departamento(cursor.getString(cursor.getColumnIndex(SQLConstantes.ubigeo_cod_departamento)));
                ubigeo.setCod_provincia(cursor.getString(cursor.getColumnIndex(SQLConstantes.ubigeo_cod_provincia)));
                ubigeo.setCod_distrito(cursor.getString(cursor.getColumnIndex(SQLConstantes.ubigeo_cod_distrito)));
                ubigeo.setNom_departamento(cursor.getString(cursor.getColumnIndex(SQLConstantes.ubigeo_nom_departamento)));
                ubigeo.setNom_provincia(cursor.getString(cursor.getColumnIndex(SQLConstantes.ubigeo_nom_provincia)));
                ubigeo.setNom_distrito(cursor.getString(cursor.getColumnIndex(SQLConstantes.ubigeo_nom_distrito)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return ubigeo;
    }

    public Ubigeo getUbigeoxId(String id){
        Ubigeo ubigeo = null;
        String[] whereArgs = new String[]{id};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaubigeo,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                ubigeo = new Ubigeo();
                ubigeo.setCod_departamento(cursor.getString(cursor.getColumnIndex(SQLConstantes.ubigeo_cod_departamento)));
                ubigeo.setCod_provincia(cursor.getString(cursor.getColumnIndex(SQLConstantes.ubigeo_cod_provincia)));
                ubigeo.setCod_distrito(cursor.getString(cursor.getColumnIndex(SQLConstantes.ubigeo_cod_distrito)));
                ubigeo.setNom_departamento(cursor.getString(cursor.getColumnIndex(SQLConstantes.ubigeo_nom_departamento)));
                ubigeo.setNom_provincia(cursor.getString(cursor.getColumnIndex(SQLConstantes.ubigeo_nom_provincia)));
                ubigeo.setNom_distrito(cursor.getString(cursor.getColumnIndex(SQLConstantes.ubigeo_nom_distrito)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return ubigeo;
    }

    public void insertarElemento(String nombreTabla, ContentValues contentValues){
        sqLiteDatabase.insert(nombreTabla,null,contentValues);
    }

    public boolean existeElemento(String nombreTabla, String idEncuestado){
        boolean existe = false;
        String[] whereArgs = new String[]{idEncuestado};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(nombreTabla, null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                existe = true;
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return existe;
    }

    public boolean existeElementos(String nombreTabla, String idHogar){
        boolean existe = false;
        String[] whereArgs = new String[]{idHogar};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(nombreTabla, null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            if(cursor.getCount() >= 1){
                Log.e("valorex", ": "+ cursor.getCount());
                existe = true;
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return existe;
    }

    public void actualizarElemento(String nombreTabla, ContentValues contentValues, String idEncuestado){
        String[] whereArgs = new String[]{idEncuestado};
        sqLiteDatabase.update(nombreTabla,contentValues,SQLConstantes.WHERE_CLAUSE_ID,whereArgs);
    }

    public void actualizarValor(String nombreTabla, String variable, String valor, String idEncuestado){
        String[] whereArgs = new String[]{idEncuestado};
        ContentValues contentValues = new ContentValues();
        contentValues.put(variable,valor);
        sqLiteDatabase.update(nombreTabla,contentValues,SQLConstantes.WHERE_CLAUSE_ID,whereArgs);
    }

    public String[] getValores(String nombreTabla, String[] variables, String id){
        String[] valores = new String[variables.length];
        String[] whereArgs = new String[]{id};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(nombreTabla, variables,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                for (int i = 0; i < variables.length; i++) {
                    valores[i] = cursor.getString(cursor.getColumnIndex(variables[i]));
                }
            }
        }finally {
            if(cursor != null)cursor.close();
        }
        return valores;
    }

    public String getValor(String nombreTabla, String variable, String id){
        String valor = "";
        String[] whereArgs = new String[]{id};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(nombreTabla, new String[]{variable},SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                valor = cursor.getString(cursor.getColumnIndex(variable));
            }
        }finally {
            if(cursor != null)cursor.close();
        }
        if(valor == null) valor = "";
        return valor;
    }

    public boolean ocultarLayoutPregunta(String varLayoutPregunta,String idencuestado){
        boolean ocultar = false;
        String valor = getValor(SQLConstantes.tablalayouts,varLayoutPregunta,idencuestado);
        if (valor.equals("0"))
            ocultar = true;
        return ocultar;
    }

    public void eliminarDato(String tabla, String id){
        String[] whereArgs = new String[]{id};
        sqLiteDatabase.delete(tabla,SQLConstantes.WHERE_CLAUSE_ID,whereArgs);
    }

    public void eliminarDatos(String tabla, String idColumna, String valorColumna){
        String[] whereArgs = new String[]{valorColumna};
        sqLiteDatabase.delete(tabla,idColumna+"=?",whereArgs);
    }

    /**
     * retornar pojo caratula
     * */

    public Caratula getCaratula(String idVivienda){
        Caratula caratula = null;
        String[] whereArgs = new String[]{idVivienda};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablacaratula,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                caratula = new Caratula();
                caratula.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_id)));
                caratula.setAnio(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_anio)));
                caratula.setMes(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_mes)));
                caratula.setPeriodo(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_periodo)));
                caratula.setConglomerado(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_conglomerado)));
                caratula.setCcdd(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_ccdd)));
                caratula.setNom_dep(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nom_dep)));
                caratula.setCcpp(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_ccpp)));
                caratula.setNom_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nom_prov)));
                caratula.setCcdi(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_ccdi)));
                caratula.setNom_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nom_dist)));
                caratula.setCodccpp(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_codccpp)));
                caratula.setNom_ccpp(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nom_ccpp)));
                caratula.setZona(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_zona)));
                caratula.setManzana_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_manzana_id)));
                caratula.setVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_vivienda)));
                caratula.setLatitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_latitud)));
                caratula.setLongitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_longitud)));
                caratula.setTipvia(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_tipvia)));
                caratula.setTipvia_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_tipvia_o)));
                caratula.setNomvia(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nomvia)));
                caratula.setNropta(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nropta)));
                caratula.setBlock(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_block)));
                caratula.setInterior(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_interior)));
                caratula.setPiso(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_piso)));
                caratula.setMza(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_mza)));
                caratula.setLote(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_lote)));
                caratula.setKm(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_km)));
                caratula.setTelefono(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_telefono)));
                caratula.setT_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_t_hogar)));
                caratula.setUsuario(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_usuario)));
                caratula.setObservaciones(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_observaciones)));
                caratula.setCobertura(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_cobertura)));
                caratula.setNro_selec_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nro_selec_vivienda)));
                caratula.setTipo_selec_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_tipo_selec_vivienda)));
                caratula.setVivienda_reemplazo(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_vivienda_reemplazo)));
                caratula.setNro_vivienda_reemplazo(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nro_vivienda_reemplazo)));
                caratula.setP21(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_p21)));
                caratula.setP21_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_p21_o)));
                caratula.setResultado_final(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_resultado_final)));
                caratula.setResultado_final_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_resultado_final_o)));
                caratula.setFecha_inicio_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_inicio_aa)));
                caratula.setFecha_inicio_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_inicio_mm)));
                caratula.setFecha_inicio_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_inicio_dd)));
                caratula.setFecha_final_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_final_aa)));
                caratula.setFecha_final_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_final_mm)));
                caratula.setFecha_final_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_final_dd)));
                caratula.setNroSegmento(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nroSegmento)));
                caratula.setNroPuerta2(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nroPta2)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return caratula;
    }

    public ArrayList<Caratula> getAllCaratulasUsuario(String idUsuario){
        ArrayList<Caratula> caratulas = new ArrayList<>();
        String[] whereArgs = new String[]{idUsuario};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablacaratula,
                    null,SQLConstantes.WHERE_CLAUSE_USUARIO,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                Caratula caratula = new Caratula();
                caratula.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_id)));
                caratula.setAnio(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_anio)));
                caratula.setMes(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_mes)));
                caratula.setPeriodo(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_periodo)));
                caratula.setConglomerado(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_conglomerado)));
                caratula.setUsuario(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_usuario)));
                caratula.setNom_dep(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nom_dep)));
                caratula.setNom_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nom_prov)));
                caratula.setNom_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nom_dist)));
                caratula.setNom_ccpp(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nom_ccpp)));
                caratula.setZona(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_zona)));
                caratula.setManzana_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_manzana_id)));
                caratula.setVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_vivienda)));
                caratula.setLatitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_latitud)));
                caratula.setLongitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_longitud)));
                caratula.setTipvia(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_tipvia)));
                caratula.setNomvia(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nomvia)));
                caratula.setNropta(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nropta)));
                caratula.setBlock(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_block)));
                caratula.setInterior(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_interior)));
                caratula.setPiso(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_piso)));
                caratula.setMza(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_mza)));
                caratula.setLote(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_lote)));
                caratula.setKm(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_km)));
                caratula.setTelefono(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_telefono)));
                caratula.setT_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_t_hogar)));
                caratula.setUsuario(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_usuario)));
                caratula.setObservaciones(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_observaciones)));
                caratula.setCobertura(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_cobertura)));
                caratula.setNro_selec_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nro_selec_vivienda)));
                caratula.setTipo_selec_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_tipo_selec_vivienda)));
                caratula.setVivienda_reemplazo(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_vivienda_reemplazo)));
                caratula.setNro_vivienda_reemplazo(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nro_vivienda_reemplazo)));
                caratula.setP21(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_p21)));
                caratula.setP21_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_p21_o)));
                caratula.setResultado_final(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_resultado_final)));
                caratula.setResultado_final_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_resultado_final_o)));
                caratula.setFecha_inicio_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_inicio_aa)));
                caratula.setFecha_inicio_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_inicio_mm)));
                caratula.setFecha_inicio_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_inicio_dd)));
                caratula.setFecha_final_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_final_aa)));
                caratula.setFecha_final_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_final_mm)));
                caratula.setFecha_final_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_final_dd)));
                caratulas.add(caratula);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return caratulas;
    }

    public ArrayList<Caratula> getAllCaratulas(){
        ArrayList<Caratula> caratulas = new ArrayList<>();
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablacaratula,
                    null,null,null,null,null,null);
            while (cursor.moveToNext()){
                Caratula caratula = new Caratula();
                caratula.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_id)));
                caratula.setAnio(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_anio)));
                caratula.setMes(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_mes)));
                caratula.setPeriodo(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_periodo)));
                caratula.setConglomerado(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_conglomerado)));
                caratula.setUsuario(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_usuario)));
                caratula.setCcdd(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_ccdd)));
                caratula.setNom_dep(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nom_dep)));
                caratula.setCcpp(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_ccpp)));
                caratula.setNom_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nom_prov)));
                caratula.setCcdi(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_ccdi)));
                caratula.setNom_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nom_dist)));
                caratula.setCodccpp(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_codccpp)));
                caratula.setNom_ccpp(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nom_ccpp)));
                caratula.setZona(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_zona)));
                caratula.setManzana_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_manzana_id)));
                caratula.setVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_vivienda)));
                caratula.setLatitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_latitud)));
                caratula.setLongitud(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_longitud)));
                caratula.setTipvia(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_tipvia)));
                caratula.setNomvia(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nomvia)));
                caratula.setNropta(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nropta)));
                caratula.setBlock(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_block)));
                caratula.setInterior(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_interior)));
                caratula.setPiso(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_piso)));
                caratula.setMza(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_mza)));
                caratula.setLote(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_lote)));
                caratula.setKm(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_km)));
                caratula.setTelefono(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_telefono)));
                caratula.setT_hogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_t_hogar)));
                caratula.setUsuario(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_usuario)));
                caratula.setObservaciones(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_observaciones)));
                caratula.setCobertura(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_cobertura)));
                caratula.setNro_selec_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nro_selec_vivienda)));
                caratula.setTipo_selec_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_tipo_selec_vivienda)));
                caratula.setVivienda_reemplazo(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_vivienda_reemplazo)));
                caratula.setNro_vivienda_reemplazo(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nro_vivienda_reemplazo)));
                caratula.setP21(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_p21)));
                caratula.setP21_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_p21_o)));
                caratula.setResultado_final(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_resultado_final)));
                caratula.setResultado_final_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_resultado_final_o)));
                caratula.setFecha_inicio_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_inicio_aa)));
                caratula.setFecha_inicio_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_inicio_mm)));
                caratula.setFecha_inicio_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_inicio_dd)));
                caratula.setFecha_final_aa(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_final_aa)));
                caratula.setFecha_final_mm(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_final_mm)));
                caratula.setFecha_final_dd(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_fecha_final_dd)));
                caratula.setNroSegmento(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nroSegmento)));
                caratula.setNroPuerta2(cursor.getString(cursor.getColumnIndex(SQLConstantes.caratula_nroPta2)));
                caratulas.add(caratula);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return caratulas;
    }

    public ArrayList<String> getListaSpinnerViviendas(String nroSegmento){
        ArrayList<String> viviendas = new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(nroSegmento),"2"};
        viviendas.add("Seleccione Vivienda");
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamarco,
                    null,SQLConstantes.WHERE_CLAUSE_SEGMENTO_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                String vivienda = cursor.getString(cursor.getColumnIndex(SQLConstantes.marco_nro_selec_vivienda));
                viviendas.add(vivienda);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return viviendas;
    }


    /**
     * retornar pojo modulo1
     * */

    public Modulo1V getModulo1V(String idVivienda){
        Modulo1V modulo1V = null;
        String[] whereArgs = new String[]{idVivienda};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo1v,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                modulo1V = new Modulo1V();
                modulo1V.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_v_id)));
                modulo1V.setC1_p101(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_v_c1_p101)));
                modulo1V.setC1_p101_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_v_c1_p101_o)));
                modulo1V.setC1_p102(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_v_c1_p102)));
                modulo1V.setC1_p102_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_v_c1_p102_o)));
                modulo1V.setC1_p103(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_v_c1_p103)));
                modulo1V.setC1_p103_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_v_c1_p103_o)));
                modulo1V.setC1_p104(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_v_c1_p104)));
                modulo1V.setC1_p104_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_v_c1_p104_o)));
                modulo1V.setC1_p105(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_v_c1_p105)));
                modulo1V.setC1_p106(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_v_c1_p106)));
                //modulo1V.setC1_p107(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_v_c1_p107)));
                modulo1V.setCOB100A(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_v_COB100A)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo1V;
    }

    public Modulo1H getModulo1H(String idEncuestado){
        Modulo1H modulo1H = null;
        String[] whereArgs = new String[]{idEncuestado};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo1h,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                modulo1H = new Modulo1H();
                modulo1H.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_id)));
                modulo1H.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_idVivienda)));
                modulo1H.setC1_p107(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p107)));
                modulo1H.setC1_p107_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p107_o)));
                modulo1H.setC1_p108_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_1)));
                modulo1H.setC1_p108_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_2)));
                modulo1H.setC1_p108_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_3)));
                modulo1H.setC1_p108_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_4)));
                modulo1H.setC1_p108_1_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_1_o)));
                modulo1H.setC1_p108_2_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_2_o)));
                modulo1H.setC1_p108_3_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_3_o)));
                modulo1H.setC1_p109(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p109)));
                modulo1H.setC1_p109_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p109_o)));
                modulo1H.setC1_p110_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_1)));
                modulo1H.setC1_p110_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_2)));
                modulo1H.setC1_p110_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_3)));
                modulo1H.setC1_p110_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_4)));
                modulo1H.setC1_p110_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_5)));
                modulo1H.setC1_p110_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_6)));
                modulo1H.setC1_p110_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_7)));
                modulo1H.setC1_p110_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_8)));
                modulo1H.setC1_p110_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_9)));
                modulo1H.setC1_p110_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_10)));
                modulo1H.setC1_p110_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_11)));
                modulo1H.setC1_p110_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_12)));
                modulo1H.setC1_p110_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_13)));
                modulo1H.setC1_p110_11_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_11_o)));
                modulo1H.setC1_p110_12_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_12_o)));
                modulo1H.setC1_p110_13_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_13_o)));
                modulo1H.setC1_p111(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p111)));
                modulo1H.setC1_p111a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p111a)));
                modulo1H.setC1_p112_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_1)));
                modulo1H.setC1_p112_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_2)));
                modulo1H.setC1_p112_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_3)));
                modulo1H.setC1_p112_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_4)));
                modulo1H.setC1_p112_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_5)));
                modulo1H.setC1_p112_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_6)));
                modulo1H.setC1_p112_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_7)));
                modulo1H.setC1_p112_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_8)));
                modulo1H.setCOB100B(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_COB100B)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo1H;
    }

    public Modulo1H getModulo1HNumero(String idVivienda,String idHogar){
        Modulo1H modulo1H = null;
        String[] whereArgs = new String[]{String.valueOf(idHogar),String.valueOf(idVivienda)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo1h,
                    null,SQLConstantes.WHERE_CLAUSE_ID_VIVIENDA,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                modulo1H = new Modulo1H();
                modulo1H.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_id)));
                modulo1H.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_idVivienda)));
                modulo1H.setC1_p107(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p107)));
                modulo1H.setC1_p107_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p107_o)));
                modulo1H.setC1_p108_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_1)));
                modulo1H.setC1_p108_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_2)));
                modulo1H.setC1_p108_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_3)));
                modulo1H.setC1_p108_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_4)));
                modulo1H.setC1_p108_1_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_1_o)));
                modulo1H.setC1_p108_2_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_2_o)));
                modulo1H.setC1_p108_3_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_3_o)));
                modulo1H.setC1_p109(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p109)));
                modulo1H.setC1_p109_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p109_o)));
                modulo1H.setC1_p110_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_1)));
                modulo1H.setC1_p110_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_2)));
                modulo1H.setC1_p110_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_3)));
                modulo1H.setC1_p110_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_4)));
                modulo1H.setC1_p110_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_5)));
                modulo1H.setC1_p110_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_6)));
                modulo1H.setC1_p110_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_7)));
                modulo1H.setC1_p110_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_8)));
                modulo1H.setC1_p110_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_9)));
                modulo1H.setC1_p110_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_10)));
                modulo1H.setC1_p110_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_11)));
                modulo1H.setC1_p110_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_12)));
                modulo1H.setC1_p110_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_13)));
                modulo1H.setC1_p110_11_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_11_o)));
                modulo1H.setC1_p110_12_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_12_o)));
                modulo1H.setC1_p110_13_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_13_o)));
                modulo1H.setC1_p111(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p111)));
                modulo1H.setC1_p111a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p111a)));
                modulo1H.setC1_p112_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_1)));
                modulo1H.setC1_p112_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_2)));
                modulo1H.setC1_p112_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_3)));
                modulo1H.setC1_p112_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_4)));
                modulo1H.setC1_p112_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_5)));
                modulo1H.setC1_p112_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_6)));
                modulo1H.setC1_p112_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_7)));
                modulo1H.setC1_p112_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_8)));
                modulo1H.setCOB100B(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_COB100B)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo1H;
    }

    public ArrayList<Modulo1H> getAllModulo1H(String idVivienda){
        ArrayList<Modulo1H> modulo1Hs = new ArrayList<>();
        String[] whereArgs = new String[]{idVivienda};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo1h,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                Modulo1H modulo1H = new Modulo1H();
                modulo1H.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_id)));
                modulo1H.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_idVivienda)));
                modulo1H.setC1_p107(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p107)));
                modulo1H.setC1_p107_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p107_o)));
                modulo1H.setC1_p108_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_1)));
                modulo1H.setC1_p108_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_2)));
                modulo1H.setC1_p108_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_3)));
                modulo1H.setC1_p108_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p108_4)));
                modulo1H.setC1_p109(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p109)));
                modulo1H.setC1_p109_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p109_o)));
                modulo1H.setC1_p110_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_1)));
                modulo1H.setC1_p110_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_2)));
                modulo1H.setC1_p110_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_3)));
                modulo1H.setC1_p110_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_4)));
                modulo1H.setC1_p110_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_5)));
                modulo1H.setC1_p110_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_6)));
                modulo1H.setC1_p110_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_7)));
                modulo1H.setC1_p110_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_8)));
                modulo1H.setC1_p110_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_9)));
                modulo1H.setC1_p110_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_10)));
                modulo1H.setC1_p110_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_11)));
                modulo1H.setC1_p110_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_12)));
                modulo1H.setC1_p110_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_13)));
                modulo1H.setC1_p110_11_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_11_o)));
                modulo1H.setC1_p110_12_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_12_o)));
                modulo1H.setC1_p110_13_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p110_13_o)));
                modulo1H.setC1_p111(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p111)));
                modulo1H.setC1_p111a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p111a)));
                modulo1H.setC1_p112_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_1)));
                modulo1H.setC1_p112_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_2)));
                modulo1H.setC1_p112_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_3)));
                modulo1H.setC1_p112_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_4)));
                modulo1H.setC1_p112_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_5)));
                modulo1H.setC1_p112_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_6)));
                modulo1H.setC1_p112_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_7)));
                modulo1H.setC1_p112_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_c1_p112_8)));
                modulo1H.setCOB100B(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo1_h_COB100B)));
                modulo1Hs.add(modulo1H);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo1Hs;
    }

    public Modulo3 getModulo3(String idEncuestado){
        Modulo3 modulo3 = null;
        String[] whereArgs = new String[]{idEncuestado};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo3,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                modulo3 = new Modulo3();
                modulo3.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_id)));
                modulo3.setIdInformante(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_id_informante)));
                modulo3.setIdHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_id_hogar)));
                modulo3.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_id_vivienda)));
                modulo3.setC3_p301_d(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p301_d)));
                modulo3.setC3_p301_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p301_m)));
                modulo3.setC3_p301_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p301_a )));
                modulo3.setC3_p302(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p302 )));
                modulo3.setC3_p302_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p302_o )));
                modulo3.setC3_p303_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p303_m)));
                modulo3.setC3_p303_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p303_a)));
//                modulo3.setC3_p303_no_nacio(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p303_no_nacio)));
                modulo3.setC3_p304(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p304 )));
                modulo3.setC3_p304_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p304_o)));
                modulo3.setC3_p305(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p305 )));
                //modulo3.setC3_p305_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p305_o))); 2021
                modulo3.setC3_p306(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306 )));
                modulo3.setC3_p306_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_1 )));//hector
                modulo3.setC3_p306_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_2 )));//hector
                modulo3.setC3_p306_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_3 )));//hector
                modulo3.setC3_p306_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_4 )));//hector
                modulo3.setC3_p306_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_5 )));//hector
                modulo3.setC3_p306_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_6 )));//hector
                modulo3.setC3_p306_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_7 )));//hector
                modulo3.setC3_p306_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_o )));
                modulo3.setC3_p307(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307)));
                modulo3.setC3_p307_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_1)));//hector
                modulo3.setC3_p307_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_2)));//hector
                modulo3.setC3_p307_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_3)));//hector
                modulo3.setC3_p307_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_4)));//hector
                modulo3.setC3_p307_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_5)));//hector
                modulo3.setC3_p307_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_6)));//hector
                modulo3.setC3_p307_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_7)));//hector
                modulo3.setC3_p307_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_8)));//hector
                modulo3.setC3_p307_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_9)));//hector
                modulo3.setC3_p307_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_10)));//hector
                modulo3.setC3_p307_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_11)));//hector
                modulo3.setC3_p307_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_12)));//hector
                modulo3.setC3_p307_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_13)));
                modulo3.setC3_p307_o6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_o6 )));
                modulo3.setC3_p307_o12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_o12 )));

                modulo3.setC3_p307_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_a )));
                modulo3.setC3_p307a_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307a_o )));

                //modulo3.setC3_p307_d(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_d )));
                //modulo3.setC3_p307_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_m )));
                // modulo3.setC3_p307_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_a )));
                modulo3.setC3_p308(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308 )));
                // modulo3.setC3_p308_e(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308_e )));
                //  modulo3.setC3_p308_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308_m )));
                //  modulo3.setC3_p308_e_seleccion(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308_e_seleccion )));
                //  modulo3.setC3_p308_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308_m )));
                //  modulo3.setC3_p308_m_seleccion(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308_m_seleccion )));
                modulo3.setC3_p309(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309 )));
                modulo3.setC3_p309_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_o )));
                modulo3.setC3_p309_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_1 )));
                modulo3.setC3_p309_1_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_1_o )));
                modulo3.setC3_p310_e(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_e )));
                modulo3.setC3_p310_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_m )));
                modulo3.setC3_p310_e_seleccion(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_e_seleccion )));
                modulo3.setC3_p310_m_seleccion(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_m_seleccion )));
                modulo3.setC3_p310_e_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_e_o)));
                modulo3.setC3_p310_m_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_m_o)));
//                modulo3.setC3_p310_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_1)));
//                modulo3.setC3_p310_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_2)));
//                modulo3.setC3_p310_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_3 )));
//                modulo3.setC3_p310_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_4 )));
                modulo3.setC3_p311(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p311)));
                modulo3.setC3_p312(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p312)));
                modulo3.setC3_p312_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p312_o)));
//                modulo3.setC3_p312_dep(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p312_dep)));
//                modulo3.setC3_p312_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p312_prov)));
//                modulo3.setC3_p312_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p312_dist)));
                modulo3.setC3_p313(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p313)));
                modulo3.setC3_p314(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p314 )));
                modulo3.setC3_p314_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p314_o)));
                modulo3.setC3_p315_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_1 )));
                modulo3.setC3_p315_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_2)));
                modulo3.setC3_p315_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_3 )));
                modulo3.setC3_p315_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_4 )));
                modulo3.setC3_p315_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_5 )));
                modulo3.setC3_p315_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_6)));
                modulo3.setC3_p315_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_7)));
                modulo3.setC3_p315_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_8)));
                modulo3.setC3_p315_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_9)));
                modulo3.setC3_p315_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_10)));
                modulo3.setC3_p315_10_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_10_o)));
                modulo3.setC3_p316(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p316)));
                modulo3.setC3_p316_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p316_o )));
                modulo3.setC3_p317(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p317 )));
                modulo3.setC3_p318(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318)));
                modulo3.setCOB300(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_COB300)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo3;
    }

    public Modulo3 getModulo3Numero(String idVivienda,String idHogar,String numero){
        Modulo3 modulo3 = null;
        String[] whereArgs = new String[]{String.valueOf(idVivienda),String.valueOf(idHogar),String.valueOf(numero)};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo3,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                modulo3 = new Modulo3();
                modulo3.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_id)));
                modulo3.setIdInformante(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_id_informante)));
                modulo3.setIdHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_id_hogar)));
                modulo3.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_id_vivienda)));
                modulo3.setC3_p301_d(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p301_d)));
                modulo3.setC3_p301_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p301_m)));
                modulo3.setC3_p301_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p301_a )));
                modulo3.setC3_p302(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p302 )));
                modulo3.setC3_p302_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p302_o )));
                modulo3.setC3_p303_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p303_m)));
                modulo3.setC3_p303_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p303_a)));
//                modulo3.setC3_p303_no_nacio(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p303_no_nacio)));
                modulo3.setC3_p304(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p304 )));
                modulo3.setC3_p304_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p304_o)));
                modulo3.setC3_p305(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p305 )));
                //modulo3.setC3_p305_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p305_o))); 2021
                modulo3.setC3_p306(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306 )));
                modulo3.setC3_p306_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_1 )));//hector
                modulo3.setC3_p306_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_2 )));//hector
                modulo3.setC3_p306_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_3 )));//hector
                modulo3.setC3_p306_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_4 )));//hector
                modulo3.setC3_p306_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_5 )));//hector
                modulo3.setC3_p306_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_6 )));//hector
                modulo3.setC3_p306_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_7 )));//hector
                modulo3.setC3_p306_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_o )));
                modulo3.setC3_p307(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307)));
                modulo3.setC3_p307_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_1)));//hector
                modulo3.setC3_p307_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_2)));//hector
                modulo3.setC3_p307_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_3)));//hector
                modulo3.setC3_p307_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_4)));//hector
                modulo3.setC3_p307_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_5)));//hector
                modulo3.setC3_p307_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_6)));//hector
                modulo3.setC3_p307_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_7)));//hector
                modulo3.setC3_p307_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_8)));//hector
                modulo3.setC3_p307_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_9)));//hector
                modulo3.setC3_p307_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_10)));//hector
                modulo3.setC3_p307_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_11)));//hector
                modulo3.setC3_p307_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_12)));//hector
                modulo3.setC3_p307_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_13)));
                modulo3.setC3_p307_o6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_o6 )));
                modulo3.setC3_p307_o12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_o12 )));

                modulo3.setC3_p307_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_a )));
                modulo3.setC3_p307a_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307a_o )));

                //modulo3.setC3_p307_d(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_d )));
                //modulo3.setC3_p307_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_m )));
                // modulo3.setC3_p307_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_a )));
                modulo3.setC3_p308(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308 )));
                // modulo3.setC3_p308_e(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308_e )));
                //  modulo3.setC3_p308_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308_m )));
                //  modulo3.setC3_p308_e_seleccion(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308_e_seleccion )));
                //  modulo3.setC3_p308_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308_m )));
                //  modulo3.setC3_p308_m_seleccion(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308_m_seleccion )));
                modulo3.setC3_p309(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309 )));
                modulo3.setC3_p309_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_o )));
                modulo3.setC3_p309_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_1 )));
                modulo3.setC3_p309_1_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_1_o )));
                modulo3.setC3_p310_e(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_e )));
                modulo3.setC3_p310_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_m )));
                modulo3.setC3_p310_e_seleccion(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_e_seleccion )));
                modulo3.setC3_p310_m_seleccion(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_m_seleccion )));
                modulo3.setC3_p310_e_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_e_o)));
                modulo3.setC3_p310_m_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_m_o)));
//                modulo3.setC3_p310_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_1)));
//                modulo3.setC3_p310_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_2)));
//                modulo3.setC3_p310_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_3 )));
//                modulo3.setC3_p310_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_4 )));
                modulo3.setC3_p311(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p311)));
                modulo3.setC3_p312(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p312)));
                modulo3.setC3_p312_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p312_o)));
//                modulo3.setC3_p312_dep(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p312_dep)));
//                modulo3.setC3_p312_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p312_prov)));
//                modulo3.setC3_p312_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p312_dist)));
                modulo3.setC3_p313(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p313)));
                modulo3.setC3_p314(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p314 )));
                modulo3.setC3_p314_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p314_o)));
                modulo3.setC3_p315_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_1 )));
                modulo3.setC3_p315_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_2)));
                modulo3.setC3_p315_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_3 )));
                modulo3.setC3_p315_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_4 )));
                modulo3.setC3_p315_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_5 )));
                modulo3.setC3_p315_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_6)));
                modulo3.setC3_p315_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_7)));
                modulo3.setC3_p315_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_8)));
                modulo3.setC3_p315_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_9)));
                modulo3.setC3_p315_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_10)));
                modulo3.setC3_p315_10_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_10_o)));
                modulo3.setC3_p316(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p316)));
                modulo3.setC3_p316_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p316_o )));
                modulo3.setC3_p317(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p317 )));
                modulo3.setC3_p318(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318)));
                modulo3.setCOB300(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_COB300)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo3;
    }

    public ArrayList<Modulo3> getAllModulo3(String idVivienda){
        ArrayList<Modulo3> modulo3s = new ArrayList<>();
        String[] whereArgs = new String[]{idVivienda};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo3,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                Modulo3 modulo3 = new Modulo3();
                modulo3.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_id)));
                modulo3.setIdInformante(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_id_informante)));
                modulo3.setIdHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_id_hogar)));
                modulo3.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_id_vivienda)));
                modulo3.setC3_p301_d(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p301_d)));
                modulo3.setC3_p301_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p301_m)));
                modulo3.setC3_p301_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p301_a )));
                modulo3.setC3_p302(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p302 )));
                modulo3.setC3_p302_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p302_o )));
                modulo3.setC3_p303_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p303_m)));
                modulo3.setC3_p303_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p303_a)));
//                modulo3.setC3_p303_no_nacio(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p303_no_nacio)));
                modulo3.setC3_p304(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p304 )));
                modulo3.setC3_p304_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p304_o)));
                modulo3.setC3_p305(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p305 )));
                //modulo3.setC3_p305_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p305_o))); 2021
                modulo3.setC3_p306(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306 )));
                modulo3.setC3_p306_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_1 )));//hector
                modulo3.setC3_p306_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_2 )));//hector
                modulo3.setC3_p306_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_3 )));//hector
                modulo3.setC3_p306_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_4 )));//hector
                modulo3.setC3_p306_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_5 )));//hector
                modulo3.setC3_p306_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_6 )));//hector
                modulo3.setC3_p306_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_7 )));//hector
                modulo3.setC3_p306_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p306_o )));
                modulo3.setC3_p307(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307 )));
                modulo3.setC3_p307_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_1 )));//hector
                modulo3.setC3_p307_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_2 )));//hector
                modulo3.setC3_p307_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_3 )));//hector
                modulo3.setC3_p307_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_4 )));//hector
                modulo3.setC3_p307_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_5 )));//hector
                modulo3.setC3_p307_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_6 )));//hector
                modulo3.setC3_p307_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_7 )));//hector
                modulo3.setC3_p307_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_8 )));//hector
                modulo3.setC3_p307_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_9 )));//hector
                modulo3.setC3_p307_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_10 )));//hector
                modulo3.setC3_p307_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_11)));//hector
                modulo3.setC3_p307_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_12 )));//hector
                modulo3.setC3_p307_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_13 )));//hector
                modulo3.setC3_p307_o6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_o6 )));
                modulo3.setC3_p307_o12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_o12 )));
                modulo3.setC3_p307_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_a )));
                modulo3.setC3_p307a_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307a_o )));
                //modulo3.setC3_p307_d(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_d )));
                //modulo3.setC3_p307_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_m )));
                // modulo3.setC3_p307_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p307_a )));
                modulo3.setC3_p308(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308 )));
                // modulo3.setC3_p308_e(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308_e )));
                //  modulo3.setC3_p308_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308_m )));
                //  modulo3.setC3_p308_e_seleccion(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308_e_seleccion )));
                //   modulo3.setC3_p308_m_seleccion(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p308_m_seleccion )));
                modulo3.setC3_p309(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309 )));
                modulo3.setC3_p309_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_o )));
                modulo3.setC3_p309_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_1 )));
                modulo3.setC3_p309_1_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_1_o )));
                modulo3.setC3_p310_e(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_e )));
                modulo3.setC3_p310_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_m )));
                modulo3.setC3_p310_e_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_e_o)));
                modulo3.setC3_p310_m_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_m_o)));
//                modulo3.setC3_p310_e_seleccion(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_e_seleccion )));
//                modulo3.setC3_p310_m_seleccion(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_m_seleccion )));
//                modulo3.setC3_p310_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_1)));
//                modulo3.setC3_p310_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_2)));
//                modulo3.setC3_p310_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_3 )));
//                modulo3.setC3_p310_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p310_4 )));
                modulo3.setC3_p311(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p311)));
                modulo3.setC3_p312(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p312)));
                modulo3.setC3_p312_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p312_o)));
//                modulo3.setC3_p312_dep(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p312_dep)));
//                modulo3.setC3_p312_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p312_prov)));
//                modulo3.setC3_p312_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p312_dist)));
                modulo3.setC3_p313(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p313)));
                modulo3.setC3_p314(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p314 )));
                modulo3.setC3_p314_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p314_o)));
                modulo3.setC3_p315_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_1 )));
                modulo3.setC3_p315_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_2)));
                modulo3.setC3_p315_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_3 )));
                modulo3.setC3_p315_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_4 )));
                modulo3.setC3_p315_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_5 )));
                modulo3.setC3_p315_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_6)));
                modulo3.setC3_p315_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_7)));
                modulo3.setC3_p315_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_8)));
                modulo3.setC3_p315_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_9)));
                modulo3.setC3_p315_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_10)));
                modulo3.setC3_p315_10_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p315_10_o)));
                modulo3.setC3_p316(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p316)));
                modulo3.setC3_p316_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p316_o )));
                modulo3.setC3_p317(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p317 )));
                modulo3.setC3_p318(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318)));
                modulo3.setObs_cap3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_obs_cap3)));
                modulo3.setCOB300(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_COB300)));
                modulo3s.add(modulo3);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo3s;
    }

    public ArrayList<M3Pregunta309> getAllM3Pregunta309(String idEncuestado){
        ArrayList<M3Pregunta309> m3Pregunta309s = new ArrayList<>();
        String[] whereArgs = new String[]{idEncuestado};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablam3p309rutas,
                    null,SQLConstantes.WHERE_CLAUSE_ID_ENCUESTADO,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                M3Pregunta309 m3Pregunta309 = new M3Pregunta309();
                m3Pregunta309.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p309_id)));
                m3Pregunta309.setId_encuestado(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p309_id_encuestado)));
                m3Pregunta309.setC3_p309_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_p)));
                m3Pregunta309.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p309_numero)));
                m3Pregunta309.setC3_p309_p_nom(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_p_nom)));
                m3Pregunta309.setC3_p309_c(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_c)));
                m3Pregunta309.setC3_p309_mod(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_mod)));
                m3Pregunta309.setC3_p309_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_m)));
                m3Pregunta309.setC3_p309_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_a)));
                m3Pregunta309.setC3_p309_m_cod(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_m_cod)));
                m3Pregunta309.setC3_p309_a_cod(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_a_cod)));
                m3Pregunta309s.add(m3Pregunta309);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return m3Pregunta309s;
    }

    public ArrayList<M3Pregunta309> getAllM3Pregunta309Vivienda(String idVivienda){
        ArrayList<M3Pregunta309> m3Pregunta309s = new ArrayList<>();
        String[] whereArgs = new String[]{idVivienda};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablam3p309rutas,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                M3Pregunta309 m3Pregunta309 = new M3Pregunta309();
                m3Pregunta309.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p309_id)));
                m3Pregunta309.setId_encuestado(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p309_id_encuestado)));
                m3Pregunta309.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p309_id_vivienda)));
                m3Pregunta309.setC3_p309_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_p)));
                m3Pregunta309.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p309_numero)));
                m3Pregunta309.setC3_p309_p_nom(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_p_nom)));
                m3Pregunta309.setC3_p309_c(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_c)));
                m3Pregunta309.setC3_p309_mod(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_mod)));
                m3Pregunta309.setC3_p309_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_m)));
                m3Pregunta309.setC3_p309_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_a)));
                m3Pregunta309.setC3_p309_m_cod(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_m_cod)));
                m3Pregunta309.setC3_p309_a_cod(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_a_cod)));
                m3Pregunta309s.add(m3Pregunta309);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return m3Pregunta309s;
    }

    public M3Pregunta309 getM3Pregunta309(String id){
        M3Pregunta309 m3Pregunta309 = null;
        String[] whereArgs = new String[]{id};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablam3p309rutas,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                m3Pregunta309 = new M3Pregunta309();
                m3Pregunta309.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p309_id)));
                m3Pregunta309.setId_encuestado(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p309_id_encuestado)));
                m3Pregunta309.setC3_p309_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_p)));
                m3Pregunta309.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p309_numero)));
                m3Pregunta309.setC3_p309_p_nom(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_p_nom)));
                m3Pregunta309.setC3_p309_c(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_c)));
                m3Pregunta309.setC3_p309_mod(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_mod)));
                m3Pregunta309.setC3_p309_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_m)));
                m3Pregunta309.setC3_p309_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_a)));
                m3Pregunta309.setC3_p309_m_cod(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_m_cod)));
                m3Pregunta309.setC3_p309_a_cod(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p309_a_cod)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return m3Pregunta309;
    }

    public ArrayList<M3Pregunta318> getAllM3Pregunta318(String idEncuestado){
        ArrayList<M3Pregunta318> m3Pregunta318s = new ArrayList<>();
        String[] whereArgs = new String[]{idEncuestado};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablam3p318personas,
                    null,SQLConstantes.WHERE_CLAUSE_ID_ENCUESTADO,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                M3Pregunta318 m3Pregunta318 = new M3Pregunta318();
                m3Pregunta318.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p318_id)));
                m3Pregunta318.setIdEncuestado(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p318_idEncuestado)));
                m3Pregunta318.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p318_numero)));
                m3Pregunta318.setC3_p318_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_f)));
                m3Pregunta318.setC3_p318_s(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_s)));
                m3Pregunta318.setC3_p318_e(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_e)));
                m3Pregunta318.setC3_p318_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_p)));
                m3Pregunta318s.add(m3Pregunta318);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return m3Pregunta318s;
    }

    public ArrayList<M3Pregunta318> getAllM3Pregunta318Vivienda(String idVivienda){
        ArrayList<M3Pregunta318> m3Pregunta318s = new ArrayList<>();
        String[] whereArgs = new String[]{idVivienda};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablam3p318personas,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs,null,null,null);
            while(cursor.moveToNext()){
                M3Pregunta318 m3Pregunta318 = new M3Pregunta318();
                m3Pregunta318.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p318_id)));
                m3Pregunta318.setIdEncuestado(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p318_idEncuestado)));
                m3Pregunta318.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p318_id_vivienda)));
                m3Pregunta318.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p318_numero)));
                m3Pregunta318.setC3_p318_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_f)));
                m3Pregunta318.setC3_p318_s(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_s)));
                m3Pregunta318.setC3_p318_e(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_e)));
                m3Pregunta318.setC3_p318_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_p)));
                m3Pregunta318s.add(m3Pregunta318);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return m3Pregunta318s;
    }

    public M3Pregunta318 getM3Pregunta318(String id){
        M3Pregunta318 m3Pregunta318 = null;
        String[] whereArgs = new String[]{id};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablam3p318personas,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                m3Pregunta318 = new M3Pregunta318();
                m3Pregunta318.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p318_id)));
                m3Pregunta318.setIdEncuestado(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p318_idEncuestado)));
                m3Pregunta318.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p318_numero)));
                m3Pregunta318.setC3_p318_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_f)));
                m3Pregunta318.setC3_p318_s(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_s)));
                m3Pregunta318.setC3_p318_e(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_e)));
                m3Pregunta318.setC3_p318_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_p)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return m3Pregunta318;
    }

    public M3Pregunta318 getM3Pregunta318Numero(String id,String numero){
        M3Pregunta318 m3Pregunta318 = null;
        String[] whereArgs = new String[]{id,numero};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablam3p318personas,
                    null,SQLConstantes.WHERE_CLAUSE_ENCUESTADO_NUMERO,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                m3Pregunta318 = new M3Pregunta318();
                m3Pregunta318.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p318_id)));
                m3Pregunta318.setIdEncuestado(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p318_idEncuestado)));
                m3Pregunta318.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_p318_numero)));
                m3Pregunta318.setC3_p318_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_f)));
                m3Pregunta318.setC3_p318_s(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_s)));
                m3Pregunta318.setC3_p318_e(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_e)));
                m3Pregunta318.setC3_p318_p(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo3_c3_p318_p)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return m3Pregunta318;
    }



    public Modulo4 getModulo4(String idEncuestado){
        Modulo4 modulo4 = null;
        String[] whereArgs = new String[]{idEncuestado};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo4,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                modulo4 = new Modulo4();
                modulo4.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_id)));
                modulo4.setIdInformante(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_id_informante)));
                modulo4.setIdHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_id_hogar)));
                modulo4.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_id_vivienda)));
                modulo4.setC4_p401_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_1)));
                modulo4.setC4_p401_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_2)));
                modulo4.setC4_p401_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_3)));
                modulo4.setC4_p401_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_4)));
                modulo4.setC4_p401_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_5)));
//                modulo4.setC4_p401_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_o)));
                modulo4.setC4_p402(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p402)));
                modulo4.setC4_p403_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_1)));
                modulo4.setC4_p403_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_2)));
                modulo4.setC4_p403_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_3)));
                modulo4.setC4_p403_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_4)));
                modulo4.setC4_p403_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_5)));
                modulo4.setC4_p403_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_6)));
                modulo4.setC4_p403_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_7)));
                modulo4.setC4_p403_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_8)));
                modulo4.setC4_p403_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_9)));
                modulo4.setC4_p403_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_10)));
                modulo4.setC4_p403_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_11)));
                modulo4.setC4_p403_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_12)));
                modulo4.setC4_p403_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_13)));
                modulo4.setC4_p403_14(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_14)));
                modulo4.setC4_p403_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_o)));
                modulo4.setC4_p404(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p404)));
                modulo4.setC4_p405_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_1)));
                modulo4.setC4_p405_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_2)));
                modulo4.setC4_p405_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_3)));
                modulo4.setC4_p405_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_4)));
                modulo4.setC4_p405_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_5)));
                modulo4.setC4_p405_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_6)));
                modulo4.setC4_p405_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_7)));
                modulo4.setC4_p406_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_1)));
                modulo4.setC4_p406_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_2)));
                modulo4.setC4_p406_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_3)));
                modulo4.setC4_p406_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_4)));
                modulo4.setC4_p406_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_5)));
                modulo4.setC4_p406_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_6)));
                modulo4.setC4_p406_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_7)));
                modulo4.setC4_p406_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_8)));
                modulo4.setC4_p406_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_o)));
                modulo4.setC4_p407_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_1)));
                modulo4.setC4_p407_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_2)));
                modulo4.setC4_p407_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_3)));
                modulo4.setC4_p407_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_4)));
                modulo4.setC4_p407_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_5)));
                modulo4.setC4_p407_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_6)));
                modulo4.setC4_p407_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_7)));
                modulo4.setC4_p407_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_8)));
                modulo4.setC4_p407_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_9)));
                modulo4.setC4_p407_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_10)));
                modulo4.setC4_p407_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_11)));
                modulo4.setC4_p407_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_12)));
                modulo4.setC4_p407_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_13)));
                modulo4.setC4_p407_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_o)));
                modulo4.setC4_p408_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_1)));
                modulo4.setC4_p408_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_2)));
                modulo4.setC4_p408_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_3)));
                modulo4.setC4_p408_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_4)));
                modulo4.setC4_p408_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_5)));
                modulo4.setC4_p408_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_6)));
                modulo4.setC4_p409(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p409)));
                modulo4.setC4_p409_nom(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p409_nom)));
                modulo4.setC4_p409_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p409_1)));
                modulo4.setC4_p409_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p409_o)));
                modulo4.setC4_p409_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p409_2)));
                modulo4.setC4_p410a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p410a)));
                modulo4.setC4_p410b(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p410b)));
                modulo4.setC4_p410(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p410)));
                modulo4.setC4_p411(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411)));
//                modulo4.setC4_p411_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_1)));
//                modulo4.setC4_p411_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_2)));
//                modulo4.setC4_p411_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_3)));
//                modulo4.setC4_p411_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_4)));
//                modulo4.setC4_p411_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_5)));
//                modulo4.setC4_p411_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_6)));
//                modulo4.setC4_p411_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_7)));
//                modulo4.setC4_p411_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_8)));
//                modulo4.setC4_p411_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_9)));
//                modulo4.setC4_p411_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_10)));
//                modulo4.setC4_p411_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_11)));
//                modulo4.setC4_p411_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_12)));
//                modulo4.setC4_p411_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_13)));
//                modulo4.setC4_p411_14(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_14)));
//                modulo4.setC4_p411_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_o)));
                modulo4.setC4_p412(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p412)));
                modulo4.setC4_p413(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413)));
                modulo4.setC4_p414(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p414)));
                modulo4.setC4_p415(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p415)));
                modulo4.setC4_p416_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_1)));
                modulo4.setC4_p416_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_2)));
                modulo4.setC4_p416_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_3)));
                modulo4.setC4_p416_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_4)));
                modulo4.setC4_p416_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_5)));
                modulo4.setC4_p416_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_6)));
                modulo4.setC4_p416_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_7)));
                modulo4.setC4_p416_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_8)));
                modulo4.setC4_p416_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_o)));
                modulo4.setObs_cap4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_obs_cap4)));
                //Anthony M. 30/04/2021
                modulo4.setC4_p401_4_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_4_o)));
                modulo4.setC4_p403_14_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_14_o)));
                modulo4.setC4_p406_7_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_7_o)));
                modulo4.setC4_p407_13_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_13_o)));
                modulo4.setC4_p411(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411)));
                modulo4.setC4_p413_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413_1)));
                modulo4.setC4_p413_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413_2)));
                modulo4.setC4_p413_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413_3)));
                modulo4.setC4_p413_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413_4)));
                modulo4.setC4_p413_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413_5)));
                modulo4.setC4_p416_5_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_5_o)));
                modulo4.setC4_p417_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1)));
                modulo4.setC4_p417_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_2)));
                modulo4.setC4_p417_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_3)));
                modulo4.setC4_p417_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_4)));
                modulo4.setC4_p417_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_5)));
                modulo4.setC4_p417_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_6)));
                modulo4.setC4_p417_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_7)));
                modulo4.setC4_p417_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_8)));
                modulo4.setC4_p417_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_9)));
                modulo4.setC4_p417_7_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_7_o)));
                modulo4.setC4_p417_1_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_1)));
                modulo4.setC4_p417_1_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_2)));
                modulo4.setC4_p417_1_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_3)));
                modulo4.setC4_p417_1_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_4)));
                modulo4.setC4_p417_1_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_5)));
                modulo4.setC4_p417_1_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_6)));
                modulo4.setC4_p417_1_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_7)));
                modulo4.setC4_p417_1_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_8)));
                modulo4.setC4_p417_1_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_9)));
                modulo4.setC4_p417_1_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_10)));
                modulo4.setC4_p417_1_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_11)));
                modulo4.setC4_p417_1_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_12)));
                modulo4.setC4_p417_1_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_13)));
                modulo4.setC4_p417_1_13_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_13_o)));
                //modulo4.setC4_p417_1a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1a)));
                //modulo4.setC4_p417_1a_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1a_o)));
                modulo4.setC4_p417_4_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_4_o)));
                modulo4.setC4_p418(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p418)));
                modulo4.setC4_p418a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p418a)));

                modulo4.setCOB400(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_COB400)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo4;
    }

    public ArrayList<Modulo4> getAllModulo4(String idVivienda){
        ArrayList<Modulo4> modulo4s = new ArrayList<>();
        String[] whereArgs = new String[]{idVivienda};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo4,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                Modulo4 modulo4 = new Modulo4();
                modulo4.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_id)));
                modulo4.setIdInformante(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_id_informante)));
                modulo4.setIdHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_id_hogar)));
                modulo4.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_id_vivienda)));
                modulo4.setC4_p401_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_1)));
                modulo4.setC4_p401_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_2)));
                modulo4.setC4_p401_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_3)));
                modulo4.setC4_p401_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_4)));
                modulo4.setC4_p401_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_5)));
//                modulo4.setC4_p401_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_o)));
                modulo4.setC4_p402(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p402)));
                modulo4.setC4_p403_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_1)));
                modulo4.setC4_p403_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_2)));
                modulo4.setC4_p403_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_3)));
                modulo4.setC4_p403_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_4)));
                modulo4.setC4_p403_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_5)));
                modulo4.setC4_p403_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_6)));
                modulo4.setC4_p403_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_7)));
                modulo4.setC4_p403_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_8)));
                modulo4.setC4_p403_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_9)));
                modulo4.setC4_p403_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_10)));
                modulo4.setC4_p403_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_11)));
                modulo4.setC4_p403_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_12)));
                modulo4.setC4_p403_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_13)));
                modulo4.setC4_p403_14(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_14)));
                modulo4.setC4_p403_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_o)));
                modulo4.setC4_p404(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p404)));
                modulo4.setC4_p405_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_1)));
                modulo4.setC4_p405_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_2)));
                modulo4.setC4_p405_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_3)));
                modulo4.setC4_p405_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_4)));
                modulo4.setC4_p405_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_5)));
                modulo4.setC4_p405_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_6)));
                modulo4.setC4_p405_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_7)));
                modulo4.setC4_p406_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_1)));
                modulo4.setC4_p406_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_2)));
                modulo4.setC4_p406_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_3)));
                modulo4.setC4_p406_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_4)));
                modulo4.setC4_p406_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_5)));
                modulo4.setC4_p406_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_6)));
                modulo4.setC4_p406_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_7)));
                modulo4.setC4_p406_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_8)));
                modulo4.setC4_p406_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_o)));
                modulo4.setC4_p407_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_1)));
                modulo4.setC4_p407_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_2)));
                modulo4.setC4_p407_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_3)));
                modulo4.setC4_p407_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_4)));
                modulo4.setC4_p407_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_5)));
                modulo4.setC4_p407_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_6)));
                modulo4.setC4_p407_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_7)));
                modulo4.setC4_p407_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_8)));
                modulo4.setC4_p407_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_9)));
                modulo4.setC4_p407_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_10)));
                modulo4.setC4_p407_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_11)));
                modulo4.setC4_p407_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_12)));
                modulo4.setC4_p407_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_13)));
                modulo4.setC4_p407_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_o)));
                modulo4.setC4_p408_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_1)));
                modulo4.setC4_p408_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_2)));
                modulo4.setC4_p408_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_3)));
                modulo4.setC4_p408_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_4)));
                modulo4.setC4_p408_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_5)));
                modulo4.setC4_p408_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_6)));
                modulo4.setC4_p409(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p409)));
                modulo4.setC4_p409_nom(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p409_nom)));
                modulo4.setC4_p409_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p409_1)));
                modulo4.setC4_p409_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p409_o)));
                modulo4.setC4_p409_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p409_2)));
                modulo4.setC4_p410a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p410a)));
                modulo4.setC4_p410b(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p410b)));
                modulo4.setC4_p410(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p410)));
                modulo4.setC4_p411(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411)));
//                modulo4.setC4_p411_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_1)));
//                modulo4.setC4_p411_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_2)));
//                modulo4.setC4_p411_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_3)));
//                modulo4.setC4_p411_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_4)));
//                modulo4.setC4_p411_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_5)));
//                modulo4.setC4_p411_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_6)));
//                modulo4.setC4_p411_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_7)));
//                modulo4.setC4_p411_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_8)));
//                modulo4.setC4_p411_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_9)));
//                modulo4.setC4_p411_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_10)));
//                modulo4.setC4_p411_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_11)));
//                modulo4.setC4_p411_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_12)));
//                modulo4.setC4_p411_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_13)));
//                modulo4.setC4_p411_14(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_14)));
//                modulo4.setC4_p411_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_o)));
                modulo4.setC4_p412(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p412)));
                modulo4.setC4_p413(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413)));
                modulo4.setC4_p414(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p414)));
                modulo4.setC4_p415(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p415)));
                modulo4.setC4_p416_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_1)));
                modulo4.setC4_p416_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_2)));
                modulo4.setC4_p416_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_3)));
                modulo4.setC4_p416_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_4)));
                modulo4.setC4_p416_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_5)));
                modulo4.setC4_p416_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_6)));
                modulo4.setC4_p416_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_7)));
                modulo4.setC4_p416_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_8)));
                modulo4.setC4_p416_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_o)));
                modulo4.setObs_cap4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_obs_cap4)));
                //Anthony M. 30/04/2021
                modulo4.setC4_p401_4_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_4_o)));
                modulo4.setC4_p403_14_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_14_o)));
                modulo4.setC4_p406_7_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_7_o)));
                modulo4.setC4_p407_13_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_13_o)));
                modulo4.setC4_p411(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411)));
                modulo4.setC4_p413_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413_1)));
                modulo4.setC4_p413_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413_2)));
                modulo4.setC4_p413_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413_3)));
                modulo4.setC4_p413_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413_4)));
                modulo4.setC4_p413_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413_5)));
                modulo4.setC4_p416_5_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_5_o)));
                modulo4.setC4_p417_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1)));
                modulo4.setC4_p417_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_2)));
                modulo4.setC4_p417_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_3)));
                modulo4.setC4_p417_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_4)));
                modulo4.setC4_p417_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_5)));
                modulo4.setC4_p417_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_6)));
                modulo4.setC4_p417_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_7)));
                modulo4.setC4_p417_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_8)));
                modulo4.setC4_p417_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_9)));
                modulo4.setC4_p417_7_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_7_o)));
                modulo4.setC4_p417_1_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_1)));
                modulo4.setC4_p417_1_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_2)));
                modulo4.setC4_p417_1_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_3)));
                modulo4.setC4_p417_1_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_4)));
                modulo4.setC4_p417_1_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_5)));
                modulo4.setC4_p417_1_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_6)));
                modulo4.setC4_p417_1_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_7)));
                modulo4.setC4_p417_1_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_8)));
                modulo4.setC4_p417_1_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_9)));
                modulo4.setC4_p417_1_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_10)));
                modulo4.setC4_p417_1_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_11)));
                modulo4.setC4_p417_1_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_12)));
                modulo4.setC4_p417_1_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_13)));
                modulo4.setC4_p417_1_13_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_13_o)));
                //modulo4.setC4_p417_1a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1a)));
                //modulo4.setC4_p417_1a_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1a_o)));
                modulo4.setC4_p417_4_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_4_o)));
                modulo4.setC4_p418(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p418)));
                modulo4.setC4_p418a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p418a)));
                modulo4.setObs_cap4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_obs_cap4)));
                modulo4.setCOB400(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_COB400)));
                modulo4s.add(modulo4);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo4s;
    }

    public ArrayList<Modulo4> getAllModulo4P408(String idHogar){
        ArrayList<Modulo4> modulo4s = new ArrayList<>();
        String[] whereArgs = new String[]{idHogar};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo4,
                    null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                Modulo4 modulo4 = new Modulo4();
                modulo4.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_id)));
                modulo4.setIdInformante(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_id_informante)));
                modulo4.setIdHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_id_hogar)));
                modulo4.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_id_vivienda)));
                modulo4.setC4_p401_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_1)));
                modulo4.setC4_p401_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_2)));
                modulo4.setC4_p401_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_3)));
                modulo4.setC4_p401_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_4)));
                modulo4.setC4_p401_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_5)));
//                modulo4.setC4_p401_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_o)));
                modulo4.setC4_p402(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p402)));
                modulo4.setC4_p403_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_1)));
                modulo4.setC4_p403_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_2)));
                modulo4.setC4_p403_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_3)));
                modulo4.setC4_p403_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_4)));
                modulo4.setC4_p403_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_5)));
                modulo4.setC4_p403_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_6)));
                modulo4.setC4_p403_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_7)));
                modulo4.setC4_p403_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_8)));
                modulo4.setC4_p403_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_9)));
                modulo4.setC4_p403_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_10)));
                modulo4.setC4_p403_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_11)));
                modulo4.setC4_p403_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_12)));
                modulo4.setC4_p403_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_13)));
                modulo4.setC4_p403_14(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_14)));
                modulo4.setC4_p403_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_o)));
                modulo4.setC4_p404(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p404)));
                modulo4.setC4_p405_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_1)));
                modulo4.setC4_p405_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_2)));
                modulo4.setC4_p405_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_3)));
                modulo4.setC4_p405_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_4)));
                modulo4.setC4_p405_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_5)));
                modulo4.setC4_p405_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_6)));
                modulo4.setC4_p405_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p405_7)));
                modulo4.setC4_p406_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_1)));
                modulo4.setC4_p406_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_2)));
                modulo4.setC4_p406_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_3)));
                modulo4.setC4_p406_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_4)));
                modulo4.setC4_p406_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_5)));
                modulo4.setC4_p406_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_6)));
                modulo4.setC4_p406_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_7)));
                modulo4.setC4_p406_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_8)));
                modulo4.setC4_p406_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_o)));
                modulo4.setC4_p407_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_1)));
                modulo4.setC4_p407_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_2)));
                modulo4.setC4_p407_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_3)));
                modulo4.setC4_p407_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_4)));
                modulo4.setC4_p407_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_5)));
                modulo4.setC4_p407_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_6)));
                modulo4.setC4_p407_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_7)));
                modulo4.setC4_p407_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_8)));
                modulo4.setC4_p407_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_9)));
                modulo4.setC4_p407_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_10)));
                modulo4.setC4_p407_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_11)));
                modulo4.setC4_p407_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_12)));
                modulo4.setC4_p407_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_13)));
                modulo4.setC4_p407_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_o)));
                modulo4.setC4_p408_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_1)));
                modulo4.setC4_p408_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_2)));
                modulo4.setC4_p408_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_3)));
                modulo4.setC4_p408_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_4)));
                modulo4.setC4_p408_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_5)));
                modulo4.setC4_p408_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p408_6)));
                modulo4.setC4_p409(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p409)));
                modulo4.setC4_p409_nom(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p409_nom)));
                modulo4.setC4_p409_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p409_2)));
                modulo4.setC4_p410a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p410a)));
                modulo4.setC4_p410b(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p410b)));
                modulo4.setC4_p410(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p410)));
//                modulo4.setC4_p411_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_1)));
//                modulo4.setC4_p411_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_2)));
//                modulo4.setC4_p411_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_3)));
//                modulo4.setC4_p411_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_4)));
//                modulo4.setC4_p411_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_5)));
//                modulo4.setC4_p411_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_6)));
//                modulo4.setC4_p411_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_7)));
//                modulo4.setC4_p411_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_8)));
//                modulo4.setC4_p411_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_9)));
//                modulo4.setC4_p411_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_10)));
//                modulo4.setC4_p411_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_11)));
//                modulo4.setC4_p411_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_12)));
//                modulo4.setC4_p411_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_13)));
//                modulo4.setC4_p411_14(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_14)));
//                modulo4.setC4_p411_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411_o)));
                modulo4.setC4_p412(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p412)));
                modulo4.setC4_p413(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413)));
                modulo4.setC4_p414(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p414)));
                modulo4.setC4_p415(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p415)));
                modulo4.setC4_p416_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_1)));
                modulo4.setC4_p416_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_2)));
                modulo4.setC4_p416_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_3)));
                modulo4.setC4_p416_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_4)));
                modulo4.setC4_p416_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_5)));
                modulo4.setC4_p416_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_6)));
                modulo4.setC4_p416_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_7)));
                modulo4.setC4_p416_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_8)));
                modulo4.setC4_p416_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_o)));
                modulo4.setObs_cap4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_obs_cap4)));
                //Anthony M. 30/04/2021
                modulo4.setC4_p401_4_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p401_4_o)));
                modulo4.setC4_p403_14_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p403_14_o)));
                modulo4.setC4_p406_7_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p406_7_o)));
                modulo4.setC4_p407_13_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p407_13_o)));
                modulo4.setC4_p411(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p411)));
                modulo4.setC4_p413_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413_1)));
                modulo4.setC4_p413_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413_2)));
                modulo4.setC4_p413_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413_3)));
                modulo4.setC4_p413_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413_4)));
                modulo4.setC4_p413_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p413_5)));
                modulo4.setC4_p416_5_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p416_5_o)));
                modulo4.setC4_p417_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1)));
                modulo4.setC4_p417_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_2)));
                modulo4.setC4_p417_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_3)));
                modulo4.setC4_p417_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_4)));
                modulo4.setC4_p417_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_5)));
                modulo4.setC4_p417_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_6)));
                modulo4.setC4_p417_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_7)));
                modulo4.setC4_p417_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_8)));
                modulo4.setC4_p417_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_9)));
                modulo4.setC4_p417_7_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_7_o)));
                modulo4.setC4_p417_1_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_1)));
                modulo4.setC4_p417_1_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_2)));
                modulo4.setC4_p417_1_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_3)));
                modulo4.setC4_p417_1_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_4)));
                modulo4.setC4_p417_1_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_5)));
                modulo4.setC4_p417_1_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_6)));
                modulo4.setC4_p417_1_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_7)));
                modulo4.setC4_p417_1_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_8)));
                modulo4.setC4_p417_1_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_9)));
                modulo4.setC4_p417_1_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_10)));
                modulo4.setC4_p417_1_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_11)));
                modulo4.setC4_p417_1_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_12)));
                modulo4.setC4_p417_1_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_13)));
                modulo4.setC4_p417_1_13_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p417_1_13_o)));
                modulo4.setC4_p418(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p418)));
                modulo4.setC4_p418a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_c4_p418a)));
                modulo4.setObs_cap4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_obs_cap4)));
                modulo4.setCOB400(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo4_COB400)));
                modulo4s.add(modulo4);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo4s;
    }

    public Modulo5 getModulo5(String idEncuestado){
        Modulo5 modulo5 = null;
        String[] whereArgs = new String[]{idEncuestado};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo5,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                modulo5 = new Modulo5();
                modulo5.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_id)));
                modulo5.setIdInformante(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_id_informante)));
                modulo5.setIdHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_id_hogar)));
                modulo5.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_id_vivienda)));
                modulo5.setC5_p501(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p501)));
                modulo5.setC5_p501a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p501a)));
                modulo5.setC5_p501b(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p501b)));
                modulo5.setC5_p502_c(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p502_c)));
                modulo5.setC5_p502(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p502)));
                modulo5.setC5_p502_eleccion(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p502_eleccion)));
                modulo5.setC5_p502_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p502_o)));
                modulo5.setC5_p503(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p503)));
                modulo5.setC5_p504(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p504)));
                modulo5.setC5_p505(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p505)));
                modulo5.setC5_p506_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p506_1)));
                modulo5.setC5_p506_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p506_2)));
                modulo5.setC5_p506_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p506_3)));
                modulo5.setC5_p506_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p506_4)));
                modulo5.setC5_p507(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p507)));
                modulo5.setC5_p507_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p507_dist)));
                modulo5.setC5_p507_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p507_prov)));
                modulo5.setC5_p507_dep(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p507_dep)));
                modulo5.setC5_p508_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_1)));
                modulo5.setC5_p508_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_2)));
                modulo5.setC5_p508_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_3)));
                modulo5.setC5_p508_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_4)));
                modulo5.setC5_p508_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_5)));
                modulo5.setC5_p508_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_6)));
                modulo5.setC5_p508_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_7)));
                modulo5.setC5_p508_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_8)));
                modulo5.setC5_p508_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_9)));
                modulo5.setC5_p508_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_10)));
                modulo5.setC5_p508_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_11)));
                modulo5.setC5_p508_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_o)));
                modulo5.setC5_p509(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509)));
                modulo5.setC5_p510(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p510)));
                modulo5.setC5_p511(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p511)));
                modulo5.setC5_p511_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p511_o)));
                modulo5.setC5_p512(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p512)));
                modulo5.setC5_p512_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p512_o)));
                modulo5.setC5_p513(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p513)));
                modulo5.setC5_p513_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p513_o)));
                modulo5.setObs_cap5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_obs_cap5)));
                modulo5.setCOB500(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_COB500)));
                //Anthony M
                modulo5.setC5_p504_anio(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p504_anio)));
                modulo5.setC5_p504_grado(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p504_grado)));
                modulo5.setC5_p504_ce(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p504_ce)));
                modulo5.setC5_p506(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p506)));
                modulo5.setC5_p506a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p506a)));
                modulo5.setC5_p507_anio(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p507_anio)));
                modulo5.setC5_p507_grado(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p507_grado)));
                modulo5.setC5_p507_ce(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p507_ce)));
                modulo5.setC5_p508_4_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_4_o)));
                modulo5.setC5_p509_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_1)));
                modulo5.setC5_p509_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_2)));
                modulo5.setC5_p509_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_3)));
                modulo5.setC5_p509_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_4)));
                modulo5.setC5_p509_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_5)));
                modulo5.setC5_p509_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_6)));
                modulo5.setC5_p509_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_7)));
                modulo5.setC5_p509_7_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_7_o)));
                modulo5.setC5_p509_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_8)));
                modulo5.setC5_p510(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p510)));
                modulo5.setC5_p510_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p510_o)));
                modulo5.setC5_p514(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p514)));
                modulo5.setC5_p514_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p514_o)));
                modulo5.setC5_p515(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p515)));
                modulo5.setC5_p515_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p515_o)));
                modulo5.setC5_p516(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p516)));
                modulo5.setC5_p516_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p516_o)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo5;
    }

    public ArrayList<Modulo5> getAllModulo5(String idVivienda){
        ArrayList<Modulo5> modulo5s = new ArrayList<>();
        String[] whereArgs = new String[]{idVivienda};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo5,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                Modulo5 modulo5 = new Modulo5();
                modulo5.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_id)));
                modulo5.setIdInformante(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_id_informante)));
                modulo5.setIdHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_id_hogar)));
                modulo5.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_id_vivienda)));
                modulo5.setC5_p501(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p501)));
                modulo5.setC5_p501a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p501a)));
                modulo5.setC5_p501b(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p501b)));
                modulo5.setC5_p502_c(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p502_c)));
                modulo5.setC5_p502(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p502)));
                modulo5.setC5_p502_eleccion(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p502_eleccion)));
                modulo5.setC5_p502_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p502_o)));
                modulo5.setC5_p503(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p503)));
                modulo5.setC5_p504(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p504)));
                modulo5.setC5_p505(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p505)));
                modulo5.setC5_p506_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p506_1)));
                modulo5.setC5_p506_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p506_2)));
                modulo5.setC5_p506_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p506_3)));
                modulo5.setC5_p506_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p506_4)));
                modulo5.setC5_p507(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p507)));
                modulo5.setC5_p507_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p507_dist)));
                modulo5.setC5_p507_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p507_prov)));
                modulo5.setC5_p507_dep(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p507_dep)));
                modulo5.setC5_p508_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_1)));
                modulo5.setC5_p508_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_2)));
                modulo5.setC5_p508_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_3)));
                modulo5.setC5_p508_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_4)));
                modulo5.setC5_p508_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_5)));
                modulo5.setC5_p508_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_6)));
                modulo5.setC5_p508_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_7)));
                modulo5.setC5_p508_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_8)));
                modulo5.setC5_p508_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_9)));
                modulo5.setC5_p508_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_10)));
                modulo5.setC5_p508_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_11)));
                modulo5.setC5_p508_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_o)));
                modulo5.setC5_p509(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509)));
                modulo5.setC5_p510(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p510)));
                modulo5.setC5_p511(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p511)));
                modulo5.setC5_p511_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p511_o)));
                modulo5.setC5_p512(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p512)));
                modulo5.setC5_p512_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p512_o)));
                modulo5.setC5_p513(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p513)));
                modulo5.setC5_p513_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p513_o)));
                modulo5.setObs_cap5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_obs_cap5)));
                modulo5.setCOB500(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_COB500)));
                //Anthony M
                modulo5.setC5_p504_anio(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p504_anio)));
                modulo5.setC5_p504_grado(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p504_grado)));
                modulo5.setC5_p504_ce(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p504_ce)));
                modulo5.setC5_p506(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p506)));
                modulo5.setC5_p506a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p506a)));
                modulo5.setC5_p507_anio(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p507_anio)));
                modulo5.setC5_p507_grado(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p507_grado)));
                modulo5.setC5_p507_ce(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p507_ce)));
                modulo5.setC5_p508_4_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p508_4_o)));
                modulo5.setC5_p509_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_1)));
                modulo5.setC5_p509_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_2)));
                modulo5.setC5_p509_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_3)));
                modulo5.setC5_p509_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_4)));
                modulo5.setC5_p509_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_5)));
                modulo5.setC5_p509_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_6)));
                modulo5.setC5_p509_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_7)));
                modulo5.setC5_p509_7_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_7_o)));
                modulo5.setC5_p509_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p509_8)));
                modulo5.setC5_p510(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p510)));
                modulo5.setC5_p510_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p510_o)));
                modulo5.setC5_p514(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p514)));
                modulo5.setC5_p514_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p514_o)));
                modulo5.setC5_p515(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p515)));
                modulo5.setC5_p515_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p515_o)));
                modulo5.setC5_p516(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p516)));
                modulo5.setC5_p516_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo5_c5_p516_o)));
                modulo5s.add(modulo5);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo5s;
    }

    public Modulo6 getModulo6(String idEncuestado){
        Modulo6 modulo6 = null;
        String[] whereArgs = new String[]{idEncuestado};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo6,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                modulo6 = new Modulo6();
                modulo6.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_id)));
                modulo6.setIdInformante(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_id_informante)));
                modulo6.setIdHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_id_hogar)));
                modulo6.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_id_vivienda)));
                modulo6.setC6_p601(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p601)));
                modulo6.setC6_p602(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p602)));
                modulo6.setC6_p603(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p603)));
                modulo6.setC6_p604_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_1)));
                modulo6.setC6_p604_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_2)));
                modulo6.setC6_p604_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_3)));
                modulo6.setC6_p604_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_4)));
                modulo6.setC6_p604_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_5)));
                modulo6.setC6_p604_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_6)));
                modulo6.setC6_p604_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_7)));
                modulo6.setC6_p604_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_8)));
                modulo6.setC6_p604_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_9)));
                modulo6.setC6_p604_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_10)));
                modulo6.setC6_p604_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_11)));
                modulo6.setC6_p604_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_o)));
                modulo6.setC6_p605(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605)));
                //
                modulo6.setC6_p605_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_1)));
                modulo6.setC6_p605_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_2)));
                modulo6.setC6_p605_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_3)));
                modulo6.setC6_p605_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_4)));
                modulo6.setC6_p605_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_5)));
                modulo6.setC6_p605_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_6)));
                modulo6.setC6_p605_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_7)));
                modulo6.setC6_p605_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_8)));
                modulo6.setC6_p605_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_9)));
                modulo6.setC6_p605_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_10)));
                modulo6.setC6_p605_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_11)));
                modulo6.setC6_p605_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_12)));
                modulo6.setC6_p605_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_o)));
                //
                modulo6.setC6_p606(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p606)));
                modulo6.setC6_p606_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p606_o)));
                modulo6.setC6_p607(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p607)));
                modulo6.setC6_p608(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p608)));
                modulo6.setC6_p608_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p608_o)));
                modulo6.setC6_p609(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p609)));
                modulo6.setC6_p609_cod(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p609_cod)));
                modulo6.setC6_p610_pd(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pd)));
                modulo6.setC6_p610_pl(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pl)));
                modulo6.setC6_p610_pm(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pm)));
                modulo6.setC6_p610_pmi(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pmi)));
                modulo6.setC6_p610_pj(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pj)));
                modulo6.setC6_p610_pv(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pv)));
                modulo6.setC6_p610_ps(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_ps)));
                modulo6.setC6_p610_pt(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pt)));
                modulo6.setC6_p610_sd(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_sd)));
                modulo6.setC6_p610_sl(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_sl)));
                modulo6.setC6_p610_sm(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_sm)));
                modulo6.setC6_p610_smi(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_smi)));
                modulo6.setC6_p610_sj(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_sj)));
                modulo6.setC6_p610_sv(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_sv)));
                modulo6.setC6_p610_ss(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_ss)));
                modulo6.setC6_p610_st(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_st)));
                modulo6.setC6_p610_t(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_t)));
                modulo6.setC6_p611(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p611)));
                modulo6.setC6_p611a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p611a)));
                modulo6.setC6_p611b(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p611b)));
                modulo6.setC6_p611_cod(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p611_cod)));
                modulo6.setC6_p612(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p612)));
                modulo6.setC6_p612_nro(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p612_nro)));
                modulo6.setC6_p613(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p613)));
                modulo6.setC6_p614_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p614_mon)));
                modulo6.setC6_p614_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p614_esp)));
                modulo6.setC6_p615_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_mon)));
                modulo6.setC6_p615_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_esp)));
                //
                modulo6.setC6_p615_pd(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pd)));
                modulo6.setC6_p615_pl(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pl)));
                modulo6.setC6_p615_pm(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pm)));
                modulo6.setC6_p615_pmi(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pmi)));
                modulo6.setC6_p615_pj(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pj)));
                modulo6.setC6_p615_pv(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pv)));
                modulo6.setC6_p615_ps(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_ps)));
                modulo6.setC6_p615_pt(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pt)));
                modulo6.setC6_p615_sd(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_sd)));
                modulo6.setC6_p615_sl(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_sl)));
                modulo6.setC6_p615_sm(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_sm)));
                modulo6.setC6_p615_smi(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_smi)));
                modulo6.setC6_p615_sj(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_sj)));
                modulo6.setC6_p615_sv(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_sv)));
                modulo6.setC6_p615_ss(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_ss)));
                modulo6.setC6_p615_st(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_st)));
                modulo6.setC6_p615_t(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_t)));
                //
                modulo6.setC6_p616(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p616)));
                modulo6.setC6_p616_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p616_a)));
                modulo6.setC6_p616_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p616_mon)));
                modulo6.setC6_p616_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p616_esp)));
                modulo6.setC6_p616_nas(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p616_nas)));
                modulo6.setC6_p617(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p617)));
                modulo6.setC6_p617_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p617_dist)));
                modulo6.setC6_p617_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p617_prov)));
                modulo6.setC6_p617_dep(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p617_dep)));
                modulo6.setC6_p625_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_1)));
                modulo6.setC6_p625_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_2)));
                modulo6.setC6_p625_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_3)));
                modulo6.setC6_p625_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_4)));
                modulo6.setC6_p625_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_5)));
                modulo6.setC6_p625_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_6)));
                modulo6.setC6_p625_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_o)));
                modulo6.setC6_p618(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p618)));
                modulo6.setC6_p619(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p619)));
                modulo6.setC6_p619_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p619_o)));
                modulo6.setC6_p620(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p620)));
                //
                modulo6.setC6_p620_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p620_o)));
                //
                modulo6.setC6_p622_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p622_mon)));
                modulo6.setC6_p622_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p622_esp)));
                modulo6.setC6_p623_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p623_mon)));
                modulo6.setC6_p623_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p623_esp)));
                modulo6.setC6_p623_nas(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p623_nas)));
                modulo6.setC6_p624_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p624_mon)));
                modulo6.setC6_p624_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p624_esp)));
                modulo6.setC6_p624_nas(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p624_nas)));
                modulo6.setC6_p624_nas2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p624_nas2)));
                //
                modulo6.setC6_p621(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p621)));
                modulo6.setC6_p622(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p622)));
                modulo6.setC6_p622_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p622_o)));
                modulo6.setC6_p623(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p623)));
                modulo6.setC6_p623_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p623_o)));
                modulo6.setC6_p624(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p624)));
                modulo6.setC6_p626(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p626)));
                modulo6.setC6_p627(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p627)));
                modulo6.setC6_p628(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p628)));
                modulo6.setC6_p629_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_1)));
                modulo6.setC6_p629_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_2)));
                modulo6.setC6_p629_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_3)));
                modulo6.setC6_p629_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_4)));
                modulo6.setC6_p629_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_o)));
                modulo6.setC6_p629_1_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_1_f)));
                modulo6.setC6_p629_1_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_1_m)));
                modulo6.setC6_p629_2_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_2_f)));
                modulo6.setC6_p629_2_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_2_m)));
                modulo6.setC6_p629_3_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_3_f)));
                modulo6.setC6_p629_3_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_3_m)));
                modulo6.setC6_p629_4_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_4_f)));
                modulo6.setC6_p629_4_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_4_m)));
                modulo6.setC6_p630_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1)));
                modulo6.setC6_p630_1med(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1med)));
                modulo6.setC6_p630_1o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1o)));
                modulo6.setC6_p630_1frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1frec)));
                modulo6.setC6_p630_1frec_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1frec_o)));
                modulo6.setC6_p630_1mont(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1mont)));
                modulo6.setC6_p630_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2)));
                modulo6.setC6_p630_2med(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2med)));
                modulo6.setC6_p630_2o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2o)));
                modulo6.setC6_p630_2frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2frec)));
                modulo6.setC6_p630_2frec_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2frec_o)));
                modulo6.setC6_p630_2mont(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2mont)));
                modulo6.setObs_cap6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_obs_cap6)));
                modulo6.setCOB600(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_COB600)));
                //Anthony M
                modulo6.setC6_p625(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625)));
                modulo6.setC6_p625_cod_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_cod_dist)));
                modulo6.setC6_p625_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_dist)));
                modulo6.setC6_p625_cod_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_cod_prov)));
                modulo6.setC6_p625_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_prov)));
                modulo6.setC6_p625_cod_depa(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_cod_depa)));
                modulo6.setC6_p625_depa(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_depa)));
                modulo6.setC6_p628_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p628_o)));
                modulo6.setC6_p629(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629)));
                modulo6.setC6_p630(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630)));
                modulo6.setC6_p631(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p631)));
                modulo6.setC6_p631_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p631_o)));
                modulo6.setC6_p632_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_1)));
                modulo6.setC6_p632_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_2)));
                modulo6.setC6_p632_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_3)));
                modulo6.setC6_p632_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_4)));
                modulo6.setC6_p632_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_5)));
                modulo6.setC6_p632_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_6)));
                modulo6.setC6_p632_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_7)));
                modulo6.setC6_p632_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_8)));
                modulo6.setC6_p632_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_9)));
                modulo6.setC6_p632_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_10)));
                modulo6.setC6_p632_10_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_10_o)));
                modulo6.setC6_p632_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_11)));//campo nuevo
                modulo6.setC6_p632i(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632i)));
                modulo6.setC6_p633(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p633)));
                modulo6.setC6_p634_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_1)));
                modulo6.setC6_p634_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_2)));
                modulo6.setC6_p634_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_3)));
                modulo6.setC6_p634_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_4)));
                modulo6.setC6_p634_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_5)));
                modulo6.setC6_p634_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_6)));
                modulo6.setC6_p634_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_7)));
                //modulo6.setC6_p634_6_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_6_o)));
                modulo6.setC6_p634_7_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_7_o)));
                modulo6.setC6_p635(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p635)));
                modulo6.setC6_p636(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p636)));
                modulo6.setC6_p637(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p637)));
                modulo6.setC6_p638_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_1)));
                modulo6.setC6_p638_1_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_1_frec)));
                modulo6.setC6_p638_1_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_1_monto)));
                modulo6.setC6_p638_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_2)));
                modulo6.setC6_p638_2_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_2_frec)));
                modulo6.setC6_p638_2_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_2_monto)));
                modulo6.setC6_p638_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_3)));
                modulo6.setC6_p638_3_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_3_frec)));
                modulo6.setC6_p638_3_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_3_monto)));
                modulo6.setC6_p638_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_4)));
                modulo6.setC6_p638_4_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_4_frec)));
                modulo6.setC6_p638_4_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_4_monto)));
                modulo6.setC6_p639_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1)));
                modulo6.setC6_p639_1_med(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1_med)));
                modulo6.setC6_p639_1_med_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1_med_o)));
                modulo6.setC6_p639_1_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1_frec)));
                modulo6.setC6_p639_1_frec_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1_frec_o)));
                modulo6.setC6_p639_1_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1_monto)));
                modulo6.setC6_p639_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2)));
                modulo6.setC6_p639_2_med(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2_med)));
                modulo6.setC6_p639_2_med_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2_med_o)));
                modulo6.setC6_p639_2_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2_frec)));
                modulo6.setC6_p639_2_frec_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2_frec_o)));
                modulo6.setC6_p639_2_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2_monto)));

            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo6;
    }

    public ArrayList<Modulo6> getAllModulo6(String idVivienda){
        ArrayList<Modulo6> modulo6s = new ArrayList<Modulo6>();
        String[] whereArgs = new String[]{idVivienda};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo6,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                Modulo6 modulo6 = new Modulo6();
                modulo6.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_id)));
                modulo6.setIdInformante(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_id_informante)));
                modulo6.setIdHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_id_hogar)));
                modulo6.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_id_vivienda)));
                modulo6.setC6_p601(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p601)));
                modulo6.setC6_p602(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p602)));
                modulo6.setC6_p603(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p603)));
                modulo6.setC6_p604_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_1)));
                modulo6.setC6_p604_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_2)));
                modulo6.setC6_p604_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_3)));
                modulo6.setC6_p604_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_4)));
                modulo6.setC6_p604_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_5)));
                modulo6.setC6_p604_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_6)));
                modulo6.setC6_p604_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_7)));
                modulo6.setC6_p604_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_8)));
                modulo6.setC6_p604_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_9)));
                modulo6.setC6_p604_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_10)));
                modulo6.setC6_p604_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_11)));
                modulo6.setC6_p604_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_o)));
                modulo6.setC6_p605(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605)));
                //
                modulo6.setC6_p605_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_1)));
                modulo6.setC6_p605_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_2)));
                modulo6.setC6_p605_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_3)));
                modulo6.setC6_p605_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_4)));
                modulo6.setC6_p605_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_5)));
                modulo6.setC6_p605_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_6)));
                modulo6.setC6_p605_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_7)));
                modulo6.setC6_p605_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_8)));
                modulo6.setC6_p605_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_9)));
                modulo6.setC6_p605_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_10)));
                modulo6.setC6_p605_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_11)));
                modulo6.setC6_p605_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_12)));
                modulo6.setC6_p605_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_o)));
                //
                modulo6.setC6_p606(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p606)));
                modulo6.setC6_p606_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p606_o)));
                modulo6.setC6_p607(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p607)));
                modulo6.setC6_p608(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p608)));
                modulo6.setC6_p608_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p608_o)));
                modulo6.setC6_p609(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p609)));
                modulo6.setC6_p609_cod(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p609_cod)));
                modulo6.setC6_p610_pd(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pd)));
                modulo6.setC6_p610_pl(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pl)));
                modulo6.setC6_p610_pm(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pm)));
                modulo6.setC6_p610_pmi(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pmi)));
                modulo6.setC6_p610_pj(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pj)));
                modulo6.setC6_p610_pv(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pv)));
                modulo6.setC6_p610_ps(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_ps)));
                modulo6.setC6_p610_pt(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pt)));
                modulo6.setC6_p610_sd(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_sd)));
                modulo6.setC6_p610_sl(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_sl)));
                modulo6.setC6_p610_sm(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_sm)));
                modulo6.setC6_p610_smi(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_smi)));
                modulo6.setC6_p610_sj(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_sj)));
                modulo6.setC6_p610_sv(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_sv)));
                modulo6.setC6_p610_ss(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_ss)));
                modulo6.setC6_p610_st(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_st)));
                modulo6.setC6_p610_t(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_t)));
                modulo6.setC6_p611(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p611)));
                modulo6.setC6_p611a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p611a)));
                modulo6.setC6_p611b(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p611b)));
                modulo6.setC6_p611_cod(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p611_cod)));
                modulo6.setC6_p612(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p612)));
                modulo6.setC6_p612_nro(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p612_nro)));
                modulo6.setC6_p613(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p613)));
                modulo6.setC6_p614_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p614_mon)));
                modulo6.setC6_p614_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p614_esp)));
                modulo6.setC6_p615_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_mon)));
                modulo6.setC6_p615_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_esp)));
                //
                modulo6.setC6_p615_pd(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pd)));
                modulo6.setC6_p615_pl(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pl)));
                modulo6.setC6_p615_pm(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pm)));
                modulo6.setC6_p615_pmi(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pmi)));
                modulo6.setC6_p615_pj(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pj)));
                modulo6.setC6_p615_pv(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pv)));
                modulo6.setC6_p615_ps(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_ps)));
                modulo6.setC6_p615_pt(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pt)));
                modulo6.setC6_p615_sd(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_sd)));
                modulo6.setC6_p615_sl(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_sl)));
                modulo6.setC6_p615_sm(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_sm)));
                modulo6.setC6_p615_smi(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_smi)));
                modulo6.setC6_p615_sj(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_sj)));
                modulo6.setC6_p615_sv(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_sv)));
                modulo6.setC6_p615_ss(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_ss)));
                modulo6.setC6_p615_st(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_st)));
                modulo6.setC6_p615_t(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_t)));
                //
                modulo6.setC6_p616(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p616)));
                modulo6.setC6_p616_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p616_a)));
                modulo6.setC6_p616_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p616_mon)));
                modulo6.setC6_p616_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p616_esp)));
                modulo6.setC6_p616_nas(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p616_nas)));
                modulo6.setC6_p617(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p617)));
                modulo6.setC6_p617_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p617_dist)));
                modulo6.setC6_p617_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p617_prov)));
                modulo6.setC6_p617_dep(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p617_dep)));
                modulo6.setC6_p625_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_1)));
                modulo6.setC6_p625_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_2)));
                modulo6.setC6_p625_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_3)));
                modulo6.setC6_p625_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_4)));
                modulo6.setC6_p625_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_5)));
                modulo6.setC6_p625_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_6)));
                modulo6.setC6_p625_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_o)));
                modulo6.setC6_p618(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p618)));
                modulo6.setC6_p619(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p619)));
                modulo6.setC6_p619_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p619_o)));
                modulo6.setC6_p620(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p620)));
                //
                modulo6.setC6_p620_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p620_o)));
                //
                modulo6.setC6_p622_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p622_mon)));
                modulo6.setC6_p622_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p622_esp)));
                modulo6.setC6_p623_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p623_mon)));
                modulo6.setC6_p623_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p623_esp)));
                modulo6.setC6_p623_nas(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p623_nas)));
                modulo6.setC6_p624_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p624_mon)));
                modulo6.setC6_p624_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p624_esp)));
                modulo6.setC6_p624_nas(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p624_nas)));
                modulo6.setC6_p624_nas2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p624_nas2)));
                //
                modulo6.setC6_p621(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p621)));
                modulo6.setC6_p622(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p622)));
                modulo6.setC6_p622_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p622_o)));
                modulo6.setC6_p623(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p623)));
                modulo6.setC6_p623_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p623_o)));
                modulo6.setC6_p624(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p624)));
                modulo6.setC6_p626(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p626)));
                modulo6.setC6_p627(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p627)));
                modulo6.setC6_p628(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p628)));
                modulo6.setC6_p629_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_1)));
                modulo6.setC6_p629_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_2)));
                modulo6.setC6_p629_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_3)));
                modulo6.setC6_p629_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_4)));
                modulo6.setC6_p629_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_o)));
                modulo6.setC6_p629_1_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_1_f)));
                modulo6.setC6_p629_1_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_1_m)));
                modulo6.setC6_p629_2_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_2_f)));
                modulo6.setC6_p629_2_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_2_m)));
                modulo6.setC6_p629_3_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_3_f)));
                modulo6.setC6_p629_3_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_3_m)));
                modulo6.setC6_p629_4_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_4_f)));
                modulo6.setC6_p629_4_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_4_m)));
                modulo6.setC6_p630_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1)));
                modulo6.setC6_p630_1med(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1med)));
                modulo6.setC6_p630_1o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1o)));
                modulo6.setC6_p630_1frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1frec)));
                modulo6.setC6_p630_1frec_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1frec_o)));
                modulo6.setC6_p630_1mont(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1mont)));
                modulo6.setC6_p630_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2)));
                modulo6.setC6_p630_2med(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2med)));
                modulo6.setC6_p630_2o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2o)));
                modulo6.setC6_p630_2frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2frec)));
                modulo6.setC6_p630_2frec_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2frec_o)));
                modulo6.setC6_p630_2mont(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2mont)));
                modulo6.setObs_cap6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_obs_cap6)));
                modulo6.setCOB600(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_COB600)));
                //Anthony M
                modulo6.setC6_p625(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625)));
                modulo6.setC6_p625_cod_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_cod_dist)));
                modulo6.setC6_p625_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_dist)));
                modulo6.setC6_p625_cod_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_cod_prov)));
                modulo6.setC6_p625_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_prov)));
                modulo6.setC6_p625_cod_depa(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_cod_depa)));
                modulo6.setC6_p625_depa(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_depa)));
                modulo6.setC6_p628_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p628_o)));
                modulo6.setC6_p629(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629)));
                modulo6.setC6_p630(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630)));
                modulo6.setC6_p631(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p631)));
                modulo6.setC6_p631_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p631_o)));
                modulo6.setC6_p632_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_1)));
                modulo6.setC6_p632_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_2)));
                modulo6.setC6_p632_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_3)));
                modulo6.setC6_p632_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_4)));
                modulo6.setC6_p632_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_5)));
                modulo6.setC6_p632_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_6)));
                modulo6.setC6_p632_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_7)));
                modulo6.setC6_p632_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_8)));
                modulo6.setC6_p632_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_9)));
                modulo6.setC6_p632_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_10)));
                modulo6.setC6_p632_10_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_10_o)));
                modulo6.setC6_p632_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_11)));//campo nuevo
                modulo6.setC6_p632i(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632i)));
                modulo6.setC6_p633(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p633)));
                modulo6.setC6_p634_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_1)));
                modulo6.setC6_p634_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_2)));
                modulo6.setC6_p634_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_3)));
                modulo6.setC6_p634_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_4)));
                modulo6.setC6_p634_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_5)));
                modulo6.setC6_p634_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_6)));
                modulo6.setC6_p634_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_7)));
                //modulo6.setC6_p634_6_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_6_o)));
                modulo6.setC6_p634_7_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_7_o)));
                modulo6.setC6_p635(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p635)));
                modulo6.setC6_p636(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p636)));
                modulo6.setC6_p637(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p637)));
                modulo6.setC6_p638_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_1)));
                modulo6.setC6_p638_1_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_1_frec)));
                modulo6.setC6_p638_1_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_1_monto)));
                modulo6.setC6_p638_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_2)));
                modulo6.setC6_p638_2_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_2_frec)));
                modulo6.setC6_p638_2_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_2_monto)));
                modulo6.setC6_p638_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_3)));
                modulo6.setC6_p638_3_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_3_frec)));
                modulo6.setC6_p638_3_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_3_monto)));
                modulo6.setC6_p638_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_4)));
                modulo6.setC6_p638_4_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_4_frec)));
                modulo6.setC6_p638_4_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_4_monto)));
                modulo6.setC6_p639_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1)));
                modulo6.setC6_p639_1_med(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1_med)));
                modulo6.setC6_p639_1_med_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1_med_o)));
                modulo6.setC6_p639_1_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1_frec)));
                modulo6.setC6_p639_1_frec_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1_frec_o)));
                modulo6.setC6_p639_1_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1_monto)));
                modulo6.setC6_p639_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2)));
                modulo6.setC6_p639_2_med(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2_med)));
                modulo6.setC6_p639_2_med_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2_med_o)));
                modulo6.setC6_p639_2_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2_frec)));
                modulo6.setC6_p639_2_frec_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2_frec_o)));
                modulo6.setC6_p639_2_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2_monto)));

                modulo6.setObs_cap6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_obs_cap6)));
                modulo6.setCOB600(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_COB600)));
                modulo6s.add(modulo6);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo6s;
    }

    public ArrayList<Modulo6> getAllModulo6Hogar(String idHogar){
        ArrayList<Modulo6> modulo6s = new ArrayList<Modulo6>();
        String[] whereArgs = new String[]{idHogar};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo6,null,SQLConstantes.WHERE_CLAUSE_HOGAR_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                Modulo6 modulo6 = new Modulo6();
                modulo6.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_id)));
                modulo6.setIdInformante(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_id_informante)));
                modulo6.setIdHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_id_hogar)));
                modulo6.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_id_vivienda)));
                modulo6.setC6_p601(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p601)));
                modulo6.setC6_p602(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p602)));
                modulo6.setC6_p603(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p603)));
                modulo6.setC6_p604_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_1)));
                modulo6.setC6_p604_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_2)));
                modulo6.setC6_p604_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_3)));
                modulo6.setC6_p604_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_4)));
                modulo6.setC6_p604_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_5)));
                modulo6.setC6_p604_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_6)));
                modulo6.setC6_p604_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_7)));
                modulo6.setC6_p604_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_8)));
                modulo6.setC6_p604_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_9)));
                modulo6.setC6_p604_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_10)));
                modulo6.setC6_p604_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_11)));
                modulo6.setC6_p604_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p604_o)));
                modulo6.setC6_p605(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605)));
                modulo6.setC6_p605_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_1)));
                modulo6.setC6_p605_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_2)));
                modulo6.setC6_p605_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_3)));
                modulo6.setC6_p605_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_4)));
                modulo6.setC6_p605_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_5)));
                modulo6.setC6_p605_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_6)));
                modulo6.setC6_p605_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_7)));
                modulo6.setC6_p605_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_8)));
                modulo6.setC6_p605_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_9)));
                modulo6.setC6_p605_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_10)));
                modulo6.setC6_p605_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_11)));
                modulo6.setC6_p605_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_12)));
                modulo6.setC6_p605_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p605_o)));
                modulo6.setC6_p606(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p606)));
                modulo6.setC6_p606_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p606_o)));
                modulo6.setC6_p607(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p607)));
                modulo6.setC6_p608(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p608)));
                modulo6.setC6_p608_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p608_o)));
                modulo6.setC6_p609(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p609)));
                modulo6.setC6_p609_cod(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p609_cod)));
                modulo6.setC6_p610_pd(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pd)));
                modulo6.setC6_p610_pl(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pl)));
                modulo6.setC6_p610_pm(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pm)));
                modulo6.setC6_p610_pmi(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pmi)));
                modulo6.setC6_p610_pj(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pj)));
                modulo6.setC6_p610_pv(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pv)));
                modulo6.setC6_p610_ps(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_ps)));
                modulo6.setC6_p610_pt(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_pt)));
                modulo6.setC6_p610_sd(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_sd)));
                modulo6.setC6_p610_sl(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_sl)));
                modulo6.setC6_p610_sm(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_sm)));
                modulo6.setC6_p610_smi(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_smi)));
                modulo6.setC6_p610_sj(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_sj)));
                modulo6.setC6_p610_sv(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_sv)));
                modulo6.setC6_p610_ss(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_ss)));
                modulo6.setC6_p610_st(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_st)));
                modulo6.setC6_p610_t(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p610_t)));
                modulo6.setC6_p611(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p611)));
                modulo6.setC6_p611a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p611a)));
                modulo6.setC6_p611b(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p611b)));
                modulo6.setC6_p611_cod(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p611_cod)));
                modulo6.setC6_p612(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p612)));
                modulo6.setC6_p612_nro(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p612_nro)));
                modulo6.setC6_p613(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p613)));
                modulo6.setC6_p614_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p614_mon)));
                modulo6.setC6_p614_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p614_esp)));
                modulo6.setC6_p615_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_mon)));
                modulo6.setC6_p615_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_esp)));
                modulo6.setC6_p615_pd(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pd)));
                modulo6.setC6_p615_pl(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pl)));
                modulo6.setC6_p615_pm(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pm)));
                modulo6.setC6_p615_pmi(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pmi)));
                modulo6.setC6_p615_pj(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pj)));
                modulo6.setC6_p615_pv(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pv)));
                modulo6.setC6_p615_ps(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_ps)));
                modulo6.setC6_p615_pt(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_pt)));
                modulo6.setC6_p615_sd(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_sd)));
                modulo6.setC6_p615_sl(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_sl)));
                modulo6.setC6_p615_sm(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_sm)));
                modulo6.setC6_p615_smi(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_smi)));
                modulo6.setC6_p615_sj(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_sj)));
                modulo6.setC6_p615_sv(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_sv)));
                modulo6.setC6_p615_ss(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_ss)));
                modulo6.setC6_p615_st(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_st)));
                modulo6.setC6_p615_t(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p615_t)));
                modulo6.setC6_p616(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p616)));
                modulo6.setC6_p616_a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p616_a)));
                modulo6.setC6_p616_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p616_mon)));
                modulo6.setC6_p616_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p616_esp)));
                modulo6.setC6_p616_nas(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p616_nas)));
                modulo6.setC6_p617(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p617)));
                modulo6.setC6_p617_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p617_dist)));
                modulo6.setC6_p617_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p617_prov)));
                modulo6.setC6_p617_dep(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p617_dep)));
                modulo6.setC6_p625_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_1)));
                modulo6.setC6_p625_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_2)));
                modulo6.setC6_p625_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_3)));
                modulo6.setC6_p625_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_4)));
                modulo6.setC6_p625_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_5)));
                modulo6.setC6_p625_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_6)));
                modulo6.setC6_p625_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_o)));
                modulo6.setC6_p618(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p618)));
                modulo6.setC6_p619(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p619)));
                modulo6.setC6_p619_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p619_o)));
                modulo6.setC6_p620(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p620)));
                modulo6.setC6_p620_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p620_o)));
                modulo6.setC6_p622_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p622_mon)));
                modulo6.setC6_p622_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p622_esp)));
                modulo6.setC6_p623_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p623_mon)));
                modulo6.setC6_p623_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p623_esp)));
                modulo6.setC6_p623_nas(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p623_nas)));
                modulo6.setC6_p624_mon(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p624_mon)));
                modulo6.setC6_p624_esp(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p624_esp)));
                modulo6.setC6_p624_nas(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p624_nas)));
                modulo6.setC6_p624_nas2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p624_nas2)));
                modulo6.setC6_p621(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p621)));
                modulo6.setC6_p622(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p622)));
                modulo6.setC6_p622_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p622_o)));
                modulo6.setC6_p623(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p623)));
                modulo6.setC6_p623_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p623_o)));
                modulo6.setC6_p624(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p624)));
                modulo6.setC6_p626(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p626)));
                modulo6.setC6_p627(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p627)));
                modulo6.setC6_p628(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p628)));
                modulo6.setC6_p629_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_1)));
                modulo6.setC6_p629_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_2)));
                modulo6.setC6_p629_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_3)));
                modulo6.setC6_p629_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_4)));
                modulo6.setC6_p629_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_o)));
                modulo6.setC6_p629_1_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_1_f)));
                modulo6.setC6_p629_1_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_1_m)));
                modulo6.setC6_p629_2_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_2_f)));
                modulo6.setC6_p629_2_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_2_m)));
                modulo6.setC6_p629_3_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_3_f)));
                modulo6.setC6_p629_3_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_3_m)));
                modulo6.setC6_p629_4_f(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_4_f)));
                modulo6.setC6_p629_4_m(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629_4_m)));
                modulo6.setC6_p630_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1)));
                modulo6.setC6_p630_1med(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1med)));
                modulo6.setC6_p630_1o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1o)));
                modulo6.setC6_p630_1frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1frec)));
                modulo6.setC6_p630_1frec_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1frec_o)));
                modulo6.setC6_p630_1mont(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_1mont)));
                modulo6.setC6_p630_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2)));
                modulo6.setC6_p630_2med(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2med)));
                modulo6.setC6_p630_2o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2o)));
                modulo6.setC6_p630_2frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2frec)));
                modulo6.setC6_p630_2frec_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2frec_o)));
                modulo6.setC6_p630_2mont(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630_2mont)));
                modulo6.setC6_p625(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625)));
                modulo6.setC6_p625_cod_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_cod_dist)));
                modulo6.setC6_p625_dist(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_dist)));
                modulo6.setC6_p625_cod_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_cod_prov)));
                modulo6.setC6_p625_prov(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_prov)));
                modulo6.setC6_p625_cod_depa(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_cod_depa)));
                modulo6.setC6_p625_depa(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p625_depa)));
                modulo6.setC6_p628_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p628_o)));
                modulo6.setC6_p629(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p629)));
                modulo6.setC6_p630(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p630)));
                modulo6.setC6_p631(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p631)));
                modulo6.setC6_p631_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p631_o)));
                modulo6.setC6_p632_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_1)));
                modulo6.setC6_p632_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_2)));
                modulo6.setC6_p632_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_3)));
                modulo6.setC6_p632_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_4)));
                modulo6.setC6_p632_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_5)));
                modulo6.setC6_p632_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_6)));
                modulo6.setC6_p632_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_7)));
                modulo6.setC6_p632_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_8)));
                modulo6.setC6_p632_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_9)));
                modulo6.setC6_p632_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_10)));
                modulo6.setC6_p632_10_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_10_o)));
                modulo6.setC6_p632_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632_11)));//campo nuevo
                modulo6.setC6_p632i(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p632i)));
                modulo6.setC6_p633(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p633)));
                modulo6.setC6_p634_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_1)));
                modulo6.setC6_p634_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_2)));
                modulo6.setC6_p634_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_3)));
                modulo6.setC6_p634_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_4)));
                modulo6.setC6_p634_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_5)));
                modulo6.setC6_p634_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_6)));
                modulo6.setC6_p634_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_7)));
                //modulo6.setC6_p634_6_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_6_o)));
                modulo6.setC6_p634_7_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p634_7_o)));
                modulo6.setC6_p635(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p635)));
                modulo6.setC6_p636(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p636)));
                modulo6.setC6_p637(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p637)));
                modulo6.setC6_p638_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_1)));
                modulo6.setC6_p638_1_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_1_frec)));
                modulo6.setC6_p638_1_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_1_monto)));
                modulo6.setC6_p638_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_2)));
                modulo6.setC6_p638_2_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_2_frec)));
                modulo6.setC6_p638_2_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_2_monto)));
                modulo6.setC6_p638_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_3)));
                modulo6.setC6_p638_3_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_3_frec)));
                modulo6.setC6_p638_3_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_3_monto)));
                modulo6.setC6_p638_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_4)));
                modulo6.setC6_p638_4_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_4_frec)));
                modulo6.setC6_p638_4_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p638_4_monto)));
                modulo6.setC6_p639_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1)));
                modulo6.setC6_p639_1_med(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1_med)));
                modulo6.setC6_p639_1_med_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1_med_o)));
                modulo6.setC6_p639_1_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1_frec)));
                modulo6.setC6_p639_1_frec_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1_frec_o)));
                modulo6.setC6_p639_1_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_1_monto)));
                modulo6.setC6_p639_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2)));
                modulo6.setC6_p639_2_med(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2_med)));
                modulo6.setC6_p639_2_med_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2_med_o)));
                modulo6.setC6_p639_2_frec(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2_frec)));
                modulo6.setC6_p639_2_frec_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2_frec_o)));
                modulo6.setC6_p639_2_monto(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_c6_p639_2_monto)));
                modulo6.setObs_cap6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_obs_cap6)));
                modulo6.setCOB600(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo6_COB600)));
                modulo6s.add(modulo6);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo6s;
    }

    public boolean menor_edad_hogar(String idHogar){
        boolean existe=false;
        String[] whereArgs = new String[]{idHogar};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaresidentes,null, SQLConstantes.WHERE_CLAUSE_EXISTE_MENOR,whereArgs,null,null,null);
            if(cursor.getCount() >= 1){
                existe = true;
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return existe;
    }

    public Modulo7 getModulo7(String idPersona) {
        Modulo7 modulo7 = null;
        String[] whereArgs = new String[]{idPersona};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo7,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                modulo7 = new Modulo7();
                modulo7.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_id)));
                modulo7.setIdInformante(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_id_informante)));
                modulo7.setIdHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_id_hogar)));
                modulo7.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_id_vivienda)));
                modulo7.setC7_p701(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p701)));
                modulo7.setC7_p702_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_1)));
                modulo7.setC7_p702_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_2)));
                modulo7.setC7_p702_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_3)));
                modulo7.setC7_p702_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_4)));
                modulo7.setC7_p702_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_5)));
                modulo7.setC7_p702_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_6)));
                modulo7.setC7_p702_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_7)));
                modulo7.setC7_p702_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_8)));
                modulo7.setC7_p702_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_9)));
                modulo7.setC7_p702_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_10)));
                modulo7.setC7_p702_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_o)));
                modulo7.setC7_p703(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703)));
                modulo7.setC7_p704_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_1)));
                modulo7.setC7_p704_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_2)));
                modulo7.setC7_p704_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_3)));
                modulo7.setC7_p704_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_4)));
                modulo7.setC7_p704_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_5)));
                modulo7.setC7_p704_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_6)));
                modulo7.setC7_p704_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_o)));
                modulo7.setC7_p705_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_1)));
                modulo7.setC7_p705_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_2)));
                modulo7.setC7_p705_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_3)));
                modulo7.setC7_p705_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_4)));
                modulo7.setC7_p705_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_5)));
                modulo7.setC7_p705_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_6)));
                modulo7.setC7_p705_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_7)));
                modulo7.setC7_p705_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_o)));
                modulo7.setC7_p706(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706)));
                modulo7.setC7_p707_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_1)));
                modulo7.setC7_p707_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_2)));
                modulo7.setC7_p707_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_3)));
                modulo7.setC7_p707_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_4)));
                modulo7.setC7_p707_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_5)));
                modulo7.setC7_p707_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_6)));
                modulo7.setC7_p707_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_7)));
                modulo7.setC7_p707_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_8)));
                modulo7.setC7_p707_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_9)));
//                modulo7.setC7_p707_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_o)));
                modulo7.setC7_p708_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p708_1)));
                modulo7.setC7_p708_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p708_2)));
                modulo7.setC7_p708_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p708_3)));
                modulo7.setC7_p708_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p708_4)));
                modulo7.setC7_p708_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p708_5)));
                modulo7.setC7_p709_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_1)));
                modulo7.setC7_p709_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_2)));
                modulo7.setC7_p709_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_3)));
                modulo7.setC7_p709_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_4)));
                modulo7.setC7_p709_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_5)));
                modulo7.setC7_p709_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_6)));
                modulo7.setC7_p709_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_7)));
                modulo7.setC7_p709_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_8)));
                modulo7.setC7_p709_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_9)));
                modulo7.setC7_p709_10a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_10a)));
                modulo7.setC7_p709_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_10)));
                modulo7.setC7_p709_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_o)));
                modulo7.setObs_cap7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_obs_cap7)));
                modulo7.setCOB700(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_COB700)));
                //Anthony M 04/05/2021
                modulo7.setC7_p702_7_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_7_o)));
                modulo7.setC7_p703_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_1)));
                modulo7.setC7_p703_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_2)));
                modulo7.setC7_p703_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_3)));
                modulo7.setC7_p703_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_4)));
                modulo7.setC7_p703_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_5)));
                modulo7.setC7_p703_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_6)));
                modulo7.setC7_p703_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_7)));
                modulo7.setC7_p703_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_8)));
                modulo7.setC7_p703_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_9)));
                modulo7.setC7_p703_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_10)));
                modulo7.setC7_p703_10_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_10_o)));
                modulo7.setC7_p704_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_7)));
                modulo7.setC7_p704_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_8)));
                modulo7.setC7_p704_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_9)));
                modulo7.setC7_p704_8_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_8_o)));
                modulo7.setC7_p705(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705)));
                modulo7.setC7_p706_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_1)));
                modulo7.setC7_p706_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_2)));
                modulo7.setC7_p706_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_3)));
                modulo7.setC7_p706_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_4)));
                modulo7.setC7_p706_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_5)));
                modulo7.setC7_p706_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_6)));
                modulo7.setC7_p706_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_7)));
                modulo7.setC7_p706_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_8)));
                modulo7.setC7_p706_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_9)));
                modulo7.setC7_p706_8_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_8_o)));
                modulo7.setC7_p706_6_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_6_o)));
                modulo7.setC7_p707_8_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_8_o)));
                modulo7.setC7_p709_10_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_10_o)));
                modulo7.setC7_p709_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_11)));



            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo7;
    }

    public ArrayList<Modulo7> getAllModulo7(String idVivienda) {
        ArrayList<Modulo7> modulo7s = new ArrayList<>();
        String[] whereArgs = new String[]{idVivienda};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo7,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                Modulo7 modulo7 = new Modulo7();
                modulo7.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_id)));
                modulo7.setIdInformante(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_id_informante)));
                modulo7.setIdHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_id_hogar)));
                modulo7.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_id_vivienda)));
                modulo7.setC7_p701(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p701)));
                modulo7.setC7_p702_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_1)));
                modulo7.setC7_p702_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_2)));
                modulo7.setC7_p702_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_3)));
                modulo7.setC7_p702_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_4)));
                modulo7.setC7_p702_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_5)));
                modulo7.setC7_p702_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_6)));
                modulo7.setC7_p702_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_7)));
                modulo7.setC7_p702_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_8)));
                modulo7.setC7_p702_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_9)));
                modulo7.setC7_p702_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_10)));
                modulo7.setC7_p702_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_o)));
                modulo7.setC7_p703(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703)));
                modulo7.setC7_p704_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_1)));
                modulo7.setC7_p704_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_2)));
                modulo7.setC7_p704_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_3)));
                modulo7.setC7_p704_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_4)));
                modulo7.setC7_p704_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_5)));
                modulo7.setC7_p704_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_6)));
                modulo7.setC7_p704_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_o)));
                modulo7.setC7_p705_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_1)));
                modulo7.setC7_p705_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_2)));
                modulo7.setC7_p705_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_3)));
                modulo7.setC7_p705_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_4)));
                modulo7.setC7_p705_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_5)));
                modulo7.setC7_p705_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_6)));
                modulo7.setC7_p705_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_7)));
                modulo7.setC7_p705_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705_o)));
                modulo7.setC7_p706(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706)));
                modulo7.setC7_p707_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_1)));
                modulo7.setC7_p707_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_2)));
                modulo7.setC7_p707_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_3)));
                modulo7.setC7_p707_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_4)));
                modulo7.setC7_p707_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_5)));
                modulo7.setC7_p707_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_6)));
                modulo7.setC7_p707_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_7)));
                modulo7.setC7_p707_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_8)));
                modulo7.setC7_p707_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_9)));
                modulo7.setC7_p707_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_o)));
                modulo7.setC7_p708_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p708_1)));
                modulo7.setC7_p708_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p708_2)));
                modulo7.setC7_p708_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p708_3)));
                modulo7.setC7_p708_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p708_4)));
                modulo7.setC7_p708_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p708_5)));
                modulo7.setC7_p709_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_1)));
                modulo7.setC7_p709_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_2)));
                modulo7.setC7_p709_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_3)));
                modulo7.setC7_p709_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_4)));
                modulo7.setC7_p709_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_5)));
                modulo7.setC7_p709_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_6)));
                modulo7.setC7_p709_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_7)));
                modulo7.setC7_p709_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_8)));
                modulo7.setC7_p709_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_9)));
                modulo7.setC7_p709_10a(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_10a)));
                modulo7.setC7_p709_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_10)));
                modulo7.setC7_p709_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_o)));
                modulo7.setObs_cap7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_obs_cap7)));
                modulo7.setCOB700(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_COB700)));
                //Anthony M 04/05/2021
                modulo7.setC7_p702_7_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p702_7_o)));
                modulo7.setC7_p703_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_1)));
                modulo7.setC7_p703_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_2)));
                modulo7.setC7_p703_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_3)));
                modulo7.setC7_p703_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_4)));
                modulo7.setC7_p703_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_5)));
                modulo7.setC7_p703_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_6)));
                modulo7.setC7_p703_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_7)));
                modulo7.setC7_p703_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_8)));
                modulo7.setC7_p703_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_9)));
                modulo7.setC7_p703_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_10)));
                modulo7.setC7_p703_10_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p703_10_o)));
                modulo7.setC7_p704_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_7)));
                modulo7.setC7_p704_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_8)));
                modulo7.setC7_p704_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_9)));
                modulo7.setC7_p704_8_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p704_8_o)));
                modulo7.setC7_p705(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p705)));
                modulo7.setC7_p706_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_1)));
                modulo7.setC7_p706_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_2)));
                modulo7.setC7_p706_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_3)));
                modulo7.setC7_p706_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_4)));
                modulo7.setC7_p706_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_5)));
                modulo7.setC7_p706_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_6)));
                modulo7.setC7_p706_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_7)));
                modulo7.setC7_p706_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_8)));
                modulo7.setC7_p706_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_9)));
                modulo7.setC7_p706_8_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_8_o)));
                modulo7.setC7_p706_6_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p706_6_o)));
                modulo7.setC7_p707_8_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p707_8_o)));
                modulo7.setC7_p709_10_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_10_o)));
                modulo7.setC7_p709_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo7_c7_p709_11)));
                modulo7s.add(modulo7);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return modulo7s;
    }

    public Modulo8 getModulo8(String idEncuestado) {
        Modulo8 modulo8 = null;
        String[] whereArgs = new String[]{idEncuestado};
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo8,
                    null,SQLConstantes.WHERE_CLAUSE_ID, whereArgs, null,null, null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                modulo8 = new Modulo8();
                modulo8.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_id)));
                modulo8.setIdInformante(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_id_informante)));
                modulo8.setIdHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_id_hogar)));
                modulo8.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_id_vivienda)));
                modulo8.setC8_p801(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p801)));
                modulo8.setC8_p802(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802)));
                modulo8.setC8_p802_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802_1)));//hector
                modulo8.setC8_p802_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802_2)));//hector
                modulo8.setC8_p802_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802_3)));//hector
                modulo8.setC8_p803(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p803)));
                modulo8.setC8_p804(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804)));
                modulo8.setC8_p804_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_1)));//hector
                modulo8.setC8_p804_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_2)));//hector
                modulo8.setC8_p804_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_3)));//hector
                modulo8.setC8_p804_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_4)));//hector
                modulo8.setC8_p804_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_5)));//hector
                modulo8.setC8_p804_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_6)));//hector
                modulo8.setC8_p804_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_7)));//hector
                modulo8.setC8_p804_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_8)));//hector
                modulo8.setC8_p804_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_9)));//hector
                modulo8.setC8_p804_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_10)));//hector
                modulo8.setC8_p804_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_11)));//hector
                modulo8.setC8_p804_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_12)));//hector
                modulo8.setC8_p804_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_13)));//hector
                modulo8.setC8_p804_14(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_14)));//hector
                modulo8.setC8_p804_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_o)));//hector
                modulo8.setC8_p805_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_1)));
                modulo8.setC8_p805_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_2)));
                modulo8.setC8_p805_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_3)));
                modulo8.setC8_p805_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_4)));
                modulo8.setC8_p805_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_5)));
                modulo8.setC8_p805_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_6)));//hector
                modulo8.setC8_p805_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_7)));//hector
                modulo8.setC8_p805_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_8)));//hector
                modulo8.setC8_p805_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_9)));//hector
                modulo8.setC8_p806_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p806_1)));
                modulo8.setC8_p806_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p806_2)));
                modulo8.setC8_p806_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p806_3)));
                modulo8.setC8_p806_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p806_4)));
                modulo8.setC8_p806_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p806_5)));
                modulo8.setC8_p806_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p806_6)));
                modulo8.setC8_p807(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p807)));
                modulo8.setC8_p808_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_1)));
                modulo8.setC8_p808_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_2)));
                modulo8.setC8_p808_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_3)));
                modulo8.setC8_p808_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_4)));
                modulo8.setC8_p808_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_5)));
                modulo8.setC8_p808_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_6)));
                modulo8.setC8_p808_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_7)));
                modulo8.setC8_p808_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_8)));
                modulo8.setC8_p808_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_9)));
                modulo8.setC8_p808_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_10)));
                modulo8.setC8_p808_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_11)));
                modulo8.setC8_p808_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_12)));
                modulo8.setC8_p808_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_13)));
                modulo8.setC8_p808_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_o)));
                modulo8.setC8_p809(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p809)));
                modulo8.setC8_p810_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_1)));
                modulo8.setC8_p810_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_2)));
                modulo8.setC8_p810_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_3)));
                modulo8.setC8_p810_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_4)));
                modulo8.setC8_p810_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_5)));
                modulo8.setC8_p810_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_6)));
                modulo8.setC8_p810_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_7)));
                modulo8.setC8_p810_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_8)));
                modulo8.setC8_p810_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_9)));
                modulo8.setC8_p810_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_10)));
                modulo8.setC8_p810_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_11)));
                modulo8.setC8_p810_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_12)));
                modulo8.setC8_p810_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_13)));
                modulo8.setC8_p810_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_o)));
                modulo8.setC8_p811(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p811)));
                modulo8.setC8_p812(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p812)));
                modulo8.setC8_p813_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_1)));
                modulo8.setC8_p813_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_2)));
                modulo8.setC8_p813_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_3)));
                modulo8.setC8_p813_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_4)));
                modulo8.setC8_p813_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_5)));
                modulo8.setC8_p813_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_6)));
                modulo8.setC8_p813_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_7)));
                modulo8.setC8_p813_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_8)));
                modulo8.setC8_p813_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_9)));
                modulo8.setC8_p813_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_10)));
                modulo8.setC8_p813_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_11)));
                modulo8.setC8_p813_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_12)));
                modulo8.setC8_p813_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_13)));
                modulo8.setC8_p813_14(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_14)));
                modulo8.setC8_p813_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_o)));
                modulo8.setC8_p814_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_1)));
                modulo8.setC8_p814_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_2)));
                modulo8.setC8_p814_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_3)));
                modulo8.setC8_p814_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_4)));
                modulo8.setC8_p814_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_5)));
                modulo8.setC8_p814_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_6)));
                modulo8.setC8_p814_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_7)));
                modulo8.setC8_p814_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_8)));
                modulo8.setC8_p815(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p815)));
                modulo8.setC8_p816_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_1)));
                modulo8.setC8_p816_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_2)));
                modulo8.setC8_p816_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_3)));
                modulo8.setC8_p816_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_4)));
                modulo8.setC8_p816_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_5)));
                modulo8.setC8_p816_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_6)));
                modulo8.setC8_p816_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_7)));
                modulo8.setC8_p816_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_8)));
                modulo8.setC8_p816_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_9)));
                modulo8.setC8_p816_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_10)));
                modulo8.setC8_p816_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_11)));
                modulo8.setC8_p816_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_12)));
                modulo8.setC8_p816_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_13)));
                modulo8.setC8_p816_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_o)));
                modulo8.setC8_p817(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p817)));
                modulo8.setC8_p818(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p818)));
                modulo8.setC8_p819_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_1)));
                modulo8.setC8_p819_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_2)));
                modulo8.setC8_p819_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_3)));
                modulo8.setC8_p819_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_4)));
                modulo8.setC8_p819_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_5)));
                modulo8.setC8_p819_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_6)));
                modulo8.setC8_p819_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_7)));
                modulo8.setC8_p819_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_8)));
                modulo8.setC8_p819_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_9)));
                modulo8.setC8_p819_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_10)));
                modulo8.setC8_p819_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_11)));
                modulo8.setC8_p819_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_12)));
                modulo8.setC8_p819_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_13)));
                modulo8.setC8_p819_14(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_14)));
                modulo8.setC8_p819_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_o)));
                modulo8.setC8_p820_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_1)));
                modulo8.setC8_p820_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_2)));
                modulo8.setC8_p820_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_3)));
                modulo8.setC8_p820_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_4)));
                modulo8.setC8_p820_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_5)));
                modulo8.setC8_p820_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_6)));
                modulo8.setC8_p820_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_7)));
                modulo8.setC8_p820_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_8)));
                modulo8.setC8_p820_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_9)));
                modulo8.setC8_p820_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_10)));
                modulo8.setC8_p820_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_11)));
                modulo8.setC8_p820_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_o)));
                modulo8.setC8_p821_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_1)));
                modulo8.setC8_p821_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_2)));
                modulo8.setC8_p821_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_3)));
                modulo8.setC8_p821_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_4)));
                modulo8.setC8_p821_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_5)));
                modulo8.setC8_p821_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_6)));
                modulo8.setC8_p821_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_7)));
                modulo8.setC8_p821_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_8)));
                modulo8.setC8_p822(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p822)));
                modulo8.setC8_p823_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p823_1)));
                modulo8.setC8_p823_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p823_2)));
                modulo8.setC8_p823_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p823_3)));
                modulo8.setC8_p823_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p823_4)));
                modulo8.setC8_p823_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p823_5)));
                modulo8.setC8_p823_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p823_o)));
                modulo8.setObs_cap8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_obs_cap8)));
                modulo8.setEmail(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_email)));
                modulo8.setCOB800(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_COB800)));
                //Anthony M 04/05/2021
                //modulo8.setC8_p802a_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1)));
                modulo8.setC8_p802a_1_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_1)));
                modulo8.setC8_p802a_1_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_2)));
                modulo8.setC8_p802a_1_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_3)));
                modulo8.setC8_p802a_1_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_4)));
                modulo8.setC8_p802a_1_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_5)));
                modulo8.setC8_p802a_1_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_6)));
                modulo8.setC8_p802a_1_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_7)));
                modulo8.setC8_p802a_1_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_8)));
                //modulo8.setC8_p802a_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2)));
                modulo8.setC8_p802a_2_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_1)));
                modulo8.setC8_p802a_2_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_2)));
                modulo8.setC8_p802a_2_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_3)));
                modulo8.setC8_p802a_2_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_4)));
                modulo8.setC8_p802a_2_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_5)));
                modulo8.setC8_p802a_2_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_6)));
                modulo8.setC8_p802a_2_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_7)));
                modulo8.setC8_p802a_2_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_8)));
                //modulo8.setC8_p802a_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3)));
                modulo8.setC8_p802a_3_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_1)));
                modulo8.setC8_p802a_3_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_2)));
                modulo8.setC8_p802a_3_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_3)));
                modulo8.setC8_p802a_3_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_4)));
                modulo8.setC8_p802a_3_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_5)));
                modulo8.setC8_p802a_3_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_6)));
                modulo8.setC8_p802a_3_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_7)));
                modulo8.setC8_p802a_3_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_8)));
                modulo8.setC8_p802a_1_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_o)));
                modulo8.setC8_p802a_2_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_o)));
                modulo8.setC8_p802a_3_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_o)));
            }
        }finally {
            if (cursor!=null) cursor.close();
        }
        return modulo8;
    }

    public ArrayList<Modulo8> getAllModulo8(String idVivienda) {
        ArrayList<Modulo8> modulo8s = new ArrayList<>();
        String[] whereArgs = new String[]{idVivienda};
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(SQLConstantes.tablamodulo8,
                    null,SQLConstantes.WHERE_CLAUSE_VIVIENDA_ID, whereArgs, null,null, null);
            while(cursor.moveToNext()){
                Modulo8 modulo8 = new Modulo8();
                modulo8.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_id)));
                modulo8.setIdInformante(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_id_informante)));
                modulo8.setIdHogar(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_id_hogar)));
                modulo8.setIdVivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_id_vivienda)));
                modulo8.setC8_p801(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p801)));
                modulo8.setC8_p802(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802)));
                modulo8.setC8_p802_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802_1)));//hector
                modulo8.setC8_p802_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802_2)));//hector
                modulo8.setC8_p802_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802_3)));//hector
                modulo8.setC8_p803(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p803)));
                modulo8.setC8_p804(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804)));
                modulo8.setC8_p804_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_1)));//hector
                modulo8.setC8_p804_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_2)));//hector
                modulo8.setC8_p804_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_3)));//hector
                modulo8.setC8_p804_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_4)));//hector
                modulo8.setC8_p804_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_5)));//hector
                modulo8.setC8_p804_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_6)));//hector
                modulo8.setC8_p804_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_7)));//hector
                modulo8.setC8_p804_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_8)));//hector
                modulo8.setC8_p804_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_9)));//hector
                modulo8.setC8_p804_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_10)));//hector
                modulo8.setC8_p804_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_11)));//hector
                modulo8.setC8_p804_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_12)));//hector
                modulo8.setC8_p804_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_13)));//hector
                modulo8.setC8_p804_14(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_14)));//hector
                modulo8.setC8_p804_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p804_o)));//hector
                modulo8.setC8_p805_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_1)));
                modulo8.setC8_p805_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_2)));
                modulo8.setC8_p805_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_3)));
                modulo8.setC8_p805_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_4)));
                modulo8.setC8_p805_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_5)));
                modulo8.setC8_p805_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_6)));//hector
                modulo8.setC8_p805_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_7)));//hector
                modulo8.setC8_p805_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_8)));//hector
                modulo8.setC8_p805_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p805_9)));//hector
                modulo8.setC8_p806_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p806_1)));
                modulo8.setC8_p806_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p806_2)));
                modulo8.setC8_p806_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p806_3)));
                modulo8.setC8_p806_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p806_4)));
                modulo8.setC8_p806_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p806_5)));
                modulo8.setC8_p806_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p806_6)));
                modulo8.setC8_p807(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p807)));
                modulo8.setC8_p808_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_1)));
                modulo8.setC8_p808_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_2)));
                modulo8.setC8_p808_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_3)));
                modulo8.setC8_p808_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_4)));
                modulo8.setC8_p808_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_5)));
                modulo8.setC8_p808_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_6)));
                modulo8.setC8_p808_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_7)));
                modulo8.setC8_p808_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_8)));
                modulo8.setC8_p808_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_9)));
                modulo8.setC8_p808_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_10)));
                modulo8.setC8_p808_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_11)));
                modulo8.setC8_p808_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_12)));
                modulo8.setC8_p808_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_13)));
                modulo8.setC8_p808_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p808_o)));
                modulo8.setC8_p809(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p809)));
                modulo8.setC8_p810_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_1)));
                modulo8.setC8_p810_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_2)));
                modulo8.setC8_p810_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_3)));
                modulo8.setC8_p810_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_4)));
                modulo8.setC8_p810_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_5)));
                modulo8.setC8_p810_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_6)));
                modulo8.setC8_p810_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_7)));
                modulo8.setC8_p810_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_8)));
                modulo8.setC8_p810_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_9)));
                modulo8.setC8_p810_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_10)));
                modulo8.setC8_p810_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_11)));
                modulo8.setC8_p810_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_12)));
                modulo8.setC8_p810_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_13)));
                modulo8.setC8_p810_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p810_o)));
                modulo8.setC8_p811(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p811)));
                modulo8.setC8_p812(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p812)));
                modulo8.setC8_p813_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_1)));
                modulo8.setC8_p813_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_2)));
                modulo8.setC8_p813_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_3)));
                modulo8.setC8_p813_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_4)));
                modulo8.setC8_p813_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_5)));
                modulo8.setC8_p813_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_6)));
                modulo8.setC8_p813_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_7)));
                modulo8.setC8_p813_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_8)));
                modulo8.setC8_p813_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_9)));
                modulo8.setC8_p813_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_10)));
                modulo8.setC8_p813_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_11)));
                modulo8.setC8_p813_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_12)));
                modulo8.setC8_p813_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_13)));
                modulo8.setC8_p813_14(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_14)));
                modulo8.setC8_p813_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p813_o)));
                modulo8.setC8_p814_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_1)));
                modulo8.setC8_p814_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_2)));
                modulo8.setC8_p814_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_3)));
                modulo8.setC8_p814_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_4)));
                modulo8.setC8_p814_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_5)));
                modulo8.setC8_p814_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_6)));
                modulo8.setC8_p814_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_7)));
                modulo8.setC8_p814_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p814_8)));
                modulo8.setC8_p815(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p815)));
                modulo8.setC8_p816_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_1)));
                modulo8.setC8_p816_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_2)));
                modulo8.setC8_p816_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_3)));
                modulo8.setC8_p816_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_4)));
                modulo8.setC8_p816_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_5)));
                modulo8.setC8_p816_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_6)));
                modulo8.setC8_p816_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_7)));
                modulo8.setC8_p816_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_8)));
                modulo8.setC8_p816_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_9)));
                modulo8.setC8_p816_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_10)));
                modulo8.setC8_p816_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_11)));
                modulo8.setC8_p816_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_12)));
                modulo8.setC8_p816_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_13)));
                modulo8.setC8_p816_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p816_o)));
                modulo8.setC8_p817(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p817)));
                modulo8.setC8_p818(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p818)));
                modulo8.setC8_p819_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_1)));
                modulo8.setC8_p819_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_2)));
                modulo8.setC8_p819_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_3)));
                modulo8.setC8_p819_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_4)));
                modulo8.setC8_p819_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_5)));
                modulo8.setC8_p819_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_6)));
                modulo8.setC8_p819_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_7)));
                modulo8.setC8_p819_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_8)));
                modulo8.setC8_p819_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_9)));
                modulo8.setC8_p819_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_10)));
                modulo8.setC8_p819_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_11)));
                modulo8.setC8_p819_12(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_12)));
                modulo8.setC8_p819_13(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_13)));
                modulo8.setC8_p819_14(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_14)));
                modulo8.setC8_p819_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p819_o)));
                modulo8.setC8_p820_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_1)));
                modulo8.setC8_p820_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_2)));
                modulo8.setC8_p820_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_3)));
                modulo8.setC8_p820_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_4)));
                modulo8.setC8_p820_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_5)));
                modulo8.setC8_p820_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_6)));
                modulo8.setC8_p820_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_7)));
                modulo8.setC8_p820_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_8)));
                modulo8.setC8_p820_9(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_9)));
                modulo8.setC8_p820_10(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_10)));
                modulo8.setC8_p820_11(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_11)));
                modulo8.setC8_p820_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p820_o)));
                modulo8.setC8_p821_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_1)));
                modulo8.setC8_p821_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_2)));
                modulo8.setC8_p821_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_3)));
                modulo8.setC8_p821_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_4)));
                modulo8.setC8_p821_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_5)));
                modulo8.setC8_p821_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_6)));
                modulo8.setC8_p821_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_7)));
                modulo8.setC8_p821_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p821_8)));
                modulo8.setC8_p822(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p822)));
                modulo8.setC8_p823_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p823_1)));
                modulo8.setC8_p823_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p823_2)));
                modulo8.setC8_p823_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p823_3)));
                modulo8.setC8_p823_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p823_4)));
                modulo8.setC8_p823_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p823_5)));
                modulo8.setC8_p823_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p823_o)));
                modulo8.setObs_cap8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_obs_cap8)));
                modulo8.setEmail(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_email)));
                modulo8.setCOB800(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_COB800)));
                //Anthony M 04/05/2021
                //modulo8.setC8_p802a_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1)));
                modulo8.setC8_p802a_1_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_1)));
                modulo8.setC8_p802a_1_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_2)));
                modulo8.setC8_p802a_1_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_3)));
                modulo8.setC8_p802a_1_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_4)));
                modulo8.setC8_p802a_1_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_5)));
                modulo8.setC8_p802a_1_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_6)));
                modulo8.setC8_p802a_1_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_7)));
                modulo8.setC8_p802a_1_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_8)));
                //modulo8.setC8_p802a_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2)));
                modulo8.setC8_p802a_2_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_1)));
                modulo8.setC8_p802a_2_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_2)));
                modulo8.setC8_p802a_2_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_3)));
                modulo8.setC8_p802a_2_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_4)));
                modulo8.setC8_p802a_2_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_5)));
                modulo8.setC8_p802a_2_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_6)));
                modulo8.setC8_p802a_2_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_7)));
                modulo8.setC8_p802a_2_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_8)));
                //modulo8.setC8_p802a_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3)));
                modulo8.setC8_p802a_3_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_1)));
                modulo8.setC8_p802a_3_2(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_2)));
                modulo8.setC8_p802a_3_3(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_3)));
                modulo8.setC8_p802a_3_4(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_4)));
                modulo8.setC8_p802a_3_5(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_5)));
                modulo8.setC8_p802a_3_6(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_6)));
                modulo8.setC8_p802a_3_7(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_7)));
                modulo8.setC8_p802a_3_8(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_8)));
                modulo8.setC8_p802a_1_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_1_o)));
                modulo8.setC8_p802a_2_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_2_o)));
                modulo8.setC8_p802a_3_o(cursor.getString(cursor.getColumnIndex(SQLConstantes.modulo8_c8_p802a_3_o)));
                modulo8s.add(modulo8);
            }
        }finally {
            if (cursor!=null) cursor.close();
        }

        return modulo8s;
    }

    public POJOLayout getLayouts(String idEncuestado){
        POJOLayout pojoLayout = null;
        String[] whereArgs = new String[]{idEncuestado};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablalayouts,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                pojoLayout = new POJOLayout();
                pojoLayout.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_id)));
                pojoLayout.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_id_vivienda)));
                pojoLayout.setP301(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p301)));
                pojoLayout.setP302(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p302)));
                pojoLayout.setP303(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p303)));
                pojoLayout.setP304(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p304)));
                pojoLayout.setP305(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p305)));
                pojoLayout.setP306(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p306)));
                pojoLayout.setP307(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p307)));
                pojoLayout.setP308(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p308)));
                pojoLayout.setP309(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p309)));
                pojoLayout.setP309_1(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p309_1)));
                pojoLayout.setP310(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p310)));
                pojoLayout.setP311(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p311)));
                pojoLayout.setP312(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p312)));
                pojoLayout.setP313(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p313)));
                pojoLayout.setP314(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p314)));
                pojoLayout.setP315(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p315)));
                pojoLayout.setP316(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p316)));
                pojoLayout.setP317(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p317)));
                pojoLayout.setP318(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p318)));
                pojoLayout.setP401(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p401)));
                pojoLayout.setP402(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p402)));
                pojoLayout.setP403(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p403)));
                pojoLayout.setP404(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p404)));
                pojoLayout.setP405(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p405)));
                pojoLayout.setP406(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p406)));
                pojoLayout.setP407(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p407)));
                pojoLayout.setP408(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p408)));
                pojoLayout.setP409(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p409)));
                pojoLayout.setP410(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p410)));
                pojoLayout.setP411(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p411)));
                pojoLayout.setP412(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p412)));
                pojoLayout.setP413(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p413)));
                pojoLayout.setP414(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p414)));
                pojoLayout.setP415(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p415)));
                pojoLayout.setP416(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p416)));
                pojoLayout.setP501(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p501)));
                pojoLayout.setP502(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p502)));
                pojoLayout.setP503(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p503)));
                pojoLayout.setP504(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p504)));
                pojoLayout.setP505(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p505)));
                pojoLayout.setP506(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p506)));
                pojoLayout.setP507(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p507)));
                pojoLayout.setP508(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p508)));
                pojoLayout.setP509(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p509)));
                pojoLayout.setP510(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p510)));
                pojoLayout.setP511(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p511)));
                pojoLayout.setP512(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p512)));
                pojoLayout.setP513(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p513)));
                pojoLayout.setP601(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p601)));
                pojoLayout.setP602(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p602)));
                pojoLayout.setP603(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p603)));
                pojoLayout.setP604(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p604)));
                pojoLayout.setP605(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p605)));
                pojoLayout.setP606(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p606)));
                pojoLayout.setP607(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p607)));
                pojoLayout.setP608(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p608)));
                pojoLayout.setP609(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p609)));
                pojoLayout.setP610(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p610)));
                pojoLayout.setP611(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p611)));
                pojoLayout.setP611a(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p611a)));
                pojoLayout.setP611b(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p611b)));
                pojoLayout.setP612(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p612)));
                pojoLayout.setP613(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p613)));
                pojoLayout.setP614(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p614)));
                pojoLayout.setP615(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p615)));
                pojoLayout.setP616(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p616)));
                pojoLayout.setP617(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p617)));
                pojoLayout.setP618(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p618)));
                pojoLayout.setP619(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p619)));
                pojoLayout.setP620(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p620)));
                pojoLayout.setP621(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p621)));
                pojoLayout.setP622(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p622)));
                pojoLayout.setP623(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p623)));
                pojoLayout.setP624(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p624)));
                pojoLayout.setP625(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p625)));
                pojoLayout.setP626(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p626)));
                pojoLayout.setP627(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p627)));
                pojoLayout.setP628(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p628)));
                pojoLayout.setP629(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p629)));
                pojoLayout.setP630(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p630)));
                pojoLayout.setP701(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p701)));
                pojoLayout.setP702(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p702)));
                pojoLayout.setP703(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p703)));
                pojoLayout.setP704(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p704)));
                pojoLayout.setP705(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p705)));
                pojoLayout.setP706(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p706)));
                pojoLayout.setP707(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p707)));
                pojoLayout.setP708(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p708)));
                pojoLayout.setP709(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p709)));
                pojoLayout.setP801(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p801)));
                pojoLayout.setP802(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p802)));
                pojoLayout.setP803(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p803)));
                pojoLayout.setP804(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p804)));
                pojoLayout.setP805(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p805)));
                pojoLayout.setP806(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p806)));
                pojoLayout.setP807(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p807)));
                pojoLayout.setP808(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p808)));
                pojoLayout.setP809(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p809)));
                pojoLayout.setP810(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p810)));
                pojoLayout.setP811(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p811)));
                pojoLayout.setP812(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p812)));
                pojoLayout.setP813(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p813)));
                pojoLayout.setP814(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p814)));
                pojoLayout.setP815(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p815)));
                pojoLayout.setP816(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p816)));
                pojoLayout.setP817(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p817)));
                pojoLayout.setP818(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p818)));
                pojoLayout.setP819(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p819)));
                pojoLayout.setP820(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p820)));
                pojoLayout.setP821(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p821)));
                pojoLayout.setP822(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p822)));
                pojoLayout.setP823(cursor.getString(cursor.getColumnIndex(SQLConstantes.layouts_p823)));

            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return pojoLayout;
    }

    public CoberturaFragment getCoberturaFragments(String idEncuestado){
        CoberturaFragment coberturaFragment = null;
        String[] whereArgs = new String[]{idEncuestado};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablacoberturafragments,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                coberturaFragment = new CoberturaFragment(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_id)));
                coberturaFragment.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_id_vivienda)));
                coberturaFragment.setCp301p305(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp301p305)));
                coberturaFragment.setCp306p308(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp306p308)));
                coberturaFragment.setCp309(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp309)));
                coberturaFragment.setCp310p312(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp310p312)));
                coberturaFragment.setCp313p317(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp313p317)));
                coberturaFragment.setCp318(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp318)));
                coberturaFragment.setCp401p404(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp401p404)));
                coberturaFragment.setCp405p407(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp405p407)));
                coberturaFragment.setCp408p410(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp408p410)));
                coberturaFragment.setCp411p416(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp411p416)));
                coberturaFragment.setCp501p505(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp501p505)));
                coberturaFragment.setCp506p507(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp506p507)));
                coberturaFragment.setCp508p511(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp508p511)));
                coberturaFragment.setCp512p513(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp512p513)));
                coberturaFragment.setCp601p604(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp601p604)));
                coberturaFragment.setCp605p608(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp605p608)));
                coberturaFragment.setCp609p612(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp609p612)));
                coberturaFragment.setCp613p617(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp613p617)));
                coberturaFragment.setCp618p621(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp618p621)));
                coberturaFragment.setCp622p625(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp622p625)));
                coberturaFragment.setCp626p629(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp626p629)));
                coberturaFragment.setCp630(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp630)));
                coberturaFragment.setCp701p705(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp701p705)));
                coberturaFragment.setCp706p709(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp706p709)));
                coberturaFragment.setCp801p804(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp801p804)));
                coberturaFragment.setCp805p808(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp805p808)));
                coberturaFragment.setCp809p812(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp809p812)));
                coberturaFragment.setCp813p816(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp813p816)));
                coberturaFragment.setCp817p820(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp817p820)));
                coberturaFragment.setCp821p823(cursor.getString(cursor.getColumnIndex(SQLConstantes.cobertura_fragments_cp821p823)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return coberturaFragment;
    }

    public POJOFragment getFragmentsLayouts(String idEncuestado){
        POJOFragment pojoFragment = null;
        String[] whereArgs = new String[]{idEncuestado};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafragments,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                pojoFragment = new POJOFragment(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_id)));
                pojoFragment.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_id_vivienda)));
                pojoFragment.setP301p305(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p301p305)));
                pojoFragment.setP306p308(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p306p308)));
                pojoFragment.setP309(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p309)));
                pojoFragment.setP310p312(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p310p312)));
                pojoFragment.setP313p317(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p313p317)));
                pojoFragment.setP318(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p318)));
                pojoFragment.setP401p404(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p401p404)));
                pojoFragment.setP405p407(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p405p407)));
                pojoFragment.setP408p410(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p408p410)));
                pojoFragment.setP411p416(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p411p416)));
                pojoFragment.setP501p505(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p501p505)));
                pojoFragment.setP506p507(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p506p507)));
                pojoFragment.setP508p511(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p508p511)));
                pojoFragment.setP512p513(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p512p513)));
                pojoFragment.setP601p604(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p601p604)));
                pojoFragment.setP605p608(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p605p608)));
                pojoFragment.setP609p612(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p609p612)));
                pojoFragment.setP613p617(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p613p617)));
                pojoFragment.setP618p621(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p618p621)));
                pojoFragment.setP622p625(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p622p625)));
                pojoFragment.setP626p629(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p626p629)));
                pojoFragment.setP630(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p630)));
                pojoFragment.setP701p705(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p701p705)));
                pojoFragment.setP706p709(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p706p709)));
                pojoFragment.setP801p804(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p801p804)));
                pojoFragment.setP805p808(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p805p808)));
                pojoFragment.setP809p812(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p809p812)));
                pojoFragment.setP813p816(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p813p816)));
                pojoFragment.setP817p820(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p817p820)));
                pojoFragment.setP821p823(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_p821p823)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return pojoFragment;
    }

    public POJOFragmentVivienda getFragmentsVivienda(String idEncuestado){
        POJOFragmentVivienda pojoFragmentVivienda = null;
        String[] whereArgs = new String[]{idEncuestado};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafragmentsvivienda,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                pojoFragmentVivienda = new POJOFragmentVivienda();
                pojoFragmentVivienda.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_vivienda_id)));
                pojoFragmentVivienda.setCaratula(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_vivienda_caratula)));
                pojoFragmentVivienda.setHogares(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_vivienda_hogares)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return pojoFragmentVivienda;
    }

    public POJOFragmentHogar getFragmentsHogar(String idEncuestado){
        POJOFragmentHogar pojoFragmentHogar = null;
        String[] whereArgs = new String[]{idEncuestado};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablafragmentshogar,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                pojoFragmentHogar = new POJOFragmentHogar();
                pojoFragmentHogar.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_hogar_id)));
                pojoFragmentHogar.setId_vivienda(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_id_vivienda)));
                pojoFragmentHogar.setVisitas_encuestador(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_hogar_visitas_encuestador)));
                pojoFragmentHogar.setVisitas_supervisor(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_hogar_visitas_supervisor)));
                pojoFragmentHogar.setFuncionarios(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_hogar_funcionarios)));
                pojoFragmentHogar.setP101p107(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_hogar_p101p107)));
                pojoFragmentHogar.setP108p113(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_hogar_p108p113)));
                pojoFragmentHogar.setP201p207(cursor.getString(cursor.getColumnIndex(SQLConstantes.fragments_hogar_p201p207)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return pojoFragmentHogar;
    }

    public void borrarAllData(String tabla){
        sqLiteDatabase.execSQL("delete from "+ tabla);
    }

    public void deleteTable(String tabla,String id){
        String[] whereArgs = new String[]{id};
        sqLiteDatabase.delete(tabla,SQLConstantes.WHERE_CLAUSE_ID_ENCUESTADO,whereArgs);
    }

    public void actualizar_municipio_error(){
        Log.e("entro", "actualizar_municipio_error: "+6 );
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id","1611");
        contentValues.put("cod_municipio","11");
        contentValues.put("nom_municipio","11.Villalba");
        contentValues.put("num_municipio","11");
        contentValues.put("cod_estado","16");
        contentValues.put("nom_estado","Estado Nueva Esparta");
        sqLiteDatabase.update("municipios",contentValues,"cod_estado='16' and nom_municipio='01.Villalba'",null);
    }

    public Pais getPais(String id){
        Pais pais = null;
        String[] whereArgs = new String[]{id};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablapaises,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                pais = new Pais();
                pais.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.paises_id)));
                pais.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.paises_numero)));
                pais.setNombre(cursor.getString(cursor.getColumnIndex(SQLConstantes.paises_nombre)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return pais;
    }

    public Estado getEstado(String id){
        Estado estado = null;
        String[] whereArgs = new String[]{id};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaestados,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                estado = new Estado();
                estado.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.estado_id)));
                estado.setNumero(cursor.getString(cursor.getColumnIndex(SQLConstantes.estado_numero)));
                estado.setNombre(cursor.getString(cursor.getColumnIndex(SQLConstantes.estado_nombre)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return estado;
    }

    public Municipio getMunicipio(String id){
        Municipio municipio = null;
        String[] whereArgs = new String[]{id};
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamunicipios,
                    null,SQLConstantes.WHERE_CLAUSE_ID,whereArgs,null,null,null);
            if(cursor.getCount() == 1){
                cursor.moveToFirst();
                municipio = new Municipio();
                municipio.set_id(cursor.getString(cursor.getColumnIndex(SQLConstantes.municipios_id)));
                municipio.setCod_municipio(cursor.getString(cursor.getColumnIndex(SQLConstantes.municipios_cod_municipio)));
                municipio.setNom_municipio(cursor.getString(cursor.getColumnIndex(SQLConstantes.municipios_nom_municipio)));
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return municipio;
    }

    public ArrayList<String> getListaPaises(){
        ArrayList<String> lista= new ArrayList<>();
        lista.add("--- SELECCIONE PAIS ---");
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablapaises,null,null,null,null,null,null);
            while (cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex(SQLConstantes.paises_id));
                String nombre = cursor.getString(cursor.getColumnIndex(SQLConstantes.paises_nombre));
                String item = id + "-" + nombre;
                lista.add(item);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return lista;
    }

    public ArrayList<String> getListaEstados(){
        ArrayList<String> lista= new ArrayList<>();
        lista.add("--- SELECCIONE ESTADOS ---");
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablaestados,null,null,null,null,null,null);
            while (cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex(SQLConstantes.estado_id));
                String nombre = cursor.getString(cursor.getColumnIndex(SQLConstantes.estado_nombre));
                String item = id + "-" + nombre;
                lista.add(item);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return lista;
    }

    public ArrayList<String> getListaMunicipios(String codEstado){
        ArrayList<String> lista= new ArrayList<>();
        String[] whereArgs = new String[]{String.valueOf(codEstado)};
        lista.add("--- SELECCIONE MUNICIPIOS ---");
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(SQLConstantes.tablamunicipios,null,SQLConstantes.WHERE_CLAUSE_ESTADO_COD,whereArgs,null,null,null);
            while (cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex(SQLConstantes.municipios_cod_municipio));
                String nombre = cursor.getString(cursor.getColumnIndex(SQLConstantes.municipios_nom_municipio));
                String item = id + "-" + nombre;
                lista.add(item);
            }
        }finally{
            if(cursor != null) cursor.close();
        }
        return lista;
    }
}