package com.lucasjs.mapagood;

public class encuesta {
    private long id;
    private String nombreUsuario;
    private String satisfecho;
    private String recomendacion;
    private String comentarios;
    private String nombreo;
    private String correoo;
    private String Telefonoo;


    public encuesta(long id, String nombreUsuario, String satisfecho, String recomendacion, String comentarios, String nombreo, String correoo, String telefonoo) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.satisfecho = satisfecho;
        this.recomendacion = recomendacion;
        this.comentarios = comentarios;
        this.nombreo = nombreo;
        this.correoo = correoo;
        Telefonoo = telefonoo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getSatisfecho() {
        return satisfecho;
    }

    public void setSatisfecho(String satisfecho) {
        this.satisfecho = satisfecho;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getNombreo() {
        return nombreo;
    }

    public void setNombreo(String nombreo) {
        this.nombreo = nombreo;
    }

    public String getCorreoo() {
        return correoo;
    }

    public void setCorreoo(String correoo) {
        this.correoo = correoo;
    }

    public String getTelefonoo() {
        return Telefonoo;
    }

    public void setTelefonoo(String telefonoo) {
        Telefonoo = telefonoo;
    }
}
