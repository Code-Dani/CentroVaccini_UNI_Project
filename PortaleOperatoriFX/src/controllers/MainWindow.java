package controllers;

import classes.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.util.Callback;

/**
 * Controller della MainWindow.fxml
 * @author Daniel Satriano
 * @author Claudio Menegotto
 * @since 5/05/2021
 */
public class MainWindow implements Initializable {
    @FXML private JFXButton BT_Home;
    @FXML private JFXButton BT_RegistraCentro;
    @FXML private JFXButton BT_RegistraVaccinato;
    @FXML private JFXButton BT_Storico;
    @FXML private JFXButton BT_Impostazioni;
    @FXML private ImageView IMG_reduce;
    @FXML private ImageView IMG_restoredown;
    @FXML private ImageView IMG_exit;
    @FXML private Label LB_dataAggiornamento;
    @FXML private Label LB_numeroVeffettuati;
    @FXML private Label LB_numeroVgiornalieri;
    @FXML private Label LB_vaccinazioniCompletate;
    @FXML private PieChart PC_home;
    @FXML private BarChart<String, Number> BC_home;
    @FXML private JFXTreeTableView<Storico> TTV_storico;
    @FXML private GridPane GP_Home;//grid per la visualizzazione della home
    @FXML private GridPane GP_RegistraCentro;//grid per la visualizzazione della registrazione di un centro vaccinale
    @FXML private GridPane GP_RegistraVaccinato;//grid per la visualizzazione della registrazione di un vaccinato
    @FXML private GridPane GP_Storico; //grid per la visualizzazione dello storico
    private Stage stage = null;
    private double currentWindowX;
    private double currentWindowY;
    private boolean max_min = false;
    private ObservableList<Storico> storici = FXCollections.observableArrayList();
    private double xOffset;
    private double yOffset;

    /**
     * Metodo default a cui la grafic accede
     * @param url
     * @param resourceBundle
     * @author Daniel Satriano
     * @author Claudio Menegotto
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //_-----------------

        //System.out.println(FilePaths.CittadiniRegistrati + "\n" + FilePaths.CentriVaccinali + "\n" + FilePaths.VaccinatiNomeCentro); //used for tests
        storici.add(new Storico("Questo è un test per lo storico",LocalDateTime.now()));
        storici.add(new Storico("Queso storicodddddddddddddddddddddddddddddddddd2",LocalDateTime.now()));
        storici.add(new Storico("Questo è r lo storico3",LocalDateTime.now()));
        storici.add(new Storico("Questo è un test 4",LocalDateTime.now()));
        //_-----------------
        JsonArray array;
        AdjustTableTreeView();
        BT_Selection(BT_Home);
        GP_selection(GP_Home);
        try {
            array = ApiRequest.makeRequest("https://lab24.ilsole24ore.com/_json/vaccini/dati-mondo.json");
        }catch (Exception e){
            array = null;
            System.out.println("Si è verificato un errore durante il recupero dei dati");
        }
        popolaHome(array);


    }

    /**
     * Evento che viene sfruttato dall'evento "dragWindowEvent" per prendere gli offset iniziali
     * @param event
     * @author Daniel Satriano
     * @since 16/07/2021
     */
    @FXML
    void PressedWindowEvent(MouseEvent event) {
        stage = (Stage) IMG_reduce.getScene().getWindow();
        xOffset = stage.getX() - event.getScreenX();
        yOffset = stage.getY() - event.getScreenY();
    }
    /**
     * Evento che va ad abilitare il drag della window, utilizza xOffset e yOffeset che vengono settati precedentemente dall'evento "PressedWindowEvent"
     * @param event
     * @author Daniel Satriano
     * @since 16/07/2021
     */
    @FXML
    void dragWindowEvent(MouseEvent event) {
        stage = (Stage) IMG_reduce.getScene().getWindow();
        stage.setX(event.getScreenX() + xOffset);
        stage.setY(event.getScreenY() + yOffset);
    }

    /**
     * OnMouseRelease event
     * @param event
     * @author Daniel Satriano
     * @since 10/05/2021
     */
    @FXML
    void tabClicked(MouseEvent event) {
        JFXButton cast = (JFXButton)event.getSource();
        BT_Selection(cast);
        switch (cast.getId()){
            case "BT_Home":
                GP_selection(GP_Home);
                break;
            case "BT_Impostazioni":
                break;
            case "BT_RegistraCentro":
                GP_selection(GP_RegistraCentro);
                break;
            case "BT_RegistraVaccinato":
                GP_selection(GP_RegistraVaccinato);
                break;
            case "BT_Storico":
                GP_selection(GP_Storico);
                break;
            default:
                System.out.println("Errore nello switch dei pulsanti di tabulazione");
                break;
        }
        BT_Selection(cast);

    }

    /**
     * Evento che gestisce la chiusura della window, il restoredown/maximase , il riduci window.
     * @param event
     * @author Daniel Satriano
     * @since 10/05/2021
     */
    @FXML
    void window_status(MouseEvent event) {
        stage = (Stage) IMG_reduce.getScene().getWindow();
        ImageView cast = (ImageView)event.getSource();
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
     * @param array jsonObject contenente i dati per popolare i grafici
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
     * @author Claudio Menegotto
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

    /**
     *
     * @param currentGrid
     */
    private void GP_selection(GridPane currentGrid){
        GP_Home.setVisible(false);
        GP_Home.setDisable(true);

        GP_RegistraCentro.setVisible(false);
        GP_RegistraCentro.setDisable(true);

        GP_RegistraVaccinato.setVisible(false);
        GP_RegistraVaccinato.setDisable(true);

        GP_Storico.setVisible(false);
        GP_Storico.setDisable(true);

        currentGrid.setVisible(true);
        currentGrid.setDisable(false);
        currentGrid.toFront();
    }

    /**
     * Metodo usato per inserire le colonne personalizzate nella ListTreeView.
     * @author Daniel Satriano
     * @since 05/05/2021
     */
    private void AdjustTableTreeView(){
        //Colonna azione
        JFXTreeTableColumn<Storico, String> azione = new JFXTreeTableColumn<>("Azione");
        azione.setPrefWidth(400);
        azione.setMinWidth(400);
        azione.setId("TTC_azione");
        azione.setReorderable(false);
        azione.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Storico, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Storico, String> param) {
                return param.getValue().getValue().azione;
            }
        });

        //Colonna Data somministrazione
        JFXTreeTableColumn<Storico, String> data = new JFXTreeTableColumn<>("Data");
        data.setPrefWidth(200);
        data.setMinWidth(200);
        data.setReorderable(false);

        data.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Storico, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Storico, String> param) {
                return param.getValue().getValue().dataSomministrazione;
            }
        });

        //Colonna Ora somministrazione
        JFXTreeTableColumn<Storico, String> ora = new JFXTreeTableColumn<>("Ora");
        ora.setPrefWidth(200);
        ora.setMinWidth(200);
        ora.setReorderable(false);
        ora.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Storico, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Storico, String> param) {
                return param.getValue().getValue().oraSomministrazione;
            }
        });

        //SERVE PER IMPLEMENTARE LA POSSIBILITA' DI RIORDINARE I RISULTATI SULLA WINDOW FINALE

        final TreeItem<Storico> root = new RecursiveTreeItem<Storico>(storici, RecursiveTreeObject::getChildren);
        TTV_storico.setRoot(root);
        TTV_storico.setShowRoot(false);
        TTV_storico.getColumns().setAll(azione,data,ora);
    }
}