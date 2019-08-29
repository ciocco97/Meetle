/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetle.eventi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Claudio
 */
public class EventoTest {
    
    Evento evento;
    
    public EventoTest() {
    }
    
    @Before
    public void setUp() {
        evento = new PartitaDiCalcio(null, "uID");
    }
    

    /**
     * Test of setValoreDaString method, of class Evento.
     */
    @Test
    public void testSetValoreDaString() {
        evento.setValoreDaString(Evento.I_TITOLO, "titolo");
        assertEquals("titolo", evento.getTitolo());
        
        evento.setValoreDaString(Evento.I_DATA, "2019-10-10");
        assertEquals(LocalDate.of(2019, 10, 10), evento.getData());
        
        evento.setValoreDaString(Evento.I_ORA, "13:00");
        assertEquals(LocalTime.of(13, 0), evento.getOra());
        
    }
    
    /**
     * Porta l'evento allo stato valido inserendo correttamente i campi obbligatori
     */
    public void eventoValido() {
        evento.setValoreDaString(Evento.I_TITOLO, "titolo");
        evento.setValoreDaString(Evento.I_NUM_PARTECIPANTI, "2");
        evento.setValoreDaString(Evento.I_LUOGO, "luogo");
        evento.setValoreDaString(Evento.I_DATA, "2020-10-10");
        evento.setValoreDaString(Evento.I_ORA, "13:00");
        evento.setValoreDaString(Evento.I_TOLLERANZA_PARTECIPANTI, "1");
        evento.setValoreDaString(Evento.I_QUOTA_INDIVIDUALE, "1");
        evento.setValoreDaString(Evento.I_TERMINE_ISCRIZIONE, "2020-10-08");
        evento.setValoreDaString(Evento.I_DATA_RITIRO_ISCRIZIONE, "2020-10-06");
        evento.setValoreDaString(PartitaDiCalcio.I_GENERE, "femminile");
        evento.setValoreDaString(PartitaDiCalcio.I_FASCIA_ETA, "10-12");
        evento.setValoreDaString(PartitaDiCalcio.I_SPESA_CAMPO, "3");     
        evento.aggiornaStato();
    }

    @Test
    public void testValidita() {
        assertEquals(Stato.NONVALIDO, evento.getIndiceStatoCorrente());
        
        eventoValido();
        assertEquals(Stato.VALIDO, evento.getIndiceStatoCorrente());
        
        evento.getTuttiCampi()[Evento.I_TITOLO].svuota();
        evento.aggiornaStato();        
        assertEquals(Stato.NONVALIDO, evento.getIndiceStatoCorrente());
        
        evento.setValoreDaString(Evento.I_TITOLO, "titolo");
        evento.aggiornaStato();        
        assertEquals(Stato.VALIDO, evento.getIndiceStatoCorrente());
        
        evento.setValoreDaString(Evento.I_DATA, "2020-10-07");
        evento.aggiornaStato();
        assertEquals(Stato.NONVALIDO, evento.getIndiceStatoCorrente());
        
        evento.setValoreDaString(Evento.I_TERMINE_ISCRIZIONE, "2020-10-06");
        evento.aggiornaStato();
        assertEquals(Stato.VALIDO, evento.getIndiceStatoCorrente());
        
        evento.setValoreDaString(Evento.I_DATA_RITIRO_ISCRIZIONE, "2020-10-07");
        evento.aggiornaStato();
        assertEquals(Stato.NONVALIDO, evento.getIndiceStatoCorrente());
        
        evento.apriEvento();
        assertNotEquals(Stato.APERTO, evento.getIndiceStatoCorrente());
        
        evento.ritiraEvento();
        assertNotEquals(Stato.RITIRATO, evento.getIndiceStatoCorrente());
        
        evento.setValoreDaString(Evento.I_DATA_RITIRO_ISCRIZIONE, "2020-10-05");
        evento.aggiornaStato();
        assertEquals(Stato.VALIDO, evento.getIndiceStatoCorrente());
        
        evento.apriEvento();
        assertEquals(Stato.APERTO, evento.getIndiceStatoCorrente());
        
        evento.ritiraEvento();
        assertEquals(Stato.RITIRATO, evento.getIndiceStatoCorrente());
        
    }
    
