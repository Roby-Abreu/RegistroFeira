package com.abreu.roby.app;

import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.abreu.roby.app.dao.DatabaseHelper;
import com.abreu.roby.app.ent.Categoria_produto;
import com.abreu.roby.app.ent.Produto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class Cadastro_produto extends AppCompatActivity {
    Spinner spCatProd;
    private String n;
    private DatabaseHelper helper;
    private Button btSalvarProd;
    private EditText etNome, etQTD, etPP, etPV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
        helper = new DatabaseHelper(this);

        etNome = (EditText)findViewById(R.id.editText_produtoNome);
        etQTD = (EditText)findViewById(R.id.editText_qtd_produto);
        etPP = (EditText)findViewById(R.id.editText_precoProd);
        etPV = (EditText)findViewById(R.id.editText_precoVenda);
        btSalvarProd = (Button)findViewById(R.id.button_cadastrarProd);
        spCatProd = (Spinner)findViewById(R.id.spinner_cat_produto);

        Dao<Categoria_produto, Integer> Cat_prodDao = null;
        try {
            Cat_prodDao = helper.getCatProdDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            final List<Categoria_produto> cat_produtos = Cat_prodDao.queryForAll();
            if (cat_produtos.isEmpty()){
                Toast.makeText(this, "lista vazia", Toast.LENGTH_SHORT).show();
            }else{
                ArrayAdapter<Categoria_produto> adapter = new ArrayAdapter<Categoria_produto>(this, android.R.layout.simple_list_item_1,
                        cat_produtos);
                spCatProd.setAdapter(adapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


//--------------------------------------------------------------
        btSalvarProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dao<Produto, Integer> ProdutoDao = null;
                try {
                    ProdutoDao = helper.getProdutoDao();
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                }
                Produto p = new Produto();
                p.setNome(etNome.getText().toString());
                p.setCategoria((Categoria_produto)spCatProd.getSelectedItem());
                p.setQtd(Integer.parseInt(etQTD.getText().toString()));
                p.setPreco_producao(Double.parseDouble(etPP.getText().toString()));
                p.setPreco_venda(Double.parseDouble(etPV.getText().toString()));

                try {
                    ProdutoDao.create(p);
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Produto Salvo!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
