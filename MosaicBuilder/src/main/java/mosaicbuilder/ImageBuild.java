/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosaicbuilder;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;

/**
 *
 * @author Miika
 */
public class ImageBuild {

    private int tileWidth;
    private int tileHeight;

    public ImageBuild(int tileW, int tileH) {
        this.tileWidth = tileW;
        this.tileHeight = tileH;
    }

    public BufferedImage getTile(BufferedImage goalImage, int x, int y) {
        BufferedImage tile = goalImage.getSubimage(x, y, this.tileWidth, this.tileHeight);
        return tile;

    }

    public int getTileWidth() {
        return this.tileWidth;
    }

    public int getTileHeight() {
        return this.tileHeight;
    }

    public int getTilesColumns(BufferedImage img) {
        int tiles = img.getWidth() / this.tileWidth;
        return tiles;
    }

    public int getTilesRows(BufferedImage img) {
        int tiles = img.getHeight() / this.tileHeight;
        return tiles;
    }

    public BufferedImage prepareSourceImg(BufferedImage img) throws IOException {

//        If image is wider than it is tall, first resize height to tile size and then crop out the parts outside of the tile width, leaving the part in the middle
        if (img.getWidth() > img.getHeight()) {
            BufferedImage resizedHeight = Thumbnails.of(img).size(img.getWidth(), this.tileHeight).asBufferedImage();
            int cropX = ((resizedHeight.getWidth() - this.tileWidth) / 2);
            BufferedImage prepared = resizedHeight.getSubimage(cropX, 0, this.tileWidth, this.tileHeight);
            return prepared;

//        If image is taller than it is wide, first resize width to tile size and then crop out the parts outside of the tile height, leaving the part in the middle
        } else {
            BufferedImage resizedWidth = Thumbnails.of(img).size(this.tileWidth, img.getHeight()).asBufferedImage();
            int cropY = ((resizedWidth.getHeight() - this.tileHeight) / 2);
            BufferedImage prepared = resizedWidth.getSubimage(0, cropY, this.tileHeight, this.tileWidth);
            return prepared;
        }

    }

    public BufferedImage buildMosaic(File[] source, int[][] feed, int tileColumns, int tileRows) throws IOException {

        int i, j;

        BufferedImage image = new BufferedImage(tileColumns * this.tileWidth, tileRows * this.tileHeight, BufferedImage.TYPE_INT_ARGB);

        for (j = 0; j < tileRows; j++) {
            for (i = 0; i < tileColumns; i++) {
                int bestID = feed[j][i];

                BufferedImage sub = image.getSubimage(i * this.tileWidth, j * this.tileHeight, this.tileWidth, this.tileHeight);
                BufferedImage block = ImageIO.read(source[bestID]);
                BufferedImage prepared = prepareSourceImg(block);
                BufferedImage converted = new BufferedImage(prepared.getWidth(), prepared.getHeight(), BufferedImage.TYPE_INT_ARGB);
                converted.getGraphics().drawImage(prepared, 0, 0, null);
                sub.setData(converted.getData());
            }
        }
        return image;
    }

//    public BufferedImage buildFromColors(File[] source, int[][] feed, int tileColumns, int tileRows) throws IOException {
//
//        ImageColor ic = new ImageColor();
//        int i, j;
//
//        BufferedImage image = new BufferedImage(tileColumns * this.tileWidth, tileRows * this.tileHeight, BufferedImage.TYPE_INT_ARGB);
//
//        for (j = 0; j < tileRows; j++) {
//            for (i = 0; i < tileColumns; i++) {
//                
//            }
//        }
//
//    }

}
