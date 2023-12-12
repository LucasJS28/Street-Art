package com.lucasjs.mapagood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
    }
    public void admubicaciones(View view){
        Intent intent = new Intent(MenuAdmin.this, MainActivity.class);
        intent.putExtra("tipoUsuario", "admin");
        startActivity(intent);
    }
    public void irUsuarios(View view){
        Intent intent = new Intent(MenuAdmin.this, AdministrarUsuarios.class);
        startActivity(intent);
    }

    public void irAopiniones(View view){
        Intent intent = new Intent(MenuAdmin.this, verOpiniones.class);
        startActivity(intent);
    }
}