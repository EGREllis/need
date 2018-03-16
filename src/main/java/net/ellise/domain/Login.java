package net.ellise.domain;

import java.io.IOException;

public interface Login {
    boolean isValidLogin(String username, String password) throws IOException;
}
