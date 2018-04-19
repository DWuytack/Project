/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Davino
 */
public class ModuleDAO {
    
     private void ModuleAanmaken(Module module) {

        //connectie maken met database
        Connection connectie = null;

        //insertquery
        String sql = "INSERT INTO modules "
                + "(moduleID,opleidingID,naam) VALUES (?,?,?)";

        //opslaan in database
        try {
            PreparedStatement statement = connectie.prepareStatement(sql);
            statement.setInt(1, module.getmoduleID());
            statement.setInt(2, module.getopleidingID());
            statement.setString(3, module.getnaam());
            statement.execute();
            statement.close();
            connectie.close();

        } catch (SQLException e) {

        }
    }

    private void ModuleAanpassen(Module module) {

        //connectie maken met database
        Connection connectie = null;
        connectie = ConnectionManager.getConnection();

        //insertquery
        String sql = "UPDATE modules "
                + " set naam =  " + module.getnaam() + ", opleidingID= "
                + module.getopleidingID() + " where moduleID= "
                + module.getmoduleID();

        //opslaan in database
        try {
            Statement statement = connectie.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            connectie.close();

        } catch (SQLException e) {

        }

    }

    private void ModuleVerwijderen(Module module) {

        //connectie maken met database
        Connection connectie = null;

        //insertquery
        String sql = "DELETE FROM modules "
                + " where moduleID= " + module.getmoduleID();

        //opslaan in database
        try {
            PreparedStatement statement = connectie.prepareStatement(sql);
            statement.execute();
            statement.close();
            connectie.close();

        } catch (SQLException e) {

        }

    }
    
     public ArrayList<Module> modulesLaden( int bladz) {
         
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
                module.setmoduleID(rs.getInt("moduleID"));
                module.setopleidingID(rs.getInt("opleidingID"));
                module.setnaam(rs.getString("naam"));
              
                
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

    public int geefAantalModules() {
        
         Connection currentCon = null;
        Statement statement = null;
        ResultSet rs = null;
        int aantalModules = 0;

        try {
            currentCon = ConnectionManager.getConnection();
            String sql = "select * from modules";
            statement = currentCon.createStatement();

            rs = statement.executeQuery(sql);
            while (rs.next()) {
                aantalModules++;
                rs.getString(2);
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
        return aantalModules;
        
    }
    
     public void moduleAanmaken() {
        Connection currentCon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "INSERT INTO modules(naam) VALUES((?))";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setString(1, "naam");
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

    public void moduleAanpassen(int parseInt, Module module) {
        
         Connection currentCon = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql = "UPDATE modules(naam) VALUES(?)";

        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setString(1, "naam");
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

    public void moduleVerwijderen(int moduleID) {
        
          Connection currentCon = null;
        PreparedStatement ps = null;

        String sql
                = "DELETE from modules where module.moduleID = ?";
        try {
            currentCon = ConnectionManager.getConnection();
            ps = currentCon.prepareStatement(sql);

            ps.setInt(1,moduleID);
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
                module.setmoduleID(rs.getInt("moduleID"));
                module.setopleidingID(rs.getInt("opleidingID"));
                module.setnaam(rs.getString("naam"));
              
                
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

}
