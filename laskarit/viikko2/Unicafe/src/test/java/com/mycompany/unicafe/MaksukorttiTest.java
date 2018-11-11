package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein(){
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void lisaaRahaaToimii(){
        kortti.lataaRahaa(1200);
        assertEquals("saldo: 12.10", kortti.toString());
    }
    
    @Test
    public void otaRahaaEiRiita(){
       kortti.otaRahaa(100);
       assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahaEiRiita(){
        assertFalse(kortti.otaRahaa(50));
    }
    
    @Test
    public void rahaRiittaa(){
        kortti.lataaRahaa(40);
        assertTrue(kortti.otaRahaa(50));
    }
    
    @Test
    public void saldoOikein(){
        assertEquals(10,kortti.saldo());
    }
}
