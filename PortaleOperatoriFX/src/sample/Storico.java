package sample;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDateTime;

/**
 * Classe che permette il salvataggio degli storici
 * @author Satriano Daniel
 * @since 05/05/2021
 */
public class Storico extends RecursiveTreeObject<Storico> {
    StringProperty azione;
    StringProperty dataSomministrazione;
    StringProperty oraSomministrazione;

    /**
     * Costruttore della classe Storico
     * @param azione azione compiuta ad una data ora, ES: effettuato un vaccino
     * @param dataSomministrazione data della somministrazione del vaccino
     * @since 05/05/2021
     */
    public Storico(String azione, LocalDateTime dataSomministrazione){
        this.azione = new SimpleStringProperty(azione);
        this.dataSomministrazione = new SimpleStringProperty(dataSomministrazione.getDayOfYear() + "/" +
                dataSomministrazione.getMonth() + "/" + dataSomministrazione.getYear());
        this.oraSomministrazione = new SimpleStringProperty(dataSomministrazione.getHour() + ":" +
                dataSomministrazione.getMinute());
    }

    /**
     * override del toString()
     * @return informazioni contenute nell'oggetto con una formattazione semplice da leggere
     */
    @Override
    public String toString() {
        return "Storico{" +
                "azione='" + azione + '\'' +
                ", dataSomministrazione=" + dataSomministrazione +
                ", oraSomministrazione=" + oraSomministrazione +
                '}';
    }
}
