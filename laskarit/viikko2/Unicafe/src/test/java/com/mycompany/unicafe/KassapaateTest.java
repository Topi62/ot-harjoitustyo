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
 * @author tkarkine
 */
public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void alkuTilanneOikein(){
        assertEquals(100000,kassa.kassassaRahaa());
        assertEquals(0,kassa.maukkaitaLounaitaMyyty());
        assertEquals(0,kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiTasaraha(){
        kassa.syoEdullisesti(240);
       assertEquals(100240,kassa.kassassaRahaa());
       assertEquals(1   ,kassa.edullisiaLounaitaMyyty());
     }
    
    @Test
    public void syoMaukkaastiKympilla(){
        assertEquals(600,kassa.syoMaukkaasti(1000));
        assertEquals(100400,kassa.kassassaRahaa());
        assertEquals(1,kassa.maukkaitaLounaitaMyyty());
    
        
    }
    
    @Test
    public void syoEdullisestiRahaEiRiita(){
       assertEquals(140,kassa.syoEdullisesti(140));
       assertEquals(100000,kassa.kassassaRahaa());
       assertEquals(0   ,kassa.edullisiaLounaitaMyyty());
      
    }
    @Test
    public void syoMaukkaastiRahaEiRiita(){
        assertEquals(390,kassa.syoMaukkaasti(390));
        assertEquals(100000,kassa.kassassaRahaa());
        assertEquals(0,kassa.maukkaitaLounaitaMyyty());
    
        
    }
    
    @Test
    public void korttiostoEdullinenTarpeeksiRahaa(){
     kortti= new Maksukortti(800);
     assertTrue(kassa.syoEdullisesti(kortti));
     assertEquals(560,kortti.saldo());
     assertEquals(1,kassa.edullisiaLounaitaMyyty());
     assertEquals(100000,kassa.kassassaRahaa());
    }
    
    @Test
    public void korttiostoMaukkaastiTarpeeksiRahaa(){
     kortti= new Maksukortti(800);
     assertTrue(kassa.syoMaukkaasti(kortti));
     assertEquals(400,kortti.saldo());
     assertEquals(1,kassa.maukkaitaLounaitaMyyty());
     assertEquals(100000,kassa.kassassaRahaa());
    }
    
     @Test
    public void korttiostoMaukkaastiEiTarpeeksiRahaa(){
     kortti= new Maksukortti(300);
     assertFalse(kassa.syoMaukkaasti(kortti));
     assertEquals(300,kortti.saldo());
     assertEquals(0,kassa.maukkaitaLounaitaMyyty());
     assertEquals(100000,kassa.kassassaRahaa());
    }
    
     @Test
    public void korttiostoEdullisestiEiTarpeeksiRahaa(){
     kortti= new Maksukortti(200);
     assertFalse(kassa.syoEdullisesti(kortti));
     assertEquals(200,kortti.saldo());
     assertEquals(0,kassa.edullisiaLounaitaMyyty());
     assertEquals(100000,kassa.kassassaRahaa());
    }
    
    @Test
    public void kortinLatausOnnistuu(){
        kortti=new Maksukortti(300);
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(1300,kortti.saldo());
        assertEquals(101000,kassa.kassassaRahaa());
        
    }
    
    @Test
    public void kortinLatausNegatiivinen(){
        kortti=new Maksukortti(300);
        kassa.lataaRahaaKortille(kortti, -1000);
        assertEquals(300,kortti.saldo());
        assertEquals(100000,kassa.kassassaRahaa());
        
    }
    
    
}
