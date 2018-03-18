package net.ellise.domain.database;

import java.sql.Connection;

public class JDBCUtils {
    public static final void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                e.printStackTrace(System.err);
                System.err.flush();
            }
        }
    }
}
