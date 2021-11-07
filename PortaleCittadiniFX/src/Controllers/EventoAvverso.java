package Controllers;

import Classes.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class EventoAvverso implements Initializable {

    //variabili usate:
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


    ObservableList<String> list= FXCollections.observableArrayList("mal_di_testa","febbre","dolori_muscolari_e_articolari","infoadonopatia","crisi_ipertensiva","altro"); ///metterci enum evento

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ComboEventi.setItems(list);
    }

    /**
     * metodo che viene richiamato quando si vuole aggiungere un evento avverso
     * questo verra prima salvato e poi aggiunto alla list view del centro vaccinale
     * @author De Nicola Cristian
     * @since 04/11/2021
     */
    @FXML
    void BtnClickAdd(MouseEvent event)
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
           s=Severita.bassa_2;
       else if(grav>40 && grav <=60)
           s=Severita.fastidiosa_3;
       else if(grav>60 && grav <=80)
           s=Severita.sopportabile_4;
       else if(grav>80 && grav <=100)
           s=Severita.insopportabile_5;
       //fine

       short id=LoginBox.getIdVaccinazione();
       String nomecognome=LoginBox.getNomeCognome();
       EventoAvversoTMP tmp= new EventoAvversoTMP(e,s,note,id,nomecognome);
       CentroVaccinaleRG.eventiAvv.add(tmp);
       ///fine aggiunta nuovo evento avverso nella list view.
        //inizio salvataggio nel db

        try{
            List<UtenteVaccinato> lista = JsonReadWrite.leggiVaccinati();
            for(int i=0;i<lista.size();i++)
            {
                if(lista.get(i).idVaccinazione==id){
                    lista.get(i).evento= new Classes.EventoAvverso(e,s,note,id);
                    JsonReadWrite.registraEventoxVaccinato(lista);
                }
            }
        }catch(Exception x){
            JOptionPane.showMessageDialog(null,x.getMessage().toString());
        }
    }



    public void OnMouseClicked(MouseEvent mouseEvent)
    {
        TextNote.setText("");
    }

    public void OnKeyPressed(KeyEvent keyEvent)
    {
        String x=TextNote.getText();
        int c=x.length();
        LbCount.setText(c+"/255");
        if(c>255)
        LbCount.setTextFill(javafx.scene.paint.Paint.valueOf("red"));
    }
}
