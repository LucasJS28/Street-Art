package com.lucasjs.mapagood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class imagenCompleta extends AppCompatActivity {
    ImageView imagencompleta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_completa);
        imagencompleta=findViewById(R.id.imagencompleta);
        Intent intent = getIntent();
        String rutaImagen = intent.getStringExtra("rutaImagen");
        imagencompleta.setImageURI(Uri.parse(rutaImagen));

    }
}