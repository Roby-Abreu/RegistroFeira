package com.abreu.roby.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abreu.roby.app.dao.DatabaseHelper;
import com.abreu.roby.app.ent.Item_Venda;
import com.abreu.roby.app.ent.Venda;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class ListarVendaDia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_venda_dia);
        DatabaseHelper helper = new DatabaseHelper(this);

    }
}
