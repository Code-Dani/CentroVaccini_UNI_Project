package Controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class EventoAvverso implements Initializable {

    @FXML
    public JFXComboBox<String> ComboEventi;
    ObservableList<String> list= FXCollections.observableArrayList("AAAA","AAAAA","BBBBBB");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ComboEventi.setItems(list);
    }
}
