package com.lucasjs.mapagood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IniciodeSesion extends AppCompatActivity {

    private EditText etusuario, etcontra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciode_sesion);
        etusuario=findViewById(R.id.etusuario);
        etcontra=findViewById(R.id.etcontra);
    }

    public void iniciodesesion(View view){
        String usuario=etusuario.getText().toString().trim();
        String contra=etcontra.getText().toString().trim();
        if (!usuario.isEmpty()&&!contra.isEmpty()){
            iniciarSesion(usuario,contra);
        }else{
            Toast.makeText(this, "Rellene los Campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void iniciarSesion(String nombreUsuario, String contraseña) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        // Verificar las credenciales
        String tipoUsuario = databaseHelper.verificarCredenciales(nombreUsuario, contraseña);

        if (tipoUsuario != null) {
            // Credenciales válidas, puedes proceder según el tipo de usuario
            if (tipoUsuario.equals("admin")) {
                Toast.makeText(this, "Iniciaste Sesion Admin", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(IniciodeSesion.this, MenuAdmin.class);
                startActivity(intent);

            } else if (tipoUsuario.equals("usuario")) {
                Toast.makeText(this, "Iniciaste Sesion Usuario", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(IniciodeSesion.this, MainActivity.class);
                intent.putExtra("tipoUsuario", "usuario");
                intent.putExtra("nombreUsuario", nombreUsuario);
                startActivity(intent);

            }
        } else {
            Toast.makeText(this, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show();
        }
    }


    public void iralRegistro(View view){
        Intent intent = new Intent(IniciodeSesion.this, RegistroUsuarios.class);
        startActivity(intent);
    }
}