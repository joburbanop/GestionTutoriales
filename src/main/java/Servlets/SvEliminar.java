/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Mundo.GestorTutorial;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jonathan
 */
@WebServlet(name = "SvEliminar", urlPatterns = {"/SvEliminar"})
public class SvEliminar extends HttpServlet {

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
        // Obtener el ID del tutorial de la solicitud
        String tutorialId = request.getParameter("id");
        
        GestorTutorial tutorial= new GestorTutorial();
        
        // Simular la eliminación del tutorial (puedes reemplazar esto con tu lógica real de eliminación)
        boolean eliminado = tutorial.eliminarTutorial(Integer.parseInt(tutorialId));

        // Enviar una respuesta al cliente
        if (eliminado) {
            response.getWriter().write("success"); // Enviar 'success' si el tutorial se eliminó correctamente
        } else {
            response.getWriter().write("error"); // Enviar 'error' si hubo un problema al eliminar el tutorial
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
