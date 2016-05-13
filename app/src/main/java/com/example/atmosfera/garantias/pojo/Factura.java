package com.example.atmosfera.garantias.pojo;


public class Factura {

    private int idF;
    private String rutaArchivoF;
    private String formatoArchivoF;

    public Factura() {
    }

    public Factura(int idF, String rutaArchivoF, String formatoArchivoF) {
        this.idF = idF;
        this.rutaArchivoF = rutaArchivoF;
        this.formatoArchivoF = formatoArchivoF;
    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public String getRutaArchivoF() {
        return rutaArchivoF;
    }

    public void setRutaArchivoF(String rutaArchivoF) {
        this.rutaArchivoF = rutaArchivoF;
    }

    public String getFormatoArchivoF() {
        return formatoArchivoF;
    }

    public void setFormatoArchivoF(String formatoArchivoF) {
        this.formatoArchivoF = formatoArchivoF;
    }
}
