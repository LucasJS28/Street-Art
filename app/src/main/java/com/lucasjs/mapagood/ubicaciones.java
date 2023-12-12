package com.lucasjs.mapagood;

public class ubicaciones {
    private long id;
    private String nombre;
    private String longitud;
    private String latitud;
    private String descripcion;
    private String rutaimagen;


    public ubicaciones(long id, String nombre, String longitud, String latitud, String descripcion, String rutaimagen) {
        this.id = id;
        this.nombre = nombre;
        this.longitud = longitud;
        this.latitud = latitud;
        this.descripcion = descripcion;
        this.rutaimagen = rutaimagen;
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

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaimagen() {
        return rutaimagen;
    }

    public void setRutaimagen(String rutaimagen) {
        this.rutaimagen = rutaimagen;
    }
}

