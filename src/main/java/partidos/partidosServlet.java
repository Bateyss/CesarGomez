/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partidos;

import arbitros.*;
import conexion.conexion;
import fases.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jugadores.*;
import modalidades.*;
import sesiones.*;

/**
 *
 * @author cesar.murciausam
 */
public class partidosServlet extends HttpServlet {

    // atributos de uso general
    conexion con;
    RequestDispatcher rd;
    String msg;
    boolean rsp;
    String error;
    // atributos de uso local
    partidosDao dao;
    partidosBean bean;
    // atributos de uso complementario
    sesiones.sesionesDao sd;
    sesiones.sesionesBean sb;
    modalidades.modalidadesDao md;
    modalidades.modalidadesBean mb;
    fases.fasesDao fd;
    fases.fasesBean fb;
    arbitros.arbitrosDao ad;
    arbitros.arbitrosBean ab;
    jugadoresDao jd;
    jugadoresBean jb;
    // variables de reusabilidad
    String carpeta = "partidos"; // carpeta donde estan las vistas
    String id_tabla = "id_partido"; // el name del input relacionado con el id de la tabla
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
            sb = new sesionesBean(0);
            mb = new modalidadesBean(0);
            fb = new fasesBean(0);
            ab = new arbitrosBean(0);
            jb = new jugadoresBean(0);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            sd = new sesionesDao(con);
            md = new modalidadesDao(con);
            fd = new fasesDao(con);
            ad = new arbitrosDao(con);
            jd = new jugadoresDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<sesionesBean> ses = sd.consultar(sb);
            List<modalidadesBean> mod = md.consultar(mb);
            List<fasesBean> fas = fd.consultar(fb);
            List<arbitrosBean> arb = ad.consultar(ab);
            List<jugadoresBean> jug = jd.consultar(jb);
            // se recogen las respuestas
            bean = new partidosBean(0);
            dao = new partidosDao(con);
            List<partidosBean> lista = dao.consultar(bean);
            request.setAttribute("lista", lista);
            request.setAttribute("sesiones", ses);
            request.setAttribute("modalidades", mod);
            request.setAttribute("fases", fas);
            request.setAttribute("arbitros", arb);
            request.setAttribute("jugadores", jug);
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
            int id_sesion = Integer.parseInt(request.getParameter("id_sesion"));
            int id_modalidad = Integer.parseInt(request.getParameter("id_modalidad"));
            int id_fase = Integer.parseInt(request.getParameter("id_fase"));
            int id_arbitro = Integer.parseInt(request.getParameter("id_arbitro"));
            int id_jugador1 = Integer.parseInt(request.getParameter("id_jugador1"));
            int id_jugador2 = Integer.parseInt(request.getParameter("id_jugador2"));
            Date fecha = Date.valueOf(String.valueOf(request.getParameter("fecha")));
            // se inician los bean
            bean = new partidosBean(0);
            // se introducen los datos almacenados desde jsp a los bean
            bean.setId_partido(id);
            bean.setId_sesion(id_sesion);
            bean.setId_modalidad(id_modalidad);
            bean.setId_fase(id_fase);
            bean.setId_arbitro(id_arbitro);
            bean.setId_ganador(id_jugador1);
            bean.setId_perdedor(id_jugador2);
            bean.setFecha(fecha);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            dao = new partidosDao(con);
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
            int id_sesion = Integer.parseInt(request.getParameter("id_sesion"));
            int id_modalidad = Integer.parseInt(request.getParameter("id_modalidad"));
            int id_fase = Integer.parseInt(request.getParameter("id_fase"));
            int id_arbitro = Integer.parseInt(request.getParameter("id_arbitro"));
            int id_jugador1 = Integer.parseInt(request.getParameter("id_jugador1"));
            int id_jugador2 = Integer.parseInt(request.getParameter("id_jugador2"));
            Date fecha = Date.valueOf(String.valueOf(request.getParameter("fecha")));
            // se inician los bean
            bean = new partidosBean(0);
            // se introducen los datos almacenados desde jsp a los bean
            bean.setId_partido(id);
            bean.setId_sesion(id_sesion);
            bean.setId_modalidad(id_modalidad);
            bean.setId_fase(id_fase);
            bean.setId_arbitro(id_arbitro);
            bean.setId_ganador(id_jugador1);
            bean.setId_perdedor(id_jugador2);
            bean.setFecha(fecha);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            dao = new partidosDao(con);
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
            sb = new sesionesBean(0);
            mb = new modalidadesBean(0);
            fb = new fasesBean(0);
            ab = new arbitrosBean(0);
            jb = new jugadoresBean(0);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            sd = new sesionesDao(con);
            md = new modalidadesDao(con);
            fd = new fasesDao(con);
            ad = new arbitrosDao(con);
            jd = new jugadoresDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<sesionesBean> ses = sd.consultar(sb);
            List<modalidadesBean> mod = md.consultar(mb);
            List<fasesBean> fas = fd.consultar(fb);
            List<arbitrosBean> arb = ad.consultar(ab);
            List<jugadoresBean> jug = jd.consultar(jb);
            // se recogen las respuestas
            bean = new partidosBean(0);
            dao = new partidosDao(con);
            List<partidosBean> lista = dao.consultar(bean);
            request.setAttribute("lista", lista);
            request.setAttribute("sesiones", ses);
            request.setAttribute("modalidades", mod);
            request.setAttribute("fases", fas);
            request.setAttribute("arbitros", arb);
            request.setAttribute("jugadores", jug);
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
            bean = new partidosBean(0);
            // se introducen los datos almacenados desde jsp a los bean
            bean.setId_partido(id);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            dao = new partidosDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<partidosBean> lista = dao.consultarId(bean);
            // se inician los bean
            sb = new sesionesBean(0);
            mb = new modalidadesBean(0);
            fb = new fasesBean(0);
            ab = new arbitrosBean(0);
            jb = new jugadoresBean(0);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            sd = new sesionesDao(con);
            md = new modalidadesDao(con);
            fd = new fasesDao(con);
            ad = new arbitrosDao(con);
            jd = new jugadoresDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<sesionesBean> ses = sd.consultar(sb);
            List<modalidadesBean> mod = md.consultar(mb);
            List<fasesBean> fas = fd.consultar(fb);
            List<arbitrosBean> arb = ad.consultar(ab);
            List<jugadoresBean> jug = jd.consultar(jb);
            // se recogen las respuestas
            request.setAttribute("sesiones", ses);
            request.setAttribute("modalidades", mod);
            request.setAttribute("fases", fas);
            request.setAttribute("arbitros", arb);
            request.setAttribute("jugadores", jug);
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
            bean = new partidosBean(0);
            // se introducen los datos almacenados desde jsp a los bean
            bean.setId_partido(id);
            // se inicia la conexion a la base de datos
            con = new conexion();
            // se inician los dao
            dao = new partidosDao(con);
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
