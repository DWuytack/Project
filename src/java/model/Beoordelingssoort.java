package model;

/**
 * Beoordelingssoort: De naam, waarde, etc... van een beoordelingssoort.
 * @author Keanu
*/ 
public class Beoordelingssoort {
    //Objecten aanmaken
    private int beoordelingssoortID;
    private String naam;
    private String beschrijving;
    private double waarde;
    
    //Getters en Setters aanmaken
    
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

    public double getWaarde() {
        return waarde;
        
    }

    public void setWaarde(double waarde) {
        this.waarde = waarde;
    }
}
