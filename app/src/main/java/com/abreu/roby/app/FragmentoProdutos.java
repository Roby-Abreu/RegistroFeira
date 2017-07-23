package com.abreu.roby.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.abreu.roby.app.dao.DatabaseHelper;
import com.abreu.roby.app.ent.Produto;
import com.abreu.roby.app.ent.Usuario;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class FragmentoProdutos extends Fragment {
    View minhaView;
    Button btNovoProd, btNovaCat;
    ListView v;
    DatabaseHelper helper;
    List<Produto> produtos;
    ArrayAdapter<Produto> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        minhaView = inflater.inflate(R.layout.layout_produtos, container, false);

        btNovaCat = (Button) minhaView.findViewById(R.id.button_CadastrarCat);
        btNovoProd = (Button) minhaView.findViewById(R.id.button_NovoProduto);
        btNovoProd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), Cadastro_produto.class);
                startActivity(it);
            }
        });

        btNovaCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), Nova_CategoriaProduto.class);
                startActivity(it);
            }
        });

        helper = new DatabaseHelper(getActivity());

        v = (ListView) minhaView.findViewById(R.id.Lista_produtos);
        Dao<Produto, Integer> ProdutoDao = null;
        try {
            ProdutoDao = helper.getProdutoDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
           produtos = ProdutoDao.queryForAll();
            if (produtos.isEmpty()) {
                Toast.makeText(getActivity(), "lista vazia", Toast.LENGTH_SHORT).show();
            } else {
               adapter = new ArrayAdapter<Produto>(getActivity(),
                        android.R.layout.simple_list_item_1, produtos);
                v.setAdapter(adapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return minhaView;
    }

    public void onResume() {
        super.onResume();

        v = (ListView) minhaView.findViewById(R.id.Lista_produtos);
        Dao<Produto, Integer> ProdutoDao = null;
        try {
            ProdutoDao = helper.getProdutoDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            final List<Produto> produtos = ProdutoDao.queryForAll();
            if (produtos.isEmpty()) {
                Toast.makeText(getActivity(), "lista vazia", Toast.LENGTH_SHORT).show();
            } else {
                ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(getActivity(),
                        android.R.layout.simple_list_item_1, produtos);
                v.setAdapter(adapter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
