package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * ModuleDAO(Module Data Access Object) is een klasse voor alle
 * handelingen in de database betreffend Modules.
 *
 * @author Ewout Phlips
 */
public class ModuleDAO {

    public void moduleToevoegen(Module module) {

        //connectie maken met database
        Connection connectie = null;

        //insertquery
        String sql = "INSERT INTO modules "
                + "(moduleID,naam) VALUES (?,?)";

        //opslaan in database
        try {
            PreparedStatement statement = connectie.prepareStatement(sql);
            statement.setInt(1, module.getModuleID());
            statement.setString(2, module.getNaam());
            statement.execute();
            statement.close();
            connectie.close();

        } catch (SQLException e) {

        }
    }

    public void moduleAanpassen(Module module) {

        //connectie maken met database
        Connection connectie = null;
        connectie = ConnectionManager.getConnection();

        //insertquery
        String sql = "UPDATE modules "
                + " set naam =  " + module.getNaam() + " where moduleID= "
                + module.getModuleID();

        //opslaan in database
        try {
            Statement statement = connectie.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            connectie.close();

        } catch (SQLException e) {

        }

    }

    public void moduleVerwijderen(Module module) {

        Connection currentCon = null;
        Statement statement = null;

        String sql = "DELETE FROM modules "
                + " WHERE moduleID= " + module.getModuleID();

        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {

        } finally {
            sluitVariabelen(null, statement, null, currentCon);
        }
    }

    public Module moduleLaden(int moduleID) {

        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM modules WHERE modules.moduleID = ?;";
        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, moduleID);
            rs = ps.executeQuery();

            Module module = new Module();
            module.setModuleID(rs.getInt("opleidingID"));
            module.setNaam(rs.getString("naam"));

            return module;
        } catch (Exception e) {

        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
        return null;
    }

    public ArrayList<Module> modulesLaden() {
        ArrayList<Module> modules = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT * FROM modules;";
            statement = currentCon.createStatement();
            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Module module = new Module();
                module.setModuleID(rs.getInt("moduleID"));
                module.setNaam(rs.getString("naam"));

                modules.add(module);
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
        return modules;
    }

    public ArrayList<Module> modulesLaden(int opleidingID) {

        ArrayList<Module> modules = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "SELECT modules.naam, modules.moduleID FROM modules"
                    + " inner join opleidingen_modules on opleidingen_modules.moduleID = modules.moduleID "
                    + " inner join opleidingen on opleidingen_modules.opleidingID = opleidingen.opleidingID "
                    + " where opleidingen_modules.opleidingID = ? ";

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, opleidingID);
            rs = ps.executeQuery();

            while (rs.next()) {
                Module module = new Module();
                module.setNaam(rs.getString("naam"));
                module.setModuleID(rs.getInt("moduleID"));
                modules.add(module);
            }
        } catch (SQLException e) {

        } finally {
            sluitVariabelen(rs, null, ps, currentCon);
        }
        return modules;
    }

    public ArrayList<Module> modulesLaden(String opleidingNaam) {
        OpleidingDAO opleidingDAO = new OpleidingDAO();

        return modulesLaden(opleidingDAO.geefOpleidingID(opleidingNaam));
    }

    public int geefModuleID(String moduleNaam) {

        int moduleID = 0;
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select moduleID from modules where "
                    + "modules.naam= ?";
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);
            ps.setString(1, moduleNaam);
            rs = ps.executeQuery();

            while (rs.next()) {
                moduleID = rs.getInt("moduleID");
            }

        } catch (Exception e) {

        } finally {
            sluitVariabelen(rs, ps, null, currentCon);
        }

        return moduleID;
    }

    /* Sluit enkele variabelen en zet ze op null */
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

    public int laadModuleID(String module) {
        int moduleID = 0;
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select moduleID from modules where "
                    + "modules.naam= ?";
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);
            ps.setString(1, module);
            rs = ps.executeQuery();

            while (rs.next()) {
               moduleID = rs.getInt("moduleID");
            }

        } catch (Exception e) {

        } finally {
            sluitVariabelen(rs, ps, null, currentCon);
        }

        return moduleID;
    }
}
