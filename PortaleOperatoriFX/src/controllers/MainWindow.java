package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;

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

    private double currentWindowX;
    private double currentWindowY;
    private boolean max_min = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BT_Selection(BT_Home);
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
                    IMG_restoredown.setImage(new Image(getClass().getResource("/Images/img_gp.png").toExternalForm()));
                }else{
                    stage.setWidth(currentWindowX);
                    stage.setHeight(currentWindowY);
                    IMG_restoredown.setImage(new Image(getClass().getResource("/Images/img_maximise.png").toExternalForm()));
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
     *
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