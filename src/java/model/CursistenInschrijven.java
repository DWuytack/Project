package model;

/**
 *
 * @author Aaron
 */
public class CursistenInschrijven {

    //beveiligingsupdate, schakel over naar jsonID
    private transient int gebruikerID;
    private int jsonID;
    private String voornaam;
    private String achternaam;
    private String naam;
    private String email;
    private boolean geldig;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getJsonID() {
        this.jsonID = gebruikerID * 4;
        return jsonID;
    }

    public int getGebruikerID() {
        return gebruikerID;
    }

    public void setGebruikerID(int gebruikerID) {
        this.jsonID = gebruikerID * 4;
        this.gebruikerID = gebruikerID;
    }

    public String getVoorNaam() {
        return voornaam;
    }

    public void setVoorNaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGeldig() {
        return geldig;
    }

    public void setGeldig(boolean geldig) {
        this.geldig = geldig;
    }

}
