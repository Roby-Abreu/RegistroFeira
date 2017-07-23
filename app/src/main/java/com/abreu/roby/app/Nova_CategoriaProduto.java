package com.abreu.roby.app;

import android.content.DialogInterface;
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
import com.abreu.roby.app.ent.Categoria_produto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class Nova_CategoriaProduto extends AppCompatActivity {
    private String r;
    private Button btNovaC;
    private DatabaseHelper helper;
    private ListView lvLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_produtos);

        helper = new DatabaseHelper(this);
        lvLista = (ListView)findViewById(R.id.lista_categoria_produto);
        btNovaC = (Button)findViewById(R.id.button_novCat);
        btNovaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Nova_CategoriaProduto.this);
                builder.setTitle("Nova Categoria:");
                final EditText input = new EditText(Nova_CategoriaProduto.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        r = input.getText().toString();
                        Dao<Categoria_produto, Integer> CatProdDao = null;
                        try {
                            CatProdDao = helper.getCatProdDao();
                        } catch (java.sql.SQLException e) {
                            e.printStackTrace();
                        }
                        Categoria_produto cp = new Categoria_produto();
                        cp.setNome(r);
                        try {
                            CatProdDao.create(cp);
                        } catch (java.sql.SQLException e) {
                            e.printStackTrace();
                        }
                        recreate();
                    }
                });
                builder.show();
            }
        });
        Dao<Categoria_produto, Integer> CatProdDao = null;
        try {
            CatProdDao = helper.getCatProdDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            final List<Categoria_produto> cp = CatProdDao.queryForAll();
            if (cp.isEmpty()){
                Toast.makeText(this, "lista vazia", Toast.LENGTH_SHORT).show();
            }else{
                ArrayAdapter<Categoria_produto> adapter = new ArrayAdapter<Categoria_produto>(this,
                        android.R.layout.simple_list_item_1, cp);
                lvLista.setAdapter(adapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
