package com.lucasjs.mapagood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

public class EncuestaSatisfaccion extends AppCompatActivity {

    Switch switchsatisfecho;
    RadioButton rbmuyprobable,rbprobable,rbneutral,rbimprobable,rbimposible;
    EditText edtopiniones,etnombrecon,etcorreocon,ettelefonocon;
    private DatabaseHelper databaseHelper;
    private String nombreusuarioo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta_satisfaccion);
        switchsatisfecho=findViewById(R.id.switchsatisfecho);
        rbmuyprobable=findViewById(R.id.rbmuyprobable);
        rbprobable=findViewById(R.id.rbprobable);
        rbneutral=findViewById(R.id.rbneutral);
        rbimprobable=findViewById(R.id.rbimprobable);
        rbimposible=findViewById(R.id.rbimposible);
        edtopiniones=findViewById(R.id.edtopiniones);
        etnombrecon=findViewById(R.id.etnombrecon);
        etcorreocon=findViewById(R.id.etcorreocon);
        ettelefonocon=findViewById(R.id.ettelefonocon);
        databaseHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        nombreusuarioo = intent.getStringExtra("nombreUsuario");
        Toast.makeText(this, "Bienvenido: "+nombreusuarioo, Toast.LENGTH_SHORT).show();
    }

    public void EnviarFormulario(View view) {
        String Recomendacion = "";
        String Satisfecho = switchsatisfecho.isChecked() ? "Satisfecho" : "Insatisfecho";

        if (rbmuyprobable.isChecked()) {
            Recomendacion = "Muy Probable";
        } else if (rbprobable.isChecked()) {
            Recomendacion = "Probable";
        } else if (rbneutral.isChecked()) {
            Recomendacion = "Neutral";
        } else if (rbimprobable.isChecked()) {
            Recomendacion = "Improbable";
        } else if (rbimposible.isChecked()) {
            Recomendacion = "Imposible";
        }

        String Comentarios = edtopiniones.getText().toString().trim();

        // Informacion Opcional
        String Nombre = etnombrecon.getText().toString().trim();
        String Correo = etcorreocon.getText().toString().trim();
        String Telefono = ettelefonocon.getText().toString().trim();

        // Validación de campos obligatorios
        if (Comentarios.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tus comentarios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificación y asignación de valores predeterminados a campos opcionales no ingresados
        if (Nombre.isEmpty()) {
            Nombre = "No Ingresado";
        }
        if (Correo.isEmpty()) {
            Correo = "No Ingresado";
        }
        if (Telefono.isEmpty()) {
            Telefono = "No Ingresado";
        }

        // Guardamos los datos en la base de datos utilizando el método agregarRespuestasEncuesta del DatabaseHelper
        databaseHelper.agregarRespuestasEncuesta(nombreusuarioo,Satisfecho, Recomendacion, Comentarios, Nombre, Correo, Telefono);
        Toast.makeText(this, "Encuesta Enviada, ¡Muchas Gracias!", Toast.LENGTH_SHORT).show();
        Limpiar();
    }


    public void Limpiar(){
        edtopiniones.setText("");
        switchsatisfecho.setChecked(false);
        etnombrecon.setText("");
        etcorreocon.setText("");
        ettelefonocon.setText("");
        edtopiniones.requestFocus();
    }
}