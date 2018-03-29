package model;

/**
 *
 * @author Aaron
 *
 * Taak is een praktische opdracht die tijdens de les wordt gegeven en vervult
 * daarmee (gedeeltelijk) een doelstelling.
 *
 */
public class Taak {

    private int taakID;
    private String naam;
    private String beschrijving;
    private boolean geldig;

    public boolean isGeldig() {
        return geldig;
    }

    public void setGeldig(boolean geldig) {
        this.geldig = geldig;
    }

    public int getTaakID() {
        return taakID;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setTaakID(int taakID) {
        this.taakID = taakID;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

}
