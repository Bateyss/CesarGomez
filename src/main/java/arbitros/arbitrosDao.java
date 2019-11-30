/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbitros;

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
public class arbitrosDao {

    // lamada a la clase conexion, para iniciar la base de datos como objeto.
    private final String tabla = "arbitros";
    private final String id_tabla= "id_arbitro";
    private conexion con;

    public arbitrosDao(conexion con) {
        this.con = con;
    }

    public boolean insertar(arbitrosBean ab) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.insertar_" + tabla + "(?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, ab.getId_arbitro());
            ps.setString(2, ab.getArbitro());
            ps.setInt(3, ab.getId_nacionalidad());
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
    public boolean actualizar(arbitrosBean ab) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.actualizar_" + tabla + "(?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, ab.getId_arbitro());
            ps.setString(2, ab.getArbitro());
            ps.setInt(3, ab.getId_nacionalidad());
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
    public List<arbitrosBean> consultar(arbitrosBean ab) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultar_" + tabla + "()";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<arbitrosBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {                
                ab = new arbitrosBean(rs.getInt(1));
                ab.setArbitro(rs.getString(2));
                ab.setId_nacionalidad(rs.getInt(3));
                ab.setNacionalidad(rs.getString(4));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(ab);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<arbitrosBean> lista = new LinkedList<>();
            ab = new arbitrosBean(0);
            ab.setArbitro("Ocurrio un error");
            ab.setNacionalidad("al revisar la tabla");
            lista.add(ab);
            return lista;
        }
    }
    public List<arbitrosBean> consultarId(arbitrosBean ab) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultarId_" + tabla + "(?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
             // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, ab.getId_arbitro());
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<arbitrosBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {                
                ab = new arbitrosBean(rs.getInt(1));
                ab.setArbitro(rs.getString(2));
                ab.setId_nacionalidad(rs.getInt(3));
                ab.setNacionalidad(rs.getString(4));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(ab);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer ID dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<arbitrosBean> lista = new LinkedList<>();
            ab = new arbitrosBean(0);
            ab.setArbitro("Ocurrio un error");
            ab.setNacionalidad("al revisar la tabla");
            lista.add(ab);
            return lista;
        }
    }
    public boolean eliminar(arbitrosBean ab) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "delete from torneo_gs." + tabla + " where "+id_tabla+"=?";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
             // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, ab.getId_arbitro());
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
