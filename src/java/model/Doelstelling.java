package model;

/**
 * Klasse Doelstelling: Een doelstelling is een verzameling taken die
 * bijeenhoort.
 *
 * @author Ewout Phlips 
 */
public class Doelstelling {

    private int doelstellingID; // ID van de doelstelling in de DataBase.
    private String naam; // Naam van de doelstelling.
    private String beschrijving; // Beschrijving van de doelstellig.
    private boolean kerndoelstelling; // Kerndoelstelling. Ja of Nee.

    public int getDoelstellingID() {
        return doelstellingID;
    }

    public void setDoelstellingID(int doelstellingID) {
        this.doelstellingID = doelstellingID;
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

    public boolean getKerndoelstelling() {
        return kerndoelstelling;
    }

    public void setKerndoelstelling(boolean kerndoelstelling) {
        this.kerndoelstelling = kerndoelstelling;
    }

}
