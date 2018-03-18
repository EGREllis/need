package net.ellise.listeners;

import net.ellise.domain.database.JDBCConstants;
import org.apache.derby.jdbc.EmbeddedDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.*;

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
        EmbeddedDataSource dataSource = new EmbeddedDataSource();
        dataSource.setDatabaseName("need");
        dataSource.setCreateDatabase("create");
        System.out.println("Creating in memory database");
        Connection connection = dataSource.getConnection();
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
        createTable.close();

        Statement populateTable = connection.createStatement();
        isResultSet = populateTable.execute(JDBCConstants.POPULATE_USER_TABLE);
        if (isResultSet) {
            System.out.println("Insert into table returned a result set!!!");
            System.out.flush();
        } else {
            System.out.println(String.format("Populating users update count: %1$d", populateTable.getUpdateCount()));
            System.out.flush();
        }
        populateTable.close();

        Statement countTable = connection.createStatement();
        isResultSet = countTable.execute(JDBCConstants.COUNT_USER_TABLE);
        if (isResultSet) {
            System.out.println("Counting tables returned result set");
            ResultSet resultSet = countTable.getResultSet();
            while (resultSet.next()) {
                System.out.println(String.format("User table count is: %1$d",resultSet.getInt(1)));
                System.out.flush();
            }
        } else {
            System.out.println("Counting the table did not produce a result set!!!");
        }

        System.out.println("Closing connection...");
        connection.close();
        System.out.println("Connection closed...");
    }
}
