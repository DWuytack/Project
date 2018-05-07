package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Klasse voor het uitvoeren van veelvoorkomende handelingen om de leesbaarheid
 * van andere klasses te verbeteren.
 *
 * @author Ewout Phlips
 */
public class Utilities {

    private static void sluitConnection(Connection currentCon) {
        try {
            if (currentCon != null) {
                if (!currentCon.isClosed()) {
                    currentCon.close();
                }
            }
        } catch (SQLException ex) {
        }
    }

    private static void sluitStatement(Statement statement) {
        try {
            if (statement != null) {
                if (!statement.isClosed()) {
                    statement.close();
                }
            }
        } catch (SQLException ex) {
        }
    }

    private static void sluitPreparedStatement(PreparedStatement ps) {
        try {
            if (ps != null) {
                if (!ps.isClosed()) {
                    ps.close();
                }
            }
        } catch (SQLException ex) {
        }
    }

    private static void sluitResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                if (!rs.isClosed()) {
                    rs.close();
                }
            }
        } catch (SQLException ex) {
        }
    }

    public static void sluitVariabelen(Statement statement, Connection currentCon) {
        sluitStatement(statement);
        sluitConnection(currentCon);
    }

    public static void sluitVariabelen(PreparedStatement ps, Connection currentCon) {
        sluitPreparedStatement(ps);
        sluitConnection(currentCon);
    }

    public static void sluitVariabelen(Statement statement, ResultSet rs, Connection currentCon) {
        sluitStatement(statement);
        sluitResultSet(rs);
        sluitConnection(currentCon);
    }

    public static void sluitVariabelen(PreparedStatement ps, ResultSet rs, Connection currentCon) {
        sluitPreparedStatement(ps);
        sluitResultSet(rs);
        sluitConnection(currentCon);
    }
}
