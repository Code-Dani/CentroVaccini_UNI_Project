package classes;

import java.nio.file.Paths;

/**
 * Enum contenente i path per raggiungere i file di salvataggio dati<br/>
 * Esistono 3 diversi file in cui vengono salvate cose diverse: <strong>CentriVaccinali.dati, Cittadini_Registrati.dati, Vaccinati_NomeCentro.dati</strong>
 * @author Daniel Satriano
 */
public enum FilePaths {

    CentriVaccinali{
        public String toString(){
            return System.getProperty("user.dir") + Paths.get("/../Database/CentriVaccinali.dati");
        }
    },
    CittadiniRegistrati{
        public String toString(){
            return System.getProperty("user.dir") + Paths.get("/../Database/Cittadini_Registrati.dati");
        }
    },
    VaccinatiNomeCentro{
        public String toString(){
            return System.getProperty("user.dir") + Paths.get("/../Database/Vaccinati_NomeCentro.dati");
        }
    }
}
