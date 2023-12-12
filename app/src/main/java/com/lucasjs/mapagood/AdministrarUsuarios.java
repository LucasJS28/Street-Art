package com.lucasjs.mapagood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class AdministrarUsuarios extends AppCompatActivity {
    private DatabaseHelper dbHelper; // IMPORTANTE
    private RecyclerView recyclerview; // IMPORTANTE
    private usuarios selectedUsuario=null; // IMPORTANTE
    private EditText edtusuario,edtcontra,edtbuscar;
    private Spinner sptiposuser;
    private String [] tiposdeusuario={"admin", "usuario"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_usuarios);
        edtusuario=findViewById(R.id.edtusuario);
        edtcontra=findViewById(R.id.edtcontra);
        edtbuscar=findViewById(R.id.edtbuscar);
        sptiposuser=findViewById(R.id.sptiposuser);
        ArrayAdapter<String> adaptador=new ArrayAdapter<> (this,android.R.layout.simple_spinner_item,tiposdeusuario); // Uniendo la lista al Adaptador
        sptiposuser.setAdapter(adaptador); // Seteando Adaptador
        dbHelper=new DatabaseHelper(this); // IMPORTANTE
        recyclerview = findViewById(R.id.recyclerview); // IMPORTANTE
        recyclerview.setLayoutManager(new LinearLayoutManager(this)); // IMPORTANTE
        cargarDatos();

        edtbuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Este método se llama antes de que el texto cambie.
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String textoBusqueda = charSequence.toString().trim();
                searchUsuario(textoBusqueda);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Este método se llama después de que el texto cambió.
            }
        });
    }

    public void agregar(View view){
        registrarUsuarios();
    }

    private void registrarUsuarios(){
        String usuario = edtusuario.getText().toString().trim();
        String contra = edtcontra.getText().toString().trim();
        String tipouser = sptiposuser.getSelectedItem().toString().trim();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(!usuario.isEmpty() && !contra.isEmpty() && !tipouser.isEmpty()){
            String insertQuery = "INSERT INTO " + DatabaseHelper.TABLE_USUARIOS + " (" +
                    DatabaseHelper.COLUMN_NOMBRE_USUARIO + ", " +
                    DatabaseHelper.COLUMN_CONTRASEÑA + ", " +
                    DatabaseHelper.COLUMN_TIPO_USUARIO + ") VALUES ('" +
                    usuario + "', '" + contra + "', '" + tipouser + "')";

            db.execSQL(insertQuery);
            db.close();
            limpiar();
            cargarDatos();
        } else {
            Toast.makeText(this, "Debe Rellenar Todos los Campos", Toast.LENGTH_SHORT).show();
        }
    }
    public void editar(View view){
        actualizarusuario();
    }
    private void actualizarusuario(){
        if (selectedUsuario!=null){
            String nombre=edtusuario.getText().toString();
            String contra=edtcontra.getText().toString();
            String tipousuario=sptiposuser.getSelectedItem().toString().trim();
            if(!nombre.isEmpty()&&!contra.isEmpty()&&!tipousuario.isEmpty()) {
                selectedUsuario.setNombre(nombre);
                selectedUsuario.setContraseña(contra);
                selectedUsuario.setTipoUsuario(tipousuario);
                dbHelper.actualizarUsuario(selectedUsuario);
                limpiar();
                cargarDatos();
            }else {
                Toast.makeText(this, "Debe Rellenar Todos los Campos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void eliminar(View view){
        deleteusuario();
    }
    public void deleteusuario(){
        if (selectedUsuario!=null){
            String nombre=selectedUsuario.getNombre();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Estás seguro de que deseas Eliminar : "+nombre+"?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dbHelper.deleteusuario(selectedUsuario);
                            limpiar();
                            cargarDatos();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }


    private void cargarDatos() {
        List<usuarios> usuarios = dbHelper.getAllusuarios();
        usuariosAdapter adapter = new usuariosAdapter(usuarios); // Pasa la lista de personas al constructor
        recyclerview.setAdapter(adapter); // Asigna el adaptador al RecyclerView
    }

    public void selectedUsuario(usuarios usuario){
        selectedUsuario = usuario;
        edtusuario.setText(selectedUsuario.getNombre());
        edtcontra.setText(selectedUsuario.getContraseña());
        // Obtener el rango del usuario seleccionado
        String rangoUsuario = selectedUsuario.getTipoUsuario(); // Supongamos que hay un método getRango() en la clase usuarios
        // Verificar si el rango obtenido está dentro de los tipos de usuario
        for (int i = 0; i < tiposdeusuario.length; i++) {
            if (tiposdeusuario[i].equals(rangoUsuario)) {
                sptiposuser.setSelection(i); // Establecer el índice del rango en el Spinner
                break;
            }
        }
    }

    public void searchUsuario(String usuarios){
        List<usuarios> buscarusuarios = dbHelper.buscarUsuarios(usuarios);
        usuariosAdapter adapter = new usuariosAdapter(buscarusuarios); // Pasa la lista de personas al constructor
        recyclerview.setAdapter(adapter); // Asigna el adaptador al RecyclerView
    }

    public void limpiar(){
        selectedUsuario=null;
        edtusuario.setText("");
        edtcontra.setText("");
        edtusuario.requestFocus();
    }
}