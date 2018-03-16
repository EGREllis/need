package net.ellise.listeners;

import net.ellise.domain.database.JDBCConstants;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LifecycleListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println(String.format("Web context started %1$s", servletContextEvent.getServletContext().getServletContextName()));
        System.out.flush();
        try {
            initialiseDatabse();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println(String.format("Web context stopped %1$s", servletContextEvent.getServletContext().getServletContextName()));
        System.out.flush();
    }

    private void initialiseDatabse() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Creating in memory database");
        Connection connection = DriverManager.getConnection(JDBCConstants.CREATE_CONNECTION);
        System.out.println("Created in memory database");

        Statement createTable = connection.createStatement();
        boolean isResultSet = createTable.execute(JDBCConstants.CREATE_USER_TABLE);
        if (isResultSet) {
            System.out.println("Create table returned a result set!!!");
            System.out.flush();
        } else {
            System.out.println(String.format("Create table update count: %1$d", createTable.getUpdateCount()));
            System.out.flush();
        }
        System.out.println("Closing connection...");
        connection.close();
        System.out.println("Connection closed...");
    }
}
