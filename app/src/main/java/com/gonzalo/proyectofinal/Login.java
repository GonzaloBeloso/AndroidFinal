package com.gonzalo.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.gonzalo.proyectofinal.SQL.DatabaseHelper;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button idLogin;
    EditText editU, editPUno;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        instancias();
        acciones();
    }

    private void acciones() {
        idLogin.setOnClickListener(this);
    }

    private void instancias() {
        editU = findViewById(R.id.editUsuarioLogin);
        editPUno = findViewById(R.id.editPrimeraPassLogin);
        idLogin = findViewById(R.id.idButtonLogin);
        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idButtonLogin:
                if (editU.equals("") || editPUno.equals("")){
                    Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_LONG).show();
                }else{
                    System.out.println("complete");
                    String nombreLogin = editU.getText().toString();
                    String passwordLogin = editPUno.getText().toString();
                    boolean comprobarLogin = databaseHelper.comprobarLogin(nombreLogin,passwordLogin);
                    if(comprobarLogin==true){
                        System.out.println("Login correcto");
                        Toast.makeText(this, "Accediendo...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this,MainAplicacion.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "Los datos introducidos son incorrectos, comprueba contraseña o correo", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}