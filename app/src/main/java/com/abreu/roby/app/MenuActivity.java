package com.abreu.roby.app;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.abreu.roby.app.dao.DatabaseHelper;
import com.abreu.roby.app.ent.Feira;
import com.abreu.roby.app.ent.Usuario;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String lo, se, srt, data;
    DatabaseHelper helper;
    Usuario u;
    List<Usuario>usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        helper = new DatabaseHelper(this);
        data = DateFormat.getDateInstance().format(new Date());

        Intent intent = getIntent();
        Bundle b = new Bundle();
        b = intent.getExtras();

        lo = b.getString("login");
        se = b.getString("senha");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        FragmentTransaction ft;
        Fragment frag = new FragmentoProdutos();

        if (id == R.id.nav_feira) {
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle("NOME DA FEIRA");
            final EditText input = new EditText(this);
            dialogo.setView(input);
            dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    srt = input.getEditableText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("nome_feira", srt);
                    bundle.putString("login", lo);
                    Intent intent = new Intent(getApplicationContext(), NovaFeira.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            dialogo.show();

        }else if (id == R.id.nav_produtos) {
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.conteudo, frag);
            ft.commit();
        }else if(id == R.id.nav_formas_de_pagamnto){
            Intent it = new Intent(this, Forma_de_Pagamento.class);
            startActivity(it);
        }else if(id == R.id.nav_cat_gasto) {
            Intent it = new Intent(this, Categorias_gasto.class);
            startActivity(it);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
