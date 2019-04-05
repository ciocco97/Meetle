package meetle.eventi;

import meetle.eventi.campi.*;
import meetle.utenti.Utente;

public class PartitaDiCalcio extends Evento {
    public static final String NOME = "Partita di Calcio", DESCRIZIONE = "Sport perloppiù maschile o comunque sappiamo guidare ecco...";
    public static final String N_GENERE = "Genere", N_FASCIA_ETA = "Fascia di eta";
    
    public static final int NUM_CAMPI_EXTRA = 2;
    public static final int I_GENERE = 12, I_FASCIA_ETA = 13;

    public PartitaDiCalcio(String creatoreID) {
        super(creatoreID);
        
        this.nome = NOME;
        this.descrizione = DESCRIZIONE;
        
        campiExtra = new Campo[NUM_CAMPI_EXTRA];
        campiExtra[I_GENERE-NUM_CAMPI_FISSI] = new CampoString(N_GENERE, "Genere dei giocatori");
        campiExtra[I_FASCIA_ETA-NUM_CAMPI_FISSI] = new CampoRange(N_FASCIA_ETA, "Eta minima e massima per partecipare");
    }
    
    
}
