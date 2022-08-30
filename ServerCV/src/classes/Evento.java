package classes;

import java.io.Serializable;

/**
 * Classe per la gestione dell'enum usato per controllare quale evento avverso si è verificato post-vaccino.
 * @author Menegotto Caludio
 * @author Cavalli Francesco
 * @since 26/04/2021
 */
public enum Evento implements FromString, Serializable
{
    /**
     * Mal di testa
     */
    mal_di_testa{ public  String toString(){return "Mal di testa";} },
    /**
     * Febbre
     */
    febbre{ public  String toString(){return "Febbre";} },
    /**
     * Dolori muscolari e aritcolari
     */
    dolori_muscolari_e_articolari { public  String toString(){return "Dolori muscolari e aritcolari";} },
    /**
     * Infoadonopatia
     */
    infoadonopatia{ public  String toString(){return "Infoadonopatia";} },
    /**
     * Tachicardia
     */
    tachicardia{ public  String toString(){return "Tachicardia";} },
    /**
     * Crisi iper-tensiva
     */
    crisi_ipertensiva{ public  String toString(){return "Crisi iper-tensiva";} },
    /**
     * ...altro...
     */
    altro{ public  String toString(){return "... altro ...";} };


    @Override
    public Evento fromString(String enumString) {
        switch (enumString){
            case "Mal di testa":
                return mal_di_testa;

            case "Febbre":
                return febbre;

            case "Dolori muscolari e aritcolari":
                return dolori_muscolari_e_articolari;

            case "Infoadonopatia":
                return infoadonopatia;

            case "Tachicardia":
                return tachicardia;

            case "Crisi iper-tensiva":
                return crisi_ipertensiva;

            case "... altro ...":
                return altro;

            default :
                return altro;
        }
    };

    static final long serialVersionUID = 1L;
}

