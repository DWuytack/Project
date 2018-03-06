/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Aaron
 * hier komen sql query's
 */
public class TaakDAO {
    private void TaakAanmaken(Gebruiker gebruiker){
        
        String sql = "INSERT INTO Taken(taakID, naam, beschrijving) VALUES(?,?,?)";
        // taak aanmaken door naam en beschrijving in te vullen
    }
    private void TaakAanpassen(Gebruiker gebruiker){
        
        String sql = "UPDATE Taken SET naam ='?' where taakID ='?'";
        // taak aanpassen door de taakID in te vullen en de juiste naam
    }
    private void TaakVerwijderen(Gebruiker gebruiker){
        
        String sql = "DELETE FROM Taken where taakID='?'";   
        // taak verwijderen door de taakID in te vullen
}
}