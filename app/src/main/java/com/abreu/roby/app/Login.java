package com.abreu.roby.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.abreu.roby.app.dao.DatabaseHelper;
import com.abreu.roby.app.ent.Usuario;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class Login extends AppCompatActivity {
    private Usuario usuario;
    private EditText etLogin, etSenha;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper = new DatabaseHelper(Login.this);
        usuario = new Usuario();

        etLogin = (EditText) findViewById(R.id.editText_Lin);
        etSenha = (EditText) findViewById(R.id.editText_Sen);
    }

    public void criarConta(View view){
        Intent it = new Intent(this, NovaConta.class);
        startActivity(it);
    }

    public void validarUsuario(View view) {
        Dao<Usuario, Integer> userDao = null;
        try {
            userDao = helper.getUserDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        try {
            final List<Usuario> users =
                    userDao.queryBuilder()
                            .where()
                            .eq(Usuario.FIELD_LOGIN, etLogin.getText().toString())
                            .and()
                            .eq(Usuario.FIELD_SENHA, etSenha.getText().toString())
                            .query();
            if (users.isEmpty()){
                Toast.makeText(this, "Login/Senha incorreto!", Toast.LENGTH_SHORT).show();
            }else{
                Intent it = new Intent(this, MenuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("login", etLogin.getText().toString());
                bundle.putString("senha", etSenha.getText().toString());
                it.putExtras(bundle);
                startActivity(it);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void teste(View view){
       Intent intent = new Intent(this, Listar_usuario.class);
        startActivity(intent);
    }

}
