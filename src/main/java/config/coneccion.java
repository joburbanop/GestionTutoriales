/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author jonathan
 */
public class coneccion {
     /*-----------------------------------------------------------------
     * Atributos
     *------------------------------------------------*/
    private static Connection conexion;
    private static final String URL = "jdbc:mysql://localhost:3306/gestorTutorial";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "";

  
    /*-----------------------------------------------------------------
     * Metodos
     *------------------------------------------------*/
    
    /**
     * 
     * @return 
     */
    public static Connection getConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establece la conexión y almacénala en la variable conexion
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            System.out.println("conexion exitosa sos un crack: "+conexion);
            return conexion;
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el controlador JDBC.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
