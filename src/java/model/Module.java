/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Davino
 */
public class Module {
    private int moduleID;
    private int opleidingID;
    private String naam;

    public int getmoduleID() {
        return moduleID;
    }

    public void setmoduleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public int getopleidingID() {
        return opleidingID;
    }

    public void setopleidingID(int opleidingID) {
        this.opleidingID = opleidingID;
    }

    public String getnaam() {
        return naam;
    }

    public void setnaam(String naam) {
        this.naam = naam;
    }
      
}