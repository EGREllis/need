package net.ellise.domain.database;

public class JDBCConstants {
    public static final String CREATE_CONNECTION = "jdbc:derby:memory:need;create=true";

    public static final String CREATE_USER_TABLE = "CREATE TABLE users (username VARCHAR(20), passoword VARCHAR(20))";
}
