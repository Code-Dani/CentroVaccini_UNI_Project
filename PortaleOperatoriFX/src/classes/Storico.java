package classes;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDateTime;

/**
 * Classe che permette il salvataggio degli storici
 * @author Daniel Satriano
 * @since 05/05/2021
 */
public class Storico extends RecursiveTreeObject<Storico> {
    public StringProperty azione;
    public StringProperty dataSomministrazione;
    public StringProperty oraSomministrazione;

    /**
     * Costruttore della classe classes.Storico
     * @param azione <b>azione compiuta ad una data ora, ES: effettuato un vaccino</b>
     * @param dataSomministrazione <b>data della somministrazione del vaccino</b>
     * @since 05/05/2021
     * @author Daniel Satriano
     */
    public Storico(String azione, LocalDateTime dataSomministrazione){
        this.azione = new SimpleStringProperty(azione);
        this.dataSomministrazione = new SimpleStringProperty(dataSomministrazione.getDayOfMonth() + "/" +
                dataSomministrazione.getMonth() + "/" + dataSomministrazione.getYear());
        this.oraSomministrazione = new SimpleStringProperty(dataSomministrazione.getHour() + ":" +
                dataSomministrazione.getMinute());
    }

    public Storico(){

    }

    /**
     * override del toString()
     * @return informazioni contenute nell'oggetto con una formattazione semplice da leggere
     */
    @Override
    public String toString() {
        return "classes.Storico{" +
                "azione='" + azione + '\'' +
                ", dataSomministrazione=" + dataSomministrazione +
                ", oraSomministrazione=" + oraSomministrazione +
                '}';
    }
}
