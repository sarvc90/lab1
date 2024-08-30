package com.example.controllers;

import com.example.Modelo.Administrador;
import com.example.Modelo.AdministradorRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtNombre;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblMensaje;

    private AdministradorRepository adminRepository;

    public LoginController() {
        adminRepository = AdministradorRepository.getInstancia();
    }

    @FXML
    public void handleLogin(ActionEvent event) {
        String nombre = txtNombre.getText();
        String password = txtPassword.getText();

        Administrador admin = adminRepository.obtenerAdmin();
        if (admin != null && admin.getNombre().equals(nombre) && admin.getEmail().equals(password)) {
            try {
                // Cargar la ventana principal
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/MainView.fxml"));
                Stage stage = (Stage) txtNombre.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al cargar la ventana principal");
                alert.setContentText("Ocurrió un error al cargar la ventana principal.");
                alert.showAndWait();
            }
        } else {
            lblMensaje.setText("Usuario inválido");
        }
    }
}