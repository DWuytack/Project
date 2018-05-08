package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * ModuleDAO(Module Data Access Object) is een klasse voor alle handelingen in
 * de database betreffend Modules.
 *
 * @author Ewout Phlips
 */
public class ModuleDAO {

    public void moduleToevoegen(Module module) {
        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO modules (modules.naam) VALUES (?)";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, module.getNaam());

            ps.executeQuery();
        } catch (SQLException ex) {
        } finally {
            Utilities.sluitVariabelen(ps, currentCon);
        }
    }

    public void modulesToevoegen(ArrayList<Module> modules) {
        modules.forEach((module) -> {
            moduleToevoegen(module);
        });
    }

    public void moduleAanpassen(Module module) {
        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "UPDATE modules SET naam = ? WHERE moduleID = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, module.getNaam());
            ps.setInt(2, module.getModuleID());

            ps.executeQuery();
        } catch (SQLException ex) {
        } finally {
            Utilities.sluitVariabelen(ps, currentCon);
        }
    }

    public void modulesAanpassen(ArrayList<Module> modules) {
        modules.forEach((module) -> {
            moduleAanpassen(module);
        });
    }

    public void moduleVerwijderen(Module module) {
        Connection currentCon = null;
        PreparedStatement ps = null;

        String sql = "DELETE FROM modules WHERE modules.moduleID = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, module.getModuleID());

            ps.executeQuery();
        } catch (SQLException ex) {
        } finally {
            Utilities.sluitVariabelen(ps, currentCon);
        }
    }

    public void modulesVerwijderen(ArrayList<Module> modules) {
        modules.forEach((module) -> {
            moduleVerwijderen(module);
        });
    }

    public Module moduleLaden(String moduleNaam) {
        Module module = new Module();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT modules.* FROM modules WHERE modules.naam = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setString(1, moduleNaam);

            rs = ps.executeQuery();
            while (rs.next()) {
                module.setModuleID(rs.getInt("moduleID"));
                module.setNaam(rs.getString("naam"));
            }

        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }

        return module;
    }

    public Module moduleLaden(int moduleID) {
        Module module = new Module();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT modules.* FROM modules WHERE modules.moduleID = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, moduleID);

            rs = ps.executeQuery();
            while (rs.next()) {
                module.setModuleID(rs.getInt("moduleID"));
                module.setNaam(rs.getString("naam"));
            }
        } catch (SQLException e) {
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }

        return module;
    }

    public ArrayList<Module> modulesLaden() {
        ArrayList<Module> modules = new ArrayList<>();
        Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;

        String sql = "SELECT modules.* FROM modules";

        try {
            currentCon = ConnectionManager.getConnection();
            statement = currentCon.createStatement();

            rs = statement.executeQuery(sql);

            while (rs.next()) {
                Module module = new Module();
                module.setModuleID(rs.getInt("moduleID"));
                module.setNaam(rs.getString("naam"));
                modules.add(module);
            }

        } catch (Exception e) {
        } finally {
            Utilities.sluitVariabelen(statement, rs, currentCon);
        }

        return modules;
    }

    public ArrayList<Module> modulesLaden(int opleidingID) {
        ArrayList<Module> modules = new ArrayList<>();
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT modules.* FROM modules "
                + "INNER JOIN opleidingen_modules ON modules.moduleID = opleidingen_modules.moduleID "
                + "WHERE opleidingen_modules.opleidingID = ?";

        try {
            currentCon = ConnectionManager.getConnection();

            ps = currentCon.prepareStatement(sql);
            ps.setInt(1, opleidingID);

            rs = ps.executeQuery();

            while (rs.next()) {
                Module module = new Module();
                module.setModuleID(rs.getInt("moduleID"));
                module.setNaam(rs.getString("naam"));
                modules.add(module);
            }
        } catch (Exception e) {
        } finally {
            Utilities.sluitVariabelen(ps, rs, currentCon);
        }
        return modules;
    }

    public String geefModuleNaam(int moduleID) {
        return moduleLaden(moduleID).getNaam();
    }

    public int geefModuleID(String moduleNaam) {
        return moduleLaden(moduleNaam).getModuleID();
    }

    public int geefAantalModules() {
        return modulesLaden().size();
    }

    public int geefAantalModules(int opleidingID) {
        return modulesLaden(opleidingID).size();
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
                } catch (Exception e) {

                }

                currentCon = null;
            }

        
           
        }

        return moduleID;
    }
}
