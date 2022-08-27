package classes;

import java.io.Serializable;
import java.nio.file.Paths;

/**
 * Enum contenente i path per raggiungere i file di salvataggio dati<br/>
 * Esistono 3 diversi file in cui vengono salvati dati diversi: <strong>CentriVaccinali.dati, Cittadini_Registrati.dati, Vaccinati_NomeCentroVaccinale.dati</strong>
 *
 * @since 8/10/2021
 * @author Daniel Satriano
 */
public enum FilePaths implements Serializable {

    /**
     * ritorna il path del database chiamato CentriVaccinali.dati
     */
    CentriVaccinali{
        public String toString(){
            return System.getProperty("user.dir") + Paths.get("/../Database/CentriVaccinali.dati");
        }
    },
    /**
     * ritorna il path del database chiamato Cittadini_Registrati.dati
     */
    CittadiniRegistrati{
        public String toString(){
            return System.getProperty("user.dir") + Paths.get("/../Database/Cittadini_Registrati.dati");
        }
    },
    /**
     * ritorna il path del database chiamato Vaccinati_NomeCentroVaccinale.dati
     */
    VaccinatiNomeCentro{
        public String toString(){
            return System.getProperty("user.dir") + Paths.get("/../Database/Vaccinati_NomeCentroVaccinale.dati");
        }
    };

    static final long serialVersionUID = 4L;
}
