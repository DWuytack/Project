package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasse Doelstelling DataBase Access Object: Alles handeling in verband met
 * doelstellingen in de Database.
 *
 * @author Ewout Phlips
 */
public class DoelstellingDAO {

    //Creër een nieuwe doelstelling.
    public void doelstellingAanmaken(Doelstelling doelstelling) {

        Connection currentCon = null;
        
        String sqlQuery //Query voor het aanmaken van een doelstelling.
                = "INSERT INTO Doelstellingen (naam, beschrijving, kerndoelstelling)"
                + " VALUES ('" + doelstelling.getNaam() + "', '"
                + doelstelling.getBeschrijving() + "', "
                + doelstelling.getKerndoelstelling() + ");";

        try { //Maak connectie met de DataBase en probeer de Query uit te voeren.
            currentCon = ConnectionManager.getConnection();
            Statement stmt = currentCon.createStatement();
            stmt.executeQuery(sqlQuery);

        } catch (SQLException error) { //Error boodschap.
            System.out.println("De volgende Query leverde een error op: " + sqlQuery + ".");
            
        } finally {
            try { //Sluit de connectie met de de DataBase.
                currentCon.close();
            } catch (SQLException error) {
                System.out.println("Error - Kon de connectie niet verbreken na het aanmaken van een doelstelling.");
            }
        }
    }

    //Pas een doelstelling aan.
    public void doelstellingAanpassen(Doelstelling doelstelling) {
        Connection currentCon = null;

        String sqlQuery //Query voor het aanpassen van een doelstelling.
                = "UPDATE Doelstellingen"
                + "SET naam='" + doelstelling.getNaam()
                + "', beschrijving='" + doelstelling.getBeschrijving()
                + "', kerndoelstelling='" + doelstelling.getKerndoelstelling()
                + "' WHERE doelstellingID='" + doelstelling.getDoelstellingID() + "';";

        try { //Maak connectie met de DataBase en probeer de Query uit te voeren.
            currentCon = ConnectionManager.getConnection();
            Statement stmt = currentCon.createStatement();
            stmt.execute(sqlQuery);

        } catch (SQLException error) { //Error boodschap.
            System.out.println("Error - De volgende Query werkte niet: " + sqlQuery + ".");
            
        } finally {
            try { //Sluit de connectie met de DataBase.
                currentCon.close();
            } catch (SQLException error) { 
                System.out.println("Error - Kon de connectie niet verbreken na het aanpassen van een doelstelling.");
            }
        }
    }

    //Verwijdert een doelstelling.
    public void doelstellingVerwijderen(Doelstelling doelstelling) {
        Connection currentCon = null;

        String sqlQuery //Query voor het verwijderen van een doelstelling.
                = "DELETE FROM Doelstellingen WHERE doelstellingID='"
                + doelstelling.getDoelstellingID() + "';";

        try { //Maak connectie met de DataBase en probeer de Query uit te voeren.
            currentCon = ConnectionManager.getConnection();
            Statement stmt = currentCon.createStatement();
            stmt.execute(sqlQuery);

        } catch (SQLException error) { //Error Boodschap
            System.out.println("Error - De volgende Query werkte niet: " + sqlQuery + ".");
            
        } finally {
            try { //Sluit de connectie met de DataBase.
                currentCon.close(); 
            } catch (SQLException error) {
                System.out.println("Error - Kon de connectie niet verbreken na het verwijderen van een doelstelling.");
            }
        }
    }
    
    //Laad alle Doelstellingen uit de Database.
    public ArrayList<Doelstelling> doelstellingenLaden() {
        Statement stmt;
        
        Connection currentCon;
        ResultSet rs;
        
        ArrayList<Doelstelling> doelstellingen = new ArrayList<>();
        
        String selectDoelstellingen = "SELECT * FROM doelstellingen;";
        
        try {
            currentCon = ConnectionManager.getConnection();
            stmt =  currentCon.createStatement();
            rs = stmt.executeQuery(selectDoelstellingen);
            
            while(rs.next()) {
                Doelstelling doelstelling = new Doelstelling();
                doelstelling.setDoelstellingID(rs.getInt("doelstellingID"));
                doelstelling.setNaam(rs.getString("naam"));
                doelstelling.setBeschrijving(rs.getString("beschrijving"));
                doelstelling.setKerndoelstelling(rs.getBoolean("kerndoelstelling"));
                
                doelstellingen.add(doelstelling);
            }
            
            currentCon.close();
            
            return doelstellingen;
            
        } catch(Exception e) {
            System.out.println("Error - Kon geen doelstellingen laden.");
            return null;
        }
    }
}
