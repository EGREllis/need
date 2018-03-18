package net.ellise.domain.database;

public class JDBCConstants {
    public static final String CREATE_USER_TABLE = "CREATE TABLE logins (username VARCHAR(20), password VARCHAR(20))";

    public static final String POPULATE_USER_TABLE = "INSERT INTO logins (username, password) VALUES ('edward', 'password')";

    public static final String COUNT_USER_TABLE = "SELECT count(0) FROM logins";

    public static final String CHECK_VALID_LOGIN = "SELECT 1 FROM logins WHERE username  = ? AND password = ?";

    public static final String CHECK_USER_EXISTS = "SELECT count(0) FROM logins WHERE username = ?";

    public static final String INSERT_VALID_LOGIN = "INSERT INTO logins (username, password) VALUES (?, ?)";
}
