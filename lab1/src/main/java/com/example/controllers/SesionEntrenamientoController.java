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
    private TextField fechaTextField;           // Campo para la fecha de la sesión
    @FXML
    private TextField duracionTextField;        // Campo para la duración de la sesión
    @FXML
    private ComboBox<Estado> estadoComboBox;    // ComboBox para el estado de la sesión
    @FXML
    private ComboBox<Deporte> deporteComboBox;  // ComboBox para el deporte de la sesión
    @FXML
    private ComboBox<Entrenador> entrenadorComboBox;  // ComboBox para el entrenador de la sesión
    @FXML
    private ListView<Miembro> miembrosListView; // Lista para los miembros de la sesión
    @FXML
    private ListView<SesionEntrenamiento> sesionesListView; // Lista para mostrar las sesiones

    private Club club; // Club actual

    /**
     * Constructor que inicializa el club usando el Singleton.
     */
    public SesionEntrenamientoController() {
        club = ClubSingleton.getClub();
    }

    /**
     * Crea una nueva sesión de entrenamiento con los parámetros dados.
     * 
     * @param fecha Fecha de la sesión
     * @param duracion Duración de la sesión
     * @param estado Estado de la sesión
     * @param deporte Deporte de la sesión
     * @param entrenador Entrenador de la sesión
     * @param miembros Miembros participantes en la sesión
     * @return La sesión de entrenamiento creada
     */
    private SesionEntrenamiento createSesionEntrenamiento(String fecha, int duracion, Estado estado, Deporte deporte,
            Entrenador entrenador, List<Miembro> miembros) {
        if (fecha == null || fecha.isEmpty()) {
            throw new IllegalArgumentException("Fecha cannot be null or empty");
        }
        if (duracion <= 0) {
            throw new IllegalArgumentException("Duracion must be a positive integer");
        }
        return new SesionEntrenamiento(fecha, duracion, estado, deporte, entrenador, miembros);
    }

    /**
     * Crea una nueva sesión de entrenamiento y la agrega al club.
     */
    @FXML
    public void crearSesion() {
        if (fechaTextField.getText().isEmpty() || duracionTextField.getText().isEmpty()) {
            mostrarAlerta("Error", "Por favor, complete todos los campos.");
            return;
        }

        if (estadoComboBox.getSelectionModel().getSelectedItem() == null ||
                deporteComboBox.getSelectionModel().getSelectedItem() == null ||
                entrenadorComboBox.getSelectionModel().getSelectedItem() == null) {
            mostrarAlerta("Error", "Por favor, seleccione un valor en todos los combobox.");
            return;
        }

        String fecha = fechaTextField.getText();
        int duracion = Integer.parseInt(duracionTextField.getText());
        Estado estado = estadoComboBox.getSelectionModel().getSelectedItem();
        Deporte deporte = deporteComboBox.getSelectionModel().getSelectedItem();
        Entrenador entrenador = entrenadorComboBox.getSelectionModel().getSelectedItem();
        List<Miembro> miembros = miembrosListView.getItems();

        SesionEntrenamiento sesion = createSesionEntrenamiento(fecha, duracion, estado, deporte, entrenador, miembros);
        if (club != null) {
            club.crearSesionEntrenamiento(sesion);
            sesionesListView.getItems().add(sesion);
            mostrarAlerta("Información", "Sesión creada con éxito.");
        } else {
            mostrarAlerta("Error", "Club is null!");
        }
    }

    /**
     * Actualiza una sesión de entrenamiento existente.
     */
    @FXML
    public void actualizarSesion() {
        String fecha = fechaTextField.getText();
        int duracion = Integer.parseInt(duracionTextField.getText());
        Estado estado = estadoComboBox.getSelectionModel().getSelectedItem();
        Deporte deporte = deporteComboBox.getSelectionModel().getSelectedItem();
        Entrenador entrenador = entrenadorComboBox.getSelectionModel().getSelectedItem();
        List<Miembro> miembros = miembrosListView.getItems();
    
        SesionEntrenamiento sesionToUpdate = null;
        for (SesionEntrenamiento sesion : sesionesListView.getItems()) {
            if (sesion.getFecha().equals(fecha)) {
                sesionToUpdate = sesion;
                break;
            }
        }
    
        if (sesionToUpdate != null) {
            sesionToUpdate.setDuracion(duracion);
            sesionToUpdate.setEstado(estado);
            sesionToUpdate.setDeporte(deporte);
            sesionToUpdate.setEntrenador(entrenador);
            sesionToUpdate.setMiembros(miembros);
    
            club.actualizarSesionEntrenamiento(sesionToUpdate);
            
            sesionesListView.getItems().remove(sesionToUpdate);
            sesionesListView.getItems().add(sesionToUpdate);
            sesionesListView.refresh();
            
            mostrarAlerta("Información", "Sesión actualizada con éxito.");
        } else {
            mostrarAlerta("Error", "No se ha encontrado una sesión que coincida con la fecha ingresada.");
        }
    }

    /**
     * Elimina una sesión de entrenamiento existente.
     */
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

    /**
     * Muestra una alerta con el título y mensaje especificados.
     * 
     * @param titulo Título de la alerta
     * @param mensaje Mensaje de la alerta
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Inicializa los ComboBox con los valores del club.
     */
    public void initialize() {
        estadoComboBox.getItems().addAll(club.getEstados());
        estadoComboBox.setCellFactory(new Callback<ListView<Estado>, ListCell<Estado>>() {
            @Override
            public ListCell<Estado> call(ListView<Estado> p) {
                return new ListCell<Estado>() {
                    @Override
                    protected void updateItem(Estado item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.name());
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
                            setText(item.getNombre());
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
                            setText(item.getNombre());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }
}
