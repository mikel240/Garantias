package com.example.atmosfera.garantias.pojo;

public class Marca {

    private int idM;
    private String nombreM;
    private String correoM;

    public Marca() {
    }

    public Marca(int idM, String nombreM, String correoM) {
        this.idM = idM;
        this.nombreM = nombreM;
        this.correoM = correoM;
    }

    public int getIdM() {
        return idM;
    }

    public void setIdM(int idM) {
        this.idM = idM;
    }

    public String getNombreM() {
        return nombreM;
    }

    public void setNombreM(String nombreM) {
        this.nombreM = nombreM;
    }

    public String getCorreoM() {
        return correoM;
    }

    public void setCorreoM(String correoM) {
        this.correoM = correoM;
    }
}
