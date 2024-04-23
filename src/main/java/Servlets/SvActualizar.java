/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Mundo.GestorTutorial;
import Mundo.Tutorial;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SvActualizar", urlPatterns = {"/SvActualizar"})
public class SvActualizar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener los parámetros enviados en la URL
        String tutorialId = request.getParameter("id");
        String categoriaId = request.getParameter("categoriaId");
        String url = request.getParameter("url");
        String prioridadLectura = request.getParameter("prioridadLectura");
        String estado = request.getParameter("estado");

        // Realizar las operaciones necesarias con los parámetros recibidos
        // Por ejemplo, imprimirlos en la consola
        System.out.println("ID del tutorial: " + tutorialId);
        System.out.println("Categoría ID: " + categoriaId);
        System.out.println("URL: " + url);
        System.out.println("Prioridad de lectura: " + prioridadLectura);
        System.out.println("Estado: " + estado);

        // Crear un objeto Tutorial con los datos actualizados
        Tutorial tutorialActualizado = new Tutorial();
        tutorialActualizado.setId(Integer.parseInt(tutorialId));
        tutorialActualizado.setCategoriaId(categoriaId);
        tutorialActualizado.setUrl(url);
        tutorialActualizado.setPrioridadLectura(Integer.parseInt(prioridadLectura));
        tutorialActualizado.setEstado(estado);

        // Actualizar el tutorial en la base de datos
        GestorTutorial gestorTutorial = new GestorTutorial();
        boolean actualizacionExitosa = gestorTutorial.actualizarTutorial(tutorialActualizado);

        if (actualizacionExitosa) {
            // La actualización fue exitosa
            response.sendRedirect("verRegistros.jsp");
        } else {
            // Ocurrió un error durante la actualización
            System.out.println("Error: No se pudo actualizar el tutorial.");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int tutorialId = Integer.parseInt(request.getParameter("id"));
        System.out.println("llegamos " + tutorialId);
        GestorTutorial tutorial = new GestorTutorial();
        // Buscar el tutorial por su ID
        Tutorial tutorialEncontrado = tutorial.buscarTutorialPorId(tutorialId);

        //System.out.println("informacion ha enviar" + tutorialEncontrado.getId());
        if (tutorialEncontrado != null) {
            // Convertir el objeto Tutorial a JSON
            Gson gson = new Gson();
            String tutorialJson = gson.toJson(tutorialEncontrado);

            // Configurar la respuesta como JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Enviar el JSON como respuesta
            response.getWriter().write(tutorialJson);
        } else {
            // Si no se encontró el tutorial, enviar una respuesta con un mensaje de error
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("No se encontró ningún tutorial con el ID: " + tutorialId);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
