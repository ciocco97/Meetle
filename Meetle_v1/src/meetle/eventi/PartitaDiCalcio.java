package meetle.eventi;

import meetle.eventi.campi.CampoGenere;
import meetle.eventi.campi.Campo;
import meetle.eventi.campi.CampoRange;

public class PartitaDiCalcio extends Evento {
    
    public static final int NUM_CAMPI_AGGIUNTIVI = 2;
    public static final int I_GENERE = 12, I_FASCIA_ETA = 13;

    public PartitaDiCalcio() {
        super();
        
        this.nome = "Partita di Calcio";
        this.descrizione = "Sport perloppiù maschile o comunque sappiamo guidare ecco...";
        
        campiExtra = new Campo[NUM_CAMPI_AGGIUNTIVI];
        campiExtra[I_GENERE-NUM_CAMPI_FISSI] = new CampoGenere("Genere", "Genere dei giocatori");
        campiExtra[I_FASCIA_ETA-NUM_CAMPI_FISSI] = new CampoRange("Fascia di Eta", "Eta minima e massima per partecipare");
    }
    
}
