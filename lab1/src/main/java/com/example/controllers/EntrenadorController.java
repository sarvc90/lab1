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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
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
        int id = Integer.parseInt(idTextField.getText());
        Deporte especialidad = especialidadComboBox.getSelectionModel().getSelectedItem();

        Entrenador entrenador = new Entrenador(nombre, email, id, especialidad);
        entrenadorRepository.crear(entrenador);
        entrenadores.add(entrenador);
        entrenadoresListView.setItems(entrenadores);
    }

    @FXML
    private void actualizarEntrenador() {
        Entrenador entrenador = entrenadoresListView.getSelectionModel().getSelectedItem();
        if (entrenador != null) {
            String nombre = nombreTextField.getText();
            String email = emailTextField.getText();
            int id = Integer.parseInt(idTextField.getText());
            Deporte especialidad = especialidadComboBox.getSelectionModel().getSelectedItem();
    
            entrenador.setNombre(nombre);
            entrenador.setEmail(email);
            entrenador.setId(id);
            entrenador.setEspecialidad(especialidad);
    
            entrenadorRepository.actualizar(entrenador);
            club.actualizarEntrenador(entrenador); // Actualiza el entrenador en la clase Club
            entrenadoresListView.setItems(entrenadores);
        }
    }
    
    @FXML
    private void eliminarEntrenador() {
        Entrenador entrenador = entrenadoresListView.getSelectionModel().getSelectedItem();
        if (entrenador != null) {
            entrenadorRepository.eliminar(entrenador.getNombre());
            club.eliminarEntrenador(entrenador.getNombre()); // Elimina el entrenador de la clase Club
            entrenadores.remove(entrenador);
            entrenadoresListView.setItems(entrenadores);
        }
    }

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
// ARREGKAR QUE NO SE ESTA GUARDANDO EN ENTRENADIR ESPECIALIDAD Y ID