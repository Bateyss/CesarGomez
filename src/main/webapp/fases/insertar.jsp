<%-- 
    Document   : insertar1
    Created on : 12-03-2019, 04:27:56 PM
    Author     : cesar.murciausam
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/torneo_tennis/src/css/bootstrap.css">
        <link href="/torneo_tennis/src/css/bootstrap.min.css" rel="stylesheet">
        <link href="/torneo_tennis/src/css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row" style="display: none">
                <div class="col-md-12">
                    <a id="modal-238486" href="#modal-container-238486" role="button" class="btn btn-outline-primary" data-toggle="modal">
                        Nuevo Equipo
                    </a>

                    <div class="modal fade" id="modal-container-238486" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                                    <form role="form" method="post" action="equipos?action=insertar">
                                        <div class="form-group">
                                            <label >
                                                Genero de Equipo
                                            </label>
                                            <select name="genero" required="true" class="form-control">
                                                <option selected="true" value="">Seleccione una opcion..</option>
                                                <option >Masculino</option>
                                                <option >Femenino</option>
                                                <option >Mixto</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label >
                                                Jugador/ra 1
                                            </label>
                                            <select name="id_jugador1" required="true" class="form-control">
                                                <option selected="true" value="">Seleccione una opcion..</option>
                                                <c:forEach items="${jugadores}" var="jug">
                                                    <option value="${jug.getId_jugador()}">${jug.getJugador()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label >
                                                Jugador/ra 2
                                            </label>
                                            <select name="id_jugador2" required="true" class="form-control">
                                                <option selected="true" value="">Seleccione una opcion..</option>
                                                <c:forEach items="${jugadores}" var="jug">
                                                    <option value="${jug.getId_jugador()}">${jug.getJugador()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <button class="btn btn-primary" type="submit">
                                            Guardar
                                        </button>
                                    </form>
                                </div>
                                <div class="modal-footer">

                                </div>
                            </div>

                        </div>

                    </div>

                </div>
            </div>
        </div>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    </body>
</html>
