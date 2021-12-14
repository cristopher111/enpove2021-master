package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class Modulo1V {
    private String _id;
    private String c1_p101;
    private String c1_p101_o;
    private String c1_p102;
    private String c1_p102_o;
    private String c1_p103;
    private String c1_p103_o;
    private String c1_p104;
    private String c1_p104_o;
    private String c1_p105;
    private String c1_p106;
    //private String c1_p107;
    private String COB100A;

    public Modulo1V() {
        _id="";
        c1_p101="";
        c1_p101_o="";
        c1_p102="";
        c1_p102_o="";
        c1_p103="";
        c1_p103_o="";
        c1_p104="";
        c1_p104_o="";
        c1_p105="";
        c1_p106="";
        //c1_p107="";
        COB100A = "2";
    }

    public String getCOB100A() {
        return COB100A;
    }

    public void setCOB100A(String COB100A) {
        this.COB100A = COB100A;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getC1_p101() {
        return c1_p101;
    }

    public void setC1_p101(String c1_p101) {
        this.c1_p101 = c1_p101;
    }

    public String getC1_p101_o() {
        return c1_p101_o;
    }

    public void setC1_p101_o(String c1_p101_o) {
        this.c1_p101_o = c1_p101_o;
    }

    public String getC1_p102() {
        return c1_p102;
    }

    public void setC1_p102(String c1_p102) {
        this.c1_p102 = c1_p102;
    }

    public String getC1_p102_o() {
        return c1_p102_o;
    }

    public void setC1_p102_o(String c1_p102_o) {
        this.c1_p102_o = c1_p102_o;
    }

    public String getC1_p103() {
        return c1_p103;
    }

    public void setC1_p103(String c1_p103) {
        this.c1_p103 = c1_p103;
    }

    public String getC1_p103_o() {
        return c1_p103_o;
    }

    public void setC1_p103_o(String c1_p103_o) {
        this.c1_p103_o = c1_p103_o;
    }

    public String getC1_p104() {
        return c1_p104;
    }

    public void setC1_p104(String c1_p104) {
        this.c1_p104 = c1_p104;
    }

    public String getC1_p104_o() {
        return c1_p104_o;
    }

    public void setC1_p104_o(String c1_p104_o) {
        this.c1_p104_o = c1_p104_o;
    }

    public String getC1_p105() {
        return c1_p105;
    }

    public void setC1_p105(String c1_p105) {
        this.c1_p105 = c1_p105;
    }

    public String getC1_p106() {
        return c1_p106;
    }

    public void setC1_p106(String c1_p106) {
        this.c1_p106 = c1_p106;
    }

//    public String getC1_p107() {
//        return c1_p107;
//    }
//
//    public void setC1_p107(String c1_p107) {
//        this.c1_p107 = c1_p107;
//    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo1_v_id,_id);
        contentValues.put(SQLConstantes.modulo1_v_c1_p101,c1_p101);
        contentValues.put(SQLConstantes.modulo1_v_c1_p101_o,c1_p101_o);
        contentValues.put(SQLConstantes.modulo1_v_c1_p102,c1_p102);
        contentValues.put(SQLConstantes.modulo1_v_c1_p102_o,c1_p102_o);
        contentValues.put(SQLConstantes.modulo1_v_c1_p103,c1_p103);
        contentValues.put(SQLConstantes.modulo1_v_c1_p103_o,c1_p103_o);
        contentValues.put(SQLConstantes.modulo1_v_c1_p104,c1_p104);
        contentValues.put(SQLConstantes.modulo1_v_c1_p104_o,c1_p104_o);
        contentValues.put(SQLConstantes.modulo1_v_c1_p105,c1_p105);
        contentValues.put(SQLConstantes.modulo1_v_c1_p106,c1_p106);
        //contentValues.put(SQLConstantes.modulo1_v_c1_p107,c1_p107);
        contentValues.put(SQLConstantes.modulo1_v_COB100A,COB100A);
        return contentValues;
    }
}
