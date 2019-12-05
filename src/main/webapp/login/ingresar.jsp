<%-- 
    Document   : ingresar
    Created on : 12-02-2019, 01:24:24 PM
    Author     : cesar.murciausam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%
    session = request.getSession();
    session.setAttribute("tipo", 0);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingresar</title>
        <link rel="stylesheet" href="/torneo_tennis/src/css/bootstrap.css">
    </head>
    <div class="container-fluid">
        <div class="row btn-info">
            <div class="col-md-12">
                <h1 class="text-center">
                    Ingresar al sitema
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
                <form role="form" action="usuarios?action=logear" method="post">
                    <div class="form-group">

                        <label >
                            Usuario
                        </label>
                        <input name="usuario" class="form-control" type="text"  required="true"/>
                    </div>
                    <div class="form-group">

                        <label >
                            Password
                        </label>
                        <input name="pass" class="form-control"  type="password" required="true" />
                    </div>
                    <button class="btn btn-primary" type="submit">
                        Ingresar
                    </button>
                </form>
            </div>
        </div>
    </div>
</html>
