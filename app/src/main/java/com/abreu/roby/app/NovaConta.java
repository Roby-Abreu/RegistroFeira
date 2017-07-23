package com.abreu.roby.app;

import android.content.DialogInterface;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.abreu.roby.app.dao.DatabaseHelper;
import com.abreu.roby.app.ent.Usuario;
import com.j256.ormlite.dao.Dao;


public class NovaConta extends AppCompatActivity {
    private Usuario usuario;
    private EditText etNome, etNovoLogin, etNovaSenha;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_conta);

        helper = new DatabaseHelper(this);
        etNome = (EditText) findViewById(R.id.editText_Nome);
        etNovoLogin = (EditText) findViewById(R.id.editText_Login);
        etNovaSenha = (EditText) findViewById(R.id.editText_Senha);

    }

    public void salvarConta(View view) {
        Dao<Usuario, Integer> userDao = null;
        try {
            userDao = helper.getUserDao();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        if (etNome.getText().toString().isEmpty() || etNovoLogin.getText().toString().isEmpty() ||
                etNovaSenha.getText().toString().isEmpty()) {
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setMessage("Preencha o(s) campo(s)!");
            alertDialogBuilder.setPositiveButton(" Ok ", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialogBuilder.show();
        }else{
            usuario = new Usuario();
            usuario.setNome(etNome.getText().toString());
            usuario.setLogin(etNovoLogin.getText().toString());
            usuario.setSenha(etNovaSenha.getText().toString());
            try {
                userDao.create(usuario);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
