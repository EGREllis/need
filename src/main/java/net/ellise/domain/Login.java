package net.ellise.domain;

import java.io.IOException;

public interface Login {
    String LOGIN_KEY = "LoginServlet";
    boolean isValidLogin(String username, String password) throws IOException;
    boolean register(String username, String password) throws IOException;
}
