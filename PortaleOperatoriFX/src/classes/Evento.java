package classes;

/** Classe per la gestione dell'enum usato per controllare quale evento avverso si è verificato post-vaccino.
 * @author Menegotto Caludio
 * @author Cavalli Francesco
 * @since 26/04/2021
 */
public enum Evento
{
    mal_di_testa{ public  String toString(){return "Mal di testa";} },
    febbre{ public  String toString(){return "Febbre";} },
    dolori_muscolari_e_articolari { public  String toString(){return "Dolori muscolari e aritcolari";} },
    infoadonopatia{ public  String toString(){return "Infoadonopatia";} },
    tachicardia{ public  String toString(){return "Tachicardia";} },
    crisi_ipertensiva{ public  String toString(){return "Crisi iper-tensiva";} },
    altro{ public  String toString(){return "... altro ...";} }
}

