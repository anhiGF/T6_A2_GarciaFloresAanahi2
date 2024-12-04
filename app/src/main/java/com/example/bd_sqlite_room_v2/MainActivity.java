package com.example.bd_sqlite_room_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText cajaUsuario, cajaContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cajaUsuario = findViewById(R.id.caja_usuario);
        cajaContraseña = findViewById(R.id.caja_contraseña);


    }//onCreate

    //metodo enlazado al EVENTO CLIC DEL BOTON ACCEDER

    public void abrirMenu(View v){
        String usuario = cajaUsuario.getText().toString();
        String contraseña = cajaContraseña.getText().toString();

        if (verificarCredenciales(usuario, contraseña)) {
            Toast.makeText(getApplicationContext(), "Credenciales correctas", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, ActivityMenu.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "Credenciales incorrectas", Toast.LENGTH_LONG).show();
        }
    }

    private boolean verificarCredenciales(String usuario, String contraseña) {
        return usuario.equals("usuario") && contraseña.equals("contraseña");
    }

}//class