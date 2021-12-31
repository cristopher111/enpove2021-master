package com.example.ricindigus.enpove2021.util;

import android.app.Application;

import com.example.ricindigus.enpove2021.modelo.pojos.Residente;

import java.util.ArrayList;

public class AppConfiguration extends Application {
    private ArrayList<Residente> residentesedad;

    public ArrayList<Residente> getResidentesedad() {
        return residentesedad;
    }

    public void setResidentesedad(ArrayList<Residente> residentesedad) {
        this.residentesedad = residentesedad;
    }
}
