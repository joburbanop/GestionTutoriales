<%-- 
    Document   : verRegistros
    Created on : 8/04/2024, 12:50:12 p. m.
    Author     : German
--%>



<%@page import="java.util.List"%>
<%@page import="Mundo.Tutorial"%>
<%@page import="config.coneccion"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Ver Registros</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <style>
            body {
                background-color: #f8f9fa;
            }
            .container {
                margin-top: 50px;
            }
            .card {
                margin-bottom: 20px;
                animation: fadeInUp 0.5s ease-in-out;
            }
            .card-header {
                background-color:  #73c4cf;
                color: white;
                font-weight: bold;
            }
            .card:hover {
                transform: scale(1.05);
                transition: transform 0.5s ease;
            }
            .btn-primary {
                border-radius: 20px;
                font-weight: bold;
                transition: background-color 0.3s ease-in-out, color 0.3s ease-in-out;
            }
            .btn-primary:hover {
                background-color: #0056b3;
                border-color: #0056b3;
            }
            .logo-img {
                max-width: 100%; /* La imagen se ajusta al ancho máximo de su contenedor */
                height: auto; /* Altura automática para mantener la proporción */
                display: block; /* Para eliminar el espacio inferior en algunas situaciones */
            }

        </style>
    </head>
    <body>
        <div class="container my-5">
            <h1 class="animate__animated animate__fadeInDown">Lista de Tutoriales</h1>
            <div class="row">
                <%
                    // Obtener la lista de tutoriales del atributo "tutoriales" en el objeto request
                    List<Tutorial> tutoriales = (List<Tutorial>) request.getAttribute("tutoriales");

                    // Verificar si la lista de tutoriales no está vacía
                    if (tutoriales != null && !tutoriales.isEmpty()) {
                        // Iterar sobre la lista de tutoriales
                        for (Tutorial tutorial : tutoriales) {
                            // Aquí puedes acceder a los atributos de cada tutorial y mostrarlos como desees
                            // Por ejemplo:
                %>
                <div class="col-md-4">
                    <div class="card overflow-auto">
                        <div class="card-header d-flex justify-content-between align-items-center">
                            <span>Tutorial ID: <%= tutorial.getId()%></span>
                            <div>
                                <button class="btn btn-danger btn-sm me-2 eliminar-btn" data-id="<%= tutorial.getId()%>"><i class="fas fa-trash-alt"></i> Eliminar</button>
                                <button class="btn btn-primary btn-sm actualizar-btn" data-id="<%= tutorial.getId()%>"><i class="fas fa-edit"></i> Actualizar</button>

                            </div>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Categoría: <%= tutorial.getCategoriaId()%></h5>

                            <%-- Verificar si la URL es de YouTube --%>
                            <% if (tutorial.getUrl().contains("youtube.com")) {%>
                            <a href="<%= tutorial.getUrl()%>" target="_blank">
                                <img src="static/img/logo-de-youtube.png" alt="YouTube Logo" class="logo-img">
                            </a>
                            <% } else {%>

                            <a href="<%= tutorial.getUrl()%>" target="_blank">
                                <img src="static/img/imagen-generica.png" alt="Imagen Genérica" class="logo-img">
                            </a>

                            <% }%>


                            <p class="card-text">Prioridad de Lectura: <%= tutorial.getPrioridadLectura()%></p>
                            <p class="card-text">Estado: <%= tutorial.getEstado()%></p>
                        </div>

                    </div>
                </div>
                <%
                    }
                } else {
                    // Si la lista de tutoriales está vacía, muestra un mensaje
                %>
                <div class="col">
                    <div class="alert alert-info" role="alert">
                        No hay tutoriales disponibles.
                    </div>
                </div>
                <%
                    }
                %>
            </div>
            <div class="row mt-3">
                <div class="col">
                    <a href="index.jsp" class="btn btn-primary animate__animated animate__fadeInUp">Regresar</a>
                </div>
            </div>
        </div>


        <!-- Modal -->
        <div class="modal fade" id="tutorialModal" tabindex="-1" aria-labelledby="tutorialModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="tutorialModalLabel">Detalles del Tutorial</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div id="tutorialDetails">
                            <!-- Aquí se cargará la información del tutorial -->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        <!-- Botón para actualizar el tutorial -->
                        <button type="button" class="btn btn-primary" id="updateTutorialBtn">Actualizar</button>
                    </div>
                </div>
            </div>
        </div>




        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

        <script>


            // Función para mostrar el modal y cargar la información del tutorial
            function mostrarModalTutorial(response) {
                // Actualiza el contenido del modal con la información del tutorial
                var tutorialHTML = `
        <form id="tutorialForm">
            <div class="mb-3">
                <label for="tutorialId" class="form-label">ID</label>
                <input type="text" class="form-control" id="tutorialId" name="tutorialId" value="` + response.id + `" readonly>
            </div>
            <div class="mb-3">
                <label for="categoria" class="form-label">Categoría</label>
                <select class="form-control" id="categoria" name="categoria">`;

                // Generar opciones de categoría
                if (response.categoriaId == '1') {
                    tutorialHTML += '<option value="1" selected>Lógica de Programación</option>';
                } else {
                    tutorialHTML += '<option value="1">Lógica de Programación</option>';
                }

                if (response.categoriaId == '2') {
                    tutorialHTML += '<option value="2" selected>Flutter</option>';
                } else {
                    tutorialHTML += '<option value="2">Flutter</option>';
                }

                if (response.categoriaId == '3') {
                    tutorialHTML += '<option value="3" selected>Node.js</option>';
                } else {
                    tutorialHTML += '<option value="3">Node.js</option>';
                }

                tutorialHTML += `
                </select>
            </div>
            <div class="mb-3">
                <label for="url" class="form-label">URL</label>
                <input type="text" class="form-control" id="url" name="url" value="` + response.url + `">
            </div>
            <div class="mb-3">
                <label for="prioridad" class="form-label">Prioridad de Lectura</label>
                <select class="form-control" id="prioridad" name="prioridad">`;

                // Generar opciones de prioridad
                for (var i = 1; i <= 10; i++) {
                    if (response.prioridadLectura == i.toString()) {
                        tutorialHTML += '<option value="' + i + '" selected>' + i + '</option>';
                    } else {
                        tutorialHTML += '<option value="' + i + '">' + i + '</option>';
                    }
                }

                tutorialHTML += `
                </select>
            </div>
            <div class="mb-3">
                <label for="estado" class="form-label">Estado</label>
                <div class="form-check">`;

                if (response.estado == 'Visto') {
                    tutorialHTML += `
                    <input class="form-check-input" type="radio" name="estado" id="status-read" value="Visto" checked>
                    <label class="form-check-label" for="status-read">Visto</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="estado" id="status-pending" value="pendiente de ver">
                    <label class="form-check-label" for="status-pending">Pendiente de ver</label>
                </div>`;
                } else {
                    tutorialHTML += `
                    <input class="form-check-input" type="radio" name="estado" id="status-read" value="Visto">
                    <label class="form-check-label" for="status-read">Visto</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="estado" id="status-pending" value="pendiente de ver" checked>
                    <label class="form-check-label" for="status-pending">Pendiente de ver</label>
                </div>`;
                }

                tutorialHTML += `
            </div>
            
        </form>
    `;

                $('#tutorialDetails').html(tutorialHTML);

                // Muestra el modal
                $('#tutorialModal').modal('show');
            }


            $(document).ready(function () {


                // Manejador de eventos para botones de "Actualizar"
                $('.actualizar-btn').click(function () {
                    var tutorialId = $(this).data('id'); // Obtener el ID del tutorial

                    console.log("Estamos buscando el tutorial con ID: " + tutorialId);
                    $.ajax({
                        url: "SvActualizar?id=" + tutorialId,
                        method: "POST",
                        success: function (response) {
                            console.log(response);
                            mostrarModalTutorial(response);
                        },
                        error: function (error) {
                            console.error("Error al obtener los detalles del tutorial:", error);
                        }
                    });
                });

                // Manejador de eventos para botones de "Eliminar"
                $('.eliminar-btn').click(function () {
                    var tutorialId = $(this).data('id');
                    if (confirm("¿Estás seguro de que deseas eliminar el tutorial con ID " + tutorialId + "?")) {

                        $.ajax({
                            url: "SvEliminar?id=" + tutorialId,
                            method: "POST",
                            success: function (response) {
                                // Manejar la respuesta del servidor
                                if (response === "success") {

                                    alert("El tutorial se eliminó correctamente.");
                                    location.reload();
                                } else {

                                    alert("Hubo un error al eliminar el tutorial.");
                                }
                            },
                            error: function (error) {
                                console.error("Error al eliminar el tutorial:", error);
                            }
                        });
                    } else {


                    }
                });


                // Manejador de eventos para el botón "Actualizar del modal"
                $('#updateTutorialBtn').click(function () {
                    // Obtén la información del formulario
                    var tutorialId = $('#tutorialId').val();
                    var categoriaId = $('#categoria').val();
                    var url = $('#url').val();
                    var prioridadLectura = $('#prioridad').val();
                    var estado = $('input[name="estado"]:checked').val();

                    // Crea un objeto con los datos del tutorial
                    var tutorialData = {
                        id: tutorialId,
                        categoriaId: categoriaId,
                        url: url,
                        prioridadLectura: prioridadLectura,
                        estado: estado
                    };

                    // Envía los datos al servlet utilizando AJAX
                    $.ajax({
                        url: 'SvActualizar',
                        method: 'get',
                        data: tutorialData,
                        success: function (response) {
                            // Maneja la respuesta del servidor si es necesario
                            console.log('Tutorial actualizado correctamente');
                            // Cierra el modal
                            $('#tutorialModal').modal('hide');
                            // Recarga la página o realiza alguna otra acción según sea necesario
                            location.reload();
                        },
                        error: function (error) {
                            console.error('Error al actualizar el tutorial:', error);
                            // Muestra un mensaje de error o realiza alguna otra acción según sea necesario
                        }
                    });
                });

            });

        </script>

        <script>
        </script>

    </body>

</html>

