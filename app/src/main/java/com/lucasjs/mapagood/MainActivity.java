package com.lucasjs.mapagood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback
        , GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    Spinner splugares;
    private DatabaseHelper dbHelper;

    EditText txtLatitud, txtLongitud, etnombre, etdescripcion;
    TextView txtsp;
    GoogleMap mMap;
    TextView tvtitulo;
    List<ubicaciones> listaUbicaciones;
    ImageView imagenlugar,imglogo,buttonencuesta;
    Button btncamara,btnagregar,btneditar,btneliminar,btngaleria;
    LinearLayout linearbotones,layoutbotonesa;
    private String rutaImagenCapturada = ""; // Variable para almacenar la ruta de la imagen
    private ubicaciones selectubi = null;
    private String nombreUsuario="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this); // Inicializar dbHelper aquí
        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);
        etnombre = findViewById(R.id.etnombre);
        txtsp = findViewById(R.id.txtsp);
        etdescripcion = findViewById(R.id.etdescripcion);
        btncamara = findViewById(R.id.btncamara);
        btnagregar = findViewById(R.id.btnagregar);
        btneditar = findViewById(R.id.btneditar);
        btneliminar = findViewById(R.id.btneliminar);
        tvtitulo = findViewById(R.id.tvtitulo);
        imglogo = findViewById(R.id.imglogo);
        buttonencuesta = findViewById(R.id.buttonencuesta);
        linearbotones = findViewById(R.id.linearbotones);
        layoutbotonesa = findViewById(R.id.layoutbotonesa);
        btngaleria = findViewById(R.id.btngaleria);
        imagenlugar = findViewById(R.id.imagenlugar);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        String valorRecibido = intent.getStringExtra("tipoUsuario");
        nombreUsuario = intent.getStringExtra("nombreUsuario");
        if (!valorRecibido.equals("usuario")){
            buttonencuesta.setVisibility(View.INVISIBLE);
        }
        if (valorRecibido.equals("usuario")){
            btngaleria.setVisibility(View.GONE);
            btnagregar.setVisibility(View.GONE);
            btneditar.setVisibility(View.GONE);
            btncamara.setVisibility(View.GONE);
            btngaleria.setVisibility(View.GONE);
            btneliminar.setVisibility(View.GONE);
            tvtitulo.setText("Ver Ubicaciones");
            imglogo.setImageResource(R.drawable.mapasimg);
            linearbotones.setVisibility(View.GONE);
            layoutbotonesa.setVisibility(View.GONE);
            etnombre.setEnabled(false);
            etdescripcion.setEnabled(false);
        }


        splugares = findViewById(R.id.splugares);
        listaUbicaciones = dbHelper.getAllUbicaciones(); // Obtener ubicaciones desde la base de datos
        ArrayList<String> nombresUbicaciones = new ArrayList<>(); // Crear un ArrayList de nombres de ubicaciones
        nombresUbicaciones.add("Seleccione una Opción"); // Agregar la opción predeterminada
        for (ubicaciones ubicacion : listaUbicaciones) {
            nombresUbicaciones.add(ubicacion.getNombre());
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresUbicaciones);
        splugares.setAdapter(adaptador);
        splugares.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String lugarSeleccionado = splugares.getSelectedItem().toString();
                ubicaciones ubicacionSeleccionada = null;
                for (ubicaciones ubicacion : listaUbicaciones) {
                    if (ubicacion.getNombre().equals(lugarSeleccionado)) {
                        ubicacionSeleccionada = ubicacion;
                        selectubi = ubicacionSeleccionada;
                        break;
                    }
                }
                if (ubicacionSeleccionada != null) {
                    etnombre.setText(ubicacionSeleccionada.getNombre());
                    txtLatitud.setText(ubicacionSeleccionada.getLatitud());
                    txtLongitud.setText(ubicacionSeleccionada.getLongitud());
                    etdescripcion.setText(ubicacionSeleccionada.getDescripcion());
                    String rutaImagen = ubicacionSeleccionada.getRutaimagen();// Obtener la ruta de la imagen de la ubicación seleccionada
                    cargarImagenEnImageView(rutaImagen);
                    LatLng ubicacion = new LatLng(Double.parseDouble(ubicacionSeleccionada.getLatitud()), Double.parseDouble(ubicacionSeleccionada.getLongitud()));
                    mostrarUbicacion(ubicacion, lugarSeleccionado);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        // Condicion para no mostrar el Spinner en caso de no haber registros
        if (listaUbicaciones.isEmpty()) {
            txtsp.setVisibility(View.INVISIBLE);
            splugares.setVisibility(View.INVISIBLE);
        }
    }

    //Inicio Codigo Camara
    public void tomarfoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);
    }
    public void abrirlaGaleria(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            rutaImagenCapturada = guardarImagenEnAlmacenamiento(image);
            imagenlugar.setImageBitmap(image);
        } else if (requestCode == 200 && resultCode == RESULT_OK && data != null) {
            Uri imagenSeleccionada = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagenSeleccionada);
                rutaImagenCapturada = guardarImagenEnAlmacenamiento(bitmap);
                imagenlugar.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void cargarImagenEnImageView(String rutaImagen) {
        if (rutaImagen != null && !rutaImagen.isEmpty()) {
            Bitmap bitmap = BitmapFactory.decodeFile(rutaImagen);
            if (bitmap != null) {
                imagenlugar.setImageBitmap(bitmap);
            } else {
                imagenlugar.setImageResource(R.drawable.imagebasee);
            }
        } else {
            imagenlugar.setImageResource(R.drawable.imagebasee);
        }
    }
    private String guardarImagenEnAlmacenamiento(Bitmap bitmap) {
        // Obtener la ruta del directorio externo de la aplicación o crear uno si no existe
        File directorio = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "DirectorioImagenes");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        // Crear un nombre de archivo único para la imagen
        String nombreArchivo = "imagen_" + System.currentTimeMillis() + ".jpg";
        // Crear el archivo en el directorio con el nombre generado
        File archivoImagen = new File(directorio, nombreArchivo);
        try {
            FileOutputStream salida = new FileOutputStream(archivoImagen);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, salida);
            salida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return archivoImagen.getAbsolutePath();
    }

    //Fin Codigo Camara


    //Metodos Onclick Agregar Eliminar Editar
    public void agregarUbi(View view) {
        agregarubicacion(rutaImagenCapturada); // Llama a agregarubicacion() pasando la ruta de la imagen
    }
    private void agregarubicacion(String rutaImagen) {
        String nombre = etnombre.getText().toString().trim();
        String longitud = txtLongitud.getText().toString().trim();
        String latitud = txtLatitud.getText().toString().trim();
        String descripcion = etdescripcion.getText().toString().trim();
        if (!nombre.isEmpty() && !longitud.isEmpty() && !latitud.isEmpty() && !rutaImagen.isEmpty()) {
            dbHelper.agregarUbicacion(nombre, longitud, latitud, descripcion, rutaImagen);
            actualizarSpinner();
            txtsp.setVisibility(View.VISIBLE);
            splugares.setVisibility(View.VISIBLE);
            Limpiar();
        } else {
            Toast.makeText(this, "Debe ingresar un Nombre, Latitud, Longitud y una imagen válida", Toast.LENGTH_SHORT).show();
        }
    }

    // Actualiza el Spinner luego de Agregar
    private void actualizarSpinner() {
        listaUbicaciones = dbHelper.getAllUbicaciones();
        ArrayList<String> nombresUbicaciones = new ArrayList<>();
        nombresUbicaciones.add("Seleccione una Opción");
        for (ubicaciones ubicacion : listaUbicaciones) {
            nombresUbicaciones.add(ubicacion.getNombre());
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresUbicaciones);
        splugares.setAdapter(adaptador);
    }


    public void update(View view) {
        actualizarUbicacion(rutaImagenCapturada);
    }
    private void actualizarUbicacion(String rutaImagen) {
        if (selectubi != null) {
            String latitud = txtLatitud.getText().toString();
            String longitud = txtLongitud.getText().toString();
            String nombre = etnombre.getText().toString();
            String descripcion = etdescripcion.getText().toString();
            if (rutaImagenCapturada.isEmpty()) {
                rutaImagen = selectubi.getRutaimagen();
            }

            if (!latitud.isEmpty() && !longitud.isEmpty() && !nombre.isEmpty() && !descripcion.isEmpty()) {
                selectubi.setLatitud(latitud);
                selectubi.setLongitud(longitud);
                selectubi.setNombre(nombre);
                selectubi.setDescripcion(descripcion);
                selectubi.setRutaimagen(rutaImagen);
                dbHelper.updateUbicacion(selectubi);
                Limpiar();
                actualizarSpinner();
            } else {
                Toast.makeText(this, "Debe Rellenar Todos los Campos", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void eliminarUbi(View view) {
        eliminarUbicaciones();
    }
    public void eliminarUbicaciones() {
        if (selectubi != null) {
            String ubi = selectubi.getNombre();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Estás seguro de que deseas Eliminar la Ubicacion : " + ubi + "?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dbHelper.deleteubicacion(selectubi);
                            Limpiar();
                            actualizarSpinner();
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


    // Función para mostrar una ubicación en el mapa con un marcador
    private void mostrarUbicacion(LatLng ubicacion, String nombre) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(ubicacion).title(nombre));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);
        LatLng talca = new LatLng(-35.4257929982051, -71.68398957699537);
        mMap.addMarker(new MarkerOptions().position(talca).title("Talca"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(talca));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLatitud.setText(String.valueOf(latLng.latitude));
        txtLongitud.setText(String.valueOf(latLng.longitude));
        mMap.clear();
        LatLng talca = new LatLng(latLng.latitude, latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(talca).title("Talca"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(talca));
        etnombre.setText("");
        etdescripcion.setText("");
        imagenlugar.setImageResource(R.drawable.imagebasee);
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLatitud.setText(String.valueOf(latLng.latitude));
        txtLongitud.setText(String.valueOf(latLng.longitude));
        mMap.clear();
        LatLng talca = new LatLng(latLng.latitude, latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(talca).title("Talca"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(talca));
    }


    public void Limpiar() {
        txtLatitud.setText("");
        txtLongitud.setText("");
        etnombre.setText("");
        etdescripcion.setText("");
        imagenlugar.setImageResource(R.drawable.imagebasee);
        etnombre.requestFocus();
    }

    public void irAencuesta(View view){
        Intent intent = new Intent(MainActivity.this, EncuestaSatisfaccion.class);
        intent.putExtra("nombreUsuario", nombreUsuario);
        startActivity(intent);
    }

    // Metodo click sobre la imagen
    public void mostrarcompleta(View view){
        if (selectubi != null) {
            String rutaImagen = selectubi.getRutaimagen();
            if (!rutaImagen.isEmpty()) {
                Intent intent = new Intent(this, imagenCompleta.class);
                intent.putExtra("rutaImagen", rutaImagen);
                startActivity(intent);
            } else {
                Toast.makeText(this, "No hay imagen disponible para mostrar a pantalla completa", Toast.LENGTH_SHORT).show();
            }
        }
    }
}