package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * TaakDAO(Taak Data Access Object) is een klasse voor alle
 * handelingen in de database betreffend Taken.
 *
 * @author Ewout Phlips
 */
public class TaakDAO {

    public void taakToevoegen(Taak taak) {
        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO taken (taken.naam, taken.beschrijving) VALUES (?, ?)";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, taak.getNaam());
            ps.setString(2, taak.getBeschrijving());

            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(ps, currentCon);
        }
    }

    public void takenToevoegen(ArrayList<Taak> taken) {
        taken.forEach((taak) -> this.taakToevoegen(taak));
    }

    public void taakAanpassen(Taak taak) {
        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "UPDATE taken SET naam = ?, beschrijving = ? WHERE taakID = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, taak.getNaam());
            ps.setString(2, taak.getBeschrijving());
            ps.setInt(3, taak.getTaakID());

            ps.executeUpdate();
        } catch (SQLException ex) {
        } finally {
            Utilities.sluitVariabelen(ps, currentCon);
        }
    }

    public void takenAanpassen(ArrayList<Taak> taken) {
        taken.forEach((taak) -> this.taakAanpassen(taak));
    }

    public void taakVerwijderen(Taak taak) {
        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "DELETE FROM taken WHERE taken.taakID = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, taak.getTaakID());

            ps.executeUpdate();
        } catch (SQLException ex) {
        } finally {
            Utilities.sluitVariabelen(ps, currentCon);
        }
    }

    public void takenVerwijderen(ArrayList<Taak> taken) {
        taken.forEach((taak) -> this.taakVerwijderen(taak));
    }

    public Taak taakLaden(String taakNaam) {
        Taak taak = new Taak();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT taken.* FROM taken WHERE taken.naam = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, taakNaam);

            rs = ps.executeQuery();
            while (rs.next()) {
                taak.setTaakID(rs.getInt("taakID"));
                taak.setNaam(rs.getString("naam"));
                taak.setBeschrijving(rs.getString("beschrijving"));
            }
        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }
        return taak;
    }

    public Taak taakLaden(int taakID) {
        Taak taak = new Taak();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT taken.* FROM taken WHERE taken.taakID = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, taakID);

            rs = ps.executeQuery();
            while (rs.next()) {
                taak.setTaakID(rs.getInt("taakID"));
                taak.setNaam(rs.getString("naam"));
                taak.setBeschrijving(rs.getString("beschrijving"));
            }
        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }
        return taak;
    }

    public ArrayList<Taak> takenLaden() {
        ArrayList<Taak> taken = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        String sql = "SELECT taken.* FROM taken";

        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();

            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Taak taak = new Taak();
                taak.setTaakID(rs.getInt("taakID"));
                taak.setNaam(rs.getString("naam"));
                taak.setBeschrijving(rs.getString("beschrijving"));
                taken.add(taak);
            }
        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(statement, rs, currentCon);
        }
        return taken;
    }

    public ArrayList<Taak> takenLaden(int moduleID) {
        ArrayList<Taak> taken = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = " select * from taken where taakID IN (select taakid from doelstellingen_taken " +
                    "where doelstellingen_taken.doelstellingID IN " +
                    "(select doelstellingID from modules_doelstellingen where modules_doelstellingen.moduleID=?));";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, moduleID);

            rs = ps.executeQuery();

            while (rs.next()) {
                Taak taak = new Taak();
                taak.setTaakID(rs.getInt("taakid"));
                taak.setNaam(rs.getString("naam"));
                taak.setBeschrijving(rs.getString("beschrijving"));
                taken.add(taak);
            }
        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }
        return taken;
    }

    public String geefTaakNaam(int taakID) {
        return taakLaden(taakID).getNaam();
    }

    public int geefTaakID(String taakNaam) {
        return taakLaden(taakNaam).getTaakID();
    }

    public int geefAantalTaken() {
        return takenLaden().size();
    }

    public int geefAantalTaken(int doelstellingID) {
        return takenLaden(doelstellingID).size();
    }

}
