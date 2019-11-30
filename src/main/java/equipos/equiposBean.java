/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipos;

/**
 *
 * @author cesar.murciausam
 */
public class equiposBean {
    private int id_equipo;
    private String genero;
    private int id_jugador1;
    private int id_jugador2;
    //impresion
    private String jugador1;
    private String jugador2;

    public equiposBean(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getId_jugador1() {
        return id_jugador1;
    }

    public void setId_jugador1(int id_jugador1) {
        this.id_jugador1 = id_jugador1;
    }

    public int getId_jugador2() {
        return id_jugador2;
    }

    public void setId_jugador2(int id_jugador2) {
        this.id_jugador2 = id_jugador2;
    }

    public String getJugador1() {
        return jugador1;
    }

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }
    
}
