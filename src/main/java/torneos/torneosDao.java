/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneos;

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
public class torneosDao {
    // lamada a la clase conexion, para iniciar la base de datos como objeto.
    private final String tabla = "partidos";
    private final String id_tabla = "id_partido";
    private conexion con;

    public torneosDao(conexion con) {
        this.con = con;
    }
    public boolean insertar(torneosBean tb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.insertar_" + tabla + "(?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, tb.getId_torneo());
            ps.setString(2, tb.getTorneo());
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

    public boolean actualizar(torneosBean tb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.actualizar_" + tabla + "(?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, tb.getId_torneo());
            ps.setString(2, tb.getTorneo());
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

    public List<torneosBean> consultar(torneosBean tb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultar_" + tabla + "()";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<torneosBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                tb = new torneosBean(rs.getInt(1));
                tb.setTorneo(rs.getString(2));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(tb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<torneosBean> lista = new LinkedList<>();
            tb = new torneosBean(0);
            tb.setTorneo("Ocurrio un error al revisar la tabla");
            lista.add(tb);
            return lista;
        }
    }

    public List<torneosBean> consultarId(torneosBean tb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultarId_" + tabla + "(?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, tb.getId_torneo());
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<torneosBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                tb = new torneosBean(rs.getInt(1));
                tb.setTorneo(rs.getString(2));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(tb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer ID dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<torneosBean> lista = new LinkedList<>();
            tb = new torneosBean(0);
            tb.setTorneo("Ocurrio un error al revisar la tabla");
            lista.add(tb);
            return lista;
        }
    }

    public boolean eliminar(torneosBean tb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "delete from torneo_gs." + tabla + " where " + id_tabla + "=?";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, tb.getId_torneo());
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
