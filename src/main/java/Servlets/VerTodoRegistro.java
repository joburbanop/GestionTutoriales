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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan
 */
@WebServlet(name = "SVerTodoRegistro", urlPatterns = {"/SVerTodoRegistro"})
public class VerTodoRegistro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //System.out.println("ya vamos aocnsultar");
   
        Tutorial tutorial = new Tutorial();
        GestorTutorial gestorTutorial = new GestorTutorial();

        // Consultar los tutoriales
        List<Tutorial> tutoriales = gestorTutorial.consultarTutoriales();

        // Almacenar los tutoriales como un atributo en el objeto request
        request.setAttribute("tutoriales", tutoriales);

        // Redirigir al JSP para mostrar los datos
        RequestDispatcher dispatcher = request.getRequestDispatcher("verRegistros.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
