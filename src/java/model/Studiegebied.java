/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author CURSIST
 */
public class Studiegebied {

    private int studiegebiedID;
    private String naam;
    private String beschrijving;

    public int getStudiegebiedID() {
        return studiegebiedID;
    }

    public void setStudiegebiedID(int studiegebiedID) {
        this.studiegebiedID = studiegebiedID;
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
    
}
