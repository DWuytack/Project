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
    
      public int moduleID() {
        return moduleID;
    }

    public void setmoduleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public int getopleidingID() {
        return opleidingID;
    }

    public void opleidingID(int opleidingID) {
        this.opleidingID = opleidingID;
    }

    public String naam() {
        return naam;
    }

    public void setnaam(String naam) {
        this.naam = naam;
    }
    

    
}
