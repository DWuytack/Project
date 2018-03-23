package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    //Laad alle Doelstellingen uit de Database.
    public ArrayList<Doelstelling> doelstellingenLaden() {

        ArrayList<Doelstelling> doelstellingen = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM doelstellingen";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Doelstelling doelstelling = new Doelstelling();
                doelstelling.setDoelstellingID(rs.getInt("doelstellingID"));
                doelstelling.setNaam(rs.getString("naam"));
                doelstelling.setBeschrijving(rs.getString("beschrijving"));
                doelstelling.setKerndoelstelling(rs.getBoolean("kerndoelstelling"));

                doelstellingen.add(doelstelling);
            }
        } catch (SQLException e) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
                rs = null;
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {

                }

                statement = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
            } catch (SQLException error) {
                System.out.println("Error - Kon de connectie niet verbreken na het aanmaken van een doelstelling.");
                }

                currentCon = null;
            }

        }
        return doelstellingen;
    }

    //Creër een nieuwe doelstelling.
    public void doelstellingAanmaken(Doelstelling doelstelling) {
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO doelstellingen (naam, beschrijving, kerndoelstelling)\n"
                + "VALUES (?, ?, ?)";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setString(1, doelstelling.getNaam());
            ps.setString(2, doelstelling.getBeschrijving());
            if (doelstelling.getKerndoelstelling()) {
                ps.setInt(3, 1);
            } else {
                ps.setInt(3, 0);
            }
            ps.executeQuery(sql);

        } catch (SQLException ex) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {

                }
                rs = null;
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {

                }
                ps = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (SQLException e) {

                }

                currentCon = null;
            }
        }

    }

    //Pas een doelstelling aan.
    public void doelstellingAanpassen(Doelstelling doelstelling) {
        Connection currentCon = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql = "UPDATE doelstellingen SET naam = ?, beschrijving = ?, kerndoelstelling = ? WHERE doelstellingID = ?";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setString(1, doelstelling.getNaam());
            ps.setString(2, doelstelling.getBeschrijving());
            if (doelstelling.getKerndoelstelling()) {
                ps.setInt(3, 1);
            } else {
                ps.setInt(3, 0);
            }
            ps.setInt(4, doelstelling.getDoelstellingID());
            ps.executeQuery(sql);

        } catch (SQLException ex) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
                rs = null;
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
                ps = null;
            }
            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (SQLException e) {
                }
                currentCon = null;
            }
        }

    }

    //Verwijdert een doelstelling.
    public void doelstellingVerwijderen(Doelstelling doelstelling) {
        Connection currentCon = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql
                = "DELETE FROM doelstellingen WHERE doelstellingID = ?";
        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setInt(1, doelstelling.getDoelstellingID());
            ps.executeQuery(sql);

        } catch (SQLException ex) {

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {

                }
                rs = null;
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
                ps = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (SQLException e) {

                }

                currentCon = null;
            }
        }

    }

}
