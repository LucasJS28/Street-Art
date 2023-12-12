package com.lucasjs.mapagood;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    private Context context;

    private static final String DATABASE_NAME="DbbUbicacionesssssssssss";
    private static final int DATABASE_VERSION = 2; // Cambiar la versión de la base de datos
    public static final String TABLE_UBICACIONES="ubicaciones";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_NOMBRE="nombre";
    public static final String COLUMN_LONGITUD="longitud";
    public static final String COLUMN_LATITUD="latitud";
    public static final String COLUMN_DESCRIPCION="descripcion";
    public static final String COLUMN_IMAGEN = "imagen";

    // Usuarios
    public static final String TABLE_USUARIOS = "usuarios";
    public static final String COLUMN_ID_USUARIO = "_id";
    public static final String COLUMN_NOMBRE_USUARIO = "nombre_usuario";
    public static final String COLUMN_CONTRASEÑA = "contraseña";
    public static final String COLUMN_TIPO_USUARIO = "tipo_usuario";

    // Encuesta

    public static final String TABLE_ENCUESTA = "encuesta";
    public static final String COLUMN_ID_ENCUESTA = "_id";
    public static final String COLUMN_NOMBREUSUARIO = "nombreUsu";
    public static final String COLUMN_SATISFECHO = "satisfecho";
    public static final String COLUMN_RECOMENDACION = "recomendacion";
    public static final String COLUMN_COMENTARIOS = "comentarios";
    public static final String COLUMN_NOMBRE_OPCIONAL = "nombre_opcional";
    public static final String COLUMN_CORREO_OPCIONAL = "correo_opcional";
    public static final String COLUMN_TELEFONO_OPCIONAL = "telefono_opcional";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_UBICACIONES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE + " TEXT, " +
                    COLUMN_LONGITUD + " TEXT, " +
                    COLUMN_LATITUD + " TEXT, " +
                    COLUMN_DESCRIPCION + " TEXT, " +
                    COLUMN_IMAGEN + " TEXT " +  ");";
    private static final String TABLE_USUARIOS_CREATE =
            "CREATE TABLE " + TABLE_USUARIOS + " (" +
                    COLUMN_ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE_USUARIO + " TEXT, " +
                    COLUMN_CONTRASEÑA + " TEXT, " +
                    COLUMN_TIPO_USUARIO + " TEXT);";
    private static final String TABLE_ENCUESTA_CREATE =
            "CREATE TABLE " + TABLE_ENCUESTA + " (" +
                    COLUMN_ID_ENCUESTA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBREUSUARIO + " TEXT, " +
                    COLUMN_SATISFECHO + " TEXT, " +
                    COLUMN_RECOMENDACION + " TEXT, " +
                    COLUMN_COMENTARIOS + " TEXT, " +
                    COLUMN_NOMBRE_OPCIONAL + " TEXT, " +
                    COLUMN_CORREO_OPCIONAL + " TEXT, " +
                    COLUMN_TELEFONO_OPCIONAL + " TEXT " +  ");";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_USUARIOS_CREATE);
        db.execSQL(TABLE_ENCUESTA_CREATE);

        // Añadir cuentas Admin
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE_USUARIO, "Lucasxdd");
        values.put(COLUMN_CONTRASEÑA, "123");
        values.put(COLUMN_TIPO_USUARIO, "admin");
        db.insert(TABLE_USUARIOS, null, values);

        ContentValues valuees = new ContentValues();
        valuees.put(COLUMN_NOMBRE_USUARIO, "Lucas");
        valuees.put(COLUMN_CONTRASEÑA, "123");
        valuees.put(COLUMN_TIPO_USUARIO, "usuario");
        db.insert(TABLE_USUARIOS, null, valuees);

        // Obtener el bitmap de la imagen en el directorio drawable
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.gogeta);

        // Guardar el bitmap en almacenamiento y obtener la ruta
        String rutaImagen1 = guardarImagenEnAlmacenamiento(bitmap1);

        ContentValues locationValues = new ContentValues();
        locationValues.put(COLUMN_NOMBRE, "Gogeta");
        locationValues.put(COLUMN_LONGITUD, "-71.6737777367");
        locationValues.put(COLUMN_LATITUD, "-35.429224493");
        locationValues.put(COLUMN_DESCRIPCION, "Grafiti de Dragon Ball");
        locationValues.put(COLUMN_IMAGEN, rutaImagen1); // Guardar la ruta en la base de datos
        db.insert(TABLE_UBICACIONES, null, locationValues);

        // Obtener el bitmap de otra imagen en el directorio drawable
        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.gragoku);

        // Guardar el bitmap en almacenamiento y obtener la ruta
        String rutaImagen2 = guardarImagenEnAlmacenamiento(bitmap2);

        ContentValues locationValues2 = new ContentValues();
        locationValues2.put(COLUMN_NOMBRE, "Goku");
        locationValues2.put(COLUMN_LONGITUD, "-71.6843282059");
        locationValues2.put(COLUMN_LATITUD, "-35.432111718");
        locationValues2.put(COLUMN_DESCRIPCION, "Grafiti de Goku");
        locationValues2.put(COLUMN_IMAGEN, rutaImagen2); // Guardar la ruta en la base de datos
        db.insert(TABLE_UBICACIONES, null, locationValues2);

        // Obtener el bitmap de otra imagen en el directorio drawable
        Bitmap bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.eljoker);

        // Guardar el bitmap en almacenamiento y obtener la ruta
        String rutaimagen3 = guardarImagenEnAlmacenamiento(bitmap3);

        ContentValues locationValues3 = new ContentValues();
        locationValues3.put(COLUMN_NOMBRE, "Mural DC");
        locationValues3.put(COLUMN_LONGITUD, "-71.6650102660");
        locationValues3.put(COLUMN_LATITUD, "-35.443803691");
        locationValues3.put(COLUMN_DESCRIPCION, "Grafiti de personajes de DC");
        locationValues3.put(COLUMN_IMAGEN, rutaimagen3); // Guardar la ruta en la base de datos
        db.insert(TABLE_UBICACIONES, null, locationValues3);

        // Obtener el bitmap de otra imagen en el directorio drawable
        Bitmap bitmap4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.gohanygoku);

        // Guardar el bitmap en almacenamiento y obtener la ruta
        String rutaimagen4 = guardarImagenEnAlmacenamiento(bitmap4);

        ContentValues locationValues4 = new ContentValues();
        locationValues4.put(COLUMN_NOMBRE, "Kamehameha Padre e Hijo");
        locationValues4.put(COLUMN_LONGITUD, "-71.6533081233");
        locationValues4.put(COLUMN_LATITUD, "-35.409921477");
        locationValues4.put(COLUMN_DESCRIPCION, "Kamehameha Padre e Hijo Saga Cell Dragon Ball");
        locationValues4.put(COLUMN_IMAGEN, rutaimagen4); // Guardar la ruta en la base de datos
        db.insert(TABLE_UBICACIONES, null, locationValues4);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UBICACIONES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENCUESTA);
        onCreate(db);
    }

    public void agregarUbicacion(String nombre, String longitud, String latitud, String descripcion, String rutaImagen) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_LONGITUD, longitud);
        values.put(COLUMN_LATITUD, latitud);
        values.put(COLUMN_DESCRIPCION, descripcion);
        values.put(COLUMN_IMAGEN, rutaImagen); // Guardar la referencia de la imagen
        db.insert(TABLE_UBICACIONES, null, values);
        db.close();
    }

    public List<ubicaciones> getAllUbicaciones() {
        List<ubicaciones> ubicaciones = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_UBICACIONES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") ubicaciones ubicacion = new ubicaciones(
                        cursor.getLong(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LONGITUD)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LATITUD)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEN))
                );
                ubicaciones.add(ubicacion);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ubicaciones;
    }
    public void deleteubicacion(ubicaciones ubi){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_UBICACIONES, COLUMN_ID+" = ? ",new String[]{String.valueOf(ubi.getId())});
        db.close();
    }

    public int updateUbicacion(ubicaciones ubi){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues vales= new ContentValues();
        vales.put(COLUMN_LATITUD,ubi.getLatitud());
        vales.put(COLUMN_LONGITUD,ubi.getLongitud());
        vales.put(COLUMN_NOMBRE,ubi.getNombre());
        vales.put(COLUMN_DESCRIPCION,ubi.getDescripcion());
        vales.put(COLUMN_IMAGEN,ubi.getRutaimagen());
        return db.update(TABLE_UBICACIONES,vales,COLUMN_ID + " = ? ",new String[]{String.valueOf(ubi.getId())});
    }


    // Método para registrar un nuevo usuario con tipo de usuario
    public boolean registrarUsuario(String nombreUsuario, String contraseña, String tipoUsuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE_USUARIO, nombreUsuario);
        values.put(COLUMN_CONTRASEÑA, contraseña);
        values.put(COLUMN_TIPO_USUARIO, tipoUsuario); // Agregar el tipo de usuario

        long resultado = db.insert(TABLE_USUARIOS, null, values);
        db.close();

        return resultado != -1; // Retorna true si se ha insertado correctamente
    }

    // Método para verificar credenciales de usuario y su tipo
    public String verificarCredenciales(String nombreUsuario, String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_TIPO_USUARIO};
        String selection = COLUMN_NOMBRE_USUARIO + "=? AND " + COLUMN_CONTRASEÑA + "=?";
        String[] selectionArgs = {nombreUsuario, contraseña};

        Cursor cursor = db.query(TABLE_USUARIOS, columns, selection, selectionArgs, null, null, null);
        String tipoUsuario = null;
        if (cursor.moveToFirst()) {
            tipoUsuario = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_USUARIO));
        }
        cursor.close();
        db.close();

        return tipoUsuario; // Retorna el tipo de usuario encontrado
    }

    public List<usuarios> getAllusuarios() {
        List<usuarios> usuarios = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_USUARIOS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") usuarios usu = new usuarios(
                        cursor.getLong(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_USUARIO)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CONTRASEÑA)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_USUARIO))

                );
                usuarios.add(usu);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return usuarios;
    }

    public void deleteusuario(usuarios usu){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_USUARIOS, COLUMN_ID+" = ? ",new String[]{String.valueOf(usu.getId())});
        db.close();
    }

    public int actualizarUsuario(usuarios usu){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues vales= new ContentValues();
        vales.put(COLUMN_NOMBRE_USUARIO,usu.getNombre());
        vales.put(COLUMN_CONTRASEÑA,usu.getContraseña());
        vales.put(COLUMN_TIPO_USUARIO,usu.getTipoUsuario());
        return db.update(TABLE_USUARIOS,vales,COLUMN_ID + " = ? ",new String[]{String.valueOf(usu.getId())});
    }

    public List<usuarios> buscarUsuarios(String searchtext) { //Version Mejorada en caso de que el edittext este vacio
        String selectQuery;
        List<usuarios> usuarios = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        if (searchtext.isEmpty()) {
            selectQuery = "SELECT * FROM " + TABLE_USUARIOS;

        } else{
            selectQuery = "SELECT * FROM " + TABLE_USUARIOS+" WHERE "+COLUMN_NOMBRE_USUARIO+" LIKE '%"+searchtext+"%'"; // Resultado que Contenga lo ingresado
            //selectQuery = "SELECT * FROM " + TABLE_USUARIOS + " WHERE " + COLUMN_NOMBRE_USUARIO + " = '" + searchtext + "'"; // Resultado tal Cual
            // selectQuery = "SELECT * FROM " + TABLE_PRODUCTO + " ORDER BY " + COLUMN_NOMBRE + " DESC"; //descendente
            // selectQuery = "SELECT * FROM " + TABLE_PRODUCTO + " ORDER BY " + COLUMN_NOMBRE + " ASC"; //ascendente
            // selectQuery = "SELECT * FROM " + TABLE_PRODUCTO + " WHERE " + COLUMN_NOMBRE + " LIKE '%" + searchtext + "%' OR " + COLUMN_PRECIO + " LIKE '%" + searchtext + "%'"; // Si es que contiene el elemento
        }
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") usuarios usu = new usuarios(
                        cursor.getLong(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_USUARIO)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CONTRASEÑA)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_USUARIO))
                );
                usuarios.add(usu);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return usuarios;
    }


    // Seccion Encuesta

    public void agregarRespuestasEncuesta(String nombreUsuario, String satisfecho, String recomendacion, String comentarios,
                                          String nombre, String correo, String telefono) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBREUSUARIO, nombreUsuario);
        values.put(COLUMN_SATISFECHO, satisfecho);
        values.put(COLUMN_RECOMENDACION, recomendacion);
        values.put(COLUMN_COMENTARIOS, comentarios);
        values.put(COLUMN_NOMBRE_OPCIONAL, nombre);
        values.put(COLUMN_CORREO_OPCIONAL, correo);
        values.put(COLUMN_TELEFONO_OPCIONAL, telefono);
        db.insert(TABLE_ENCUESTA, null, values);
        db.close();
    }

    public List<encuesta> getAllEncuestas() {
        List<encuesta> encuesta = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_ENCUESTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") encuesta encu = new encuesta(
                        cursor.getLong(cursor.getColumnIndex(COLUMN_ID_ENCUESTA)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NOMBREUSUARIO)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_SATISFECHO)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_RECOMENDACION)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_COMENTARIOS)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_OPCIONAL)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CORREO_OPCIONAL)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO_OPCIONAL))
                );
                encuesta.add(encu);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return encuesta;
    }

    private String guardarImagenEnAlmacenamiento(Bitmap bitmap) {
        File directorio = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "DirectorioImagenes");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        // Crear un nombre de archivo único para la imagen
        String nombreArchivo = "imagen_" + System.currentTimeMillis() + ".jpg";
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
}