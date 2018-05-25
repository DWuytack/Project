/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author CURSIST
 */
public class GebruikersOverzicht {
    int bladz;
    int aantalBladz;
    int toonGebruikers;
    int aantalGebruikers;
    ArrayList<Gebruiker> lijst;
    
    public GebruikersOverzicht(int bladz, int aantalBladz, int toonGebruikers, int aantalGebruikers, ArrayList<Gebruiker> lijst) {
        this.bladz = bladz;
        this.aantalBladz = aantalBladz;
        this.toonGebruikers = toonGebruikers;
        this.aantalGebruikers = aantalGebruikers;
        this.lijst = lijst;
    }
}
