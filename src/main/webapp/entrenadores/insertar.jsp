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
            <div class="row">
                <div class="col-md-12">
                    <a id="modal-238486" href="#modal-container-238486" role="button" class="btn btn-outline-primary" data-toggle="modal">
                        Nuevo Entrenador
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
                                    <form role="form" method="post" action="entrenadores?action=insertar">
                                        <div class="form-group">
                                            <label >
                                                Nombre de Entrenador
                                            </label>
                                            <input name="entrenador" class="form-control"  type="text" required="true" />
                                        </div>
                                        <div class="form-group">
                                            <label >
                                                Jugador Entrenado
                                            </label>
                                            <select name="id_jugador" required="true" class="form-control">
                                                <option selected="true" value="">Seleccione una opcion..</option>
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
                                                <option selected="true" value="">Seleccione una opcion..</option>
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
                                                <option selected="true" value="">Seleccione una opcion..</option>
                                                <c:forEach items="${sesiones}" var="sec">
                                                    <option value="${sec.getId_sesion()}">${sec.getDescripcion()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label >
                                                Fecha de inicio de Entrenamiento
                                            </label>
                                            <input name="fecha_inicio" class="form-control"  type="date" required="true" />
                                        </div>
                                        <div class="form-group">
                                            <label >
                                                Fecha de fin de Entrenamiento
                                            </label>
                                            <input name="fecha_fin" class="form-control"  type="date" required="true" />
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
        <script src="/torneo_tennis/src/js/jquery.min.js" type="text/javascript"></script>
        <script src="/torneo_tennis/src/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/torneo_tennis/src/js/scripts.js" type="text/javascript"></script>
    </body>
</html>
