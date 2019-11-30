/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package premios;

import conexion.conexion;
import fases.fasesBean;
import fases.fasesDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cesar.murciausam
 */
public class premiosServlet extends HttpServlet {

    // atributos de uso general
    conexion con;
    RequestDispatcher rd;
    String msg;
    boolean rsp;
    String error;
    // atributos de uso local
    premiosDao dao;
    premiosBean bean;
    // atributos de uso complementario
    fasesDao fd;
    fasesBean fb;
    // variables de reusabilidad
    String carpeta = "premios"; // carpeta donde estan las vistas
    String id_tabla = "id_premio"; // el name del input relacionado con el id de la tabla
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
            fb = new fasesBean(0);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            fd = new fasesDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<fasesBean> fac = fd.consultar(fb);
            // se recogen las respuestas
            request.setAttribute("fases", fac);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/insertar.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        } catch (IOException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos en nuevo " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        }
    }

    protected void insertar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // variables de recoleccion de datos del jsp
            id = 0;
            String premio = request.getParameter("premio");
            String para = request.getParameter("para");
            int id_fase = Integer.parseInt(request.getParameter("id_fase"));
            double ganancia = Double.parseDouble(String.valueOf(request.getParameter("ganancia")));
            // se inician los bean
            bean = new premiosBean(0);
            // se introducen los datos almacenados desde jsp a los bean
            bean.setId_premio(id);
            bean.setPremio(premio);
            bean.setPara(para);
            bean.setId_fase(id_fase);
            bean.setGanancia(ganancia);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            dao = new premiosDao(con);
            // se ejecutan las acciones requeridas a los dao
            rsp = dao.insertar(bean);
            // se analizan las respuestas
            if (rsp) {
                msg = "Datos Guardados";
            } else {
                msg = "Error al Guardar";
            }
             // se inician los bean
            fb = new fasesBean(0);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            fd = new fasesDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<fasesBean> fac = fd.consultar(fb);
            // se recogen las respuestas
            request.setAttribute("fases", fac);
            // se recogen las respuestas
            request.setAttribute("msg", msg);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/insertar.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos al insertar " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        }
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // variables de recoleccion de datos del jsp
            id = Integer.parseInt(request.getParameter(id_tabla));
            String premio = request.getParameter("premio");
            String para = request.getParameter("para");
            int id_fase = Integer.parseInt(request.getParameter("id_fase"));
            double ganancia = Double.parseDouble(String.valueOf(request.getParameter("ganancia")));
            // se inician los bean
            bean = new premiosBean(0);
            // se introducen los datos almacenados desde jsp a los bean
            bean.setId_premio(id);
            bean.setPremio(premio);
            bean.setPara(para);
            bean.setId_fase(id_fase);
            bean.setGanancia(ganancia);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            dao = new premiosDao(con);
            // se ejecutan las acciones requeridas a los dao
            rsp = dao.actualizar(bean);
            // se analizan las respuestas
            if (rsp) {
                msg = "Datos Actualizados";
            } else {
                msg = "Error al Actualizar";
            }
            // se inician los bean
            fb = new fasesBean(0);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            fd = new fasesDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<fasesBean> fac = fd.consultar(fb);
            // se recogen las respuestas
            request.setAttribute("fases", fac);
            // se recogen las respuestas
            request.setAttribute("msg", msg);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/actualizar.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos al actualizar " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        }
    }

    protected void consultar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // se inician los bean
            bean = new premiosBean(0);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            dao = new premiosDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<premiosBean> lista = dao.consultar(bean);
            // se recogen las respuestas
            request.setAttribute("lista", lista);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        } catch (IOException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos al consultar " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo.jsp");
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
            bean = new premiosBean(0);
            // se introducen los datos almacenados desde jsp a los bean
            bean.setId_premio(id);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            dao = new premiosDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<premiosBean> lista = dao.consultarId(bean);
            // se inician los bean
            fb = new fasesBean(0);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            fd = new fasesDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<fasesBean> fac = fd.consultar(fb);
            // se recogen las respuestas
            request.setAttribute("fases", fac);
            // se recogen las respuestas
            request.setAttribute("lista", lista);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/actualizar.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos al consultar ID " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo.jsp");
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
            bean = new premiosBean(0);
            // se introducen los datos almacenados desde jsp a los bean
            bean.setId_premio(id);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            dao = new premiosDao(con);
            // se ejecutan las acciones requeridas a los dao
            rsp = dao.eliminar(bean);
            // se analizan las respuestas
            if (rsp) {
                msg = "Datos Eliminados";
            } else {
                msg = "Error al Eliminar";
            }
            // se ejecutan las acciones requeridas a los dao
            List<premiosBean> lista = dao.consultar(bean);
            // se recogen las respuestas
            request.setAttribute("lista", lista);
            // se recogen las respuestas
            request.setAttribute("msg", msg);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos al eliminar " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/catalogo.jsp");
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
