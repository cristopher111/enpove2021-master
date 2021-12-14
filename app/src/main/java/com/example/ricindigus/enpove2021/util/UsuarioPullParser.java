package com.example.ricindigus.enpove2021.util;

import android.content.Context;

import com.example.ricindigus.enpove2021.modelo.pojos.Usuario;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * Created by RICARDO on 10/08/2017.
 */

public class UsuarioPullParser {
    private static final String _ID = "_ID";
    private static final String USUARIO = "USUARIO";
    private static final String CLAVE = "CLAVE";
    private static final String CARGO_ID = "CARGO_ID";
    private static final String DNI = "DNI";
    private static final String NOMBRE = "NOMBRE";
    private static final String TELEFONO = "TELEFONO";
    private static final String NRO_ENCUESTADOR = "NRO_ENCUESTADOR";
    private static final String AREA_TRABAJO = "AREA_TRABAJO";
    private static final String SEDE = "SEDE";



    private Usuario currentUsuario = null;
    private String currentTag = null;
    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    public ArrayList<Usuario> parseXML(Context context){
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            try {
                InputStream stream = context.getAssets().open("marcousuario.xml");
                xpp.setInput(stream,null);

                int eventType = xpp.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    if(eventType == XmlPullParser.START_TAG){
                        handleStarTag(xpp.getName());
                    }else if(eventType == XmlPullParser.END_TAG){
                        currentTag = null;
                    }else if(eventType == XmlPullParser.TEXT){
                        handleText(xpp.getText());
                    }
                    eventType = xpp.next();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public ArrayList<Usuario> parseXML(Context context, String archivo){
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            FileInputStream fis = null;
            try {
                File file = new File(archivo);
                FileInputStream fileInputStream = new FileInputStream(file);
                fis = new FileInputStream(file);
                xpp.setInput(fis, null);
                int eventType = xpp.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    if(eventType == XmlPullParser.START_TAG){
                        handleStarTag(xpp.getName());
                    }else if(eventType == XmlPullParser.END_TAG){
                        currentTag = null;
                    }else if(eventType == XmlPullParser.TEXT){
                        handleText(xpp.getText());
                    }
                    eventType = xpp.next();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    private void handleText(String text) {
        String xmlText = text;
        if(currentUsuario!= null && currentTag != null){
            switch (currentTag){
                case _ID             : currentUsuario.set_id(xmlText);break;
                case USUARIO         : currentUsuario.setUsuario(xmlText);break;
                case CLAVE           : currentUsuario.setClave(xmlText);break;
                case DNI             : currentUsuario.setDni(xmlText);break;
                case NOMBRE          : currentUsuario.setNombre(xmlText);break;
                case CARGO_ID        : currentUsuario.setCargo_id(xmlText);break;
                case TELEFONO        : currentUsuario.setTelefono(xmlText);break;
                case NRO_ENCUESTADOR : currentUsuario.setNro_encuestador(xmlText);break;
                case AREA_TRABAJO    : currentUsuario.setArea_trabajo(xmlText);break;
                case SEDE            : currentUsuario.setSede(xmlText);break;
            }
        }
    }

    private void handleStarTag(String name) {
        if(name.equals("ITEM_USUARIO")){
            currentUsuario = new Usuario();
            usuarios.add(currentUsuario);
        }else{
            currentTag = name;
        }
    }
}
