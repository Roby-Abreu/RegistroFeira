package com.abreu.roby.app.ent;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "produto")
public class Produto {

    public static final String FIELD_ID    = "id";
    public static final String FIELD_NOME   = "nome";
    public static final String FIELD_QTD   = "quantidade";
    public static final String FIELD_PRECO_VENDA   = "preço_venda";
    public static final String FIELD_PRECO_PROD   = "preço_producao";
    public static final String FIELD_CATEGORIA   = "categoria";

    @DatabaseField(columnName = FIELD_ID, generatedId=true)
    private int id;
    @DatabaseField(columnName = FIELD_NOME)
    private String nome;
    @DatabaseField(columnName = FIELD_QTD)
    private int qtd;
    @DatabaseField(columnName = FIELD_PRECO_PROD)
    private double preco_producao;
    @DatabaseField(columnName = FIELD_PRECO_VENDA)
    private double preco_venda;
    @DatabaseField(columnName = FIELD_CATEGORIA, foreign = true, foreignAutoRefresh = true)
    private Categoria_produto categoria;
    @DatabaseField(columnName = "item_vendido", foreign = true)
    private Item_Venda ItemVenda;

    public Produto(){}
    public Produto(int id, String nome, int qtd, double preco_producao, double preco_venda, Categoria_produto categoria,
    Item_Venda ItemVenda) {
        this.id = id;
        this.nome = nome;
        this.qtd = qtd;
        this.preco_producao = preco_producao;
        this.preco_venda = preco_venda;
        this.categoria = categoria;
        this.ItemVenda = ItemVenda;
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

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getPreco_producao() {
        return preco_producao;
    }

    public void setPreco_producao(double preco_producao) {
        this.preco_producao = preco_producao;
    }

    public double getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(double preco_venda) {
        this.preco_venda = preco_venda;
    }

    public Categoria_produto getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria_produto categoria) {
        this.categoria = categoria;
    }

    public Item_Venda getItemVenda() {
        return ItemVenda;
    }

    public void setItemVenda(Item_Venda itemVenda) {
        ItemVenda = itemVenda;
    }

    public String toString(){
        return "Produto: "+getNome()+"\n"+"Categoria: "+getCategoria();
    }
}
