package com.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador para la ventana principal de la aplicación.
 * 
 * Maneja la apertura de diferentes vistas dentro de la aplicación.
 */
public class MainController {
    @FXML
    private TabPane mainTabPane;  // El TabPane principal donde se manejan las pestañas

    /**
     * Inicializa la ventana principal.
     * 
     * Aquí puedes agregar lógica para configurar la ventana al inicio, si es necesario.
     */
    @FXML
    public void initialize() {
        // Configura la ventana principal si es necesario
    }

    /**
     * Abre la vista de Miembros.
     * 
     * @param event El evento que dispara la apertura de la vista.
     */
    @FXML
    private void openMiembrosView(ActionEvent event) {
        cargarVista("/com/example/MiembroView.fxml");
    }

    /**
     * Abre la vista de Deportes.
     * 
     * @param event El evento que dispara la apertura de la vista.
     */
    @FXML
    private void openDeportesView(ActionEvent event) {
        cargarVista("/com/example/DeporteView.fxml");
    }

    /**
     * Abre la vista de Sesiones.
     * 
     * @param event El evento que dispara la apertura de la vista.
     */
    @FXML
    private void openSesionesView(ActionEvent event) {
        cargarVista("/com/example/SesionEntrenamientoView.fxml");
    }

    /**
     * Abre la vista de Entrenadores.
     * 
     * @param event El evento que dispara la apertura de la vista.
     */
    @FXML
    private void openEntrenadorView(ActionEvent event) {
        cargarVista("/com/example/EntrenadorView.fxml");
    }

    /**
     * Carga una vista específica en una nueva ventana.
     * 
     * @param vista Ruta del archivo FXML de la vista a cargar.
     */
    private void cargarVista(String vista) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vista));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();  // Muestra un error si no se puede cargar la vista
        }
    }
}
