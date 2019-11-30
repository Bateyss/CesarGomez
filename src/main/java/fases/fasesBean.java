/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fases;

/**
 *
 * @author cesar.murciausam
 */
public class fasesBean {
    private int id_fase;
    private String fase;
    private int nivel;

    public fasesBean(int id_fase) {
        this.id_fase = id_fase;
    }

    public int getId_fase() {
        return id_fase;
    }

    public void setId_fase(int id_fase) {
        this.id_fase = id_fase;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
}
