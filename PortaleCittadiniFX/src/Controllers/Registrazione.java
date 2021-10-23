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

    /**
     * Evento che gestisce la chiusura della window, il restoredown/maximase , il riduci window.
     * @param mouseEvent
     * @author Satriano Daniel
     * @since 10/05/2021
     */
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

    /**
     * Evento che gestisce il cambio di window per passare al login
     * @param mouseEvent
     * @author Cavallini Francesco
     */
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

    /**
     * metodo utilie all'inizializzazione di nome centro alla creazione della finestra da codice.
     * @param nomeCen nome del centro vaccinale
     * @author Cavallini Francesco
     */
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
    /**
     * evento che scatena alla pressione del bottone di registrazione.
     * @param actionEvent
     * @author Cavallini Francesco
     */
    public void BtnRegistrzioneClick(javafx.event.ActionEvent actionEvent) {

        try {
            nome = TxtNome.getText().toString();
            cognome = TxtCognome.getText().toString();
            codFiscale = TxtCodiceFiscale.getText().toString();
            mail = TxtNUtente.getText().toString();
            psw = PFpassword.getText().toString();

            JsonReadWrite rw = new JsonReadWrite();

            //ricerca dell'utenente già vaccinato
            List<UtenteVaccinato> temp = JsonReadWrite.leggiVaccinati();
            List<UtenteVaccinato> listaVaccinati = new ArrayList();
            for(int i = 0; i < temp.size(); i++) {
                //System.out.println(temp.get(i));
                listaVaccinati.add(new UtenteVaccinato(temp.get(i).nomeCentroVaccinale, temp.get(i).nome,temp.get(i).cognome, temp.get(i).codiceFiscale, temp.get(i).dataSomministrazione , temp.get(i).vaccino , temp.get(i).getIdVaccinazione()));
            }

            boolean checkNome = false;
            boolean checkCodFiscale = false;
            short idVaccinazione = -1;
            if(listaVaccinati.size()>0)
            {
                for (int i = 0; i<listaVaccinati.size(); i++) {
                    if(listaVaccinati.get(i).nome.equals(nome) && listaVaccinati.get(i).cognome.equals(cognome))
                    {
                        checkNome = true;
                        if(listaVaccinati.get(i).codiceFiscale.equals(codFiscale))
                        {
                            checkCodFiscale = true;
                            idVaccinazione = listaVaccinati.get(i).getIdVaccinazione();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "ERRORE: il codice fiscale inserito non corrisponde con nome e cognome");
                        }
                    }
                }
            }

            //ricerca su registrati per vedere se l'utente è già registrato
            List<UtenteCredenziali> listaCredenziali = JsonReadWrite.leggiCredenziali();
            boolean checkFinale = false; //usa un algoritmo di ricerca diverso + aggiungi analisi di complessità
            if(listaCredenziali.size() > 0 && checkNome && checkCodFiscale)
            {
                for (int i = 0; i<listaCredenziali.size(); i++) {
                    if(listaCredenziali.get(i).indirizzoEmail.equals(mail))
                    {
                        checkFinale = true;
                    }
                }
            }

            if(!checkFinale) // se l'utente non è ancora registrato e a superato tutti i controlli
            {
                UtenteCredenziali uc = new UtenteCredenziali(idVaccinazione, mail, psw);
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

