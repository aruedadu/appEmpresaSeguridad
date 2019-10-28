/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.gui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageJPanel extends JPanel {

    private Image fondo=null;
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fondo,0,0,getWidth(),getHeight(),null);
    }
    public void setImage(String image){
        if (image!=null) {
            fondo=new ImageIcon(getClass().getResource(image)).getImage();
        }
    }

}
