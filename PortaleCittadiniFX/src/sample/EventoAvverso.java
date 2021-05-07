package sample;

/** classe per la definizione di un evento avvesro*/
public class EventoAvverso
{
    /** definisce il tipo di EventoAvverso verificato post vaccino
     * @author Menegotto Caludio
     * */
    public enum Evento
    {
        mal_di_testa,
        febbre,
        dolori_muscolari_e_articolari,
        infoadonopatia,
        tachicardita,
        crisi_ipertensiva,
        altro
    }

    /** definisce la gravità con la quale si verifica EventoAvverso post vaccino*/
    public enum Severita
    {
        molto_bassa_1,
        bassa_2,
        fastidiosa_3,
        sopportabile_4,
        insopportabile_5
    }

    public Evento evento;

    public Severita severita;

    /** per l'inserimento delle note opzionali inserite dall'utente. di massimo 255 caratteri*/
    //da vedere se tenere stringa o array di char
    public String noteOpzionali;

    /** costruttore*/
    public EventoAvverso(Evento _evento, Severita _severita, String _noteOpzionali)
    {
        evento = _evento;
        severita = _severita;
        noteOpzionali = _noteOpzionali;
    }
}