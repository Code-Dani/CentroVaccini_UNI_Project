package Controllers;

import Classes.Evento;
import Classes.EventoAvversoTMP;
import Classes.Severita;
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


    ObservableList<String> list= FXCollections.observableArrayList("AAAA","AAAAA","BBBBBB"); ///metterci enum evento

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
        Evento e;
        //prendo in carico i valori dei tre parametri
        String evento=ComboEventi.getValue();
        double grav= SliderGravita.getValue();
        String note= TextNote.getText();
        //fine
        //creo nuovo elemento e lo aggiungo alla list view
        if(evento==Evento.mal_di_testa.toString())
            e=Evento.mal_di_testa;
        ///dividere scaglioni
      ///  EventoAvversoTMP tmp= new EventoAvversoTMP();
       CentroVaccinaleRG.eventiAvv.add()
        //devo chiedere a cava dove devono essere salvati questi dati oltre che nella list view
        //vaccinati_nomeCentro
        ///usare login box per id e salvare NON IN EVENTO AVVEERSO:TXT ....


        JOptionPane.showMessageDialog(null, evento+" "+grav+" "+note);
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
