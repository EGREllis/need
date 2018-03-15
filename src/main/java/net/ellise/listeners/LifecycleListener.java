package net.ellise.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LifecycleListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println(String.format("Web context started %1$s", servletContextEvent.getServletContext().getServletContextName()));
        System.out.flush();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println(String.format("Web context stopped %1$s", servletContextEvent.getServletContext().getServletContextName()));
        System.out.flush();
    }
}
