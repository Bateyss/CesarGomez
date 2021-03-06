<%-- 
    Document   : catalogo
    Created on : 12-03-2019, 10:54:51 AM
    Author     : cesar.murciausam
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<%
    HttpSession sesion = request.getSession();
    String usuario;
    int tipo;
    if (sesion.getAttribute("usuario") != null && Integer.parseInt(String.valueOf(sesion.getAttribute("tipo"))) == 1) {
        usuario = sesion.getAttribute("usuario").toString();
        tipo = Integer.parseInt(sesion.getAttribute("tipo").toString());
    } else {
        String msg = "Sesion de Invitado..";
        request.setAttribute("msg", msg);
        RequestDispatcher rd = request.getRequestDispatcher("catalogo-invit.jsp");
        rd.forward(request, response);
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogo</title>
        <link rel="stylesheet" href="/torneo_tennis/src/css/bootstrap-table.css">
        <link rel="stylesheet" href="/torneo_tennis/src/css/bootstrap.css">
        <link href="/torneo_tennis/src/css/bootstrap.min.css" rel="stylesheet">
        <link href="/torneo_tennis/src/css/style.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="/src/navbar.jsp" flush="true"/>
        <div class="container-fluid">
            <div class="row btn-info">
                <div class="col-md-12">
                    <h1 class="text-center">
                        Listado de Entrenadores
                    </h1>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <blockquote class="blockquote">
                        <p class="mb-0">
                            ${msg}
                        </p>
                        <footer class="blockquote-footer">
                            ${error}
                        </footer>
                    </blockquote>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <jsp:include page="insertar.jsp" flush="false"/>
                </div>
            </div>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <h3 class="text-center">
                        Tabla
                    </h3>
                    <table  class="table table-responsive-md table-bordered">
                        <thead class="table-primary">
                            <tr>
                                <th>Correlativo</th>
                                <th>Nombre de Entrenador</th>
                                <th>Jugador Entrenado</th>
                                <th>Nacionalidad</th>
                                <th>Torneo</th>
                                <th>Fecha Inicio</th>
                                <th>Fecha Fin</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody >
                            <% int x = 1;%>
                            <c:forEach items="${lista}" var="bean">
                                <tr class="btn-dark">
                                    <td><%= x%></td>
                                    <td>${bean.getEntrenador()}</td>
                                    <td>${bean.getJugador()}</td>
                                    <td>${bean.getNacionalidad()}</td>
                                    <td>${bean.getTorneo()}</td>
                                    <td>${bean.getFecha_inicio()}</td>
                                    <td>${bean.getFecha_fin()}</td>
                                    <td class="table-light">
                                        <a id="modal-238486" href="#modal-container-actualizar-<%= x%>" role="button" class="btn btn-outline-primary" data-toggle="modal">
                                            Modificar
                                        </a>
                                        <a id="modal-238486" href="#modal-container-eliminar-<%= x%>" role="button" class="btn btn-outline-primary" data-toggle="modal">
                                            Eliminar
                                        </a>
                                    </td>

                                </tr>
                            <div class="modal fade " id="modal-container-actualizar-<%= x%>" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content bg-info">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="myModalLabel">
                                                Complete Los campos antes de guardar
                                            </h5> 
                                            <button type="button" class="close" data-dismiss="modal">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form role="form" method="post" action="entrenadores?action=actualizar&id_entrenador=${bean.getId_entrenador()}">
                                                <div class="form-group">
                                                    <label >
                                                        Nombre de Entrenador
                                                    </label>
                                                    <input value="${bean.getEntrenador()}" name="entrenador" class="form-control"  type="text" required="true" />
                                                </div>
                                                <div class="form-group">
                                                    <label >
                                                        Jugador Entrenado
                                                    </label>
                                                    <select name="id_jugador" required="true" class="form-control">
                                                        <option selected="true" value="">--${bean.getJugador()}--</option>
                                                        <c:forEach items="${jugadores}" var="jug">
                                                            <option value="${jug.getId_jugador()}">${jug.getJugador()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label >
                                                        Nacionalidad
                                                    </label>
                                                    <select name="id_nacionalidad" required="true" class="form-control">
                                                        <option selected="true" value="">--${bean.getNacionalidad()}--</option>
                                                        <c:forEach items="${nacionalidades}" var="nac">
                                                            <option value="${nac.getId_nacionalidad()}">${nac.getNacionalidad()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label >
                                                        Sesion de Juegos
                                                    </label>
                                                    <select name="id_sesion" required="true" class="form-control">
                                                        <option selected="true" value="">--${bean.getTorneo()}--</option>
                                                        <c:forEach items="${sesiones}" var="nac">
                                                            <option value="${nac.getId_sesion()}">${nac.getDescripcion()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label >
                                                        Fecha de inicio de Entrenamiento
                                                    </label>
                                                    <input value="${bean.getFecha_inicio()}" name="fecha_inicio" class="form-control"  type="date" required="true" />
                                                </div>
                                                <div class="form-group">
                                                    <label >
                                                        Fecha de fin de Entrenamiento
                                                    </label>
                                                    <input value="${bean.getFecha_fin()}" name="fecha_fin" class="form-control"  type="date" required="true" />
                                                </div>
                                                <button class="btn btn-primary" type="submit">
                                                    Actualizar
                                                </button>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="modal fade " id="modal-container-eliminar-<%= x%>" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content bg-danger">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="myModalLabel">
                                                Eliminar Datos
                                            </h5> 
                                            <button type="button" class="close" data-dismiss="modal">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form role="form" method="post" action="entrenadores?action=eliminar&id_entrenador=${bean.getId_entrenador()}">
                                                <div class="form-group">
                                                    <label >
                                                        Esta seguro que desea eliminar los datos?
                                                    </label>
                                                </div>
                                                <button class="btn btn-secondary form-control" type="submit">
                                                    Si
                                                </button>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% x++;%>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        <script src="/src/js/jquery.min.js" type="text/javascript"></script>
        <script src="/src/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/src/js/scripts.js" type="text/javascript"></script>
    </body>
</html>
