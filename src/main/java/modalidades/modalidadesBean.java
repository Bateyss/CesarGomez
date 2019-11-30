/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modalidades;

/**
 *
 * @author cesar.murciausam
 */
public class modalidadesBean {
    private int id_modalidad;
    private String modalidad;

    public modalidadesBean(int id_modalidad) {
        this.id_modalidad = id_modalidad;
    }

    public int getId_modalidad() {
        return id_modalidad;
    }

    public void setId_modalidad(int id_modalidad) {
        this.id_modalidad = id_modalidad;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
    
}
