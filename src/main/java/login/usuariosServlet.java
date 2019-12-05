/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cesar.murciausam
 */
public class usuariosServlet extends HttpServlet {

    // atributos de uso general
    base con;
    RequestDispatcher rd;
    String msg;
    boolean rsp;
    String error;
    // atributos de uso local
    usuariosDao dao;
    usuariosBean bean;
    // atributos de uso complementario
    personasBean pb;
    personasDao pd;
    tiposBean tb;
    tiposDao td;
    // variables de reusabilidad
    String carpeta = "login"; // carpeta donde estan las vistas
    String id_tabla = "id_usuario"; // el name del input relacionado con el id de la tabla
    int id; // aqui se almacena el request id_tabla

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "insertar":
                insertar(request, response);
                break;
            case "logear":
                logear(request, response);
                break;
            case "nuevo":
                nuevo(request, response);
                break;
            case "cerrarsesion":
                cerrarsesion(request, response);
                break;
            default:
                msg = "ocurrio un error con la entrada.";
                request.setAttribute("msg", msg);
                logear(request, response);
        }
    }

    protected void nuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // se inician los bean
            pb = new personasBean(0);
            tb = new tiposBean(0);
            // se inicia la conexion a la base de datos
            con = new base();
            // se inician los dao
            pd = new personasDao(con);
            td = new tiposDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<personasBean> per = pd.consultar(pb);
            List<tiposBean> tip = td.consultar(tb);
            // se recogen las respuestas
            request.setAttribute("personas", per);
            request.setAttribute("tipos", tip);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/registrar.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        } catch (IOException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos en nuevo " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/registrar.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        }
    }

    protected void insertar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // variables de recoleccion de datos del jsp
            int id_persona = 0;
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            int edad = Integer.valueOf(String.valueOf(request.getParameter("edad")));
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            id = 0;
            String usuario = request.getParameter("usuario");
            String pass = request.getParameter("pass");
            int id_tipo = Integer.parseInt(request.getParameter("id_tipo"));
            // se inician los bean
            pb = new personasBean(0);
            bean = new usuariosBean(0);
            // se introducen los datos almacenados desde jsp a los bean
            pb.setId_persona(id_persona);
            pb.setNombres(nombres);
            pb.setApellidos(apellidos);
            pb.setEdad(edad);
            pb.setEmail(email);
            pb.setTelefono(telefono);
            // se inicia la conexion a la base de datos
            con = new base();
            // se inician los dao
            pd = new personasDao(con);
            dao = new usuariosDao(con);
            // se ejecutan las acciones requeridas a los dao
            rsp = pd.insertar(pb);
            if (rsp) {
                pb = new personasBean(0);
                pb.setNombres(nombres);
                pb.setApellidos(apellidos);
                int id_persona2 = pd.consultarNombre(pb);
                // se introducen los datos almacenados desde jsp a los bean
                bean.setId_usuario(id);
                bean.setUsuario(usuario);
                bean.setPass(pass);
                bean.setEstado("nuevo");
                bean.setId_persona(id_persona2);
                bean.setId_tipo(id_tipo);
                // se ejecutan las acciones requeridas a los dao
                rsp = dao.insertar(bean);
                // se analizan las respuestas
                if (rsp) {
                    msg = "Datos Guardados";
                } else {
                    msg = "Error al Guardar";
                }
            } else {
                msg = "error en Datos de persona";
            }
            // se inician los bean
            pb = new personasBean(0);
            tb = new tiposBean(0);
            // se inicia la conexion a la base de datos
            con = new base();
            // se inician los dao
            pd = new personasDao(con);
            td = new tiposDao(con);
            // se ejecutan las acciones requeridas a los dao
            List<personasBean> per = pd.consultar(pb);
            List<tiposBean> tip = td.consultar(tb);
            // se recogen las respuestas
            request.setAttribute("personas", per);
            request.setAttribute("tipos", tip);
            // se recogen las respuestas
            request.setAttribute("msg", msg);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/registrar.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos al insertar " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/registrar.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        }
    }

    protected void logear(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // variables de recoleccion de datos del jsp
            String usuario = request.getParameter("usuario");
            String pass = request.getParameter("pass");
            // se inician los bean
            tb = new tiposBean(0);
            bean = new usuariosBean(0);
            // se introducen los datos almacenados desde jsp a los bean
            bean.setUsuario(usuario);
            bean.setPass(pass);
            // se inicia la conexion a la base de datos
            con = new base();
            // se inician los dao
            dao = new usuariosDao(con);
            // se ejecutan las acciones requeridas a los dao
            rsp = dao.sing(bean);
            // se introducen los datos almacenados desde jsp a los bean
            bean.setUsuario(usuario);
            bean.setPass(pass);
            tb.setTipo(dao.stipe(bean));
            // se recogen las respuestas
            if (rsp) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                session.setAttribute("tipo", tb.getTipo());
                msg = null;
                session.setAttribute("msg", msg);
                response.sendRedirect("index.jsp");
            } else {
                msg = "No esxiste usuario o clave";
                HttpSession session = request.getSession();
                session.setAttribute("usuario", null);
                session.setAttribute("tipo", null);
                session.setAttribute("msg", msg);
                rd = request.getRequestDispatcher("/login/ingresar.jsp");
                rd.forward(request, response);
            }
        } catch (IOException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos al consultar " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/ingresar.jsp");
            // se envian los datos de respuesta.
            rd.forward(request, response);
        }
    }

    protected void cerrarsesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            msg = "Ha salido con exito.";
            HttpSession session = request.getSession();
            session.setAttribute("usuario", null);
            session.setAttribute("tipo", null);
            session.setAttribute("msg", msg);
            rd = request.getRequestDispatcher("/login/ingresar.jsp");
            rd.forward(request, response);
        } catch (IOException | ServletException e) {
            // en caso de error, se mostraria el error en la vista, relacionando a la base de datos
            error = e.getMessage();
            msg = "Ocurrio un error con la base de datos al consultar " + carpeta;
            request.setAttribute("msg", msg);
            request.setAttribute("error", error);
            // se llama a la direccion o accion de resputesta
            rd = request.getRequestDispatcher("/" + carpeta + "/ingresar.jsp");
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
