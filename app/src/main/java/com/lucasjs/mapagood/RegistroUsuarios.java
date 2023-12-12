package com.lucasjs.mapagood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroUsuarios extends AppCompatActivity {

    private EditText ettnombre,ettcontra,ettrepecontra;
    private CheckBox checkterminos;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        ettnombre=findViewById(R.id.ettnombre);
        ettcontra=findViewById(R.id.ettcontra);
        ettrepecontra=findViewById(R.id.ettrepecontra);
        checkterminos=findViewById(R.id.checkterminos);
        databaseHelper = new DatabaseHelper(this);

    }

    public void Registraar(View view) {
        String nombre = ettnombre.getText().toString().trim();
        String contraseña = ettcontra.getText().toString().trim();
        String repetirContraseña = ettrepecontra.getText().toString().trim();

        if (nombre.isEmpty() || contraseña.isEmpty() || repetirContraseña.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (contraseña.length() < 3) {
            Toast.makeText(this, "La contraseña es demasiado corta", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!contraseña.equals(repetirContraseña)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!checkterminos.isChecked()) {
            Toast.makeText(this, "Acepta los términos y condiciones", Toast.LENGTH_SHORT).show();
            return;
        }

        nombre = nombre.trim();
        String tipoUsuario = "usuario";

        if (nombre.equals("Lucasxdd")) {
            tipoUsuario = "admin";
        }

            boolean registrado = databaseHelper.registrarUsuario(nombre, contraseña, tipoUsuario);

            if (registrado) {
                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistroUsuarios.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
            }
        }
    }
