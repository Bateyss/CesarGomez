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
public class tiposDao {
    base con;
    String tabla = "tipos";

    public tiposDao(base con) {
        this.con = con;
    }
    
    public List<tiposBean> consultar(tiposBean tb) {
        try {
            // consulta SQL a utilizar segun metodo
            String sql = "select * from usuarios." + tabla + " ;";
            // llamada a una sentencia preparada de sql
            PreparedStatement ps = con.conectar().prepareStatement(sql);
            // se ejecuta la accion de consultar con los datos en memoria
            ResultSet rs = ps.executeQuery();
            // se genera una lista para almacenar los datos de la tabla recividos
            List<tiposBean> lista = new LinkedList<>();
            // para almacenar los datos en la lista, se utiliza un ciclo
            // mientras existan filas con datos hacer..
            while (rs.next()) {                
                tb = new tiposBean(rs.getInt(1));
                tb.setTipo(rs.getInt(2));
                tb.setDescripcion(rs.getString(3));
                // en cada ciclo se agregan los datos a la lista.
                lista.add(tb);
            } // aqui sale del ciclo cuando ya no hay filas en la tabla
            return lista;
        } catch (SQLException e) {
            System.out.println("Error en leer dao de: " + tabla);
            System.out.println("Error de :" + e);
            List<tiposBean> lista = new LinkedList<>();
            tb = new tiposBean(0);
            tb.setDescripcion("Ocurrio un erroral revisar la tabla");
            lista.add(tb);
            return lista;
        }
    }
}
