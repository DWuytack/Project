package model;

/**
 *
 * @author Dirk
 */
public class Gebruiker {
   
    //beveiligingsupdate, schakel over naar jsonID
    private int gebruikerID;
    private String rol;
    private String voornaam;
    private String achternaam;
    private String naam;
    private String geboorteDatum;
    private String email;
    private String login;
    private String paswoord;
    private boolean geldig;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getGebruikerID() {
        return gebruikerID;
    }
    
     public void setGebruikerID(int gebruikerID) {
        this.gebruikerID=gebruikerID;
    }
    
    

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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

    public String getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(String geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public void setPaswoord(String paswoord) {
        this.paswoord = paswoord;
    }

    public boolean isGeldig() {
        return geldig;
    }

    public void setGeldig(boolean geldig) {
        this.geldig = geldig;
    }

  
}
