package com.abreu.roby.app.ent;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "categoria_gasto")
public class Categoria_gasto {

    @DatabaseField(columnName = "id", generatedId=true)
    private int id;

    @DatabaseField(columnName = "categoria_gasto")
    private String categoria_gasto;

    public Categoria_gasto(){}
    public Categoria_gasto(int id, String categoria_gasto) {
        this.id = id;
        this.categoria_gasto = categoria_gasto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria_gasto() {
        return categoria_gasto;
    }

    public void setCategoria_gasto(String categoria_gasto) {
        this.categoria_gasto = categoria_gasto;
    }
    public String toString(){
        return getCategoria_gasto();
    }
}
