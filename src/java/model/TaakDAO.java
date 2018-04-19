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
            String sql = "SELECT * FROM taken";
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
            String sql = "select COUNT(*) from taken";
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

    public void taakAanmaken(Taak taak) {
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO taken (naam,beschrijving) VALUES (?,?)";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setString(1, taak.getNaam());
            ps.setString(2, taak.getBeschrijving());
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

    public void taakAanpassen(int taakID, Taak taak) {
        Connection currentCon = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql = "UPDATE taken(taakID, naam, beschrijving) VALUES(?,?,?)";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setString(1, taak.getNaam());
            ps.setString(2, taak.getBeschrijving());
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

    public ArrayList<Taak> takenZoeken(String zoekterm) {

        ArrayList<Taak> taken = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM taken  WHERE taken.naam LIKE ? OR taken.beschrijving LIKE ?";

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, "%" + zoekterm + "%");
            ps.setString(2, "%" + zoekterm + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Taak taak = new Taak();
                taak.setTaakID(rs.getInt("taakID"));
                taak.setBeschrijving(rs.getString("beschrijving"));
                taak.setNaam(rs.getString("naam"));
                taken.add(taak);
            }
        } catch (Exception e) {
        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
        return taken;
    }

    private void sluitVariabelen(ResultSet rs, Statement statement, PreparedStatement ps, Connection currentCon) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
            }
            try {
                rs = null;
            } catch (Exception e) {
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                statement = null;
            } catch (Exception e) {
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
            }
            try {
                ps = null;
            } catch (Exception e) {
            }
        }
        if (currentCon != null) {
            try {
                currentCon.close();
            } catch (Exception e) {
            }
            try {
                currentCon = null;
            } catch (Exception e) {
            }
        }
    }

    public void takenVerwijderen(int taakID) {

        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql
                = "DELETE from taken where taken.taakID = ?";
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
