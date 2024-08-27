package com.example.controllers;

import java.io.FileWriter;
import java.util.List;

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
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

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
    @FXML
    private Button mostrarMiembrosButton;
    @FXML
    private ComboBox<Deporte> deporteComboBox;
    @FXML
    private ListView<Deporte> deportesListView;
    @FXML
    private ListView<Miembro> miembrosListView;

    private ObservableList<Miembro> miembros = FXCollections.observableArrayList();
    private ObservableList<Deporte> deportes = FXCollections.observableArrayList();
    private Club club;


    @FXML
    public void initialize() {
        dificultadComboBox.getItems().addAll("BAJO", "MEDIO", "ALTO");
        club = new Club("Mi Club");
        deporteComboBox.setItems(deportes);
        cargarDeportes();
        miembrosListView.setItems(miembros);
        miembrosListView.setPlaceholder(new Label("No hay miembros"));

    }
    private void cargarDeportes() {
        deportes.clear();
        List<Deporte> deportesList = club.obtenerDeportes();
        deportesListView.getItems().clear();
        deportesListView.setCellFactory(new Callback<ListView<Deporte>, ListCell<Deporte>>() {
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
        deportesListView.getItems().addAll(deportesList);
        for (Deporte deporte : deportesList) {
            deportes.add(deporte);
        }
        deporteComboBox.setCellFactory((Callback<ListView<Deporte>, ListCell<Deporte>>) new Callback<ListView<Deporte>, ListCell<Deporte>>() {
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
        deporteComboBox.setItems(deportes);
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
            
            cargarDeportes();

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
        String nombre = nombreMiembroTextField.getText();
        int edad = Integer.parseInt(edadMiembroTextField.getText());
        Deporte deporte = deporteComboBox.getSelectionModel().getSelectedItem();
        if (deporte == null) {
            // Mostrar un mensaje de error si no se ha seleccionado un deporte
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al agregar miembro");
            alert.setContentText("Debe seleccionar un deporte.");
            alert.showAndWait();
            return;
        }
        Miembro miembro = new Miembro(nombre, edad, deporte);
        club.inscribirMiembro(miembro, deporte);
        
        // Limpia los campos
        nombreMiembroTextField.clear();
        edadMiembroTextField.clear();
        deporteComboBox.getSelectionModel().clearSelection();
        
        // Muestra un mensaje de confirmación
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Miembro agregado");
        alert.setHeaderText("Miembro agregado con éxito");
        alert.setContentText("El miembro " + miembro.getNombre() + " ha sido agregado con éxito al deporte " + deporte.getNombre());
        alert.showAndWait();
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
        
                cargarDeportes();
        
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
            String nombre = nombreTextField.getText();
            String descripcion = descripcionTextField.getText();
            String dificultadStr = dificultadComboBox.getSelectionModel().getSelectedItem();
            Dificultad dificultad = Dificultad.valueOf(dificultadStr);
            if (nombre.isEmpty() ) {
                // Mostrar un mensaje de error si el campo está vacíos
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al actualizar deporte");
                alert.setContentText("El campo de nombre no puede estar vacío.");
                alert.showAndWait();
                return;
            }
            try {
                Deporte deporte = new Deporte(nombre, descripcion, dificultad);
                club.actualizarDeporte(deporte);
        
                // Limpia los campos
                nombreTextField.clear();
                descripcionTextField.clear();
                dificultadComboBox.getSelectionModel().clearSelection();
        
                cargarDeportes();
        
                // Muestra un mensaje de confirmación
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Deporte actualizado");
                alert.setHeaderText("Deporte actualizado con éxito");
                alert.setContentText("El deporte " + deporte.getNombre() + " ha sido actualizado con éxito.");
                alert.showAndWait();
            } catch (Exception e) {
                // Mostrar un mensaje de error si ocurre un error al crear el deporte
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error al actualizar deporte");
                alert.setContentText("Ocurrió un error al actualizar el deporte: " + e.getMessage());
                alert.showAndWait();
            }

        }





        @FXML
        private void cargarMiembros() {
            miembros.clear(); // Limpia la lista observable de miembros
            Deporte deporteSeleccionado = deporteComboBox.getSelectionModel().getSelectedItem();
        
            if (deporteSeleccionado != null) {
                try {
                    deporteSeleccionado.fetchMiembrosAndEntrenadores(club);
                    List<Miembro> miembrosList = deporteSeleccionado.getMiembros();
                    miembros.setAll(miembrosList); // Actualiza la lista observable
                    
                    // Configura la ListView con el contenido de la lista observable
                    miembrosListView.setItems(miembros);
        
                    miembrosListView.setCellFactory(new Callback<ListView<Miembro>, ListCell<Miembro>>() {
                        @Override
                        public ListCell<Miembro> call(ListView<Miembro> p) {
                            return new ListCell<Miembro>() {
                                @Override
                                protected void updateItem(Miembro item, boolean empty) {
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
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error al obtener miembros");
                    alert.setContentText("Ocurrió un error al obtener los miembros del deporte: " + e.getMessage());
                    alert.showAndWait();
                }
            } else {
                miembrosListView.setItems(FXCollections.observableArrayList()); // Limpia la lista si no hay deporte seleccionado
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No se ha seleccionado un deporte");
                alert.setContentText("Por favor, seleccione un deporte del combo box");
                alert.showAndWait();
            }
        }
        


        @FXML
        public void mostrarMiembros() {
            cargarMiembros();
            
        }
 
}
