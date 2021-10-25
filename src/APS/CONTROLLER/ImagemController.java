/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APS.CONTROLLER;

import APS.MODEL.Banco;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import javax.imageio.ImageIO;

/**
 *
 * @author marco
 */
public class ImagemController {
    
    Banco Banco = new Banco();
    
    
    public void sendImagem(File[] Imagens){
        byte[] bytearray;
        
        Random Gerador = new Random();
        
        try { 
            for(int i = 0; i < Imagens.length; i++){
                bytearray = Files.readAllBytes(Imagens[i].toPath());
                Banco.create(bytearray, Gerador.nextInt(100));
            }
        } catch (IOException ex) {
            System.out.println("Imagem não encontrada " + ex.getMessage());
        }
    }
    
    public BufferedImage redimensionarImagem(byte[] img, int larguraFinal, int alturaFinal) {
        BufferedImage ImagemBuffer = null; 
         
        ByteArrayInputStream imagemByte = new ByteArrayInputStream(img);
        BufferedImage tmp = new BufferedImage(larguraFinal, alturaFinal, BufferedImage.TYPE_INT_RGB);
        
        try {
            ImagemBuffer = ImageIO.read(imagemByte);
        } catch (IOException ex) {
            System.out.println("Erro ao converter imagem " + ex.getMessage());
        }
        
        Graphics2D g3 = tmp.createGraphics();
        
        g3.drawImage(ImagemBuffer, 0, 0, larguraFinal, alturaFinal, null);
        g3.dispose();
        
        ImagemBuffer = tmp;
        
        return ImagemBuffer;
    }
}
