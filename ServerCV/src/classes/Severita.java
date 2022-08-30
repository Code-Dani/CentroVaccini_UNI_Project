package classes;

import java.io.Serializable;

/**
 * Classe contenente enum per la gestione della severità (quindi quanto forte il sintomo viene percepito dall'utente) di un evento avverso. <br/>
 * Viene diviso in: <br/>
 *<ul>
 *  <li>0-20: {@link #molto_bassa_1}</li>
 *  <li>20-40: {@link #bassa_2}</li>
 *  <li>40-60: {@link #fastidiosa_3}</li>
 *  <li>60-80: {@link #sopportabile_4}</li>
 *  <li>80-100: {@link #insopportabile_5}</li>
 *</ul>
 * @author Menegotto Caludio
 * @author Cavalli Francesco
 */
public enum Severita implements FromString, Serializable

{
    /**
     * Caso severità: molto bassa
     */
    molto_bassa_1{ public  String toString(){return "Molto Bassa (1)";} },

    /**
     * Caso severità: bassa
     */
    bassa_2{ public  String toString(){return "Bassa (2)";} },
    /**
     * Caso severità: fastidiosa
     */
    fastidiosa_3{ public  String toString(){return "Fastidiosa (3)";} },
    /**
     * Caso severità: sopportabile
     */
    sopportabile_4{ public  String toString(){return "Sopportabile (4)";} },
    /**
     * Caso severità: insopportabile
     */
    insopportabile_5{ public  String toString(){return "Insopportabile (5)";} };

    @Override
    public Severita fromString(String enumString) {
        switch (enumString){
            case "Molto Bassa (1)":
                return molto_bassa_1;

            case "Bassa (2)":
                return bassa_2;

            case "Fastidiosa (3)":
                return fastidiosa_3;

            case "Sopportabile (4)":
                return sopportabile_4;

            case "Insopportabile (5)":
                return insopportabile_5;

            default :
                return fastidiosa_3;
        }
    };

    static final long serialVersionUID = 8L;
}

