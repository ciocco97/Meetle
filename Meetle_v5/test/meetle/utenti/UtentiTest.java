/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetle.utenti;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Claudio
 */
public class UtentiTest {
    
    Utenti utenti;
    
    public UtentiTest() {
    }
    
    @Before
    public void setUp() {
        utenti = new Utenti();
    }
    
    @Test
    public void testGetByID() {
        
        assertNull(utenti.getUtenteDaID("nonesiste"));
        
        Utente u1 = new Utente("u1");
        utenti.add(u1);
        assertEquals(u1, utenti.getUtenteDaID(u1.getID()));
        
    }

    @Test
    public void testGetUtentiPerPreferenza() {
        Utente u1 = new Utente("u1"),
                u2 = new Utente("u2"),
                u3 = new Utente("u3"),
                u4 = new Utente("u4");
        ArrayList<String> prefs;
        prefs = new ArrayList<>();
        prefs.add("cat1");
        u1.setCategoriePreferite(prefs);
        u2.setCategoriePreferite(prefs);
        prefs = new ArrayList<>();
        prefs.add("cat1");
        prefs.add("cat2");
        u3.setCategoriePreferite(prefs);
        prefs = new ArrayList<>();
        prefs.add("cat1");
        prefs.add("cat2");
        prefs.add("cat3");
        u4.setCategoriePreferite(prefs);
        
        utenti.add(u1);
        utenti.add(u2);
        utenti.add(u3);
        utenti.add(u4);
        
        assertEquals(4, utenti.getUtentiPerPreferenza("cat1").size());
        assertEquals(2, utenti.getUtentiPerPreferenza("cat2").size());
        assertEquals(1, utenti.getUtentiPerPreferenza("cat3").size());
    }

    @Test
    public void testAggiungiUtente() {
        
        assertTrue(utenti.add(new Utente("u1")));
        
        assertNotNull(utenti.getUtenteDaID("u1"));
        
        assertTrue(utenti.add(new Utente("u2")));
        
        assertFalse("non è ancora implementato, non dovrebbe aggiungere due utenti uguali", utenti.add(new Utente("u1")));        
        
        
    }
    
}
