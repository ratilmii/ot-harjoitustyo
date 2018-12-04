/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosaicbuilder;

import java.awt.image.BufferedImage;
import java.io.File;


/**
 *
 * @author Miika
 */
public class ImageCompare {
    
    private double distanceSum;
    
    public ImageCompare(){
        this.distanceSum = 0;
    }
    
    public void compareTile(BufferedImage goalImage, BufferedImage image){
        int ImWidth = goalImage.getWidth();
        int ImHeight = goalImage.getHeight();
        int i, j;
        
        
        for (i=0; i < ImWidth; i++){
            for (j=0; j < ImHeight; j++){
                
                int tileRGB = goalImage.getRGB(i, j);
                int imgRGB = image.getRGB(i, j);
                int[] tileVector = getVector(goalImage, i, j);
                int[] sourceVector = getVector(image, i, j);
                
                double dist = getDist(sourceVector, tileVector);
                sumDist(dist);

            }
        }
        
    }

    public int[] getVector(BufferedImage image, int i, int j){
        int RGB = image.getRGB(i, j);
        int[] RGBVector = new int[4];
        
        RGBVector[0] = (RGB >> 24) & 0xff;
        RGBVector[1] = (RGB >> 16) & 0xff;
        RGBVector[2] = (RGB >> 8) & 0xff;
        RGBVector[3] = (RGB) & 0xff;
        
        return RGBVector;
    }
    
    public double subSquare(int A, int B){
        double result = (B - A) * (B - A);
        return result;
    }
    
    public double getDist(int[] A, int[] B){
        
        double dist = Math.sqrt(subSquare(A[0], B[0]) + subSquare(A[1], B[1]) + subSquare(A[2], B[2]) + subSquare(A[3], B[3]));
        return dist;
    }
    
    public void sumDist(double dist){
        this.distanceSum += dist;
    }
    
    public double getDistanceSum(){
        return this.distanceSum;
    }
    
}
