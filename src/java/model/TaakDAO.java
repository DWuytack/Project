package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Deze klasse is verantwoordelijk voor alle gebruikersbewerkingen in de
 * database.
 *
 * @author Gil
 */
public class TaakDAO {

    public ArrayList<Taak> takenLaden(int bladz) {

        ArrayList<Taak> taken = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM Taken";
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
                } catch (Exception e) {

                }

                currentCon = null;
            }

        }
        return taken;
    }

    public int geefAantalTaken() {

        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;
        int aantalTaken = 0;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "select COUNT(*) from Taken";
            statement = currentCon.createStatement();

            rs = statement.executeQuery(sql);
            while (rs.next()) {
                aantalTaken = rs.getInt(1);
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
                } catch (Exception e) {

                }

                currentCon = null;
            }

        }
        return aantalTaken;
    }

    public void taakAanmaken() {
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO Taken (naam,beschrijving) VALUES (?,?)";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setString(1, "naam");
            ps.setString(2, "beschrijving");
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

    public void taakAanpassen(int taakID,Taak taak) {
        Connection currentCon = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql = "UPDATE Taken(taakID, naam, beschrijving) VALUES(?,?,?)";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setString(1, "naam");
            ps.setString(2, "beschrijving");
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
    
     public void takenVerwijderen(int taakID) {

        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql
                = "DELETE from Taken where Taken.taakID = ?";
        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setInt(1, taakID);
            ps.executeQuery();

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        } finally {

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
