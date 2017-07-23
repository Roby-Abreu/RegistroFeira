package com.abreu.roby.app.ent;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "venda")
public class Venda {
    public static final String FIELD_ID    = "id";
    public static final String FIELD_VALOR   = "valor";
    public static final String FIELD_FOR_PAG   = "forma_pagamento";
    public static final String FIELD_FEIRA   = "feira_id";

    @DatabaseField(columnName = FIELD_ID, generatedId=true)
    private int id;
    @DatabaseField(columnName = FIELD_VALOR)
    private double valor;
    @DatabaseField(columnName = FIELD_FOR_PAG, foreign = true)
    private Forma_Pagamento form_pag;
    @DatabaseField(columnName = FIELD_FEIRA, foreign = true)
    private Feira feira;

    public Venda(){}

    public Venda(int id, double valor, Forma_Pagamento form_pag, Feira feira) {
        this.id = id;
        this.valor = valor;
        this.form_pag = form_pag;
        this.feira = feira;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Forma_Pagamento getForm_pag() {
        return form_pag;
    }

    public void setForm_pag(Forma_Pagamento form_pag) {
        this.form_pag = form_pag;
    }

    public Feira getFeira() {
        return feira;
    }

    public void setFeira(Feira feira) {
        this.feira = feira;
    }
}
