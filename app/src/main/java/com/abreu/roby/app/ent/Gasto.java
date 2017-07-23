package com.abreu.roby.app.ent;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "gasto")
public class Gasto {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField(columnName = "categoria_gasto", foreign = true)
    private Categoria_gasto cat_gasto;

    @DatabaseField(columnName = "valor")
    private double valor;

    @DatabaseField(columnName = "id_feira", foreign = true)
    private Feira feira;

    public Gasto(){}
    public Gasto(int id, Categoria_gasto cat_gasto, double valor, Feira feira) {
        this.id = id;
        this.cat_gasto = cat_gasto;
        this.valor = valor;
        this.feira = feira;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categoria_gasto getCat_gasto() {
        return cat_gasto;
    }

    public void setCat_gasto(Categoria_gasto cat_gasto) {
        this.cat_gasto = cat_gasto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Feira getFeira() {
        return feira;
    }

    public void setFeira(Feira feira) {
        this.feira = feira;
    }
}
