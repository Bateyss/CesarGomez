/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package premios;

/**
 *
 * @author cesar.murciausam
 */
public class premiosBean {
    private int id_premio;
    private String premio;
    private String para;
    private int id_fase;
    private double ganancia;
    // impresion
    private String fase;

    public premiosBean(int id_premio) {
        this.id_premio = id_premio;
    }

    public int getId_premio() {
        return id_premio;
    }

    public void setId_premio(int id_premio) {
        this.id_premio = id_premio;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public int getId_fase() {
        return id_fase;
    }

    public void setId_fase(int id_fase) {
        this.id_fase = id_fase;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }
    
}
