/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbitros;

/**
 *
 * @author cesar.murciausam
 */
public class arbitrosBean {
    private int id_arbitro;
    private String arbitro;
    private int id_nacionalidad;
    // impresion
    private String nacionalidad;

    public arbitrosBean(int id_arbitro) {
        this.id_arbitro = id_arbitro;
    }

    public int getId_arbitro() {
        return id_arbitro;
    }

    public void setId_arbitro(int id_arbitro) {
        this.id_arbitro = id_arbitro;
    }

    public String getArbitro() {
        return arbitro;
    }

    public void setArbitro(String arbitro) {
        this.arbitro = arbitro;
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
    
}
