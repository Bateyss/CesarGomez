/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package premios;

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
public class premiosDao {

    // lamada a la clase conexion, para iniciar la base de datos como objeto.
    private final String tabla = "partidos";
    private final String id_tabla = "id_partido";
    private conexion con;

    public premiosDao(conexion con) {
        this.con = con;
    }

    public boolean insertar(premiosBean pb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.insertar_" + tabla + "(?,?,?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, pb.getId_premio());
            ps.setString(2, pb.getPremio());
            ps.setString(3, pb.getPara());
            ps.setInt(4, pb.getId_fase());
            ps.setDouble(5, pb.getGanancia());
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

    public boolean actualizar(premiosBean pb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.actualizar_" + tabla + "(?,?,?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, pb.getId_premio());
            ps.setString(2, pb.getPremio());
            ps.setString(3, pb.getPara());
            ps.setInt(4, pb.getId_fase());
            ps.setDouble(5, pb.getGanancia());
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

    public List<premiosBean> consultar(premiosBean pb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultar_" + tabla + "()";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<premiosBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                pb = new premiosBean(rs.getInt(1));
                pb.setPremio(rs.getString(2));
                pb.setPara(rs.getString(3));
                pb.setId_fase(rs.getInt(4));
                pb.setGanancia(rs.getDouble(5));
                pb.setFase(rs.getString(6));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(pb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<premiosBean> lista = new LinkedList<>();
            pb = new premiosBean(0);
            pb.setPremio("Ocurrio un error al revisar la tabla");
            lista.add(pb);
            return lista;
        }
    }

    public List<premiosBean> consultarId(premiosBean pb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultarId_" + tabla + "(?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, pb.getId_premio());
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<premiosBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                pb = new premiosBean(rs.getInt(1));
                pb = new premiosBean(rs.getInt(1));
                pb.setPremio(rs.getString(2));
                pb.setPara(rs.getString(3));
                pb.setId_fase(rs.getInt(4));
                pb.setGanancia(rs.getDouble(5));
                pb.setFase(rs.getString(6));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(pb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer ID dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<premiosBean> lista = new LinkedList<>();
            pb = new premiosBean(0);
            pb.setPremio("Ocurrio un error al revisar la tabla");
            lista.add(pb);
            return lista;
        }
    }

    public boolean eliminar(premiosBean pb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "delete from torneo_gs." + tabla + " where " + id_tabla + "=?";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, pb.getId_premio());
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
