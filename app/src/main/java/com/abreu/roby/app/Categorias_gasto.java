package com.abreu.roby.app;

import android.content.DialogInterface;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.abreu.roby.app.dao.DatabaseHelper;
import com.abreu.roby.app.ent.Categoria_gasto;
import com.j256.ormlite.dao.Dao;

import java.util.List;

public class Categorias_gasto extends AppCompatActivity {
    ListView lvLista;
    Button btNovocatg;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias_gasto);
        helper = new DatabaseHelper(this);

        lvLista = (ListView)findViewById(R.id.lista_cat_gasto);
        btNovocatg = (Button)findViewById(R.id.button_add_catG);

        btNovocatg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Categorias_gasto.this);
                builder.setTitle("Categoria de Gasto:");
                final EditText input = new EditText(Categorias_gasto.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String r = input.getText().toString();
                        Dao<Categoria_gasto, Integer> CatGDao = null;
                        try {
                            CatGDao = helper.getCatGastoDao();
                        } catch (java.sql.SQLException e) {
                            e.printStackTrace();
                        }
                        Categoria_gasto cp = new Categoria_gasto();
                      cp.setCategoria_gasto(r);
                        try {
                            CatGDao.create(cp);
                        } catch (java.sql.SQLException e) {
                            e.printStackTrace();
                        }
                        recreate();
                    }
                });
                builder.show();
            }
        });
        Dao<Categoria_gasto, Integer> CatGDao = null;
        try {
            CatGDao = helper.getCatGastoDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            final List<Categoria_gasto> lcg = CatGDao.queryForAll();
            if (lcg.isEmpty()){
                Toast.makeText(this, "lista vazia", Toast.LENGTH_SHORT).show();
            }else{
                ArrayAdapter<Categoria_gasto> adapter = new ArrayAdapter<Categoria_gasto>(this,
                        android.R.layout.simple_list_item_1, lcg);
                lvLista.setAdapter(adapter);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
}
