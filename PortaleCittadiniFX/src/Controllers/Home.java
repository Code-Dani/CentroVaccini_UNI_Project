package Controllers;

import Classes.*;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import jdk.swing.interop.LightweightContentWrapper;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Home implements Initializable {

        @FXML
        private Text TxtUtente;

        @FXML
        private Circle IcoUtente;

        @FXML
        private Text txtIniziale;

        @FXML
        private ImageView IMG_reduce;

        @FXML
        private ImageView IMG_restoredown;

        @FXML
        private ImageView IMG_exit;

        @FXML
        private CustomMenuItem MILogOut;

        @FXML
        private CustomMenuItem MICentroVaccinale;

        @FXML
        private CustomMenuItem MICrediti;

        @FXML
        private TextField txtRicereca;

        @FXML
        private Arc CrcIndiceContagio;

        @FXML
        private Text txtIndiceContagio;

        @FXML
        private Text txtLink;

        @FXML
        private Text txtValoreIndice;

        @FXML
        private JFXRadioButton cbOrdineAlfabetico;

        @FXML
        private ToggleGroup OrdinaPer;

        @FXML
        private JFXRadioButton cbVicinanza;

        @FXML
        private JFXCheckBox cbOspedale;

        @FXML
        private JFXCheckBox cbAzienda;

        @FXML
        private JFXCheckBox cbHub;

        @FXML
        public JFXTreeTableView<CentroVaccinale> LWElenco;


        ObservableList<CentroVaccinale> centri; //lista in cui vengono salvati i dati dei centri vaccinali da mostrare poi nella table view.
        ObservableList<CentroVaccinale> tmp; //lista temporanea.

        /**
         * variabili di stato booleane delle checkbox quadrate.
         * BOspedale fa riferimento alla checkbox ospedale.
         * BAzienda fa riferimento alla checkbox azienda.
         * Bhub fa riferimento alla checkbox hub.
         */
        private boolean BOspedale = true;
        private boolean BAzienda = true;
        private boolean Bhub = true;

        /**
         * inizializza la window home e la tabella iniziale;
         * inserisce nella table view gli elementi della ObservableList<CentroVaccinale> centri.
         * gestisce un listener che permette di effettuare la ricerca sulla lista di oggetti.
         * @author Cavallni Francesco
         * @since 21/08/2021
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle)
        {
                //inizio creazione tabella

                JFXTreeTableColumn<CentroVaccinale, String> ind = new JFXTreeTableColumn<>("Indirizzo");
                ind.setPrefWidth(400);
                ind.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CentroVaccinale, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CentroVaccinale, String> centroVaccinaleStringCellDataFeatures) {
                                return centroVaccinaleStringCellDataFeatures.getValue().getValue().indirizzo2;
                        }
                });

                JFXTreeTableColumn<CentroVaccinale, String> nome = new JFXTreeTableColumn<>("Nome");
                nome.setPrefWidth(250);
                nome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CentroVaccinale, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CentroVaccinale, String> centroVaccinaleStringCellDataFeatures) {
                                return centroVaccinaleStringCellDataFeatures.getValue().getValue().nome2;
                        }
                });

                JFXTreeTableColumn<CentroVaccinale, String> tipo = new JFXTreeTableColumn<>("Tipologia");
                tipo.setPrefWidth(128);
                tipo.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CentroVaccinale, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CentroVaccinale, String> centroVaccinaleStringCellDataFeatures) {
                                return centroVaccinaleStringCellDataFeatures.getValue().getValue().tipologia2;
                        }
                });
                //fine creazione tabella

                //inizzializzazione delle due liste viste precedentemente.
                centri = FXCollections.observableArrayList();
                tmp = FXCollections.observableArrayList();

                //imposto i parametri leggendo il file centro vaccinali.
                JsonReadWrite RW = new JsonReadWrite();
                try {
                        List<CentroVaccinale> temp = RW.ReadFromFileCentroVaccinali();
                        for(int i = 0; i < temp.size(); i++)
                        {
                                centri.add(new CentroVaccinale(temp.get(i).nome, temp.get(i).indirizzo,temp.get(i).tipologia, temp.get(i).IDVaccinazioni));
                        }
                }catch (Exception E) {
                        System.out.println(E);
                }
                tmp = centri;
                //fine

                //inserimento colonne della tabella create precedentemente in grafica
                final TreeItem<CentroVaccinale> root = new RecursiveTreeItem<CentroVaccinale>(centri, RecursiveTreeObject::getChildren);
                LWElenco.getColumns().setAll(nome, tipo, ind);
                LWElenco.setRoot(root);
                LWElenco.setShowRoot(false);
                //fine

                //listener che permette di effettuare la ricerca sulla lista di oggetti
                txtRicereca.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                                LWElenco.setPredicate(new Predicate<TreeItem<CentroVaccinale>>() {
                                        @Override
                                        public boolean test(TreeItem<CentroVaccinale> centroVaccinaleTreeItem) {

                                               Boolean textFlag = centroVaccinaleTreeItem.getValue().nome.contains(t1);
                                               return textFlag;
                                        }
                                });
                        }
                });
        }

        //misure della window home e settaggio a false di una variabile booleana per la gestione della window.
        private double currentWindowX;
        private double currentWindowY;
        private boolean max_min = false;

        /**
         * Evento che gestisce la chiusura della window, il restore down/maximase , il riduci a window.
         * @author Satriano Daniel
         * @since 10/05/2021
         */
        @FXML
        void window_status(MouseEvent event)
        {
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
                                max_min = !max_min;

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
         * evento click che apre il browser e da più info sulle vaccinazioni e lo stato della pandemia.
         * fonte: "https://www.governo.it/it/cscovid19/report-vaccini/".
         * @author Cavallini Francesco
         * @since 02/08/2021
         */
        @FXML
        void MouseClick(MouseEvent event)
        {
                try {
                        Desktop.getDesktop().browse(new URL("https://www.governo.it/it/cscovid19/report-vaccini/").toURI());
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        /**
         * Evento applicato al selezionamento di una delle checkbox quadrate nella home.
         * queste checkbox fungono da filtro per le ricerche dell'utente.
         * @author Cavallini Francesco
         * @since 02/08/2021
         */
        @FXML
        void CBFilter(MouseEvent event)
        {
                try {
                        tmp.removeAll();
                        if(cbOspedale.isSelected()) {
                                for (int i = 0; i < centri.size(); i++) {
                                        if (centri.get(i).tipologia.toString().equals(Tipologia.Ospedaliero.toString())) {
                                                tmp.add(centri.get(i));
                                        }
                                }
                        }
                       if(cbHub.isSelected()) {
                                for (int i = 0; i < centri.size(); i++) {
                                        if (centri.get(i).tipologia.toString().equals(Tipologia.Hub.toString())) {
                                                tmp.add(centri.get(i));
                                        }
                                }
                        }
                       if(cbAzienda.isSelected()) {
                                for (int i = 0; i < centri.size(); i++) {
                                        if (centri.get(i).tipologia.toString().equals(Tipologia.Aziendale.toString()) ) {
                                                tmp.add(centri.get(i));
                                        }
                                }
                        }

                       centri = tmp;
                       for(int i=0; i<centri.size(); i++)
                        {
                                int min = i;
                                System.out.println(centri.get(i));
                                for(int j=i+1; j<tmp.size();j++)
                                {
                                        //System.out.println(tmp.get(j).nome.compareTo(tmp.get(min).nome));
                                }
                        }
                }catch (Exception E)
                {
                        System.out.println(E);
                }
        }

        /**
         * Evento applicato al selezionamento di una delle checkbox rotonde nella home.
         * queste checkbox fungono da filtro per le ricerche dell'utente.
         * @author Cavallini Francesco
         * @since 02/08/2021
         */
        @FXML
        void RCBFilter(MouseEvent event)
        {
                if(cbOrdineAlfabetico.isSelected())
                {
                        //selection sort
                        tmp = centri;
                        for(int i=0; i<tmp.size(); i++)
                        {
                                int min = i;
                                for(int j=i+1; j<tmp.size();j++)
                                {
                                        if(tmp.get(j).nome.compareTo(tmp.get(min).nome) < 0)
                                        {
                                                CentroVaccinale temp;
                                                temp = tmp.get(j);
                                                tmp.set(j,tmp.get(min));
                                                tmp.set(min, temp);
                                        }
                                }
                        }

                        centri.removeAll();
                        centri = tmp;
                        LWElenco.refresh();
                        System.out.println(LWElenco.getCurrentItemsCount());


                        for(int i=0; i<centri.size(); i++)
                        {
                                int min = i;
                                System.out.println(centri.get(i));
                                for(int j=i+1; j<tmp.size();j++)
                                {
                                        //System.out.println(tmp.get(j).nome.compareTo(tmp.get(min).nome));
                                }
                        }
                }
                else {
                        //selection sort
                        tmp = centri;
                        for(int i=0; i<tmp.size(); i++)
                        {
                                int min = i;
                                for(int j=i+1; j<tmp.size();j++)
                                {
                                        if(tmp.get(j).indirizzo.comune.compareTo(tmp.get(min).indirizzo.comune) < 0)
                                        {
                                                CentroVaccinale temp;
                                                temp = tmp.get(j);
                                                tmp.set(j,tmp.get(min));
                                                tmp.set(min, temp);
                                        }
                                }
                        }

                        centri.removeAll();
                        centri = tmp;
                        LWElenco.refresh();

                        for(int i=0; i<centri.size(); i++)
                        {
                                int min = i;
                                System.out.println(centri.get(i));
                                for(int j=i+1; j<tmp.size();j++)
                                {
                                        //System.out.println(tmp.get(j).nome.compareTo(tmp.get(min).nome));
                                }
                        }
                }
        }

        /**
         * evento che scatena l'apertura della nuova window "CentroVaccinaleRG" contenente
         * tutte le informazioni sui centri vaccinali
         * la possibilità di loggare (e in seguito aggiungere un evento avverso) o fare la registrazione.
         * @author Cavallini Francesco
         * @since 22/08/2021
         */
        @FXML
        void LWELencoClick(MouseEvent event)
        {
                try {
                        if(event.getButton().equals(MouseButton.PRIMARY)){
                                if(event.getClickCount() == 2){
                                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/CentroVaccinaleRG.fxml"));
                                        Parent root;
                                        root = (Parent) fxmlLoader.load();
                                        CentroVaccinaleRG controller = fxmlLoader.getController();
                                        controller.setParameters( LWElenco.getSelectionModel().getSelectedItem().getValue() );

                                        Scene scene = new Scene(root);
                                        Stage stage = new Stage();
                                        stage.setTitle("Centro Vaccinale");
                                        stage.setScene(scene);

                                        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
                                        stage.setX((int)size.getWidth()/2 - 570);
                                        stage.setY((int)size.getHeight()/2 -350);

                                        stage.setTitle("Scheda Centro Vaccinale");
                                        stage.initStyle(StageStyle.UNDECORATED);
                                        stage.show();
                                }
                        }
                } catch (IOException e) {
                        Logger logger = Logger.getLogger(getClass().getName());
                        logger.log(Level.SEVERE, "Failed to create new Window.", e);
                }
        }
}



