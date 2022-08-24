package Controllers;

import Classes.*;
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
import java.rmi.NotBoundException;
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


    //misure della window home e settaggio a false di una variabile booleana per la gestione della window.
    private double currentWindowX;
    private double currentWindowY;
    private boolean max_min = false;

    /**
     * Evento che gestisce la chiusura della window, il restore down/maximase , il riduci a window.
     * @author Satriano Daniel
     * @since 10/05/2021
     */
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

    /**
     * Evento che gestisce il cambio di window per passare dalla registrazione al login.
     * @author Cavallini Francesco
     */
    public void lablelClick(javafx.scene.input.MouseEvent mouseEvent)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXML/Login.fxml"));

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

    /**
     * metodo di inizializzazione della window.
     * non è stato usato in questa parte di programma.
     * @author De Nicola Cristian
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    /**
     * metodo utilie all'inizializzazione di nome centro alla creazione della finestra da codice.
     * @param nomeCen nome del centro vaccinale
     * @author Cavallini Francesco
     */
    public void inizializza(String nomeCen)
    {
        nomeCentro = nomeCen;
    }

    //creazione variabili utilizzate di seguito.
    String nomeCentro; //nome del centro vaccinale,
    String nome; // nome dell'utente,
    String cognome; //cognome dell'utente,
    String codFiscale; //codice fiscale dell'utente,
    String mail; //mail dell'utente con cui viene registrato,
    String psw; //password dell'utente con cui viene registrato.+

    //da specifiche il nome di questo metodo deve diventare: registraCittadino()
    /**
     * evento click del BtnRegistrazione che permette di registrare il nuovo utente nel sistema.
     * comprende:
     * la presa in carico dei nuovi dati,
     * il confronto quindi controllo di questi dati con quelli già presenti nel sistema per controllare che non ci siano mail uguali.
     * il controllo se l'utente è già registrato presso un altro centro vaccianle.
     * @author Cavallini Francesco
     */
    @FXML
    public void registraCittadino(javafx.event.ActionEvent actionEvent)
    {
        try {
            //presa in carico dei nuovi dati.
            nome = TxtNome.getText().toString();
            cognome = TxtCognome.getText().toString();
            codFiscale = TxtCodiceFiscale.getText().toString();
            mail = TxtNUtente.getText().toString();
            psw = PFpassword.getText().toString();

            //PARTE RMI
            try {
                DatabaseHelper db = new DatabaseHelper();
                String risposta = db.Registrazione(nome, cognome, nomeCentro, codFiscale, mail, psw);
                JOptionPane.showMessageDialog(null, risposta);
                if(risposta.equals("Registrazione completata")){
                    //se la registrazione è avvenuta con successo allora faccio il login
                    LoginBox.login(mail,psw,nomeCentro);
                }
            } catch (NotBoundException e) {
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

