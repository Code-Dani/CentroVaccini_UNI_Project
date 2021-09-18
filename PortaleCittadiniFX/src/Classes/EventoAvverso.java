package Classes;

/**
*classe per la definizione di un evento avvesro
*@author Menegotto Caludio
*/
public class EventoAvverso
{
    public Evento evento;
    public Severita severita;
    public short IDVaccinazione;

    /** per l'inserimento delle note opzionali inserite dall'utente. di massimo 255 caratteri*/
    public String noteOpzionali;

    /** costruttore*/
    public EventoAvverso(Evento _evento, Severita _severita, String _noteOpzionali, short IDV)
    {
        evento = _evento;
        severita = _severita;
        noteOpzionali = _noteOpzionali;
        IDVaccinazione = IDV;
    }
}