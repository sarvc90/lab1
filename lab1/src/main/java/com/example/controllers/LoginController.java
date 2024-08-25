package com.example.controllers;
import com.example.Modelo.Administrador;
import com.example.Modelo.AdministradorRepository;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
            lblMensaje.setText("Bienvenido!");
        } else {
            lblMensaje.setText("Usuario inválido");
        }
    }
}