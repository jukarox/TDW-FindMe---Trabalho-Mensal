package edu.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class Dao {

    public static String DRIVER, URL, USER, PASSWORD;

    protected Connection getConnection() throws Exception {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (Exception e) {
            throw new Exception("Erro ao obter a conexão.", e);
        }
    }
}
