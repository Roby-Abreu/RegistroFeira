package com.abreu.roby.app;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.abreu.roby.app.dao.DatabaseHelper;
import com.abreu.roby.app.ent.Feira;
import com.abreu.roby.app.ent.Forma_Pagamento;
import com.abreu.roby.app.ent.Item_Venda;
import com.abreu.roby.app.ent.Produto;
import com.abreu.roby.app.ent.Venda;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RealizarVenda extends AppCompatActivity {

    DatabaseHelper helper;
    Spinner spLista_produto, spLista_forma;
    ListView lvProd_selecionado;
    ImageButton ibAdd;
    List<String>prod_selecionado;
    double valor_total, conta = 0, d;
    TextView tvConta;
    Forma_Pagamento f;
    String fe;
    Feira fei = new Feira();
    Button btDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_venda);
        helper = new DatabaseHelper(this);

        Intent intent = getIntent();
        Bundle b = new Bundle();
        b = intent.getExtras();
        fe = b.getString("nome_feira");

        spLista_produto = (Spinner)findViewById(R.id.spinner_lista_vendaProduto);
        spLista_forma = (Spinner)findViewById(R.id.spinner_Lista_forma);
        lvProd_selecionado = (ListView)findViewById(R.id.lv_produtosVendidos);
        ibAdd = (ImageButton)findViewById(R.id.imageButton_add);
        tvConta = (TextView)findViewById(R.id.textView_conta);
        btDesc = (Button)findViewById(R.id.button_inserirDesc);

//--------------------Carregar spinner produto-------------------------------
        Dao<Produto, Integer> ProdutoDao = null;
        try {
            ProdutoDao = helper.getProdutoDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            final List<Produto> produtos = ProdutoDao.queryForAll();
            ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this, android.R.layout.simple_spinner_dropdown_item,
                    produtos);
            spLista_produto.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//--------------------Carregar spinner forma pagamento------------------------
        Dao<Forma_Pagamento, Integer> FPagDao = null;
        try {
            FPagDao = helper.getFormPagDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            final List<Forma_Pagamento> forma_pag = FPagDao.queryForAll();
            ArrayAdapter<Forma_Pagamento> adapter = new ArrayAdapter<Forma_Pagamento>(this, android.R.layout.simple_spinner_dropdown_item,
                    forma_pag);
            spLista_forma.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }

//--------------------Carregar item na listview-------------------------------
        prod_selecionado = new ArrayList<String>();
        ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto p = (Produto)spLista_produto.getSelectedItem();
                String s = p.getNome();
                String va = String.valueOf(p.getPreco_venda());
                prod_selecionado.add(s+": r$ "+va);
                ArrayAdapter<String>adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, prod_selecionado);
                lvProd_selecionado.setAdapter(adapter);

                valor_total = p.getPreco_venda();
                conta = (conta + valor_total);

                DecimalFormat df = new DecimalFormat("0.##");
                String r = df.format(conta);
                tvConta.setText(r);


            }
        });

//--------------------Ação desconto-------------------------------------------

        btDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(RealizarVenda.this);
                dialogo.setTitle("Insira o desconto (%)");
                final EditText input = new EditText(RealizarVenda.this);
                dialogo.setView(input);
                dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String s = input.getText().toString();
                        double val = Double.parseDouble(s);
                        d = (val / 100) * conta;
                        double nova_c = conta-d;
                        DecimalFormat df = new DecimalFormat("0.##");
                        String w = df.format(nova_c);
                        tvConta.setText(String.valueOf(w));
                    }
                });
                dialogo.show();
            }
        });


//--------------------Pegar forma pgamento------------------------------------
        f = (Forma_Pagamento)spLista_forma.getSelectedItem();

        Dao<Feira, Integer> feiraDao = null;
        try {
            feiraDao = helper.getFeiraDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            List<Feira> feira = feiraDao.queryBuilder()
                    .where()
                    .eq(Feira.FIELD_LOCAL,fe)
                    .query();

            for (int i =0; feira.size()>i; i++){
                fei = feira.get(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void fechar_venda(View view){
        Venda venda = new Venda();
        venda.setValor(Double.parseDouble(tvConta.getText().toString()));
        venda.setForm_pag(f);
        venda.setFeira(fei);
        Dao<Venda, Integer> vendaDao = null;
        try {
            vendaDao = helper.getVendaDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            vendaDao.create(venda);
        } catch (SQLException e) {
        }

        // Falta o create iten_venda

        Intent intent = new Intent();
        intent.putExtra("valorvendido", conta);
        setResult(1, intent);
        finish();
    }
}
