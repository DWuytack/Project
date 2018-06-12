/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author dw018
 */
public class ScoreOverzicht {
    
    private int doelstellingID=0;
    private String doelstellingnaam="";
    private boolean kerndoelstelling=false;
    private int taakID=0;
    private String taaknaam="";

    public int getDoelstellingID() {
        return doelstellingID;
    }

    public void setDoelstellingID(int doelstellingID) {
        this.doelstellingID = doelstellingID;
    }

    public String getDoelstellingnaam() {
        return doelstellingnaam;
    }

    public void setDoelstellingnaam(String doelstellingnaam) {
        this.doelstellingnaam = doelstellingnaam;
    }

    public boolean isKerndoelstelling() {
        return kerndoelstelling;
    }

    public void setKerndoelstelling(boolean kerndoelstelling) {
        this.kerndoelstelling = kerndoelstelling;
    }

    public int getTaakID() {
        return taakID;
    }

    public void setTaakID(int taakID) {
        this.taakID = taakID;
    }

    public String getTaaknaam() {
        return taaknaam;
    }

    public void setTaaknaam(String taaknaam) {
        this.taaknaam = taaknaam;
    }
    
    
}
