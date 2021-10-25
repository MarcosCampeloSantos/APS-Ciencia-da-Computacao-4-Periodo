/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APS.MODEL;

import java.sql.*;

/**
 *
 * @author marcossantos
 */
public class Banco {
    
    private String query;
    private byte[] imagem;

    private final String URL = "jdbc:mysql://localhost:3306/aps";
    private final String USER = "root";
    private final String PASS = "";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet result = null;
    
    private void openConection(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASS);           
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
    
    public void closeConnection(){
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Erro para deslogar "+ex.getMessage());
            }finally{
                connection = null;
            }
        }
    }
    
    public void create(byte[] Imagem, int Nome){
        query = "INSERT INTO aps.imagens (NOME, IMAGEM) VALUES (?, ?)";
        openConection();
        
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1 , "Imagem Nª "+Nome);
            statement.setBytes(2, Imagem);
            statement.execute();
            
            System.out.println("Aquivo Gravado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro na Query " + e.getMessage());
        }finally{
            this.closeConnection();
        }
    }
    
    public ResultSet AllSearch(){
        query = "SELECT * FROM aps.imagens";
        openConection();
        
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            
        } catch (SQLException ex) {
            System.out.println("Erro na Busca " + ex.getMessage());
            result = null;
        }
        
        return result;
    }
    
    public byte[] SearchImage(int id){
        query = "SELECT * FROM aps.imagens WHERE ID = ?";
        openConection();
       
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();
            
            result.next();
            imagem = result.getBytes("IMAGEM");
        } catch (SQLException ex) {
            System.out.println("Erro na Busca " + ex.getMessage());
            imagem = null;
        }
        
        return imagem;
    }
}
