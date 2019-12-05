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
            <div class="row btn-info ">
                <div class="col-md-12">
                    <h1 class="text-center">
                        Listado de Fases
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
                                <th>Fase</th>
                                <th>Nivel</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody >
                            <% int x = 1;%>
                            <c:forEach items="${lista}" var="bean">
                                <tr class="btn-dark">
                                    <td><%= x%></td>
                                    <td>${bean.getFase()}</td>
                                    <td>${bean.getNivel()}</td>
                                    <td class="table-light">
                                        <a id="modal-238486" href="#modal-container-actualizar-<%= x%>" role="button" class="btn btn-outline-primary" data-toggle="modal">
                                            Modificar
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
                                            <form role="form" method="post" action="fases?action=actualizar&id_fase=${bean.getId_fase()}">
                                                <div class="form-group">
                                                    <label >
                                                        Fase
                                                    </label>
                                                    <input value="${bean.getFase()}" name="fase" type="text" required="true">
                                                </div>
                                                <div class="form-group">
                                                    <label >
                                                        Nivel
                                                    </label>
                                                    <input value="${bean.getNivel()}" name="nivel" type="text" readonly="true">
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

                            <% x++;%>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
