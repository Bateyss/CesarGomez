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
public class usuariosDao {

    base con;
    String tabla = "usuarios";

    public usuariosDao(base con) {
        this.con = con;
    }

    public boolean insertar(usuariosBean ub) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "insert into usuarios." + tabla + " values (?,?,?,?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, ub.getId_usuario());
            ps.setString(2, ub.getUsuario());
            ps.setString(3, ub.getPass());
            ps.setString(4, ub.getEstado());
            ps.setInt(5, ub.getId_persona());
            ps.setInt(6, ub.getId_tipo());
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

    public boolean actualizar(usuariosBean ub) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "ubdate usuarios." + tabla + " set "
                    + " usuario = ?, pass = ?, estado = ?, id_persona = ?, id_tipo = ? "
                    + " where id_usuario = ?;";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, ub.getId_usuario());
            ps.setString(2, ub.getUsuario());
            ps.setString(3, ub.getPass());
            ps.setString(4, ub.getEstado());
            ps.setInt(5, ub.getId_persona());
            ps.setInt(6, ub.getId_tipo());
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

    public List<usuariosBean> consultar(usuariosBean ub) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "select * from usuarios." + tabla + ";";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<usuariosBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                ub = new usuariosBean(rs.getInt(1));
                ub.setUsuario(rs.getString(2));
                ub.setPass(rs.getString(3));
                ub.setEstado(rs.getString(4));
                ub.setId_persona(rs.getInt(5));
                ub.setId_tipo(rs.getInt(6));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(ub);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<usuariosBean> lista = new LinkedList<>();
            ub = new usuariosBean(0);
            ub.setUsuario("Ocurrio un error");
            ub.setPass("al revisar la tabla");
            lista.add(ub);
            return lista;
        }
    }

    public boolean sing(usuariosBean ub) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "select * from usuarios." + tabla + " where usuario = ? and pass = ?;";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            ps.setString(1, ub.getUsuario());
            ps.setString(2, ub.getPass());
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // si el usuario y contraseña coinciden hacer..
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            return false;
        }
    }

    public int stipe(usuariosBean ub) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "SELECT "
                    + "    u.*, t.tipo "
                    + "FROM "
                    + "    usuarios.usuarios u "
                    + "        INNER JOIN "
                    + "    usuarios.tipos AS t ON u.id_tipo = t.id_tipo "
                    + "WHERE "
                    + "    usuario = ? AND pass = ?;";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            ps.setString(1, ub.getUsuario());
            ps.setString(2, ub.getPass());
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                ub = new usuariosBean(rs.getInt(1));
                ub.setTipo(rs.getInt(7));
                // en cada ciclo se agregan los datos a la lista.
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return ub.getTipo();
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            return 0;
        }
    }
}
