package net.ellise.servlets;

import net.ellise.domain.Login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Received doGet!");
        System.out.flush();
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Received doPost!");
        System.out.flush();
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(String.format("Logging in with username, password (%1$s, %2$s)", username, password));
        Login login = (Login)getServletContext().getAttribute(Login.LOGIN_KEY);
        boolean success = login.isValidLogin(username, password);
        if (success) {
            System.out.println("Login success");
            String url = response.encodeRedirectURL("./needs");
            response.sendRedirect(url);
        } else {
            System.out.println("Login failure");
        }
        System.out.flush();
    }
}
