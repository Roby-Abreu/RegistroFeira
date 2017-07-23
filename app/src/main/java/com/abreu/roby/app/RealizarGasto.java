package com.abreu.roby.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.abreu.roby.app.dao.DatabaseHelper;
import com.abreu.roby.app.ent.Categoria_gasto;
import com.abreu.roby.app.ent.Feira;
import com.abreu.roby.app.ent.Gasto;
import com.abreu.roby.app.ent.Produto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class RealizarGasto extends AppCompatActivity {
    DatabaseHelper helper;
    Spinner spLista;
    EditText etValor;
    Feira fei;
    String f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_gasto);
        helper = new DatabaseHelper(this);
        fei = new Feira();

        spLista = (Spinner) findViewById(R.id.spinner_cat_gasto);
        etValor = (EditText) findViewById(R.id.editText_valorGasto);

        Intent intent = getIntent();
        Bundle b = new Bundle();
        b = intent.getExtras();
        f = b.getString("nome_feira");


//--------------------Carregar spinner produto-------------------------------
        Dao<Categoria_gasto, Integer> CatGDao = null;
        try {
            CatGDao = helper.getCatGastoDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            final List<Categoria_gasto> cat_gasto = CatGDao.queryForAll();
            ArrayAdapter<Categoria_gasto> adapter = new ArrayAdapter<Categoria_gasto>(this, android.R.layout.simple_spinner_dropdown_item,
                    cat_gasto);
            spLista.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }

  //-------------------------------------------------------------------------
        Dao<Feira, Integer> feiraDao = null;
        try {
            feiraDao = helper.getFeiraDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            List<Feira> feira = feiraDao.queryBuilder()
                    .where()
                    .eq(Feira.FIELD_LOCAL,f)
                    .query();

            for (int i =0; feira.size()>i; i++){
                fei = feira.get(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void fecharGasto(View view){
        Gasto gasto = new Gasto();
        gasto.setCat_gasto((Categoria_gasto)spLista.getSelectedItem());
        gasto.setValor(Double.parseDouble(etValor.getText().toString()));
        gasto.setFeira(fei);

        Dao<Gasto, Integer> gastoDao = null;
        try {
            gastoDao = helper.getGastoDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            gastoDao.create(gasto);
        } catch (SQLException e) {
        }
        Intent intent = new Intent();
        intent.putExtra("valorgasto", Double.valueOf(etValor.getText().toString()));
        setResult(2, intent);
        finish();
    }
}
