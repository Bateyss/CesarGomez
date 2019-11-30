/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * asdasd
 * @author cesar.murciausam
 */
public class conexion {
    // atributos de inicio de la base de datos
    Connection con;
    private final String bd = "torneo_js";
    private final String user = "root";
    private final String pass = "root";
    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/"+bd+"?useSSL=false";

    public conexion() {
        try {
            // llamada al driver del motor de base de datos para java.
            Class.forName(driver);
            // llamada a la base de datos especifica
            con = DriverManager.getConnection(url, user, pass);
            // se comprueba si la llamada funciono.
            if (con!=null) {
                System.out.println("Exito en Conexion");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en conexion a bd");
            System.out.println("Error en: "+ e);
        }
    }
    public Connection conectar(){
    // se envia la llamada a la base de datos, para poder ser utilizada en procesos de query
    return con;
    }
    public void desconectar(){
    // se puede desconectar la llamada a la base cuando esta no se utiliza.
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Error en desconexion a bd");
            System.out.println("Error en: "+ e);
        }
    }
}
