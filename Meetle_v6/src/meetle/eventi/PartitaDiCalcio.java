package meetle.eventi;

import meetle.Meetle;
import meetle.eventi.campi.*;
import meetle.gui.testo.Dizionario;
import meetle.utenti.Utente;

public class PartitaDiCalcio extends Evento {
    public static final String NOME = "Partita di Calcio";
    
    public static final int NUM_CAMPI_EXTRA = 2;
    public static final int NUM_CAMPI_SPESA = 1;
    public static final int I_GENERE = 14, I_FASCIA_ETA = 15, I_SPESA_CAMPO = 16;

    public PartitaDiCalcio(Utente tenteCreatore) {
        super(tenteCreatore, NOME);
        
        this.nome = NOME;
        this.descrizione = Dizionario.get(Dizionario.DESC_PARTITA_CALCIO);
        
        campiExtra = new Campo[NUM_CAMPI_EXTRA];
        campiSpesa = new Campo[NUM_CAMPI_SPESA];
        campiExtra[I_GENERE-NUM_CAMPI_FISSI] = new CampoString(Dizionario.get(Dizionario.NOME_GENERE_GIOCATORI), Dizionario.get(Dizionario.DESC_GENERE_GIOCATORI));
        campiExtra[I_FASCIA_ETA-NUM_CAMPI_FISSI] = new CampoRange(Dizionario.get(Dizionario.NOME_ETA_MIN_MAX), Dizionario.get(Dizionario.DESC_ETA_MIN_MAX));
        campiSpesa[I_SPESA_CAMPO-NUM_CAMPI_FISSI-NUM_CAMPI_EXTRA] = new CampoInt(Dizionario.get(Dizionario.NOME_SPESA), Dizionario.get(Dizionario.DESC_SPESA));
        
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
