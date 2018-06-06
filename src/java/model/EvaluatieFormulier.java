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
public class EvaluatieFormulier {
    

    private int evaluatieFormulierID;
    private int inschrijvingID;
    private int lesnrID;
    private String datum;

    public int getEvaluatieFormulierID() {
        return evaluatieFormulierID;
    }

    public void setEvaluatieFormulierID(int evaluatieFormulierID) {
        this.evaluatieFormulierID = evaluatieFormulierID;
    }

    public int getInschrijvingID() {
        return inschrijvingID;
    }

    public void setInschrijvingID(int inschrijvingID) {
        this.inschrijvingID = inschrijvingID;
    }

    public int getLesnrID() {
        return lesnrID;
    }

    public void setLesnrID(int lesnrID) {
        this.lesnrID = lesnrID;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
   
}
