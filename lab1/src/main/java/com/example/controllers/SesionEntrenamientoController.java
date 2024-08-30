package com.example.controllers;

import java.util.List;

import com.example.Modelo.Club;
import com.example.Modelo.ClubSingleton;
import com.example.Modelo.Deporte;
import com.example.Modelo.Entrenador;
import com.example.Modelo.Estado;
import com.example.Modelo.Miembro;
import com.example.Modelo.SesionEntrenamiento;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class SesionEntrenamientoController {

    @FXML
    private TextField fechaTextField;
    @FXML
    private TextField duracionTextField;
    @FXML
    private ComboBox<Estado> estadoComboBox;
    @FXML
    private ComboBox<Deporte> deporteComboBox;
    @FXML
    private ComboBox<Entrenador> entrenadorComboBox;
    @FXML
    private ListView<Miembro> miembrosListView;
    @FXML
    private ListView<SesionEntrenamiento> sesionesListView;

    private Club club;

    // Constructor que inicializa el club
    public SesionEntrenamientoController() {
        club = ClubSingleton.getClub(); // Usamos el Singleton para obtener la instancia de Club
    }

    private SesionEntrenamiento createSesionEntrenamiento(String fecha, int duracion, Estado estado, Deporte deporte,
            Entrenador entrenador, List<Miembro> miembros) {
        if (fecha == null || fecha.isEmpty()) {
            throw new IllegalArgumentException("Fecha cannot be null or empty");
        }
        if (duracion <= 0) {
            throw new IllegalArgumentException("Duracion must be a positive integer");
        }
        // Additional checks as needed
        return new SesionEntrenamiento(fecha, duracion, estado, deporte, entrenador, miembros);
    }

    @FXML
    public void crearSesion() {
        // Validar campos de texto
        if (fechaTextField.getText().isEmpty() || duracionTextField.getText().isEmpty()) {
            mostrarAlerta("Error", "Por favor, complete todos los campos.");
            return;
        }

        // Validar combobox
        if (estadoComboBox.getSelectionModel().getSelectedItem() == null ||
                deporteComboBox.getSelectionModel().getSelectedItem() == null ||
                entrenadorComboBox.getSelectionModel().getSelectedItem() == null) {
            mostrarAlerta("Error", "Por favor, seleccione un valor en todos los combobox.");
            return;
        }

        // Obtener valores
        String fecha = fechaTextField.getText();
        int duracion = Integer.parseInt(duracionTextField.getText());
        Estado estado = estadoComboBox.getSelectionModel().getSelectedItem();
        Deporte deporte = deporteComboBox.getSelectionModel().getSelectedItem();
        Entrenador entrenador = entrenadorComboBox.getSelectionModel().getSelectedItem();
        List<Miembro> miembros = miembrosListView.getItems();

        // Crear sesión
        SesionEntrenamiento sesion = createSesionEntrenamiento(fecha, duracion, estado, deporte, entrenador, miembros);
        // Agregar sesión al club y a la lista
        if (club != null) {
            club.crearSesionEntrenamiento(sesion);
            sesionesListView.getItems().add(sesion);
            mostrarAlerta("Información", "Sesión creada con éxito.");
        } else {
            mostrarAlerta("Error", "Club is null!");
        }
    }
    @FXML
    public void actualizarSesion() {
        String fecha = fechaTextField.getText();
        int duracion = Integer.parseInt(duracionTextField.getText());
        Estado estado = estadoComboBox.getSelectionModel().getSelectedItem();
        Deporte deporte = deporteComboBox.getSelectionModel().getSelectedItem();
        Entrenador entrenador = entrenadorComboBox.getSelectionModel().getSelectedItem();
        List<Miembro> miembros = miembrosListView.getItems();
    
        // Find the existing session to update
        SesionEntrenamiento sesionToUpdate = null;
        for (SesionEntrenamiento sesion : sesionesListView.getItems()) {
            if (sesion.getFecha().equals(fecha)) {
                sesionToUpdate = sesion;
                break;
            }
        }
    
        if (sesionToUpdate != null) {
            // Update the existing session
            sesionToUpdate.setDuracion(duracion);
            sesionToUpdate.setEstado(estado);
            sesionToUpdate.setDeporte(deporte);
            sesionToUpdate.setEntrenador(entrenador);
            sesionToUpdate.setMiembros(miembros);
    
            // Update the club and the ListView
            club.actualizarSesionEntrenamiento(sesionToUpdate);
            
            // Remove and re-add the session to refresh the ListView
            sesionesListView.getItems().remove(sesionToUpdate);
            sesionesListView.getItems().add(sesionToUpdate);
            sesionesListView.refresh();
            
            mostrarAlerta("Información", "Sesión actualizada con éxito.");
        } else {
            mostrarAlerta("Error", "No se ha encontrado una sesión que coincida con la fecha ingresada.");
        }
    }
    

    @FXML
    public void eliminarSesion() {
        String fecha = fechaTextField.getText();
        int duracion = Integer.parseInt(duracionTextField.getText());
        Estado estado = estadoComboBox.getSelectionModel().getSelectedItem();
        Deporte deporte = deporteComboBox.getSelectionModel().getSelectedItem();
        Entrenador entrenador = entrenadorComboBox.getSelectionModel().getSelectedItem();
        List<Miembro> miembros = miembrosListView.getItems();
    
        SesionEntrenamiento sesion = createSesionEntrenamiento(fecha, duracion, estado, deporte, entrenador, miembros);
        club.eliminarSesionEntrenamiento(sesion.getFecha());
        sesionesListView.getItems().remove(sesion);
        mostrarAlerta("Información", "Sesión eliminada con éxito.");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void initialize() {
        // Inicializar los ComboBox con los valores del club
        estadoComboBox.getItems().addAll(club.getEstados());
        estadoComboBox.setCellFactory(
                (Callback<ListView<Estado>, ListCell<Estado>>) new Callback<ListView<Estado>, ListCell<Estado>>() {
                    @Override
                    public ListCell<Estado> call(ListView<Estado> p) {
                        return new ListCell<Estado>() {
                            @Override
                            protected void updateItem(Estado item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item.name()); // Display the name of the Estado
                                } else {
                                    setText(null);
                                }
                            }
                        };
                    }
                });

        deporteComboBox.getItems().addAll(club.obtenerDeportes());
        deporteComboBox.setCellFactory(new Callback<ListView<Deporte>, ListCell<Deporte>>() {
            @Override
            public ListCell<Deporte> call(ListView<Deporte> p) {
                return new ListCell<Deporte>() {
                    @Override
                    protected void updateItem(Deporte item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getNombre()); // Display the name of the Deporte
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        entrenadorComboBox.getItems().addAll(club.obtenerEntrenadores());
        entrenadorComboBox.setCellFactory(new Callback<ListView<Entrenador>, ListCell<Entrenador>>() {
            @Override
            public ListCell<Entrenador> call(ListView<Entrenador> p) {
                return new ListCell<Entrenador>() {
                    @Override
                    protected void updateItem(Entrenador item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getNombre()); // Display the name of the Entrenador
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }
}