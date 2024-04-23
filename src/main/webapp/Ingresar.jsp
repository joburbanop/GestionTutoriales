<%-- 
    Document   : Ingresar
    Created on : 15/04/2024, 5:25:01 p. m.
    Author     : German
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ingresar Tutorial</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <style>
            body {
                background-color: #f5f5f5;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .card {
                width: 450px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .card-header {
                background-color:  #73c4cf;
                color: #fff;
                border-top-left-radius: 10px;
                border-top-right-radius: 10px;
                padding: 20px;
                text-align: center;
            }
            .card-body {
                padding: 30px;
            }
            .form-group {
                margin-bottom: 20px;
            }
            .form-group label {
                font-weight: bold;
            }
            .form-control {
                border-radius: 20px;
                padding: 12px 20px;
                font-size: 16px;
            }
            .btn {
                border-radius: 20px;
                padding: 12px 30px;
                font-size: 16px;
                font-weight: bold;
            }
            .btn-primary {
                background-color:  #73c4cf;
                border-color: #57b6c3;
            }
            .btn-primary:hover {
                background-color: #0056b3;
                border-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="card">
            <div class="card-header">
                <i class="fas fa-plus-circle"></i> Ingresar Nuevo Tutorial
            </div>
            <div class="card-body">
                <form action="SVIngresar" method="post">
                    <div class="form-group">
                        <label for="category">Categoría</label>
                        <select class="form-control" id="category" name="category">
                            <option value="1">Lógica de Programación</option>
                            <option value="2">Flutter</option>
                            <option value="3">Node.js</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="url">URL</label>
                        <input type="text" class="form-control" id="url" name="url" placeholder="Ingrese la URL del tutorial">
                    </div>
                    <div class="form-group">
                        <label for="priority">Prioridad</label>
                        <select class="form-control" id="priority" name="priority">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="status">Estado</label>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="status" id="status-read" value="Visto" disabled>
                            <label class="form-check-label" for="status-read">Visto</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="status" id="status-pending" value="pendiente" checked disabled>
                            <label class="form-check-label" for="status-pending">Pendiente de ver</label>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary">Guardar Tutorial</button>
                </form>
            </div>
        </div>
    </body>
</html>