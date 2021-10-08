package Controllers;

import Classes.JsonReadWrite;
import Classes.UtenteCredenziali;
import Classes.UtenteVaccinato;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Registrazione implements Initializable {
    @FXML
    private ImageView IMG_reduce;

    @FXML
    private ImageView IMG_restoredown;

    @FXML
    private ImageView IMG_exit;

    @FXML
    private PasswordField PFpassword;

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtCognome;

    @FXML
    private TextField TxtNUtente;

    @FXML
    private JFXButton BtnRegistrazione;

    @FXML
    private TextField TxtCodiceFiscale;

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
                    IMG_restoredown.setImage(new javafx.scene.image.Image(getClass().getResource("/Images/lightMode/img_gp_black.png").toExternalForm()));
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

    public void lablelClick(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            //String absolutePath = System.getProperty("user.dir") + Paths.get("../FXML/CentroVaccinaleRG.fxml");

            fxmlLoader.setLocation(getClass().getResource("../FXML/Login.fxml"));

            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);

            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            stage.setX((int)size.getWidth()/2 + 170);
            stage.setY((int)size.getHeight()/2 - 350);

            stage.setTitle("Scheda Login");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void inizializza(String nomeCen)
    {
        nomeCentro = nomeCen;
    }

    String nomeCentro;
    String nome;
    String cognome;
    String codFiscale;
    String mail;
    String psw;
    @FXML
    public void BtnRegistrzioneClick(javafx.event.ActionEvent actionEvent) {

        try {
            nome = TxtNome.getText().toString();
            cognome = TxtCognome.getText().toString();
            codFiscale = TxtCodiceFiscale.getText().toString();
            mail = TxtNUtente.getText().toString();
            psw = PFpassword.getText().toString();

            JsonReadWrite rw = new JsonReadWrite();

            UtenteVaccinato uv = new UtenteVaccinato(nomeCentro, nome, cognome, codFiscale);
            UtenteCredenziali uc = new UtenteCredenziali(uv.IDUser.toString(),mail,psw);

            List<UtenteCredenziali> listaCredenziali = JsonReadWrite.leggiCredenziali();

            boolean check = false; //usa un algoritmo di ricerca diverso + aggiungi analisi di complessità
            if(listaCredenziali.size() > 0)
            {
                for (int i = 0; i<listaCredenziali.size(); i++) {
                    if(listaCredenziali.get(i).indirizzoEmail.equals(mail))
                    {
                        check = true;
                    }
                }
            }

            if(!check) // se l'utente non è ancora registrato
            {
                JsonReadWrite.registraVaccinato(uv);
                JsonReadWrite.registraCredenziali(uc);

                /* CODICE CREAZIONE NUOVA FINESTRA
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/CentroVaccinaleRG.fxml"));
                Parent root;
                root = (Parent) fxmlLoader.load();
                CentroVaccinaleRG controller = fxmlLoader.getController();
                controller.IsLogin = true;
                controller.BtnEventoAvverso.setVisible(true);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);

                //stage.show();
                 */
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Hai già usato questa mail per iscriverti, clicca sul link sottostante per loggare");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

