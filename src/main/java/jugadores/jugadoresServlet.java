/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jugadores;

import conexion.conexion;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nacionalidades.nacionalidadesBean;
import nacionalidades.nacionalidadesDao;

/**
 *
 * @author cesar.murciausam
 */
public class jugadoresServlet extends HttpServlet {

    // atributos de uso general
    conexion con;
    RequestDispatcher rd;
    String msg;
    boolean rsp;
    String error;
    // atributos de uso local
    jugadoresDao dao;
    jugadoresBean bean;
    // atributos de uso complementario
    nacionalidadesDao nd;
    nacionalidadesBean nb;
    // variables de reusabilidad
    String carpeta = "jugadores"; // carpeta donde estan las vistas
    String id_tabla = "id_jugador"; // el name del input relacionado con el id de la tabla
    int id; // aqui se almacena el request id_tabla

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "insertar":
                insertar(request, response);
                break;
            case "actualizar":
                actualizar(request, response);
                break;
            case "consular":
                consultar(request, response);
                break;
            case "consularId":
                consultarId(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            case "nuevo":
                nuevo(request, response);
                break;
            default:
                msg = "ocurrio un error con la entrada.";
                request.setAttribute("msg", msg);
                consultar(request, response);
        }
    }

    protected void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // se inician los bean
            nb = new nacionalidadesBean(0);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            nd = new nacionalidadesDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<nacionalidadesBean> nac = nd.consultar(nb);
            // se recogen las respuestas
            bean = new jugadoresBean(0);
            dao = new jugadoresDao(con);
            List<jugadoresBean> lista = dao.consultar(bean);
            request.setAttribute("lista", lista);
            request.setAttribute("nacionalidades", nac);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo-admin.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        } catch (IOException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos en nuevo " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo-admin.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        }
    }

    protected void insertar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // variables de recoleccion de datos del jsp
            id = 0;
            String jugador = request.getParameter("jugador");
            String sexo = request.getParameter("sexo");
            int id_nacionalidad = Integer.parseInt(request.getParameter("id_nacionalidad"));
            // se inician los bean
            bean = new jugadoresBean(0);
            // se introducen los datos almacenados desde jsp a los bean
            bean.setId_jugador(id);
            bean.setJugador(jugador);
            bean.setSexo(sexo);
            bean.setId_nacionalidad(id_nacionalidad);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            dao = new jugadoresDao(con);
            // se ejecutan las acciones requeridas a los dao
            rsp = dao.insertar(bean);
            // se analizan las respuestas
            if (rsp) {
                msg = "Datos Guardados";
            } else {
                msg = "Error al Guardar";
            }
            
            // se recogen las respuestas
            request.setAttribute("msg", msg);
            // se envia la respuesta y se consulta la tabla
            consultar(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos al insertar " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo-admin.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        }
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // variables de recoleccion de datos del jsp
            id = Integer.parseInt(request.getParameter(id_tabla));
            String jugador = request.getParameter("jugador");
            String sexo = request.getParameter("sexo");
            int id_nacionalidad = Integer.parseInt(request.getParameter("id_nacionalidad"));
            // se inician los bean
            bean = new jugadoresBean(0);
            // se introducen los datos almacenados desde jsp a los bean
            bean.setId_jugador(id);
            bean.setJugador(jugador);
            bean.setSexo(sexo);
            bean.setId_nacionalidad(id_nacionalidad);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            dao = new jugadoresDao(con);
            // se ejecutan las acciones requeridas a los dao
            rsp = dao.actualizar(bean);
            // se analizan las respuestas
            if (rsp) {
                msg = "Datos Actualizados";
            } else {
                msg = "Error al Actualizar";
            }
            
            // se recogen las respuestas
            request.setAttribute("msg", msg);
            // se envia la respuesta y se consulta la tabla
            consultar(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos al actualizar " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo-admin.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        }
    }

    protected void consultar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // se inician los bean
            nb = new nacionalidadesBean(0);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            nd = new nacionalidadesDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<nacionalidadesBean> nac = nd.consultar(nb);
            // se recogen las respuestas
            bean = new jugadoresBean(0);
            dao = new jugadoresDao(con);
            List<jugadoresBean> lista = dao.consultar(bean);
            request.setAttribute("lista", lista);
            request.setAttribute("nacionalidades", nac);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo-admin.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        } catch (IOException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos al consultar " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo-admin.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        }
    }

    protected void consultarId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // variables de recoleccion de datos del jsp
            id = Integer.parseInt(request.getParameter(id_tabla));
            // se inician los bean
            bean = new jugadoresBean(0);
            // se introducen los datos almacenados desde jsp a los bean
            bean.setId_jugador(id);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            dao = new jugadoresDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<jugadoresBean> lista = dao.consultarId(bean);
            // se inician los bean
            nb = new nacionalidadesBean(0);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            nd = new nacionalidadesDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<nacionalidadesBean> nac = nd.consultar(nb);
            // se recogen las respuestas
            request.setAttribute("nacionalidades", nac);
            // se recogen las respuestas
            request.setAttribute("lista", lista);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo-admin.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos al consultar ID " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo-admin.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // variables de recoleccion de datos del jsp
            id = Integer.parseInt(request.getParameter(id_tabla));
            // se inician los bean
            bean = new jugadoresBean(0);
            // se introducen los datos almacenados desde jsp a los bean
            bean.setId_jugador(id);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            dao = new jugadoresDao(con);
            // se ejecutan las acciones requeridas a los dao
            rsp = dao.eliminar(bean);
            // se analizan las respuestas
            if (rsp) {
                msg = "Datos Eliminados";
            } else {
                msg = "Error al Eliminar";
            }
            
            // se recogen las respuestas
            request.setAttribute("msg", msg);
            // se envia la respuesta y se consulta la tabla
            consultar(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos al eliminar " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo-admin.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
