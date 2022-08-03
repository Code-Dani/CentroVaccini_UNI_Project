package Controllers;

import Classes.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;


public class EventoAvverso implements Initializable {

    @FXML
    public JFXComboBox<String> ComboEventi;

    @FXML
    public TextArea TextNote;

    @FXML
    private ImageView IMG_reduce;

    @FXML
    private ImageView IMG_restoredown;

    @FXML
    private ImageView IMG_exit;

    @FXML
    public JFXSlider SliderGravita;

    @FXML
    public Label LbCount;

    @FXML
    public JFXButton BtnAddEvento;


    //lista contenente i vari eventi avversi che l'utente potrà scegliere.
    ObservableList<String> list= FXCollections.observableArrayList("mal_di_testa","febbre","dolori_muscolari_e_articolari","infoadonopatia","crisi_ipertensiva","altro"); ///metterci enum evento

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
     * metodo di inizializzazione della window.
     * viene inizializzata la Comboeventi con la list vista precedentemente.
     * in modo tale che contenga i vari eventi avversi
     * @author De Nicola Cristian
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ComboEventi.setItems(list);

        /**
         * listener che permette l'aggiornamento del count di caratteri inseriti nell'apposito box per il commento
         * @author Cavallini Francesco
         */
        TextNote.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                LbCount.setText(TextNote.getText().length()+"/255");
                if(TextNote.getText().length()>255)
                {
                    LbCount.setTextFill(Paint.valueOf("#FF0000"));
                    BtnAddEvento.setDisable(true);
                }
                else
                {
                    LbCount.setTextFill(Paint.valueOf("#000000"));
                    BtnAddEvento.setDisable(false);
                }
            }
        });
    }

    //da specifiche il nome di questo metodo deve cambiare in: inserisciEventiAvversi()
    //TODO: salvare evento avverso nel db
    /**
     * metodo che viene richiamato quando si vuole aggiungere un evento avverso
     * questo verra prima salvato e poi aggiunto alla list view del centro vaccinale
     * @author De Nicola Cristian
     * @since 04/11/2021
     */
    @FXML
    void inserisciEventiAvversi(MouseEvent event)
    {

        Evento e=Evento.altro; //servirà per creare l'oggetto EventoAvversoTMP, settati cosi da default
        Severita s=Severita.molto_bassa_1; //servirà per creare l'oggetto EventoAvversoTMP, settati cosi da default

        //prendo in carico i valori dei tre parametri
        String evento=ComboEventi.getValue();
        double grav= SliderGravita.getValue();
        String note= TextNote.getText(); //servirà per creare l'oggetto EventoAvversoTMP
        //fine

        //switch per prendere il corretto enum dall'elemento selezionato dall'utente
       switch (evento){
           case "mal_di_testa":
               if(evento.equals("mal_di_testa"))
                   e=Evento.mal_di_testa;
               break;
           case "febbre":
               if(evento.equals("febbre"))
                   e=Evento.febbre;
               break;
           case "dolori_muscolari_e_articolari":
               if(evento.equals("dolori_muscolari_e_articolari"))
                   e=Evento.dolori_muscolari_e_articolari;
               break;
           case "infoadonopatia":
               if(evento.equals("infoadonopatia"))
                   e=Evento.infoadonopatia;
               break;
           case "tachicardia":
               break;
           case "crisi_ipertensiva":
               if(evento.equals("crisi_ipertensiva"))
                   e=Evento.crisi_ipertensiva;
               break;
           case "altro":
               if(evento.equals("altro"))
                   e=Evento.altro;
               break;
           default:
               JOptionPane.showMessageDialog(null, "nessun evento selezionato");
       }
       //fine switch

       //divido in scaglioni la gravità dello slider (da 0 a 100 quindi in scaglioni da 20) per permettere di ottenere il corretto enum della gravità.
       if(grav > 0 && grav <=20)
           s=Severita.molto_bassa_1;
       else if (grav>20 && grav <=40)
           s= Severita.bassa_2;
       else if(grav>40 && grav <=60)
           s=Severita.fastidiosa_3;
       else if(grav>60 && grav <=80)
           s=Severita.sopportabile_4;
       else if(grav>80 && grav <=100)
           s=Severita.insopportabile_5;
       //fine

       short id=LoginBox.getIdVaccinazione(); //id dell'utente che richiede l'aggiunta dell'evento avverso (usata come chiave esterna per legare il tutto).
       String nomecognome=LoginBox.nome + " " + LoginBox.cognome; //stringa nome+cognome usata per trackare l'utente.
       //fine ricerca dei dati

       EventoAvversoTMP tmp= new EventoAvversoTMP(e,s,note,id,nomecognome);
       CentroVaccinaleRG.eventiAvv.add(tmp);
       //fine aggiunta nuovo evento avverso nella list view.

       //inizio salvataggio nel db
        try
        {
            List<UtenteVaccinato> lista = JsonReadWrite.leggiVaccinati();
            for(int i=0;i<lista.size();i++)
            {
                if(lista.get(i).idVaccinazione==id)
                {
                    lista.get(i).evento= new Classes.EventoAvverso(e,s,note,id);
                    JsonReadWrite.registraEventoxVaccinato(lista);
                }
            }
        }catch(Exception x){
            JOptionPane.showMessageDialog(null,x.getMessage().toString());
        }

        //chiudo la window
        Stage stage2 = new Stage();
        stage2 = (Stage) IMG_reduce.getScene().getWindow();
        stage2.close();
    }

    /**
     * utilizzato per l'inizializzazione della textnote, in modo tale che non ci sia scritto nulla,
     * @author De Nicola Cristian
     */
    public void OnMouseClicked(MouseEvent mouseEvent)
    {
        TextNote.setText("");
    }

}
