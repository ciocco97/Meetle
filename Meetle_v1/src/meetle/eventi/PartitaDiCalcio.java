package meetle.eventi;

import java.util.Arrays;
import static meetle.eventi.Evento.SEPARATORE_CAMPI;
import meetle.eventi.campi.CampoGenere;
import meetle.eventi.campi.Campo;
import meetle.eventi.campi.CampoRange;

public class PartitaDiCalcio extends Evento {
    
    public static final int NUM_CAMPI_AGGIUNTIVI = 2;
    public static final int I_GENERE = 12, I_FASCIA_ETA = 13;
    
    protected Campo[] campiExtra;

    public PartitaDiCalcio() {
        super();
        
        this.nome = "Partita di Calcio";
        this.descrizione = "Sport perloppiù maschile o comunque sappiamo guidare ecco...";
        
        campiExtra = new Campo[NUM_CAMPI_AGGIUNTIVI];
        campiExtra[I_GENERE-NUM_CAMPI_FISSI] = new CampoGenere("Genere", "Genere dei giocatori");
        campiExtra[I_FASCIA_ETA-NUM_CAMPI_FISSI] = new CampoRange("Fascia di Eta", "Eta minima e massima per partecipare");
    }
    @Override
    public void setValoreDaString(int indice, String valore) {
        super.setValoreDaString(indice, valore);
        if(indice >= NUM_CAMPI_FISSI)
            campiExtra[indice-NUM_CAMPI_FISSI].setValoreDaString(valore);
    }
    
    public String toString()
    {
        return super.toString() + Arrays.stream(campiExtra)
                .filter(c -> !c.toString().equals(""))
                .map(c -> c.toString() + SEPARATORE_CAMPI)
                .reduce("", String::concat);  
    }
    
}
