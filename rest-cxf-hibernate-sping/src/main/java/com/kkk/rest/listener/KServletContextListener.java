package com.kkk.rest.listener;

//import javax.jms.JMSException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class KServletContextListener implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(KServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        /**
         * Initialize Configuration Properties, module, service
         */
    }

    @Override
    public void contextDestroyed(ServletContextEvent contextEvent) {
        /**
         * Destroy the Service, resource etc
         */
    }
}
