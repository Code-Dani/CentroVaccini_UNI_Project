/**
 * Sample Skeleton for 'MainWindow.fxml' Controller Class
 */

package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

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
        BT_Selection(BT_Home);
    }

    @FXML
    void HomeClicked(MouseEvent event) {
        BT_Selection(BT_Home);
    }

    @FXML
    void ImpostazioniClicked(MouseEvent event) {
        BT_Selection(BT_Impostazioni);
    }

    @FXML
    void RegistraCentroClicked(MouseEvent event) {
        BT_Selection(BT_RegistraCentro);
    }

    @FXML
    void RegistraVaccinatoClicked(MouseEvent event) {
        BT_Selection(BT_RegistraVaccinato);
    }

    @FXML
    void StoricoClicked(MouseEvent event) {
        BT_Selection(BT_Storico);
    }

    private void BT_Selection(JFXButton selectedButton){
        BT_Home.setStyle("-fx-background-color: #535353");
        BT_Home.setTextFill(Paint.valueOf("#ffffff"));

        BT_RegistraCentro.setStyle("-fx-background-color: #535353");
        BT_RegistraCentro.setTextFill(Paint.valueOf("#ffffff"));

        BT_RegistraVaccinato.setStyle("-fx-background-color: #535353");
        BT_RegistraVaccinato.setTextFill(Paint.valueOf("#ffffff"));

        BT_Storico.setStyle("-fx-background-color: #535353");
        BT_Storico.setTextFill(Paint.valueOf("#ffffff"));

        BT_Impostazioni.setStyle("-fx-background-color: #535353");
        BT_Impostazioni.setTextFill(Paint.valueOf("#ffffff"));

        selectedButton.setStyle("-fx-background-color: #FABF01");
        selectedButton.setTextFill(Paint.valueOf("#333333"));
    }
}