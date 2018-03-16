package net.ellise.domain.database;

import net.ellise.domain.Login;

import java.io.IOException;

public class JDBCLogin implements Login {
    @Override
    public boolean isValidLogin(String username, String password) throws IOException {
        return false;
    }
}
