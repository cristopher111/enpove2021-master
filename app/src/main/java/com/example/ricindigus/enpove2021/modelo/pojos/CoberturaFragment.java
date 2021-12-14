package com.example.ricindigus.enpove2021.modelo.pojos;

import android.content.ContentValues;

import com.example.ricindigus.enpove2021.modelo.SQLConstantes;

public class CoberturaFragment {
    private String _id;
    private String id_vivienda;
    private String cp301p305;
    private String cp306p308;
    private String cp309;
    private String cp310p312;
    private String cp313p317;
    private String cp318;
    private String cp401p404;
    private String cp405p407;
    private String cp408p410;
    private String cp411p416;
    private String cp501p505;
    private String cp506p507;
    private String cp508p511;
    private String cp512p513;
    private String cp601p604;
    private String cp605p608;
    private String cp609p612;
    private String cp613p617;
    private String cp618p621;
    private String cp622p625;
    private String cp626p629;
    private String cp630;
    private String cp701p705;
    private String cp706p709;
    private String cp801p804;
    private String cp805p808;
    private String cp809p812;
    private String cp813p816;
    private String cp817p820;
    private String cp821p823;

    public CoberturaFragment(String _id) {
        this._id = _id;
        this.id_vivienda = "";
        this.cp301p305 = "0";
        this.cp306p308 = "0";
        this.cp309 = "0";
        this.cp310p312 = "0";
        this.cp313p317 = "0";
        this.cp318 = "0";
        this.cp401p404 = "0";
        this.cp405p407 = "0";
        this.cp408p410 = "0";
        this.cp411p416 = "0";
        this.cp501p505 = "0";
        this.cp506p507 = "0";
        this.cp508p511 = "0";
        this.cp512p513 = "0";
        this.cp601p604 = "0";
        this.cp605p608 = "0";
        this.cp609p612 = "0";
        this.cp613p617 = "0";
        this.cp618p621 = "0";
        this.cp622p625 = "0";
        this.cp626p629 = "0";
        this.cp630 = "0";
        this.cp701p705 = "0";
        this.cp706p709 = "0";
        this.cp801p804 = "0";
        this.cp805p808 = "0";
        this.cp809p812 = "0";
        this.cp813p816 = "0";
        this.cp817p820 = "0";
        this.cp821p823 = "0";
    }

