/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nacionalidades;

/**
 *
 * @author cesar.murciausam
 */
public class nacionalidadesBean {
    private int id_nacionalidad;
    private String nacionalidad;
    private int id_pais;
    // impresion 
    private String pais;

    public nacionalidadesBean(int id_nacionalidad) {
        this.id_nacionalidad = id_nacionalidad;
    }

    public int getId_nacionalidad() {
        return id_nacionalidad;
    }

    public void setId_nacionalidad(int id_nacionalidad) {
        this.id_nacionalidad = id_nacionalidad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
}
