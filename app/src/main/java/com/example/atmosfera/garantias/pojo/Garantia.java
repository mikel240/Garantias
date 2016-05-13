package com.example.atmosfera.garantias.pojo;

public class Garantia {

    private int idG;
    private String tipoProductoG;
    private int marcaG;
    private String modeloG;
    private String numeroSerieG;
    private String detallesG;
    private String fechaCompraG;
    private String lugarCompraG;
    private int duracionG;
    private int facturaG;

    private Marca objMarcaG;
    private Factura objFacturaG;

    public Garantia() {
    }

    public Garantia(int idG, String tipoProductoG, int marcaG, String modeloG, String numeroSerieG, String detallesG, String fechaCompraG, String lugarCompraG, int duracionG, int facturaG) {
        this.idG = idG;
        this.tipoProductoG = tipoProductoG;
        this.marcaG = marcaG;
        this.modeloG = modeloG;
        this.numeroSerieG = numeroSerieG;
        this.detallesG = detallesG;
        this.fechaCompraG = fechaCompraG;
        this.lugarCompraG = lugarCompraG;
        this.duracionG = duracionG;
        this.facturaG = facturaG;
    }

    public int getIdG() {
        return idG;
    }

    public void setIdG(int idG) {
        this.idG = idG;
    }

    public String getTipoProductoG() {
        return tipoProductoG;
    }

    public void setTipoProductoG(String tipoProductoG) {
        this.tipoProductoG = tipoProductoG;
    }

    public int getMarcaG() {
        return marcaG;
    }

    public void setMarcaG(int marcaG) {
        this.marcaG = marcaG;
    }

    public String getModeloG() {
        return modeloG;
    }

    public void setModeloG(String modeloG) {
        this.modeloG = modeloG;
    }

    public String getNumeroSerieG() {
        return numeroSerieG;
    }

    public void setNumeroSerieG(String numeroSerieG) {
        this.numeroSerieG = numeroSerieG;
    }

    public String getDetallesG() {
        return detallesG;
    }

    public void setDetallesG(String detallesG) {
        this.detallesG = detallesG;
    }

    public String getFechaCompraG() {
        return fechaCompraG;
    }

    public void setFechaCompraG(String fechaCompraG) {
        this.fechaCompraG = fechaCompraG;
    }

    public String getLugarCompraG() {
        return lugarCompraG;
    }

    public void setLugarCompraG(String lugarCompraG) {
        this.lugarCompraG = lugarCompraG;
    }

    public int getDuracionG() {
        return duracionG;
    }

    public void setDuracionG(int duracionG) {
        this.duracionG = duracionG;
    }

    public int getFacturaG() {
        return facturaG;
    }

    public void setFacturaG(int facturaG) {
        this.facturaG = facturaG;
    }

    public Marca getObjMarcaG() {
        return objMarcaG;
    }

    public void setObjMarcaG(Marca objMarcaG) {
        this.objMarcaG = objMarcaG;
    }

    public Factura getObjFacturaG() {
        return objFacturaG;
    }

    public void setObjFacturaG(Factura objFacturaG) {
        this.objFacturaG = objFacturaG;
    }
}
