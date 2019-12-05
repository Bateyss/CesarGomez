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
                        Listado de Modalidades
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
                                <th>Modalidad</th>
                            </tr>
                        </thead>
                        <tbody >
                            <% int x = 1;%>
                            <c:forEach items="${lista}" var="bean">
                                <tr class="btn-dark">
                                    <td><%= x%></td>
                                    <td>${bean.getModalidad()}</td>
                                </tr>
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
