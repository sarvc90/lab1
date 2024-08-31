package com.example.controllers;

import com.example.Modelo.Club;
import com.example.Modelo.ClubSingleton;
import com.example.Modelo.Deporte;
import com.example.Modelo.DeporteRepository;
import com.example.Modelo.Entrenador;
import com.example.Modelo.EntrenadorRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.util.Callback;


public class EntrenadorController implements Initializable {

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private ComboBox<Deporte> especialidadComboBox;

    @FXML
    private Button agregarEntrenadorButton;

    @FXML
    private Button eliminarEntrenadorButton;

    @FXML
    private Button actualizarEntrenadorButton;

    @FXML
    private ListView<Entrenador> entrenadoresListView;

    private EntrenadorRepository entrenadorRepository;
    private ObservableList<Entrenador> entrenadores = FXCollections.observableArrayList();
    private Club club;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        entrenadorRepository = new EntrenadorRepository();
        club = ClubSingleton.getClub();
        entrenadores = FXCollections.observableArrayList(club.obtenerEntrenadores());
        entrenadoresListView.setItems(entrenadores);

        // Inicializar el combo box de especialidades
        // Inicializar el combo box de especialidades
        DeporteRepository deporteRepository = new DeporteRepository();
        List<Deporte> deportes = deporteRepository.obtenerTodos();
        especialidadComboBox.setItems(FXCollections.observableArrayList(deportes));
    }

@FXML
private void agregarEntrenador() {
    String nombre = nombreTextField.getText();
    String email = emailTextField.getText();
    String id = idTextField.getText();
    Deporte especialidad = especialidadComboBox.getSelectionModel().getSelectedItem();

    if (nombre.isEmpty() || email.isEmpty() || id.isEmpty() || especialidad == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Faltan datos");
        alert.setContentText("Por favor, complete todos los campos");
        alert.showAndWait();
        return;
    }

    try {
        int idInt = Integer.parseInt(id);
        Entrenador entrenador = new Entrenador(nombre, email, idInt, especialidad);
        entrenadorRepository.crear(entrenador);
        entrenadores.add(entrenador);
        entrenadoresListView.setItems(entrenadores);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText("Entrenador agregado");
        alert.setContentText("El entrenador ha sido agregado correctamente");
        alert.showAndWait();
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("ID inválido");
        alert.setContentText("Por favor, ingrese un ID válido");
        alert.showAndWait();
    }
}

@FXML
private void actualizarEntrenador() {
    Entrenador entrenador = entrenadoresListView.getSelectionModel().getSelectedItem();
    if (entrenador != null) {
        String nombre = nombreTextField.getText();
        String email = emailTextField.getText();
        String id = idTextField.getText();
        Deporte especialidad = especialidadComboBox.getSelectionModel().getSelectedItem();

        if (nombre.isEmpty() || email.isEmpty() || id.isEmpty() || especialidad == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Faltan datos");
            alert.setContentText("Por favor, complete todos los campos");
            alert.showAndWait();
            return;
        }

        try {
            int idInt = Integer.parseInt(id);
            entrenador.setNombre(nombre);
            entrenador.setEmail(email);
            entrenador.setId(idInt);
            entrenador.setEspecialidad(especialidad);
            entrenadorRepository.actualizar(entrenador);
            club.actualizarEntrenador(entrenador);

            // Actualiza la lista de entrenadores
            entrenadores.set(entrenadores.indexOf(entrenador), entrenador);

            entrenadoresListView.setItems(entrenadores);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText("Entrenador actualizado");
            alert.setContentText("El entrenador ha sido actualizado correctamente");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ID inválido");
            alert.setContentText("Por favor, ingrese un ID válido");
            alert.showAndWait();
        }
    }
}


@FXML
private void eliminarEntrenador() {
    Entrenador entrenador = entrenadoresListView.getSelectionModel().getSelectedItem();
    if (entrenador != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Eliminar entrenador");
        alert.setContentText("¿Está seguro de que desea eliminar al entrenador?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            entrenadorRepository.eliminar(entrenador.getNombre());
            club.eliminarEntrenador(entrenador.getNombre());
            entrenadores.remove(entrenador);
            entrenadoresListView.setItems(entrenadores);
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Éxito");
            alert2.setHeaderText("Entrenador eliminado");
            alert2.setContentText("El entrenador ha sido eliminado correctamente");
            alert2.showAndWait();
            mostrarEntrenadores(); // Actualiza la lista de entrenadores
        }
    }
}

@FXML
public void mostrarEntrenadores() {
    entrenadores.clear();
    List<Entrenador> entrenadoresList = club.obtenerEntrenadores();
    entrenadores.addAll(entrenadoresList);
    entrenadoresListView.setItems(entrenadores);
    entrenadoresListView.setCellFactory(new Callback<ListView<Entrenador>, ListCell<Entrenador>>() {
        @Override
        public ListCell<Entrenador> call(ListView<Entrenador> param) {
            return new ListCell<Entrenador>() {
                @Override
                protected void updateItem(Entrenador item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        setText("Nombre: " + item.getNombre() + ", Email: " + item.getEmail() + ", Id: "
                                + item.getId() + ", Especialidad: " + item.getEspecialidad().toString());
                    } else {
                        setText(null);
                    }
                }
            };
        }
    });
}

}
// ARREGKAR QUE NO SE ESTA GUARDANDO EN ENTRENADIR ESPECIALIDAD Y ID