/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jugadores;

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
public class jugadoresDao {

    // lamada a la clase conexion, para iniciar la base de datos como objeto.
    private final String tabla = "jugadores";
    private final String id_tabla = "id_jugador";
    private conexion con;

    public jugadoresDao(conexion con) {
        this.con = con;
    }

    public boolean insertar(jugadoresBean jb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.insertar_" + tabla + "(?,?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, jb.getId_jugador());
            ps.setString(2, jb.getJugador());
            ps.setString(3, jb.getSexo());
            ps.setInt(4, jb.getId_nacionalidad());
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

    public boolean actualizar(jugadoresBean jb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.actualizar_" + tabla + "(?,?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, jb.getId_jugador());
            ps.setString(2, jb.getJugador());
            ps.setString(3, jb.getSexo());
            ps.setInt(4, jb.getId_nacionalidad());
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

    public List<jugadoresBean> consultar(jugadoresBean jb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultar_" + tabla + "()";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<jugadoresBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                jb = new jugadoresBean(rs.getInt(1));
                jb.setJugador(rs.getString(2));
                jb.setSexo(rs.getString(3));
                jb.setId_nacionalidad(rs.getInt(4));
                jb.setNacionalidad(rs.getString(5));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(jb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<jugadoresBean> lista = new LinkedList<>();
            jb = new jugadoresBean(0);
            jb.setJugador("Ocurrio un error al revisar la tabla");
            lista.add(jb);
            return lista;
        }
    }

    public List<jugadoresBean> consultarId(jugadoresBean jb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultarId_" + tabla + "(?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, jb.getId_jugador());
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<jugadoresBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                jb = new jugadoresBean(rs.getInt(1));
                jb.setJugador(rs.getString(2));
                jb.setSexo(rs.getString(3));
                jb.setId_nacionalidad(rs.getInt(4));
                jb.setNacionalidad(rs.getString(5));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(jb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer ID dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<jugadoresBean> lista = new LinkedList<>();
            jb = new jugadoresBean(0);
            jb.setJugador("Ocurrio un error al revisar la tabla");
            lista.add(jb);
            return lista;
        }
    }

    public boolean eliminar(jugadoresBean jb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "delete from torneo_gs." + tabla + " where " + id_tabla + "=?";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, jb.getId_jugador());
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
