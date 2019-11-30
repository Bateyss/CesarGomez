/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrenadores;

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
public class entrenadoresDao {
    // lamada a la clase conexion, para iniciar la base de datos como objeto.
    private final String tabla = "entrenadores";
    private final String id_tabla= "id_entrenador";
    private conexion con;

    public entrenadoresDao(conexion con) {
        this.con = con;
    }
    public boolean insertar(entrenadoresBean eb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.insertar_" + tabla + "(?,?,?,?,?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, eb.getId_entrenador());
            ps.setString(2, eb.getEntrenador());
            ps.setInt(3, eb.getId_jugador());
            ps.setInt(4, eb.getId_nacionalidad());
            ps.setInt(5, eb.getId_sesion());
            ps.setString(6, String.valueOf(eb.getFecha_inicio()));
            ps.setString(7, String.valueOf(eb.getFecha_fin()));
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
    public boolean actualizar(entrenadoresBean eb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.actualizar_" + tabla + "(?,?,?,?,?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, eb.getId_entrenador());
            ps.setString(2, eb.getEntrenador());
            ps.setInt(3, eb.getId_jugador());
            ps.setInt(4, eb.getId_nacionalidad());
            ps.setInt(5, eb.getId_sesion());
            ps.setString(6, String.valueOf(eb.getFecha_inicio()));
            ps.setString(7, String.valueOf(eb.getFecha_fin()));
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
    public List<entrenadoresBean> consultar(entrenadoresBean eb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultar_" + tabla + "()";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<entrenadoresBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {                
                eb = new entrenadoresBean(rs.getInt(1));
                eb.setEntrenador(rs.getString(2));
                eb.setId_jugador(rs.getInt(3));
                eb.setId_nacionalidad(rs.getInt(4));
                eb.setId_sesion(rs.getInt(5));
                eb.setFecha_inicio(rs.getDate(6));
                eb.setFecha_fin(rs.getDate(7));
                eb.setJugador(rs.getString(8));
                eb.setTorneo(rs.getString(9));
                eb.setNacionalidad(rs.getString(10));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(eb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<entrenadoresBean> lista = new LinkedList<>();
            eb = new entrenadoresBean(0);
            eb.setEntrenador("Ocurrio un error");
            eb.setJugador("al revisar la tabla");
            lista.add(eb);
            return lista;
        }
    }
    public List<entrenadoresBean> consultarId(entrenadoresBean eb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultarId_" + tabla + "(?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
             // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, eb.getId_entrenador());
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<entrenadoresBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {                
                eb = new entrenadoresBean(rs.getInt(1));
                eb.setEntrenador(rs.getString(2));
                eb.setId_jugador(rs.getInt(3));
                eb.setId_nacionalidad(rs.getInt(4));
                eb.setId_sesion(rs.getInt(5));
                eb.setFecha_inicio(rs.getDate(6));
                eb.setFecha_fin(rs.getDate(7));
                eb.setJugador(rs.getString(8));
                eb.setTorneo(rs.getString(9));
                eb.setNacionalidad(rs.getString(10));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(eb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer ID dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<entrenadoresBean> lista = new LinkedList<>();
            eb = new entrenadoresBean(0);
            eb.setEntrenador("Ocurrio un error");
            eb.setJugador("al revisar la tabla");
            lista.add(eb);
            return lista;
        }
    }
    public boolean eliminar(entrenadoresBean eb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "delete from torneo_gs." + tabla + " where "+id_tabla+"=?";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
             // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, eb.getId_entrenador());
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
