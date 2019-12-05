<%-- 
    Document   : navbar
    Created on : 12-02-2019, 04:20:29 PM
    Author     : cesar.murciausam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/torneo_tennis/src/css/bootstrap.css">
        <link href="/torneo_tennis/src/css/bootstrap.min.css" rel="stylesheet">
        <link href="/torneo_tennis/src/css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <ul class="nav nav-pills" style="background-color: #000">
                        <li class="nav-item">
                            <a class="nav-link bg-success active" href="/torneo_tennis/index.jsp">
                                Inicio
                            </a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link  dropdown-toggle btn-info"  id="navbarDropdownMenuLink" data-toggle="dropdown">
                                Abrir
                            </a>
                            <div class="dropdown-menu dropdown-menu-left" aria-labelledby="navbarDropdownMenuLink">
                                <a class="dropdown-item" href="/torneo_tennis/arbitros?action=consular">
                                    Arbitros
                                </a>
                                <a class="dropdown-item" href="/torneo_tennis/entrenadores?action=consular">
                                    Entrenadores
                                </a>
                                <a class="dropdown-item" href="/torneo_tennis/equipos?action=consular">
                                    Equipos
                                </a>
                                <a class="dropdown-item" href="/torneo_tennis/fases?action=consular">
                                    Fases
                                </a>
                                <a class="dropdown-item" href="/torneo_tennis/jugadores?action=consular">
                                    Jugadores
                                </a>
                                <a class="dropdown-item" href="/torneo_tennis/lugares?action=consular">
                                    Lugares
                                </a>
                                <a class="dropdown-item" href="/torneo_tennis/modalidades?action=consular">
                                    Modalidades
                                </a>
                                <a class="dropdown-item" href="/torneo_tennis/nacionalidades?action=consular">
                                    Nacionalidades
                                </a>
                                <a class="dropdown-item" href="/torneo_tennis/paises?action=consular">
                                    Paises
                                </a>
                                <a class="dropdown-item" href="#">
                                    Action
                                </a>
                            </div>
                        </li>
                        <li class="nav-item dropdown ml-md-auto">
                            <a class="nav-link  btn-info dropdown-toggle"  id="navbarDropdownMenuLink" data-toggle="dropdown">Usuario</a>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                                <a class="dropdown-item" href="#">
                                    Perfil
                                </a>
                                <div class="dropdown-divider"></div> 
                                <a class="dropdown-item" href="/torneo_tennis/usuarios?action=cerrarsesion">
                                    Cerrar Sesion
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>


    </body>
</html>
