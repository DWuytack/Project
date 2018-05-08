package model;

/**
 * Beoordelingssoort: De naam, waarde, etc... van een beoordelingssoort.
 * @author Keanu
*/ 
public class Beoordelingssoort {
    
    private int beoordelingssoortID;
    private String naam;
    private String beschrijving;
    private int waarde;
    
    public int getBeoordelingssoortID() {
        return beoordelingssoortID;
    }

    public void setBeoordelingssoortID(int beoordelingssoortID) {
        this.beoordelingssoortID = beoordelingssoortID;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public int getWaarde() {
        return waarde;
    }

    public void setWaarde(int waarde) {
        this.waarde = waarde;
    }
}
