package controllers;

import ClassSerializers.StoricoSerialize;
import classes.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import java.io.Writer;
import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.chart.PieChart;
import javafx.util.Callback;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
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

    /**
     * variabili per il form di registrazione centro
     * @author Claudio Menegotto
     */
    @FXML private TextField TF_NomeNuovoCentro;
    @FXML private JFXComboBox<String> CB_TipologiaNuovoCentro;
    @FXML private JFXComboBox<String> CB_Qualificatore;
    @FXML private TextField TF_NomeVia;
    @FXML private TextField TF_NumeroCivico;
    @FXML private TextField TF_Comune;
    @FXML private TextField TF_CAP;
    @FXML private TextField TF_Provincia;
    @FXML private JFXButton BT_Register_centro;//Button per il salvataggio su file dei dati del nuovo centro
    //fine variabili registrazione centro

    /**
     * variabili per il form nuova vaccinazione
     * @author Claudio Menegotto
     */
    @FXML private TextField TF_NomeCentroVaccinazione;
    @FXML private TextField TF_NomeVaccinato;
    @FXML private TextField TF_CognomeVaccinato;
    @FXML private DatePicker DP_DataVaccinazione;
    @FXML private ComboBox<String> CB_Vaccino;
    @FXML private TextField TF_idVaccinazione;
    @FXML private TextField TF_CodiceFiscale;
    @FXML private JFXButton BT_Register_vaccinazione;//Button per la registrazione del nuovo vaccinato
    //fine variabili nuova vaccinazione

    private Stage stage = null;
    private double currentWindowX;
    private double currentWindowY;
    private boolean max_min = false;
    public ObservableList<Storico> storici = FXCollections.observableArrayList();
    private double xOffset;
    private double yOffset;

    /**
     * Metodo default a cui la grafica accede
     * @param url
     * @param resourceBundle
     * @author Daniel Satriano
     * @author Claudio Menegotto
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //_-----------------

        /**
         * legge da file json inserendo i dati decodificati nella lista e in grafica
         * @author Claudio Menegotto
         */
        try {
            storici = JsonReadWrite.readFromFile(FilePaths.VaccinatiNomeCentro);
        }catch (Exception e) {
            //errore nell'aprire il file di salvataggio dati
            System.out.println(e.toString());
        }

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

        //riempimento combo box nuovo centro
        ObservableList<String> qualificatori = FXCollections.observableArrayList();;
        qualificatori.add("Via");
        qualificatori.add("Viale");
        qualificatori.add("Piazza");
        qualificatori.add("Corso");
        CB_Qualificatore.setItems(qualificatori);

        ObservableList<String> tipologiaCentro = FXCollections.observableArrayList();;
        tipologiaCentro.add("Aziendale");
        tipologiaCentro.add("Ospedaliero");
        tipologiaCentro.add("Hub");
        CB_TipologiaNuovoCentro.setItems(tipologiaCentro);

        //riempimento combo box per la nuova vaccinazione
        ObservableList<String> Vaccini = FXCollections.observableArrayList();;
        Vaccini.add("Pfizer");
        Vaccini.add("AstraZeneca");
        Vaccini.add("Moderna");
        Vaccini.add("JeJ");
        CB_Vaccino.setItems(Vaccini);
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
        //BT_Selection(cast);
        switch (cast.getId()){
            case "BT_Home":
                GP_selection(GP_Home);
                BT_Selection(cast);
                break;
            case "BT_Impostazioni":
                break;
            case "BT_RegistraCentro":
                GP_selection(GP_RegistraCentro);
                BT_Selection(cast);
                break;
            case "BT_RegistraVaccinato":
                GP_selection(GP_RegistraVaccinato);
                BT_Selection(cast);
                break;
            case "BT_Storico":
                GP_selection(GP_Storico);
                BT_Selection(cast);
                break;
            case "BT_Register_centro":
                BT_RegistraCentro();
                break;
            case "BT_Register_vaccinazione":
                BT_NuovaVaccinazione();
                break;
            default:
                System.out.println("Errore nello switch dei pulsanti di tabulazione");
                break;
        }

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
     * @author Claudio Menegotto
     * @author Daniel Satriano
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
     * Metodo per nascondere tutte le grid eccetto la selezionata nel menu laterale
     * @param currentGrid
     * @author Claudio Menegotto
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

    /**
     * Metodo per il salvataggio dati nella la creaione del nuovo centro
     * @author Claudio Menegotto
     */
    private void BT_RegistraCentro(){
        if(CB_Qualificatore.getValue()!=null && CB_TipologiaNuovoCentro.getValue()!=null && TF_NomeNuovoCentro.getText().toString()!= "" && TF_NomeVia.getText().toString() != "" && TF_NumeroCivico.getText().toString() != "" && TF_Comune.getText().toString() != "" && TF_Provincia.getText().toString() != "" && TF_CAP.getText().toString() != "") {
            Qualificatore qualificatore;
            Tipologia tipologiaCentro;
            qualificatore = Qualificatore.valueOf(CB_Qualificatore.getValue());
            tipologiaCentro = Tipologia.valueOf(CB_TipologiaNuovoCentro.getValue());
            CentroVaccinale centro = new CentroVaccinale(TF_NomeNuovoCentro.getText().toString(), new Indirizzo(qualificatore, TF_NomeVia.getText().toString(), Integer.parseInt(TF_NumeroCivico.getText().toString()), TF_Comune.getText().toString(), TF_Provincia.getText().toString(), Integer.parseInt(TF_CAP.getText().toString())), tipologiaCentro);
            //richiamo il metodo per la scrittura su file
            try {
                JsonReadWrite.RegistraCentroVaccinale(centro);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo per il salvataggio dati della nuova vaccinazione per il salvataggio su file
     * @author Claudio Menegotto
     */
    private void BT_NuovaVaccinazione()
    {
        if(TF_NomeCentroVaccinazione.getText().toString() != "" && TF_CognomeVaccinato.getText().toString() != "" && TF_NomeVaccinato.getText().toString() != "" && CB_Vaccino.getValue() != null && TF_idVaccinazione.getText().toString() != "" && TF_CodiceFiscale.getText().toString() != "") {
            Vaccini vaccino;
            String data = DP_DataVaccinazione.getValue().toString();
            vaccino = Vaccini.valueOf(CB_Vaccino.getValue().toString());
            UtenteVaccinato Vaccinato = new UtenteVaccinato(TF_NomeCentroVaccinazione.getText().toString(), TF_NomeVaccinato.getText().toString(), TF_CognomeVaccinato.getText().toString(), TF_CodiceFiscale.getText().toString(), data, vaccino,Short.valueOf(TF_idVaccinazione.getText().toString()));
            //da fare il salvataggio su file
        }
    }
}