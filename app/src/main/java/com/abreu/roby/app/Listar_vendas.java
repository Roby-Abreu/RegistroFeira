package com.abreu.roby.app;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.abreu.roby.app.dao.DatabaseHelper;
import com.abreu.roby.app.ent.Feira;
import com.abreu.roby.app.ent.Item_Venda;
import com.abreu.roby.app.ent.Usuario;
import com.abreu.roby.app.ent.Venda;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class Listar_vendas extends AppCompatActivity {
    DatabaseHelper helper;
    ListView lvLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_vendas);
        helper = new DatabaseHelper(this);
        String data = DateFormat.getDateInstance().format(new Date());
        lvLista = (ListView)findViewById(R.id.lista_v);

        Dao<Feira, Integer> feiraDao = null;
        try {
            feiraDao = helper.getFeiraDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            List<Feira> f = feiraDao.queryBuilder()
                    .where()
                    .eq(Feira.FIELD_DATA, data)
                    .query();

            for (int i = 0; f.size() > i; i++) {
                Dao<Venda, Integer> vendaDao = null;
                try {
                    vendaDao = helper.getVendaDao();
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                }
                try {
                    List<Venda> v = vendaDao.queryBuilder()
                            .where()
                            .eq(Venda.FIELD_FEIRA, f.get(i))
                            .query();
                    for (int q = 0; v.size() > q; q++) {
                        Dao<Item_Venda, Integer> ivDao = null;
                        try {
                            ivDao = helper.getItemVendDao();
                        } catch (java.sql.SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<Item_Venda> iv = ivDao.queryBuilder()
                                    .where()
                                    .eq(Item_Venda.FIELD_ID, v.get(q))
                                    .query();
                            ArrayAdapter<Item_Venda>adapter = new ArrayAdapter<Item_Venda>(this,
                                    android.R.layout.simple_list_item_1, iv);
                            lvLista.setAdapter(adapter);
                        } catch (java.sql.SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
