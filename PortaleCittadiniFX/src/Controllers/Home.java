package Controllers;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.awt.*;
import java.net.URL;


public class Home {

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
        private MenuItem MenuItem1;

        @FXML
        private MenuItem MenuItem2;

        @FXML
        private MenuItem MenuItem3;

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
         * aggiornamento lista tramite barra di ricerca txtRicerca
         * @param event
         * @author Cavallini Francesco
         * @since 02/08/2021
         */
        @FXML
        void TextChanged(InputMethodEvent event) {
                //METODO PER TESTING
                if(txtRicereca.getText() == "x")
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
        void CBFilter(MouseEvent event) {

        }

        /**
         * filtro che si applica con click delle checkbox regolari
         * @param event
         * @author Cavallini Francesco
         * @since 02/08/2021
         */
        @FXML
        void RCBFilter(MouseEvent event) {

        }

}



