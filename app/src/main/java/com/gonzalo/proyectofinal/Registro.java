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

    EditText editU, editPUno, editPDos;
    Button btnR, btnV;
    TextView idLogin;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        instancias();
        acciones();
    }

    private void acciones() {
        btnV.setOnClickListener(this);
        btnR.setOnClickListener(this);
        idLogin.setOnClickListener(this);
    }

    private void instancias() {

        btnR = findViewById(R.id.idButtonRegistrar);
        btnV = findViewById(R.id.idButtonVaciar);
        editU = findViewById(R.id.editUsuarioRegistro);
        editPUno = findViewById(R.id.editPrimeraPassRegistro);
        editPDos = findViewById(R.id.editSegundaPassRegistro);
        idLogin = findViewById(R.id.idtextLogin);

        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idButtonRegistrar:
                if (editU.equals("") || editPUno.equals("") || editPDos.equals("")){
                    Toast.makeText(this, "Se debe completar todos los campos", Toast.LENGTH_LONG).show();
                }
                else if(editPUno.getText().toString().equals(editPDos.getText().toString())) {
                    System.out.println("Registrando usuario...");
                    String usuarioRegistro = editU.getText().toString();
                    String passwordRegistro = editPUno.getText().toString();
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
        }
    }
}