/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Mundo.GestorTutorial;
import Mundo.Tutorial;
import config.coneccion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan
 */
@WebServlet(name = "SVIngresar", urlPatterns = {"/SVIngresar"})
public class SVIngresar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener los parámetros del formulario
        String categoria = request.getParameter("category");
        String url = request.getParameter("url");
        int prioridad = Integer.parseInt(request.getParameter("priority"));
        String estado = "pediente de ver";

        // Crear un objeto Tutorial con los datos del formulario
        Tutorial tutorial = new Tutorial();
        tutorial.setCategoriaId(categoria);
        tutorial.setUrl(url);
        tutorial.setPrioridadLectura(prioridad);
        tutorial.setEstado(estado);

        // Insertar el tutorial utilizando el gestor de tutoriales
        GestorTutorial gS=new GestorTutorial();
        gS.insertarTutorial(tutorial);
        // Redirigir a una página de éxito
        response.sendRedirect("index.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
