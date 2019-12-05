/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesiones;

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
public class sesionesDao {
    // lamada a la clase conexion, para iniciar la base de datos como objeto.
    private final String tabla = "sesiones";
    private final String id_tabla = "id_sesion";
    private conexion con;

    public sesionesDao(conexion con) {
        this.con = con;
    }
    public boolean insertar(sesionesBean sb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.insertar_" + tabla + "(?,?,?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, sb.getId_sesion());
            ps.setString(2, sb.getDescripcion());
            ps.setString(3, sb.getAnio());
            ps.setInt(4, sb.getId_torneo());
            ps.setInt(5, sb.getId_lugar());
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

    public boolean actualizar(sesionesBean sb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.actualizar_" + tabla + "(?,?,?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, sb.getId_sesion());
            ps.setString(2, sb.getDescripcion());
            ps.setString(3, sb.getAnio());
            ps.setInt(4, sb.getId_torneo());
            ps.setInt(5, sb.getId_lugar());
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

    public List<sesionesBean> consultar(sesionesBean sb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultar_" + tabla + "()";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<sesionesBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                sb = new sesionesBean(rs.getInt(1));
                sb.setDescripcion(rs.getString(2));
                sb.setAnio(rs.getString(3));
                sb.setId_torneo(rs.getInt(4));
                sb.setId_lugar(rs.getInt(5));
                sb.setTorneo(rs.getString(6));
                sb.setLugar(rs.getString(7));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(sb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<sesionesBean> lista = new LinkedList<>();
            sb = new sesionesBean(0);
            sb.setDescripcion("Ocurrio un error al revisar la tabla");
            lista.add(sb);
            return lista;
        }
    }

    public List<sesionesBean> consultarId(sesionesBean sb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultarId_" + tabla + "(?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, sb.getId_sesion());
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<sesionesBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                sb = new sesionesBean(rs.getInt(1));
                sb.setDescripcion(rs.getString(2));
                sb.setAnio(rs.getString(3));
                sb.setId_torneo(rs.getInt(4));
                sb.setId_lugar(rs.getInt(5));
                sb.setTorneo(rs.getString(6));
                sb.setLugar(rs.getString(7));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(sb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer ID dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<sesionesBean> lista = new LinkedList<>();
            sb = new sesionesBean(0);
            sb.setDescripcion("Ocurrio un error al revisar la tabla");
            lista.add(sb);
            return lista;
        }
    }

    public boolean eliminar(sesionesBean sb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "delete from torneo_gs." + tabla + " where " + id_tabla + "=?";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, sb.getId_sesion());
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
