/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APS.MODEL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    
    private void closeConnection(){
        if(this.connection != null) {
            try {
                this.connection.close();
                this.statement.close();
            } catch (SQLException ex) {
                System.out.println("Erro para deslogar "+ex.getMessage());
            }finally{
                this.connection = null;
                this.statement = null;
            }
        }
    }
    
    public void create(String dado){
        String query = "INSERT INTO aps.imagens (NOME) VALUES ('Apenas Um Teste')";
        this.openConection();
        try {
            this.statement.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("Erro na Query "+ex.getMessage());
        }finally{
            this.closeConnection();
        }
    }
}
