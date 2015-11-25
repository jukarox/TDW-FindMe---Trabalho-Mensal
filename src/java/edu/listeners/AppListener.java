/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.listeners;

import edu.dao.Dao;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author julio
 */
@WebListener()
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties prop = new Properties();
      
        try {
            //lê o arquivo de propriedades
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

            //obtem as propriedades do arquivo e carrega no Dao
            Dao.DRIVER =  prop.getProperty("driver");
            Dao.URL =  prop.getProperty("url");
            Dao.USER =  prop.getProperty("user");
            Dao.PASSWORD =  prop.getProperty("password");

            //carrega driver no DriverManager
            Class.forName(Dao.DRIVER);        
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
