/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APS.MODEL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcossantos
 */
public class Banco {

    private String SERVER = "jdbc:mysql://localhost:3306/aps";
    private String USER = "root";
    private String PASS = "";
    
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultset = null;
    
    private void openConection(){
        try {
            this.connection = (Connection)DriverManager.getConnection(SERVER, USER, PASS);
            this.statement = this.connection.createStatement();
        } catch (Exception e) {
            System.out.println("Erro "+e.getMessage());
        }
    }
    
    private void closeConnection(PreparedStatement con){
        if(this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException ex) {
                System.out.println("Erro para deslogar "+ex.getMessage());
            }
            this.connection = null;
        }
    }
    
    public void create(String dado){
        String query = "INSERT INTO imagens ('NOME') VALUES ('Apenas Um Teste')";
        try {
            this.openConection();
            this.statement.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("Erro para na Query "+ex.getMessage());
        }
    }
}
