/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partidos;

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
public class partidosDao {

    // lamada a la clase conexion, para iniciar la base de datos como objeto.
    private final String tabla = "partidos";
    private final String id_tabla = "id_partido";
    private conexion con;

    public partidosDao(conexion con) {
        this.con = con;
    }

    public boolean insertar(partidosBean pb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.insertar_" + tabla + "(?,?,?,?,?,?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, pb.getId_partido());
            ps.setInt(2, pb.getId_sesion());
            ps.setInt(3, pb.getId_modalidad());
            ps.setInt(4, pb.getId_fase());
            ps.setInt(5, pb.getId_arbitro());
            ps.setInt(6, pb.getId_ganador());
            ps.setInt(7, pb.getId_perdedor());
            ps.setString(8, String.valueOf(pb.getFecha()));
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

    public boolean actualizar(partidosBean pb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.actualizar_" + tabla + "(?,?,?,?,?,?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, pb.getId_partido());
            ps.setInt(2, pb.getId_sesion());
            ps.setInt(3, pb.getId_modalidad());
            ps.setInt(4, pb.getId_fase());
            ps.setInt(5, pb.getId_arbitro());
            ps.setInt(6, pb.getId_ganador());
            ps.setInt(7, pb.getId_perdedor());
            ps.setString(8, String.valueOf(pb.getFecha()));
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

    public List<partidosBean> consultar(partidosBean pb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultar_" + tabla + "()";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<partidosBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                pb = new partidosBean(rs.getInt(1));
                pb.setId_sesion(rs.getInt(2));
                pb.setId_modalidad(rs.getInt(3));
                pb.setId_fase(rs.getInt(4));
                pb.setId_arbitro(rs.getInt(5));
                pb.setId_ganador(rs.getInt(6));
                pb.setId_perdedor(rs.getInt(7));
                pb.setFecha(rs.getDate(8));
                pb.setSesion(rs.getString(9));
                pb.setModalidad(rs.getString(10));
                pb.setFase(rs.getString(11));
                pb.setArbitro(rs.getString(12));
                pb.setGandor(rs.getString(13));
                pb.setPernedor(rs.getString(14));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(pb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<partidosBean> lista = new LinkedList<>();
            pb = new partidosBean(0);
            pb.setSesion("Ocurrio un error al revisar la tabla");
            lista.add(pb);
            return lista;
        }
    }

    public List<partidosBean> consultarId(partidosBean pb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultarId_" + tabla + "(?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, pb.getId_partido());
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<partidosBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                pb = new partidosBean(rs.getInt(1));
                pb.setId_sesion(rs.getInt(2));
                pb.setId_modalidad(rs.getInt(3));
                pb.setId_fase(rs.getInt(4));
                pb.setId_arbitro(rs.getInt(5));
                pb.setId_ganador(rs.getInt(6));
                pb.setId_perdedor(rs.getInt(7));
                pb.setFecha(rs.getDate(8));
                pb.setSesion(rs.getString(9));
                pb.setModalidad(rs.getString(10));
                pb.setFase(rs.getString(11));
                pb.setArbitro(rs.getString(12));
                pb.setGandor(rs.getString(13));
                pb.setPernedor(rs.getString(14));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(pb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer ID dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<partidosBean> lista = new LinkedList<>();
            pb = new partidosBean(0);
            pb.setSesion("Ocurrio un error al revisar la tabla");
            lista.add(pb);
            return lista;
        }
    }

    public boolean eliminar(partidosBean pb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "delete from torneo_gs." + tabla + " where " + id_tabla + "=?";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, pb.getId_partido());
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
