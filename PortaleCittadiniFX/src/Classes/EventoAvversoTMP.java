package Classes;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EventoAvversoTMP extends RecursiveTreeObject<EventoAvversoTMP> {
    public Evento evento;
    public Severita severita;
    public short IDVaccinazione;
    public String nomeCognome;
    /** per l'inserimento delle note opzionali inserite dall'utente. di massimo 255 caratteri*/
    public String noteOpzionali;

    public StringProperty evento2;
    public StringProperty severita2;
    public StringProperty nomeCognome2;



    /** costruttore*/
    public EventoAvversoTMP(Evento _evento, Severita _severita, String _noteOpzionali, short IDV, String NC)
    {
        evento = _evento;
        severita = _severita;
        noteOpzionali = _noteOpzionali;
        IDVaccinazione = IDV;
        nomeCognome = NC;

        evento2 = new SimpleStringProperty(evento.toString());
        severita2 = new SimpleStringProperty(severita.name());
        nomeCognome2 = new SimpleStringProperty(nomeCognome);
    }



    @Override
    public String toString() {
        return "EventoAvversoTMP{" +
                "evento=" + evento +
                ", severita=" + severita +
                ", IDVaccinazione=" + IDVaccinazione +
                ", nomeCognome='" + nomeCognome + '\'' +
                ", noteOpzionali='" + noteOpzionali + '\'' +
                '}';
    }
}
