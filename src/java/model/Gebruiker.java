package model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Dirk
 */
public class Gebruiker {
   
    //beveiligingsupdate, schakel over naar jsonID
    //private transient int gebruikerID;
    private int gebruikerID;
    private int jsonID;
    private String rol;
    private String voornaam;
    private String achternaam;
    private String naam;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
    private String geboorteDatum;
    private String email;
    private String login;
    private String paswoord;
    private boolean geldig;

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
