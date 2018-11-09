/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

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
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }
    
    @Test
    public void rahamaaraAlussaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void myytyjaEdullisiaLounaitaAlussaOikein() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myytyjaMaukkaitaLounaitaAlussaOikein() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenKateisostoOnnistui() {
        assertEquals(60, kassa.syoEdullisesti(300));
        assertEquals(100240, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenKateisostoEpaonnistui() {
        assertEquals(200, kassa.syoEdullisesti(200));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKorttiostoOnnistui() {
        kortti = new Maksukortti(500);
        kassa.syoEdullisesti(kortti);
        assertEquals(260, kortti.saldo());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test 
    public void edullinenKorttiostoEpaonnistui() {
        kortti = new Maksukortti(100);
        kassa.syoEdullisesti(kortti);
        assertEquals(100, kortti.saldo());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukasKateisostoOnnistui() {
        assertEquals(200, kassa.syoMaukkaasti(600));
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukasKateisostoEpaonnistui() {
        assertEquals(200, kassa.syoMaukkaasti(200));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukasKorttiostoOnnistui() {
        kortti = new Maksukortti(500);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukasKorttiOstoEpaonnistui() {
        kortti = new Maksukortti(300);
        kassa.syoMaukkaasti(kortti);
        assertEquals(300, kortti.saldo());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortilleRahanLatausOnnistuu() {
        kortti = new Maksukortti(500);
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, kassa.kassassaRahaa());
        assertEquals(1500, kortti.saldo());
    }
    
    @Test
    public void kortilleRahanLatausEpaonnistuu() {
        kortti = new Maksukortti(500);
        kassa.lataaRahaaKortille(kortti, -400);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(500, kortti.saldo());
    }

}
