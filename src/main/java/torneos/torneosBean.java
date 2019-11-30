/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneos;

/**
 *
 * @author cesar.murciausam
 */
public class torneosBean {
    private int id_torneo;
    private String torneo;

    public torneosBean(int id_torneo) {
        this.id_torneo = id_torneo;
    }

    public int getId_torneo() {
        return id_torneo;
    }

    public void setId_torneo(int id_torneo) {
        this.id_torneo = id_torneo;
    }

    public String getTorneo() {
        return torneo;
    }

    public void setTorneo(String torneo) {
        this.torneo = torneo;
    }
    
}
