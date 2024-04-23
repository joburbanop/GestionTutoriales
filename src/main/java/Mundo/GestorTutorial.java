/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mundo;

import config.coneccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorTutorial {

    /*----------------------------------------------------------------------------
     * Atributos 
     *--------------------------------------------------------------------------*/
    private List<Tutorial> tutoriales;

    /*-------------------------------------------------------------------------
     * Metodos
     *-------------------------------------------------------------------------*/
    public GestorTutorial() {
        this.tutoriales = new ArrayList<>();
    }

    /**
     * Metdo para insertar a la base de datos nuevo tutorial
     *
     * @param tutorial tutorial
     */
    public void insertarTutorial(Tutorial tutorial) {
        // Establecer la conexión a la base de datos
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        try {
            conexion = coneccion.getConexion();
            if (conexion != null) {

                String sql = "INSERT INTO tutorial (categoria_id, url, prioridad_lectura, estado) VALUES (?, ?, ?, ?)";

                preparedStatement = conexion.prepareStatement(sql);
                preparedStatement.setString(1, tutorial.getCategoriaId());
                preparedStatement.setString(2, tutorial.getUrl());
                preparedStatement.setInt(3, tutorial.getPrioridadLectura());
                preparedStatement.setString(4, tutorial.getEstado());

                preparedStatement.executeUpdate();
                //System.out.println("Felicisddes carck agrewgaste tu nuevo tutorial: " + tutorial.getId());
            } else {
                System.out.println("Error: No se pudo establecer conexión con la base de datos.");
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
        }
    }

    /**
     * Metodo para sleccionar visualizar todos los datos
     *
     * @return un arraylIst con todos los tutoriales
     */
    public List<Tutorial> consultarTutoriales() {
        List<Tutorial> tutoriales = new ArrayList<>();
        // Establecer la conexión a la base de datos
        try (Connection conexion = coneccion.getConexion()) {
            if (conexion != null) {
                String sql = "SELECT t.id, t.url, t.prioridad_lectura, t.estado, c.nombre AS categoria "
                        + "FROM tutorial t "
                        + "JOIN categoria c ON t.categoria_id = c.id";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Tutorial tutorial = new Tutorial();
                        tutorial.setId(resultSet.getInt("id"));
                        tutorial.setCategoriaId(resultSet.getString("categoria"));
                        tutorial.setUrl(resultSet.getString("url"));
                        tutorial.setPrioridadLectura(resultSet.getInt("prioridad_lectura"));
                        tutorial.setEstado(resultSet.getString("estado"));
                        tutoriales.add(tutorial);
                        //System.out.println("SE LOGRO " );
                    }
                } catch (SQLException ex) {
                    System.out.println("Error SQL al ejecutar la consulta: " + ex.getMessage());
                }
            } else {
                System.out.println("Error: No se pudo establecer conexión con la base de datos.");
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al conectar con la base de datos: " + ex.getMessage());
        }
        return tutoriales;
    }

    /**
     * Método para buscar un tutorial por su ID.
     *
     * @param id El ID del tutorial a buscar.
     * @return El tutorial encontrado, o null si no se encuentra.
     */
    public Tutorial buscarTutorialPorId(int id) {
        Tutorial tutorialEncontrado = null;
        // Establecer la conexión a la base de datos
        try (Connection conexion = coneccion.getConexion()) {
            if (conexion != null) {
                String sql = "SELECT t.id, t.url, t.prioridad_lectura, t.estado, c.nombre AS categoria "
                        + "FROM tutorial t "
                        + "JOIN categoria c ON t.categoria_id = c.id "
                        + "WHERE t.id = ?";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            tutorialEncontrado = new Tutorial();
                            tutorialEncontrado.setId(resultSet.getInt("id"));
                            tutorialEncontrado.setCategoriaId(resultSet.getString("categoria"));
                            tutorialEncontrado.setUrl(resultSet.getString("url"));
                            tutorialEncontrado.setPrioridadLectura(resultSet.getInt("prioridad_lectura"));
                            tutorialEncontrado.setEstado(resultSet.getString("estado"));
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println("Error SQL al ejecutar la consulta: " + ex.getMessage());
                }
            } else {
                System.out.println("Error: No se pudo establecer conexión con la base de datos.");
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al conectar con la base de datos: " + ex.getMessage());
        }
        return tutorialEncontrado;
    }

    /**
     * Método para eliminar un tutorial por su ID.
     *
     * @param id El ID del tutorial a eliminar.
     * @return true si se eliminó correctamente, false si ocurrió un error.
     */
    public boolean eliminarTutorial(int id) {
        // Establecer la conexión a la base de datos
        try (Connection conexion = coneccion.getConexion()) {
            if (conexion != null) {
                String sql = "DELETE FROM tutorial WHERE id = ?";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    int filasEliminadas = preparedStatement.executeUpdate();
                    return filasEliminadas > 0; // Devuelve true si se eliminó al menos una fila
                } catch (SQLException ex) {
                    System.out.println("Error SQL al ejecutar la consulta: " + ex.getMessage());
                    return false;
                }
            } else {
                System.out.println("Error: No se pudo establecer conexión con la base de datos.");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al conectar con la base de datos: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Método para actualizar un tutorial en la base de datos.
     *
     * @param tutorial El tutorial con los nuevos datos a actualizar.
     * @return true si la actualización fue exitosa, false si ocurrió un error.
     */
    public boolean actualizarTutorial(Tutorial tutorial) {
        // Establecer la conexión a la base de datos
        try (Connection conexion = coneccion.getConexion()) {
            if (conexion != null) {
                String sql = "UPDATE tutorial SET categoria_id = ?, url = ?, prioridad_lectura = ?, estado = ? WHERE id = ?";
                try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                    preparedStatement.setString(1, tutorial.getCategoriaId());
                    preparedStatement.setString(2, tutorial.getUrl());
                    preparedStatement.setInt(3, tutorial.getPrioridadLectura());
                    preparedStatement.setString(4, tutorial.getEstado());
                    preparedStatement.setInt(5, tutorial.getId());

                    int filasActualizadas = preparedStatement.executeUpdate();
                    return filasActualizadas > 0; // Devuelve true si se actualizó al menos una fila
                } catch (SQLException ex) {
                    System.out.println("Error SQL al ejecutar la consulta: " + ex.getMessage());
                    return false;
                }
            } else {
                System.out.println("Error: No se pudo establecer conexión con la base de datos.");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al conectar con la base de datos: " + ex.getMessage());
            return false;
        }
    }

}
