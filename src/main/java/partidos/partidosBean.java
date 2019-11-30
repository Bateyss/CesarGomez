/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partidos;

import java.sql.Date;

/**
 *
 * @author cesar.murciausam
 */
public class partidosBean {
    private int id_partido;
    private int id_sesion;
    private int id_modalidad;
    private int id_fase;
    private int id_arbitro;
    private int id_ganador;
    private int id_perdedor;
    private Date fecha;
    // impresion
    private String sesion;
    private String modalidad;
    private String fase;
    private String arbitro;
    private String gandor;
    private String pernedor;

    public partidosBean(int id_partido) {
        this.id_partido = id_partido;
    }

    public int getId_partido() {
        return id_partido;
    }

    public void setId_partido(int id_partido) {
        this.id_partido = id_partido;
    }

    public int getId_sesion() {
        return id_sesion;
    }

    public void setId_sesion(int id_sesion) {
        this.id_sesion = id_sesion;
    }

    public int getId_modalidad() {
        return id_modalidad;
    }

    public void setId_modalidad(int id_modalidad) {
        this.id_modalidad = id_modalidad;
    }

    public int getId_fase() {
        return id_fase;
    }

    public void setId_fase(int id_fase) {
        this.id_fase = id_fase;
    }

    public int getId_arbitro() {
        return id_arbitro;
    }

    public void setId_arbitro(int id_arbitro) {
        this.id_arbitro = id_arbitro;
    }

    public int getId_ganador() {
        return id_ganador;
    }

    public void setId_ganador(int id_ganador) {
        this.id_ganador = id_ganador;
    }

    public int getId_perdedor() {
        return id_perdedor;
    }

    public void setId_perdedor(int id_perdedor) {
        this.id_perdedor = id_perdedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getArbitro() {
        return arbitro;
    }

    public void setArbitro(String arbitro) {
        this.arbitro = arbitro;
    }

    public String getGandor() {
        return gandor;
    }

    public void setGandor(String gandor) {
        this.gandor = gandor;
    }

    public String getPernedor() {
        return pernedor;
    }

    public void setPernedor(String pernedor) {
        this.pernedor = pernedor;
    }
    
}
