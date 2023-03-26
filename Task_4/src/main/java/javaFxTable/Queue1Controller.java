package javaFxTable;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Queue1Controller implements Initializable {
    public Label lable;
    public TextField filterField;
    public TableView<Passenger> tableview;
    public TableColumn<Passenger, String> col1;
    public TableColumn<Passenger, String> col2;
    public TableColumn<Passenger, String> col3;
    public TableColumn<Passenger, Integer> col4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col3.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        col4.setCellValueFactory(new PropertyValueFactory<>("liters"));
        tableview.setItems(Main.queue1.getPassenger());

        FilteredList<Passenger> filteredData = new FilteredList<>(Main.queue1.getPassenger(), b -> true);
        filterField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate(Passenger -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (Passenger.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Passenger.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Passenger.getVehicleNo().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return Integer.toString(Passenger.getLiters()).contains(lowerCaseFilter);
            });
        });
        SortedList<Passenger> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);
    }

}