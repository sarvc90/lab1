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

/**
 * Controlador para la pantalla de inicio de sesión.
 * 
 * Esta clase maneja la autenticación del administrador en la aplicación.
 * Verifica las credenciales y, si son correctas, redirige al usuario a la ventana principal.
 */
public class LoginController {

    @FXML
    private TextField txtNombre;  // Campo de texto para ingresar el nombre de usuario

    @FXML
    private PasswordField txtPassword;  // Campo de texto para ingresar la contraseña

    @FXML
    private Label lblMensaje;  // Etiqueta para mostrar mensajes de error o éxito

    private AdministradorRepository adminRepository;  // Repositorio para gestionar datos de administradores

    /**
     * Constructor de LoginController.
     * 
     * Inicializa el repositorio de administradores que maneja los datos de autenticación.
     */
    public LoginController() {
        adminRepository = AdministradorRepository.getInstancia();
    }

    /**
     * Maneja el evento de inicio de sesión.
     * 
     * Verifica si el nombre de usuario y la contraseña son correctos. Si las credenciales coinciden,
     * carga la ventana principal de la aplicación. Si no, muestra un mensaje de error.
     * 
     * @param event Evento que desencadena el proceso de inicio de sesión (por ejemplo, clic en el botón de login).
     */
    @FXML
    public void handleLogin(ActionEvent event) {
        String nombre = txtNombre.getText();  // Obtiene el nombre ingresado
        String password = txtPassword.getText();  // Obtiene la contraseña ingresada

        Administrador admin = adminRepository.obtenerAdmin();
        
        // Verifica si el administrador existe y las credenciales coinciden
        if (admin != null && admin.getNombre().equals(nombre) && admin.getEmail().equals(password)) {
            try {
                // Cargar la ventana principal
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/MainView.fxml"));
                Stage stage = (Stage) txtNombre.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Muestra un mensaje de error si falla la carga de la ventana principal
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al cargar la ventana principal");
                alert.setContentText("Ocurrió un error al cargar la ventana principal.");
                alert.showAndWait();
            }
        } else {
            // Muestra un mensaje de error si las credenciales son inválidas
            lblMensaje.setText("Usuario inválido");
        }
    }
}
