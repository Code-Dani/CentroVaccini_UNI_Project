package Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CentroVaccinaleRG implements Initializable {

    @FXML
    private Text txtNome;

    @FXML
    private Text txtIndirizzo;

    @FXML
    private BarChart<?, ?> chartEta;

    @FXML
    private StackedAreaChart<?, ?> ChartVaccinazioni;

    @FXML
    private PieChart chartFasce;

    @FXML
    private Text txtTipologia;

    @FXML
    private JFXButton BtnLogin;

    @FXML
    private JFXButton BtnRegistrazione;

    @FXML
    private JFXButton BtnEventoAvverso;

    @FXML
    private ImageView IMG_reduce;

    @FXML
    private ImageView IMG_restoredown;

    @FXML
    private ImageView IMG_exit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtNome.setText("....");//dobbiamo capire da quale file/lista andare a prendere i dati per riempire la window.
        txtIndirizzo.setText(("...."));//
        txtTipologia.setText(".....");//
    }
    @FXML
    void btnClickReg(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registrazione.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("ABC");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    void btnclickLog(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("ABC");
        stage.setScene(new Scene(root2));
        stage.show();
    }


    /*@FXML
    void window_status(MouseEvent event) {

    }*/


}
