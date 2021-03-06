package mosaicbuilder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ramirami
 */
public class ImageColorTest {
    
    ImageColor ic = new ImageColor();
    
    public ImageColorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void getDominantColorTest() throws IOException {
        BufferedImage b = ImageIO.read(new File("src/testImage.png"));
        int d = ic.getDominantColor(b);
        assertEquals(-6235046, d);
    }
    
    @Test
    public void biggestValueKeyFromHashMap(){
        HashMap<Integer,Integer> hm = new HashMap();
        hm.put(1, 1);
        hm.put(2, 2);
        
        int k = ic.getBiggestValueKey(hm);
        
        assertEquals(2,k);
    }
    
    @Test
    public void PixelIsWhiteTest(){
        int[] test = new int[] {255,255,255,255};
        boolean b = ic.isPixelWhite(test);
        
        assertTrue(b);
    }
    
    @Test 
    public void PixelIsNotWhite(){
        int[] test = new int[] {255,255,200,255};
        boolean b = ic.isPixelWhite(test);
        
        assertFalse(b);
    }
    

}
