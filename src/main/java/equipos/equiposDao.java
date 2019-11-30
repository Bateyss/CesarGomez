/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipos;

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
public class equiposDao {

    // lamada a la clase conexion, para iniciar la base de datos como objeto.
    private final String tabla = "equipos";
    private final String id_tabla = "id_equipo";
    private conexion con;

    public equiposDao(conexion con) {
        this.con = con;
    }

    public boolean insertar(equiposBean eb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.insertar_" + tabla + "(?,?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, eb.getId_equipo());
            ps.setString(2, eb.getGenero());
            ps.setInt(3, eb.getId_jugador1());
            ps.setInt(4, eb.getId_jugador2());
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

    public boolean actualizar(equiposBean eb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.actualizar_" + tabla + "(?,?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, eb.getId_equipo());
            ps.setString(2, eb.getGenero());
            ps.setInt(3, eb.getId_jugador1());
            ps.setInt(4, eb.getId_jugador2());
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

    public List<equiposBean> consultar(equiposBean eb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultar_" + tabla + "()";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<equiposBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                eb = new equiposBean(rs.getInt(1));
                eb.setGenero(rs.getString(2));
                eb.setId_jugador1(rs.getInt(3));
                eb.setId_jugador2(rs.getInt(4));
                eb.setJugador1(rs.getString(5));
                eb.setJugador2(rs.getString(6));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(eb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<equiposBean> lista = new LinkedList<>();
            eb = new equiposBean(0);
            eb.setGenero("Ocurrio un error");
            eb.setJugador1("al revisar la tabla");
            lista.add(eb);
            return lista;
        }
    }

    public List<equiposBean> consultarId(equiposBean eb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultarId_" + tabla + "(?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, eb.getId_equipo());
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<equiposBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                eb = new equiposBean(rs.getInt(1));
                eb.setGenero(rs.getString(2));
                eb.setId_jugador1(rs.getInt(3));
                eb.setId_jugador2(rs.getInt(4));
                eb.setJugador1(rs.getString(5));
                eb.setJugador2(rs.getString(6));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(eb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer ID dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<equiposBean> lista = new LinkedList<>();
            eb = new equiposBean(0);
            eb.setGenero("Ocurrio un error");
            eb.setJugador1("al revisar la tabla");
            lista.add(eb);
            return lista;
        }
    }

    public boolean eliminar(equiposBean eb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "delete from torneo_gs." + tabla + " where " + id_tabla + "=?";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, eb.getId_equipo());
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
