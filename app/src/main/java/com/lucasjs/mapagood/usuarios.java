package com.lucasjs.mapagood;

public class usuarios {
    private long id;
    private String nombre;
    private String contraseña;
    private String tipoUsuario;


    public usuarios(long id, String nombre, String contraseña, String tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
