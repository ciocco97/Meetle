/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetle.utenti;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Claudio
 */
public class NotificheTest {
    
    Utente utente;
    
    public NotificheTest() {
    }
    
    @Before
    public void setUp() {
        utente = new Utente("utente");
    }
    
    @Test
    public void testAggiungiNotifica() {
        
        for (int i = 0; i < 1000; i++) 
            assertTrue(utente.aggiungiNotifica(0, "tit", "mex"));  
        assertEquals(1000, utente.getNotifiche().size());
        
    }

    @Test
    public void testRimuoviNotifica() {
        assertFalse(utente.rimuoviNotifica(0));
        
        utente.aggiungiNotifica(0, "tit", "mex");
        utente.aggiungiNotifica(0, "tit", "mex");
        assertEquals(2, utente.getNotifiche().size());
        
        assertTrue(utente.rimuoviNotifica(utente.getNotifiche().get(0).getID()));
        assertEquals(1, utente.getNotifiche().size());
        
    }

//    /**
//     * Test of segnaNotificaLetta method, of class Utente.
//     */
//    @Test
//    public void testSegnaNotificaLetta() {
//        System.out.println("segnaNotificaLetta");
//        int nID = 0;
//        Utente instance = null;
//        instance.segnaNotificaLetta(nID);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of aggiungiInvito method, of class Utente.
//     */
//    @Test
//    public void testAggiungiInvito() {
//        System.out.println("aggiungiInvito");
//        int eID = 0;
//        Utente instance = null;
//        instance.aggiungiInvito(eID);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
