/**
 * Sample Skeleton for 'MainWindow.fxml' Controller Class
 */

package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {

    @FXML // fx:id="BT_Home"
    private JFXButton BT_Home; // Value injected by FXMLLoader

    @FXML // fx:id="BT_RegistraCentro"
    private JFXButton BT_RegistraCentro; // Value injected by FXMLLoader

    @FXML // fx:id="BT_RegistraVaccinato"
    private JFXButton BT_RegistraVaccinato; // Value injected by FXMLLoader

    @FXML // fx:id="BT_Storico"
    private JFXButton BT_Storico; // Value injected by FXMLLoader

    @FXML // fx:id="BT_Impostazioni"
    private JFXButton BT_Impostazioni; // Value injected by FXMLLoader

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}