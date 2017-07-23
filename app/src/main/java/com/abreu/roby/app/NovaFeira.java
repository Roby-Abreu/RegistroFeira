package com.abreu.roby.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.abreu.roby.app.dao.DatabaseHelper;
import com.abreu.roby.app.ent.Feira;
import com.abreu.roby.app.ent.Usuario;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.query.In;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class NovaFeira extends AppCompatActivity {

    TextView tvNome_feira, tvData_feira, valor1, valor2;
    Button btNova_venda, btLis_venda, btNovo_gasto, btLis_gasto;
    Usuario p = new Usuario();
    String f;
    double soma = 0, s = 0;
    public static final int CONSTANTE = 1;
    public static final int CONSTANTEG = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_feira);
        DatabaseHelper helper = new DatabaseHelper(this);
        String data = DateFormat.getDateInstance().format(new Date());

        Intent intent = getIntent();
        Bundle b = new Bundle();
        b = intent.getExtras();

        valor1 = (TextView)findViewById(R.id.textView8);
        valor2 = (TextView)findViewById(R.id.textView10);
        tvNome_feira = (TextView)findViewById(R.id.textView_nomeFeira);
        tvData_feira = (TextView)findViewById(R.id.textView_dataFeira);

        tvData_feira.setText(data);
        tvNome_feira.setText(b.getString("nome_feira"));
        f = b.getString("nome_feira");

//---------------------Criando uma feira-----------------------------------------

        Dao<Usuario, Integer> userDao = null;
        try {
            userDao = helper.getUserDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            List<Usuario> us = userDao.queryBuilder()
                    .where()
                    .eq(Usuario.FIELD_NOME, b.getString("login"))
                    .query();

            for (int i =0; us.size()>i; i++){
                p = us.get(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Feira feira = new Feira();
        feira.setLocal(b.getString("nome_feira"));
        feira.setData(data);
        feira.setStatus(true);
        feira.setUsuario(p);
        feira.setTotalVendido(0.00);
        feira.setTotalGasto(0.00);
        Dao<Feira, Integer> feiraDao = null;
        try {
            feiraDao = helper.getFeiraDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            feiraDao.create(feira);
        } catch (SQLException e) {
        }

    }
    public void mostrarOpVenda(View view){
        btNova_venda = (Button)findViewById(R.id.button_realizarVenda);
        btLis_venda = (Button)findViewById(R.id.button_listarVendaFeira);
        btNova_venda.setVisibility(View.VISIBLE);
        btLis_venda.setVisibility(View.VISIBLE);
    }
    public void mostrarOpGasto(View view){
        btNovo_gasto = (Button)findViewById(R.id.button_realizarGasto);
        btLis_gasto = (Button)findViewById(R.id.button_listarGastoFeira);
        btNovo_gasto.setVisibility(View.VISIBLE);
        btLis_gasto.setVisibility(View.VISIBLE);
    }
    public void realizarVenda(View view){
        Intent it = new Intent(this, RealizarVenda.class);
        Bundle bundle = new Bundle();
        bundle.putString("nome_feira",f);
        it.putExtras(bundle);
        startActivityForResult(it, CONSTANTE);
    }
    public void realizarGasto(View view){
        Intent it = new Intent(this, RealizarGasto.class);
        Bundle bundle = new Bundle();
        bundle.putString("nome_feira",f);
        it.putExtras(bundle);
        startActivityForResult(it, CONSTANTEG );
    }
    public void listarVrnda(View view){
        Intent it = new Intent(this, Listar_vendas.class);
        startActivity(it);
    }

    protected void onActivityResult(int codigoTela, int resultado, Intent intent ){
        if (codigoTela == CONSTANTE){
            Bundle params = intent.getExtras();
            if (params!= null){
                double c = params.getDouble("valorvendido");
                soma  = soma+c;
                valor1.setText(String.valueOf(soma));
            }
        }else if (codigoTela == CONSTANTEG){
            Bundle params = intent.getExtras();
            if (params!=null){
                double q = params.getDouble("valorgasto");
                s = s+q;
                valor2.setText(String.valueOf(s));
            }
        }
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        Valores_globais vg = (Valores_globais)getApplication();
        double valor_v = vg.valor_total_vendido;
        double valor_g = vg.valor_total_gasto;
        valor1.setText(String.valueOf(valor_v));
    }*/
}
