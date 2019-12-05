/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author cesar.murciausam
 */
public class personasDao {
    base con;
    String tabla = "personas";

    public personasDao(base con) {
        this.con = con;
    }
    public boolean insertar(personasBean pb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "insert into usuarios." + tabla + " values (?,?,?,?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, pb.getId_persona());
            ps.setString(2, pb.getNombres());
            ps.setString(3, pb.getApellidos());
            ps.setInt(4, pb.getEdad());
            ps.setString(5, pb.getEmail());
            ps.setString(6, pb.getTelefono());
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
    public List<personasBean> consultar(personasBean pb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "select * from usuarios." + tabla + ";";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<personasBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {                
                pb = new personasBean(rs.getInt(1));
                pb.setNombres(rs.getString(2));
                pb.setApellidos(rs.getString(3));
                pb.setEdad(rs.getInt(4));
                pb.setEmail(rs.getString(5));
                pb.setTelefono(rs.getString(6));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(pb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<personasBean> lista = new LinkedList<>();
            pb = new personasBean(0);
            pb.setNombres("Ocurrio un error");
            pb.setApellidos("al revisar la tabla");
            lista.add(pb);
            return lista;
        }
        
    }
    public int consultarNombre(personasBean pb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "select * from usuarios." + tabla + " where nombres = ? and apellidos = ?;";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            ps.setString(1, pb.getNombres());
            ps.setString(2, pb.getApellidos());
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            int x  = 0;
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {                
                x = rs.getInt(1);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return x;
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            return 0;
        }
        
    }
}
