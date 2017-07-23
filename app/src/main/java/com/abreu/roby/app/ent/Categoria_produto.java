package com.abreu.roby.app.ent;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "categoria_produto")
public class Categoria_produto {

    @DatabaseField(columnName = "id", generatedId=true)
    private int id;

    @DatabaseField(columnName = "categoria")
    private String nome;

    public Categoria_produto(){}
    public Categoria_produto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString(){
        return getNome();
    }
}
