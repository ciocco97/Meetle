package meetle;

import meetle.campi.CampoString;
import meetle.campi.CampoRange;
import meetle.campi.CampoGenere;
import meetle.campi.tempo.Data;
import meetle.campi.tempo.Durata;
import meetle.campi.tempo.Ora;

public class PartitaDiCalcio extends Evento {

    public PartitaDiCalcio(String nome, String descrizione, String titolo, 
            int numPartecipanti, Data termineIscrizione, String luogo,
            Data data, Ora ora, Durata durata, int quotaIndividuale, 
            int compresoQuota, Data dataConclusiva, Ora oraConclusiva,
            String note, int indiceGenere, int minEta, int maxEta) {
        super(nome, descrizione, titolo, numPartecipanti, termineIscrizione, 
                luogo, data, ora, durata, quotaIndividuale, compresoQuota, 
                dataConclusiva, oraConclusiva, note);
        
        campiAggiuntivi.add(new CampoGenere(indiceGenere));
        campiAggiuntivi.add(new CampoRange(minEta, maxEta));
    }
    
    public PartitaDiCalcio(String titolo){
        super();
        campiFissi[I_TITOLO] = new CampoString(titolo);
        
        this.nome = "Partita di calcio";
    }   
    
}
