/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mosaicbuilder;

import java.io.File;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;

/**
 *
 * @author ramirami
 */
public class ImageColor {

    public int getDominantColor(BufferedImage img) {
        int dominantColor;
        int width = img.getWidth();
        int height = img.getHeight();

        int i, j;

        HashMap<Integer, Integer> hm = new HashMap();

        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                int p = img.getRGB(i, j);
                int[] argb = getPixelARGBArray(p);
                int[] roundedARGB = getRoundedPixelARGBArray(argb);

                if (isPixelWhite(roundedARGB)) {
                    continue;
                }
                int rounded = (roundedARGB[0] << 24) | (roundedARGB[1] << 16) | (roundedARGB[2] << 8) | roundedARGB[3];

                if (!hm.containsKey(rounded)) {
                    hm.put(rounded, 1);
                } else {
                    hm.put(rounded, hm.get(rounded) + 1);
                }
            }
        }

        dominantColor = getBiggestValueKey(hm);
        return dominantColor;
    }

    public boolean isPixelWhite(int[] argb) {
        for (int i = 1; i <= 3; i++) {
            if (argb[i] <= 245) {
                return false;
            }
        }
        return true;
    }

    public int[] getPixelARGBArray(int pixel) {

        int[] pixelARGB = new int[4];

        pixelARGB[0] = (pixel >> 24) & 0xff;
        pixelARGB[1] = (pixel >> 16) & 0xff;
        pixelARGB[2] = (pixel >> 8) & 0xff;
        pixelARGB[3] = (pixel) & 0xff;

        return pixelARGB;

    }

    public int[] getRoundedPixelARGBArray(int[] pixel) {

        int[] roundedPixelARGB = new int[4];
        int i;

        for (i = 0; i < pixel.length; i++) {
            int temp = (int) (Math.round(pixel[i] / 10.0) * 10);

            roundedPixelARGB[i] = Math.min(temp, 255);
        }

        return roundedPixelARGB;
    }

    public int getBiggestValueKey(HashMap<Integer, Integer> hm) {
        int highestColor = -1;
        int highestAmount = -1;

        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            int color = entry.getKey();
            int amount = entry.getValue();

            if (amount > highestAmount) {
                highestAmount = amount;
                highestColor = color;
            }
        }

        return highestColor;
    }

}
