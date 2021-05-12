package controllers;

import classes.ApiRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;


/**
 * Controller della MainWindow.fxml
 * @author Satriano Daniel
 * @author Menegotto Claudio
 * @since 5/05/2021
 */
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
    @FXML // fx:id="IMG_reduce"
    private ImageView IMG_reduce; // Value injected by FXMLLoader
    @FXML // fx:id="IMG_restoredown"
    private ImageView IMG_restoredown; // Value injected by FXMLLoader
    @FXML // fx:id="IMG_exit"
    private ImageView IMG_exit; // Value injected by FXMLLoader
    @FXML // fx:id="LB_dataAggiornamento"
    private Label LB_dataAggiornamento; // Value injected by FXMLLoader
    @FXML // fx:id="LB_numeroVeffettuati"
    private Label LB_numeroVeffettuati; // Value injected by FXMLLoader
    @FXML // fx:id="LB_numeroVgiornalieri"
    private Label LB_numeroVgiornalieri; // Value injected by FXMLLoader
    @FXML // fx:id="LB_vaccinazioniCompletate"
    private Label LB_vaccinazioniCompletate; // Value injected by FXMLLoader
    @FXML // fx:id="PC_home"
    private PieChart PC_home; // Value injected by FXMLLoader
    @FXML // fx:id="BC_home"
    private BarChart<String, Number> BC_home; // Value injected by FXMLLoader

    private double currentWindowX;
    private double currentWindowY;
    private boolean max_min = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JsonArray array;
        BT_Selection(BT_Home);
        try {
            array = ApiRequest.makeRequest("https://lab24.ilsole24ore.com/_json/vaccini/dati-mondo.json");
        }catch (Exception e){
            array = null;
            System.out.println("Si è verificato un errore durante il recupero dei dati");
        }
        popolaHome(array);
    }

    /**
     * OnMouseRelease event
     * @param event
     * @author Satriano Daniel
     * @since 10/05/2021
     */
    @FXML
    void tabClicked(MouseEvent event) {
        JFXButton cast = (JFXButton)event.getSource();
        switch (cast.getId()){
            case "BT_Home":
                BT_Selection(BT_Home);
                break;
            case "BT_Impostazioni":
                BT_Selection(BT_Impostazioni);
                break;
            case "BT_RegistraCentro":
                BT_Selection(BT_RegistraCentro);
                break;
            case "BT_RegistraVaccinato":
                BT_Selection(BT_RegistraVaccinato);
                break;
            case "BT_Storico":
                BT_Selection(BT_Storico);
                break;
            default:
                System.out.println("Errore nello switch dei pulsanti di tabulazione");
                break;
        }
    }

    /**
     * Evento che gestisce la chiusura della window, il restoredown/maximase , il riduci window.
     * @param event
     * @author Satriano Daniel
     * @since 10/05/2021
     */
    @FXML
    void window_status(MouseEvent event) {
        Stage stage = null;
        ImageView cast = (ImageView)event.getSource();
        stage = (Stage) IMG_reduce.getScene().getWindow();
        switch(cast.getId()){
            case "IMG_reduce":
                stage.setIconified(true);
                break;
            case "IMG_restoredown":
                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getVisualBounds();
                //stage.setMaximized(!stage.isMaximized());
                max_min = !max_min;
                /*Purtroppo se primaryStage.initStyle(StageStyle.UNDECORATED); è attivo non si riesce ad utilizzare .setMaximized, in quanto va a coprire la toolbar.
                  per oviare a questo problema ho dovuto usare la serie di metodi riportati di seguito.
                */
                if(max_min == true){
                    currentWindowX = stage.getWidth();
                    currentWindowY = stage.getHeight();
                    stage.setX(bounds.getMinX());
                    stage.setY(bounds.getMinY());
                    stage.setWidth(bounds.getWidth());
                    stage.setHeight(bounds.getHeight());
                    IMG_restoredown.setImage(new Image(getClass().getResource("/Images/lightMode/img_gp_black.png").toExternalForm()));
                }else{
                    stage.setWidth(currentWindowX);
                    stage.setHeight(currentWindowY);
                    IMG_restoredown.setImage(new Image(getClass().getResource("/Images/lightMode/img_maximise_black.png").toExternalForm()));
                    stage.centerOnScreen();
                }
                break;
            case "IMG_exit":
                stage.close();
                break;
            default:
                System.out.println("Errore nello switch delle ImageView per lo status della window");
                break;
        }
    }

    /**
     * Popola la grafica della home i dati che necessita
     * @param array jsonObject contenente i dati
     */
    private void popolaHome(JsonArray array){
        //popolo il PieChart
        JsonObject italy = new JsonObject();
        if(array != null){
            for(int i = 0; i< array.size(); i++) {
                if (array.get(i).getAsJsonObject().get("chiave").toString().equals("\"ITA\"")) {
                    italy = array.get(i).getAsJsonObject();
                    break;
                }
            }
        }
        if(italy!=null) {
            ObservableList<PieChart.Data> pc_data = FXCollections.observableArrayList(
                    new PieChart.Data("Popolazione italiana totale", Integer.parseInt(italy.get("abitanti").toString().split("\"")[1])),
                    new PieChart.Data("Vaccinazioni completate", Integer.parseInt(ApiRequest.infoGrabber(italy,"vaccinazioni_complete"))));
            PC_home.setData(pc_data);
            LB_dataAggiornamento.setText("Data aggiornamento dati: " + ApiRequest.infoGrabber(italy, "dataAggiornamento"));
            LB_numeroVeffettuati.setText("Numero vaccini effettuati: " + ApiRequest.infoGrabber(italy, "somministrazioni"));
            LB_numeroVgiornalieri.setText("Numero vaccini giornalieri: " + ApiRequest.infoGrabber(italy, "dosiGiornaliere"));
            LB_vaccinazioniCompletate.setText("Vaccinazioni completate: " + ApiRequest.infoGrabber(italy, "vaccinazioni_complete"));
        }

        //popolo il barchart
        XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.setName("Top 15 Nazioni");
        for(int i=0; i<15;i++){
            if(!(array.get(i).getAsJsonObject().get("chiave").toString().equals("\"OWID_EUN\""))){
                var tmp = array.get(i).getAsJsonObject();
                series.getData().add(new XYChart.Data<>(ApiRequest.infoGrabber(tmp, "nazione"),Integer.parseInt(ApiRequest.infoGrabber(tmp, "somministrazioni"))));
            }else{
                i++;
            }

        }
        BC_home.getData().add(series);
    }



    /**
     * @param selectedButton bottone selezionato
     * @author Menegotto Claudio
     * @since 15/05/2021
     */
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