    @Test
    public void testAperturaEvento() {
        eventoValido();
        evento.getTuttiCampi()[Evento.I_TITOLO].svuota();
        evento.aggiornaStato();
        
        evento.apriEvento();
        assertEquals(Stato.NONVALIDO, evento.getIndiceStatoCorrente());
        
       
        evento.setValoreDaString(Evento.I_TITOLO, "titolo");
        evento.aggiornaStato();       
        evento.apriEvento();
        assertEquals(Stato.APERTO, evento.getIndiceStatoCorrente()); 
        
    }
    
    @Test
    public void testRitiroEvento() {
        eventoValido();
        
        evento.ritiraEvento();
        assertEquals(Stato.VALIDO, evento.getIndiceStatoCorrente());
        
        evento.apriEvento();
        evento.ritiraEvento();
        assertEquals(Stato.RITIRATO, evento.getIndiceStatoCorrente());
        
    }
    
    @Test
    public void testEventoChiusoEConcluso() {
        eventoValido();
        
        evento.apriEvento();        
        assertEquals(Stato.APERTO, evento.getIndiceStatoCorrente());        
        evento.switchIscrizione("u1", "t");
        evento.switchIscrizione("u2", "t");
        evento.setValoreDaString(Evento.I_TERMINE_ISCRIZIONE, LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_DATE));
        evento.aggiornaStato();        
        assertEquals(Stato.CHIUSO, evento.getIndiceStatoCorrente());
        evento.setValoreDaString(Evento.I_DATA, LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_DATE));
        evento.aggiornaStato();
        assertEquals(Stato.CONCLUSO, evento.getIndiceStatoCorrente());        
    
    }
    
    @Test
    public void testEventoFallito() {
        eventoValido();
        
        evento.apriEvento();        
        evento.setValoreDaString(Evento.I_TERMINE_ISCRIZIONE, LocalDate.now().minusDays(1).format(DateTimeFormatter.ISO_DATE));
        assertEquals(Stato.APERTO, evento.getIndiceStatoCorrente());        
        evento.aggiornaStato();        
        assertEquals(Stato.FALLITO, evento.getIndiceStatoCorrente());
    
    }
    
    @Test
    public void testIscrizioni() {
        eventoValido();
        assertEquals(1, evento.getNumIscrittiCorrente());
        
        
        assertFalse("non implementato: il creatore non può disiscriversi", evento.switchIscrizione(evento.getCreatoreID(), "t"));
        assertEquals(1, evento.getNumIscrittiCorrente());
        
        assertTrue(evento.switchIscrizione("u1", "t"));
        assertEquals(2, evento.getNumIscrittiCorrente());
        
        assertTrue(evento.switchIscrizione("u1", "t"));
        assertEquals(1, evento.getNumIscrittiCorrente());
        
        assertTrue(evento.switchIscrizione("u2", "t"));
        assertEquals(2, evento.getNumIscrittiCorrente());
        
        assertTrue(evento.switchIscrizione("u3", "t"));
        assertEquals(3, evento.getNumIscrittiCorrente());
        
        assertFalse(evento.switchIscrizione("u4", "t"));
        assertEquals(3, evento.getNumIscrittiCorrente());
        
        assertFalse(evento.switchIscrizione("u4", "t"));
        assertEquals(3, evento.getNumIscrittiCorrente());        
        
    }
    
//
//    /**
//     * Test of isRitirabile method, of class Evento.
//     */
//    @Test
//    public void testIsRitirabile() {
//        System.out.println("isRitirabile");
//        Evento instance = null;
//        boolean expResult = false;
//        boolean result = instance.isRitirabile();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isInvitoInviabile method, of class Evento.
//     */
//    @Test
//    public void testIsInvitoInviabile() {
//        System.out.println("isInvitoInviabile");
//        Evento instance = null;
//        boolean expResult = false;
//        boolean result = instance.isInvitoInviabile();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of isIscrivibile method, of class Evento.
//     */
//    @Test
//    public void testIsIscrivibile() {
//        System.out.println("isIscrivibile");
//        Evento instance = null;
//        boolean expResult = false;
//        boolean result = instance.isIscrivibile();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
