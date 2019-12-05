<%-- 
    Document   : registrar
    Created on : 12-02-2019, 10:19:42 AM
    Author     : cesar.murciausam
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
        <link rel="stylesheet" href="/torneo_tennis/src/css/bootstrap.css">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row btn-info">
                <div class="col-md-12">
                    <h1>
                        Registrar Usuario
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
                    <h3>
                        Complete los Siguientes Datos antes de guardar
                    </h3>
                    <form role="form" action="usuarios?action=insertar" method="post">
                        <div class="form-group">

                            <label >
                                Nombres 
                            </label>
                            <input name="nombres" class="form-control"  type="text" required="true"/>
                        </div>
                        <div class="form-group">

                            <label >
                                Apellidos
                            </label>
                            <input name="apellidos" class="form-control" type="text" required="true"/>
                        </div>
                        <div class="form-group">

                            <label >
                                Edad 
                            </label>
                            <input name="edad" class="form-control"  type="number" step="1" min="0" required="true"/>
                        </div>
                        <div class="form-group">

                            <label >
                                Email
                            </label>
                            <input name="email" class="form-control"  type="email" required="true"/>
                        </div>
                        <div class="form-group">

                            <label >
                                Telefono
                            </label>
                            <input name="telefono" class="form-control"  type="text" required="true"/>
                        </div>
                        <div class="form-group">

                            <label >
                                Usuario
                            </label>
                            <input name="usuario" class="form-control"  type="text" required="true"/>
                        </div>
                        <div class="form-group">

                            <label >
                                Password
                            </label>
                            <input name="pass" class="form-control"  type="text" required="true"/>
                        </div>
                        <div class="form-group">

                            <label >
                                tipo de usuario
                            </label>
                            <select name="id_tipo" required="true" class="form-control">
                                <option selected="true" value="">Seleccione una opcion..</option>
                                <c:forEach items="${tipos}" var="tip">
                                    <option value="${tip.getId_tipo()}">${tip.getDescripcion()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button class="btn btn-primary" type="submit">
                            Guardar
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
