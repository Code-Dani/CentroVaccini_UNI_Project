package Classes;

public enum Vaccini
{
    Pfizer,
    AstraZeneca,
    Moderna,
    JeJ {
        public String toString(){
            return "J&J";
        }
    }
}

