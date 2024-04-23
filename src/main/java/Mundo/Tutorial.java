/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mundo;

/**
 *
 * @author German
 */
public class Tutorial {

    /*--------------------------------------------------------------------------
     * Atributos
     *--------------------------------------------------------------------------*/
    private int id;
    private String categoriaId;
    private String url;
    private int prioridadLectura;
    private String estado;

    /*-------------------------------------------------------------------------
     *Metodos 
     *----------------------------------------------------------------------*/
    public Tutorial(String categoriaId, String url, int prioridadLectura, String estado) {
        this.categoriaId = categoriaId;
        this.url = url;
        this.prioridadLectura = prioridadLectura;
        this.estado = estado;
    }

    public Tutorial() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPrioridadLectura() {
        return prioridadLectura;
    }

    public void setPrioridadLectura(int prioridadLectura) {
        this.prioridadLectura = prioridadLectura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
