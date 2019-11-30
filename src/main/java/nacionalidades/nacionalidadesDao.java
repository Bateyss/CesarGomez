/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nacionalidades;

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
public class nacionalidadesDao {
    // lamada a la clase conexion, para iniciar la base de datos como objeto.
    private final String tabla = "nacionalidades";
    private final String id_tabla = "id_nacionalidad";
    private conexion con;

    public nacionalidadesDao(conexion con) {
        this.con = con;
    }
    public boolean insertar(nacionalidadesBean nb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.insertar_" + tabla + "(?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, nb.getId_nacionalidad());
            ps.setString(2, nb.getNacionalidad());
            ps.setInt(3, nb.getId_pais());
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

    public boolean actualizar(nacionalidadesBean nb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.actualizar_" + tabla + "(?,?,?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, nb.getId_nacionalidad());
            ps.setString(2, nb.getNacionalidad());
            ps.setInt(3, nb.getId_pais());
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

    public List<nacionalidadesBean> consultar(nacionalidadesBean nb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultar_" + tabla + "()";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<nacionalidadesBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                nb = new nacionalidadesBean(rs.getInt(1));
                nb.setNacionalidad(rs.getString(2));
                nb.setId_pais(rs.getInt(3));
                nb.setPais(rs.getString(4));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(nb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<nacionalidadesBean> lista = new LinkedList<>();
            nb = new nacionalidadesBean(0);
            nb.setNacionalidad("Ocurrio un error al revisar la tabla");
            lista.add(nb);
            return lista;
        }
    }

    public List<nacionalidadesBean> consultarId(nacionalidadesBean nb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "call torneo_gs.consultarId_" + tabla + "(?)";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, nb.getId_nacionalidad());
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<nacionalidadesBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {
                nb = new nacionalidadesBean(rs.getInt(1));
                nb.setNacionalidad(rs.getString(2));
                nb.setId_pais(rs.getInt(3));
                nb.setPais(rs.getString(4));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(nb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer ID dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<nacionalidadesBean> lista = new LinkedList<>();
            nb = new nacionalidadesBean(0);
            nb.setNacionalidad("Ocurrio un error al revisar la tabla");
            lista.add(nb);
            return lista;
        }
    }

    public boolean eliminar(nacionalidadesBean nb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "delete from torneo_gs." + tabla + " where " + id_tabla + "=?";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se introduce en memoria los datos a almacenar para sentencia sql
            ps.setInt(1, nb.getId_nacionalidad());
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
