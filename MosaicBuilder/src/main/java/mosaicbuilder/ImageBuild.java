/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosaicbuilder;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Miika
 */
public class ImageBuild {
    
    private int tileWidth;
    private int tileHeight;
    
    public ImageBuild(int tileW, int tileH){
        this.tileWidth = tileW;
        this.tileHeight = tileH;
    }
    
    public BufferedImage getTile(BufferedImage goalImage, int x, int y){
        BufferedImage tile = goalImage.getSubimage(x, y, this.tileWidth, this.tileHeight);
        return tile;
        
    }
    
    public int getTileWidth(){
        return this.tileWidth;
    }
    
    public int getTileHeight(){
        return this.tileHeight;
    }
    
    public BufferedImage prepareSourceImg(BufferedImage img){
        Image tmp = img.getScaledInstance(this.tileWidth, this.tileHeight, Image.SCALE_FAST);
        BufferedImage resized = new BufferedImage(this.tileWidth, this.tileHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
    
}
