package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Keanu      
hier komt de score  
*/ 
public class Score {
    
  private int beoordelingssoortID;
   private String naam;
   private String beschrijving;
   private int waarde;
    
    private int getBeoordelingssoortID() {
        return beoordelingssoortID;
    }

    private void setBeoordelingssoortID(int beoordelingssoortID) {
        this.beoordelingssoortID = beoordelingssoortID;
    }

    private String getNaam() {
        return naam;
    }

    void setNaam(String naam) {
        this.naam = naam;
    }

    private String getBeschrijving() {
        return beschrijving;
    }

    void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    private int getWaarde() {
        return waarde;
    }

    void setWaarde(int waarde) {
        this.waarde = waarde;
    }
}
