package net.ellise.domain.database;

public class JDBCConstants {
    public static final String CREATE_CONNECTION = "jdbc:derby:memory:need;create=true";

    public static final String CREATE_USER_TABLE = "CREATE TABLE logins (username VARCHAR(20), password VARCHAR(20))";

    public static final String POPULATE_USER_TABLE = "INSERT INTO logins (username, password) VALUES ('edward', 'password')";

    public static final String COUNT_USER_TABLE = "SELECT count(0) FROM logins";

    public static final String CHECK_USER_EXISTS = "SELECT 1 FROM logins WHERE username  = ? AND password = ?";
}
