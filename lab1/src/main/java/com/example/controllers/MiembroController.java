package com.example.controllers;

import java.io.FileWriter;
import java.util.List;

import com.example.Modelo.Club;
import com.example.Modelo.ClubSingleton;
import com.example.Modelo.Deporte;
import com.example.Modelo.Miembro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class MiembroController {

    @FXML
    private TextField nombreTextField;  // Campo para el nombre del miembro
    @FXML
    private TextField edadTextField;    // Campo para la edad del miembro
    @FXML
    private Button agregarMiembroButton;  // Botón para agregar un miembro
    @FXML
    private Button eliminarMiembroButton;  // Botón para eliminar un miembro
    @FXML
    private Button actualizarMiembroButton;  // Botón para actualizar un miembro
    @FXML
    private Button mostrarMiembrosButton;  // Botón para mostrar todos los miembros
    @FXML
    private ListView<Miembro> miembrosListView;  // Lista que muestra los miembros

    private ObservableList<Miembro> miembros = FXCollections.observableArrayList();  // Lista observable de miembros
    private Club club;  // Club actual
    private Deporte deporte;  // Deporte (actualmente no usado)

    /**
     * Inicializa el controlador. Configura la lista de miembros y muestra los miembros.
     */
    @FXML
    public void initialize() {
        club = ClubSingleton.getClub();
        miembrosListView.setItems(miembros);
        deporte = null;
        miembrosListView.setPlaceholder(new Label("No hay miembros"));
        mostrarMiembros();
    }

    /**
     * Agrega un nuevo miembro al club.
     */
    @FXML
    public void agregarMiembro() {
        String nombre = nombreTextField.getText();
        int edad = Integer.parseInt(edadTextField.getText());
        Miembro miembro = new Miembro(nombre, edad, deporte);

        club.crearMiembro(miembro);
        mostrarMiembros();

        nombreTextField.clear();
        edadTextField.clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Miembro agregado");
        alert.setHeaderText("Miembro agregado con éxito");
        alert.setContentText("El miembro " + miembro.getNombre() + " ha sido agregado con éxito.");
        alert.showAndWait();
    }

    /**
     * Elimina un miembro del club por nombre.
     */
    @FXML
    public void eliminarMiembro() {
        String nombre = nombreTextField.getText();
        if (nombre.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al eliminar miembro");
            alert.setContentText("Debe ingresar un nombre.");
            alert.showAndWait();
            return;
        }
        
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

            nombreTextField.clear();
            edadTextField.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Miembro eliminado");
            alert.setHeaderText("Miembro eliminado con éxito");
            alert.setContentText("El miembro " + miembro.getNombre() + " ha sido eliminado con éxito.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al eliminar miembro");
            alert.setContentText("No se encuentra el miembro con el nombre ingresado.");
            alert.showAndWait();
        }
    }
    
    /**
     * Actualiza la edad de un miembro existente.
     */
    @FXML
    public void actualizarMiembro() {
        String nombre = nombreTextField.getText();
        String edadString = edadTextField.getText();
        if (nombre.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al actualizar miembro");
            alert.setContentText("Debe ingresar un nombre.");
            alert.showAndWait();
            return;
        }
        
        Miembro miembro = null;
        for (Miembro m : club.obtenerMiembros()) {
            if (m.getNombre().equals(nombre)) {
                miembro = m;
                break;
            }
        }
        
        if (miembro != null) {
            try {
                int edad = Integer.parseInt(edadString);
                miembro.setEdad(edad);
                club.actualizarMiembro(miembro);
                mostrarMiembros();

                nombreTextField.clear();
                edadTextField.clear();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Miembro actualizado");
                alert.setHeaderText("Miembro actualizado con éxito");
                alert.setContentText("El miembro " + miembro.getNombre() + " ha sido actualizado con éxito.");
                alert.showAndWait();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al actualizar miembro");
                alert.setContentText("La edad debe ser un número.");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al actualizar miembro");
                alert.setContentText("Ocurrió un error al actualizar el miembro: " + e.getMessage());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al actualizar miembro");
            alert.setContentText("No se encuentra el miembro con el nombre ingresado.");
            alert.showAndWait();
        }
    }

    /**
     * Muestra todos los miembros en la lista.
     */
    @FXML
    public void mostrarMiembros() {
        miembros.clear();
        List<Miembro> miembrosList = club.obtenerMiembros();
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

        for (Miembro miembro : miembrosList) {
            miembros.add(miembro);
        }
    }
}
