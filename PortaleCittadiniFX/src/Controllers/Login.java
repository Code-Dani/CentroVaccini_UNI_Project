package Controllers;

import classes.*;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login implements Initializable {

    @FXML
    private ImageView IMG_reduce;

    @FXML
    private ImageView IMG_restoredown;

    @FXML
    private ImageView IMG_exit;

    @FXML
    private PasswordField PFpassword;

    @FXML
    private TextField TxtNUtente;

    @FXML
    private JFXButton BtnLogin;

    @FXML
    private Label LblReindirizzamento;


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
    public void window_status(javafx.scene.input.MouseEvent mouseEvent)
    {
        Stage stage = null;
        ImageView cast = (ImageView)mouseEvent.getSource();
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
     * Evento che gestisce il cambio di window per passare dal login alla registrazione
     * @author Cavallini Francesco
     */
    @FXML
    public void labelClick(javafx.scene.input.MouseEvent mouseEvent)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML/Registrazione.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);

            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            stage.setX((int)size.getWidth()/2 + 170);
            stage.setY((int)size.getHeight()/2 - 350);

            stage.setTitle("Scheda Registrazione");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            Stage stage2 = new Stage();
            stage2 = (Stage) IMG_reduce.getScene().getWindow();
            stage2.close();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    /**
     * metodo di inizializzazione della window.
     * non è stato usato in questa parte di programma.
     * @author Cavallini Francesco
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    CentroVaccinale identitaCentro;
    /**
     * metodo che permette di settare parametri, in questo caso il nome del centro vaccinale tramite una variabile di tipo string.
     * questo dato verrà utilizzato poi per passarlo alla window di centro vaccinale.
     * @param nomeCentro nome del centro vaccinale.
     */
    public void setParameters(CentroVaccinale nomeCentro)
    {
        identitaCentro = nomeCentro;
    }

    /**
     * Evento che gestisce il cambio di window per passare al login
     * @author Cavallini Francesco
     */
    @FXML
    public void BtnLoginClick(MouseEvent mouseEvent)
    {
        try {
            Stage stage = (Stage) BtnLogin.getScene().getWindow();

            //recupero dati dal file
            String email=TxtNUtente.getText().toString();
            String psw=PFpassword.getText().toString();

            //uso un metodo statico della classe LoginBox per controllare il login.
            LoginBox.login(email, psw, identitaCentro.nome);
            stage.close();

        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Errore: " + e.toString());
        }
    }
}
