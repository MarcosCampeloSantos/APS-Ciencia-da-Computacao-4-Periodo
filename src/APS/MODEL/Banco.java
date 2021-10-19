/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APS.MODEL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcossantos
 */
public class Banco {

    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/aps";
    private final String USER = "root";
    private final String PASSWORD = "";
    
    private Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na Conexão", ex);
        }
    }
    
    private void closeConnection(PreparedStatement con){
        try {
            if(con != null){
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void create(String dado){
        
        PreparedStatement stmt = null;
        
        try {
            stmt = getConnection().prepareStatement("INSERT INTO imagens (NOME) VALUES (?)");
            stmt.setString(1, dado);
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeConnection(stmt);
        }
        
    }
}
