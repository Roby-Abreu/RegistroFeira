package com.abreu.roby.app.ent;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;


@DatabaseTable(tableName = "feira")
public class Feira {

    public static final String FIELD_ID    = "id";
    public static final String FIELD_LOCAL   = "local";
    public static final String FIELD_DATA   = "data";
    public static final String FIELD_TVEDIDO   = "total_vendido";
    public static final String FIELD_TGASTO   = "total_gasto";
    public static final String FIELD_STATUS   = "status";
    public static final String FIELD_USUARIO   = "usuario";

    @DatabaseField(columnName = FIELD_ID, generatedId=true)
    private int id;

    @DatabaseField(columnName = FIELD_LOCAL)
    private String local;

    @DatabaseField(columnName = FIELD_DATA)
    private String data;

    @DatabaseField(columnName = FIELD_STATUS)
    private boolean status;

    @DatabaseField(columnName = FIELD_TVEDIDO)
    private double totalVendido;

    @DatabaseField(columnName = FIELD_TGASTO)
    private double totalGasto;

    @DatabaseField(columnName = FIELD_USUARIO, foreign = true)
    private Usuario usuario;

    public Feira(){}
    public Feira(int id, String local, String data, boolean status, double totalVendido, double totalGasto, Usuario usuario) {
        this.id = id;
        this.local = local;
        this.data = data;
        this.status = status;
        this.totalVendido = totalVendido;
        this.totalGasto = totalGasto;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(double totalVendido) {
        this.totalVendido = totalVendido;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }

    public String toString(){
        return "Feira: "+getLocal()+"\n"+"Data: "+getData()+"\n"+"Usuario"+getUsuario();
    }

}