    public CoberturaFragment() {
        this._id = "";
        this.id_vivienda = "";
        this.cp301p305 = "0";
        this.cp306p308 = "0";
        this.cp309 = "0";
        this.cp310p312 = "0";
        this.cp313p317 = "0";
        this.cp318 = "0";
        this.cp401p404 = "0";
        this.cp405p407 = "0";
        this.cp408p410 = "0";
        this.cp411p416 = "0";
        this.cp501p505 = "0";
        this.cp506p507 = "0";
        this.cp508p511 = "0";
        this.cp512p513 = "0";
        this.cp601p604 = "0";
        this.cp605p608 = "0";
        this.cp609p612 = "0";
        this.cp613p617 = "0";
        this.cp618p621 = "0";
        this.cp622p625 = "0";
        this.cp626p629 = "0";
        this.cp630 = "0";
        this.cp701p705 = "0";
        this.cp706p709 = "0";
        this.cp801p804 = "0";
        this.cp805p808 = "0";
        this.cp809p812 = "0";
        this.cp813p816 = "0";
        this.cp817p820 = "0";
        this.cp821p823 = "0";
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId_vivienda() {
        return id_vivienda;
    }

    public void setId_vivienda(String id_vivienda) {
        this.id_vivienda = id_vivienda;
    }

    public String getCp301p305() {
        return cp301p305;
    }

    public void setCp301p305(String cp301p305) {
        this.cp301p305 = cp301p305;
    }

    public String getCp306p308() {
        return cp306p308;
    }

    public void setCp306p308(String cp306p308) {
        this.cp306p308 = cp306p308;
    }

    public String getCp309() {
        return cp309;
    }

    public void setCp309(String cp309) {
        this.cp309 = cp309;
    }

    public String getCp310p312() {
        return cp310p312;
    }

    public void setCp310p312(String cp310p312) {
        this.cp310p312 = cp310p312;
    }

    public String getCp313p317() {
        return cp313p317;
    }

    public void setCp313p317(String cp313p317) {
        this.cp313p317 = cp313p317;
    }

    public String getCp318() {
        return cp318;
    }

    public void setCp318(String cp318) {
        this.cp318 = cp318;
    }

    public String getCp401p404() {
        return cp401p404;
    }

    public void setCp401p404(String cp401p404) {
        this.cp401p404 = cp401p404;
    }

    public String getCp405p407() {
        return cp405p407;
    }

    public void setCp405p407(String cp405p407) {
        this.cp405p407 = cp405p407;
    }

    public String getCp408p410() {
        return cp408p410;
    }

    public void setCp408p410(String cp408p410) {
        this.cp408p410 = cp408p410;
    }

    public String getCp411p416() {
        return cp411p416;
    }

    public void setCp411p416(String cp411p416) {
        this.cp411p416 = cp411p416;
    }

    public String getCp501p505() {
        return cp501p505;
    }

    public void setCp501p505(String cp501p505) {
        this.cp501p505 = cp501p505;
    }

    public String getCp506p507() {
        return cp506p507;
    }

    public void setCp506p507(String cp506p507) {
        this.cp506p507 = cp506p507;
    }

    public String getCp508p511() {
        return cp508p511;
    }

    public void setCp508p511(String cp508p511) {
        this.cp508p511 = cp508p511;
    }

    public String getCp512p513() {
        return cp512p513;
    }

    public void setCp512p513(String cp512p513) {
        this.cp512p513 = cp512p513;
    }

    public String getCp601p604() {
        return cp601p604;
    }

    public void setCp601p604(String cp601p604) {
        this.cp601p604 = cp601p604;
    }

    public String getCp605p608() {
        return cp605p608;
    }

    public void setCp605p608(String cp605p608) {
        this.cp605p608 = cp605p608;
    }

    public String getCp609p612() {
        return cp609p612;
    }

    public void setCp609p612(String cp609p612) {
        this.cp609p612 = cp609p612;
    }

    public String getCp613p617() {
        return cp613p617;
    }

    public void setCp613p617(String cp613p617) {
        this.cp613p617 = cp613p617;
    }

    public String getCp618p621() {
        return cp618p621;
    }

    public void setCp618p621(String cp618p621) {
        this.cp618p621 = cp618p621;
    }

    public String getCp622p625() {
        return cp622p625;
    }

    public void setCp622p625(String cp622p625) {
        this.cp622p625 = cp622p625;
    }

    public String getCp626p629() {
        return cp626p629;
    }

    public void setCp626p629(String cp626p629) {
        this.cp626p629 = cp626p629;
    }

    public String getCp630() {
        return cp630;
    }

    public void setCp630(String cp630) {
        this.cp630 = cp630;
    }

    public String getCp701p705() {
        return cp701p705;
    }

    public void setCp701p705(String cp701p705) {
        this.cp701p705 = cp701p705;
    }

    public String getCp706p709() {
        return cp706p709;
    }

    public void setCp706p709(String cp706p709) {
        this.cp706p709 = cp706p709;
    }

    public String getCp801p804() {
        return cp801p804;
    }

    public void setCp801p804(String cp801p804) {
        this.cp801p804 = cp801p804;
    }

    public String getCp805p808() {
        return cp805p808;
    }

    public void setCp805p808(String cp805p808) {
        this.cp805p808 = cp805p808;
    }

    public String getCp809p812() {
        return cp809p812;
    }

    public void setCp809p812(String cp809p812) {
        this.cp809p812 = cp809p812;
    }

    public String getCp813p816() {
        return cp813p816;
    }

    public void setCp813p816(String cp813p816) {
        this.cp813p816 = cp813p816;
    }

    public String getCp817p820() {
        return cp817p820;
    }

    public void setCp817p820(String cp817p820) {
        this.cp817p820 = cp817p820;
    }

    public String getCp821p823() {
        return cp821p823;
    }

    public void setCp821p823(String cp821p823) {
        this.cp821p823 = cp821p823;
    }

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.cobertura_fragments_id ,_id);
        contentValues.put(SQLConstantes.cobertura_fragments_id_vivienda ,id_vivienda);
        contentValues.put(SQLConstantes.cobertura_fragments_cp301p305 ,cp301p305);
        contentValues.put(SQLConstantes.cobertura_fragments_cp306p308 ,cp306p308);
        contentValues.put(SQLConstantes.cobertura_fragments_cp309 ,cp309);
        contentValues.put(SQLConstantes.cobertura_fragments_cp310p312 ,cp310p312);
        contentValues.put(SQLConstantes.cobertura_fragments_cp313p317 ,cp313p317);
        contentValues.put(SQLConstantes.cobertura_fragments_cp318 ,cp318);
        contentValues.put(SQLConstantes.cobertura_fragments_cp401p404 ,cp401p404);
        contentValues.put(SQLConstantes.cobertura_fragments_cp405p407 ,cp405p407);
        contentValues.put(SQLConstantes.cobertura_fragments_cp408p410 ,cp408p410);
        contentValues.put(SQLConstantes.cobertura_fragments_cp411p416 ,cp411p416);
        contentValues.put(SQLConstantes.cobertura_fragments_cp501p505 ,cp501p505);
        contentValues.put(SQLConstantes.cobertura_fragments_cp506p507 ,cp506p507);
        contentValues.put(SQLConstantes.cobertura_fragments_cp508p511 ,cp508p511);
        contentValues.put(SQLConstantes.cobertura_fragments_cp512p513 ,cp512p513);
        contentValues.put(SQLConstantes.cobertura_fragments_cp601p604 ,cp601p604);
        contentValues.put(SQLConstantes.cobertura_fragments_cp605p608 ,cp605p608);
        contentValues.put(SQLConstantes.cobertura_fragments_cp609p612 ,cp609p612);
        contentValues.put(SQLConstantes.cobertura_fragments_cp613p617, cp613p617);
        contentValues.put(SQLConstantes.cobertura_fragments_cp618p621, cp618p621);
        contentValues.put(SQLConstantes.cobertura_fragments_cp622p625, cp622p625);
        contentValues.put(SQLConstantes.cobertura_fragments_cp626p629 ,cp626p629);
        contentValues.put(SQLConstantes.cobertura_fragments_cp630 ,cp630);
        contentValues.put(SQLConstantes.cobertura_fragments_cp701p705 ,cp701p705);
        contentValues.put(SQLConstantes.cobertura_fragments_cp706p709 ,cp706p709);
        contentValues.put(SQLConstantes.cobertura_fragments_cp801p804 ,cp801p804);
        contentValues.put(SQLConstantes.cobertura_fragments_cp805p808 ,cp805p808);
        contentValues.put(SQLConstantes.cobertura_fragments_cp809p812 ,cp809p812);
        contentValues.put(SQLConstantes.cobertura_fragments_cp813p816 ,cp813p816);
        contentValues.put(SQLConstantes.cobertura_fragments_cp817p820 ,cp817p820);
        contentValues.put(SQLConstantes.cobertura_fragments_cp821p823 ,cp821p823);
        return contentValues;
    }
}
