package com.example.controllers;

import java.io.FileWriter;

import com.example.Modelo.Club;
import com.example.Modelo.Deporte;
import com.example.Modelo.Dificultad;
import com.example.Modelo.Miembro;
import javafx.scene.control.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DeporteController {

    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField descripcionTextField;
    @FXML
    private ComboBox<String> dificultadComboBox;
    @FXML
    private Button agregarDeporteButton;
    @FXML
    private TextField nombreMiembroTextField;
    @FXML
    private TextField edadMiembroTextField;
    @FXML
    private Button agregarMiembroButton;
    @FXML
    private TextField eliminarNombreTextField;
    @FXML
    private Button eliminarDeporteButton;
    @FXML
    private TextField actualizarNombreTextField;
    @FXML
    private Button actualizarDeporteButton;

    private Club club;


    @FXML
    public void initialize() {
        dificultadComboBox.getItems().addAll("BAJO", "MEDIO", "ALTO");
        club = new Club("Mi Club");

    }

@FXML
public void agregarDeporte() {
    String nombre = nombreTextField.getText();
    String descripcion = descripcionTextField.getText();
    String dificultadStr = dificultadComboBox.getSelectionModel().getSelectedItem();
    Dificultad dificultad = Dificultad.valueOf(dificultadStr);

    if (nombre.isEmpty() || descripcion.isEmpty()) {
        // Mostrar un mensaje de error si los campos están vacíos
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error al crear deporte");
        alert.setContentText("Los campos de nombre y descripción no pueden estar vacíos.");
        alert.showAndWait();
        return;
    }

    try {
        Deporte deporte = new Deporte(nombre, descripcion, dificultad);
        club.crearDeporte(deporte);

        // Guardar la información en un archivo de texto
        FileWriter writer = new FileWriter("deportes.txt", true);
        writer.write(deporte.getNombre() + "," + deporte.getDescripcion() + "," + deporte.getNivelDificultad() + "\n");
        writer.close();

        // Limpia los campos
        nombreTextField.clear();
        descripcionTextField.clear();
        dificultadComboBox.getSelectionModel().clearSelection();

        // Muestra un mensaje de confirmación
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Deporte creado");
        alert.setHeaderText("Deporte creado con éxito");
        alert.setContentText("El deporte " + deporte.getNombre() + " ha sido creado con éxito.");
        alert.showAndWait();
    } catch (Exception e) {
        // Mostrar un mensaje de error si ocurre un error al crear el deporte
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error al crear deporte");
        alert.setContentText("Ocurrió un error al crear el deporte: " + e.getMessage());
        alert.showAndWait();
    }
}

    @FXML
    public void agregarMiembro() {
        // Dejar vacío por ahora
    }
    @FXML
    public void eliminarDeporte() {
        String nombre = nombreTextField.getText();
        if (nombre.isEmpty() ) {
            // Mostrar un mensaje de error si el campo está vacíos
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al crear deporte");
            alert.setContentText("El campo de nombre no puede estar vacío.");
            alert.showAndWait();
            return;
        }
        try {
            Deporte deporte = new Deporte(nombre, null, null);
            club.eliminarDeporte(deporte.getNombre());
    
            // Limpia los campos
            nombreTextField.clear();
            descripcionTextField.clear();
            dificultadComboBox.getSelectionModel().clearSelection();
    

    
            // Muestra un mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deporte eliminado");
            alert.setHeaderText("Deporte eliminado con éxito");
            alert.setContentText("El deporte " + deporte.getNombre() + " ha sido eliminado con éxito.");
            alert.showAndWait();
        } catch (Exception e) {
            // Mostrar un mensaje de error si ocurre un error al crear el deporte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al eliminar deporte");
            alert.setContentText("Ocurrió un error al eliminar el deporte: " + e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    public void actualizarDeporte() {
        // Dejar vacío por ahora
    }
}