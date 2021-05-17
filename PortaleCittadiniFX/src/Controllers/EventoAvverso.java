package Controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;


public class EventoAvverso implements Initializable {

    //variabili usate:
    @FXML
    public JFXComboBox<String> ComboEventi;

    @FXML
    public TextArea TextNote;
    public JFXSlider SliderGravita;
    public Label LbCount;

    ObservableList<String> list= FXCollections.observableArrayList("AAAA","AAAAA","BBBBBB");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ComboEventi.setItems(list);
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
