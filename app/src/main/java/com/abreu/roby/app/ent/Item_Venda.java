package com.abreu.roby.app.ent;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "item_venda")
public class Item_Venda {

    public static final String FIELD_ID    = "id";
    public static final String FIELD_VENDA   = "venda";
    public static final String FIELD_PRODUTOS = "produtos";

    @DatabaseField(columnName = FIELD_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_VENDA, foreign = true)
    private Venda venda;

    @ForeignCollectionField(columnName = FIELD_PRODUTOS, eager = true)
    private ForeignCollection<Produto>produtos;

    public Item_Venda(){}
    public Item_Venda(int id, Venda venda, ForeignCollection<Produto> produtos) {
        this.id = id;
        this.venda = venda;
        this.produtos = produtos;
    }

    public Venda getVenda() {
        return venda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public ForeignCollection<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ForeignCollection<Produto> produtos) {
        this.produtos = produtos;
    }

}
