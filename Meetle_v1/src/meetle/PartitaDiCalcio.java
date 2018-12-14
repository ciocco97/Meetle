package meetle;

import meetle.campi.CampoDurata;
import meetle.campi.CampoOra;
import meetle.campi.CampoInt;
import meetle.campi.CampoString;
import meetle.campi.CampoRange;
import meetle.campi.CampoData;
import meetle.campi.CampoGenere;

public class PartitaDiCalcio extends Evento {
    private CampoGenere genere;
    private CampoRange fasciaEta;

    public PartitaDiCalcio(CampoGenere genere, CampoRange fasciaEta, String nome, 
            String descrizione, CampoString titolo, CampoString luogo, CampoString note, 
            CampoInt numPartecipanti, CampoInt quotaIndividuale, CampoInt compresoQuota, 
            CampoData termineIscrizione, CampoData data, CampoData dataConclusiva, CampoOra ora, 
            CampoOra oraConclusiva, CampoDurata durata) {
        super(nome, descrizione, titolo, luogo, note, numPartecipanti, quotaIndividuale, 
                compresoQuota, termineIscrizione, data, dataConclusiva, ora, oraConclusiva, durata);
        
        campiAggiuntivi.add(genere);
        campiAggiuntivi.add(fasciaEta);        
        
    }
    
    
    
}
