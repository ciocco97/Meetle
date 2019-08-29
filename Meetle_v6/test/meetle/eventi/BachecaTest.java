package meetle.eventi;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Claudio
 */
public class BachecaTest {
    
    @Before
    public void setUp() {
    }
    
    /**
     * Test of getByID method, of class Bacheca.
     */
    @Test
    public void testGetByID() {
        System.out.println("getByID");
        
        Evento expResult = new PartitaDiCalcio(null, "creatore");
        Bacheca instance = new Bacheca();
        instance.aggiungiEvento(expResult);
        Evento result = instance.getByID(expResult.ID);
        assertEquals(expResult, result);
        
        int count = 0;
        for(int i=0; i<instance.getEventi().size(); i++)
            if(instance.getEventi().get(i).ID == expResult.ID)
                count++;
        assertEquals(1, count);
        
        Evento result2 = instance.getByID(expResult.ID+1);
        assertEquals(null, result2);
        
        
    }

//    /**
//     * Test of getEventiByCreatoreID method, of class Bacheca.
//     */
//    @Test
//    public void testGetEventiByCreatoreID() {
//        System.out.println("getEventiByCreatoreID");
//        String uID = "";
//        Bacheca instance = new Bacheca();
//        ArrayList<Evento> expResult = new PartitaDiCalcio(null, uID);
//        
//        ArrayList<Evento> result = instance.getEventiByCreatoreID(uID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEventiByIscrittoID method, of class Bacheca.
//     */
//    @Test
//    public void testGetEventiByIscrittoID() {
//        System.out.println("getEventiByIscrittoID");
//        String uID = "";
//        Bacheca instance = new Bacheca();
//        ArrayList<Evento> expResult = null;
//        ArrayList<Evento> result = instance.getEventiByIscrittoID(uID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEventi method, of class Bacheca.
//     */
//    @Test
//    public void testGetEventi() {
//        System.out.println("getEventi");
//        Bacheca instance = new Bacheca();
//        ArrayList<Evento> expResult = null;
//        ArrayList<Evento> result = instance.getEventi();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
    /**
     * Test of aggiungiEvento method, of class Bacheca.
     */
    @Test
    public void testAggiungiEvento() {
        System.out.println("aggiungiEvento");
        Bacheca instance = new Bacheca();
        for (int i = 0; i < 5e3; i++) {
            Evento e = new PartitaDiCalcio(null, "uID");
            assertTrue(instance.aggiungiEvento(e));
            assertEquals(e, instance.getByID(e.ID));      
        }
    }

//    /**
//     * Test of rimuoviByID method, of class Bacheca.
//     */
//    @Test
//    public void testRimuoviByID() {
//        System.out.println("rimuoviByID");
//        int eID = 0;
//        Bacheca instance = new Bacheca();
//        boolean expResult = false;
//        boolean result = instance.rimuoviByID(eID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of utentiInvitabili method, of class Bacheca.
//     */
//    @Test
//    public void testUtentiInvitabili() {
//        System.out.println("utentiInvitabili");
//        int eID = 0;
//        Bacheca instance = new Bacheca();
//        ArrayList<String> expResult = null;
//        ArrayList<String> result = instance.utentiInvitabili(eID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
