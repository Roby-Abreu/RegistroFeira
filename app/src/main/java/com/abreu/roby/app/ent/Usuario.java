package com.abreu.roby.app.ent;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.query.ColumnNameOrRawSql;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "usuario")
public class Usuario {

    public static final String FIELD_ID    = "id";
    public static final String FIELD_NOME   = "nome";
    public static final String FIELD_LOGIN   = "login";
    public static final String FIELD_SENHA   = "senha";

    @DatabaseField(columnName = FIELD_ID, generatedId=true)
    private int id;

    @DatabaseField(columnName = FIELD_NOME )
    private String nome;

    @DatabaseField(columnName = FIELD_LOGIN )
    private String login;

    @DatabaseField(columnName = FIELD_SENHA )
    private String senha;

    public Usuario(){}
    public Usuario(int id, String nome, String login, String senha){
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String toString(){
        return "Nome: "+this.getNome()+"\n"+"Login: "+this.getLogin()+"\n"+"Senha: "+this.getSenha();
    }
}
