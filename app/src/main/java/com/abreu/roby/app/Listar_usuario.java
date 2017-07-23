package com.abreu.roby.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.abreu.roby.app.dao.DatabaseHelper;
import com.abreu.roby.app.ent.Feira;
import com.abreu.roby.app.ent.Usuario;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class Listar_usuario extends AppCompatActivity {
    private DatabaseHelper helper;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuario);
        helper = new DatabaseHelper(this);
        lista = (ListView)findViewById(R.id.Lista_usuarios);

        Dao<Feira, Integer> feiraDao = null;
        //Dao<Usuario, Integer> userDao = null;
            try {
                feiraDao = helper.getFeiraDao();
              //  userDao = helper.getUserDao();
            } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            final List<Feira> users = feiraDao.queryForAll();
           // final List<Usuario> users = userDao.queryForAll();
            if (users.isEmpty()){
                Toast.makeText(this, "lista vazia", Toast.LENGTH_SHORT).show();
            }else{
                ArrayAdapter<Feira>adapter = new ArrayAdapter<Feira>(this, android.R.layout.simple_list_item_1, users);
               //ArrayAdapter<Usuario>adapter = new ArrayAdapter<Usuario>(this, android.R.layout.simple_list_item_1, users);
                lista.setAdapter(adapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
