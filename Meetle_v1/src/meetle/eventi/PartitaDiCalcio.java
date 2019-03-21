package meetle.eventi;

import meetle.eventi.campi.CampoGenere;
import meetle.eventi.campi.Campo;
import meetle.eventi.campi.CampoRange;

public class PartitaDiCalcio extends Evento {
    
    public static final int NUM_CAMPI_AGGIUNTIVI = 2;
    public static final int I_GENERE = 0, I_FASCIA_ETA = 1;
    
    protected Campo[] campiExtra;

    public PartitaDiCalcio() {
        super();
        
        this.nome = "Partita di Calcio";
        this.descrizione = "Sport noioso perchè non c'è la figa a meno che ... ecco";
        
        campiExtra = new Campo[NUM_CAMPI_AGGIUNTIVI];
        campiExtra[I_GENERE] = new CampoGenere("Genere", "Genere dei giocatori");
        campiExtra[I_FASCIA_ETA] = new CampoRange("Fascia di Eta", "Eta minima e massima per partecipare");
    }

    @Override
    public void setValoreDaString(int indice, String valore) {
        super.setValoreDaString(indice, valore);
        if(indice >= NUM_CAMPI_FISSI)
            campiExtra[indice-NUM_CAMPI_FISSI].setValoreDaString(valore);
    }
    
}
