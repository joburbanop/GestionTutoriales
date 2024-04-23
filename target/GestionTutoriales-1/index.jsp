<%-- 
    Document   : index
    Created on : 15/04/2024, 1:51:34 p. m.
    Author     : jonathan
--%>

<%@page import="config.coneccion"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interfaz CRUD - compustore</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="static/css/index.css">
        <style>
            .tutorial-card {
                background-color: #73c4cf;
            }

            .categoria-card {
                background-color: #0094f08b;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1 class="animate__animated animate__fadeInDown">Toda tu informacion en tus manos</h1>

            <div class="alert alert-info animate__animated animate__fadeInUp" role="alert">
                <%
                    // Obtiene el estado de la conexión del objeto request
                    Boolean conexionExitosa = (Boolean) request.getAttribute("conexionExitosa");

                    // Verifica si el atributo conexionExitosa ya está disponible en el objeto request
                    if (conexionExitosa == null) {
                        // Si no está disponible, redirige al servlet SVConeccion
                        response.sendRedirect("SVConeccion");
                    } else {
                        // Si está disponible, muestra un mensaje en función del estado de la conexión
                        if (conexionExitosa) {
                %>
                <h1>Conexión exitosa</h1>
                <%
                } else {
                %>
                <h1>Error al conectar a la base de datos</h1>
                <%
                        }
                    }
                %>

            </div>

            <div class="row mt-4 animate__animated animate__fadeInUp">
                <div class="col-md-12">
                    <h2>Operaciones CRUD:</h2>
                    <div class="card-group">
                        <div class="card  tutorial-card">
                            <div class="card-body">                                
                                <h5 class="card-title">Ver todos los Tutoriales</h5>
                                <form action="SVerTodoRegistro" method="post">
                                    <button type="submit" class="btn btn-primary">Ir</button>
                                </form>
                            </div>
                        </div>
                        <div class="card  tutorial-card">
                            <div class="card-body">
                                <h5 class="card-title">Agregar Nuevo Tutorial</h5>
                                <a href="Ingresar.jsp" class="btn btn-success">Ir</a>
                            </div>
                        </div>
                        <div class="card categoria-card">
                            <div class="card-body">
                                <h5 class="card-title">Ver todas las categorias</h5>
                                <form action="SVerTodoRegistro" method="post">
                                    <button type="submit" class="btn btn-primary">Ir</button>
                                </form>
                            </div>
                        </div>
                        <div class="card categoria-card">
                            <div class="card-body">
                                <h5 class="card-title">Agregar Nueva categoria</h5>
                                <a href="ingresarCategoria.jsp" class="btn btn-success">Ir</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
