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
 * Clase controladora para la ventana principal de la aplicación.
 */
public class MainController {
    /**
     * Referencia al TabPane principal de la ventana.
     */
    @FXML
    private TabPane mainTabPane;

    /**
     * Método de inicialización de la ventana.
     */
    @FXML
    public void initialize() {
        // Aquí puedes inicializar cualquier lógica adicional si es necesario
    }

    /**
     * Método para abrir la vista de Miembros.
     * @param event Evento de acción que disparó este método.
     */
    @FXML
    private void openMiembrosView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/MiembroView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para abrir la vista de Deportes.
     * @param event Evento de acción que disparó este método.
     */
    @FXML
    private void openDeportesView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/DeporteView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para abrir la vista de Sesiones.
     * @param event Evento de acción que disparó este método.
     */
    @FXML
    private void openSesionesView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/SesionEntrenamientoView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void openEntrenadorView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/EntrenadorView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}