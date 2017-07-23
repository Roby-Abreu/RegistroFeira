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
import com.abreu.roby.app.ent.Forma_Pagamento;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class Forma_de_Pagamento extends AppCompatActivity {

    private String r;
    private Button btNovaFPag;
    private DatabaseHelper helper;
    private ListView lvLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forma_de__pagamento);
        helper = new DatabaseHelper(this);

        lvLista = (ListView)findViewById(R.id.lista_formaPagamento);
        btNovaFPag = (Button)findViewById(R.id.button_novoFPag);

        btNovaFPag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Forma_de_Pagamento.this);
                builder.setTitle("Forma de Pagamento:");
                final EditText input = new EditText(Forma_de_Pagamento.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        r = input.getText().toString();
                        Dao<Forma_Pagamento, Integer> FPagdDao = null;
                        try {
                            FPagdDao = helper.getFormPagDao();
                        } catch (java.sql.SQLException e) {
                            e.printStackTrace();
                        }
                        Forma_Pagamento fp = new Forma_Pagamento();
                        fp.setTipo(r);
                        try {
                            FPagdDao.create(fp);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        recreate();
                    }
                });
                builder.show();
            }
        });
        Dao<Forma_Pagamento, Integer> FPagdDao = null;
        try {
            FPagdDao = helper.getFormPagDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            final List<Forma_Pagamento> fpp = FPagdDao.queryForAll();
            if (fpp.isEmpty()){
                Toast.makeText(this, "lista vazia", Toast.LENGTH_SHORT).show();
            }else{
                ArrayAdapter<Forma_Pagamento> adapter = new ArrayAdapter<Forma_Pagamento>(this,
                        android.R.layout.simple_list_item_1, fpp);
                lvLista.setAdapter(adapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
