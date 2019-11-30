/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paises;

/**
 *
 * @author cesar.murciausam
 */
public class paisesBean {
    private int id_pais;
    private String pais;

    public paisesBean(int id_pais) {
        this.id_pais = id_pais;
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
