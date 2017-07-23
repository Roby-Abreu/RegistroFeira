package com.abreu.roby.app.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.abreu.roby.app.ent.Categoria_gasto;
import com.abreu.roby.app.ent.Categoria_produto;
import com.abreu.roby.app.ent.Feira;
import com.abreu.roby.app.ent.Forma_Pagamento;
import com.abreu.roby.app.ent.Gasto;
import com.abreu.roby.app.ent.Item_Venda;
import com.abreu.roby.app.ent.Produto;
import com.abreu.roby.app.ent.Usuario;
import com.abreu.roby.app.ent.Venda;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;


public class DatabaseHelper<E> extends OrmLiteSqliteOpenHelper {
    private static final String databaseName = "feira.db";
    private static final int databaseVersion = 4;
    private Dao<Usuario, Integer> mUserDao = null;
    private Dao<Categoria_produto, Integer> mCatProdDao = null;
    private Dao<Categoria_gasto, Integer> mCatGastoDao = null;
    private Dao<Forma_Pagamento, Integer> mFormPagDao = null;
    private Dao<Feira, Integer> mFeiraDao = null;
    private Dao<Produto, Integer> mProdutoDao = null;
    private Dao<Venda, Integer> mVendaDao = null;
    private Dao<Gasto, Integer> mGastoDao = null;
    private Dao<Item_Venda, Integer> mItemVendDao = null;

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sd, ConnectionSource cs) {
        try {
            TableUtils.createTable(cs, Usuario.class);
            TableUtils.createTable(cs, Categoria_produto.class);
            TableUtils.createTable(cs, Categoria_gasto.class);
            TableUtils.createTable(cs, Forma_Pagamento.class);
            TableUtils.createTable(cs, Feira.class);
            TableUtils.createTable(cs, Produto.class);
            TableUtils.createTable(cs, Venda.class);
            TableUtils.createTable(cs, Gasto.class);
            TableUtils.createTable(cs, Item_Venda.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sd, ConnectionSource cs, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(cs, Usuario.class, true);
            TableUtils.dropTable(cs, Categoria_produto.class, true);
            TableUtils.dropTable(cs, Categoria_gasto.class, true);
            TableUtils.dropTable(cs, Forma_Pagamento.class, true);
            TableUtils.dropTable(cs, Feira.class, true);
            TableUtils.dropTable(cs, Produto.class, true);
            TableUtils.dropTable(cs, Venda.class, true);
            TableUtils.dropTable(cs, Gasto.class, true);
            TableUtils.dropTable(cs, Item_Venda.class, true);
            onCreate(sd,cs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* User */
    public Dao<Usuario, Integer> getUserDao() throws SQLException {
        if (mUserDao == null) {
            mUserDao = getDao(Usuario.class);
        }

        return mUserDao;
    }
    /* Produto */
    public Dao<Produto, Integer> getProdutoDao() throws SQLException {
        if (mProdutoDao == null) {
            mProdutoDao = getDao(Produto.class);
        }

        return mProdutoDao;
    }
    /* Categoria_produto*/
    public Dao<Categoria_produto, Integer> getCatProdDao() throws SQLException {
        if (mCatProdDao == null) {
            mCatProdDao = getDao(Categoria_produto.class);
        }

        return mCatProdDao;
    }
    /* Categoria gasto */
    public Dao<Categoria_gasto, Integer> getCatGastoDao() throws SQLException {
        if (mCatGastoDao == null) {
            mCatGastoDao = getDao(Categoria_gasto.class);
        }

        return mCatGastoDao;
    }
    /* Forma pagamento*/
    public Dao<Forma_Pagamento, Integer> getFormPagDao() throws SQLException {
        if (mFormPagDao == null) {
            mFormPagDao = getDao(Forma_Pagamento.class);
        }

        return mFormPagDao;
    }
    /* Feira */
    public Dao<Feira, Integer> getFeiraDao() throws SQLException {
        if (mFeiraDao == null) {
            mFeiraDao = getDao(Feira.class);
        }

        return mFeiraDao;
    }
    /* Venda */
    public Dao<Venda, Integer> getVendaDao() throws SQLException {
        if (mVendaDao == null) {
            mVendaDao = getDao(Venda.class);
        }

        return mVendaDao;
    }
    /* Gasto */
    public Dao<Gasto, Integer> getGastoDao() throws SQLException {
        if (mGastoDao == null) {
            mGastoDao = getDao(Gasto.class);
        }

        return mGastoDao;
    }
    /* Item Venda */
    public Dao<Item_Venda, Integer> getItemVendDao() throws SQLException {
        if (mItemVendDao == null) {
            mItemVendDao = getDao(Item_Venda.class);
        }

        return mItemVendDao;
    }


    @Override
    public void close() {
        mUserDao = null;

        super.close();
    }


}
