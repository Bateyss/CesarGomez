/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fases;

import conexion.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author cesar.murciausam
 */
public class fasesDao {

    // lamada a la clase conexion, para iniciar la base de datos como objeto.
    private final String tabla = "fases";
    private final String id_tabla = "id_fase";
    private conexion con;

    public fasesDao(conexion con) {
        this.con = con;
    }
    public boolean insertar(fasesBean fb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.insertar_" + tabla + "(?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, fb.getId_fase());
            ps.setString(2, fb.getFase());
            ps.setInt(3, fb.getNivel());
            // se ejecuta la accion de almacenar con los datos en memoria
            ps.executeUpdate();
            // si las operaciones anteriores funcionaron, se retorna un dato verdadero
            return true;
        } catch (SQLException e) {
            System.out.println("Error en insertar dao de: " + tabla);
            System.out.println("Error de :" + e);
            return false;
        }
    }

    public boolean actualizar(fasesBean fb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.actualizar_" + tabla + "(?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, fb.getId_fase());
            ps.setString(2, fb.getFase());
            ps.setInt(3, fb.getNivel());
            // se ejecuta la accion de almacenar con los datos en memoria
            ps.executeUpdate();
            // si las operaciones anteriores funcionaron, se retorna un dato verdadero
            return true;
        } catch (SQLException e) {
            System.out.println("Error en actualizar dao de: " + tabla);
            System.out.println("Error de :" + e);
            return false;
        }
    }

    public List<fasesBean> consultar(fasesBean fb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultar_" + tabla + "()";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<fasesBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                fb = new fasesBean(rs.getInt(1));
                fb.setFase(rs.getString(2));
                fb.setNivel(rs.getInt(3));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(fb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<fasesBean> lista = new LinkedList<>();
            fb = new fasesBean(0);
            fb.setFase("Ocurrio un error al revisar la tabla");
            lista.add(fb);
            return lista;
        }
    }

    public List<fasesBean> consultarId(fasesBean fb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultarId_" + tabla + "(?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, fb.getId_fase());
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<fasesBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                fb = new fasesBean(rs.getInt(1));
                fb.setFase(rs.getString(2));
                fb.setNivel(rs.getInt(3));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(fb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer ID dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<fasesBean> lista = new LinkedList<>();
            fb = new fasesBean(0);
            fb.setFase("Ocurrio un error al revisar la tabla");
            lista.add(fb);
            return lista;
        }
    }

    public boolean eliminar(fasesBean fb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "delete from torneo_gs." + tabla + " where " + id_tabla + "=?";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, fb.getId_fase());
            // se ejecuta la accion de almacenar con los datos en memoria
            ps.executeUpdate();
            // si las operaciones anteriores funcionaron, se retorna un dato verdadero
            return true;
        } catch (SQLException e) {
            System.out.println("Error en actualizar dao de: " + tabla);
            System.out.println("Error de :" + e);
            return false;
        }
    }
}
