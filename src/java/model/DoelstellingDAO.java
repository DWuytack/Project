package model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Klasse Doelstelling DataBase Access Object:
 *
 *
 * @author Ewout Phlips
 */
public class DoelstellingDAO {

    public void DoelstellingAanmaken(Doelstelling doelstelling) {
        Connection currentCon = null; 
        
        String sqlQuery
                = "INSERT INTO Doelstellingen (naam, beschrijving, kerndoelstelling)"
                + " VALUES ('" + doelstelling.getNaam() + "', '"
                + doelstelling.getBeschrijving() + "', "
                + doelstelling.getKerndoelstelling() + ");";

        try {
            currentCon = ConnectionManager.getConnection();
            Statement stmt = currentCon.createStatement();
            stmt.executeQuery(sqlQuery);

        } catch (SQLException error) {
            System.out.println("De volgende Query leverde een error op: " + sqlQuery + ".");
        } finally {
            try {
                currentCon.close();
            } catch (SQLException error) {
                System.out.println("Error - Kon de connectie niet verbreken na het aanmaken van een doelstelling.");
            }
        }
    }

    public void DoelstellingAanpassen(Doelstelling doelstelling) {
        Connection currentCon = null;

        String sqlQuery
                = "UPDATE Doelstellingen"
                + "SET naam='" + doelstelling.getNaam()
                + "', beschrijving='" + doelstelling.getBeschrijving()
                + "', kerndoelstelling='" + doelstelling.getKerndoelstelling()
                + "' WHERE doelstellingID='" + doelstelling.getDoelstellingID() + "';";

        try {
            currentCon = ConnectionManager.getConnection();
            Statement stmt = currentCon.createStatement();
            stmt.execute(sqlQuery);

        } catch (SQLException error) {
            System.out.println("Error - De volgende Query werkte niet: " + sqlQuery + ".");
        } finally {
            try {
                currentCon.close();
            } catch (SQLException error) {
                System.out.println("Error - Kon de connectie niet verbreken na het aanpassen van een doelstelling.");
            }
        }
    }

    public void DoelstellingVerwijderen(Doelstelling doelstelling) {
        Connection currentCon = null;

        String sqlQuery
                = "DELETE FROM Doelstellingen WHERE doelstellingID='" + doelstelling.getDoelstellingID() + "';";

        try {
            currentCon = ConnectionManager.getConnection();
            Statement stmt = currentCon.createStatement();
            stmt.execute(sqlQuery);

        } catch (SQLException error) {
            System.out.println("Error - De volgende Query werkte niet: " + sqlQuery + ".");
        } finally {
            try {
                currentCon.close();
            } catch (SQLException error) {
                System.out.println("Error - Kon de connectie niet verbreken na het verwijderen van een doelstelling.");
            }
        }
    }

}
