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
import java.util.ArrayList;
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

        //nelle parentesi angolari ci va l'oggetto da bindare con la lista, string ci sta solo di prova
        @FXML
        private JFXTreeTableView<CentroVaccinale> LWElenco;

        //lista dalla quale vengono mosstrati gli elementi dentro la table view
        ObservableList<CentroVaccinale> centri;
        ObservableList<CentroVaccinale> tmp;
        //variabili di stato delle checkbox quadrate
        private boolean BOspedale = true;
        private boolean BAzienda = true;
        private boolean Bhub = true;

        /**
         * inializza la window inserendo nella table view gli elementi della lista
         * @param url
         * @param resourceBundle
         * @author Cavallni Francesco
         * @since 21/08/2021
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle)
        {
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
                centri = FXCollections.observableArrayList();
                tmp = FXCollections.observableArrayList();



                //linia di prova giusto per vedere se funziona
                centri.add(new CentroVaccinale("Varese",new Indirizzo(Qualificatore.Via,"gallarate", 5, "gallarate", "VA", 21040), Tipologia.Ospedale, new ArrayList<Short>() ));
                centri.add(new CentroVaccinale("Gallarate",new Indirizzo(Qualificatore.Via,"canegrate", 5, "canegrate", "VA", 21040), Tipologia.Hub, new ArrayList<Short>() ));
                centri.add(new CentroVaccinale("Busto",new Indirizzo(Qualificatore.Via,"busto", 5, "busto", "VA", 21040), Tipologia.Aziendale, new ArrayList<Short>() ));
                centri.add(new CentroVaccinale("Varese",new Indirizzo(Qualificatore.Via,"varese", 5, "varese", "VA", 21040), Tipologia.Hub, new ArrayList<Short>() ));
                tmp = centri;
                //

                final TreeItem<CentroVaccinale> root = new RecursiveTreeItem<CentroVaccinale>(centri, RecursiveTreeObject::getChildren);
                LWElenco.getColumns().setAll(nome, tipo, ind);
                LWElenco.setRoot(root);
                LWElenco.setShowRoot(false);

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

                /*
                //listener che permette di filtrare la ricerca sulla lista di oggetti mettendo solo ospedali
                cbOspedale.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                                LWElenco.setPredicate(new Predicate<TreeItem<CentroVaccinale>>() {
                                        @Override
                                        public boolean test(TreeItem<CentroVaccinale> centroVaccinaleTreeItem) {
                                                Boolean flag = centroVaccinaleTreeItem.getValue().tipologia == Tipologia.Ospedale && t1;
                                                return flag;
                                        }
                                });
                        }
                });

                //listener che permette di filtrare la ricerca sulla lista di oggetti mettendo solo Hub
                cbHub.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                                LWElenco.setPredicate(new Predicate<TreeItem<CentroVaccinale>>() {
                                        @Override
                                        public boolean test(TreeItem<CentroVaccinale> centroVaccinaleTreeItem) {
                                                Boolean flag = centroVaccinaleTreeItem.getValue().tipologia == Tipologia.Hub && t1;
                                                return flag;
                                        }
                                });
                        }
                });

                //listener che permette di filtrare la ricerca sulla lista di oggetti mettendo solo aziende
                cbAzienda.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                                LWElenco.setPredicate(new Predicate<TreeItem<CentroVaccinale>>() {
                                        @Override
                                        public boolean test(TreeItem<CentroVaccinale> centroVaccinaleTreeItem) {
                                                Boolean flag = centroVaccinaleTreeItem.getValue().tipologia == Tipologia.Aziendale && t1;
                                                return flag;
                                        }
                                });
                        }
                });


                */

        }

        /**
         * Evento che gestisce la chiusura della window, il restoredown/maximase , il riduci window.
         * @param event
         * @author Satriano Daniel
         * @since 10/05/2021
         */
        private double currentWindowX;
        private double currentWindowY;
        private boolean max_min = false;

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
         * evento click che apre il browser e da più info sulle vaccinazioni e lo stato pandemia
         * @param event
         * @author Cavallini Francesco
         * @since 02/08/2021
         */
        @FXML
        void MouseClick(MouseEvent event) {
                try {
                        Desktop.getDesktop().browse(new URL("https://www.governo.it/it/cscovid19/report-vaccini/").toURI());
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        /**
         * filtro che si applica con click delle checkbox regolari
         * @param event
         * @author Cavallini Francesco
         * @since 02/08/2021
         */
        @FXML
        void CBFilter(MouseEvent event) {

                try {
                        //non capisco perchè mandi in loop il programma porcamadonna
                        tmp.removeAll();
                        if(cbOspedale.isSelected()) {
                                for (int i = 0; i < centri.size(); i++) {
                                        if (centri.get(i).tipologia.toString().equals(Tipologia.Ospedale.toString())) {
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

                }

        }

        /**
         * filtro che si applica con click delle checkbox circolari
         * @param event
         * @author Cavallini Francesco
         * @since 02/08/2021
         */
        @FXML
        void RCBFilter(MouseEvent event) {
                //ordina i centri vaccinali in ordine alfabetico per città dell'indiriozzo così sembra che siano davvero in ordine di posizione. (è solo una simulazione)
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

                        System.out.println(LWElenco.getCurrentItemsCount()); // questo dice che ce ne sono 4 e ne mette 5

                        for(int i=0; i<centri.size(); i++)
                        {
                                int min = i;
                                System.out.println(centri.get(i));
                                for(int j=i+1; j<tmp.size();j++)
                                {
                                        //System.out.println(tmp.get(j).nome.compareTo(tmp.get(min).nome));
                                }
                        } //questo ne stampa 4 nell'ordine giusto, fioi io non so che dire


                       //FIOI IL CODICE FUNZIONA, IN CONSOLE COME VEDETE LO STAMPA GIUSTO PERò JAVAFX FA SCHIFO E NON VUOLE MOSTRARE L'ELENCO CORRETTAMENTE, IO HO FATTO GIUSTO è JAVA CHE SBAGLIA

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

                        //centri.sort(new CentroVaccinaleComparator("vicinanza"));
                }
        }

        /**
         * evento che scatena l'apertura della seconda finiestra contenente tutte le informazioni sui centri vaccinali
         * @param event
         * @author Cavallini Francesco
         * @since 22/08/2021
         */
        @FXML
        void LWELencoClick(MouseEvent event) {
                try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        //String absolutePath = System.getProperty("user.dir") + Paths.get("../FXML/CentroVaccinaleRG.fxml");

                        fxmlLoader.setLocation(getClass().getResource("../FXML/CentroVaccinaleRG.fxml"));
                        //fxmlLoader.setController("../Controllers/CentroVaccinaleRG.java");

                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = new Stage();
                        stage.setTitle("New Window");
                        stage.setScene(scene);

                        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
                        stage.setX((int)size.getWidth()/2 - 570);
                        stage.setY((int)size.getHeight()/2 -350);

                        stage.setTitle("Scheda Centro Vaccinale");
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.show();

                } catch (IOException e) {
                        Logger logger = Logger.getLogger(getClass().getName());
                        logger.log(Level.SEVERE, "Failed to create new Window.", e);
                }
        }

}



