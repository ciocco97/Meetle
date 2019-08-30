package meetle.eventi;

import meetle.Meetle;
import meetle.eventi.campi.*;
import meetle.utenti.Utente;

public class PartitaDiCalcio extends Evento {
    public static final String NOME = "Partita di Calcio", DESCRIZIONE = "Sport perloppiù maschile o comunque sappiamo guidare ecco...";
    public static final String N_GENERE = "Genere", N_FASCIA_ETA = "Fascia di eta", N_SPESA_CAMPO = "Spesa campo";
    
    public static final int NUM_CAMPI_EXTRA = 2;
    public static final int NUM_CAMPI_SPESA = 1;
    public static final int I_GENERE = 14, I_FASCIA_ETA = 15, I_SPESA_CAMPO = 16;

    public PartitaDiCalcio(Utente tenteCreatore) {
        super(tenteCreatore, NOME);
        
        this.nome = NOME;
        this.descrizione = DESCRIZIONE;
        
        campiExtra = new Campo[NUM_CAMPI_EXTRA];
        campiSpesa = new Campo[NUM_CAMPI_SPESA];
        campiExtra[I_GENERE-NUM_CAMPI_FISSI] = new CampoString(N_GENERE, "Genere dei giocatori");
        campiExtra[I_FASCIA_ETA-NUM_CAMPI_FISSI] = new CampoRange(N_FASCIA_ETA, "Eta minima e massima per partecipare (formato min-max)");
        campiSpesa[I_SPESA_CAMPO-NUM_CAMPI_FISSI-NUM_CAMPI_EXTRA] = new CampoInt(N_SPESA_CAMPO, "Spesa per l'affitto del campo da calcio");
        
//        setFacoltativi();
    }
    
//    // METODO DA CANCELLARE PRIMA O POI
//    private void setFacoltativi(){        
//        // i prossimi sono solo per comodità per non stare a inserirli ogni volta
//        campi[I_DATA].setFacoltativo();
//        campi[I_TERMINE_ISCRIZIONE].setFacoltativo();
//        campiExtra[I_GENERE-NUM_CAMPI_FISSI].setFacoltativo();
//        campiExtra[I_FASCIA_ETA-NUM_CAMPI_FISSI].setFacoltativo();
//        
//    }
    
    
}
