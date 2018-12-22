/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosaicbuilder;

import java.awt.image.BufferedImage;

/**
 *
 * @author Miika
 */
public class ImageCompare {

    private double distanceSum;

    public ImageCompare() {
        this.distanceSum = 0;
    }

    public void compareTilePixels(BufferedImage goalImage, BufferedImage image) {
        int imWidth = goalImage.getWidth();
        int imHeight = goalImage.getHeight();
        int i, j;

        for (i = 0; i < imWidth; i++) {
            for (j = 0; j < imHeight; j++) {

                int[] tileVector = getVector(goalImage, i, j);
                int[] sourceVector = getVector(image, i, j);

                double dist = getDist(sourceVector, tileVector);
                sumDist(dist);
            }
        }

    }

    public int[] getVector(BufferedImage image, int i, int j) {
        int rgb = image.getRGB(i, j);
        int[] rgbVector = new int[3];

        rgbVector[0] = (rgb >> 16) & 0xff;
        rgbVector[1] = (rgb >> 8) & 0xff;
        rgbVector[2] = (rgb) & 0xff;

        return rgbVector;
    }

    public int[] getVector(BufferedImage image) {
        ImageColor ic = new ImageColor();
        int rgb = ic.getDominantColor(image);
        int[] rgbVector = new int[3];

        rgbVector[0] = (rgb >> 16) & 0xff;
        rgbVector[1] = (rgb >> 8) & 0xff;
        rgbVector[2] = (rgb) & 0xff;

        return rgbVector;
    }

    public double subSquare(int a, int b) {
        double result = (b - a) * (b - a);
        return result;
    }

    public double getDist(int[] a, int[] b) {

        double dist = subSquare(a[0], b[0]) + subSquare(a[1], b[1]) + subSquare(a[2], b[2]);
        return dist;
    }

    public void sumDist(double dist) {
        this.distanceSum += dist;
    }

    public double getDistanceSum() {
        return this.distanceSum;
    }

}
