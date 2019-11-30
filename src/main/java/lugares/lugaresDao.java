/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lugares;

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
public class lugaresDao {

    // lamada a la clase conexion, para iniciar la base de datos como objeto.
    private final String tabla = "lugares";
    private final String id_tabla = "id_lugar";
    private conexion con;

    public lugaresDao(conexion con) {
        this.con = con;
    }

    public boolean insertar(lugaresBean lb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.insertar_" + tabla + "(?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, lb.getId_lugar());
            ps.setString(2, lb.getLugar());
            ps.setInt(3, lb.getId_pais());
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

    public boolean actualizar(lugaresBean lb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.actualizar_" + tabla + "(?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, lb.getId_lugar());
            ps.setString(2, lb.getLugar());
            ps.setInt(3, lb.getId_pais());
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

    public List<lugaresBean> consultar(lugaresBean lb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultar_" + tabla + "()";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<lugaresBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                lb = new lugaresBean(rs.getInt(1));
                lb.setLugar(rs.getString(2));
                lb.setId_pais(rs.getInt(3));
                lb.setPais(rs.getString(4));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(lb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<lugaresBean> lista = new LinkedList<>();
            lb = new lugaresBean(0);
            lb.setLugar("Ocurrio un error al revisar la tabla");
            lista.add(lb);
            return lista;
        }
    }

    public List<lugaresBean> consultarId(lugaresBean lb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultarId_" + tabla + "(?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, lb.getId_lugar());
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<lugaresBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                lb = new lugaresBean(rs.getInt(1));
                lb.setLugar(rs.getString(2));
                lb.setId_pais(rs.getInt(3));
                lb.setPais(rs.getString(4));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(lb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer ID dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<lugaresBean> lista = new LinkedList<>();
            lb = new lugaresBean(0);
            lb.setLugar("Ocurrio un error al revisar la tabla");
            lista.add(lb);
            return lista;
        }
    }

    public boolean eliminar(lugaresBean lb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "delete from torneo_gs." + tabla + " where " + id_tabla + "=?";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, lb.getId_lugar());
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
