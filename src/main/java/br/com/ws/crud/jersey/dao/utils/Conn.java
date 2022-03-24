package br.com.ws.crud.jersey.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {

	private static final Connection connection = buildConnection();
	 
    private static Connection buildConnection() {
        try {           
            Class.forName("org.postgresql.Driver");         
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/crudjersey", "postgres", "9379");
 
        } catch (Exception ex) {
            System.err.println("Connection failed: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
 
    public static Connection getConnection() {
        return connection;
    }
}
