package com.lucasjs.mapagood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class verOpiniones extends AppCompatActivity {

    private DatabaseHelper dbHelper; // IMPORTANTE
    private RecyclerView recycleerview; // IMPORTANTE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_opiniones);
        dbHelper=new DatabaseHelper(this); // IMPORTANTE
        recycleerview = findViewById(R.id.recycleerview); // IMPORTANTE
        recycleerview.setLayoutManager(new LinearLayoutManager(this)); // IMPORTANTE
        cargarDatos();
    }

    private void cargarDatos() {
        List<encuesta> encuestas = dbHelper.getAllEncuestas();
        encuestaAdapter adapter = new encuestaAdapter(encuestas); // Pasa la lista de personas al constructor
        recycleerview.setAdapter(adapter); // Asigna el adaptador al RecyclerView

    }
}