package net.ellise.domain.database;

import net.ellise.domain.Login;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCLogin implements Login {
    private final DataSource dataSource;

    public JDBCLogin(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean isValidLogin(String username, String password) throws IOException {
        boolean isValidLogin = false;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(JDBCConstants.CHECK_VALID_LOGIN);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            boolean hasResultSet = preparedStatement.execute();
            if (hasResultSet) {
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    if (resultSet.getInt(1) == 1) {
                        isValidLogin = true;
                    }
                }
                resultSet.close();
            }
            preparedStatement.close();
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return isValidLogin;
    }

    @Override
    public boolean register(String username, String password) throws IOException {
        boolean alreadyExists = false;
        boolean isRegistered = false;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement checkIfUserExists = connection.prepareStatement(JDBCConstants.CHECK_USER_EXISTS);
            checkIfUserExists.setString(1, username);
            boolean hasResultSet = checkIfUserExists.execute();
            if (hasResultSet) {
                ResultSet resultSet = checkIfUserExists.getResultSet();
                while (resultSet.next()) {
                    if (resultSet.getInt(1) > 0) {
                        alreadyExists = true;
                    }
                }
            }
            checkIfUserExists.close();

            if (!alreadyExists) {
                PreparedStatement insertUser = connection.prepareStatement(JDBCConstants.INSERT_VALID_LOGIN);
                insertUser.setString(1, username);
                insertUser.setString(2, password);
                hasResultSet = insertUser.execute();
                if (!hasResultSet) {
                    if (insertUser.getUpdateCount() == 1) {
                        isRegistered = true;
                    }
                }
                insertUser.close();
            }
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            JDBCUtils.closeConnection(connection);
        }
        return isRegistered;
    }
}
