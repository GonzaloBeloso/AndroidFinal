package com.gonzalo.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gonzalo.proyectofinal.SQL.DatabaseHelper;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    EditText editUsuario, editPasswordUno, editPasswordDos;
    Button buttonRegistro, buttonVaciar;
    TextView idviewlogin;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        instancias();
        acciones();
    }

    private void acciones() {
        buttonVaciar.setOnClickListener(this);
        buttonRegistro.setOnClickListener(this);
        idviewlogin.setOnClickListener(this);
    }

    private void instancias() {

        buttonRegistro = findViewById(R.id.idButtonRegistrar);
        buttonVaciar = findViewById(R.id.idButtonVaciar);
        editUsuario = findViewById(R.id.editUsuarioRegistro);
        editPasswordUno = findViewById(R.id.editPrimeraPassRegistro);
        editPasswordDos = findViewById(R.id.editSegundaPassRegistro);
        idviewlogin = findViewById(R.id.idtextLogin);

        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idButtonRegistrar:
                if (editUsuario.equals("") || editPasswordUno.equals("") || editPasswordDos.equals("")){
                    Toast.makeText(this, "Se debe completar todos los campos", Toast.LENGTH_LONG).show();
                }
                else if(editPasswordUno.getText().toString().equals(editPasswordDos.getText().toString())) {
                    System.out.println("Registrando usuario...");
                    String usuarioRegistro = editUsuario.getText().toString();
                    String passwordRegistro = editPasswordUno.getText().toString();
                    Boolean usuarioExistente = databaseHelper.comprobarUsuario(usuarioRegistro);
                    if (usuarioExistente == true) {
                        Boolean insertarUsuario = databaseHelper.insertarUsuarios(usuarioRegistro, passwordRegistro);
                        if (insertarUsuario == true) {
                            Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "Ya existe el usuario", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.idButtonVaciar:
                System.out.println("Borrado");
                break;
            case R.id.idtextLogin:
                System.out.println("Accediendo...");
                Intent intent = new Intent(Registro.this,Login.class);
                startActivity(intent);
                break;
        }
    }
}