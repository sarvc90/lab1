package com.example.controllers;

import java.io.FileWriter;
import java.util.List;

import com.example.Modelo.Club;
import com.example.Modelo.ClubSingleton;
import com.example.Modelo.Deporte;
import com.example.Modelo.Dificultad;
import com.example.Modelo.Miembro;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class MiembroController {

    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField edadTextField;
    @FXML
    private Button agregarMiembroButton;
    @FXML
    private Button eliminarMiembroButton;
    @FXML
    private Button actualizarMiembroButton;
    @FXML
    private Button mostrarMiembrosButton;
    @FXML
    private ListView<Miembro> miembrosListView;

    private ObservableList<Miembro> miembros = FXCollections.observableArrayList();
    private Club club;
    private Deporte deporte;

    @FXML
    public void initialize() {
        club = ClubSingleton.getClub();
        miembrosListView.setItems(miembros);
        deporte = null;
        miembrosListView.setPlaceholder(new Label("No hay miembros"));
        mostrarMiembros();
        miembrosListView.setItems(miembros);
    }

    @FXML
    public void agregarMiembro() {
        String nombre = nombreTextField.getText();
        int edad = Integer.parseInt(edadTextField.getText());
        Deporte deporte = null; // Allow null deporte
        Miembro miembro = new Miembro(nombre, edad, deporte); // Create miembro with optional deporte

        club.crearMiembro(miembro); // Pass null deporte if not selected
        mostrarMiembros();
        // Limpia los campos
        nombreTextField.clear();
        edadTextField.clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Miembro agregado");
        alert.setHeaderText("Miembro agregado con éxito");
        alert.setContentText("El miembro " + miembro.getNombre() + " ha sido agregado con éxito.");
        alert.showAndWait();
    }

    @FXML
    public void eliminarMiembro() {
        String nombre = nombreTextField.getText();
        if (nombre.isEmpty()) {
            // Mostrar un mensaje de error si no se ha ingresado un nombre
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al eliminar miembro");
            alert.setContentText("Debe ingresar un nombre.");
            alert.showAndWait();
            return;
        }
        
        // Buscar el miembro por nombre
        Miembro miembro = null;
        for (Miembro m : club.obtenerMiembros()) {
            if (m.getNombre().equals(nombre)) {
                miembro = m;
                break;
            }
        }
        
        if (miembro != null) {
            club.eliminarMiembro(miembro.getNombre());
            mostrarMiembros();
            // Muestra un mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Miembro eliminado");
            alert.setHeaderText("Miembro eliminado con éxito");
            alert.setContentText("El miembro " + miembro.getNombre() + " ha sido eliminado con éxito.");
            alert.showAndWait();
            // Limpia los campos
            nombreTextField.clear();
            edadTextField.clear();
        } else {
            // Mostrar un mensaje de error si no se encuentra el miembro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al eliminar miembro");
            alert.setContentText("No se encuentra el miembro con el nombre ingresado.");
            alert.showAndWait();
        }
    }
    
    @FXML
    public void actualizarMiembro() {
        String nombre = nombreTextField.getText();
        String edadString = edadTextField.getText();
        if (nombre.isEmpty()) {
            // Mostrar un mensaje de error si no se ha ingresado un nombre
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al actualizar miembro");
            alert.setContentText("Debe ingresar un nombre.");
            alert.showAndWait();
            return;
        }
        
        // Buscar el miembro por nombre
        Miembro miembro = null;
        for (Miembro m : club.obtenerMiembros()) {
            if (m.getNombre().equals(nombre)) {
                miembro = m;
                break;
            }
        }
        
        if (miembro != null) {
            try {
                int edad = Integer.parseInt(edadString); // Parse edad as an int
                miembro.setEdad(edad);
                club.actualizarMiembro(miembro);
                mostrarMiembros();
                // Limpia los campos
                nombreTextField.clear();
                edadTextField.clear();
                // Muestra un mensaje de confirmación
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Miembro actualizado");
                alert.setHeaderText("Miembro actualizado con éxito");
                alert.setContentText("El miembro " + miembro.getNombre() + " ha sido actualizado con éxito.");
                alert.showAndWait();
            } catch (NumberFormatException e) {
                // Mostrar un mensaje de error si la edad no es un número válido
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al actualizar miembro");
                alert.setContentText("La edad debe ser un número.");
                alert.showAndWait();
            } catch (Exception e) {
                // Mostrar un mensaje de error si ocurre un error al crear el deporte
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al actualizar miembro");
                alert.setContentText("Ocurrió un error al actualizar el miembro: " + e.getMessage());
                alert.showAndWait();
            }
        } else {
            // Mostrar un mensaje de error si no se encuentra el miembro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al actualizar miembro");
            alert.setContentText("No se encuentra el miembro con el nombre ingresado.");
            alert.showAndWait();
        }
    }

    @FXML
    public void mostrarMiembros() {
        miembros.clear();
        List<Miembro> miembrosList = club.obtenerMiembros();
        miembrosListView.getItems().clear();
        miembrosListView.setCellFactory(new Callback<ListView<Miembro>, ListCell<Miembro>>() {
            @Override
            public ListCell<Miembro> call(ListView<Miembro> p) {
                return new ListCell<Miembro>() {
                    @Override
                    protected void updateItem(Miembro item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText("Nombre: " + item.getNombre() + ", Edad: " + item.getEdad());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        for (Miembro miembro : miembrosList){
            miembros.add(miembro);
        }

    }
}