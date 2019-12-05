<%-- 
    Document   : inicio
    Created on : 12-02-2019, 01:39:33 PM
    Author     : cesar.murciausam
--%>
<%@page session="true" %>
<%
    HttpSession sesion = request.getSession();
    String usuario;
    int tipo;
    if (sesion.getAttribute("usuario") != null) {
        usuario = sesion.getAttribute("usuario").toString();
        tipo = Integer.parseInt(sesion.getAttribute("tipo").toString());
    } else {
        String msg = "Debe de iniciar sesion..";
        request.setAttribute("msg", msg);
        RequestDispatcher rd = request.getRequestDispatcher("/login/ingresar.jsp");
        rd.forward(request, response);
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link rel="stylesheet" href="/torneo_tennis/src/css/bootstrap.css">
        <link href="/torneo_tennis/src/css/bootstrap.min.css" rel="stylesheet">
        <link href="/torneo_tennis/src/css/style.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="src/navbar.jsp"/>
        <div class="container-fluid">
            <div class="row bg-info">
                <div class="col-md-12">
                    <h1 class="text-center">
                        Inicio
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
                    <div class="carousel slide" id="carousel-903996">
                        <ol class="carousel-indicators">
                            <li data-slide-to="0" data-target="#carousel-903996" class="active">
                            </li>
                            <li data-slide-to="1" data-target="#carousel-903996">
                            </li>
                            <li data-slide-to="2" data-target="#carousel-903996">
                            </li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img class="d-block w-100" style="height: 500px" alt="Carousel Bootstrap First" src="https://static.ultrasports.tv/2019/03/106000348_cdf_170312_he_wales_france_36-1.jpg">
                                <div class="carousel-caption">
                                    <h4>
                                        Six Nations 2019: Wales’ Grand Slam glory days in Cardiff
                                    </h4>
                                    <p>
                                        Coverage: Go on BBC One and S4C, BBC Radio 5 Live, BBC Radio Wales, BBC Radio Cymru and BBC Sport website and BBC Sport app, plus live text Comment.
                                        Grand Slam occasions have generally supposed glory days in Wales.
                                    </p>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" style="height: 500px" alt="Carousel Bootstrap Second" src="http://2.bp.blogspot.com/-YUDBRlMn3zI/TsP8wXVymGI/AAAAAAAAAAk/YlE6Gx7C8gg/s1600/maria-sharapova-desktop-wallpapers-2.jpg">
                                <div class="carousel-caption">
                                    <h4>
                                        All Out, All Game, All Season.
                                    </h4>
                                    <p>
                                        We’re not waiters (or waitresses), but boy can we serve!
                                        All it takes is all you’ve got!
                                    </p>
                                </div>
                            </div>
                            <div class="carousel-item">
                                <img class="d-block w-100" alt="Carousel Bootstrap Third" style="height: 500px" src="https://3.bp.blogspot.com/-D7hKPwivy5w/XHK2jNPbrII/AAAAAAAAHlw/8UFsK6dtDQc3t4gs_MBP6uSym9arYCq6wCHMYCw/s1600/best-top-desktop-tennis-wallpapers-hd-tennis-wallpaper-sport.jpg">
                                <div class="carousel-caption">
                                    <h4>
                                        Serve it, Smash it, Win it, Love it.
                                    </h4>
                                    <p>
                                        Respect All, Fear None.
                                        If its gotta be, it starts with me.
                                        Champions train; Losers complain.
                                        Refuse to Lose!
                                    </p>
                                </div>
                            </div>
                        </div> <a class="carousel-control-prev" href="#carousel-903996" data-slide="prev"><span class="carousel-control-prev-icon"></span> <span class="sr-only">Previous</span></a> <a class="carousel-control-next" href="#carousel-903996" data-slide="next"><span class="carousel-control-next-icon"></span> <span class="sr-only">Next</span></a>
                    </div>
                </div>
            </div>
        </div>
        <script src="/torneo_tennis/src/js/jquery.min.js" type="text/javascript"></script>
        <script src="/torneo_tennis/src/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/torneo_tennis/src/js/scripts.js" type="text/javascript"></script>
    </body>
</html>
