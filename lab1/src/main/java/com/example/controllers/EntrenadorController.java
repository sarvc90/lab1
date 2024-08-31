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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.util.Callback;

/**
 * Controlador para la pantalla de gestión de entrenadores.
 * 
 * Esta clase maneja la adición, actualización, eliminación y visualización de entrenadores en la aplicación.
 */
public class EntrenadorController implements Initializable {

    @FXML
    private TextField nombreTextField;  // Campo para el nombre del entrenador

    @FXML
    private TextField emailTextField;  // Campo para el email del entrenador

    @FXML
    private TextField idTextField;  // Campo para el ID del entrenador

    @FXML
    private ComboBox<Deporte> especialidadComboBox;  // ComboBox para seleccionar la especialidad del entrenador

    @FXML
    private Button agregarEntrenadorButton;  // Botón para agregar un nuevo entrenador

    @FXML
    private Button eliminarEntrenadorButton;  // Botón para eliminar un entrenador

    @FXML
    private Button actualizarEntrenadorButton;  // Botón para actualizar la información de un entrenador

    @FXML
    private ListView<Entrenador> entrenadoresListView;  // Lista para mostrar los entrenadores

    private EntrenadorRepository entrenadorRepository;  // Repositorio para gestionar datos de entrenadores
    private ObservableList<Entrenador> entrenadores = FXCollections.observableArrayList();  // Lista observable de entrenadores
    private Club club;  // Instancia del club

    /**
     * Se ejecuta al cargar la vista.
     * Inicializa la lista de entrenadores y llena el ComboBox con deportes disponibles.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        entrenadorRepository = new EntrenadorRepository();
        club = ClubSingleton.getClub();
        entrenadores = FXCollections.observableArrayList(club.obtenerEntrenadores());
        entrenadoresListView.setItems(entrenadores);

        DeporteRepository deporteRepository = new DeporteRepository();
        List<Deporte> deportes = deporteRepository.obtenerTodos();
        especialidadComboBox.setItems(FXCollections.observableArrayList(deportes));
    }

    /**
     * Agrega un nuevo entrenador con la información ingresada en los campos.
     * Muestra un mensaje de éxito o error según el resultado.
     */
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

    /**
     * Actualiza el entrenador seleccionado con la información ingresada en los campos.
     * Muestra un mensaje de éxito o error según el resultado.
     */
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

    /**
     * Elimina el entrenador seleccionado después de confirmar la acción.
     * Muestra un mensaje de éxito o error según el resultado.
     */
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
            }
        }
    }

    /**
     * Muestra todos los entrenadores en la lista.
     * Actualiza la vista de la lista para mostrar información clara de cada entrenador.
     */
    @FXML
    public void mostrarEntrenadores() {
        entrenadores.clear();
        List<Entrenador> entrenadoresList = club.obtenerEntrenadores();
        entrenadoresListView.getItems().clear();
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

        for (Entrenador entrenador : entrenadoresList) {
            entrenadores.add(entrenador);
        }
    }

}
