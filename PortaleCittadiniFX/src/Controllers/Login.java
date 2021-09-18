package Controllers;

import Classes.JsonReadWrite;
import Classes.UtenteCredenziali;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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



    private double currentWindowX;
    private double currentWindowY;
    private boolean max_min = false;

    public void window_status(javafx.scene.input.MouseEvent mouseEvent) {
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

    public void labelClick(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            //String absolutePath = System.getProperty("user.dir") + Paths.get("../FXML/CentroVaccinaleRG.fxml");

            fxmlLoader.setLocation(getClass().getResource("../FXML/Registrazione.fxml"));
            //fxmlLoader.setController("../Controllers/CentroVaccinaleRG.java");

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

    public void BtnLoginClick()
    {
        //ANCORA DA TESTARE
        try {
            //recupero dati da file +
            String email=TxtNUtente.getText().toString();
            String psw=PFpassword.getText().toString();

            JsonReadWrite rw = new JsonReadWrite();
            List<UtenteCredenziali> utenti = rw.leggiRegistrati();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/CentroVaccinaleRG.fxml"));
            Parent root;
            root = (Parent) fxmlLoader.load();
            CentroVaccinaleRG controller = fxmlLoader.getController();

            for(int i=0;i<utenti.size();i++)
            {
                //controllo nella lista utenti se ne ho uno a cui corrsiponde mail e pass
                if(utenti.get(i).indirizzoEmail.toString().equals(email)&&utenti.get(i).password.toString().equals(psw))
                {
                    controller.IsLogin = true;
                    controller.BtnEventoAvverso.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Errore riprova");
                }

            }

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Centro Vaccinale");
            stage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
